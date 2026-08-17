package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.OperandType;
import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegisterFormat;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliInstruction extends SmaliCode {
    private int address;
    private Opcode<?> opcode;
    private SmaliInstructionOperand operand;
    private SmaliRegisterSet registerSet;

    public SmaliInstruction(Opcode<?> opcode) {
        opcode.getClass();
        this.opcode = opcode;
        initRegisterSet(opcode);
        try {
            initOperand(opcode);
        } catch (IOException e) {
            rc6.a(e);
            throw null;
        }
    }

    private void initOperand(Opcode<?> opcode) throws IOException {
        SmaliInstructionOperand smaliDecimalOperand;
        OperandType operandType = opcode.getOperandType();
        if (operandType == OperandType.NONE) {
            smaliDecimalOperand = SmaliInstructionOperand.NO_OPERAND;
        } else if (operandType == OperandType.HEX) {
            smaliDecimalOperand = new SmaliInstructionOperand.SmaliHexOperand();
        } else if (operandType == OperandType.KEY) {
            smaliDecimalOperand = new SmaliInstructionOperand.SmaliKeyOperand();
        } else if (operandType == OperandType.DUAL_KEY) {
            smaliDecimalOperand = new SmaliInstructionOperand.SmaliDualKeyOperand();
        } else if (operandType == OperandType.LABEL) {
            smaliDecimalOperand = new SmaliInstructionOperand.SmaliLabelOperand();
        } else {
            if (operandType != OperandType.DECIMAL) {
                a28.a("Unknown operand type: ", operandType, ", opcode = ", opcode);
                return;
            }
            smaliDecimalOperand = new SmaliInstructionOperand.SmaliDecimalOperand();
        }
        setOperand(smaliDecimalOperand);
    }

    private void initRegisterSet(Opcode<?> opcode) {
        RegisterFormat registerFormat = opcode.getRegisterFormat();
        setRegisterSet(registerFormat == RegisterFormat.NONE ? SmaliRegisterSet.NO_REGISTER_SET : new SmaliRegisterSet(registerFormat));
    }

    private Opcode<?> parseOpcode(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        Opcode<?> smali = Opcode.parseSmali(smaliReader, true);
        initializeOpcode(smali);
        smaliReader.skipSpaces();
        return smali;
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        Opcode<?> opcode = getOpcode();
        if (opcode == null) {
            return;
        }
        smaliWriter.newLine();
        opcode.append(smaliWriter);
        SmaliRegisterSet registerSet = getRegisterSet();
        if (registerSet != null) {
            registerSet.append(smaliWriter);
        }
        if (opcode.getRegisterFormat() != RegisterFormat.NONE && opcode.getOperandType() != OperandType.NONE) {
            smaliWriter.append(", ");
        }
        getOperand().append(smaliWriter);
    }

    public int getAddress() {
        return this.address;
    }

    public int getCodeUnits() {
        return getOpcode().size() / 2;
    }

    public long getDataAsLong() {
        SmaliInstructionOperand operand = getOperand();
        if (operand instanceof SmaliInstructionOperand.SmaliHexOperand) {
            return operand.getValueAsLong();
        }
        if (operand instanceof SmaliInstructionOperand.SmaliLabelOperand) {
            return ((int) operand.getValueAsLong()) - getAddress();
        }
        return 0L;
    }

    public Key getKey() {
        SmaliInstructionOperand operand = getOperand();
        if (operand instanceof SmaliInstructionOperand.SmaliKeyOperand) {
            return ((SmaliInstructionOperand.SmaliKeyOperand) operand).getKey();
        }
        return null;
    }

    public Key getKey2() {
        SmaliInstructionOperand operand = getOperand();
        if (operand instanceof SmaliInstructionOperand.SmaliDualKeyOperand) {
            return ((SmaliInstructionOperand.SmaliDualKeyOperand) operand).getKey2();
        }
        return null;
    }

    public Opcode<?> getOpcode() {
        return this.opcode;
    }

    public SmaliInstructionOperand getOperand() {
        return this.operand;
    }

    public OperandType getOperandType() {
        return getOperand().getOperandType();
    }

    public Register getRegister(int i) {
        return getRegisterSet().getRegister(i);
    }

    public RegisterFormat getRegisterFormat() {
        return getOpcode().getRegisterFormat();
    }

    public SmaliRegisterSet getRegisterSet() {
        return this.registerSet;
    }

    public int getRegistersCount() {
        return getRegisterSet().size();
    }

    public RegistersTable getRegistersTable() {
        SmaliRegisterSet registerSet = getRegisterSet();
        if (registerSet != null) {
            return registerSet.getRegistersTable();
        }
        return null;
    }

    public boolean hasLabelOperand(SmaliLabel smaliLabel) {
        SmaliInstructionOperand operand = getOperand();
        if (operand instanceof SmaliInstructionOperand.SmaliLabelOperand) {
            return smaliLabel.equals(((SmaliInstructionOperand.SmaliLabelOperand) operand).getLabel());
        }
        return false;
    }

    public boolean hasNumberData() {
        SmaliInstructionOperand operand = getOperand();
        return (operand instanceof SmaliInstructionOperand.SmaliHexOperand) || (operand instanceof SmaliInstructionOperand.SmaliLabelOperand);
    }

    public void initializeOpcode(Opcode<?> opcode) throws IOException {
        this.opcode = opcode;
        initRegisterSet(opcode);
        initOperand(opcode);
    }

    @Override // com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        setOrigin(smaliReader.getCurrentOrigin());
        Opcode<?> opcode = parseOpcode(smaliReader);
        getRegisterSet().parse(smaliReader);
        if (opcode.getRegisterFormat() != RegisterFormat.NONE && opcode.getOperandType() != OperandType.NONE) {
            smaliReader.skipWhitespacesOrComment();
            SmaliParseException.expect(smaliReader, ',');
            smaliReader.skipWhitespacesOrComment();
        }
        getOperand().parse(opcode, smaliReader);
    }

    public void replaceOpcode(Opcode<?> opcode) {
        this.opcode = opcode;
    }

    public void setAddress(int i) {
        this.address = i;
    }

    public void setOperand(SmaliInstructionOperand smaliInstructionOperand) {
        this.operand = smaliInstructionOperand;
        if (smaliInstructionOperand != null) {
            smaliInstructionOperand.setParent(this);
        }
    }

    public void setRegisterSet(SmaliRegisterSet smaliRegisterSet) {
        this.registerSet = smaliRegisterSet;
        if (smaliRegisterSet != null) {
            smaliRegisterSet.setParent(this);
        }
    }

    public void setRegistersTable(RegistersTable registersTable) {
        SmaliRegisterSet registerSet = getRegisterSet();
        if (registerSet != null) {
            registerSet.setRegistersTable(registersTable);
        }
    }

    public Register getRegister() {
        return getRegister(0);
    }

    public SmaliInstruction() {
        this.opcode = Opcode.NOP;
        this.registerSet = SmaliRegisterSet.NO_REGISTER_SET;
        this.operand = SmaliInstructionOperand.NO_OPERAND;
    }
}
