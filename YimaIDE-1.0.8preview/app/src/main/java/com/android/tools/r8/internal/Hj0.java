package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Hj0 {
    public static final /* synthetic */ boolean a = true;

    public static void a(C2543rl0 c2543rl0, Sm0 sm0) {
        Iterator<AbstractC0890Uw> it = c2543rl0.b0().iterator();
        while (it.hasNext()) {
            sm0.b(new Gj0(it.next(), c2543rl0));
        }
        Iterator it2 = c2543rl0.a0().iterator();
        while (it2.hasNext()) {
            sm0.b(new Gj0((PW) it2.next(), c2543rl0));
        }
    }
}
