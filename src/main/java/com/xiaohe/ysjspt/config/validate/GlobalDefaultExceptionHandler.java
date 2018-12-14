package com.xiaohe.ysjspt.config.validate;

import com.xiaohe.ysjspt.entity.Result;
import com.xiaohe.ysjspt.modules.complaints.controller.ComplaintController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 捕获异常
 *
 * @author hzh
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * 声明要捕获的异常
     */
    @ExceptionHandler(XException.class)
    @ResponseBody
    public Result defaultExceptionHandler(XException e) {
        log.error(e.getMsg());
        Result result = new Result();
        return result.error(e.getRet(), e.getMsg());

    }

    /**
     * 入参校验异常
     *
     * @param e
     * @return Result
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result bindExceptionErrorHandler(MethodArgumentNotValidException e) throws Exception {

        Result r = new Result();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage() + ",");
            }
        }
        log.error(sb.toString());
        return r.error(-1, sb.toString());
    }

    /**
     * 不可读 转化异常
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Result bindExceptionErrorHandler(HttpMessageNotReadableException e) throws Exception {
        e.printStackTrace();
        Result r = new Result();
        String message = e.getMessage();
        if (!StringUtils.isEmpty(message)) {
            message = "请输入合法参数！";
        }
        log.error(message);
        return r.error(-1, message);
    }
}