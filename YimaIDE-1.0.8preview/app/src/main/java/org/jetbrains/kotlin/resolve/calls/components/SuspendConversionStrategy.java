package org.jetbrains.kotlin.resolve.calls.components;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/components/SuspendConversionStrategy;", "", "<init>", "(Ljava/lang/String;I)V", "SUSPEND_CONVERSION", "NO_CONVERSION", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum SuspendConversionStrategy {
    SUSPEND_CONVERSION,
    NO_CONVERSION;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<SuspendConversionStrategy> getEntries() {
        return $ENTRIES;
    }
}
