package org.jetbrains.kotlin.fir.types.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ5\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010 \"\u0004\b\u0001\u0010!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H!0#2\u0006\u0010$\u001a\u0002H!H\u0016¢\u0006\u0002\u0010%J)\u0010&\u001a\u00020\u0000\"\u0004\b\u0000\u0010!2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H!0(2\u0006\u0010$\u001a\u0002H!H\u0016¢\u0006\u0002\u0010)J)\u0010*\u001a\u00020\u0000\"\u0004\b\u0000\u0010!2\f\u0010'\u001a\b\u0012\u0004\u0012\u0002H!0(2\u0006\u0010$\u001a\u0002H!H\u0016¢\u0006\u0002\u0010)J\u0016\u0010+\u001a\u00020\u001f2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0016R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0015R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u000b\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirIntersectionTypeRefImpl;", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isMarkedNullable", "", "leftType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "rightType", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;ZLorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "()Z", "getLeftType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setLeftType", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getRightType", "setRightType", "customRenderer", "getCustomRenderer", "acceptChildren", "", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/impl/FirIntersectionTypeRefImpl;", "transformAnnotations", "replaceAnnotations", "newAnnotations", "", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirIntersectionTypeRefImpl extends FirIntersectionTypeRef {
    private List<FirAnnotation> annotations;
    private final boolean isMarkedNullable;
    private FirTypeRef leftType;
    private FirTypeRef rightType;
    private final KtSourceElement source;

    private FirIntersectionTypeRefImpl(List<FirAnnotation> list, KtSourceElement ktSourceElement, boolean z, FirTypeRef firTypeRef, FirTypeRef firTypeRef2) {
        ktSourceElement.getClass();
        firTypeRef.getClass();
        firTypeRef2.getClass();
        this.annotations = list;
        this.source = ktSourceElement;
        this.isMarkedNullable = z;
        this.leftType = firTypeRef;
        this.rightType = firTypeRef2;
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator it = MutableOrEmptyList.box-impl(m65getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getLeftType().accept(visitor, data);
        getRightType().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.box-impl(m65getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m65getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public boolean getCustomRenderer() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef
    public FirTypeRef getLeftType() {
        return this.leftType;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef
    public FirTypeRef getRightType() {
        return this.rightType;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m66setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m66setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setLeftType(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.leftType = firTypeRef;
    }

    public void setRightType(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.rightType = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public <D> FirIntersectionTypeRefImpl mo42transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace-aLnlfrU(m65getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    /* JADX INFO: renamed from: transformChildren, reason: collision with other method in class */
    public <D> FirIntersectionTypeRefImpl m67transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        mo42transformAnnotations((FirTransformer) transformer, (Object) data);
        setLeftType((FirTypeRef) getLeftType().transform(transformer, data));
        setRightType((FirTypeRef) getRightType().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirIntersectionTypeRef mo42transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirTypeRef mo42transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirUnresolvedTypeRef mo37transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirIntersectionTypeRefImpl(List list, KtSourceElement ktSourceElement, boolean z, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, ktSourceElement, z, firTypeRef, firTypeRef2);
    }

    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return m67transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
