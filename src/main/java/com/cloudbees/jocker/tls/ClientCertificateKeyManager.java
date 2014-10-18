package com.cloudbees.jocker.tls;

import javax.net.ssl.X509KeyManager;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
* @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
*/
public class ClientCertificateKeyManager implements X509KeyManager {

    private final X509Certificate cert;
    private final PrivateKey key;

    public ClientCertificateKeyManager(X509Certificate cert, PrivateKey key) {
        this.cert = cert;
        this.key = key;
    }

    @Override
    public String[] getClientAliases(String s, Principal[] principals) {
        return new String[0];
    }

    @Override
    public String chooseClientAlias(String[] strings, Principal[] principals, Socket socket) {
        return "docker";
    }

    @Override
    public String[] getServerAliases(String s, Principal[] principals) {
        return new String[0];
    }

    @Override
    public String chooseServerAlias(String s, Principal[] principals, Socket socket) {
        return null;
    }

    @Override
    public X509Certificate[] getCertificateChain(String s) {
        return new X509Certificate[] {cert};
    }

    @Override
    public PrivateKey getPrivateKey(String s) {
        return key;
    }
}
