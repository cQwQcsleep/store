package androidx.compose.ui.layout;

import androidx.compose.runtime.ShouldPauseCallback;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u0082\u0001\u0001\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "", "isComplete", "", "()Z", "resume", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "apply", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "cancel", "", "Landroidx/compose/ui/layout/PausedPrecompositionImpl;", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SubcomposeLayoutState$PausedPrecomposition {
    SubcomposeLayoutState$PrecomposedSlotHandle apply();

    void cancel();

    boolean isComplete();

    boolean resume(ShouldPauseCallback shouldPause);
}
