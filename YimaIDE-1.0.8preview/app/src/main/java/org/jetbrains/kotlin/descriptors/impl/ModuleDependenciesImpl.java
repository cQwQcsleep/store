package org.jetbrains.kotlin.descriptors.impl;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u000b\u0018\u00002\u00020\u0001B?\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/ModuleDependenciesImpl;", "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDependencies;", "allDependencies", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "modulesWhoseInternalsAreVisible", Argument.Delimiters.none, "directExpectedByDependencies", "allExpectedByDependencies", "<init>", "(Ljava/util/List;Ljava/util/Set;Ljava/util/List;Ljava/util/Set;)V", "getAllDependencies", "()Ljava/util/List;", "getModulesWhoseInternalsAreVisible", "()Ljava/util/Set;", "getDirectExpectedByDependencies", "getAllExpectedByDependencies", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleDependenciesImpl implements ModuleDependencies {
    private final List<ModuleDescriptorImpl> allDependencies;
    private final Set<ModuleDescriptorImpl> allExpectedByDependencies;
    private final List<ModuleDescriptorImpl> directExpectedByDependencies;
    private final Set<ModuleDescriptorImpl> modulesWhoseInternalsAreVisible;

    public ModuleDependenciesImpl(List<ModuleDescriptorImpl> list, Set<ModuleDescriptorImpl> set, List<ModuleDescriptorImpl> list2, Set<ModuleDescriptorImpl> set2) {
        list.getClass();
        set.getClass();
        list2.getClass();
        set2.getClass();
        this.allDependencies = list;
        this.modulesWhoseInternalsAreVisible = set;
        this.directExpectedByDependencies = list2;
        this.allExpectedByDependencies = set2;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.ModuleDependencies
    public List<ModuleDescriptorImpl> getAllDependencies() {
        return this.allDependencies;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.ModuleDependencies
    public Set<ModuleDescriptorImpl> getAllExpectedByDependencies() {
        return this.allExpectedByDependencies;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.ModuleDependencies
    public List<ModuleDescriptorImpl> getDirectExpectedByDependencies() {
        return this.directExpectedByDependencies;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.ModuleDependencies
    public Set<ModuleDescriptorImpl> getModulesWhoseInternalsAreVisible() {
        return this.modulesWhoseInternalsAreVisible;
    }
}
