package org.jetbrains.kotlin.resolve.calls;

import com.intellij.lang.ASTNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtQualifiedExpression;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0018\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÀ\u0001¢\u0006\u0002\b\u0018J\u0014\u0010\u0019\u001a\u00020\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/CallExpressionElement;", "", "qualified", "Lorg/jetbrains/kotlin/psi/KtQualifiedExpression;", "<init>", "(Lorg/jetbrains/kotlin/psi/KtQualifiedExpression;)V", "getQualified", "()Lorg/jetbrains/kotlin/psi/KtQualifiedExpression;", "receiver", "Lorg/jetbrains/kotlin/psi/KtExpression;", "getReceiver", "()Lorg/jetbrains/kotlin/psi/KtExpression;", "selector", "getSelector", "safe", "", "getSafe", "()Z", "node", "Lcom/intellij/lang/ASTNode;", "getNode", "()Lcom/intellij/lang/ASTNode;", "component1", "copy", "copy$org_jetbrains_kotlin_frontend", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* data */ class CallExpressionElement {
    private final KtQualifiedExpression qualified;

    public CallExpressionElement(KtQualifiedExpression ktQualifiedExpression) {
        ktQualifiedExpression.getClass();
        this.qualified = ktQualifiedExpression;
    }

    public static /* synthetic */ CallExpressionElement copy$org_jetbrains_kotlin_frontend$default(CallExpressionElement callExpressionElement, KtQualifiedExpression ktQualifiedExpression, int i, Object obj) {
        if ((i & 1) != 0) {
            ktQualifiedExpression = callExpressionElement.qualified;
        }
        return callExpressionElement.copy$org_jetbrains_kotlin_frontend(ktQualifiedExpression);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final KtQualifiedExpression getQualified() {
        return this.qualified;
    }

    public final CallExpressionElement copy$org_jetbrains_kotlin_frontend(KtQualifiedExpression qualified) {
        qualified.getClass();
        return new CallExpressionElement(qualified);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CallExpressionElement) && Intrinsics.areEqual(this.qualified, ((CallExpressionElement) other).qualified);
    }

    public final ASTNode getNode() {
        return this.qualified.getOperationTokenNode();
    }

    public final KtQualifiedExpression getQualified() {
        return this.qualified;
    }

    public final KtExpression getReceiver() {
        return this.qualified.getReceiverExpression();
    }

    public final boolean getSafe() {
        return Intrinsics.areEqual(this.qualified.getOperationSign(), KtTokens.SAFE_ACCESS);
    }

    public final KtExpression getSelector() {
        return this.qualified.getSelectorExpression();
    }

    public int hashCode() {
        return this.qualified.hashCode();
    }

    public String toString() {
        return "CallExpressionElement(qualified=" + this.qualified + ')';
    }
}
