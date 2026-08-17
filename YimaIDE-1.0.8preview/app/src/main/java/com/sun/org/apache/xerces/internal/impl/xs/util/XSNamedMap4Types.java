package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSNamedMap4Types extends XSNamedMapImpl {
    private final short fType;

    public XSNamedMap4Types(String str, SymbolHash symbolHash, short s) {
        super(str, symbolHash);
        this.fType = s;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl, com.sun.org.apache.xerces.internal.xs.XSNamedMap
    public synchronized int getLength() {
        try {
            if (this.fLength == -1) {
                int length = 0;
                for (int i = 0; i < this.fNSNum; i++) {
                    length += this.fMaps[i].getLength();
                }
                XSObject[] xSObjectArr = new XSObject[length];
                int values = 0;
                for (int i2 = 0; i2 < this.fNSNum; i2++) {
                    values += this.fMaps[i2].getValues(xSObjectArr, values);
                }
                this.fLength = 0;
                this.fArray = new XSObject[length];
                for (int i3 = 0; i3 < length; i3++) {
                    XSTypeDefinition xSTypeDefinition = (XSTypeDefinition) xSObjectArr[i3];
                    if (xSTypeDefinition.getTypeCategory() == this.fType) {
                        XSObject[] xSObjectArr2 = this.fArray;
                        int i4 = this.fLength;
                        this.fLength = i4 + 1;
                        xSObjectArr2[i4] = xSTypeDefinition;
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.fLength;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl, com.sun.org.apache.xerces.internal.xs.XSNamedMap
    public synchronized XSObject item(int i) {
        try {
            if (this.fArray == null) {
                getLength();
            }
            if (i >= 0 && i < this.fLength) {
                return this.fArray[i];
            }
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl, com.sun.org.apache.xerces.internal.xs.XSNamedMap
    public XSObject itemByName(String str, String str2) {
        for (int i = 0; i < this.fNSNum; i++) {
            if (XSNamedMapImpl.isEqual(str, this.fNamespaces[i])) {
                XSTypeDefinition xSTypeDefinition = (XSTypeDefinition) this.fMaps[i].get(str2);
                if (xSTypeDefinition == null || xSTypeDefinition.getTypeCategory() != this.fType) {
                    return null;
                }
                return xSTypeDefinition;
            }
        }
        return null;
    }

    public XSNamedMap4Types(String[] strArr, SymbolHash[] symbolHashArr, int i, short s) {
        super(strArr, symbolHashArr, i);
        this.fType = s;
    }
}
