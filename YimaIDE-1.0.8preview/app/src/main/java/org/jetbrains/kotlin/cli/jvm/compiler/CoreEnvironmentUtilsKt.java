package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRootsKt;
import org.jetbrains.kotlin.cli.common.config.KotlinSourceRoot;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.CompilerConfigurationExtension;
import org.jetbrains.kotlin.extensions.PreprocessedFileCreator;
import org.jetbrains.kotlin.idea.KotlinFileType;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.multiplatform.IsCommonSourceKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a]\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2/\u0010\n\u001a+\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00010\u000b\u001az\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u00140\u0013\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u0001H\u00160\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00160\u001b2\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001d0\u0018\u001a6\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u001a\u001c\u0010'\u001a\u00020\u0005*\u00020\u00052\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0019\u001a\u001c\u0010+\u001a\u00020\u0001*\u00020\u00052\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0019\u001a\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005\"\u0015\u0010\"\u001a\u00020#*\u00020$8F¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006-"}, d2 = {"forAllFiles", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/config/KotlinSourceRoot;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "project", "Lcom/intellij/openapi/project/Project;", "reportLocation", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "body", "Lkotlin/Function3;", "Lcom/intellij/openapi/vfs/VirtualFile;", Argument.Delimiters.none, Argument.Delimiters.none, "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "moduleName", "allSourceFilesSequence", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/SourceFileWithModule;", "Source", "VirtualFile", "findVirtualFile", "Lkotlin/Function1;", "Ljava/io/File;", "filter", "Lorg/jetbrains/kotlin/cli/jvm/compiler/ValidSourceFilesFilter;", "convertToSourceFiles", Argument.Delimiters.none, "createSourceFilesFromSourceRoots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "sourceRoots", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "getMessageCollector", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;)Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "createConfigurationForModule", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/modules/Module;", "buildFile", "applyModuleProperties", "getSourceRootsCheckingForDuplicates", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoreEnvironmentUtilsKt {

    /* JADX INFO: Add missing generic type declarations: [Source] */
    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt$allSourceFilesSequence$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none, "Source", "Lkotlin/sequences/SequenceScope;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/SourceFileWithModule;"}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1<Source> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super SourceFileWithModule<Source>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ CompilerConfiguration $configuration;
        final /* synthetic */ Function1<VirtualFile, Iterable<Source>> $convertToSourceFiles;
        final /* synthetic */ ValidSourceFilesFilter<VirtualFile> $filter;
        final /* synthetic */ Function1<File, VirtualFile> $findVirtualFile;
        final /* synthetic */ CompilerMessageLocation $reportLocation;
        final /* synthetic */ List<KotlinSourceRoot> $this_allSourceFilesSequence;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(List<KotlinSourceRoot> list, Function1<? super File, ? extends VirtualFile> function1, CompilerConfiguration compilerConfiguration, CompilerMessageLocation compilerMessageLocation, ValidSourceFilesFilter<VirtualFile> validSourceFilesFilter, Function1<? super VirtualFile, ? extends Iterable<? extends Source>> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_allSourceFilesSequence = list;
            this.$findVirtualFile = function1;
            this.$configuration = compilerConfiguration;
            this.$reportLocation = compilerMessageLocation;
            this.$filter = validSourceFilesFilter;
            this.$convertToSourceFiles = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_allSourceFilesSequence, this.$findVirtualFile, this.$configuration, this.$reportLocation, this.$filter, this.$convertToSourceFiles, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super SourceFileWithModule<Source>> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x005d  */
        /* JADX WARN: Code duplicated, block: B:21:0x00d9  */
        /* JADX WARN: Code duplicated, block: B:23:0x00e1 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:26:0x00f7  */
        /* JADX WARN: Code duplicated, block: B:28:0x0103  */
        /* JADX WARN: Code duplicated, block: B:30:0x0112  */
        /* JADX WARN: Code duplicated, block: B:36:0x0160 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:38:0x0163  */
        /* JADX WARN: Code duplicated, block: B:43:0x00d3 A[SYNTHETIC] */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0101 -> B:37:0x0161). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0110 -> B:37:0x0161). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0116 -> B:37:0x0161). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x011f -> B:37:0x0161). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x015e -> B:37:0x0161). Please report as a decompilation issue!!! */
        /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static boolean a(CompilerConfiguration compilerConfiguration, CompilerMessageLocation compilerMessageLocation, Ref.BooleanRef booleanRef, Project project, VirtualFile virtualFile, boolean z) {
        virtualFile.getClass();
        if (!Intrinsics.areEqual(virtualFile.getExtension(), "kt")) {
            forAllFiles$ensurePluginsConfigured(booleanRef, project);
        }
        boolean z2 = Intrinsics.areEqual(virtualFile.getExtension(), "kt") || Intrinsics.areEqual(virtualFile.getFileType(), KotlinFileType.INSTANCE);
        if (z && !z2) {
            CliDiagnosticReportingKt.report(compilerConfiguration, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_ERROR(), "Source entry is not a Kotlin file: " + virtualFile.getPath(), compilerMessageLocation);
        }
        return z2;
    }

    public static final <VirtualFile, Source> Sequence<SourceFileWithModule<Source>> allSourceFilesSequence(List<KotlinSourceRoot> list, CompilerConfiguration compilerConfiguration, CompilerMessageLocation compilerMessageLocation, Function1<? super File, ? extends VirtualFile> function1, ValidSourceFilesFilter<VirtualFile> validSourceFilesFilter, Function1<? super VirtualFile, ? extends Iterable<? extends Source>> function2) {
        list.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        validSourceFilesFilter.getClass();
        function2.getClass();
        return SequencesKt.sequence(new AnonymousClass1(list, function1, compilerConfiguration, compilerMessageLocation, validSourceFilesFilter, function2, null));
    }

    public static /* synthetic */ Sequence allSourceFilesSequence$default(List list, CompilerConfiguration compilerConfiguration, CompilerMessageLocation compilerMessageLocation, Function1 function1, ValidSourceFilesFilter validSourceFilesFilter, Function1 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            compilerMessageLocation = null;
        }
        return allSourceFilesSequence(list, compilerConfiguration, compilerMessageLocation, function1, validSourceFilesFilter, function2);
    }

    public static final void applyModuleProperties(CompilerConfiguration compilerConfiguration, Module module, File file) {
        compilerConfiguration.getClass();
        module.getClass();
        if (file == null) {
            return;
        }
        CompilerConfigurationKey<File> compilerConfigurationKey = JVMConfigurationKeys.OUTPUT_DIRECTORY;
        compilerConfiguration.get(compilerConfigurationKey);
        compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_JAR);
        compilerConfiguration.put(compilerConfigurationKey, new File(module.getOutputDir()));
    }

    public static Unit b(PsiManager psiManager, List list, VirtualFile virtualFile, boolean z, String str) {
        virtualFile.getClass();
        KtFile ktFileFindFile = psiManager.findFile(virtualFile);
        if (ktFileFindFile != null && (ktFileFindFile instanceof KtFile)) {
            KtFile ktFile = ktFileFindFile;
            IsCommonSourceKt.setCommonSource(ktFile, Boolean.valueOf(z));
            if (str != null) {
                IsCommonSourceKt.setHmppModuleName(ktFile, str);
            }
            list.add(ktFileFindFile);
        }
        return Unit.INSTANCE;
    }

    public static VirtualFile c(VirtualFileSystem virtualFileSystem, File file) {
        file.getClass();
        return virtualFileSystem.findFileByPath(FilesKt.normalize(file).getPath());
    }

    public static final CompilerConfiguration createConfigurationForModule(CompilerConfiguration compilerConfiguration, Module module, File file) {
        compilerConfiguration.getClass();
        module.getClass();
        CompilerConfiguration compilerConfigurationCopy = compilerConfiguration.copy();
        applyModuleProperties(compilerConfigurationCopy, module, file);
        return compilerConfigurationCopy;
    }

    public static final List<KtFile> createSourceFilesFromSourceRoots(CompilerConfiguration compilerConfiguration, Project project, List<KotlinSourceRoot> list, CompilerMessageLocation compilerMessageLocation) {
        compilerConfiguration.getClass();
        project.getClass();
        list.getClass();
        final PsiManager psiManager = PsiManager.getInstance(project);
        psiManager.getClass();
        final ArrayList arrayList = new ArrayList();
        forAllFiles(list, compilerConfiguration, project, compilerMessageLocation, new Function3() { // from class: ky2
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CoreEnvironmentUtilsKt.b(psiManager, arrayList, (VirtualFile) obj, ((Boolean) obj2).booleanValue(), (String) obj3);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ List createSourceFilesFromSourceRoots$default(CompilerConfiguration compilerConfiguration, Project project, List list, CompilerMessageLocation compilerMessageLocation, int i, Object obj) {
        if ((i & 8) != 0) {
            compilerMessageLocation = null;
        }
        return createSourceFilesFromSourceRoots(compilerConfiguration, project, list, compilerMessageLocation);
    }

    public static Iterable d(PreprocessedFileCreator preprocessedFileCreator, VirtualFile virtualFile) {
        virtualFile.getClass();
        return CollectionsKt.listOf(preprocessedFileCreator.create(virtualFile));
    }

    public static final void forAllFiles(List<KotlinSourceRoot> list, final CompilerConfiguration compilerConfiguration, final Project project, final CompilerMessageLocation compilerMessageLocation, Function3<? super VirtualFile, ? super Boolean, ? super String, Unit> function3) {
        list.getClass();
        compilerConfiguration.getClass();
        project.getClass();
        function3.getClass();
        if (list.isEmpty()) {
            return;
        }
        final VirtualFileSystem fileSystem = VirtualFileManager.getInstance().getFileSystem("file");
        final PreprocessedFileCreator preprocessedFileCreator = new PreprocessedFileCreator(project);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        for (SourceFileWithModule sourceFileWithModule : allSourceFilesSequence(list, compilerConfiguration, compilerMessageLocation, new Function1() { // from class: ly2
            public final Object invoke(Object obj) {
                return CoreEnvironmentUtilsKt.c(fileSystem, (File) obj);
            }
        }, new ValidSourceFilesFilter() { // from class: my2
            @Override // org.jetbrains.kotlin.cli.jvm.compiler.ValidSourceFilesFilter
            public final boolean invoke(Object obj, boolean z) {
                return CoreEnvironmentUtilsKt.a(compilerConfiguration, compilerMessageLocation, booleanRef, project, (VirtualFile) obj, z);
            }
        }, new Function1() { // from class: ny2
            public final Object invoke(Object obj) {
                return CoreEnvironmentUtilsKt.d(preprocessedFileCreator, (VirtualFile) obj);
            }
        })) {
            Iterator it = sourceFileWithModule.getSourceFiles().iterator();
            while (it.hasNext()) {
                function3.invoke((VirtualFile) it.next(), Boolean.valueOf(sourceFileWithModule.getIsCommon()), sourceFileWithModule.getModuleName());
            }
        }
    }

    public static /* synthetic */ void forAllFiles$default(List list, CompilerConfiguration compilerConfiguration, Project project, CompilerMessageLocation compilerMessageLocation, Function3 function3, int i, Object obj) {
        if ((i & 4) != 0) {
            compilerMessageLocation = null;
        }
        forAllFiles(list, compilerConfiguration, project, compilerMessageLocation, function3);
    }

    private static final void forAllFiles$ensurePluginsConfigured(Ref.BooleanRef booleanRef, Project project) {
        if (booleanRef.element) {
            return;
        }
        Iterator<CompilerConfigurationExtension> it = CompilerConfigurationExtension.INSTANCE.getInstances(project).iterator();
        while (it.hasNext()) {
            it.next().updateFileRegistry();
        }
        booleanRef.element = true;
    }

    public static final MessageCollector getMessageCollector(KotlinCoreEnvironment kotlinCoreEnvironment) {
        kotlinCoreEnvironment.getClass();
        return (MessageCollector) kotlinCoreEnvironment.getConfiguration().getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY);
    }

    public static final List<KotlinSourceRoot> getSourceRootsCheckingForDuplicates(CompilerConfiguration compilerConfiguration) {
        CompilerConfiguration compilerConfiguration2;
        compilerConfiguration.getClass();
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (KotlinSourceRoot kotlinSourceRoot : ContentRootsKt.getKotlinSourceRoots(compilerConfiguration)) {
            if (hashSet.add(kotlinSourceRoot.getPath())) {
                compilerConfiguration2 = compilerConfiguration;
            } else {
                compilerConfiguration2 = compilerConfiguration;
                CliDiagnosticReportingKt.report$default(compilerConfiguration2, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_WARNING(), "Duplicate source root: " + kotlinSourceRoot.getPath(), null, 4, null);
            }
            arrayList.add(kotlinSourceRoot);
            compilerConfiguration = compilerConfiguration2;
        }
        return arrayList;
    }
}
