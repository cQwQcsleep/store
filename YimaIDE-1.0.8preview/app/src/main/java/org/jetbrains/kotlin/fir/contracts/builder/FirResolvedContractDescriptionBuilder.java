package org.jetbrains.kotlin.fir.contracts.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.contracts.FirContractElementDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.contracts.impl.FirResolvedContractDescriptionImpl;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0018\u001a\u00020\u0019R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017Ê\u0001\u0002\b\u001b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/builder/FirResolvedContractDescriptionBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "effects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;", "getEffects", "()Ljava/util/List;", "unresolvedEffects", "Lorg/jetbrains/kotlin/fir/contracts/FirContractElementDeclaration;", "getUnresolvedEffects", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "setDiagnostic", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "build", "Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedContractDescriptionBuilder {
    private ConeDiagnostic diagnostic;
    private KtSourceElement source;
    private final List<FirEffectDeclaration> effects = new ArrayList();
    private final List<FirContractElementDeclaration> unresolvedEffects = new ArrayList();

    public final FirResolvedContractDescription build() {
        return new FirResolvedContractDescriptionImpl(this.source, this.effects, this.unresolvedEffects, this.diagnostic);
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    public final List<FirEffectDeclaration> getEffects() {
        return this.effects;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final List<FirContractElementDeclaration> getUnresolvedEffects() {
        return this.unresolvedEffects;
    }

    public final void setDiagnostic(ConeDiagnostic coneDiagnostic) {
        this.diagnostic = coneDiagnostic;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
