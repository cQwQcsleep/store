package org.jetbrains.kotlin.fir.expressions.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.impl.FirCatchImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirCatchBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "setParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBlock", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCatchBuilder {
    public FirBlock block;
    public FirProperty parameter;
    private KtSourceElement source;

    public final FirCatch build() {
        return new FirCatchImpl(this.source, getParameter(), getBlock());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirBlock getBlock() throws UninitializedPropertyAccessException {
        FirBlock firBlock = this.block;
        if (firBlock != null) {
            return firBlock;
        }
        Intrinsics.throwUninitializedPropertyAccessException("block");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirProperty getParameter() throws UninitializedPropertyAccessException {
        FirProperty firProperty = this.parameter;
        if (firProperty != null) {
            return firProperty;
        }
        Intrinsics.throwUninitializedPropertyAccessException("parameter");
        return null;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final void setBlock(FirBlock firBlock) {
        firBlock.getClass();
        this.block = firBlock;
    }

    public final void setParameter(FirProperty firProperty) {
        firProperty.getClass();
        this.parameter = firProperty;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
