package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0416Cp;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.Reference;
import java.util.Comparator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0416Cp {
    public static final Comparator a = new Comparator() { // from class: v13
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return C0416Cp.a((FieldReference) obj, (FieldReference) obj2);
        }
    };

    public static int a(FieldReference fieldReference, FieldReference fieldReference2) {
        int iA = AbstractC3042xe.a(C1758id.a().compare(fieldReference.getHolderClass(), fieldReference2.getHolderClass()));
        if (!AbstractC3042xe.b(iA)) {
            return AbstractC3042xe.c(iA);
        }
        int iA2 = AbstractC3042xe.a(fieldReference.getFieldName().compareTo(fieldReference2.getFieldName()));
        return !AbstractC3042xe.b(iA2) ? AbstractC3042xe.c(iA2) : C3136yj0.a.compare(fieldReference.getFieldType(), fieldReference2.getFieldType());
    }

    public static FieldReference a(Class<?> cls, String str) {
        try {
            return Reference.fieldFromField(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            rc6.a(e);
            return null;
        }
    }

    public static String a(FieldReference fieldReference) {
        return fieldReference.getFieldType().getTypeName() + " " + fieldReference.getHolderClass().getTypeName() + "." + fieldReference.getFieldName();
    }
}
