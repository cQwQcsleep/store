package com.sun.org.apache.xml.internal.dtm;

import com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import javax.xml.transform.Source;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DTMManager {
    public static final int IDENT_DTM_DEFAULT = -65536;
    public static final int IDENT_DTM_NODE_BITS = 16;
    public static final int IDENT_MAX_DTMS = 65536;
    public static final int IDENT_NODE_DEFAULT = 65535;
    private boolean _overrideDefaultParser;
    protected XMLStringFactory m_xsf = null;
    public boolean m_incremental = false;
    public boolean m_source_location = false;

    public static DTMManager newInstance(XMLStringFactory xMLStringFactory) throws DTMException {
        DTMManagerDefault dTMManagerDefault = new DTMManagerDefault();
        dTMManagerDefault.setXMLStringFactory(xMLStringFactory);
        return dTMManagerDefault;
    }

    public abstract DTMIterator createDTMIterator(int i);

    public abstract DTMIterator createDTMIterator(int i, DTMFilter dTMFilter, boolean z);

    public abstract DTMIterator createDTMIterator(Object obj, int i);

    public abstract DTMIterator createDTMIterator(String str, PrefixResolver prefixResolver);

    public abstract DTM createDocumentFragment();

    public abstract DTM getDTM(int i);

    public abstract DTM getDTM(Source source, boolean z, DTMWSFilter dTMWSFilter, boolean z2, boolean z3);

    public abstract int getDTMHandleFromNode(Node node);

    public abstract int getDTMIdentity(DTM dtm);

    public int getDTMIdentityMask() {
        return -65536;
    }

    public boolean getIncremental() {
        return this.m_incremental;
    }

    public int getNodeIdentityMask() {
        return 65535;
    }

    public boolean getSource_location() {
        return this.m_source_location;
    }

    public XMLStringFactory getXMLStringFactory() {
        return this.m_xsf;
    }

    public boolean overrideDefaultParser() {
        return this._overrideDefaultParser;
    }

    public abstract boolean release(DTM dtm, boolean z);

    public void setIncremental(boolean z) {
        this.m_incremental = z;
    }

    public void setOverrideDefaultParser(boolean z) {
        this._overrideDefaultParser = z;
    }

    public void setSource_location(boolean z) {
        this.m_source_location = z;
    }

    public void setXMLStringFactory(XMLStringFactory xMLStringFactory) {
        this.m_xsf = xMLStringFactory;
    }
}
