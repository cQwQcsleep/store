package kotlin.coroutines.cancellation;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00060\u0001j\u0002`\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0087\u0088\u0004b\u0002\b\nb\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u001a*\u0010\u0000\u001a\u00060\u0001j\u0002`\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0087\u0088\u0004b\u0002\b\nb\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004*\u001a\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004¨\u0006\u000b"}, d2 = {"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlin/SinceKotlin;", "version", "1.4", "Lkotlin/coroutines/cancellation/CancellationException;", "message", "", "cause", "", "Lkotlin/internal/InlineOnly;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CancellationExceptionKt {
    private static final CancellationException CancellationException(Throwable th) {
        CancellationException cancellationException = new CancellationException(th != null ? String.valueOf(th) : null);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public static /* synthetic */ void CancellationException$annotations() {
    }

    private static final CancellationException CancellationException(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }
}
