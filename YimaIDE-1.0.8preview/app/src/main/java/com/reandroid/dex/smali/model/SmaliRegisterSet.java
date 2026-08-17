package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegisterFormat;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliRegisterSet extends SmaliSet<SmaliRegister> implements Iterable<SmaliRegister> {
    public static final SmaliRegisterSet NO_REGISTER_SET = new SmaliRegisterSet(RegisterFormat.NONE) { // from class: com.reandroid.dex.smali.model.SmaliRegisterSet.1
        @Override // com.reandroid.dex.smali.model.SmaliSet
        public boolean add(SmaliRegister smaliRegister) {
            throw new RuntimeException("NO_REGISTER_SET");
        }

        @Override // com.reandroid.dex.smali.model.SmaliRegisterSet, com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) {
        }

        @Override // com.reandroid.dex.smali.model.SmaliSet, java.lang.Iterable
        public Iterator<SmaliRegister> iterator() {
            return EmptyIterator.of();
        }

        @Override // com.reandroid.dex.smali.model.SmaliRegisterSet, com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.SmaliParser
        public void parse(SmaliReader smaliReader) {
        }

        @Override // com.reandroid.dex.smali.model.SmaliRegisterSet
        public void setRegistersTable(RegistersTable registersTable) {
        }

        @Override // com.reandroid.dex.smali.model.SmaliSet
        public int size() {
            return 0;
        }

        @Override // com.reandroid.dex.smali.model.Smali
        public String toString() {
            return "";
        }

        @Override // com.reandroid.dex.smali.model.SmaliRegisterSet, com.reandroid.dex.smali.model.SmaliSet
        public SmaliRegister parseNext(SmaliReader smaliReader) {
            return null;
        }
    };
    private final RegisterFormat format;
    private RegistersTable registersTable;

    public SmaliRegisterSet(RegisterFormat registerFormat) {
        this.format = registerFormat;
    }

    private void parseOutRangeRegisters(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, '{');
        smaliReader.skipWhitespacesOrComment();
        parseNext(smaliReader);
        SmaliParseException.expect(smaliReader, '.');
        SmaliParseException.expect(smaliReader, '.');
        smaliReader.skipWhitespacesOrComment();
        parseNext(smaliReader);
        SmaliParseException.expect(smaliReader, '}');
    }

    private void parseOutRegisters(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, '{');
        smaliReader.skipWhitespacesOrComment();
        boolean z = false;
        while (!smaliReader.finished() && smaliReader.get() != 125) {
            if (z) {
                SmaliParseException.expect(smaliReader, ',');
                smaliReader.skipWhitespacesOrComment();
            }
            parseNext(smaliReader);
            z = true;
        }
        SmaliParseException.expect(smaliReader, '}');
    }

    private void parseRegisters(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        int size = getFormat().size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                SmaliParseException.expect(smaliReader, ',');
                smaliReader.skipWhitespacesOrComment();
            }
            parseNext(smaliReader);
        }
    }

    public void addRegister(boolean z, int i) {
        SmaliRegister smaliRegister = new SmaliRegister();
        add(smaliRegister);
        smaliRegister.setParameter(z);
        smaliRegister.setNumber(i);
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        RegisterFormat format = getFormat();
        String str = format.isRange() ? " .. " : ", ";
        if (format.isOut()) {
            smaliWriter.append('{');
        }
        boolean z = false;
        for (SmaliRegister smaliRegister : this) {
            if (z) {
                smaliWriter.append((CharSequence) str);
            }
            smaliRegister.append(smaliWriter);
            z = true;
        }
        if (format.isOut()) {
            smaliWriter.append('}');
        }
    }

    public RegisterFormat getFormat() {
        return this.format;
    }

    public Register getRegister(int i) {
        SmaliRegister smaliRegister = get(i);
        if (smaliRegister != null) {
            return smaliRegister.toRegister(getRegistersTable());
        }
        return null;
    }

    public RegistersTable getRegistersTable() {
        RegistersTable registersTable = this.registersTable;
        return registersTable == null ? (RegistersTable) getParentInstance(SmaliMethod.class) : registersTable;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        RegisterFormat format = getFormat();
        if (format.isRange()) {
            parseOutRangeRegisters(smaliReader);
        } else if (format.isOut()) {
            parseOutRegisters(smaliReader);
        } else {
            parseRegisters(smaliReader);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet
    public SmaliRegister parseNext(SmaliReader smaliReader) throws IOException {
        SmaliRegister smaliRegister = new SmaliRegister();
        add(smaliRegister);
        smaliRegister.parse(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        return smaliRegister;
    }

    public void setRegistersTable(RegistersTable registersTable) {
        this.registersTable = registersTable;
    }

    public SmaliRegisterSet() {
        this(RegisterFormat.READ);
    }
}
