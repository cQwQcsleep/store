package j$.util;

import java.io.Serializable;

/* renamed from: j$.util.g, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class C0083g extends RuntimeException {
    public static void a(String str, Serializable serializable) {
        throw new C0083g("Unsupported " + str + " :" + serializable);
    }
}
