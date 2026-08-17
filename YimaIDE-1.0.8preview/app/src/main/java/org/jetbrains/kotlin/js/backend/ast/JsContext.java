package org.jetbrains.kotlin.js.backend.ast;

import java.util.Iterator;
import java.util.List;
import org.jetbrains.kotlin.js.backend.ast.JsNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class JsContext<T extends JsNode> {
    public <R extends T> void addPrevious(List<R> list) {
        Iterator<R> it = list.iterator();
        while (it.hasNext()) {
            addPrevious((JsNode) it.next());
        }
    }

    public abstract T getCurrentNode();

    public abstract void removeMe();

    /* JADX WARN: Incorrect types in method signature: <R:TT;>(TR;)V */
    public abstract void replaceMe(JsNode jsNode);

    /* JADX WARN: Incorrect types in method signature: <R:TT;>(TR;)V */
    public void addPrevious(JsNode jsNode) {
        throw new UnsupportedOperationException();
    }
}
