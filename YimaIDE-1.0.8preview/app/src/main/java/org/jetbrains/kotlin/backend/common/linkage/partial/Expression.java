package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/Expression;", "", "kind", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/ExpressionKind;", "referencedDeclarationKind", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/DeclarationKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/common/linkage/partial/ExpressionKind;Lorg/jetbrains/kotlin/backend/common/linkage/partial/DeclarationKind;)V", "getKind", "()Lorg/jetbrains/kotlin/backend/common/linkage/partial/ExpressionKind;", "getReferencedDeclarationKind", "()Lorg/jetbrains/kotlin/backend/common/linkage/partial/DeclarationKind;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final /* data */ class Expression {
    private final ExpressionKind kind;
    private final DeclarationKind referencedDeclarationKind;

    public Expression(ExpressionKind expressionKind, DeclarationKind declarationKind) {
        expressionKind.getClass();
        this.kind = expressionKind;
        this.referencedDeclarationKind = declarationKind;
    }

    public static /* synthetic */ Expression copy$default(Expression expression, ExpressionKind expressionKind, DeclarationKind declarationKind, int i, Object obj) {
        if ((i & 1) != 0) {
            expressionKind = expression.kind;
        }
        if ((i & 2) != 0) {
            declarationKind = expression.referencedDeclarationKind;
        }
        return expression.copy(expressionKind, declarationKind);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ExpressionKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final DeclarationKind getReferencedDeclarationKind() {
        return this.referencedDeclarationKind;
    }

    public final Expression copy(ExpressionKind kind, DeclarationKind referencedDeclarationKind) {
        kind.getClass();
        return new Expression(kind, referencedDeclarationKind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Expression)) {
            return false;
        }
        Expression expression = (Expression) other;
        return this.kind == expression.kind && this.referencedDeclarationKind == expression.referencedDeclarationKind;
    }

    public final ExpressionKind getKind() {
        return this.kind;
    }

    public final DeclarationKind getReferencedDeclarationKind() {
        return this.referencedDeclarationKind;
    }

    public int hashCode() {
        int iHashCode = this.kind.hashCode() * 31;
        DeclarationKind declarationKind = this.referencedDeclarationKind;
        return iHashCode + (declarationKind == null ? 0 : declarationKind.hashCode());
    }

    public String toString() {
        return "Expression(kind=" + this.kind + ", referencedDeclarationKind=" + this.referencedDeclarationKind + Util.C_PARAM_END;
    }
}
