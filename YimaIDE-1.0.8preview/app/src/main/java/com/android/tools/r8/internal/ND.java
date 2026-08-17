package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ND implements InterfaceC2425qQ {
    public static final List d;
    public static final LinkedHashMap e;
    public final String[] a;
    public final Set b;
    public final List c;

    static {
        String strA = AbstractC1760ie.a(AbstractC1929ke.a((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), XmlPullParser.NO_NAMESPACE, null, null, null, 62);
        List listA = AbstractC1929ke.a((Object[]) new String[]{strA.concat("/Any"), strA.concat("/Nothing"), strA.concat("/Unit"), strA.concat("/Throwable"), strA.concat("/Number"), strA.concat("/Byte"), strA.concat("/Double"), strA.concat("/Float"), strA.concat("/Int"), strA.concat("/Long"), strA.concat("/Short"), strA.concat("/Boolean"), strA.concat("/Char"), strA.concat("/CharSequence"), strA.concat("/String"), strA.concat("/Comparable"), strA.concat("/Enum"), strA.concat("/Array"), strA.concat("/ByteArray"), strA.concat("/DoubleArray"), strA.concat("/FloatArray"), strA.concat("/IntArray"), strA.concat("/LongArray"), strA.concat("/ShortArray"), strA.concat("/BooleanArray"), strA.concat("/CharArray"), strA.concat("/Cloneable"), strA.concat("/Annotation"), strA.concat("/collections/Iterable"), strA.concat("/collections/MutableIterable"), strA.concat("/collections/Collection"), strA.concat("/collections/MutableCollection"), strA.concat("/collections/List"), strA.concat("/collections/MutableList"), strA.concat("/collections/Set"), strA.concat("/collections/MutableSet"), strA.concat("/collections/Map"), strA.concat("/collections/MutableMap"), strA.concat("/collections/Map.Entry"), strA.concat("/collections/MutableMap.MutableEntry"), strA.concat("/collections/Iterator"), strA.concat("/collections/MutableIterator"), strA.concat("/collections/ListIterator"), strA.concat("/collections/MutableListIterator")});
        d = listA;
        C0707Nv c0707Nv = new C0707Nv(new C2528re(listA));
        int iA = AbstractC1823jN.a(AbstractC2015le.a((Iterable) c0707Nv));
        if (iA < 16) {
            iA = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iA);
        Iterator it = c0707Nv.iterator();
        while (true) {
            C0733Ov c0733Ov = (C0733Ov) it;
            if (!c0733Ov.b.hasNext()) {
                e = linkedHashMap;
                return;
            } else {
                C0681Mv c0681Mv = (C0681Mv) c0733Ov.next();
                linkedHashMap.put((String) c0681Mv.b, Integer.valueOf(c0681Mv.a));
            }
        }
    }

    public ND(C1473fE c1473fE, String[] strArr) {
        int size;
        Set setSingleton;
        List list = c1473fE.d;
        if (list.isEmpty() || (size = list.size()) == 0) {
            setSingleton = C1266cn.b;
        } else if (size != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(AbstractC1823jN.a(list.size()));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(it.next());
            }
            setSingleton = linkedHashSet;
        } else {
            setSingleton = Collections.singleton(list.get(0));
            KB.b(setSingleton, "singleton(...)");
        }
        List<C1387eE> list2 = c1473fE.c;
        KB.b(list2, "getRecordList(...)");
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(list2.size());
        for (C1387eE c1387eE : list2) {
            int i = c1387eE.d;
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(c1387eE);
            }
        }
        arrayList.trimToSize();
        this.a = strArr;
        this.b = setSingleton;
        this.c = arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003f  */
    public final String a(int i) {
        String strA;
        C1387eE c1387eE = (C1387eE) this.c.get(i);
        int i2 = c1387eE.c;
        if ((i2 & 4) == 4) {
            Object obj = c1387eE.f;
            if (obj instanceof String) {
                strA = (String) obj;
            } else {
                T7 t7 = (T7) obj;
                String strG = t7.g();
                if (t7.c()) {
                    c1387eE.f = strG;
                }
                strA = strG;
            }
        } else if ((i2 & 2) == 2) {
            List list = d;
            int size = list.size();
            int i3 = c1387eE.e;
            if (i3 < 0 || i3 >= size) {
                strA = this.a[i];
            } else {
                strA = (String) list.get(i3);
            }
        } else {
            strA = this.a[i];
        }
        if (c1387eE.h.size() >= 2) {
            List list2 = c1387eE.h;
            KB.a(list2);
            Integer num = (Integer) list2.get(0);
            Integer num2 = (Integer) list2.get(1);
            if (num.intValue() >= 0 && num.intValue() <= num2.intValue() && num2.intValue() <= strA.length()) {
                strA = strA.substring(num.intValue(), num2.intValue());
                KB.b(strA, "substring(...)");
            }
        }
        if (c1387eE.j.size() >= 2) {
            List list3 = c1387eE.j;
            KB.a(list3);
            Integer num3 = (Integer) list3.get(0);
            Integer num4 = (Integer) list3.get(1);
            KB.a((Object) strA);
            strA = AbstractC1679hg0.a(strA, (char) num3.intValue(), (char) num4.intValue());
        }
        EnumC1303dE enumC1303dE = c1387eE.g;
        if (enumC1303dE == null) {
            enumC1303dE = EnumC1303dE.c;
        }
        int iOrdinal = enumC1303dE.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                KB.a((Object) strA);
                strA = AbstractC1679hg0.a(strA, '$', '.');
            } else {
                if (iOrdinal != 2) {
                    throw new HR();
                }
                if (strA.length() >= 2) {
                    strA = strA.substring(1, strA.length() - 1);
                    KB.b(strA, "substring(...)");
                }
                strA = AbstractC1679hg0.a(strA, '$', '.');
            }
        }
        KB.a((Object) strA);
        return strA;
    }
}
