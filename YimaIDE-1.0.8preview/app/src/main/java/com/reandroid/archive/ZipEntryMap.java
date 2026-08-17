package com.reandroid.archive;

import com.reandroid.archive.InputSource;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.ArraySort;
import com.reandroid.utils.collection.CollectionUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipEntryMap implements Comparator<InputSource>, Iterable<InputSource> {
    private ArchiveInfo archiveInfo;
    private final Object mLock;
    private final LinkedHashMap<String, InputSource> mSourceMap;
    private String moduleName;
    private InputSource[] sourcesArray;

    public ZipEntryMap(LinkedHashMap<String, InputSource> linkedHashMap) {
        this.mLock = new Object();
        this.mSourceMap = linkedHashMap;
        this.moduleName = "";
        this.archiveInfo = ArchiveInfo.build(linkedHashMap.values().iterator());
    }

    private void onChanged(boolean z) {
        if (z) {
            this.sourcesArray = null;
        }
    }

    private void set(InputSource[] inputSourceArr) {
        clear();
        addAll(inputSourceArr);
    }

    public void add(InputSource inputSource) {
        if (inputSource == null) {
            return;
        }
        synchronized (this.mLock) {
            String alias = inputSource.getAlias();
            LinkedHashMap<String, InputSource> linkedHashMap = this.mSourceMap;
            linkedHashMap.remove(alias);
            linkedHashMap.put(alias, inputSource);
            onChanged(true);
        }
    }

    public void addAll(InputSource[] inputSourceArr) {
        if (inputSourceArr == null) {
            return;
        }
        synchronized (this.mLock) {
            try {
                LinkedHashMap<String, InputSource> linkedHashMap = this.mSourceMap;
                boolean z = false;
                for (InputSource inputSource : inputSourceArr) {
                    if (inputSource != null) {
                        linkedHashMap.remove(inputSource.getName());
                        String alias = inputSource.getAlias();
                        linkedHashMap.remove(alias);
                        linkedHashMap.put(alias, inputSource);
                        if (!z) {
                            onChanged(true);
                        }
                        z = true;
                    }
                }
                onChanged(z);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void autoSortApkFiles() {
        InputSource[] array = toArray();
        int length = array.length;
        for (InputSource inputSource : array) {
            inputSource.setSort(-1);
        }
        ArraySort.sort(array, InputSource.ALIAS_COMPARATOR);
        for (int i = 0; i < length; i++) {
            array[i].setSort(i);
        }
        set(array);
    }

    public void clear() {
        synchronized (this.mLock) {
            this.mSourceMap.clear();
            onChanged(true);
        }
    }

    @Override // java.util.Comparator
    public int compare(InputSource inputSource, InputSource inputSource2) {
        if (inputSource == inputSource2) {
            return 0;
        }
        if (inputSource == null) {
            return 1;
        }
        if (inputSource2 == null) {
            return -1;
        }
        return Integer.compare(inputSource.getSort(), inputSource2.getSort());
    }

    public boolean contains(String str) {
        boolean zContainsKey;
        synchronized (this.mLock) {
            zContainsKey = this.mSourceMap.containsKey(str);
        }
        return zContainsKey;
    }

    public ArchiveInfo getArchiveInfo() {
        return this.archiveInfo;
    }

    public InputSource getInputSource(String str) {
        InputSource inputSource;
        synchronized (this.mLock) {
            inputSource = this.mSourceMap.get(str);
        }
        return inputSource;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public ArchiveInfo getOrCreateArchiveInfo() {
        ArchiveInfo archiveInfo = getArchiveInfo();
        if (archiveInfo == null) {
            archiveInfo = ArchiveInfo.build(iterator());
            if (archiveInfo == null) {
                archiveInfo = ArchiveInfo.apk();
            }
            setArchiveInfo(archiveInfo);
        }
        return archiveInfo;
    }

    public PathTree<InputSource> getPathTree() {
        return Archive.buildPathTree(toArray());
    }

    public Iterator<InputSource> iterator(Predicate<? super InputSource> predicate) {
        return ArrayIterator.of(toArray(), predicate);
    }

    public Iterator<InputSource> iteratorWithPath(final Predicate<? super String> predicate) {
        return iterator(new Predicate() { // from class: p8g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((InputSource) obj).getAlias());
            }
        });
    }

    public List<InputSource> listInputSources() {
        return new ArrayList(this.mSourceMap.values());
    }

    public void refresh() {
        set(toArray(true));
    }

    public InputSource remove(InputSource inputSource) {
        InputSource inputSourceRemove;
        if (inputSource == null) {
            return null;
        }
        synchronized (this.mLock) {
            try {
                inputSourceRemove = this.mSourceMap.remove(inputSource.getAlias());
                if (inputSourceRemove == null) {
                    inputSourceRemove = this.mSourceMap.remove(inputSource.getName());
                }
                onChanged(inputSourceRemove != null);
            } catch (Throwable th) {
                throw th;
            }
        }
        return inputSourceRemove;
    }

    public void removeDir(String str) {
        if (!str.endsWith("/")) {
            str = str.concat("/");
        }
        synchronized (this.mLock) {
            try {
                boolean z = false;
                for (InputSource inputSource : toArray()) {
                    if (inputSource.getName().startsWith(str)) {
                        InputSource inputSourceRemove = this.mSourceMap.remove(inputSource.getName());
                        if (!z) {
                            z = inputSourceRemove != null;
                        }
                    }
                }
                onChanged(z);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeIf(Pattern pattern) {
        synchronized (this.mLock) {
            try {
                LinkedHashMap<String, InputSource> linkedHashMap = this.mSourceMap;
                boolean z = false;
                for (InputSource inputSource : toArray()) {
                    String alias = inputSource.getAlias();
                    if (pattern.matcher(alias).matches() && linkedHashMap.remove(alias) != null) {
                        z = true;
                    }
                }
                onChanged(z);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setArchiveInfo(ArchiveInfo archiveInfo) {
        this.archiveInfo = archiveInfo;
    }

    public void setModuleName(String str) {
        this.moduleName = str;
    }

    public int size() {
        int size;
        synchronized (this.mLock) {
            size = this.mSourceMap.size();
        }
        return size;
    }

    public LinkedHashMap<String, InputSource> toAliasMap() {
        InputSource[] array = toArray();
        LinkedHashMap<String, InputSource> linkedHashMap = new LinkedHashMap<>(array.length);
        for (InputSource inputSource : array) {
            linkedHashMap.put(inputSource.getAlias(), inputSource);
        }
        return linkedHashMap;
    }

    public InputSource[] toArray() {
        synchronized (this.mLock) {
            try {
                InputSource[] inputSourceArr = this.sourcesArray;
                if (inputSourceArr != null) {
                    return inputSourceArr;
                }
                LinkedHashMap<String, InputSource> linkedHashMap = this.mSourceMap;
                InputSource[] inputSourceArr2 = new InputSource[linkedHashMap.size()];
                Iterator<InputSource> it = linkedHashMap.values().iterator();
                int i = 0;
                while (it.hasNext()) {
                    inputSourceArr2[i] = it.next();
                    i++;
                }
                this.sourcesArray = inputSourceArr2;
                return inputSourceArr2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Iterator<InputSource> withinDirectory(final String str, boolean z) {
        if (str.length() != 0 && !str.endsWith("/")) {
            str = str.concat("/");
        }
        return z ? iterator(new Predicate() { // from class: n8g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((InputSource) obj).getAlias().startsWith(str);
            }
        }) : iterator(new Predicate() { // from class: o8g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((InputSource) obj).getParentPath().equals(str);
            }
        });
    }

    @Override // java.lang.Iterable
    public Iterator<InputSource> iterator() {
        return ArrayIterator.of(toArray());
    }

    public ZipEntryMap() {
        this(new LinkedHashMap());
    }

    public Iterator<InputSource> withinDirectory(String str) {
        return withinDirectory(str, true);
    }

    public InputSource remove(String str) {
        InputSource inputSourceRemove;
        synchronized (this.mLock) {
            inputSourceRemove = this.mSourceMap.remove(str);
            onChanged(inputSourceRemove != null);
        }
        return inputSourceRemove;
    }

    public void removeIf(Predicate<? super InputSource> predicate) {
        Iterator<InputSource> it = iterator(predicate);
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    public InputSource[] toArray(boolean z) {
        InputSource[] array = toArray();
        if (z) {
            ArraySort.sort(array, this);
        }
        return array;
    }

    public InputSource[] toArray(Predicate<? super InputSource> predicate) {
        return (InputSource[]) CollectionUtil.toList(iterator(predicate)).toArray(new InputSource[0]);
    }

    public void addAll(Iterable<? extends InputSource> iterable) {
        addAll(iterable.iterator());
    }

    public void addAll(Iterator<? extends InputSource> it) {
        while (it.hasNext()) {
            add(it.next());
        }
    }
}
