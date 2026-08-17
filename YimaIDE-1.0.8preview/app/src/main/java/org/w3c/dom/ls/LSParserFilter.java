package org.w3c.dom.ls;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LSParserFilter {
    public static final short FILTER_ACCEPT = 1;
    public static final short FILTER_INTERRUPT = 4;
    public static final short FILTER_REJECT = 2;
    public static final short FILTER_SKIP = 3;

    short acceptNode(Node node);

    int getWhatToShow();

    short startElement(Element element);
}
