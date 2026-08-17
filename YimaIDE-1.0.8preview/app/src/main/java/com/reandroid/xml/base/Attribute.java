package com.reandroid.xml.base;

import com.reandroid.common.Namespace;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Attribute extends Node, NamedNode {
    default Object getAttributeValue() {
        return getValueAsString();
    }

    @Override // com.reandroid.xml.base.NamedNode
    Namespace getNamespace();

    Element<?> getParentNode();

    String getValueAsString();

    @Override // com.reandroid.xml.base.NamedNode
    void setNamespace(Namespace namespace);
}
