package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.trax.DOM2SAX;
import com.sun.org.apache.xalan.internal.xsltc.trax.StAXEvent2SAX;
import com.sun.org.apache.xalan.internal.xsltc.trax.StAXStream2SAX;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMException;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSLTCDTMManager extends DTMManagerDefault {
    private static final boolean DEBUG = false;
    private static final boolean DUMPTREE = false;

    public static XSLTCDTMManager createNewDTMManagerInstance() {
        return newInstance();
    }

    public static XSLTCDTMManager newInstance() {
        return new XSLTCDTMManager();
    }

    public DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3, boolean z4, int i, boolean z5, boolean z6) {
        boolean z7;
        XMLReader xMLReader;
        InputSource inputSource;
        SAXImpl sAXImpl;
        Source source2;
        StAXEvent2SAX stAXEvent2SAX;
        StAXStream2SAX stAXStream2SAX;
        SAXImpl sAXImpl2;
        Source source3;
        int firstFreeDTMID = getFirstFreeDTMID();
        int i2 = firstFreeDTMID << 16;
        if (source != null && (source instanceof StAXSource)) {
            StAXSource stAXSource = (StAXSource) source;
            if (stAXSource.getXMLEventReader() != null) {
                stAXStream2SAX = null;
                stAXEvent2SAX = new StAXEvent2SAX(stAXSource.getXMLEventReader());
            } else if (stAXSource.getXMLStreamReader() != null) {
                stAXEvent2SAX = null;
                stAXStream2SAX = new StAXStream2SAX(stAXSource.getXMLStreamReader());
            } else {
                stAXEvent2SAX = null;
                stAXStream2SAX = null;
            }
            if (i <= 0) {
                sAXImpl2 = new SAXImpl(this, source, i2, dTMWSFilter, null, z3, 512, z5, z6);
                source3 = source;
            } else {
                source3 = source;
                sAXImpl2 = new SAXImpl(this, source3, i2, dTMWSFilter, null, z3, i, z5, z6);
            }
            sAXImpl2.setDocumentURI(source3.getSystemId());
            addDTM(sAXImpl2, firstFreeDTMID, 0);
            try {
                if (stAXEvent2SAX != null) {
                    stAXEvent2SAX.setContentHandler(sAXImpl2);
                    stAXEvent2SAX.parse();
                    return sAXImpl2;
                }
                if (stAXStream2SAX == null) {
                    return sAXImpl2;
                }
                stAXStream2SAX.setContentHandler(sAXImpl2);
                stAXStream2SAX.parse();
                return sAXImpl2;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new WrappedRuntimeException(e2);
            }
        }
        if (source != null && (source instanceof DOMSource)) {
            DOM2SAX dom2sax = new DOM2SAX(((DOMSource) source).getNode());
            if (i <= 0) {
                sAXImpl = new SAXImpl(this, source, i2, dTMWSFilter, null, z3, 512, z5, z6);
                source2 = source;
            } else {
                source2 = source;
                sAXImpl = new SAXImpl(this, source2, i2, dTMWSFilter, null, z3, i, z5, z6);
            }
            sAXImpl.setDocumentURI(source2.getSystemId());
            addDTM(sAXImpl, firstFreeDTMID, 0);
            dom2sax.setContentHandler(sAXImpl);
            try {
                dom2sax.parse();
                return sAXImpl;
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception e4) {
                throw new WrappedRuntimeException(e4);
            }
        }
        boolean z8 = source != null ? source instanceof SAXSource : true;
        boolean z9 = source != null ? source instanceof StreamSource : false;
        if (!z8 && !z9) {
            throw new DTMException(XMLMessages.createXMLMessage("ER_NOT_SUPPORTED", new Object[]{source}));
        }
        if (source == null) {
            xMLReader = null;
            inputSource = null;
            z7 = false;
        } else {
            XMLReader xMLReader2 = getXMLReader(source);
            InputSource inputSourceSourceToInputSource = SAXSource.sourceToInputSource(source);
            String systemId = inputSourceSourceToInputSource.getSystemId();
            if (systemId != null) {
                try {
                    systemId = SystemIDResolver.getAbsoluteURI(systemId);
                } catch (Exception unused) {
                    System.err.println("Can not absolutize URL: " + systemId);
                }
                inputSourceSourceToInputSource.setSystemId(systemId);
            }
            z7 = z4;
            xMLReader = xMLReader2;
            inputSource = inputSourceSourceToInputSource;
        }
        SAXImpl sAXImpl3 = i <= 0 ? new SAXImpl(this, source, i2, dTMWSFilter, null, z3, 512, z5, z6) : new SAXImpl(this, source, i2, dTMWSFilter, null, z3, i, z5, z6);
        addDTM(sAXImpl3, firstFreeDTMID, 0);
        if (xMLReader != null) {
            xMLReader.setContentHandler(sAXImpl3.getBuilder());
            if (!z7 || xMLReader.getDTDHandler() == null) {
                xMLReader.setDTDHandler(sAXImpl3);
            }
            if (!z7 || xMLReader.getErrorHandler() == null) {
                xMLReader.setErrorHandler(sAXImpl3);
            }
            try {
                try {
                    xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", sAXImpl3);
                } catch (SAXNotRecognizedException | SAXNotSupportedException unused2) {
                }
                try {
                    xMLReader.parse(inputSource);
                    if (!z7) {
                        releaseXMLReader(xMLReader);
                    }
                } catch (RuntimeException e5) {
                    throw e5;
                } catch (Exception e6) {
                    throw new WrappedRuntimeException(e6);
                }
            } catch (Throwable th) {
                if (!z7) {
                    releaseXMLReader(xMLReader);
                }
                throw th;
            }
        }
        return sAXImpl3;
    }

    public DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3, boolean z4) {
        return getDTM(source, z, dTMWSFilter, z2, z3, false, 0, z4, false);
    }

    public DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3, boolean z4, boolean z5) {
        return getDTM(source, z, dTMWSFilter, z2, z3, false, 0, z4, z5);
    }

    public DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3, boolean z4, int i, boolean z5) {
        return getDTM(source, z, dTMWSFilter, z2, z3, z4, i, z5, false);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault, com.sun.org.apache.xml.internal.dtm.DTMManager
    public DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3) {
        return getDTM(source, z, dTMWSFilter, z2, z3, false, 0, true, false);
    }
}
