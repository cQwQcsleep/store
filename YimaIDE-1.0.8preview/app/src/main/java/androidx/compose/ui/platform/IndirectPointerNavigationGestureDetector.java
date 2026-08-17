package androidx.compose.ui.platform;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.input.indirect.AndroidIndirectPointerEvent_androidKt;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u0007R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/platform/IndirectPointerNavigationGestureDetector;", "", "context", "Landroid/content/Context;", "onMoveFocus", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusDirection;", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "getPrimaryDirectionalMotionAxis-nZO2Niw", "()I", "setPrimaryDirectionalMotionAxis-WQKaTuc", "(I)V", "I", "ignoreCurrentGestureStream", "", "gestureDetector", "Landroid/view/GestureDetector;", "onIndirectPointerEvent", "indirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "isConsumed", "cancelCurrentEventStream", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class IndirectPointerNavigationGestureDetector {
    public static final int $stable = 8;
    private final GestureDetector gestureDetector;
    private boolean ignoreCurrentGestureStream;
    private final Function1<FocusDirection, Unit> onMoveFocus;
    private int primaryDirectionalMotionAxis = IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3965getNonenZO2Niw();

    /* JADX WARN: Multi-variable type inference failed */
    public IndirectPointerNavigationGestureDetector(Context context, Function1<? super FocusDirection, Unit> function1) {
        this.onMoveFocus = function1;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() { // from class: androidx.compose.ui.platform.IndirectPointerNavigationGestureDetector$gestureDetector$1
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (this.this$0.ignoreCurrentGestureStream) {
                    return true;
                }
                int primaryDirectionalMotionAxis = this.this$0.getPrimaryDirectionalMotionAxis();
                IndirectPointerEventPrimaryDirectionalMotionAxis.Companion companion = IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE;
                if (IndirectPointerEventPrimaryDirectionalMotionAxis.m3961equalsimpl0(primaryDirectionalMotionAxis, companion.m3966getXnZO2Niw())) {
                    if (Math.abs(velocityX) > Math.abs(velocityY)) {
                        this.this$0.onMoveFocus.invoke(FocusDirection.m2756boximpl(velocityX > 0.0f ? FocusDirection.INSTANCE.m2767getNextdhqQ8s() : FocusDirection.INSTANCE.m2768getPreviousdhqQ8s()));
                    }
                } else if (IndirectPointerEventPrimaryDirectionalMotionAxis.m3961equalsimpl0(this.this$0.getPrimaryDirectionalMotionAxis(), companion.m3967getYnZO2Niw()) && Math.abs(velocityY) > Math.abs(velocityX)) {
                    this.this$0.onMoveFocus.invoke(FocusDirection.m2756boximpl(velocityY > 0.0f ? FocusDirection.INSTANCE.m2767getNextdhqQ8s() : FocusDirection.INSTANCE.m2768getPreviousdhqQ8s()));
                }
                return true;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return true;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent e) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    public final void cancelCurrentEventStream() {
        this.primaryDirectionalMotionAxis = IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3965getNonenZO2Niw();
        this.ignoreCurrentGestureStream = true;
    }

    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxis-nZO2Niw, reason: not valid java name and from getter */
    public final int getPrimaryDirectionalMotionAxis() {
        return this.primaryDirectionalMotionAxis;
    }

    public final boolean onIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent, boolean isConsumed) {
        MotionEvent nativeEvent = AndroidIndirectPointerEvent_androidKt.getNativeEvent(indirectPointerEvent);
        int action = nativeEvent.getAction();
        if (action == 0) {
            this.primaryDirectionalMotionAxis = indirectPointerEvent.getPrimaryDirectionalMotionAxis();
            this.ignoreCurrentGestureStream = false;
        } else if ((action == 1 || action == 2) && isConsumed) {
            cancelCurrentEventStream();
        }
        return this.gestureDetector.onTouchEvent(nativeEvent);
    }

    /* JADX INFO: renamed from: setPrimaryDirectionalMotionAxis-WQKaTuc, reason: not valid java name */
    public final void m5172setPrimaryDirectionalMotionAxisWQKaTuc(int i) {
        this.primaryDirectionalMotionAxis = i;
    }
}
