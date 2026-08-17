package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/util/ImplementationStatus;", "", "<init>", "(Ljava/lang/String;I)V", "NOT_IMPLEMENTED", "VAR_IMPLEMENTED_BY_VAL", "AMBIGUOUSLY_INHERITED", "INHERITED_OR_SYNTHESIZED", "ALREADY_IMPLEMENTED", "CANNOT_BE_IMPLEMENTED", "shouldBeImplemented", "", "getShouldBeImplemented", "()Z", "isOverridable", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum ImplementationStatus {
    NOT_IMPLEMENTED,
    VAR_IMPLEMENTED_BY_VAL,
    AMBIGUOUSLY_INHERITED,
    INHERITED_OR_SYNTHESIZED,
    ALREADY_IMPLEMENTED,
    CANNOT_BE_IMPLEMENTED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ImplementationStatus> getEntries() {
        return $ENTRIES;
    }

    public final boolean getShouldBeImplemented() {
        return this == NOT_IMPLEMENTED || this == AMBIGUOUSLY_INHERITED;
    }

    public final boolean isOverridable() {
        return (this == ALREADY_IMPLEMENTED || this == CANNOT_BE_IMPLEMENTED) ? false : true;
    }
}
