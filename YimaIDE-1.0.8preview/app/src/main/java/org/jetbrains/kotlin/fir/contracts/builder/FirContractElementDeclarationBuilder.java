package org.jetbrains.kotlin.fir.contracts.builder;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.contracts.FirContractElementDeclaration;
import org.jetbrains.kotlin.fir.contracts.impl.FirContractElementDeclarationImpl;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0013\u001a\u00020\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u0002`\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012Ê\u0001\u0002\b\u0016¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/builder/FirContractElementDeclarationBuilder;", Argument.Delimiters.none, "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "effect", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractDescriptionElement;", "getEffect", "()Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "setEffect", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;)V", "build", "Lorg/jetbrains/kotlin/fir/contracts/FirContractElementDeclaration;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractElementDeclarationBuilder {
    public KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> effect;
    private KtSourceElement source;

    public final FirContractElementDeclaration build() {
        return new FirContractElementDeclarationImpl(this.source, getEffect());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> getEffect() throws UninitializedPropertyAccessException {
        KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement = this.effect;
        if (ktContractDescriptionElement != null) {
            return ktContractDescriptionElement;
        }
        Intrinsics.throwUninitializedPropertyAccessException("effect");
        return null;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final void setEffect(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement) {
        ktContractDescriptionElement.getClass();
        this.effect = ktContractDescriptionElement;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
