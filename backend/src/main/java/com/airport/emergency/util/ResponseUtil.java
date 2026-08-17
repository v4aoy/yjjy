package com.airport.emergency.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 响应结果包装工具类
 */
@Component
public class ResponseUtil {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    /**
     * 生成成功响应
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status(SUCCESS)
                .message("Operation successful")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * 生成成功响应
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status(SUCCESS)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * 生成错误响应
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .status(ERROR)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * API响应类
     */
    public static class ApiResponse<T> {
        private String status;
        private String message;
        private T data;
        private LocalDateTime timestamp;

        public ApiResponse() {}

        public ApiResponse(String status, String message, T data, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.data = data;
            this.timestamp = timestamp;
        }

        public static <U> Builder<U> builder() {
            return new Builder<>();
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public static class Builder<T> {
            private String status;
            private String message;
            private T data;
            private LocalDateTime timestamp;

            public Builder<T> status(String status) {
                this.status = status;
                return this;
            }

            public Builder<T> message(String message) {
                this.message = message;
                return this;
            }

            public Builder<T> data(T data) {
                this.data = data;
                return this;
            }

            public Builder<T> timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ApiResponse<T> build() {
                return new ApiResponse<>(status, message, data, timestamp);
            }
        }
    }
}
