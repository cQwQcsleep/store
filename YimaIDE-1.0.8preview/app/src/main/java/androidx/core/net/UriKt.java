package androidx.core.net;

import android.net.Uri;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0086\b\u001a\n\u0010\u0004\u001a\u00020\u0003*\u00020\u0001¨\u0006\u0005"}, d2 = {"toUri", "Landroid/net/Uri;", "", "Ljava/io/File;", "toFile", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class UriKt {
    public static final File toFile(Uri uri) {
        uri.getClass();
        if (!Intrinsics.areEqual(uri.getScheme(), "file")) {
            dt1.a("Uri lacks 'file' scheme: ", uri);
            return null;
        }
        String path = uri.getPath();
        if (path != null) {
            return new File(path);
        }
        dt1.a("Uri path is null: ", uri);
        return null;
    }

    public static final Uri toUri(String str) {
        str.getClass();
        Uri uri = Uri.parse(str);
        uri.getClass();
        return uri;
    }

    public static final Uri toUri(File file) {
        file.getClass();
        Uri uriFromFile = Uri.fromFile(file);
        uriFromFile.getClass();
        return uriFromFile;
    }
}
