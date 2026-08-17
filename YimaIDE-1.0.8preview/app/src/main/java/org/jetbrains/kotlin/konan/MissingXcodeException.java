package org.jetbrains.kotlin.konan;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/konan/MissingXcodeException;", "Lorg/jetbrains/kotlin/konan/KonanException;", "Lorg/jetbrains/kotlin/konan/KonanPendingCompilationError;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getMessage", "()Ljava/lang/String;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MissingXcodeException extends KonanException implements KonanPendingCompilationError {
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingXcodeException(String str, Throwable th) {
        super(str, th);
        str.getClass();
        this.message = str;
    }

    @Override // java.lang.Throwable, org.jetbrains.kotlin.konan.KonanPendingCompilationError
    public String getMessage() {
        return this.message;
    }

    public /* synthetic */ MissingXcodeException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th);
    }
}
