package org.jetbrains.kotlin.fir.expressions.impl;

import defpackage.ia5;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u00002\u00020\u0001B9\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ5\u0010+\u001a\u00020,\"\u0004\b\u0000\u0010-\"\u0004\b\u0001\u0010.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H.002\u0006\u00101\u001a\u0002H.H\u0016¢\u0006\u0002\u00102J)\u00103\u001a\u00020\u0000\"\u0004\b\u0000\u0010.2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H.052\u0006\u00101\u001a\u0002H.H\u0016¢\u0006\u0002\u00106J)\u00107\u001a\u00020\u0000\"\u0004\b\u0000\u0010.2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H.052\u0006\u00101\u001a\u0002H.H\u0016¢\u0006\u0002\u00106J)\u00108\u001a\u00020\u0000\"\u0004\b\u0000\u0010.2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H.052\u0006\u00101\u001a\u0002H.H\u0016¢\u0006\u0002\u00106J)\u00109\u001a\u00020\u0000\"\u0004\b\u0000\u0010.2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H.052\u0006\u00101\u001a\u0002H.H\u0016¢\u0006\u0002\u00106J\u0012\u0010:\u001a\u00020,2\b\u0010;\u001a\u0004\u0018\u00010#H\u0016J\u0016\u0010<\u001a\u00020,2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00010)H\u0016J\u0012\u0010>\u001a\u00020,2\b\u0010?\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010@\u001a\u00020,2\u0006\u0010A\u001a\u00020\u0007H\u0016J\u0010\u0010B\u001a\u00020,2\u0006\u0010C\u001a\u00020\tH\u0016J\u0016\u0010D\u001a\u00020,2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\f0)H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010\"\u001a\u0004\u0018\u00010#8VX\u0096\u0004¢\u0006\f\u0012\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u001e¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirAnnotationImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "typeArguments", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getUseSiteTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "setUseSiteTarget", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setAnnotationTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "setArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "getTypeArguments-5e3fPpI", "()Ljava/util/List;", "setTypeArguments-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", Argument.Delimiters.none, "getAnnotations", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirAnnotationImpl;", "transformAnnotations", "transformAnnotationTypeRef", "transformTypeArguments", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceUseSiteTarget", "newUseSiteTarget", "replaceAnnotationTypeRef", "newAnnotationTypeRef", "replaceArgumentMapping", "newArgumentMapping", "replaceTypeArguments", "newTypeArguments", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationImpl extends FirAnnotation {
    private FirTypeRef annotationTypeRef;
    private FirAnnotationArgumentMapping argumentMapping;
    private final KtSourceElement source;
    private List<FirTypeProjection> typeArguments;
    private AnnotationUseSiteTarget useSiteTarget;

    private FirAnnotationImpl(KtSourceElement ktSourceElement, AnnotationUseSiteTarget annotationUseSiteTarget, FirTypeRef firTypeRef, FirAnnotationArgumentMapping firAnnotationArgumentMapping, List<FirTypeProjection> list) {
        firTypeRef.getClass();
        firAnnotationArgumentMapping.getClass();
        this.source = ktSourceElement;
        this.useSiteTarget = annotationUseSiteTarget;
        this.annotationTypeRef = firTypeRef;
        this.argumentMapping = firAnnotationArgumentMapping;
        this.typeArguments = list;
    }

    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getAnnotationTypeRef().accept(visitor, data);
        getArgumentMapping().accept(visitor, data);
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m376getTypeArguments5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirTypeProjection) it.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public FirTypeRef getAnnotationTypeRef() {
        return this.annotationTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public FirAnnotationArgumentMapping getArgumentMapping() {
        return this.argumentMapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return FirTypeUtilsKt.getConeTypeOrNull(getAnnotationTypeRef());
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ List getTypeArguments() {
        return MutableOrEmptyList.m194boximpl(m376getTypeArguments5e3fPpI());
    }

    /* JADX INFO: renamed from: getTypeArguments-5e3fPpI, reason: not valid java name */
    public List<FirTypeProjection> m376getTypeArguments5e3fPpI() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public AnnotationUseSiteTarget getUseSiteTarget() {
        return this.useSiteTarget;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceAnnotationTypeRef(FirTypeRef newAnnotationTypeRef) {
        newAnnotationTypeRef.getClass();
        setAnnotationTypeRef(newAnnotationTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceArgumentMapping(FirAnnotationArgumentMapping newArgumentMapping) {
        newArgumentMapping.getClass();
        setArgumentMapping(newArgumentMapping);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        if (Intrinsics.areEqual(newConeTypeOrNull, getConeTypeOrNull())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(FirAnnotationImpl.class.getSimpleName());
        sb.append(".replaceConeTypeOrNull() called with invalid type '");
        sb.append(newConeTypeOrNull);
        ia5.a(sb, "'. Current type is '", getConeTypeOrNull(), 39);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments) {
        newTypeArguments.getClass();
        m377setTypeArgumentsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newTypeArguments));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceUseSiteTarget(AnnotationUseSiteTarget newUseSiteTarget) {
        setUseSiteTarget(newUseSiteTarget);
    }

    public void setAnnotationTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.annotationTypeRef = firTypeRef;
    }

    public void setArgumentMapping(FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firAnnotationArgumentMapping.getClass();
        this.argumentMapping = firAnnotationArgumentMapping;
    }

    /* JADX INFO: renamed from: setTypeArguments-GqUYU-s, reason: not valid java name */
    public void m377setTypeArgumentsGqUYUs(List<FirTypeProjection> list) {
        this.typeArguments = list;
    }

    public void setUseSiteTarget(AnnotationUseSiteTarget annotationUseSiteTarget) {
        this.useSiteTarget = annotationUseSiteTarget;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public <D> FirAnnotationImpl transformAnnotationTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setAnnotationTypeRef((FirTypeRef) getAnnotationTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirAnnotationImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotationTypeRef((FirTransformer) transformer, (Object) data);
        setArgumentMapping((FirAnnotationArgumentMapping) getArgumentMapping().transform(transformer, data));
        transformTypeArguments((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public <D> FirAnnotationImpl transformTypeArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m376getTypeArguments5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirAnnotationImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotation transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotation transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotation transformAnnotationTypeRef(FirTransformer firTransformer, Object obj) {
        return transformAnnotationTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirAnnotationImpl(KtSourceElement ktSourceElement, AnnotationUseSiteTarget annotationUseSiteTarget, FirTypeRef firTypeRef, FirAnnotationArgumentMapping firAnnotationArgumentMapping, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, annotationUseSiteTarget, firTypeRef, firAnnotationArgumentMapping, list);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
