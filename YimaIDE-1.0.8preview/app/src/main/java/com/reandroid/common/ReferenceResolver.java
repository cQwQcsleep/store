package com.reandroid.common;

import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.collection.ArrayCollection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ReferenceResolver {
    private final TableBlock entryStore;
    private final List<Entry> results = new ArrayCollection();
    private final Set<Integer> resolvedIds = new HashSet();
    private int limit = -1;

    public static class ConfigFilter implements Predicate<Entry>, Comparator<Entry> {
        private final ResConfig config;

        public ConfigFilter(ResConfig resConfig) {
            this.config = resConfig;
        }

        @Override // java.util.Comparator
        public int compare(Entry entry, Entry entry2) {
            ResConfig resConfig = this.config;
            ResConfig resConfig2 = entry.getResConfig();
            ResConfig resConfig3 = entry2.getResConfig();
            if (resConfig.equals(resConfig2)) {
                return resConfig.equals(resConfig3) ? 0 : -1;
            }
            if (resConfig.equals(resConfig3) || resConfig2.isEqualOrMoreSpecificThan(resConfig3)) {
                return 1;
            }
            return resConfig3.isEqualOrMoreSpecificThan(resConfig2) ? -1 : 0;
        }

        @Override // java.util.function.Predicate
        public boolean test(Entry entry) {
            ResConfig resConfig = entry.getResConfig();
            if (resConfig == null) {
                return false;
            }
            return resConfig.isEqualOrMoreSpecificThan(this.config);
        }
    }

    public ReferenceResolver(TableBlock tableBlock) {
        this.entryStore = tableBlock;
    }

    private void addResult(Predicate<Entry> predicate, Entry entry) {
        if (predicate == null || predicate.test(entry)) {
            this.results.add(entry);
        }
    }

    private boolean isFinished() {
        return this.limit >= this.results.size();
    }

    private List<Entry> listNonNullEntries(int i) {
        ArrayCollection arrayCollection = new ArrayCollection();
        ResourceEntry resource = this.entryStore.getResource(i);
        if (resource != null) {
            Iterator<Entry> it = resource.iterator(true);
            while (it.hasNext()) {
                arrayCollection.add(it.next());
            }
        }
        return arrayCollection;
    }

    private void reset() {
        this.results.clear();
        this.resolvedIds.clear();
        this.limit = -1;
    }

    private void resolveReference(int i, Predicate<Entry> predicate) {
        if (i == 0 || isFinished() || this.resolvedIds.contains(Integer.valueOf(i))) {
            return;
        }
        this.resolvedIds.add(Integer.valueOf(i));
        List<Entry> listListNonNullEntries = listNonNullEntries(i);
        List<Entry> list = this.results;
        for (Entry entry : listListNonNullEntries) {
            if (isFinished()) {
                return;
            }
            if (!list.contains(entry)) {
                if (entry.isComplex()) {
                    addResult(predicate, entry);
                } else {
                    ResValue resValue = entry.getResValue();
                    if (resValue.getValueType() != ValueType.REFERENCE) {
                        addResult(predicate, entry);
                    } else {
                        resolveReference(resValue.getData(), predicate);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Entry resolve(int i, Predicate<Entry> predicate) {
        resolveReference(i, predicate);
        ArrayCollection arrayCollection = new ArrayCollection(this.results);
        reset();
        if (arrayCollection.size() <= 0) {
            return null;
        }
        return (Entry) arrayCollection.get(0);
    }

    public synchronized List<Entry> resolveAll(int i, Predicate<Entry> predicate) {
        ArrayCollection arrayCollection;
        resolveReference(i, predicate);
        arrayCollection = new ArrayCollection(this.results);
        reset();
        return arrayCollection;
    }

    public List<Entry> resolveWithConfig(int i, ResConfig resConfig) {
        ConfigFilter configFilter = new ConfigFilter(resConfig);
        List<Entry> listResolveAll = resolveAll(i, configFilter);
        listResolveAll.sort(configFilter);
        return listResolveAll;
    }

    public List<Entry> resolveAll(int i) {
        return resolveAll(i, null);
    }

    public Entry resolve(int i) {
        return resolve(i, null);
    }
}
