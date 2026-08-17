package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.internal.AbstractC0379Be;
import com.android.tools.r8.internal.AbstractC0574Ir;
import com.android.tools.r8.internal.AbstractC1679hg0;
import com.android.tools.r8.internal.AbstractC1760ie;
import com.android.tools.r8.internal.AbstractC2015le;
import com.android.tools.r8.internal.C0767Qd;
import com.android.tools.r8.internal.C1153bW;
import com.android.tools.r8.internal.C1491fW;
import com.android.tools.r8.internal.C3017xJ;
import com.android.tools.r8.internal.GD;
import com.android.tools.r8.internal.ID;
import com.android.tools.r8.internal.JD;
import com.android.tools.r8.internal.KB;
import com.android.tools.r8.internal.LD;
import com.android.tools.r8.internal.MD;
import com.android.tools.r8.internal.Zm0;
import defpackage.bk;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class v {
    public final p a;
    public final j b;

    public v(p pVar, j jVar) {
        this.a = pVar;
        this.b = jVar;
    }

    public final byte[] a() throws IOException {
        Iterator it;
        byte[] bArr;
        JD jd = JD.m;
        ID id = new ID();
        Iterator it2 = this.a.a.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            String str = (String) entry.getKey();
            q qVar = (q) entry.getValue();
            KB.c(str, "packageFqName");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (String str2 : qVar.a) {
                KB.c(str2, "partInternalName");
                linkedHashMap.put(str2, null);
            }
            for (Map.Entry entry2 : qVar.b.entrySet()) {
                String str3 = (String) entry2.getKey();
                String str4 = (String) entry2.getValue();
                KB.c(str3, "partInternalName");
                linkedHashMap.put(str3, str4);
            }
            Set setKeySet = linkedHashMap.keySet();
            KB.b(setKeySet, "<get-keys>(...)");
            if (setKeySet.isEmpty()) {
                it = it2;
                bArr = null;
            } else {
                MD md = MD.p;
                LD ld = new LD();
                ld.c |= 1;
                ld.d = str;
                bArr = null;
                String strA = AbstractC1679hg0.a(str, '.', DataResource.SEPARATOR);
                Set setKeySet2 = linkedHashMap.keySet();
                KB.b(setKeySet2, "<get-keys>(...)");
                ArrayList arrayList = new ArrayList();
                int i = 1;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : setKeySet2) {
                    if (AbstractC1679hg0.a((String) obj, DataResource.SEPARATOR, XmlPullParser.NO_NAMESPACE).equals(strA)) {
                        arrayList.add(obj);
                    } else {
                        arrayList2.add(obj);
                    }
                }
                int i2 = 4;
                int i3 = 2;
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                for (Object obj2 : arrayList) {
                    String str5 = (String) obj2;
                    KB.c(str5, "partInternalName");
                    String str6 = (String) linkedHashMap.get(str5);
                    Object obj3 = linkedHashMap3.get(str6);
                    if (obj3 == null) {
                        ArrayList arrayList3 = new ArrayList();
                        linkedHashMap3.put(str6, arrayList3);
                        obj3 = arrayList3;
                    }
                    ((List) obj3).add(obj2);
                }
                Comparator comparatorA = AbstractC0379Be.a();
                KB.c(comparatorA, "comparator");
                TreeMap treeMap = new TreeMap(comparatorA);
                treeMap.putAll(linkedHashMap3);
                Iterator it3 = treeMap.entrySet().iterator();
                while (it3.hasNext()) {
                    Map.Entry entry3 = (Map.Entry) it3.next();
                    String str7 = (String) entry3.getKey();
                    List list = (List) entry3.getValue();
                    KB.a(list);
                    Iterator it4 = AbstractC1760ie.b(list).iterator();
                    while (it4.hasNext()) {
                        Iterator it5 = it2;
                        String strA2 = AbstractC1679hg0.a((String) it4.next(), DataResource.SEPARATOR);
                        Iterator it6 = it3;
                        if ((ld.c & 2) != i3) {
                            ld.e = new C3017xJ(ld.e);
                            ld.c |= 2;
                        }
                        ld.e.add(strA2);
                        if (str7 != null) {
                            String strA3 = AbstractC1679hg0.a(str7, DataResource.SEPARATOR);
                            Object objValueOf = linkedHashMap2.get(strA3);
                            if (objValueOf == null) {
                                objValueOf = Integer.valueOf(linkedHashMap2.size());
                                linkedHashMap2.put(strA3, objValueOf);
                            }
                            int iIntValue = ((Number) objValueOf).intValue() + 1;
                            if ((ld.c & 4) != i2) {
                                ld.f = new ArrayList(ld.f);
                                ld.c |= 4;
                            }
                            ld.f.add(Integer.valueOf(iIntValue));
                        }
                        it2 = it5;
                        it3 = it6;
                        i2 = 4;
                        i3 = 2;
                    }
                    i2 = 4;
                    i3 = 2;
                }
                it = it2;
                ArrayList arrayList4 = new ArrayList();
                LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                for (Object obj4 : arrayList2) {
                    String strA4 = AbstractC1679hg0.a((String) obj4, DataResource.SEPARATOR, XmlPullParser.NO_NAMESPACE);
                    Object arrayList5 = linkedHashMap4.get(strA4);
                    if (arrayList5 == null) {
                        arrayList5 = new ArrayList();
                        linkedHashMap4.put(strA4, arrayList5);
                    }
                    ((List) arrayList5).add(obj4);
                }
                Iterator it7 = new TreeMap(linkedHashMap4).entrySet().iterator();
                while (it7.hasNext()) {
                    Map.Entry entry4 = (Map.Entry) it7.next();
                    String str8 = (String) entry4.getKey();
                    List list2 = (List) entry4.getValue();
                    KB.a((Object) str8);
                    String strA5 = AbstractC1679hg0.a(str8, DataResource.SEPARATOR, '.');
                    if (!id.f.f().contains(strA5)) {
                        if ((id.c & 4) != 4) {
                            id.f = new C3017xJ(id.f);
                            id.c |= 4;
                        }
                        id.f.add(strA5);
                    }
                    int iIndexOf = id.f.f().indexOf(strA5);
                    KB.a(list2);
                    LinkedHashMap linkedHashMap5 = new LinkedHashMap();
                    for (Object obj5 : list2) {
                        String str9 = (String) obj5;
                        KB.c(str9, "partInternalName");
                        String str10 = (String) linkedHashMap.get(str9);
                        Object obj6 = linkedHashMap5.get(str10);
                        Iterator it8 = it7;
                        if (obj6 == null) {
                            ArrayList arrayList6 = new ArrayList();
                            linkedHashMap5.put(str10, arrayList6);
                            obj6 = arrayList6;
                        }
                        ((List) obj6).add(obj5);
                        it7 = it8;
                    }
                    Iterator it9 = it7;
                    Comparator comparatorA2 = AbstractC0379Be.a();
                    KB.c(comparatorA2, "comparator");
                    TreeMap treeMap2 = new TreeMap(comparatorA2);
                    treeMap2.putAll(linkedHashMap5);
                    Iterator it10 = treeMap2.entrySet().iterator();
                    while (it10.hasNext()) {
                        Map.Entry entry5 = (Map.Entry) it10.next();
                        String str11 = (String) entry5.getKey();
                        List list3 = (List) entry5.getValue();
                        KB.a(list3);
                        Iterator it11 = AbstractC1760ie.b(list3).iterator();
                        while (it11.hasNext()) {
                            String strA6 = AbstractC1679hg0.a((String) it11.next(), DataResource.SEPARATOR);
                            Iterator it12 = it10;
                            if ((ld.c & 16) != 16) {
                                ld.h = new C3017xJ(ld.h);
                                ld.c |= 16;
                            }
                            ld.h.add(strA6);
                            if (str11 != null) {
                                String strA7 = AbstractC1679hg0.a(str11, DataResource.SEPARATOR);
                                Object objValueOf2 = linkedHashMap2.get(strA7);
                                if (objValueOf2 == null) {
                                    objValueOf2 = Integer.valueOf(linkedHashMap2.size());
                                    linkedHashMap2.put(strA7, objValueOf2);
                                }
                                int iIntValue2 = ((Number) objValueOf2).intValue() + 1;
                                if ((ld.c & 32) != 32) {
                                    ld.i = new ArrayList(ld.i);
                                    ld.c |= 32;
                                }
                                ld.i.add(Integer.valueOf(iIntValue2));
                            }
                            arrayList4.add(Integer.valueOf(iIndexOf));
                            it10 = it12;
                        }
                    }
                    it7 = it9;
                }
                while (true) {
                    int i4 = i;
                    if (arrayList4.size() <= i4 || ((Number) arrayList4.get(arrayList4.size() - i4)).intValue() != ((Number) arrayList4.get(arrayList4.size() - 2)).intValue()) {
                        break;
                    }
                    arrayList4.remove(arrayList4.size() - i4);
                    i = 1;
                }
                if ((ld.c & 64) != 64) {
                    ld.j = new ArrayList(ld.j);
                    ld.c |= 64;
                }
                AbstractC0574Ir.a(arrayList4, ld.j);
                Collection collectionValues = linkedHashMap2.values();
                Set setKeySet3 = linkedHashMap2.keySet();
                KB.c(collectionValues, "<this>");
                KB.c(setKeySet3, "other");
                Iterator it13 = collectionValues.iterator();
                Iterator it14 = setKeySet3.iterator();
                ArrayList arrayList7 = new ArrayList(Math.min(AbstractC2015le.a((Iterable) collectionValues), AbstractC2015le.a((Iterable) setKeySet3)));
                while (it13.hasNext() && it14.hasNext()) {
                    arrayList7.add(new C1491fW(it13.next(), it14.next()));
                }
                for (C1491fW c1491fW : AbstractC1760ie.a(arrayList7, new C1153bW())) {
                    int iIntValue3 = ((Number) c1491fW.b).intValue();
                    String str12 = (String) c1491fW.c;
                    boolean z = iIntValue3 == ld.g.size();
                    if (Zm0.a && !z) {
                        pe1.a("Multifile facades are loaded incorrectly: ", linkedHashMap2);
                        return null;
                    }
                    str12.getClass();
                    if ((ld.c & 8) != 8) {
                        ld.g = new C3017xJ(ld.g);
                        ld.c |= 8;
                    }
                    ld.g.add(str12);
                }
                if ((id.c & 1) != 1) {
                    id.d = new ArrayList(id.d);
                    id.c |= 1;
                }
                List list4 = id.d;
                MD mdE = ld.e();
                if (!mdE.a()) {
                    bk.a();
                    return null;
                }
                list4.add(mdE);
            }
            if (!linkedHashSet.isEmpty()) {
                MD md2 = MD.p;
                LD ld2 = new LD();
                ld2.c |= 1;
                ld2.d = str;
                List listB = AbstractC1760ie.b(linkedHashSet);
                if ((ld2.c & 2) != 2) {
                    ld2.e = new C3017xJ(ld2.e);
                    ld2.c |= 2;
                }
                AbstractC0574Ir.a(listB, ld2.e);
                if ((id.c & 2) != 2) {
                    id.e = new ArrayList(id.e);
                    id.c |= 2;
                }
                List list5 = id.e;
                MD mdE2 = ld2.e();
                if (!mdE2.a()) {
                    bk.a();
                    return bArr;
                }
                list5.add(mdE2);
            }
            it2 = it;
        }
        JD jdE = id.e();
        if (!jdE.a()) {
            bk.a();
            return null;
        }
        j jVar = this.b;
        GD gd = new GD(new int[]{jVar.b, jVar.c, jVar.d}, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        int[] iArr = gd.a;
        dataOutputStream.writeInt(iArr.length);
        for (int i5 : iArr) {
            dataOutputStream.writeInt(i5);
        }
        int i6 = gd.b;
        if ((i6 == 1 && gd.c >= 4) || i6 > 1) {
            dataOutputStream.writeInt(0);
        }
        int iC = jdE.c();
        C0767Qd c0767Qd = new C0767Qd(dataOutputStream, new byte[iC <= 4096 ? iC : 4096]);
        jdE.a(c0767Qd);
        c0767Qd.a();
        dataOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        KB.b(byteArray, "toByteArray(...)");
        return byteArray;
    }
}
