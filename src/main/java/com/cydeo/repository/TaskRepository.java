package com.cydeo.repository;

import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Long> {


    List<Task>findAllByTaskStatusIsNot(Status status);
    List<Task>findAllByTaskStatus(Status status);
}
