package com.android.tools.r8.kotlin;

import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.C1870jv;
import com.android.tools.r8.kotlin.AbstractC3284b;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3284b {
    public static final AbstractC2554rv a;

    static {
        int i = AbstractC2554rv.c;
        AbstractC2554rv abstractC2554rvA = new C1870jv().a("Lkotlin/Boolean").a("Lkotlin/Char").a("Lkotlin/Byte").a("Lkotlin/UByte").a("Lkotlin/Short;").a("Lkotlin/UShort;").a("Lkotlin/Int;").a("Lkotlin/UInt;").a("Lkotlin/Float;").a("Lkotlin/Long;").a("Lkotlin/ULong;").a("Lkotlin/Double;").a();
        final C1870jv c1870jv = new C1870jv();
        abstractC2554rvA.forEach(new Consumer() { // from class: reg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC3284b.a(c1870jv, (String) obj);
            }
        });
        c1870jv.a("Lkotlin/Unit;");
        c1870jv.a("Lkotlin/Any;");
        c1870jv.a("Lkotlin/Array;");
        c1870jv.a("Lkotlin/Function;");
        c1870jv.a("Lkotlin/KFunction;");
        a = c1870jv.a();
    }

    public static /* synthetic */ void a(C1870jv c1870jv, String str) {
        c1870jv.a(str);
        c1870jv.a(str.substring(0, str.length() - 1).concat("Array;"));
    }
}
