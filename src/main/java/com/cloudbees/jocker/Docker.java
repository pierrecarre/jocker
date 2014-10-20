package com.cloudbees.jocker;

import com.cloudbees.jocker.tls.ClientCertificateKeyManager;
import com.cloudbees.jocker.tls.CustomX509TrustManager;
import com.cloudbees.jocker.tls.PEMReader;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;


/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class Docker {

    final String url;

    /**
     * @param url docker daemon we want to talk to. Can be remote (tcp://) or local (unix://)
     */
    public Docker(String url) {
        this.url = url.replace("tcp://","https://");
    }

    public Docker() {
        this(getDocker_host());
    }

    private static String getDocker_host() {
        String dh = System.getenv("DOCKER_HOST");
        return dh != null ? dh : "unix:///var/run/docker.sock";
    }

    protected CloseableHttpClient setupClient() throws FileNotFoundException {

        String path = System.getenv("DOCKER_CERT_PATH");

        File certFile = new File(path + "/cert.pem");
        final X509Certificate cert = PEMReader.readCertificate(certFile);

        File keyFile = new File(path + "/key.pem");
        final PrivateKey key = PEMReader.readPrivateKey(keyFile);


        SSLContext sslcontext;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(new KeyManager[] {new ClientCertificateKeyManager(cert, key)},
                            new TrustManager[] {new CustomX509TrustManager()},
                            new SecureRandom());

        } catch (final NoSuchAlgorithmException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        } catch (final KeyManagementException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        return HttpClients.custom()
            .setSSLSocketFactory(sslsf)
            .build();
    }

    /**
     * Uses <a href="https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-containers"/>
     */
    public ListContainers ps() throws IOException {
        return new ListContainers(this);
    }

    public InspectContainer inspect(String id) throws IOException {
        return new InspectContainer(this, id);
    }

}
