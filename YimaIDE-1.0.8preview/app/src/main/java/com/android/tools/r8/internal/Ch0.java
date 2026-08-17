package com.android.tools.r8.internal;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Ch0 {
    public static final int d;
    public static final C2877vh0 e;
    public static final /* synthetic */ boolean f = true;
    public final C3048xh0 a;
    public final ArrayDeque b;
    public final boolean c;

    static {
        String property = System.getProperty("com.android.tools.r8.printtimes.minvalue");
        d = property != null ? Integer.parseInt(property) : 2;
        e = new C2877vh0();
    }

    public Ch0(String str, boolean z) {
        this.c = z;
        ArrayDeque arrayDeque = new ArrayDeque();
        this.b = arrayDeque;
        C3048xh0 c3048xh0 = new C3048xh0(str, z);
        this.a = c3048xh0;
        arrayDeque.push(c3048xh0);
    }

    public static String b(long j) {
        return a(j / 1024) + "k";
    }

    public void a(String str) {
        C3048xh0 c3048xh0;
        C3048xh0 c3048xh1 = (C3048xh0) this.b.peek();
        if (c3048xh1.c.containsKey(str)) {
            c3048xh0 = (C3048xh0) c3048xh1.c.get(str);
            if (!C3048xh0.h && c3048xh0.e != -1) {
                x1f.a();
                return;
            }
            if (c3048xh0.b) {
                System.gc();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("Memory", new C2963wh0(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
                c3048xh0.f = linkedHashMap;
            }
            c3048xh0.e = System.nanoTime();
        } else {
            C3048xh0 c3048xh2 = new C3048xh0(str, this.c);
            c3048xh1.c.put(str, c3048xh2);
            c3048xh0 = c3048xh2;
        }
        this.b.push(c3048xh0);
    }

    public void c() {
        boolean z = f;
        if (!z && this.b.size() != 1) {
            pe1.a("Unexpected non-singleton stack: ", this.b);
            return;
        }
        C3048xh0 c3048xh0 = (C3048xh0) this.b.peek();
        if (!z && c3048xh0 != this.a) {
            x1f.a();
            return;
        }
        c3048xh0.b();
        System.out.println("Recorded timings:");
        c3048xh0.a(0, c3048xh0);
    }

    public static String b(long j, long j2) {
        return ((j * 100) / j2) + "%";
    }

    public void b() {
        ((C3048xh0) this.b.peek()).b();
        this.b.pop();
    }

    public static String c(long j) {
        return (j / 1000000) + "ms";
    }

    public static Ch0 a(C2752uB c2752uB, String str) {
        Ch0 ch0;
        if (!c2752uB.r && !C2752uB.b()) {
            ch0 = a();
        } else {
            ch0 = new Ch0(str, c2752uB.s);
        }
        return c2752uB.c != null ? new Bh0(c2752uB, ch0) : ch0;
    }

    public final Ah0 a(ExecutorService executorService) {
        return a(C1086ah0.a(executorService), "Write files");
    }

    public Ah0 a(int i, String str) {
        boolean z = f;
        if (!z && this.b.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!z) {
            C3048xh0 c3048xh0 = (C3048xh0) this.b.peekFirst();
            if (!z && c3048xh0.c.containsKey(str)) {
                x01.a("Ambiguous timing chain. Insert a begin/end to fix");
                return null;
            }
        }
        return new Ah0(str, i, this);
    }

    public static long a(long j, long j2) {
        return (j * 100) / j2;
    }

    public static String a(long j) {
        long jAbs = Math.abs(j);
        StringBuilder sb = new StringBuilder();
        sb.append(jAbs);
        String string = sb.toString();
        if (string.length() < 4) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(j);
            return sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        if (j < 0) {
            sb3.append('-');
        }
        int length = string.length() % 3;
        sb3.append((CharSequence) string, 0, length);
        while (length < string.length()) {
            if (length > 0) {
                sb3.append('.');
            }
            int i = length + 3;
            sb3.append((CharSequence) string, length, i);
            length = i;
        }
        return sb3.toString();
    }

    public static Ch0 a() {
        return e;
    }

    public void a(String str, InterfaceC1681hh0 interfaceC1681hh0) {
        a(str);
        try {
            interfaceC1681hh0.b();
        } finally {
            b();
        }
    }

    public Object a(String str, InterfaceC2706th0 interfaceC2706th0) {
        a(str);
        try {
            return interfaceC2706th0.get();
        } finally {
            b();
        }
    }
}
