package org.jetbrains.kotlin.analysis.decompiler.psi;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.analysis.decompiler.psi.BuiltinsVirtualFileProviderBaseImpl;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0006H$J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005H\u0016R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/BuiltinsVirtualFileProviderBaseImpl;", "Lorg/jetbrains/kotlin/analysis/decompiler/psi/BuiltinsVirtualFileProvider;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "builtInUrls", "", "Ljava/net/URL;", "getBuiltInUrls", "()Ljava/util/Set;", "builtInUrls$delegate", "Lkotlin/Lazy;", "createBuiltinsScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", "findVirtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "url", "getBuiltinVirtualFiles", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class BuiltinsVirtualFileProviderBaseImpl extends BuiltinsVirtualFileProvider {

    /* JADX INFO: renamed from: builtInUrls$delegate, reason: from kotlin metadata */
    private final Lazy builtInUrls = LazyKt.lazy(new Function0() { // from class: m21
        public final Object invoke() {
            return BuiltinsVirtualFileProviderBaseImpl.a(this.b);
        }
    });

    public static Set a(BuiltinsVirtualFileProviderBaseImpl builtinsVirtualFileProviderBaseImpl) {
        ClassLoader classLoader = builtinsVirtualFileProviderBaseImpl.getClass().getClassLoader();
        Set builtInsPackages = StandardClassIds.INSTANCE.getBuiltInsPackages();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = builtInsPackages.iterator();
        while (it.hasNext()) {
            URL resource = classLoader.getResource(BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath((FqName) it.next()));
            if (resource != null) {
                linkedHashSet.add(resource);
            }
        }
        return linkedHashSet;
    }

    private final Set<URL> getBuiltInUrls() {
        return (Set) this.builtInUrls.getValue();
    }

    @Override // org.jetbrains.kotlin.analysis.decompiler.psi.BuiltinsVirtualFileProvider
    public GlobalSearchScope createBuiltinsScope(Project project) {
        project.getClass();
        GlobalSearchScope globalSearchScopeFilesScope = GlobalSearchScope.filesScope(project, getBuiltinVirtualFiles());
        globalSearchScopeFilesScope.getClass();
        return globalSearchScopeFilesScope;
    }

    public abstract VirtualFile findVirtualFile(URL url);

    @Override // org.jetbrains.kotlin.analysis.decompiler.psi.BuiltinsVirtualFileProvider
    public Set<VirtualFile> getBuiltinVirtualFiles() {
        Set<URL> builtInUrls = getBuiltInUrls();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (URL url : builtInUrls) {
            VirtualFile virtualFileFindVirtualFile = findVirtualFile(url);
            if (virtualFileFindVirtualFile == null) {
                Logger logger = Logger.getInstance(BuiltinsVirtualFileProvider.class);
                logger.getClass();
                logger.warn("VirtualFile not found for builtin " + url);
            }
            if (virtualFileFindVirtualFile != null) {
                linkedHashSet.add(virtualFileFindVirtualFile);
            }
        }
        return linkedHashSet;
    }
}
