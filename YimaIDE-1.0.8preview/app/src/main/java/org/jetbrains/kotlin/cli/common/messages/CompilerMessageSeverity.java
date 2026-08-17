package org.jetbrains.kotlin.cli.common.messages;

import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v6 org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity, still in use, count: 1, list:
  (r0v6 org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity) from 0x0064: INVOKE (r0v7 java.util.EnumSet<org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity>) = (r0v6 org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity) STATIC call: java.util.EnumSet.of(java.lang.Enum):java.util.EnumSet A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>):java.util.EnumSet<E extends java.lang.Enum<E>> (c)]
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
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "EXCEPTION", "ERROR", "STRONG_WARNING", "FIXED_WARNING", "WARNING", "INFO", "LOGGING", "OUTPUT", "isError", Argument.Delimiters.none, "()Z", "isWarning", "isRegularWarning", "presentableName", Argument.Delimiters.none, "getPresentableName", "()Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerMessageSeverity {
    EXCEPTION,
    ERROR,
    STRONG_WARNING,
    FIXED_WARNING,
    WARNING,
    INFO,
    LOGGING,
    OUTPUT;

    public static final EnumSet<CompilerMessageSeverity> VERBOSE;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CompilerMessageSeverity.values().length];
            try {
                iArr[CompilerMessageSeverity.EXCEPTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CompilerMessageSeverity.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CompilerMessageSeverity.STRONG_WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CompilerMessageSeverity.WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CompilerMessageSeverity.FIXED_WARNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CompilerMessageSeverity.INFO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CompilerMessageSeverity.LOGGING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CompilerMessageSeverity.OUTPUT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        EnumSet<CompilerMessageSeverity> enumSetOf = EnumSet.of(new CompilerMessageSeverity());
        enumSetOf.getClass();
        VERBOSE = enumSetOf;
    }

    private CompilerMessageSeverity() {
        super(str, i);
    }

    public static EnumEntries<CompilerMessageSeverity> getEntries() {
        return $ENTRIES;
    }

    public static CompilerMessageSeverity valueOf(String str) {
        return (CompilerMessageSeverity) Enum.valueOf(CompilerMessageSeverity.class, str);
    }

    public static CompilerMessageSeverity[] values() {
        return (CompilerMessageSeverity[]) $VALUES.clone();
    }

    public final String getPresentableName() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_EXCEPTION;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return "error";
            case 3:
            case 4:
            case 5:
                return "warning";
            case 6:
                return "info";
            case 7:
                return "logging";
            case 8:
                return "output";
            default:
                bu8.a();
                return null;
        }
    }

    public final boolean isError() {
        return this == EXCEPTION || this == ERROR;
    }

    public final boolean isRegularWarning() {
        return this == STRONG_WARNING || this == WARNING;
    }

    public final boolean isWarning() {
        return this == STRONG_WARNING || this == WARNING || this == FIXED_WARNING;
    }
}
