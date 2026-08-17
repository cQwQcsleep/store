package org.jetbrains.kotlin.backend.common;

import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrGetObjectValue;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrReturn;
import org.jetbrains.kotlin.ir.expressions.IrRichFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrStatementContainer;
import org.jetbrains.kotlin.ir.expressions.IrTry;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u0005¨\u0006\n"}, d2 = {"collectTailRecursionCalls", "Lorg/jetbrains/kotlin/backend/common/TailCalls;", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "followFunctionReference", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionReference;", "", "followRichFunctionReference", "Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class TailRecursionCallsCollectorKt {

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"org/jetbrains/kotlin/backend/common/TailRecursionCallsCollectorKt$collectTailRecursionCalls$VisitorState", "", "isTailExpression", "", "inOtherFunction", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ZZ)V", "()Z", "getInOtherFunction", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class VisitorState {
        private final boolean inOtherFunction;
        private final boolean isTailExpression;

        public VisitorState(boolean z, boolean z2) {
            this.isTailExpression = z;
            this.inOtherFunction = z2;
        }

        public final boolean getInOtherFunction() {
            return this.inOtherFunction;
        }

        /* JADX INFO: renamed from: isTailExpression, reason: from getter */
        public final boolean getIsTailExpression() {
            return this.isTailExpression;
        }
    }

    public static final TailCalls collectTailRecursionCalls(final IrFunction irFunction, final Function1<? super IrFunctionReference, Boolean> function1, final Function1<? super IrRichFunctionReference, Boolean> function2) {
        irFunction.getClass();
        function1.getClass();
        function2.getClass();
        IrSimpleFunction irSimpleFunction = irFunction instanceof IrSimpleFunction ? (IrSimpleFunction) irFunction : null;
        if (irSimpleFunction == null || !irSimpleFunction.isTailrec()) {
            return new TailCalls(SetsKt.emptySet(), false);
        }
        final boolean zIsUnit = IrTypePredicatesKt.isUnit(irFunction.getReturnType());
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        IrVisitor<Unit, VisitorState> irVisitor = new IrVisitor<Unit, VisitorState>() { // from class: org.jetbrains.kotlin.backend.common.TailRecursionCallsCollectorKt$collectTailRecursionCalls$visitor$1
            private final boolean isUnitRead(IrExpression irExpression) {
                return (irExpression instanceof IrGetObjectValue) && IrTypePredicatesKt.isClassWithFqName(((IrGetObjectValue) irExpression).getSymbol(), StandardNames.FqNames.unit);
            }

            /* JADX WARN: Code duplicated, block: B:19:0x0061  */
            private final void visitStatementContainer(IrStatementContainer expression, TailRecursionCallsCollectorKt.VisitorState data) {
                boolean isTailExpression;
                List statements = expression.getStatements();
                boolean z = zIsUnit;
                IrSimpleFunction irSimpleFunction2 = irFunction;
                int i = 0;
                for (Object obj : statements) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    IrStatement irStatement = (IrStatement) obj;
                    if (i == CollectionsKt.getLastIndex(expression.getStatements())) {
                        isTailExpression = data.getIsTailExpression();
                    } else if (z) {
                        IrReturn irReturn = (IrStatement) expression.getStatements().get(i2);
                        if (irReturn instanceof IrReturn) {
                            IrReturn irReturn2 = irReturn;
                            if (Intrinsics.areEqual(irReturn2.getReturnTargetSymbol(), irSimpleFunction2.getSymbol()) && isUnitRead(irReturn2.getValue())) {
                                isTailExpression = true;
                            } else {
                                isTailExpression = false;
                            }
                        } else {
                            isTailExpression = false;
                        }
                    } else {
                        isTailExpression = false;
                    }
                    irStatement.accept(this, new TailRecursionCallsCollectorKt.VisitorState(isTailExpression, data.getInOtherFunction()));
                    i = i2;
                }
            }

            public void visitBlockBody(IrBlockBody body, TailRecursionCallsCollectorKt.VisitorState data) {
                body.getClass();
                data.getClass();
                visitStatementContainer(body, data);
            }

            public void visitCall(IrCall expression, TailRecursionCallsCollectorKt.VisitorState data) {
                IrGetValue dispatchReceiver;
                IrType type;
                IrClassSymbol classOrNull;
                IrClass owner;
                ClassKind kind;
                expression.getClass();
                data.getClass();
                expression.acceptChildren(this, new TailRecursionCallsCollectorKt.VisitorState(false, data.getInOtherFunction()));
                if (data.getIsTailExpression() && Intrinsics.areEqual(expression.getSymbol(), irFunction.getSymbol())) {
                    if (irFunction.getOverriddenSymbols().isEmpty() || !IrUtilsKt.usesDefaultArguments(expression)) {
                        IrValueParameter dispatchReceiverParameter = irFunction.getDispatchReceiverParameter();
                        if ((dispatchReceiverParameter == null || (type = dispatchReceiverParameter.getType()) == null || (classOrNull = IrTypesKt.getClassOrNull(type)) == null || (owner = classOrNull.getOwner()) == null || (kind = owner.getKind()) == null || !kind.isSingleton()) && (dispatchReceiver = expression.getDispatchReceiver()) != null) {
                            IrFunction irFunction2 = irFunction;
                            if (!(dispatchReceiver instanceof IrGetValue) || !Intrinsics.areEqual(dispatchReceiver.getSymbol().getOwner(), irFunction2.getDispatchReceiverParameter())) {
                                return;
                            }
                        }
                        if (data.getInOtherFunction()) {
                            booleanRef.element = true;
                        }
                        linkedHashSet.add(expression);
                    }
                }
            }

            public /* bridge */ /* synthetic */ Object visitClass(IrClass irClass, Object obj) {
                visitClass(irClass, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public void visitContainerExpression(IrContainerExpression expression, TailRecursionCallsCollectorKt.VisitorState data) {
                expression.getClass();
                data.getClass();
                visitStatementContainer(expression, data);
            }

            public void visitElement(IrElement element, TailRecursionCallsCollectorKt.VisitorState data) {
                element.getClass();
                data.getClass();
                element.acceptChildren(this, new TailRecursionCallsCollectorKt.VisitorState(false, data.getInOtherFunction()));
            }

            public void visitExpressionBody(IrExpressionBody body, TailRecursionCallsCollectorKt.VisitorState data) {
                body.getClass();
                data.getClass();
                body.acceptChildren(this, data);
            }

            public /* bridge */ /* synthetic */ Object visitFunction(IrFunction irFunction2, Object obj) {
                visitFunction(irFunction2, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public void visitFunctionReference(IrFunctionReference expression, TailRecursionCallsCollectorKt.VisitorState data) {
                IrBody body;
                expression.getClass();
                data.getClass();
                expression.acceptChildren(this, new TailRecursionCallsCollectorKt.VisitorState(false, data.getInOtherFunction()));
                if (!((Boolean) function1.invoke(expression)).booleanValue() || (body = expression.getSymbol().getOwner().getBody()) == null) {
                    return;
                }
                body.accept(this, new TailRecursionCallsCollectorKt.VisitorState(false, true));
            }

            public void visitReturn(IrReturn expression, TailRecursionCallsCollectorKt.VisitorState data) {
                expression.getClass();
                data.getClass();
                expression.getValue().accept(this, new TailRecursionCallsCollectorKt.VisitorState(Intrinsics.areEqual(expression.getReturnTargetSymbol(), irFunction.getSymbol()), data.getInOtherFunction()));
            }

            public void visitRichFunctionReference(IrRichFunctionReference expression, TailRecursionCallsCollectorKt.VisitorState data) {
                IrBody body;
                expression.getClass();
                data.getClass();
                expression.acceptChildren(this, new TailRecursionCallsCollectorKt.VisitorState(false, data.getInOtherFunction()));
                if (!((Boolean) function2.invoke(expression)).booleanValue() || (body = expression.getInvokeFunction().getBody()) == null) {
                    return;
                }
                body.accept(this, new TailRecursionCallsCollectorKt.VisitorState(false, true));
            }

            public /* bridge */ /* synthetic */ Object visitTry(IrTry irTry, Object obj) {
                visitTry(irTry, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public void visitWhen(IrWhen expression, TailRecursionCallsCollectorKt.VisitorState data) {
                expression.getClass();
                data.getClass();
                for (IrBranch irBranch : expression.getBranches()) {
                    irBranch.getCondition().accept(this, new TailRecursionCallsCollectorKt.VisitorState(false, data.getInOtherFunction()));
                    irBranch.getResult().accept(this, data);
                }
            }

            public void visitClass(IrClass declaration, TailRecursionCallsCollectorKt.VisitorState data) {
                declaration.getClass();
                data.getClass();
            }

            public void visitFunction(IrFunction declaration, TailRecursionCallsCollectorKt.VisitorState data) {
                declaration.getClass();
                data.getClass();
            }

            public void visitTry(IrTry aTry, TailRecursionCallsCollectorKt.VisitorState data) {
                aTry.getClass();
                data.getClass();
            }

            public /* bridge */ /* synthetic */ Object visitBlockBody(IrBlockBody irBlockBody, Object obj) {
                visitBlockBody(irBlockBody, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitContainerExpression(IrContainerExpression irContainerExpression, Object obj) {
                visitContainerExpression(irContainerExpression, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitExpressionBody(IrExpressionBody irExpressionBody, Object obj) {
                visitExpressionBody(irExpressionBody, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitElement(IrElement irElement, Object obj) {
                visitElement(irElement, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitReturn(IrReturn irReturn, Object obj) {
                visitReturn(irReturn, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitRichFunctionReference(IrRichFunctionReference irRichFunctionReference, Object obj) {
                visitRichFunctionReference(irRichFunctionReference, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitWhen(IrWhen irWhen, Object obj) {
                visitWhen(irWhen, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitFunctionReference(IrFunctionReference irFunctionReference, Object obj) {
                visitFunctionReference(irFunctionReference, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }

            public /* bridge */ /* synthetic */ Object visitCall(IrCall irCall, Object obj) {
                visitCall(irCall, (TailRecursionCallsCollectorKt.VisitorState) obj);
                return Unit.INSTANCE;
            }
        };
        IrBody body = irFunction.getBody();
        if (body != null) {
            body.accept(irVisitor, new VisitorState(true, false));
        }
        return new TailCalls(linkedHashSet, booleanRef.element);
    }
}
