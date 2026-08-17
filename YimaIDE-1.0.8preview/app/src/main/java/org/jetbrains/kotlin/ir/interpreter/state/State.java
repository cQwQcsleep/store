package org.jetbrains.kotlin.ir.interpreter.state;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00002\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0000H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&R\"\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00000\u0003j\u0002`\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/interpreter/state/State;", "", "fields", "", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lorg/jetbrains/kotlin/ir/interpreter/stack/Fields;", "getFields", "()Ljava/util/Map;", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getIrClass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getField", "symbol", "setField", "", "state", "getIrFunctionByIrCall", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "org.jetbrains.kotlin:ir.interpreter"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface State {
    default State getField(IrSymbol symbol) {
        symbol.getClass();
        return getFields().get(symbol);
    }

    Map<IrSymbol, State> getFields();

    IrClass getIrClass();

    IrFunction getIrFunctionByIrCall(IrCall expression);

    default void setField(IrSymbol symbol, State state) {
        symbol.getClass();
        state.getClass();
        getFields().put(symbol, state);
    }
}
