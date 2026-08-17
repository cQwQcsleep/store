package com.reandroid.graph;

import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.collection.CollectionUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ApkBuildOption {
    private Predicate<? super TypeKey> keepClassesFilter;
    private ResourceMergeOption mMergeOption;
    private boolean minifyResources = true;
    private boolean minifyClasses = true;
    private boolean minifyFields = true;
    private boolean minifyMethods = false;
    private boolean cleanAnnotations = true;
    private boolean processClassNamesOnStrings = true;
    private final Set<TypeKey> keepClassesList = new HashSet();

    public void addKeepClasses(TypeKey typeKey) {
        this.keepClassesList.add(typeKey.getDeclaring());
    }

    public void clearKeepClasses() {
        this.keepClassesList.clear();
    }

    public Predicate<? super TypeKey> getKeepClasses() {
        return CollectionUtil.orFilter(getKeepClassesFilter(), getKeepClassesListFilter());
    }

    public Predicate<? super TypeKey> getKeepClassesFilter() {
        return this.keepClassesFilter;
    }

    public Predicate<? super TypeKey> getKeepClassesListFilter() {
        final Set<TypeKey> set = this.keepClassesList;
        if (set.isEmpty()) {
            return null;
        }
        return new Predicate() { // from class: ua0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return set.contains((TypeKey) obj);
            }
        };
    }

    public ResourceMergeOption getResourceMergeOption() {
        ResourceMergeOption resourceMergeOption = this.mMergeOption;
        if (resourceMergeOption != null) {
            return resourceMergeOption;
        }
        ResourceMergeOption resourceMergeOption2 = new ResourceMergeOption();
        this.mMergeOption = resourceMergeOption2;
        return resourceMergeOption2;
    }

    public boolean isCleanAnnotations() {
        return this.cleanAnnotations;
    }

    public boolean isMinifyClasses() {
        return this.minifyClasses;
    }

    public boolean isMinifyFields() {
        return this.minifyFields;
    }

    public boolean isMinifyMethods() {
        return this.minifyMethods;
    }

    public boolean isMinifyResources() {
        return this.minifyResources;
    }

    public boolean isProcessClassNamesOnStrings() {
        return this.processClassNamesOnStrings;
    }

    public void readKeepClassesList(File file) throws IOException {
        SmaliReader smaliReaderOf = SmaliReader.of(file);
        Set<TypeKey> set = this.keepClassesList;
        while (!smaliReaderOf.finished()) {
            set.add(TypeKey.read(smaliReaderOf).getDeclaring());
            smaliReaderOf.skipWhitespacesOrComment();
        }
    }

    public void readKeepResourceNameList(File file) throws IOException {
        getResourceMergeOption().readKeepResourceNameList(file);
    }

    public void setCleanAnnotations(boolean z) {
        this.cleanAnnotations = z;
    }

    public void setKeepClassesFilter(Predicate<? super TypeKey> predicate) {
        this.keepClassesFilter = predicate;
    }

    public void setMinifyClasses(boolean z) {
        this.minifyClasses = z;
    }

    public void setMinifyFields(boolean z) {
        this.minifyFields = z;
    }

    public void setMinifyMethods(boolean z) {
        this.minifyMethods = z;
    }

    public void setMinifyResources(boolean z) {
        this.minifyResources = z;
    }

    public void setProcessClassNamesOnStrings(boolean z) {
        this.processClassNamesOnStrings = z;
    }

    public void setResourceMergeOption(ResourceMergeOption resourceMergeOption) {
        this.mMergeOption = resourceMergeOption;
    }
}
