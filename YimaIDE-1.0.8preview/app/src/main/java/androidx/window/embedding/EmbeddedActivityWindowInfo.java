package androidx.window.embedding;

import android.graphics.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "", "isEmbedded", "", "parentHostBounds", "Landroid/graphics/Rect;", "boundsInParentHost", "<init>", "(ZLandroid/graphics/Rect;Landroid/graphics/Rect;)V", "()Z", "getParentHostBounds", "()Landroid/graphics/Rect;", "getBoundsInParentHost", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EmbeddedActivityWindowInfo {
    private final Rect boundsInParentHost;
    private final boolean isEmbedded;
    private final Rect parentHostBounds;

    public EmbeddedActivityWindowInfo(boolean z, Rect rect, Rect rect2) {
        rect.getClass();
        rect2.getClass();
        this.isEmbedded = z;
        this.parentHostBounds = rect;
        this.boundsInParentHost = rect2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmbeddedActivityWindowInfo)) {
            return false;
        }
        EmbeddedActivityWindowInfo embeddedActivityWindowInfo = (EmbeddedActivityWindowInfo) other;
        return this.isEmbedded == embeddedActivityWindowInfo.isEmbedded && Intrinsics.areEqual(this.parentHostBounds, embeddedActivityWindowInfo.parentHostBounds) && Intrinsics.areEqual(this.boundsInParentHost, embeddedActivityWindowInfo.boundsInParentHost);
    }

    public final Rect getBoundsInParentHost() {
        return this.boundsInParentHost;
    }

    public final Rect getParentHostBounds() {
        return this.parentHostBounds;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.isEmbedded) * 31) + this.parentHostBounds.hashCode()) * 31) + this.boundsInParentHost.hashCode();
    }

    /* JADX INFO: renamed from: isEmbedded, reason: from getter */
    public final boolean getIsEmbedded() {
        return this.isEmbedded;
    }

    public String toString() {
        return "EmbeddedActivityWindowInfo{isEmbedded=" + this.isEmbedded + ", parentHostBounds=" + this.parentHostBounds + ", boundsInParentHost=" + this.boundsInParentHost + '}';
    }
}
