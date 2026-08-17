package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.DelegatingGlobalSearchScope;
import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0096\u0002J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/AllJavaSourcesInProjectScope;", "Lcom/intellij/psi/search/DelegatingGlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "contains", Argument.Delimiters.none, "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AllJavaSourcesInProjectScope extends DelegatingGlobalSearchScope {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllJavaSourcesInProjectScope(Project project) {
        super(GlobalSearchScope.allScope(project));
        project.getClass();
    }

    public boolean contains(VirtualFile file) {
        file.getClass();
        return (Intrinsics.areEqual(file.getExtension(), "java") || file.getFileType() == JavaFileType.INSTANCE) && !file.isDirectory();
    }

    public String toString() {
        return "All Java sources in the project";
    }
}
