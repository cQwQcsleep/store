package com.sun.org.apache.xml.internal.serialize;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public interface DOMSerializer {
    void serialize(Document document) throws IOException;

    void serialize(DocumentFragment documentFragment) throws IOException;

    void serialize(Element element) throws IOException;
}
