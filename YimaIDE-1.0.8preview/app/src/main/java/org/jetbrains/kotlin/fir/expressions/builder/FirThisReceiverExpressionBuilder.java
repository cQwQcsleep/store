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
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirThisReceiverExpressionImpl;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010(\u001a\u00020)H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010%\"\u0004\b&\u0010'R<\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\r8\u0016X\u0097\u0004r\u0018\b.\u0012\b\b/\u0012\u0004\b\b(0\u0012\n\b1\u0012\u0006\b\n0283¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u0005\u001a\u0004\b-\u0010\u0010RH\u00105\u001a\u0004\u0018\u00010+2\b\u00104\u001a\u0004\u0018\u00010+8V@VX\u0097\u000er\u0018\b.\u0012\b\b/\u0012\u0004\b\b(;\u0012\n\b1\u0012\u0006\b\n0283¢\u0006\u0012\u0012\u0004\b6\u0010\u0005\u001a\u0004\b7\u00108\"\u0004\b9\u0010:RH\u0010<\u001a\u0004\u0018\u00010+2\b\u00104\u001a\u0004\u0018\u00010+8V@VX\u0097\u000er\u0018\b.\u0012\b\b/\u0012\u0004\b\b(@\u0012\n\b1\u0012\u0006\b\n0283¢\u0006\u0012\u0012\u0004\b=\u0010\u0005\u001a\u0004\b>\u00108\"\u0004\b?\u0010:RH\u0010A\u001a\u0004\u0018\u00010+2\b\u00104\u001a\u0004\u0018\u00010+8V@VX\u0097\u000er\u0018\b.\u0012\b\b/\u0012\u0004\b\b(E\u0012\n\b1\u0012\u0006\b\n0283¢\u0006\u0012\u0012\u0004\bB\u0010\u0005\u001a\u0004\bC\u00108\"\u0004\bD\u0010:Ê\u0001\u0002\bG¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirThisReceiverExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirQualifiedAccessExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirThisReference;)V", "isImplicit", Argument.Delimiters.none, "()Z", "setImplicit", "(Z)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments$annotations", "getContextArguments", "Lkotlin/Deprecated;", "message", "Modification of 'contextArguments' has no impact for FirThisReceiverExpressionBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "explicitReceiver", "getExplicitReceiver$annotations", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setExplicitReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "Modification of 'explicitReceiver' has no impact for FirThisReceiverExpressionBuilder", "dispatchReceiver", "getDispatchReceiver$annotations", "getDispatchReceiver", "setDispatchReceiver", "Modification of 'dispatchReceiver' has no impact for FirThisReceiverExpressionBuilder", "extensionReceiver", "getExtensionReceiver$annotations", "getExtensionReceiver", "setExtensionReceiver", "Modification of 'extensionReceiver' has no impact for FirThisReceiverExpressionBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirThisReceiverExpressionBuilder implements FirAnnotationContainerBuilder, FirExpressionBuilder, FirQualifiedAccessExpressionBuilder {
    public FirThisReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private boolean isImplicit;
    private KtSourceElement source;
    private final List<FirAnnotation> annotations = new ArrayList();
    private final List<FirTypeProjection> typeArguments = new ArrayList();
    private final List<ConeDiagnostic> nonFatalDiagnostics = new ArrayList();
    private final List<FirExpression> contextArguments = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'contextArguments' has no impact for FirThisReceiverExpressionBuilder")
    public static /* synthetic */ void getContextArguments$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'dispatchReceiver' has no impact for FirThisReceiverExpressionBuilder")
    public static /* synthetic */ void getDispatchReceiver$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'explicitReceiver' has no impact for FirThisReceiverExpressionBuilder")
    public static /* synthetic */ void getExplicitReceiver$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'extensionReceiver' has no impact for FirThisReceiverExpressionBuilder")
    public static /* synthetic */ void getExtensionReceiver$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirThisReceiverExpression mo288build() {
        return new FirThisReceiverExpressionImpl(getConeTypeOrNull(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), FirBuilderDslKt.toMutableOrEmpty(getTypeArguments()), getSource(), FirBuilderDslKt.toMutableOrEmpty(getNonFatalDiagnostics()), getCalleeReference(), this.isImplicit, null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirThisReference getCalleeReference() throws UninitializedPropertyAccessException {
        FirThisReference firThisReference = this.calleeReference;
        if (firThisReference != null) {
            return firThisReference;
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
    public List<FirTypeProjection> getTypeArguments() {
        return this.typeArguments;
    }

    /* JADX INFO: renamed from: isImplicit, reason: from getter */
    public final boolean getIsImplicit() {
        return this.isImplicit;
    }

    public final void setCalleeReference(FirThisReference firThisReference) {
        firThisReference.getClass();
        this.calleeReference = firThisReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
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

    public final void setImplicit(boolean z) {
        this.isImplicit = z;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
