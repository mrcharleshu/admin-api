package com.charles.admin;

import com.charles.admin.core.InvalidArgumentException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Set;

@RestControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);
    private static final String ERR_KEY = "err";
    private static final String MSG_KEY = "msg";
    private static final String SQL_OPERATE_ERROR = "数据库执行错误,参数错误";
    private static final String FILE_STORE_ERROR = "文件储存错误";
    private static final String WRONG_REQUEST_URL_ERROR = "Wrong API Request URL";

    private static final class ErrorCode {
        static final int RUNTIME_EXCEPTION = 1;
        static final int IO_EXCEPTION = 2;
        static final int SQL_EXCEPTION = 3;
        static final int WRONG_REQUEST_URL = 404;
        static final int WRONG_REQUEST_PARAM = 400;
    }

    @ExceptionHandler(value = InvalidArgumentException.class)
    public ModelMap invalidArgumentExceptionHandler(final InvalidArgumentException exception) {
        LOGGER.warn("InvalidArgumentException:{{}}", exception.getMessage());
        return sendResponseData(ErrorCode.WRONG_REQUEST_URL, WRONG_REQUEST_URL_ERROR);
    }

    /**
     * Controller中带有org.springframework.validation.annotation.Validated;
     * 方法中带有javax.validation.Valid;
     * 可以用对象列表的参数验证
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ModelMap constraintViolationExceptionHandler(final ConstraintViolationException exception) {
        LOGGER.warn("ConstraintViolationException:{{}}", exception.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            return sendResponseData(ErrorCode.RUNTIME_EXCEPTION, constraintViolations.iterator().next().getMessage());
        }
        return sendResponseData(ErrorCode.WRONG_REQUEST_PARAM, exception.getMessage());
    }

    /**
     * Controller中不带有org.springframework.validation.annotation.Validated;
     * 方法中带有javax.validation.Valid;
     * 可用于单个对象的验证
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ModelMap methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException exception) {
        LOGGER.warn("MethodArgumentNotValidException:{{}}", exception.getMessage());
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            return sendResponseData(ErrorCode.RUNTIME_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());
        }
        return sendResponseData(ErrorCode.WRONG_REQUEST_PARAM, exception.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ModelMap dataAccessExceptionHandler(DataAccessException exception) {
        printException(exception);
        return sendResponseData(ErrorCode.SQL_EXCEPTION, SQL_OPERATE_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelMap runtimeExceptionHandler(RuntimeException exception) {
        printException(exception);
        return sendResponseData(ErrorCode.RUNTIME_EXCEPTION, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ModelMap ioExceptionHandler(IOException exception) {
        printException(exception);
        return sendResponseData(ErrorCode.IO_EXCEPTION, FILE_STORE_ERROR);
    }

    /**
     * 有了这个方法，就不能使用框架自带的404错误界面跳转功能了，需要自定义实现了
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelMap handleError404(NoHandlerFoundException exception) {
        printException(exception);
        return sendResponseData(ErrorCode.WRONG_REQUEST_URL, WRONG_REQUEST_URL_ERROR);
    }

    /**
     * 前台请求参数不对（400）(Required String parameter 'location' is not present)
     * @param exception
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelMap handleError400(MissingServletRequestParameterException exception) {
        printException(exception);
        return sendResponseData(ErrorCode.WRONG_REQUEST_PARAM, exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ModelMap defaultExceptionHandler(final HttpServletRequest request, final Exception exception) {
        printException(exception);
        return sendResponseData(ErrorCode.RUNTIME_EXCEPTION, exception.getMessage());
    }

    private void printException(Exception exception) {
        exception.printStackTrace();
        LOGGER.error(exception.getLocalizedMessage());
        LOGGER.warn(exception.getClass().toString());
    }

    private ModelMap sendResponseData(int errorCode, String message) {
        ModelMap model = new ModelMap();
        model.put(ERR_KEY, errorCode);
        model.put(MSG_KEY, message);
        return model;
    }
}
