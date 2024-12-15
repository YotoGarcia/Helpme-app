package co.edu.iudigital.helmelud.security;


import co.edu.iudigital.helmelud.models.Usuario;
import co.edu.iudigital.helmelud.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {

            Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            String username = user.getUsername();
            String password = user.getPassword();
            log.info("{}", username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            log.info("{}", authenticationToken);

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException("Error al procesar la solicitud de autenticación", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add(Constants.AUTHORITIES, new ObjectMapper().writeValueAsString(roles))
                .add(Constants.USERNAME, username)
                .build();

        String jwt =
                Jwts.builder()
                        .subject(username)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + 3600000))
                        .issuedAt(new Date())
                        .signWith(Constants.SECRET_KEY)
                        .compact();

        response.setHeader(HttpHeaders.AUTHORIZATION, Constants.BEARER.concat(" ").concat(jwt));

        Map<String, String> body = new HashMap<>();
        body.put(Constants.TOKEN, jwt);
        body.put(Constants.USERNAME, username);
        body.put(Constants.MESSAGE, Constants.SESSION_EXITOSO + username);

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put(Constants.MESSAGE, Constants.SESSION_ERROR);
        body.put(Constants.ERROR, failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
