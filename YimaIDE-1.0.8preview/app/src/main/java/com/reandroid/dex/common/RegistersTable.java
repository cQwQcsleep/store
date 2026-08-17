package com.reandroid.dex.common;

import com.reandroid.dex.ins.RegistersSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface RegistersTable {
    boolean ensureLocalRegistersCount(int i);

    default int getLocalRegistersCount() {
        return getRegistersCount() - getParameterRegistersCount();
    }

    int getParameterRegistersCount();

    default Register getRegisterFor(int i) {
        boolean z;
        int localRegistersCount = getLocalRegistersCount();
        if (i >= localRegistersCount) {
            i -= localRegistersCount;
            z = true;
        } else {
            z = false;
        }
        return new Register(i, z);
    }

    default int getRegisterValue(Register register) {
        int number = register.getNumber();
        return register.isParameter() ? number + getLocalRegistersCount() : number;
    }

    default Iterator<Register> getRegisters(final RegistersSet registersSet) {
        return new Iterator<Register>() { // from class: com.reandroid.dex.common.RegistersTable.1
            private int mIndex;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.mIndex < registersSet.getRegistersCount();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Register next() {
                RegistersTable registersTable = RegistersTable.this;
                RegistersSet registersSet2 = registersSet;
                int i = this.mIndex;
                this.mIndex = i + 1;
                return registersTable.getRegisterFor(registersSet2.getRegister(i));
            }
        };
    }

    int getRegistersCount();

    void setParameterRegistersCount(int i);

    void setRegistersCount(int i);
}
