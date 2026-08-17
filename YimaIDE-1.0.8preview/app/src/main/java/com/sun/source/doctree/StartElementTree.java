package com.sun.source.doctree;

import java.util.List;
import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface StartElementTree extends DocTree {
    List<? extends DocTree> getAttributes();

    Name getName();

    boolean isSelfClosing();
}
