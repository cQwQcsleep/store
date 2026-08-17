package org.jetbrains.kotlin.ir.expressions;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrDeclarationReference;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "<init>", "()V", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrDeclarationReference extends IrExpression {
    /* JADX INFO: renamed from: getSymbol */
    public abstract IrSymbol mo387getSymbol();
}
