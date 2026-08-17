package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B#\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJA\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u00142\u001e\u0010\u0015\u001a\u001a\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00162\u0006\u0010\u0017\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010\u0018R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/KtCallsEffectDeclaration;", "Type", "Diagnostic", "Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "valueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "kind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;)V", "getValueParameterReference", "()Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "getKind", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "erroneous", Argument.Delimiters.none, "getErroneous", "()Z", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class KtCallsEffectDeclaration<Type, Diagnostic> extends KtEffectDeclaration<Type, Diagnostic> {
    private final EventOccurrencesRange kind;
    private final KtValueParameterReference<Type, Diagnostic> valueParameterReference;

    public KtCallsEffectDeclaration(KtValueParameterReference<Type, Diagnostic> ktValueParameterReference, EventOccurrencesRange eventOccurrencesRange) {
        ktValueParameterReference.getClass();
        eventOccurrencesRange.getClass();
        this.valueParameterReference = ktValueParameterReference;
        this.kind = eventOccurrencesRange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.KtEffectDeclaration, org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public <R, D> R accept(KtContractDescriptionVisitor<? extends R, ? super D, Type, Diagnostic> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitCallsEffectDeclaration(this, data);
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public boolean getErroneous() {
        return this.valueParameterReference.getErroneous();
    }

    public final EventOccurrencesRange getKind() {
        return this.kind;
    }

    public final KtValueParameterReference<Type, Diagnostic> getValueParameterReference() {
        return this.valueParameterReference;
    }
}
