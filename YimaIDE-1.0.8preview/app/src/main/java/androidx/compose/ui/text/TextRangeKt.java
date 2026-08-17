package androidx.compose.ui.text;

import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000b\u001a\u0013\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\r\u001a!\u0010\u000e\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\u0015"}, d2 = {"substring", "", "", "range", "Landroidx/compose/ui/text/TextRange;", "substring-FDrldGo", "(Ljava/lang/CharSequence;J)Ljava/lang/String;", "TextRange", "start", "", "end", "(II)J", "index", "(I)J", "coerceIn", "minimumValue", "maximumValue", "coerceIn-8ffj60Q", "(JII)J", "packWithCheck", "", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextRangeKt {
    public static final long TextRange(int i, int i2) {
        return TextRange.m5468constructorimpl(packWithCheck(i, i2));
    }

    /* JADX INFO: renamed from: coerceIn-8ffj60Q, reason: not valid java name */
    public static final long m5485coerceIn8ffj60Q(long j, int i, int i2) {
        int iM5479getStartimpl = TextRange.m5479getStartimpl(j);
        if (iM5479getStartimpl < i) {
            iM5479getStartimpl = i;
        }
        if (iM5479getStartimpl > i2) {
            iM5479getStartimpl = i2;
        }
        int iM5474getEndimpl = TextRange.m5474getEndimpl(j);
        if (iM5474getEndimpl >= i) {
            i = iM5474getEndimpl;
        }
        if (i <= i2) {
            i2 = i;
        }
        return (iM5479getStartimpl == TextRange.m5479getStartimpl(j) && i2 == TextRange.m5474getEndimpl(j)) ? j : TextRange(iM5479getStartimpl, i2);
    }

    private static final long packWithCheck(int i, int i2) {
        if (!(i >= 0 && i2 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("start and end cannot be negative. [start: " + i + ", end: " + i2 + ']');
        }
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    /* JADX INFO: renamed from: substring-FDrldGo, reason: not valid java name */
    public static final String m5486substringFDrldGo(CharSequence charSequence, long j) {
        return charSequence.subSequence(TextRange.m5477getMinimpl(j), TextRange.m5476getMaximpl(j)).toString();
    }

    public static final long TextRange(int i) {
        return TextRange(i, i);
    }
}
