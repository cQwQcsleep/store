package org.jetbrains.kotlin.cli.jvm.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.FileWalkDirection;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.IncrementalCompilationContextUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.ProjectFileSearchScopeProvider;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.session.IncrementalCompilationContext;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.incremental.IncrementalPackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.modules.TargetIdKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u001a8\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0001\u001a \u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0001¨\u0006\u000f"}, d2 = {"createIncrementalCompilationScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "incrementalExcludesScope", "createContextForIncrementalCompilation", "Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", "sourceScope", "previousStepsSymbolProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "incrementalCompilationScope", "moduleConfiguration", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IncrementalCompilationContextUtilsKt {
    public static boolean a(File file) {
        file.getClass();
        return Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static final IncrementalCompilationContext createContextForIncrementalCompilation(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, CompilerConfiguration compilerConfiguration, AbstractProjectFileSearchScope abstractProjectFileSearchScope) {
        ArrayList arrayList;
        File file;
        vfsBasedProjectEnvironment.getClass();
        compilerConfiguration.getClass();
        abstractProjectFileSearchScope.getClass();
        IncrementalCompilationComponents incrementalCompilationComponents = (IncrementalCompilationComponents) compilerConfiguration.get(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS);
        List list = (List) compilerConfiguration.get(JVMConfigurationKeys.MODULES);
        if (list != null) {
            List list2 = list;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(TargetIdKt.TargetId((Module) it.next()));
            }
        } else {
            arrayList = null;
        }
        if (arrayList == null || incrementalCompilationComponents == null || (file = (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_DIRECTORY)) == null) {
            return null;
        }
        AbstractProjectFileSearchScope searchScopeByIoFiles$default = AbstractProjectEnvironment.getSearchScopeByIoFiles$default(vfsBasedProjectEnvironment, SequencesKt.asIterable(SequencesKt.filter(FilesKt.walk$default(file, (FileWalkDirection) null, 1, (Object) null), new Function1() { // from class: am6
            public final Object invoke(Object obj) {
                return Boolean.valueOf(IncrementalCompilationContextUtilsKt.a((File) obj));
            }
        })), false, 2, null);
        if (searchScopeByIoFiles$default.isEmpty()) {
            searchScopeByIoFiles$default = null;
        }
        if (searchScopeByIoFiles$default == null) {
            return null;
        }
        PackagePartProvider packagePartProvider = vfsBasedProjectEnvironment.getPackagePartProvider(abstractProjectFileSearchScope);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(incrementalCompilationComponents.getIncrementalCache((TargetId) it2.next()));
        }
        return new IncrementalCompilationContext(CollectionsKt.emptyList(), new IncrementalPackagePartProvider(packagePartProvider, arrayList2), searchScopeByIoFiles$default);
    }

    public static final AbstractProjectFileSearchScope createIncrementalCompilationScope(CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope) {
        IncrementalCompilationComponents incrementalCompilationComponents;
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        if (compilerConfiguration.get(JVMConfigurationKeys.MODULES) == null || (incrementalCompilationComponents = (IncrementalCompilationComponents) compilerConfiguration.get(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS)) == null) {
            return null;
        }
        if (incrementalCompilationComponents instanceof ProjectFileSearchScopeProvider) {
            return ((ProjectFileSearchScopeProvider) incrementalCompilationComponents).createSearchScope(vfsBasedProjectEnvironment);
        }
        File file = (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_DIRECTORY);
        if (file == null) {
            return null;
        }
        AbstractProjectFileSearchScope searchScopeByDirectories = vfsBasedProjectEnvironment.getSearchScopeByDirectories(SetsKt.setOf(file));
        return (abstractProjectFileSearchScope == null || abstractProjectFileSearchScope.isEmpty()) ? searchScopeByDirectories : searchScopeByDirectories.minus(abstractProjectFileSearchScope);
    }

    public static final IncrementalCompilationContext createContextForIncrementalCompilation(CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope, List<? extends FirSymbolProvider> list, AbstractProjectFileSearchScope abstractProjectFileSearchScope2) {
        List list2;
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        abstractProjectFileSearchScope.getClass();
        list.getClass();
        if ((abstractProjectFileSearchScope2 == null && list.isEmpty()) || (list2 = (List) compilerConfiguration.get(JVMConfigurationKeys.MODULES)) == null) {
            return null;
        }
        List list3 = list2;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator it = list3.iterator();
        while (it.hasNext()) {
            arrayList.add(TargetIdKt.TargetId((Module) it.next()));
        }
        IncrementalCompilationComponents incrementalCompilationComponents = (IncrementalCompilationComponents) compilerConfiguration.get(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS);
        if (incrementalCompilationComponents == null) {
            return null;
        }
        List<? extends FirSymbolProvider> list4 = list;
        PackagePartProvider packagePartProvider = vfsBasedProjectEnvironment.getPackagePartProvider(abstractProjectFileSearchScope);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(incrementalCompilationComponents.getIncrementalCache((TargetId) it2.next()));
        }
        return new IncrementalCompilationContext(list4, new IncrementalPackagePartProvider(packagePartProvider, arrayList2), abstractProjectFileSearchScope2);
    }
}
