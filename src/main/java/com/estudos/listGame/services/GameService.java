package com.estudos.listGame.services;

import com.estudos.listGame.dto.GameMinDTO;
import com.estudos.listGame.entities.Game;
import com.estudos.listGame.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    public GameRepository gameRepository;

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

    public Game updateGame(Long id, Game gameUpdate){
        Game existingGame = gameRepository.findById(id).orElse(null);
        if(existingGame != null) {
            existingGame.setTitle(gameUpdate.getTitle());
            existingGame.setYear(gameUpdate.getYear());
            existingGame.setGenre(gameUpdate.getGenre());
            existingGame.setPlatforms(gameUpdate.getPlatforms());
            existingGame.setScore(gameUpdate.getScore());
            existingGame.setImgUrl(gameUpdate.getImgUrl());
            existingGame.setShortDescription(gameUpdate.getShortDescription());
            existingGame.setLongDescription(gameUpdate.getLongDescription());

            return gameRepository.save(existingGame);
        }else {
            return null;
        }
    }

    public boolean deleteGame(Long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()){
            gameRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
