package j$.util.concurrent;

import java.security.PrivilegedAction;

/* loaded from: /workspace/unpacked/classes3.dex */
final class v implements PrivilegedAction {
    @Override // java.security.PrivilegedAction
    public final Object run() {
        return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
    }
}
