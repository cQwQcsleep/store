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
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0000\u0018\u00002\u00020\u0001B]\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014¢\u0006\u0004\b\u0015\u0010\u0016J5\u0010B\u001a\u00020C\"\u0004\b\u0000\u0010D\"\u0004\b\u0001\u0010E2\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u0002HD\u0012\u0004\u0012\u0002HE0G2\u0006\u0010H\u001a\u0002HEH\u0016¢\u0006\u0002\u0010IJ)\u0010J\u001a\u00020\u0000\"\u0004\b\u0000\u0010E2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HE0L2\u0006\u0010H\u001a\u0002HEH\u0016¢\u0006\u0002\u0010MJ)\u0010N\u001a\u00020\u0000\"\u0004\b\u0000\u0010E2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HE0L2\u0006\u0010H\u001a\u0002HEH\u0016¢\u0006\u0002\u0010MJ)\u0010O\u001a\u00020\u0000\"\u0004\b\u0000\u0010E2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HE0L2\u0006\u0010H\u001a\u0002HEH\u0016¢\u0006\u0002\u0010MJ)\u0010P\u001a\u00020\u0000\"\u0004\b\u0000\u0010E2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HE0L2\u0006\u0010H\u001a\u0002HEH\u0016¢\u0006\u0002\u0010MJ)\u0010Q\u001a\u00020\u0000\"\u0004\b\u0000\u0010E2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002HE0L2\u0006\u0010H\u001a\u0002HEH\u0016¢\u0006\u0002\u0010MJ\u0012\u0010R\u001a\u00020C2\b\u0010S\u001a\u0004\u0018\u000109H\u0016J\u0016\u0010T\u001a\u00020C2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020@0?H\u0016J\u0012\u0010V\u001a\u00020C2\b\u0010W\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010X\u001a\u00020C2\u0006\u0010Y\u001a\u00020\u0007H\u0016J\u0016\u0010Z\u001a\u00020C2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\n0?H\u0016J\u0010\u0010\\\u001a\u00020C2\u0006\u0010]\u001a\u00020\fH\u0016J\u0010\u0010^\u001a\u00020C2\u0006\u0010_\u001a\u00020\u000eH\u0016J\u0010\u0010`\u001a\u00020C2\u0006\u0010a\u001a\u00020\u0010H\u0016J\u0010\u0010b\u001a\u00020C2\u0006\u0010c\u001a\u00020\u0012H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\u0011\u001a\u00020\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0018\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001c\u00108\u001a\u0004\u0018\u0001098VX\u0096\u0004¢\u0006\f\u0012\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u001a\u0010>\u001a\b\u0012\u0004\u0012\u00020@0?8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\"¨\u0006d"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirAnnotationCallImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "typeArguments", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "annotationResolvePhase", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;Lorg/jetbrains/kotlin/fir/references/FirReference;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getUseSiteTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "setUseSiteTarget", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setAnnotationTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getTypeArguments-5e3fPpI", "()Ljava/util/List;", "setTypeArguments-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "setArgumentList", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;)V", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "setArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "getAnnotationResolvePhase", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;", "setAnnotationResolvePhase", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;)V", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirAnnotationCallImpl;", "transformAnnotations", "transformAnnotationTypeRef", "transformTypeArguments", "transformCalleeReference", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceUseSiteTarget", "newUseSiteTarget", "replaceAnnotationTypeRef", "newAnnotationTypeRef", "replaceTypeArguments", "newTypeArguments", "replaceArgumentList", "newArgumentList", "replaceCalleeReference", "newCalleeReference", "replaceArgumentMapping", "newArgumentMapping", "replaceAnnotationResolvePhase", "newAnnotationResolvePhase", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationCallImpl extends FirAnnotationCall {
    private FirAnnotationResolvePhase annotationResolvePhase;
    private FirTypeRef annotationTypeRef;
    private FirArgumentList argumentList;
    private FirAnnotationArgumentMapping argumentMapping;
    private FirReference calleeReference;
    private final FirBasedSymbol<?> containingDeclarationSymbol;
    private final KtSourceElement source;
    private List<FirTypeProjection> typeArguments;
    private AnnotationUseSiteTarget useSiteTarget;

    private FirAnnotationCallImpl(KtSourceElement ktSourceElement, AnnotationUseSiteTarget annotationUseSiteTarget, FirTypeRef firTypeRef, List<FirTypeProjection> list, FirArgumentList firArgumentList, FirReference firReference, FirAnnotationArgumentMapping firAnnotationArgumentMapping, FirAnnotationResolvePhase firAnnotationResolvePhase, FirBasedSymbol<?> firBasedSymbol) {
        firTypeRef.getClass();
        firArgumentList.getClass();
        firReference.getClass();
        firAnnotationArgumentMapping.getClass();
        firAnnotationResolvePhase.getClass();
        firBasedSymbol.getClass();
        this.source = ktSourceElement;
        this.useSiteTarget = annotationUseSiteTarget;
        this.annotationTypeRef = firTypeRef;
        this.typeArguments = list;
        this.argumentList = firArgumentList;
        this.calleeReference = firReference;
        this.argumentMapping = firAnnotationArgumentMapping;
        this.annotationResolvePhase = firAnnotationResolvePhase;
        this.containingDeclarationSymbol = firBasedSymbol;
    }

    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getAnnotationTypeRef().accept(visitor, data);
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m374getTypeArguments5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirTypeProjection) it.next()).accept(visitor, data);
        }
        getArgumentList().accept(visitor, data);
        getCalleeReference().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
    public FirAnnotationResolvePhase getAnnotationResolvePhase() {
        return this.annotationResolvePhase;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public FirTypeRef getAnnotationTypeRef() {
        return this.annotationTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirCall
    public FirArgumentList getArgumentList() {
        return this.argumentList;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public FirAnnotationArgumentMapping getArgumentMapping() {
        return this.argumentMapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return FirTypeUtilsKt.getConeTypeOrNull(getAnnotationTypeRef());
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
    public FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return this.containingDeclarationSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ List getTypeArguments() {
        return MutableOrEmptyList.m194boximpl(m374getTypeArguments5e3fPpI());
    }

    /* JADX INFO: renamed from: getTypeArguments-5e3fPpI, reason: not valid java name */
    public List<FirTypeProjection> m374getTypeArguments5e3fPpI() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public AnnotationUseSiteTarget getUseSiteTarget() {
        return this.useSiteTarget;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
    public void replaceAnnotationResolvePhase(FirAnnotationResolvePhase newAnnotationResolvePhase) {
        newAnnotationResolvePhase.getClass();
        setAnnotationResolvePhase(newAnnotationResolvePhase);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceAnnotationTypeRef(FirTypeRef newAnnotationTypeRef) {
        newAnnotationTypeRef.getClass();
        setAnnotationTypeRef(newAnnotationTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirCall
    public void replaceArgumentList(FirArgumentList newArgumentList) {
        newArgumentList.getClass();
        setArgumentList(newArgumentList);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceArgumentMapping(FirAnnotationArgumentMapping newArgumentMapping) {
        newArgumentMapping.getClass();
        setArgumentMapping(newArgumentMapping);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        if (Intrinsics.areEqual(newConeTypeOrNull, getConeTypeOrNull())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(FirAnnotationCallImpl.class.getSimpleName());
        sb.append(".replaceConeTypeOrNull() called with invalid type '");
        sb.append(newConeTypeOrNull);
        ia5.a(sb, "'. Current type is '", getConeTypeOrNull(), 39);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments) {
        newTypeArguments.getClass();
        m375setTypeArgumentsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newTypeArguments));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceUseSiteTarget(AnnotationUseSiteTarget newUseSiteTarget) {
        setUseSiteTarget(newUseSiteTarget);
    }

    public void setAnnotationResolvePhase(FirAnnotationResolvePhase firAnnotationResolvePhase) {
        firAnnotationResolvePhase.getClass();
        this.annotationResolvePhase = firAnnotationResolvePhase;
    }

    public void setAnnotationTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.annotationTypeRef = firTypeRef;
    }

    public void setArgumentList(FirArgumentList firArgumentList) {
        firArgumentList.getClass();
        this.argumentList = firArgumentList;
    }

    public void setArgumentMapping(FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firAnnotationArgumentMapping.getClass();
        this.argumentMapping = firAnnotationArgumentMapping;
    }

    public void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    /* JADX INFO: renamed from: setTypeArguments-GqUYU-s, reason: not valid java name */
    public void m375setTypeArgumentsGqUYUs(List<FirTypeProjection> list) {
        this.typeArguments = list;
    }

    public void setUseSiteTarget(AnnotationUseSiteTarget annotationUseSiteTarget) {
        this.useSiteTarget = annotationUseSiteTarget;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public <D> FirAnnotationCallImpl transformAnnotationTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setAnnotationTypeRef((FirTypeRef) getAnnotationTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirAnnotationCallImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirAnnotationCallImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotationTypeRef((FirTransformer) transformer, (Object) data);
        transformTypeArguments((FirTransformer) transformer, (Object) data);
        setArgumentList((FirArgumentList) getArgumentList().transform(transformer, data));
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public <D> FirAnnotationCallImpl transformTypeArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m374getTypeArguments5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirAnnotationCallImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotation transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCall transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotationCall transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotation transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotationCall transformAnnotationTypeRef(FirTransformer firTransformer, Object obj) {
        return transformAnnotationTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotation transformAnnotationTypeRef(FirTransformer firTransformer, Object obj) {
        return transformAnnotationTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationCall, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirAnnotationCall transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirAnnotationCallImpl(KtSourceElement ktSourceElement, AnnotationUseSiteTarget annotationUseSiteTarget, FirTypeRef firTypeRef, List list, FirArgumentList firArgumentList, FirReference firReference, FirAnnotationArgumentMapping firAnnotationArgumentMapping, FirAnnotationResolvePhase firAnnotationResolvePhase, FirBasedSymbol firBasedSymbol, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, annotationUseSiteTarget, firTypeRef, list, firArgumentList, firReference, firAnnotationArgumentMapping, firAnnotationResolvePhase, firBasedSymbol);
    }
}
