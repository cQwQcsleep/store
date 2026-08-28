package j$.util;

import java.security.AccessController;

/* loaded from: /workspace/unpacked/classes3.dex */
abstract class k0 {
    static final boolean a = ((Boolean) AccessController.doPrivileged(new j0())).booleanValue();

    static void a(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}
