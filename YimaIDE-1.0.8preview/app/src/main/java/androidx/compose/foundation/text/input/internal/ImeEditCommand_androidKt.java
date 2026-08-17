package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldBufferKt;
import androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000\u001a8\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001a\b\u0002\u0010\u000b\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000e0\rj\u0002`\u000f\u0018\u00010\fH\u0000\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0000\u001a\f\u0010\u0014\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u001c\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u001a$\u0010\u001b\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u001dH\u0001\u001a\u001c\u0010\u001e\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0001¨\u0006\u001f"}, d2 = {"commitText", "", "Landroidx/compose/foundation/text/input/internal/ImeEditCommandScope;", "text", "", "newCursorPosition", "", "setComposingRegion", "start", "end", "setComposingText", "annotations", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "Landroidx/compose/foundation/text/input/PlacedAnnotation;", "deleteSurroundingText", "lengthBeforeCursor", "lengthAfterCursor", "deleteSurroundingTextInCodePoints", "finishComposingText", "setSelection", "isSurrogatePair", "", "high", "", "low", "imeReplace", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "", "imeDelete", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ImeEditCommand_androidKt {
    public static Unit a(String str, int i, TextFieldBuffer textFieldBuffer) {
        TextRange composition = textFieldBuffer.getComposition();
        if (composition != null) {
            imeReplace(textFieldBuffer, TextRange.getStart-impl(composition.unbox-impl()), TextRange.getEnd-impl(composition.unbox-impl()), str);
        } else {
            imeReplace(textFieldBuffer, TextRange.getStart-impl(textFieldBuffer.getSelectionInChars()), TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()), str);
        }
        int i2 = TextRange.getStart-impl(textFieldBuffer.getSelectionInChars());
        textFieldBuffer.m1482setSelection5zctL8(TextRangeKt.TextRange(RangesKt.coerceIn(i > 0 ? (i2 + i) - 1 : (i2 + i) - str.length(), 0, textFieldBuffer.getLength())));
        return Unit.INSTANCE;
    }

    public static Unit b(String str, List list, int i, TextFieldBuffer textFieldBuffer) {
        TextRange composition = textFieldBuffer.getComposition();
        if (composition != null) {
            imeReplace(textFieldBuffer, TextRange.getStart-impl(composition.unbox-impl()), TextRange.getEnd-impl(composition.unbox-impl()), str);
            if (str.length() > 0) {
                textFieldBuffer.setComposition$foundation(TextRange.getStart-impl(composition.unbox-impl()), TextRange.getStart-impl(composition.unbox-impl()) + str.length(), list);
            }
        } else {
            int i2 = TextRange.getStart-impl(textFieldBuffer.getSelectionInChars());
            imeReplace(textFieldBuffer, i2, TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()), str);
            if (str.length() > 0) {
                textFieldBuffer.setComposition$foundation(i2, str.length() + i2, list);
            }
        }
        int i3 = TextRange.getStart-impl(textFieldBuffer.getSelectionInChars());
        textFieldBuffer.m1482setSelection5zctL8(TextRangeKt.TextRange(RangesKt.coerceIn(i > 0 ? (i3 + i) - 1 : (i3 + i) - str.length(), 0, textFieldBuffer.getLength())));
        return Unit.INSTANCE;
    }

    public static Unit c(int i, int i2, ImeEditCommandScope imeEditCommandScope, TextFieldBuffer textFieldBuffer) {
        if (i < 0 || i2 < 0) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i + " and " + i2 + " respectively.");
        }
        long jMo1520mapToTransformedGEjPoXI = imeEditCommandScope.mo1520mapToTransformedGEjPoXI(textFieldBuffer.getSelectionInChars());
        int i3 = TextRange.getEnd-impl(jMo1520mapToTransformedGEjPoXI);
        int length = i3 + i2;
        if (((i2 ^ length) & (i3 ^ length)) < 0) {
            length = textFieldBuffer.getLength();
        }
        long jMo1519mapFromTransformedGEjPoXI = imeEditCommandScope.mo1519mapFromTransformedGEjPoXI(TextRangeKt.TextRange(TextRange.getEnd-impl(jMo1520mapToTransformedGEjPoXI), Math.min(length, textFieldBuffer.getLength())));
        imeDelete(textFieldBuffer, TextRange.getMin-impl(jMo1519mapFromTransformedGEjPoXI), TextRange.getMax-impl(jMo1519mapFromTransformedGEjPoXI));
        int i4 = TextRange.getStart-impl(jMo1520mapToTransformedGEjPoXI);
        int i5 = i4 - i;
        if (((i ^ i4) & (i4 ^ i5)) < 0) {
            i5 = 0;
        }
        long jMo1519mapFromTransformedGEjPoXI2 = imeEditCommandScope.mo1519mapFromTransformedGEjPoXI(TextRangeKt.TextRange(Math.max(0, i5), TextRange.getStart-impl(jMo1520mapToTransformedGEjPoXI)));
        imeDelete(textFieldBuffer, TextRange.getMin-impl(jMo1519mapFromTransformedGEjPoXI2), TextRange.getMax-impl(jMo1519mapFromTransformedGEjPoXI2));
        return Unit.INSTANCE;
    }

    public static final void commitText(ImeEditCommandScope imeEditCommandScope, final String str, final int i) {
        imeEditCommandScope.edit(new Function1() { // from class: jk6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.a(str, i, (TextFieldBuffer) obj);
            }
        });
    }

    public static Unit d(ImeEditCommandScope imeEditCommandScope, int i, int i2, TextFieldBuffer textFieldBuffer) {
        long jMo1520mapToTransformedGEjPoXI = imeEditCommandScope.mo1520mapToTransformedGEjPoXI(TextRangeKt.TextRange(0, textFieldBuffer.getLength()));
        int i3 = TextRange.getMin-impl(jMo1520mapToTransformedGEjPoXI);
        int i4 = TextRange.getMax-impl(jMo1520mapToTransformedGEjPoXI);
        if (i < i3) {
            i = i3;
        }
        if (i <= i4) {
            i4 = i;
        }
        int i5 = TextRange.getMin-impl(jMo1520mapToTransformedGEjPoXI);
        int i6 = TextRange.getMax-impl(jMo1520mapToTransformedGEjPoXI);
        if (i2 < i5) {
            i2 = i5;
        }
        if (i2 <= i6) {
            i6 = i2;
        }
        textFieldBuffer.m1482setSelection5zctL8(imeEditCommandScope.mo1519mapFromTransformedGEjPoXI(TextRangeKt.TextRange(i4, i6)));
        return Unit.INSTANCE;
    }

    public static final void deleteSurroundingText(final ImeEditCommandScope imeEditCommandScope, final int i, final int i2) {
        imeEditCommandScope.edit(new Function1() { // from class: kk6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.c(i, i2, imeEditCommandScope, (TextFieldBuffer) obj);
            }
        });
    }

    public static final void deleteSurroundingTextInCodePoints(ImeEditCommandScope imeEditCommandScope, final int i, final int i2) {
        imeEditCommandScope.edit(new Function1() { // from class: gk6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.e(i, i2, (TextFieldBuffer) obj);
            }
        });
    }

    public static Unit e(int i, int i2, TextFieldBuffer textFieldBuffer) {
        if (i < 0 || i2 < 0) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i + " and " + i2 + " respectively.");
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 + 1;
            if (TextRange.getStart-impl(textFieldBuffer.getSelectionInChars()) <= i5) {
                i3 = TextRange.getStart-impl(textFieldBuffer.getSelectionInChars());
                break;
            }
            i3 = isSurrogatePair(textFieldBuffer.asCharSequence().charAt((TextRange.getStart-impl(textFieldBuffer.getSelectionInChars()) - i5) + (-1)), textFieldBuffer.asCharSequence().charAt(TextRange.getStart-impl(textFieldBuffer.getSelectionInChars()) - i5)) ? i3 + 2 : i5;
        }
        int length = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = length + 1;
            if (TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()) + i7 >= textFieldBuffer.getLength()) {
                length = textFieldBuffer.getLength() - TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars());
                break;
            }
            length = isSurrogatePair(textFieldBuffer.asCharSequence().charAt((TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()) + i7) + (-1)), textFieldBuffer.asCharSequence().charAt(TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()) + i7)) ? length + 2 : i7;
        }
        imeDelete(textFieldBuffer, TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()), TextRange.getEnd-impl(textFieldBuffer.getSelectionInChars()) + length);
        imeDelete(textFieldBuffer, TextRange.getStart-impl(textFieldBuffer.getSelectionInChars()) - i3, TextRange.getStart-impl(textFieldBuffer.getSelectionInChars()));
        return Unit.INSTANCE;
    }

    public static Unit f(int i, int i2, TextFieldBuffer textFieldBuffer) {
        if (textFieldBuffer.hasComposition$foundation()) {
            textFieldBuffer.commitComposition$foundation();
        }
        int iCoerceIn = RangesKt.coerceIn(i, 0, textFieldBuffer.getLength());
        int iCoerceIn2 = RangesKt.coerceIn(i2, 0, textFieldBuffer.getLength());
        if (iCoerceIn != iCoerceIn2) {
            if (iCoerceIn < iCoerceIn2) {
                TextFieldBuffer.setComposition$foundation$default(textFieldBuffer, iCoerceIn, iCoerceIn2, null, 4, null);
            } else {
                TextFieldBuffer.setComposition$foundation$default(textFieldBuffer, iCoerceIn2, iCoerceIn, null, 4, null);
            }
        }
        return Unit.INSTANCE;
    }

    public static final void finishComposingText(ImeEditCommandScope imeEditCommandScope) {
        imeEditCommandScope.edit(new Function1() { // from class: lk6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.g((TextFieldBuffer) obj);
            }
        });
    }

    public static Unit g(TextFieldBuffer textFieldBuffer) {
        textFieldBuffer.commitComposition$foundation();
        return Unit.INSTANCE;
    }

    public static final void imeDelete(TextFieldBuffer textFieldBuffer, int i, int i2) {
        TextRange composition = textFieldBuffer.getComposition();
        int iMin = Math.min(i, i2);
        int iMax = Math.max(i, i2);
        TextFieldBufferKt.delete(textFieldBuffer, iMin, iMax);
        if (composition != null) {
            composition.unbox-impl();
            long jM1486adjustTextRangevJH6DeI = TextFieldBufferKt.m1486adjustTextRangevJH6DeI(composition.unbox-impl(), iMin, iMax, 0);
            if (TextRange.getCollapsed-impl(jM1486adjustTextRangevJH6DeI)) {
                textFieldBuffer.commitComposition$foundation();
            } else {
                TextFieldBuffer.setComposition$foundation$default(textFieldBuffer, TextRange.getMin-impl(jM1486adjustTextRangevJH6DeI), TextRange.getMax-impl(jM1486adjustTextRangevJH6DeI), null, 4, null);
            }
        }
    }

    public static final void imeReplace(TextFieldBuffer textFieldBuffer, int i, int i2, CharSequence charSequence) {
        int iMin = Math.min(i, i2);
        int iMax = Math.max(i, i2);
        int i3 = 0;
        int i4 = iMin;
        while (i4 < iMax && i3 < charSequence.length() && charSequence.charAt(i3) == textFieldBuffer.asCharSequence().charAt(i4)) {
            i3++;
            i4++;
        }
        int length = charSequence.length();
        while (iMax > i4 && length > i3 && charSequence.charAt(length - 1) == textFieldBuffer.asCharSequence().charAt(iMax - 1)) {
            length--;
            iMax--;
        }
        if (i4 == iMax && i3 == length) {
            textFieldBuffer.commitComposition$foundation();
            textFieldBuffer.clearHighlight$foundation();
        } else {
            textFieldBuffer.replace(i4, iMax, charSequence.subSequence(i3, length));
        }
        textFieldBuffer.m1482setSelection5zctL8(TextRangeKt.TextRange(iMin + charSequence.length()));
    }

    private static final boolean isSurrogatePair(char c, char c2) {
        return Character.isHighSurrogate(c) && Character.isLowSurrogate(c2);
    }

    public static final void setComposingRegion(ImeEditCommandScope imeEditCommandScope, final int i, final int i2) {
        imeEditCommandScope.edit(new Function1() { // from class: mk6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.f(i, i2, (TextFieldBuffer) obj);
            }
        });
    }

    public static final void setComposingText(ImeEditCommandScope imeEditCommandScope, final String str, final int i, final List<AnnotatedString.Range<AnnotatedString.Annotation>> list) {
        imeEditCommandScope.edit(new Function1() { // from class: ik6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.b(str, list, i, (TextFieldBuffer) obj);
            }
        });
    }

    public static /* synthetic */ void setComposingText$default(ImeEditCommandScope imeEditCommandScope, String str, int i, List list, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            list = null;
        }
        setComposingText(imeEditCommandScope, str, i, list);
    }

    public static final void setSelection(final ImeEditCommandScope imeEditCommandScope, final int i, final int i2) {
        imeEditCommandScope.edit(new Function1() { // from class: hk6
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.d(imeEditCommandScope, i, i2, (TextFieldBuffer) obj);
            }
        });
    }
}
