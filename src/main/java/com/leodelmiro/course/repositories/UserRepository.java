package com.leodelmiro.course.repositories;

import com.leodelmiro.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
