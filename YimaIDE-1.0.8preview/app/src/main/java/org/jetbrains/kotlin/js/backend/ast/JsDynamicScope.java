package org.jetbrains.kotlin.js.backend.ast;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/JsDynamicScope;", "Lorg/jetbrains/kotlin/js/backend/ast/JsScope;", "<init>", "()V", "doCreateName", "Lorg/jetbrains/kotlin/js/backend/ast/JsName;", "name", "", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsDynamicScope extends JsScope {
    public static final JsDynamicScope INSTANCE = new JsDynamicScope();

    private JsDynamicScope() {
        super(null, "Scope for dynamic declarations");
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.JsScope
    public JsName doCreateName(String name) {
        name.getClass();
        return new JsName(name, false);
    }
}
