package com.fundamentsproyect.demo.repository;

import com.fundamentsproyect.demo.dto.UserDto;
import com.fundamentsproyect.demo.entity.User;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long >
{
    @Query("Select u from User u WHERE u.email=?1 ") // primer query apártir de jpql
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin,LocalDate end);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.fundamentsproyect.demo.dto.UserDto(u.id,u.name,u.birthDate)"+
            " FROM User u"+
            " where u.birthDate=:parametroFecha"+
            " and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

    List<User> findAll();



}

