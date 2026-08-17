package io.github.rosemoe.sora.text;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"batchEdit", "Lio/github/rosemoe/sora/text/Content;", "block", "Lkotlin/Function1;", "", "editor_release"}, k = 2, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ContentKt {
    public static final Content batchEdit(Content content, Function1<? super Content, Unit> function1) {
        content.getClass();
        function1.getClass();
        content.beginBatchEdit();
        function1.invoke(content);
        content.endBatchEdit();
        return content;
    }
}
