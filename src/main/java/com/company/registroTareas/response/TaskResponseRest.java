package com.company.registroTareas.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseRest extends ResponseRest{

	private TaskResponse tareaResponse = new TaskResponse();
}
