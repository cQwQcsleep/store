package org.jetbrains.kotlin.ir.backend.js.ic;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/DirtyFileState;", "", "str", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getStr", "()Ljava/lang/String;", "ADDED_FILE", "MODIFIED_IR", "NON_MODIFIED_IR", "UPDATED_EXPORTS", "UPDATED_IMPORTS", "REMOVED_INVERSE_DEPENDS", "REMOVED_DIRECT_DEPENDS", "REMOVED_FILE", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum DirtyFileState {
    ADDED_FILE("added file"),
    MODIFIED_IR("modified ir"),
    NON_MODIFIED_IR("non modified ir"),
    UPDATED_EXPORTS("updated exports"),
    UPDATED_IMPORTS("updated imports"),
    REMOVED_INVERSE_DEPENDS("removed inverse depends"),
    REMOVED_DIRECT_DEPENDS("removed direct depends"),
    REMOVED_FILE("removed file");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String str;

    DirtyFileState(String str) {
        this.str = str;
    }

    public static EnumEntries<DirtyFileState> getEntries() {
        return $ENTRIES;
    }

    public final String getStr() {
        return this.str;
    }
}
