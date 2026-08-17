package androidx.core.content;

import android.content.ContentProvider;
import android.content.Context;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class ContentProviderCompat {
    private ContentProviderCompat() {
    }

    public static Context requireContext(ContentProvider contentProvider) {
        Context context = contentProvider.getContext();
        if (context != null) {
            return context;
        }
        k2d.a("Cannot find context from the provider.");
        return null;
    }
}
