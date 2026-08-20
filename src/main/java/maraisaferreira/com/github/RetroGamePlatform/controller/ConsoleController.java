package maraisaferreira.com.github.RetroGamePlatform.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.ConsoleRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.ConsoleUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.ConsoleResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PageResponse;
import maraisaferreira.com.github.RetroGamePlatform.service.ConsoleService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Consoles")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/consoles")
public class ConsoleController {
    private final ConsoleService consoleService;

    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PageResponse<ConsoleResponseDto>> findAllConsoles(
            @ParameterObject @PageableDefault(size = AppConstants.PAGE_SIZE, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(consoleService.findAllConsoles(pageable));
    }


    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            value = "/part_name/{part}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PageResponse<ConsoleResponseDto>> findConsoleByPartName(
            @PathVariable String part,
            @ParameterObject @PageableDefault(size = AppConstants.PAGE_SIZE, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(consoleService.findConsolesByPartName(part, pageable));
    }


    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ConsoleResponseDto> findConsoleById(@PathVariable Long id) {
        return ResponseEntity.ok(consoleService.findConsoleById(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ConsoleResponseDto> saveConsole(@Valid @RequestBody ConsoleRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                consoleService.saveConsole(requestDto)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ConsoleResponseDto> updateConsole(@PathVariable Long id,
                                                            @Valid @RequestBody ConsoleUpdateRequestDto requestDto) {
        return ResponseEntity.ok(consoleService.updateConsole(id, requestDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(
            value = "/remove_acronym/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ConsoleResponseDto> removeAcronym(@PathVariable Long id) {
        return ResponseEntity.ok(consoleService.removeAcronym(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable Long id) {
        consoleService.deleteConsole(id);
        return ResponseEntity.noContent().build();
    }
}
