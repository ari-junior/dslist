package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.controllers.projections.GameMinProjection;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

//@Component ou
@Service
public class GameListService {
	
	@Autowired //injeção
	private GameListRepository gameListRepository;
	
	@Autowired //injeção
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		//var result = gameListRepository.findAll(); ou
		List<GameList> result = gameListRepository.findAll();
		List<GameListDTO> dto = result.stream().map(x -> new GameListDTO(x)).toList();
		return dto;
		
	}
	@Transactional //garantia de que vai executar todas, por isso uma transactional
	public void move(Long listId ,int sourceIndex, int destinationSouce) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationSouce, obj);
		
		int min = sourceIndex < destinationSouce ? sourceIndex : destinationSouce;
		int max = sourceIndex < destinationSouce ? destinationSouce : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId,list.get(i).getId(),i);
		}
		
	}

}