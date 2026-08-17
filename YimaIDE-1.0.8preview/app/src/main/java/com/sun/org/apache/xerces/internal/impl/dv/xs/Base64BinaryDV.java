package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xerces.internal.impl.dv.util.ByteListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Base64BinaryDV extends TypeValidator {

    public static final class XBase64 extends ByteListImpl {
        public XBase64(byte[] bArr) {
            super(bArr);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (!(obj instanceof XBase64)) {
                return false;
            }
            byte[] bArr = ((XBase64) obj).data;
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
                    this.canonical = Base64.encode(this.data);
                }
            } catch (Throwable th) {
                throw th;
            }
            return this.canonical;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        byte[] bArrDecode = Base64.decode(str);
        if (bArrDecode != null) {
            return new XBase64(bArrDecode);
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_BASE64BINARY});
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2079;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int getDataLength(Object obj) {
        return ((XBase64) obj).getLength();
    }
}
