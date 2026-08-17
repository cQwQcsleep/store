package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.util.ByteListImpl;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HexBinaryDV extends TypeValidator {

    public static final class XHex extends ByteListImpl {
        public XHex(byte[] bArr) {
            super(bArr);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (!(obj instanceof XHex)) {
                return false;
            }
            byte[] bArr = ((XHex) obj).data;
            int length = this.data.length;
            if (length != bArr.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (this.data[i] != bArr[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i = 0;
            int i2 = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i >= bArr.length) {
                    return i2;
                }
                i2 = (i2 * 37) + (bArr[i] & 255);
                i++;
            }
        }

        @Override // java.util.AbstractCollection
        public synchronized String toString() {
            try {
                if (this.canonical == null) {
                    this.canonical = HexBin.encode(this.data);
                }
            } catch (Throwable th) {
                throw th;
            }
            return this.canonical;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        byte[] bArrDecode = HexBin.decode(str);
        if (bArrDecode != null) {
            return new XHex(bArrDecode);
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_HEXBINARY});
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2079;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int getDataLength(Object obj) {
        return ((XHex) obj).getLength();
    }
}
