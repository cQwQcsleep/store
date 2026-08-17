package io.github.rosemoe.sora.util;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Cloneable;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H&J\r\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lio/github/rosemoe/sora/util/ShareableData;", "T", "", "retain", "", "release", "isMutable", "", "toMutable", "()Ljava/lang/Object;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface ShareableData<T> extends Cloneable {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class DefaultImpls {
        public static <T> Object clone(ShareableData<T> shareableData) {
            return Cloneable.DefaultImpls.clone(shareableData);
        }
    }

    boolean isMutable();

    void release();

    void retain();

    T toMutable();
}
