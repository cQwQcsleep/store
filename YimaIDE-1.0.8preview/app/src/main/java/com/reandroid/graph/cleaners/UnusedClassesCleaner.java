package com.reandroid.graph.cleaners;

import com.reandroid.apk.ApkModule;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.graph.ApkBuildOption;
import com.reandroid.graph.RequiredClassesScanner;
import com.reandroid.graph.VitalClassesSet;
import com.reandroid.graph.cleaners.UnusedClassesCleaner;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnusedClassesCleaner extends UnusedCleaner<DexClass> {
    private static final int MAXIMUM_CYCLE = 25;
    private VitalClassesSet vitalClassesSet;

    public UnusedClassesCleaner(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkBuildOption, apkModule, dexClassRepository);
    }

    public static /* synthetic */ boolean a(Set set, TypeKey typeKey) {
        return !set.contains(typeKey);
    }

    public static /* synthetic */ boolean c(Set set, TypeKey typeKey) {
        return !set.contains(typeKey);
    }

    private void cleanCyclic() {
        int i = 0;
        int iCleanUnusedClasses = 1;
        int i2 = 0;
        while (i < 25 && iCleanUnusedClasses > 0) {
            i++;
            Set<TypeKey> setScanRequiredClasses = scanRequiredClasses();
            debugReportClassesToRemove(setScanRequiredClasses);
            iCleanUnusedClasses = cleanUnusedClasses(setScanRequiredClasses);
            i2 += iCleanUnusedClasses;
            verbose("Cycle: " + i + ", removed: " + iCleanUnusedClasses + ", total: " + i2);
        }
        setCount(i2);
    }

    private int cleanUnusedClasses(final Set<TypeKey> set) {
        DexClassRepository classRepository = getClassRepository();
        int dexClassesCount = classRepository.getDexClassesCount();
        classRepository.removeClassesWithKeys(new Predicate() { // from class: c1f
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return UnusedClassesCleaner.a(set, (TypeKey) obj);
            }
        });
        int dexClassesCount2 = dexClassesCount - classRepository.getDexClassesCount();
        setCount(dexClassesCount2);
        if (dexClassesCount2 != 0) {
            classRepository.shrink();
        }
        return dexClassesCount2;
    }

    private void debugReportClassesToRemove(final Set<TypeKey> set) {
        if (isDebugEnabled()) {
            Iterator<DexClass> dexClasses = getClassRepository().getDexClasses(new Predicate() { // from class: b1f
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return UnusedClassesCleaner.c(set, (TypeKey) obj);
                }
            });
            while (dexClasses.hasNext()) {
                debug(dexClasses.next().getKey().toString());
            }
        }
    }

    private Set<TypeKey> scanRequiredClasses() {
        RequiredClassesScanner requiredClassesScanner = new RequiredClassesScanner(this.vitalClassesSet, getApkModule(), getClassRepository());
        requiredClassesScanner.setReporter(getReporter());
        requiredClassesScanner.setLookInStrings(getBuildOption().isProcessClassNamesOnStrings());
        requiredClassesScanner.apply();
        return requiredClassesScanner.getResults();
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        if (!isEnabled()) {
            debug("Skip");
        } else {
            getVitalClassesSet().setReporter(getReporter()).apply();
            cleanCyclic();
        }
    }

    public VitalClassesSet getVitalClassesSet() {
        VitalClassesSet vitalClassesSet = this.vitalClassesSet;
        if (vitalClassesSet != null) {
            return vitalClassesSet;
        }
        VitalClassesSet vitalClassesSet2 = new VitalClassesSet(getBuildOption(), getApkModule(), getClassRepository());
        this.vitalClassesSet = vitalClassesSet2;
        return vitalClassesSet2;
    }

    @Override // com.reandroid.graph.cleaners.UnusedCleaner
    public boolean isEnabled() {
        return getBuildOption().isMinifyClasses();
    }

    public void setVitalClassesSet(VitalClassesSet vitalClassesSet) {
        this.vitalClassesSet = vitalClassesSet;
    }
}
