package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.MO;
import com.android.tools.r8.references.ArrayReference;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class MO {
    public static final Comparator a = new Comparator() { // from class: wr9
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return MO.a((MethodReference) obj, (MethodReference) obj2);
        }
    };

    public static int a(MethodReference methodReference, MethodReference methodReference2) {
        int iA = AbstractC3042xe.a(C1758id.a().compare(methodReference.getHolderClass(), methodReference2.getHolderClass()));
        if (!AbstractC3042xe.b(iA)) {
            return AbstractC3042xe.c(iA);
        }
        int iA2 = AbstractC3042xe.a(methodReference.getMethodName().compareTo(methodReference2.getMethodName()));
        if (!AbstractC3042xe.b(iA2)) {
            return AbstractC3042xe.c(iA2);
        }
        int iA3 = AbstractC3042xe.a(C3136yj0.a.compare(methodReference.getReturnType(), methodReference2.getReturnType()));
        if (!AbstractC3042xe.b(iA3)) {
            return AbstractC3042xe.c(iA3);
        }
        for (int i = 0; i < Math.min(methodReference.getFormalTypes().size(), methodReference2.getFormalTypes().size()); i++) {
            int iA4 = AbstractC3042xe.a(C3136yj0.a.compare(methodReference.getFormalTypes().get(i), methodReference2.getFormalTypes().get(i)));
            if (!AbstractC3042xe.b(iA4)) {
                return AbstractC3042xe.c(iA4);
            }
        }
        return methodReference.getFormalTypes().size() - methodReference2.getFormalTypes().size();
    }

    public static MethodReference b(ClassReference classReference) {
        ArrayReference arrayReferenceArray = Reference.array(Reference.classFromClass(String.class), 1);
        int i = AbstractC0551Hu.c;
        return Reference.method(classReference, "main", new Bc0(arrayReferenceArray), null);
    }

    public static MethodReference b(Class<?> cls) {
        return b(Reference.classFromClass(cls));
    }

    public static String b(MethodReference methodReference) {
        return a(methodReference, true, true);
    }

    public static MethodReference a(Class<?> cls) {
        return a(Reference.classFromClass(cls));
    }

    public static MethodReference a(ClassReference classReference) {
        return Reference.method(classReference, "<init>", Collections.EMPTY_LIST, null);
    }

    public static MethodReference a(ClassReference classReference, TypeReference... typeReferenceArr) {
        return Reference.method(classReference, "<init>", Arrays.asList(typeReferenceArr), null);
    }

    public static Comparator<MethodReference> a() {
        return a;
    }

    public static MethodReference a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return Reference.methodFromMethod(cls.getDeclaredMethod(str, clsArr));
        } catch (NoSuchMethodException e) {
            rc6.a(e);
            return null;
        }
    }

    public static MethodReference a(int i, String str) {
        String strSubstring;
        int iIndexOf;
        String strSubstring2 = str.substring(0, i);
        Comparator comparator = C1758id.a;
        ClassReference classReferenceClassFromDescriptor = C0929Wj.z(strSubstring2) ? Reference.classFromDescriptor(strSubstring2) : null;
        if (classReferenceClassFromDescriptor == null || (iIndexOf = (strSubstring = str.substring(i + 2)).indexOf(40)) <= 0) {
            return null;
        }
        String strSubstring3 = strSubstring.substring(0, iIndexOf);
        String strSubstring4 = strSubstring.substring(iIndexOf);
        ArrayList arrayList = new ArrayList();
        for (String str2 : C0929Wj.e(strSubstring4)) {
            arrayList.add(Reference.typeFromDescriptor(str2));
        }
        return Reference.method(classReferenceClassFromDescriptor, strSubstring3, arrayList, Reference.returnTypeFromDescriptor(C0929Wj.u(strSubstring4)));
    }

    public static C0322w2 a(MethodReference methodReference, com.android.tools.r8.graph.B1 b1) {
        return b1.a(C1758id.a(methodReference.getHolderClass(), b1), C3136yj0.a(methodReference.getFormalTypes(), methodReference.getReturnType(), b1), methodReference.getMethodName());
    }

    public static String a(MethodReference methodReference) {
        return methodReference.getHolderClass().getDescriptor() + "->" + methodReference.getMethodName() + methodReference.getMethodDescriptor();
    }

    public static String a(MethodReference methodReference, boolean z, boolean z2) {
        String typeName;
        StringBuilder sb = new StringBuilder();
        if (z2) {
            if (methodReference.getReturnType() != null) {
                typeName = methodReference.getReturnType().getTypeName();
            } else {
                typeName = "void";
            }
            sb.append(typeName);
            sb.append(" ");
        }
        if (z) {
            sb.append(methodReference.getHolderClass().getTypeName());
            sb.append(".");
        }
        sb.append(methodReference.getMethodName());
        sb.append("(");
        Iterator<TypeReference> it = methodReference.getFormalTypes().iterator();
        if (it.hasNext()) {
            sb.append(it.next().getTypeName());
            while (it.hasNext()) {
                sb.append(", ");
                sb.append(it.next().getTypeName());
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static MethodReference a(String str) {
        int iIndexOf = str.indexOf(";") + 1;
        int iIndexOf2 = str.indexOf("(", iIndexOf);
        return Reference.methodFromDescriptor(str.substring(0, iIndexOf), str.substring(iIndexOf, iIndexOf2), str.substring(iIndexOf2));
    }
}
