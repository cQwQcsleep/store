package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/Compatibility;", "", "<init>", "(Ljava/lang/String;I)V", "COMPATIBLE", "REDUNDANT", "REVERSE_REDUNDANT", "REPEATED", "DEPRECATED", "INCOMPATIBLE", "COMPATIBLE_FOR_CLASSES_ONLY", "org.jetbrains.kotlin:psi-frontend-utils"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum Compatibility {
    COMPATIBLE,
    REDUNDANT,
    REVERSE_REDUNDANT,
    REPEATED,
    DEPRECATED,
    INCOMPATIBLE,
    COMPATIBLE_FOR_CLASSES_ONLY;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<Compatibility> getEntries() {
        return $ENTRIES;
    }
}
