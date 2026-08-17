package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0011\u0010\u000e\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/Severity;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "INFO", "ERROR", "WARNING", "FIXED_WARNING", "STRONG_WARNING", "toCompilerMessageSeverity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "isErrorWhenWError", Argument.Delimiters.none, "()Z", "isError", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum Severity {
    INFO,
    ERROR,
    WARNING,
    FIXED_WARNING,
    STRONG_WARNING;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Severity.values().length];
            try {
                iArr[Severity.INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Severity.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Severity.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Severity.STRONG_WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Severity.FIXED_WARNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<Severity> getEntries() {
        return $ENTRIES;
    }

    public final boolean isError() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        if (i == 3 || i == 4 || i == 5) {
            return false;
        }
        bu8.a();
        return false;
    }

    public final boolean isErrorWhenWError() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1 || i == 2) {
            return false;
        }
        if (i == 3 || i == 4) {
            return true;
        }
        if (i == 5) {
            return false;
        }
        bu8.a();
        return false;
    }

    public final CompilerMessageSeverity toCompilerMessageSeverity() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return CompilerMessageSeverity.INFO;
        }
        if (i == 2) {
            return CompilerMessageSeverity.ERROR;
        }
        if (i == 3) {
            return CompilerMessageSeverity.WARNING;
        }
        if (i == 4) {
            return CompilerMessageSeverity.STRONG_WARNING;
        }
        if (i == 5) {
            return CompilerMessageSeverity.FIXED_WARNING;
        }
        bu8.a();
        return null;
    }
}
