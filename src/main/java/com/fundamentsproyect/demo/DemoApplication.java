package com.fundamentsproyect.demo;

import bean.MyBean;
import bean.MyBeanWithDependency;
import bean.MyBeanWithProperties;
import com.fundamentsproyect.demo.component.ComponentDependency;
import com.fundamentsproyect.demo.entity.User;
import com.fundamentsproyect.demo.pojo.UserPojo;
import com.fundamentsproyect.demo.repository.UserRepository;
import com.fundamentsproyect.demo.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {// inyectado dependencia con spring boot

    private final Log LOGGER = LogFactory.getLog(DemoApplication.class);
    private final ComponentDependency componentDependency; // llamar la interfaz
    private final MyBean myBean;
    private final MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserService userService;

    public DemoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
                           UserPojo userPojo, UserRepository userRepository,UserService userService) // constrcutor
    {
        this.myBean = myBean; // al bean de esta clase se le asigna el bean que llega por parametros
        this.componentDependency = componentDependency; // e
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args){
       // ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();
    }
    private void saveWithErrorTransactional(){

        User test1 = new User("Test1Transactional1","Test1Transactional1@domain.com",LocalDate.now());
        User test2 = new User("Test2Transactional1","Test2Transactional1@domain.com",LocalDate.now());
        User test3 = new User("Test1Transactional1","Test3Transactional1@domain.com",LocalDate.now());
        User test4 = new User("Test4Transactional1","Test4Transactional1@domain.com",LocalDate.now());
        List<User> users = Arrays.asList(test1,test2,test3,test4);

        try {
            userService.saveTransactional(users);
        }catch (Exception e){
            LOGGER.error("Esta es una excepcion dentro del metodo transaccion" + e);
        }
        userService.saveTransactional(users);
        userService.getAllUser().stream().forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional"
        + user));

    }

    private void getInformationJpqlFromUser()
    {
      /* LOGGER.info( "Usuario con el metodo findByUserEmail" + userRepository.findByUserEmail("Saray@domain.com ")
                       .orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

       userRepository.findAndSort("user", Sort.by("id").descending()).stream()
               .forEach(user-> LOGGER.info("usuario con metodo sort" + user));

       userRepository.findByName("Luisa").stream().forEach(user-> LOGGER.info("usuario con query method" + user));

       LOGGER.info("usuario con query method findByEmailAndName" +
                       userRepository.findByEmailAndName("luisa@domain.com", "Luisa")
                               .orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

       userRepository.findByNameLike("%l%").stream().forEach(user -> LOGGER.info("Ususario findByNameLike " + user));

        userRepository.findByNameOrEmail(null, "luisa@domain.com").stream()
                .forEach(user -> LOGGER.info("Ususario findByNameOrEmail " + user));*/
        userRepository.findByBirthDateBetween(LocalDate
               .of(2022,03,12),LocalDate.of(2022,05,25)).stream()
                .forEach(user -> LOGGER.info("Ususario con intervalo de fechas: " + user) );

        userRepository.findByNameContainingOrderByIdDesc("user").stream()
                .forEach(user -> LOGGER.info("Ususario encontrado con like y ordenado: " + user) );

        LOGGER.info("el usuario apartir de name parameter:" +userRepository.getAllByBirthDateAndEmail(LocalDate.of(2022,03,22),"luisa@domain.com")
                .orElseThrow(()-> new RuntimeException("No se encontro el usuario apartir de name parameter")));

    }
    private void saveUsersInDataBase(){
        User user1 = new User("Luisa", "luisa@domain.com", LocalDate.of(2022,03,22));
        User user2 = new User("Saray", "Saray@domain.com ", LocalDate.of(2022,04,23));
        User user3 = new User("Isa", "Isa@domain.com ", LocalDate.of(2022,05,24));
        User user4 = new User("user4", "Diego@domain.com ", LocalDate.of(2022,03,25));
        User user5 = new User("user5", "Valentina@domain.com ", LocalDate.of(2022,10,27));
        User user6 = new User("Karen", "Karen@domain.com ", LocalDate.of(2022,9,29));
        User user7 = new User("user7", "Andres@domain.com ", LocalDate.of(2022,8,15));
        User user8 = new User("user8", "Lina@domain.com ", LocalDate.of(2022,02,12));
        List<User> list = Arrays.asList(user1, user2,user3,user4,user5,user6,user7,user8);
        list.stream().forEach(userRepository::save);
    }

    private void ejemplosAnteriores() {
        componentDependency.saludar(); // llamado la primera dependencia inyectada
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
        try{
            //error
            int value = 10/0;
            LOGGER.info("Mi valor : " + value );
        }catch (Exception nombreex){
            LOGGER.error("Esto es un error al dividir por cero :" + nombreex.getMessage());
        }
    }
}
