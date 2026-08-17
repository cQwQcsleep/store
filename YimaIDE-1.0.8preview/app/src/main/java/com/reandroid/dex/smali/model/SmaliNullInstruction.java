package com.reandroid.dex.smali.model;

import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliNullInstruction extends SmaliInstruction {
    @Override // com.reandroid.dex.smali.model.SmaliInstruction, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstruction
    public int getCodeUnits() {
        return 0;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstruction
    public Opcode<?> getOpcode() {
        return Opcode.NOP;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstruction
    public SmaliInstructionOperand getOperand() {
        return SmaliInstructionOperand.NO_OPERAND;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstruction
    public SmaliRegisterSet getRegisterSet() {
        return SmaliRegisterSet.NO_REGISTER_SET;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstruction, com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
    }
}
