package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.os, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC2295os<T> {

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.android.tools.r8.internal.os$a */
    public static final class a {
        public static final a b = new a(0, "CLASS_SUPER_OR_INTERFACE_ANNOTATION");
        public static final a c = new a(1, "ENCLOSING_INNER_OR_TYPE_ANNOTATION");
        public static final a d = new a(2, "MEMBER_ANNOTATION");

        public a(int i, String str) {
            super(str, i);
        }
    }

    T a(T t, String str);

    T a(String str, a aVar);

    void a();

    void a(char c);

    void a(String str);

    void b();
}
