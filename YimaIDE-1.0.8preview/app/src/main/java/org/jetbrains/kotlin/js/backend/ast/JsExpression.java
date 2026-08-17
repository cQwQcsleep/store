package org.jetbrains.kotlin.js.backend.ast;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class JsExpression extends SourceInfoAwareJsNode {

    public static abstract class JsExpressionHasArguments extends JsExpression implements HasArguments {
        protected final List<JsExpression> arguments;

        public JsExpressionHasArguments(List<JsExpression> list) {
            this.arguments = list;
        }

        @Override // org.jetbrains.kotlin.js.backend.ast.JsExpression, org.jetbrains.kotlin.js.backend.ast.JsNode
        public /* bridge */ /* synthetic */ JsNode deepCopy() {
            return super.deepCopy();
        }

        @Override // org.jetbrains.kotlin.js.backend.ast.HasArguments
        public List<JsExpression> getArguments() {
            return this.arguments;
        }
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsNode
    public abstract JsExpression deepCopy();

    public boolean isLeaf() {
        return false;
    }

    public JsStatement makeStmt() {
        return new JsExpressionStatement(this);
    }
}
