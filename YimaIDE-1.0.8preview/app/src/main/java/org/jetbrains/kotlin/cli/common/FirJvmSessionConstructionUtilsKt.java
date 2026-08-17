package org.jetbrains.kotlin.cli.common;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.FrontendContext;
import org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.fir.session.IncrementalCompilationContext;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a¨\u0001\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\r0\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\r0\f2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r0\u00102\u001a\u0010\u0011\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\fH\u0007\u001a¢\u0001\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\r0\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\r0\f2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r0\u00102\u001a\u0010\u0011\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\fH\u0007¨\u0006\u0017"}, d2 = {"prepareJvmSessions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/SessionWithSources;", "F", "Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/FrontendContext;", "files", "rootModuleNameAsString", Argument.Delimiters.none, "friendPaths", "librariesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "isCommonSource", "Lkotlin/Function1;", Argument.Delimiters.none, "isScript", "fileBelongsToModule", "Lkotlin/Function2;", "createProviderAndScopeForIncrementalCompilation", "Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", "rootModuleName", "Lorg/jetbrains/kotlin/name/Name;", "libraryList", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmSessionConstructionUtilsKt {
    @LegacyK2CliPipeline
    public static final <F> List<SessionWithSources<F>> prepareJvmSessions(FrontendContext frontendContext, List<? extends F> list, String str, List<String> list2, AbstractProjectFileSearchScope abstractProjectFileSearchScope, Function1<? super F, Boolean> function1, Function1<? super F, Boolean> function2, Function2<? super F, ? super String, Boolean> function3, Function1<? super List<? extends F>, IncrementalCompilationContext> function4) {
        frontendContext.getClass();
        list.getClass();
        str.getClass();
        list2.getClass();
        abstractProjectFileSearchScope.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        function4.getClass();
        DependencyListForCliModule dependencyListForCliModuleCreateLibraryListForJvm = CliCompilerUtilsKt.createLibraryListForJvm(str, frontendContext.getConfiguration(), list2);
        Name nameSpecial = Name.special("<" + str + '>');
        nameSpecial.getClass();
        return prepareJvmSessions(frontendContext, list, nameSpecial, abstractProjectFileSearchScope, dependencyListForCliModuleCreateLibraryListForJvm, function1, function2, function3, function4);
    }

    @LegacyK2CliPipeline
    public static final <F> List<SessionWithSources<F>> prepareJvmSessions(FrontendContext frontendContext, List<? extends F> list, Name name, AbstractProjectFileSearchScope abstractProjectFileSearchScope, DependencyListForCliModule dependencyListForCliModule, Function1<? super F, Boolean> function1, Function1<? super F, Boolean> function2, Function2<? super F, ? super String, Boolean> function3, Function1<? super List<? extends F>, IncrementalCompilationContext> function4) {
        frontendContext.getClass();
        list.getClass();
        name.getClass();
        abstractProjectFileSearchScope.getClass();
        dependencyListForCliModule.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        function4.getClass();
        return JvmFrontendPipelinePhase.INSTANCE.prepareJvmSessions(list, name, frontendContext.getConfiguration(), frontendContext.getProjectEnvironment(), abstractProjectFileSearchScope, dependencyListForCliModule, function1, function2, function3, function4);
    }
}
