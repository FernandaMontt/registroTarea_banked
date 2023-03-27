package com.company.registroTareas.response;

import java.util.List;

import com.company.registroTareas.model.Tareas;

import lombok.Data;

@Data
public class TaskResponse {

	private List<Tareas> tarea;
	
}
