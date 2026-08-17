package androidx.compose.foundation.text.selection;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.text.TextLayoutHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001f\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\n\u0010$\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010%\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0013H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010*\u001a\u00020\u0013H\u0016J\u0010\u0010-\u001a\u00020,2\u0006\u0010*\u001a\u00020\u0013H\u0016J\u0010\u0010.\u001a\u00020,2\u0006\u0010*\u001a\u00020\u0013H\u0016J\u0017\u0010/\u001a\u0002002\u0006\u0010*\u001a\u00020\u0013H\u0016¢\u0006\u0004\b1\u00102J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\u0010\u00103\u001a\u00020,2\u0006\u0010*\u001a\u00020\u0013H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00060\u000ej\u0002`\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\u00020\u0013*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u00064"}, d2 = {"Landroidx/compose/foundation/text/selection/MultiWidgetSelectionDelegate;", "Landroidx/compose/foundation/text/selection/Selectable;", "selectableId", "", "coordinatesCallback", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutResultCallback", "Landroidx/compose/ui/text/TextLayoutResult;", "<init>", "(JLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getSelectableId", "()J", "lock", "", "Landroidx/compose/foundation/platform/SynchronizedObject;", "Ljava/lang/Object;", "_previousTextLayoutResult", "_previousLastVisibleOffset", "", "lastVisibleOffset", "getLastVisibleOffset", "(Landroidx/compose/ui/text/TextLayoutResult;)I", "appendSelectableInfoToBuilder", "", "builder", "Landroidx/compose/foundation/text/selection/SelectionLayoutBuilder;", "getSelectAllSelection", "Landroidx/compose/foundation/text/selection/Selection;", "getHandlePosition", "Landroidx/compose/ui/geometry/Offset;", "selection", "isStartHandle", "", "getHandlePosition-dBAh8RU", "(Landroidx/compose/foundation/text/selection/Selection;Z)J", "getLayoutCoordinates", "textLayoutResult", "getText", "Landroidx/compose/ui/text/AnnotatedString;", "getBoundingBox", "Landroidx/compose/ui/geometry/Rect;", "offset", "getLineLeft", "", "getLineRight", "getCenterYForOffset", "getRangeOfLineContaining", "Landroidx/compose/ui/text/TextRange;", "getRangeOfLineContaining--jx7JFs", "(I)J", "getLineHeight", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MultiWidgetSelectionDelegate implements Selectable {
    public static final int $stable = 8;
    private TextLayoutResult _previousTextLayoutResult;
    private final Function0<LayoutCoordinates> coordinatesCallback;
    private final Function0<TextLayoutResult> layoutResultCallback;
    private final long selectableId;
    private final Object lock = this;
    private int _previousLastVisibleOffset = -1;

    public MultiWidgetSelectionDelegate(long j, Function0<? extends LayoutCoordinates> function0, Function0<TextLayoutResult> function1) {
        this.selectableId = j;
        this.coordinatesCallback = function0;
        this.layoutResultCallback = function1;
    }

    private final int getLastVisibleOffset(TextLayoutResult textLayoutResult) {
        int i;
        int lineCount;
        synchronized (this.lock) {
            try {
                if (this._previousTextLayoutResult != textLayoutResult) {
                    if (!textLayoutResult.getDidOverflowHeight() || textLayoutResult.getMultiParagraph().getDidExceedMaxLines()) {
                        lineCount = textLayoutResult.getLineCount() - 1;
                    } else {
                        int iCoerceAtMost = RangesKt.coerceAtMost(textLayoutResult.getLineForVerticalPosition((int) (textLayoutResult.getSize-YbymL2g() & 4294967295L)), textLayoutResult.getLineCount() - 1);
                        while (iCoerceAtMost >= 0 && textLayoutResult.getLineTop(iCoerceAtMost) >= ((int) (textLayoutResult.getSize-YbymL2g() & 4294967295L))) {
                            iCoerceAtMost--;
                        }
                        lineCount = RangesKt.coerceAtLeast(iCoerceAtMost, 0);
                    }
                    this._previousLastVisibleOffset = textLayoutResult.getLineEnd(lineCount, true);
                    this._previousTextLayoutResult = textLayoutResult;
                }
                i = this._previousLastVisibleOffset;
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public void appendSelectableInfoToBuilder(SelectionLayoutBuilder builder) {
        TextLayoutResult textLayoutResult;
        LayoutCoordinates layoutCoordinates = getLayoutCoordinates();
        if (layoutCoordinates == null || (textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke()) == null) {
            return;
        }
        LayoutCoordinates containerCoordinates = builder.getContainerCoordinates();
        Offset.Companion companion = Offset.Companion;
        long j = containerCoordinates.localPositionOf-R5De75A(layoutCoordinates, companion.getZero-F1C5BW0());
        MultiWidgetSelectionDelegateKt.m1743appendSelectableInfoParwq6A(builder, textLayoutResult, Offset.minus-MK-Hz9U(builder.getCurrentPosition(), j), (builder.getPreviousHandlePosition() & SieveCacheKt.InvalidMapping) == 9205357640488583168L ? companion.getUnspecified-F1C5BW0() : Offset.minus-MK-Hz9U(builder.getPreviousHandlePosition(), j), getSelectableId());
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public Rect getBoundingBox(int offset) {
        int length;
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult != null && (length = textLayoutResult.getLayoutInput().getText().length()) >= 1) {
            return textLayoutResult.getBoundingBox(RangesKt.coerceIn(offset, 0, length - 1));
        }
        return Rect.Companion.getZero();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public float getCenterYForOffset(int offset) {
        int lineForOffset;
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult == null || (lineForOffset = textLayoutResult.getLineForOffset(offset)) >= textLayoutResult.getLineCount()) {
            return -1.0f;
        }
        float lineTop = textLayoutResult.getLineTop(lineForOffset);
        return ((textLayoutResult.getLineBottom(lineForOffset) - lineTop) / 2.0f) + lineTop;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    /* JADX INFO: renamed from: getHandlePosition-dBAh8RU, reason: not valid java name */
    public long mo1741getHandlePositiondBAh8RU(Selection selection, boolean isStartHandle) {
        TextLayoutResult textLayoutResult;
        if ((isStartHandle && selection.getStart().getSelectableId() != getSelectableId()) || (!isStartHandle && selection.getEnd().getSelectableId() != getSelectableId())) {
            return Offset.Companion.getUnspecified-F1C5BW0();
        }
        if (getLayoutCoordinates() != null && (textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke()) != null) {
            return TextSelectionDelegateKt.getSelectionHandleCoordinates(textLayoutResult, RangesKt.coerceIn((isStartHandle ? selection.getStart() : selection.getEnd()).getOffset(), 0, getLastVisibleOffset(textLayoutResult)), isStartHandle, selection.getHandlesCrossed());
        }
        return Offset.Companion.getUnspecified-F1C5BW0();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public LayoutCoordinates getLayoutCoordinates() {
        LayoutCoordinates layoutCoordinates = (LayoutCoordinates) this.coordinatesCallback.invoke();
        if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
            return null;
        }
        return layoutCoordinates;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public float getLineHeight(int offset) {
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult != null) {
            return TextLayoutHelperKt.getLineHeight(textLayoutResult, offset);
        }
        return 0.0f;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public float getLineLeft(int offset) {
        int lineForOffset;
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult != null && (lineForOffset = textLayoutResult.getLineForOffset(offset)) < textLayoutResult.getLineCount()) {
            return textLayoutResult.getLineLeft(lineForOffset);
        }
        return -1.0f;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public float getLineRight(int offset) {
        int lineForOffset;
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult != null && (lineForOffset = textLayoutResult.getLineForOffset(offset)) < textLayoutResult.getLineCount()) {
            return textLayoutResult.getLineRight(lineForOffset);
        }
        return -1.0f;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    /* JADX INFO: renamed from: getRangeOfLineContaining--jx7JFs, reason: not valid java name */
    public long mo1742getRangeOfLineContainingjx7JFs(int offset) {
        int lastVisibleOffset;
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult != null && (lastVisibleOffset = getLastVisibleOffset(textLayoutResult)) >= 1) {
            int lineForOffset = textLayoutResult.getLineForOffset(RangesKt.coerceIn(offset, 0, lastVisibleOffset - 1));
            return TextRangeKt.TextRange(textLayoutResult.getLineStart(lineForOffset), textLayoutResult.getLineEnd(lineForOffset, true));
        }
        return TextRange.Companion.getZero-d9O1mEE();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public Selection getSelectAllSelection() {
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult == null) {
            return null;
        }
        int length = textLayoutResult.getLayoutInput().getText().length();
        return new Selection(new Selection.AnchorInfo(textLayoutResult.getBidiRunDirection(0), 0, getSelectableId()), new Selection.AnchorInfo(textLayoutResult.getBidiRunDirection(Math.max(length - 1, 0)), length, getSelectableId()), false);
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public long getSelectableId() {
        return this.selectableId;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public AnnotatedString getText() {
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        return textLayoutResult == null ? new AnnotatedString("", (List) null, 2, (DefaultConstructorMarker) null) : textLayoutResult.getLayoutInput().getText();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public TextLayoutResult textLayoutResult() {
        return (TextLayoutResult) this.layoutResultCallback.invoke();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public int getLastVisibleOffset() {
        TextLayoutResult textLayoutResult = (TextLayoutResult) this.layoutResultCallback.invoke();
        if (textLayoutResult == null) {
            return 0;
        }
        return getLastVisibleOffset(textLayoutResult);
    }
}
