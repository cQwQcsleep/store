package com.intellij.codeInsight.multiverse;

import com.intellij.psi.impl.source.tree.ChildRole;
import java.util.EventListener;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Lcom/intellij/codeInsight/multiverse/CodeInsightContextChangeListener;", "Ljava/util/EventListener;", "contextsChanged", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public interface CodeInsightContextChangeListener extends EventListener {
    void contextsChanged();
}
