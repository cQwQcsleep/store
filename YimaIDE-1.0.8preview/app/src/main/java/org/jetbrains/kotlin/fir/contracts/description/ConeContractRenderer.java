package org.jetbrains.kotlin.fir.contracts.description;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBinaryLogicExpression;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalReturnsDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtIsInstancePredicate;
import org.jetbrains.kotlin.contracts.description.KtIsNullPredicate;
import org.jetbrains.kotlin.contracts.description.KtLogicalNot;
import org.jetbrains.kotlin.contracts.description.KtReturnsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtReturnsResultOfDeclaration;
import org.jetbrains.kotlin.contracts.description.KtValueParameterReference;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirErrorContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractRenderer;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.renderer.FirPrinter;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.renderer.FirRendererComponents;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dJ\u0015\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b J\u0015\u0010\u0017\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0000¢\u0006\u0002\b J\u0015\u0010\u0017\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b J&\u0010%\u001a\u00020\u00022\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050'2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010)\u001a\u00020\u00022\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050*2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010+\u001a\u00020\u00022\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050-2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005002\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u00101\u001a\u00020\u00022\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005032\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u00104\u001a\u00020\u00022\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005062\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u00107\u001a\u00020\u00022\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005092\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010:\u001a\u00020\u00022\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050<2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010=\u001a\u00020\u00022\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050?2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010@\u001a\u00020\u00022\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050B2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010C\u001a\u00020\u00022\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050E2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010F\u001a\u00020\u00022\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050H2\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J>\u0010I\u001a\u00020\u00022\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050K2\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050K2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00020NH\u0002J\u0018\u0010O\u001a\u00020P*\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050KH\u0002J0\u0010Q\u001a\u00020P2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050K2\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050KH\u0002R\u001a\u0010\b\u001a\u00020\tX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00060\u0013R\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006R"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractRenderer;", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "render", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "effectDeclaration", "Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;", "legacyRawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "render$org_jetbrains_kotlin_tree", "rawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;", "resolvedContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;", "visitConditionalEffectDeclaration", "conditionalEffect", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalEffectDeclaration;", "data", "visitConditionalReturnsDeclaration", "Lorg/jetbrains/kotlin/contracts/description/KtConditionalReturnsDeclaration;", "visitHoldsInEffectDeclaration", "holdsInEffect", "Lorg/jetbrains/kotlin/contracts/description/KtHoldsInEffectDeclaration;", "visitReturnsResultOfEffectDeclaration", "returnsResultOfEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsResultOfDeclaration;", "visitReturnsEffectDeclaration", "returnsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtReturnsEffectDeclaration;", "visitCallsEffectDeclaration", "callsEffect", "Lorg/jetbrains/kotlin/contracts/description/KtCallsEffectDeclaration;", "visitLogicalBinaryOperationContractExpression", "binaryLogicExpression", "Lorg/jetbrains/kotlin/contracts/description/KtBinaryLogicExpression;", "visitLogicalNot", "logicalNot", "Lorg/jetbrains/kotlin/contracts/description/KtLogicalNot;", "visitIsInstancePredicate", "isInstancePredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;", "visitIsNullPredicate", "isNullPredicate", "Lorg/jetbrains/kotlin/contracts/description/KtIsNullPredicate;", "visitConstantDescriptor", "constantReference", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "visitValueParameterReference", "valueParameterReference", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "inBracketsIfNecessary", "parent", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "child", "block", "Lkotlin/Function0;", "isAtom", Argument.Delimiters.none, "needsBrackets", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeContractRenderer extends KtContractDescriptionVisitor {
    public FirRendererComponents components;

    public static Unit a(ConeContractRenderer coneContractRenderer) {
        coneContractRenderer.getPrinter().print("!");
        return Unit.INSTANCE;
    }

    public static Unit b(FirLegacyRawContractDescription firLegacyRawContractDescription, ConeContractRenderer coneContractRenderer) {
        firLegacyRawContractDescription.getContractCall().accept(coneContractRenderer.getVisitor());
        coneContractRenderer.getPrinter().newLine();
        return Unit.INSTANCE;
    }

    public static Unit c(ConeContractRenderer coneContractRenderer, FirRawContractDescription firRawContractDescription) {
        coneContractRenderer.getPrinter().renderSeparatedWithNewlines$org_jetbrains_kotlin_tree(firRawContractDescription.getRawEffects(), coneContractRenderer.getVisitor());
        coneContractRenderer.getPrinter().newLine();
        return Unit.INSTANCE;
    }

    public static Unit d(KtBinaryLogicExpression ktBinaryLogicExpression, ConeContractRenderer coneContractRenderer, Void r2) {
        ktBinaryLogicExpression.getRight().accept(coneContractRenderer, r2);
        return Unit.INSTANCE;
    }

    public static Unit e(KtBinaryLogicExpression ktBinaryLogicExpression, ConeContractRenderer coneContractRenderer, Void r2) {
        ktBinaryLogicExpression.getLeft().accept(coneContractRenderer, r2);
        return Unit.INSTANCE;
    }

    private final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    private final FirRenderer.Visitor getVisitor() {
        return getComponents$org_jetbrains_kotlin_tree().getVisitor();
    }

    private final void inBracketsIfNecessary(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> parent, KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> child, Function0<Unit> block) {
        if (!needsBrackets(parent, child)) {
            block.invoke();
            return;
        }
        getPrinter().print("(");
        block.invoke();
        getPrinter().print(")");
    }

    private final boolean isAtom(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement) {
        return (ktContractDescriptionElement instanceof KtValueParameterReference) || (ktContractDescriptionElement instanceof KtConstantReference) || (ktContractDescriptionElement instanceof KtIsNullPredicate) || (ktContractDescriptionElement instanceof KtIsInstancePredicate);
    }

    private final boolean needsBrackets(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> parent, KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> child) {
        if (isAtom(child)) {
            return false;
        }
        return (parent instanceof KtLogicalNot) || parent.getClass() != child.getClass();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRendererComponents getComponents$org_jetbrains_kotlin_tree() throws UninitializedPropertyAccessException {
        FirRendererComponents firRendererComponents = this.components;
        if (firRendererComponents != null) {
            return firRendererComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    public final void render(FirContractDescription contractDescription) {
        String str;
        contractDescription.getClass();
        getPrinter().pushIndent$org_jetbrains_kotlin_tree();
        getPrinter().newLine();
        boolean z = contractDescription instanceof FirResolvedContractDescription;
        if (z) {
            str = "R|";
        } else if (contractDescription instanceof FirErrorContractDescription) {
            str = "E|";
        } else if (contractDescription instanceof FirLazyContractDescription) {
            str = "L|";
        } else {
            if (!(contractDescription instanceof FirLegacyRawContractDescription) && !(contractDescription instanceof FirRawContractDescription)) {
                bu8.a();
                return;
            }
            str = Argument.Delimiters.none;
        }
        getPrinter().print("[" + str + "Contract description]");
        if (!(contractDescription instanceof FirLazyContractDescription)) {
            if (contractDescription instanceof FirLegacyRawContractDescription) {
                render$org_jetbrains_kotlin_tree((FirLegacyRawContractDescription) contractDescription);
            } else if (contractDescription instanceof FirRawContractDescription) {
                render$org_jetbrains_kotlin_tree((FirRawContractDescription) contractDescription);
            } else if (z) {
                getPrinter().println(new Object[0]);
                render$org_jetbrains_kotlin_tree((FirResolvedContractDescription) contractDescription);
            } else if (!(contractDescription instanceof FirErrorContractDescription)) {
                bu8.a();
                return;
            }
        }
        getPrinter().popIndent$org_jetbrains_kotlin_tree();
    }

    public final void render$org_jetbrains_kotlin_tree(FirResolvedContractDescription resolvedContractDescription) {
        resolvedContractDescription.getClass();
        getPrinter().println(" <");
        getPrinter().pushIndent$org_jetbrains_kotlin_tree();
        Iterator<T> it = resolvedContractDescription.getEffects().iterator();
        while (it.hasNext()) {
            ((FirEffectDeclaration) it.next()).getEffect().accept(this, null);
            getPrinter().println(new Object[0]);
        }
        getPrinter().popIndent$org_jetbrains_kotlin_tree();
        getPrinter().println(">");
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }

    public void visitCallsEffectDeclaration(KtCallsEffectDeclaration<ConeKotlinType, ConeDiagnostic> callsEffect, Void data) {
        callsEffect.getClass();
        getPrinter().print("CallsInPlace(");
        callsEffect.getValueParameterReference().accept(this, data);
        getPrinter().print(", " + callsEffect.getKind() + ')');
    }

    public void visitConditionalEffectDeclaration(KtConditionalEffectDeclaration<ConeKotlinType, ConeDiagnostic> conditionalEffect, Void data) {
        conditionalEffect.getClass();
        conditionalEffect.getEffect().accept(this, data);
        getPrinter().print(" -> ");
        conditionalEffect.getCondition().accept(this, data);
    }

    public void visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration<ConeKotlinType, ConeDiagnostic> conditionalEffect, Void data) {
        conditionalEffect.getClass();
        conditionalEffect.getArgumentsCondition().accept(this, data);
        getPrinter().print(" -> ");
        conditionalEffect.getReturnsEffect().accept(this, data);
    }

    public void visitConstantDescriptor(KtConstantReference<ConeKotlinType, ConeDiagnostic> constantReference, Void data) {
        constantReference.getClass();
        getPrinter().print(constantReference.getName());
    }

    public void visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration<ConeKotlinType, ConeDiagnostic> holdsInEffect, Void data) {
        holdsInEffect.getClass();
        holdsInEffect.getArgumentsCondition().accept(this, data);
        getPrinter().print(" HoldsIn(");
        holdsInEffect.getValueParameterReference().accept(this, data);
        getPrinter().print(")");
    }

    public void visitIsInstancePredicate(KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic> isInstancePredicate, Void data) {
        isInstancePredicate.getClass();
        isInstancePredicate.getArg().accept(this, data);
        FirPrinter printer = getPrinter();
        StringBuilder sb = new StringBuilder(Argument.Delimiters.space);
        sb.append(isInstancePredicate.getIsNegated() ? "!" : Argument.Delimiters.none);
        sb.append("is ");
        sb.append(ConeTypeUtilsKt.renderForDebugging(isInstancePredicate.getType()));
        printer.print(sb.toString());
    }

    public void visitIsNullPredicate(KtIsNullPredicate<ConeKotlinType, ConeDiagnostic> isNullPredicate, Void data) {
        isNullPredicate.getClass();
        isNullPredicate.getArg().accept(this, data);
        FirPrinter printer = getPrinter();
        StringBuilder sb = new StringBuilder(Argument.Delimiters.space);
        sb.append(isNullPredicate.getIsNegated() ? "!=" : "==");
        sb.append(" null");
        printer.print(sb.toString());
    }

    public void visitLogicalBinaryOperationContractExpression(final KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic> binaryLogicExpression, final Void data) {
        binaryLogicExpression.getClass();
        inBracketsIfNecessary(binaryLogicExpression, binaryLogicExpression.getLeft(), new Function0() { // from class: pp2
            public final Object invoke() {
                return ConeContractRenderer.e(binaryLogicExpression, this, data);
            }
        });
        getPrinter().print(Argument.Delimiters.space + binaryLogicExpression.getKind().getToken() + ' ');
        inBracketsIfNecessary(binaryLogicExpression, binaryLogicExpression.getRight(), new Function0() { // from class: qp2
            public final Object invoke() {
                return ConeContractRenderer.d(binaryLogicExpression, this, data);
            }
        });
    }

    public void visitLogicalNot(KtLogicalNot<ConeKotlinType, ConeDiagnostic> logicalNot, Void data) {
        logicalNot.getClass();
        inBracketsIfNecessary(logicalNot, logicalNot.getArg(), new Function0() { // from class: rp2
            public final Object invoke() {
                return ConeContractRenderer.a(this.b);
            }
        });
        logicalNot.getArg().accept(this, data);
    }

    public void visitReturnsEffectDeclaration(KtReturnsEffectDeclaration<ConeKotlinType, ConeDiagnostic> returnsEffect, Void data) {
        returnsEffect.getClass();
        getPrinter().print("Returns(");
        returnsEffect.getValue().accept(this, data);
        getPrinter().print(")");
    }

    public void visitReturnsResultOfEffectDeclaration(KtReturnsResultOfDeclaration<ConeKotlinType, ConeDiagnostic> returnsResultOfEffect, Void data) {
        returnsResultOfEffect.getClass();
        getPrinter().print("ReturnsResultOf(");
        returnsResultOfEffect.getValueParameterReference().accept(this, data);
        getPrinter().print(")");
    }

    public void visitValueParameterReference(KtValueParameterReference<ConeKotlinType, ConeDiagnostic> valueParameterReference, Void data) {
        valueParameterReference.getClass();
        getPrinter().print(valueParameterReference.getName());
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitConstantDescriptor(KtConstantReference ktConstantReference, Object obj) {
        visitConstantDescriptor((KtConstantReference<ConeKotlinType, ConeDiagnostic>) ktConstantReference, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitValueParameterReference(KtValueParameterReference ktValueParameterReference, Object obj) {
        visitValueParameterReference((KtValueParameterReference<ConeKotlinType, ConeDiagnostic>) ktValueParameterReference, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitLogicalNot(KtLogicalNot ktLogicalNot, Object obj) {
        visitLogicalNot((KtLogicalNot<ConeKotlinType, ConeDiagnostic>) ktLogicalNot, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitConditionalEffectDeclaration(KtConditionalEffectDeclaration ktConditionalEffectDeclaration, Object obj) {
        visitConditionalEffectDeclaration((KtConditionalEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktConditionalEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitConditionalReturnsDeclaration(KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration, Object obj) {
        visitConditionalReturnsDeclaration((KtConditionalReturnsDeclaration<ConeKotlinType, ConeDiagnostic>) ktConditionalReturnsDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitReturnsEffectDeclaration(KtReturnsEffectDeclaration ktReturnsEffectDeclaration, Object obj) {
        visitReturnsEffectDeclaration((KtReturnsEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktReturnsEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitReturnsResultOfEffectDeclaration(KtReturnsResultOfDeclaration ktReturnsResultOfDeclaration, Object obj) {
        visitReturnsResultOfEffectDeclaration((KtReturnsResultOfDeclaration<ConeKotlinType, ConeDiagnostic>) ktReturnsResultOfDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitHoldsInEffectDeclaration(KtHoldsInEffectDeclaration ktHoldsInEffectDeclaration, Object obj) {
        visitHoldsInEffectDeclaration((KtHoldsInEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktHoldsInEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitIsNullPredicate(KtIsNullPredicate ktIsNullPredicate, Object obj) {
        visitIsNullPredicate((KtIsNullPredicate<ConeKotlinType, ConeDiagnostic>) ktIsNullPredicate, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitCallsEffectDeclaration(KtCallsEffectDeclaration ktCallsEffectDeclaration, Object obj) {
        visitCallsEffectDeclaration((KtCallsEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktCallsEffectDeclaration, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitIsInstancePredicate(KtIsInstancePredicate ktIsInstancePredicate, Object obj) {
        visitIsInstancePredicate((KtIsInstancePredicate<ConeKotlinType, ConeDiagnostic>) ktIsInstancePredicate, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionVisitor
    public /* bridge */ /* synthetic */ Object visitLogicalBinaryOperationContractExpression(KtBinaryLogicExpression ktBinaryLogicExpression, Object obj) {
        visitLogicalBinaryOperationContractExpression((KtBinaryLogicExpression<ConeKotlinType, ConeDiagnostic>) ktBinaryLogicExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    public final void render$org_jetbrains_kotlin_tree(final FirRawContractDescription rawContractDescription) {
        rawContractDescription.getClass();
        getPrinter().renderInBraces("<", ">", new Function0() { // from class: op2
            public final Object invoke() {
                return ConeContractRenderer.c(this.b, rawContractDescription);
            }
        });
    }

    public final void render$org_jetbrains_kotlin_tree(final FirLegacyRawContractDescription legacyRawContractDescription) {
        legacyRawContractDescription.getClass();
        getPrinter().renderInBraces("<", ">", new Function0() { // from class: sp2
            public final Object invoke() {
                return ConeContractRenderer.b(legacyRawContractDescription, this);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void render(FirDeclaration declaration) {
        FirContractDescription contractDescription;
        declaration.getClass();
        FirContractDescriptionOwner firContractDescriptionOwner = declaration instanceof FirContractDescriptionOwner ? (FirContractDescriptionOwner) declaration : null;
        if (firContractDescriptionOwner == null || (contractDescription = firContractDescriptionOwner.getContractDescription()) == null) {
            return;
        }
        render(contractDescription);
    }

    public final void render(FirEffectDeclaration effectDeclaration) {
        effectDeclaration.getClass();
        getPrinter().newLine();
        getPrinter().println("[Effect declaration] <");
        effectDeclaration.getEffect().accept(this, null);
        getPrinter().println(new Object[0]);
        getPrinter().println(">");
    }
}
