package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#\b\u0001\u0018\u0000 Y2\u00020\u0001:\u0001YB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ,\u0010%\u001a\u00020\u00002\b\b\u0002\u0010&\u001a\u00020\u00072\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0(¢\u0006\u0002\b*H\u0082\bJ\u0006\u0010+\u001a\u00020\u0000J\u0006\u0010,\u001a\u00020\u0000J\u0006\u0010-\u001a\u00020\u0000J\u0006\u0010.\u001a\u00020\u0000J\u001f\u0010/\u001a\u00020\u00002\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0(¢\u0006\u0002\b*J\u001f\u00101\u001a\u00020\u00002\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020)0(¢\u0006\u0002\b*J\u0006\u00102\u001a\u000203J\u0006\u00104\u001a\u000203J!\u00105\u001a\u00020\u00002\b\b\u0002\u0010&\u001a\u00020\u00072\f\u00106\u001a\b\u0012\u0004\u0012\u00020307H\u0082\bJ\u0006\u00108\u001a\u00020\u0000J\u0006\u00109\u001a\u00020\u0000J\u0006\u0010:\u001a\u00020\u0000J\u0006\u0010;\u001a\u00020\u0000J\u0006\u0010<\u001a\u00020\u0000J\u0006\u0010=\u001a\u00020\u0000J\u0006\u0010>\u001a\u00020\u0000J\u0006\u0010?\u001a\u00020\u0000J\u0006\u0010@\u001a\u00020\u0000J\u0006\u0010A\u001a\u00020\u0000J\u0006\u0010B\u001a\u00020\u0000J\u0006\u0010C\u001a\u00020\u0000J\u0006\u0010D\u001a\u00020\u0000J\u0006\u0010E\u001a\u00020\u0000J\u0006\u0010F\u001a\u00020\u0000J\u0006\u0010G\u001a\u00020\u0000J\u0006\u0010H\u001a\u00020\u0000J\u0006\u0010I\u001a\u00020\u0000J\u0006\u0010J\u001a\u00020\u0000J\u0006\u0010K\u001a\u00020\u0000J\u0006\u0010L\u001a\u00020\u0000J\b\u0010M\u001a\u00020\u0007H\u0002J\u0017\u0010N\u001a\u000203*\u00020\u00052\b\b\u0002\u0010O\u001a\u000203H\u0082\u0010J\u0017\u0010P\u001a\u000203*\u00020\u00052\b\b\u0002\u0010O\u001a\u000203H\u0082\u0010J\u0016\u0010Q\u001a\u000203*\u00020\u00052\b\b\u0002\u0010O\u001a\u000203H\u0002J\u0016\u0010R\u001a\u000203*\u00020\u00052\b\b\u0002\u0010O\u001a\u000203H\u0002J\u0014\u0010S\u001a\u000203*\u00020\u00052\u0006\u0010T\u001a\u000203H\u0002J\u0010\u0010U\u001a\u0002032\u0006\u0010V\u001a\u000203H\u0002J\u0010\u0010W\u001a\u0002032\u0006\u0010X\u001a\u000203H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/SelectionMovementDeletionContext;", "", "state", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "isFromSoftKeyboard", "", "visibleTextLayoutHeight", "", "textPreparedSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelectionState;", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextLayoutResult;ZFLandroidx/compose/foundation/text/input/internal/selection/TextFieldPreparedSelectionState;)V", "initialValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getInitialValue", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "initialWedgeAffinity", "Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;", "getInitialWedgeAffinity", "()Landroidx/compose/foundation/text/input/internal/SelectionWedgeAffinity;", "selection", "Landroidx/compose/ui/text/TextRange;", "getSelection-d9O1mEE", "()J", "setSelection-5zc-tL8", "(J)V", "J", "wedgeAffinity", "Landroidx/compose/foundation/text/input/internal/WedgeAffinity;", "getWedgeAffinity", "()Landroidx/compose/foundation/text/input/internal/WedgeAffinity;", "setWedgeAffinity", "(Landroidx/compose/foundation/text/input/internal/WedgeAffinity;)V", "text", "", "applyIfNotEmpty", "resetCachedX", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "selectAll", "deselect", "moveCursorLeftByChar", "moveCursorRightByChar", "collapseLeftOr", "or", "collapseRightOr", "getPrecedingCharacterIndex", "", "getNextCharacterIndex", "moveCursorTo", "proposedCursorMovement", "Lkotlin/Function0;", "moveCursorPrevByCodePointOrEmoji", "moveCursorPrevByChar", "moveCursorNextByChar", "moveCursorToHome", "moveCursorToEnd", "moveCursorLeftByWord", "moveCursorRightByWord", "moveCursorNextByWord", "moveCursorPrevByWord", "moveCursorPrevByParagraph", "moveCursorNextByParagraph", "moveCursorUpByLine", "moveCursorDownByLine", "moveCursorToLineLeftSide", "moveCursorToLineRightSide", "moveCursorToLineStart", "moveCursorToLineEnd", "moveCursorUpByPage", "moveCursorDownByPage", "selectMovement", "deleteMovement", "isLtr", "getNextWordOffsetForLayout", "currentOffset", "getPrevWordOffsetForLayout", "getLineStartByOffsetForLayout", "getLineEndByOffsetForLayout", "jumpByLinesOffset", "linesAmount", "jumpByPagesOffset", "pagesAmount", "charOffset", "offset", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SelectionMovementDeletionContext {
    public static final int NoCharacterFound = -1;
    private final TextFieldCharSequence initialValue;
    private final SelectionWedgeAffinity initialWedgeAffinity;
    private final boolean isFromSoftKeyboard;
    private long selection;
    private final TransformedTextFieldState state;
    private final String text;
    private final TextLayoutResult textLayoutResult;
    private final TextFieldPreparedSelectionState textPreparedSelectionState;
    private final float visibleTextLayoutHeight;
    private WedgeAffinity wedgeAffinity;
    public static final int $stable = 8;

    public SelectionMovementDeletionContext(TransformedTextFieldState transformedTextFieldState, TextLayoutResult textLayoutResult, boolean z, float f, TextFieldPreparedSelectionState textFieldPreparedSelectionState) {
        this.state = transformedTextFieldState;
        this.textLayoutResult = textLayoutResult;
        this.isFromSoftKeyboard = z;
        this.visibleTextLayoutHeight = f;
        this.textPreparedSelectionState = textFieldPreparedSelectionState;
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            TextFieldCharSequence visualText = transformedTextFieldState.getVisualText();
            this.initialValue = visualText;
            this.initialWedgeAffinity = transformedTextFieldState.getSelectionWedgeAffinity();
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            this.selection = visualText.getSelection();
            this.text = visualText.getText().toString();
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    private final SelectionMovementDeletionContext applyIfNotEmpty(boolean resetCachedX, Function1<? super SelectionMovementDeletionContext, Unit> block) {
        if (resetCachedX) {
            this.textPreparedSelectionState.resetCachedX();
        }
        if (this.text.length() > 0) {
            block.invoke(this);
        }
        return this;
    }

    public static /* synthetic */ SelectionMovementDeletionContext applyIfNotEmpty$default(SelectionMovementDeletionContext selectionMovementDeletionContext, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if (z) {
            selectionMovementDeletionContext.textPreparedSelectionState.resetCachedX();
        }
        if (selectionMovementDeletionContext.text.length() > 0) {
            function1.invoke(selectionMovementDeletionContext);
        }
        return selectionMovementDeletionContext;
    }

    private final int charOffset(int offset) {
        return RangesKt.coerceAtMost(offset, this.text.length() - 1);
    }

    private final int getLineEndByOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        return textLayoutResult.getLineEnd(textLayoutResult.getLineForOffset(i), true);
    }

    public static /* synthetic */ int getLineEndByOffsetForLayout$default(SelectionMovementDeletionContext selectionMovementDeletionContext, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.getMax-impl(selectionMovementDeletionContext.selection);
        }
        return selectionMovementDeletionContext.getLineEndByOffsetForLayout(textLayoutResult, i);
    }

    private final int getLineStartByOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        return textLayoutResult.getLineStart(textLayoutResult.getLineForOffset(i));
    }

    public static /* synthetic */ int getLineStartByOffsetForLayout$default(SelectionMovementDeletionContext selectionMovementDeletionContext, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.getMin-impl(selectionMovementDeletionContext.selection);
        }
        return selectionMovementDeletionContext.getLineStartByOffsetForLayout(textLayoutResult, i);
    }

    private final int getNextWordOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        while (i < this.initialValue.length()) {
            long j = textLayoutResult.getWordBoundary--jx7JFs(charOffset(i));
            if (TextRange.getEnd-impl(j) > i) {
                return TextRange.getEnd-impl(j);
            }
            i++;
        }
        return this.initialValue.length();
    }

    public static /* synthetic */ int getNextWordOffsetForLayout$default(SelectionMovementDeletionContext selectionMovementDeletionContext, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.getEnd-impl(selectionMovementDeletionContext.selection);
        }
        return selectionMovementDeletionContext.getNextWordOffsetForLayout(textLayoutResult, i);
    }

    private final int getPrevWordOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        while (i > 0) {
            long j = textLayoutResult.getWordBoundary--jx7JFs(charOffset(i));
            if (TextRange.getStart-impl(j) < i) {
                return TextRange.getStart-impl(j);
            }
            i--;
        }
        return 0;
    }

    public static /* synthetic */ int getPrevWordOffsetForLayout$default(SelectionMovementDeletionContext selectionMovementDeletionContext, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = TextRange.getEnd-impl(selectionMovementDeletionContext.selection);
        }
        return selectionMovementDeletionContext.getPrevWordOffsetForLayout(textLayoutResult, i);
    }

    private final boolean isLtr() {
        ResolvedTextDirection paragraphDirection;
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        return textLayoutResult == null || (paragraphDirection = textLayoutResult.getParagraphDirection(TextRange.getEnd-impl(this.selection))) == null || paragraphDirection == ResolvedTextDirection.Ltr;
    }

    private final int jumpByLinesOffset(TextLayoutResult textLayoutResult, int i) {
        int i2 = TextRange.getEnd-impl(this.selection);
        if (Float.isNaN(this.textPreparedSelectionState.getCachedX())) {
            this.textPreparedSelectionState.setCachedX(textLayoutResult.getCursorRect(i2).getLeft());
        }
        int lineForOffset = textLayoutResult.getLineForOffset(i2) + i;
        if (lineForOffset < 0) {
            return Integer.MIN_VALUE;
        }
        if (lineForOffset >= textLayoutResult.getLineCount()) {
            return Integer.MAX_VALUE;
        }
        float lineBottom = textLayoutResult.getLineBottom(lineForOffset) - 1.0f;
        float cachedX = this.textPreparedSelectionState.getCachedX();
        if ((isLtr() && cachedX >= textLayoutResult.getLineRight(lineForOffset)) || (!isLtr() && cachedX <= textLayoutResult.getLineLeft(lineForOffset))) {
            return textLayoutResult.getLineEnd(lineForOffset, true);
        }
        return textLayoutResult.getOffsetForPosition-k-4lQ0M(Offset.constructor-impl((((long) Float.floatToRawIntBits(cachedX)) << 32) | (((long) Float.floatToRawIntBits(lineBottom)) & 4294967295L)));
    }

    private final int jumpByPagesOffset(int pagesAmount) {
        int i = TextRange.getEnd-impl(this.initialValue.getSelection());
        if (this.textLayoutResult == null || Float.isNaN(this.visibleTextLayoutHeight)) {
            return i;
        }
        Rect rectTranslate = this.textLayoutResult.getCursorRect(i).translate(0.0f, this.visibleTextLayoutHeight * pagesAmount);
        float lineBottom = this.textLayoutResult.getLineBottom(this.textLayoutResult.getLineForVerticalPosition(rectTranslate.getTop()));
        float fAbs = Math.abs(rectTranslate.getTop() - lineBottom);
        float fAbs2 = Math.abs(rectTranslate.getBottom() - lineBottom);
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        return fAbs > fAbs2 ? textLayoutResult.getOffsetForPosition-k-4lQ0M(rectTranslate.getTopLeft-F1C5BW0()) : textLayoutResult.getOffsetForPosition-k-4lQ0M(rectTranslate.getBottomLeft-F1C5BW0());
    }

    private final SelectionMovementDeletionContext moveCursorTo(boolean resetCachedX, Function0<Integer> proposedCursorMovement) {
        if (resetCachedX) {
            this.textPreparedSelectionState.resetCachedX();
        }
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(((Number) proposedCursorMovement.invoke()).intValue(), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public static /* synthetic */ SelectionMovementDeletionContext moveCursorTo$default(SelectionMovementDeletionContext selectionMovementDeletionContext, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if (z) {
            selectionMovementDeletionContext.textPreparedSelectionState.resetCachedX();
        }
        if (selectionMovementDeletionContext.text.length() > 0) {
            int i2 = TextRange.getEnd-impl(selectionMovementDeletionContext.getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(((Number) function0.invoke()).intValue(), i2, selectionMovementDeletionContext.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i2 || !TextRange.getCollapsed-impl(selectionMovementDeletionContext.getSelection())) {
                selectionMovementDeletionContext.m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                selectionMovementDeletionContext.setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return selectionMovementDeletionContext;
    }

    public final SelectionMovementDeletionContext collapseLeftOr(Function1<? super SelectionMovementDeletionContext, Unit> or) {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            if (TextRange.getCollapsed-impl(this.selection)) {
                or.invoke(this);
                return this;
            }
            boolean zIsLtr = isLtr();
            long j = this.selection;
            if (zIsLtr) {
                this.selection = TextRangeKt.TextRange(TextRange.getMin-impl(j));
                return this;
            }
            this.selection = TextRangeKt.TextRange(TextRange.getMax-impl(j));
        }
        return this;
    }

    public final SelectionMovementDeletionContext collapseRightOr(Function1<? super SelectionMovementDeletionContext, Unit> or) {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            if (TextRange.getCollapsed-impl(this.selection)) {
                or.invoke(this);
                return this;
            }
            boolean zIsLtr = isLtr();
            long j = this.selection;
            if (zIsLtr) {
                this.selection = TextRangeKt.TextRange(TextRange.getMax-impl(j));
                return this;
            }
            this.selection = TextRangeKt.TextRange(TextRange.getMin-impl(j));
        }
        return this;
    }

    public final SelectionMovementDeletionContext deleteMovement() {
        if (this.text.length() > 0) {
            boolean z = TextRange.getCollapsed-impl(this.initialValue.getSelection());
            TransformedTextFieldState transformedTextFieldState = this.state;
            if (z) {
                TransformedTextFieldState.m1609replaceTextM8tDOmk$default(transformedTextFieldState, "", TextRangeKt.TextRange(TextRange.getStart-impl(this.initialValue.getSelection()), TextRange.getEnd-impl(this.selection)), null, !this.isFromSoftKeyboard, 4, null);
            } else {
                transformedTextFieldState.deleteSelectedText();
            }
            this.selection = this.state.getVisualText().getSelection();
            this.wedgeAffinity = WedgeAffinity.Start;
        }
        return this;
    }

    public final SelectionMovementDeletionContext deselect() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            this.selection = TextRangeKt.TextRange(TextRange.getEnd-impl(this.selection));
        }
        return this;
    }

    public final TextFieldCharSequence getInitialValue() {
        return this.initialValue;
    }

    public final SelectionWedgeAffinity getInitialWedgeAffinity() {
        return this.initialWedgeAffinity;
    }

    public final int getNextCharacterIndex() {
        return StringHelpers_androidKt.findFollowingBreak(this.text, TextRange.getEnd-impl(this.selection));
    }

    public final int getPrecedingCharacterIndex() {
        return StringHelpers_androidKt.findPrecedingBreak(this.text, TextRange.getEnd-impl(this.selection));
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelection() {
        return this.selection;
    }

    public final WedgeAffinity getWedgeAffinity() {
        return this.wedgeAffinity;
    }

    public final SelectionMovementDeletionContext moveCursorDownByLine() {
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        int iJumpByLinesOffset = textLayoutResult != null ? jumpByLinesOffset(textLayoutResult, 1) : Integer.MAX_VALUE;
        if (iJumpByLinesOffset == Integer.MAX_VALUE) {
            this.textPreparedSelectionState.resetCachedX();
        }
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(RangesKt.coerceAtMost(iJumpByLinesOffset, this.text.length()), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorDownByPage() {
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(jumpByPagesOffset(1), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorLeftByChar() {
        return isLtr() ? moveCursorPrevByChar() : moveCursorNextByChar();
    }

    public final SelectionMovementDeletionContext moveCursorLeftByWord() {
        return isLtr() ? moveCursorPrevByWord() : moveCursorNextByWord();
    }

    public final SelectionMovementDeletionContext moveCursorNextByChar() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(StringHelpers_androidKt.findFollowingBreak(this.text, TextRange.getEnd-impl(this.selection)), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorNextByParagraph() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            int iFindParagraphEnd = StringHelpersKt.findParagraphEnd(this.text, TextRange.getMax-impl(this.selection));
            if (iFindParagraphEnd == TextRange.getMax-impl(this.selection) && iFindParagraphEnd != this.text.length()) {
                iFindParagraphEnd = StringHelpersKt.findParagraphEnd(this.text, iFindParagraphEnd + 1);
            }
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(iFindParagraphEnd, i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorNextByWord() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            TextLayoutResult textLayoutResult = this.textLayoutResult;
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(textLayoutResult != null ? getNextWordOffsetForLayout$default(this, textLayoutResult, 0, 1, null) : this.text.length(), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorPrevByChar() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(StringHelpers_androidKt.findPrecedingBreak(this.text, TextRange.getEnd-impl(this.selection)), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorPrevByCodePointOrEmoji() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(StringHelpers_androidKt.findCodePointOrEmojiStartBefore(this.text, TextRange.getEnd-impl(this.selection), -1), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorPrevByParagraph() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            int iFindParagraphStart = StringHelpersKt.findParagraphStart(this.text, TextRange.getMin-impl(this.selection));
            if (iFindParagraphStart == TextRange.getMin-impl(this.selection) && iFindParagraphStart != 0) {
                iFindParagraphStart = StringHelpersKt.findParagraphStart(this.text, iFindParagraphStart - 1);
            }
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(iFindParagraphStart, i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorPrevByWord() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            TextLayoutResult textLayoutResult = this.textLayoutResult;
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(textLayoutResult != null ? getPrevWordOffsetForLayout$default(this, textLayoutResult, 0, 1, null) : 0, i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorRightByChar() {
        return isLtr() ? moveCursorNextByChar() : moveCursorPrevByChar();
    }

    public final SelectionMovementDeletionContext moveCursorRightByWord() {
        return isLtr() ? moveCursorNextByWord() : moveCursorPrevByWord();
    }

    public final SelectionMovementDeletionContext moveCursorToEnd() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(this.text.length(), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorToHome() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(0, i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorToLineEnd() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            TextLayoutResult textLayoutResult = this.textLayoutResult;
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(textLayoutResult != null ? getLineEndByOffsetForLayout$default(this, textLayoutResult, 0, 1, null) : this.text.length(), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorToLineLeftSide() {
        return isLtr() ? moveCursorToLineStart() : moveCursorToLineEnd();
    }

    public final SelectionMovementDeletionContext moveCursorToLineRightSide() {
        return isLtr() ? moveCursorToLineEnd() : moveCursorToLineStart();
    }

    public final SelectionMovementDeletionContext moveCursorToLineStart() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            TextLayoutResult textLayoutResult = this.textLayoutResult;
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(textLayoutResult != null ? getLineStartByOffsetForLayout$default(this, textLayoutResult, 0, 1, null) : 0, i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorUpByLine() {
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        int iJumpByLinesOffset = textLayoutResult != null ? jumpByLinesOffset(textLayoutResult, -1) : Integer.MIN_VALUE;
        if (iJumpByLinesOffset == Integer.MIN_VALUE) {
            this.textPreparedSelectionState.resetCachedX();
        }
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(RangesKt.coerceAtLeast(iJumpByLinesOffset, 0), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext moveCursorUpByPage() {
        if (this.text.length() > 0) {
            int i = TextRange.getEnd-impl(getSelection());
            long jCalculateNextCursorPositionAndWedgeAffinity = TextPreparedSelectionKt.calculateNextCursorPositionAndWedgeAffinity(jumpByPagesOffset(-1), i, this.state);
            int iM1624component1impl = CursorAndWedgeAffinity.m1624component1impl(jCalculateNextCursorPositionAndWedgeAffinity);
            WedgeAffinity wedgeAffinityM1625component2impl = CursorAndWedgeAffinity.m1625component2impl(jCalculateNextCursorPositionAndWedgeAffinity);
            if (iM1624component1impl != i || !TextRange.getCollapsed-impl(getSelection())) {
                m1637setSelection5zctL8(TextRangeKt.TextRange(iM1624component1impl));
            }
            if (wedgeAffinityM1625component2impl != null) {
                setWedgeAffinity(wedgeAffinityM1625component2impl);
            }
        }
        return this;
    }

    public final SelectionMovementDeletionContext selectAll() {
        this.textPreparedSelectionState.resetCachedX();
        if (this.text.length() > 0) {
            this.selection = TextRangeKt.TextRange(0, this.text.length());
        }
        return this;
    }

    public final SelectionMovementDeletionContext selectMovement() {
        if (this.text.length() > 0) {
            this.selection = TextRangeKt.TextRange(TextRange.getStart-impl(this.initialValue.getSelection()), TextRange.getEnd-impl(this.selection));
        }
        return this;
    }

    /* JADX INFO: renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1637setSelection5zctL8(long j) {
        this.selection = j;
    }

    public final void setWedgeAffinity(WedgeAffinity wedgeAffinity) {
        this.wedgeAffinity = wedgeAffinity;
    }
}
