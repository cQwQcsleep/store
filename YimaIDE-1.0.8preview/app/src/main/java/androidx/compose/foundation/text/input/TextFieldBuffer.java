package androidx.compose.foundation.text.input;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.internal.ChangeTracker;
import androidx.compose.foundation.text.input.internal.OffsetMappingCalculator;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.ParagraphStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002\u0086\u0001B3\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010;\u001a\u00020'H\u0000¢\u0006\u0002\b<J\r\u0010=\u001a\u00020>H\u0000¢\u0006\u0002\b?J9\u0010@\u001a\u00020>2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00152\u001a\b\u0002\u0010C\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010DH\u0000¢\u0006\u0002\bFJ'\u0010L\u001a\u00020>2\u0006\u0010M\u001a\u00020H2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015H\u0000¢\u0006\u0004\bN\u0010OJ\r\u0010P\u001a\u00020>H\u0000¢\u0006\u0002\bQJ\u001e\u0010R\u001a\u00020>2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u0019J9\u0010R\u001a\u00020>2\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u00192\b\b\u0002\u0010T\u001a\u00020\u00152\b\b\u0002\u0010U\u001a\u00020\u0015H\u0000¢\u0006\u0002\bVJ\u0015\u0010W\u001a\u00020>2\u0006\u0010X\u001a\u00020\u0019H\u0000¢\u0006\u0002\bYJ\u0016\u0010Z\u001a\u00060\u0001j\u0002`\u00022\b\u0010S\u001a\u0004\u0018\u00010\u0019H\u0016J&\u0010Z\u001a\u00060\u0001j\u0002`\u00022\b\u0010S\u001a\u0004\u0018\u00010\u00192\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015H\u0016J\u0014\u0010Z\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010[\u001a\u00020\\H\u0016J \u0010]\u001a\u00020>2\u0006\u0010^\u001a\u00020\u00152\u0006\u0010_\u001a\u00020\u00152\u0006\u0010`\u001a\u00020\u0015H\u0002J\u000e\u0010a\u001a\u00020\\2\u0006\u0010b\u001a\u00020\u0015J\b\u0010c\u001a\u00020dH\u0016J\u0006\u0010e\u001a\u00020\u0019J\b\u0010f\u001a\u00020>H\u0002J\u0006\u0010g\u001a\u00020>J\u000e\u0010h\u001a\u00020>2\u0006\u0010b\u001a\u00020\u0015J\u000e\u0010i\u001a\u00020>2\u0006\u0010b\u001a\u00020\u0015J]\u0010j\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u001d2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001d2\u001a\b\u0002\u00108\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010D2\u001a\b\u0002\u0010k\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010DH\u0000¢\u0006\u0004\bl\u0010mJ \u0010n\u001a\u00020>2\u0006\u0010b\u001a\u00020\u00152\u0006\u0010o\u001a\u00020'2\u0006\u0010p\u001a\u00020'H\u0002J\u0017\u0010q\u001a\u00020>2\u0006\u0010r\u001a\u00020\u001dH\u0002¢\u0006\u0004\bs\u0010/J&\u0010~\u001a\u00020>2\u0006\u0010\u007f\u001a\u0002072\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015H\u0000¢\u0006\u0003\b\u0080\u0001J!\u0010\u0081\u0001\u001a\u00020>2\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015J!\u0010\u0081\u0001\u001a\u00020>2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0006\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u0015R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'8G¢\u0006\u0006\u001a\u0004\b&\u0010(R\u0010\u0010)\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0004\n\u0002\u0010*R$\u0010,\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u001d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010\u001f\"\u0004\b.\u0010/R(\u00100\u001a\u0004\u0018\u00010\u001d2\b\u0010+\u001a\u0004\u0018\u00010\u001d@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R:\u00108\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020706\u0018\u0001052\u0014\u0010+\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020706\u0018\u000105@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R:\u0010I\u001a\u0010\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u001d\u0018\u00010G2\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u001d\u0018\u00010G@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u001a\u0010t\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010(\"\u0004\bv\u0010wR,\u0010x\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020706j\u0002`E\u0018\u00010yX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}¨\u0006\u0087\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "initialValue", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "initialChanges", "Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "originalValue", "offsetMappingCalculator", "Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "<init>", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/ChangeTracker;Landroidx/compose/foundation/text/input/TextFieldCharSequence;Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;)V", "getOriginalValue$foundation", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "buffer", "Landroidx/compose/foundation/text/input/internal/PartialGapBuffer;", "backingChangeTracker", "changeTracker", "getChangeTracker$foundation", "()Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "length", "", "getLength", "()I", "originalText", "", "getOriginalText", "()Ljava/lang/CharSequence;", "originalSelection", "Landroidx/compose/ui/text/TextRange;", "getOriginalSelection-d9O1mEE", "()J", "changes", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "getChanges$annotations", "()V", "getChanges", "()Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "hasSelection", "", "()Z", "selectionInChars", "J", "value", "selection", "getSelection-d9O1mEE", "setSelection-5zc-tL8", "(J)V", "composition", "getComposition-MzsxiRA$foundation", "()Landroidx/compose/ui/text/TextRange;", "setComposition-OEnZFl4", "(Landroidx/compose/ui/text/TextRange;)V", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "composingAnnotations", "getComposingAnnotations$foundation", "()Landroidx/compose/runtime/collection/MutableVector;", "hasComposition", "hasComposition$foundation", "commitComposition", "", "commitComposition$foundation", "setComposition", "start", "end", "annotations", "", "Landroidx/compose/foundation/text/input/PlacedAnnotation;", "setComposition$foundation", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/input/TextHighlightType;", "highlight", "getHighlight$foundation", "()Lkotlin/Pair;", "setHighlight", "type", "setHighlight-K7f2yys$foundation", "(III)V", "clearHighlight", "clearHighlight$foundation", "replace", "text", "textStart", "textEnd", "replace$foundation", "setTextIfChanged", "newText", "setTextIfChanged$foundation", "append", "char", "", "onTextWillChange", "replaceStart", "replaceEnd", "newLength", "charAt", "index", "toString", "", "asCharSequence", "clearChangeList", "revertAllChanges", "placeCursorBeforeCharAt", "placeCursorAfterCharAt", "toTextFieldCharSequence", "outputAnnotations", "toTextFieldCharSequence-wFTz33Y$foundation", "(JLandroidx/compose/ui/text/TextRange;Ljava/util/List;Ljava/util/List;)Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "requireValidIndex", "startExclusive", "endExclusive", "requireValidRange", "range", "requireValidRange-5zc-tL8", "canCallAddStyle", "getCanCallAddStyle$foundation", "setCanCallAddStyle$foundation", "(Z)V", "outputTransformationAnnotations", "", "getOutputTransformationAnnotations$foundation", "()Ljava/util/List;", "setOutputTransformationAnnotations$foundation", "(Ljava/util/List;)V", "addAnnotation", "annotation", "addAnnotation$foundation", "addStyle", "spanStyle", "Landroidx/compose/ui/text/SpanStyle;", "paragraphStyle", "Landroidx/compose/ui/text/ParagraphStyle;", "ChangeList", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldBuffer implements Appendable {
    public static final int $stable = 8;
    private ChangeTracker backingChangeTracker;
    private final PartialGapBuffer buffer;
    private boolean canCallAddStyle;
    private MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations;
    private TextRange composition;
    private Pair<TextHighlightType, TextRange> highlight;
    private final OffsetMappingCalculator offsetMappingCalculator;
    private final TextFieldCharSequence originalValue;
    private List<AnnotatedString.Range<AnnotatedString.Annotation>> outputTransformationAnnotations;
    private long selectionInChars;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\f\u0010\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "", "changeCount", "", "getChangeCount", "()I", "getRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getRange--jx7JFs", "(I)J", "getOriginalRange", "getOriginalRange--jx7JFs", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface ChangeList {
        int getChangeCount();

        /* JADX INFO: renamed from: getOriginalRange--jx7JFs, reason: not valid java name */
        long mo1484getOriginalRangejx7JFs(int changeIndex);

        /* JADX INFO: renamed from: getRange--jx7JFs, reason: not valid java name */
        long mo1485getRangejx7JFs(int changeIndex);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldBuffer(final TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, OffsetMappingCalculator offsetMappingCalculator) {
        this.originalValue = textFieldCharSequence2;
        this.offsetMappingCalculator = offsetMappingCalculator;
        this.buffer = new PartialGapBuffer(textFieldCharSequence);
        MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = null;
        this.backingChangeTracker = changeTracker != null ? new ChangeTracker(changeTracker) : null;
        this.selectionInChars = textFieldCharSequence.getSelection();
        this.composition = textFieldCharSequence.getComposition();
        List<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations = textFieldCharSequence.getComposingAnnotations();
        if (composingAnnotations != null && !composingAnnotations.isEmpty()) {
            int size = textFieldCharSequence.getComposingAnnotations().size();
            Function1 function1 = new Function1() { // from class: h5e
                public final Object invoke(Object obj) {
                    return TextFieldBuffer.a(textFieldCharSequence, ((Integer) obj).intValue());
                }
            };
            AnnotatedString.Range[] rangeArr = new AnnotatedString.Range[size];
            for (int i = 0; i < size; i++) {
                rangeArr[i] = function1.invoke(Integer.valueOf(i));
            }
            mutableVector = new MutableVector<>(rangeArr, size);
        }
        this.composingAnnotations = mutableVector;
        this.canCallAddStyle = this.offsetMappingCalculator != null;
    }

    public static AnnotatedString.Range a(TextFieldCharSequence textFieldCharSequence, int i) {
        return textFieldCharSequence.getComposingAnnotations().get(i);
    }

    private final void clearChangeList() {
        getChangeTracker$foundation().clearChanges();
    }

    public static /* synthetic */ void getChanges$annotations() {
    }

    private final void onTextWillChange(int replaceStart, int replaceEnd, int newLength) {
        getChangeTracker$foundation().trackChange(replaceStart, replaceEnd, newLength);
        OffsetMappingCalculator offsetMappingCalculator = this.offsetMappingCalculator;
        if (offsetMappingCalculator != null) {
            offsetMappingCalculator.recordEditOperation(replaceStart, replaceEnd, newLength);
        }
        this.selectionInChars = TextFieldBufferKt.m1486adjustTextRangevJH6DeI(getSelectionInChars(), replaceStart, replaceEnd, newLength);
    }

    public static /* synthetic */ void replace$foundation$default(TextFieldBuffer textFieldBuffer, int i, int i2, CharSequence charSequence, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i3 = 0;
        }
        int i6 = i3;
        if ((i5 & 16) != 0) {
            i4 = charSequence.length();
        }
        textFieldBuffer.replace$foundation(i, i2, charSequence, i6, i4);
    }

    private final void requireValidIndex(int index, boolean startExclusive, boolean endExclusive) {
        boolean z = false;
        int i = startExclusive ? 0 : -1;
        int length = getLength();
        if (!endExclusive) {
            length++;
        }
        if (i <= index && index < length) {
            z = true;
        }
        if (z) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Expected " + index + " to be in [" + i + ", " + length + ')');
    }

    /* JADX INFO: renamed from: requireValidRange-5zc-tL8, reason: not valid java name */
    private final void m1475requireValidRange5zctL8(long range) {
        long jTextRange = TextRangeKt.TextRange(0, getLength());
        if (TextRange.contains-5zc-tL8(jTextRange, range)) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Expected " + ((Object) TextRange.toString-impl(range)) + " to be in " + ((Object) TextRange.toString-impl(jTextRange)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void setComposition$foundation$default(TextFieldBuffer textFieldBuffer, int i, int i2, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            list = null;
        }
        textFieldBuffer.setComposition$foundation(i, i2, list);
    }

    /* JADX INFO: renamed from: setComposition-OEnZFl4, reason: not valid java name */
    private final void m1476setCompositionOEnZFl4(TextRange textRange) {
        if (textRange != null && !TextRange.getCollapsed-impl(textRange.unbox-impl())) {
            this.composition = textRange;
            return;
        }
        this.composition = null;
        MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = this.composingAnnotations;
        if (mutableVector != null) {
            mutableVector.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: toTextFieldCharSequence-wFTz33Y$foundation$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1477toTextFieldCharSequencewFTz33Y$foundation$default(TextFieldBuffer textFieldBuffer, long j, TextRange textRange, List list, List list2, int i, Object obj) {
        List listAsMutableList;
        if ((i & 1) != 0) {
            j = textFieldBuffer.getSelectionInChars();
        }
        long j2 = j;
        if ((i & 2) != 0) {
            textRange = textFieldBuffer.composition;
        }
        TextRange textRange2 = textRange;
        if ((i & 4) != 0) {
            MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = textFieldBuffer.composingAnnotations;
            list = (mutableVector == null || (listAsMutableList = mutableVector.asMutableList()) == null || listAsMutableList.isEmpty()) ? null : listAsMutableList;
        }
        return textFieldBuffer.m1483toTextFieldCharSequencewFTz33Y$foundation(j2, textRange2, list, (i & 8) != 0 ? null : list2);
    }

    public final void addAnnotation$foundation(AnnotatedString.Annotation annotation, int start, int end) {
        if (!this.canCallAddStyle) {
            InlineClassHelperKt.throwIllegalStateException("You can add styling to a [TextFieldBuffer] only from an [OutputTransformation].");
        }
        if (this.outputTransformationAnnotations == null) {
            this.outputTransformationAnnotations = new ArrayList();
        }
        List<AnnotatedString.Range<AnnotatedString.Annotation>> list = this.outputTransformationAnnotations;
        if (list != null) {
            list.add(new AnnotatedString.Range<>(annotation, start, end));
        }
    }

    public final void addStyle(SpanStyle spanStyle, int start, int end) {
        addAnnotation$foundation(spanStyle, start, end);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text, int start, int end) {
        if (text != null) {
            onTextWillChange(getLength(), getLength(), end - start);
            PartialGapBuffer partialGapBuffer = this.buffer;
            PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), text.subSequence(start, end), 0, 0, 24, null);
        }
        return this;
    }

    public final CharSequence asCharSequence() {
        return this.buffer;
    }

    public final char charAt(int index) {
        return this.buffer.charAt(index);
    }

    public final void clearHighlight$foundation() {
        this.highlight = null;
    }

    public final void commitComposition$foundation() {
        m1476setCompositionOEnZFl4(null);
    }

    /* JADX INFO: renamed from: getCanCallAddStyle$foundation, reason: from getter */
    public final boolean getCanCallAddStyle() {
        return this.canCallAddStyle;
    }

    public final ChangeTracker getChangeTracker$foundation() {
        ChangeTracker changeTracker = this.backingChangeTracker;
        if (changeTracker != null) {
            return changeTracker;
        }
        ChangeTracker changeTracker2 = new ChangeTracker(null, 1, null);
        this.backingChangeTracker = changeTracker2;
        return changeTracker2;
    }

    public final ChangeList getChanges() {
        return getChangeTracker$foundation();
    }

    public final MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> getComposingAnnotations$foundation() {
        return this.composingAnnotations;
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA$foundation, reason: not valid java name and from getter */
    public final TextRange getComposition() {
        return this.composition;
    }

    public final Pair<TextHighlightType, TextRange> getHighlight$foundation() {
        return this.highlight;
    }

    public final int getLength() {
        return this.buffer.length();
    }

    /* JADX INFO: renamed from: getOriginalSelection-d9O1mEE, reason: not valid java name */
    public final long m1479getOriginalSelectiond9O1mEE() {
        return this.originalValue.getSelection();
    }

    public final CharSequence getOriginalText() {
        return this.originalValue.getText();
    }

    /* JADX INFO: renamed from: getOriginalValue$foundation, reason: from getter */
    public final TextFieldCharSequence getOriginalValue() {
        return this.originalValue;
    }

    public final List<AnnotatedString.Range<AnnotatedString.Annotation>> getOutputTransformationAnnotations$foundation() {
        return this.outputTransformationAnnotations;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelectionInChars() {
        return this.selectionInChars;
    }

    public final boolean hasComposition$foundation() {
        return this.composition != null;
    }

    public final boolean hasSelection() {
        return !TextRange.getCollapsed-impl(getSelectionInChars());
    }

    public final void placeCursorAfterCharAt(int index) {
        requireValidIndex(index, false, true);
        this.selectionInChars = TextRangeKt.TextRange(RangesKt.coerceAtMost(index + 1, getLength()));
    }

    public final void placeCursorBeforeCharAt(int index) {
        requireValidIndex(index, true, false);
        this.selectionInChars = TextRangeKt.TextRange(index);
    }

    public final void replace(int start, int end, CharSequence text) {
        replace$foundation(start, end, text, 0, text.length());
    }

    public final void replace$foundation(int start, int end, CharSequence text, int textStart, int textEnd) {
        if (!(start <= end)) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected start=" + start + " <= end=" + end);
        }
        if (!(textStart <= textEnd)) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected textStart=" + textStart + " <= textEnd=" + textEnd);
        }
        onTextWillChange(start, end, textEnd - textStart);
        this.buffer.replace(start, end, text, textStart, textEnd);
        commitComposition$foundation();
        clearHighlight$foundation();
    }

    public final void revertAllChanges() {
        replace(0, getLength(), this.originalValue.toString());
        m1482setSelection5zctL8(this.originalValue.getSelection());
        clearChangeList();
    }

    public final void setCanCallAddStyle$foundation(boolean z) {
        this.canCallAddStyle = z;
    }

    public final void setComposition$foundation(int start, int end, List<AnnotatedString.Range<AnnotatedString.Annotation>> annotations) {
        if (start < 0 || start > this.buffer.length()) {
            kac.a("start (", start, ") offset is outside of text region ", this.buffer.length());
            return;
        }
        if (end < 0 || end > this.buffer.length()) {
            kac.a("end (", end, ") offset is outside of text region ", this.buffer.length());
            return;
        }
        if (start >= end) {
            dn0.a("Do not set reversed or empty range: ", start, " > ", end);
            return;
        }
        m1476setCompositionOEnZFl4(TextRange.box-impl(TextRangeKt.TextRange(start, end)));
        MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector = this.composingAnnotations;
        if (mutableVector != null) {
            mutableVector.clear();
        }
        List<AnnotatedString.Range<AnnotatedString.Annotation>> list = annotations;
        if (list == null || list.isEmpty()) {
            return;
        }
        if (this.composingAnnotations == null) {
            this.composingAnnotations = new MutableVector<>(new AnnotatedString.Range[16], 0);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range<AnnotatedString.Annotation> range = annotations.get(i);
            MutableVector<AnnotatedString.Range<AnnotatedString.Annotation>> mutableVector2 = this.composingAnnotations;
            if (mutableVector2 != null) {
                mutableVector2.add(AnnotatedString.Range.copy$default(range, (Object) null, range.getStart() + start, range.getEnd() + start, (String) null, 9, (Object) null));
            }
        }
    }

    /* JADX INFO: renamed from: setHighlight-K7f2yys$foundation, reason: not valid java name */
    public final void m1481setHighlightK7f2yys$foundation(int type, int start, int end) {
        if (start >= end) {
            dn0.a("Do not set reversed or empty range: ", start, " > ", end);
            return;
        }
        this.highlight = new Pair<>(TextHighlightType.m1495boximpl(type), TextRange.box-impl(TextRangeKt.TextRange(RangesKt.coerceIn(start, 0, getLength()), RangesKt.coerceIn(end, 0, getLength()))));
    }

    public final void setOutputTransformationAnnotations$foundation(List<AnnotatedString.Range<AnnotatedString.Annotation>> list) {
        this.outputTransformationAnnotations = list;
    }

    /* JADX INFO: renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1482setSelection5zctL8(long j) {
        m1475requireValidRange5zctL8(j);
        this.selectionInChars = j;
        this.highlight = null;
    }

    public final void setTextIfChanged$foundation(CharSequence newText) {
        int i;
        int i2;
        PartialGapBuffer partialGapBuffer = this.buffer;
        int length = partialGapBuffer.length();
        int length2 = newText.length();
        boolean z = false;
        if (partialGapBuffer.length() <= 0 || newText.length() <= 0) {
            i = 0;
            i2 = 0;
        } else {
            int i3 = 0;
            int i4 = 0;
            boolean z2 = false;
            while (true) {
                if (!z) {
                    if (partialGapBuffer.charAt(i3) == newText.charAt(i4)) {
                        i3++;
                        i4++;
                    } else {
                        z = true;
                    }
                }
                if (!z2) {
                    if (partialGapBuffer.charAt(length - 1) == newText.charAt(length2 - 1)) {
                        length--;
                        length2--;
                    } else {
                        z2 = true;
                    }
                }
                if (i3 >= length || i4 >= length2 || (z && z2)) {
                    break;
                }
            }
            i = i3;
            i2 = i4;
        }
        int i5 = length;
        int i6 = length2;
        if (i < i5 || i2 < i6) {
            replace$foundation(i, i5, newText, i2, i6);
        }
    }

    public String toString() {
        return this.buffer.toString();
    }

    /* JADX INFO: renamed from: toTextFieldCharSequence-wFTz33Y$foundation, reason: not valid java name */
    public final TextFieldCharSequence m1483toTextFieldCharSequencewFTz33Y$foundation(long selection, TextRange composition, List<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations, List<AnnotatedString.Range<AnnotatedString.Annotation>> outputAnnotations) {
        return new TextFieldCharSequence(this.buffer.toString(), selection, composition, null, composingAnnotations, outputAnnotations, 8, null);
    }

    public final void addStyle(ParagraphStyle paragraphStyle, int start, int end) {
        addAnnotation$foundation(paragraphStyle, start, end);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text) {
        if (text != null) {
            onTextWillChange(getLength(), getLength(), text.length());
            PartialGapBuffer partialGapBuffer = this.buffer;
            PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), text, 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char c) {
        onTextWillChange(getLength(), getLength(), 1);
        PartialGapBuffer partialGapBuffer = this.buffer;
        PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), String.valueOf(c), 0, 0, 24, null);
        return this;
    }

    public /* synthetic */ TextFieldBuffer(TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, OffsetMappingCalculator offsetMappingCalculator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(textFieldCharSequence, (i & 2) != 0 ? null : changeTracker, (i & 4) != 0 ? textFieldCharSequence : textFieldCharSequence2, (i & 8) != 0 ? null : offsetMappingCalculator);
    }
}
