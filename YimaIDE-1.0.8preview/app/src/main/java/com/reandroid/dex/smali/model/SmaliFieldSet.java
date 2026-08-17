package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliField;
import com.reandroid.utils.collection.FilterIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliFieldSet extends SmaliDefSet<SmaliField> {
    public static SmaliFieldSet read(SmaliReader smaliReader) throws IOException {
        SmaliFieldSet smaliFieldSet = new SmaliFieldSet();
        smaliFieldSet.parse(smaliReader);
        if (smaliFieldSet.isEmpty()) {
            return null;
        }
        return smaliFieldSet;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDefSet, com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        Iterator<SmaliField> staticFields = getStaticFields();
        boolean z = false;
        boolean z2 = false;
        while (staticFields.hasNext()) {
            smaliWriter.newLineDouble();
            if (!z2) {
                smaliWriter.appendComment("static fields");
                smaliWriter.newLine();
            }
            staticFields.next().append(smaliWriter);
            z2 = true;
        }
        Iterator<SmaliField> instanceFields = getInstanceFields();
        while (instanceFields.hasNext()) {
            smaliWriter.newLineDouble();
            if (!z) {
                smaliWriter.appendComment("instance fields");
                smaliWriter.newLine();
            }
            instanceFields.next().append(smaliWriter);
            z = true;
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliDefSet
    public SmaliField createNew() {
        return new SmaliField();
    }

    public Iterator<SmaliField> getInstanceFields() {
        return FilterIterator.of(iterator(), new Predicate() { // from class: tgd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SmaliField) obj).isInstance();
            }
        });
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.FIELD;
    }

    public Iterator<SmaliField> getStaticFields() {
        return FilterIterator.of(iterator(), new Predicate() { // from class: ugd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SmaliField) obj).isStatic();
            }
        });
    }
}
