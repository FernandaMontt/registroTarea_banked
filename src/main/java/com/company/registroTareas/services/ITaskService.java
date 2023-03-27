package com.company.registroTareas.services;

import org.springframework.http.ResponseEntity;

import com.company.registroTareas.model.Tareas;
import com.company.registroTareas.response.TaskResponseRest;

public interface ITaskService {

	public ResponseEntity<TaskResponseRest> search();
	public ResponseEntity<TaskResponseRest> searchById(Long Id);
	public ResponseEntity<TaskResponseRest> save(Tareas tarea);
	public ResponseEntity<TaskResponseRest> update(Tareas tarea, Long Id);
	public ResponseEntity<TaskResponseRest> deleteById(Long Id);
	
}
