package org.jdom2.adapters;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.JDOMException;
import org.w3c.dom.Document;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class JAXPDOMAdapter extends AbstractDOMAdapter {
    private static final ThreadLocal<DocumentBuilder> localbuilder = new ThreadLocal<>();

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jdom2.JDOMException */
    public Document createDocument() throws JDOMException {
        ThreadLocal<DocumentBuilder> threadLocal = localbuilder;
        DocumentBuilder documentBuilderNewDocumentBuilder = threadLocal.get();
        if (documentBuilderNewDocumentBuilder == null) {
            try {
                documentBuilderNewDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                threadLocal.set(documentBuilderNewDocumentBuilder);
            } catch (ParserConfigurationException e) {
                throw new JDOMException("Unable to obtain a DOM parser. See cause:", e);
            }
        }
        return documentBuilderNewDocumentBuilder.newDocument();
    }
}
