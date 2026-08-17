package com.sun.source.tree;

import java.util.List;
import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MemberReferenceTree extends ExpressionTree {

    public enum ReferenceMode {
        INVOKE,
        NEW
    }

    ReferenceMode getMode();

    Name getName();

    ExpressionTree getQualifierExpression();

    List<? extends ExpressionTree> getTypeArguments();
}
