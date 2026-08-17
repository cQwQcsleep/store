package com.android.tools.r8.utils.structural;

import com.android.tools.r8.utils.structural.s;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface s<T extends s<T>> extends k<T>, Comparable<T> {
    static {
        boolean z = r.a;
    }

    static <T extends s<T>> T d(T t, T t2) {
        if (t == null) {
            return t2;
        }
        return (t2 == null || t.e(t2)) ? t : t2;
    }

    default boolean a(T t) {
        return compareTo((s) t) >= 0;
    }

    default boolean b(T t, T t2) {
        return a(t) && b(t2);
    }

    @Override // com.android.tools.r8.utils.structural.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    default boolean isEqualTo(T t) {
        if (r.a || t != null) {
            return this == t || compareTo((s) t) == 0;
        }
        x1f.a();
        return false;
    }

    @Override // java.lang.Comparable
    int compareTo(T t);

    default boolean e(T t) {
        return compareTo((s) t) < 0;
    }

    static <T extends s<T>> T a(T t, T t2) {
        return t.e(t2) ? t2 : t;
    }

    default boolean d(T t) {
        return compareTo((s) t) > 0;
    }

    default boolean b(T t) {
        return compareTo((s) t) <= 0;
    }

    static <T extends s<T>> T c(T t, T t2) {
        if (t == null) {
            return t2;
        }
        return (t2 != null && t.e(t2)) ? t2 : t;
    }
}
