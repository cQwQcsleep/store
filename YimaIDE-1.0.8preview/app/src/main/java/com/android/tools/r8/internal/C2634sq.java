package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2634sq extends AbstractC2720tq {
    public final ZA[] c;

    /* JADX WARN: Illegal instructions before constructor call */
    public C2634sq(int i, ZA[] zaArr) {
        int i2 = 1;
        int length = zaArr.length - 1;
        if (length != 0) {
            for (int i3 = 31; i3 >= 0; i3--) {
                if (((1 << i3) & length) != 0) {
                    i2 = 1 + i3;
                }
            }
            sle.a("Empty enum: ", zaArr.getClass());
            throw null;
        }
        super(i, i2);
        this.c = zaArr;
    }
}
