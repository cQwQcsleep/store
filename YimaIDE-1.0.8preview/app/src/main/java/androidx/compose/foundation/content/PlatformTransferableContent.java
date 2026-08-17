package androidx.compose.foundation.content;

import android.net.Uri;
import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/content/PlatformTransferableContent;", "", "linkUri", "Landroid/net/Uri;", "extras", "Landroid/os/Bundle;", "<init>", "(Landroid/net/Uri;Landroid/os/Bundle;)V", "getLinkUri", "()Landroid/net/Uri;", "getExtras", "()Landroid/os/Bundle;", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PlatformTransferableContent {
    public static final int $stable = 8;
    private final Bundle extras;
    private final Uri linkUri;

    public PlatformTransferableContent(Uri uri, Bundle bundle) {
        this.linkUri = uri;
        this.extras = bundle;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlatformTransferableContent)) {
            return false;
        }
        PlatformTransferableContent platformTransferableContent = (PlatformTransferableContent) other;
        return Intrinsics.areEqual(this.linkUri, platformTransferableContent.linkUri) && Intrinsics.areEqual(this.extras, platformTransferableContent.extras);
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final Uri getLinkUri() {
        return this.linkUri;
    }

    public int hashCode() {
        Uri uri = this.linkUri;
        return ((uri != null ? uri.hashCode() : 0) * 31) + this.extras.hashCode();
    }

    public String toString() {
        return "PlatformTransferableContent(linkUri=" + this.linkUri + ", extras=" + this.extras + ')';
    }
}
