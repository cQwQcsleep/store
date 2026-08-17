package com.android.tools.r8.internal;

import com.android.tools.r8.internal.S90;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceTypeElement;
import com.android.tools.r8.retrace.RetraceTypeResult;
import com.android.tools.r8.retrace.RetracedTypeReference;
import com.android.tools.r8.retrace.Retracer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S90 implements RetraceTypeResult {
    public static final /* synthetic */ boolean d = true;
    public final TypeReference a;
    public final List b;
    public final Retracer c;

    public S90(TypeReference typeReference, List list, Retracer retracer) {
        this.a = typeReference;
        this.b = list;
        this.c = retracer;
    }

    public static List a(TypeReference typeReference, Retracer retracer) {
        if (typeReference == null) {
            return Collections.EMPTY_LIST;
        }
        if (typeReference.isPrimitive()) {
            return Collections.singletonList(new C1496fa0(typeReference));
        }
        if (typeReference.isArray()) {
            final int dimensions = typeReference.asArray().getDimensions();
            return C2847vL.a((Collection) a(typeReference.asArray().getBaseType(), retracer), new Function() { // from class: enc
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return S90.a(dimensions, (RetracedTypeReference) obj);
                }
            });
        }
        if (d || typeReference.isClass()) {
            return (List) retracer.retraceClass(typeReference.asClass()).stream().map(new Function() { // from class: fnc
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((RetraceClassElement) obj).getRetracedClass().getRetracedType();
                }
            }).collect(Collectors.toList());
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final void forEach(Consumer consumer) {
        stream().forEach(consumer);
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final boolean isAmbiguous() {
        return this.b.size() > 1;
    }

    @Override // com.android.tools.r8.retrace.RetraceTypeResult, com.android.tools.r8.retrace.RetraceResult
    public final boolean isEmpty() {
        return this.b.size() == 0;
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final Stream stream() {
        return C2847vL.a((Collection) this.b, new Function() { // from class: gnc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((RetracedTypeReference) obj);
            }
        }).stream();
    }

    public static RetracedTypeReference a(int i, RetracedTypeReference retracedTypeReference) {
        return new C1496fa0(Reference.array(retracedTypeReference.getTypeReference(), i));
    }

    public final /* synthetic */ RetraceTypeElement a(RetracedTypeReference retracedTypeReference) {
        return new R90(this, retracedTypeReference);
    }
}
