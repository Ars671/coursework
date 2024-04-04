package com.example.cruddemo.repositories;


import com.example.cruddemo.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
    
//    List<Cantract> findByName(String name);
    
}
