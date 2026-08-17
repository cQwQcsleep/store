package org.jetbrains.kotlin.config;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.platform.TargetPlatformVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 org.jetbrains.kotlin.config.JvmTarget, still in use, count: 1, list:
  (r0v1 org.jetbrains.kotlin.config.JvmTarget) from 0x0138: SPUT (r0v1 org.jetbrains.kotlin.config.JvmTarget) org.jetbrains.kotlin.config.JvmTarget.DEFAULT org.jetbrains.kotlin.config.JvmTarget
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
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001d\b\u0086\u0081\u0002\u0018\u0000 \"2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\"B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010!\u001a\u00020\u0004H\u0096\u0080\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmTarget;", "Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "majorVersion", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;I)V", "getDescription", "()Ljava/lang/String;", "getMajorVersion", "()I", "JVM_1_6", "JVM_1_8", "JVM_9", "JVM_10", "JVM_11", "JVM_12", "JVM_13", "JVM_14", "JVM_15", "JVM_16", "JVM_17", "JVM_18", "JVM_19", "JVM_20", "JVM_21", "JVM_22", "JVM_23", "JVM_24", "JVM_25", "JVM_26", "toString", "Companion", "org.jetbrains.kotlin:language.targets.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmTarget implements TargetPlatformVersion {
    JVM_1_6("1.6", 50),
    JVM_1_8("1.8", 52),
    JVM_9("9", 53),
    JVM_10("10", 54),
    JVM_11("11", 55),
    JVM_12("12", 56),
    JVM_13("13", 57),
    JVM_14("14", 58),
    JVM_15("15", 59),
    JVM_16("16", 60),
    JVM_17("17", 61),
    JVM_18("18", 62),
    JVM_19("19", 63),
    JVM_20("20", 64),
    JVM_21("21", 65),
    JVM_22("22", 66),
    JVM_23("23", 67),
    JVM_24("24", 68),
    JVM_25("25", 69),
    JVM_26("26", 70);

    private final String description;
    private final int majorVersion;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final JvmTarget DEFAULT = new JvmTarget("1.8", 52);

    static {
    }

    private JvmTarget(String str, int i) {
        super(str, i);
        this.description = str;
        this.majorVersion = i;
    }

    @JvmStatic
    public static final JvmTarget fromString(String str) {
        return INSTANCE.fromString(str);
    }

    public static EnumEntries<JvmTarget> getEntries() {
        return $ENTRIES;
    }

    public static JvmTarget valueOf(String str) {
        return (JvmTarget) Enum.valueOf(JvmTarget.class, str);
    }

    public static JvmTarget[] values() {
        return (JvmTarget[]) $VALUES.clone();
    }

    public String getDescription() {
        return this.description;
    }

    public final int getMajorVersion() {
        return this.majorVersion;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getDescription();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmTarget$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/config/JvmTarget;", "fromString", "string", Argument.Delimiters.none, "getDescription", "majorVersion", Argument.Delimiters.none, "supportedValues", Argument.Delimiters.none, "org.jetbrains.kotlin:language.targets.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JvmTarget fromString(String string) {
            Object next;
            string.getClass();
            Iterator it = JvmTarget.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((JvmTarget) next).getDescription(), string)) {
                    return (JvmTarget) next;
                }
            }
            next = null;
            return (JvmTarget) next;
        }

        public final String getDescription(int majorVersion) {
            String str;
            Object next;
            String description;
            Iterator it = JvmTarget.getEntries().iterator();
            do {
                str = null;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (((JvmTarget) next).getMajorVersion() != majorVersion);
            JvmTarget jvmTarget = (JvmTarget) next;
            if (jvmTarget != null && (description = jvmTarget.getDescription()) != null) {
                str = description;
            } else if (majorVersion == 51) {
                str = "1.7";
            }
            if (str != null) {
                return "JVM target ".concat(str);
            }
            return "JVM bytecode version " + majorVersion;
        }

        public final List<JvmTarget> supportedValues() {
            return CollectionsKt.minus(JvmTarget.getEntries(), JvmTarget.JVM_1_6);
        }

        private Companion() {
        }
    }
}
