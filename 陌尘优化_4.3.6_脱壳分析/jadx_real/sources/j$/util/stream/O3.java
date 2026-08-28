package j$.util.stream;

import java.security.PrivilegedAction;

/* loaded from: /workspace/unpacked/classes3.dex */
public final /* synthetic */ class O3 implements PrivilegedAction {
    @Override // java.security.PrivilegedAction
    public final Object run() {
        return Boolean.valueOf(Boolean.getBoolean("org.openjdk.java.util.stream.tripwire"));
    }
}
