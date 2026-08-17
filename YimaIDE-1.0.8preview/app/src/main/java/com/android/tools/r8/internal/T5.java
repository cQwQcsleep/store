package com.android.tools.r8.internal;

import com.android.tools.r8.internal.T5;
import java.util.function.BiPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class T5 {
    public static <S, T> BiPredicate<S, T> a() {
        return new BiPredicate() { // from class: g0e
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return T5.a(obj, obj2);
            }
        };
    }

    public static <S, T> BiPredicate<S, T> b() {
        return new BiPredicate() { // from class: h0e
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return T5.b(obj, obj2);
            }
        };
    }

    public static /* synthetic */ boolean a(Object obj, Object obj2) {
        return false;
    }

    public static /* synthetic */ boolean b(Object obj, Object obj2) {
        return true;
    }
}
