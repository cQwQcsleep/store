package org.jetbrains.kotlin.ir.backend.js.jsexport;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedModule;", "", "name", "", "declarations", "", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedDeclaration;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getDeclarations", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ExportedModule {
    private final List<ExportedDeclaration> declarations;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public ExportedModule(String str, List<? extends ExportedDeclaration> list) {
        str.getClass();
        list.getClass();
        this.name = str;
        this.declarations = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExportedModule copy$default(ExportedModule exportedModule, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = exportedModule.name;
        }
        if ((i & 2) != 0) {
            list = exportedModule.declarations;
        }
        return exportedModule.copy(str, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<ExportedDeclaration> component2() {
        return this.declarations;
    }

    public final ExportedModule copy(String name, List<? extends ExportedDeclaration> declarations) {
        name.getClass();
        declarations.getClass();
        return new ExportedModule(name, declarations);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExportedModule)) {
            return false;
        }
        ExportedModule exportedModule = (ExportedModule) other;
        return Intrinsics.areEqual(this.name, exportedModule.name) && Intrinsics.areEqual(this.declarations, exportedModule.declarations);
    }

    public final List<ExportedDeclaration> getDeclarations() {
        return this.declarations;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.declarations.hashCode();
    }

    public String toString() {
        return "ExportedModule(name=" + this.name + ", declarations=" + this.declarations + ')';
    }
}
