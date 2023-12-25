package com.gcxy.config.exception;

import com.gcxy.config.R;
import com.gcxy.config.ResultCondeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R errorHandler(MethodArgumentNotValidException exception){
        BindingResult bindingResult=exception.getBindingResult();
        //获取所有校验异常信息进行拼接返回
        String message=bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getCode).collect(Collectors.joining(","));
        return  R.Failed(ResultCondeEnum.REQUEST_PARAM_ERROR.getCode(),exception.getMessage());

    }
    /**
     * 处理全局异常
     * @param exception 异常
     * @return
     */

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception exception){
        return R.Failed(ResultCondeEnum.SYSTEM_EXCEPTION.getCode(),exception.getMessage());
    }
}
