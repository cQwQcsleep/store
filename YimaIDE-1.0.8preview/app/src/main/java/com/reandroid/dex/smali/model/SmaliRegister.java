package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliRegister extends Smali {
    private int number;
    private boolean parameter;

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        if (isParameter()) {
            smaliWriter.append('p');
        } else {
            smaliWriter.append('v');
        }
        smaliWriter.appendInteger(getNumber());
    }

    public int getNumber() {
        return this.number;
    }

    public boolean isParameter() {
        return this.parameter;
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        setParameter(SmaliParseException.expect(smaliReader, 'v', 'p') == 'p');
        setNumber(smaliReader.readInteger());
    }

    public void setNumber(int i) {
        this.number = i;
    }

    public void setParameter(boolean z) {
        this.parameter = z;
    }

    public Register toRegister(RegistersTable registersTable) {
        return new Register(getNumber(), isParameter(), registersTable);
    }

    public Register toRegister() {
        return toRegister(null);
    }
}
