package com.reandroid.dex.ins;

import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.IndexIterator;
import com.reandroid.utils.collection.SizedSupplier;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegistersIterator implements SizedSupplier<RegisterReference>, Iterable<RegisterReference>, SmaliFormat {
    private final RegistersSet registersSet;
    private final RegistersTable registersTable;

    public RegistersIterator(RegistersTable registersTable, RegistersSet registersSet) {
        this.registersTable = registersTable;
        this.registersSet = registersSet;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        if (size == 0) {
            return;
        }
        if (isRange()) {
            get(0).append(smaliWriter);
            smaliWriter.append(" .. ");
            get(size - 1).append(smaliWriter);
        } else {
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    smaliWriter.append(", ");
                }
                get(i).append(smaliWriter);
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RegistersIterator)) {
            return false;
        }
        RegistersIterator registersIterator = (RegistersIterator) obj;
        int size = size();
        if (size != registersIterator.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(registersIterator.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.reandroid.utils.collection.SizedSupplier
    public RegisterReference get(int i) {
        return new RegisterReference(getRegistersTable(), getRegistersSet(), i);
    }

    public RegistersSet getRegistersSet() {
        return this.registersSet;
    }

    public RegistersTable getRegistersTable() {
        return this.registersTable;
    }

    public int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i = 0; i < size; i++) {
            iHashCode = (iHashCode * 31) + get(i).hashCode();
        }
        return iHashCode;
    }

    public boolean isRange() {
        return getRegistersSet().getRegisterFormat().isRange();
    }

    @Override // java.lang.Iterable
    public Iterator<RegisterReference> iterator() {
        return new IndexIterator(this);
    }

    public void setSize(int i) {
        getRegistersSet().setRegistersCount(i);
    }

    @Override // com.reandroid.utils.collection.SizedItem
    public int size() {
        return getRegistersSet().getRegistersCount();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(get(i));
        }
        return sb.toString();
    }
}
