package androidx.compose.ui.text.platform;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import androidx.collection.SieveCache;
import androidx.compose.ui.text.font.AndroidFont;
import androidx.compose.ui.text.font.AndroidPreloadedFont;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.ResourceFont;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated(message = "Duplicate cache")
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidTypefaceCache;", "", "<init>", "()V", "cache", "Landroidx/collection/SieveCache;", "", "Landroid/graphics/Typeface;", "getOrCreate", "context", "Landroid/content/Context;", "font", "Landroidx/compose/ui/text/font/Font;", "getKey", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidTypefaceCache {
    public static final AndroidTypefaceCache INSTANCE = new AndroidTypefaceCache();
    private static final SieveCache<String, Typeface> cache = new SieveCache<>(16, 16, (Function2) null, (Function1) null, (Function4) null, 28, (DefaultConstructorMarker) null);
    public static final int $stable = 8;

    private AndroidTypefaceCache() {
    }

    private final String getKey(Context context, Font font) {
        if (!(font instanceof ResourceFont)) {
            if (font instanceof AndroidPreloadedFont) {
                return ((AndroidPreloadedFont) font).getCacheKey();
            }
            aca.a("Unknown font type: ", font);
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(((ResourceFont) font).getResId(), typedValue, true);
        StringBuilder sb = new StringBuilder("res:");
        CharSequence charSequence = typedValue.string;
        String string = charSequence != null ? charSequence.toString() : null;
        string.getClass();
        sb.append(string);
        return sb.toString();
    }

    public final Typeface getOrCreate(Context context, Font font) {
        Typeface typefaceLoadBlocking;
        Typeface typeface;
        String key = getKey(context, font);
        if (key != null && (typeface = (Typeface) cache.get(key)) != null) {
            return typeface;
        }
        if (font instanceof ResourceFont) {
            typefaceLoadBlocking = AndroidResourceFontLoaderHelper.INSTANCE.create(context, ((ResourceFont) font).getResId());
        } else {
            if (!(font instanceof AndroidFont)) {
                aca.a("Unknown font type: ", font);
                return null;
            }
            AndroidFont androidFont = (AndroidFont) font;
            typefaceLoadBlocking = androidFont.getTypefaceLoader().loadBlocking(context, androidFont);
        }
        if (typefaceLoadBlocking == null) {
            aca.a("Unable to load font ", font);
            return null;
        }
        if (key != null) {
            cache.put(key, typefaceLoadBlocking);
        }
        return typefaceLoadBlocking;
    }
}
