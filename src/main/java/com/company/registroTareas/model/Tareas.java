package com.company.registroTareas.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="task")
public class Tareas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5581892472769789443L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private Date fechaCreación;
	private boolean estado;
	
	
}
