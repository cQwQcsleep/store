package com.android.tools.r8.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GD extends AbstractC1461f6 {
    public static final GD g;
    public static final GD h;
    public final boolean f;

    static {
        GD gd = new GD(new int[]{2, 0, 0}, false);
        g = gd;
        int i = gd.b;
        h = (i == 1 && gd.c == 9) ? new GD(new int[]{2, 0, 0}, false) : new GD(new int[]{i, gd.c + 1, 0}, false);
        new GD(new int[0], false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GD(int[] iArr, boolean z) {
        super(Arrays.copyOf(iArr, iArr.length));
        KB.c(iArr, "versionArray");
        this.f = z;
    }
}
