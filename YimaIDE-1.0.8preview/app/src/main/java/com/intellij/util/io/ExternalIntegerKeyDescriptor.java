package com.intellij.util.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class ExternalIntegerKeyDescriptor implements KeyDescriptor<Integer> {
    public static final ExternalIntegerKeyDescriptor INSTANCE = new ExternalIntegerKeyDescriptor();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "out";
        } else {
            objArr[0] = "in";
        }
        objArr[1] = "com/intellij/util/io/ExternalIntegerKeyDescriptor";
        if (i != 1) {
            objArr[2] = "save";
        } else {
            objArr[2] = "read";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX INFO: renamed from: read, reason: merged with bridge method [inline-methods] */
    public Integer m1read(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(1);
        }
        return Integer.valueOf(DataInputOutputUtil.readINT(dataInput));
    }

    public void save(DataOutput dataOutput, Integer num) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(0);
        }
        DataInputOutputUtil.writeINT(dataOutput, num.intValue());
    }

    public int getHashCode(Integer num) {
        return num.intValue();
    }

    public boolean isEqual(Integer num, Integer num2) {
        return num.equals(num2);
    }
}
