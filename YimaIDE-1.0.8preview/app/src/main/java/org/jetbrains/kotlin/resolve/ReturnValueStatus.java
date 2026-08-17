package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "", "<init>", "(Ljava/lang/String;I)V", "MustUse", "ExplicitlyIgnorable", "Unspecified", "Companion", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum ReturnValueStatus {
    MustUse,
    ExplicitlyIgnorable,
    Unspecified;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<ReturnValueStatus> getEntries() {
        return $ENTRIES;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ReturnValueStatus$Companion;", "", "<init>", "()V", "fromBitFlags", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "hasMustUseReturnValue", "", "hasIgnorableReturnValue", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ReturnValueStatus fromBitFlags(boolean hasMustUseReturnValue, boolean hasIgnorableReturnValue) {
            if (hasMustUseReturnValue && hasIgnorableReturnValue) {
                k2d.a("State is incorrect: cannot be both must use and explicitly ignorable");
                return null;
            }
            if (hasMustUseReturnValue) {
                return ReturnValueStatus.MustUse;
            }
            return hasIgnorableReturnValue ? ReturnValueStatus.ExplicitlyIgnorable : ReturnValueStatus.Unspecified;
        }

        private Companion() {
        }
    }
}
