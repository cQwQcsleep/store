package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirPureAbstractElement;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00152\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u0017J3\u0010\u0018\u001a\u0002H\u0019\"\b\b\u0000\u0010\u0019*\u00020\u0002\"\u0004\b\u0001\u0010\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001b2\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u001cJ)\u0010\u001d\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001b2\u0006\u0010\u0016\u001a\u0002H\u0013H&¢\u0006\u0002\u0010\u001eJ)\u0010\u001f\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001b2\u0006\u0010\u0016\u001a\u0002H\u0013H&¢\u0006\u0002\u0010\u001eJ)\u0010 \u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001b2\u0006\u0010\u0016\u001a\u0002H\u0013H&¢\u0006\u0002\u0010\u001eR\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "Lorg/jetbrains/kotlin/fir/FirPureAbstractElement;", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformParameter", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "transformBlock", "transformOtherChildren", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirCatch extends FirPureAbstractElement implements FirElement {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitCatch(this, data);
    }

    public abstract FirBlock getBlock();

    public abstract FirProperty getParameter();

    @Override // org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirCatch firCatchTransformCatch = transformer.transformCatch(this, data);
        firCatchTransformCatch.getClass();
        return firCatchTransformCatch;
    }

    public abstract <D> FirCatch transformBlock(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirCatch transformOtherChildren(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirCatch transformParameter(FirTransformer<? super D> transformer, D data);
}
