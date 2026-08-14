package maraisaferreira.com.github.RetroGamePlatform.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public record PageResponse<T>(
        List<T> content,
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPages,
        Boolean first,
        Boolean last
) {
    public PageResponse(Page<T> page) {
        this(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    public static <T, R> PageResponse<R> from(Page<T> page, Function<T, R> mapper) {
        Page<R> dtoPage = page.map(mapper);
        return new PageResponse<>(dtoPage);
    }
}
