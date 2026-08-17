package org.jetbrains.kotlin.kapt3.diagnostic;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001\fB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/kapt3/diagnostic/KaptError;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "kind", "Lorg/jetbrains/kotlin/kapt3/diagnostic/KaptError$Kind;", "<init>", "(Lorg/jetbrains/kotlin/kapt3/diagnostic/KaptError$Kind;)V", "cause", "", "(Lorg/jetbrains/kotlin/kapt3/diagnostic/KaptError$Kind;Ljava/lang/Throwable;)V", "getKind", "()Lorg/jetbrains/kotlin/kapt3/diagnostic/KaptError$Kind;", "Kind", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KaptError extends RuntimeException {
    private final Kind kind;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/kapt3/diagnostic/KaptError$Kind;", "", "message", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "EXCEPTION", "ERROR_RAISED", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Kind {
        EXCEPTION("Exception while annotation processing"),
        ERROR_RAISED("Error while annotation processing");

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String message;

        Kind(String str) {
            this.message = str;
        }

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KaptError(Kind kind, Throwable th) {
        super(kind.getMessage(), th);
        kind.getClass();
        th.getClass();
        this.kind = kind;
    }

    public final Kind getKind() {
        return this.kind;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KaptError(Kind kind) {
        super(kind.getMessage());
        kind.getClass();
        this.kind = kind;
    }
}
