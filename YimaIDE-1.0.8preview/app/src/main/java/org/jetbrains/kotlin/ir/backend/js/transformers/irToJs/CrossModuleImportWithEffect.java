package org.jetbrains.kotlin.ir.backend.js.transformers.irToJs;

import kotlin.Metadata;
import org.jetbrains.kotlin.js.backend.ast.JsImportedModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CrossModuleImportWithEffect;", "", "moduleExporter", "Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModule;", "<init>", "(Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModule;)V", "getModuleExporter", "()Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModule;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CrossModuleImportWithEffect {
    private final JsImportedModule moduleExporter;

    public CrossModuleImportWithEffect(JsImportedModule jsImportedModule) {
        jsImportedModule.getClass();
        this.moduleExporter = jsImportedModule;
    }

    public final JsImportedModule getModuleExporter() {
        return this.moduleExporter;
    }
}
