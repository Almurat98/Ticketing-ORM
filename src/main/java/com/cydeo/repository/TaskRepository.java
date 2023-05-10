package com.cydeo.repository;

import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Long> {


    List<Task>findAllByTaskStatusIsNot(Status status);
    List<Task>findAllByTaskStatus(Status status);

    @Query("SELECT count(t)FROM Task t WHERE t.project.projectCode = ?1  AND t.taskStatus <> 'COMPLETED'")
    int totalNonCompletedTask(String projectCode);

    @Query(value = "SELECT COUNT(*) FROM tasks t join projects p ON " +
            "t.project_id = p.id WHERE p.project_code=?1 AND t.task_status = 'COMPLETE'" ,nativeQuery = true)
    int totalCompletedTask(String projectCode);
}
