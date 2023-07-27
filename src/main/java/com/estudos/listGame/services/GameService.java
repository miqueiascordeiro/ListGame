package com.estudos.listGame.services;

import com.estudos.listGame.dto.GameMinDTO;
import com.estudos.listGame.entities.Game;
import com.estudos.listGame.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GameService {
    @Autowired
    public GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();
        List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
        return dto;
    }
    public GameMinDTO getGameById(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if(game != null){
            return new GameMinDTO(game);
        }
        return null;
    }
    public Game createGame(Game gameCreatedto){
        Game savedGame = gameRepository.save(gameCreatedto);
        return new Game(savedGame);
    }
}
