package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class IteratorPool implements Serializable {
    static final long serialVersionUID = -460927331149566998L;
    private final List<DTMIterator> m_freeStack = new ArrayList();
    private final DTMIterator m_orig;

    public IteratorPool(DTMIterator dTMIterator) {
        this.m_orig = dTMIterator;
    }

    public synchronized void freeInstance(DTMIterator dTMIterator) {
        this.m_freeStack.add(dTMIterator);
    }

    public synchronized DTMIterator getInstance() {
        if (!this.m_freeStack.isEmpty()) {
            List<DTMIterator> list = this.m_freeStack;
            return list.remove(list.size() - 1);
        }
        try {
            return (DTMIterator) this.m_orig.clone();
        } catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
    }

    public synchronized DTMIterator getInstanceOrThrow() throws CloneNotSupportedException {
        if (this.m_freeStack.isEmpty()) {
            return (DTMIterator) this.m_orig.clone();
        }
        List<DTMIterator> list = this.m_freeStack;
        return list.remove(list.size() - 1);
    }
}
