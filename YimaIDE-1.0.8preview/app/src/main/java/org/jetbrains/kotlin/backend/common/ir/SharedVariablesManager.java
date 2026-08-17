package org.jetbrains.kotlin.backend.common.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrSetValue;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/SharedVariablesManager;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "declareSharedVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "originalDeclaration", "defineSharedValue", "Lorg/jetbrains/kotlin/ir/IrStatement;", "sharedVariableDeclaration", "getSharedValue", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "sharedVariableSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", "originalGet", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "setSharedValue", "originalSet", "Lorg/jetbrains/kotlin/ir/expressions/IrSetValue;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class SharedVariablesManager {
    public abstract IrVariable declareSharedVariable(IrVariable originalDeclaration);

    public abstract IrStatement defineSharedValue(IrVariable originalDeclaration, IrVariable sharedVariableDeclaration);

    public abstract IrExpression getSharedValue(IrValueSymbol sharedVariableSymbol, IrGetValue originalGet);

    public abstract IrExpression setSharedValue(IrValueSymbol sharedVariableSymbol, IrSetValue originalSet);
}
