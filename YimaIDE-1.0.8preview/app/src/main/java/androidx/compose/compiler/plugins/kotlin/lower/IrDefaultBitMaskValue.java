package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/IrDefaultBitMaskValue;", "", "irIsolateBitAtIndex", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "index", "", "irHasAnyProvidedAndUnstable", "unstable", "", "putAsValueArgumentIn", "", "fn", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "startIndex", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrDefaultBitMaskValue {
    IrExpression irHasAnyProvidedAndUnstable(boolean[] unstable);

    IrExpression irIsolateBitAtIndex(int index);

    void putAsValueArgumentIn(IrFunctionAccessExpression fn, int startIndex);
}
