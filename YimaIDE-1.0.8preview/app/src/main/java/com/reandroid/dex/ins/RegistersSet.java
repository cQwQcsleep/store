package com.reandroid.dex.ins;

import com.reandroid.dex.common.RegisterFormat;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface RegistersSet {
    default int getRegister() {
        return getRegister(0);
    }

    int getRegister(int i);

    default RegisterFormat getRegisterFormat() {
        return null;
    }

    int getRegisterLimit(int i);

    int getRegistersCount();

    default boolean isWideRegisterAt(int i) {
        return false;
    }

    default boolean removeRegisterAt(int i) {
        int registersCount;
        if (i < 0 || i >= (registersCount = getRegistersCount())) {
            return false;
        }
        int i2 = registersCount - 1;
        if (getRegisterFormat().isRange()) {
            if (i == 0) {
                setRegister(getRegister() + 1);
                return true;
            }
            if (i != i2) {
                return false;
            }
            setRegistersCount(i2);
            return true;
        }
        while (i < i2) {
            int i3 = i + 1;
            setRegister(i, getRegister(i3));
            i = i3;
        }
        setRegister(i2, 0);
        setRegistersCount(i2);
        return true;
    }

    default void setRegister(int i) {
        setRegister(0, i);
    }

    void setRegister(int i, int i2);

    void setRegistersCount(int i);
}
