/**
 * 自定义异常
 */
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//通过状态码 指定404异常
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdminException extends RuntimeException {


    public AdminException() {
    }

    public AdminException(String message) {
        super(message);
    }

    public AdminException(String message, Throwable cause) {
        super(message, cause);
    }
}
