package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.OperandType;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.CallSiteKey;
import com.reandroid.dex.key.DualKeyReference;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliInstructionOperand extends Smali {
    public static final SmaliInstructionOperand NO_OPERAND = new SmaliInstructionOperand() { // from class: com.reandroid.dex.smali.model.SmaliInstructionOperand.1
        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) {
        }

        public boolean equals(Object obj) {
            return obj == this;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public long getValueAsLong() {
            return -1L;
        }

        public int hashCode() {
            return super.hashCode();
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public void parse(Opcode<?> opcode, SmaliReader smaliReader) {
            smaliReader.skipSpaces();
        }

        @Override // com.reandroid.dex.smali.model.Smali
        public void setParent(Smali smali) {
        }
    };

    public static class SmaliDecimalOperand extends SmaliInstructionOperand {
        private int number;

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendInteger(getNumber());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && getNumber() == ((SmaliDecimalOperand) obj).getNumber();
        }

        public int getNumber() {
            return this.number;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public OperandType getOperandType() {
            return OperandType.DECIMAL;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public long getValueAsLong() {
            return this.number;
        }

        public int hashCode() {
            return getNumber() + 31;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public void parse(Opcode<?> opcode, SmaliReader smaliReader) throws IOException {
            smaliReader.skipSpaces();
            setNumber(smaliReader.readInteger());
        }

        public void setNumber(int i) {
            this.number = i;
        }
    }

    public static class SmaliDualKeyOperand extends SmaliKeyOperand implements DualKeyReference {
        private Key key2;

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand.SmaliKeyOperand, com.reandroid.dex.smali.model.SmaliInstructionOperand, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            super.append(smaliWriter);
            smaliWriter.append(", ");
            Key key2 = getKey2();
            if (key2 != null) {
                key2.append(smaliWriter);
            }
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand.SmaliKeyOperand
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                SmaliDualKeyOperand smaliDualKeyOperand = (SmaliDualKeyOperand) obj;
                if (ObjectsUtil.equals(getKey(), smaliDualKeyOperand.getKey()) && ObjectsUtil.equals(getKey2(), smaliDualKeyOperand.getKey2())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.reandroid.dex.key.DualKeyReference
        public Key getKey2() {
            return this.key2;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand.SmaliKeyOperand, com.reandroid.dex.smali.model.SmaliInstructionOperand
        public OperandType getOperandType() {
            return OperandType.DUAL_KEY;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand.SmaliKeyOperand
        public int hashCode() {
            return ObjectsUtil.hash(getKey(), getKey2());
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand.SmaliKeyOperand, com.reandroid.dex.smali.model.SmaliInstructionOperand
        public void parse(Opcode<?> opcode, SmaliReader smaliReader) throws IOException {
            super.parse(opcode, smaliReader);
            smaliReader.skipWhitespaces();
            SmaliParseException.expect(smaliReader, ',');
            smaliReader.skipWhitespaces();
            setKey2(parseKey(opcode.getSectionType2(), smaliReader));
        }

        @Override // com.reandroid.dex.key.DualKeyReference
        public void setKey2(Key key) {
            this.key2 = key;
        }
    }

    public static class SmaliHexOperand extends SmaliInstructionOperand {
        private SmaliValueNumber<?> valueNumber = new SmaliValueInteger();

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendOptional(getValueNumber());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return ObjectsUtil.equals(getValueNumber(), ((SmaliHexOperand) obj).getValueNumber());
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public long getValueAsLong() {
            return getValueNumber().getValueAsLong();
        }

        public SmaliValueNumber<?> getValueNumber() {
            return this.valueNumber;
        }

        public int hashCode() {
            return ObjectsUtil.hash(getValueNumber());
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public void parse(Opcode<?> opcode, SmaliReader smaliReader) throws IOException {
            smaliReader.skipSpaces();
            SmaliValueNumber<?> smaliValueNumberCreateNumber = SmaliValueNumber.createNumber(smaliReader);
            setNumberValue(smaliValueNumberCreateNumber);
            smaliValueNumberCreateNumber.parse(smaliReader);
        }

        public void setNumber(Number number) {
            setNumberValue(SmaliValueNumber.createFor(number));
        }

        public void setNumberValue(SmaliValueNumber<?> smaliValueNumber) {
            this.valueNumber = smaliValueNumber;
            if (smaliValueNumber != null) {
                smaliValueNumber.setParent(this);
            }
        }
    }

    public static class SmaliKeyOperand extends SmaliInstructionOperand implements KeyReference {
        private Key key;

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            Key key = getKey();
            if (key != null) {
                key.append(smaliWriter);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return ObjectsUtil.equals(getKey(), ((SmaliKeyOperand) obj).getKey());
        }

        @Override // com.reandroid.dex.key.KeyItem
        public Key getKey() {
            return this.key;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public long getValueAsLong() {
            return -1L;
        }

        public int hashCode() {
            return ObjectsUtil.hash(getKey());
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public void parse(Opcode<?> opcode, SmaliReader smaliReader) throws IOException {
            setKey(parseKey(opcode.getSectionType(), smaliReader));
        }

        public Key parseKey(SectionType<?> sectionType, SmaliReader smaliReader) throws IOException {
            if (sectionType == SectionType.STRING_ID) {
                return StringKey.read(smaliReader);
            }
            if (sectionType == SectionType.TYPE_ID) {
                return TypeKey.read(smaliReader);
            }
            if (sectionType == SectionType.FIELD_ID) {
                return FieldKey.read(smaliReader);
            }
            if (sectionType == SectionType.PROTO_ID) {
                return ProtoKey.read(smaliReader);
            }
            if (sectionType == SectionType.METHOD_ID) {
                return MethodKey.read(smaliReader);
            }
            if (sectionType == SectionType.CALL_SITE_ID) {
                return CallSiteKey.read(smaliReader);
            }
            l78.a("Invalid key", smaliReader);
            return null;
        }

        @Override // com.reandroid.dex.key.KeyReference
        public void setKey(Key key) {
            this.key = key;
        }
    }

    public static class SmaliLabelOperand extends SmaliInstructionOperand {
        private final SmaliLabel label;

        public SmaliLabelOperand() {
            SmaliLabel smaliLabel = new SmaliLabel();
            this.label = smaliLabel;
            smaliLabel.setParent(this);
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            getLabel().append(smaliWriter);
        }

        public SmaliLabel getLabel() {
            return this.label;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public long getValueAsLong() {
            return getLabel().getIntegerData();
        }

        @Override // com.reandroid.dex.smali.model.SmaliInstructionOperand
        public void parse(Opcode<?> opcode, SmaliReader smaliReader) throws IOException {
            getLabel().parse(smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public abstract void append(SmaliWriter smaliWriter) throws IOException;

    public abstract OperandType getOperandType();

    public abstract long getValueAsLong();

    public abstract void parse(Opcode<?> opcode, SmaliReader smaliReader) throws IOException;

    @Override // com.reandroid.dex.smali.SmaliParser
    public final void parse(SmaliReader smaliReader) throws IOException {
        throw new RuntimeException("Must call parse(Opcode, SmaliReader)");
    }
}
