package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.output.OutputFile;
import org.jetbrains.kotlin.backend.common.output.OutputFileCollection;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.OutputMessageUtil;
import org.jetbrains.kotlin.cli.common.modules.ModuleBuilder;
import org.jetbrains.kotlin.cli.common.output.OutputUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.VirtualJvmClasspathRoot;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.javac.JavacWrapper;
import org.jetbrains.kotlin.modules.JavaRootPath;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00012\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0001\u001a\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002\u001a\f\u0010\u0016\u001a\u00020\n*\u00020\u0011H\u0002\u001a>\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u001a\u0012\u0010 \u001a\u00020\u000f*\u00020!2\u0006\u0010\"\u001a\u00020#\u001a$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¨\u0006("}, d2 = {"getSourceFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "Lorg/jetbrains/kotlin/modules/Module;", "allSourceFiles", "localFileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "multiModuleChunk", Argument.Delimiters.none, "buildFile", "Ljava/io/File;", "getBuildFilePaths", Argument.Delimiters.none, "sourceFilePaths", "writeOutput", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "outputFiles", "Lorg/jetbrains/kotlin/backend/common/output/OutputFileCollection;", "mainClassFqName", "Lorg/jetbrains/kotlin/name/FqName;", "outputDirOrCurrentDirectory", "writeOutputsIfNeeded", "project", "Lcom/intellij/openapi/project/Project;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "hasPendingErrors", "outputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "configureFromArgs", "Lorg/jetbrains/kotlin/cli/common/modules/ModuleBuilder;", "args", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "createLibraryListForJvm", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "moduleName", "friendPaths", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliCompilerUtilsKt {
    public static CharSequence a(GenerationState generationState) {
        generationState.getClass();
        return generationState.getModuleName();
    }

    public static final void configureFromArgs(ModuleBuilder moduleBuilder, K2JVMCompilerArguments k2JVMCompilerArguments) {
        moduleBuilder.getClass();
        k2JVMCompilerArguments.getClass();
        String[] friendPaths = k2JVMCompilerArguments.getFriendPaths();
        if (friendPaths != null) {
            for (String str : friendPaths) {
                moduleBuilder.addFriendDir(str);
            }
        }
        String classpath = k2JVMCompilerArguments.getClasspath();
        if (classpath != null) {
            String str2 = File.pathSeparator;
            str2.getClass();
            List listSplit$default = StringsKt.split$default(classpath, new String[]{str2}, false, 0, 6, (Object) null);
            if (listSplit$default != null) {
                Iterator it = listSplit$default.iterator();
                while (it.hasNext()) {
                    moduleBuilder.addClasspathEntry((String) it.next());
                }
            }
        }
        String[] javaSourceRoots = k2JVMCompilerArguments.getJavaSourceRoots();
        if (javaSourceRoots != null) {
            for (String str3 : javaSourceRoots) {
                moduleBuilder.addJavaSourceRoot(new JavaRootPath(str3, k2JVMCompilerArguments.getJavaPackagePrefix()));
            }
        }
        String[] commonSources = k2JVMCompilerArguments.getCommonSources();
        Set set = commonSources != null ? ArraysKt.toSet(commonSources) : null;
        if (set == null) {
            set = SetsKt.emptySet();
        }
        if (k2JVMCompilerArguments.getScript()) {
            return;
        }
        for (String str4 : k2JVMCompilerArguments.getFreeArgs()) {
            if (StringsKt.endsWith$default(str4, ".java", false, 2, (Object) null)) {
                moduleBuilder.addJavaSourceRoot(new JavaRootPath(str4, k2JVMCompilerArguments.getJavaPackagePrefix()));
            } else {
                moduleBuilder.addSourceFiles(str4);
                if (set.contains(str4)) {
                    moduleBuilder.addCommonSourceFiles(str4);
                }
                if (new File(str4).isDirectory()) {
                    moduleBuilder.addJavaSourceRoot(new JavaRootPath(str4, k2JVMCompilerArguments.getJavaPackagePrefix()));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x00a2  */
    public static final DependencyListForCliModule createLibraryListForJvm(String str, CompilerConfiguration compilerConfiguration, List<String> list) {
        String string;
        str.getClass();
        compilerConfiguration.getClass();
        list.getClass();
        List list2 = compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
        DependencyListForCliModule.Companion companion = DependencyListForCliModule.INSTANCE;
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        DependencyListForCliModule.Builder builder = new DependencyListForCliModule.Builder();
        DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule builderForDefaultDependenciesModule = new DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + nameIdentifier + '>'), builder.createData("<dependsOn dependencies of " + nameIdentifier + '>'), builder.createData("<friends dependencies of " + nameIdentifier + '>'));
        List<ContentRoot> list3 = list2;
        ArrayList arrayList = new ArrayList();
        for (ContentRoot contentRoot : list3) {
            if (contentRoot instanceof JvmClasspathRoot) {
                string = ((JvmClasspathRoot) contentRoot).getFile().getPath();
            } else if (contentRoot instanceof VirtualJvmClasspathRoot) {
                VirtualJvmClasspathRoot virtualJvmClasspathRoot = (VirtualJvmClasspathRoot) contentRoot;
                if (virtualJvmClasspathRoot.isFriend()) {
                    string = null;
                } else {
                    string = virtualJvmClasspathRoot.getFile().toNioPath().toString();
                }
            } else {
                string = null;
            }
            if (string != null) {
                arrayList.add(string);
            }
        }
        builderForDefaultDependenciesModule.dependencies(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list3) {
            if (obj instanceof VirtualJvmClasspathRoot) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (((VirtualJvmClasspathRoot) obj2).isFriend()) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            arrayList4.add(((VirtualJvmClasspathRoot) it.next()).getFile().toNioPath().toString());
        }
        builderForDefaultDependenciesModule.friendDependencies(arrayList4);
        List<File> jvmModularRoots = JvmContentRootsKt.getJvmModularRoots(compilerConfiguration);
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(jvmModularRoots, 10));
        Iterator<T> it2 = jvmModularRoots.iterator();
        while (it2.hasNext()) {
            arrayList5.add(((File) it2.next()).getPath());
        }
        builderForDefaultDependenciesModule.dependencies(arrayList5);
        List listEmptyList = (List) compilerConfiguration.get(JVMConfigurationKeys.FRIEND_PATHS);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        builderForDefaultDependenciesModule.friendDependencies(listEmptyList);
        builderForDefaultDependenciesModule.friendDependencies(list);
        return builder.build();
    }

    public static final List<String> getBuildFilePaths(File file, List<String> list) {
        list.getClass();
        if (file == null) {
            return list;
        }
        List<String> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (String str : list2) {
            File file2 = new File(str);
            if (!file2.isAbsolute()) {
                file2 = null;
            }
            if (file2 == null) {
                file2 = FilesKt.resolveSibling(file, str);
            }
            arrayList.add(file2.getAbsolutePath());
        }
        return arrayList;
    }

    public static final List<KtFile> getSourceFiles(Module module, List<? extends KtFile> list, VirtualFileSystem virtualFileSystem, boolean z, File file) {
        module.getClass();
        list.getClass();
        if (!z) {
            return list;
        }
        List<String> buildFilePaths = getBuildFilePaths(file, module.getSourceFiles());
        virtualFileSystem.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = buildFilePaths.iterator();
        while (it.hasNext()) {
            VirtualFile virtualFileFindFileByPath = virtualFileSystem.findFileByPath((String) it.next());
            if (virtualFileFindFileByPath != null) {
                arrayList.add(virtualFileFindFileByPath);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (((VirtualFile) obj).isDirectory()) {
                arrayList2.add(obj);
            } else {
                arrayList3.add(obj);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        List list2 = (List) pair.component1();
        List list3 = (List) pair.component2();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : list) {
            VirtualFile virtualFile = ((KtFile) obj2).getVirtualFile();
            if (!list3.contains(virtualFile)) {
                List list4 = list2;
                if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                    Iterator it2 = list4.iterator();
                    while (it2.hasNext()) {
                        if (VfsUtilCore.isAncestor((VirtualFile) it2.next(), virtualFile, true)) {
                        }
                    }
                }
            }
            arrayList4.add(obj2);
        }
        return arrayList4;
    }

    private static final File outputDirOrCurrentDirectory(CompilerConfiguration compilerConfiguration) {
        File outputDirectory = JVMConfigurationKeysKt.getOutputDirectory(compilerConfiguration);
        if (outputDirectory != null) {
            String path = outputDirectory.getPath();
            path.getClass();
            if (StringsKt.isBlank(path)) {
                outputDirectory = null;
            }
            if (outputDirectory != null) {
                return outputDirectory;
            }
        }
        return new File(".");
    }

    private static final void writeOutput(CompilerConfiguration compilerConfiguration, OutputFileCollection outputFileCollection, FqName fqName) throws Throwable {
        boolean z = compilerConfiguration.getBoolean(CommonConfigurationKeys.REPORT_OUTPUT_FILES);
        File file = (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_JAR);
        MessageCollector messageCollector = CommonConfigurationKeysKt.getMessageCollector(compilerConfiguration);
        if (file == null) {
            OutputUtilsKt.writeAll(outputFileCollection, outputDirOrCurrentDirectory(compilerConfiguration), compilerConfiguration, z, CommonConfigurationKeysKt.getFileMappingTracker(compilerConfiguration));
            return;
        }
        CompilerConfigurationKey<Boolean> compilerConfigurationKey = JVMConfigurationKeys.INCLUDE_RUNTIME;
        Boolean bool = Boolean.FALSE;
        CompileEnvironmentUtil.writeToJar(file, ((Boolean) compilerConfiguration.get(compilerConfigurationKey, bool)).booleanValue(), ((Boolean) compilerConfiguration.get(JVMConfigurationKeys.NO_REFLECT, bool)).booleanValue(), !((Boolean) compilerConfiguration.get(JVMConfigurationKeys.NO_RESET_JAR_TIMESTAMPS, bool)).booleanValue(), fqName, outputFileCollection, compilerConfiguration);
        List<OutputFile> listAsList = outputFileCollection.asList();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listAsList.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((OutputFile) it.next()).getSourceFiles());
        }
        List listDistinct = CollectionsKt.distinct(arrayList);
        ICFileMappingTracker fileMappingTracker = CommonConfigurationKeysKt.getFileMappingTracker(compilerConfiguration);
        if (fileMappingTracker != null) {
            fileMappingTracker.recordSourceFilesToOutputFileMapping(listDistinct, file);
        }
        if (z) {
            String outputMessage = OutputMessageUtil.formatOutputMessage(listDistinct, file);
            outputMessage.getClass();
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.OUTPUT, outputMessage, null, 4, null);
        }
    }

    public static final boolean writeOutputsIfNeeded(Project project, CompilerConfiguration compilerConfiguration, MessageCollector messageCollector, boolean z, Collection<GenerationState> collection, FqName fqName) throws Throwable {
        project.getClass();
        compilerConfiguration.getClass();
        messageCollector.getClass();
        collection.getClass();
        if (z || messageCollector.hasErrors()) {
            return false;
        }
        for (GenerationState generationState : collection) {
            ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
            writeOutput(generationState.getConfiguration(), generationState.getFactory(), fqName);
        }
        if (!compilerConfiguration.getBoolean(JVMConfigurationKeys.COMPILE_JAVA)) {
            return true;
        }
        Collection<GenerationState> collection2 = collection;
        if (((GenerationState) CollectionsKt.singleOrNull(collection2)) == null) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getJAVAC_INTEGRATION_WARNING(), "A chunk contains multiple modules (" + CollectionsKt.joinToString$default(collection2, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: sz1
                public final Object invoke(Object obj) {
                    return CliCompilerUtilsKt.a((GenerationState) obj);
                }
            }, 31, (Object) null) + "). ", null, 4, null);
            return true;
        }
        JavacWrapper companion = JavacWrapper.Companion.getInstance(project);
        try {
            boolean zCompile = companion.compile(outputDirOrCurrentDirectory(compilerConfiguration));
            CloseableKt.closeFinally(companion, (Throwable) null);
            return zCompile;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(companion, th);
                throw th2;
            }
        }
    }
}
