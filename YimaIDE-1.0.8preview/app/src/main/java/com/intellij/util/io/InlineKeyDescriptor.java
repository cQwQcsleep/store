package com.intellij.util.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class InlineKeyDescriptor<T> implements KeyDescriptor<T> {
    private final boolean myCompactFormat = isCompactFormat();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "out";
        } else {
            objArr[0] = "in";
        }
        objArr[1] = "com/intellij/util/io/InlineKeyDescriptor";
        if (i != 1) {
            objArr[2] = "save";
        } else {
            objArr[2] = "read";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public abstract T fromInt(int i);

    @Override // com.intellij.util.io.KeyDescriptor, com.intellij.util.containers.hash.EqualityPolicy
    public final int getHashCode(T t) {
        return toInt(t);
    }

    public boolean isCompactFormat() {
        return false;
    }

    @Override // com.intellij.util.containers.hash.EqualityPolicy
    public final boolean isEqual(T t, T t2) {
        return toInt(t) == toInt(t2);
    }

    @Override // com.intellij.util.io.DataExternalizer
    public final T read(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(1);
        }
        return fromInt(this.myCompactFormat ? DataInputOutputUtil.readINT(dataInput) : dataInput.readInt());
    }

    @Override // com.intellij.util.io.KeyDescriptor, com.intellij.util.io.DataExternalizer
    public final void save(DataOutput dataOutput, T t) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(0);
        }
        int i = toInt(t);
        if (this.myCompactFormat) {
            DataInputOutputUtil.writeINT(dataOutput, i);
        } else {
            dataOutput.writeInt(i);
        }
    }

    public abstract int toInt(T t);
}
