package org.jetbrains.kotlin.load.kotlin;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinderFactory;", "", "create", "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "SERVICE", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface VirtualFileFinderFactory {

    /* JADX INFO: renamed from: SERVICE, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory$SERVICE, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinderFactory$SERVICE;", "", "<init>", "()V", "getInstance", "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinderFactory;", "project", "Lcom/intellij/openapi/project/Project;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final VirtualFileFinderFactory getInstance(Project project) {
            project.getClass();
            Object service = project.getService(VirtualFileFinderFactory.class);
            service.getClass();
            return (VirtualFileFinderFactory) service;
        }
    }

    VirtualFileFinder create(Project project, ModuleInfo module);

    VirtualFileFinder create(GlobalSearchScope scope);
}
