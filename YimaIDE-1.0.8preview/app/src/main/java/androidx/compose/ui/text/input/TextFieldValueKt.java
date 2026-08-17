package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0007"}, d2 = {"getTextBeforeSelection", "Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/text/input/TextFieldValue;", "maxChars", "", "getTextAfterSelection", "getSelectedText", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldValueKt {
    public static final AnnotatedString getSelectedText(TextFieldValue textFieldValue) {
        return textFieldValue.getAnnotatedString().subSequence-5zc-tL8(textFieldValue.getSelection-d9O1mEE());
    }

    public static final AnnotatedString getTextAfterSelection(TextFieldValue textFieldValue, int i) {
        AnnotatedString annotatedString = textFieldValue.getAnnotatedString();
        int i2 = TextRange.getMax-impl(textFieldValue.getSelection-d9O1mEE());
        int i3 = TextRange.getMax-impl(textFieldValue.getSelection-d9O1mEE());
        int length = i3 + i;
        if (((i ^ length) & (i3 ^ length)) < 0) {
            length = textFieldValue.getText().length();
        }
        return annotatedString.subSequence(i2, Math.min(length, textFieldValue.getText().length()));
    }

    public static final AnnotatedString getTextBeforeSelection(TextFieldValue textFieldValue, int i) {
        AnnotatedString annotatedString = textFieldValue.getAnnotatedString();
        int i2 = TextRange.getMin-impl(textFieldValue.getSelection-d9O1mEE());
        int i3 = i2 - i;
        if (((i ^ i2) & (i2 ^ i3)) < 0) {
            i3 = 0;
        }
        return annotatedString.subSequence(Math.max(0, i3), TextRange.getMin-impl(textFieldValue.getSelection-d9O1mEE()));
    }
}
