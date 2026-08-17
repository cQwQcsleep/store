package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.util.Optional;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.am, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum EnumC1095am implements com.android.tools.r8.utils.structural.s<EnumC1095am> {
    V35(35, new byte[]{48, 51, 53}, 1),
    V37(37, new byte[]{48, 51, 55}, 1),
    V38(38, new byte[]{48, 51, 56}, 1),
    V39(39, new byte[]{48, 51, 57}, 1),
    V40(40, new byte[]{48, 52, 48}, 1),
    V41(41, new byte[]{48, 52, 49}, 2);

    public static final /* synthetic */ boolean l = true;
    public final int b;
    public final byte[] c;
    public final int d;

    EnumC1095am(int i, byte[] bArr, int i2) {
        this.b = i;
        this.c = bArr;
        this.d = i2;
    }

    public static Optional<EnumC1095am> a(char c, char c2, char c3) {
        if (c != '0') {
            return Optional.empty();
        }
        for (EnumC1095am enumC1095am : (EnumC1095am[]) values().clone()) {
            if (!l && enumC1095am.a()[0] != 48) {
                x1f.a();
                return null;
            }
            if (enumC1095am.a()[2] == c3 && enumC1095am.a()[1] == c2) {
                return Optional.of(enumC1095am);
            }
        }
        return Optional.empty();
    }

    public static Optional<EnumC1095am> b(int i) {
        switch (i) {
            case 35:
                return Optional.of(V35);
            case 36:
            default:
                return Optional.empty();
            case 37:
                return Optional.of(V37);
            case 38:
                return Optional.of(V38);
            case 39:
                return Optional.of(V39);
            case 40:
                return Optional.of(V40);
            case 41:
                return Optional.of(V41);
        }
    }

    public static EnumC1095am c(EnumC3077y2 enumC3077y2) {
        switch (AbstractC1009Zl.a[enumC3077y2.ordinal()]) {
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
            case 8:
                return V39;
            case 9:
            case XmlPullParser.DOCDECL /* 10 */:
                return V38;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
            case 12:
                return V37;
            case 13:
            case 14:
            case 15:
            case Fcntl.S_IWGRP /* 16 */:
            case 17:
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
            case AndroidSdkVersion.KITKAT /* 19 */:
            case 20:
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
            case 22:
            case AndroidSdkVersion.M /* 23 */:
            case AndroidSdkVersion.N /* 24 */:
            case 25:
            case AndroidSdkVersion.O /* 26 */:
            case 27:
            case AndroidSdkVersion.P /* 28 */:
            case AndroidSdkVersion.Q /* 29 */:
            case AndroidSdkVersion.R /* 30 */:
            case AndroidSdkVersion.S /* 31 */:
            case 32:
            case AndroidSdkVersion.T /* 33 */:
            case AndroidSdkVersion.U /* 34 */:
            case 35:
                return V35;
            default:
                defpackage.gk0.a("Unsupported api level ", enumC3077y2);
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.s
    public final /* bridge */ /* synthetic */ int compareTo(com.android.tools.r8.utils.structural.s sVar) {
        return compareTo((Enum) sVar);
    }

    public boolean c() {
        return this.d == 2;
    }

    public final int b() {
        return this.b;
    }

    public byte[] a() {
        return this.c;
    }
}
