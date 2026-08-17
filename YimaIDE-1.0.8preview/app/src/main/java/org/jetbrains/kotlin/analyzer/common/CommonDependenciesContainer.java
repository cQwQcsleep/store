package org.jetbrains.kotlin.analyzer.common;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\u0004H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0006R\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/common/CommonDependenciesContainer;", "", "moduleInfos", "", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "getModuleInfos", "()Ljava/util/List;", "moduleDescriptorForModuleInfo", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "moduleInfo", "registerDependencyForAllModules", "", "descriptorForModule", "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "packageFragmentProviderForModuleInfo", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "friendModuleInfos", "getFriendModuleInfos", "refinesModuleInfos", "getRefinesModuleInfos", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface CommonDependenciesContainer {
    List<ModuleInfo> getFriendModuleInfos();

    List<ModuleInfo> getModuleInfos();

    List<ModuleInfo> getRefinesModuleInfos();

    ModuleDescriptor moduleDescriptorForModuleInfo(ModuleInfo moduleInfo);

    PackageFragmentProvider packageFragmentProviderForModuleInfo(ModuleInfo moduleInfo);

    void registerDependencyForAllModules(ModuleInfo moduleInfo, ModuleDescriptorImpl descriptorForModule);
}
