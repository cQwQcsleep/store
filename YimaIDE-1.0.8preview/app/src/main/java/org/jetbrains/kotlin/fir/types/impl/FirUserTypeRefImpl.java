package org.jetbrains.kotlin.fir.types.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ5\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0 2\u0006\u0010!\u001a\u0002H\u001eH\u0016¢\u0006\u0002\u0010\"J)\u0010#\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u0006\u0010!\u001a\u0002H\u001eH\u0016¢\u0006\u0002\u0010&J\u0016\u0010'\u001a\u00020\u001c2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0)H\u0016J)\u0010*\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u0006\u0010!\u001a\u0002H\u001eH\u0016¢\u0006\u0002\u0010+R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0012R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0015\u0010\u0014\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0012¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirUserTypeRefImpl;", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isMarkedNullable", "", "qualifier", "", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;ZLjava/util/List;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "()Z", "getQualifier", "()Ljava/util/List;", "getAnnotations-5e3fPpI", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "customRenderer", "getCustomRenderer", "acceptChildren", "", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/impl/FirUserTypeRefImpl;", "replaceAnnotations", "newAnnotations", "", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirUserTypeRefImpl extends FirUserTypeRef {
    private List<FirAnnotation> annotations;
    private final boolean isMarkedNullable;
    private final List<FirQualifierPart> qualifier;
    private KtSourceElement source;

    private FirUserTypeRefImpl(KtSourceElement ktSourceElement, boolean z, List<FirQualifierPart> list, List<FirAnnotation> list2) {
        ktSourceElement.getClass();
        list.getClass();
        this.source = ktSourceElement;
        this.isMarkedNullable = z;
        this.qualifier = list;
        this.annotations = list2;
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<FirQualifierPart> it = getQualifier().iterator();
        while (it.hasNext()) {
            Iterator it2 = it.next().getTypeArgumentList().getTypeArguments().iterator();
            while (it2.hasNext()) {
                ((FirTypeProjection) it2.next()).accept(visitor, data);
            }
        }
        Iterator it3 = MutableOrEmptyList.box-impl(m74getAnnotations5e3fPpI()).iterator();
        while (it3.hasNext()) {
            ((FirAnnotation) it3.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.box-impl(m74getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m74getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public boolean getCustomRenderer() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef
    public List<FirQualifierPart> getQualifier() {
        return this.qualifier;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: isMarkedNullable, reason: from getter */
    public boolean getIsMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m75setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m75setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setSource(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public <D> FirUserTypeRef mo42transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace-aLnlfrU(m74getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    /* JADX INFO: renamed from: transformChildren, reason: collision with other method in class */
    public <D> FirUserTypeRefImpl m76transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        Iterator<FirQualifierPart> it = getQualifier().iterator();
        while (it.hasNext()) {
            List typeArguments = it.next().getTypeArgumentList().getTypeArguments();
            typeArguments.getClass();
            FirTransformerUtilKt.transformInplace(TypeIntrinsics.asMutableList(typeArguments), transformer, data);
        }
        mo42transformAnnotations((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirTypeRef mo42transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    /* JADX INFO: renamed from: transformAnnotations */
    public /* bridge */ /* synthetic */ FirUnresolvedTypeRef mo37transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.types.FirUserTypeRef, org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return mo42transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirUserTypeRefImpl(KtSourceElement ktSourceElement, boolean z, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, z, list, list2);
    }

    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return m76transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
