package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.selection.BaseTextPreparedSelection;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b3\b!\u0018\u0000 c*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\u0001cB1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ;\u0010&\u001a\u00028\u0000\"\u0004\b\u0001\u0010'*\u0002H'2\b\b\u0002\u0010(\u001a\u00020)2\u0017\u0010*\u001a\u0013\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020,0+¢\u0006\u0002\b-H\u0084\b¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0004J\u0018\u00102\u001a\u00020,2\u0006\u00103\u001a\u0002012\u0006\u00104\u001a\u000201H\u0004J\u000b\u00105\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u00107\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u00108\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u00109\u001a\u00028\u0000¢\u0006\u0002\u00106J$\u0010:\u001a\u00028\u00002\u0017\u0010;\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020,0+¢\u0006\u0002\b-¢\u0006\u0002\u0010<J$\u0010=\u001a\u00028\u00002\u0017\u0010;\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020,0+¢\u0006\u0002\b-¢\u0006\u0002\u0010<J\u0006\u0010>\u001a\u000201J\u0006\u0010?\u001a\u000201J\u0006\u0010@\u001a\u000201J\r\u0010A\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00106J\r\u0010B\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00106J\u000b\u0010C\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010D\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010E\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010F\u001a\u00028\u0000¢\u0006\u0002\u00106J\r\u0010G\u001a\u0004\u0018\u000101¢\u0006\u0002\u0010HJ\r\u0010I\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00106J\r\u0010J\u001a\u0004\u0018\u000101¢\u0006\u0002\u0010HJ\r\u0010K\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00106J\u000b\u0010L\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010M\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010N\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010O\u001a\u00028\u0000¢\u0006\u0002\u00106J\r\u0010P\u001a\u0004\u0018\u000101¢\u0006\u0002\u0010HJ\u000b\u0010Q\u001a\u00028\u0000¢\u0006\u0002\u00106J\r\u0010R\u001a\u0004\u0018\u000101¢\u0006\u0002\u0010HJ\u000b\u0010S\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010T\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010U\u001a\u00028\u0000¢\u0006\u0002\u00106J\u000b\u0010V\u001a\u00028\u0000¢\u0006\u0002\u00106J\b\u0010W\u001a\u00020)H\u0002J\u0017\u0010X\u001a\u000201*\u00020\b2\b\b\u0002\u0010Y\u001a\u000201H\u0082\u0010J\u0017\u0010Z\u001a\u000201*\u00020\b2\b\b\u0002\u0010Y\u001a\u000201H\u0082\u0010J\u0016\u0010[\u001a\u000201*\u00020\b2\b\b\u0002\u0010Y\u001a\u000201H\u0002J\u0016\u0010\\\u001a\u000201*\u00020\b2\b\b\u0002\u0010Y\u001a\u000201H\u0002J\u0014\u0010]\u001a\u000201*\u00020\b2\u0006\u0010^\u001a\u000201H\u0002J\b\u0010_\u001a\u000201H\u0002J\b\u0010`\u001a\u000201H\u0002J\b\u0010a\u001a\u000201H\u0002J\u0010\u0010b\u001a\u0002012\u0006\u00100\u001a\u000201H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0005\u001a\u00020\u0006¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006d"}, d2 = {"Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "T", "", "originalText", "Landroidx/compose/ui/text/AnnotatedString;", "originalSelection", "Landroidx/compose/ui/text/TextRange;", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "state", "Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;JLandroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getOriginalText", "()Landroidx/compose/ui/text/AnnotatedString;", "getOriginalSelection-d9O1mEE", "()J", "J", "getLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "getState", "()Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "selection", "getSelection-d9O1mEE", "setSelection-5zc-tL8", "(J)V", "annotatedString", "getAnnotatedString", "setAnnotatedString", "(Landroidx/compose/ui/text/AnnotatedString;)V", "text", "", "getText$foundation", "()Ljava/lang/String;", "apply", "U", "resetCachedX", "", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;ZLkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "setCursor", "offset", "", "setSelection", "start", "end", "selectAll", "()Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "deselect", "moveCursorLeft", "moveCursorRight", "collapseLeftOr", "or", "(Lkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "collapseRightOr", "getPrecedingCodePointOrEmojiStartIndex", "getPrecedingCharacterIndex", "getNextCharacterIndex", "moveCursorPrev", "moveCursorNext", "moveCursorToHome", "moveCursorToEnd", "moveCursorLeftByWord", "moveCursorRightByWord", "getNextWordOffset", "()Ljava/lang/Integer;", "moveCursorNextByWord", "getPreviousWordOffset", "moveCursorPrevByWord", "moveCursorPrevByParagraph", "moveCursorNextByParagraph", "moveCursorUpByLine", "moveCursorDownByLine", "getLineStartByOffset", "moveCursorToLineStart", "getLineEndByOffset", "moveCursorToLineEnd", "moveCursorToLineLeftSide", "moveCursorToLineRightSide", "selectMovement", "isLtr", "getNextWordOffsetForLayout", "currentOffset", "getPrevWordOffset", "getLineStartByOffsetForLayout", "getLineEndByOffsetForLayout", "jumpByLinesOffset", "linesAmount", "transformedEndOffset", "transformedMinOffset", "transformedMaxOffset", "charOffset", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class BaseTextPreparedSelection<T extends BaseTextPreparedSelection<T>> {
    public static final int NoCharacterFound = -1;
    private AnnotatedString annotatedString;
    private final TextLayoutResult layoutResult;
    private final OffsetMapping offsetMapping;
    private final long originalSelection;
    private final AnnotatedString originalText;
    private long selection;
    private final TextPreparedSelectionState state;
    public static final int $stable = 8;

    private BaseTextPreparedSelection(AnnotatedString annotatedString, long j, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, TextPreparedSelectionState textPreparedSelectionState) {
        this.originalText = annotatedString;
        this.originalSelection = j;
        this.layoutResult = textLayoutResult;
        this.offsetMapping = offsetMapping;
        this.state = textPreparedSelectionState;
        this.selection = j;
        this.annotatedString = annotatedString;
    }

    public static /* synthetic */ BaseTextPreparedSelection apply$default(BaseTextPreparedSelection baseTextPreparedSelection, Object obj, boolean z, Function1 function1, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: apply");
            return null;
        }
        if ((i & 1) != 0) {
            z = true;
        }
        if (z) {
            baseTextPreparedSelection.getState().resetCachedX();
        }
        if (baseTextPreparedSelection.getText$foundation().length() > 0) {
            function1.invoke(obj);
        }
        obj.getClass();
        return (BaseTextPreparedSelection) obj;
    }

    private final int charOffset(int offset) {
        return RangesKt.coerceAtMost(offset, getText$foundation().length() - 1);
    }

    private final int getLineEndByOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        return this.offsetMapping.transformedToOriginal(textLayoutResult.getLineEnd(textLayoutResult.getLineForOffset(i), true));
    }

    public static /* synthetic */ int getLineEndByOffsetForLayout$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getLineEndByOffsetForLayout");
            return 0;
        }
        if ((i2 & 1) != 0) {
            i = baseTextPreparedSelection.transformedMaxOffset();
        }
        return baseTextPreparedSelection.getLineEndByOffsetForLayout(textLayoutResult, i);
    }

    private final int getLineStartByOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        return this.offsetMapping.transformedToOriginal(textLayoutResult.getLineStart(textLayoutResult.getLineForOffset(i)));
    }

    public static /* synthetic */ int getLineStartByOffsetForLayout$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getLineStartByOffsetForLayout");
            return 0;
        }
        if ((i2 & 1) != 0) {
            i = baseTextPreparedSelection.transformedMinOffset();
        }
        return baseTextPreparedSelection.getLineStartByOffsetForLayout(textLayoutResult, i);
    }

    private final int getNextWordOffsetForLayout(TextLayoutResult textLayoutResult, int i) {
        while (i < this.originalText.length()) {
            long j = textLayoutResult.getWordBoundary--jx7JFs(charOffset(i));
            if (TextRange.getEnd-impl(j) > i) {
                return this.offsetMapping.transformedToOriginal(TextRange.getEnd-impl(j));
            }
            i++;
        }
        return this.originalText.length();
    }

    public static /* synthetic */ int getNextWordOffsetForLayout$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getNextWordOffsetForLayout");
            return 0;
        }
        if ((i2 & 1) != 0) {
            i = baseTextPreparedSelection.transformedEndOffset();
        }
        return baseTextPreparedSelection.getNextWordOffsetForLayout(textLayoutResult, i);
    }

    private final int getPrevWordOffset(TextLayoutResult textLayoutResult, int i) {
        while (i > 0) {
            long j = textLayoutResult.getWordBoundary--jx7JFs(charOffset(i));
            if (TextRange.getStart-impl(j) < i) {
                return this.offsetMapping.transformedToOriginal(TextRange.getStart-impl(j));
            }
            i--;
        }
        return 0;
    }

    public static /* synthetic */ int getPrevWordOffset$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getPrevWordOffset");
            return 0;
        }
        if ((i2 & 1) != 0) {
            i = baseTextPreparedSelection.transformedEndOffset();
        }
        return baseTextPreparedSelection.getPrevWordOffset(textLayoutResult, i);
    }

    private final boolean isLtr() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        return (textLayoutResult != null ? textLayoutResult.getParagraphDirection(transformedEndOffset()) : null) != ResolvedTextDirection.Rtl;
    }

    private final int jumpByLinesOffset(TextLayoutResult textLayoutResult, int i) {
        int iTransformedEndOffset = transformedEndOffset();
        if (this.state.getCachedX() == null) {
            this.state.setCachedX(Float.valueOf(textLayoutResult.getCursorRect(iTransformedEndOffset).getLeft()));
        }
        int lineForOffset = textLayoutResult.getLineForOffset(iTransformedEndOffset) + i;
        if (lineForOffset < 0) {
            return 0;
        }
        if (lineForOffset >= textLayoutResult.getLineCount()) {
            return getText$foundation().length();
        }
        float lineBottom = textLayoutResult.getLineBottom(lineForOffset) - 1.0f;
        Float cachedX = this.state.getCachedX();
        cachedX.getClass();
        float fFloatValue = cachedX.floatValue();
        if ((isLtr() && fFloatValue >= textLayoutResult.getLineRight(lineForOffset)) || (!isLtr() && fFloatValue <= textLayoutResult.getLineLeft(lineForOffset))) {
            return textLayoutResult.getLineEnd(lineForOffset, true);
        }
        return this.offsetMapping.transformedToOriginal(textLayoutResult.getOffsetForPosition-k-4lQ0M(Offset.constructor-impl((((long) Float.floatToRawIntBits(cachedX.floatValue())) << 32) | (((long) Float.floatToRawIntBits(lineBottom)) & 4294967295L))));
    }

    private final T moveCursorNext() {
        int nextCharacterIndex;
        getState().resetCachedX();
        if (getText$foundation().length() > 0 && (nextCharacterIndex = getNextCharacterIndex()) != -1) {
            setCursor(nextCharacterIndex);
        }
        return this;
    }

    private final T moveCursorNextByWord() {
        Integer nextWordOffset;
        getState().resetCachedX();
        if (getText$foundation().length() > 0 && (nextWordOffset = getNextWordOffset()) != null) {
            setCursor(nextWordOffset.intValue());
        }
        return this;
    }

    private final T moveCursorPrev() {
        int precedingCharacterIndex;
        getState().resetCachedX();
        if (getText$foundation().length() > 0 && (precedingCharacterIndex = getPrecedingCharacterIndex()) != -1) {
            setCursor(precedingCharacterIndex);
        }
        return this;
    }

    private final T moveCursorPrevByWord() {
        Integer previousWordOffset;
        getState().resetCachedX();
        if (getText$foundation().length() > 0 && (previousWordOffset = getPreviousWordOffset()) != null) {
            setCursor(previousWordOffset.intValue());
        }
        return this;
    }

    private final int transformedEndOffset() {
        return this.offsetMapping.originalToTransformed(TextRange.getEnd-impl(this.selection));
    }

    private final int transformedMaxOffset() {
        return this.offsetMapping.originalToTransformed(TextRange.getMax-impl(this.selection));
    }

    private final int transformedMinOffset() {
        return this.offsetMapping.originalToTransformed(TextRange.getMin-impl(this.selection));
    }

    public final <U> T apply(U u, boolean z, Function1<? super U, Unit> function1) {
        if (z) {
            getState().resetCachedX();
        }
        if (getText$foundation().length() > 0) {
            function1.invoke(u);
        }
        u.getClass();
        return (T) u;
    }

    public final T collapseLeftOr(Function1<? super T, Unit> or) {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (TextRange.getCollapsed-impl(this.selection)) {
                or.invoke(this);
            } else {
                boolean zIsLtr = isLtr();
                long j = this.selection;
                if (zIsLtr) {
                    setCursor(TextRange.getMin-impl(j));
                } else {
                    setCursor(TextRange.getMax-impl(j));
                }
            }
        }
        return this;
    }

    public final T collapseRightOr(Function1<? super T, Unit> or) {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (TextRange.getCollapsed-impl(this.selection)) {
                or.invoke(this);
            } else {
                boolean zIsLtr = isLtr();
                long j = this.selection;
                if (zIsLtr) {
                    setCursor(TextRange.getMax-impl(j));
                } else {
                    setCursor(TextRange.getMin-impl(j));
                }
            }
        }
        return this;
    }

    public final T deselect() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            setCursor(TextRange.getEnd-impl(this.selection));
        }
        return this;
    }

    public final AnnotatedString getAnnotatedString() {
        return this.annotatedString;
    }

    public final TextLayoutResult getLayoutResult() {
        return this.layoutResult;
    }

    public final Integer getLineEndByOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getLineEndByOffsetForLayout$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    public final Integer getLineStartByOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getLineStartByOffsetForLayout$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    public final int getNextCharacterIndex() {
        return StringHelpers_androidKt.findFollowingBreak(this.annotatedString.getText(), TextRange.getEnd-impl(this.selection));
    }

    public final Integer getNextWordOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getNextWordOffsetForLayout$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    /* JADX INFO: renamed from: getOriginalSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getOriginalSelection() {
        return this.originalSelection;
    }

    public final AnnotatedString getOriginalText() {
        return this.originalText;
    }

    public final int getPrecedingCharacterIndex() {
        return StringHelpers_androidKt.findPrecedingBreak(this.annotatedString.getText(), TextRange.getEnd-impl(this.selection));
    }

    public final int getPrecedingCodePointOrEmojiStartIndex() {
        return StringHelpers_androidKt.findCodePointOrEmojiStartBefore(this.annotatedString.getText(), TextRange.getEnd-impl(this.selection), -1);
    }

    public final Integer getPreviousWordOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getPrevWordOffset$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelection() {
        return this.selection;
    }

    public final TextPreparedSelectionState getState() {
        return this.state;
    }

    public final String getText$foundation() {
        return this.annotatedString.getText();
    }

    public final T moveCursorDownByLine() {
        TextLayoutResult textLayoutResult;
        if (getText$foundation().length() > 0 && (textLayoutResult = this.layoutResult) != null) {
            setCursor(jumpByLinesOffset(textLayoutResult, 1));
        }
        return this;
    }

    public final T moveCursorLeft() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (isLtr()) {
                moveCursorPrev();
            } else {
                moveCursorNext();
            }
        }
        return this;
    }

    public final T moveCursorLeftByWord() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (isLtr()) {
                moveCursorPrevByWord();
            } else {
                moveCursorNextByWord();
            }
        }
        return this;
    }

    public final T moveCursorNextByParagraph() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            int iFindParagraphEnd = StringHelpersKt.findParagraphEnd(getText$foundation(), TextRange.getMax-impl(this.selection));
            if (iFindParagraphEnd == TextRange.getMax-impl(this.selection) && iFindParagraphEnd != getText$foundation().length()) {
                iFindParagraphEnd = StringHelpersKt.findParagraphEnd(getText$foundation(), iFindParagraphEnd + 1);
            }
            setCursor(iFindParagraphEnd);
        }
        return this;
    }

    public final T moveCursorPrevByParagraph() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            int iFindParagraphStart = StringHelpersKt.findParagraphStart(getText$foundation(), TextRange.getMin-impl(this.selection));
            if (iFindParagraphStart == TextRange.getMin-impl(this.selection) && iFindParagraphStart != 0) {
                iFindParagraphStart = StringHelpersKt.findParagraphStart(getText$foundation(), iFindParagraphStart - 1);
            }
            setCursor(iFindParagraphStart);
        }
        return this;
    }

    public final T moveCursorRight() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (isLtr()) {
                moveCursorNext();
            } else {
                moveCursorPrev();
            }
        }
        return this;
    }

    public final T moveCursorRightByWord() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (isLtr()) {
                moveCursorNextByWord();
            } else {
                moveCursorPrevByWord();
            }
        }
        return this;
    }

    public final T moveCursorToEnd() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            setCursor(getText$foundation().length());
        }
        return this;
    }

    public final T moveCursorToHome() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            setCursor(0);
        }
        return this;
    }

    public final T moveCursorToLineEnd() {
        Integer lineEndByOffset;
        getState().resetCachedX();
        if (getText$foundation().length() > 0 && (lineEndByOffset = getLineEndByOffset()) != null) {
            setCursor(lineEndByOffset.intValue());
        }
        return this;
    }

    public final T moveCursorToLineLeftSide() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (isLtr()) {
                moveCursorToLineStart();
            } else {
                moveCursorToLineEnd();
            }
        }
        return this;
    }

    public final T moveCursorToLineRightSide() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            if (isLtr()) {
                moveCursorToLineEnd();
            } else {
                moveCursorToLineStart();
            }
        }
        return this;
    }

    public final T moveCursorToLineStart() {
        Integer lineStartByOffset;
        getState().resetCachedX();
        if (getText$foundation().length() > 0 && (lineStartByOffset = getLineStartByOffset()) != null) {
            setCursor(lineStartByOffset.intValue());
        }
        return this;
    }

    public final T moveCursorUpByLine() {
        TextLayoutResult textLayoutResult;
        if (getText$foundation().length() > 0 && (textLayoutResult = this.layoutResult) != null) {
            setCursor(jumpByLinesOffset(textLayoutResult, -1));
        }
        return this;
    }

    public final T selectAll() {
        getState().resetCachedX();
        if (getText$foundation().length() > 0) {
            setSelection(0, getText$foundation().length());
        }
        return this;
    }

    public final T selectMovement() {
        if (getText$foundation().length() > 0) {
            this.selection = TextRangeKt.TextRange(TextRange.getStart-impl(this.originalSelection), TextRange.getEnd-impl(this.selection));
        }
        return this;
    }

    public final void setAnnotatedString(AnnotatedString annotatedString) {
        this.annotatedString = annotatedString;
    }

    public final void setCursor(int offset) {
        setSelection(offset, offset);
    }

    public final void setSelection(int start, int end) {
        this.selection = TextRangeKt.TextRange(start, end);
    }

    /* JADX INFO: renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1738setSelection5zctL8(long j) {
        this.selection = j;
    }

    public /* synthetic */ BaseTextPreparedSelection(AnnotatedString annotatedString, long j, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, TextPreparedSelectionState textPreparedSelectionState, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, j, textLayoutResult, offsetMapping, textPreparedSelectionState);
    }
}
