package org.jetbrains.kotlin.resolve.calls.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/model/ArgumentMatchStatus;", "", "isError", "", "<init>", "(Ljava/lang/String;IZ)V", "()Z", "SUCCESS", "TYPE_MISMATCH", "ARGUMENT_HAS_NO_TYPE", "MATCH_MODULO_UNINFERRED_TYPES", "UNKNOWN", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum ArgumentMatchStatus {
    SUCCESS(false),
    TYPE_MISMATCH(false, 1, null),
    ARGUMENT_HAS_NO_TYPE(false, 1, null),
    MATCH_MODULO_UNINFERRED_TYPES(false, 1, null),
    UNKNOWN(false, 1, null);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean isError;

    /* synthetic */ ArgumentMatchStatus(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public static EnumEntries<ArgumentMatchStatus> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: isError, reason: from getter */
    public final boolean getIsError() {
        return this.isError;
    }

    ArgumentMatchStatus(boolean z) {
        this.isError = z;
    }
}
