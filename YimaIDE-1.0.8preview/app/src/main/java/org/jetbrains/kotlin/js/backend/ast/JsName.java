package org.jetbrains.kotlin.js.backend.ast;

import org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadataImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class JsName extends HasMetadataImpl {
    private final String ident;
    private final boolean temporary;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "ident";
        } else {
            objArr[0] = "org/jetbrains/kotlin/js/backend/ast/JsName";
        }
        if (i != 1) {
            objArr[1] = "org/jetbrains/kotlin/js/backend/ast/JsName";
        } else {
            objArr[1] = "getIdent";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public JsName(String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.ident = str;
        this.temporary = z;
    }

    public String getIdent() {
        String str = this.ident;
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        return str;
    }

    public boolean isTemporary() {
        return this.temporary;
    }

    public JsNameRef makeRef() {
        return new JsNameRef(this);
    }

    public String toString() {
        return this.ident;
    }
}
