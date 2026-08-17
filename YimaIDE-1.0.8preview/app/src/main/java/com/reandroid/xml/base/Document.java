package com.reandroid.xml.base;

import com.reandroid.xml.base.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Document<E extends Element<?>> extends Node, NodeFactory {
    E getDocumentElement();
}
