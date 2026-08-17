package androidx.compose.foundation.text.modifiers;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0082\u0002¢\u0006\u0004\b\u0003\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"times", "Landroidx/compose/ui/unit/TextUnit;", "other", "times-NB67dxo", "(JJ)J", "DefaultFontSize", "J", "foundation"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MultiParagraphLayoutCacheKt {
    private static final long DefaultFontSize = TextUnitKt.getSp(14);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: times-NB67dxo, reason: not valid java name */
    public static final long m68timesNB67dxo(long j, long j2) {
        if (!TextUnit.m6218isEmimpl(j2)) {
            throw new IllegalArgumentException("The multiplier must be in em, but was " + ((Object) TextUnit.m6223toStringimpl(j2)) + '.');
        }
        if (TextUnit.m6218isEmimpl(j)) {
            zia.a("Cannot convert Em to Px when style.fontSize is Em (", TextUnit.m6223toStringimpl(j2), "). Please declare the style.fontSize with Sp units instead.");
            return 0L;
        }
        if (TextUnit.m6214getRawTypeimpl(j) != 0) {
            float fM6216getValueimpl = TextUnit.m6216getValueimpl(j2);
            TextUnitKt.m6229checkArithmeticR2X_6o(j);
            return TextUnitKt.pack(TextUnit.m6214getRawTypeimpl(j), TextUnit.m6216getValueimpl(j) * fM6216getValueimpl);
        }
        long j3 = DefaultFontSize;
        float fM6216getValueimpl2 = TextUnit.m6216getValueimpl(j2);
        TextUnitKt.m6229checkArithmeticR2X_6o(j3);
        return TextUnitKt.pack(TextUnit.m6214getRawTypeimpl(j3), TextUnit.m6216getValueimpl(j3) * fM6216getValueimpl2);
    }
}
