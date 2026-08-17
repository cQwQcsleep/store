package com.sun.org.apache.xml.internal.serializer.dom3;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMStringList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DOMStringListImpl implements DOMStringList {
    private List<String> fStrings;

    public DOMStringListImpl(String[] strArr) {
        this.fStrings = new ArrayList();
        if (strArr != null) {
            for (String str : strArr) {
                this.fStrings.add(str);
            }
        }
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
        try {
            return this.fStrings.get(i);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public DOMStringListImpl(List<String> list) {
        this.fStrings = list;
    }

    public DOMStringListImpl() {
        this.fStrings = new ArrayList();
    }
}
