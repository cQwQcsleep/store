package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.JvmPackagePartProvider;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ProjectEnvironmentWithCoreEnvironmentEmulation;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "project", "Lcom/intellij/openapi/project/Project;", "knownFileSystems", Argument.Delimiters.none, "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "getPackagePartProviderFn", "Lkotlin/Function1;", "Lcom/intellij/psi/search/GlobalSearchScope;", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "initialRoots", "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lcom/intellij/openapi/project/Project;Ljava/util/List;Lkotlin/jvm/functions/Function1;Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getInitialRoots", "()Ljava/util/List;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "packagePartProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/compiler/JvmPackagePartProvider;", "getPackagePartProviders", "getPackagePartProvider", "fileSearchScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ProjectEnvironmentWithCoreEnvironmentEmulation extends VfsBasedProjectEnvironment {
    private final CompilerConfiguration configuration;
    private final List<JavaRoot> initialRoots;
    private final List<JvmPackagePartProvider> packagePartProviders;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProjectEnvironmentWithCoreEnvironmentEmulation(Project project, List<? extends VirtualFileSystem> list, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1, List<JavaRoot> list2, CompilerConfiguration compilerConfiguration) {
        super(project, list, function1);
        project.getClass();
        list.getClass();
        function1.getClass();
        list2.getClass();
        compilerConfiguration.getClass();
        this.initialRoots = list2;
        this.configuration = compilerConfiguration;
        this.packagePartProviders = new ArrayList();
    }

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final List<JavaRoot> getInitialRoots() {
        return this.initialRoots;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment, org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public PackagePartProvider getPackagePartProvider(AbstractProjectFileSearchScope fileSearchScope) {
        fileSearchScope.getClass();
        JvmPackagePartProvider packagePartProvider = super.getPackagePartProvider(fileSearchScope);
        JvmPackagePartProvider jvmPackagePartProvider = packagePartProvider instanceof JvmPackagePartProvider ? packagePartProvider : null;
        if (jvmPackagePartProvider != null) {
            jvmPackagePartProvider.addRoots(this.initialRoots, this.configuration);
            this.packagePartProviders.add(jvmPackagePartProvider);
        }
        return packagePartProvider;
    }

    public final List<JvmPackagePartProvider> getPackagePartProviders() {
        return this.packagePartProviders;
    }
}
