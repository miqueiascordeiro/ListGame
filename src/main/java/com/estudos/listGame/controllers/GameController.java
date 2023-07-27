package com.estudos.listGame.controllers;

import com.estudos.listGame.dto.GameMinDTO;
import com.estudos.listGame.entities.Game;
import com.estudos.listGame.exception.ErrorMessage;
import com.estudos.listGame.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class GameController {

    @Autowired
    public GameService gameService;
    @GetMapping(value = "/games")
    public List<GameMinDTO> findAll(){
        List<GameMinDTO> result = gameService.findAll();
        return result;
    }
    @GetMapping(value = "/games/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        GameMinDTO gameDTO = gameService.getGameById(id);
        if(gameDTO != null){
            return ResponseEntity.ok(gameDTO);
        }else{
            String Message = "Game not found for ID: " + id;
            ErrorMessage error = new ErrorMessage(Message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @PostMapping(value = "/games")
    public ResponseEntity<Game> createByGame(@Validated @RequestBody Game game) {
        Game createdGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }
}
