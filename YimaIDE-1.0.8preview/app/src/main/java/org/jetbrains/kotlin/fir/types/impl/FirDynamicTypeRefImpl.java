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
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ5\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00180\u001a2\u0006\u0010\u001b\u001a\u0002H\u0018H\u0016¢\u0006\u0002\u0010\u001cJ)\u0010\u001d\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001f2\u0006\u0010\u001b\u001a\u0002H\u0018H\u0016¢\u0006\u0002\u0010 J)\u0010!\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001f2\u0006\u0010\u001b\u001a\u0002H\u0018H\u0016¢\u0006\u0002\u0010 J\u0016\u0010\"\u001a\u00020\u00162\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040$H\u0016R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirDynamicTypeRefImpl;", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isMarkedNullable", "", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "()Z", "customRenderer", "getCustomRenderer", "acceptChildren", "", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/impl/FirDynamicTypeRefImpl;", "transformAnnotations", "replaceAnnotations", "newAnnotations", "", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirDynamicTypeRefImpl extends FirDynamicTypeRef {
    private List<FirAnnotation> annotations;
    private final boolean isMarkedNullable;
    private final KtSourceElement source;

    private FirDynamicTypeRefImpl(List<FirAnnotation> list, KtSourceElement ktSourceElement, boolean z) {
        ktSourceElement.getClass();
        this.annotations = list;
        this.source = ktSourceElement;
        this.isMarkedNullable = z;
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator it = MutableOrEmptyList.box-impl(m47getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
    }

    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.box-impl(m47getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m47getAnnotations5e3fPpI() {
        return this.annotations;
    }

    public boolean getCustomRenderer() {
        return false;
    }

    public KtSourceElement getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m48setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m48setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public <D> FirDynamicTypeRefImpl m52transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace-aLnlfrU(m47getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    /* JADX INFO: renamed from: transformChildren, reason: collision with other method in class */
    public <D> FirDynamicTypeRefImpl m53transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        m52transformAnnotations((FirTransformer) transformer, (Object) data);
        return this;
    }

    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return m53transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirDynamicTypeRef m49transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m52transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirTypeRef m50transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m52transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirDynamicTypeRefImpl(List list, KtSourceElement ktSourceElement, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, ktSourceElement, z);
    }

    /* JADX INFO: renamed from: transformAnnotations, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ FirUnresolvedTypeRef m51transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m52transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return m52transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
