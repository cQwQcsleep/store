package com.sun.jna;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StructureWriteContext extends ToNativeContext {
    private Field field;
    private Structure struct;

    public StructureWriteContext(Structure structure, Field field) {
        this.struct = structure;
        this.field = field;
    }

    public Field getField() {
        return this.field;
    }

    public Structure getStructure() {
        return this.struct;
    }
}
