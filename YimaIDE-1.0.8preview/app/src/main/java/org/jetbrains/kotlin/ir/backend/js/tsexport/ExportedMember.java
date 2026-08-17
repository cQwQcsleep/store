package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b\u0082\u0001\u0003\n\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMember;", "", "name", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName;", "getName", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName;", "isMember", "", "()Z", "isStatic", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedField;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedFunction;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedPropertyAccessor;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ExportedMember {
    ExportedMemberName getName();

    boolean isMember();

    boolean isStatic();
}
