package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J5\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00102\u0006\u0010\u0011\u001a\u0002H\u000eH\u0016¢\u0006\u0002\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/CallsEffectDeclaration;", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "variableReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "kind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;)V", "getVariableReference", "()Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "getKind", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallsEffectDeclaration implements EffectDeclaration {
    private final EventOccurrencesRange kind;
    private final VariableReference variableReference;

    public CallsEffectDeclaration(VariableReference variableReference, EventOccurrencesRange eventOccurrencesRange) {
        variableReference.getClass();
        eventOccurrencesRange.getClass();
        this.variableReference = variableReference;
        this.kind = eventOccurrencesRange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.EffectDeclaration, org.jetbrains.kotlin.contracts.description.ContractDescriptionElement
    public <R, D> R accept(ContractDescriptionVisitor<? extends R, ? super D> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitCallsEffectDeclaration(this, data);
    }

    public final EventOccurrencesRange getKind() {
        return this.kind;
    }

    public final VariableReference getVariableReference() {
        return this.variableReference;
    }
}
