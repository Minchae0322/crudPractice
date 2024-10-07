package com.example.crudpractice.app.core.response;

import lombok.Data;

/**
 * 단일 객체 Api 응답
 * @param <T> 리소스 객체
 *
 * {
 *   "status": 200,
 *   "success": true,
 *   "data": {
 *     "id": 1,
 *     "user_id": "admin",
 *     "name": "관리자"
 *   }
 * }
 */
@Data
public class ApiResponse<T>  {

    private boolean result;

    private String message;
    private T data;

    public ApiResponse(String message) {
        this(message, null);
    }

    public ApiResponse(T data) {
        this("success", data);
    }

    public ApiResponse(boolean result, String message) {
        this(result, message, null);
    }

    public ApiResponse(String message, T data) {
        this(true, message, data);
    }

    public ApiResponse(boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }


}
