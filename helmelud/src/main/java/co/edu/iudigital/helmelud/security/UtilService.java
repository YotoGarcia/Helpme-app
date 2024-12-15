package co.edu.iudigital.helmelud.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public static String obtenerUsuarioActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {

                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();
            } else {

                return principal.toString();
            }
        }

        return null;
    }
}