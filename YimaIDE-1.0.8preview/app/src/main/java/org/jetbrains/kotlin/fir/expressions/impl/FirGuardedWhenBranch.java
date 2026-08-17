package org.jetbrains.kotlin.fir.expressions.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u001a\"\u0004\b\u0001\u0010\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001b0\u001d2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J)\u0010$\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J)\u0010%\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#J)\u0010&\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001b0\"2\u0006\u0010\u001e\u001a\u0002H\u001bH\u0016¢\u0006\u0002\u0010#R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirGuardedWhenBranch;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "condition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getCondition", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setCondition", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getResult", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setResult", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "hasGuard", Argument.Delimiters.none, "getHasGuard", "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirGuardedWhenBranch;", "transformCondition", "transformResult", "transformOtherChildren", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirGuardedWhenBranch extends FirWhenBranch {
    private FirExpression condition;
    private FirBlock result;
    private final KtSourceElement source;

    public FirGuardedWhenBranch(KtSourceElement ktSourceElement, FirExpression firExpression, FirBlock firBlock) {
        firExpression.getClass();
        firBlock.getClass();
        this.source = ktSourceElement;
        this.condition = firExpression;
        this.result = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getCondition().accept(visitor, data);
        getResult().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public FirExpression getCondition() {
        return this.condition;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public boolean getHasGuard() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public FirBlock getResult() {
        return this.result;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    public void setCondition(FirExpression firExpression) {
        firExpression.getClass();
        this.condition = firExpression;
    }

    public void setResult(FirBlock firBlock) {
        firBlock.getClass();
        this.result = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirGuardedWhenBranch transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformCondition((FirTransformer) transformer, (Object) data);
        transformResult((FirTransformer) transformer, (Object) data);
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public <D> FirGuardedWhenBranch transformCondition(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCondition((FirExpression) getCondition().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public /* bridge */ /* synthetic */ FirWhenBranch transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public <D> FirGuardedWhenBranch transformResult(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setResult((FirBlock) getResult().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public <D> FirGuardedWhenBranch transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public /* bridge */ /* synthetic */ FirWhenBranch transformCondition(FirTransformer firTransformer, Object obj) {
        return transformCondition((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenBranch
    public /* bridge */ /* synthetic */ FirWhenBranch transformResult(FirTransformer firTransformer, Object obj) {
        return transformResult((FirTransformer<? super Object>) firTransformer, obj);
    }
}
