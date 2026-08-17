package io.github.rosemoe.sora.util;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\bH\u0096\u0002¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lio/github/rosemoe/sora/util/ReversedListView;", "E", "Lkotlin/collections/AbstractList;", "src", "", "<init>", "(Ljava/util/List;)V", "size", "", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ReversedListView<E> extends AbstractList<E> {
    private final List<E> src;

    /* JADX WARN: Multi-variable type inference failed */
    public ReversedListView(List<? extends E> list) {
        list.getClass();
        this.src = list;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public E get(int index) {
        List<E> list = this.src;
        return list.get((list.size() - 1) - index);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    /* JADX INFO: renamed from: getSize */
    public int get_size() {
        return this.src.size();
    }
}
