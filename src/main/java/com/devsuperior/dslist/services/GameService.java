package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.controllers.projections.GameMinProjection;
import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

//@Component ou
@Service
public class GameService {
	
	@Autowired //injeção
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)//deixa o banco de dados mais rápido, não bloqueio ele para a escrita, consulta apenas
	public GameDTO findById(Long gameId) {		
		Game result = gameRepository.findById(gameId).get();
		GameDTO dto = new GameDTO(result); //Dúvida, essa thread não fica em memória, ela termina dps?
		
		//falta tratamente se não encontrar o ID
		return dto;
		
	}

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		//var result = gamerepository.findAll(); ou
		List<Game> result = gameRepository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
		
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId){
		//var result = gamerepository.findAll(); ou
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
		
	}

}
