package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, d2 = {"toAdaptiveIcon", "Landroid/graphics/drawable/Icon;", "Landroid/graphics/Bitmap;", "toIcon", "Landroid/net/Uri;", "", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class IconKt {
    public static final Icon toAdaptiveIcon(Bitmap bitmap) {
        bitmap.getClass();
        Icon iconCreateWithAdaptiveBitmap = Icon.createWithAdaptiveBitmap(bitmap);
        iconCreateWithAdaptiveBitmap.getClass();
        return iconCreateWithAdaptiveBitmap;
    }

    public static final Icon toIcon(byte[] bArr) {
        bArr.getClass();
        Icon iconCreateWithData = Icon.createWithData(bArr, 0, bArr.length);
        iconCreateWithData.getClass();
        return iconCreateWithData;
    }

    public static final Icon toIcon(Uri uri) {
        uri.getClass();
        Icon iconCreateWithContentUri = Icon.createWithContentUri(uri);
        iconCreateWithContentUri.getClass();
        return iconCreateWithContentUri;
    }

    public static final Icon toIcon(Bitmap bitmap) {
        bitmap.getClass();
        Icon iconCreateWithBitmap = Icon.createWithBitmap(bitmap);
        iconCreateWithBitmap.getClass();
        return iconCreateWithBitmap;
    }
}
