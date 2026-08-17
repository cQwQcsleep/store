package com.android.tools.r8.internal;

import com.android.tools.r8.internal.U50;
import com.reandroid.arsc.chunk.TypeBlock;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum U50 {
    ANIM("anim", new String[0]),
    ANIMATOR("animator", new String[0]),
    ARRAY("array", "string-array", "integer-array"),
    ATTR("attr", new String[0]),
    BOOL("bool", new String[0]),
    COLOR("color", new String[0]),
    DIMEN("dimen", new String[0]),
    DRAWABLE("drawable", new String[0]),
    FONT("font", new String[0]),
    FRACTION("fraction", new String[0]),
    ID(TypeBlock.NAME_id, new String[0]),
    INTEGER("integer", new String[0]),
    INTERPOLATOR("interpolator", new String[0]),
    LAYOUT("layout", new String[0]),
    MENU("menu", new String[0]),
    MIPMAP("mipmap", new String[0]),
    NAVIGATION("navigation", new String[0]),
    PLURALS("plurals", new String[0]),
    RAW("raw", new String[0]),
    STRING("string", new String[0]),
    STYLE("style", new String[0]),
    z("STYLEABLE", "styleable"),
    TRANSITION("transition", new String[0]),
    XML("xml", new String[0]),
    C("PUBLIC", "public"),
    D("AAPT", "_aapt"),
    E("OVERLAYABLE", "overlayable"),
    F("STYLE_ITEM", "item"),
    G("SAMPLE_DATA", "sample"),
    H("MACRO", "macro");

    public static final AbstractC0706Nu I;
    public final String b;
    public final String[] c;
    public final int d;

    static {
        U50 u50 = z;
        U50 u51 = C;
        U50 u52 = E;
        U50 u53 = H;
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        c0629KuE.a("declare-styleable", u50);
        c0629KuE.a("public", u51);
        c0629KuE.a("overlayable", u52);
        c0629KuE.a("macro", u53);
        C0629Ku c0629KuE2 = AbstractC0706Nu.e();
        c0629KuE2.a("styleable", u50);
        for (U50 u54 : values()) {
            if (u54.d == 1 && u54 != z) {
                c0629KuE2.a(u54.b, u54);
                c0629KuE.a(u54.b, u54);
                for (String str : u54.c) {
                    c0629KuE.a(str, u54);
                }
            }
        }
        c0629KuE.b();
        I = c0629KuE2.b();
        Stream streamFilter = Arrays.stream(values()).filter(new Predicate() { // from class: cye
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((U50) obj).a();
            }
        });
        Collector collector = AbstractC0845Td.a;
    }

    U50(String str, String str2) {
        this.b = str2;
        this.d = i;
        this.c = new String[0];
    }

    public final boolean a() {
        return (this.d == 1 && this != ATTR) || this == H;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.b;
    }

    U50(String str, String... strArr) {
        this.b = str;
        this.d = 1;
        this.c = strArr;
    }
}
