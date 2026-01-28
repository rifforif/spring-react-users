package com.riffo.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.riffo.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
