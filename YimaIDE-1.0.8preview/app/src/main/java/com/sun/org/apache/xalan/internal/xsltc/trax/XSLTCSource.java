package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMWSFilter;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import com.sun.org.apache.xalan.internal.xsltc.dom.XSLTCDTMManager;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSLTCSource implements Source {
    private ThreadLocal<SAXImpl> _dom;
    private Source _source;
    private String _systemId;

    public XSLTCSource(String str) {
        this._systemId = null;
        this._source = null;
        this._dom = new ThreadLocal<>();
        this._systemId = str;
    }

    public DOM getDOM(XSLTCDTMManager xSLTCDTMManager, AbstractTranslet abstractTranslet) throws SAXException {
        SAXImpl sAXImpl = this._dom.get();
        if (sAXImpl != null) {
            if (xSLTCDTMManager != null) {
                sAXImpl.migrateTo(xSLTCDTMManager);
            }
            return sAXImpl;
        }
        Source streamSource = this._source;
        if (streamSource == null) {
            String str = this._systemId;
            if (str == null || str.length() <= 0) {
                throw new SAXException(new ErrorMsg(ErrorMsg.XSLTC_SOURCE_ERR).toString());
            }
            streamSource = new StreamSource(this._systemId);
        }
        Source source = streamSource;
        DOMWSFilter dOMWSFilter = (abstractTranslet == null || !(abstractTranslet instanceof StripFilter)) ? null : new DOMWSFilter(abstractTranslet);
        boolean zHasIdCall = abstractTranslet != null ? abstractTranslet.hasIdCall() : false;
        if (xSLTCDTMManager == null) {
            xSLTCDTMManager = XSLTCDTMManager.newInstance();
        }
        SAXImpl sAXImpl2 = (SAXImpl) xSLTCDTMManager.getDTM(source, true, dOMWSFilter, false, false, zHasIdCall);
        String systemId = getSystemId();
        if (systemId != null) {
            sAXImpl2.setDocumentURI(systemId);
        }
        this._dom.set(sAXImpl2);
        return sAXImpl2;
    }

    @Override // javax.xml.transform.Source
    public String getSystemId() {
        Source source = this._source;
        return source != null ? source.getSystemId() : this._systemId;
    }

    @Override // javax.xml.transform.Source
    public void setSystemId(String str) {
        this._systemId = str;
        Source source = this._source;
        if (source != null) {
            source.setSystemId(str);
        }
    }

    public XSLTCSource(Source source) {
        this._systemId = null;
        this._source = null;
        this._dom = new ThreadLocal<>();
        this._source = source;
    }
}
