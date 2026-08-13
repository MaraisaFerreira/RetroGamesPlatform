package maraisaferreira.com.github.RetroGamePlatform.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameConsolesUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameSaveResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomNotFoundException;
import maraisaferreira.com.github.RetroGamePlatform.model.Console;
import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.model.enums.GameType;
import maraisaferreira.com.github.RetroGamePlatform.repositories.ConsoleRepository;
import maraisaferreira.com.github.RetroGamePlatform.repositories.GameRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final ConsoleRepository consoleRepository;


    @Transactional(readOnly = true)
    public List<GameResponseDto> findAllGames() {
        return gameRepository.findAll().stream().map(GameResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findGameByPartName(String part) {
        return gameRepository.findByPartName(part).stream().map(GameResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findGameByConsole(Long id) {
        return gameRepository.findByConsolesId(id).stream().map(GameResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findGameByGameType(String gameTypeStr) {
        try {
            GameType gameType = GameType.valueOf(gameTypeStr.toUpperCase());
            return gameRepository.findByGameType(gameType).stream().map(GameResponseDto::new).toList();
        } catch (IllegalArgumentException e) {
            throw new CustomBadRequestException(ExceptionMessages.invalidGameTypeMessage);
        }
    }

    @Transactional(readOnly = true)
    public GameResponseDto findGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        return new GameResponseDto(game);
    }

    @Transactional
    public GameSaveResponseDto saveGame(GameRequestDto requestDto) {
        gameRepository.findByName(requestDto.name())
                .ifPresent(found -> {
                    throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("name"));
                });

        GameType gameType = GameType.UNKNOWN;
        if (Strings.isNotBlank(requestDto.gameType())) {
            try {
                gameType = GameType.valueOf(requestDto.gameType().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new CustomBadRequestException(ExceptionMessages.invalidGameTypeMessage);
            }
        }

        Set<Console> consoles = new HashSet<>();
        List<Long> idErrors = new ArrayList<>();
        for (Long id : requestDto.consoleIds()) {
            Optional<Console> console = consoleRepository.findById(id);
            console.ifPresentOrElse(consoles::add, () -> idErrors.add(id));
        }

        if (consoles.isEmpty()) {
            throw new CustomBadRequestException(ExceptionMessages.anyCorrectId);
        }

        Game game = gameRepository.save(new Game(
                requestDto.name(),
                requestDto.releaseYear(),
                gameType
        ));

        game.getConsoles().addAll(consoles);

        return new GameSaveResponseDto(
                idErrors.isEmpty() ?
                        "Game created" :
                        "Some consoles ids were wrong. Ids: " + idErrors,
                new GameResponseDto(game)
        );
    }

    @Transactional
    public GameResponseDto updateGame(Long id, GameUpdateRequestDto requestDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        if (Strings.isNotBlank(requestDto.name())) {
            gameRepository.findByName(requestDto.name())
                    .filter(saved -> !saved.getId().equals(id))
                    .ifPresent(found -> {
                        throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("name"));
                    });

            game.setName(requestDto.name());
        }

        if (Strings.isNotBlank(requestDto.gameType())) {
            try {
                GameType gameType = GameType.valueOf(requestDto.gameType().toUpperCase());
                game.setGameType(gameType);
            } catch (IllegalArgumentException e) {
                throw new CustomBadRequestException(ExceptionMessages.invalidGameTypeMessage);
            }
        }

        if (requestDto.releaseYear() != null) {
            game.setReleaseYear(requestDto.releaseYear());
        }

        return new GameResponseDto(game);
    }

    @Transactional
    public GameSaveResponseDto addGameConsoles(Long id,
                                               @Valid GameConsolesUpdateRequestDto requestDto) {

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        Set<Console> consoles = new HashSet<>();
        List<Long> errors = new ArrayList<>();

        for (Long consoleID : requestDto.consolesIDs()) {
            consoleRepository.findById(consoleID)
                    .ifPresentOrElse(consoles::add, () -> errors.add(consoleID));
        }

        if (!consoles.isEmpty()) {
            game.getConsoles().addAll(consoles);
        } else {
            throw new CustomBadRequestException(ExceptionMessages.anyCorrectId);
        }

        return new GameSaveResponseDto(
                errors.isEmpty() ?
                        "All consoles were updated." :
                        "The following IDs were invalid: " + errors.stream().map(String::valueOf)
                                .collect(Collectors.joining(", ")),
                new GameResponseDto(game)
        );
    }

    @Transactional
    public GameSaveResponseDto removeGameConsoles(Long id,
                                                  @Valid GameConsolesUpdateRequestDto requestDto) {

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        Set<Console> consoles = new HashSet<>();
        List<Long> errors = new ArrayList<>();

        for (Long consoleID : requestDto.consolesIDs()) {
            consoleRepository.findById(consoleID)
                    .ifPresentOrElse(consoles::add, () -> errors.add(consoleID));
        }

        if (!consoles.isEmpty()) {
            for (Console console : consoles) {
                game.clearRelation(console);
            }
        } else {
            throw new CustomBadRequestException(ExceptionMessages.anyCorrectId);
        }

        return new GameSaveResponseDto(
                errors.isEmpty() ?
                        "All consoles were updated." :
                        "The following IDs were invalid: " + errors.stream().map(String::valueOf)
                                .collect(Collectors.joining(", ")),
                new GameResponseDto(game)
        );
    }

    @Transactional
    public GameResponseDto addCover(Long id, String cover) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        gameRepository.findByCover(cover)
                .filter(saved -> !saved.getId().equals(game.getId()))
                .ifPresent(present -> {
                    throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("Image"));
                });

        if (Strings.isBlank(game.getCover()) ||
                !Objects.equals(game.getCover(), cover)) {
            game.setCover(cover);
        }

        return new GameResponseDto(game);
    }

    @Transactional
    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        for (Console console : new HashSet<>(game.getConsoles())) {
            game.clearRelation(console);
        }

        gameRepository.delete(game);
    }


    @Transactional
    public String getGameCover(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Game")));

        return game.getCover();
    }
}
