package net.ict.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice   //"예외처리 클래스"  우선순위가 가장 높음.
@Log4j2
public class CommonExceptionAdvice {

    @ResponseBody //view 페이지가 아닌 반환값 그대로 클라이언트한테 return 하고 싶은 경우에 사용.
    @ExceptionHandler(NumberFormatException.class)    //어떤 예외상황일때 처리될지 지정하고 등록.
    public String exceptNumber(NumberFormatException numberFormatException){
        log.error("===================");
        log.error(numberFormatException.getMessage());
        return "NUMBER FORMAT EXCEPTION";
    }
    // 예외처리의 최상위 타입인 Exception타입을 처리하도록 구성.
    @ResponseBody //view 페이지가 아닌 반환값 그대로 클라이언트한테 return 하고 싶은 경우에 사용.
    @ExceptionHandler(Exception.class)    //어떤 예외상황일때 처리될지 지정하고 등록.
    public String exceptCommon(Exception exception){

        log.error("=======================");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>"+exception.getMessage()+"</li>");
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement ->{
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");
        return buffer.toString();
    }
    @ExceptionHandler(NoHandlerFoundException.class)//핸들링 할수 있는 페이지가 없는경우.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";    // view의 이름과 동일.
    }

}
