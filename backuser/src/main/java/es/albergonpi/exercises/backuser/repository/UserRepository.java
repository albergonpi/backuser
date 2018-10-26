package es.albergonpi.exercises.backuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albergonpi.exercises.backuser.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
