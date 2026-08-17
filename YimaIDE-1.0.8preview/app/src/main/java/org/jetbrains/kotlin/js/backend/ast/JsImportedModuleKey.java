package org.jetbrains.kotlin.js.backend.ast;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/JsImportedModuleKey;", "", "baseName", "", "plainName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getBaseName", "()Ljava/lang/String;", "getPlainName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsImportedModuleKey {
    private final String baseName;
    private final String plainName;

    public JsImportedModuleKey(String str, String str2) {
        str.getClass();
        this.baseName = str;
        this.plainName = str2;
    }

    public static /* synthetic */ JsImportedModuleKey copy$default(JsImportedModuleKey jsImportedModuleKey, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsImportedModuleKey.baseName;
        }
        if ((i & 2) != 0) {
            str2 = jsImportedModuleKey.plainName;
        }
        return jsImportedModuleKey.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getBaseName() {
        return this.baseName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPlainName() {
        return this.plainName;
    }

    public final JsImportedModuleKey copy(String baseName, String plainName) {
        baseName.getClass();
        return new JsImportedModuleKey(baseName, plainName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsImportedModuleKey)) {
            return false;
        }
        JsImportedModuleKey jsImportedModuleKey = (JsImportedModuleKey) other;
        return Intrinsics.areEqual(this.baseName, jsImportedModuleKey.baseName) && Intrinsics.areEqual(this.plainName, jsImportedModuleKey.plainName);
    }

    public final String getBaseName() {
        return this.baseName;
    }

    public final String getPlainName() {
        return this.plainName;
    }

    public int hashCode() {
        int iHashCode = this.baseName.hashCode() * 31;
        String str = this.plainName;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "JsImportedModuleKey(baseName=" + this.baseName + ", plainName=" + this.plainName + ')';
    }
}
