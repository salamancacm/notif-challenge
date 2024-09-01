package com.gila.notifchallenge.repositories;

import com.gila.notifchallenge.models.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

    List<NotificationLog> findAllByOrderByTimestampDesc();
}
