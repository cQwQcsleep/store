package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 org.jetbrains.kotlin.config.nativeBinaryOptions.AndroidProgramType, still in use, count: 1, list:
  (r0v0 org.jetbrains.kotlin.config.nativeBinaryOptions.AndroidProgramType) from 0x002a: SPUT (r0v0 org.jetbrains.kotlin.config.nativeBinaryOptions.AndroidProgramType) org.jetbrains.kotlin.config.nativeBinaryOptions.AndroidProgramType.Default org.jetbrains.kotlin.config.nativeBinaryOptions.AndroidProgramType
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
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u001b\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/AndroidProgramType;", Argument.Delimiters.none, "konanMainOverride", Argument.Delimiters.none, "consolePrintsToLogcat", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getKonanMainOverride", "()Ljava/lang/String;", "getConsolePrintsToLogcat", "()Z", "Standalone", "NativeActivity", "Companion", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AndroidProgramType {
    Standalone("Konan_main_standalone", false),
    NativeActivity(null, true);

    private final boolean consolePrintsToLogcat;
    private final String konanMainOverride;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AndroidProgramType Default = new AndroidProgramType("Konan_main_standalone", false);

    static {
    }

    private AndroidProgramType(String str, boolean z) {
        super(str, i);
        this.konanMainOverride = str;
        this.consolePrintsToLogcat = z;
    }

    public static EnumEntries<AndroidProgramType> getEntries() {
        return $ENTRIES;
    }

    public static AndroidProgramType valueOf(String str) {
        return (AndroidProgramType) Enum.valueOf(AndroidProgramType.class, str);
    }

    public static AndroidProgramType[] values() {
        return (AndroidProgramType[]) $VALUES.clone();
    }

    public final boolean getConsolePrintsToLogcat() {
        return this.consolePrintsToLogcat;
    }

    public final String getKonanMainOverride() {
        return this.konanMainOverride;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/AndroidProgramType$Companion;", Argument.Delimiters.none, "<init>", "()V", "Default", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/AndroidProgramType;", "getDefault", "()Lorg/jetbrains/kotlin/config/nativeBinaryOptions/AndroidProgramType;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AndroidProgramType getDefault() {
            return AndroidProgramType.Default;
        }

        private Companion() {
        }
    }
}
