package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1758id;
import com.android.tools.r8.internal.C3136yj0;
import com.android.tools.r8.references.ArrayReference;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.PrimitiveReference;
import com.android.tools.r8.references.TypeReference;
import defpackage.pah;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3136yj0 {
    public static final Comparator a = new Comparator() { // from class: hwi
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return C3136yj0.a((TypeReference) obj, (TypeReference) obj2);
        }
    };
    public static final /* synthetic */ boolean b = true;

    public static /* synthetic */ int a(TypeReference typeReference, TypeReference typeReference2) {
        if (typeReference == typeReference2) {
            return 0;
        }
        if (typeReference == null) {
            return -1;
        }
        if (typeReference2 == null) {
            return 1;
        }
        return typeReference.getDescriptor().compareTo(typeReference2.getDescriptor());
    }

    public static com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.B1 b1, Function function, TypeReference typeReference) {
        if (typeReference == null) {
            return b1.E1;
        }
        if (!typeReference.isPrimitive()) {
            if (typeReference.isArray()) {
                ArrayReference arrayReferenceAsArray = typeReference.asArray();
                return b1.a(arrayReferenceAsArray.getDimensions(), b(b1, function, arrayReferenceAsArray.getBaseType()));
            }
            if (b || typeReference.isClass()) {
                return (com.android.tools.r8.graph.I2) function.apply(typeReference.asClass());
            }
            x1f.a();
            return null;
        }
        PrimitiveReference primitiveReferenceAsPrimitive = typeReference.asPrimitive();
        char cCharAt = primitiveReferenceAsPrimitive.getDescriptor().charAt(0);
        if (cCharAt == 'F') {
            return b1.A1;
        }
        if (cCharAt == 'S') {
            return b1.D1;
        }
        if (cCharAt == 'Z') {
            return b1.w1;
        }
        if (cCharAt == 'I') {
            return b1.B1;
        }
        if (cCharAt == 'J') {
            return b1.C1;
        }
        switch (cCharAt) {
            case 'B':
                return b1.x1;
            case 'C':
                return b1.y1;
            case 'D':
                return b1.z1;
            default:
                pah.a("Invalid primitive descriptor: ", primitiveReferenceAsPrimitive.getDescriptor());
                return null;
        }
    }

    public static TypeReference a() {
        return null;
    }

    public static com.android.tools.r8.graph.E2 a(List list, TypeReference typeReference, final com.android.tools.r8.graph.B1 b1) {
        return a(list, typeReference, b1, new Function() { // from class: iwi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1758id.a((ClassReference) obj, b1);
            }
        });
    }

    public static com.android.tools.r8.graph.E2 a(List list, TypeReference typeReference, final com.android.tools.r8.graph.B1 b1, final Function function) {
        return b1.a(b(b1, function, typeReference), C2847vL.a((Collection) list, new Function() { // from class: jwi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3136yj0.b(b1, function, (TypeReference) obj);
            }
        }));
    }
}
