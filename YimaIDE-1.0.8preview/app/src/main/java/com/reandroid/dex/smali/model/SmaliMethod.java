package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.HiddenApiFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.MethodProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.SmaliWriterSetting;
import com.reandroid.dex.smali.fix.SmaliGotoFix;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliMethod extends SmaliDef implements MethodProgram, RegistersTable {
    private final SmaliCodeSet codeSet;
    private final SmaliParamSet paramSet;
    private ProtoKey protoKey;
    private final SmaliRegistersCount smaliRegistersCount;

    public static class SmaliRegistersCount extends Smali implements SmaliRegion {
        private SmaliDirective directive = SmaliDirective.LOCALS;
        private int value;

        private void setDirective(SmaliDirective smaliDirective) {
            this.directive = smaliDirective;
        }

        @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            SmaliWriterSetting writerSetting = smaliWriter.getWriterSetting();
            if (writerSetting != null) {
                setLocalsMode(writerSetting.isLocalRegistersCount());
            }
            getSmaliDirective().append(smaliWriter);
            smaliWriter.appendInteger(getValue());
        }

        public int getLocals() {
            int value = getValue();
            return !isLocalsMode() ? value - ((SmaliMethod) getParent(SmaliMethod.class)).getParameterRegistersCount() : value;
        }

        public int getRegisters() {
            int value = getValue();
            return isLocalsMode() ? value + ((SmaliMethod) getParent(SmaliMethod.class)).getParameterRegistersCount() : value;
        }

        @Override // com.reandroid.dex.smali.SmaliRegion
        public SmaliDirective getSmaliDirective() {
            return this.directive;
        }

        public int getValue() {
            return this.value;
        }

        public boolean isLocalsMode() {
            return getSmaliDirective() == SmaliDirective.LOCALS;
        }

        @Override // com.reandroid.dex.smali.SmaliParser
        public void parse(SmaliReader smaliReader) throws IOException {
            SmaliDirective smaliDirective;
            SmaliDirective smaliDirective2 = SmaliDirective.parse(smaliReader);
            SmaliDirective smaliDirective3 = SmaliDirective.LOCALS;
            if (smaliDirective2 == smaliDirective3 || smaliDirective2 == (smaliDirective = SmaliDirective.REGISTERS)) {
                setDirective(smaliDirective2);
                smaliReader.skipSpaces();
                setValue(smaliReader.readInteger());
            } else {
                throw new SmaliParseException("expecting '" + smaliDirective3 + "', or '" + smaliDirective + "'", smaliReader);
            }
        }

        public void setLocalsMode(boolean z) {
            int registers;
            SmaliDirective smaliDirective;
            if (z == isLocalsMode()) {
                return;
            }
            if (z) {
                registers = getLocals();
                smaliDirective = SmaliDirective.LOCALS;
            } else {
                registers = getRegisters();
                smaliDirective = SmaliDirective.REGISTERS;
            }
            setDirective(smaliDirective);
            setValue(registers);
        }

        public void setValue(int i) {
            this.value = i;
        }
    }

    public SmaliMethod() {
        SmaliParamSet smaliParamSet = new SmaliParamSet();
        this.paramSet = smaliParamSet;
        SmaliRegistersCount smaliRegistersCount = new SmaliRegistersCount();
        this.smaliRegistersCount = smaliRegistersCount;
        SmaliCodeSet smaliCodeSet = new SmaliCodeSet();
        this.codeSet = smaliCodeSet;
        smaliParamSet.setParent(this);
        smaliRegistersCount.setParent(this);
        smaliCodeSet.setParent(this);
    }

    private boolean parseNoneCode(SmaliReader smaliReader) throws IOException {
        SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader, false);
        if (smaliDirective == SmaliDirective.LOCALS || smaliDirective == SmaliDirective.REGISTERS) {
            getSmaliRegistersCount().parse(smaliReader);
            return true;
        }
        if (smaliDirective == SmaliDirective.ANNOTATION) {
            getOrCreateSmaliAnnotationSet().parse(smaliReader);
            return true;
        }
        if (smaliDirective != SmaliDirective.PARAM) {
            return false;
        }
        getParamSet().parse(smaliReader);
        return true;
    }

    private void parseProto(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        setProtoKey(ProtoKey.read(smaliReader));
    }

    private void runFixes() {
        new SmaliGotoFix(this).apply();
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        Modifier.append(smaliWriter, getModifiers());
        smaliWriter.append((CharSequence) getName());
        getProtoKey().append(smaliWriter);
        smaliWriter.indentPlus();
        if (hasInstructions()) {
            smaliWriter.newLine();
            getSmaliRegistersCount().append(smaliWriter);
        }
        getParamSet().append(smaliWriter);
        if (hasAnnotation()) {
            smaliWriter.newLine();
            getAnnotationSet().append(smaliWriter);
        }
        getCodeSet().append(smaliWriter);
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public boolean ensureLocalRegistersCount(int i) {
        return true;
    }

    public SmaliCodeSet getCodeSet() {
        return this.codeSet;
    }

    public Iterator<SmaliDebugElement> getDebugElements() {
        return getCodeSet().getDebugElements();
    }

    public Iterator<SmaliInstruction> getInstructions() {
        return getCodeSet().getInstructions();
    }

    @Override // com.reandroid.dex.smali.model.SmaliDef, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public MethodKey getKey() {
        TypeKey defining = getDefining();
        if (defining != null) {
            return getKey(defining);
        }
        return null;
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public int getLocalRegistersCount() {
        return getSmaliRegistersCount().getLocals();
    }

    public SmaliParamSet getParamSet() {
        return this.paramSet;
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public int getParameterRegistersCount() {
        int i = !isStatic() ? 1 : 0;
        ProtoKey protoKey = getProtoKey();
        return protoKey != null ? i + protoKey.getParameterRegistersCount() : i;
    }

    public Iterator<SmaliMethodParameter> getParameters() {
        return getParamSet().iterator();
    }

    public ProtoKey getProtoKey() {
        return this.protoKey;
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public int getRegistersCount() {
        return getSmaliRegistersCount().getRegisters();
    }

    @Override // com.reandroid.dex.smali.model.SmaliDef, com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.METHOD;
    }

    public SmaliRegistersCount getSmaliRegistersCount() {
        return this.smaliRegistersCount;
    }

    public Iterator<SmaliCodeTryItem> getTryItems() {
        return getCodeSet().getTryItems();
    }

    public boolean hasDebugElements() {
        return getDebugElements().hasNext();
    }

    public boolean hasInstructions() {
        return getInstructions().hasNext();
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, getSmaliDirective());
        setAccessFlags(AccessFlag.parse(smaliReader));
        setHiddenApiFlags(HiddenApiFlag.parse(smaliReader));
        setName(StringKey.readSimpleName(smaliReader, '('));
        parseProto(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        while (parseNoneCode(smaliReader)) {
            smaliReader.skipWhitespacesOrComment();
        }
        getCodeSet().parse(smaliReader);
        SmaliParseException.expect(smaliReader, getSmaliDirective(), true);
        runFixes();
    }

    public void setKey(Key key) {
        MethodKey methodKey = (MethodKey) key;
        setName(methodKey.getNameKey());
        setProtoKey(methodKey.getProto());
        setDefining(methodKey.getDeclaring());
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public void setParameterRegistersCount(int i) {
    }

    public void setProtoKey(ProtoKey protoKey) {
        this.protoKey = protoKey;
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public void setRegistersCount(int i) {
    }

    @Override // com.reandroid.dex.smali.model.Smali
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        TypeKey defining = getDefining();
        if (defining != null) {
            sb.append(defining);
            sb.append(", ");
        }
        sb.append("method = ");
        sb.append(getName());
        sb.append(getProtoKey());
        return sb.toString();
    }

    public MethodKey getKey(TypeKey typeKey) {
        return MethodKey.create(typeKey, getName(), getProtoKey());
    }
}
