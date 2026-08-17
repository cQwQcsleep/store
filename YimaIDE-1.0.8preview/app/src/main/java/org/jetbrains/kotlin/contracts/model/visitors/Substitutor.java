package org.jetbrains.kotlin.contracts.model.visitors;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ESExpressionVisitor;
import org.jetbrains.kotlin.contracts.model.ESTypeSubstitution;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.structure.CallComputation;
import org.jetbrains.kotlin.contracts.model.structure.ESAnd;
import org.jetbrains.kotlin.contracts.model.structure.ESBooleanType;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESEqual;
import org.jetbrains.kotlin.contracts.model.structure.ESIs;
import org.jetbrains.kotlin.contracts.model.structure.ESNot;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESReceiver;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B+\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\u0010\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/visitors/Substitutor;", "Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "substitutions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "typeSubstitution", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "reducer", "Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;)V", "visitIs", "isOperator", "Lorg/jetbrains/kotlin/contracts/model/structure/ESIs;", "visitNot", "not", "Lorg/jetbrains/kotlin/contracts/model/structure/ESNot;", "visitEqual", "equal", "Lorg/jetbrains/kotlin/contracts/model/structure/ESEqual;", "visitAnd", "and", "Lorg/jetbrains/kotlin/contracts/model/structure/ESAnd;", "visitOr", "or", "Lorg/jetbrains/kotlin/contracts/model/structure/ESOr;", "visitVariable", "esVariable", "visitConstant", "esConstant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "visitReceiver", "Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiver;", "esReceiver", "visitLambda", "lambda", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Substitutor implements ESExpressionVisitor<Computation> {
    private final Reducer reducer;
    private final Map<ESVariable, Computation> substitutions;
    private final ESTypeSubstitution typeSubstitution;

    /* JADX WARN: Multi-variable type inference failed */
    public Substitutor(Map<ESVariable, ? extends Computation> map, ESTypeSubstitution eSTypeSubstitution, Reducer reducer) {
        map.getClass();
        eSTypeSubstitution.getClass();
        reducer.getClass();
        this.substitutions = map;
        this.typeSubstitution = eSTypeSubstitution;
        this.reducer = reducer;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitAnd(ESAnd and) {
        Computation computation;
        and.getClass();
        Computation computation2 = (Computation) and.getLeft().accept(this);
        if (computation2 == null || (computation = (Computation) and.getRight().accept(this)) == null) {
            return null;
        }
        return new CallComputation(ESBooleanType.INSTANCE, and.getFunctor().invokeWithArguments(computation2, computation));
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitEqual(ESEqual equal) {
        Computation computation;
        equal.getClass();
        Computation computation2 = (Computation) equal.getLeft().accept(this);
        if (computation2 == null || (computation = (Computation) equal.getRight().accept(this)) == null) {
            return null;
        }
        return new CallComputation(ESBooleanType.INSTANCE, equal.getFunctor().invokeWithArguments(CollectionsKt.listOf(new Computation[]{computation2, computation}), this.typeSubstitution, this.reducer));
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitIs(ESIs isOperator) {
        isOperator.getClass();
        Computation computation = (Computation) isOperator.getLeft().accept(this);
        if (computation == null) {
            return null;
        }
        return new CallComputation(ESBooleanType.INSTANCE, isOperator.getFunctor().invokeWithArguments(computation, this.typeSubstitution));
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitNot(ESNot not) {
        not.getClass();
        Computation computation = (Computation) not.getArg().accept(this);
        if (computation == null) {
            return null;
        }
        return new CallComputation(ESBooleanType.INSTANCE, not.getFunctor().invokeWithArguments(computation));
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitOr(ESOr or) {
        Computation computation;
        or.getClass();
        Computation computation2 = (Computation) or.getLeft().accept(this);
        if (computation2 == null || (computation = (Computation) or.getRight().accept(this)) == null) {
            return null;
        }
        return new CallComputation(ESBooleanType.INSTANCE, or.getFunctor().invokeWithArguments(computation2, computation));
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitVariable(ESVariable esVariable) {
        esVariable.getClass();
        Computation computation = this.substitutions.get(esVariable);
        return computation == null ? esVariable : computation;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitConstant(ESConstant esConstant) {
        esConstant.getClass();
        return esConstant;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitLambda(ESValue lambda) {
        lambda.getClass();
        return lambda;
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESExpressionVisitor
    public Computation visitReceiver(ESReceiver esReceiver) {
        esReceiver.getClass();
        return esReceiver;
    }
}
