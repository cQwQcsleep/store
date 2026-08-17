package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Hl0 {
    public static final /* synthetic */ boolean d = true;
    public List a;
    public final N3[] b;
    public final C2543rl0 c;

    public Hl0(C2543rl0 c2543rl0, ArrayList arrayList) {
        this.c = c2543rl0;
        this.a = arrayList;
    }

    public final N3[] a() {
        if (d || this.b != null) {
            return this.b;
        }
        x1f.a();
        return null;
    }

    public final List b() {
        if (this.a == null) {
            N3[] n3Arr = this.b;
            C2543rl0[] c2543rl0Arr = new C2543rl0[n3Arr.length];
            for (int i = 0; i < n3Arr.length; i++) {
                N3 n3 = n3Arr[i];
                c2543rl0Arr[i] = n3 == null ? null : n3.value();
            }
            this.a = Arrays.asList(c2543rl0Arr);
        }
        return this.a;
    }

    public Hl0(C2543rl0 c2543rl0, N3[] n3Arr) {
        this.c = c2543rl0;
        this.b = n3Arr;
    }
}
