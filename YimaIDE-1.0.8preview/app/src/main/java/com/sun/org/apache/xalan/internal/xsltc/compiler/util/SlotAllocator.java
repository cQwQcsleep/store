package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SlotAllocator {
    private int _firstAvailableSlot;
    private int _size = 8;
    private int _free = 0;
    private int[] _slotsTaken = new int[8];

    public int allocateSlot(com.sun.org.apache.bcel.internal.generic.Type type) {
        int size = type.getSize();
        int i = this._free;
        int i2 = this._firstAvailableSlot;
        int i3 = i + size;
        int i4 = this._size;
        if (i3 > i4) {
            int i5 = i4 * 2;
            this._size = i5;
            int[] iArr = new int[i5];
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i6] = this._slotsTaken[i6];
            }
            this._slotsTaken = iArr;
        }
        int i7 = 0;
        while (i7 < i) {
            int i8 = i2 + size;
            int i9 = this._slotsTaken[i7];
            if (i8 <= i9) {
                for (int i10 = i - 1; i10 >= i7; i10--) {
                    int[] iArr2 = this._slotsTaken;
                    iArr2[i10 + size] = iArr2[i10];
                }
                break;
            }
            i7++;
            i2 = i9 + 1;
        }
        for (int i11 = 0; i11 < size; i11++) {
            this._slotsTaken[i7 + i11] = i2 + i11;
        }
        this._free += size;
        return i2;
    }

    public void initialize(LocalVariableGen[] localVariableGenArr) {
        int length = localVariableGenArr.length;
        int iMax = 0;
        for (int i = 0; i < length; i++) {
            iMax = Math.max(iMax, localVariableGenArr[i].getIndex() + localVariableGenArr[i].getType().getSize());
        }
        this._firstAvailableSlot = iMax;
    }

    public void releaseSlot(LocalVariableGen localVariableGen) {
        int size = localVariableGen.getType().getSize();
        int index = localVariableGen.getIndex();
        int i = this._free;
        int i2 = 0;
        while (i2 < i) {
            if (this._slotsTaken[i2] == index) {
                for (int i3 = i2 + size; i3 < i; i3++) {
                    int[] iArr = this._slotsTaken;
                    iArr[i2] = iArr[i3];
                    i2++;
                }
                this._free -= size;
                return;
            }
            i2++;
        }
        throw new Error(new ErrorMsg(ErrorMsg.INTERNAL_ERR, "Variable slot allocation error(size=" + size + ", slot=" + index + ", limit=" + i + ")").toString());
    }
}
