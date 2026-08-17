package org.jetbrains.kotlin.fir.contracts.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00152\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u0017J)\u0010\u0018\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00130\u001a2\u0006\u0010\u0016\u001a\u0002H\u0013H\u0016¢\u0006\u0002\u0010\u001bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/impl/FirLazyContractDescriptionImpl;", "Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "contractCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getContractCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/contracts/impl/FirLazyContractDescriptionImpl;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyContractDescriptionImpl extends FirLazyContractDescription {
    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription, org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription
    public FirFunctionCall getContractCall() {
        throw new IllegalStateException("FirLazyContractDescription should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription, org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription
    public ConeDiagnostic getDiagnostic() {
        throw new IllegalStateException("FirLazyContractDescription should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription, org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription, org.jetbrains.kotlin.fir.contracts.FirContractDescription, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        throw new IllegalStateException("FirLazyContractDescription should be calculated before accessing");
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirLazyContractDescriptionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
