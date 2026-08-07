package maraisaferreira.com.github.RetroGamePlatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.ConsoleRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.ConsoleUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.ConsoleResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.ConsoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/consoles")
public class ConsoleController {
    private final ConsoleService consoleService;

    @GetMapping
    public ResponseEntity<List<ConsoleResponseDto>> findAllConsoles() {
        return ResponseEntity.ok(consoleService.findAllConsoles());
    }

    @GetMapping("/part_name/{part}")
    public ResponseEntity<List<ConsoleResponseDto>> findConsoleByPartName(@PathVariable String part) {
        return ResponseEntity.ok(consoleService.findConsolesByPartName(part));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsoleResponseDto> findConsoleById(@PathVariable Long id) {
        return ResponseEntity.ok(consoleService.findConsoleById(id));
    }

    @PostMapping
    public ResponseEntity<ConsoleResponseDto> saveConsole(@Valid @RequestBody ConsoleRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                consoleService.saveConsole(requestDto)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ConsoleResponseDto> updateConsole(@PathVariable Long id,
                                                            @Valid @RequestBody ConsoleUpdateRequestDto requestDto) {
        return ResponseEntity.ok(consoleService.updateConsole(id, requestDto));
    }

    @PatchMapping("/remove_acronym/{id}")
    public ResponseEntity<ConsoleResponseDto> setConsoleAcronymAsNull(@PathVariable Long id) {
        return ResponseEntity.ok(consoleService.setConsoleAcronymAsNull(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable Long id) {
        consoleService.deleteConsole(id);
        return ResponseEntity.noContent().build();
    }
}
