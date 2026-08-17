package org.jetbrains.kotlin.fir.contracts.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019J)\u0010\u001a\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00150\u001c2\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u001dR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/impl/FirLegacyRawContractDescriptionImpl;", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "contractCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getContractCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "setContractCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/contracts/impl/FirLegacyRawContractDescriptionImpl;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLegacyRawContractDescriptionImpl extends FirLegacyRawContractDescription {
    private FirFunctionCall contractCall;
    private final ConeDiagnostic diagnostic;
    private final KtSourceElement source;

    public FirLegacyRawContractDescriptionImpl(KtSourceElement ktSourceElement, FirFunctionCall firFunctionCall, ConeDiagnostic coneDiagnostic) {
        firFunctionCall.getClass();
        this.source = ktSourceElement;
        this.contractCall = firFunctionCall;
        this.diagnostic = coneDiagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getContractCall().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription
    public FirFunctionCall getContractCall() {
        return this.contractCall;
    }

    @Override // org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription
    public ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription, org.jetbrains.kotlin.fir.contracts.FirContractDescription, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    public void setContractCall(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        this.contractCall = firFunctionCall;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirLegacyRawContractDescriptionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setContractCall((FirFunctionCall) getContractCall().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
