package com.reandroid.arsc.refactor;

import com.reandroid.apk.ApkModule;
import com.reandroid.apk.ResFile;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.list.EntryItemList;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.pool.TypeStringPool;
import com.reandroid.arsc.refactor.ResourceBuilder;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.FilterIterator;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResourceBuilder {
    private ResourceMergeOption mMergeOption;
    private boolean mRebuilt;
    private final Map<Integer, Integer> resourceIdMap;
    private ApkModule resultModule;
    private final TableBlock sourceTable;

    public ResourceBuilder(ResourceMergeOption resourceMergeOption, TableBlock tableBlock) {
        this.mMergeOption = resourceMergeOption;
        this.sourceTable = tableBlock;
        this.resourceIdMap = new HashMap();
    }

    public static /* synthetic */ boolean a(Predicate predicate, ResourceEntry resourceEntry) {
        return resourceEntry.isDefined() && predicate.test(resourceEntry);
    }

    private void addIdMap(int i, int i2) {
        if (i == i2 || i == 0 || i2 == 0 || i == -1 || i2 == -1) {
            return;
        }
        this.resourceIdMap.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    private void initializeEntries(String str, PackageBlock packageBlock, PackageBlock packageBlock2) {
        final Predicate<? super ResourceEntry> keepEntries = getMergeOption().getKeepEntries();
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.addAll(FilterIterator.of(packageBlock.getResources(str), new Predicate() { // from class: whc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ResourceBuilder.a(keepEntries, (ResourceEntry) obj);
            }
        }));
        arrayCollection.sort(new Comparator() { // from class: xhc
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.b.compareEntryNames((ResourceEntry) obj, (ResourceEntry) obj2);
            }
        });
        EntryItemList entryArray = packageBlock2.getOrCreateTypeBlock(ResConfig.getDefault(), str).getEntryArray();
        int size = arrayCollection.size();
        entryArray.setSize(arrayCollection.size());
        for (int i = 0; i < size; i++) {
            ((Entry) entryArray.get(i)).setName(((ResourceEntry) arrayCollection.get(i)).getName(), true);
        }
    }

    private void initializePackage(PackageBlock packageBlock, PackageBlock packageBlock2) {
        initializeTypeString(packageBlock, packageBlock2);
        initializeSpecString(packageBlock, packageBlock2);
        initializeEntries(packageBlock, packageBlock2);
    }

    private void initializePackages() {
        TableBlock sourceTable = getSourceTable();
        TableBlock resultTable = getResultTable();
        int size = sourceTable.size();
        for (int i = 0; i < size; i++) {
            initializePackage(sourceTable.get(i), resultTable.get(i));
        }
    }

    private void initializeSpecString(PackageBlock packageBlock, PackageBlock packageBlock2) {
        packageBlock2.getSpecStringPool().merge(packageBlock.getSpecStringPool());
    }

    private void initializeTable() {
        TableBlock<PackageBlock> sourceTable = getSourceTable();
        sourceTable.refresh();
        TableBlock resultTable = getResultTable();
        resultTable.addFrameworks(sourceTable.frameworks());
        for (PackageBlock packageBlock : sourceTable) {
            resultTable.newPackage(packageBlock.getId(), packageBlock.getName());
        }
        resultTable.getStringPool().merge(sourceTable.getStringPool());
        initializePackages();
    }

    private void initializeTypeString(PackageBlock packageBlock, PackageBlock packageBlock2) {
        Predicate<? super ResourceEntry> keepEntries = getMergeOption().getKeepEntries();
        TypeStringPool typeStringPool = packageBlock.getTypeStringPool();
        HashSet hashSet = new HashSet(typeStringPool.size());
        Iterator it = typeStringPool.iterator();
        while (it.hasNext()) {
            String str = ((TypeString) it.next()).get();
            if (FilterIterator.of(packageBlock.getResources(str), keepEntries).hasNext()) {
                hashSet.add(str);
            }
        }
        ArrayCollection arrayCollection = new ArrayCollection(hashSet);
        arrayCollection.sort(CompareUtil.getComparableComparator());
        packageBlock2.getTypeStringPool().addStrings(arrayCollection);
    }

    private void mergePackage(PackageBlock packageBlock, PackageBlock packageBlock2) {
        ResourceMergeOption mergeOption = getMergeOption();
        Iterator itOf = FilterIterator.of(packageBlock.getResources(), mergeOption.getKeepEntries());
        while (itOf.hasNext()) {
            ResourceEntry resourceEntry = (ResourceEntry) itOf.next();
            if (!resourceEntry.isEmpty()) {
                addIdMap(resourceEntry.getResourceId(), packageBlock2.mergeWithName(mergeOption, resourceEntry).getResourceId());
            }
        }
    }

    private void mergePackages() {
        TableBlock tableBlock = this.sourceTable;
        TableBlock resultTable = getResultTable();
        int size = tableBlock.size();
        for (int i = 0; i < size; i++) {
            mergePackage(tableBlock.get(i), resultTable.get(i));
        }
    }

    private void transferResFiles(ApkModule apkModule) {
        ApkModule resultModule = getResultModule();
        List listListResFiles = resultModule.listResFiles();
        HashSet hashSet = new HashSet();
        Iterator it = listListResFiles.iterator();
        while (it.hasNext()) {
            InputSource inputSource = ((ResFile) it.next()).getInputSource();
            String alias = inputSource.getAlias();
            if (!hashSet.contains(alias)) {
                apkModule.add(inputSource);
                hashSet.add(alias);
            }
        }
        ZipEntryMap zipEntryMap = resultModule.getZipEntryMap();
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            zipEntryMap.remove((String) it2.next());
        }
    }

    private void transferTableBlock(ApkModule apkModule) {
        ApkModule resultModule = getResultModule();
        TableBlock tableBlock = resultModule.getTableBlock();
        if (tableBlock == null) {
            return;
        }
        apkModule.setTableBlock(tableBlock);
        resultModule.setTableBlock((TableBlock) null);
    }

    public int applyIdChanges(Iterator<IntegerReference> it) {
        Map<Integer, Integer> resourceIdMap = getResourceIdMap();
        int i = 0;
        while (it.hasNext()) {
            IntegerReference next = it.next();
            Integer num = resourceIdMap.get(Integer.valueOf(next.get()));
            if (num != null) {
                next.set(num.intValue());
                i++;
            }
        }
        return i;
    }

    public int compareEntryNames(ResourceEntry resourceEntry, ResourceEntry resourceEntry2) {
        String name = resourceEntry.getName();
        String name2 = resourceEntry2.getName();
        if (name == null && name2 != null) {
            return 1;
        }
        if (name != null && name2 == null) {
            return -1;
        }
        if (name == null) {
            return 0;
        }
        if (name.startsWith("$")) {
            name = name.substring(1);
        }
        if (name2.startsWith("$")) {
            name2 = name2.substring(1);
        }
        return CompareUtil.compare(name.compareTo(name2), 0);
    }

    public ResourceMergeOption getMergeOption() {
        ResourceMergeOption resourceMergeOption = this.mMergeOption;
        if (resourceMergeOption != null) {
            return resourceMergeOption;
        }
        ResourceMergeOption resourceMergeOption2 = new ResourceMergeOption();
        this.mMergeOption = resourceMergeOption2;
        return resourceMergeOption2;
    }

    public Map<Integer, Integer> getResourceIdMap() {
        return this.resourceIdMap;
    }

    public ApkModule getResultModule() {
        return this.resultModule;
    }

    public TableBlock getResultTable() {
        return getResultModule().getTableBlock();
    }

    public TableBlock getSourceTable() {
        return this.sourceTable;
    }

    public void rebuild() {
        if (this.mRebuilt) {
            return;
        }
        this.mRebuilt = true;
        this.resultModule = new ApkModule();
        TableBlock tableBlock = new TableBlock();
        this.resultModule.setTableBlock(tableBlock);
        initializeTable();
        mergePackages();
        tableBlock.refreshFull();
    }

    public void rebuildManifest(ApkModule apkModule) {
        TableBlock resultTable;
        AndroidManifestBlock androidManifest = apkModule.getAndroidManifest();
        if (androidManifest == null || (resultTable = getResultTable()) == null) {
            return;
        }
        PackageBlock packageBlock = androidManifest.getPackageBlock();
        if (packageBlock.getTableBlock() == resultTable) {
            return;
        }
        AndroidManifestBlock androidManifestBlock = new AndroidManifestBlock();
        androidManifestBlock.setPackageBlock(resultTable.pickOne(packageBlock.getId()));
        androidManifestBlock.mergeWithName(getMergeOption(), androidManifest);
        apkModule.setManifest(androidManifestBlock);
        apkModule.keepManifestChanges();
    }

    public void rebuildTo(ApkModule apkModule) {
        rebuild();
        rebuildManifest(apkModule);
        transferResFiles(apkModule);
        transferTableBlock(apkModule);
    }

    public void setMergeOption(ResourceMergeOption resourceMergeOption) {
        this.mMergeOption = resourceMergeOption;
    }

    public ResourceBuilder(TableBlock tableBlock) {
        this(new ResourceMergeOption(), tableBlock);
    }

    private void initializeEntries(PackageBlock packageBlock, PackageBlock packageBlock2) {
        Iterator it = packageBlock2.getTypeStringPool().iterator();
        while (it.hasNext()) {
            initializeEntries(((TypeString) it.next()).get(), packageBlock, packageBlock2);
        }
    }
}
