package org.jetbrains.kotlin.fir.types.builder;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.impl.FirPlaceholderProjectionImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tÊ\u0001\u0002\b\r¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/builder/FirPlaceholderProjectionBuilder;", "", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "build", "Lorg/jetbrains/kotlin/fir/types/FirPlaceholderProjection;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FirPlaceholderProjectionBuilder {
    private KtSourceElement source;

    public final FirPlaceholderProjection build() {
        return new FirPlaceholderProjectionImpl(this.source);
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
