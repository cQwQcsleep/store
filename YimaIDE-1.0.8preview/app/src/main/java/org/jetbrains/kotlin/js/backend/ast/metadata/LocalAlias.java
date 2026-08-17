package org.jetbrains.kotlin.js.backend.ast.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.js.backend.ast.JsName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/metadata/LocalAlias;", "", "name", "Lorg/jetbrains/kotlin/js/backend/ast/JsName;", "tag", "", "<init>", "(Lorg/jetbrains/kotlin/js/backend/ast/JsName;Ljava/lang/String;)V", "getName", "()Lorg/jetbrains/kotlin/js/backend/ast/JsName;", "getTag", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class LocalAlias {
    private final JsName name;
    private final String tag;

    public LocalAlias(JsName jsName, String str) {
        jsName.getClass();
        this.name = jsName;
        this.tag = str;
    }

    public static /* synthetic */ LocalAlias copy$default(LocalAlias localAlias, JsName jsName, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            jsName = localAlias.name;
        }
        if ((i & 2) != 0) {
            str = localAlias.tag;
        }
        return localAlias.copy(jsName, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final JsName getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTag() {
        return this.tag;
    }

    public final LocalAlias copy(JsName name, String tag) {
        name.getClass();
        return new LocalAlias(name, tag);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalAlias)) {
            return false;
        }
        LocalAlias localAlias = (LocalAlias) other;
        return Intrinsics.areEqual(this.name, localAlias.name) && Intrinsics.areEqual(this.tag, localAlias.tag);
    }

    public final JsName getName() {
        return this.name;
    }

    public final String getTag() {
        return this.tag;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.tag;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "LocalAlias(name=" + this.name + ", tag=" + this.tag + ')';
    }
}
