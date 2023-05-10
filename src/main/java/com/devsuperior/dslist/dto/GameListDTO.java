package com.devsuperior.dslist.dto;

import org.springframework.beans.BeanUtils;

import com.devsuperior.dslist.entities.GameList;

public class GameListDTO {
	private Long id;
	private String name;
	
	
	public GameListDTO() {
		
	}


	public GameListDTO(GameList entity) {
		id = entity.getId();
		name = entity.getName();
		//ou BeanUtils.copyProperties(entity, this);//copia os mesmos atributos para a target se tiver o mesmo nome
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	
	
}
