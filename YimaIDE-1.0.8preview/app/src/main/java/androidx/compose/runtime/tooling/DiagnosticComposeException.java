package androidx.compose.runtime.tooling;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J@\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u0010\"\u0006\b\u0001\u0010\u000f\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00100\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000f0\u0013H\u0082\b¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Landroidx/compose/runtime/tooling/DiagnosticComposeException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "trace", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "<init>", "(Landroidx/compose/runtime/tooling/ComposeStackTrace;)V", "fillInStackTrace", "", "message", "", "getMessage", "()Ljava/lang/String;", "mapToArray", "", "R", "T", "", "map", "Lkotlin/Function1;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)[Ljava/lang/Object;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DiagnosticComposeException extends RuntimeException {
    public static final int $stable = 8;
    private final ComposeStackTrace trace;

    public DiagnosticComposeException(ComposeStackTrace composeStackTrace) {
        this.trace = composeStackTrace;
        if (composeStackTrace.getHasSourceInformation()) {
            return;
        }
        List<ComposeStackTraceFrame> listFilterInternalFramesByGroupKey = ComposeStackTraceKt.filterInternalFramesByGroupKey(composeStackTrace);
        int size = listFilterInternalFramesByGroupKey.size();
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[size];
        for (int i = 0; i < size; i++) {
            stackTraceElementArr[i] = new StackTraceElement("$$compose", "m$" + listFilterInternalFramesByGroupKey.get(i).getGroupKey(), "SourceFile", 1);
        }
        setStackTrace(stackTraceElementArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T, R> R[] mapToArray(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        int size = list.size();
        Intrinsics.reifiedOperationMarker(0, "R");
        R[] rArr = (R[]) new Object[size];
        for (int i = 0; i < size; i++) {
            rArr[i] = function1.invoke(list.get(i));
        }
        return rArr;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (!this.trace.getHasSourceInformation()) {
            return "Composition stack when thrown:";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Composition stack when thrown:\n");
        ComposeStackTraceKt.appendStackTrace(sb, this.trace);
        return sb.toString();
    }
}
