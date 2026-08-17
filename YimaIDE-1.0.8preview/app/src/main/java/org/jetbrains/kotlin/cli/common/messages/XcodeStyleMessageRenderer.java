package org.jetbrains.kotlin.cli.common.messages;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/XcodeStyleMessageRenderer;", "Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;", "<init>", "()V", "render", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "message", "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "renderPreamble", "renderUsage", "usage", "renderConclusion", "getName", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class XcodeStyleMessageRenderer implements MessageRenderer {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CompilerMessageSeverity.values().length];
            try {
                iArr[CompilerMessageSeverity.WARNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CompilerMessageSeverity.STRONG_WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CompilerMessageSeverity.FIXED_WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CompilerMessageSeverity.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CompilerMessageSeverity.EXCEPTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CompilerMessageSeverity.LOGGING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CompilerMessageSeverity.OUTPUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CompilerMessageSeverity.INFO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String getName() {
        return "XcodeStyle";
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String render(CompilerMessageSeverity severity, String message, CompilerMessageSourceLocation location) {
        String str;
        severity.getClass();
        message.getClass();
        switch (WhenMappings.$EnumSwitchMapping$0[severity.ordinal()]) {
            case 1:
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                str = "warning";
                break;
            case 4:
            case 5:
                str = "error";
                break;
            case 6:
            case 7:
            case 8:
                str = "note";
                break;
            default:
                bu8.a();
                return null;
        }
        StringBuilder sb = new StringBuilder();
        if (location != null) {
            sb.append(location.getPath() + ':' + location.getLine() + ':' + location.getColumn() + ": ");
        }
        sb.append(str + ": " + message);
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String renderConclusion() {
        return Argument.Delimiters.none;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String renderPreamble() {
        return Argument.Delimiters.none;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String renderUsage(String usage) {
        usage.getClass();
        return usage;
    }
}
