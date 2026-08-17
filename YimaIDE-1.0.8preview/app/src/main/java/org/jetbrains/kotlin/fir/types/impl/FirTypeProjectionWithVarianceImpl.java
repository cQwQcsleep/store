package org.jetbrains.kotlin.fir.types.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019J)\u0010\u001a\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00150\u001c2\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u001dR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/impl/FirTypeProjectionWithVarianceImpl;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/types/Variance;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "acceptChildren", "", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/impl/FirTypeProjectionWithVarianceImpl;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirTypeProjectionWithVarianceImpl extends FirTypeProjectionWithVariance {
    private final KtSourceElement source;
    private FirTypeRef typeRef;
    private final Variance variance;

    public FirTypeProjectionWithVarianceImpl(KtSourceElement ktSourceElement, FirTypeRef firTypeRef, Variance variance) {
        firTypeRef.getClass();
        variance.getClass();
        this.source = ktSourceElement;
        this.typeRef = firTypeRef;
        this.variance = variance;
    }

    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getTypeRef().accept(visitor, data);
    }

    public KtSourceElement getSource() {
        return this.source;
    }

    public FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    public Variance getVariance() {
        return this.variance;
    }

    public void setTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.typeRef = firTypeRef;
    }

    /* JADX INFO: renamed from: transformChildren, reason: collision with other method in class */
    public <D> FirTypeProjectionWithVarianceImpl m73transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setTypeRef((FirTypeRef) getTypeRef().transform(transformer, data));
        return this;
    }

    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return m73transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
