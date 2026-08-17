package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class V90 {
    public static final HashSet a;
    public static final /* synthetic */ boolean b = true;

    static {
        HashSet hashSet = new HashSet(AbstractC1739iN.a(1));
        Collections.addAll(hashSet, "Native Method");
        a = hashSet;
    }

    public static String a(String str, String str2, boolean z) {
        if (!z || a.contains(str2)) {
            return str2;
        }
        String strA = AbstractC0857Tp.a(str2);
        int iLastIndexOf = str.lastIndexOf(46);
        int iIndexOf = str.indexOf(36, iLastIndexOf);
        if (iLastIndexOf > iIndexOf || iIndexOf < 0) {
            iIndexOf = str.length();
        }
        String strSubstring = str.substring(iLastIndexOf + 1, iIndexOf);
        if (strSubstring.endsWith("Kt") && (strA.isEmpty() || strA.equals("kt"))) {
            strSubstring = AbstractC0005a.a(2, 0, strSubstring);
            strA = "kt";
        } else if (!strA.equals("kt")) {
            strA = "java";
        }
        return strSubstring + "." + strA;
    }

    public static MethodReference a(com.android.tools.r8.naming.V.b bVar, ClassReference classReference) {
        if (bVar.e()) {
            classReference = Reference.classFromDescriptor(C0929Wj.I(bVar.g()));
        }
        ArrayList arrayList = new ArrayList(bVar.d.length);
        for (String str : bVar.d) {
            arrayList.add(Reference.typeFromTypeName(str));
        }
        return Reference.method(classReference, bVar.e() ? bVar.h() : bVar.a, arrayList, Reference.returnTypeFromDescriptor(C0929Wj.I(bVar.c)));
    }
}
