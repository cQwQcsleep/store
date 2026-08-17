package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class EmptyIterator implements DTMAxisIterator {
    private static final EmptyIterator INSTANCE = new EmptyIterator();

    private EmptyIterator() {
    }

    public static DTMAxisIterator getInstance() {
        return INSTANCE;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final DTMAxisIterator cloneIterator() {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final int getLast() {
        return 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final int getNodeByPosition(int i) {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final int getPosition() {
        return 1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final int getStartNode() {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final void gotoMark() {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final boolean isReverse() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final int next() {
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final DTMAxisIterator reset() {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final void setMark() {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final void setRestartable(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
    public final DTMAxisIterator setStartNode(int i) {
        return this;
    }
}
