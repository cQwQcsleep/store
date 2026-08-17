package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.ESOperator;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.functors.EqualsFunctor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0013H\u0016¢\u0006\u0002\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESEqual;", "Lorg/jetbrains/kotlin/contracts/model/ESOperator;", "left", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "right", "isNegated", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESValue;Lorg/jetbrains/kotlin/contracts/model/ESValue;Z)V", "getLeft", "()Lorg/jetbrains/kotlin/contracts/model/ESValue;", "getRight", "functor", "Lorg/jetbrains/kotlin/contracts/model/functors/EqualsFunctor;", "getFunctor", "()Lorg/jetbrains/kotlin/contracts/model/functors/EqualsFunctor;", "accept", "T", "visitor", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "(Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESEqual implements ESOperator {
    private final EqualsFunctor functor;
    private final ESValue left;
    private final ESValue right;

    public ESEqual(ESValue eSValue, ESValue eSValue2, boolean z) {
        eSValue.getClass();
        eSValue2.getClass();
        this.left = eSValue;
        this.right = eSValue2;
        this.functor = new EqualsFunctor(z);
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpression
    public <T> T accept(ESExpressionVisitor<? extends T> visitor) {
        visitor.getClass();
        return visitor.visitEqual(this);
    }

    public final ESValue getLeft() {
        return this.left;
    }

    public final ESValue getRight() {
        return this.right;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESOperator
    public EqualsFunctor getFunctor() {
        return this.functor;
    }
}
