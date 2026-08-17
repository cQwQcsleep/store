package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.EditableItem;
import com.reandroid.dex.common.HiddenApiFlag;
import com.reandroid.dex.common.IdDefinition;
import com.reandroid.dex.common.IdUsageIterator;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.data.AnnotationSet;
import com.reandroid.dex.data.Def;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.ProgramKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionList;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliDef;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.ExpandIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.MultiMap;
import com.reandroid.utils.collection.SingleIterator;
import defpackage.df3;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Def<T extends IdItem> extends FixedDexContainerWithTool implements IdDefinition<T>, EditableItem, Comparable<Def<T>>, SmaliRegion, DefIndex, IdUsageIterator {
    private final Ule128Item accessFlags;
    private HiddenApiFlagValue hiddenApiFlagValue;
    private int mCachedIndex;
    private boolean mCachedIndexUpdated;
    private T mDefId;
    private final Ule128Item relativeId;
    private final SectionType<T> sectionType;

    public Def(int i, SectionType<T> sectionType) {
        super(i + 2);
        this.sectionType = sectionType;
        Ule128Item ule128Item = new Ule128Item(true);
        this.relativeId = ule128Item;
        Ule128Item ule128Item2 = new Ule128Item();
        this.accessFlags = ule128Item2;
        addChild(0, ule128Item);
        addChild(1, ule128Item2);
    }

    public static /* synthetic */ boolean b(AnnotationSet annotationSet) {
        return !annotationSet.isEmpty();
    }

    private void cacheItem() {
        T t = (T) getSectionItem(this.sectionType, getDefinitionIndex());
        this.mDefId = t;
        if (t != null) {
            t.addUsageType(UsageMarker.USAGE_DEFINITION);
        }
    }

    private int calculateDefinitionIndex() {
        Def<T> def;
        DefArray<Def<T>> parentArray = getParentArray();
        return (parentArray == null || (def = parentArray.get(getIndex() + (-1))) == null) ? this.relativeId.get() : getRelativeIdValue() + def.getDefinitionIndex();
    }

    private Iterator<AnnotationItem> getAnnotationItemBlocks() {
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        return annotationsDirectory != null ? ExpandIterator.of(annotationsDirectory.getAnnotations(this)) : EmptyIterator.of();
    }

    private ClassData getClassData() {
        DefArray<Def<T>> parentArray = getParentArray();
        if (parentArray != null) {
            return parentArray.getClassData();
        }
        return null;
    }

    private DefArray<Def<T>> getParentArray() {
        return (DefArray) getParent();
    }

    private int getPreviousIdIndex() {
        Def<T> def;
        DefArray<Def<T>> parentArray = getParentArray();
        if (parentArray == null || (def = parentArray.get(getIndex() - 1)) == null) {
            return 0;
        }
        return def.getDefinitionIndex();
    }

    private boolean hasAnnotationSetBlocks() {
        return getAnnotationItemBlocks().hasNext();
    }

    private void updateHiddenApiFlag() {
        HiddenApiFlagValue hiddenApiFlagValue = this.hiddenApiFlagValue;
        if (hiddenApiFlagValue == null || !hiddenApiFlagValue.isRemoved()) {
            return;
        }
        this.hiddenApiFlagValue = null;
    }

    private void updateIndex() {
        resetIndex();
        T t = (T) this.mDefId.getReplace();
        this.mDefId = t;
        this.relativeId.set(t.getIndex() - getPreviousIdIndex());
        t.addUsageType(UsageMarker.USAGE_DEFINITION);
    }

    private void writeAnnotation(AnnotationSetKey annotationSetKey) {
        if (annotationSetKey != null && !annotationSetKey.isEmpty()) {
            getOrCreateUniqueAnnotationsDirectory().addAnnotation((Def<?>) this, annotationSetKey);
        } else if (hasAnnotationSetBlocks()) {
            getOrCreateUniqueAnnotationsDirectory().remove(this);
        }
    }

    public void addHiddenApiFlag(HiddenApiFlag hiddenApiFlag) {
        if (hiddenApiFlag != null) {
            getOrCreateHiddenApiFlagValue().add(hiddenApiFlag);
        }
    }

    public void addHiddenApiFlags(Iterator<HiddenApiFlag> it) {
        while (it.hasNext()) {
            addHiddenApiFlag(it.next());
        }
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void clearAnnotations() {
        writeAnnotation(AnnotationSetKey.empty());
    }

    @Override // java.lang.Comparable
    public int compareTo(Def<T> def) {
        if (def == null) {
            return -1;
        }
        return SectionTool.compareIdx(getId(), def.getId());
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
    }

    public abstract void fromSmali(Smali smali);

    @Override // com.reandroid.dex.program.AccessibleProgram
    public int getAccessFlagsValue() {
        return this.accessFlags.get();
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationSetKey getAnnotation() {
        return AnnotationSetKey.create(ComputeIterator.of(getAnnotationItemBlocks(), new df3()));
    }

    public Iterator<AnnotationItemKey> getAnnotationKeys() {
        Iterator<AnnotationSetKey> annotationSetKeys = getAnnotationSetKeys();
        return !annotationSetKeys.hasNext() ? EmptyIterator.of() : new IterableIterator<AnnotationSetKey, AnnotationItemKey>(annotationSetKeys) { // from class: com.reandroid.dex.data.Def.1
            public Iterator<AnnotationItemKey> iterator(AnnotationSetKey annotationSetKey) {
                return annotationSetKey.iterator();
            }
        };
    }

    public Iterator<AnnotationSetKey> getAnnotationSetKeys() {
        return ComputeIterator.of(getAnnotationSets(true), new Function() { // from class: cf3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AnnotationSet) obj).getKey();
            }
        });
    }

    public Iterator<AnnotationSet> getAnnotationSets(boolean z) {
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        if (annotationsDirectory == null) {
            return EmptyIterator.of();
        }
        Iterator<AnnotationSet> annotations = annotationsDirectory.getAnnotations(this);
        return (z && annotations.hasNext()) ? FilterIterator.of(annotations, new Predicate() { // from class: bf3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Def.b((AnnotationSet) obj);
            }
        }) : annotations;
    }

    public AnnotationsDirectory getAnnotationsDirectory() {
        ClassId classId = getClassId();
        if (classId != null) {
            return classId.getAnnotationsDirectory();
        }
        return null;
    }

    public ClassId getClassId() {
        TypeKey defining;
        MultiMap pool;
        ClassId classId;
        ClassData classData;
        ClassId classId2;
        DefArray<Def<T>> parentArray = getParentArray();
        if (parentArray != null && (classId2 = parentArray.getClassId()) != null) {
            return classId2;
        }
        SectionList sectionList = getSectionList();
        if (sectionList == null || sectionList.isReading() || (defining = getDefining()) == null || (pool = getPool(SectionType.CLASS_ID)) == null || (classId = (ClassId) pool.get(defining)) == null || (classData = getClassData()) == null) {
            return null;
        }
        classData.setClassId(classId);
        return classId;
    }

    public TypeKey getDefining() {
        ProgramKey key = getKey();
        if (key != null) {
            return key.getDeclaring();
        }
        return null;
    }

    @Override // com.reandroid.dex.data.DefIndex
    public int getDefinitionIndex() {
        if (!this.mCachedIndexUpdated) {
            this.mCachedIndexUpdated = true;
            this.mCachedIndex = calculateDefinitionIndex();
        }
        return this.mCachedIndex;
    }

    public HiddenApiFlagValue getHiddenApiFlagValue() {
        return this.hiddenApiFlagValue;
    }

    public Iterator<HiddenApiFlag> getHiddenApiFlags() {
        HiddenApiFlagValue hiddenApiFlagValue = getHiddenApiFlagValue();
        return hiddenApiFlagValue != null ? hiddenApiFlagValue.iterator() : EmptyIterator.of();
    }

    @Override // com.reandroid.dex.common.IdDefinition
    public T getId() {
        return this.mDefId;
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public abstract ProgramKey getKey();

    @Override // com.reandroid.dex.program.AccessibleProgram
    public Iterator<? extends Modifier> getModifiers() {
        return CombiningIterator.two(getAccessFlags(), getHiddenApiFlags());
    }

    public HiddenApiFlagValue getOrCreateHiddenApiFlagValue() {
        HiddenApiFlagValue hiddenApiFlagValue = getHiddenApiFlagValue();
        if (hiddenApiFlagValue != null) {
            return hiddenApiFlagValue;
        }
        Section<T1> orCreateSection = getOrCreateSection(SectionType.HIDDEN_API);
        HiddenApiRestrictions hiddenApiRestrictions = (HiddenApiRestrictions) orCreateSection.get(0);
        if (hiddenApiRestrictions == null) {
            hiddenApiRestrictions = (HiddenApiRestrictions) orCreateSection.createItem();
        }
        return hiddenApiRestrictions.getFlagValue(getKey());
    }

    public AnnotationsDirectory getOrCreateUniqueAnnotationsDirectory() {
        ClassId classId = getClassId();
        if (classId != null) {
            return classId.getOrCreateUniqueAnnotationsDirectory();
        }
        return null;
    }

    public int getRelativeIdValue() {
        return this.relativeId.get();
    }

    @Override // com.reandroid.dex.common.IdDefinition
    public boolean isRemoved() {
        IdItem id;
        return getParent() == null || (id = getId()) == null || id.isRemoved();
    }

    public void linkHiddenApiFlagValueInternal(HiddenApiFlagValue hiddenApiFlagValue) {
        this.hiddenApiFlagValue = hiddenApiFlagValue;
    }

    public void merge(Def<?> def) {
        setItem(def.getKey());
        setAccessFlagsValue(def.getAccessFlagsValue());
        HiddenApiFlagValue hiddenApiFlagValue = def.getHiddenApiFlagValue();
        if (hiddenApiFlagValue != null) {
            addHiddenApiFlag(hiddenApiFlagValue.getRestriction());
            addHiddenApiFlag(hiddenApiFlagValue.getDomain());
        }
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        cacheItem();
    }

    public void onRefreshed() {
        super.onRefreshed();
        updateIndex();
        updateHiddenApiFlag();
    }

    public void onRemove() {
        HiddenApiFlagValue hiddenApiFlagValue = getHiddenApiFlagValue();
        if (hiddenApiFlagValue != null) {
            linkHiddenApiFlagValueInternal(null);
            hiddenApiFlagValue.removeSelf();
        }
        this.mCachedIndexUpdated = true;
        this.mDefId = null;
        this.relativeId.set(0);
    }

    public void removeHiddenApiFlag(HiddenApiFlag hiddenApiFlag) {
        HiddenApiFlagValue hiddenApiFlagValue;
        if (hiddenApiFlag == null || (hiddenApiFlagValue = getHiddenApiFlagValue()) == null) {
            return;
        }
        hiddenApiFlagValue.remove(hiddenApiFlag);
    }

    public void removeHiddenApiFlags() {
        HiddenApiFlagValue hiddenApiFlagValue = getHiddenApiFlagValue();
        if (hiddenApiFlagValue != null) {
            hiddenApiFlagValue.clear();
        }
    }

    public void removeSelf() {
        DefArray<Def<T>> parentArray = getParentArray();
        if (parentArray != null) {
            parentArray.remove(this);
        }
    }

    public void replaceKeys(Key key, Key key2) {
        Key key3 = getKey();
        Key keyReplaceKey = key3.replaceKey(key, key2);
        if (key3 != keyReplaceKey) {
            setItem(keyReplaceKey);
        }
    }

    public void resetIndex() {
        this.mCachedIndexUpdated = false;
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public void setAccessFlagsValue(int i) {
        this.accessFlags.set(i);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void setAnnotation(AnnotationSetKey annotationSetKey) {
        if (ObjectsUtil.equals(getAnnotation(), annotationSetKey)) {
            return;
        }
        clearAnnotations();
        writeAnnotation(annotationSetKey);
    }

    public void setItem(Key key) {
        IdItem id = getId();
        if (id == null || !key.equals(id.getKey())) {
            setItem((IdItem) getOrCreateSection(this.sectionType).getOrCreate(key));
        }
    }

    public void setKey(Key key) {
        setItem(key);
    }

    public abstract SmaliDef toSmali();

    @Override // com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return SingleIterator.of(getId());
    }

    public void setItem(T t) {
        this.mDefId = t;
        updateIndex();
    }
}
