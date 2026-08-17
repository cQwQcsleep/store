package org.jetbrains.kotlin.ir.backend.js.transformers.irToJs;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.js.backend.ast.JsName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/Scope;", "", "<init>", "()V", "declaredNames", "", "Lorg/jetbrains/kotlin/js/backend/ast/JsName;", "getDeclaredNames", "()Ljava/util/Set;", "usedNames", "getUsedNames", "children", "getChildren", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class Scope {
    private final Set<JsName> declaredNames = new LinkedHashSet();
    private final Set<JsName> usedNames = new LinkedHashSet();
    private final Set<Scope> children = new LinkedHashSet();

    public final Set<Scope> getChildren() {
        return this.children;
    }

    public final Set<JsName> getDeclaredNames() {
        return this.declaredNames;
    }

    public final Set<JsName> getUsedNames() {
        return this.usedNames;
    }
}
