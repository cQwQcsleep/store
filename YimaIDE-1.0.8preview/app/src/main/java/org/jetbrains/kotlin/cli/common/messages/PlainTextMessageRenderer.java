package org.jetbrains.kotlin.cli.common.messages;

import java.util.EnumSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.internal.CLibrary;
import org.jetbrains.kotlin.cli.common.CompilerSystemProperties;
import org.jetbrains.kotlin.cli.common.PropertiesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.codegen.inline.SMAPKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\rH$J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/PlainTextMessageRenderer;", "Lorg/jetbrains/kotlin/cli/common/messages/MessageRenderer;", "colorEnabled", Argument.Delimiters.none, "<init>", "(Z)V", "renderPreamble", Argument.Delimiters.none, "render", "severity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "message", "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "getPath", "renderUsage", "usage", "renderConclusion", "enableColorsIfNeeded", Argument.Delimiters.none, "disableColorsIfNeeded", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PlainTextMessageRenderer implements MessageRenderer {
    private static final boolean COLOR_ENABLED;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<CompilerMessageSeverity> IMPORTANT_MESSAGE_SEVERITIES;
    private static final String LINE_SEPARATOR;
    private final boolean colorEnabled;

    /* JADX WARN: Code duplicated, block: B:11:0x002d  */
    /* JADX WARN: Code duplicated, block: B:12:0x002f  */
    /* JADX WARN: Code duplicated, block: B:9:0x0025  */
    static {
        boolean z;
        String value = CompilerSystemProperties.KOTLIN_COLORS_ENABLED_PROPERTY.getValue();
        if (!PropertiesKt.isWindows() && Intrinsics.areEqual("true", value)) {
            try {
                if (CLibrary.isatty(CLibrary.STDERR_FILENO) != 0) {
                    z = true;
                } else if (Intrinsics.areEqual(K2JsArgumentConstants.SOURCE_MAP_SOURCE_CONTENT_ALWAYS, value)) {
                    z = true;
                } else {
                    z = false;
                }
            } catch (UnsatisfiedLinkError unused) {
            }
        } else if (Intrinsics.areEqual(K2JsArgumentConstants.SOURCE_MAP_SOURCE_CONTENT_ALWAYS, value)) {
            z = true;
        } else {
            z = false;
        }
        COLOR_ENABLED = z;
        String strLineSeparator = System.lineSeparator();
        strLineSeparator.getClass();
        LINE_SEPARATOR = strLineSeparator;
        EnumSet enumSetOf = EnumSet.of(CompilerMessageSeverity.EXCEPTION, CompilerMessageSeverity.ERROR, CompilerMessageSeverity.STRONG_WARNING, CompilerMessageSeverity.WARNING, CompilerMessageSeverity.FIXED_WARNING);
        enumSetOf.getClass();
        IMPORTANT_MESSAGE_SEVERITIES = enumSetOf;
    }

    public /* synthetic */ PlainTextMessageRenderer(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? COLOR_ENABLED : z);
    }

    public final void disableColorsIfNeeded() {
        if (this.colorEnabled) {
            AnsiConsole.systemUninstall();
        }
    }

    public final void enableColorsIfNeeded() {
        if (this.colorEnabled) {
            AnsiConsole.systemInstall();
        }
    }

    public abstract String getPath(CompilerMessageSourceLocation location);

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageRenderer
    public String render(CompilerMessageSeverity severity, String message, CompilerMessageSourceLocation location) {
        severity.getClass();
        message.getClass();
        StringBuilder sb = new StringBuilder();
        int line = location != null ? location.getLine() : -1;
        int column = location != null ? location.getColumn() : -1;
        int lineEnd = location != null ? location.getLineEnd() : -1;
        int columnEnd = location != null ? location.getColumnEnd() : -1;
        String lineContent = location != null ? location.getLineContent() : null;
        String path = location != null ? getPath(location) : null;
        if (path != null) {
            sb.append(path);
            sb.append(":");
            if (line > 0) {
                sb.append(line);
                sb.append(":");
                if (column > 0) {
                    sb.append(column);
                    sb.append(":");
                }
            }
            sb.append(Argument.Delimiters.space);
        }
        if (this.colorEnabled) {
            Ansi ansiBold = Ansi.ansi().bold();
            Companion companion = INSTANCE;
            Ansi ansiReset = ansiBold.fg(companion.severityColor(severity)).a(severity.getPresentableName()).a(": ").reset();
            ansiReset.getClass();
            if (IMPORTANT_MESSAGE_SEVERITIES.contains(severity)) {
                ansiReset.bold();
            }
            String strDecapitalizeIfNeeded = companion.decapitalizeIfNeeded(message);
            int iIndexOf$default = StringsKt.indexOf$default(strDecapitalizeIfNeeded, LINE_SEPARATOR, 0, false, 6, (Object) null);
            if (iIndexOf$default < 0) {
                sb.append(ansiReset.a(strDecapitalizeIfNeeded).reset());
            } else {
                sb.append(ansiReset.a(strDecapitalizeIfNeeded.substring(0, iIndexOf$default)).reset().a(strDecapitalizeIfNeeded.substring(iIndexOf$default)));
            }
        } else {
            sb.append(severity.getPresentableName());
            sb.append(": ");
            sb.append(INSTANCE.decapitalizeIfNeeded(message));
        }
        if (lineContent != null && 1 <= column && column <= lineContent.length() + 1) {
            String str = LINE_SEPARATOR;
            sb.append(str);
            sb.append(lineContent);
            sb.append(str);
            sb.append(StringsKt.repeat(Argument.Delimiters.space, column - 1));
            if (lineEnd > line) {
                sb.append(StringsKt.repeat("^", (lineContent.length() - column) + 1));
            } else if (lineEnd != line || columnEnd <= column) {
                sb.append("^");
            } else {
                sb.append(StringsKt.repeat("^", columnEnd - column));
            }
        }
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

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/PlainTextMessageRenderer$Companion;", Argument.Delimiters.none, "<init>", "()V", "COLOR_ENABLED", Argument.Delimiters.none, "LINE_SEPARATOR", Argument.Delimiters.none, "IMPORTANT_MESSAGE_SEVERITIES", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "decapitalizeIfNeeded", "message", "severityColor", "Lorg/fusesource/jansi/Ansi$Color;", "severity", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {

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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String decapitalizeIfNeeded(String message) {
            if (StringsKt.startsWith$default(message, "Java", false, 2, (Object) null) || StringsKt.startsWith$default(message, SMAPKt.KOTLIN_STRATA_NAME, false, 2, (Object) null)) {
                return message;
            }
            return (message.length() >= 2 && Character.isUpperCase(message.charAt(0)) && Character.isUpperCase(message.charAt(1))) ? message : CapitalizeDecapitalizeKt.decapitalizeAsciiOnly(message);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Ansi.Color severityColor(CompilerMessageSeverity severity) {
            switch (WhenMappings.$EnumSwitchMapping$0[severity.ordinal()]) {
                case 1:
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    return Ansi.Color.RED;
                case 3:
                case 4:
                case 5:
                    return Ansi.Color.YELLOW;
                case 6:
                case 7:
                case 8:
                    return Ansi.Color.BLUE;
                default:
                    bu8.a();
                    return null;
            }
        }

        private Companion() {
        }
    }

    public PlainTextMessageRenderer(boolean z) {
        this.colorEnabled = z;
    }

    public PlainTextMessageRenderer() {
        this(false, 1, null);
    }
}
