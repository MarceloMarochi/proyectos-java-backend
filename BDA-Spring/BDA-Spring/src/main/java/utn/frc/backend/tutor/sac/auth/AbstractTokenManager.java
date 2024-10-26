package utn.frc.backend.tutor.sac.auth;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import java.security.Key;
import java.util.Map;

public abstract class AbstractTokenManager implements TokenManager {
    protected static final Depth DEFAULT_DEPTH = Depth.DEPTH256;
    protected String ALG;
    protected final String KEY_ID;
    protected final long DEFAULT_TTL;

    public AbstractTokenManager(String keyId, long defaultTtl) {
        KEY_ID = keyId;
        DEFAULT_TTL = defaultTtl;
    }

    @Override
    public String createSignedToken(
            String issuer,
            String audience,
            String jwtId,
            String subject,
            Map<String, Object> otherClaims
            ) throws JoseException, TokenManagerException {
        return createSignedToken(issuer, audience, jwtId, subject, otherClaims, DEFAULT_TTL);
    }

    @Override
    public String createSignedToken(
            String issuer,
            String audience,
            String jwtId,
            String subject,
            Map<String, Object> otherClaims,
            long ttl
    ) throws JoseException, TokenManagerException {
        JwtClaims claims = buildClaims(issuer, audience, jwtId, subject, otherClaims, ttl);
        JsonWebSignature jws = buildJsonWebSignature(claims);

        return jws.getCompactSerialization();
    }

    protected JwtClaims buildClaims(
            String issuer,
            String audience,
            String jwtId,
            String subject,
            Map<String, Object> otherClaims,
            long ttl
    ) {
        NumericDate issuedAt = NumericDate.now();
        NumericDate expiresAt = NumericDate.fromSeconds(issuedAt.getValue() + ttl);

        JwtClaims claims = new JwtClaims();
        if (issuer != null) {
            claims.setIssuer(issuer);
        }
        if (audience != null) {
            claims.setAudience(audience);
        }
        if (jwtId != null) {
            claims.setJwtId(jwtId);
        }
        if (subject != null) {
            claims.setSubject(subject); // user
        }

        if (otherClaims != null) {
            otherClaims.forEach((claim, value) -> claims.setClaim(claim, value));
        }

        claims.setIssuedAt(issuedAt);
        claims.setExpirationTime(expiresAt);

        // https://bitbucket.org/b_c/jose4j/wiki/JWT%20Examples
        //claims.setIssuer("Issuer");  // who creates the token and signs it
        //claims.setAudience("Audience"); // to whom the token is intended to be sent
        //claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        //claims.setGeneratedJwtId(); // a unique identifier for the token
        //claims.setIssuedAtToNow();  // when the token was issued/created (now)
        //claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        //claims.setSubject("subject"); // the subject/principal is whom the token is about
        //claims.setClaim("email","mail@example.com"); // additional claims/attributes about the subject can be added
        //List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        //claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

        return claims;
    }

    protected JsonWebSignature buildJsonWebSignature(JwtClaims claims, Key key) throws TokenManagerException {
        if (key == null) {
            throw new TokenManagerException("Invalid [private]key");
        }

        JsonWebSignature jws = new JsonWebSignature();

        // signature
        jws.setAlgorithmHeaderValue(ALG);
        jws.setKeyIdHeaderValue(KEY_ID);
        jws.setKey(key);

        // claims
        jws.setPayload(claims.toJson());

        return jws;
    }

    protected abstract JsonWebSignature buildJsonWebSignature(JwtClaims claims) throws TokenManagerException;

    protected Map<String, Object> processToken(String serializedToken, Key key) throws InvalidJwtException {
        // https://bitbucket.org/b_c/jose4j/wiki/JWT%20Examples
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setRequireSubject()
                //.setExpectedIssuer("Issuer") // whom the JWT needs to have been issued by
                //.setExpectedAudience("Audience") // to whom the JWT is intended for                .setVerificationKey(PRIVATE_KEY)
                .setVerificationKey(key)
                .setJwsAlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, ALG)
                .build();

        JwtClaims claims = jwtConsumer.processToClaims(serializedToken);

        return claims.getClaimsMap();
    }
}
