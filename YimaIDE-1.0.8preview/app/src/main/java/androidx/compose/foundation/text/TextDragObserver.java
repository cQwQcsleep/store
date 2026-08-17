package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionAdjustment;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H&J\u001f\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H&¢\u0006\u0004\b\u0011\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/TextDragObserver;", "", "onDown", "", "point", "Landroidx/compose/ui/geometry/Offset;", "onDown-k-4lQ0M", "(J)V", "onUp", "onStart", "startPoint", "selectionAdjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "onStart-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "onDrag", "delta", "onDrag-k-4lQ0M", "onStop", "onCancel", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface TextDragObserver {
    void onCancel();

    /* JADX INFO: renamed from: onDown-k-4lQ0M, reason: not valid java name */
    void mo1409onDownk4lQ0M(long point);

    /* JADX INFO: renamed from: onDrag-k-4lQ0M, reason: not valid java name */
    void mo1410onDragk4lQ0M(long delta);

    /* JADX INFO: renamed from: onStart-3MmeM6k, reason: not valid java name */
    void mo1411onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment);

    void onStop();

    void onUp();
}
