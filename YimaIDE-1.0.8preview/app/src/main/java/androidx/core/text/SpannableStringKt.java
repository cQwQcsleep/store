package androidx.core.text;

import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0001H\u0086\b\u001a%\u0010\u0005\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086\n\u001a\u001d\u0010\u0005\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0086\n¨\u0006\r"}, d2 = {"toSpannable", "Landroid/text/Spannable;", "", "clearSpans", "", "set", "start", "", "end", "span", "", "range", "Lkotlin/ranges/IntRange;", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SpannableStringKt {
    public static final void clearSpans(Spannable spannable) {
        spannable.getClass();
        Object[] spans = spannable.getSpans(0, spannable.length(), Object.class);
        spans.getClass();
        for (Object obj : spans) {
            spannable.removeSpan(obj);
        }
    }

    public static final void set(Spannable spannable, IntRange intRange, Object obj) {
        spannable.getClass();
        intRange.getClass();
        obj.getClass();
        spannable.setSpan(obj, intRange.getStart().intValue(), intRange.getEndInclusive().intValue(), 17);
    }

    public static final Spannable toSpannable(CharSequence charSequence) {
        charSequence.getClass();
        SpannableString spannableStringValueOf = SpannableString.valueOf(charSequence);
        spannableStringValueOf.getClass();
        return spannableStringValueOf;
    }

    public static final void set(Spannable spannable, int i, int i2, Object obj) {
        spannable.getClass();
        obj.getClass();
        spannable.setSpan(obj, i, i2, 17);
    }
}
