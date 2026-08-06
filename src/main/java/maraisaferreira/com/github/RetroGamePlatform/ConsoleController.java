package maraisaferreira.com.github.RetroGamePlatform;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.ConsoleResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.service.ConsoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/consoles")
public class ConsoleController {
    private final ConsoleService consoleService;

    @GetMapping
    public ResponseEntity<List<ConsoleResponseDto>> findAllConsoles(){
        return ResponseEntity.ok(consoleService.findAllConsoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsoleResponseDto> findConsoleById(@PathVariable Long id){
        return ResponseEntity.ok(consoleService.findConsoleById(id));
    }

    @GetMapping("/acronym/{acronym}")
    public ResponseEntity<ConsoleResponseDto> findConsoleByAcronym(@PathVariable String acronym){
        return ResponseEntity.ok(consoleService.findConsoleByAcronym(acronym));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable Long id){
        consoleService.deleteConsole(id);
        return ResponseEntity.noContent().build();
    }
}
