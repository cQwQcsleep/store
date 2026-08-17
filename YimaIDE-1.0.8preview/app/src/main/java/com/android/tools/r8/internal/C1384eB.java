package com.android.tools.r8.internal;

import java.util.AbstractList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1384eB extends AbstractList {
    public final List b;
    public final InterfaceC1300dB c;

    public C1384eB(List list) {
        C1210c70 c1210c70 = C1550g70.k;
        this.b = list;
        this.c = c1210c70;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        EnumC1464f70 enumC1464f70;
        InterfaceC1300dB interfaceC1300dB = this.c;
        Object obj = this.b.get(i);
        ((C1210c70) interfaceC1300dB).getClass();
        switch (((Integer) obj).intValue()) {
            case 0:
                enumC1464f70 = EnumC1464f70.c;
                break;
            case 1:
                enumC1464f70 = EnumC1464f70.d;
                break;
            case 2:
                enumC1464f70 = EnumC1464f70.e;
                break;
            case XmlPullParser.END_TAG /* 3 */:
                enumC1464f70 = EnumC1464f70.f;
                break;
            case 4:
                enumC1464f70 = EnumC1464f70.g;
                break;
            case XmlPullParser.CDSECT /* 5 */:
                enumC1464f70 = EnumC1464f70.h;
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                enumC1464f70 = EnumC1464f70.i;
                break;
            case 7:
                enumC1464f70 = EnumC1464f70.j;
                break;
            case 8:
                enumC1464f70 = EnumC1464f70.k;
                break;
            case 9:
                enumC1464f70 = EnumC1464f70.l;
                break;
            default:
                enumC1464f70 = null;
                break;
        }
        return enumC1464f70 == null ? EnumC1464f70.m : enumC1464f70;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.b.size();
    }
}
