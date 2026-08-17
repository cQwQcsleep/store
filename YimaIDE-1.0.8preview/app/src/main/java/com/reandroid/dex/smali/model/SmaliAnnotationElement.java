package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliAnnotationElement extends Smali implements KeyReference {
    private String name;
    private SmaliValue value;

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        AnnotationElementKey key = getKey();
        if (key == null) {
            smaliWriter.append("# error");
        } else {
            key.append(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.key.KeyItem
    public AnnotationElementKey getKey() {
        return AnnotationElementKey.create(getName(), getValueKey());
    }

    public String getName() {
        return this.name;
    }

    public SmaliValue getValue() {
        return this.value;
    }

    public Key getValueKey() {
        SmaliValue value = getValue();
        if (value != null) {
            return value.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        int iIndexOfWhiteSpace = smaliReader.indexOfWhiteSpace();
        int iIndexOf = smaliReader.indexOf('=');
        if (iIndexOfWhiteSpace >= iIndexOf) {
            iIndexOfWhiteSpace = iIndexOf;
        }
        setName(smaliReader.readString(iIndexOfWhiteSpace - smaliReader.position()));
        smaliReader.skipWhitespaces();
        SmaliParseException.expect(smaliReader, '=');
        smaliReader.skipWhitespaces();
        SmaliValue smaliValueCreate = SmaliValueFactory.create(smaliReader);
        setValue(smaliValueCreate);
        smaliValueCreate.parse(smaliReader);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        AnnotationElementKey annotationElementKey = (AnnotationElementKey) key;
        setName(annotationElementKey.getName());
        setValue(annotationElementKey.getValue());
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setValue(Key key) {
        setValue(SmaliValueFactory.createForValue(key));
    }

    public void setValue(SmaliValue smaliValue) {
        this.value = smaliValue;
        if (smaliValue != null) {
            smaliValue.setParent(this);
        }
    }
}
