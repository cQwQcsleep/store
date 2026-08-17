package org.jetbrains.kotlin.config;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v3 org.jetbrains.kotlin.config.JVMAssertionsMode, still in use, count: 1, list:
  (r0v3 org.jetbrains.kotlin.config.JVMAssertionsMode) from 0x0044: SPUT (r0v3 org.jetbrains.kotlin.config.JVMAssertionsMode) org.jetbrains.kotlin.config.JVMAssertionsMode.DEFAULT org.jetbrains.kotlin.config.JVMAssertionsMode
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
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "ALWAYS_ENABLE", "ALWAYS_DISABLE", "JVM", "LEGACY", "Companion", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JVMAssertionsMode {
    ALWAYS_ENABLE("always-enable"),
    ALWAYS_DISABLE("always-disable"),
    JVM("jvm"),
    LEGACY("legacy");

    private final String description;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final JVMAssertionsMode DEFAULT = new JVMAssertionsMode("legacy");

    static {
    }

    private JVMAssertionsMode(String str) {
        super(str, i);
        this.description = str;
    }

    @JvmStatic
    public static final JVMAssertionsMode fromString(String str) {
        return INSTANCE.fromString(str);
    }

    @JvmStatic
    public static final JVMAssertionsMode fromStringOrNull(String str) {
        return INSTANCE.fromStringOrNull(str);
    }

    public static EnumEntries<JVMAssertionsMode> getEntries() {
        return $ENTRIES;
    }

    public static JVMAssertionsMode valueOf(String str) {
        return (JVMAssertionsMode) Enum.valueOf(JVMAssertionsMode.class, str);
    }

    public static JVMAssertionsMode[] values() {
        return (JVMAssertionsMode[]) $VALUES.clone();
    }

    public final String getDescription() {
        return this.description;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u0012\u0010\t\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/JVMAssertionsMode$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/config/JVMAssertionsMode;", "fromStringOrNull", "string", Argument.Delimiters.none, "fromString", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JVMAssertionsMode fromString(String string) {
            JVMAssertionsMode jVMAssertionsModeFromStringOrNull = fromStringOrNull(string);
            return jVMAssertionsModeFromStringOrNull == null ? JVMAssertionsMode.DEFAULT : jVMAssertionsModeFromStringOrNull;
        }

        @JvmStatic
        public final JVMAssertionsMode fromStringOrNull(String string) {
            Object next;
            Iterator it = JVMAssertionsMode.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((JVMAssertionsMode) next).getDescription(), string)) {
                    return (JVMAssertionsMode) next;
                }
            }
            next = null;
            return (JVMAssertionsMode) next;
        }

        private Companion() {
        }
    }
}
