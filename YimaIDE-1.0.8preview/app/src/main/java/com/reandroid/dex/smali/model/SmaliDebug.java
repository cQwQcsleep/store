package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.InstanceIterator;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliDebug extends SmaliCode implements SmaliRegion {
    private int searchAddress() {
        SmaliInstruction smaliInstruction;
        SmaliCodeSet codeSet = getCodeSet();
        if (codeSet == null || (smaliInstruction = (SmaliInstruction) CollectionUtil.getFirst(InstanceIterator.of(codeSet.iterator(codeSet.indexOf(this) + 1), SmaliInstruction.class))) == null) {
            return -1;
        }
        return smaliInstruction.getAddress();
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
    }

    public int getAddress() {
        return searchAddress();
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public abstract SmaliDirective getSmaliDirective();

    @Override // com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, getSmaliDirective());
    }
}
