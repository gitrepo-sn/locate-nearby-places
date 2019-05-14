package com.group.exception.handlers;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * <h1>GlobalExceptionHandler</h1> This java file works as global exception handler.
 *
 * @author Sandeep nair
 * @version 1.0
 * @since 2019-05-13
 */
	@ControllerAdvice
	public class GlobalExceptionHandler {
		private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());
	    
		/**
		 * This method is used to handle overall exception that might occur while serving the request.
		 * 
		 * @param ll This denotes longitude and latitude. Eg:40.7337621,-74.0095604
		 * @return String This returns JSON output to client.
		 */

		@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Unexpected error occurred. Please contact admin")
		@ExceptionHandler(Exception.class)
		public void  handleGlobalException(Exception ex){
			 LOGGER.log(Level.SEVERE, "Exception occur", ex);
			 System.out.println(ex);
				}
}
