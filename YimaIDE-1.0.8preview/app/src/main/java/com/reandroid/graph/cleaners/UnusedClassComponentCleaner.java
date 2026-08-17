package com.reandroid.graph.cleaners;

import com.reandroid.apk.ApkModule;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.Dex;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.model.DexDeclaration;
import com.reandroid.graph.ApkBuildOption;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class UnusedClassComponentCleaner<T extends Dex> extends UnusedCleaner<T> {
    public UnusedClassComponentCleaner(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkBuildOption, apkModule, dexClassRepository);
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        if (!isEnabled()) {
            debug("Skip");
            return;
        }
        verbose("Searching for unused ...");
        cleanUnusedInCleanableClasses();
        verbose("Cleaned: " + getCount());
    }

    public void cleanUnusedInClass(DexClass dexClass) {
        List<T> listListUnusedInClass = listUnusedInClass(dexClass);
        if (listListUnusedInClass != null) {
            boolean zIsDebugEnabled = isDebugEnabled();
            for (T t : listListUnusedInClass) {
                if (zIsDebugEnabled) {
                    debug(getDebugString(t));
                }
                t.removeSelf();
                addCount();
            }
        }
    }

    public void cleanUnusedInCleanableClasses() {
        Iterator<DexClass> cleanableClasses = getCleanableClasses();
        while (cleanableClasses.hasNext()) {
            cleanUnusedInClass(cleanableClasses.next());
        }
    }

    public Iterator<DexClass> getCleanableClasses() {
        return getDexClasses(new Predicate() { // from class: a1f
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.isCleanableClass((DexClass) obj);
            }
        });
    }

    public String getDebugString(T t) {
        return t instanceof DexDeclaration ? ((DexDeclaration) t).getKey().toString() : t.toSmaliString();
    }

    public boolean isCleanableClass(DexClass dexClass) {
        if (dexClass.usesNative() || dexClass.isEnum()) {
            return false;
        }
        Predicate<? super TypeKey> keepClasses = getBuildOption().getKeepClasses();
        return keepClasses == null || !keepClasses.test(dexClass.getKey());
    }

    public abstract List<T> listUnusedInClass(DexClass dexClass);
}
