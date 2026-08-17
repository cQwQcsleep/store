package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1405eW<T, S> {
    public Object a;
    public Object b;

    public C1405eW(T t, S s) {
        this.a = t;
        this.b = s;
    }

    public static <T, S> C1405eW<T, S> a(T t, S s) {
        return new C1405eW<>(t, s);
    }

    public S b() {
        return (S) this.b;
    }

    public final boolean equals(Object obj) {
        throw new Kk0("Pair does not want to support equality!");
    }

    public final int hashCode() {
        throw new Kk0("Pair does not want to support hashing!");
    }

    public final String toString() {
        return "Pair{" + this.a + ", " + this.b + "}";
    }

    public T a() {
        return (T) this.a;
    }
}
