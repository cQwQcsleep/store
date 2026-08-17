package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.InterfaceC1963l1;
import com.android.tools.r8.internal.InterfaceC2049m1;
import com.android.tools.r8.internal.Sm0;
import defpackage.axb;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2518rY<MethodRule extends InterfaceC2049m1, MethodRuleBuilder extends InterfaceC1963l1> {
    public static final /* synthetic */ boolean c = true;
    public final ConcurrentHashMap a = new ConcurrentHashMap();
    public final ConcurrentHashMap b = new ConcurrentHashMap();

    public final void a(Set set, Set set2, Sm0 sm0, C0322w2 c0322w2) {
        if (!set.add(c0322w2)) {
            boolean zRemove = set2.remove(c0322w2);
            if (c || zRemove) {
                return;
            }
            x1f.a();
            return;
        }
        set2.add(c0322w2);
        sm0.a.addFirst(c0322w2);
        for (C0322w2 c0322w3 : (Set) this.a.getOrDefault(c0322w2, Collections.EMPTY_SET)) {
            if (!c && set2.contains(c0322w3)) {
                x01.a("Found a cycle");
                return;
            }
            sm0.a(c0322w3);
        }
    }

    public void a(final Map<C0322w2, MethodRuleBuilder> map) {
        Set setKeySet = this.a.keySet();
        Sm0 sm0 = new Sm0(2);
        sm0.b((Iterable) setKeySet);
        sm0.a(new BiConsumer() { // from class: u7i
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(map, (C0322w2) obj, (Sm0) obj2);
            }
        });
    }

    public final /* synthetic */ void a(Map map, C0322w2 c0322w2, final Sm0 sm0) {
        InterfaceC1963l1 interfaceC1963l1 = (InterfaceC1963l1) map.get(c0322w2);
        for (final C0322w2 c0322w3 : (Set) this.a.getOrDefault(c0322w2, Collections.EMPTY_SET)) {
            ((InterfaceC1963l1) map.get(c0322w3)).a(interfaceC1963l1, new Runnable() { // from class: s7i
                @Override // java.lang.Runnable
                public final void run() {
                    sm0.c(c0322w3);
                }
            });
        }
    }

    public boolean a() {
        Set setC = AbstractC2780ub0.c();
        for (C0322w2 c0322w2 : this.a.keySet()) {
            if (setC.add(c0322w2)) {
                setC.addAll(a(c0322w2));
            }
        }
        return true;
    }

    public Set<C0322w2> a(C0322w2 c0322w2) {
        final Set setC = AbstractC2780ub0.c();
        final Set setC2 = AbstractC2780ub0.c();
        final Sm0 sm0 = new Sm0(2);
        sm0.b(c0322w2);
        sm0.a(new Consumer() { // from class: t7i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(setC, setC2, sm0, (C0322w2) obj);
            }
        });
        if (c || setC2.isEmpty()) {
            return sm0.a();
        }
        x1f.a();
        return null;
    }

    public void a(C0322w2 c0322w2, C0322w2 c0322w3) {
        ((Set) this.b.computeIfAbsent(c0322w2, IM.a(new axb()))).add(c0322w3);
        ((Set) this.a.computeIfAbsent(c0322w3, IM.a(new axb()))).add(c0322w2);
    }
}
