package com.reandroid.graph;

import com.reandroid.apk.ApkModule;
import com.reandroid.apk.ResFile;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.model.ResourceName;
import com.reandroid.arsc.value.AttributeValue;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResTableMapEntry;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.Value;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.graph.RequiredEntriesScanner;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.FilterIterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RequiredEntriesScanner extends BaseApkModuleProcessor {
    private final ApkBuildOption buildOption;
    private final Set<String> processedFiles;
    private final Set<Integer> processedNumbers;
    private final Set<String> requiredFiles;
    private final Set<ResourceName> requiredResources;

    public RequiredEntriesScanner(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkModule, dexClassRepository);
        this.buildOption = apkBuildOption;
        this.requiredResources = new HashSet();
        this.requiredFiles = new HashSet();
        this.processedFiles = new HashSet();
        this.processedNumbers = new HashSet();
    }

    public static /* synthetic */ boolean a(Predicate predicate, ResourceEntry resourceEntry) {
        ResourceName resourceName = resourceEntry.toResourceName();
        return resourceName != null && predicate.test(resourceName);
    }

    private void add(ResourceEntry resourceEntry) {
        ResourceName resourceName;
        if (resourceEntry == null || !resourceEntry.isContext((Block) getTableBlock()) || (resourceName = resourceEntry.toResourceName()) == null || !this.requiredResources.add(resourceName)) {
            return;
        }
        Iterator<Entry> it = resourceEntry.iterator(true);
        while (it.hasNext()) {
            addEntry(it.next());
        }
    }

    private void addAttribute(AttributeValue attributeValue) {
        if (attributeValue == null) {
            return;
        }
        add(attributeValue.resolveName());
        addValue(attributeValue);
    }

    private void addComplex(ResTableMapEntry resTableMapEntry) {
        if (resTableMapEntry == null) {
            return;
        }
        add(getLocalResource(resTableMapEntry.getParentId()));
        Iterator<ResValueMap> it = resTableMapEntry.iterator();
        while (it.hasNext()) {
            addAttribute(it.next());
        }
    }

    private void addEntry(Entry entry) {
        if (entry == null || entry.isNull()) {
            return;
        }
        if (entry.isScalar()) {
            addValue(entry.getResValue());
        } else if (entry.isComplex()) {
            addComplex(entry.getResTableMapEntry());
        }
    }

    private void addValue(Value value) {
        if (value == null) {
            return;
        }
        String valueAsString = value.getValueAsString();
        if (valueAsString != null) {
            scanIdOnResXml(valueAsString);
        } else {
            add(value.getValueAsReference());
        }
    }

    public static /* synthetic */ ResourceEntry c(RequiredEntriesScanner requiredEntriesScanner, IntegerReference integerReference) {
        requiredEntriesScanner.getClass();
        return requiredEntriesScanner.getLocalResource(integerReference.get());
    }

    private TableBlock getTableBlock() {
        return getApkModule().getTableBlock();
    }

    private void scanIdOnDexClasses() {
        Iterator itOf = ComputeIterator.of(getClassRepository().visitIntegers(), new Function() { // from class: afc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return RequiredEntriesScanner.c(this.b, (IntegerReference) obj);
            }
        });
        while (itOf.hasNext()) {
            add((ResourceEntry) itOf.next());
        }
    }

    private void scanIdOnResXml(String str) {
        ResFile resFile;
        if (str == null || !this.processedFiles.add(str) || (resFile = getApkModule().getResFile(str)) == null) {
            return;
        }
        this.requiredFiles.add(str);
        scanIdOnXml(resFile.getResXmlDocument());
    }

    private void scanIdOnXml(ResXmlDocument resXmlDocument) {
        if (resXmlDocument != null) {
            Iterator itRecursiveAttributes = resXmlDocument.recursiveAttributes();
            while (itRecursiveAttributes.hasNext()) {
                addAttribute((AttributeValue) itRecursiveAttributes.next());
            }
        }
    }

    private void scanUserConfigs() {
        final Predicate<? super ResourceName> keepResourceName = this.buildOption.getResourceMergeOption().getKeepResourceName();
        if (keepResourceName == null) {
            return;
        }
        Iterator itOf = FilterIterator.of(getTableBlock().getResources(), new Predicate() { // from class: zec
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RequiredEntriesScanner.a(keepResourceName, (ResourceEntry) obj);
            }
        });
        while (itOf.hasNext()) {
            add((ResourceEntry) itOf.next());
        }
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        scanUserConfigs();
        scanIdOnXml(getApkModule().getAndroidManifest());
        scanIdOnDexClasses();
        reset();
    }

    public ResourceEntry getLocalResource(int i) {
        Block tableBlock;
        ResourceEntry localResource;
        if (PackageBlock.isResourceId(i) && this.processedNumbers.add(Integer.valueOf(i)) && (localResource = (tableBlock = getTableBlock()).getLocalResource(i)) != null && localResource.isContext(tableBlock)) {
            return localResource;
        }
        return null;
    }

    public Set<String> getRequiredFiles() {
        return this.requiredFiles;
    }

    public Set<ResourceName> getRequiredResources() {
        return this.requiredResources;
    }

    public void reset() {
        this.processedFiles.clear();
        this.processedNumbers.clear();
    }
}
