package uni.pu.fmi.legacy.dtos;

public record Response<T>(
        boolean success,
        String message,
        T data
) {
}
