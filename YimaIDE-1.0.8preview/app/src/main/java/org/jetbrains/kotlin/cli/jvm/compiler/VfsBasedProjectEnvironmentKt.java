package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiElementFinder;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironmentKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u0002\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0003*\u00020\u0001\u001a\u001f\u0010\b\u001a\u00020\t\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\b\u0012\u0004\u0012\u00020\u000b0\fH\u0086\b\u001a(\u0010\r\u001a\u0004\u0018\u00010\u000e*\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0000\u001a\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012¨\u0006\u0014"}, d2 = {"asPsiSearchScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "kotlin.jvm.PlatformType", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "toVfsBasedProjectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "toAbstractProjectFileSearchScope", "unregisterFinders", Argument.Delimiters.none, "T", "Lcom/intellij/psi/PsiElementFinder;", "Lcom/intellij/openapi/extensions/ExtensionPoint;", "findFileByPath", "Lcom/intellij/openapi/vfs/VirtualFile;", Argument.Delimiters.none, "Lcom/intellij/openapi/vfs/VirtualFileSystem;", ModuleXmlParser.PATH, Argument.Delimiters.none, "protocolFilter", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VfsBasedProjectEnvironmentKt {
    public static PackagePartProvider a(KotlinCoreEnvironment kotlinCoreEnvironment, GlobalSearchScope globalSearchScope) {
        globalSearchScope.getClass();
        return kotlinCoreEnvironment.createPackagePartProvider(globalSearchScope);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GlobalSearchScope asPsiSearchScope(AbstractProjectFileSearchScope abstractProjectFileSearchScope) {
        if (abstractProjectFileSearchScope == AbstractProjectFileSearchScope.EMPTY.INSTANCE) {
            return GlobalSearchScope.EMPTY_SCOPE;
        }
        if (abstractProjectFileSearchScope == AbstractProjectFileSearchScope.ANY.INSTANCE) {
            return GlobalSearchScope.notScope(GlobalSearchScope.EMPTY_SCOPE);
        }
        abstractProjectFileSearchScope.getClass();
        return ((PsiBasedProjectFileSearchScope) abstractProjectFileSearchScope).getPsiSearchScope();
    }

    public static final VirtualFile findFileByPath(List<? extends VirtualFileSystem> list, String str, String str2) {
        VirtualFile virtualFileFindFileByPath;
        list.getClass();
        str.getClass();
        Iterator<T> it = list.iterator();
        do {
            virtualFileFindFileByPath = null;
            if (!it.hasNext()) {
                break;
            }
            VirtualFileSystem virtualFileSystem = (VirtualFileSystem) it.next();
            if (str2 == null || Intrinsics.areEqual(virtualFileSystem.getProtocol(), str2)) {
                virtualFileFindFileByPath = virtualFileSystem.findFileByPath(str);
            }
        } while (virtualFileFindFileByPath == null);
        return virtualFileFindFileByPath;
    }

    public static /* synthetic */ VirtualFile findFileByPath$default(List list, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "file";
        }
        return findFileByPath(list, str, str2);
    }

    public static final AbstractProjectFileSearchScope toAbstractProjectFileSearchScope(GlobalSearchScope globalSearchScope) {
        globalSearchScope.getClass();
        return new PsiBasedProjectFileSearchScope(globalSearchScope);
    }

    public static final VfsBasedProjectEnvironment toVfsBasedProjectEnvironment(final KotlinCoreEnvironment kotlinCoreEnvironment) {
        kotlinCoreEnvironment.getClass();
        return new VfsBasedProjectEnvironment(kotlinCoreEnvironment.getProject(), (List<? extends VirtualFileSystem>) CollectionsKt.listOfNotNull(new VirtualFileSystem[]{kotlinCoreEnvironment.getProjectEnvironment().getJarFileSystem(), kotlinCoreEnvironment.getProjectEnvironment().getEnvironment().getJrtFileSystem(), kotlinCoreEnvironment.getProjectEnvironment().getEnvironment().getLocalFileSystem()}), (Function1<? super GlobalSearchScope, ? extends PackagePartProvider>) new Function1() { // from class: vbf
            public final Object invoke(Object obj) {
                return VfsBasedProjectEnvironmentKt.a(kotlinCoreEnvironment, (GlobalSearchScope) obj);
            }
        });
    }

    public static final /* synthetic */ <T extends PsiElementFinder> void unregisterFinders(ExtensionPoint<PsiElementFinder> extensionPoint) {
        extensionPoint.getClass();
        List extensionList = extensionPoint.getExtensionList();
        extensionList.getClass();
        List<PsiElementFinder> list = extensionList;
        if ((list instanceof Collection) && list.isEmpty()) {
            return;
        }
        for (PsiElementFinder psiElementFinder : list) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (psiElementFinder != null) {
                Intrinsics.reifiedOperationMarker(4, "T");
                extensionPoint.unregisterExtension(PsiElementFinder.class);
                return;
            }
        }
    }

    public static final VirtualFile findFileByPath(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, String str) {
        vfsBasedProjectEnvironment.getClass();
        str.getClass();
        return findFileByPath$default(vfsBasedProjectEnvironment.getKnownFileSystems(), str, null, 2, null);
    }
}
