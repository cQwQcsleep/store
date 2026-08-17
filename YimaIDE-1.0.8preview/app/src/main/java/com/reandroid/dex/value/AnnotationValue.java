package com.reandroid.dex.value;

import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.model.SmaliValue;
import com.reandroid.dex.smali.model.SmaliValueAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationValue extends DexValueBlock<AnnotationItem> {
    public AnnotationValue() {
        super(new AnnotationItem(true), DexValueType.ANNOTATION);
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void fromSmali(SmaliValue smaliValue) {
        get().fromSmali(((SmaliValueAnnotation) smaliValue).getValue());
    }

    public AnnotationItem get() {
        return getValueContainer();
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyItem
    public AnnotationItemKey getKey() {
        return get().getKey();
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public DexValueType<?> getValueType() {
        return DexValueType.ANNOTATION;
    }

    @Override // com.reandroid.dex.value.DexValueBlock
    public void merge(DexValueBlock<?> dexValueBlock) {
        super.merge(dexValueBlock);
        AnnotationItem annotationItem = ((AnnotationValue) dexValueBlock).get();
        AnnotationItem annotationItem2 = get();
        annotationItem2.setType(annotationItem.getType());
        annotationItem2.merge(annotationItem);
    }

    @Override // com.reandroid.dex.value.DexValueBlock, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        get().setKey(key);
    }
}
