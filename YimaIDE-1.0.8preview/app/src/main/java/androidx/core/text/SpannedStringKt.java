package androidx.core.text;

import android.text.Spanned;
import android.text.SpannedString;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a:\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\u0004\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0086\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"toSpanned", "Landroid/text/Spanned;", "", "getSpans", "", "T", "", "start", "", "end", "(Landroid/text/Spanned;II)[Ljava/lang/Object;", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SpannedStringKt {
    public static final /* synthetic */ <T> T[] getSpans(Spanned spanned, int i, int i2) {
        spanned.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        T[] tArr = (T[]) spanned.getSpans(i, i2, Object.class);
        tArr.getClass();
        return tArr;
    }

    public static /* synthetic */ Object[] getSpans$default(Spanned spanned, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = spanned.length();
        }
        spanned.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        Object[] spans = spanned.getSpans(i, i2, Object.class);
        spans.getClass();
        return spans;
    }

    public static final Spanned toSpanned(CharSequence charSequence) {
        charSequence.getClass();
        SpannedString spannedStringValueOf = SpannedString.valueOf(charSequence);
        spannedStringValueOf.getClass();
        return spannedStringValueOf;
    }
}
