package com.sun.org.apache.xerces.internal.util;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLAttributesIteratorImpl extends XMLAttributesImpl implements Iterator<XMLAttributesImpl.Attribute> {
    protected int fCurrent = 0;
    protected XMLAttributesImpl.Attribute fLastReturnedItem;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.fCurrent < getLength();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public XMLAttributesImpl.Attribute next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        XMLAttributesImpl.Attribute[] attributeArr = this.fAttributes;
        int i = this.fCurrent;
        this.fCurrent = i + 1;
        XMLAttributesImpl.Attribute attribute = attributeArr[i];
        this.fLastReturnedItem = attribute;
        return attribute;
    }

    @Override // java.util.Iterator
    public void remove() {
        XMLAttributesImpl.Attribute attribute = this.fLastReturnedItem;
        XMLAttributesImpl.Attribute[] attributeArr = this.fAttributes;
        int i = this.fCurrent;
        if (attribute != attributeArr[i - 1]) {
            g33.a();
        } else {
            this.fCurrent = i - 1;
            removeAttributeAt(i);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.util.XMLAttributesImpl, com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void removeAllAttributes() {
        super.removeAllAttributes();
        this.fCurrent = 0;
    }
}
