package org.jetbrains.kotlin.fir.expressions.builder;

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
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirAnonymousFunctionExpressionImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015RH\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u00198V@VX\u0097\u000er\u0018\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\n\b#\u0012\u0006\b\n0$8%¢\u0006\u0012\u0012\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR:\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'8VX\u0097\u0004r\u0018\b \u0012\b\b!\u0012\u0004\b\b(,\u0012\n\b#\u0012\u0006\b\n0$8%¢\u0006\f\u0012\u0004\b)\u0010\u0004\u001a\u0004\b*\u0010+Ê\u0001\u0002\b.¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirAnonymousFunctionExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getAnonymousFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "setAnonymousFunction", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "isTrailingLambda", Argument.Delimiters.none, "()Z", "setTrailingLambda", "(Z)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "coneTypeOrNull", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lkotlin/Deprecated;", "message", "Modification of 'coneTypeOrNull' has no impact for FirAnonymousFunctionExpressionBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations$annotations", "getAnnotations", "()Ljava/util/List;", "Modification of 'annotations' has no impact for FirAnonymousFunctionExpressionBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionExpressionBuilder implements FirAnnotationContainerBuilder, FirExpressionBuilder {
    public FirAnonymousFunction anonymousFunction;
    private boolean isTrailingLambda;
    private KtSourceElement source;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'annotations' has no impact for FirAnonymousFunctionExpressionBuilder")
    public static /* synthetic */ void getAnnotations$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'coneTypeOrNull' has no impact for FirAnonymousFunctionExpressionBuilder")
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirAnonymousFunctionExpression mo288build() {
        return new FirAnonymousFunctionExpressionImpl(getSource(), getAnonymousFunction(), this.isTrailingLambda);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ List getAnnotations() {
        throw new IllegalStateException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirAnonymousFunction getAnonymousFunction() throws UninitializedPropertyAccessException {
        FirAnonymousFunction firAnonymousFunction = this.anonymousFunction;
        if (firAnonymousFunction != null) {
            return firAnonymousFunction;
        }
        Intrinsics.throwUninitializedPropertyAccessException("anonymousFunction");
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

    /* JADX INFO: renamed from: isTrailingLambda, reason: from getter */
    public final boolean getIsTrailingLambda() {
        return this.isTrailingLambda;
    }

    public final void setAnonymousFunction(FirAnonymousFunction firAnonymousFunction) {
        firAnonymousFunction.getClass();
        this.anonymousFunction = firAnonymousFunction;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setTrailingLambda(boolean z) {
        this.isTrailingLambda = z;
    }
}
