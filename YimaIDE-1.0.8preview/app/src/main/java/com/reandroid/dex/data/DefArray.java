package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.EditableItem;
import com.reandroid.dex.common.IdUsageIterator;
import com.reandroid.dex.data.Def;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliDef;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DefArray<T extends Def<?>> extends CountedBlockList<T> implements Iterable<T>, EditableItem, SmaliFormat, IdUsageIterator {
    private ClassId mClassId;

    public DefArray(IntegerReference integerReference, Creator<T> creator) {
        super(creator, integerReference);
    }

    private void linkAnnotation() {
        AnnotationsDirectory annotationsDirectory;
        int size = size();
        if (size == 0 || (annotationsDirectory = getAnnotationsDirectory()) == null) {
            return;
        }
        for (int i = 0; i < size; i++) {
            annotationsDirectory.link(get(i));
        }
    }

    private void resetIndex() {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).resetIndex();
        }
    }

    private ClassId searchClassId() {
        ClassId classId = this.mClassId;
        if (classId != null) {
            return classId;
        }
        Iterator<T> it = iterator();
        return it.hasNext() ? it.next().getClassId() : classId;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).append(smaliWriter);
            smaliWriter.newLine();
        }
    }

    public void clear() {
        clearChildes();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public T createNext() {
        T tCreateNext = super.createNext();
        updateCountReference();
        return tCreateNext;
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).editInternal(block);
        }
    }

    public void fromSmali(Iterator<? extends Smali> it) {
        while (it.hasNext()) {
            fromSmali(it.next());
        }
    }

    public T get(Key key) {
        for (T t : this) {
            if (key.equals(t.getKey())) {
                return t;
            }
        }
        return null;
    }

    public AnnotationsDirectory getAnnotationsDirectory() {
        ClassId classIdSearchClassId = searchClassId();
        if (classIdSearchClassId == null) {
            return null;
        }
        return classIdSearchClassId.getAnnotationsDirectory();
    }

    public ClassData getClassData() {
        return (ClassData) getParentInstance(ClassData.class);
    }

    public ClassId getClassId() {
        return this.mClassId;
    }

    public T getOrCreate(Key key) {
        T t = (T) get(key);
        if (t != null) {
            return t;
        }
        T t2 = (T) createNext();
        t2.setKey(key);
        return t2;
    }

    public AnnotationsDirectory getUniqueAnnotationsDirectory() {
        ClassId classIdSearchClassId = searchClassId();
        if (classIdSearchClassId == null) {
            return null;
        }
        return classIdSearchClassId.getUniqueAnnotationsDirectory();
    }

    public void merge(DefArray<T> defArray) {
        int size = defArray.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            T t = get(i);
            T t2 = defArray.get(i);
            t.merge(t2);
            onMerged(t, t2);
        }
        updateCountReference();
        linkAnnotation();
    }

    public void onMerged(T t, T t2) {
    }

    public void onPostSort(Object obj) {
        resetIndex();
        sortAnnotations();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onPreRefresh() {
        super.onPreRefresh();
        linkAnnotation();
        if (sort()) {
            resetIndex();
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onPreRemove(T t) {
        AnnotationsDirectory uniqueAnnotationsDirectory = getUniqueAnnotationsDirectory();
        if (uniqueAnnotationsDirectory != null) {
            uniqueAnnotationsDirectory.clear(t);
        }
        resetIndex();
        super.onPreRemove(t);
        t.onRemove();
    }

    public Object onPreSort() {
        ClassId classId = getClassId();
        if (classId != null) {
            classId.getUniqueAnnotationsDirectory();
        }
        linkAnnotation();
        return null;
    }

    @Override // com.reandroid.arsc.container.CountedBlockList
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onRemoveRequestCompleted(Object obj) {
        super.onRemoveRequestCompleted(obj);
        updateCountReference();
    }

    public void replaceKeys(Key key, Key key2) {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).replaceKeys(key, key2);
        }
    }

    public void setClassId(ClassId classId) {
        if (this.mClassId == classId) {
            return;
        }
        this.mClassId = classId;
        if (classId != null) {
            linkAnnotation();
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public final boolean sort(Comparator<? super T> comparator) {
        if (!needsSort(comparator)) {
            sortAnnotations();
            return false;
        }
        Object objOnPreSort = onPreSort();
        boolean zSort = super.sort(comparator);
        onPostSort(objOnPreSort);
        return zSort;
    }

    public void sortAnnotations() {
    }

    public Iterator<SmaliDef> toSmali() {
        return ComputeIterator.of(iterator(), new Function() { // from class: ef3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Def) obj).toSmali();
            }
        });
    }

    @Override // com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<Def<?>, IdItem>(iterator()) { // from class: com.reandroid.dex.data.DefArray.1
            public Iterator<IdItem> iterator(Def<?> def) {
                return def.usedIds();
            }
        };
    }

    public T fromSmali(Smali smali) {
        T t = (T) createNext();
        t.fromSmali(smali);
        return t;
    }

    public boolean sort() {
        return sort(CompareUtil.getComparableComparator());
    }
}
