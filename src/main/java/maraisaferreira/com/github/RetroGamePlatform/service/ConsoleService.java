package maraisaferreira.com.github.RetroGamePlatform.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.ConsoleRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.ConsoleUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.ConsoleResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PageResponse;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomNotFoundException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.model.Console;
import maraisaferreira.com.github.RetroGamePlatform.model.Game;
import maraisaferreira.com.github.RetroGamePlatform.repositories.ConsoleRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    @Transactional(readOnly = true)
    public PageResponse<ConsoleResponseDto> findAllConsoles(Pageable pageable) {
        return PageResponse.from(consoleRepository.findAll(pageable), ConsoleResponseDto::new);
    }

    @Transactional(readOnly = true)
    public PageResponse<ConsoleResponseDto> findConsolesByPartName(String part, Pageable pageable) {
        return PageResponse.from(consoleRepository.findByPartName(part, pageable), ConsoleResponseDto::new);

    }

    @Transactional(readOnly = true)
    public ConsoleResponseDto findConsoleById(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Console"))
                );

        return new ConsoleResponseDto(console);
    }

    @Transactional
    public ConsoleResponseDto saveConsole(ConsoleRequestDto requestDto) {
        consoleRepository.findByName(requestDto.name())
                .ifPresent(found -> {
                    throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("name"));
                });

        if (Strings.isNotBlank(requestDto.acronym())) {
            consoleRepository.findByAcronym(requestDto.acronym())
                    .ifPresent(found -> {
                        throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("acronym"));
                    });
        }

        Console console = consoleRepository.save(new Console(
                null,
                requestDto.name(),
                requestDto.acronym(),
                requestDto.origin()
        ));

        return new ConsoleResponseDto(console);
    }

    @Transactional
    public ConsoleResponseDto updateConsole(Long id, ConsoleUpdateRequestDto requestDto) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Console")));

        if (Strings.isNotBlank(requestDto.name())) {
            consoleRepository.findByName(requestDto.name())
                    .filter(saved -> !saved.getId().equals(id))
                    .ifPresent(found -> {
                        throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("name"));
                    });

            console.setName(requestDto.name());
        }

        if (Strings.isNotBlank(requestDto.acronym())) {
            consoleRepository.findByAcronym(requestDto.acronym())
                    .filter(saved -> !saved.getId().equals(id))
                    .ifPresent(found -> {
                        throw new CustomBadRequestException(ExceptionMessages.getUniqueFieldMessage("acronym"));
                    });

            console.setAcronym(requestDto.acronym());
        }

        if (Strings.isNotBlank(requestDto.origin())) {
            console.setOrigin(requestDto.origin());
        }

        return new ConsoleResponseDto(console);

    }

    @Transactional
    public ConsoleResponseDto removeAcronym(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Console")));

        console.setAcronym(null);

        return new ConsoleResponseDto(console);
    }

    @Transactional
    public void deleteConsole(Long id) {
        Console console = consoleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ExceptionMessages.notFound("Console")));

        for (Game game : new HashSet<>(console.getGames())) {
            game.clearRelation(console);
        }

        consoleRepository.delete(console);
    }
}
