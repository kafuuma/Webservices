package org.henry.webservice.rest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

@Path("jwt")
public class JWTResource {

    @Context
    SecurityContext securityContext;

    private final Logger logger = Logger.getLogger("JWTResource");

    @GET
    @Path("/generate-jwt")
    @Produces(MediaType.APPLICATION_JSON)
    public String generateJwt(){
       return generate( securityContext.getUserPrincipal().getName());
    }

    @POST
    @Path("validate-jwt")
    public void validateJWT(String jwt){
        validate(jwt);
    }

    private String generate(String userName){
        String fixedId = "aGeneratedStringID";
        String clearTextPassword="ftjgsjjhdsghjdsgyuiudiydsduosidfousdiuosfunufndsuudgu;gajhk;adifdugfubfofdoiufdouia";
        byte[] byteBasedPassPhrase = DatatypeConverter.parseBase64Binary(clearTextPassword);

        Key signatureKey = new SecretKeySpec(byteBasedPassPhrase, "HMACSHA256");

        JwtBuilder builder = Jwts.builder().setId(fixedId)
                .setIssuedAt(Date.from(Instant.now()))
                .setSubject(userName)
                .setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.HOURS)))
                .signWith(signatureKey);
        return builder.compact();
    }


    private void validate(String hiddenJwt){
        String fixedId = "aGeneratedStringID";
        String clearTextPassword="ftjgsjjhdsghjdsgyuiudiydsduosidfousdiuosfunufndsuudgu;gajhk;adifdugfubfofdoiufdouia";
        byte[] byteBasedPassPhrase = DatatypeConverter.parseBase64Binary(clearTextPassword);

        Key signatureKey = new SecretKeySpec(byteBasedPassPhrase, "HMACSHA256");

        JwtParser parser = Jwts.parser();
        Claims claims = parser.setSigningKey(signatureKey)
                .parseClaimsJws(hiddenJwt)
                .getBody();

        logger.info("The subject claim value "+ claims.getSubject());
    }
}
