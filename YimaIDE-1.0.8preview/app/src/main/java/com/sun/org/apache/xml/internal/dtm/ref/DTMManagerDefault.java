package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMException;
import com.sun.org.apache.xml.internal.dtm.DTMFilter;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.dtm.ref.dom2dtm.DOM2DTM;
import com.sun.org.apache.xml.internal.dtm.ref.dom2dtm.DOM2DTMdefaultNamespaceDeclarationNode;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2RTFDTM;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.SuballocatedIntVector;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xml.internal.utils.XMLReaderManager;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMManagerDefault extends DTMManager {
    private static final boolean DEBUG = false;
    private static final boolean DUMPTREE = false;
    protected DTM[] m_dtms = new DTM[256];
    int[] m_dtm_offsets = new int[256];
    protected XMLReaderManager m_readerManager = null;
    protected DefaultHandler m_defaultHandler = new DefaultHandler();
    private ExpandedNameTable m_expandedNameTable = new ExpandedNameTable();

    public synchronized void addDTM(DTM dtm, int i, int i2) {
        try {
            if (i >= 65536) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
            }
            int length = this.m_dtms.length;
            if (length <= i) {
                int iMin = Math.min(i + 256, 65536);
                DTM[] dtmArr = new DTM[iMin];
                System.arraycopy(this.m_dtms, 0, dtmArr, 0, length);
                this.m_dtms = dtmArr;
                int[] iArr = new int[iMin];
                System.arraycopy(this.m_dtm_offsets, 0, iArr, 0, length);
                this.m_dtm_offsets = iArr;
            }
            this.m_dtms[i] = dtm;
            this.m_dtm_offsets[i] = i2;
            dtm.documentRegistration();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTMIterator createDTMIterator(int i, DTMFilter dTMFilter, boolean z) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTM createDocumentFragment() throws Throwable {
        try {
            try {
                try {
                    return getDTM(new DOMSource(JdkXmlUtils.getDOMFactory(super.overrideDefaultParser()).newDocumentBuilder().newDocument().createDocumentFragment()), true, null, false, false);
                } catch (Exception e) {
                    e = e;
                    throw new DTMException(e);
                }
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:68:0x00d9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:69:0x00db A[Catch: all -> 0x00ce, TryCatch #8 {all -> 0x00ce, blocks: (B:55:0x00c0, B:59:0x00c8, B:69:0x00db, B:70:0x00e1, B:71:0x00e9, B:82:0x010f, B:84:0x0115, B:85:0x0118, B:86:0x011b, B:90:0x0121, B:91:0x0129, B:93:0x012b, B:94:0x012f, B:65:0x00d3, B:104:0x0151, B:106:0x015d, B:107:0x0160, B:108:0x0165, B:120:0x018b, B:121:0x0193, B:123:0x0195, B:124:0x0199), top: B:151:0x00c0, inners: #9, #16, #15 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x00e1 A[Catch: all -> 0x00ce, TryCatch #8 {all -> 0x00ce, blocks: (B:55:0x00c0, B:59:0x00c8, B:69:0x00db, B:70:0x00e1, B:71:0x00e9, B:82:0x010f, B:84:0x0115, B:85:0x0118, B:86:0x011b, B:90:0x0121, B:91:0x0129, B:93:0x012b, B:94:0x012f, B:65:0x00d3, B:104:0x0151, B:106:0x015d, B:107:0x0160, B:108:0x0165, B:120:0x018b, B:121:0x0193, B:123:0x0195, B:124:0x0199), top: B:151:0x00c0, inners: #9, #16, #15 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:82:0x010f A[Catch: all -> 0x00ce, TRY_ENTER, TryCatch #8 {all -> 0x00ce, blocks: (B:55:0x00c0, B:59:0x00c8, B:69:0x00db, B:70:0x00e1, B:71:0x00e9, B:82:0x010f, B:84:0x0115, B:85:0x0118, B:86:0x011b, B:90:0x0121, B:91:0x0129, B:93:0x012b, B:94:0x012f, B:65:0x00d3, B:104:0x0151, B:106:0x015d, B:107:0x0160, B:108:0x0165, B:120:0x018b, B:121:0x0193, B:123:0x0195, B:124:0x0199), top: B:151:0x00c0, inners: #9, #16, #15 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0115 A[Catch: all -> 0x00ce, TryCatch #8 {all -> 0x00ce, blocks: (B:55:0x00c0, B:59:0x00c8, B:69:0x00db, B:70:0x00e1, B:71:0x00e9, B:82:0x010f, B:84:0x0115, B:85:0x0118, B:86:0x011b, B:90:0x0121, B:91:0x0129, B:93:0x012b, B:94:0x012f, B:65:0x00d3, B:104:0x0151, B:106:0x015d, B:107:0x0160, B:108:0x0165, B:120:0x018b, B:121:0x0193, B:123:0x0195, B:124:0x0199), top: B:151:0x00c0, inners: #9, #16, #15 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.lang.Object, org.xml.sax.XMLReader] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r14v0, types: [com.sun.org.apache.xml.internal.dtm.DTMManager, com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault] */
    /* JADX WARN: Type inference failed for: r15v24 */
    /* JADX WARN: Type inference failed for: r15v25, types: [com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource] */
    /* JADX WARN: Type inference failed for: r15v33, types: [com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource_Filter] */
    /* JADX WARN: Type inference failed for: r15v34, types: [com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource_Filter] */
    /* JADX WARN: Type inference failed for: r15v35 */
    /* JADX WARN: Type inference failed for: r15v41 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4, types: [org.xml.sax.XMLReader] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.sun.org.apache.xml.internal.dtm.DTM, com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, java.lang.Object, org.xml.sax.ContentHandler, org.xml.sax.DTDHandler, org.xml.sax.ErrorHandler] */
    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3) {
        Throwable th;
        boolean z4;
        ?? r1;
        ?? r12;
        InputSource inputSource;
        ?? incrementalSAXSource_Filter;
        XMLStringFactory xMLStringFactory = this.m_xsf;
        int firstFreeDTMID = getFirstFreeDTMID();
        int i = firstFreeDTMID << 16;
        boolean z5 = false;
        if (source != null && (source instanceof DOMSource)) {
            DOM2DTM dom2dtm = new DOM2DTM(this, (DOMSource) source, i, dTMWSFilter, xMLStringFactory, z3);
            addDTM(dom2dtm, firstFreeDTMID, 0);
            return dom2dtm;
        }
        boolean z6 = source != null ? source instanceof SAXSource : true;
        boolean z7 = source != null ? source instanceof StreamSource : false;
        if (!z6 && !z7) {
            throw new DTMException(XMLMessages.createXMLMessage("ER_NOT_SUPPORTED", new Object[]{source}));
        }
        if (source == null) {
            r12 = 0;
            inputSource = null;
        } else {
            try {
                XMLReader xMLReader = getXMLReader(source);
                try {
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
                    r12 = xMLReader;
                    inputSource = inputSourceSourceToInputSource;
                } catch (Throwable th2) {
                    th = th2;
                    z4 = z2;
                    r1 = xMLReader;
                    if (r1 != 0 && (!this.m_incremental || !z4)) {
                        r1.setContentHandler(this.m_defaultHandler);
                        r1.setDTDHandler(this.m_defaultHandler);
                        r1.setErrorHandler(this.m_defaultHandler);
                        try {
                            r1.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                        } catch (Exception unused2) {
                        }
                    }
                    releaseXMLReader(r1);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                z4 = z2;
                r1 = 0;
            }
        }
        try {
            ?? sax2dtm = (source != null || !z || z2 || z3) ? new SAX2DTM(this, source, i, dTMWSFilter, xMLStringFactory, z3) : new SAX2RTFDTM(this, source, i, dTMWSFilter, xMLStringFactory, z3);
            addDTM(sax2dtm, firstFreeDTMID, 0);
            if (r12 != 0 && r12.getClass().getName().equals("com.sun.org.apache.xerces.internal.parsers.SAXParser")) {
                z5 = true;
            }
            z4 = z5 ? true : z2;
            try {
                boolean z8 = this.m_incremental;
                if (z8 && z4) {
                    if (z5) {
                        try {
                            incrementalSAXSource_Filter = new IncrementalSAXSource_Xerces();
                        } catch (Exception e) {
                            e.printStackTrace();
                            incrementalSAXSource_Filter = 0;
                        }
                        if (incrementalSAXSource_Filter == 0) {
                            if (r12 == 0) {
                                incrementalSAXSource_Filter = new IncrementalSAXSource_Filter();
                            } else {
                                incrementalSAXSource_Filter = new IncrementalSAXSource_Filter();
                                incrementalSAXSource_Filter.setXMLReader(r12);
                            }
                        }
                        sax2dtm.setIncrementalSAXSource(incrementalSAXSource_Filter);
                        if (inputSource == null) {
                            if (r12 != 0 && (!this.m_incremental || !z4)) {
                                r12.setContentHandler(this.m_defaultHandler);
                                r12.setDTDHandler(this.m_defaultHandler);
                                r12.setErrorHandler(this.m_defaultHandler);
                                try {
                                    r12.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                                } catch (Exception unused3) {
                                }
                            }
                            releaseXMLReader(r12);
                            return sax2dtm;
                        }
                        if (r12.getErrorHandler() == null) {
                            r12.setErrorHandler(sax2dtm);
                        }
                        r12.setDTDHandler(sax2dtm);
                        try {
                            incrementalSAXSource_Filter.startParse(inputSource);
                        } catch (RuntimeException e2) {
                            sax2dtm.clearCoRoutine();
                            throw e2;
                        } catch (Exception e3) {
                            sax2dtm.clearCoRoutine();
                            throw new WrappedRuntimeException(e3);
                        }
                    } else {
                        incrementalSAXSource_Filter = 0;
                        if (incrementalSAXSource_Filter == 0) {
                            if (r12 == 0) {
                                incrementalSAXSource_Filter = new IncrementalSAXSource_Filter();
                            } else {
                                incrementalSAXSource_Filter = new IncrementalSAXSource_Filter();
                                incrementalSAXSource_Filter.setXMLReader(r12);
                            }
                        }
                        sax2dtm.setIncrementalSAXSource(incrementalSAXSource_Filter);
                        if (inputSource == null) {
                            if (r12 != 0) {
                                r12.setContentHandler(this.m_defaultHandler);
                                r12.setDTDHandler(this.m_defaultHandler);
                                r12.setErrorHandler(this.m_defaultHandler);
                                r12.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                            }
                            releaseXMLReader(r12);
                            return sax2dtm;
                        }
                        if (r12.getErrorHandler() == null) {
                            r12.setErrorHandler(sax2dtm);
                        }
                        r12.setDTDHandler(sax2dtm);
                        incrementalSAXSource_Filter.startParse(inputSource);
                    }
                } else {
                    if (r12 == 0) {
                        if (r12 != 0 && (!z8 || !z4)) {
                            r12.setContentHandler(this.m_defaultHandler);
                            r12.setDTDHandler(this.m_defaultHandler);
                            r12.setErrorHandler(this.m_defaultHandler);
                            try {
                                r12.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                            } catch (Exception unused4) {
                            }
                        }
                        releaseXMLReader(r12);
                        return sax2dtm;
                    }
                    r12.setContentHandler(sax2dtm);
                    r12.setDTDHandler(sax2dtm);
                    if (r12.getErrorHandler() == null) {
                        r12.setErrorHandler(sax2dtm);
                    }
                    try {
                        r12.setProperty("http://xml.org/sax/properties/lexical-handler", sax2dtm);
                    } catch (SAXNotRecognizedException | SAXNotSupportedException unused5) {
                    }
                    try {
                        r12.parse(inputSource);
                    } catch (RuntimeException e4) {
                        sax2dtm.clearCoRoutine();
                        throw e4;
                    } catch (Exception e5) {
                        sax2dtm.clearCoRoutine();
                        throw new WrappedRuntimeException(e5);
                    }
                }
                if (r12 != 0 && (!this.m_incremental || !z4)) {
                    r12.setContentHandler(this.m_defaultHandler);
                    r12.setDTDHandler(this.m_defaultHandler);
                    r12.setErrorHandler(this.m_defaultHandler);
                    try {
                        r12.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                    } catch (Exception unused6) {
                    }
                }
                releaseXMLReader(r12);
                return sax2dtm;
            } catch (Throwable th4) {
                th = th4;
                r1 = r12;
                if (r1 != 0) {
                    r1.setContentHandler(this.m_defaultHandler);
                    r1.setDTDHandler(this.m_defaultHandler);
                    r1.setErrorHandler(this.m_defaultHandler);
                    r1.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                }
                releaseXMLReader(r1);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            z4 = z2;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized int getDTMHandleFromNode(Node node) throws Throwable {
        Throwable th;
        int handleOfNode;
        try {
            if (node == null) {
                throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_NODE_NON_NULL", null));
            }
            try {
                try {
                    if (node instanceof DTMNodeProxy) {
                        return ((DTMNodeProxy) node).getDTMNodeNumber();
                    }
                    int length = this.m_dtms.length;
                    for (int i = 0; i < length; i++) {
                        DTM dtm = this.m_dtms[i];
                        if (dtm != null && (dtm instanceof DOM2DTM) && (handleOfNode = ((DOM2DTM) dtm).getHandleOfNode(node)) != -1) {
                            return handleOfNode;
                        }
                    }
                    Node node2 = node;
                    for (Node ownerElement = node.getNodeType() == 2 ? ((Attr) node).getOwnerElement() : node.getParentNode(); ownerElement != null; ownerElement = ownerElement.getParentNode()) {
                        node2 = ownerElement;
                    }
                    DOM2DTM dom2dtm = (DOM2DTM) getDTM(new DOMSource(node2), false, null, true, true);
                    int attributeNode = node instanceof DOM2DTMdefaultNamespaceDeclarationNode ? dom2dtm.getAttributeNode(dom2dtm.getHandleOfNode(((Attr) node).getOwnerElement()), node.getNamespaceURI(), node.getLocalName()) : dom2dtm.getHandleOfNode(node);
                    if (-1 != attributeNode) {
                        return attributeNode;
                    }
                    throw new RuntimeException(XMLMessages.createXMLMessage("ER_COULD_NOT_RESOLVE_NODE", null));
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                th = th;
            }
            throw th;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized int getDTMIdentity(DTM dtm) {
        if (dtm instanceof DTMDefaultBase) {
            DTMDefaultBase dTMDefaultBase = (DTMDefaultBase) dtm;
            if (dTMDefaultBase.getManager() != this) {
                return -1;
            }
            return dTMDefaultBase.getDTMIDs().elementAt(0);
        }
        int length = this.m_dtms.length;
        for (int i = 0; i < length; i++) {
            if (this.m_dtms[i] == dtm && this.m_dtm_offsets[i] == 0) {
                return i << 16;
            }
        }
        return -1;
    }

    public ExpandedNameTable getExpandedNameTable(DTM dtm) {
        return this.m_expandedNameTable;
    }

    public synchronized int getFirstFreeDTMID() {
        int length = this.m_dtms.length;
        for (int i = 1; i < length; i++) {
            if (this.m_dtms[i] == null) {
                return i;
            }
        }
        return length;
    }

    public synchronized XMLReader getXMLReader(Source source) {
        XMLReader xMLReader;
        try {
            try {
                xMLReader = source instanceof SAXSource ? ((SAXSource) source).getXMLReader() : null;
                if (xMLReader == null) {
                    if (this.m_readerManager == null) {
                        this.m_readerManager = XMLReaderManager.getInstance(super.overrideDefaultParser());
                    }
                    xMLReader = this.m_readerManager.getXMLReader();
                }
            } catch (SAXException e) {
                throw new DTMException(e.getMessage(), e);
            }
        } catch (Throwable th) {
            throw th;
        }
        return xMLReader;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized boolean release(DTM dtm, boolean z) {
        try {
            if (dtm instanceof SAX2DTM) {
                ((SAX2DTM) dtm).clearCoRoutine();
            }
            if (dtm instanceof DTMDefaultBase) {
                SuballocatedIntVector dTMIDs = ((DTMDefaultBase) dtm).getDTMIDs();
                for (int size = dTMIDs.size() - 1; size >= 0; size--) {
                    this.m_dtms[dTMIDs.elementAt(size) >>> 16] = null;
                }
            } else {
                int dTMIdentity = getDTMIdentity(dtm);
                if (dTMIdentity >= 0) {
                    this.m_dtms[dTMIdentity >>> 16] = null;
                }
            }
            dtm.documentRelease();
        } catch (Throwable th) {
            throw th;
        }
        return true;
    }

    public synchronized void releaseXMLReader(XMLReader xMLReader) {
        XMLReaderManager xMLReaderManager = this.m_readerManager;
        if (xMLReaderManager != null) {
            xMLReaderManager.releaseXMLReader(xMLReader);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTMIterator createDTMIterator(String str, PrefixResolver prefixResolver) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTMIterator createDTMIterator(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTMIterator createDTMIterator(Object obj, int i) {
        return null;
    }

    public synchronized void addDTM(DTM dtm, int i) {
        addDTM(dtm, i, 0);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMManager
    public synchronized DTM getDTM(int i) {
        try {
        } catch (ArrayIndexOutOfBoundsException e) {
            if (i == -1) {
                return null;
            }
            throw e;
        }
        return this.m_dtms[i >>> 16];
    }
}
