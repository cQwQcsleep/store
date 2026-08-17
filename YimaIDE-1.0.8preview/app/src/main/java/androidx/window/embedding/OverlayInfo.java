package androidx.window.embedding;

import android.app.Activity;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\u0002J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Landroidx/window/embedding/OverlayInfo;", "", "overlayTag", "", "currentOverlayAttributes", "Landroidx/window/embedding/OverlayAttributes;", "activityStack", "Landroidx/window/embedding/ActivityStack;", "<init>", "(Ljava/lang/String;Landroidx/window/embedding/OverlayAttributes;Landroidx/window/embedding/ActivityStack;)V", "getOverlayTag", "()Ljava/lang/String;", "getCurrentOverlayAttributes", "()Landroidx/window/embedding/OverlayAttributes;", "getActivityStack", "()Landroidx/window/embedding/ActivityStack;", "contains", "", "activity", "Landroid/app/Activity;", "toString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OverlayInfo {
    private final ActivityStack activityStack;

    /* JADX INFO: renamed from: currentOverlayAttributes, reason: from kotlin metadata and from toString */
    private final OverlayAttributes currentOverlayAttrs;
    private final String overlayTag;

    public OverlayInfo(String str, OverlayAttributes overlayAttributes, ActivityStack activityStack) {
        str.getClass();
        this.overlayTag = str;
        this.currentOverlayAttrs = overlayAttributes;
        this.activityStack = activityStack;
    }

    public final boolean contains(Activity activity) {
        activity.getClass();
        ActivityStack activityStack = this.activityStack;
        if (activityStack != null) {
            return activityStack.contains(activity);
        }
        return false;
    }

    public final ActivityStack getActivityStack() {
        return this.activityStack;
    }

    /* JADX INFO: renamed from: getCurrentOverlayAttributes, reason: from getter */
    public final OverlayAttributes getCurrentOverlayAttrs() {
        return this.currentOverlayAttrs;
    }

    public final String getOverlayTag() {
        return this.overlayTag;
    }

    public String toString() {
        return OverlayInfo.class.getSimpleName() + ": {tag=" + this.overlayTag + ", currentOverlayAttrs=" + this.currentOverlayAttrs + ", activityStack=" + this.activityStack + '}';
    }
}
