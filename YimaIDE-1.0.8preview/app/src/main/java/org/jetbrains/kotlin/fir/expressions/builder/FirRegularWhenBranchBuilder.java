package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.impl.FirRegularWhenBranch;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirRegularWhenBranchBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirAbstractWhenBranchBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "condition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCondition", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setCondition", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getResult", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setResult", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRegularWhenBranchBuilder implements FirAbstractWhenBranchBuilder {
    public FirExpression condition;
    public FirBlock result;
    private KtSourceElement source;

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public FirWhenBranch build() {
        return new FirRegularWhenBranch(getSource(), getCondition(), getResult());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public FirExpression getCondition() throws UninitializedPropertyAccessException {
        FirExpression firExpression = this.condition;
        if (firExpression != null) {
            return firExpression;
        }
        Intrinsics.throwUninitializedPropertyAccessException("condition");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public FirBlock getResult() throws UninitializedPropertyAccessException {
        FirBlock firBlock = this.result;
        if (firBlock != null) {
            return firBlock;
        }
        Intrinsics.throwUninitializedPropertyAccessException(CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME);
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public void setCondition(FirExpression firExpression) {
        firExpression.getClass();
        this.condition = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public void setResult(FirBlock firBlock) {
        firBlock.getClass();
        this.result = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
