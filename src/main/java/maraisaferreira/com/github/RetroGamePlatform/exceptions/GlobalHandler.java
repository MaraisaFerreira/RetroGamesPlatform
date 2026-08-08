package maraisaferreira.com.github.RetroGamePlatform.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> genericExceptionHandler(Exception ex, WebRequest request) {
        return ResponseEntity.internalServerError().body(
                new ExceptionResponseDto(
                        Instant.now(),
                        ex.getMessage(),
                        request.getDescription(false).split("=")[1]
                )
        );
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ExceptionResponseDto> customBadRequestHandler(CustomBadRequestException ex,
                                                                        WebRequest request) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponseDto(
                        Instant.now(),
                        ex.getMessage(),
                        request.getDescription(false).split("=")[1]
                )
        );
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> customNotFoundHandler(CustomNotFoundException ex,
                                                                      WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponseDto(
                        Instant.now(),
                        ex.getMessage(),
                        request.getDescription(false).split("=")[1]
                )
        );
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(
                error -> error.getField() + ": " + error.getDefaultMessage()).toList();

        return ResponseEntity.badRequest().body(
                new ExceptionListResponseDto(
                        Instant.now(),
                        messages,
                        request.getDescription(false).split("=")[1]
                )
        );
    }
}
