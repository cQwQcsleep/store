package com.shadow.okhttp3;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.io.CloseableKt;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Dns {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final Dns SYSTEM = new Companion.DnsSystem();

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public static final class DnsSystem implements Dns {
            @Override // com.shadow.okhttp3.Dns
            public List<InetAddress> lookup(String str) throws UnknownHostException {
                CloseableKt.checkNotNullParameter(str, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    CloseableKt.checkNotNullExpressionValue(allByName, "getAllByName(hostname)");
                    int length = allByName.length;
                    return length != 0 ? length != 1 ? ArraysKt.g(allByName) : CollectionsKt.d(allByName[0]) : EmptyList.INSTANCE;
                } catch (NullPointerException e) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
                    unknownHostException.initCause(e);
                    throw unknownHostException;
                }
            }
        }

        private Companion() {
        }
    }

    List<InetAddress> lookup(String str) throws UnknownHostException;
}
