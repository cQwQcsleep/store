package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\b\u0007\u001a\u0002\b\u0006¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bR\u001c\u0010\u0007\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirExpressionRef;", "T", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", Argument.Delimiters.none, "<init>", "()V", "Lorg/jetbrains/kotlin/fir/FirContractViolation;", "value", "getValue", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setValue", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "bind", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionRef<T extends FirExpression> {
    public T value;

    @FirContractViolation
    public FirExpressionRef() {
    }

    public final void bind(T value) {
        value.getClass();
        setValue(value);
    }

    public final T getValue() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("value");
        return null;
    }

    public final void setValue(T t) {
        t.getClass();
        this.value = t;
    }
}
