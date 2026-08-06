package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.ConsoleResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.model.Console;
import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.repositories.ConsoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    @Transactional(readOnly = true)
    public List<ConsoleResponseDto> findAllConsoles() {
        return consoleRepository.findAll().stream().map(ConsoleResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public ConsoleResponseDto findConsoleById(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Console not found. Is id correct?"));

        return new ConsoleResponseDto(console);
    }

    @Transactional(readOnly = true)
    public ConsoleResponseDto findConsoleByAcronym(String acronym) {
        Console console = consoleRepository.findByAcronym(acronym)
                .orElseThrow(() -> new RuntimeException("Console not found. Is Acronym correct?"));

        return new ConsoleResponseDto(console);
    }

    @Transactional
    public void deleteConsole(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Console not found. Is id correct?"));

        for (Game game : new HashSet<>(console.getGames())) {
            game.clearRelation(console);
        }

        consoleRepository.delete(console);
    }
}
