package org.jdom2.input.sax;

import org.jdom2.JDOMException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface XMLReaderJDOMFactory {
    XMLReader createXMLReader() throws JDOMException;

    boolean isValidating();
}
