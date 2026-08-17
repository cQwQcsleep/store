package org.jetbrains.kotlin.fir.contracts.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.impl.FirLegacyRawContractDescriptionImpl;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015Ê\u0001\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/builder/FirLegacyRawContractDescriptionBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "contractCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getContractCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "setContractCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "setDiagnostic", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "build", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLegacyRawContractDescriptionBuilder {
    public FirFunctionCall contractCall;
    private ConeDiagnostic diagnostic;
    private KtSourceElement source;

    public final FirLegacyRawContractDescription build() {
        return new FirLegacyRawContractDescriptionImpl(this.source, getContractCall(), this.diagnostic);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirFunctionCall getContractCall() throws UninitializedPropertyAccessException {
        FirFunctionCall firFunctionCall = this.contractCall;
        if (firFunctionCall != null) {
            return firFunctionCall;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractCall");
        return null;
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final void setContractCall(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        this.contractCall = firFunctionCall;
    }

    public final void setDiagnostic(ConeDiagnostic coneDiagnostic) {
        this.diagnostic = coneDiagnostic;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
