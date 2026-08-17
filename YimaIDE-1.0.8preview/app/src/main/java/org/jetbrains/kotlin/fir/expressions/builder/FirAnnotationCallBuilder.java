package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.impl.FirAnnotationCallImpl;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010;\u001a\u00020<H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001e\u00105\u001a\u0006\u0012\u0002\b\u000306X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:RH\u0010?\u001a\u0004\u0018\u00010>2\b\u0010=\u001a\u0004\u0018\u00010>8V@VX\u0097\u000er\u0018\bE\u0012\b\bF\u0012\u0004\b\b(G\u0012\n\bH\u0012\u0006\b\n0I8J¢\u0006\u0012\u0012\u0004\b@\u0010\u0005\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR<\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u00198\u0016X\u0097\u0004r\u0018\bE\u0012\b\bF\u0012\u0004\b\b(O\u0012\n\bH\u0012\u0006\b\n0I8J¢\u0006\u000e\n\u0000\u0012\u0004\bM\u0010\u0005\u001a\u0004\bN\u0010\u001cÊ\u0001\u0002\bQ¨\u0006P"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirAnnotationCallBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirCallBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getUseSiteTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "setUseSiteTarget", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setAnnotationTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "()Ljava/util/List;", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "getArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "setArgumentList", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;)V", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "setArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "annotationResolvePhase", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;", "getAnnotationResolvePhase", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;", "setAnnotationResolvePhase", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationResolvePhase;)V", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "setContainingDeclarationSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "coneTypeOrNull", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lkotlin/Deprecated;", "message", "Modification of 'coneTypeOrNull' has no impact for FirAnnotationCallBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations$annotations", "getAnnotations", "Modification of 'annotations' has no impact for FirAnnotationCallBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationCallBuilder implements FirAnnotationContainerBuilder, FirCallBuilder, FirExpressionBuilder {
    public FirReference calleeReference;
    public FirBasedSymbol<?> containingDeclarationSymbol;
    private KtSourceElement source;
    private AnnotationUseSiteTarget useSiteTarget;
    private FirTypeRef annotationTypeRef = FirImplicitTypeRefImplWithoutSource.INSTANCE;
    private final List<FirTypeProjection> typeArguments = new ArrayList();
    private FirArgumentList argumentList = FirEmptyArgumentList.INSTANCE;
    private FirAnnotationArgumentMapping argumentMapping = FirEmptyAnnotationArgumentMapping.INSTANCE;
    private FirAnnotationResolvePhase annotationResolvePhase = FirAnnotationResolvePhase.Unresolved;
    private final List<FirAnnotation> annotations = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'annotations' has no impact for FirAnnotationCallBuilder")
    public static /* synthetic */ void getAnnotations$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'coneTypeOrNull' has no impact for FirAnnotationCallBuilder")
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirAnnotationCall mo288build() {
        return new FirAnnotationCallImpl(getSource(), this.useSiteTarget, this.annotationTypeRef, FirBuilderDslKt.toMutableOrEmpty(this.typeArguments), getArgumentList(), getCalleeReference(), this.argumentMapping, this.annotationResolvePhase, getContainingDeclarationSymbol(), null);
    }

    public final FirAnnotationResolvePhase getAnnotationResolvePhase() {
        return this.annotationResolvePhase;
    }

    public final FirTypeRef getAnnotationTypeRef() {
        return this.annotationTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ List getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder
    public FirArgumentList getArgumentList() {
        return this.argumentList;
    }

    public final FirAnnotationArgumentMapping getArgumentMapping() {
        return this.argumentMapping;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirReference getCalleeReference() throws UninitializedPropertyAccessException {
        FirReference firReference = this.calleeReference;
        if (firReference != null) {
            return firReference;
        }
        Intrinsics.throwUninitializedPropertyAccessException("calleeReference");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ ConeKotlinType getConeTypeOrNull() {
        throw new IllegalStateException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirBasedSymbol<?> getContainingDeclarationSymbol() throws UninitializedPropertyAccessException {
        FirBasedSymbol<?> firBasedSymbol = this.containingDeclarationSymbol;
        if (firBasedSymbol != null) {
            return firBasedSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("containingDeclarationSymbol");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    public final List<FirTypeProjection> getTypeArguments() {
        return this.typeArguments;
    }

    public final AnnotationUseSiteTarget getUseSiteTarget() {
        return this.useSiteTarget;
    }

    public final void setAnnotationResolvePhase(FirAnnotationResolvePhase firAnnotationResolvePhase) {
        firAnnotationResolvePhase.getClass();
        this.annotationResolvePhase = firAnnotationResolvePhase;
    }

    public final void setAnnotationTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.annotationTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder
    public void setArgumentList(FirArgumentList firArgumentList) {
        firArgumentList.getClass();
        this.argumentList = firArgumentList;
    }

    public final void setArgumentMapping(FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firAnnotationArgumentMapping.getClass();
        this.argumentMapping = firAnnotationArgumentMapping;
    }

    public final void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        throw new IllegalStateException();
    }

    public final void setContainingDeclarationSymbol(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        this.containingDeclarationSymbol = firBasedSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setUseSiteTarget(AnnotationUseSiteTarget annotationUseSiteTarget) {
        this.useSiteTarget = annotationUseSiteTarget;
    }
}
