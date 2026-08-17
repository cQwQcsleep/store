package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttributeIterator extends ChildTestIterator {
    static final long serialVersionUID = -8417986700712229686L;

    public AttributeIterator(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.ChildTestIterator, com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        return 2;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.ChildTestIterator, com.sun.org.apache.xpath.internal.axes.BasicTestIterator
    public int getNextNode() {
        int i = this.m_lastFetched;
        DTM dtm = this.m_cdtm;
        int firstAttribute = -1 == i ? dtm.getFirstAttribute(this.m_context) : dtm.getNextAttribute(i);
        this.m_lastFetched = firstAttribute;
        return firstAttribute;
    }
}
