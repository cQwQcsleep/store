package com.reandroid.dex.sections;

import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.TypeKey;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MergeOptions {
    public static final MergeOptions DEFAULT = new MergeOptions() { // from class: com.reandroid.dex.sections.MergeOptions.1
        @Override // com.reandroid.dex.sections.MergeOptions
        public DexLayoutBlock onCreateNext(DexLayoutBlock dexLayoutBlock) {
            return null;
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public void onDexFull(DexLayoutBlock dexLayoutBlock, ClassId classId) {
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public void onDuplicate(ClassId classId) {
            classId.removeSelf();
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public void onMergeError(DexLayoutBlock dexLayoutBlock, ClassId classId, String str) {
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public void onMergeError(DexLayoutBlock dexLayoutBlock, SectionList sectionList, String str) {
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public void onMergeSuccess(ClassId classId, TypeKey typeKey) {
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public boolean relocateClass() {
            return true;
        }

        @Override // com.reandroid.dex.sections.MergeOptions
        public boolean skipMerging(ClassId classId, TypeKey typeKey) {
            return false;
        }
    };

    default int getMergeStartDexFile() {
        return 0;
    }

    default boolean isEmptyDexFile(DexLayoutBlock dexLayoutBlock) {
        if (dexLayoutBlock != null && !dexLayoutBlock.isEmpty()) {
            for (ClassId classId : dexLayoutBlock.getSection(SectionType.CLASS_ID)) {
                if (!skipMerging(classId, classId.getKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    DexLayoutBlock onCreateNext(DexLayoutBlock dexLayoutBlock);

    void onDexFull(DexLayoutBlock dexLayoutBlock, ClassId classId);

    void onDuplicate(ClassId classId);

    void onMergeError(DexLayoutBlock dexLayoutBlock, ClassId classId, String str);

    void onMergeError(DexLayoutBlock dexLayoutBlock, SectionList sectionList, String str);

    void onMergeSuccess(ClassId classId, TypeKey typeKey);

    boolean relocateClass();

    default void setMergeStartDexFile(int i) {
    }

    boolean skipMerging(ClassId classId, TypeKey typeKey);

    default boolean isEmptyDexFile(DexContainerBlock dexContainerBlock) {
        if (dexContainerBlock != null && !dexContainerBlock.isEmpty()) {
            Iterator<DexLayoutBlock> it = dexContainerBlock.iterator();
            while (it.hasNext()) {
                if (!isEmptyDexFile(it.next())) {
                    return false;
                }
            }
        }
        return true;
    }
}
