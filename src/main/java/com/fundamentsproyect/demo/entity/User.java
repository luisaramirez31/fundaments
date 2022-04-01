package com.fundamentsproyect.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "userT")
public class User
{
    @Id // creamos un id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto es para que se auto incremente cuando creamos un usuario
    @Column( name = "id_user" , nullable = false,unique = true) // nombre de columna
    private Long id;

    @Column(length = 50) // se van a representar como columnas a nivel de base de datos
    private String name;

    @Column(length = 50, unique = true)
    private String email;

    //@Column(length = 50)
    private LocalDate birthDate; // no tiene length porque es tipo date

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER) // un usuario va ir a muchos post
     @JsonManagedReference // esta anotacion es para que cuanso accedamos a este servicio a nivel de servicio rest no nos de un error relacionado con esta goberflow
    private List<Post> post = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", post=" + post +
                '}';
    }
}
