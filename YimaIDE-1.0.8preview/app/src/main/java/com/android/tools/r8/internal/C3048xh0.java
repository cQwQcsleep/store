package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3048xh0;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xh0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3048xh0 {
    public static final /* synthetic */ boolean h = true;
    public final String a;
    public final boolean b;
    public final LinkedHashMap c = new LinkedHashMap();
    public long d = 0;
    public long e;
    public LinkedHashMap f;
    public Map g;

    public C3048xh0(String str, boolean z) {
        this.a = str;
        this.b = z;
        if (z) {
            int i = Ch0.d;
            System.gc();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("Memory", new C2963wh0(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            this.f = linkedHashMap;
        }
        this.e = System.nanoTime();
    }

    public void a(final int i, final C3048xh0 c3048xh0) {
        long j = 0;
        if (!h && this.d < 0) {
            x1f.a();
            return;
        }
        if (Ch0.a(this.d, c3048xh0.d) < Ch0.d) {
            return;
        }
        b(i);
        System.out.println(a(c3048xh0));
        if (this.b) {
            a(i);
        }
        if (this.c.isEmpty()) {
            return;
        }
        Collection collectionValues = this.c.values();
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            j += ((C3048xh0) it.next()).d;
        }
        long j2 = this.d;
        if (j < j2) {
            long j3 = j2 - j;
            if (Ch0.a(j3, c3048xh0.d) >= Ch0.d) {
                b(i + 1);
                System.out.println("(" + Ch0.b(j3, c3048xh0.d) + ") Unaccounted: " + Ch0.c(j3));
            }
        }
        collectionValues.forEach(new Consumer() { // from class: msi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3048xh0 c3048xh1 = (C3048xh0) obj;
                c3048xh1.a(i + 1, c3048xh0);
            }
        });
    }

    public final void b() {
        long jNanoTime = (System.nanoTime() - this.e) + this.d;
        this.d = jNanoTime;
        this.e = -1L;
        if (!h && jNanoTime < 0) {
            x1f.a();
            return;
        }
        if (this.b) {
            int i = Ch0.d;
            System.gc();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("Memory", new C2963wh0(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            this.g = linkedHashMap;
        }
    }

    public String toString() {
        return this.a + ": " + Ch0.c(this.d);
    }

    public static void b(int i) {
        if (i > 0) {
            PrintStream printStream = System.out;
            printStream.print("  ".repeat(i));
            printStream.print("- ");
        }
    }

    public final String a(C3048xh0 c3048xh0) {
        if (this == c3048xh0) {
            return toString();
        }
        return "(" + Ch0.b(this.d, c3048xh0.d) + ") " + toString();
    }

    public final long a() {
        return this.d;
    }

    public final void a(int i) {
        for (Map.Entry entry : this.f.entrySet()) {
            if (((String) entry.getKey()).equals("Memory")) {
                for (int i2 = 0; i2 <= i; i2++) {
                    System.out.print("  ");
                }
                C2963wh0 c2963wh0 = (C2963wh0) this.g.get(entry.getKey());
                C2963wh0 c2963wh1 = (C2963wh0) entry.getValue();
                System.out.println(((String) entry.getKey()) + " start: " + Ch0.b(c2963wh1.a) + ", end: " + Ch0.b(c2963wh0.a) + ", delta: " + Ch0.b(c2963wh0.a(c2963wh1)));
            }
        }
    }
}
