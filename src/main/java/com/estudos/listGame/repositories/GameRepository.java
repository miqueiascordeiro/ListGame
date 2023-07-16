package com.estudos.listGame.repositories;

import com.estudos.listGame.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
