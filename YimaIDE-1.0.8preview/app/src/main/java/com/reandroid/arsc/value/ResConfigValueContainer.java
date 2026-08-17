package com.reandroid.arsc.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.arsc.item.IntegerItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ResConfigValueContainer extends ByteArray {
    private final IntegerItem configSize;

    public ResConfigValueContainer(int i, IntegerItem integerItem) {
        super(i);
        this.configSize = integerItem;
    }

    private static byte[] ensureArrayLength(byte[] bArr, int i) {
        if (bArr == null || i == 0) {
            return new byte[i];
        }
        if (bArr.length == i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        if (bArr.length < i) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private static int getNearestValueSize(int i) {
        return ResConfigBase.nearestSize(i + 4) - 4;
    }

    private int getValue(int i, int i2) {
        if (size() < i + i2) {
            return 0;
        }
        if (i2 == 1) {
            return getByteUnsigned(i);
        }
        if (i2 == 2) {
            return getShortUnsigned(i) & 65535;
        }
        if (i2 == 4) {
            return getInteger(i);
        }
        qf1.a("Invalid data size ", i2);
        return 0;
    }

    private static boolean isNullBytes(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean putByteValue(int i, byte b) {
        if (get(i) == b) {
            return false;
        }
        put(i, b);
        return true;
    }

    private boolean putIntegerValue(int i, int i2) {
        if (getInteger(i) == i2) {
            return false;
        }
        putInteger(i, i2);
        return true;
    }

    private boolean putShortValue(int i, int i2) {
        if (getShortUnsigned(i) == i2) {
            return false;
        }
        putShort(i, i2);
        return true;
    }

    private void setValue(int i, int i2, int i3) {
        boolean z;
        boolean zPutIntegerValue;
        int i4 = i + i2;
        if (size() >= i4) {
            z = false;
        } else {
            if (i3 == 0) {
                return;
            }
            ensureArraySize(getNearestValueSize(i4));
            this.configSize.set(size() + 4);
            z = true;
        }
        if (i2 == 1) {
            zPutIntegerValue = putByteValue(i, (byte) i3);
        } else if (i2 == 2) {
            zPutIntegerValue = putShortValue(i, i3);
        } else {
            if (i2 != 4) {
                qf1.a("Invalid data size ", i2);
                return;
            }
            zPutIntegerValue = putIntegerValue(i, i3);
        }
        if (z) {
            onSizeChanged();
        }
        if (zPutIntegerValue) {
            onValueChanged();
        }
    }

    public byte[] getByteArrayValue(int i, int i2) {
        int size = size() - i;
        if (size < i2 || size <= 0) {
            return null;
        }
        return getByteArray(i, i2);
    }

    public int getByteValue(int i) {
        return getValue(i, 1);
    }

    public int getIntValue(int i) {
        return getValue(i, 4);
    }

    public long getLongValue(int i) {
        return Block.getLong(getBytesInternal(), i);
    }

    public int getShortValue(int i) {
        return getValue(i, 2);
    }

    public void onSizeChanged() {
    }

    public void onValueChanged() {
    }

    public void setByteArrayValue(int i, byte[] bArr, int i2) {
        if ((bArr == null || bArr.length == 0) && size() - i < i2) {
            return;
        }
        setValue(i, ensureArrayLength(bArr, i2));
    }

    public void setByteValue(int i, int i2) {
        setValue(i, 1, i2);
    }

    public void setIntValue(int i, int i2) {
        setValue(i, 4, i2);
    }

    public void setShortValue(int i, int i2) {
        setValue(i, 2, i2);
    }

    private void setValue(int i, byte[] bArr) {
        boolean z;
        int size = size();
        int length = bArr.length + i;
        if (size >= length) {
            z = false;
        } else {
            if (isNullBytes(bArr)) {
                return;
            }
            ensureArraySize(getNearestValueSize(length));
            this.configSize.set(size() + 4);
            z = true;
        }
        putByteArray(i, bArr);
        if (z) {
            onSizeChanged();
        }
    }
}
