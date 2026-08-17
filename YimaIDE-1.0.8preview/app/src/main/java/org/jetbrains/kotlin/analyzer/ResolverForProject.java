package org.jetbrains.kotlin.analyzer;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0005\b&\u0018\u0000  *\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001 B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\fH&¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\fH&J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H&J\n\u0010\u001f\u001a\u00020\u0018H\u0096\u0080\u0004R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/ResolverForProject;", "M", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "resolverForModule", "Lorg/jetbrains/kotlin/analyzer/ResolverForModule;", "moduleInfo", "(Lorg/jetbrains/kotlin/analyzer/ModuleInfo;)Lorg/jetbrains/kotlin/analyzer/ResolverForModule;", "tryGetResolverForModule", "descriptorForModule", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "(Lorg/jetbrains/kotlin/analyzer/ModuleInfo;)Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "moduleInfoForModuleDescriptor", "moduleDescriptor", "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "resolverForModuleDescriptor", "descriptor", "diagnoseUnknownModuleInfo", "", "infos", "", "name", "", "getName", "()Ljava/lang/String;", "allModules", "", "getAllModules", "()Ljava/util/Collection;", "toString", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ResolverForProject<M extends ModuleInfo> {
    public static final String resolverForLibrariesName = "project libraries";
    public static final String resolverForModulesName = "project source roots and libraries";
    public static final String resolverForScriptDependenciesName = "dependencies of scripts";
    public static final String resolverForSdkName = "sdk";
    public static final String resolverForSpecialInfoName = "completion/highlighting in ";

    /* JADX INFO: renamed from: descriptorForModule */
    public abstract ModuleDescriptor mo51descriptorForModule(M moduleInfo);

    public abstract Void diagnoseUnknownModuleInfo(List<? extends ModuleInfo> infos);

    public abstract Collection<M> getAllModules();

    public abstract String getName();

    public abstract M moduleInfoForModuleDescriptor(ModuleDescriptor moduleDescriptor);

    public final ResolverForModule resolverForModule(M moduleInfo) {
        moduleInfo.getClass();
        return resolverForModuleDescriptor(mo51descriptorForModule(moduleInfo));
    }

    public abstract ResolverForModule resolverForModuleDescriptor(ModuleDescriptor descriptor);

    public String toString() {
        return getName();
    }

    public abstract ResolverForModule tryGetResolverForModule(M moduleInfo);
}
