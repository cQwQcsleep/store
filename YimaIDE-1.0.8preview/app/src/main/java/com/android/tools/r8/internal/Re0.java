package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2543rl0;
import defpackage.jc4;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Re0 {
    public static final /* synthetic */ boolean g = true;
    public Map a;
    public Map b;
    public Set c;
    public Set d;
    public final HashSet e = new HashSet();
    public final Se0 f;

    public Re0(Se0 se0) {
        this.a = se0.b;
        this.b = se0.c;
        this.c = se0.d;
        this.d = se0.e;
        this.f = se0;
    }

    public final Se0 a() {
        boolean z = g;
        if (!z && !this.d.containsAll(this.c)) {
            x01.a("Escaping is not a subset of live string builders");
            return null;
        }
        if (!z && !this.d.containsAll(this.a.keySet())) {
            x01.a("Aliases is not a subset of live string builders");
            return null;
        }
        if (!z && !this.c.containsAll(this.e)) {
            x01.a("Unexpected value in newlyEscaping not in escaping");
            return null;
        }
        if (!z && !this.d.containsAll(this.b.keySet())) {
            x01.a("Escaped definitions should all be live");
            return null;
        }
        if (!z && !this.b.values().stream().allMatch(new Predicate() { // from class: c7c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a((Set) obj);
            }
        })) {
            x01.a("All known escaping definitions should be live string builders");
            return null;
        }
        Se0 se0 = this.f;
        if (se0.e != this.d || se0.d != this.c || se0.b != this.a || se0.c != this.b) {
            return new Se0(this.a, this.b, this.c, this.d, this.e);
        }
        se0.f.clear();
        return this.f;
    }

    public final void b() {
        if (this.a == this.f.b) {
            this.a = new HashMap(this.f.b.size() + 1);
            this.f.b.forEach(new BiConsumer() { // from class: a7c
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.c((C2543rl0) obj, (Set) obj2);
                }
            });
        }
    }

    public final void c() {
        if (this.b == this.f.c) {
            this.b = new HashMap(this.f.c.size() + 1);
            this.f.c.forEach(new BiConsumer() { // from class: b7c
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.d((C2543rl0) obj, (Set) obj2);
                }
            });
        }
    }

    public final void d() {
        if (this.d == this.f.e) {
            this.d = new HashSet(this.d);
        }
    }

    public final /* synthetic */ void d(C2543rl0 c2543rl0, Set set) {
        this.b.put(c2543rl0, AbstractC2780ub0.a((Iterable) set));
    }

    public final Re0 b(C2543rl0 c2543rl0, Set set) {
        c();
        ((Set) this.b.computeIfAbsent(c2543rl0, C0470Er.a(new jc4()))).addAll(set);
        return this;
    }

    public final /* synthetic */ void c(C2543rl0 c2543rl0, Set set) {
        this.a.put(c2543rl0, AbstractC2780ub0.a((Iterable) set));
    }

    public final void a(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        b();
        c();
        ((Set) this.a.computeIfAbsent(c2543rl0, C0470Er.a(new jc4()))).add(c2543rl1);
        ((Set) this.b.computeIfAbsent(c2543rl1, C0470Er.a(new jc4()))).add(c2543rl0);
    }

    public final Re0 a(C2543rl0 c2543rl0, Set set) {
        b();
        ((Set) this.a.computeIfAbsent(c2543rl0, C0470Er.a(new jc4()))).addAll(set);
        return this;
    }

    public final /* synthetic */ boolean a(Set set) {
        return this.d.containsAll(set);
    }
}
