package com.company.registroTareas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.registroTareas.model.Tareas;
import com.company.registroTareas.response.TaskResponseRest;
import com.company.registroTareas.services.ITaskService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class TaskRestController {
	
	@Autowired
	private ITaskService service;
	/**
	 * get all Tareas
	 * @return
	 */
	@GetMapping("/tareas")
	public ResponseEntity<TaskResponseRest> searchTarea(){
		
		ResponseEntity<TaskResponseRest> response = service.search();
		return response;
	}
	/**
	 * get id Tareas
	 * @param id
	 * @return
	 */
	@GetMapping("/tareas/{id}")
	public ResponseEntity<TaskResponseRest> searchTareaById(@PathVariable Long id){
		
		ResponseEntity<TaskResponseRest> response = service.searchById(id);
		return response;
	}
	/**
	 * save Tareas
	 * @param Tareas
	 * @return
	 */
	@PostMapping("/tareas")
	public ResponseEntity<TaskResponseRest> saveTarea(@RequestBody Tareas tarea){
		
		ResponseEntity<TaskResponseRest> response = service.save(tarea);
		return response;
	}
	/**
	 * update Tareas
	 * @param Tareas, id
	 * @return
	 */
	@PutMapping("/tareas/{id}")
	public ResponseEntity<TaskResponseRest> updateTarea(@RequestBody Tareas tarea, @PathVariable Long id){
		
		ResponseEntity<TaskResponseRest> response = service.update(tarea, id);
		return response;
	}
	/**
	 * delete Tareas
	 * @param id
	 * @return
	 */
	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<TaskResponseRest> deleteCategory(@PathVariable Long id){
		
		ResponseEntity<TaskResponseRest> response = service.deleteById(id);
		return response;
	}

}
