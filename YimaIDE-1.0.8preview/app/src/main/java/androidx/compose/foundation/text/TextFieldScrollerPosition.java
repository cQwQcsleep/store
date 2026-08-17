package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.text.TextFieldScrollerPosition;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextRange;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\r\b\u0001\u0018\u0000 :2\u00020\u0001:\u0001:B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bJ&\u0010-\u001a\u00020.2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u0015J%\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0015H\u0000¢\u0006\u0002\b5J\u0015\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020 ¢\u0006\u0004\b8\u00109R+\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00058F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u00158F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R+\u0010&\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006;"}, d2 = {"Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "", "initialOrientation", "Landroidx/compose/foundation/gestures/Orientation;", "initial", "", "<init>", "(Landroidx/compose/foundation/gestures/Orientation;F)V", "()V", "<set-?>", "offset", "getOffset", "()F", "setOffset", "(F)V", "offset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "maximum", "getMaximum", "setMaximum", "maximum$delegate", "", "viewportSize", "getViewportSize", "()I", "setViewportSize", "(I)V", "viewportSize$delegate", "Landroidx/compose/runtime/MutableIntState;", "previousCursorRect", "Landroidx/compose/ui/geometry/Rect;", "previousSelection", "Landroidx/compose/ui/text/TextRange;", "getPreviousSelection-d9O1mEE", "()J", "setPreviousSelection-5zc-tL8", "(J)V", "J", "orientation", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "orientation$delegate", "Landroidx/compose/runtime/MutableState;", "update", "", "cursorRect", "containerSize", "textFieldSize", "coerceOffset", "cursorStart", "cursorEnd", "coerceOffset$foundation", "getOffsetToFollow", "selection", "getOffsetToFollow-5zc-tL8", "(J)I", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldScrollerPosition {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<TextFieldScrollerPosition, Object> Saver = ListSaverKt.listSaver(new Function2() { // from class: h8e
        public final Object invoke(Object obj, Object obj2) {
            return TextFieldScrollerPosition.a((SaverScope) obj, (TextFieldScrollerPosition) obj2);
        }
    }, new Function1() { // from class: i8e
        public final Object invoke(Object obj) {
            return TextFieldScrollerPosition.b((List) obj);
        }
    });

    /* JADX INFO: renamed from: maximum$delegate, reason: from kotlin metadata */
    private final MutableFloatState maximum;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableFloatState offset;

    /* JADX INFO: renamed from: orientation$delegate, reason: from kotlin metadata */
    private final MutableState orientation;
    private Rect previousCursorRect;
    private long previousSelection;

    /* JADX INFO: renamed from: viewportSize$delegate, reason: from kotlin metadata */
    private final MutableIntState viewportSize;

    public TextFieldScrollerPosition(Orientation orientation, float f) {
        this.offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        this.maximum = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.viewportSize = SnapshotIntStateKt.mutableIntStateOf(0);
        this.previousCursorRect = Rect.Companion.getZero();
        this.previousSelection = TextRange.Companion.getZero-d9O1mEE();
        this.orientation = SnapshotStateKt.mutableStateOf(orientation, SnapshotStateKt.structuralEqualityPolicy());
    }

    public static List a(SaverScope saverScope, TextFieldScrollerPosition textFieldScrollerPosition) {
        return CollectionsKt.listOf(new Object[]{Float.valueOf(textFieldScrollerPosition.getOffset()), Boolean.valueOf(textFieldScrollerPosition.getOrientation() == Orientation.Vertical)});
    }

    public static TextFieldScrollerPosition b(List list) {
        Object obj = list.get(1);
        obj.getClass();
        Orientation orientation = ((Boolean) obj).booleanValue() ? Orientation.Vertical : Orientation.Horizontal;
        Object obj2 = list.get(0);
        obj2.getClass();
        return new TextFieldScrollerPosition(orientation, ((Float) obj2).floatValue());
    }

    private final void setMaximum(float f) {
        this.maximum.setFloatValue(f);
    }

    private final void setViewportSize(int i) {
        this.viewportSize.setIntValue(i);
    }

    public final void coerceOffset$foundation(float cursorStart, float cursorEnd, int containerSize) {
        float f;
        float offset = getOffset();
        float f2 = containerSize;
        float f3 = offset + f2;
        if (cursorEnd <= f3 && (cursorStart >= offset || cursorEnd - cursorStart <= f2)) {
            f = (cursorStart >= offset || cursorEnd - cursorStart > f2) ? 0.0f : cursorStart - offset;
        } else {
            f = cursorEnd - f3;
        }
        setOffset(getOffset() + f);
    }

    public final float getMaximum() {
        return this.maximum.getFloatValue();
    }

    public final float getOffset() {
        return this.offset.getFloatValue();
    }

    /* JADX INFO: renamed from: getOffsetToFollow-5zc-tL8, reason: not valid java name */
    public final int m1432getOffsetToFollow5zctL8(long selection) {
        if (TextRange.getStart-impl(selection) != TextRange.getStart-impl(this.previousSelection)) {
            return TextRange.getStart-impl(selection);
        }
        return TextRange.getEnd-impl(selection) != TextRange.getEnd-impl(this.previousSelection) ? TextRange.getEnd-impl(selection) : TextRange.getMin-impl(selection);
    }

    public final Orientation getOrientation() {
        return (Orientation) this.orientation.getValue();
    }

    /* JADX INFO: renamed from: getPreviousSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getPreviousSelection() {
        return this.previousSelection;
    }

    public final int getViewportSize() {
        return this.viewportSize.getIntValue();
    }

    public final void setOffset(float f) {
        this.offset.setFloatValue(f);
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation.setValue(orientation);
    }

    /* JADX INFO: renamed from: setPreviousSelection-5zc-tL8, reason: not valid java name */
    public final void m1434setPreviousSelection5zctL8(long j) {
        this.previousSelection = j;
    }

    public final void update(Orientation orientation, Rect cursorRect, int containerSize, int textFieldSize) {
        float f = textFieldSize - containerSize;
        setMaximum(f);
        if (cursorRect.getLeft() != this.previousCursorRect.getLeft() || cursorRect.getTop() != this.previousCursorRect.getTop()) {
            boolean z = orientation == Orientation.Vertical;
            coerceOffset$foundation(z ? cursorRect.getTop() : cursorRect.getLeft(), z ? cursorRect.getBottom() : cursorRect.getRight(), containerSize);
            this.previousCursorRect = cursorRect;
        }
        setOffset(RangesKt.coerceIn(getOffset(), 0.0f, f));
        setViewportSize(containerSize);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text/TextFieldScrollerPosition$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<TextFieldScrollerPosition, Object> getSaver() {
            return TextFieldScrollerPosition.Saver;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TextFieldScrollerPosition(Orientation orientation, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(orientation, (i & 2) != 0 ? 0.0f : f);
    }

    public TextFieldScrollerPosition() {
        this(Orientation.Vertical, 0.0f, 2, null);
    }
}
