package org.eclipse.tm4e.core.grammar;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IToken {
    int getEndIndex();

    List<String> getScopes();

    int getStartIndex();

    void setStartIndex(int i);
}
