package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/CliVirtualFileFinderFactory;", "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinderFactory;", "index", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", "enableSearchInCtSym", Argument.Delimiters.none, "perfManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;ZLorg/jetbrains/kotlin/util/PerformanceManager;)V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliVirtualFileFinderFactory implements VirtualFileFinderFactory {
    private final boolean enableSearchInCtSym;
    private final JvmDependenciesIndex index;
    private final PerformanceManager perfManager;

    public CliVirtualFileFinderFactory(JvmDependenciesIndex jvmDependenciesIndex, boolean z, PerformanceManager performanceManager) {
        jvmDependenciesIndex.getClass();
        this.index = jvmDependenciesIndex;
        this.enableSearchInCtSym = z;
        this.perfManager = performanceManager;
    }

    public VirtualFileFinder create(Project project, ModuleInfo module) {
        project.getClass();
        module.getClass();
        JvmDependenciesIndex jvmDependenciesIndex = this.index;
        GlobalSearchScope globalSearchScopeAllScope = GlobalSearchScope.allScope(project);
        globalSearchScopeAllScope.getClass();
        return new CliVirtualFileFinder(jvmDependenciesIndex, globalSearchScopeAllScope, this.enableSearchInCtSym, this.perfManager);
    }

    public VirtualFileFinder create(GlobalSearchScope scope) {
        scope.getClass();
        return new CliVirtualFileFinder(this.index, scope, this.enableSearchInCtSym, this.perfManager);
    }
}
