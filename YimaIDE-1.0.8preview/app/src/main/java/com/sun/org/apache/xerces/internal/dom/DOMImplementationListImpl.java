package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMImplementationListImpl implements DOMImplementationList {
    private List<DOMImplementation> fImplementations;

    public DOMImplementationListImpl() {
        this.fImplementations = new ArrayList();
    }

    @Override // org.w3c.dom.DOMImplementationList
    public int getLength() {
        return this.fImplementations.size();
    }

    @Override // org.w3c.dom.DOMImplementationList
    public DOMImplementation item(int i) {
        try {
            return this.fImplementations.get(i);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public DOMImplementationListImpl(List<DOMImplementation> list) {
        this.fImplementations = list;
    }
}
