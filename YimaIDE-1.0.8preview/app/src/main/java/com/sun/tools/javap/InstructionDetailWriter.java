package com.sun.tools.javap;

import com.sun.tools.classfile.Instruction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class InstructionDetailWriter extends BasicWriter {

    public enum Kind {
        LOCAL_VARS("localVariables"),
        LOCAL_VAR_TYPES("localVariableTypes"),
        SOURCE("source"),
        STACKMAPS("stackMaps"),
        TRY_BLOCKS("tryBlocks"),
        TYPE_ANNOS("typeAnnotations");

        final String option;

        Kind(String str) {
            this.option = str;
        }
    }

    public InstructionDetailWriter(Context context) {
        super(context);
    }

    public void flush() {
    }

    public abstract void writeDetails(Instruction instruction);
}
