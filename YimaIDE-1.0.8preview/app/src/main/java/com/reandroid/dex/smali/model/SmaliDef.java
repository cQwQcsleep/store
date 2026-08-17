package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.HiddenApiFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.ProgramKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AccessibleProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.utils.collection.CombiningIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliDef extends Smali implements AccessibleProgram, SmaliRegion {
    private int accessFlagsValue;
    private SmaliAnnotationSet annotation;
    private TypeKey defining;
    private int hiddenApiFlagsValue = HiddenApiFlag.NO_RESTRICTION;
    private StringKey name;

    private SmaliDefSet<?> getDefSet() {
        return (SmaliDefSet) getParentInstance(SmaliDefSet.class);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void addAnnotation(AnnotationItemKey annotationItemKey) {
        getOrCreateSmaliAnnotationSet().addAnnotation(annotationItemKey);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void clearAnnotations() {
        setSmaliAnnotationSet(null);
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public int getAccessFlagsValue() {
        return this.accessFlagsValue;
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationSetKey getAnnotation() {
        SmaliAnnotationSet annotationSet = getAnnotationSet();
        return annotationSet != null ? annotationSet.getKey() : AnnotationSetKey.empty();
    }

    public SmaliAnnotationSet getAnnotationSet() {
        return this.annotation;
    }

    public AnnotationSetKey getAnnotationSetKey() {
        SmaliAnnotationSet annotationSet = getAnnotationSet();
        if (annotationSet != null) {
            return annotationSet.getKey();
        }
        return null;
    }

    public TypeKey getDefining() {
        SmaliDefSet<?> defSet = getDefSet();
        TypeKey defining = defSet != null ? defSet.getDefining() : null;
        return defining == null ? this.defining : defining;
    }

    public Iterator<HiddenApiFlag> getHiddenApiFlags() {
        return HiddenApiFlag.valuesOf(getHiddenApiFlagsValue());
    }

    public int getHiddenApiFlagsValue() {
        return this.hiddenApiFlagsValue;
    }

    public abstract ProgramKey getKey();

    @Override // com.reandroid.dex.program.AccessibleProgram
    public Iterator<? extends Modifier> getModifiers() {
        return CombiningIterator.two(getAccessFlags(), getHiddenApiFlags());
    }

    public String getName() {
        StringKey nameKey = getNameKey();
        if (nameKey != null) {
            return nameKey.getString();
        }
        return null;
    }

    public StringKey getNameKey() {
        return this.name;
    }

    public SmaliAnnotationSet getOrCreateSmaliAnnotationSet() {
        SmaliAnnotationSet annotationSet = getAnnotationSet();
        if (annotationSet != null) {
            return annotationSet;
        }
        SmaliAnnotationSet smaliAnnotationSet = new SmaliAnnotationSet();
        setSmaliAnnotationSet(smaliAnnotationSet);
        return smaliAnnotationSet;
    }

    public SmaliClass getSmaliClass() {
        return getClass().isInstance(SmaliClass.class) ? (SmaliClass) this : (SmaliClass) getParentInstance(SmaliClass.class);
    }

    public SmaliDirective getSmaliDirective() {
        return null;
    }

    public boolean hasAnnotation() {
        SmaliAnnotationSet annotationSet = getAnnotationSet();
        return (annotationSet == null || annotationSet.isEmpty()) ? false : true;
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public boolean hasAnnotations() {
        SmaliAnnotationSet annotationSet = getAnnotationSet();
        return (annotationSet == null || annotationSet.isEmpty()) ? false : true;
    }

    public void setAccessFlags(AccessFlag[] accessFlagArr) {
        setAccessFlagsValue(Modifier.combineValues(accessFlagArr));
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public void setAccessFlagsValue(int i) {
        this.accessFlagsValue = i;
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void setAnnotation(AnnotationSetKey annotationSetKey) {
        if (annotationSetKey == null || annotationSetKey.isEmpty()) {
            setSmaliAnnotationSet(null);
        } else {
            getOrCreateSmaliAnnotationSet().setKey(annotationSetKey);
        }
    }

    public void setDefining(TypeKey typeKey) {
        this.defining = typeKey;
    }

    public void setHiddenApiFlags(HiddenApiFlag... hiddenApiFlagArr) {
        setHiddenApiFlagsValue(HiddenApiFlag.combineHiddenApiFlag(hiddenApiFlagArr));
    }

    public void setHiddenApiFlagsValue(int i) {
        this.hiddenApiFlagsValue = i;
    }

    public void setName(String str) {
        setName(StringKey.create(str));
    }

    public void setSmaliAnnotationSet(SmaliAnnotationSet smaliAnnotationSet) {
        SmaliAnnotationSet smaliAnnotationSet2 = this.annotation;
        this.annotation = smaliAnnotationSet;
        if (smaliAnnotationSet != null) {
            smaliAnnotationSet.setParent(this);
        }
        if (smaliAnnotationSet2 == null || smaliAnnotationSet2 == smaliAnnotationSet) {
            return;
        }
        smaliAnnotationSet2.setParent(null);
    }

    public void setAccessFlags(Iterator<AccessFlag> it) {
        setAccessFlagsValue(AccessFlag.combineAccessFlags(it));
    }

    public void setHiddenApiFlags(Iterator<HiddenApiFlag> it) {
        setHiddenApiFlagsValue(HiddenApiFlag.combineHiddenApiFlag(it));
    }

    public void setName(StringKey stringKey) {
        this.name = stringKey;
    }

    public void setAnnotation(Iterator<AnnotationItemKey> it) {
        if (it.hasNext()) {
            getOrCreateSmaliAnnotationSet().addAnnotations(it);
        } else {
            setSmaliAnnotationSet(null);
        }
    }
}
