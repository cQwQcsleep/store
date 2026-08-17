package androidx.compose.foundation.text.input.internal;

import android.icu.text.DecimalFormatSymbols;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text/input/internal/DigitDirectionalityApi24;", "", "<init>", "()V", "resolve", "", "locale", "Ljava/util/Locale;", "Landroidx/compose/ui/text/intl/PlatformLocale;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class DigitDirectionalityApi24 {
    public static final DigitDirectionalityApi24 INSTANCE = new DigitDirectionalityApi24();

    private DigitDirectionalityApi24() {
    }

    public final byte resolve(Locale locale) {
        return Character.getDirectionality(DecimalFormatSymbols.getInstance(locale).getZeroDigit());
    }
}
