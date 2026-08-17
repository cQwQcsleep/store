package androidx.compose.material3;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.WeakHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\nH\u0000\u001a,\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0001j\u0002`\nH\u0002\"\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u000f"}, d2 = {"CalendarLocale", "Ljava/util/Locale;", "toLocalString", "", "", "minDigits", "maxDigits", "isGroupingUsed", "", "locale", "Landroidx/compose/material3/CalendarLocale;", "cachedFormatters", "Ljava/util/WeakHashMap;", "Ljava/text/NumberFormat;", "getCachedDateTimeFormatter", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CalendarLocale_jvmKt {
    private static final WeakHashMap<String, NumberFormat> cachedFormatters = new WeakHashMap<>();

    private static final NumberFormat getCachedDateTimeFormatter(int i, int i2, boolean z, Locale locale) {
        String str = i + '.' + i2 + '.' + z + '.' + locale.toLanguageTag();
        WeakHashMap<String, NumberFormat> weakHashMap = cachedFormatters;
        NumberFormat integerInstance = weakHashMap.get(str);
        if (integerInstance == null) {
            integerInstance = NumberFormat.getIntegerInstance(locale);
            integerInstance.setGroupingUsed(z);
            integerInstance.setMinimumIntegerDigits(i);
            integerInstance.setMaximumIntegerDigits(i2);
            weakHashMap.put(str, integerInstance);
        }
        return integerInstance;
    }

    public static final String toLocalString(int i, int i2, int i3, boolean z, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return getCachedDateTimeFormatter(i2, i3, z, locale).format(Integer.valueOf(i));
    }

    public static /* synthetic */ String toLocalString$default(int i, int i2, int i3, boolean z, Locale locale, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 1;
        }
        if ((i4 & 2) != 0) {
            i3 = 40;
        }
        if ((i4 & 4) != 0) {
            z = false;
        }
        if ((i4 & 8) != 0) {
            locale = null;
        }
        return toLocalString(i, i2, i3, z, locale);
    }
}
