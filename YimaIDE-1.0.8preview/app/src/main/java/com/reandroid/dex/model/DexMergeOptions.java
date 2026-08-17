package com.reandroid.dex.model;

import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.DexLayoutBlock;
import com.reandroid.dex.sections.MergeOptions;
import com.reandroid.dex.sections.SectionList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexMergeOptions implements MergeOptions {
    private int mergeStartDexFile;
    private final Set<TypeKey> mergedSet;
    private final boolean relocate;

    public DexMergeOptions(boolean z) {
        this.relocate = z;
        this.mergedSet = new HashSet();
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public int getMergeStartDexFile() {
        return this.mergeStartDexFile;
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public DexLayoutBlock onCreateNext(DexLayoutBlock dexLayoutBlock) {
        DexDirectory dexDirectory;
        DexLayout dexLayoutFindDexFile = DexLayout.findDexFile(dexLayoutBlock);
        if (dexLayoutFindDexFile == null || (dexDirectory = dexLayoutFindDexFile.getDexFile().getDexDirectory()) == null || dexDirectory.getLast() != dexLayoutFindDexFile.getDexFile()) {
            return null;
        }
        DexFile dexFileCreateDefault = dexDirectory.createDefault();
        setMergeStartDexFile(dexFileCreateDefault.getIndex());
        return dexFileCreateDefault.getFirst().getDexLayoutBlock();
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public void onDexFull(DexLayoutBlock dexLayoutBlock, ClassId classId) {
        DexLayout dexLayoutFindDexFile;
        DexDirectory dexDirectory;
        DexLayout dexLayoutFindDexFile2 = DexLayout.findDexFile(classId);
        if (dexLayoutFindDexFile2 == null || (dexLayoutFindDexFile = DexLayout.findDexFile(dexLayoutBlock)) == null || (dexDirectory = dexLayoutFindDexFile.getDexFile().getDexDirectory()) == null || dexDirectory == dexLayoutFindDexFile2.getDexFile().getDexDirectory()) {
            return;
        }
        onCreateNext(dexLayoutBlock);
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public void onDuplicate(ClassId classId) {
        if (this.relocate) {
            classId.removeSelf();
        }
        this.mergedSet.add(classId.getKey());
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public void onMergeError(DexLayoutBlock dexLayoutBlock, ClassId classId, String str) {
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public void onMergeError(DexLayoutBlock dexLayoutBlock, SectionList sectionList, String str) {
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public void onMergeSuccess(ClassId classId, TypeKey typeKey) {
        if (this.relocate) {
            return;
        }
        this.mergedSet.add(typeKey);
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public boolean relocateClass() {
        return this.relocate;
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public void setMergeStartDexFile(int i) {
        this.mergeStartDexFile = i;
    }

    @Override // com.reandroid.dex.sections.MergeOptions
    public boolean skipMerging(ClassId classId, TypeKey typeKey) {
        return this.mergedSet.contains(typeKey);
    }

    public DexMergeOptions() {
        this(true);
    }
}
