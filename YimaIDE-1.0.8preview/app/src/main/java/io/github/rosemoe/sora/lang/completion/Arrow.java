package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/Arrow;", "", "<init>", "()V", "Diag", "", "getDiag", "()I", "Left", "getLeft", "LeftLeft", "getLeftLeft", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class Arrow {
    public static final Arrow INSTANCE = new Arrow();
    private static final int Diag = 1;
    private static final int Left = 2;
    private static final int LeftLeft = 3;

    private Arrow() {
    }

    public final int getDiag() {
        return Diag;
    }

    public final int getLeft() {
        return Left;
    }

    public final int getLeftLeft() {
        return LeftLeft;
    }
}
