package j$.util.stream;

import java.security.AccessController;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class P3 {
    static final boolean a = ((Boolean) AccessController.doPrivileged(new O3())).booleanValue();

    static void a(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}
