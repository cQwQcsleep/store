package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/NonVarargSpread;", "Lorg/jetbrains/kotlin/fir/resolve/calls/InapplicableArgumentDiagnostic;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getArgument", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NonVarargSpread extends InapplicableArgumentDiagnostic {
    private final FirExpression argument;

    public NonVarargSpread(FirExpression firExpression) {
        firExpression.getClass();
        this.argument = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.InapplicableArgumentDiagnostic
    public FirExpression getArgument() {
        return this.argument;
    }
}
