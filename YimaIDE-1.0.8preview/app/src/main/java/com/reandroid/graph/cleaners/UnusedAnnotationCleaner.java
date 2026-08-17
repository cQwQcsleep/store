package com.reandroid.graph.cleaners;

import com.reandroid.apk.ApkModule;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.model.DexInstruction;
import com.reandroid.graph.ApkBuildOption;
import com.reandroid.utils.collection.FilterIterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnusedAnnotationCleaner extends UnusedCleaner<DexClass> {
    public UnusedAnnotationCleaner(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkBuildOption, apkModule, dexClassRepository);
    }

    private void cleanAnnotations(Set<TypeKey> set) {
        DexClassRepository classRepository = getClassRepository();
        Iterator<TypeKey> it = set.iterator();
        while (it.hasNext()) {
            if (classRepository.removeAnnotations(it.next())) {
                addCount();
            }
        }
    }

    private Set<TypeKey> findUnusedAnnotations() {
        verbose("Searching ...");
        HashSet hashSet = new HashSet();
        Iterator<DexClass> targetAnnotations = getTargetAnnotations();
        while (targetAnnotations.hasNext()) {
            hashSet.add(targetAnnotations.next().getKey());
        }
        subtractUnused(hashSet);
        logUnused(hashSet);
        verbose("Unused annotations: " + hashSet.size());
        return hashSet;
    }

    private Iterator<DexClass> getTargetAnnotations() {
        return FilterIterator.of(getClassRepository().getDexClasses(), new Predicate() { // from class: z0f
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DexClass) obj).isAnnotation();
            }
        });
    }

    private void logUnused(Set<TypeKey> set) {
        if (isDebugEnabled()) {
            Iterator<TypeKey> it = set.iterator();
            while (it.hasNext()) {
                debug(it.next().getTypeName());
            }
        }
    }

    private void subtractUnused(Set<TypeKey> set) {
        Iterator<DexClass> dexClasses = getClassRepository().getDexClasses();
        while (!set.isEmpty() && dexClasses.hasNext()) {
            Iterator<DexInstruction> dexInstructions = dexClasses.next().getDexInstructions();
            while (dexInstructions.hasNext()) {
                subtractUnused(set, dexInstructions.next());
            }
        }
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        if (isEnabled()) {
            cleanAnnotations(findUnusedAnnotations());
        } else {
            debug("Skip");
        }
    }

    @Override // com.reandroid.graph.cleaners.UnusedCleaner
    public boolean isEnabled() {
        return getBuildOption().isCleanAnnotations();
    }

    private void subtractUnused(Set<TypeKey> set, DexInstruction dexInstruction) {
        Key key = dexInstruction.getKey();
        if (key != null) {
            Iterator<? extends Key> itMentionedKeys = key.mentionedKeys();
            while (itMentionedKeys.hasNext()) {
                set.remove(itMentionedKeys.next().getDeclaring());
            }
        }
    }
}
