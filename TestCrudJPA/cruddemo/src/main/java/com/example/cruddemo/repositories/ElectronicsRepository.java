package com.example.cruddemo.repositories;

import com.example.cruddemo.models.Electronics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectronicsRepository extends JpaRepository<Electronics, Integer> {
    
    List<Electronics> findByElectronicsname(String electronicsname);
    
}
