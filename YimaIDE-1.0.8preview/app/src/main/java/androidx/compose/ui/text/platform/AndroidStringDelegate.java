package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.PlatformStringDelegate;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.CharsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u001c\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u001c\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u001c\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidStringDelegate;", "Landroidx/compose/ui/text/PlatformStringDelegate;", "<init>", "()V", "toUpperCase", "", "string", "locale", "Ljava/util/Locale;", "Landroidx/compose/ui/text/intl/PlatformLocale;", "toLowerCase", "capitalize", "decapitalize", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidStringDelegate implements PlatformStringDelegate {
    public static final int $stable = 0;

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String capitalize(String string, Locale locale) {
        if (string.length() <= 0) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        char cCharAt = string.charAt(0);
        sb.append((Object) (Character.isLowerCase(cCharAt) ? CharsKt.titlecase(cCharAt, locale) : String.valueOf(cCharAt)));
        sb.append(string.substring(1));
        return sb.toString();
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String decapitalize(String string, Locale locale) {
        if (string.length() <= 0) {
            return string;
        }
        return ((Object) CharsKt.lowercase(string.charAt(0), locale)) + string.substring(1);
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String toLowerCase(String string, Locale locale) {
        String lowerCase = string.toLowerCase(locale);
        lowerCase.getClass();
        return lowerCase;
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String toUpperCase(String string, Locale locale) {
        String upperCase = string.toUpperCase(locale);
        upperCase.getClass();
        return upperCase;
    }
}
