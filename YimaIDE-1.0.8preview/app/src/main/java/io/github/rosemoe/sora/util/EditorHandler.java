package io.github.rosemoe.sora.util;

import android.os.Handler;
import android.os.Looper;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/github/rosemoe/sora/util/EditorHandler;", "Landroid/os/Handler;", "<init>", "()V", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class EditorHandler extends Handler {
    public static final EditorHandler INSTANCE = new EditorHandler();

    private EditorHandler() {
        super(Looper.getMainLooper());
    }
}
