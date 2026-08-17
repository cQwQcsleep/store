package org.jetbrains.kotlin.ir.builders;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.expressions.IrElseBranch;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001\u001a>\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001\u001a\u001a\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\"\u0010\u0011\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0001\u001a*\u0010\u0013\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001\u001a\u001a\u0010\u0014\u001a\u00020\u0015*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u001a\u0010\u0016\u001a\u00020\u0015*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0012\u0010\u0017\u001a\u00020\u0018*\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0001\u001a4\u0010\u001a\u001a\u00020\u001b*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n\u001a$\u0010\u001a\u001a\u00020\u001b*\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n\u001a\u001a\u0010\u001e\u001a\u00020\u001b*\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001\u001a4\u0010\u001f\u001a\u00020\u001b*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n\u001a$\u0010\u001f\u001a\u00020\u001b*\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006 "}, d2 = {"primitiveOp1", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "startOffset", "", "endOffset", "primitiveOpSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "primitiveOpReturnType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "origin", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "dispatchReceiver", "primitiveOp2", "argument1", "argument2", "constNull", "Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContextInterface;", "equalsNull", "argument", "eqeqeq", "constTrue", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrConstImpl;", "constFalse", "elseBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrElseBranch;", "elseExpr", "oror", "Lorg/jetbrains/kotlin/ir/expressions/IrWhen;", "a", "b", "whenComma", "andand", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PrimitivesKt {
    public static final IrWhen andand(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        irStatementOrigin.getClass();
        IrWhenImpl IrWhenImpl = BuildersKt.IrWhenImpl(i, i2, irGeneratorContextInterface.getIrBuiltIns().getBooleanType(), irStatementOrigin);
        IrWhenImpl.getBranches().add(BuildersKt.IrBranchImpl(irExpression, irExpression2));
        IrWhenImpl.getBranches().add(elseBranch(irGeneratorContextInterface, constFalse(irGeneratorContextInterface, irExpression2.getStartOffset(), irExpression2.getEndOffset())));
        return IrWhenImpl;
    }

    public static /* synthetic */ IrWhen andand$default(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            irStatementOrigin = IrStatementOrigin.Companion.getANDAND();
        }
        return andand(irGeneratorContextInterface, i, i2, irExpression, irExpression2, irStatementOrigin);
    }

    public static final IrConstImpl constFalse(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2) {
        irGeneratorContextInterface.getClass();
        return IrConstImpl.Companion.constFalse(i, i2, irGeneratorContextInterface.getIrBuiltIns().getBooleanType());
    }

    public static final IrExpression constNull(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2) {
        irGeneratorContextInterface.getClass();
        return IrConstImpl.Companion.constNull(i, i2, irGeneratorContextInterface.getIrBuiltIns().getNothingNType());
    }

    public static final IrConstImpl constTrue(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2) {
        irGeneratorContextInterface.getClass();
        return IrConstImpl.Companion.constTrue(i, i2, irGeneratorContextInterface.getIrBuiltIns().getBooleanType());
    }

    public static final IrElseBranch elseBranch(IrGeneratorContextInterface irGeneratorContextInterface, IrExpression irExpression) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        int startOffset = irExpression.getStartOffset();
        int endOffset = irExpression.getEndOffset();
        return BuildersKt.IrElseBranchImpl(startOffset, endOffset, constTrue(irGeneratorContextInterface, startOffset, endOffset), irExpression);
    }

    public static final IrExpression eqeqeq(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2, IrExpression irExpression, IrExpression irExpression2) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        return primitiveOp2(i, i2, irGeneratorContextInterface.getIrBuiltIns().getEqeqeqSymbol(), irGeneratorContextInterface.getIrBuiltIns().getBooleanType(), IrStatementOrigin.Companion.getEQEQEQ(), irExpression, irExpression2);
    }

    public static final IrExpression equalsNull(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2, IrExpression irExpression) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        return primitiveOp2(i, i2, irGeneratorContextInterface.getIrBuiltIns().getEqeqSymbol(), irGeneratorContextInterface.getIrBuiltIns().getBooleanType(), IrStatementOrigin.Companion.getEQEQ(), irExpression, constNull(irGeneratorContextInterface, i, i2));
    }

    public static final IrWhen oror(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        irStatementOrigin.getClass();
        IrWhenImpl IrWhenImpl = BuildersKt.IrWhenImpl(i, i2, irGeneratorContextInterface.getIrBuiltIns().getBooleanType(), irStatementOrigin);
        IrWhenImpl.getBranches().add(BuildersKt.IrBranchImpl(irExpression, constTrue(irGeneratorContextInterface, irExpression.getStartOffset(), irExpression.getEndOffset())));
        IrWhenImpl.getBranches().add(elseBranch(irGeneratorContextInterface, irExpression2));
        return IrWhenImpl;
    }

    public static /* synthetic */ IrWhen oror$default(IrGeneratorContextInterface irGeneratorContextInterface, int i, int i2, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            irStatementOrigin = IrStatementOrigin.Companion.getOROR();
        }
        return oror(irGeneratorContextInterface, i, i2, irExpression, irExpression2, irStatementOrigin);
    }

    public static final IrExpression primitiveOp1(int i, int i2, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrType irType, IrStatementOrigin irStatementOrigin, IrExpression irExpression) {
        irSimpleFunctionSymbol.getClass();
        irType.getClass();
        irStatementOrigin.getClass();
        irExpression.getClass();
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(i, i2, irType, irSimpleFunctionSymbol, 0, irStatementOrigin, (IrClassSymbol) null, 64, (Object) null);
        irCallImplIrCallImpl$default.getArguments().set(0, irExpression);
        return irCallImplIrCallImpl$default;
    }

    public static final IrExpression primitiveOp2(int i, int i2, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrType irType, IrStatementOrigin irStatementOrigin, IrExpression irExpression, IrExpression irExpression2) {
        irSimpleFunctionSymbol.getClass();
        irType.getClass();
        irStatementOrigin.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(i, i2, irType, irSimpleFunctionSymbol, 0, irStatementOrigin, (IrClassSymbol) null, 64, (Object) null);
        irCallImplIrCallImpl$default.getArguments().set(0, irExpression);
        irCallImplIrCallImpl$default.getArguments().set(1, irExpression2);
        return irCallImplIrCallImpl$default;
    }

    public static final IrWhen whenComma(IrGeneratorContextInterface irGeneratorContextInterface, IrExpression irExpression, IrExpression irExpression2) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        return oror(irGeneratorContextInterface, irExpression, irExpression2, IrStatementOrigin.Companion.getWHEN_COMMA());
    }

    public static /* synthetic */ IrWhen andand$default(IrGeneratorContextInterface irGeneratorContextInterface, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            irStatementOrigin = IrStatementOrigin.Companion.getANDAND();
        }
        return andand(irGeneratorContextInterface, irExpression, irExpression2, irStatementOrigin);
    }

    public static /* synthetic */ IrWhen oror$default(IrGeneratorContextInterface irGeneratorContextInterface, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            irStatementOrigin = IrStatementOrigin.Companion.getOROR();
        }
        return oror(irGeneratorContextInterface, irExpression, irExpression2, irStatementOrigin);
    }

    public static final IrWhen andand(IrGeneratorContextInterface irGeneratorContextInterface, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        irStatementOrigin.getClass();
        return andand(irGeneratorContextInterface, irExpression2.getStartOffset(), irExpression2.getEndOffset(), irExpression, irExpression2, irStatementOrigin);
    }

    public static final IrWhen oror(IrGeneratorContextInterface irGeneratorContextInterface, IrExpression irExpression, IrExpression irExpression2, IrStatementOrigin irStatementOrigin) {
        irGeneratorContextInterface.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        irStatementOrigin.getClass();
        return oror(irGeneratorContextInterface, irExpression2.getStartOffset(), irExpression2.getEndOffset(), irExpression, irExpression2, irStatementOrigin);
    }
}
