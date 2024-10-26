package utn.frc.backend.tutor.sac.auth;

import org.jose4j.jwt.consumer.InvalidJwtSignatureException;
import org.jose4j.lang.JoseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utn.frc.backend.tutor.sac.auth.HmacTokenManager;

import org.jose4j.lang.InvalidKeyException;
import utn.frc.backend.tutor.sac.auth.RsaTokenManager;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

@SpringBootTest
public class TockenManagerTests {
    private static String keyIdLaBella, keyIdDonRodrigo, keyIdRsa;
    private static String invalidSecret;
    private static String validSecretLaBella, validSecretDonRodrigo;
    private static HmacTokenManager managerInvalidSecret, managerLaBella, managerDonRodrigo;
    private static RsaTokenManager managerRsa;
    private static String subjectLaBella, subjectDonRodrigo, subjectRsa;
    private static long defaultTtl;


    @BeforeAll
    static void setup() throws JoseException {
        defaultTtl = 3600;

        keyIdLaBella = "keyLaBella";
        keyIdDonRodrigo = "keyDonRodrigo";
        keyIdRsa = "keyRsa";

        invalidSecret = "31 bytes (248 bits) long string";
        validSecretLaBella = ""
                + "La bella y graciosa moza marchose a lavar la ropa,          "
                + "la mojo en el arroyuelo y cantando la lavo,                 "
                + "la froto sobre una piedra la colgo de un abedul.            "
                + "............................................................"
                + "............................................................";
        validSecretDonRodrigo = ""
                + "Cantata del adelantado Don Rodrigo Diaz de Carreras,        "
                + "de sus hazanas en tierra de Indias,                         "
                + "de los singulares acontecimientos en que se vio envuelto,   "
                + "y de como se desenvolvio.                                   "
                + "............................................................";

        managerInvalidSecret = new HmacTokenManager("someKeyString", defaultTtl, invalidSecret);
        managerLaBella = new HmacTokenManager(keyIdLaBella, defaultTtl, validSecretLaBella);
        managerDonRodrigo = new HmacTokenManager(keyIdDonRodrigo, defaultTtl, validSecretDonRodrigo);
        managerRsa = new RsaTokenManager(keyIdRsa, defaultTtl);

        subjectLaBella = "subLaBella";
        subjectDonRodrigo = "subDonRodrigo";
        subjectRsa = "subRsa";
    }

    @Test
    void testHmacNotValidSecret() {
        InvalidKeyException thrown = Assertions.assertThrows(
                InvalidKeyException.class,
                () -> managerInvalidSecret.createSignedToken(
                        null, null, null,
                        "someSubjectString",
                        null
                ),
                "Se espera InvalidKeyException"
        );

        Assertions.assertEquals(
                thrown.getMessage(),
                "A key of the same size as the hash output (i.e. 256 bits for HS256) or larger MUST be used with the HMAC SHA algorithms but this key is only 248 bits"
        );
    }

    @Test
    void testHmac1LaBellaValidToken() {
        try {
            String serializedToken = managerLaBella.createSignedToken(
                    null, null, null,
                    subjectLaBella,
                    null
            );

            Map<String, Object> claims = managerLaBella.processToken(serializedToken);

            String subject = (String) claims.get("sub");

            Assertions.assertEquals(subject, subjectLaBella);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(false);
        }
    }

    @Test
    void testHmac2LaBellaInvalidToken() {
        try {
            String serializedToken = managerLaBella.createSignedToken(
                    null, null, null,
                    subjectLaBella,
                    null
            );

            InvalidJwtSignatureException thrown = Assertions.assertThrows(
                    InvalidJwtSignatureException.class,
                    () -> managerDonRodrigo.processToken(serializedToken),
                    "Se espera InvalidJwtSignatureException"
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(false);
        }
    }

    @Test
    void testHmac3DonRodrigoValidToken() {
        try {
            String serializedToken = managerDonRodrigo.createSignedToken(
                    null, null, null,
                    subjectDonRodrigo,
                    null
            );

            HmacTokenManager manager = managerDonRodrigo;
            Map<String, Object> claims = manager.processToken(serializedToken);

            String subject = (String) claims.get("sub");

            Assertions.assertEquals(subject, subjectDonRodrigo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(false);
        }
    }

    @Test
    void testRsa1Default() {
        try {
            String serializedToken = managerRsa.createSignedToken(
                    null, null, null,
                    subjectRsa,
                    null
            );

            Map<String, Object> claims = managerRsa.processToken(serializedToken);

            String subject = (String) claims.get("sub");

            Assertions.assertEquals(subject, subjectRsa);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(false);
        }
    }

    @Test
    void testRsa2WithPublicKey() {
        try {
            String serializedToken = managerRsa.createSignedToken(
                    null, null, null,
                    subjectRsa,
                    null
            );
            Key pubKey = managerRsa.getPublicKey();

            RsaTokenManager manager = new RsaTokenManager(keyIdRsa, defaultTtl, pubKey);

            Map<String, Object> claims = manager.processToken(serializedToken);

            String subject = (String) claims.get("sub");

            Assertions.assertEquals(subject, subjectRsa);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(false);
        }
    }

    @Test
    void testRsa3WithPublicKeyString() {
        try {
            String serializedToken = managerRsa.createSignedToken(
                    null, null, null,
                    subjectRsa,
                    null
            );
            String pubKeyString = managerRsa.getPublicKeyString();

            RsaTokenManager manager = new RsaTokenManager(keyIdRsa, defaultTtl, pubKeyString);

            Map<String, Object> claims = manager.processToken(serializedToken);

            String subject = (String) claims.get("sub");

            Assertions.assertEquals(subject, subjectRsa);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertTrue(false);
        }
    }

}
