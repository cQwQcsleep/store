package androidx.window.core;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/window/core/BuildConfig;", "", "<init>", "()V", "verificationMode", "Landroidx/window/core/VerificationMode;", "getVerificationMode", "()Landroidx/window/core/VerificationMode;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BuildConfig {
    public static final BuildConfig INSTANCE = new BuildConfig();
    private static final VerificationMode verificationMode = VerificationMode.QUIET;

    private BuildConfig() {
    }

    public final VerificationMode getVerificationMode() {
        return verificationMode;
    }
}
