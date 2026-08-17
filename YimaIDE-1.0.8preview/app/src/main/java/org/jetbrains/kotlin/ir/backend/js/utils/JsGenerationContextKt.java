package org.jetbrains.kotlin.ir.backend.js.utils;

import kotlin.Metadata;
import org.jetbrains.kotlin.js.backend.ast.JsName;
import org.jetbrains.kotlin.js.backend.ast.JsScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"emptyScope", "Lorg/jetbrains/kotlin/js/backend/ast/JsScope;", "getEmptyScope", "()Lorg/jetbrains/kotlin/js/backend/ast/JsScope;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JsGenerationContextKt {
    private static final JsScope emptyScope = new JsScope() { // from class: org.jetbrains.kotlin.ir.backend.js.utils.JsGenerationContextKt$emptyScope$1
        @Override // org.jetbrains.kotlin.js.backend.ast.JsScope
        public void copyOwnNames(JsScope other) {
            throw new IllegalStateException("Trying to copy names to empty scope");
        }

        @Override // org.jetbrains.kotlin.js.backend.ast.JsScope
        public JsName doCreateName(String ident) {
            ident.getClass();
            throw new IllegalStateException("Trying to create name in empty scope");
        }
    };

    public static final JsScope getEmptyScope() {
        return emptyScope;
    }
}
