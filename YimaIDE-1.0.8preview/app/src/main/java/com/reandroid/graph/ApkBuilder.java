package com.reandroid.graph;

import com.reandroid.apk.ApkModule;
import com.reandroid.apk.ResFile;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.model.ResourceName;
import com.reandroid.arsc.refactor.ResourceBuilder;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.graph.cleaners.UnusedAnnotationCleaner;
import com.reandroid.graph.cleaners.UnusedClassesCleaner;
import com.reandroid.graph.cleaners.UnusedFieldsCleaner;
import com.reandroid.graph.cleaners.UnusedMethodsCleaner;
import com.reandroid.utils.collection.CollectionUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ApkBuilder extends BaseApkModuleProcessor {
    private static final int MAX_CYCLE = 25;
    private ApkBuildOption buildOption;
    private VitalClassesSet vitalClassesSet;

    public ApkBuilder(ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkModule, dexClassRepository);
    }

    private void cleanDex() {
        int i = 0;
        int iCyclicCleanDex = 1;
        int i2 = 0;
        while (i < 25 && iCyclicCleanDex > 0) {
            i++;
            verbose("Stage " + i);
            iCyclicCleanDex = cyclicCleanDex();
            i2 += iCyclicCleanDex;
        }
        verbose("Cleaned dex: " + i2);
    }

    private void cleanUnusedResFiles() {
        if (getBuildOption().isMinifyResources()) {
            ApkModule apkModule = getApkModule();
            RequiredEntriesScanner requiredEntriesScanner = new RequiredEntriesScanner(getBuildOption(), apkModule, getClassRepository());
            requiredEntriesScanner.apply();
            Set<String> requiredFiles = requiredEntriesScanner.getRequiredFiles();
            List listListResFiles = apkModule.listResFiles();
            ZipEntryMap zipEntryMap = apkModule.getZipEntryMap();
            Iterator it = listListResFiles.iterator();
            while (it.hasNext()) {
                String filePath = ((ResFile) it.next()).getFilePath();
                if (!requiredFiles.contains(filePath)) {
                    zipEntryMap.remove(filePath);
                }
            }
        }
    }

    private int cyclicCleanDex() {
        UnusedFieldsCleaner unusedFieldsCleaner = new UnusedFieldsCleaner(getBuildOption(), getApkModule(), getClassRepository());
        unusedFieldsCleaner.setReporter(getReporter());
        unusedFieldsCleaner.apply();
        int count = unusedFieldsCleaner.getCount();
        UnusedMethodsCleaner unusedMethodsCleaner = new UnusedMethodsCleaner(getBuildOption(), getApkModule(), getClassRepository());
        unusedMethodsCleaner.setReporter(getReporter());
        unusedMethodsCleaner.apply();
        int count2 = count + unusedMethodsCleaner.getCount();
        UnusedAnnotationCleaner unusedAnnotationCleaner = new UnusedAnnotationCleaner(getBuildOption(), getApkModule(), getClassRepository());
        unusedAnnotationCleaner.setReporter(getReporter());
        unusedAnnotationCleaner.apply();
        int count3 = count2 + unusedAnnotationCleaner.getCount();
        UnusedClassesCleaner unusedClassesCleaner = new UnusedClassesCleaner(getBuildOption(), getApkModule(), getClassRepository());
        unusedClassesCleaner.setReporter(getReporter());
        unusedClassesCleaner.setVitalClassesSet(this.vitalClassesSet);
        unusedClassesCleaner.apply();
        this.vitalClassesSet = unusedClassesCleaner.getVitalClassesSet();
        return count3 + unusedClassesCleaner.getCount();
    }

    private void initializeRequiredIds() {
        ResourceMergeOption resourceMergeOption = getBuildOption().getResourceMergeOption();
        if (!getBuildOption().isMinifyResources()) {
            resourceMergeOption.setKeepEntries(CollectionUtil.getAcceptAll());
            return;
        }
        RequiredEntriesScanner requiredEntriesScanner = new RequiredEntriesScanner(getBuildOption(), getApkModule(), getClassRepository());
        requiredEntriesScanner.setReporter(getReporter());
        requiredEntriesScanner.apply();
        final Set<ResourceName> requiredResources = requiredEntriesScanner.getRequiredResources();
        resourceMergeOption.setKeepEntries(new Predicate() { // from class: va0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return requiredResources.contains(((ResourceEntry) obj).toResourceName());
            }
        });
    }

    private void removeResFiles() {
        if (getBuildOption().isMinifyResources()) {
            ApkModule apkModule = getApkModule();
            List listListResFiles = apkModule.listResFiles();
            ZipEntryMap zipEntryMap = apkModule.getZipEntryMap();
            Iterator it = listListResFiles.iterator();
            while (it.hasNext()) {
                zipEntryMap.remove(((ResFile) it.next()).getInputSource());
            }
        }
    }

    private void resolveInlineIntegerFieldCalls() {
        if (getBuildOption().isMinifyResources()) {
            InlineFieldIntResolver inlineFieldIntResolver = new InlineFieldIntResolver(getClassRepository(), getApkModule().getTableBlock());
            inlineFieldIntResolver.setReporter(getReporter());
            inlineFieldIntResolver.apply();
        }
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        int size = getApkModule().getZipEntryMap().size();
        resolveInlineIntegerFieldCalls();
        cleanDex();
        ResourceBuilder resourceBuilder = new ResourceBuilder(getBuildOption().getResourceMergeOption(), getApkModule().getTableBlock());
        cleanUnusedResFiles();
        initializeRequiredIds();
        resourceBuilder.rebuild();
        ApkModule apkModule = getApkModule();
        resourceBuilder.rebuildManifest(apkModule);
        resourceBuilder.applyIdChanges(getClassRepository().visitIntegers());
        ApkModule resultModule = resourceBuilder.getResultModule();
        removeResFiles();
        apkModule.setTableBlock(resultModule.getTableBlock());
        apkModule.keepTableBlockChanges();
        resultModule.setTableBlock((TableBlock) null);
        apkModule.getZipEntryMap().addAll(resultModule.getZipEntryMap());
        verbose("Removed files: " + (size - apkModule.getZipEntryMap().size()));
    }

    public ApkBuildOption getBuildOption() {
        ApkBuildOption apkBuildOption = this.buildOption;
        if (apkBuildOption != null) {
            return apkBuildOption;
        }
        ApkBuildOption apkBuildOption2 = new ApkBuildOption();
        this.buildOption = apkBuildOption2;
        return apkBuildOption2;
    }

    public void setBuildOption(ApkBuildOption apkBuildOption) {
        this.buildOption = apkBuildOption;
    }
}
