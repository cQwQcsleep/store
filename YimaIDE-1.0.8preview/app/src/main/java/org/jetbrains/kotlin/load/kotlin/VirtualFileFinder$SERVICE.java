package org.jetbrains.kotlin.load.kotlin;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder$SERVICE;", "", "<init>", "()V", "getInstance", "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "project", "Lcom/intellij/openapi/project/Project;", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class VirtualFileFinder$SERVICE {
    public /* synthetic */ VirtualFileFinder$SERVICE(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final VirtualFileFinder getInstance(Project project, ModuleInfo module) {
        project.getClass();
        VirtualFileFinderFactory companion = VirtualFileFinderFactory.INSTANCE.getInstance(project);
        if (module != null) {
            return companion.create(project, module);
        }
        GlobalSearchScope globalSearchScopeAllScope = GlobalSearchScope.allScope(project);
        globalSearchScopeAllScope.getClass();
        return companion.create(globalSearchScopeAllScope);
    }

    private VirtualFileFinder$SERVICE() {
    }
}
