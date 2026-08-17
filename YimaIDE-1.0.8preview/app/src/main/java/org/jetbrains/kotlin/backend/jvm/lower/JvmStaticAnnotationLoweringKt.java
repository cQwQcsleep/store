package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrBlockImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.resolve.annotations.AnnotationUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\"\u0010\n\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002\u001a\u001c\u0010\u000e\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\tH\u0002¨\u0006\u0011"}, d2 = {"isJvmStaticDeclaration", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "isJvmStaticInCompanion", "isJvmStaticInObject", "coerceToUnit", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrTypeOperatorCallImpl;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "irBuiltIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "makeStatic", "Lorg/jetbrains/kotlin/ir/expressions/IrMemberAccessExpression;", "replaceCallee", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "addEvaluationOfArgIfSideEffects", "arg", "builtIns", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmStaticAnnotationLoweringKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression addEvaluationOfArgIfSideEffects(IrExpression irExpression, IrExpression irExpression2, IrBuiltIns irBuiltIns) {
        if (IrUtilsKt.isTrivial(irExpression2)) {
            return irExpression;
        }
        IrBlockImpl irBlockImplIrBlockImpl$default = BuildersKt.IrBlockImpl$default(irExpression.getStartOffset(), irExpression.getEndOffset(), irExpression.getType(), (IrStatementOrigin) null, 8, (Object) null);
        irBlockImplIrBlockImpl$default.getStatements().add(coerceToUnit(irExpression2, irBuiltIns));
        irBlockImplIrBlockImpl$default.getStatements().add(irExpression);
        return irBlockImplIrBlockImpl$default;
    }

    private static final IrTypeOperatorCallImpl coerceToUnit(IrExpression irExpression, IrBuiltIns irBuiltIns) {
        return BuildersKt.IrTypeOperatorCallImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), irBuiltIns.getUnitType(), IrTypeOperator.IMPLICIT_COERCION_TO_UNIT, irBuiltIns.getUnitType(), irExpression);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isJvmStaticDeclaration(IrDeclaration irDeclaration) {
        IrSimpleFunction getter;
        IrPropertySymbol correspondingPropertySymbol;
        IrProperty owner;
        if (!IrUtilsKt.hasAnnotation((IrAnnotationContainer) irDeclaration, AnnotationUtilKt.getJVM_STATIC_ANNOTATION_FQ_NAME())) {
            IrSimpleFunction irSimpleFunction = irDeclaration instanceof IrSimpleFunction ? (IrSimpleFunction) irDeclaration : null;
            if (irSimpleFunction == null || (correspondingPropertySymbol = irSimpleFunction.getCorrespondingPropertySymbol()) == null || (owner = correspondingPropertySymbol.getOwner()) == null || !IrUtilsKt.hasAnnotation(owner, AnnotationUtilKt.getJVM_STATIC_ANNOTATION_FQ_NAME())) {
                IrProperty irProperty = irDeclaration instanceof IrProperty ? (IrProperty) irDeclaration : null;
                if (irProperty == null || (getter = irProperty.getGetter()) == null || !IrUtilsKt.hasAnnotation(getter, AnnotationUtilKt.getJVM_STATIC_ANNOTATION_FQ_NAME())) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isJvmStaticInCompanion(IrDeclaration irDeclaration) {
        if (!isJvmStaticDeclaration(irDeclaration)) {
            return false;
        }
        IrClass parent = irDeclaration.getParent();
        IrClass irClass = parent instanceof IrClass ? parent : null;
        return irClass != null && irClass.isCompanion();
    }

    public static final boolean isJvmStaticInObject(IrDeclaration irDeclaration) {
        irDeclaration.getClass();
        if (!isJvmStaticDeclaration(irDeclaration)) {
            return false;
        }
        IrClass parent = irDeclaration.getParent();
        IrClass irClass = parent instanceof IrClass ? parent : null;
        return irClass != null && IrUtilsKt.isNonCompanionObject(irClass);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression makeStatic(IrMemberAccessExpression<?> irMemberAccessExpression, IrBuiltIns irBuiltIns, IrSimpleFunction irSimpleFunction) {
        IrExpression irExpressionRemove = irMemberAccessExpression.getArguments().remove(0);
        if (irSimpleFunction != null) {
            ((IrCall) irMemberAccessExpression).setSymbol(irSimpleFunction.getSymbol());
        }
        return irExpressionRemove == null ? irMemberAccessExpression : addEvaluationOfArgIfSideEffects(irMemberAccessExpression, irExpressionRemove, irBuiltIns);
    }
}
