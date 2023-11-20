package edu.nyu.ratemyprofessor.user.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.nyu.ratemyprofessor.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}