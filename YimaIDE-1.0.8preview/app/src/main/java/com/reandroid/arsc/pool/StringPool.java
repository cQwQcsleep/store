package com.reandroid.arsc.pool;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.header.StringPoolHeader;
import com.reandroid.arsc.item.AlignItem;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.OffsetItem;
import com.reandroid.arsc.item.ReferenceItem;
import com.reandroid.arsc.item.StringCreator;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.StyleItem;
import com.reandroid.arsc.list.OffsetReferenceList;
import com.reandroid.arsc.list.StringItemList;
import com.reandroid.arsc.list.StyleItemList;
import com.reandroid.arsc.list.StyleItemListEnd;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.common.BytesOutputStream;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.NumbersUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.MultiMap;
import com.reandroid.xml.StyleDocument;
import defpackage.tqd;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class StringPool<T extends StringItem> extends Chunk<StringPoolHeader> implements Iterable<T>, JSONConvert<JSONArray> {
    private final StringItemList<T> mArrayStrings;
    private final StyleItemList mArrayStyles;
    private final Object mLock;
    private final MultiMap<String, T> poolMap;
    private boolean stringLinkLocked;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.OffsetReferenceList] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.StringItemList, com.reandroid.arsc.list.StringItemList<T extends com.reandroid.arsc.item.StringItem>] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.OffsetReferenceList] */
    /* JADX WARN: Type inference failed for: r8v1, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.StyleItemList] */
    public StringPool(boolean z, boolean z2, StringCreator<T> stringCreator) {
        super(new StringPoolHeader(), 6);
        this.mLock = new Object();
        StringPoolHeader headerBlock = getHeaderBlock();
        headerBlock.setEncodingChangedListener((StringPoolHeader.EncodingChangedListener) null);
        headerBlock.setUtf8(z);
        IntegerItem countStrings = headerBlock.getCountStrings();
        Creator<OffsetItem> creator = OffsetItem.CREATOR_OFFSET32;
        ?? offsetReferenceList = new OffsetReferenceList(countStrings, creator);
        ?? offsetReferenceList2 = new OffsetReferenceList(headerBlock.getCountStyles(), creator);
        AlignItem alignItem = new AlignItem(true);
        ?? r3 = (StringItemList<T>) new StringItemList(alignItem, headerBlock, offsetReferenceList, stringCreator);
        this.mArrayStrings = r3;
        ?? styleItemList = new StyleItemList(headerBlock.getStartStyles(), offsetReferenceList2);
        this.mArrayStyles = styleItemList;
        StyleItemListEnd styleItemListEnd = new StyleItemListEnd(headerBlock.getCountStyles());
        addChild(offsetReferenceList);
        addChild(offsetReferenceList2);
        addChild(r3);
        addChild(alignItem);
        addChild(styleItemList);
        addChild(styleItemListEnd);
        this.stringLinkLocked = z2;
        MultiMap<String, T> multiMap = new MultiMap<>();
        this.poolMap = multiMap;
        multiMap.setFavouriteObjectsSorter(new Comparator() { // from class: pqd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return StringPool.q((StringItem) obj, (StringItem) obj2);
            }
        });
    }

    private boolean containsInternal(final T t) {
        return this.poolMap.containsValue(t.getXml(), new Predicate() { // from class: vqd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return StringPool.o(t, (StringItem) obj);
            }
        });
    }

    private Predicate<T> getUnusedStringsFilter() {
        return new Predicate() { // from class: rqd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return StringPool.k((StringItem) obj);
            }
        };
    }

    public static /* synthetic */ boolean k(StringItem stringItem) {
        return !stringItem.hasReference();
    }

    public static /* synthetic */ boolean o(StringItem stringItem, StringItem stringItem2) {
        return stringItem2.compareTo(stringItem) == 0;
    }

    public static /* synthetic */ void p(List list) {
        StringItem stringItem = (StringItem) list.get(0);
        for (int i = 1; i < list.size(); i++) {
            stringItem.transferReferences((StringItem) list.get(i));
        }
    }

    public static /* synthetic */ int q(StringItem stringItem, StringItem stringItem2) {
        int iCompareTo = stringItem.compareTo(stringItem2);
        return iCompareTo == 0 ? CompareUtil.compareUnsigned(stringItem.getIndex(), stringItem2.getIndex()) : iCompareTo;
    }

    private void reloadPoolMap() {
        if (this.poolMap.size() == 0) {
            this.poolMap.clear();
            this.poolMap.setInitialSize(size());
            this.poolMap.putAll(new tqd(), iterator());
        }
    }

    public void addStrings(Collection<String> collection) {
        if (collection == null || collection.size() == 0) {
            return;
        }
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            createNewString(it.next());
        }
    }

    public void clear() {
        getStyleArray().clear();
        getStringsArray().clear();
        this.poolMap.clear();
    }

    public void compressDuplicates() {
        ensureStringLinkUnlockedInternal();
        this.poolMap.findDuplicates(CompareUtil.getComparableComparator(), new Consumer() { // from class: uqd
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StringPool.p((List) obj);
            }
        });
    }

    public boolean contains(String str) {
        return this.poolMap.containsKey(str);
    }

    public final int countStyles() {
        return this.mArrayStyles.size();
    }

    public int countUnused() {
        return getStringsArray().countIf(getUnusedStringsFilter());
    }

    public T createNewString() {
        return (T) this.mArrayStrings.createNext();
    }

    public void ensureStringLinkUnlockedInternal() {
        synchronized (this.mLock) {
            try {
                if (this.stringLinkLocked) {
                    this.stringLinkLocked = false;
                    linkStrings();
                    reloadPoolMap();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        getStringsArray().fromJson(jSONArray);
        refresh();
    }

    public final T get(String str, Predicate<? super T> predicate) {
        ensureStringLinkUnlockedInternal();
        if (str == null) {
            str = StringsUtil.EMPTY;
        }
        return (T) this.poolMap.get(str, predicate);
    }

    public final Iterator<T> getAll(String str) {
        ensureStringLinkUnlockedInternal();
        MultiMap<String, T> multiMap = this.poolMap;
        return str == null ? FilterIterator.of(multiMap.getAll(StringsUtil.EMPTY), new Predicate() { // from class: qqd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((StringItem) obj).isNull();
            }
        }) : multiMap.getAll(str);
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream(getHeaderBlock().getChunkSize());
        try {
            writeBytes(bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    public String getEncoding() {
        return isUtf8() ? "utf-8" : "utf-16";
    }

    public final T getLast() {
        return (T) this.mArrayStrings.getLast();
    }

    public T getOrCreate(JSONObject jSONObject) {
        ensureStringLinkUnlockedInternal();
        if (jSONObject.optJSONObject(StringItem.NAME_style) != null) {
            T t = (T) createNewString();
            t.set(jSONObject);
            StyleDocument styleDocument = t.getStyleDocument();
            if (styleDocument != null) {
                T t2 = (T) getString(styleDocument.getXml());
                if (t2 == null) {
                    return t;
                }
                if (t.hasReference()) {
                    t.set(null);
                    t.setNull(true);
                }
                return t2;
            }
        }
        return (T) getOrCreate(jSONObject.getString(StringItem.NAME_string));
    }

    public final T getString(String str) {
        return (T) CollectionUtil.getFirst(getAll(str));
    }

    public Iterator<String> getStrings() {
        return ComputeIterator.of(iterator(), new tqd());
    }

    public StringItemList<T> getStringsArray() {
        return this.mArrayStrings;
    }

    public StyleItemList getStyleArray() {
        return this.mArrayStyles;
    }

    public <E extends Block> Iterator<E> getUsers(final Class<E> cls, String str) {
        return new IterableIterator<T, E>(getAll(str)) { // from class: com.reandroid.arsc.pool.StringPool.1
            public Iterator<E> iterator(T t) {
                return t.getUsers(cls);
            }
        };
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isStringLinkLocked() {
        return this.stringLinkLocked;
    }

    public boolean isUtf8() {
        return getHeaderBlock().isUtf8();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.mArrayStrings.iterator();
    }

    public void linkStrings() {
        getStyleArray().linkStyleStringsInternal();
    }

    public void linkStylesInternal() {
        StyleItemList styleArray = getStyleArray();
        StringItemList<T> stringsArray = getStringsArray();
        int iMin = NumbersUtil.min(stringsArray.size(), styleArray.size());
        for (int i = 0; i < iMin; i++) {
            ((StringItem) stringsArray.get(i)).linkStyleItemInternal((StyleItem) styleArray.get(i));
        }
        getStyleArray().linkStyleStringsInternal();
    }

    public List<T> listUnused() {
        return getStringsArray().subListIf(getUnusedStringsFilter());
    }

    public void merge(StringPool<T> stringPool) {
        if (stringPool == null || stringPool == this || stringPool.isEmpty()) {
            return;
        }
        ensureStringLinkUnlockedInternal();
        for (T t : stringPool) {
            if (!containsInternal(t)) {
                createNewString().merge(t);
            }
        }
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkLoaded() {
        linkStylesInternal();
        reloadPoolMap();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    public void onPreAddInternal(int i, T t) {
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        ensureStringLinkUnlockedInternal();
    }

    public void onSortedInternal() {
        getStyleArray().sort();
    }

    public void onStringChanged(String str, T t) {
        if (this.stringLinkLocked) {
            return;
        }
        this.poolMap.updateKey(str, t.getXml(), t);
    }

    public void onStringRemoved(T t) {
        if (this.stringLinkLocked) {
            return;
        }
        this.poolMap.remove(t.getXml(), t);
    }

    public T removeReference(ReferenceItem referenceItem) {
        T t;
        if (referenceItem == null || (t = (T) get(referenceItem.get())) == null) {
            return null;
        }
        t.removeReference(referenceItem);
        return t;
    }

    public void removeString(T t) {
        getStringsArray().remove(t);
    }

    public boolean removeUnusedStrings() {
        return getStringsArray().removeIf(getUnusedStringsFilter());
    }

    public void setEncoding(String str) {
        setUtf8((str == null || StringsUtil.toLowercase(str).startsWith("utf-16")) ? false : true);
    }

    public void setUtf8(boolean z) {
        getHeaderBlock().setUtf8(z);
    }

    public int size() {
        return this.mArrayStrings.size();
    }

    public void sort() {
        ensureStringLinkUnlockedInternal();
        getStringsArray().sort();
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m76toJson() {
        return getStringsArray().m75toJson();
    }

    public T createNewString(String str) {
        T t = (T) createNewString();
        t.set(str);
        return t;
    }

    public final T get(int i) {
        return (T) this.mArrayStrings.get(i);
    }

    public T getOrCreate(StyleDocument styleDocument) {
        ensureStringLinkUnlockedInternal();
        T t = (T) get(styleDocument.getXml(), new Predicate() { // from class: wqd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((StringItem) obj).hasStyle();
            }
        });
        if (t != null) {
            return t;
        }
        T t2 = (T) createNewString();
        t2.set(styleDocument);
        return t2;
    }

    public T getOrCreate(final String str) {
        ensureStringLinkUnlockedInternal();
        T t = (T) get(str, new Predicate() { // from class: sqd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((StringItem) obj).equalsValue(str);
            }
        });
        return t == null ? (T) createNewString(str) : t;
    }

    public StringPool(boolean z, StringCreator<T> stringCreator) {
        this(z, true, stringCreator);
    }
}
