package androidx.compose.runtime;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0018\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/ComposeRuntimeFlags;", "", "<init>", "()V", "isMovingNestedMovableContentEnabled", "", "isMovingNestedMovableContentEnabled$annotations", "isMovableContentUsageTrackingEnabled", "isMovableContentUsageTrackingEnabled$annotations", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposeRuntimeFlags {
    public static boolean isMovableContentUsageTrackingEnabled;
    public static final ComposeRuntimeFlags INSTANCE = new ComposeRuntimeFlags();
    public static boolean isMovingNestedMovableContentEnabled = true;
    public static final int $stable = 8;

    private ComposeRuntimeFlags() {
    }

    public static /* synthetic */ void isMovableContentUsageTrackingEnabled$annotations() {
    }

    public static /* synthetic */ void isMovingNestedMovableContentEnabled$annotations() {
    }
}
