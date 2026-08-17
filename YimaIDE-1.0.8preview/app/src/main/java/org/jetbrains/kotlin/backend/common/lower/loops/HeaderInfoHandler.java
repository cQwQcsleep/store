package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J'\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u0010H&¢\u0006\u0002\u0010\u0011J1\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00028\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfoHandler;", "E", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "D", "", "matchIterable", "", "expression", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)Z", "matchIteratorCall", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "build", "Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "data", "scopeOwner", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Ljava/lang/Object;Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "handle", "iteratorCall", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/ir/expressions/IrCall;Ljava/lang/Object;Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface HeaderInfoHandler<E extends IrExpression, D> {
    HeaderInfo build(E expression, D data, IrSymbol scopeOwner);

    default HeaderInfo handle(E expression, IrCall iteratorCall, D data, IrSymbol scopeOwner) {
        expression.getClass();
        scopeOwner.getClass();
        if ((iteratorCall == null || matchIteratorCall(iteratorCall)) && matchIterable(expression)) {
            return build(expression, data, scopeOwner);
        }
        return null;
    }

    boolean matchIterable(E expression);

    default boolean matchIteratorCall(IrCall call) {
        call.getClass();
        return true;
    }
}
