package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ZB extends AbstractC0708Nw {
    public static final /* synthetic */ boolean f = true;
    public C0322w2 d;
    public List e = Collections.EMPTY_LIST;

    public final ZB a(List list) {
        if (f || list != null) {
            this.e = list;
            return (ZB) a();
        }
        x1f.a();
        return null;
    }

    public final ZB b(C2543rl0 c2543rl0) {
        int i = AbstractC0551Hu.c;
        return a(new Bc0(c2543rl0));
    }

    public final ZB a(C2543rl0... c2543rl0Arr) {
        return a(Arrays.asList(c2543rl0Arr));
    }

    public final ZB a(C0322w2 c0322w2) {
        this.d = c0322w2;
        return (ZB) a();
    }
}
