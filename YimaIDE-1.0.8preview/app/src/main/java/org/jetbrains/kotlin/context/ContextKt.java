package org.jetbrains.kotlin.context;

import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.context.ContextKt;
import org.jetbrains.kotlin.descriptors.ModuleCapability;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.storage.ExceptionTracker;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\f\u001a\u00020\u0005*\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\u000e\u001a\u00020\t*\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b\u001aD\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u001a\b\u0002\u0010\u0018\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019¨\u0006\u001c"}, d2 = {"GlobalContext", "Lorg/jetbrains/kotlin/context/GlobalContextImpl;", "debugName", Argument.Delimiters.none, "ProjectContext", "Lorg/jetbrains/kotlin/context/ProjectContext;", "project", "Lcom/intellij/openapi/project/Project;", "ModuleContext", "Lorg/jetbrains/kotlin/context/ModuleContext;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "withProject", "Lorg/jetbrains/kotlin/context/GlobalContext;", "withModule", "ContextForNewModule", "Lorg/jetbrains/kotlin/context/MutableModuleContext;", "projectContext", "moduleName", "Lorg/jetbrains/kotlin/name/Name;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "capabilities", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContextKt {
    public static final MutableModuleContext ContextForNewModule(ProjectContext projectContext, Name name, KotlinBuiltIns kotlinBuiltIns, TargetPlatform targetPlatform, Map<ModuleCapability<?>, ? extends Object> map) {
        projectContext.getClass();
        name.getClass();
        kotlinBuiltIns.getClass();
        map.getClass();
        return new MutableModuleContextImpl(new ModuleDescriptorImpl(name, projectContext.getStorageManager(), kotlinBuiltIns, targetPlatform, map, null, 32, null), projectContext);
    }

    public static /* synthetic */ MutableModuleContext ContextForNewModule$default(ProjectContext projectContext, Name name, KotlinBuiltIns kotlinBuiltIns, TargetPlatform targetPlatform, Map map, int i, Object obj) {
        if ((i & 16) != 0) {
            map = MapsKt.emptyMap();
        }
        return ContextForNewModule(projectContext, name, kotlinBuiltIns, targetPlatform, map);
    }

    public static final GlobalContextImpl GlobalContext(String str) {
        str.getClass();
        ExceptionTracker exceptionTracker = new ExceptionTracker();
        LockBasedStorageManager lockBasedStorageManagerCreateWithExceptionHandling = LockBasedStorageManager.createWithExceptionHandling(str, exceptionTracker, new Runnable() { // from class: su2
            @Override // java.lang.Runnable
            public final void run() {
                ContextKt.b();
            }
        }, new Function1() { // from class: uu2
            public final Object invoke(Object obj) {
                return ContextKt.a((InterruptedException) obj);
            }
        });
        lockBasedStorageManagerCreateWithExceptionHandling.getClass();
        return new GlobalContextImpl(lockBasedStorageManagerCreateWithExceptionHandling, exceptionTracker);
    }

    public static final ModuleContext ModuleContext(ModuleDescriptor moduleDescriptor, Project project, String str) {
        moduleDescriptor.getClass();
        project.getClass();
        str.getClass();
        return new ModuleContextImpl(moduleDescriptor, ProjectContext(project, str));
    }

    public static final ProjectContext ProjectContext(Project project, String str) {
        project.getClass();
        str.getClass();
        return new ProjectContextImpl(project, GlobalContext(str));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.progress.ProcessCanceledException */
    public static Unit a(InterruptedException interruptedException) throws ProcessCanceledException {
        throw new ProcessCanceledException(interruptedException);
    }

    public static void b() {
        ProgressManager.checkCanceled();
    }

    public static final ModuleContext withModule(ProjectContext projectContext, ModuleDescriptor moduleDescriptor) {
        projectContext.getClass();
        moduleDescriptor.getClass();
        return new ModuleContextImpl(moduleDescriptor, projectContext);
    }

    public static final ProjectContext withProject(GlobalContext globalContext, Project project) {
        globalContext.getClass();
        project.getClass();
        return new ProjectContextImpl(project, globalContext);
    }
}
