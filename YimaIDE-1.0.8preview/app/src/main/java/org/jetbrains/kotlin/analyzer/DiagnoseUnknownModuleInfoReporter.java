package org.jetbrains.kotlin.analyzer;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/DiagnoseUnknownModuleInfoReporter;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "report", "", "name", "", "infos", "", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "allModules", "", "errorInSdkResolver", "Lorg/jetbrains/kotlin/utils/KotlinExceptionWithAttachments;", "message", "errorInLibrariesResolver", "errorInModulesResolver", "errorInModulesResolverWithEmptyInfos", "errorInModulesResolverWithScriptDependencies", "errorInModulesResolverWithLibraryInfo", "errorInScriptDependenciesInfoResolver", "errorInScriptModuleInfoResolver", "errorInSpecialModuleInfoResolver", "otherError", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class DiagnoseUnknownModuleInfoReporter {
    public static final DiagnoseUnknownModuleInfoReporter INSTANCE = new DiagnoseUnknownModuleInfoReporter();

    private DiagnoseUnknownModuleInfoReporter() {
    }

    private final KotlinExceptionWithAttachments errorInLibrariesResolver(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInModulesResolver(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInModulesResolverWithEmptyInfos(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInModulesResolverWithLibraryInfo(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInModulesResolverWithScriptDependencies(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInScriptDependenciesInfoResolver(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInScriptModuleInfoResolver(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInSdkResolver(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments errorInSpecialModuleInfoResolver(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    private final KotlinExceptionWithAttachments otherError(String message) {
        return new KotlinExceptionWithAttachments(message);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments */
    public final Void report(String name, List<? extends ModuleInfo> infos, Collection<? extends ModuleInfo> allModules) throws KotlinExceptionWithAttachments {
        KotlinExceptionWithAttachments kotlinExceptionWithAttachmentsErrorInSdkResolver;
        name.getClass();
        infos.getClass();
        allModules.getClass();
        String str = name + " does not know how to resolve";
        if (StringsKt.contains$default(name, ResolverForProject.resolverForSdkName, false, 2, (Object) null)) {
            kotlinExceptionWithAttachmentsErrorInSdkResolver = errorInSdkResolver(str);
        } else if (StringsKt.contains$default(name, ResolverForProject.resolverForLibrariesName, false, 2, (Object) null)) {
            kotlinExceptionWithAttachmentsErrorInSdkResolver = errorInLibrariesResolver(str);
        } else if (StringsKt.contains$default(name, ResolverForProject.resolverForModulesName, false, 2, (Object) null)) {
            if (infos.isEmpty()) {
                kotlinExceptionWithAttachmentsErrorInSdkResolver = errorInModulesResolverWithEmptyInfos(str);
            } else if (infos.size() == 1) {
                String string = CollectionsKt.single(infos).toString();
                if (StringsKt.contains$default(string, "ScriptDependencies", false, 2, (Object) null)) {
                    kotlinExceptionWithAttachmentsErrorInSdkResolver = errorInModulesResolverWithScriptDependencies(str);
                } else {
                    kotlinExceptionWithAttachmentsErrorInSdkResolver = StringsKt.contains$default(string, "Library", false, 2, (Object) null) ? errorInModulesResolverWithLibraryInfo(str) : errorInModulesResolver(str);
                }
            } else {
                kotlinExceptionWithAttachmentsErrorInSdkResolver = errorInModulesResolver(str);
            }
        } else if (StringsKt.contains$default(name, ResolverForProject.resolverForScriptDependenciesName, false, 2, (Object) null)) {
            kotlinExceptionWithAttachmentsErrorInSdkResolver = errorInScriptDependenciesInfoResolver(str);
        } else if (StringsKt.contains$default(name, ResolverForProject.resolverForSpecialInfoName, false, 2, (Object) null)) {
            kotlinExceptionWithAttachmentsErrorInSdkResolver = StringsKt.contains$default(name, "ScriptModuleInfo", false, 2, (Object) null) ? errorInScriptModuleInfoResolver(str) : errorInSpecialModuleInfoResolver(str);
        } else {
            kotlinExceptionWithAttachmentsErrorInSdkResolver = otherError(str);
        }
        throw kotlinExceptionWithAttachmentsErrorInSdkResolver.withAttachment("infos.txt", infos).withAttachment("allModules.txt", allModules);
    }
}
