package org.henry.webservice.rest.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;


@Provider
public class ExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        StringBuilder responseBdy = new StringBuilder();

        violations.forEach(violation -> responseBdy.append("Value: "+ violation.getInvalidValue()+
                " failed validation because of "+ violation.getMessage()));
        Response respondWithVariable = Response.status(400, responseBdy.toString())
                .type(MediaType.APPLICATION_JSON).build();
        return respondWithVariable;
    }
}