package org.jetbrains.kotlin.context;

import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.storage.ExceptionTracker;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/context/ProjectContextImpl;", "Lorg/jetbrains/kotlin/context/ProjectContext;", "Lorg/jetbrains/kotlin/context/GlobalContext;", "project", "Lcom/intellij/openapi/project/Project;", "globalContext", "<init>", "(Lcom/intellij/openapi/project/Project;Lorg/jetbrains/kotlin/context/GlobalContext;)V", "getProject", "()Lcom/intellij/openapi/project/Project;", "exceptionTracker", "Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "getExceptionTracker", "()Lorg/jetbrains/kotlin/storage/ExceptionTracker;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "getStorageManager", "()Lorg/jetbrains/kotlin/storage/StorageManager;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ProjectContextImpl implements GlobalContext, ProjectContext {
    private final GlobalContext globalContext;
    private final Project project;

    public ProjectContextImpl(Project project, GlobalContext globalContext) {
        project.getClass();
        globalContext.getClass();
        this.project = project;
        this.globalContext = globalContext;
    }

    @Override // org.jetbrains.kotlin.context.GlobalContext
    public ExceptionTracker getExceptionTracker() {
        return this.globalContext.getExceptionTracker();
    }

    @Override // org.jetbrains.kotlin.context.ProjectContext
    public Project getProject() {
        return this.project;
    }

    @Override // org.jetbrains.kotlin.context.GlobalContext
    public StorageManager getStorageManager() {
        return this.globalContext.getStorageManager();
    }
}
