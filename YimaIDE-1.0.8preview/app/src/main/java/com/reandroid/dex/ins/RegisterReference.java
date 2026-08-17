package com.reandroid.dex.ins;

import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegisterType;
import com.reandroid.dex.common.RegistersTable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegisterReference extends Register {
    private final int index;
    private final RegistersSet registersSet;

    public static class Editor extends RegisterReference {
        private final RegisterReference mBaseRegisterReference;
        private final int number;
        private final boolean parameter;

        public Editor(RegisterReference registerReference) {
            super(null, null, registerReference.getIndex());
            this.mBaseRegisterReference = registerReference;
            this.number = registerReference.getNumber();
            this.parameter = registerReference.isParameter();
        }

        private boolean isChanged() {
            RegisterReference baseReg = getBaseReg();
            return isParameter() == baseReg.isParameter() && getNumber() == baseReg.getNumber() && getValue() == baseReg.getValue();
        }

        public void apply() {
            getBaseReg().setRegisterValue(getValue());
        }

        public RegisterReference getBaseReg() {
            return this.mBaseRegisterReference;
        }

        @Override // com.reandroid.dex.ins.RegisterReference
        public int getIndex() {
            return getBaseReg().getIndex();
        }

        @Override // com.reandroid.dex.ins.RegisterReference, com.reandroid.dex.common.Register
        public int getNumber() {
            return this.number;
        }

        @Override // com.reandroid.dex.ins.RegisterReference
        public RegistersSet getRegistersSet() {
            return getBaseReg().getRegistersSet();
        }

        @Override // com.reandroid.dex.common.Register
        public RegistersTable getRegistersTable() {
            return getBaseReg().getRegistersTable();
        }

        @Override // com.reandroid.dex.ins.RegisterReference, com.reandroid.dex.common.Register
        public int getValue() {
            int number = getNumber();
            return isParameter() ? number + getLocalRegistersCount() : number;
        }

        @Override // com.reandroid.dex.ins.RegisterReference, com.reandroid.dex.common.Register
        public boolean isParameter() {
            return this.parameter;
        }
    }

    public RegisterReference(RegistersTable registersTable, RegistersSet registersSet, int i) {
        super(0, false, registersTable);
        this.registersSet = registersSet;
        this.index = i;
    }

    @Override // com.reandroid.dex.common.Register
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            RegisterReference registerReference = (RegisterReference) obj;
            if (isParameter() == registerReference.isParameter() && getNumber() == registerReference.getNumber()) {
                return true;
            }
        }
        return false;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLimit() {
        return getRegistersSet().getRegisterLimit(getIndex());
    }

    public int getLocalRegistersCount() {
        RegistersTable registersTable = getRegistersTable();
        return registersTable.getRegistersCount() - registersTable.getParameterRegistersCount();
    }

    @Override // com.reandroid.dex.common.Register
    public int getNumber() {
        int value = getValue();
        int localRegistersCount = getLocalRegistersCount();
        return value >= localRegistersCount ? value - localRegistersCount : value;
    }

    public RegisterType getRegisterType() {
        return getRegistersSet().getRegisterFormat().get(getIndex());
    }

    public RegistersSet getRegistersSet() {
        return this.registersSet;
    }

    @Override // com.reandroid.dex.common.Register
    public int getValue() {
        return getRegistersSet().getRegister(getIndex());
    }

    @Override // com.reandroid.dex.common.Register
    public boolean isParameter() {
        return getValue() >= getLocalRegistersCount();
    }

    public void setRegisterValue(int i) {
        getRegistersSet().setRegister(getIndex(), i);
    }

    public Editor toEditor() {
        return new Editor(this);
    }
}
