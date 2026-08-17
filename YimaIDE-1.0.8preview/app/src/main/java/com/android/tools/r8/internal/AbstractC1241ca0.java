package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1241ca0;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import defpackage.ykg;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ca0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1241ca0 implements RetracedMethodReference {
    public static final Comparator c = Comparator.comparing(new Function() { // from class: tkg
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((RetracedMethodReference) obj).getMethodName();
        }
    }).thenComparing(new Function() { // from class: ukg
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Boolean.valueOf(((RetracedMethodReference) obj).isKnown());
        }
    }).thenComparing(new Function() { // from class: vkg
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((RetracedMethodReference) obj).asKnown();
        }
    }, Comparator.nullsFirst(Comparator.comparing(new Function() { // from class: wkg
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return AbstractC1241ca0.a((RetracedMethodReference.KnownRetracedMethodReference) obj);
        }
    })).thenComparing(new Function() { // from class: xkg
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((RetracedMethodReference.KnownRetracedMethodReference) obj).getFormalTypes();
        }
    }, AbstractC2956we.b(Comparator.comparing(new ykg()))));
    public final OptionalInt b;

    public AbstractC1241ca0(OptionalInt optionalInt) {
        this.b = optionalInt;
    }

    public static /* synthetic */ String a(RetracedMethodReference.KnownRetracedMethodReference knownRetracedMethodReference) {
        if (knownRetracedMethodReference == null) {
            return null;
        }
        return knownRetracedMethodReference.isVoid() ? "void" : knownRetracedMethodReference.getReturnType().getTypeName();
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final int getOriginalPositionOrDefault(int i) {
        return this.b.orElse(i);
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final boolean hasPosition() {
        return this.b.isPresent();
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public final boolean isKnown() {
        return this instanceof C1072aa0;
    }

    @Override // com.android.tools.r8.retrace.RetracedMethodReference
    public boolean isUnknown() {
        return !(this instanceof C1072aa0);
    }

    public C1072aa0 a() {
        return null;
    }

    public static C1072aa0 a(MethodReference methodReference) {
        return new C1072aa0(methodReference, OptionalInt.empty());
    }
}
