package org.jetbrains.kotlin.utils.repl;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/utils/repl/ReplEscapeType;", "", "<init>", "(Ljava/lang/String;I)V", "INITIAL_PROMPT", "HELP_PROMPT", "USER_OUTPUT", "REPL_RESULT", "READLINE_START", "READLINE_END", "REPL_INCOMPLETE", "COMPILE_ERROR", "RUNTIME_ERROR", "INTERNAL_ERROR", "ERRORS_REPORTED", "SUCCESS", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum ReplEscapeType {
    INITIAL_PROMPT,
    HELP_PROMPT,
    USER_OUTPUT,
    REPL_RESULT,
    READLINE_START,
    READLINE_END,
    REPL_INCOMPLETE,
    COMPILE_ERROR,
    RUNTIME_ERROR,
    INTERNAL_ERROR,
    ERRORS_REPORTED,
    SUCCESS;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<ReplEscapeType> getEntries() {
        return $ENTRIES;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/utils/repl/ReplEscapeType$Companion;", "", "<init>", "()V", "valueOfOrNull", "Lorg/jetbrains/kotlin/utils/repl/ReplEscapeType;", "string", "", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ReplEscapeType valueOfOrNull(String string) {
            string.getClass();
            try {
                return ReplEscapeType.valueOf(string);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        private Companion() {
        }
    }
}
