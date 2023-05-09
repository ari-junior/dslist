package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

//@Component ou
@Service
public class GameService {
	
	@Autowired //injeção
	private GameRepository gamerepository;
	
	public List<GameMinDTO> findAll(){
		//var result = gamerepository.findAll(); ou
		List<Game> result = gamerepository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
		
	}

}
