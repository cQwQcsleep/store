package androidx.core.app;

import android.app.PictureInPictureUiState;
import android.os.Build;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/core/app/PictureInPictureUiStateCompat;", "", "isStashed", "", "isTransitioningToPip", "<init>", "(ZZ)V", "()Z", "Companion", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PictureInPictureUiStateCompat {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isStashed;
    private final boolean isTransitioningToPip;

    public PictureInPictureUiStateCompat(boolean z, boolean z2) {
        this.isStashed = z;
        this.isTransitioningToPip = z2;
    }

    @JvmStatic
    public static final PictureInPictureUiStateCompat fromPictureInPictureUiState(PictureInPictureUiState pictureInPictureUiState) {
        return INSTANCE.fromPictureInPictureUiState(pictureInPictureUiState);
    }

    /* JADX INFO: renamed from: isStashed, reason: from getter */
    public final boolean getIsStashed() {
        return this.isStashed;
    }

    /* JADX INFO: renamed from: isTransitioningToPip, reason: from getter */
    public final boolean getIsTransitioningToPip() {
        return this.isTransitioningToPip;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/core/app/PictureInPictureUiStateCompat$Companion;", "", "<init>", "()V", "fromPictureInPictureUiState", "Landroidx/core/app/PictureInPictureUiStateCompat;", "uiState", "Landroid/app/PictureInPictureUiState;", "core"}, k = 1, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final PictureInPictureUiStateCompat fromPictureInPictureUiState(PictureInPictureUiState uiState) {
            uiState.getClass();
            return Build.VERSION.SDK_INT >= 35 ? new PictureInPictureUiStateCompat(uiState.isStashed(), uiState.isTransitioningToPip()) : new PictureInPictureUiStateCompat(uiState.isStashed(), false);
        }

        private Companion() {
        }
    }
}
