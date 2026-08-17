package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00060\u0001j\u0002`\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0005\u001a\u00020\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u000e\u001a\u00020\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/util/FileAnalysisException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "path", "", "cause", "", "lineAndOffset", "Lkotlin/Pair;", "", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Lkotlin/Pair;)V", "getCause", "()Ljava/lang/Throwable;", "message", "getMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class FileAnalysisException extends Exception {
    private final Throwable cause;
    private final Pair<Integer, Integer> lineAndOffset;
    private final String path;

    public FileAnalysisException(String str, Throwable th, Pair<Integer, Integer> pair) {
        str.getClass();
        th.getClass();
        this.path = str;
        this.cause = th;
        this.lineAndOffset = pair;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Pair<Integer, Integer> pair = this.lineAndOffset;
        if (pair == null) {
            return "Somewhere in file " + this.path + ": " + AnalysisExceptionsKt.getClassNameAndMessage(getCause());
        }
        return "While analysing " + this.path + ':' + (((Number) pair.component1()).intValue() + 1) + ':' + (((Number) pair.component2()).intValue() + 1) + ": " + AnalysisExceptionsKt.getClassNameAndMessage(getCause());
    }

    public /* synthetic */ FileAnalysisException(String str, Throwable th, Pair pair, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, th, (i & 4) != 0 ? null : pair);
    }
}
