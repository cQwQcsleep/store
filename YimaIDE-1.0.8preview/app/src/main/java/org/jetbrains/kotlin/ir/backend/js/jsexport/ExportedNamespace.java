package org.jetbrains.kotlin.ir.backend.js.jsexport;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedNamespace;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedDeclaration;", "name", "", "declarations", "", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getDeclarations", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ExportedNamespace extends ExportedDeclaration {
    private final List<ExportedDeclaration> declarations;
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ExportedNamespace(String str, List<? extends ExportedDeclaration> list) {
        super(null);
        str.getClass();
        list.getClass();
        this.name = str;
        this.declarations = list;
    }

    public final List<ExportedDeclaration> getDeclarations() {
        return this.declarations;
    }

    public final String getName() {
        return this.name;
    }
}
