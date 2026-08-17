package androidx.compose.ui.focus;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000 A2\u00020\u0001:\u0001AR\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0012\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR$\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR$\u0010\u0018\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR$\u0010\u001b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR$\u0010\u001e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000eR$\u0010!\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eRB\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%8V@WX\u0097\u000e¢\u0006\u0012\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-RF\u00101\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b02\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b08V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b2\u0010+\"\u0004\b3\u0010-RB\u00104\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\t0%8V@WX\u0097\u000e¢\u0006\u0012\u0012\u0004\b5\u0010)\u001a\u0004\b6\u0010+\"\u0004\b7\u0010-RF\u00108\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b02\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020/0%¢\u0006\u0002\b08V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u0010+\"\u0004\b:\u0010-R$\u0010<\u001a\u00020;2\u0006\u0010\b\u001a\u00020;8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006BÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/FocusProperties;", "", "canFocus", "", "getCanFocus", "()Z", "setCanFocus", "(Z)V", "_", "Landroidx/compose/ui/focus/FocusRequester;", "next", "getNext", "()Landroidx/compose/ui/focus/FocusRequester;", "setNext", "(Landroidx/compose/ui/focus/FocusRequester;)V", "previous", "getPrevious", "setPrevious", "up", "getUp", "setUp", "down", "getDown", "setDown", "left", "getLeft", "setLeft", "right", "getRight", "setRight", "start", "getStart", "setStart", "end", "getEnd", "setEnd", "value", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusDirection;", "enter", "getEnter$annotations", "()V", "getEnter", "()Lkotlin/jvm/functions/Function1;", "setEnter", "(Lkotlin/jvm/functions/Function1;)V", "Landroidx/compose/ui/focus/FocusEnterExitScope;", "", "Lkotlin/ExtensionFunctionType;", "onEnter", "getOnEnter", "setOnEnter", "exit", "getExit$annotations", "getExit", "setExit", "onExit", "getOnExit", "setOnExit", "Landroidx/compose/ui/geometry/Rect;", "focusRect", "getFocusRect", "()Landroidx/compose/ui/geometry/Rect;", "setFocusRect", "(Landroidx/compose/ui/geometry/Rect;)V", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface FocusProperties {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/focus/FocusProperties$Companion;", "", "<init>", "()V", "UnsetFocusRect", "Landroidx/compose/ui/geometry/Rect;", "getUnsetFocusRect", "()Landroidx/compose/ui/geometry/Rect;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Rect UnsetFocusRect = new Rect(Float.NaN, Float.NaN, Float.NaN, Float.NaN);

        private Companion() {
        }

        public final Rect getUnsetFocusRect() {
            return UnsetFocusRect;
        }
    }

    static /* synthetic */ void getEnter$annotations() {
    }

    static /* synthetic */ void getExit$annotations() {
    }

    boolean getCanFocus();

    default FocusRequester getDown() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default FocusRequester getEnd() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default Function1<FocusDirection, FocusRequester> getEnter() {
        return new Function1<FocusDirection, FocusRequester>() { // from class: androidx.compose.ui.focus.FocusProperties$enter$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m2790invoke3ESFkO8(((FocusDirection) obj).getValue());
            }

            /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
            public final FocusRequester m2790invoke3ESFkO8(int i) {
                return FocusRequester.INSTANCE.getDefault();
            }
        };
    }

    default Function1<FocusDirection, FocusRequester> getExit() {
        return new Function1<FocusDirection, FocusRequester>() { // from class: androidx.compose.ui.focus.FocusProperties$exit$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m2791invoke3ESFkO8(((FocusDirection) obj).getValue());
            }

            /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
            public final FocusRequester m2791invoke3ESFkO8(int i) {
                return FocusRequester.INSTANCE.getDefault();
            }
        };
    }

    default Rect getFocusRect() {
        return INSTANCE.getUnsetFocusRect();
    }

    default FocusRequester getLeft() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default FocusRequester getNext() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default Function1<FocusEnterExitScope, Unit> getOnEnter() {
        return new Function1<FocusEnterExitScope, Unit>() { // from class: androidx.compose.ui.focus.FocusProperties$onEnter$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((FocusEnterExitScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(FocusEnterExitScope focusEnterExitScope) {
            }
        };
    }

    default Function1<FocusEnterExitScope, Unit> getOnExit() {
        return new Function1<FocusEnterExitScope, Unit>() { // from class: androidx.compose.ui.focus.FocusProperties$onExit$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((FocusEnterExitScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(FocusEnterExitScope focusEnterExitScope) {
            }
        };
    }

    default FocusRequester getPrevious() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default FocusRequester getRight() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default FocusRequester getStart() {
        return FocusRequester.INSTANCE.getDefault();
    }

    default FocusRequester getUp() {
        return FocusRequester.INSTANCE.getDefault();
    }

    void setCanFocus(boolean z);

    default void setDown(FocusRequester focusRequester) {
    }

    default void setEnd(FocusRequester focusRequester) {
    }

    @Deprecated(message = "Use onEnter instead", replaceWith = @ReplaceWith(expression = "onEnter", imports = {}))
    default void setEnter(Function1<? super FocusDirection, FocusRequester> function1) {
        setOnEnter(FocusPropertiesKt.toUsingEnterExitScope(function1));
    }

    @Deprecated(message = "Use onExit instead", replaceWith = @ReplaceWith(expression = "onExit", imports = {}))
    default void setExit(Function1<? super FocusDirection, FocusRequester> function1) {
        setOnExit(FocusPropertiesKt.toUsingEnterExitScope(function1));
    }

    default void setFocusRect(Rect rect) {
    }

    default void setLeft(FocusRequester focusRequester) {
    }

    default void setNext(FocusRequester focusRequester) {
    }

    default void setOnEnter(Function1<? super FocusEnterExitScope, Unit> function1) {
    }

    default void setOnExit(Function1<? super FocusEnterExitScope, Unit> function1) {
    }

    default void setPrevious(FocusRequester focusRequester) {
    }

    default void setRight(FocusRequester focusRequester) {
    }

    default void setStart(FocusRequester focusRequester) {
    }

    default void setUp(FocusRequester focusRequester) {
    }
}
