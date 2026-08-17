package org.w3c.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocumentType extends Node {
    NamedNodeMap getEntities();

    String getInternalSubset();

    String getName();

    NamedNodeMap getNotations();

    String getPublicId();

    String getSystemId();
}
