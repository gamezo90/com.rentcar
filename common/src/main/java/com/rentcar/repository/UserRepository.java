package com.rentcar.repository;

import com.rentcar.domain.Gender;
import com.rentcar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u")
    Optional<User> findByLogin(String login);

  //  User findByCredentialsLogin(String login);

    @Modifying
    @Query(value = "insert into rentcar.l_role_user(user_id, role_id) values (:user_id, :role_id)", nativeQuery = true)
    int createRoleRow(@Param("user_id") Long userId, @Param("role_id") Long roleId);

//    User update(User userToUpdate);

    List<User> findAllByOrderById();

    Optional<User> findByCredentialsLogin(String login);

    Optional<User> findByCredentialsEmail(String email);
}
