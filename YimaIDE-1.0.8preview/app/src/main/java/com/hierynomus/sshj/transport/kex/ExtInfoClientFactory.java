package com.hierynomus.sshj.transport.kex;

import net.schmizz.sshj.common.Factory;
import net.schmizz.sshj.transport.kex.KeyExchange;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class ExtInfoClientFactory implements Factory.Named<KeyExchange> {
    public String getName() {
        return "ext-info-c";
    }

    public KeyExchange create() {
        return null;
    }
}
