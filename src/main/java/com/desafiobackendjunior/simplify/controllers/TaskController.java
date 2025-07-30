package com.desafiobackendjunior.simplify.controllers;

import com.desafiobackendjunior.simplify.dtos.TaskRecordDto;
import com.desafiobackendjunior.simplify.dtos.TaskStatusUpdateDto;
import com.desafiobackendjunior.simplify.enuns.Status;
import com.desafiobackendjunior.simplify.models.TaskModel;
import com.desafiobackendjunior.simplify.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/task")
    public ResponseEntity<TaskModel> createTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
        var taskModel = new TaskModel();
        taskModel.setName(taskRecordDto.name());
        taskModel.setDescription(taskRecordDto.description());
        taskModel.setPriority(taskRecordDto.priority());
        taskModel.setStatus(Status.PARADO);

        BeanUtils.copyProperties(taskRecordDto, taskModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskModel));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskModel>> getTasks(@RequestParam(required = false, name = "query") String query) {
        if (query != null && !query.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findByTitleContainingIgnoreCase(query));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll());
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable(name = "id") UUID id) {
        Optional<TaskModel> taskObj = taskRepository.findById(id);

        if (taskObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(taskObj.get());
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(name = "id") UUID id,
                                             @RequestBody @Valid TaskRecordDto taskRecordDto) {
        Optional<TaskModel> taskObj = taskRepository.findById(id);

        if (taskObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var taskModel = taskObj.get();
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskModel));
    }

    @PutMapping("/task/{id}/status")
    public ResponseEntity<Object> updateTaskStatus(@PathVariable(name = "id") UUID id,
                                                   @RequestBody @Valid TaskStatusUpdateDto taskStatusUpdateDto) {
        Optional<TaskModel> taskObj = taskRepository.findById(id);

        if (taskObj.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var taskModel = taskObj.get();
        taskModel.setStatus(taskStatusUpdateDto.status());

        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskModel));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id) {
        Optional<TaskModel> productO = taskRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        taskRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted with success!");
    }
}
