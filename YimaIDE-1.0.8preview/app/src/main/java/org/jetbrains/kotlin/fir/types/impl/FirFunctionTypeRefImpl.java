package org.jetbrains.kotlin.fir.types.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B[\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0004\b\u0011\u0010\u0012J5\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010'\"\u0004\b\u0001\u0010(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H(0*2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u0010,J)\u0010-\u001a\u00020\u0000\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00100J)\u00101\u001a\u00020\u0000\"\u0004\b\u0000\u0010(2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H(0/2\u0006\u0010+\u001a\u0002H(H\u0016¢\u0006\u0002\u00100J\u0016\u00102\u001a\u00020&2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000404H\u0016R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u001a\u0010\u000e\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u0014\u0010\u000f\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001aR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0014\u0010#\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001a¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirFunctionTypeRefImpl;", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isMarkedNullable", "", "receiverTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "parameters", "", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "returnTypeRef", "isSuspend", "contextParameterTypeRefs", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;ZLorg/jetbrains/kotlin/fir/types/FirTypeRef;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;ZLjava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "()Z", "getReceiverTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReceiverTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getParameters", "getReturnTypeRef", "setReturnTypeRef", "getContextParameterTypeRefs", "customRenderer", "getCustomRenderer", "acceptChildren", "", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/impl/FirFunctionTypeRefImpl;", "transformAnnotations", "replaceAnnotations", "newAnnotations", "", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirFunctionTypeRefImpl extends FirFunctionTypeRef {
    private List<FirAnnotation> annotations;
    private final List<FirTypeRef> contextParameterTypeRefs;
    private final boolean isMarkedNullable;
    private final boolean isSuspend;
    private final List<FirFunctionTypeParameter> parameters;
    private FirTypeRef receiverTypeRef;
    private FirTypeRef returnTypeRef;
    private final KtSourceElement source;

    private FirFunctionTypeRefImpl(List<FirAnnotation> list, KtSourceElement ktSourceElement, boolean z, FirTypeRef firTypeRef, List<FirFunctionTypeParameter> list2, FirTypeRef firTypeRef2, boolean z2, List<FirTypeRef> list3) {
        ktSourceElement.getClass();
        list2.getClass();
        firTypeRef2.getClass();
        list3.getClass();
        this.annotations = list;
        this.source = ktSourceElement;
        this.isMarkedNullable = z;
        this.receiverTypeRef = firTypeRef;
        this.parameters = list2;
        this.returnTypeRef = firTypeRef2;
        this.isSuspend = z2;
        this.contextParameterTypeRefs = list3;
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator it = MutableOrEmptyList.box-impl(m61getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        FirTypeRef receiverTypeRef = getReceiverTypeRef();
        if (receiverTypeRef != null) {
            receiverTypeRef.accept(visitor, data);
        }
        Iterator<T> it2 = getParameters().iterator();
        while (it2.hasNext()) {
            ((FirFunctionTypeParameter) it2.next()).accept(visitor, data);
        }
        getReturnTypeRef().accept(visitor, data);
        Iterator<T> it3 = getContextParameterTypeRefs().iterator();
        while (it3.hasNext()) {
            ((FirTypeRef) it3.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.box-impl(m61getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m61getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef
    public List<FirTypeRef> getContextParameterTypeRefs() {
        return this.contextParameterTypeRefs;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public boolean getCustomRenderer() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef
    public List<FirFunctionTypeParameter> getParameters() {
        return this.parameters;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef
    public FirTypeRef getReceiverTypeRef() {
        return this.receiverTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef
    public FirTypeRef getReturnTypeRef() {
        return this.returnTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef
    /* JADX INFO: renamed from: isSuspend, reason: from getter */
    public boolean getIsSuspend() {
        return this.isSuspend;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m62setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m62setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setReceiverTypeRef(FirTypeRef firTypeRef) {
        this.receiverTypeRef = firTypeRef;
    }

    public void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public <D> FirFunctionTypeRefImpl mo42transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace-aLnlfrU(m61getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    /* JADX INFO: renamed from: transformChildren, reason: collision with other method in class */
    public <D> FirFunctionTypeRefImpl m63transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        mo42transformAnnotations((FirTransformer) transformer, (Object) data);
        FirTypeRef receiverTypeRef = getReceiverTypeRef();
        setReceiverTypeRef(receiverTypeRef != null ? (FirTypeRef) receiverTypeRef.transform(transformer, data) : null);
        FirTransformerUtilKt.transformInplace(getParameters(), transformer, data);
        setReturnTypeRef((FirTypeRef) getReturnTypeRef().transform(transformer, data));
        FirTransformerUtilKt.transformInplace(getContextParameterTypeRefs(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirFunctionTypeRef mo42transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirTypeRef mo42transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirUnresolvedTypeRef mo37transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirFunctionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirFunctionTypeRefImpl(List list, KtSourceElement ktSourceElement, boolean z, FirTypeRef firTypeRef, List list2, FirTypeRef firTypeRef2, boolean z2, List list3, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, ktSourceElement, z, firTypeRef, list2, firTypeRef2, z2, list3);
    }

    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return m63transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
