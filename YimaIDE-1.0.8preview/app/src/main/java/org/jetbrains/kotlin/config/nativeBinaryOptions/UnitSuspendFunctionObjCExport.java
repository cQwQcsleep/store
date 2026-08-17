package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 org.jetbrains.kotlin.config.nativeBinaryOptions.UnitSuspendFunctionObjCExport, still in use, count: 1, list:
  (r0v1 org.jetbrains.kotlin.config.nativeBinaryOptions.UnitSuspendFunctionObjCExport) from 0x0028: SPUT (r0v1 org.jetbrains.kotlin.config.nativeBinaryOptions.UnitSuspendFunctionObjCExport) org.jetbrains.kotlin.config.nativeBinaryOptions.UnitSuspendFunctionObjCExport.DEFAULT org.jetbrains.kotlin.config.nativeBinaryOptions.UnitSuspendFunctionObjCExport
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/UnitSuspendFunctionObjCExport;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "LEGACY", "PROPER", "Companion", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnitSuspendFunctionObjCExport {
    LEGACY,
    PROPER;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final UnitSuspendFunctionObjCExport DEFAULT = new UnitSuspendFunctionObjCExport();

    static {
    }

    private UnitSuspendFunctionObjCExport() {
        super(str, i);
    }

    public static EnumEntries<UnitSuspendFunctionObjCExport> getEntries() {
        return $ENTRIES;
    }

    public static UnitSuspendFunctionObjCExport valueOf(String str) {
        return (UnitSuspendFunctionObjCExport) Enum.valueOf(UnitSuspendFunctionObjCExport.class, str);
    }

    public static UnitSuspendFunctionObjCExport[] values() {
        return (UnitSuspendFunctionObjCExport[]) $VALUES.clone();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/UnitSuspendFunctionObjCExport$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/UnitSuspendFunctionObjCExport;", "getDEFAULT", "()Lorg/jetbrains/kotlin/config/nativeBinaryOptions/UnitSuspendFunctionObjCExport;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final UnitSuspendFunctionObjCExport getDEFAULT() {
            return UnitSuspendFunctionObjCExport.DEFAULT;
        }

        private Companion() {
        }
    }
}
