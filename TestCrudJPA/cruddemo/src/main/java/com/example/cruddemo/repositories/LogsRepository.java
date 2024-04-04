package com.example.cruddemo.repositories;

import com.example.cruddemo.models.Logs;
import com.example.cruddemo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogsRepository extends JpaRepository<Logs, Integer> {

    List<Logs> findByAction(String action);

}
