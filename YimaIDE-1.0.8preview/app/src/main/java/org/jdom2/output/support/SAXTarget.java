package org.jdom2.output.support;

import org.jdom2.output.JDOMLocator;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class SAXTarget {
    private final ContentHandler contentHandler;
    private final DeclHandler declHandler;
    private final boolean declareNamespaces;
    private final DTDHandler dtdHandler;
    private final EntityResolver entityResolver;
    private final ErrorHandler errorHandler;
    private final LexicalHandler lexicalHandler;
    private final SAXLocator locator;
    private final boolean reportDtdEvents;

    public static final class SAXLocator implements JDOMLocator {
        private Object node = null;
        private final String publicid;
        private final String systemid;

        public SAXLocator(String str, String str2) {
            this.publicid = str;
            this.systemid = str2;
        }

        @Override // org.xml.sax.Locator
        public int getColumnNumber() {
            return -1;
        }

        @Override // org.xml.sax.Locator
        public int getLineNumber() {
            return -1;
        }

        @Override // org.jdom2.output.JDOMLocator
        public Object getNode() {
            return this.node;
        }

        @Override // org.xml.sax.Locator
        public String getPublicId() {
            return this.publicid;
        }

        @Override // org.xml.sax.Locator
        public String getSystemId() {
            return this.systemid;
        }

        public void setNode(Object obj) {
            this.node = obj;
        }
    }

    public SAXTarget(ContentHandler contentHandler, ErrorHandler errorHandler, DTDHandler dTDHandler, EntityResolver entityResolver, LexicalHandler lexicalHandler, DeclHandler declHandler, boolean z, boolean z2, String str, String str2) {
        this.contentHandler = contentHandler;
        this.errorHandler = errorHandler;
        this.dtdHandler = dTDHandler;
        this.entityResolver = entityResolver;
        this.lexicalHandler = lexicalHandler;
        this.declHandler = declHandler;
        this.declareNamespaces = z;
        this.reportDtdEvents = z2;
        this.locator = new SAXLocator(str, str2);
    }

    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }

    public DeclHandler getDeclHandler() {
        return this.declHandler;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }

    public SAXLocator getLocator() {
        return this.locator;
    }

    public boolean isDeclareNamespaces() {
        return this.declareNamespaces;
    }

    public boolean isReportDTDEvents() {
        return this.reportDtdEvents;
    }
}
