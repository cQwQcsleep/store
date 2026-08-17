package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.OperandType;
import com.reandroid.dex.common.RegisterFormat;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.data.CodeItem;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.DualKeyReference;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.reference.InsIdSectionReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliInstructionOperand;
import com.reandroid.dex.smali.model.SmaliRegisterSet;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SizeXIns extends Ins {
    private final InsIdSectionReference sectionReference;
    private final ByteArray valueBytes;

    public SizeXIns(Opcode<?> opcode, boolean z) {
        super(opcode);
        ByteArray byteArray = new ByteArray();
        this.valueBytes = byteArray;
        addChild(0, byteArray);
        byteArray.setSize(opcode.size());
        byteArray.putShort(0, opcode.getValue());
        this.sectionReference = z ? new InsIdSectionReference(this) : null;
    }

    private void appendHexDataComment(SmaliWriter smaliWriter) {
        String commentForConstNumber = InsConstCommentHelper.getCommentForConstNumber(this);
        if (commentForConstNumber != null) {
            smaliWriter.appendComment(commentForConstNumber);
        }
    }

    private static boolean areEqual(RegistersSet registersSet, RegistersSet registersSet2) {
        int registersCount;
        if (registersSet == registersSet2) {
            return true;
        }
        if (registersSet == null || (registersCount = registersSet.getRegistersCount()) != registersSet2.getRegistersCount()) {
            return false;
        }
        for (int i = 0; i < registersCount; i++) {
            if (registersSet.getRegister(i) != registersSet2.getRegister(i)) {
                return false;
            }
        }
        return true;
    }

    public static String buildTrace(SizeXIns sizeXIns, IdItem idItem, int i) {
        InstructionList instructionList = sizeXIns.getInstructionList();
        if (instructionList == null) {
            return "removed instruction";
        }
        CodeItem codeItem = instructionList.getCodeItem();
        if (codeItem == null) {
            return "removed instruction list";
        }
        if (codeItem.getParent() == null) {
            return "removed code item";
        }
        MethodDef methodDef = codeItem.getMethodDef();
        StringBuilder sb = new StringBuilder();
        if (methodDef != null) {
            sb.append("method = ");
            sb.append(methodDef.getKey());
            sb.append(", ");
        }
        sb.append(sizeXIns.getOpcode());
        String debugString = toDebugString(idItem);
        if (debugString == null) {
            debugString = HexUtil.toHex(i, 1);
        }
        sb.append(", key = '");
        sb.append(debugString);
        sb.append('\'');
        return sb.toString();
    }

    private void fromSmaliData(SmaliInstruction smaliInstruction) {
        if (getSectionType() == null && smaliInstruction.hasNumberData()) {
            setData(smaliInstruction.getDataAsLong());
        }
    }

    private void fromSmaliKey(SmaliInstruction smaliInstruction) {
        if (getSectionType() == null) {
            return;
        }
        setKey(smaliInstruction.getKey());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fromSmaliRegisters(SmaliInstruction smaliInstruction) {
        if (this instanceof RegistersSet) {
            if (smaliInstruction.getRegistersTable() == null) {
                smaliInstruction.setRegistersTable(getRegistersTable());
            }
            int registersCount = smaliInstruction.getRegistersCount();
            RegistersSet registersSet = (RegistersSet) this;
            registersSet.setRegistersCount(registersCount);
            for (int i = 0; i < registersCount; i++) {
                registersSet.setRegister(i, smaliInstruction.getRegister(i).getValue());
            }
        }
    }

    private RegistersTable getRegistersTable() {
        InstructionList instructionList = (InstructionList) getParentInstance(InstructionList.class);
        if (instructionList != null) {
            return instructionList.getCodeItem();
        }
        return null;
    }

    public static String toDebugString(IdItem idItem) {
        Key key;
        String string;
        if (idItem == null || (key = idItem.getKey()) == null || (string = key.toString()) == null) {
            return null;
        }
        if (string.length() > 100) {
            string = string.substring(0, 100).concat("...");
        }
        if (string.startsWith("\"")) {
            string = string.substring(1);
        }
        return string.endsWith("\"") ? string.substring(0, string.length() - 1) : string;
    }

    @Override // com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getOpcode().getName());
        smaliWriter.append(' ');
        appendRegisters(smaliWriter);
        appendOperand(smaliWriter);
    }

    public void appendHexData(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendHex(getSignedData());
    }

    public void appendOperand(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(", ");
        IdItem sectionId = getSectionId();
        if (sectionId != null) {
            sectionId.append(smaliWriter);
        } else {
            appendHexData(smaliWriter);
            appendHexDataComment(smaliWriter);
        }
    }

    public void appendRegisters(SmaliWriter smaliWriter) throws IOException {
        RegistersIterator registersIterator = getRegistersIterator();
        if (registersIterator == null) {
            return;
        }
        boolean zHasOutRegisters = getOpcode().hasOutRegisters();
        if (zHasOutRegisters) {
            smaliWriter.append('{');
        }
        registersIterator.append(smaliWriter);
        if (zHasOutRegisters) {
            smaliWriter.append('}');
        }
    }

    public int countBytes() {
        return this.valueBytes.countBytes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.Ins
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SizeXIns sizeXIns = (SizeXIns) obj;
            if (getIndex() != sizeXIns.getIndex() || getOpcode() != sizeXIns.getOpcode()) {
                return false;
            }
            if ((this instanceof RegistersSet) && !areEqual((RegistersSet) this, (RegistersSet) sizeXIns)) {
                return false;
            }
            if (getSectionType() != null) {
                return Objects.equals(getKey(), sizeXIns.getKey());
            }
            if (getDataAsLong() == sizeXIns.getDataAsLong()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.ins.Ins
    public void fromSmali(SmaliInstruction smaliInstruction) throws DexException {
        validateOpcode(smaliInstruction);
        fromSmaliRegisters(smaliInstruction);
        fromSmaliKey(smaliInstruction);
        fromSmaliData(smaliInstruction);
    }

    public int getByteSigned() {
        return this.valueBytes.get(1);
    }

    public int getByteUnsigned(int i) {
        return this.valueBytes.getByteUnsigned(i);
    }

    public int getData() {
        return getShortUnsigned(2);
    }

    public long getDataAsLong() {
        return getSignedData();
    }

    public int getInteger() {
        return this.valueBytes.getInteger(2);
    }

    public Key getKey() {
        InsIdSectionReference insIdSectionReference = this.sectionReference;
        if (insIdSectionReference != null) {
            return insIdSectionReference.getKey();
        }
        return null;
    }

    public long getLong() {
        return Block.getLong(this.valueBytes.getBytes(), 2);
    }

    public int getNibble(int i) {
        return (this.valueBytes.getByteUnsigned(i / 2) >> ((i % 2) * 4)) & 15;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.Ins
    public int getOutSize() {
        if (getOpcode().hasOutRegisters()) {
            return ((RegistersSet) this).getRegistersCount();
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RegistersIterator getRegistersIterator() {
        RegistersTable registersTable;
        if (!(this instanceof RegistersSet) || (registersTable = getRegistersTable()) == null) {
            return null;
        }
        return new RegistersIterator(registersTable, (RegistersSet) this);
    }

    public IdItem getSectionId() {
        InsIdSectionReference insIdSectionReference = this.sectionReference;
        if (insIdSectionReference != null) {
            return insIdSectionReference.getItem();
        }
        return null;
    }

    @Deprecated
    public Key getSectionIdKey() {
        return getKey();
    }

    public SectionType<? extends IdItem> getSectionType() {
        return getOpcode().getSectionType();
    }

    public SectionType<? extends IdItem> getSectionType2() {
        return getOpcode().getSectionType2();
    }

    public int getShortSigned() {
        return this.valueBytes.getShort(2);
    }

    public int getShortUnsigned(int i) {
        return this.valueBytes.getShortUnsigned(i);
    }

    public int getSignedData() {
        return getData();
    }

    public final ByteArray getValueBytes() {
        return this.valueBytes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int index = ((getIndex() + 1) * 31) + getOpcode().getValue();
        if (this instanceof RegistersSet) {
            RegistersSet registersSet = (RegistersSet) this;
            int registersCount = registersSet.getRegistersCount();
            index = (index * 31) + registersCount;
            for (int i = 0; i < registersCount; i++) {
                index = (index * 31) + registersSet.getRegister(i);
            }
        }
        int i2 = index * 31;
        Key key = getKey();
        return key != null ? i2 + key.hashCode() : (index * 961) + Long.hashCode(getDataAsLong());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.Ins
    public void merge(Ins ins) {
        SizeXIns sizeXIns = (SizeXIns) ins;
        if (sizeXIns.getSectionType() == null) {
            this.valueBytes.set((byte[]) sizeXIns.valueBytes.getBytes().clone());
            return;
        }
        setKey(sizeXIns.getKey());
        this.sectionReference.validate();
        RegistersSet registersSet = (RegistersSet) sizeXIns;
        RegistersSet registersSet2 = (RegistersSet) this;
        int registersCount = registersSet.getRegistersCount();
        registersSet2.setRegistersCount(registersCount);
        for (int i = 0; i < registersCount; i++) {
            registersSet2.setRegister(i, registersSet.getRegister(i));
        }
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.valueBytes.onReadBytes(blockReader);
        pullSectionItem();
    }

    public void onRefreshed() {
        super.onRefreshed();
        InsIdSectionReference insIdSectionReference = this.sectionReference;
        if (insIdSectionReference != null) {
            insIdSectionReference.refresh();
        }
    }

    public void pullSectionItem() {
        InsIdSectionReference insIdSectionReference = this.sectionReference;
        if (insIdSectionReference != null) {
            insIdSectionReference.pullItem();
        }
    }

    @Override // com.reandroid.dex.ins.Ins
    public void replaceKeys(Key key, Key key2) {
        Key keyReplaceKey;
        Key key3 = getKey();
        if (key3 == null || key3 == (keyReplaceKey = key3.replaceKey(key, key2))) {
            return;
        }
        setKey(keyReplaceKey);
    }

    public void setByte(int i, int i2) {
        if ((i2 & (-256)) == 0 || i2 == ((byte) i2)) {
            this.valueBytes.put(i, (byte) i2);
            return;
        }
        throw new InstructionException("Byte value out of range " + HexUtil.toHex(i2 & 65535, 2) + "> 0xff", this);
    }

    public void setData(long j) {
        setData((int) j);
    }

    public void setInteger(int i) {
        this.valueBytes.putInteger(2, i);
    }

    public void setKey(Key key) {
        this.sectionReference.setKey(key);
    }

    public void setLong(long j) {
        Block.putLong(this.valueBytes.getBytes(), 2, j);
    }

    public void setNibble(int i, int i2) {
        if ((i2 & 15) == i2) {
            int i3 = i / 2;
            int i4 = (i % 2) * 4;
            this.valueBytes.put(i3, (byte) ((i2 << i4) | (this.valueBytes.getByteUnsigned(i3) & (i4 == 0 ? 240 : 15))));
        } else {
            throw new InstructionException("Nibble value out of range " + HexUtil.toHex(i2, 1) + " > 0xf", this);
        }
    }

    public void setSectionId(IdItem idItem) {
        this.sectionReference.setItem(idItem);
    }

    @Deprecated
    public void setSectionIdKey(Key key) {
        setKey(key);
    }

    public void setShort(int i, int i2) {
        if (((-65536) & i2) == 0 || i2 == ((short) i2)) {
            this.valueBytes.putShort(i, i2);
            return;
        }
        throw new InstructionException("Short value out of range " + HexUtil.toHex(i2, 4) + " > 0xffff", this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.Ins
    public void toSmaliOperand(SmaliInstruction smaliInstruction) {
        super.toSmaliOperand(smaliInstruction);
        SmaliInstructionOperand operand = smaliInstruction.getOperand();
        OperandType operandType = smaliInstruction.getOperandType();
        if (operandType == OperandType.HEX) {
            ((SmaliInstructionOperand.SmaliHexOperand) operand).setNumber(Integer.valueOf(getData()));
            return;
        }
        if (operandType == OperandType.KEY) {
            ((SmaliInstructionOperand.SmaliKeyOperand) operand).setKey(getKey());
            return;
        }
        if (operandType == OperandType.LABEL) {
            ((SmaliInstructionOperand.SmaliLabelOperand) operand).getLabel().setLabelName(((Label) this).getLabelName());
        } else if (operandType == OperandType.DUAL_KEY) {
            SmaliInstructionOperand.SmaliDualKeyOperand smaliDualKeyOperand = (SmaliInstructionOperand.SmaliDualKeyOperand) operand;
            smaliDualKeyOperand.setKey(getKey());
            smaliDualKeyOperand.setKey2(((DualKeyReference) this).getKey2());
        }
    }

    @Override // com.reandroid.dex.ins.Ins
    public void toSmaliRegisters(SmaliInstruction smaliInstruction) {
        super.toSmaliRegisters(smaliInstruction);
        SmaliRegisterSet registerSet = smaliInstruction.getRegisterSet();
        RegisterFormat format = registerSet.getFormat();
        if (format == RegisterFormat.NONE) {
            return;
        }
        RegistersIterator registersIterator = getRegistersIterator();
        RegisterReference registerReference = registersIterator.get(0);
        registerSet.addRegister(registerReference.isParameter(), registerReference.getNumber());
        int size = registersIterator.size();
        for (int i = format.isRange() ? size - 1 : 1; i < size; i++) {
            RegisterReference registerReference2 = registersIterator.get(i);
            registerSet.addRegister(registerReference2.isParameter(), registerReference2.getNumber());
        }
    }

    @Override // com.reandroid.dex.ins.Ins
    public Iterator<IdItem> usedIds() {
        return SingleIterator.of(getSectionId());
    }

    public void setData(int i) {
        setShort(2, i);
    }

    public SizeXIns(Opcode<?> opcode) {
        this(opcode, opcode.getSectionType() != null);
    }
}
