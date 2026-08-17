package com.android.tools.r8.internal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ya0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3119ya0 {
    public static final /* synthetic */ boolean h = true;
    public int a = 0;
    public final C2481r30 b = new C2481r30();
    public final Set c = AbstractC2780ub0.c();
    public final ArrayDeque d = new ArrayDeque();
    public final ArrayDeque e = new ArrayDeque();
    public final ArrayList f = new ArrayList();
    public final Function g;

    public C3119ya0(Function function) {
        this.g = function;
    }

    public final void a(Object obj) {
        Object objPop;
        C2481r30 c2481r30 = this.b;
        int i = this.a;
        this.a = i + 1;
        c2481r30.b(i, obj);
        this.c.add(obj);
        this.d.push(obj);
        this.e.push(obj);
        for (Object obj2 : (Iterable) this.g.apply(obj)) {
            if (!this.b.containsKey(obj2)) {
                a(obj2);
            } else if (this.c.contains(obj2)) {
                int iB = this.b.b(obj2);
                while (iB < this.b.b(this.e.peek())) {
                    this.e.pop();
                }
            }
        }
        if (this.e.peek() == obj) {
            Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(this.d.size()));
            do {
                objPop = this.d.pop();
                this.c.remove(objPop);
                setNewSetFromMap.add(objPop);
            } while (objPop != obj);
            this.f.add(setNewSetFromMap);
            this.e.pop();
        }
    }
}
