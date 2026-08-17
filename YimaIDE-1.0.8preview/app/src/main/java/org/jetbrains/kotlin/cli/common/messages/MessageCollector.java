package org.jetbrains.kotlin.cli.common.messages;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ\b\u0010\u0002\u001a\u00020\u0003H&J$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000b\u001a\u00020\fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", Argument.Delimiters.none, "clear", Argument.Delimiters.none, "report", "severity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "hasErrors", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface MessageCollector {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector$Companion;", Argument.Delimiters.none, "<init>", "()V", "NONE", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "getNONE", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final MessageCollector NONE = new MessageCollector() { // from class: org.jetbrains.kotlin.cli.common.messages.MessageCollector$Companion$NONE$1
            @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
            public void clear() {
            }

            @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
            public boolean hasErrors() {
                return false;
            }

            @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
            public void report(CompilerMessageSeverity severity, String message, CompilerMessageSourceLocation location) {
                severity.getClass();
                message.getClass();
            }
        };

        private Companion() {
        }

        public final MessageCollector getNONE() {
            return NONE;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void report$default(MessageCollector messageCollector, CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: report");
            return;
        }
        if ((i & 4) != 0) {
            compilerMessageSourceLocation = null;
        }
        messageCollector.report(compilerMessageSeverity, str, compilerMessageSourceLocation);
    }

    void clear();

    boolean hasErrors();

    void report(CompilerMessageSeverity severity, String message, CompilerMessageSourceLocation location);
}
