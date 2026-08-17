package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMWSFilter implements DTMWSFilter {
    private DTM m_currentDTM;
    private short[] m_currentMapping;
    private StripFilter m_filter;
    private Map<DTM, short[]> m_mappings = new HashMap();
    private AbstractTranslet m_translet;

    /* JADX WARN: Multi-variable type inference failed */
    public DOMWSFilter(AbstractTranslet abstractTranslet) {
        this.m_translet = abstractTranslet;
        if (abstractTranslet instanceof StripFilter) {
            this.m_filter = (StripFilter) abstractTranslet;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMWSFilter
    public short getShouldStripSpace(int i, DTM dtm) {
        short[] sArr;
        if (this.m_filter == null || !(dtm instanceof DOM)) {
            return (short) 1;
        }
        DOM dom = (DOM) dtm;
        if (!(dtm instanceof DOMEnhancedForDTM)) {
            return (short) 3;
        }
        DOMEnhancedForDTM dOMEnhancedForDTM = (DOMEnhancedForDTM) dtm;
        if (dtm == this.m_currentDTM) {
            sArr = this.m_currentMapping;
        } else {
            short[] mapping = this.m_mappings.get(dtm);
            if (mapping == null) {
                mapping = dOMEnhancedForDTM.getMapping(this.m_translet.getNamesArray(), this.m_translet.getUrisArray(), this.m_translet.getTypesArray());
                this.m_mappings.put(dtm, mapping);
                this.m_currentDTM = dtm;
                this.m_currentMapping = mapping;
            }
            sArr = mapping;
        }
        int expandedTypeID = dOMEnhancedForDTM.getExpandedTypeID(i);
        return this.m_filter.stripSpace(dom, i, (expandedTypeID < 0 || expandedTypeID >= sArr.length) ? (short) -1 : sArr[expandedTypeID]) ? (short) 2 : (short) 1;
    }
}
