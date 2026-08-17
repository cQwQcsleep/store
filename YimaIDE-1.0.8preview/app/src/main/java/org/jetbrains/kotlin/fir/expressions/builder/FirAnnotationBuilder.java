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
import org.jetbrains.kotlin.fir.expressions.impl.FirAnnotationImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\"\u001a\u00020#H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!RH\u0010&\u001a\u0004\u0018\u00010%2\b\u0010$\u001a\u0004\u0018\u00010%8V@VX\u0097\u000er\u0018\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u0012\u0012\u0004\b'\u0010\u0004\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R<\u00102\u001a\b\u0012\u0004\u0012\u00020#0\u001e8\u0016X\u0097\u0004r\u0018\b,\u0012\b\b-\u0012\u0004\b\b(5\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u0004\u001a\u0004\b4\u0010!Ê\u0001\u0002\b7¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirAnnotationBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getUseSiteTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "setUseSiteTarget", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)V", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setAnnotationTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "setArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "()Ljava/util/List;", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "coneTypeOrNull", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lkotlin/Deprecated;", "message", "Modification of 'coneTypeOrNull' has no impact for FirAnnotationBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "annotations", "getAnnotations$annotations", "getAnnotations", "Modification of 'annotations' has no impact for FirAnnotationBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationBuilder implements FirAnnotationContainerBuilder, FirExpressionBuilder {
    public FirTypeRef annotationTypeRef;
    public FirAnnotationArgumentMapping argumentMapping;
    private KtSourceElement source;
    private AnnotationUseSiteTarget useSiteTarget;
    private final List<FirTypeProjection> typeArguments = new ArrayList();
    private final List<FirAnnotation> annotations = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'annotations' has no impact for FirAnnotationBuilder")
    public static /* synthetic */ void getAnnotations$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'coneTypeOrNull' has no impact for FirAnnotationBuilder")
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirAnnotation mo289build() {
        return new FirAnnotationImpl(getSource(), this.useSiteTarget, getAnnotationTypeRef(), getArgumentMapping(), FirBuilderDslKt.toMutableOrEmpty(this.typeArguments), null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirTypeRef getAnnotationTypeRef() throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef = this.annotationTypeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("annotationTypeRef");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ List getAnnotations() {
        return this.annotations;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirAnnotationArgumentMapping getArgumentMapping() throws UninitializedPropertyAccessException {
        FirAnnotationArgumentMapping firAnnotationArgumentMapping = this.argumentMapping;
        if (firAnnotationArgumentMapping != null) {
            return firAnnotationArgumentMapping;
        }
        Intrinsics.throwUninitializedPropertyAccessException("argumentMapping");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ ConeKotlinType getConeTypeOrNull() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    public final List<FirTypeProjection> getTypeArguments() {
        return this.typeArguments;
    }

    public final AnnotationUseSiteTarget getUseSiteTarget() {
        return this.useSiteTarget;
    }

    public final void setAnnotationTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.annotationTypeRef = firTypeRef;
    }

    public final void setArgumentMapping(FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firAnnotationArgumentMapping.getClass();
        this.argumentMapping = firAnnotationArgumentMapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setUseSiteTarget(AnnotationUseSiteTarget annotationUseSiteTarget) {
        this.useSiteTarget = annotationUseSiteTarget;
    }
}
