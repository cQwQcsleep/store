package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.xpath.internal.XPath;
import defpackage.iva;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class PUSH implements CompoundInstruction, VariableLengthInstruction {
    private final Instruction instruction;

    public PUSH(ConstantPoolGen constantPoolGen, Number number) {
        if ((number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            this.instruction = new PUSH(constantPoolGen, number.intValue()).instruction;
            return;
        }
        if (number instanceof Double) {
            this.instruction = new PUSH(constantPoolGen, number.doubleValue()).instruction;
            return;
        }
        if (number instanceof Float) {
            this.instruction = new PUSH(constantPoolGen, number.floatValue()).instruction;
        } else if (number instanceof Long) {
            this.instruction = new PUSH(constantPoolGen, number.longValue()).instruction;
        } else {
            iva.a("What's this: ", number);
            throw null;
        }
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CompoundInstruction
    public InstructionList getInstructionList() {
        return new InstructionList(this.instruction);
    }

    public String toString() {
        return this.instruction + " (PUSH)";
    }

    public PUSH(ConstantPoolGen constantPoolGen, boolean z) {
        Objects.requireNonNull(constantPoolGen, "cp");
        this.instruction = InstructionConst.getInstruction((z ? 1 : 0) + 3);
    }

    public PUSH(ConstantPoolGen constantPoolGen, Boolean bool) {
        this(constantPoolGen, bool.booleanValue());
    }

    public PUSH(ConstantPoolGen constantPoolGen, Character ch) {
        this(constantPoolGen, (int) ch.charValue());
    }

    public PUSH(ConstantPoolGen constantPoolGen, double d) {
        if (d == XPath.MATCH_SCORE_QNAME) {
            this.instruction = InstructionConst.DCONST_0;
        } else if (d == 1.0d) {
            this.instruction = InstructionConst.DCONST_1;
        } else {
            this.instruction = new LDC2_W(constantPoolGen.addDouble(d));
        }
    }

    public PUSH(ConstantPoolGen constantPoolGen, float f) {
        double d = f;
        if (d == XPath.MATCH_SCORE_QNAME) {
            this.instruction = InstructionConst.FCONST_0;
            return;
        }
        if (d == 1.0d) {
            this.instruction = InstructionConst.FCONST_1;
        } else if (d == 2.0d) {
            this.instruction = InstructionConst.FCONST_2;
        } else {
            this.instruction = new LDC(constantPoolGen.addFloat(f));
        }
    }

    public PUSH(ConstantPoolGen constantPoolGen, int i) {
        if (i >= -1 && i <= 5) {
            this.instruction = InstructionConst.getInstruction(i + 3);
            return;
        }
        if (Instruction.isValidByte(i)) {
            this.instruction = new BIPUSH((byte) i);
        } else if (Instruction.isValidShort(i)) {
            this.instruction = new SIPUSH((short) i);
        } else {
            this.instruction = new LDC(constantPoolGen.addInteger(i));
        }
    }

    public PUSH(ConstantPoolGen constantPoolGen, long j) {
        if (j == 0) {
            this.instruction = InstructionConst.LCONST_0;
        } else if (j == 1) {
            this.instruction = InstructionConst.LCONST_1;
        } else {
            this.instruction = new LDC2_W(constantPoolGen.addLong(j));
        }
    }

    public PUSH(ConstantPoolGen constantPoolGen, ArrayType arrayType) {
        if (arrayType == null) {
            this.instruction = InstructionConst.ACONST_NULL;
        } else {
            this.instruction = new LDC(constantPoolGen.addArrayClass(arrayType));
        }
    }

    public PUSH(ConstantPoolGen constantPoolGen, ObjectType objectType) {
        if (objectType == null) {
            this.instruction = InstructionConst.ACONST_NULL;
        } else {
            this.instruction = new LDC(constantPoolGen.addClass(objectType));
        }
    }

    public PUSH(ConstantPoolGen constantPoolGen, String str) {
        if (str == null) {
            this.instruction = InstructionConst.ACONST_NULL;
        } else {
            this.instruction = new LDC(constantPoolGen.addString(str));
        }
    }
}
