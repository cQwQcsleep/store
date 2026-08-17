package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.reference.Ule128IdItemReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliAnnotationElement;
import com.reandroid.dex.smali.model.SmaliAnnotationItem;
import com.reandroid.dex.value.DexValueBlock;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationItem extends DataItem implements Comparable<AnnotationItem>, Iterable<AnnotationElement>, KeyReference, SmaliRegion {
    private final CountedBlockList<AnnotationElement> annotationElements;
    private final boolean mValueEntry;
    private final Ule128IdItemReference<TypeId> typeId;
    private final ByteItem visibility;

    public AnnotationItem(boolean z) {
        super(z ? 3 : 4);
        this.mValueEntry = z;
        Block byteItem = z ? null : new ByteItem();
        this.visibility = byteItem;
        Block ule128IdItemReference = new Ule128IdItemReference(SectionType.TYPE_ID, UsageMarker.USAGE_ANNOTATION);
        this.typeId = ule128IdItemReference;
        Block ule128Item = new Ule128Item();
        CountedBlockList<AnnotationElement> countedBlockList = new CountedBlockList<>(AnnotationElement.CREATOR, ule128Item);
        this.annotationElements = countedBlockList;
        int i = 0;
        if (!z) {
            addChildBlock(0, byteItem);
            i = 1;
        }
        addChildBlock(i, ule128IdItemReference);
        addChildBlock(i + 1, ule128Item);
        addChildBlock(i + 2, countedBlockList);
    }

    public void addElement(AnnotationElementKey annotationElementKey) {
        createNewElement().setKey(annotationElementKey);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendOptional(getVisibility());
        getTypeId().append(smaliWriter);
        smaliWriter.indentPlus();
        smaliWriter.appendAllWithDoubleNewLine(iterator());
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    public AnnotatedProgram asAnnotated() {
        return new WarpedAnnotation(this);
    }

    public void clear() {
        this.annotationElements.clearChildes();
    }

    public Iterator<AnnotationElement> clonedIterator() {
        return this.annotationElements.clonedIterator();
    }

    @Override // java.lang.Comparable
    public int compareTo(AnnotationItem annotationItem) {
        if (annotationItem == null) {
            return -1;
        }
        if (annotationItem == this) {
            return 0;
        }
        int iCompareIdx = SectionTool.compareIdx(getTypeId(), annotationItem.getTypeId());
        return iCompareIdx != 0 ? iCompareIdx : CompareUtil.compare(getVisibilityValue(), annotationItem.getVisibilityValue());
    }

    public boolean containsName(String str) {
        Iterator<AnnotationElement> it = iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getName())) {
                return true;
            }
        }
        return false;
    }

    public AnnotationElement createNewElement() {
        return this.annotationElements.createNext();
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        super.editInternal(block);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AnnotationItem annotationItem = (AnnotationItem) obj;
        if (ObjectsUtil.equals(getType(), annotationItem.getType()) && getVisibilityValue() == annotationItem.getVisibilityValue()) {
            return this.annotationElements.equals(annotationItem.annotationElements);
        }
        return false;
    }

    public void fromSmali(SmaliAnnotationItem smaliAnnotationItem) {
        setType(smaliAnnotationItem.getType());
        setVisibility(smaliAnnotationItem.getVisibility());
        Iterator<SmaliAnnotationElement> it = smaliAnnotationItem.iterator();
        while (it.hasNext()) {
            createNewElement().fromSmali(it.next());
        }
    }

    public AnnotationElement getElement(String str) {
        for (AnnotationElement annotationElement : this) {
            if (str.equals(annotationElement.getName())) {
                return annotationElement;
            }
        }
        return null;
    }

    public DexValueBlock<?> getElementValue(String str) {
        AnnotationElement element = getElement(str);
        if (element != null) {
            return element.getValueBlock();
        }
        return null;
    }

    public AnnotationElementKey[] getElements() {
        int elementsCount = getElementsCount();
        AnnotationElementKey[] annotationElementKeyArr = new AnnotationElementKey[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            annotationElementKeyArr[i] = getElement(i).getKey();
        }
        return annotationElementKeyArr;
    }

    public int getElementsCount() {
        return this.annotationElements.size();
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public AnnotationItemKey getKey() {
        return (AnnotationItemKey) checkKey(AnnotationItemKey.create(getVisibility(), getType(), getElements()));
    }

    public String[] getNames() {
        CountedBlockList<AnnotationElement> countedBlockList = this.annotationElements;
        int size = countedBlockList.size();
        if (size == 0) {
            return null;
        }
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = countedBlockList.get(i).getName();
        }
        return strArr;
    }

    public AnnotationElement getOrCreateElement(String str) {
        AnnotationElement element = getElement(str);
        if (element != null) {
            return element;
        }
        AnnotationElement annotationElementCreateNewElement = createNewElement();
        annotationElementCreateNewElement.setName(str);
        annotationElementCreateNewElement.getOrCreateValue(DexValueType.NULL);
        return annotationElementCreateNewElement;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<AnnotationItem> getSectionType() {
        return SectionType.ANNOTATION_ITEM;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return isValueEntry() ? SmaliDirective.SUB_ANNOTATION : SmaliDirective.ANNOTATION;
    }

    public TypeKey getType() {
        return (TypeKey) this.typeId.getKey();
    }

    public TypeId getTypeId() {
        return (TypeId) this.typeId.getItem();
    }

    public AnnotationVisibility getVisibility() {
        if (isValueEntry()) {
            return null;
        }
        return AnnotationVisibility.valueOf(this.visibility.get());
    }

    public int getVisibilityValue() {
        ByteItem byteItem = this.visibility;
        if (byteItem == null) {
            return -1;
        }
        return byteItem.get();
    }

    public int hashCode() {
        return ObjectsUtil.hash(getVisibility(), getType(), this.annotationElements);
    }

    public boolean isEmpty() {
        return this.annotationElements.size() == 0;
    }

    public boolean isValueEntry() {
        return this.mValueEntry;
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotationElement> iterator() {
        return this.annotationElements.iterator();
    }

    public void merge(AnnotationItem annotationItem) {
        if (annotationItem == this) {
            return;
        }
        setVisibility(annotationItem.getVisibilityValue());
        setType(annotationItem.getType());
        this.annotationElements.ensureCapacity(annotationItem.getElementsCount());
        Iterator<AnnotationElement> it = annotationItem.iterator();
        while (it.hasNext()) {
            createNewElement().merge(it.next());
        }
    }

    public void remove(AnnotationElement annotationElement) {
        this.annotationElements.remove(annotationElement);
    }

    public boolean removeIf(Predicate<AnnotationElement> predicate) {
        return this.annotationElements.removeIf(predicate);
    }

    public void replaceKeys(Key key, Key key2) {
        AnnotationItemKey key3 = getKey();
        AnnotationItemKey annotationItemKeyReplaceKey = key3.replaceKey(key, key2);
        if (key3 != annotationItemKeyReplaceKey) {
            setKey(annotationItemKeyReplaceKey);
        }
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        AnnotationItemKey annotationItemKey = (AnnotationItemKey) key;
        setVisibility(annotationItemKey.getVisibility());
        setType(annotationItemKey.getType());
        clear();
        Iterator<AnnotationElementKey> it = annotationItemKey.iterator();
        while (it.hasNext()) {
            addElement(it.next());
        }
    }

    public void setType(String str) {
        setType(TypeKey.create(str));
    }

    public void setVisibility(AnnotationVisibility annotationVisibility) {
        setVisibility(annotationVisibility != null ? annotationVisibility.getValue() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("@");
        sb.append(getType());
        boolean z = false;
        for (AnnotationElement annotationElement : this) {
            if (z) {
                sb.append(", ");
            } else {
                sb.append('(');
            }
            sb.append(annotationElement);
            z = true;
        }
        if (z) {
            sb.append(')');
        }
        return sb.toString();
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return getType().getTypeName().startsWith("Ldalvik/annotation/") ? EmptyIterator.of() : CombiningIterator.singleOne(getTypeId(), new IterableIterator<AnnotationElement, IdItem>(iterator()) { // from class: com.reandroid.dex.data.AnnotationItem.1
            public Iterator<IdItem> iterator(AnnotationElement annotationElement) {
                return annotationElement.usedIds();
            }
        });
    }

    public void setType(TypeKey typeKey) {
        this.typeId.setKey(typeKey);
    }

    public void setVisibility(int i) {
        ByteItem byteItem = this.visibility;
        if (byteItem != null) {
            byteItem.set((byte) i);
        }
    }

    public static class WarpedAnnotation implements AnnotatedProgram {
        private final AnnotationItem mItem;

        public WarpedAnnotation(AnnotationItem annotationItem) {
            this.mItem = annotationItem;
        }

        private AnnotationItemKey getItemKey() {
            if (this.mItem.isRemoved()) {
                return null;
            }
            return this.mItem.getKey();
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public void clearAnnotations() {
            this.mItem.removeSelf();
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public AnnotationSetKey getAnnotation() {
            AnnotationItemKey itemKey = getItemKey();
            return itemKey != null ? AnnotationSetKey.of(itemKey) : AnnotationSetKey.empty();
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public boolean hasAnnotation(TypeKey typeKey) {
            AnnotationItemKey itemKey = getItemKey();
            return itemKey != null && itemKey.getType().equals(typeKey);
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public boolean hasAnnotations() {
            return !this.mItem.isRemoved();
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public boolean removeAnnotation(TypeKey typeKey) {
            AnnotationItemKey itemKey = getItemKey();
            if (itemKey == null || !itemKey.getType().equals(typeKey)) {
                return false;
            }
            clearAnnotations();
            return true;
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public boolean removeAnnotationIf(Predicate<? super AnnotationItemKey> predicate) {
            AnnotationItemKey itemKey = getItemKey();
            if (itemKey == null || !predicate.test(itemKey)) {
                return false;
            }
            clearAnnotations();
            return true;
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public void setAnnotation(AnnotationSetKey annotationSetKey) {
            if (annotationSetKey.isEmpty()) {
                return;
            }
            if (this.mItem.isRemoved()) {
                w01.a("AnnotationItem was removed");
            } else if (annotationSetKey.size() <= 1) {
                this.mItem.setKey(annotationSetKey.get(0));
            } else {
                w01.a("Multiple AnnotationItem");
            }
        }

        @Override // com.reandroid.dex.program.AnnotatedProgram
        public AnnotationItemKey getAnnotation(TypeKey typeKey) {
            AnnotationItemKey itemKey = getItemKey();
            if (itemKey == null || !itemKey.getType().equals(typeKey)) {
                return null;
            }
            return itemKey;
        }
    }

    public AnnotationElement getElement(int i) {
        return this.annotationElements.get(i);
    }

    public AnnotationItem() {
        this(false);
    }
}
