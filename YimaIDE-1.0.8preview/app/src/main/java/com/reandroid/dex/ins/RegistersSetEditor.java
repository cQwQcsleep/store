package com.reandroid.dex.ins;

import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.utils.collection.ArraySupplierIterator;
import com.reandroid.utils.collection.EmptyIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegistersSetEditor implements ArraySupplier<RegisterReference.Editor>, Iterable<RegisterReference.Editor> {
    private static final int DEFAULT_CAPACITY = 2;
    public static final RegistersSetEditor EMPTY = new RegistersSetEditor() { // from class: com.reandroid.dex.ins.RegistersSetEditor.1
        @Override // com.reandroid.dex.ins.RegistersSetEditor
        public void add(Iterator<RegisterReference.Editor> it) {
            if (it == null || !it.hasNext()) {
                return;
            }
            w01.a("Empty RegistersList");
        }

        @Override // com.reandroid.dex.ins.RegistersSetEditor
        public boolean contains(Object obj) {
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.reandroid.dex.ins.RegistersSetEditor, com.reandroid.common.ArraySupplier
        public RegisterReference.Editor get(int i) {
            throw new IllegalArgumentException("Empty RegistersList");
        }

        @Override // com.reandroid.dex.ins.RegistersSetEditor, com.reandroid.common.CountSupplier
        public int getCount() {
            return 0;
        }

        @Override // com.reandroid.dex.ins.RegistersSetEditor
        public boolean isEmpty() {
            return true;
        }

        @Override // com.reandroid.dex.ins.RegistersSetEditor, java.lang.Iterable
        public Iterator<RegisterReference.Editor> iterator() {
            return EmptyIterator.of();
        }

        @Override // com.reandroid.dex.ins.RegistersSetEditor
        public void trimToSize() {
        }

        @Override // com.reandroid.dex.ins.RegistersSetEditor
        public void add(RegisterReference.Editor editor) {
            if (editor == null) {
                return;
            }
            w01.a("Empty RegistersList");
        }
    };
    private RegisterReference.Editor[] elements;
    private int size;

    private int availableCapacity() {
        RegisterReference.Editor[] editorArr = this.elements;
        if (editorArr != null) {
            return editorArr.length - this.size;
        }
        return 0;
    }

    private void ensureCapacity(int i) {
        if (availableCapacity() >= i) {
            return;
        }
        int i2 = this.size;
        RegisterReference.Editor[] editorArr = new RegisterReference.Editor[i + i2];
        RegisterReference.Editor[] editorArr2 = this.elements;
        if (editorArr2 == null || i2 == 0) {
            this.elements = editorArr;
        } else {
            System.arraycopy(editorArr2, 0, editorArr, 0, i2);
            this.elements = editorArr;
        }
    }

    public static RegistersSetEditor of(RegistersTable registersTable, RegistersSet registersSet) {
        int registersCount;
        if (registersSet != null && (registersCount = registersSet.getRegistersCount()) != 0) {
            RegistersSetEditor registersSetEditor = new RegistersSetEditor();
            registersSetEditor.ensureCapacity(registersCount);
            for (int i = 0; i < registersCount; i++) {
                registersSetEditor.add(new RegisterReference(registersTable, registersSet, i).toEditor());
            }
            return registersSetEditor;
        }
        return EMPTY;
    }

    public void add(Iterator<RegisterReference.Editor> it) {
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public void apply() {
        Iterator<RegisterReference.Editor> it = iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
    }

    public boolean contains(Object obj) {
        RegisterReference.Editor[] editorArr;
        if (obj == null || (editorArr = this.elements) == null) {
            return false;
        }
        for (RegisterReference.Editor editor : editorArr) {
            if (obj.equals(editor)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.reandroid.common.ArraySupplier
    public RegisterReference.Editor get(int i) {
        RegisterReference.Editor[] editorArr = this.elements;
        if (editorArr != null) {
            return editorArr[i];
        }
        return null;
    }

    @Override // com.reandroid.common.CountSupplier
    public int getCount() {
        return this.size;
    }

    public boolean isEmpty() {
        return getCount() != 0;
    }

    @Override // java.lang.Iterable
    public Iterator<RegisterReference.Editor> iterator() {
        return ArraySupplierIterator.of(this);
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
        RegisterReference.Editor[] editorArr = new RegisterReference.Editor[i];
        System.arraycopy(this.elements, 0, editorArr, 0, i);
        this.elements = editorArr;
    }

    public void add(RegisterReference.Editor editor) {
        if (editor == null) {
            return;
        }
        ensureCapacity();
        RegisterReference.Editor[] editorArr = this.elements;
        int i = this.size;
        editorArr[i] = editor;
        this.size = i + 1;
    }

    private void ensureCapacity() {
        ensureCapacity(this.size == 0 ? 1 : 2);
    }
}
