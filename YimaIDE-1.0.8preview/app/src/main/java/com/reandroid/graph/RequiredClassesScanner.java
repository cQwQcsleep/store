package com.reandroid.graph;

import com.reandroid.apk.ApkModule;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.graph.RequiredClassesScanner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RequiredClassesScanner extends BaseApkModuleProcessor {
    private boolean lookInStrings;
    private final Set<TypeKey> requiredTypes;
    private final VitalClassesSet vitalClassesSet;

    public RequiredClassesScanner(VitalClassesSet vitalClassesSet, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkModule, dexClassRepository);
        this.lookInStrings = true;
        this.vitalClassesSet = vitalClassesSet;
        this.requiredTypes = new HashSet();
    }

    public static /* synthetic */ boolean a(Set set, TypeKey typeKey) {
        return !set.contains(typeKey);
    }

    private void addUsed(DexClass dexClass) {
        if (dexClass == null) {
            return;
        }
        TypeKey key = dexClass.getKey();
        final Set<TypeKey> set = this.requiredTypes;
        if (set.contains(key)) {
            return;
        }
        Set<DexClass> required = dexClass.getRequired(new Predicate() { // from class: xec
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RequiredClassesScanner.a(set, (TypeKey) obj);
            }
        });
        set.add(key);
        Iterator<DexClass> it = required.iterator();
        while (it.hasNext()) {
            set.add(it.next().getKey());
        }
    }

    private void addVitalClasses() {
        VitalClassesSet vitalClassesSet = this.vitalClassesSet;
        vitalClassesSet.apply();
        DexClassRepository classRepository = getClassRepository();
        Iterator<TypeKey> mainClasses = vitalClassesSet.getMainClasses();
        while (mainClasses.hasNext()) {
            addUsed(classRepository.getDexClass(mainClasses.next()));
        }
    }

    private boolean keptAll() {
        return this.requiredTypes.size() == getClassRepository().getDexClassesCount();
    }

    private void scanOnDexStrings() {
        if (keptAll()) {
            return;
        }
        debug("Searching on dex strings ...");
        final VitalClassesSet vitalClassesSet = this.vitalClassesSet;
        vitalClassesSet.updateSourceStrings();
        Iterator<DexClass> dexClasses = getClassRepository().getDexClasses(new Predicate() { // from class: yec
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return vitalClassesSet.containsSourceString((TypeKey) obj);
            }
        });
        while (dexClasses.hasNext()) {
            DexClass next = dexClasses.next();
            addUsed(next);
            debug(next.getKey().getSourceName());
        }
    }

    private void scanOnStrings() {
        if (this.lookInStrings) {
            scanOnDexStrings();
        }
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        debug("Scanning required classes ...");
        addVitalClasses();
        scanOnStrings();
    }

    public Set<TypeKey> getResults() {
        return this.requiredTypes;
    }

    public void reset() {
        this.requiredTypes.clear();
    }

    public void setLookInStrings(boolean z) {
        this.lookInStrings = z;
    }
}
