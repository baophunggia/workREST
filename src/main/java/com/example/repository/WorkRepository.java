package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Work;


/**
 * WorkRepository
 * @author Bao
 *
 */
public interface WorkRepository extends JpaRepository<Work, Integer> {

}
