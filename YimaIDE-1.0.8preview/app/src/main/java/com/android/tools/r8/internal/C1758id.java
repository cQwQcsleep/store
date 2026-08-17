package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import java.util.Comparator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.id, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1758id {
    public static final Comparator a = Comparator.comparing(new Function() { // from class: x8h
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ClassReference) obj).getDescriptor();
        }
    });

    public static com.android.tools.r8.graph.I2 a(ClassReference classReference, com.android.tools.r8.graph.B1 b1) {
        return b1.e(classReference.getDescriptor());
    }

    public static String a(ClassReference classReference) {
        return classReference.getDescriptor();
    }

    public static Comparator<ClassReference> a() {
        return a;
    }
}
