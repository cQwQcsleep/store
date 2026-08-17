package com.reandroid.dex.ins;

import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.utils.collection.ArraySupplierIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.InstanceIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegistersEditor implements ArraySupplier<RegistersSetEditor>, Iterable<RegistersSetEditor> {
    private static final int DEFAULT_CAPACITY = 100;
    public static final RegistersEditor EMPTY = new RegistersEditor(null) { // from class: com.reandroid.dex.ins.RegistersEditor.1
        @Override // com.reandroid.dex.ins.RegistersEditor
        public void add(Iterator<RegistersSetEditor> it) {
            if (it == null || !it.hasNext()) {
                return;
            }
            w01.a("Empty RegistersEditor");
        }

        @Override // com.reandroid.dex.ins.RegistersEditor
        public boolean contains(Object obj) {
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.reandroid.dex.ins.RegistersEditor, com.reandroid.common.ArraySupplier
        public RegistersSetEditor get(int i) {
            throw new IllegalArgumentException("Empty RegistersEditor");
        }

        @Override // com.reandroid.dex.ins.RegistersEditor, com.reandroid.common.CountSupplier
        public int getCount() {
            return 0;
        }

        @Override // com.reandroid.dex.ins.RegistersEditor
        public int getLocalRegistersCount() {
            throw new IllegalArgumentException("Empty RegistersEditor");
        }

        @Override // com.reandroid.dex.ins.RegistersEditor
        public boolean isEmpty() {
            return true;
        }

        @Override // com.reandroid.dex.ins.RegistersEditor, java.lang.Iterable
        public Iterator<RegistersSetEditor> iterator() {
            return EmptyIterator.of();
        }

        @Override // com.reandroid.dex.ins.RegistersEditor
        public void setLocalRegistersCount(int i) {
            throw new IllegalArgumentException("Empty RegistersEditor");
        }

        @Override // com.reandroid.dex.ins.RegistersEditor
        public void trimToSize() {
        }

        @Override // com.reandroid.dex.ins.RegistersEditor
        public void add(RegistersSetEditor registersSetEditor) {
            if (registersSetEditor == null) {
                return;
            }
            w01.a("Empty RegistersEditor");
        }
    };
    private RegistersSetEditor[] elements;
    private final RegistersTable registersTable;
    private int size;

    public RegistersEditor(RegistersTable registersTable) {
        this.registersTable = registersTable;
    }

    private int availableCapacity() {
        RegistersSetEditor[] registersSetEditorArr = this.elements;
        if (registersSetEditorArr != null) {
            return registersSetEditorArr.length - this.size;
        }
        return 0;
    }

    private void ensureCapacity(int i) {
        if (availableCapacity() >= i) {
            return;
        }
        int i2 = this.size;
        RegistersSetEditor[] registersSetEditorArr = new RegistersSetEditor[i + i2];
        RegistersSetEditor[] registersSetEditorArr2 = this.elements;
        if (registersSetEditorArr2 == null || i2 == 0) {
            this.elements = registersSetEditorArr;
        } else {
            System.arraycopy(registersSetEditorArr2, 0, registersSetEditorArr, 0, i2);
            this.elements = registersSetEditorArr;
        }
    }

    public static RegistersEditor fromIns(RegistersTable registersTable, Iterator<Ins> it) {
        return of(registersTable, InstanceIterator.of(it, RegistersSet.class));
    }

    public static RegistersEditor of(RegistersTable registersTable, Iterator<RegistersSet> it) {
        if (!it.hasNext()) {
            return EMPTY;
        }
        RegistersEditor registersEditor = new RegistersEditor(registersTable);
        while (it.hasNext()) {
            registersEditor.add(RegistersSetEditor.of(registersTable, it.next()));
        }
        return registersEditor;
    }

    public void add(Iterator<RegistersSetEditor> it) {
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public void addLocalRegistersCount(int i) {
        setLocalRegistersCount(getLocalRegistersCount() + i);
    }

    public void apply() {
        Iterator<RegistersSetEditor> it = iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
    }

    public boolean contains(Object obj) {
        RegistersSetEditor[] registersSetEditorArr;
        if (obj == null || (registersSetEditorArr = this.elements) == null) {
            return false;
        }
        for (RegistersSetEditor registersSetEditor : registersSetEditorArr) {
            if (obj.equals(registersSetEditor)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.reandroid.common.ArraySupplier
    public RegistersSetEditor get(int i) {
        RegistersSetEditor[] registersSetEditorArr = this.elements;
        if (registersSetEditorArr != null) {
            return registersSetEditorArr[i];
        }
        return null;
    }

    @Override // com.reandroid.common.CountSupplier
    public int getCount() {
        return this.size;
    }

    public int getLocalRegistersCount() {
        RegistersTable registersTable = getRegistersTable();
        return registersTable.getRegistersCount() - registersTable.getParameterRegistersCount();
    }

    public int getParameterRegistersCount() {
        return getRegistersTable().getParameterRegistersCount();
    }

    public RegistersTable getRegistersTable() {
        return this.registersTable;
    }

    public boolean isEmpty() {
        return getCount() != 0;
    }

    @Override // java.lang.Iterable
    public Iterator<RegistersSetEditor> iterator() {
        return ArraySupplierIterator.of(this);
    }

    public void setLocalRegistersCount(int i) {
        RegistersTable registersTable = getRegistersTable();
        registersTable.setRegistersCount(i + registersTable.getParameterRegistersCount());
    }

    public void trimToSize() {
        if (availableCapacity() == 0) {
            return;
        }
        int i = this.size;
        if (i == 0) {
            this.elements = null;
            return;
        }
        RegistersSetEditor[] registersSetEditorArr = new RegistersSetEditor[i];
        System.arraycopy(this.elements, 0, registersSetEditorArr, 0, i);
        this.elements = registersSetEditorArr;
    }

    public void add(RegistersSetEditor registersSetEditor) {
        if (registersSetEditor == null) {
            return;
        }
        ensureCapacity();
        RegistersSetEditor[] registersSetEditorArr = this.elements;
        int i = this.size;
        registersSetEditorArr[i] = registersSetEditor;
        this.size = i + 1;
    }

    private void ensureCapacity() {
        ensureCapacity(this.size == 0 ? 1 : 100);
    }
}
