package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.model.Console;
import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameResponseDto> findAllGames(){
        return gameRepository.findAll().stream().map(GameResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public GameResponseDto findGameById(Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found. Is id correct?"));

        return new GameResponseDto(game);
    }

    @Transactional
    public void deleteGame(Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found. Is id correct?"));

        for (Console console : new HashSet<>(game.getConsoles())){
            game.clearRelation(console);
        }

        gameRepository.delete(game);
    }
}
