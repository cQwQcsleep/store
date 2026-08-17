package org.jetbrains.kotlin.contracts.description.expressions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.BooleanExpression;
import org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00110\u00132\u0006\u0010\u0014\u001a\u0002H\u0011H\u0016¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;", "Lorg/jetbrains/kotlin/contracts/description/BooleanExpression;", "arg", "Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/KotlinType;", "isNegated", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;Lorg/jetbrains/kotlin/types/KotlinType;Z)V", "getArg", "()Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "getType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "()Z", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "negated", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IsInstancePredicate implements BooleanExpression {
    private final VariableReference arg;
    private final boolean isNegated;
    private final KotlinType type;

    public IsInstancePredicate(VariableReference variableReference, KotlinType kotlinType, boolean z) {
        variableReference.getClass();
        kotlinType.getClass();
        this.arg = variableReference;
        this.type = kotlinType;
        this.isNegated = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.BooleanExpression, org.jetbrains.kotlin.contracts.description.ContractDescriptionElement
    public <R, D> R accept(ContractDescriptionVisitor<? extends R, ? super D> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitIsInstancePredicate(this, data);
    }

    public final VariableReference getArg() {
        return this.arg;
    }

    public final KotlinType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isNegated, reason: from getter */
    public final boolean getIsNegated() {
        return this.isNegated;
    }

    public final IsInstancePredicate negated() {
        return new IsInstancePredicate(this.arg, this.type, !this.isNegated);
    }
}
