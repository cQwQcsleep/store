package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueAnnotation extends SmaliValue implements SmaliRegion {
    private SmaliAnnotationItem value;

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendOptional(getValue());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public AnnotationItemKey getKey() {
        SmaliAnnotationItem value = getValue();
        if (value != null) {
            return value.getKey();
        }
        return null;
    }

    public SmaliAnnotationItem getOrCreateValue() {
        SmaliAnnotationItem value = getValue();
        if (value != null) {
            return value;
        }
        SmaliAnnotationItem smaliAnnotationItem = new SmaliAnnotationItem();
        setValue(smaliAnnotationItem);
        return smaliAnnotationItem;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.SUB_ANNOTATION;
    }

    public SmaliAnnotationItem getValue() {
        return this.value;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue
    public DexValueType<?> getValueType() {
        return DexValueType.ANNOTATION;
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        SmaliAnnotationItem smaliAnnotationItem = new SmaliAnnotationItem();
        smaliAnnotationItem.setVisibility(null);
        setValue(smaliAnnotationItem);
        smaliAnnotationItem.parse(smaliReader);
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        getOrCreateValue().setKey(key);
    }

    public void setValue(SmaliAnnotationItem smaliAnnotationItem) {
        this.value = smaliAnnotationItem;
        if (smaliAnnotationItem != null) {
            smaliAnnotationItem.setParent(this);
        }
    }
}
