package org.jetbrains.kotlin.context;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J!\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\t\"\u00020\u0003H\u0016¢\u0006\u0002\u0010\nJ\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/context/MutableModuleContext;", "Lorg/jetbrains/kotlin/context/ModuleContext;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "getModule", "()Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "setDependencies", Argument.Delimiters.none, "dependencies", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;)V", Argument.Delimiters.none, "initializeModuleContents", "packageFragmentProvider", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface MutableModuleContext extends ModuleContext {
    @Override // org.jetbrains.kotlin.context.ModuleContext
    ModuleDescriptorImpl getModule();

    default void initializeModuleContents(PackageFragmentProvider packageFragmentProvider) {
        packageFragmentProvider.getClass();
        getModule().initialize(packageFragmentProvider);
    }

    default void setDependencies(ModuleDescriptorImpl... dependencies) {
        dependencies.getClass();
        getModule().setDependencies((ModuleDescriptorImpl[]) Arrays.copyOf(dependencies, dependencies.length));
    }

    default void setDependencies(List<ModuleDescriptorImpl> dependencies) {
        dependencies.getClass();
        getModule().setDependencies(dependencies);
    }
}
