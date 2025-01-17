package tpvv.controller;

import tpvv.controller.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global exception handler to manage application-wide exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoLogeadoException.class)
    public ModelAndView handleUsuarioNoLogeadoException(UsuarioNoLogeadoException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/login?error=not_logged_in");
        return mav;
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<String> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("error/404"); // Ensure you have a 404.html in src/main/resources/templates/error/
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

}

