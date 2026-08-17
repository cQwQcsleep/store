package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H¦@¢\u0006\u0004\b\u0007\u0010\bJ*\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH¦@¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u000f\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H¦@¢\u0006\u0004\b\u0010\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "", "suggestSelectionForLongPressOrDoubleClick", "Landroidx/compose/ui/text/TextRange;", "text", "", "selection", "suggestSelectionForLongPressOrDoubleClick-pYaCw-w", "(Ljava/lang/CharSequence;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShowContextMenu", "", "secondaryClickLocation", "Landroidx/compose/ui/geometry/Offset;", "onShowContextMenu-_2OEclM", "(Ljava/lang/CharSequence;JLandroidx/compose/ui/geometry/Offset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShowSelectionToolbar", "onShowSelectionToolbar-Sb-Bc2M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PlatformSelectionBehaviors {
    /* JADX INFO: renamed from: onShowContextMenu-_2OEclM, reason: not valid java name */
    Object mo1747onShowContextMenu_2OEclM(CharSequence charSequence, long j, Offset offset, Continuation<? super Unit> continuation);

    /* JADX INFO: renamed from: onShowSelectionToolbar-Sb-Bc2M, reason: not valid java name */
    Object mo1748onShowSelectionToolbarSbBc2M(CharSequence charSequence, long j, Continuation<? super Unit> continuation);

    /* JADX INFO: renamed from: suggestSelectionForLongPressOrDoubleClick-pYaCw-w, reason: not valid java name */
    Object mo1749suggestSelectionForLongPressOrDoubleClickpYaCww(CharSequence charSequence, long j, Continuation<? super TextRange> continuation);
}
