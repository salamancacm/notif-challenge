package com.gila.notifchallenge.repositories;

import com.gila.notifchallenge.enums.Category;
import com.gila.notifchallenge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySubscribedCategoriesContains(Category category);
}
