package maraisaferreira.com.github.RetroGamePlatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameConsolesUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.request.GameUpdateRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.GameSaveResponseDto;
import maraisaferreira.com.github.RetroGamePlatform.dto.response.PageResponse;
import maraisaferreira.com.github.RetroGamePlatform.service.GameService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Games")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/games")
public class GameController {
    private final GameService gameService;


    @Operation(
            summary = "Get all the games available in the database"
    )
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PageResponse<GameResponseDto>> findAllGames(
            @ParameterObject @PageableDefault(size = AppConstants.PAGE_SIZE, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(gameService.findAllGames(pageable));
    }


    @Operation(
            summary = "Get all games that match the specified name."
    )
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            value = "/part_name/{part}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PageResponse<GameResponseDto>> findGameByPartName(
            @PathVariable String part,
            @ParameterObject @PageableDefault(size = AppConstants.PAGE_SIZE, sort = {"name"}) Pageable pageable) {

        return ResponseEntity.ok(gameService.findGameByPartName(part, pageable));
    }

    @Operation(
            summary = "Get all the games for the specified console."
    )
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            value = "/console/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PageResponse<GameResponseDto>> findGameByConsole(
            @PathVariable Long id,
            @ParameterObject @PageableDefault(size = AppConstants.PAGE_SIZE, sort = {"name"}) Pageable pageable) {

        return ResponseEntity.ok(gameService.findGameByConsole(id, pageable));
    }

    @Operation(
            summary = "Get all the games for the specified type."
    )
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            value = "/type/{type}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PageResponse<GameResponseDto>> findGameByGameType(
            @PathVariable String type,
            @ParameterObject @PageableDefault(size = AppConstants.PAGE_SIZE, sort = {"name"}) Pageable pageable) {

        return ResponseEntity.ok(gameService.findGameByGameType(type, pageable));
    }

    @Operation(
            summary = "Get a specific game by its ID."
    )
    @PreAuthorize("hasRole('BASIC')")
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GameResponseDto> findGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findGameById(id));
    }

    @Operation(
            summary = "Save a new game to the database."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GameSaveResponseDto> saveGame(@Valid @RequestBody GameRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                gameService.saveGame(requestDto)
        );
    }

    @Operation(
            summary = "Updates one or more fields in the game specified by its ID."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GameResponseDto> updateGame(@PathVariable Long id,
                                                      @Valid @RequestBody GameUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.updateGame(id, requestDto));
    }


    @Operation(
            summary = "Add one or more consoles for which the game is available."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(
            value = "/{id}/add_consoles",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GameSaveResponseDto> addGameConsoles(@PathVariable Long id,
                                                               @Valid @RequestBody GameConsolesUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.addGameConsoles(id, requestDto));
    }

    @Operation(
            summary = "Remove one or more the consoles for which the game is no more available."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(
            value = "/{id}/remove_consoles",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GameSaveResponseDto> removeGameConsoles(@PathVariable Long id,
                                                                  @Valid @RequestBody GameConsolesUpdateRequestDto requestDto) {
        return ResponseEntity.ok(gameService.removeGameConsoles(id, requestDto));
    }

    @Operation(
            summary = "Removes the entire specified game."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);

        return ResponseEntity.noContent().build();
    }
}
