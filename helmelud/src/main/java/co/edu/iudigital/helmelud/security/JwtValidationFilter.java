package co.edu.iudigital.helmelud.security;

import co.edu.iudigital.helmelud.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(Constants.BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = header.replace(Constants.BEARER.concat(" "), StringUtils.EMPTY);
        log.info(jwt);
        try {

            Claims claims = Jwts.parser()
                    .verifyWith(Constants.SECRET_KEY)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

            String username = claims.getSubject();

            Object authoritiesClaims = claims.get(Constants.AUTHORITIES);
            Collection<? extends GrantedAuthority> authorities = List.of(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(
                                    authoritiesClaims.toString().getBytes(),
                                    SimpleGrantedAuthority[].class
                            )
            );

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            chain.doFilter(request, response);

        } catch (JwtException e) {
            log.error(e.getMessage());

            Map<String, String> body = new HashMap<>();
            body.put(Constants.ERROR, e.getMessage());
            body.put(Constants.MESSAGE, "Token inválido");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

}
