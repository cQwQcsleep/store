package org.jetbrains.kotlin.library.metadata;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u000b\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/KotlinResolvedModuleDescriptors;", "", "resolvedDescriptors", "", "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "forwardDeclarationsModule", "friendModules", "", "refinesModules", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;Ljava/util/Set;Ljava/util/Set;)V", "getResolvedDescriptors", "()Ljava/util/List;", "getForwardDeclarationsModule", "()Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "getFriendModules", "()Ljava/util/Set;", "getRefinesModules", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class KotlinResolvedModuleDescriptors {
    private final ModuleDescriptorImpl forwardDeclarationsModule;
    private final Set<ModuleDescriptorImpl> friendModules;
    private final Set<ModuleDescriptorImpl> refinesModules;
    private final List<ModuleDescriptorImpl> resolvedDescriptors;

    public KotlinResolvedModuleDescriptors(List<ModuleDescriptorImpl> list, ModuleDescriptorImpl moduleDescriptorImpl, Set<ModuleDescriptorImpl> set, Set<ModuleDescriptorImpl> set2) {
        list.getClass();
        moduleDescriptorImpl.getClass();
        set.getClass();
        set2.getClass();
        this.resolvedDescriptors = list;
        this.forwardDeclarationsModule = moduleDescriptorImpl;
        this.friendModules = set;
        this.refinesModules = set2;
    }

    public final ModuleDescriptorImpl getForwardDeclarationsModule() {
        return this.forwardDeclarationsModule;
    }

    public final Set<ModuleDescriptorImpl> getFriendModules() {
        return this.friendModules;
    }

    public final Set<ModuleDescriptorImpl> getRefinesModules() {
        return this.refinesModules;
    }

    public final List<ModuleDescriptorImpl> getResolvedDescriptors() {
        return this.resolvedDescriptors;
    }
}
