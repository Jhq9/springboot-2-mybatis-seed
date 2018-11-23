package isa.qa.project.exception;

/**
 *  Service Exception
 *
 *  @author    May
 *  @date      2018/11/21 10:06
 *  @version   1.0
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
