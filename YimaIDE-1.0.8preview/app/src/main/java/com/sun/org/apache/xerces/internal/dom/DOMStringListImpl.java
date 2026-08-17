package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMStringList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMStringListImpl implements DOMStringList {
    private final List<String> fStrings;

    public DOMStringListImpl() {
        this.fStrings = new ArrayList();
    }

    public void add(String str) {
        this.fStrings.add(str);
    }

    @Override // org.w3c.dom.DOMStringList
    public boolean contains(String str) {
        return this.fStrings.contains(str);
    }

    @Override // org.w3c.dom.DOMStringList
    public int getLength() {
        return this.fStrings.size();
    }

    @Override // org.w3c.dom.DOMStringList
    public String item(int i) {
        int length = getLength();
        if (i < 0 || i >= length) {
            return null;
        }
        return this.fStrings.get(i);
    }

    public DOMStringListImpl(List<String> list) {
        this.fStrings = list;
    }
}
