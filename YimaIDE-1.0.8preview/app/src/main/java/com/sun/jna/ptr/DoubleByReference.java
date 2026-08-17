package com.sun.jna.ptr;

import com.sun.jna.Pointer;
import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DoubleByReference extends ByReference {
    public DoubleByReference(double d) {
        super(8);
        setValue(d);
    }

    public double getValue() {
        return getPointer().getDouble(0L);
    }

    public void setValue(double d) {
        getPointer().setDouble(0L, d);
    }

    @Override // com.sun.jna.ptr.ByReference, com.sun.jna.PointerType
    public String toString() {
        return String.format("double@0x%x=%s", Long.valueOf(Pointer.nativeValue(getPointer())), Double.valueOf(getValue()));
    }

    public DoubleByReference() {
        this(XPath.MATCH_SCORE_QNAME);
    }
}
