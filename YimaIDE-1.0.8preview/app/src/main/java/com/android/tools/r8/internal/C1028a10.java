package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1028a10 {
    public static final /* synthetic */ boolean b = true;
    public final C2986wz a = new C2986wz(16);

    public final Z00 a(int i) {
        Z00 z00 = (Z00) this.a.get(i);
        if (z00 != null) {
            return z00;
        }
        int i2 = i & 255;
        Z00 z01 = i2 < 51 ? new Z00(i2, A6.b(i, Fcntl.S_IRUSR), A6.b(i, 512), A6.b(i, Fcntl.S_ISGID), A6.b(i, Fcntl.S_ISUID), A6.b(i, 4096)) : new C2221o10(i2, A6.b(i, Fcntl.S_IRUSR), A6.b(i, 512), A6.b(i, Fcntl.S_ISGID), A6.b(i, Fcntl.S_ISUID), A6.b(i, 4096));
        if (b || z01.h() == i) {
            this.a.a(i, z01);
            return z01;
        }
        x1f.a();
        return null;
    }
}
