package utn.frc.backend.tutor.sac.auth;

import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.JoseException;

import java.util.Map;

public interface TokenManager {
    public enum Depth {DEPTH256, DEPTH384, DEPTH512};

    public String createSignedToken(
            String issuer,
            String audience,
            String jwtId,
            String subject,
            Map<String, Object> otherClaims
    ) throws JoseException, TokenManagerException;

    public String createSignedToken(
            String issuer,
            String audience,
            String jwtId,
            String subject,
            Map<String, Object> otherClaims,
            long ttl
    ) throws JoseException, TokenManagerException;

    public Map<String, Object> processToken(String serializedToken) throws InvalidJwtException;
}
