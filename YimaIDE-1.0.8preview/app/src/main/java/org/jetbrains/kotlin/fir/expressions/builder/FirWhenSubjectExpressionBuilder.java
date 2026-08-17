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
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirWhenSubjectExpressionImpl;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010&\u001a\u00020'H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R<\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\r8\u0016X\u0097\u0004r\u0018\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u0005\u001a\u0004\b+\u0010\u0010R<\u00102\u001a\b\u0012\u0004\u0012\u0002030\r8\u0016X\u0097\u0004r\u0018\b,\u0012\b\b-\u0012\u0004\b\b(6\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u0005\u001a\u0004\b5\u0010\u0010RH\u00108\u001a\u0004\u0018\u00010)2\b\u00107\u001a\u0004\u0018\u00010)8V@VX\u0097\u000er\u0018\b,\u0012\b\b-\u0012\u0004\b\b(>\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u0012\u0012\u0004\b9\u0010\u0005\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=RH\u0010?\u001a\u0004\u0018\u00010)2\b\u00107\u001a\u0004\u0018\u00010)8V@VX\u0097\u000er\u0018\b,\u0012\b\b-\u0012\u0004\b\b(C\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u0012\u0012\u0004\b@\u0010\u0005\u001a\u0004\bA\u0010;\"\u0004\bB\u0010=RH\u0010D\u001a\u0004\u0018\u00010)2\b\u00107\u001a\u0004\u0018\u00010)8V@VX\u0097\u000er\u0018\b,\u0012\b\b-\u0012\u0004\b\b(H\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u0012\u0012\u0004\bE\u0010\u0005\u001a\u0004\bF\u0010;\"\u0004\bG\u0010=Ê\u0001\u0002\bJ¨\u0006I"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirWhenSubjectExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirQualifiedAccessExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "setContextSensitiveAlternative", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments$annotations", "getContextArguments", "Lkotlin/Deprecated;", "message", "Modification of 'contextArguments' has no impact for FirWhenSubjectExpressionBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments$annotations", "getTypeArguments", "Modification of 'typeArguments' has no impact for FirWhenSubjectExpressionBuilder", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "explicitReceiver", "getExplicitReceiver$annotations", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setExplicitReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "Modification of 'explicitReceiver' has no impact for FirWhenSubjectExpressionBuilder", "dispatchReceiver", "getDispatchReceiver$annotations", "getDispatchReceiver", "setDispatchReceiver", "Modification of 'dispatchReceiver' has no impact for FirWhenSubjectExpressionBuilder", "extensionReceiver", "getExtensionReceiver$annotations", "getExtensionReceiver", "setExtensionReceiver", "Modification of 'extensionReceiver' has no impact for FirWhenSubjectExpressionBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWhenSubjectExpressionBuilder implements FirAnnotationContainerBuilder, FirExpressionBuilder, FirQualifiedAccessExpressionBuilder {
    public FirNamedReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private FirPropertyAccessExpression contextSensitiveAlternative;
    private KtSourceElement source;
    private final List<FirAnnotation> annotations = new ArrayList();
    private final List<ConeDiagnostic> nonFatalDiagnostics = new ArrayList();
    private final List<FirExpression> contextArguments = new ArrayList();
    private final List<FirTypeProjection> typeArguments = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'contextArguments' has no impact for FirWhenSubjectExpressionBuilder")
    public static /* synthetic */ void getContextArguments$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'dispatchReceiver' has no impact for FirWhenSubjectExpressionBuilder")
    public static /* synthetic */ void getDispatchReceiver$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'explicitReceiver' has no impact for FirWhenSubjectExpressionBuilder")
    public static /* synthetic */ void getExplicitReceiver$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'extensionReceiver' has no impact for FirWhenSubjectExpressionBuilder")
    public static /* synthetic */ void getExtensionReceiver$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'typeArguments' has no impact for FirWhenSubjectExpressionBuilder")
    public static /* synthetic */ void getTypeArguments$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirWhenSubjectExpression build() {
        return new FirWhenSubjectExpressionImpl(getConeTypeOrNull(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getSource(), FirBuilderDslKt.toMutableOrEmpty(getNonFatalDiagnostics()), this.contextSensitiveAlternative, getCalleeReference(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirNamedReference getCalleeReference() throws UninitializedPropertyAccessException {
        FirNamedReference firNamedReference = this.calleeReference;
        if (firNamedReference != null) {
            return firNamedReference;
        }
        Intrinsics.throwUninitializedPropertyAccessException("calleeReference");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ List getContextArguments() {
        return this.contextArguments;
    }

    public final FirPropertyAccessExpression getContextSensitiveAlternative() {
        return this.contextSensitiveAlternative;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ FirExpression getDispatchReceiver() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ FirExpression getExplicitReceiver() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ FirExpression getExtensionReceiver() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public List<ConeDiagnostic> getNonFatalDiagnostics() {
        return this.nonFatalDiagnostics;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ List getTypeArguments() {
        return this.typeArguments;
    }

    public final void setCalleeReference(FirNamedReference firNamedReference) {
        firNamedReference.getClass();
        this.calleeReference = firNamedReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public final void setContextSensitiveAlternative(FirPropertyAccessExpression firPropertyAccessExpression) {
        this.contextSensitiveAlternative = firPropertyAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ void setDispatchReceiver(FirExpression firExpression) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ void setExplicitReceiver(FirExpression firExpression) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public /* synthetic */ void setExtensionReceiver(FirExpression firExpression) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
