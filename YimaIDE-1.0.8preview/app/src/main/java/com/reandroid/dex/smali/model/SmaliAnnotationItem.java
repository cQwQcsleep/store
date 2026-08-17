package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliAnnotationItem extends SmaliSet<SmaliAnnotationElement> implements KeyReference, SmaliRegion {
    private TypeKey type;
    private SmaliDirective smaliDirective = SmaliDirective.ANNOTATION;
    private AnnotationVisibility visibility = AnnotationVisibility.BUILD;

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        AnnotationItemKey key = getKey();
        if (key != null) {
            key.append(smaliWriter);
            return;
        }
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendOptional(getVisibility());
        smaliWriter.appendOptional(getType());
        smaliWriter.appendAllWithIndent(iterator());
        getSmaliDirective().appendEnd(smaliWriter);
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet
    public SmaliAnnotationElement createNext(SmaliReader smaliReader) {
        smaliReader.skipWhitespacesOrComment();
        if (smaliReader.finished() || getSmaliDirective().isEnd(smaliReader)) {
            return null;
        }
        return new SmaliAnnotationElement();
    }

    public SmaliAnnotationElement get(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            SmaliAnnotationElement smaliAnnotationElement = get(i);
            if (ObjectsUtil.equals(smaliAnnotationElement.getName(), str)) {
                return smaliAnnotationElement;
            }
        }
        return null;
    }

    @Override // com.reandroid.dex.key.KeyItem
    public AnnotationItemKey getKey() {
        TypeKey type = getType();
        if (type == null) {
            return null;
        }
        int size = size();
        AnnotationElementKey[] annotationElementKeyArr = new AnnotationElementKey[size];
        for (int i = 0; i < size; i++) {
            annotationElementKeyArr[i] = get(i).getKey();
        }
        return AnnotationItemKey.create(getVisibility(), type, annotationElementKeyArr);
    }

    public SmaliAnnotationSet getParentAnnotationSet() {
        Smali parent = getParent();
        if (parent instanceof SmaliAnnotationSet) {
            return (SmaliAnnotationSet) parent;
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return this.smaliDirective;
    }

    public TypeKey getType() {
        return this.type;
    }

    public AnnotationVisibility getVisibility() {
        return this.visibility;
    }

    public boolean hasElement(String str) {
        return get(str) != null;
    }

    public SmaliAnnotationElement newElement() {
        SmaliAnnotationElement smaliAnnotationElement = new SmaliAnnotationElement();
        add(smaliAnnotationElement);
        return smaliAnnotationElement;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        TypeKey type;
        SmaliAnnotationItem smaliAnnotationItem;
        int iPosition = smaliReader.position();
        AnnotationItemKey annotationItemKey = AnnotationItemKey.read(smaliReader);
        SmaliDirective smaliDirective = getSmaliDirective();
        SmaliAnnotationSet parentAnnotationSet = getParentAnnotationSet();
        if (parentAnnotationSet != null) {
            smaliDirective = SmaliDirective.ANNOTATION;
        }
        if (smaliDirective != annotationItemKey.getSmaliDirective()) {
            smaliReader.position(iPosition);
            throw new SmaliParseException("Expecting: " + smaliDirective, smaliReader);
        }
        if (parentAnnotationSet == null || (smaliAnnotationItem = parentAnnotationSet.get((type = annotationItemKey.getType()))) == null || smaliAnnotationItem == this) {
            setKey(annotationItemKey);
            return;
        }
        smaliReader.position(iPosition);
        throw new SmaliParseException("Duplicate annotation: " + type, smaliReader);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        clear();
        AnnotationItemKey annotationItemKey = (AnnotationItemKey) key;
        setVisibility(annotationItemKey.getVisibility());
        setType(annotationItemKey.getType());
        Iterator<AnnotationElementKey> it = annotationItemKey.iterator();
        while (it.hasNext()) {
            newElement().setKey(it.next());
        }
    }

    public void setType(TypeKey typeKey) {
        this.type = typeKey;
    }

    public void setVisibility(AnnotationVisibility annotationVisibility) {
        if (annotationVisibility != null) {
            this.smaliDirective = SmaliDirective.ANNOTATION;
        } else {
            if (getParentAnnotationSet() != null) {
                x0e.a("Null AnnotationVisibility");
                return;
            }
            this.smaliDirective = SmaliDirective.SUB_ANNOTATION;
        }
        this.visibility = annotationVisibility;
    }
}
