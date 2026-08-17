package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.internal.PlatformImplementationsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0087\u0080\u0004b\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u001a(\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002H\u0087\u0080\u0004b\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016b\u0002\b\u0017\"%\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\u00028FX\u0086\u0084\b¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"3\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019*\u00020\u00028FX\u0087\u0084\br\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013¢\u0006\f\u0012\u0004\b\u001a\u0010\f\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"printStackTrace", "", "", "Lkotlin/internal/InlineOnly;", "writer", "Ljava/io/PrintWriter;", "stream", "Ljava/io/PrintStream;", "stackTrace", "", "Ljava/lang/StackTraceElement;", "getStackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "stackTraceToString", "", "Lkotlin/SinceKotlin;", "version", "1.4", "addSuppressed", "exception", "1.1", "Lkotlin/internal/HidesMembers;", "suppressedExceptions", "", "getSuppressedExceptions$annotations", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/ExceptionsKt")
public class ExceptionsKt__ExceptionsKt {
    public static void addSuppressed(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        th.getClass();
        th2.getClass();
        if (th != th2) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, th2);
        }
    }

    public static final StackTraceElement[] getStackTrace(Throwable th) {
        th.getClass();
        StackTraceElement[] stackTrace = th.getStackTrace();
        stackTrace.getClass();
        return stackTrace;
    }

    public static /* synthetic */ void getStackTrace$annotations(Throwable th) {
    }

    public static List<Throwable> getSuppressedExceptions(Throwable th) {
        th.getClass();
        return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed(th);
    }

    public static /* synthetic */ void getSuppressedExceptions$annotations(Throwable th) {
    }

    private static final void printStackTrace(Throwable th, PrintWriter printWriter) {
        th.getClass();
        printWriter.getClass();
        th.printStackTrace(printWriter);
    }

    public static String stackTraceToString(Throwable th) {
        th.getClass();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String string = stringWriter.toString();
        string.getClass();
        return string;
    }

    private static final void printStackTrace(Throwable th) {
        th.getClass();
        th.printStackTrace();
    }

    private static final void printStackTrace(Throwable th, PrintStream printStream) {
        th.getClass();
        printStream.getClass();
        th.printStackTrace(printStream);
    }
}
