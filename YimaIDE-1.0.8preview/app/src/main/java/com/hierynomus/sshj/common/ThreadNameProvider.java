package com.hierynomus.sshj.common;

import java.net.InetSocketAddress;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ThreadNameProvider {
    private static final String DISCONNECTED = "DISCONNECTED";

    public static void setThreadName(Thread thread, RemoteAddressProvider remoteAddressProvider) {
        InetSocketAddress remoteSocketAddress = remoteAddressProvider.getRemoteSocketAddress();
        thread.setName(String.format("sshj-%s-%s-%d", thread.getClass().getSimpleName(), remoteSocketAddress == null ? DISCONNECTED : remoteSocketAddress.toString(), Long.valueOf(System.currentTimeMillis())));
    }
}
