package com.sun.source.doctree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocCommentTree extends DocTree {
    List<? extends DocTree> getBlockTags();

    List<? extends DocTree> getBody();

    List<? extends DocTree> getFirstSentence();

    default List<? extends DocTree> getFullBody() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getFirstSentence());
        arrayList.addAll(getBody());
        return arrayList;
    }

    default List<? extends DocTree> getPostamble() {
        return Collections.EMPTY_LIST;
    }

    default List<? extends DocTree> getPreamble() {
        return Collections.EMPTY_LIST;
    }
}
