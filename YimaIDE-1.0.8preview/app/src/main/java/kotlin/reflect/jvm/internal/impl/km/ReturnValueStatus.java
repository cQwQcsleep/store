package kotlin.reflect.jvm.internal.impl.km;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum ReturnValueStatus {
    UNSPECIFIED,
    MUST_USE,
    EXPLICITLY_IGNORABLE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ReturnValueStatus> getEntries() {
        return $ENTRIES;
    }
}
