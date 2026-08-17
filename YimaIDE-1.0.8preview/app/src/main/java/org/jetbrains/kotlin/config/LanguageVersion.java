package org.jetbrains.kotlin.config;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.utils.DescriptionAware;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v12 org.jetbrains.kotlin.config.LanguageVersion, still in use, count: 1, list:
  (r1v12 org.jetbrains.kotlin.config.LanguageVersion) from 0x00c0: SPUT (r1v12 org.jetbrains.kotlin.config.LanguageVersion) org.jetbrains.kotlin.config.LanguageVersion.FIRST_NON_DEPRECATED org.jetbrains.kotlin.config.LanguageVersion
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
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u0000 (2\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003:\u0001(B\u0019\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010'\u001a\u00020$H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0014\u0010!\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001eR\u0014\u0010\"\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0014\u0010#\u001a\u00020$X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&j\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageVersion;", "Lorg/jetbrains/kotlin/utils/DescriptionAware;", "Lorg/jetbrains/kotlin/config/LanguageOrApiVersion;", Argument.Delimiters.none, "major", Argument.Delimiters.none, "minor", "<init>", "(Ljava/lang/String;III)V", "getMajor", "()I", "getMinor", "KOTLIN_1_0", "KOTLIN_1_1", "KOTLIN_1_2", "KOTLIN_1_3", "KOTLIN_1_4", "KOTLIN_1_5", "KOTLIN_1_6", "KOTLIN_1_7", "KOTLIN_1_8", "KOTLIN_1_9", "KOTLIN_2_0", "KOTLIN_2_1", "KOTLIN_2_2", "KOTLIN_2_3", "KOTLIN_2_4", "KOTLIN_2_5", "isStable", Argument.Delimiters.none, "()Z", "usesK2", "getUsesK2", "isDeprecated", "isUnsupported", "versionString", Argument.Delimiters.none, "getVersionString", "()Ljava/lang/String;", "toString", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageVersion implements LanguageOrApiVersion, DescriptionAware {
    KOTLIN_1_0(1, 0),
    KOTLIN_1_1(1, 1),
    KOTLIN_1_2(1, 2),
    KOTLIN_1_3(1, 3),
    KOTLIN_1_4(1, 4),
    KOTLIN_1_5(1, 5),
    KOTLIN_1_6(1, 6),
    KOTLIN_1_7(1, 7),
    KOTLIN_1_8(1, 8),
    KOTLIN_1_9(1, 9),
    KOTLIN_2_0(2, 0),
    KOTLIN_2_1(2, 1),
    KOTLIN_2_2(2, 2),
    KOTLIN_2_3(2, 3),
    KOTLIN_2_4(2, 4),
    KOTLIN_2_5(2, 5);

    private static final /* synthetic */ EnumEntries $ENTRIES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final LanguageVersion FIRST_API_SUPPORTED;
    public static final LanguageVersion FIRST_NON_DEPRECATED;
    public static final LanguageVersion FIRST_SUPPORTED;
    public static final LanguageVersion LATEST_STABLE;
    private final int major;
    private final int minor;
    private final String versionString;

    static {
        LanguageVersion languageVersion = KOTLIN_2_0;
        $ENTRIES = EnumEntriesKt.enumEntries(values());
        INSTANCE = new Companion(null);
        FIRST_API_SUPPORTED = languageVersion;
        FIRST_SUPPORTED = languageVersion;
        FIRST_NON_DEPRECATED = new LanguageVersion(2, 2);
        LATEST_STABLE = new LanguageVersion(2, 4);
    }

    private LanguageVersion(int i, int i2) {
        super(str, i);
        this.major = i;
        this.minor = i2;
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append('.');
        sb.append(i2);
        this.versionString = sb.toString();
    }

    @JvmStatic
    public static final LanguageVersion fromFullVersionString(String str) {
        return INSTANCE.fromFullVersionString(str);
    }

    @JvmStatic
    public static final LanguageVersion fromVersionString(String str) {
        return INSTANCE.fromVersionString(str);
    }

    public static EnumEntries<LanguageVersion> getEntries() {
        return $ENTRIES;
    }

    public static LanguageVersion valueOf(String str) {
        return (LanguageVersion) Enum.valueOf(LanguageVersion.class, str);
    }

    public static LanguageVersion[] values() {
        return (LanguageVersion[]) $VALUES.clone();
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final boolean getUsesK2() {
        return compareTo(KOTLIN_2_0) >= 0;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public String getVersionString() {
        return this.versionString;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public boolean isDeprecated() {
        return FIRST_SUPPORTED.compareTo(this) <= 0 && compareTo(FIRST_NON_DEPRECATED) < 0;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public boolean isStable() {
        return compareTo(LATEST_STABLE) <= 0;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public boolean isUnsupported() {
        return compareTo(FIRST_SUPPORTED) < 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getVersionString();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageVersion$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromVersionString", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "str", Argument.Delimiters.none, "fromFullVersionString", "FIRST_API_SUPPORTED", "FIRST_SUPPORTED", "FIRST_NON_DEPRECATED", "LATEST_STABLE", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final LanguageVersion fromFullVersionString(String str) {
            str.getClass();
            List listSplit$default = StringsKt.split$default(str, new String[]{".", "-"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() < 2) {
                return null;
            }
            return LanguageVersion.INSTANCE.fromVersionString(((String) listSplit$default.get(0)) + '.' + ((String) listSplit$default.get(1)));
        }

        @JvmStatic
        public final LanguageVersion fromVersionString(String str) {
            Object next;
            Iterator it = LanguageVersion.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((LanguageVersion) next).getVersionString(), str)) {
                    return (LanguageVersion) next;
                }
            }
            next = null;
            return (LanguageVersion) next;
        }

        private Companion() {
        }
    }
}
