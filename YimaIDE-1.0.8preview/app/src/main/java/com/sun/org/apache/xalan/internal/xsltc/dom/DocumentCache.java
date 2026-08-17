package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMCache;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import defpackage.x73;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DocumentCache implements DOMCache {
    private static final int REFRESH_INTERVAL = 1000;
    private String[] _URIs;
    private int _count;
    private int _current;
    private XSLTCDTMManager _dtmManager;
    private SAXParser _parser;
    private XMLReader _reader;
    private Map<String, CachedDocument> _references;
    private int _size;

    public final class CachedDocument {
        private long _accessCount;
        private long _buildTime;
        private DOMEnhancedForDTM _dom = null;
        private long _firstReferenced;
        private long _lastChecked;
        private long _lastModified;
        private long _lastReferenced;

        public CachedDocument(String str) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this._firstReferenced = jCurrentTimeMillis;
            this._lastReferenced = jCurrentTimeMillis;
            this._accessCount = 0L;
            loadDocument(str);
            this._buildTime = System.currentTimeMillis() - jCurrentTimeMillis;
        }

        public long getAccessCount() {
            return this._accessCount;
        }

        public DOM getDocument() {
            return this._dom;
        }

        public long getEstimatedSize() {
            DOMEnhancedForDTM dOMEnhancedForDTM = this._dom;
            if (dOMEnhancedForDTM != null) {
                return dOMEnhancedForDTM.getSize() << 5;
            }
            return 0L;
        }

        public long getFirstReferenced() {
            return this._firstReferenced;
        }

        public long getLastChecked() {
            return this._lastChecked;
        }

        public long getLastModified() {
            return this._lastModified;
        }

        public long getLastReferenced() {
            return this._lastReferenced;
        }

        public long getLatency() {
            return this._buildTime;
        }

        public void incAccessCount() {
            this._accessCount++;
        }

        public void loadDocument(String str) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                DOMEnhancedForDTM dOMEnhancedForDTM = (DOMEnhancedForDTM) DocumentCache.this._dtmManager.getDTM(new SAXSource(DocumentCache.this._reader, new InputSource(str)), false, null, true, false);
                this._dom = dOMEnhancedForDTM;
                dOMEnhancedForDTM.setDocumentURI(str);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                long j = this._buildTime;
                if (j > 0) {
                    this._buildTime = (j + jCurrentTimeMillis2) >>> 1;
                } else {
                    this._buildTime = jCurrentTimeMillis2;
                }
            } catch (Exception unused) {
                this._dom = null;
            }
        }

        public void setLastChecked(long j) {
            this._lastChecked = j;
        }

        public void setLastModified(long j) {
            this._lastModified = j;
        }
    }

    public DocumentCache(int i, XSLTCDTMManager xSLTCDTMManager) throws SAXException {
        this._dtmManager = xSLTCDTMManager;
        this._count = 0;
        this._current = 0;
        this._size = i;
        this._references = new HashMap(this._size + 2);
        this._URIs = new String[this._size];
        try {
            SAXParserFactory sAXParserFactoryNewInstance = SAXParserFactory.newInstance();
            try {
                sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/namespaces", true);
            } catch (Exception unused) {
                sAXParserFactoryNewInstance.setNamespaceAware(true);
            }
            SAXParser sAXParserNewSAXParser = sAXParserFactoryNewInstance.newSAXParser();
            this._parser = sAXParserNewSAXParser;
            this._reader = sAXParserNewSAXParser.getXMLReader();
        } catch (ParserConfigurationException unused2) {
            BasisLibrary.runTimeError(BasisLibrary.NAMESPACES_SUPPORT_ERR);
        }
    }

    private final long getLastModified(String str) {
        try {
            URL url = new URL(str);
            long lastModified = url.openConnection().getLastModified();
            return (lastModified == 0 && "file".equals(url.getProtocol())) ? Paths.get(url.toURI()).toFile().lastModified() : lastModified;
        } catch (Exception unused) {
            return System.currentTimeMillis();
        }
    }

    private synchronized void insertDocument(String str, CachedDocument cachedDocument) {
        try {
            int i = this._count;
            if (i < this._size) {
                String[] strArr = this._URIs;
                this._count = i + 1;
                strArr[i] = str;
                this._current = 0;
            } else {
                this._references.remove(this._URIs[this._current]);
                String[] strArr2 = this._URIs;
                int i2 = this._current;
                strArr2[i2] = str;
                int i3 = i2 + 1;
                this._current = i3;
                if (i3 >= this._size) {
                    this._current = 0;
                }
            }
            this._references.put(str, cachedDocument);
        } catch (Throwable th) {
            throw th;
        }
    }

    private CachedDocument lookupDocument(String str) {
        return this._references.get(str);
    }

    private synchronized void replaceDocument(String str, CachedDocument cachedDocument) {
        try {
            if (cachedDocument == null) {
                insertDocument(str, cachedDocument);
            } else {
                this._references.put(str, cachedDocument);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void getStatistics(PrintWriter printWriter) {
        printWriter.println("<h2>DOM cache statistics</h2><center><table border=\"2\"><tr><td><b>Document URI</b></td><td><center><b>Build time</b></center></td><td><center><b>Access count</b></center></td><td><center><b>Last accessed</b></center></td><td><center><b>Last modified</b></center></td></tr>");
        for (int i = 0; i < this._count; i++) {
            CachedDocument cachedDocument = this._references.get(this._URIs[i]);
            printWriter.print("<tr><td><a href=\"" + this._URIs[i] + "\"><font size=-1>" + this._URIs[i] + "</font></a></td>");
            StringBuilder sb = new StringBuilder("<td><center>");
            sb.append(cachedDocument.getLatency());
            sb.append("ms</center></td>");
            printWriter.print(sb.toString());
            printWriter.print("<td><center>" + cachedDocument.getAccessCount() + "</center></td>");
            printWriter.print("<td><center>" + new Date(cachedDocument.getLastReferenced()) + "</center></td>");
            printWriter.print("<td><center>" + new Date(cachedDocument.getLastModified()) + "</center></td>");
            printWriter.println("</tr>");
        }
        printWriter.println("</table></center>");
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMCache
    public DOM retrieveDocument(String str, String str2, Translet translet) {
        if (str != null && !str.equals("")) {
            try {
                str2 = SystemIDResolver.getAbsoluteURI(str2, str);
            } catch (TransformerException unused) {
            }
        }
        CachedDocument cachedDocumentLookupDocument = lookupDocument(str2);
        if (cachedDocumentLookupDocument == null) {
            cachedDocumentLookupDocument = new CachedDocument(str2);
            cachedDocumentLookupDocument.setLastModified(getLastModified(str2));
            insertDocument(str2, cachedDocumentLookupDocument);
        } else {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long lastChecked = cachedDocumentLookupDocument.getLastChecked();
            cachedDocumentLookupDocument.setLastChecked(jCurrentTimeMillis);
            if (jCurrentTimeMillis > lastChecked + 1000) {
                cachedDocumentLookupDocument.setLastChecked(jCurrentTimeMillis);
                if (getLastModified(str2) > cachedDocumentLookupDocument.getLastModified()) {
                    cachedDocumentLookupDocument = new CachedDocument(str2);
                    cachedDocumentLookupDocument.setLastModified(getLastModified(str2));
                    replaceDocument(str2, cachedDocumentLookupDocument);
                }
            }
        }
        DOM document = cachedDocumentLookupDocument.getDocument();
        if (document == null) {
            return null;
        }
        cachedDocumentLookupDocument.incAccessCount();
        ((AbstractTranslet) translet).prepassDocument(document);
        return cachedDocumentLookupDocument.getDocument();
    }

    public DocumentCache(int i) throws SAXException {
        this(i, null);
        try {
            this._dtmManager = XSLTCDTMManager.createNewDTMManagerInstance();
        } catch (Exception e) {
            x73.a(e);
            throw null;
        }
    }
}
