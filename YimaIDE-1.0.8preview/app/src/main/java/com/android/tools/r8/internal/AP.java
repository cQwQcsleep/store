package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0259n1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.F4;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AP;
import com.android.tools.r8.internal.C2637st;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AP {
    public static final /* synthetic */ boolean a = true;

    public static /* synthetic */ void a(C2552rt c2552rt, final C2552rt.a aVar, final C2552rt.a aVar2, final String str, Map map) {
        if (!c2552rt.h().containsKey(str)) {
            map.forEach(new BiConsumer() { // from class: b1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    aVar.a(str, (String) obj, (String) obj2);
                }
            });
        } else {
            final Map<String, String> map2 = c2552rt.h().get(str);
            map.forEach(new BiConsumer() { // from class: c1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    AP.a(map2, aVar2, str, aVar, (String) obj, (String) obj2);
                }
            });
        }
    }

    public static void b(C2552rt c2552rt, C2552rt c2552rt2, final C2552rt.a aVar, final C2552rt.a aVar2) {
        d(c2552rt, c2552rt2, aVar, aVar2);
        c(c2552rt, c2552rt2, aVar, aVar2);
        Map<com.android.tools.r8.graph.I2, C2637st> mapE = c2552rt.e();
        Map<com.android.tools.r8.graph.I2, C2637st> mapE2 = c2552rt2.e();
        Objects.requireNonNull(aVar);
        BiConsumer biConsumer = new BiConsumer() { // from class: i1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                aVar.a((I2) obj, (C2637st) obj2);
            }
        };
        Objects.requireNonNull(aVar2);
        a(mapE, mapE2, biConsumer, new BiConsumer() { // from class: i1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                aVar2.a((I2) obj, (C2637st) obj2);
            }
        });
        b(c2552rt.g(), c2552rt2.g(), new BiConsumer() { // from class: k1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                aVar.b((C0322w2) obj, (I2) obj2);
            }
        }, new BiConsumer() { // from class: k1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                aVar2.b((C0322w2) obj, (I2) obj2);
            }
        });
        b(c2552rt.i, c2552rt2.i, new BiConsumer() { // from class: l1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                aVar.a((C0322w2) obj, (I2) obj2);
            }
        }, new BiConsumer() { // from class: l1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                aVar2.a((C0322w2) obj, (I2) obj2);
            }
        });
        b(c2552rt.f(), c2552rt2.f(), new defpackage.m1(aVar), new defpackage.m1(aVar2));
        b(c2552rt.c(), c2552rt2.c(), new defpackage.z0(aVar), new defpackage.z0(aVar2));
        a(c2552rt.d(), c2552rt2.d(), new defpackage.j1(aVar), new defpackage.j1(aVar2));
        e(c2552rt, c2552rt2, aVar, aVar2);
        a(c2552rt, c2552rt2, aVar, aVar2);
    }

    public static void c(C2552rt c2552rt, final C2552rt c2552rt2, final C2552rt.a aVar, final C2552rt.a aVar2) {
        c2552rt.h().forEach(new BiConsumer() { // from class: f1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AP.a(c2552rt2, aVar2, aVar, (String) obj, (Map) obj2);
            }
        });
    }

    public static void d(C2552rt c2552rt, final C2552rt c2552rt2, final C2552rt.a aVar, final C2552rt.a aVar2) {
        c2552rt.i().forEach(new BiConsumer() { // from class: h1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AP.a(c2552rt2, aVar, aVar2, (String) obj, (String) obj2);
            }
        });
    }

    public static void e(C2552rt c2552rt, C2552rt c2552rt2, final C2552rt.a aVar, final C2552rt.a aVar2) {
        final Map<com.android.tools.r8.graph.I2, Set<C0322w2>> mapJ = c2552rt2.j();
        c2552rt.j().forEach(new BiConsumer() { // from class: y0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AP.a(mapJ, aVar, aVar2, (I2) obj, (Set) obj2);
            }
        });
    }

    public static void a(C2552rt c2552rt, C2552rt c2552rt2, final C2552rt.a aVar, final C2552rt.a aVar2) {
        final Map<C0322w2, com.android.tools.r8.graph.F4> mapB = c2552rt2.b();
        c2552rt.b().forEach(new BiConsumer() { // from class: d1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AP.a(mapB, aVar, aVar2, (C0322w2) obj, (F4) obj2);
            }
        });
    }

    public static void a(Map map, C2552rt.a aVar, C2552rt.a aVar2, C0322w2 c0322w2, com.android.tools.r8.graph.F4 f4) {
        if (map.get(c0322w2) == f4) {
            aVar.t.put(c0322w2, f4);
        } else {
            aVar2.t.put(c0322w2, f4);
        }
    }

    public static void a(Map map, C2552rt.a aVar, C2552rt.a aVar2, com.android.tools.r8.graph.I2 i2, Set set) {
        if (map.containsKey(i2)) {
            if (a || set.equals(map.get(i2))) {
                aVar.r.put(i2, set);
                return;
            } else {
                x1f.a();
                return;
            }
        }
        aVar2.r.put(i2, set);
    }

    public static /* synthetic */ void a(Map map, C2552rt.a aVar, String str, C2552rt.a aVar2, String str2, String str3) {
        if (map.containsKey(str2) && ((String) map.get(str2)).equals(str3)) {
            aVar.a(str, str2, str3);
        } else {
            aVar2.a(str, str2, str3);
        }
    }

    public static /* synthetic */ void a(C2552rt c2552rt, C2552rt.a aVar, C2552rt.a aVar2, String str, String str2) {
        if (c2552rt.i().containsKey(str) && c2552rt.i().get(str).equals(str2)) {
            aVar.a(str, str2);
        } else {
            aVar2.a(str, str2);
        }
    }

    public static void a(Map map, final Map map2, final BiConsumer biConsumer, final BiConsumer biConsumer2) {
        map.forEach(new BiConsumer() { // from class: g1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AP.a(map2, biConsumer, biConsumer2, (AbstractC0259n1) obj, (C2637st) obj2);
            }
        });
    }

    public static /* synthetic */ void a(Map map, BiConsumer biConsumer, BiConsumer biConsumer2, AbstractC0259n1 abstractC0259n1, C2637st c2637st) {
        if (((C2637st) map.get(abstractC0259n1)).equals(c2637st)) {
            biConsumer.accept(abstractC0259n1, c2637st);
        } else {
            biConsumer2.accept(abstractC0259n1, c2637st);
        }
    }

    public static /* synthetic */ void a(Map map, BiConsumer biConsumer, BiConsumer biConsumer2, AbstractC0259n1 abstractC0259n1, com.android.tools.r8.graph.I2 i2) {
        if (map.get(abstractC0259n1) == i2) {
            biConsumer.accept(abstractC0259n1, i2);
        } else {
            biConsumer2.accept(abstractC0259n1, i2);
        }
    }

    public static void a(Set set, final Set set2, final Consumer consumer, final Consumer consumer2) {
        set.forEach(new Consumer() { // from class: e1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AP.a(set2, consumer, consumer2, (AbstractC0259n1) obj);
            }
        });
    }

    public static /* synthetic */ void a(Set set, Consumer consumer, Consumer consumer2, AbstractC0259n1 abstractC0259n1) {
        if (set.contains(abstractC0259n1)) {
            consumer.accept(abstractC0259n1);
        } else {
            consumer2.accept(abstractC0259n1);
        }
    }

    public static void b(Map map, final Map map2, final BiConsumer biConsumer, final BiConsumer biConsumer2) {
        map.forEach(new BiConsumer() { // from class: a1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AP.a(map2, biConsumer, biConsumer2, (AbstractC0259n1) obj, (I2) obj2);
            }
        });
    }
}
