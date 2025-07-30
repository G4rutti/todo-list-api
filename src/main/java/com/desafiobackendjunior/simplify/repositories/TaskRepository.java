package com.desafiobackendjunior.simplify.repositories;

import com.desafiobackendjunior.simplify.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {

    @Query("SELECT t FROM TaskModel t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<TaskModel> findByTitleContainingIgnoreCase(@Param("query") String keyword);

}
