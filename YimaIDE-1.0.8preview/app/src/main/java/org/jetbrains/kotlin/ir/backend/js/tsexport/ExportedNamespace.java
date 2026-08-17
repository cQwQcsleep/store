package org.jetbrains.kotlin.ir.backend.js.tsexport;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedNamespace;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedDeclaration;", "name", "", "declarations", "", "isPrivate", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Z)V", "getName", "()Ljava/lang/String;", "getDeclarations", "()Ljava/util/List;", "()Z", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ExportedNamespace extends ExportedDeclaration {
    private final List<ExportedDeclaration> declarations;
    private final boolean isPrivate;
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ExportedNamespace(String str, List<? extends ExportedDeclaration> list, boolean z) {
        super(null);
        str.getClass();
        list.getClass();
        this.name = str;
        this.declarations = list;
        this.isPrivate = z;
    }

    public final List<ExportedDeclaration> getDeclarations() {
        return this.declarations;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: isPrivate, reason: from getter */
    public final boolean getIsPrivate() {
        return this.isPrivate;
    }

    public /* synthetic */ ExportedNamespace(String str, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i & 4) != 0 ? false : z);
    }
}
