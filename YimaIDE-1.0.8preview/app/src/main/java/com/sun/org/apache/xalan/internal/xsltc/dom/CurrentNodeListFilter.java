package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface CurrentNodeListFilter {
    boolean test(int i, int i2, int i3, int i4, AbstractTranslet abstractTranslet, DTMAxisIterator dTMAxisIterator);
}
