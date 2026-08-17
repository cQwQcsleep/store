package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.ContractDescriptionRenderer;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.IsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.expressions.IsNullPredicate;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalAnd;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalNot;
import org.jetbrains.kotlin.contracts.description.expressions.LogicalOr;
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010$J\u001d\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020/2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u00100J\u001d\u00101\u001a\u00020\u00022\u0006\u00102\u001a\u0002032\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u00104J\f\u00105\u001a\u000206*\u000207H\u0002J\u0018\u00108\u001a\u0002062\u0006\u00109\u001a\u0002072\u0006\u0010:\u001a\u000207H\u0002J&\u0010;\u001a\u00020\u00022\u0006\u00109\u001a\u0002072\u0006\u0010:\u001a\u0002072\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020=H\u0002R\u0012\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionRenderer;", "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionVisitor;", Argument.Delimiters.none, "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "<init>", "(Ljava/lang/StringBuilder;)V", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/ConditionalEffectDeclaration;", "data", "(Lorg/jetbrains/kotlin/contracts/description/ConditionalEffectDeclaration;Lkotlin/Unit;)V", "visitReturnsEffectDeclaration", "returnsEffect", "Lorg/jetbrains/kotlin/contracts/description/ReturnsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/ReturnsEffectDeclaration;Lkotlin/Unit;)V", "visitCallsEffectDeclaration", "callsEffect", "Lorg/jetbrains/kotlin/contracts/description/CallsEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/CallsEffectDeclaration;Lkotlin/Unit;)V", "visitReturnsResultOfEffectDeclaration", "returnsResultOfEffect", "Lorg/jetbrains/kotlin/contracts/description/ReturnsResultOfEffectDeclaration;", "(Lorg/jetbrains/kotlin/contracts/description/ReturnsResultOfEffectDeclaration;Lkotlin/Unit;)V", "visitLogicalOr", "logicalOr", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalOr;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalOr;Lkotlin/Unit;)V", "visitLogicalAnd", "logicalAnd", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalAnd;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalAnd;Lkotlin/Unit;)V", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalNot;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/LogicalNot;Lkotlin/Unit;)V", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/IsInstancePredicate;Lkotlin/Unit;)V", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/expressions/IsNullPredicate;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/IsNullPredicate;Lkotlin/Unit;)V", "visitConstantDescriptor", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;Lkotlin/Unit;)V", "visitVariableReference", "variableReference", "Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;", "(Lorg/jetbrains/kotlin/contracts/description/expressions/VariableReference;Lkotlin/Unit;)V", "isAtom", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/description/ContractDescriptionElement;", "needsBrackets", "parent", "child", "inBracketsIfNecessary", "block", "Lkotlin/Function0;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractDescriptionRenderer implements ContractDescriptionVisitor<Unit, Unit> {
    private final StringBuilder builder;

    public ContractDescriptionRenderer(StringBuilder sb) {
        sb.getClass();
        this.builder = sb;
    }

    public static Unit a(LogicalOr logicalOr, ContractDescriptionRenderer contractDescriptionRenderer, Unit unit) {
        logicalOr.getRight().accept(contractDescriptionRenderer, unit);
        return Unit.INSTANCE;
    }

    public static Unit b(LogicalOr logicalOr, ContractDescriptionRenderer contractDescriptionRenderer, Unit unit) {
        logicalOr.getLeft().accept(contractDescriptionRenderer, unit);
        return Unit.INSTANCE;
    }

    public static Unit c(ContractDescriptionRenderer contractDescriptionRenderer) {
        contractDescriptionRenderer.builder.append("!");
        return Unit.INSTANCE;
    }

    public static Unit d(LogicalAnd logicalAnd, ContractDescriptionRenderer contractDescriptionRenderer, Unit unit) {
        logicalAnd.getRight().accept(contractDescriptionRenderer, unit);
        return Unit.INSTANCE;
    }

    public static Unit e(LogicalAnd logicalAnd, ContractDescriptionRenderer contractDescriptionRenderer, Unit unit) {
        logicalAnd.getLeft().accept(contractDescriptionRenderer, unit);
        return Unit.INSTANCE;
    }

    private final void inBracketsIfNecessary(ContractDescriptionElement parent, ContractDescriptionElement child, Function0<Unit> block) {
        if (!needsBrackets(parent, child)) {
            block.invoke();
            return;
        }
        this.builder.append("(");
        block.invoke();
        this.builder.append(")");
    }

    private final boolean isAtom(ContractDescriptionElement contractDescriptionElement) {
        return (contractDescriptionElement instanceof VariableReference) || (contractDescriptionElement instanceof ConstantReference) || (contractDescriptionElement instanceof IsNullPredicate) || (contractDescriptionElement instanceof IsInstancePredicate);
    }

    private final boolean needsBrackets(ContractDescriptionElement parent, ContractDescriptionElement child) {
        if (isAtom(child)) {
            return false;
        }
        return (parent instanceof LogicalNot) || parent.getClass() != child.getClass();
    }

    /* JADX INFO: renamed from: visitCallsEffectDeclaration, reason: avoid collision after fix types in other method */
    public void visitCallsEffectDeclaration2(CallsEffectDeclaration callsEffect, Unit data) {
        callsEffect.getClass();
        data.getClass();
        this.builder.append("CallsInPlace(");
        callsEffect.getVariableReference().accept(this, data);
        this.builder.append(", " + callsEffect.getKind() + ')');
    }

    /* JADX INFO: renamed from: visitConditionalEffectDeclaration, reason: avoid collision after fix types in other method */
    public void visitConditionalEffectDeclaration2(ConditionalEffectDeclaration conditionalEffect, Unit data) {
        conditionalEffect.getClass();
        data.getClass();
        conditionalEffect.getEffect().accept(this, data);
        this.builder.append(" -> ");
        conditionalEffect.getCondition().accept(this, data);
    }

    /* JADX INFO: renamed from: visitConstantDescriptor, reason: avoid collision after fix types in other method */
    public void visitConstantDescriptor2(ConstantReference constantReference, Unit data) {
        constantReference.getClass();
        data.getClass();
        this.builder.append(constantReference.getName());
    }

    /* JADX INFO: renamed from: visitIsInstancePredicate, reason: avoid collision after fix types in other method */
    public void visitIsInstancePredicate2(IsInstancePredicate isInstancePredicate, Unit data) {
        isInstancePredicate.getClass();
        data.getClass();
        isInstancePredicate.getArg().accept(this, data);
        StringBuilder sb = this.builder;
        StringBuilder sb2 = new StringBuilder(Argument.Delimiters.space);
        sb2.append(isInstancePredicate.getIsNegated() ? "!" : Argument.Delimiters.none);
        sb2.append("is ");
        sb2.append(isInstancePredicate.getType());
        sb.append(sb2.toString());
    }

    /* JADX INFO: renamed from: visitIsNullPredicate, reason: avoid collision after fix types in other method */
    public void visitIsNullPredicate2(IsNullPredicate isNullPredicate, Unit data) {
        isNullPredicate.getClass();
        data.getClass();
        isNullPredicate.getArg().accept(this, data);
        StringBuilder sb = this.builder;
        StringBuilder sb2 = new StringBuilder(Argument.Delimiters.space);
        sb2.append(isNullPredicate.getIsNegated() ? "!=" : "==");
        sb2.append(" null");
        sb.append(sb2.toString());
    }

    /* JADX INFO: renamed from: visitLogicalAnd, reason: avoid collision after fix types in other method */
    public void visitLogicalAnd2(final LogicalAnd logicalAnd, final Unit data) {
        logicalAnd.getClass();
        data.getClass();
        inBracketsIfNecessary(logicalAnd, logicalAnd.getLeft(), new Function0() { // from class: kw2
            public final Object invoke() {
                return ContractDescriptionRenderer.e(logicalAnd, this, data);
            }
        });
        this.builder.append(" && ");
        inBracketsIfNecessary(logicalAnd, logicalAnd.getRight(), new Function0() { // from class: lw2
            public final Object invoke() {
                return ContractDescriptionRenderer.d(logicalAnd, this, data);
            }
        });
    }

    /* JADX INFO: renamed from: visitLogicalNot, reason: avoid collision after fix types in other method */
    public void visitLogicalNot2(LogicalNot logicalNot, Unit data) {
        logicalNot.getClass();
        data.getClass();
        inBracketsIfNecessary(logicalNot, logicalNot.getArg(), new Function0() { // from class: ow2
            public final Object invoke() {
                return ContractDescriptionRenderer.c(this.b);
            }
        });
        logicalNot.getArg().accept(this, data);
    }

    /* JADX INFO: renamed from: visitLogicalOr, reason: avoid collision after fix types in other method */
    public void visitLogicalOr2(final LogicalOr logicalOr, final Unit data) {
        logicalOr.getClass();
        data.getClass();
        inBracketsIfNecessary(logicalOr, logicalOr.getLeft(), new Function0() { // from class: mw2
            public final Object invoke() {
                return ContractDescriptionRenderer.b(logicalOr, this, data);
            }
        });
        this.builder.append(" || ");
        inBracketsIfNecessary(logicalOr, logicalOr.getRight(), new Function0() { // from class: nw2
            public final Object invoke() {
                return ContractDescriptionRenderer.a(logicalOr, this, data);
            }
        });
    }

    /* JADX INFO: renamed from: visitReturnsEffectDeclaration, reason: avoid collision after fix types in other method */
    public void visitReturnsEffectDeclaration2(ReturnsEffectDeclaration returnsEffect, Unit data) {
        returnsEffect.getClass();
        data.getClass();
        this.builder.append("Returns(");
        returnsEffect.getValue().accept(this, data);
        this.builder.append(")");
    }

    /* JADX INFO: renamed from: visitReturnsResultOfEffectDeclaration, reason: avoid collision after fix types in other method */
    public void visitReturnsResultOfEffectDeclaration2(ReturnsResultOfEffectDeclaration returnsResultOfEffect, Unit data) {
        returnsResultOfEffect.getClass();
        data.getClass();
        this.builder.append("ReturnsResultOf(");
        returnsResultOfEffect.getVariableReference().accept(this, data);
        this.builder.append(")");
    }

    /* JADX INFO: renamed from: visitVariableReference, reason: avoid collision after fix types in other method */
    public void visitVariableReference2(VariableReference variableReference, Unit data) {
        variableReference.getClass();
        data.getClass();
        this.builder.append(variableReference.getDescriptor().getName());
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitConstantDescriptor(ConstantReference constantReference, Unit unit) {
        visitConstantDescriptor2(constantReference, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitVariableReference(VariableReference variableReference, Unit unit) {
        visitVariableReference2(variableReference, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitLogicalNot(LogicalNot logicalNot, Unit unit) {
        visitLogicalNot2(logicalNot, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitConditionalEffectDeclaration(ConditionalEffectDeclaration conditionalEffectDeclaration, Unit unit) {
        visitConditionalEffectDeclaration2(conditionalEffectDeclaration, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitReturnsEffectDeclaration(ReturnsEffectDeclaration returnsEffectDeclaration, Unit unit) {
        visitReturnsEffectDeclaration2(returnsEffectDeclaration, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitReturnsResultOfEffectDeclaration(ReturnsResultOfEffectDeclaration returnsResultOfEffectDeclaration, Unit unit) {
        visitReturnsResultOfEffectDeclaration2(returnsResultOfEffectDeclaration, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitLogicalAnd(LogicalAnd logicalAnd, Unit unit) {
        visitLogicalAnd2(logicalAnd, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitLogicalOr(LogicalOr logicalOr, Unit unit) {
        visitLogicalOr2(logicalOr, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitCallsEffectDeclaration(CallsEffectDeclaration callsEffectDeclaration, Unit unit) {
        visitCallsEffectDeclaration2(callsEffectDeclaration, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitIsNullPredicate(IsNullPredicate isNullPredicate, Unit unit) {
        visitIsNullPredicate2(isNullPredicate, unit);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.ContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Unit visitIsInstancePredicate(IsInstancePredicate isInstancePredicate, Unit unit) {
        visitIsInstancePredicate2(isInstancePredicate, unit);
        return Unit.INSTANCE;
    }
}
