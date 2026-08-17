package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.library.KotlinAbiVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel, still in use, count: 1, list:
  (r0v1 org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel) from 0x002b: SPUT (r0v1 org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel) org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel.LATEST_STABLE org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel
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
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0000J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", Argument.Delimiters.none, "major", Argument.Delimiters.none, "minor", "<init>", "(Ljava/lang/String;III)V", "getMajor", "()I", "getMinor", "ABI_LEVEL_2_3", "ABI_LEVEL_2_4", "toString", Argument.Delimiters.none, "toAbiVersionForManifest", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "isAtLeast", Argument.Delimiters.none, "other", "previous", "Companion", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibAbiCompatibilityLevel {
    ABI_LEVEL_2_3(2, 3),
    ABI_LEVEL_2_4(2, 4);

    private final int major;
    private final int minor;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KlibAbiCompatibilityLevel LATEST_STABLE = new KlibAbiCompatibilityLevel(2, 4);

    static {
    }

    private KlibAbiCompatibilityLevel(int i, int i2) {
        super(str, i);
        this.major = i;
        this.minor = i2;
    }

    public static EnumEntries<KlibAbiCompatibilityLevel> getEntries() {
        return $ENTRIES;
    }

    public static KlibAbiCompatibilityLevel valueOf(String str) {
        return (KlibAbiCompatibilityLevel) Enum.valueOf(KlibAbiCompatibilityLevel.class, str);
    }

    public static KlibAbiCompatibilityLevel[] values() {
        return (KlibAbiCompatibilityLevel[]) $VALUES.clone();
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final boolean isAtLeast(KlibAbiCompatibilityLevel other) {
        other.getClass();
        int i = this.major;
        int i2 = other.major;
        if (i <= i2) {
            return i == i2 && this.minor >= other.minor;
        }
        return true;
    }

    public final KlibAbiCompatibilityLevel previous() {
        if (ordinal() > 0) {
            return (KlibAbiCompatibilityLevel) getEntries().get(ordinal() - 1);
        }
        return null;
    }

    public final KotlinAbiVersion toAbiVersionForManifest() {
        return new KotlinAbiVersion(this.major, this.minor, 0);
    }

    @Override // java.lang.Enum
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append('.');
        sb.append(this.minor);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel$Companion;", Argument.Delimiters.none, "<init>", "()V", "LATEST_STABLE", "Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "getLATEST_STABLE", "()Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KlibAbiCompatibilityLevel getLATEST_STABLE() {
            return KlibAbiCompatibilityLevel.LATEST_STABLE;
        }

        private Companion() {
        }
    }
}
