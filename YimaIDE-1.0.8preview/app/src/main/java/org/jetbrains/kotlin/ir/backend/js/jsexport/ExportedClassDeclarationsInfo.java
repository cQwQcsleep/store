package org.jetbrains.kotlin.ir.backend.js.jsexport;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086\u0002J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u0086\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedClassDeclarationsInfo;", "", "members", "", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedDeclaration;", "nestedClasses", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedClass;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getMembers", "()Ljava/util/List;", "getNestedClasses", "component1", "component2", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ExportedClassDeclarationsInfo {
    private final List<ExportedDeclaration> members;
    private final List<ExportedClass> nestedClasses;

    /* JADX WARN: Multi-variable type inference failed */
    public ExportedClassDeclarationsInfo(List<? extends ExportedDeclaration> list, List<? extends ExportedClass> list2) {
        list.getClass();
        list2.getClass();
        this.members = list;
        this.nestedClasses = list2;
    }

    public final List<ExportedDeclaration> component1() {
        return this.members;
    }

    public final List<ExportedClass> component2() {
        return this.nestedClasses;
    }

    public final List<ExportedDeclaration> getMembers() {
        return this.members;
    }

    public final List<ExportedClass> getNestedClasses() {
        return this.nestedClasses;
    }
}
