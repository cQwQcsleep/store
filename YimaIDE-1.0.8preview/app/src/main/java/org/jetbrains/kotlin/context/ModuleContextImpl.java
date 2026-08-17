package org.jetbrains.kotlin.context;

import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.storage.ExceptionTracker;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/context/ModuleContextImpl;", "Lorg/jetbrains/kotlin/context/ModuleContext;", "Lorg/jetbrains/kotlin/context/ProjectContext;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "projectContext", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/context/ProjectContext;)V", "getModule", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "exceptionTracker", "Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "getExceptionTracker", "()Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "project", "Lcom/intellij/openapi/project/Project;", "getProject", "()Lcom/intellij/openapi/project/Project;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "getStorageManager", "()Lorg/jetbrains/kotlin/storage/StorageManager;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleContextImpl implements ModuleContext, ProjectContext {
    private final /* synthetic */ ProjectContext $$delegate_0;
    private final ModuleDescriptor module;

    public ModuleContextImpl(ModuleDescriptor moduleDescriptor, ProjectContext projectContext) {
        moduleDescriptor.getClass();
        projectContext.getClass();
        this.$$delegate_0 = projectContext;
        this.module = moduleDescriptor;
    }

    @Override // org.jetbrains.kotlin.context.GlobalContext
    public ExceptionTracker getExceptionTracker() {
        return this.$$delegate_0.getExceptionTracker();
    }

    @Override // org.jetbrains.kotlin.context.ModuleContext
    public ModuleDescriptor getModule() {
        return this.module;
    }

    @Override // org.jetbrains.kotlin.context.ProjectContext
    public Project getProject() {
        return this.$$delegate_0.getProject();
    }

    @Override // org.jetbrains.kotlin.context.GlobalContext
    public StorageManager getStorageManager() {
        return this.$$delegate_0.getStorageManager();
    }
}
