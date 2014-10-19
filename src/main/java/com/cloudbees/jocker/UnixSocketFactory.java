package com.cloudbees.jocker;

import org.apache.http.HttpHost;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class UnixSocketFactory implements ConnectionSocketFactory {

    File socket;

    @Override
    public Socket createSocket(HttpContext context) throws IOException {
        return AFUNIXSocket.newInstance();
    }

    @Override
    public Socket connectSocket(int connectTimeout, Socket sock, HttpHost host, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpContext context) throws IOException {
        sock.connect(new AFUNIXSocketAddress(socket));
        return sock;
    }

}
