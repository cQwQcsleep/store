package org.jetbrains.kotlin.js.backend.ast;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class JsLiteral extends JsExpression {

    public static abstract class JsValueLiteral extends JsLiteral {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/js/backend/ast/JsLiteral$JsValueLiteral", "deepCopy"));
        }

        @Override // org.jetbrains.kotlin.js.backend.ast.JsExpression
        public final boolean isLeaf() {
            return true;
        }

        @Override // org.jetbrains.kotlin.js.backend.ast.JsExpression, org.jetbrains.kotlin.js.backend.ast.JsNode
        public JsExpression deepCopy() {
            return this;
        }
    }
}
