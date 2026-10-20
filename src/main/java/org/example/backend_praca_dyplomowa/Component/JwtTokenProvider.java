package org.example.backend_praca_dyplomowa.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenProvider {


        @Value("${app.jwt-secret}")
        private String jwtSecret;

        @Value("${app.jwt-expiration-milliseconds}")
        private long jwtExpirationDate;

        // generate JWT token
        public String generateToken(Authentication authentication){

            String username = authentication.getName();

            Date currentDate = new Date();

            Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

            List<String> roles = authentication.getAuthorities().stream().
                    map(GrantedAuthority::getAuthority).filter(authority-> authority.startsWith("ROLE_")).toList();

            for(String role : roles){
                System.out.println(role);
            }
            String token = Jwts.builder()
                    .subject(username)
                    .claim("roles",roles)
                    .issuedAt(new Date())
                    .expiration(expireDate)
                    .signWith(key())
                    .compact();

            return token;
        }

        private Key key(){
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        }

        // get username from JWT token
        public String getUsername(String token){

            return Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }
//
//    When you write .class after a class name, it references the class literal -
//    java.lang.Class object that represents information about a given class.
//
//    For example, if your class is Print, then Print.class is an object that represents the
//    class Print on runtime. It is the same object that is returned by the getClass() method of any
//    (direct) instance of Print.
//
//            Print myPrint = new Print();
//System.out.println(Print.class.getName());
//System.out.println(myPrint.getClass().getName());

        public Set<?> getRoles(String token){
            return Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("roles", Set.class);
        }

    public String getEmail(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

        // validate JWT token
        public boolean validateToken(String token){
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;

        }
    }
