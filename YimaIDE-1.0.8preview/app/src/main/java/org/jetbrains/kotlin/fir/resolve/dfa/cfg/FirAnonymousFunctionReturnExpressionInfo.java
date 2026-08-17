package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirAnonymousFunctionReturnExpressionInfo;", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isExplicit", Argument.Delimiters.none, "containingStatement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;ZLorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "()Z", "getContainingStatement", "()Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirAnonymousFunctionReturnExpressionInfo {
    private final FirStatement containingStatement;
    private final FirExpression expression;
    private final boolean isExplicit;

    public FirAnonymousFunctionReturnExpressionInfo(FirExpression firExpression, boolean z, FirStatement firStatement) {
        firExpression.getClass();
        firStatement.getClass();
        this.expression = firExpression;
        this.isExplicit = z;
        this.containingStatement = firStatement;
    }

    public static /* synthetic */ FirAnonymousFunctionReturnExpressionInfo copy$default(FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfo, FirExpression firExpression, boolean z, FirStatement firStatement, int i, Object obj) {
        if ((i & 1) != 0) {
            firExpression = firAnonymousFunctionReturnExpressionInfo.expression;
        }
        if ((i & 2) != 0) {
            z = firAnonymousFunctionReturnExpressionInfo.isExplicit;
        }
        if ((i & 4) != 0) {
            firStatement = firAnonymousFunctionReturnExpressionInfo.containingStatement;
        }
        return firAnonymousFunctionReturnExpressionInfo.copy(firExpression, z, firStatement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirExpression getExpression() {
        return this.expression;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsExplicit() {
        return this.isExplicit;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final FirStatement getContainingStatement() {
        return this.containingStatement;
    }

    public final FirAnonymousFunctionReturnExpressionInfo copy(FirExpression expression, boolean isExplicit, FirStatement containingStatement) {
        expression.getClass();
        containingStatement.getClass();
        return new FirAnonymousFunctionReturnExpressionInfo(expression, isExplicit, containingStatement);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirAnonymousFunctionReturnExpressionInfo)) {
            return false;
        }
        FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfo = (FirAnonymousFunctionReturnExpressionInfo) other;
        return Intrinsics.areEqual(this.expression, firAnonymousFunctionReturnExpressionInfo.expression) && this.isExplicit == firAnonymousFunctionReturnExpressionInfo.isExplicit && Intrinsics.areEqual(this.containingStatement, firAnonymousFunctionReturnExpressionInfo.containingStatement);
    }

    public final FirStatement getContainingStatement() {
        return this.containingStatement;
    }

    public final FirExpression getExpression() {
        return this.expression;
    }

    public int hashCode() {
        return (((this.expression.hashCode() * 31) + Boolean.hashCode(this.isExplicit)) * 31) + this.containingStatement.hashCode();
    }

    public final boolean isExplicit() {
        return this.isExplicit;
    }

    public String toString() {
        return "FirAnonymousFunctionReturnExpressionInfo(expression=" + this.expression + ", isExplicit=" + this.isExplicit + ", containingStatement=" + this.containingStatement + ')';
    }
}
