package com.company.registroTareas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.registroTareas.dao.ITaskDao;
import com.company.registroTareas.model.Tareas;
import com.company.registroTareas.response.TaskResponseRest;

@Service
public class TaskServiceImpl implements ITaskService{
	
	@Autowired
	private ITaskDao taskDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<TaskResponseRest> search() {

		TaskResponseRest response = new TaskResponseRest();
		
		try {
			
			List<Tareas> tarea = (List<Tareas>) taskDao.findAll();
			
			response.getTareaResponse().setTarea(tarea);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch(Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al no consultar");
			e.getStackTrace();
			return new ResponseEntity<TaskResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<TaskResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<TaskResponseRest> searchById(Long Id) {
		
		TaskResponseRest response = new TaskResponseRest();
		List<Tareas> list = new ArrayList<>();
				
		
		try {
			
			Optional<Tareas> tarea = taskDao.findById(Id);
			
			if(tarea.isPresent()) {
				list.add(tarea.get());
				response.getTareaResponse().setTarea(list);
				response.setMetadata("Respuesta ok", "00", "Tarea encontrada");
			}else {
				response.setMetadata("Respuesta no ok", "-1", "Tarea no encontrada");
				return new ResponseEntity<TaskResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al no consultar por id");
			e.getStackTrace();
			return new ResponseEntity<TaskResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<TaskResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TaskResponseRest> save(Tareas tareas) {
		
		TaskResponseRest response = new TaskResponseRest();
		List<Tareas> list = new ArrayList<>();
				
		
		try {
			
			Tareas tareaSaved = taskDao.save(tareas);
			
			if(tareaSaved != null) {
				list.add(tareaSaved);
				response.getTareaResponse().setTarea(list);;
				response.setMetadata("Respuesta ok", "00", "Tarea guardada");
			}else {
				response.setMetadata("Respuesta no ok", "-1", "Tarea no guardada");
				return new ResponseEntity<TaskResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch(Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al guardar tarea");
			e.getStackTrace();
			return new ResponseEntity<TaskResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<TaskResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TaskResponseRest> update(Tareas tarea, Long Id) {

		TaskResponseRest response = new TaskResponseRest();
		List<Tareas> list = new ArrayList<>();
				
		
		try {
			
			Optional<Tareas> tareaSearch = taskDao.findById(Id);
			
			if(tareaSearch.isPresent()) {
				//update register
				tareaSearch.get().setDescripcion(tarea.getDescripcion());
				tareaSearch.get().setFechaCreación(tarea.getFechaCreación());
				tareaSearch.get().setEstado(tarea.isEstado());
				
				Tareas tareaToUpdate = taskDao.save(tareaSearch.get());
				
				if(tareaToUpdate != null) {
					list.add(tareaToUpdate);
					response.getTareaResponse().setTarea(list);;
					response.setMetadata("Respuesta ok", "00", "Tarea actualizada");
				}else {
					response.setMetadata("Respuesta no ok", "-1", "Tarea no actualizada");
					return new ResponseEntity<TaskResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				response.setMetadata("Respuesta no ok", "-1", "Tarea no encontrada");
				return new ResponseEntity<TaskResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<TaskResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<TaskResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<TaskResponseRest> deleteById(Long Id) {
		
		TaskResponseRest response = new TaskResponseRest();
		
		try {
			
			taskDao.deleteById(Id);
			response.setMetadata("Respuesta ok", "00", "Tarea eliminada");
			
		} catch(Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<TaskResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<TaskResponseRest>(response, HttpStatus.OK);
	}
	
	

}
