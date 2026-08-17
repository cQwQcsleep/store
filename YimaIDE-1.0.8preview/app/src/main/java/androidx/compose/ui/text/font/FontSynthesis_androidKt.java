package androidx.compose.ui.text.font;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"synthesizeTypeface", "", "Landroidx/compose/ui/text/font/FontSynthesis;", "typeface", "font", "Landroidx/compose/ui/text/font/Font;", "requestedWeight", "Landroidx/compose/ui/text/font/FontWeight;", "requestedStyle", "Landroidx/compose/ui/text/font/FontStyle;", "synthesizeTypeface-FxwP2eA", "(ILjava/lang/Object;Landroidx/compose/ui/text/font/Font;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/lang/Object;", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FontSynthesis_androidKt {
    /* JADX WARN: Code duplicated, block: B:14:0x0033  */
    /* JADX INFO: renamed from: synthesizeTypeface-FxwP2eA, reason: not valid java name */
    public static final Object m5613synthesizeTypefaceFxwP2eA(int i, Object obj, Font font, FontWeight fontWeight, int i2) {
        boolean z;
        if (!(obj instanceof android.graphics.Typeface)) {
            return obj;
        }
        boolean z2 = false;
        if (!FontSynthesis.m5605isWeightOnimpl$ui_text(i) || Intrinsics.areEqual(font.getWeight(), fontWeight)) {
            z = false;
        } else {
            FontWeight.Companion companion = FontWeight.INSTANCE;
            if (fontWeight.compareTo(AndroidFontUtils_androidKt.getAndroidBold(companion)) < 0 || font.getWeight().compareTo(AndroidFontUtils_androidKt.getAndroidBold(companion)) >= 0) {
                z = false;
            } else {
                z = true;
            }
        }
        if (FontSynthesis.m5604isStyleOnimpl$ui_text(i) && !FontStyle.m5591equalsimpl0(i2, font.getStyle())) {
            z2 = true;
        }
        if (z2 || z) {
            return TypefaceHelperMethodsApi28.INSTANCE.create((android.graphics.Typeface) obj, z ? fontWeight.getWeight() : font.getWeight().getWeight(), z2 ? FontStyle.m5591equalsimpl0(i2, FontStyle.INSTANCE.m5597getItalic_LCdwA()) : FontStyle.m5591equalsimpl0(font.getStyle(), FontStyle.INSTANCE.m5597getItalic_LCdwA()));
        }
        return obj;
    }
}
