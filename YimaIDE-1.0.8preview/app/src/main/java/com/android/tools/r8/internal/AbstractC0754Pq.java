package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0754Pq {
    public static final EnumMap a = new EnumMap(U50.class);
    public static final EnumMap b = new EnumMap(E50.class);
    public static final /* synthetic */ boolean c = true;

    static {
        a(U50.ANIM, E50.c);
        a(U50.ANIMATOR, E50.d);
        U50 u50 = U50.COLOR;
        a(u50, E50.e);
        U50 u51 = U50.DRAWABLE;
        E50 e50 = E50.f;
        a(u51, e50);
        a(U50.FONT, E50.g);
        a(U50.INTERPOLATOR, E50.h);
        U50 u52 = U50.LAYOUT;
        E50 e51 = E50.i;
        a(u52, e51);
        U50 u53 = U50.MENU;
        E50 e52 = E50.j;
        a(u53, e52);
        a(U50.MIPMAP, E50.k);
        a(U50.RAW, E50.m);
        U50 u54 = U50.TRANSITION;
        E50 e53 = E50.n;
        a(u54, e53);
        U50 u55 = U50.XML;
        E50 e54 = E50.p;
        a(u55, e54);
        U50 u56 = U50.NAVIGATION;
        E50 e55 = E50.l;
        a(u56, e55);
        U50 u57 = U50.ARRAY;
        E50 e56 = E50.o;
        a(u57, e56);
        a(U50.ATTR, e56);
        a(U50.BOOL, e56);
        a(u50, e56);
        a(U50.DIMEN, e56);
        a(u51, e56);
        a(U50.FRACTION, e56);
        U50 u58 = U50.ID;
        a(u58, e56);
        a(U50.INTEGER, e56);
        a(U50.H, e56);
        a(U50.PLURALS, e56);
        a(U50.C, e56);
        a(U50.STRING, e56);
        a(U50.STYLE, e56);
        a(U50.F, e56);
        a(U50.z, e56);
        a(u58, e50);
        a(u58, e51);
        a(u58, e52);
        a(u58, e55);
        a(u58, e53);
        a(u58, e54);
        for (U50 u59 : U50.values()) {
            EnumMap enumMap = a;
            List list = (List) enumMap.get(u59);
            if (list != null) {
                if (!c && u59 != U50.ID && list.size() > 2) {
                    x1f.a();
                    return;
                }
                enumMap.put(u59, Collections.unmodifiableList(list));
            }
        }
        for (E50 e57 : E50.values()) {
            EnumMap enumMap2 = b;
            List list2 = (List) enumMap2.get(e57);
            if (list2 != null) {
                enumMap2.put(e57, Collections.unmodifiableList(list2));
            }
        }
        EnumSet.of(E50.i, E50.j, E50.f, E50.p, E50.n, E50.l);
    }

    public static void a(U50 u50, E50 e50) {
        EnumMap enumMap = a;
        List arrayList = (List) enumMap.get(u50);
        if (arrayList == null) {
            arrayList = new ArrayList();
            enumMap.put(u50, arrayList);
        }
        if (arrayList.indexOf(e50) == -1) {
            arrayList.add(e50);
        }
        EnumMap enumMap2 = b;
        List arrayList2 = (List) enumMap2.get(e50);
        if (arrayList2 == null) {
            arrayList2 = new ArrayList();
            enumMap2.put(e50, arrayList2);
        }
        if (arrayList2.indexOf(u50) == -1) {
            arrayList2.add(u50);
        }
    }
}
