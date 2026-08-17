package org.jetbrains.kotlin.contracts.parsing;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.expressions.BooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.expressions.ConstantReference;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtVisitor;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.constants.CompileTimeConstant;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/PsiConstantParser;", "Lorg/jetbrains/kotlin/psi/KtVisitor;", "Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", Argument.Delimiters.none, "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "<init>", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;)V", "visitKtElement", "element", "Lorg/jetbrains/kotlin/psi/KtElement;", "data", "(Lorg/jetbrains/kotlin/psi/KtElement;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "visitConstantExpression", "expression", "Lorg/jetbrains/kotlin/psi/KtConstantExpression;", "(Lorg/jetbrains/kotlin/psi/KtConstantExpression;Lkotlin/Unit;)Lorg/jetbrains/kotlin/contracts/description/expressions/ConstantReference;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiConstantParser extends KtVisitor<ConstantReference, Unit> {
    private final ContractCallContext callContext;

    public PsiConstantParser(ContractCallContext contractCallContext) {
        contractCallContext.getClass();
        this.callContext = contractCallContext;
    }

    public ConstantReference visitConstantExpression(KtConstantExpression expression, Unit data) {
        CompileTimeConstant compileTimeConstant;
        expression.getClass();
        KotlinType type = this.callContext.getBindingContext().getType(expression);
        if (type == null || (compileTimeConstant = (CompileTimeConstant) this.callContext.getBindingContext().get(BindingContext.COMPILE_TIME_VALUE, expression)) == null) {
            return null;
        }
        Object value = compileTimeConstant.getValue(type);
        if (Intrinsics.areEqual(value, Boolean.TRUE)) {
            return BooleanConstantReference.INSTANCE.getTRUE();
        }
        if (Intrinsics.areEqual(value, Boolean.FALSE)) {
            return BooleanConstantReference.INSTANCE.getFALSE();
        }
        if (value == null) {
            return ConstantReference.INSTANCE.getNULL();
        }
        return null;
    }

    public ConstantReference visitKtElement(KtElement element, Unit data) {
        element.getClass();
        return null;
    }
}
