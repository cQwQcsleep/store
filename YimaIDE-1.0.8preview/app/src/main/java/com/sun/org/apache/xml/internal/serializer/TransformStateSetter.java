package com.sun.org.apache.xml.internal.serializer;

import javax.xml.transform.Transformer;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TransformStateSetter {
    void resetState(Transformer transformer);

    void setCurrentNode(Node node);
}
