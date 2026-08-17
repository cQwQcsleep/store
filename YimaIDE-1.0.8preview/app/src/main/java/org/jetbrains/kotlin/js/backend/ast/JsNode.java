package org.jetbrains.kotlin.js.backend.ast;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface JsNode {
    void accept(JsVisitor jsVisitor);

    void acceptChildren(JsVisitor jsVisitor);

    JsNode deepCopy();

    List<JsComment> getCommentsAfterNode();

    List<JsComment> getCommentsBeforeNode();

    JsLocationWithSource getSource();

    void setCommentsAfterNode(List<JsComment> list);

    void setCommentsBeforeNode(List<JsComment> list);

    void setSource(JsLocationWithSource jsLocationWithSource);

    void traverse(JsVisitorWithContext jsVisitorWithContext, JsContext jsContext);
}
