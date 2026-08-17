package com.sun.org.apache.xerces.internal.impl.xs.traversers;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class SmallContainer extends Container {
    String[] keys;

    public SmallContainer(int i) {
        this.keys = new String[i];
        this.values = new OneAttr[i];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.traversers.Container
    public OneAttr get(String str) {
        for (int i = 0; i < this.pos; i++) {
            if (this.keys[i].equals(str)) {
                return this.values[i];
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.traversers.Container
    public void put(String str, OneAttr oneAttr) {
        String[] strArr = this.keys;
        int i = this.pos;
        strArr[i] = str;
        OneAttr[] oneAttrArr = this.values;
        this.pos = i + 1;
        oneAttrArr[i] = oneAttr;
    }
}
