package com.reandroid.dex.key;

import com.reandroid.dex.common.MethodHandleType;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodHandleKey implements Key {
    private final MethodHandleType handleType;
    private final Key member;

    public MethodHandleKey(MethodHandleType methodHandleType, Key key) {
        this.handleType = methodHandleType;
        this.member = key;
    }

    public static MethodHandleKey read(MethodHandleType methodHandleType, SmaliReader smaliReader) throws IOException {
        return new MethodHandleKey(methodHandleType, methodHandleType.isField() ? FieldKey.read(smaliReader) : MethodKey.read(smaliReader));
    }

    public void append(SmaliWriter smaliWriter, boolean z) throws IOException {
        if (z) {
            getHandleType().append(smaliWriter);
        }
        smaliWriter.append('@');
        getMember().append(smaliWriter);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (!(obj instanceof MethodHandleKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        MethodHandleKey methodHandleKey = (MethodHandleKey) obj;
        int iCompare = CompareUtil.compare(getHandleType(), methodHandleKey.getHandleType());
        return iCompare != 0 ? iCompare : CompareUtil.compare(getMember(), methodHandleKey.getMember());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodHandleKey)) {
            return false;
        }
        MethodHandleKey methodHandleKey = (MethodHandleKey) obj;
        return ObjectsUtil.equals(getHandleType(), methodHandleKey.getHandleType()) && ObjectsUtil.equals(getMember(), methodHandleKey.getMember());
    }

    @Override // com.reandroid.dex.key.Key
    public TypeKey getDeclaring() {
        return getMember().getDeclaring();
    }

    public MethodHandleType getHandleType() {
        return this.handleType;
    }

    public Key getMember() {
        return this.member;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getHandleType(), Integer.valueOf(getMember().hashCode()));
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<? extends Key> mentionedKeys() {
        return getMember().mentionedKeys();
    }

    @Override // com.reandroid.dex.key.Key
    public Key replaceKey(Key key, Key key2) {
        if (key.equals(this)) {
            return key2;
        }
        Key keyReplaceKey = getMember().replaceKey(key, key2);
        return keyReplaceKey != getMember() ? new MethodHandleKey(getHandleType(), keyReplaceKey) : this;
    }

    public String toString() {
        return getHandleType() + "@" + getMember();
    }

    public static MethodHandleKey read(SmaliReader smaliReader) throws IOException {
        MethodHandleType methodHandleType = MethodHandleType.read(smaliReader);
        if (methodHandleType == null) {
            return null;
        }
        SmaliParseException.expect(smaliReader, '@');
        return read(methodHandleType, smaliReader);
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(smaliWriter, true);
    }
}
