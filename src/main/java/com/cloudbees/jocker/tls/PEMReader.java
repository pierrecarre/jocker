package com.cloudbees.jocker.tls;

import org.apache.http.conn.ssl.SSLInitializationException;
import sun.misc.BASE64Decoder;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class PEMReader {
    public static X509Certificate readCertificate(File certFile) {
        final X509Certificate cert;
        try (FileInputStream cis = new FileInputStream(certFile)) {
            cert = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(cis);
        } catch (IOException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        } catch (CertificateException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
        return cert;
    }

    public static PrivateKey readPrivateKey(File keyFile) {
        final PrivateKey key;
        try (FileInputStream fis = new FileInputStream(keyFile);
             DataInputStream dis = new DataInputStream(fis);
        ) {

            byte[] keyBytes = new byte[(int) keyFile.length()];
            dis.readFully(keyBytes);
            String replace = new String(keyBytes, "UTF-8")
                    .replace("-----BEGIN RSA PRIVATE KEY-----\n", "")
                    .replace("-----END RSA PRIVATE KEY-----", "");
            KeySpec keySpec = getRSAKeySpec(new BASE64Decoder().decodeBuffer(replace));
            key = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (IOException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
        return key;
    }

    private static RSAPrivateCrtKeySpec getRSAKeySpec(byte[] keyBytes) throws IOException  {

        DerParser parser = new DerParser(keyBytes);

        Asn1Object sequence = parser.read();
        if (sequence.getType() != DerParser.SEQUENCE)
            throw new IOException("Invalid DER: not a sequence"); //$NON-NLS-1$

        // Parse inside the sequence
        parser = sequence.getParser();

        parser.read(); // Skip version
        BigInteger modulus = parser.read().getInteger();
        BigInteger publicExp = parser.read().getInteger();
        BigInteger privateExp = parser.read().getInteger();
        BigInteger prime1 = parser.read().getInteger();
        BigInteger prime2 = parser.read().getInteger();
        BigInteger exp1 = parser.read().getInteger();
        BigInteger exp2 = parser.read().getInteger();
        BigInteger crtCoef = parser.read().getInteger();

        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(
                modulus, publicExp, privateExp, prime1, prime2,
                exp1, exp2, crtCoef);

        return keySpec;
    }
}
