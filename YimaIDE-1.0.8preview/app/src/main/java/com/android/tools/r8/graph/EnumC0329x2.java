package com.android.tools.r8.graph;

import com.android.tools.r8.internal.C0497Fs;
import com.android.tools.r8.internal.EnumC2326pC;
import defpackage.gk0;
import defpackage.sla;
import org.xmlpull.v1.XmlPullParser;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.graph.x2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC0329x2 {
    public static final EnumC0329x2 c = new EnumC0329x2("STATIC_PUT", 0, 0);
    public static final EnumC0329x2 d = new EnumC0329x2("STATIC_GET", 1, 1);
    public static final EnumC0329x2 e = new EnumC0329x2("INSTANCE_PUT", 2, 2);
    public static final EnumC0329x2 f = new EnumC0329x2("INSTANCE_GET", 3, 3);
    public static final EnumC0329x2 g = new EnumC0329x2("INVOKE_STATIC", 4, 4);
    public static final EnumC0329x2 h = new EnumC0329x2("INVOKE_INSTANCE", 5, 5);
    public static final EnumC0329x2 i = new EnumC0329x2("INVOKE_CONSTRUCTOR", 6, 6);
    public static final EnumC0329x2 j = new EnumC0329x2("INVOKE_DIRECT", 7, 7);
    public static final EnumC0329x2 k = new EnumC0329x2("INVOKE_INTERFACE", 8, 8);
    public static final EnumC0329x2 l = new EnumC0329x2("INVOKE_SUPER", 9, 9);
    public static final /* synthetic */ boolean m = true;
    public final short b;

    public EnumC0329x2(String str, int i2, short s) {
        super(str, i2);
        this.b = s;
    }

    public static EnumC0329x2 a(C0497Fs c0497Fs, C0178b4 c0178b4, I2 i2) {
        switch (c0497Fs.c()) {
            case 1:
                return f;
            case 2:
                return d;
            case XmlPullParser.END_TAG /* 3 */:
                return e;
            case 4:
                return c;
            case XmlPullParser.CDSECT /* 5 */:
                return h;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return g;
            case 7:
                boolean z = m;
                if (!z && c0497Fs.a().equals("<init>")) {
                    x1f.a();
                    return null;
                }
                if (z || !c0497Fs.a().equals("<clinit>")) {
                    return c0178b4.f(c0497Fs.b()) == i2 ? j : l;
                }
                x1f.a();
                return null;
            case 8:
                return i;
            case 9:
                return k;
            default:
                sla.a("MethodHandle tag is not supported: ", c0497Fs.c());
                return null;
        }
    }

    public final boolean b() {
        return this == i;
    }

    public final boolean c() {
        return this == j;
    }

    public final boolean d() {
        return this == h;
    }

    public final boolean e() {
        return this == k;
    }

    public final boolean f() {
        return this == g;
    }

    public final boolean g() {
        return f() || d() || e() || this == l || b() || c();
    }

    public final EnumC2326pC h() {
        if (!m && !g()) {
            x1f.a();
            return null;
        }
        switch (ordinal()) {
            case 4:
                return EnumC2326pC.f;
            case XmlPullParser.CDSECT /* 5 */:
                return EnumC2326pC.h;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return EnumC2326pC.d;
            case 7:
                return EnumC2326pC.d;
            case 8:
                return EnumC2326pC.e;
            case 9:
                return EnumC2326pC.g;
            default:
                gk0.a("Conversion to invoke type with unexpected method handle: ", this);
                return null;
        }
    }

    public final boolean a() {
        return this == c || this == d || this == e || this == f;
    }
}
