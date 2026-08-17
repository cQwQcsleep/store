package androidx.compose.foundation.text.contextmenu.internal;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.util.Log;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/internal/TextClassificationHelper34;", "", "<init>", "()V", "sendIntentAllowBackgroundActivityStart", "", "pendingIntent", "Landroid/app/PendingIntent;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class TextClassificationHelper34 {
    public static final TextClassificationHelper34 INSTANCE = new TextClassificationHelper34();

    private TextClassificationHelper34() {
    }

    public final void sendIntentAllowBackgroundActivityStart(PendingIntent pendingIntent) {
        try {
            pendingIntent.send(ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle());
        } catch (PendingIntent.CanceledException e) {
            Log.e(TextContextMenuTextClassificationHelper_androidKt.TAG, "error sending pendingIntent: " + pendingIntent + " error: " + e);
        }
    }
}
