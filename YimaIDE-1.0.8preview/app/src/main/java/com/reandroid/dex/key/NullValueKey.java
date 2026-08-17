package com.reandroid.dex.key;

import com.intellij.psi.PsiKeyword;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NullValueKey implements Key {
    public static NullValueKey INSTANCE = new NullValueKey();

    private NullValueKey() {
    }

    public static NullValueKey read(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, 'n');
        SmaliParseException.expect(smaliReader, 'u');
        SmaliParseException.expect(smaliReader, 'l');
        SmaliParseException.expect(smaliReader, 'l');
        return INSTANCE;
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(PsiKeyword.NULL);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        return obj == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return PsiKeyword.NULL;
    }
}
