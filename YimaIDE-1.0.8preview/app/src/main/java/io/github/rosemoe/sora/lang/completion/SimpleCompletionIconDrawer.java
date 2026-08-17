package io.github.rosemoe.sora.lang.completion;

import android.graphics.drawable.Drawable;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/SimpleCompletionIconDrawer;", "", "<init>", "()V", "draw", "Landroid/graphics/drawable/Drawable;", "kind", "Lio/github/rosemoe/sora/lang/completion/CompletionItemKind;", "circle", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class SimpleCompletionIconDrawer {
    public static final SimpleCompletionIconDrawer INSTANCE = new SimpleCompletionIconDrawer();

    private SimpleCompletionIconDrawer() {
    }

    @JvmStatic
    public static final Drawable draw(CompletionItemKind completionItemKind) {
        completionItemKind.getClass();
        return draw$default(completionItemKind, false, 2, null);
    }

    public static /* synthetic */ Drawable draw$default(CompletionItemKind completionItemKind, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return draw(completionItemKind, z);
    }

    @JvmStatic
    public static final Drawable draw(CompletionItemKind kind, boolean circle) {
        kind.getClass();
        return new CircleDrawable(kind, circle);
    }
}
