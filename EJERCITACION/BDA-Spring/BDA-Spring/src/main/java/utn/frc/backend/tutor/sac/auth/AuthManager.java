package utn.frc.backend.tutor.sac.auth;

import org.jose4j.base64url.Base64;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.JoseException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AuthManager {
    // todo: implementar (en serio) autenticación Y autorización
    //       (realms, roles, rules, assets, permissions, etc...)
    //       por ahora implementación simple de muestra

    public enum Permission {ListarPersonas, ListarAlumnos, ListarMaterias}

    private static final Map<String, Map<String, String>> USER_DATA = new HashMap<>() {{
       put("admin", new HashMap<>() {{
           put("pwd", "admin");
           put("role", "admin");
       }});
        put("docente", new HashMap<>() {{
            put("pwd", "docente");
            put("role", "docente");
        }});
        put("alumno", new HashMap<>() {{
            put("pwd", "alumno");
            put("role", "alumno");
        }});
    }};
    private static final Map<String, Map<Permission, Boolean>> ROLE_DATA = new HashMap<>() {{
        put("admin", new HashMap<>() {{
            put(Permission.ListarPersonas, Boolean.TRUE);
            put(Permission.ListarAlumnos, Boolean.TRUE);
            put(Permission.ListarMaterias, Boolean.TRUE);
        }});
        put("docente", new HashMap<>() {{
            put(Permission.ListarAlumnos, Boolean.TRUE);
            put(Permission.ListarMaterias, Boolean.TRUE);
        }});
        put("alumno", new HashMap<>() {{
            put(Permission.ListarMaterias, Boolean.TRUE);
        }});
    }};

    private TokenManager tokenManager;
    public AuthManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public String login(String usr, String pwd) {
        if (!USER_DATA.containsKey(usr)) {
            throw new AuthenticationException("Invalid credentials");
        }

        Map<String, String> userData = USER_DATA.get(usr);
        if (!userData.get("pwd").equals(pwd)) {
            throw new AuthenticationException("Invalid credentials");
        }

        String role = userData.get("role");

        try {
            return tokenManager.createSignedToken(
                    null, null, null,
                    usr,
                    new HashMap<>() {{ put("role", role); }}
                    );
        } catch (Exception e) { // JoseException, TokenManagerException
            throw new AuthenticationException(e.getMessage());
        }
    }

    public String login(Map<String, String> header) {
        String prefix = "Basic ";
        String auth = header.getOrDefault("authorization", "");
        if (!auth.contains(prefix)) {
            throw new AuthenticationException("Invalid header");
        }

        String encodedCredentials = auth.replace(prefix, "");
        String plainCredentials = new String(Base64.decode(encodedCredentials), StandardCharsets.UTF_8);
        String[] credentials = plainCredentials.split(":");
        if (credentials.length != 2) {
            throw new AuthenticationException("Invalid header");
        }

        return login(credentials[0], credentials[1]);
    }

    public boolean verifyPermission(Map<String, String> header, Permission permission) {
        Map<Permission, Boolean> permissions = getPermissions(header);
        return permissions.getOrDefault(permission, false);
    }

    public Map<Permission, Boolean> getPermissions(Map<String, String> header) {
        Map<String, Object> claimsMap = getClaimsMap(header);

        try {
            String role = (String) claimsMap.get("role");
            return ROLE_DATA.get(role);
        } catch (Exception e) {
            throw new AuthorizationException(e.getMessage());
        }
    }

    public Map<Permission, Boolean> getPermissions(String role) {
        return ROLE_DATA.get(role);
    }

    public Map<String, Object> getClaimsMap(Map<String, String> header) {
        String prefix = "Bearer ";
        String auth = header.getOrDefault("authorization", "");
        String token = auth.replace(prefix, "");

        if (!auth.contains(prefix)) {
            throw new AuthenticationException("Invalid header");
        }

        try {
            return tokenManager.processToken(token);
        } catch (InvalidJwtException e) {
            throw new AuthenticationException("Invalid token");
        }
    }

}
