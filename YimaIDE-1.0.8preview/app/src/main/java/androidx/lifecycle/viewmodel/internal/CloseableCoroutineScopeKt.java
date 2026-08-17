package androidx.lifecycle.viewmodel.internal;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"VIEW_MODEL_SCOPE_KEY", "", "createViewModelScope", "Landroidx/lifecycle/viewmodel/internal/CloseableCoroutineScope;", "asCloseable", "Lkotlinx/coroutines/CoroutineScope;", "lifecycle-viewmodel_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CloseableCoroutineScopeKt {
    public static final String VIEW_MODEL_SCOPE_KEY = "androidx.lifecycle.viewmodel.internal.ViewModelCoroutineScope.JOB_KEY";

    public static final CloseableCoroutineScope asCloseable(CoroutineScope coroutineScope) {
        coroutineScope.getClass();
        return new CloseableCoroutineScope(coroutineScope);
    }

    public static final CloseableCoroutineScope createViewModelScope() {
        MainCoroutineDispatcher immediate;
        try {
            immediate = Dispatchers.getMain().getImmediate();
        } catch (NotImplementedError unused) {
            immediate = EmptyCoroutineContext.INSTANCE;
        } catch (IllegalStateException unused2) {
            immediate = EmptyCoroutineContext.INSTANCE;
        }
        return new CloseableCoroutineScope(immediate.plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    }
}
