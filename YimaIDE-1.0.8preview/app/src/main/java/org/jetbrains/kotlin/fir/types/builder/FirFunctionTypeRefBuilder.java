package org.jetbrains.kotlin.fir.types.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirFunctionTypeRefImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010(\u001a\u00020)H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\bR\u001a\u0010 \u001a\u00020\u0018X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR+\u0010#\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\u0016\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\bÊ\u0001\u0002\b+¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/builder/FirFunctionTypeRefBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "annotations", "", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "<set-?>", "", "isMarkedNullable", "()Z", "setMarkedNullable", "(Z)V", "isMarkedNullable$delegate", "Lkotlin/properties/ReadWriteProperty;", "receiverTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReceiverTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReceiverTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "parameters", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "getParameters", "returnTypeRef", "getReturnTypeRef", "setReturnTypeRef", "isSuspend", "setSuspend", "isSuspend$delegate", "contextParameterTypeRefs", "getContextParameterTypeRefs", "build", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirFunctionTypeRefBuilder implements FirAnnotationContainerBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirFunctionTypeRefBuilder.class, "isMarkedNullable", "isMarkedNullable()Z", 0), new MutablePropertyReference1Impl<>(FirFunctionTypeRefBuilder.class, "isSuspend", "isSuspend()Z", 0)};
    private final List<FirAnnotation> annotations = new ArrayList();
    private final List<FirTypeRef> contextParameterTypeRefs;

    /* JADX INFO: renamed from: isMarkedNullable$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isMarkedNullable;

    /* JADX INFO: renamed from: isSuspend$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isSuspend;
    private final List<FirFunctionTypeParameter> parameters;
    private FirTypeRef receiverTypeRef;
    public FirTypeRef returnTypeRef;
    public KtSourceElement source;

    public FirFunctionTypeRefBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        this.isMarkedNullable = delegates.notNull();
        this.parameters = new ArrayList();
        this.isSuspend = delegates.notNull();
        this.contextParameterTypeRefs = new ArrayList();
    }

    public FirFunctionTypeRef build() {
        return new FirFunctionTypeRefImpl(FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getSource(), isMarkedNullable(), this.receiverTypeRef, this.parameters, getReturnTypeRef(), isSuspend(), this.contextParameterTypeRefs, null);
    }

    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final List<FirTypeRef> getContextParameterTypeRefs() {
        return this.contextParameterTypeRefs;
    }

    public final List<FirFunctionTypeParameter> getParameters() {
        return this.parameters;
    }

    public final FirTypeRef getReceiverTypeRef() {
        return this.receiverTypeRef;
    }

    public final FirTypeRef getReturnTypeRef() {
        FirTypeRef firTypeRef = this.returnTypeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("returnTypeRef");
        return null;
    }

    public final KtSourceElement getSource() {
        KtSourceElement ktSourceElement = this.source;
        if (ktSourceElement != null) {
            return ktSourceElement;
        }
        Intrinsics.throwUninitializedPropertyAccessException("source");
        return null;
    }

    public final boolean isMarkedNullable() {
        return ((Boolean) this.isMarkedNullable.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final boolean isSuspend() {
        return ((Boolean) this.isSuspend.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public final void setMarkedNullable(boolean z) {
        this.isMarkedNullable.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void setReceiverTypeRef(FirTypeRef firTypeRef) {
        this.receiverTypeRef = firTypeRef;
    }

    public final void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        this.source = ktSourceElement;
    }

    public final void setSuspend(boolean z) {
        this.isSuspend.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }
}
