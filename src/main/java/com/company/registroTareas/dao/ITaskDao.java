package com.company.registroTareas.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.registroTareas.model.Tareas;

public interface ITaskDao extends CrudRepository<Tareas, Long>{

}
