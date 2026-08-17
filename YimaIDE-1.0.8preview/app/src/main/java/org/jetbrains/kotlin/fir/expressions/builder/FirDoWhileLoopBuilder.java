package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirDoWhileLoopImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\"\u001a\u00020#H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!Ê\u0001\u0002\b%¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirDoWhileLoopBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirLoopBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBlock", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "condition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCondition", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setCondition", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "getLabel", "()Lorg/jetbrains/kotlin/fir/FirLabel;", "setLabel", "(Lorg/jetbrains/kotlin/fir/FirLabel;)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDoWhileLoopBuilder implements FirAnnotationContainerBuilder, FirLoopBuilder {
    private final List<FirAnnotation> annotations = new ArrayList();
    public FirBlock block;
    public FirExpression condition;
    private FirLabel label;
    private KtSourceElement source;

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirDoWhileLoop build() {
        return new FirDoWhileLoopImpl(getSource(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getBlock(), getCondition(), getLabel(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public FirBlock getBlock() throws UninitializedPropertyAccessException {
        FirBlock firBlock = this.block;
        if (firBlock != null) {
            return firBlock;
        }
        Intrinsics.throwUninitializedPropertyAccessException("block");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public FirExpression getCondition() throws UninitializedPropertyAccessException {
        FirExpression firExpression = this.condition;
        if (firExpression != null) {
            return firExpression;
        }
        Intrinsics.throwUninitializedPropertyAccessException("condition");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public FirLabel getLabel() {
        return this.label;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public void setBlock(FirBlock firBlock) {
        firBlock.getClass();
        this.block = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public void setCondition(FirExpression firExpression) {
        firExpression.getClass();
        this.condition = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public void setLabel(FirLabel firLabel) {
        this.label = firLabel;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
