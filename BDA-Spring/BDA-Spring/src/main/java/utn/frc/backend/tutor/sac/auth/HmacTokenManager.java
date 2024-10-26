package utn.frc.backend.tutor.sac.auth;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.keys.HmacKey;

import org.jose4j.jws.JsonWebSignature;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

public class HmacTokenManager extends AbstractTokenManager implements TokenManager {

    private final Key KEY;

    public HmacTokenManager(String keyId, long defaultTtl, String secret) {
        this(keyId, defaultTtl, secret, DEFAULT_DEPTH);
    }

    public HmacTokenManager(String keyId, long defaultTtl, String secret, Depth depth) {
        super(keyId, defaultTtl);

        ALG = depth == Depth.DEPTH512
                ? AlgorithmIdentifiers.HMAC_SHA512
                : depth == Depth.DEPTH384
                    ? AlgorithmIdentifiers.HMAC_SHA384
                    : AlgorithmIdentifiers.HMAC_SHA256;

        KEY = new HmacKey(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected JsonWebSignature buildJsonWebSignature(JwtClaims claims) throws TokenManagerException {
        return buildJsonWebSignature(claims, KEY);
    }

    @Override
    public Map<String, Object> processToken(String serializedToken) throws InvalidJwtException {
        return processToken(serializedToken, KEY);
    }
}
