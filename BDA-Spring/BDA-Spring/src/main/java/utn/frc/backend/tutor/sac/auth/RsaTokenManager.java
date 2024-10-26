package utn.frc.backend.tutor.sac.auth;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.JoseException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

public class RsaTokenManager extends AbstractTokenManager implements TokenManager {
    public static final String PRIVATE_KEY_NOT_PROVIDED_MESSAGE = "Private key not provided";
    public static final String PUBLIC_KEY_NOT_PROVIDED_MESSAGE = "Public key not provided";

    private final Key PRIVATE_KEY;
    private final Key PUBLIC_KEY;

    public RsaTokenManager(String keyId, long defaultTtl) throws JoseException {
        this(keyId, defaultTtl, DEFAULT_DEPTH);
    }

    public RsaTokenManager(String keyId, long defaultTtl, Depth depth) throws JoseException {
        super(keyId, defaultTtl);

        ALG = depth == Depth.DEPTH512
                ? AlgorithmIdentifiers.RSA_USING_SHA512
                : depth == Depth.DEPTH384
                    ? AlgorithmIdentifiers.RSA_USING_SHA384
                    : AlgorithmIdentifiers.RSA_USING_SHA256;

        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        // https://bitbucket.org/b_c/jose4j/wiki/JWT%20Examples
        // Give the JWK a Key ID (kid), which is just the polite thing to do
        rsaJsonWebKey.setKeyId(keyId); // eg: "key0001"

        PRIVATE_KEY = rsaJsonWebKey.getPrivateKey();
        PUBLIC_KEY = rsaJsonWebKey.getPublicKey();
    }

    public RsaTokenManager(String keyId, long defaultTtl, Key publicKey) {
        this(keyId, defaultTtl, DEFAULT_DEPTH, publicKey);
    }

    public RsaTokenManager(String keyId, long defaultTtl, Depth depth, Key publicKey) {
        super(keyId, defaultTtl);

        ALG = depth == Depth.DEPTH512
                ? AlgorithmIdentifiers.RSA_USING_SHA512
                : depth == Depth.DEPTH384
                ? AlgorithmIdentifiers.RSA_USING_SHA384
                : AlgorithmIdentifiers.RSA_USING_SHA256;

        PUBLIC_KEY = publicKey;
        PRIVATE_KEY = null;
    }

    public RsaTokenManager(String keyId, long defaultTtl, String publicKey) throws TokenManagerException {
        this(keyId, defaultTtl, DEFAULT_DEPTH, publicKey);
    }

    public RsaTokenManager(String keyId, long defaultTtl, Depth depth, String publicKey) throws TokenManagerException {
        super(keyId, defaultTtl);

        ALG = depth == Depth.DEPTH512
                ? AlgorithmIdentifiers.RSA_USING_SHA512
                : depth == Depth.DEPTH384
                ? AlgorithmIdentifiers.RSA_USING_SHA384
                : AlgorithmIdentifiers.RSA_USING_SHA256;

        try {
            byte[] bytes = Base64.getDecoder().decode(publicKey);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            PUBLIC_KEY = factory.generatePublic(spec);
            PRIVATE_KEY = null;

        } catch (Exception e) { // NoSuchAlgorithmException, InvalidKeySpecException
            throw new TokenManagerException(e.getMessage());
        }
    }

    public Key getPublicKey() {
        return PUBLIC_KEY;
    }

    public String getPublicKeyString() {
        byte[] bytes = PUBLIC_KEY.getEncoded();
        String str64Key = ""
                //+ "-----BEGIN CERTIFICATE-----\n"
                //+ "-----BEGIN PUBLIC KEY-----\n"
                + Base64.getEncoder().encodeToString(bytes)
                //+ "\n-----END PUBLIC KEY-----\n"
                //+ "\n-----END CERTIFICATE-----\n"
                + "";
        return str64Key;
    }

    @Override
    protected JsonWebSignature buildJsonWebSignature(JwtClaims claims) throws TokenManagerException {
        return buildJsonWebSignature(claims, PRIVATE_KEY);
    }

    @Override
    public Map<String, Object> processToken(String serializedToken) throws InvalidJwtException {
        return processToken(serializedToken, PUBLIC_KEY);
    }
}
