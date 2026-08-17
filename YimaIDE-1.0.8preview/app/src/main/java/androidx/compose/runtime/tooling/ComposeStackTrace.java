package androidx.compose.runtime.tooling;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\n8F¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTrace;", "", "frames", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "<init>", "(Ljava/util/List;)V", "getFrames", "()Ljava/util/List;", "hasSourceInformation", "", "getHasSourceInformation$annotations", "()V", "getHasSourceInformation", "()Z", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposeStackTrace {
    public static final int $stable = 8;
    private final List<ComposeStackTraceFrame> frames;

    public ComposeStackTrace(List<ComposeStackTraceFrame> list) {
        this.frames = list;
    }

    public static /* synthetic */ void getHasSourceInformation$annotations() {
    }

    public final List<ComposeStackTraceFrame> getFrames() {
        return this.frames;
    }

    public final boolean getHasSourceInformation() {
        List<ComposeStackTraceFrame> list = this.frames;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getSourceInfo() != null) {
                return true;
            }
        }
        return false;
    }
}
