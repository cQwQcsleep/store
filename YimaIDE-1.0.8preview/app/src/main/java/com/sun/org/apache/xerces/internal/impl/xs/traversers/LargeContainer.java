package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class LargeContainer extends Container {
    Map<String, OneAttr> items;

    public LargeContainer(int i) {
        this.items = new HashMap((i * 2) + 1);
        this.values = new OneAttr[i];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.traversers.Container
    public OneAttr get(String str) {
        return this.items.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.traversers.Container
    public void put(String str, OneAttr oneAttr) {
        this.items.put(str, oneAttr);
        OneAttr[] oneAttrArr = this.values;
        int i = this.pos;
        this.pos = i + 1;
        oneAttrArr[i] = oneAttr;
    }
}
