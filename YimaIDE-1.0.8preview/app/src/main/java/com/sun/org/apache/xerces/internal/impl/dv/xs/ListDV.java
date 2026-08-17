package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import java.util.AbstractList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ListDV extends TypeValidator {

    public static final class ListData extends AbstractList<Object> implements ObjectList {
        private String canonical;
        final Object[] data;

        public ListData(Object[] objArr) {
            this.data = objArr;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List, com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
        public boolean contains(Object obj) {
            int i = 0;
            while (true) {
                Object[] objArr = this.data;
                if (i >= objArr.length) {
                    return false;
                }
                if (obj == objArr[i]) {
                    return true;
                }
                i++;
            }
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (!(obj instanceof ListData)) {
                return false;
            }
            Object[] objArr = ((ListData) obj).data;
            int length = this.data.length;
            if (length != objArr.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!this.data[i].equals(objArr[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            if (i >= 0) {
                Object[] objArr = this.data;
                if (i < objArr.length) {
                    return objArr[i];
                }
            }
            b1e.a("Index: ", i);
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
        public int getLength() {
            return this.data.length;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int i = 0;
            int iHashCode = 0;
            while (true) {
                Object[] objArr = this.data;
                if (i >= objArr.length) {
                    return iHashCode;
                }
                iHashCode ^= objArr[i].hashCode();
                i++;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
        public Object item(int i) {
            if (i < 0) {
                return null;
            }
            Object[] objArr = this.data;
            if (i >= objArr.length) {
                return null;
            }
            return objArr[i];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return getLength();
        }

        @Override // java.util.AbstractCollection
        public synchronized String toString() {
            try {
                if (this.canonical == null) {
                    int length = this.data.length;
                    StringBuffer stringBuffer = new StringBuffer();
                    if (length > 0) {
                        stringBuffer.append(this.data[0].toString());
                    }
                    for (int i = 1; i < length; i++) {
                        stringBuffer.append(' ');
                        stringBuffer.append(this.data[i].toString());
                    }
                    this.canonical = stringBuffer.toString();
                }
            } catch (Throwable th) {
                throw th;
            }
            return this.canonical;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        return str;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2079;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int getDataLength(Object obj) {
        return ((ListData) obj).getLength();
    }
}
