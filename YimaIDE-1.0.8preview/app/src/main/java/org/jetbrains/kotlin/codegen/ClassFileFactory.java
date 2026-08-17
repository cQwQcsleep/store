package org.jetbrains.kotlin.codegen;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.output.OutputFile;
import org.jetbrains.kotlin.backend.common.output.OutputFileCollection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassFileFactory;
import org.jetbrains.kotlin.codegen.extensions.ClassFileFactoryFinalizerExtension;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.ModuleMappingUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.metadata.jvm.JvmModuleProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMapping;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMappingKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.PackageParts;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.kotlin.serialization.deserialization.DeserializationConfiguration;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0004?@ABB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007J\u0006\u0010!\u001a\u00020\"J\u0016\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020)J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u0007H\u0016J\u0012\u00103\u001a\u0004\u0018\u00010/2\u0006\u00104\u001a\u00020\u000fH\u0016J\u0006\u00105\u001a\u00020\u000fJ\u0010\u00105\u001a\u00020\u000f2\b\u00106\u001a\u0004\u0018\u00010\u000fJ\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f08J\u0016\u00109\u001a\u00020\"2\u000e\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130;J\u0016\u0010<\u001a\u00020\"2\u000e\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0>R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010*\u001a\u00020+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020/0\u00078F¢\u0006\u0006\u001a\u0004\b1\u00102¨\u0006C"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassFileFactory;", "Lorg/jetbrains/kotlin/backend/common/output/OutputFileCollection;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "builderFactory", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "finalizers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/extensions/ClassFileFactoryFinalizerExtension;", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/GenerationState;Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;Ljava/util/List;)V", "getGenerationState", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "generators", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/ClassFileFactory$OutAndSourceFileList;", "generatedForCompilerPluginSources", Argument.Delimiters.none, "Ljava/io/File;", "isDone", Argument.Delimiters.none, "sourceFiles", "packagePartRegistry", "Lorg/jetbrains/kotlin/codegen/PackagePartRegistry;", "getPackagePartRegistry", "()Lorg/jetbrains/kotlin/codegen/PackagePartRegistry;", "newVisitor", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "origin", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "asmType", "Lorg/jetbrains/org/objectweb/asm/Type;", "done", Argument.Delimiters.none, "addSerializedBuiltinsPackageMetadata", ModuleXmlParser.PATH, "serialized", Argument.Delimiters.none, "setModuleMapping", "moduleProto", "Lorg/jetbrains/kotlin/metadata/jvm/JvmModuleProtoBuf$Module;", "metadataVersionToUseForModuleMapping", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getMetadataVersionToUseForModuleMapping", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "asList", "Lorg/jetbrains/kotlin/backend/common/output/OutputFile;", "currentOutput", "getCurrentOutput", "()Ljava/util/List;", "get", "relativePath", "createText", "ignorePrefixPath", "createTextForEachFile", Argument.Delimiters.none, "registerSourceFiles", "files", Argument.Delimiters.none, "removeClasses", "classNamesToRemove", Argument.Delimiters.none, "ModuleMappingException", "OutputClassFile", "ClassBuilderAndSourceFileList", "OutAndSourceFileList", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassFileFactory implements OutputFileCollection {
    private final ClassBuilderFactory builderFactory;
    private final List<ClassFileFactoryFinalizerExtension> finalizers;
    private final Set<File> generatedForCompilerPluginSources;
    private final GenerationState generationState;
    private final Map<String, OutAndSourceFileList> generators;
    private boolean isDone;
    private final PackagePartRegistry packagePartRegistry;
    private final Set<File> sourceFiles;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassFileFactory$ClassBuilderAndSourceFileList;", "Lorg/jetbrains/kotlin/codegen/ClassFileFactory$OutAndSourceFileList;", "classBuilder", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "sourceFiles", Argument.Delimiters.none, "Ljava/io/File;", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassBuilder;Ljava/util/List;)V", "asBytes", Argument.Delimiters.none, "factory", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "asText", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ClassBuilderAndSourceFileList extends OutAndSourceFileList {
        private final ClassBuilder classBuilder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ClassBuilderAndSourceFileList(ClassBuilder classBuilder, List<? extends File> list) {
            super(list);
            classBuilder.getClass();
            list.getClass();
            this.classBuilder = classBuilder;
        }

        @Override // org.jetbrains.kotlin.codegen.ClassFileFactory.OutAndSourceFileList
        public byte[] asBytes(ClassBuilderFactory factory) {
            byte[] bArrAsBytes;
            factory.getClass();
            synchronized (this) {
                bArrAsBytes = factory.asBytes(this.classBuilder);
                bArrAsBytes.getClass();
            }
            return bArrAsBytes;
        }

        @Override // org.jetbrains.kotlin.codegen.ClassFileFactory.OutAndSourceFileList
        public String asText(ClassBuilderFactory factory) {
            factory.getClass();
            String strAsText = factory.asText(this.classBuilder);
            strAsText.getClass();
            return strAsText;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassFileFactory$ModuleMappingException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ModuleMappingException extends RuntimeException {
        public ModuleMappingException(String str) {
            super(str);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\"\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH&R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassFileFactory$OutAndSourceFileList;", Argument.Delimiters.none, "sourceFiles", Argument.Delimiters.none, "Ljava/io/File;", "<init>", "(Ljava/util/List;)V", "getSourceFiles", "()Ljava/util/List;", "asBytes", Argument.Delimiters.none, "factory", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "asText", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class OutAndSourceFileList {
        private final List<File> sourceFiles;

        /* JADX WARN: Multi-variable type inference failed */
        public OutAndSourceFileList(List<? extends File> list) {
            list.getClass();
            this.sourceFiles = list;
        }

        public abstract byte[] asBytes(ClassBuilderFactory factory);

        public abstract String asText(ClassBuilderFactory factory);

        public final List<File> getSourceFiles() {
            return this.sourceFiles;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\n\u0010\u0014\u001a\u00020\u0003H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/ClassFileFactory$OutputClassFile;", "Lorg/jetbrains/kotlin/backend/common/output/OutputFile;", "relativePath", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassFileFactory;Ljava/lang/String;)V", "getRelativePath", "()Ljava/lang/String;", "sourceFiles", Argument.Delimiters.none, "Ljava/io/File;", "getSourceFiles", "()Ljava/util/List;", "generatedForCompilerPlugin", Argument.Delimiters.none, "getGeneratedForCompilerPlugin", "()Z", "asByteArray", Argument.Delimiters.none, "asText", "toString", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class OutputClassFile implements OutputFile {
        private final String relativePath;
        final /* synthetic */ ClassFileFactory this$0;

        public OutputClassFile(ClassFileFactory classFileFactory, String str) {
            str.getClass();
            this.this$0 = classFileFactory;
            this.relativePath = str;
        }

        @Override // org.jetbrains.kotlin.backend.common.output.OutputFile
        public byte[] asByteArray() {
            try {
                Object obj = this.this$0.generators.get(getRelativePath());
                obj.getClass();
                return ((OutAndSourceFileList) obj).asBytes(this.this$0.builderFactory);
            } catch (RuntimeException e) {
                StringBuilder sb = new StringBuilder("Error generating class file ");
                sb.append(this);
                String message = e.getMessage();
                sb.append(": ");
                sb.append(message);
                throw new RuntimeException(sb.toString(), e);
            }
        }

        @Override // org.jetbrains.kotlin.backend.common.output.OutputFile
        public String asText() {
            try {
                Object obj = this.this$0.generators.get(getRelativePath());
                obj.getClass();
                return ((OutAndSourceFileList) obj).asText(this.this$0.builderFactory);
            } catch (RuntimeException e) {
                StringBuilder sb = new StringBuilder("Error generating class file ");
                sb.append(this);
                String message = e.getMessage();
                sb.append(": ");
                sb.append(message);
                throw new RuntimeException(sb.toString(), e);
            }
        }

        @Override // org.jetbrains.kotlin.backend.common.output.OutputFile
        public boolean getGeneratedForCompilerPlugin() {
            List<File> sourceFiles = getSourceFiles();
            ClassFileFactory classFileFactory = this.this$0;
            if ((sourceFiles instanceof Collection) && sourceFiles.isEmpty()) {
                return false;
            }
            Iterator<T> it = sourceFiles.iterator();
            while (it.hasNext()) {
                if (classFileFactory.generatedForCompilerPluginSources.contains((File) it.next())) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.backend.common.output.OutputFile
        public String getRelativePath() {
            return this.relativePath;
        }

        @Override // org.jetbrains.kotlin.backend.common.output.OutputFile
        public List<File> getSourceFiles() {
            Object obj = this.this$0.generators.get(getRelativePath());
            obj.getClass();
            OutAndSourceFileList outAndSourceFileList = (OutAndSourceFileList) obj;
            if (outAndSourceFileList != null) {
                return outAndSourceFileList.getSourceFiles();
            }
            cpa.a("No record for binary file ", getRelativePath());
            return null;
        }

        public String toString() {
            return getRelativePath() + " (compiled from " + getSourceFiles() + ')';
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ClassFileFactory(GenerationState generationState, ClassBuilderFactory classBuilderFactory, List<? extends ClassFileFactoryFinalizerExtension> list) {
        generationState.getClass();
        classBuilderFactory.getClass();
        list.getClass();
        this.generationState = generationState;
        this.builderFactory = classBuilderFactory;
        this.finalizers = list;
        Map<String, OutAndSourceFileList> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        mapSynchronizedMap.getClass();
        this.generators = mapSynchronizedMap;
        this.generatedForCompilerPluginSources = new LinkedHashSet();
        this.sourceFiles = new HashSet();
        this.packagePartRegistry = new PackagePartRegistry();
    }

    public static Unit a(MetadataVersion metadataVersion) {
        throw new ModuleMappingException("Generated module has incompatible JVM metadata version: " + metadataVersion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BinaryVersion getMetadataVersionToUseForModuleMapping() {
        BinaryVersion metadataVersion = this.generationState.getConfig().getMetadataVersion();
        int major = metadataVersion.getMajor();
        LanguageVersion languageVersion = LanguageVersion.KOTLIN_2_0;
        return (major == languageVersion.getMajor() && metadataVersion.getMinor() == languageVersion.getMinor()) ? new MetadataVersion(new int[]{1, 9, 9999}) : metadataVersion;
    }

    public final void addSerializedBuiltinsPackageMetadata(String path, final byte[] serialized) {
        path.getClass();
        serialized.getClass();
        this.generators.put(path, new OutAndSourceFileList(CollectionsKt.toList(this.sourceFiles)) { // from class: org.jetbrains.kotlin.codegen.ClassFileFactory.addSerializedBuiltinsPackageMetadata.1
            @Override // org.jetbrains.kotlin.codegen.ClassFileFactory.OutAndSourceFileList
            public byte[] asBytes(ClassBuilderFactory factory) {
                factory.getClass();
                return serialized;
            }

            @Override // org.jetbrains.kotlin.codegen.ClassFileFactory.OutAndSourceFileList
            public String asText(ClassBuilderFactory factory) {
                factory.getClass();
                throw new UnsupportedOperationException("No string representation for protobuf-serialized metadata");
            }
        });
    }

    @Override // org.jetbrains.kotlin.backend.common.output.OutputFileCollection
    public List<OutputFile> asList() {
        done();
        return getCurrentOutput();
    }

    public final String createText(String ignorePrefixPath) {
        StringBuilder sb = new StringBuilder();
        for (OutputFile outputFile : asList()) {
            if (ignorePrefixPath == null || !StringsKt.startsWith$default(outputFile.getRelativePath(), ignorePrefixPath, false, 2, (Object) null)) {
                File file = new File(outputFile.getRelativePath());
                sb.append(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT);
                sb.append(file);
                sb.append('\n');
                String extension = FilesKt.getExtension(file);
                if (Intrinsics.areEqual(extension, "class")) {
                    sb.append(outputFile.asText());
                } else if (Intrinsics.areEqual(extension, "kotlin_module")) {
                    try {
                        ModuleMapping.Companion companion = ModuleMapping.Companion;
                        byte[] bArrAsByteArray = outputFile.asByteArray();
                        String path = file.getPath();
                        path.getClass();
                        for (Map.Entry entry : ModuleMappingUtilKt.loadModuleMapping(companion, bArrAsByteArray, path, DeserializationConfiguration.Default.INSTANCE, new Function1() { // from class: ru1
                            public final Object invoke(Object obj) {
                                return ClassFileFactory.a((MetadataVersion) obj);
                            }
                        }).getPackageFqName2Parts().entrySet()) {
                            FqName fqName = new FqName((String) entry.getKey());
                            PackageParts packageParts = (PackageParts) entry.getValue();
                            sb.append("<package ");
                            sb.append(fqName);
                            sb.append(": ");
                            sb.append(packageParts.getParts());
                            sb.append(">\n");
                        }
                        Unit unit = Unit.INSTANCE;
                    } catch (ModuleMappingException e) {
                        sb.append(file);
                        sb.append(": ");
                        sb.append(e.getMessage());
                        sb.append("\n");
                    }
                } else {
                    sb.append("Unknown output file: ");
                    sb.append(outputFile);
                }
            }
        }
        return sb.toString();
    }

    public final Map<String, String> createTextForEachFile() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (OutputFile outputFile : asList()) {
            linkedHashMap.put(outputFile.getRelativePath(), outputFile.asText());
        }
        return linkedHashMap;
    }

    public final void done() {
        if (this.isDone) {
            return;
        }
        this.isDone = true;
        Iterator<ClassFileFactoryFinalizerExtension> it = this.finalizers.iterator();
        while (it.hasNext()) {
            it.next().finalizeClassFactory(this);
        }
    }

    @Override // org.jetbrains.kotlin.backend.common.output.OutputFileCollection
    public OutputFile get(String relativePath) {
        relativePath.getClass();
        if (this.generators.containsKey(relativePath)) {
            return new OutputClassFile(this, relativePath);
        }
        return null;
    }

    public final List<OutputFile> getCurrentOutput() {
        Set<String> setKeySet = this.generators.keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputClassFile(this, (String) it.next()));
        }
        return arrayList;
    }

    public final GenerationState getGenerationState() {
        return this.generationState;
    }

    public final PackagePartRegistry getPackagePartRegistry() {
        return this.packagePartRegistry;
    }

    public final ClassBuilder newVisitor(JvmDeclarationOrigin origin, Type asmType, List<? extends File> sourceFiles) {
        origin.getClass();
        asmType.getClass();
        sourceFiles.getClass();
        ClassBuilder classBuilderNewClassBuilder = this.builderFactory.newClassBuilder(origin);
        classBuilderNewClassBuilder.getClass();
        this.generators.put(asmType.getInternalName() + ".class", new ClassBuilderAndSourceFileList(classBuilderNewClassBuilder, sourceFiles));
        if (origin.getGeneratedForCompilerPlugin()) {
            this.generatedForCompilerPluginSources.addAll(sourceFiles);
        }
        return classBuilderNewClassBuilder;
    }

    public final void registerSourceFiles(Collection<? extends File> files) {
        files.getClass();
        for (File file : files) {
            if (file != null) {
                this.sourceFiles.add(file);
            }
        }
    }

    public final void removeClasses(Set<String> classNamesToRemove) {
        classNamesToRemove.getClass();
        for (String str : classNamesToRemove) {
            this.generators.remove(str + ".class");
        }
    }

    public final void setModuleMapping(final JvmModuleProtoBuf.Module moduleProto) {
        moduleProto.getClass();
        Map<String, OutAndSourceFileList> map = this.generators;
        String mappingFileName = JvmCodegenUtil.getMappingFileName(this.generationState.getModuleName());
        mappingFileName.getClass();
        map.put(mappingFileName, new OutAndSourceFileList(CollectionsKt.toList(this.sourceFiles)) { // from class: org.jetbrains.kotlin.codegen.ClassFileFactory.setModuleMapping.1
            @Override // org.jetbrains.kotlin.codegen.ClassFileFactory.OutAndSourceFileList
            public byte[] asBytes(ClassBuilderFactory factory) {
                factory.getClass();
                return ModuleMappingKt.serializeToByteArray(moduleProto, ClassFileFactory.this.getMetadataVersionToUseForModuleMapping(), ((Boolean) ClassFileFactory.this.getGenerationState().getConfig().getLanguageVersionSettings().getFlag(JvmAnalysisFlags.getStrictMetadataVersionSemantics())).booleanValue() ? 1 : 0);
            }

            @Override // org.jetbrains.kotlin.codegen.ClassFileFactory.OutAndSourceFileList
            public String asText(ClassBuilderFactory factory) {
                factory.getClass();
                byte[] bArrAsBytes = asBytes(factory);
                Charset charset = StandardCharsets.UTF_8;
                charset.getClass();
                return new String(bArrAsBytes, charset);
            }
        });
    }

    public final String createText() {
        return createText(null);
    }
}
