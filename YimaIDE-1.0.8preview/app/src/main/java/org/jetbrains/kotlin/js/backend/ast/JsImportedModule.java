package org.jetbrains.kotlin.js.backend.ast;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u001a\u0002\b\u000b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModule;", "", "externalName", "", "internalName", "Lorg/jetbrains/kotlin/js/backend/ast/JsName;", "plainReference", "Lorg/jetbrains/kotlin/js/backend/ast/JsExpression;", "relativeRequirePath", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/js/backend/ast/JsName;Lorg/jetbrains/kotlin/js/backend/ast/JsExpression;Ljava/lang/String;)V", "Lkotlin/jvm/JvmOverloads;", "getExternalName", "()Ljava/lang/String;", "getInternalName", "()Lorg/jetbrains/kotlin/js/backend/ast/JsName;", "setInternalName", "(Lorg/jetbrains/kotlin/js/backend/ast/JsName;)V", "getPlainReference", "()Lorg/jetbrains/kotlin/js/backend/ast/JsExpression;", "getRelativeRequirePath", "key", "Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModuleKey;", "getKey", "()Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModuleKey;", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsImportedModule {
    private final String externalName;
    private JsName internalName;
    private final JsImportedModuleKey key;
    private final JsExpression plainReference;
    private final String relativeRequirePath;

    public JsImportedModule(String str, JsName jsName, JsExpression jsExpression, String str2) {
        str.getClass();
        jsName.getClass();
        this.externalName = str;
        this.internalName = jsName;
        this.plainReference = jsExpression;
        this.relativeRequirePath = str2;
        this.key = new JsImportedModuleKey(str, jsExpression != null ? jsExpression.toString() : null);
    }

    public final String getExternalName() {
        return this.externalName;
    }

    public final JsName getInternalName() {
        return this.internalName;
    }

    public final JsImportedModuleKey getKey() {
        return this.key;
    }

    public final JsExpression getPlainReference() {
        return this.plainReference;
    }

    public final String getRelativeRequirePath() {
        return this.relativeRequirePath;
    }

    public final void setInternalName(JsName jsName) {
        jsName.getClass();
        this.internalName = jsName;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JsImportedModule(String str, JsName jsName, JsExpression jsExpression) {
        this(str, jsName, jsExpression, null, 8, null);
        str.getClass();
        jsName.getClass();
    }

    public /* synthetic */ JsImportedModule(String str, JsName jsName, JsExpression jsExpression, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, jsName, jsExpression, (i & 8) != 0 ? null : str2);
    }
}
