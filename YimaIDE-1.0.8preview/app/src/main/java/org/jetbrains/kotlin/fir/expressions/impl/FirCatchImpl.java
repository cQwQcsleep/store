package org.jetbrains.kotlin.fir.expressions.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u00170\u00192\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001bJ)\u0010\u001c\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001e2\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001e2\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001fJ)\u0010!\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001e2\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001fJ)\u0010\"\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001e2\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001fR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirCatchImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "setParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "getBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBlock", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirCatchImpl;", "transformParameter", "transformBlock", "transformOtherChildren", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCatchImpl extends FirCatch {
    private FirBlock block;
    private FirProperty parameter;
    private final KtSourceElement source;

    public FirCatchImpl(KtSourceElement ktSourceElement, FirProperty firProperty, FirBlock firBlock) {
        firProperty.getClass();
        firBlock.getClass();
        this.source = ktSourceElement;
        this.parameter = firProperty;
        this.block = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getParameter().accept(visitor, data);
        getBlock().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public FirBlock getBlock() {
        return this.block;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public FirProperty getParameter() {
        return this.parameter;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    public void setBlock(FirBlock firBlock) {
        firBlock.getClass();
        this.block = firBlock;
    }

    public void setParameter(FirProperty firProperty) {
        firProperty.getClass();
        this.parameter = firProperty;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public <D> FirCatchImpl transformBlock(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setBlock((FirBlock) getBlock().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirCatchImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformParameter((FirTransformer) transformer, (Object) data);
        transformBlock((FirTransformer) transformer, (Object) data);
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public /* bridge */ /* synthetic */ FirCatch transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public <D> FirCatchImpl transformParameter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setParameter((FirProperty) getParameter().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public <D> FirCatchImpl transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public /* bridge */ /* synthetic */ FirCatch transformBlock(FirTransformer firTransformer, Object obj) {
        return transformBlock((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCatch
    public /* bridge */ /* synthetic */ FirCatch transformParameter(FirTransformer firTransformer, Object obj) {
        return transformParameter((FirTransformer<? super Object>) firTransformer, obj);
    }
}
