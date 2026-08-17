package org.jetbrains.kotlin.fir.types.builder;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirTypeProjectionWithVarianceImpl;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/builder/FirTypeProjectionWithVarianceBuilder;", "", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "setVariance", "(Lorg/jetbrains/kotlin/types/Variance;)V", "build", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirTypeProjectionWithVarianceBuilder {
    private KtSourceElement source;
    public FirTypeRef typeRef;
    public Variance variance;

    public final FirTypeProjectionWithVariance build() {
        return new FirTypeProjectionWithVarianceImpl(this.source, getTypeRef(), getVariance());
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final FirTypeRef getTypeRef() {
        FirTypeRef firTypeRef = this.typeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("typeRef");
        return null;
    }

    public final Variance getVariance() {
        Variance variance = this.variance;
        if (variance != null) {
            return variance;
        }
        Intrinsics.throwUninitializedPropertyAccessException("variance");
        return null;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.typeRef = firTypeRef;
    }

    public final void setVariance(Variance variance) {
        variance.getClass();
        this.variance = variance;
    }
}
