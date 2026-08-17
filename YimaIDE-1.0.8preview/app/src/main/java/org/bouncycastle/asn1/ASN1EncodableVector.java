package org.bouncycastle.asn1;

import defpackage.w01;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ASN1EncodableVector {
    private static final int DEFAULT_CAPACITY = 10;
    static final ASN1Encodable[] EMPTY_ELEMENTS = new ASN1Encodable[0];
    private boolean copyOnWrite;
    private int elementCount;
    private ASN1Encodable[] elements;

    public ASN1EncodableVector(int i) {
        if (i < 0) {
            w01.a("'initialCapacity' must not be negative");
            throw null;
        }
        this.elements = i == 0 ? EMPTY_ELEMENTS : new ASN1Encodable[i];
        this.elementCount = 0;
        this.copyOnWrite = false;
    }

    public static ASN1Encodable[] cloneElements(ASN1Encodable[] aSN1EncodableArr) {
        return aSN1EncodableArr.length < 1 ? EMPTY_ELEMENTS : (ASN1Encodable[]) aSN1EncodableArr.clone();
    }

    private void doAddAll(ASN1Encodable[] aSN1EncodableArr, String str) {
        int length = aSN1EncodableArr.length;
        if (length < 1) {
            return;
        }
        int length2 = this.elements.length;
        int i = this.elementCount + length;
        int i2 = 0;
        if ((i > length2) | this.copyOnWrite) {
            reallocate(i);
        }
        do {
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[i2];
            if (aSN1Encodable == null) {
                x0e.a(str);
                return;
            } else {
                this.elements[this.elementCount + i2] = aSN1Encodable;
                i2++;
            }
        } while (i2 < length);
        this.elementCount = i;
    }

    private void reallocate(int i) {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[Math.max(this.elements.length, i + (i >> 1))];
        System.arraycopy(this.elements, 0, aSN1EncodableArr, 0, this.elementCount);
        this.elements = aSN1EncodableArr;
        this.copyOnWrite = false;
    }

    public void add(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable == null) {
            x0e.a("'element' cannot be null");
            return;
        }
        int length = this.elements.length;
        int i = this.elementCount + 1;
        if (this.copyOnWrite | (i > length)) {
            reallocate(i);
        }
        this.elements[this.elementCount] = aSN1Encodable;
        this.elementCount = i;
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        if (aSN1EncodableVector != null) {
            doAddAll(aSN1EncodableVector.elements, "'other' elements cannot be null");
        } else {
            x0e.a("'other' cannot be null");
        }
    }

    public ASN1Encodable[] copyElements() {
        int i = this.elementCount;
        if (i == 0) {
            return EMPTY_ELEMENTS;
        }
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[i];
        System.arraycopy(this.elements, 0, aSN1EncodableArr, 0, i);
        return aSN1EncodableArr;
    }

    public ASN1Encodable get(int i) {
        if (i < this.elementCount) {
            return this.elements[i];
        }
        throw new ArrayIndexOutOfBoundsException(i + " >= " + this.elementCount);
    }

    public int size() {
        return this.elementCount;
    }

    public ASN1Encodable[] takeElements() {
        int i = this.elementCount;
        if (i == 0) {
            return EMPTY_ELEMENTS;
        }
        ASN1Encodable[] aSN1EncodableArr = this.elements;
        if (aSN1EncodableArr.length == i) {
            this.copyOnWrite = true;
            return aSN1EncodableArr;
        }
        ASN1Encodable[] aSN1EncodableArr2 = new ASN1Encodable[i];
        System.arraycopy(aSN1EncodableArr, 0, aSN1EncodableArr2, 0, i);
        return aSN1EncodableArr2;
    }

    public void addAll(ASN1Encodable[] aSN1EncodableArr) {
        if (aSN1EncodableArr != null) {
            doAddAll(aSN1EncodableArr, "'others' elements cannot be null");
        } else {
            x0e.a("'others' cannot be null");
        }
    }

    public ASN1EncodableVector() {
        this(10);
    }
}
