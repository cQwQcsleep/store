package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", Argument.Delimiters.none, "condition", "Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "effect", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;)V", "getCondition", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "getEffect", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Implication {
    private final OperationStatement condition;
    private final Statement effect;

    public Implication(OperationStatement operationStatement, Statement statement) {
        operationStatement.getClass();
        statement.getClass();
        this.condition = operationStatement;
        this.effect = statement;
    }

    public final OperationStatement getCondition() {
        return this.condition;
    }

    public final Statement getEffect() {
        return this.effect;
    }

    public String toString() {
        return this.condition + " -> " + this.effect;
    }
}
