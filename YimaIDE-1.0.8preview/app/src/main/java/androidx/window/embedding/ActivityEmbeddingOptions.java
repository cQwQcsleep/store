package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u001c\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"setLaunchingActivityStack", "Landroid/os/Bundle;", "context", "Landroid/content/Context;", "activityStack", "Landroidx/window/embedding/ActivityStack;", "setOverlayCreateParams", "activity", "Landroid/app/Activity;", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "window_release"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ActivityEmbeddingOptions {
    public static final Bundle setLaunchingActivityStack(Bundle bundle, Context context, ActivityStack activityStack) {
        bundle.getClass();
        context.getClass();
        activityStack.getClass();
        return ActivityEmbeddingController.INSTANCE.getInstance(context).setLaunchingActivityStack$window_release(bundle, activityStack);
    }

    public static final Bundle setOverlayCreateParams(Bundle bundle, Activity activity, OverlayCreateParams overlayCreateParams) {
        bundle.getClass();
        activity.getClass();
        overlayCreateParams.getClass();
        return OverlayController.INSTANCE.getInstance(activity).setOverlayCreateParams$window_release(bundle, overlayCreateParams);
    }
}
