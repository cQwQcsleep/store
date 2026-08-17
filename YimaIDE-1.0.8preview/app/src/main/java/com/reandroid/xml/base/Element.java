package com.reandroid.xml.base;

import com.reandroid.common.Namespace;
import com.reandroid.xml.base.Node;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Element<T extends Node> extends NodeTree<T>, NamedNode, NodeFactory {
    Attribute getAttributeAt(int i);

    int getAttributeCount();

    Iterator<? extends Attribute> getAttributes();

    Namespace getNamespaceAt(int i);

    int getNamespaceCount();

    Iterator<? extends Namespace> getNamespaces();

    Attribute newAttribute();

    Element<T> newElement();

    Namespace newNamespace(String str, String str2);
}
