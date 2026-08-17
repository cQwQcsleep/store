package androidx.core.app;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DialogCompat {

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static <T> T requireViewById(Dialog dialog, int i) {
            return (T) dialog.requireViewById(i);
        }
    }

    private DialogCompat() {
    }

    public static View requireViewById(Dialog dialog, int i) {
        return (View) Api28Impl.requireViewById(dialog, i);
    }
}
