package com.reandroid.dex.model;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.dex.model.DexFileSourceSet;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.gq3;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexFileSourceSet implements Iterable<DexSource<DexFile>>, Closeable {
    private Predicate<SectionType<?>> readFilter;
    private final ArrayCollection<DexSource<DexFile>> sourceList = new ArrayCollection<>();
    private ZipEntryMap zipEntryMap;

    public static /* synthetic */ boolean a(String str, InputSource inputSource) {
        String alias = inputSource.getAlias();
        return alias.startsWith(str) && DexFile.getDexFileNumber(alias) >= 0;
    }

    private boolean isEmpty(DexSource<DexFile> dexSource) {
        DexFile dexFile = dexSource.get();
        return dexFile == null || dexFile.isEmpty();
    }

    private void load(DexSource<DexFile> dexSource) throws IOException {
        if (dexSource.get() != null) {
            return;
        }
        DexFile dexFile = DexFile.read(dexSource.openStream(), getReadFilter());
        dexSource.set(dexFile);
        dexFile.setSimpleName(dexSource.getSimpleName());
    }

    private void save(DexSource<DexFile> dexSource) throws IOException {
        if (isEmpty(dexSource)) {
            delete(dexSource);
        } else {
            dexSource.write(dexSource.get().getBytes());
        }
    }

    public DexSource<DexFile> add(DexSource<DexFile> dexSource) throws IOException {
        DexSource<DexFile> element = this.sourceList.getElement(dexSource);
        if (element != null) {
            if (element == dexSource) {
                load(dexSource);
                return element;
            }
            r8g.a("Duplicate dex source: ", dexSource);
            return null;
        }
        load(dexSource);
        this.sourceList.remove(dexSource);
        this.sourceList.add(dexSource);
        this.sourceList.sort(CompareUtil.getComparableComparator());
        return dexSource;
    }

    public void addAll(ZipEntryMap zipEntryMap, String str) throws IOException {
        if (str == null || str.length() <= 0) {
            str = "";
        } else if (str.charAt(0) == '/') {
            str = str.substring(1);
        }
        if (str.length() > 0 && !str.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
            str = str.concat(PsuedoNames.PSEUDONAME_ROOT);
        }
        final String strConcat = str.concat("classes");
        addAll(zipEntryMap, new Predicate() { // from class: hq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexFileSourceSet.a(strConcat, (InputSource) obj);
            }
        });
    }

    public Iterator<DexSource<DexFile>> clonedIterator() {
        return this.sourceList.clonedIterator();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Iterator<DexSource<DexFile>> it = this.sourceList.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.sourceList.clear();
    }

    public DexSource<DexFile> createNext() {
        DexSource<DexFile> last = getLast();
        if (last != null) {
            DexSource<DexFile> dexSourceCreateNext = last.createNext();
            this.sourceList.add(dexSourceCreateNext);
            this.sourceList.sort(CompareUtil.getComparableComparator());
            return dexSourceCreateNext;
        }
        ZipEntryMap zipEntryMap = getZipEntryMap();
        if (zipEntryMap == null) {
            x0e.a("Null ZipEntryMap");
            return null;
        }
        DexSource<DexFile> dexSourceCreate = DexSource.create(zipEntryMap, "classes.dex", DexFile.createDefault());
        this.sourceList.add(dexSourceCreate);
        return dexSourceCreate;
    }

    public void delete(DexSource<DexFile> dexSource) {
        remove(dexSource);
        dexSource.delete();
    }

    public Iterator<DexFile> getClonedDexFiles() {
        return ComputeIterator.of(clonedIterator(), new gq3());
    }

    public DexFile getDexFile(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }
        return getSource(i).get();
    }

    public Iterator<DexFile> getDexFiles() {
        return ComputeIterator.of(iterator(), new gq3());
    }

    public DexSource<DexFile> getFirst() {
        return this.sourceList.getFirst();
    }

    public DexSource<DexFile> getLast() {
        this.sourceList.sort(CompareUtil.getComparableComparator());
        return this.sourceList.getLast();
    }

    public Predicate<SectionType<?>> getReadFilter() {
        return this.readFilter;
    }

    public DexSource<DexFile> getSource(DexFile dexFile) {
        if (dexFile == null) {
            return null;
        }
        for (DexSource<DexFile> dexSource : this) {
            if (dexFile == dexSource.get()) {
                return dexSource;
            }
        }
        return null;
    }

    public ZipEntryMap getZipEntryMap() {
        return this.zipEntryMap;
    }

    @Override // java.lang.Iterable
    public Iterator<DexSource<DexFile>> iterator() {
        return this.sourceList.iterator();
    }

    public void merge(DexFileSourceSet dexFileSourceSet) {
        if (dexFileSourceSet == this) {
            w01.a("Cyclic merge");
            return;
        }
        for (DexSource<DexFile> dexSource : dexFileSourceSet) {
            if (!isEmpty(dexSource)) {
                DexSource<DexFile> dexSourceCreateNext = createNext();
                dexSourceCreateNext.set(dexSource.get());
                try {
                    save(dexSourceCreateNext);
                } catch (IOException e) {
                    rc6.a(e);
                    return;
                }
            }
        }
    }

    public void remove(DexSource<DexFile> dexSource) {
        this.sourceList.remove(dexSource);
        dexSource.set(null);
    }

    public void removeEmpty() {
        Iterator<DexSource<DexFile>> itClonedIterator = this.sourceList.clonedIterator();
        while (itClonedIterator.hasNext()) {
            DexSource<DexFile> next = itClonedIterator.next();
            if (isEmpty(next)) {
                remove(next);
            }
        }
    }

    public void saveAll(File file) throws IOException {
        Iterator<DexSource<DexFile>> itClonedIterator = this.sourceList.clonedIterator();
        while (itClonedIterator.hasNext()) {
            DexSource<DexFile> next = itClonedIterator.next();
            DexSource<DexFile> dexSourceCreate = DexSource.create(new File(file, next.getSimpleName()));
            dexSourceCreate.set(next.get());
            if (isEmpty(dexSourceCreate)) {
                dexSourceCreate.delete();
            } else {
                dexSourceCreate.write(next.get().getBytes());
            }
        }
    }

    public void setReadFilter(Predicate<SectionType<?>> predicate) {
        this.readFilter = predicate;
    }

    public void setZipEntryMap(ZipEntryMap zipEntryMap) {
        this.zipEntryMap = zipEntryMap;
    }

    public int size() {
        return this.sourceList.size();
    }

    public String toString() {
        return "size = " + size();
    }

    public DexSource<DexFile> getSource(int i) {
        return this.sourceList.get(i);
    }

    public void add(ZipEntryMap zipEntryMap, String str) throws IOException {
        add(DexSource.create(zipEntryMap, str));
        if (getZipEntryMap() == null) {
            setZipEntryMap(zipEntryMap);
        }
    }

    public void add(ZipEntryMap zipEntryMap, String str, DexFile dexFile) throws IOException {
        add(DexSource.create(zipEntryMap, str, dexFile));
        if (getZipEntryMap() == null) {
            setZipEntryMap(zipEntryMap);
        }
    }

    public DexSource<DexFile> add(File file) throws IOException {
        return add(DexSource.create(file));
    }

    public void add(ZipEntryMap zipEntryMap, InputSource inputSource) throws IOException {
        String alias = inputSource.getAlias();
        if (zipEntryMap.getInputSource(alias) == null) {
            zipEntryMap.add(inputSource);
        }
        add(zipEntryMap, alias);
    }

    public void addAll(ZipEntryMap zipEntryMap) throws IOException {
        addAll(zipEntryMap, (String) null);
    }

    public void addAll(ZipEntryMap zipEntryMap, Predicate<InputSource> predicate) throws IOException {
        addAll(zipEntryMap, zipEntryMap.iterator(predicate));
    }

    public void addAll(ZipEntryMap zipEntryMap, Iterator<InputSource> it) throws IOException {
        while (it.hasNext()) {
            add(zipEntryMap, it.next().getAlias());
        }
    }

    public void addAll(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isFile() && DexFile.getDexFileNumber(file2.getName()) >= 0) {
                    add(file2);
                }
            }
            return;
        }
        r8g.a("No such directory: ", file);
    }

    public void saveAll() throws IOException {
        Iterator<DexSource<DexFile>> itClonedIterator = this.sourceList.clonedIterator();
        while (itClonedIterator.hasNext()) {
            save(itClonedIterator.next());
        }
    }
}
