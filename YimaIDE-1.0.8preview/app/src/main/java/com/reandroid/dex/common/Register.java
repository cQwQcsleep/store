package com.reandroid.dex.common;

import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Register implements SmaliFormat, Comparable<Register> {
    private final int number;
    private final boolean parameter;
    private final RegistersTable registersTable;

    public Register(int i, boolean z, RegistersTable registersTable) {
        this.number = i;
        this.parameter = z;
        this.registersTable = registersTable;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(getSymbol());
        smaliWriter.appendInteger(getNumber());
    }

    @Override // java.lang.Comparable
    public int compareTo(Register register) {
        return CompareUtil.compare(getValue(), register.getValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Register)) {
            return false;
        }
        Register register = (Register) obj;
        return getNumber() == register.getNumber() && isParameter() == register.isParameter();
    }

    public int getNumber() {
        return this.number;
    }

    public RegistersTable getRegistersTable() {
        return this.registersTable;
    }

    public char getSymbol() {
        return isParameter() ? 'p' : 'v';
    }

    public int getValue() throws NullPointerException {
        RegistersTable registersTable = getRegistersTable();
        if (registersTable == null) {
            throw new NullPointerException("Missing register table for: " + toString());
        }
        int registerValue = registersTable.getRegisterValue(this);
        if (!isParameter()) {
            registersTable.ensureLocalRegistersCount(registerValue + 1);
        }
        return registerValue;
    }

    public int hashCode() {
        return ((isParameter() ? 32 : 31) * 31) + getNumber();
    }

    public boolean isParameter() {
        return this.parameter;
    }

    public String toString() {
        return getSymbol() + Integer.toString(getNumber());
    }

    public Register(int i, boolean z) {
        this(i, z, null);
    }
}
