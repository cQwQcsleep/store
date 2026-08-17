package androidx.compose.foundation.text.input;

import androidx.compose.foundation.text.input.internal.ToCharArray_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001Bw\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007\u0012\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\r\u0018\u00010\n\u0012\u001a\b\u0002\u0010\u000e\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\r\u0018\u00010\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0015H\u0096\u0002J\u0018\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u0015H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0001J&\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u00020\u0015J\u0006\u00103\u001a\u00020*J\u0013\u00104\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u000105H\u0096\u0002J\b\u00106\u001a\u00020\u0015H\u0016R#\u0010\t\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\r\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R#\u0010\u000e\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\r\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0003\u001a\u00020\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00067"}, d2 = {"Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "", "text", "selection", "Landroidx/compose/ui/text/TextRange;", "composition", "highlight", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/input/TextHighlightType;", "composingAnnotations", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "Landroidx/compose/foundation/text/input/PlacedAnnotation;", "outputAnnotations", "<init>", "(Ljava/lang/CharSequence;JLandroidx/compose/ui/text/TextRange;Lkotlin/Pair;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getComposingAnnotations", "()Ljava/util/List;", "getOutputAnnotations", "length", "", "getLength", "()I", "getText", "()Ljava/lang/CharSequence;", "getSelection-d9O1mEE", "()J", "J", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "getHighlight", "()Lkotlin/Pair;", "get", "", "index", "subSequence", "startIndex", "endIndex", "toString", "", "contentEquals", "", "other", "toCharArray", "", "destination", "", "destinationOffset", "sourceStartIndex", "sourceEndIndex", "shouldShowSelection", "equals", "", "hashCode", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldCharSequence implements CharSequence {
    public static final int $stable = 8;
    private final List<AnnotatedString.Range<AnnotatedString.Annotation>> composingAnnotations;
    private final TextRange composition;
    private final Pair<TextHighlightType, TextRange> highlight;
    private final List<AnnotatedString.Range<AnnotatedString.Annotation>> outputAnnotations;
    private final long selection;
    private final CharSequence text;

    private TextFieldCharSequence(CharSequence charSequence, long j, TextRange textRange, Pair<TextHighlightType, TextRange> pair, List<AnnotatedString.Range<AnnotatedString.Annotation>> list, List<AnnotatedString.Range<AnnotatedString.Annotation>> list2) {
        this.composingAnnotations = list;
        this.outputAnnotations = list2;
        this.text = charSequence instanceof TextFieldCharSequence ? ((TextFieldCharSequence) charSequence).text : charSequence;
        this.selection = TextRangeKt.coerceIn-8ffj60Q(j, 0, charSequence.length());
        this.composition = textRange != null ? TextRange.box-impl(TextRangeKt.coerceIn-8ffj60Q(textRange.unbox-impl(), 0, charSequence.length())) : null;
        this.highlight = pair != null ? Pair.copy$default(pair, (Object) null, TextRange.box-impl(TextRangeKt.coerceIn-8ffj60Q(((TextRange) pair.getSecond()).unbox-impl(), 0, charSequence.length())), 1, (Object) null) : null;
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i) {
        return get(i);
    }

    public final boolean contentEquals(CharSequence other) {
        return StringsKt.contentEquals(this.text, other);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || TextFieldCharSequence.class != other.getClass()) {
            return false;
        }
        TextFieldCharSequence textFieldCharSequence = (TextFieldCharSequence) other;
        return TextRange.equals-impl0(this.selection, textFieldCharSequence.selection) && Intrinsics.areEqual(this.composition, textFieldCharSequence.composition) && Intrinsics.areEqual(this.highlight, textFieldCharSequence.highlight) && Intrinsics.areEqual(this.composingAnnotations, textFieldCharSequence.composingAnnotations) && contentEquals(textFieldCharSequence.text);
    }

    public char get(int index) {
        return this.text.charAt(index);
    }

    public final List<AnnotatedString.Range<AnnotatedString.Annotation>> getComposingAnnotations() {
        return this.composingAnnotations;
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA, reason: not valid java name and from getter */
    public final TextRange getComposition() {
        return this.composition;
    }

    public final Pair<TextHighlightType, TextRange> getHighlight() {
        return this.highlight;
    }

    public int getLength() {
        return this.text.length();
    }

    public final List<AnnotatedString.Range<AnnotatedString.Annotation>> getOutputAnnotations() {
        return this.outputAnnotations;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelection() {
        return this.selection;
    }

    public final CharSequence getText() {
        return this.text;
    }

    public int hashCode() {
        int iHashCode = ((this.text.hashCode() * 31) + TextRange.hashCode-impl(this.selection)) * 31;
        TextRange textRange = this.composition;
        int i = (iHashCode + (textRange != null ? TextRange.hashCode-impl(textRange.unbox-impl()) : 0)) * 31;
        Pair<TextHighlightType, TextRange> pair = this.highlight;
        int iHashCode2 = (i + (pair != null ? pair.hashCode() : 0)) * 31;
        List<AnnotatedString.Range<AnnotatedString.Annotation>> list = this.composingAnnotations;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    public final boolean shouldShowSelection() {
        return this.highlight == null;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int startIndex, int endIndex) {
        return this.text.subSequence(startIndex, endIndex);
    }

    public final void toCharArray(char[] destination, int destinationOffset, int sourceStartIndex, int sourceEndIndex) {
        ToCharArray_androidKt.toCharArray(this.text, destination, destinationOffset, sourceStartIndex, sourceEndIndex);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.text.toString();
    }

    public /* synthetic */ TextFieldCharSequence(CharSequence charSequence, long j, TextRange textRange, Pair pair, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(charSequence, j, textRange, pair, list, list2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TextFieldCharSequence(CharSequence charSequence, long j, TextRange textRange, Pair pair, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        CharSequence charSequence2 = (i & 1) != 0 ? "" : charSequence;
        long j2 = (i & 2) != 0 ? TextRange.Companion.getZero-d9O1mEE() : j;
        TextRange textRange2 = (i & 4) != 0 ? null : textRange;
        Pair pair2 = (i & 8) != 0 ? null : pair;
        List list3 = (i & 16) != 0 ? null : list;
        this(charSequence2, j2, textRange2, pair2, list3, (i & 32) == 0 ? list2 : null, null);
    }
}
