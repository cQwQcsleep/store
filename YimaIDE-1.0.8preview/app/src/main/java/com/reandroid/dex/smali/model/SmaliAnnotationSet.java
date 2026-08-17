package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliAnnotationSet extends SmaliSet<SmaliAnnotationItem> {
    public static SmaliAnnotationSet read(SmaliReader smaliReader) throws IOException {
        SmaliAnnotationSet smaliAnnotationSet = new SmaliAnnotationSet();
        smaliAnnotationSet.parse(smaliReader);
        if (smaliAnnotationSet.isEmpty()) {
            return null;
        }
        return smaliAnnotationSet;
    }

    public void addAnnotation(AnnotationItemKey annotationItemKey) {
        getOrCreate(annotationItemKey.getType()).setKey(annotationItemKey);
    }

    public void addAnnotations(Iterator<? extends AnnotationItemKey> it) {
        while (it.hasNext()) {
            addAnnotation(it.next());
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendAll(iterator());
    }

    public AnnotatedProgram asAnnotatedProgram() {
        return new AnnotatedProgram() { // from class: com.reandroid.dex.smali.model.SmaliAnnotationSet.1
            @Override // com.reandroid.dex.program.AnnotatedProgram
            public void clearAnnotations() {
                this.clear();
            }

            @Override // com.reandroid.dex.program.AnnotatedProgram
            public AnnotationSetKey getAnnotation() {
                return this.getKey();
            }

            @Override // com.reandroid.dex.program.AnnotatedProgram
            public boolean hasAnnotation(TypeKey typeKey) {
                return this.hasAnnotation(typeKey);
            }

            @Override // com.reandroid.dex.program.AnnotatedProgram
            public void setAnnotation(AnnotationSetKey annotationSetKey) {
                this.setKey(annotationSetKey);
            }
        };
    }

    public SmaliAnnotationItem createNew() {
        SmaliAnnotationItem smaliAnnotationItem = new SmaliAnnotationItem();
        add(smaliAnnotationItem);
        return smaliAnnotationItem;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet
    public SmaliAnnotationItem createNext(SmaliReader smaliReader) {
        smaliReader.skipWhitespacesOrComment();
        SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader, false);
        if (smaliDirective != SmaliDirective.ANNOTATION && smaliDirective != SmaliDirective.SUB_ANNOTATION) {
            return null;
        }
        if (!smaliDirective.isEnd(smaliReader)) {
            return new SmaliAnnotationItem();
        }
        SmaliDirective.parse(smaliReader);
        return null;
    }

    public SmaliAnnotationItem get(TypeKey typeKey) {
        int size = size();
        for (int i = 0; i < size; i++) {
            SmaliAnnotationItem smaliAnnotationItem = get(i);
            if (ObjectsUtil.equals(smaliAnnotationItem.getType(), typeKey)) {
                return smaliAnnotationItem;
            }
        }
        return null;
    }

    public AnnotationSetKey getKey() {
        int size = size();
        AnnotationItemKey[] annotationItemKeyArr = new AnnotationItemKey[size];
        for (int i = 0; i < size; i++) {
            annotationItemKeyArr[i] = get(i).getKey();
        }
        return AnnotationSetKey.of(annotationItemKeyArr);
    }

    public SmaliAnnotationItem getOrCreate(TypeKey typeKey) {
        SmaliAnnotationItem smaliAnnotationItem = get(typeKey);
        if (smaliAnnotationItem != null) {
            return smaliAnnotationItem;
        }
        SmaliAnnotationItem smaliAnnotationItemCreateNew = createNew();
        smaliAnnotationItemCreateNew.setType(typeKey);
        return smaliAnnotationItemCreateNew;
    }

    public boolean hasAnnotation(TypeKey typeKey) {
        return get(typeKey) != null;
    }

    public void setKey(Key key) {
        clear();
        Iterator<AnnotationItemKey> it = ((AnnotationSetKey) key).iterator();
        while (it.hasNext()) {
            createNew().setKey(it.next());
        }
    }
}
