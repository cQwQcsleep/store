package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.collection.MutableObjectList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b`\u0018\u00002\u00020\u0001J!\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\b\fJ7\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010H&¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0007H&¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH&J/\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b$\u0010%J\b\u0010 \u001a\u00020\u001cH&J\n\u0010&\u001a\u0004\u0018\u00010\u000bH&J\b\u0010'\u001a\u00020\u0007H&J\b\u0010(\u001a\u00020\u0007H&J'\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00070-H&¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+H&¢\u0006\u0004\b1\u00102J \u00103\u001a\u00020\u00072\u0006\u00104\u001a\u0002052\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00070-H&J\u0010\u00106\u001a\u00020\u00072\u0006\u00104\u001a\u000207H&J\b\u00108\u001a\u00020\u001cH&J\b\u00109\u001a\u00020\u001cH&J\u0010\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020\u0011H&J\u0010\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H&J\b\u0010=\u001a\u00020\u001cH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010>\u001a\b\u0012\u0004\u0012\u00020@0?X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0012\u0010C\u001a\u00020DX¦\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u001a\u0010G\u001a\u0004\u0018\u00010\u0011X¦\u000e¢\u0006\f\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u0018\u0010L\u001a\u00020\u0007X¦\u000e¢\u0006\f\u001a\u0004\bL\u0010M\"\u0004\bN\u0010Oø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006PÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/FocusOwner;", "Landroidx/compose/ui/focus/FocusManager;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "requestOwnerFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-7o62pno", "focusSearch", "focusedRect", "onFound", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusTargetNode;", "focusSearch-ULY8qGw", "(ILandroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "moveFocus", "wrapAroundForOneDimensionalFocus", "moveFocus-aToIllA", "(IZ)Z", "takeFocus", "takeFocus-aToIllA", "(ILandroidx/compose/ui/geometry/Rect;)Z", "releaseFocus", "", "clearFocus", "force", "refreshFocusEvents", "clearOwnerFocus", "clearFocus-I7lrPNg", "(ZZZI)Z", "resetFocus", "resetFocus-3ESFkO8", "(I)Z", "getFocusRect", "hasFocusableContent", "hasNonInteropFocusableContent", "dispatchKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onFocusedItem", "Lkotlin/Function0;", "dispatchKeyEvent-YhN2O0w", "(Landroid/view/KeyEvent;Lkotlin/jvm/functions/Function0;)Z", "dispatchInterceptedSoftKeyboardEvent", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "dispatchRotaryEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "dispatchIndirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "dispatchIndirectPointerCancel", "focusTargetAvailable", "scheduleInvalidation", "node", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "scheduleInvalidationForOwner", "listeners", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/focus/FocusListener;", "getListeners", "()Landroidx/collection/MutableObjectList;", "rootState", "Landroidx/compose/ui/focus/FocusState;", "getRootState", "()Landroidx/compose/ui/focus/FocusState;", "activeFocusTargetNode", "getActiveFocusTargetNode", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setActiveFocusTargetNode", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "isFocusCaptured", "()Z", "setFocusCaptured", "(Z)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface FocusOwner extends FocusManager {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: dispatchKeyEvent-YhN2O0w$default, reason: not valid java name */
    static /* synthetic */ boolean m2775dispatchKeyEventYhN2O0w$default(FocusOwner focusOwner, KeyEvent keyEvent, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: dispatchKeyEvent-YhN2O0w");
            return false;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.ui.focus.FocusOwner$dispatchKeyEvent$1
                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m2784invoke() {
                    return Boolean.FALSE;
                }
            };
        }
        return focusOwner.mo2778dispatchKeyEventYhN2O0w(keyEvent, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean dispatchRotaryEvent$default(FocusOwner focusOwner, RotaryScrollEvent rotaryScrollEvent, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: dispatchRotaryEvent");
            return false;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.compose.ui.focus.FocusOwner.dispatchRotaryEvent.1
                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m2785invoke() {
                    return Boolean.FALSE;
                }
            };
        }
        return focusOwner.dispatchRotaryEvent(rotaryScrollEvent, function0);
    }

    /* JADX INFO: renamed from: clearFocus-I7lrPNg, reason: not valid java name */
    boolean mo2776clearFocusI7lrPNg(boolean force, boolean refreshFocusEvents, boolean clearOwnerFocus, int focusDirection);

    void clearOwnerFocus();

    void dispatchIndirectPointerCancel();

    boolean dispatchIndirectPointerEvent(IndirectPointerEvent event);

    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo, reason: not valid java name */
    boolean mo2777dispatchInterceptedSoftKeyboardEventZmokQxo(KeyEvent keyEvent);

    /* JADX INFO: renamed from: dispatchKeyEvent-YhN2O0w, reason: not valid java name */
    boolean mo2778dispatchKeyEventYhN2O0w(KeyEvent keyEvent, Function0<Boolean> onFocusedItem);

    boolean dispatchRotaryEvent(RotaryScrollEvent event, Function0<Boolean> onFocusedItem);

    /* JADX INFO: renamed from: focusSearch-ULY8qGw, reason: not valid java name */
    Boolean mo2779focusSearchULY8qGw(int focusDirection, Rect focusedRect, Function1<? super FocusTargetNode, Boolean> onFound);

    void focusTargetAvailable();

    FocusTargetNode getActiveFocusTargetNode();

    Rect getFocusRect();

    MutableObjectList<FocusListener> getListeners();

    Modifier getModifier();

    FocusState getRootState();

    boolean hasFocusableContent();

    boolean hasNonInteropFocusableContent();

    boolean isFocusCaptured();

    /* JADX INFO: renamed from: moveFocus-aToIllA, reason: not valid java name */
    boolean mo2780moveFocusaToIllA(int focusDirection, boolean wrapAroundForOneDimensionalFocus);

    void releaseFocus();

    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno, reason: not valid java name */
    boolean mo2781requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect);

    /* JADX INFO: renamed from: resetFocus-3ESFkO8, reason: not valid java name */
    boolean mo2782resetFocus3ESFkO8(int focusDirection);

    void scheduleInvalidation(FocusEventModifierNode node);

    void scheduleInvalidation(FocusTargetNode node);

    void scheduleInvalidationForOwner();

    void setActiveFocusTargetNode(FocusTargetNode focusTargetNode);

    void setFocusCaptured(boolean z);

    /* JADX INFO: renamed from: takeFocus-aToIllA, reason: not valid java name */
    boolean mo2783takeFocusaToIllA(int focusDirection, Rect previouslyFocusedRect);
}
