package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.utils.HexUtil;
import com.sun.org.apache.xml.internal.serializer.CharInfo;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueChar extends SmaliValue {
    private char value;

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        DexUtils.appendSingleQuotedChar(smaliWriter, getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public Key getKey() {
        return PrimitiveKey.of(getValue());
    }

    public char getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.CHAR;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        if (smaliReader.read() != 39) {
            smaliReader.skip(-1);
            l78.a("Missing start \"'\"", smaliReader);
            return;
        }
        char ascii = smaliReader.readASCII();
        if (ascii == '\\') {
            ascii = smaliReader.readASCII();
            if (ascii == 'u') {
                try {
                    ascii = (char) HexUtil.parseHex(smaliReader.readString(4));
                } catch (NumberFormatException unused) {
                    smaliReader.skip(-4);
                    l78.a("Invalid four-char hex encoded char", smaliReader);
                    return;
                }
            } else if (ascii == 'b') {
                ascii = '\b';
            } else if (ascii == 'f') {
                ascii = '\f';
            } else if (ascii == 'n') {
                ascii = '\n';
            } else if (ascii == 'r') {
                ascii = CharInfo.S_CARRIAGERETURN;
            } else if (ascii == 't') {
                ascii = '\t';
            }
        }
        setValue(ascii);
        if (smaliReader.read() == 39) {
            return;
        }
        smaliReader.skip(-2);
        l78.a("Missing end \"'\"", smaliReader);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setValue(((PrimitiveKey.CharKey) key).value());
    }

    public void setValue(char c) {
        this.value = c;
    }
}
