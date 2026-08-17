package org.jetbrains.kotlin.cli.metadata;

import com.intellij.openapi.project.Project;
import defpackage.f2f;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.analyzer.common.CommonDependenciesContainer;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.metadata.K1LegacyMetadataSerializer;
import org.jetbrains.kotlin.codegen.JvmCodegenUtil;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.metadata.jvm.JvmModuleProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMappingKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.PackageParts;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtTypeAlias;
import org.jetbrains.kotlin.psi.KtVisitorVoid;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.resolve.scopes.ResolutionScope;
import org.jetbrains.kotlin.serialization.DescriptorSerializer;
import org.jetbrains.kotlin.serialization.KotlinSerializerExtensionBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\n\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0014J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/K1LegacyMetadataSerializer;", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer;", "Lorg/jetbrains/kotlin/cli/metadata/CommonAnalysisResult;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "dependOnOldBuiltIns", Argument.Delimiters.none, "definedMetadataVersion", "Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;ZLorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;)V", "totalSize", Argument.Delimiters.none, "getTotalSize", "()I", "setTotalSize", "(I)V", "totalFiles", "getTotalFiles", "setTotalFiles", "analyze", "serialize", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "analysisResult", "destDir", "Ljava/io/File;", "createSerializerExtension", "Lorg/jetbrains/kotlin/serialization/KotlinSerializerExtensionBase;", "PackageSerializer", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class K1LegacyMetadataSerializer extends AbstractMetadataSerializer<CommonAnalysisResult> {
    private final boolean dependOnOldBuiltIns;
    private int totalFiles;
    private int totalSize;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K1LegacyMetadataSerializer(CompilerConfiguration compilerConfiguration, KotlinCoreEnvironment kotlinCoreEnvironment, boolean z, BuiltInsBinaryVersion builtInsBinaryVersion) {
        super(compilerConfiguration, kotlinCoreEnvironment, builtInsBinaryVersion);
        compilerConfiguration.getClass();
        kotlinCoreEnvironment.getClass();
        this.dependOnOldBuiltIns = z;
    }

    public static CommonDependenciesContainer a() {
        return null;
    }

    @Override // org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer
    public CommonAnalysisResult analyze() {
        return CommonAnalysisKt.runCommonAnalysisForSerialization(getEnvironment(), this.dependOnOldBuiltIns, new Function0() { // from class: d38
            public final Object invoke() {
                return K1LegacyMetadataSerializer.a();
            }
        });
    }

    public KotlinSerializerExtensionBase createSerializerExtension() {
        return new MetadataSerializerExtension(getMetadataVersion());
    }

    public final int getTotalFiles() {
        return this.totalFiles;
    }

    public final int getTotalSize() {
        return this.totalSize;
    }

    @Override // org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer
    public AbstractMetadataSerializer.OutputInfo serialize(CommonAnalysisResult analysisResult, File destDir) throws IOException {
        analysisResult.getClass();
        destDir.getClass();
        final LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(getEnvironment().getConfiguration());
        List<KtFile> sourceFiles = getEnvironment().getSourceFiles();
        final Project project = getEnvironment().getProject();
        ModuleDescriptor moduleDescriptor = analysisResult.getModuleDescriptor();
        final BindingContext bindingContext = analysisResult.getBindingContext();
        HashMap map = new HashMap();
        for (KtFile ktFile : sourceFiles) {
            final FqName packageFqName = ktFile.getPackageFqName();
            final ArrayList arrayList = new ArrayList();
            Iterator it = ktFile.getDeclarations().iterator();
            while (it.hasNext()) {
                final K1LegacyMetadataSerializer k1LegacyMetadataSerializer = this;
                final File file = destDir;
                ((KtDeclaration) it.next()).accept(new KtVisitorVoid() { // from class: org.jetbrains.kotlin.cli.metadata.K1LegacyMetadataSerializer.serialize.1
                    public void visitClassOrObject(KtClassOrObject classOrObject) throws IOException {
                        classOrObject.getClass();
                        ClassDescriptor classDescriptor = (ClassDescriptor) bindingContext.get(BindingContext.CLASS, classOrObject);
                        if (classDescriptor == null) {
                            f2f.a("No descriptor found for class ", classOrObject.getFqName());
                            return;
                        }
                        File file2 = file;
                        FqName fqName = packageFqName;
                        Name name = classDescriptor.getName();
                        name.getClass();
                        new PackageSerializer(k1LegacyMetadataSerializer, CollectionsKt.listOf(classDescriptor), CollectionsKt.emptyList(), packageFqName, new File(file2, K1LegacyMetadataSerializerKt.getClassFilePath(new ClassId(fqName, name))), languageVersionSettings, project).run();
                    }

                    public void visitNamedFunction(KtNamedFunction function) {
                        function.getClass();
                        ArrayList<DeclarationDescriptor> arrayList2 = arrayList;
                        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) bindingContext.get(BindingContext.FUNCTION, function);
                        if (simpleFunctionDescriptor != null) {
                            arrayList2.add(simpleFunctionDescriptor);
                        } else {
                            f2f.a("No descriptor found for function ", function.getFqName());
                        }
                    }

                    public void visitProperty(KtProperty property) {
                        property.getClass();
                        ArrayList<DeclarationDescriptor> arrayList2 = arrayList;
                        VariableDescriptor variableDescriptor = (VariableDescriptor) bindingContext.get(BindingContext.VARIABLE, property);
                        if (variableDescriptor != null) {
                            arrayList2.add(variableDescriptor);
                        } else {
                            f2f.a("No descriptor found for property ", property.getFqName());
                        }
                    }

                    public void visitTypeAlias(KtTypeAlias typeAlias) {
                        typeAlias.getClass();
                        ArrayList<DeclarationDescriptor> arrayList2 = arrayList;
                        TypeAliasDescriptor typeAliasDescriptor = (TypeAliasDescriptor) bindingContext.get(BindingContext.TYPE_ALIAS, typeAlias);
                        if (typeAliasDescriptor != null) {
                            arrayList2.add(typeAliasDescriptor);
                        } else {
                            f2f.a("No descriptor found for type alias ", typeAlias.getFqName());
                        }
                    }
                });
                destDir = file;
                this = k1LegacyMetadataSerializer;
                arrayList = arrayList;
            }
            ArrayList arrayList2 = arrayList;
            K1LegacyMetadataSerializer k1LegacyMetadataSerializer2 = this;
            File file2 = destDir;
            BindingContext bindingContext2 = bindingContext;
            if (!arrayList2.isEmpty()) {
                String name = ktFile.getName();
                name.getClass();
                File file3 = new File(file2, K1LegacyMetadataSerializerKt.getPackageFilePath(packageFqName, name));
                new PackageSerializer(k1LegacyMetadataSerializer2, CollectionsKt.emptyList(), arrayList2, packageFqName, file3, languageVersionSettings, project).run();
                Object packageParts = map.get(packageFqName);
                if (packageParts == null) {
                    packageParts = new PackageParts(packageFqName.asString());
                    map.put(packageFqName, packageParts);
                }
                ((PackageParts) packageParts).addMetadataPart(FilesKt.getNameWithoutExtension(file3));
            }
            bindingContext = bindingContext2;
            destDir = file2;
            this = k1LegacyMetadataSerializer2;
        }
        K1LegacyMetadataSerializer k1LegacyMetadataSerializer3 = this;
        File file4 = new File(destDir, JvmCodegenUtil.getMappingFileName(JvmCodegenUtil.getModuleName(moduleDescriptor)));
        JvmModuleProtoBuf.Module.Builder builderNewBuilder = JvmModuleProtoBuf.Module.newBuilder();
        for (Object obj : map.values()) {
            obj.getClass();
            builderNewBuilder.getClass();
            ((PackageParts) obj).addTo(builderNewBuilder);
        }
        JvmModuleProtoBuf.Module moduleBuild = builderNewBuilder.build();
        moduleBuild.getClass();
        byte[] bArrSerializeToByteArray = ModuleMappingKt.serializeToByteArray(moduleBuild, MetadataVersion.INSTANCE, 0);
        file4.getParentFile().mkdirs();
        FilesKt.writeBytes(file4, bArrSerializeToByteArray);
        return new AbstractMetadataSerializer.OutputInfo(k1LegacyMetadataSerializer3.totalSize, k1LegacyMetadataSerializer3.totalFiles);
    }

    public final void setTotalFiles(int i) {
        this.totalFiles = i;
    }

    public final void setTotalSize(int i) {
        this.totalSize = i;
    }

    public /* synthetic */ K1LegacyMetadataSerializer(CompilerConfiguration compilerConfiguration, KotlinCoreEnvironment kotlinCoreEnvironment, boolean z, BuiltInsBinaryVersion builtInsBinaryVersion, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compilerConfiguration, kotlinCoreEnvironment, z, (i & 8) != 0 ? null : builtInsBinaryVersion);
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010\u0015\u001a\u00020\u0016J(\u0010\u0017\u001a\u00020\u00162\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u001e\u0010\u001a\u001a\u00020\u00162\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u001b\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/K1LegacyMetadataSerializer$PackageSerializer;", Argument.Delimiters.none, "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "members", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "destFile", "Ljava/io/File;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lorg/jetbrains/kotlin/cli/metadata/K1LegacyMetadataSerializer;Ljava/util/Collection;Ljava/util/Collection;Lorg/jetbrains/kotlin/name/FqName;Ljava/io/File;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lcom/intellij/openapi/project/Project;)V", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment$Builder;", "kotlin.jvm.PlatformType", "extension", "Lorg/jetbrains/kotlin/serialization/KotlinSerializerExtensionBase;", "run", Argument.Delimiters.none, "serializeClasses", "parentSerializer", "Lorg/jetbrains/kotlin/serialization/DescriptorSerializer;", "serializeMembers", "serializer", "serializeStringTable", "serializeBuiltInsFile", "write", "stream", "Ljava/io/ByteArrayOutputStream;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class PackageSerializer {
        private final Collection<DeclarationDescriptor> classes;
        private final File destFile;
        private final KotlinSerializerExtensionBase extension;
        private final LanguageVersionSettings languageVersionSettings;
        private final Collection<DeclarationDescriptor> members;
        private final FqName packageFqName;
        private final Project project;
        private final ProtoBuf.PackageFragment.Builder proto;
        final /* synthetic */ K1LegacyMetadataSerializer this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public PackageSerializer(K1LegacyMetadataSerializer k1LegacyMetadataSerializer, Collection<? extends DeclarationDescriptor> collection, Collection<? extends DeclarationDescriptor> collection2, FqName fqName, File file, LanguageVersionSettings languageVersionSettings, Project project) {
            collection.getClass();
            collection2.getClass();
            fqName.getClass();
            file.getClass();
            languageVersionSettings.getClass();
            this.this$0 = k1LegacyMetadataSerializer;
            this.classes = collection;
            this.members = collection2;
            this.packageFqName = fqName;
            this.destFile = file;
            this.languageVersionSettings = languageVersionSettings;
            this.project = project;
            this.proto = ProtoBuf.PackageFragment.newBuilder();
            this.extension = k1LegacyMetadataSerializer.createSerializerExtension();
        }

        private final void serializeBuiltInsFile() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int[] array = this.extension.getMetadataVersion().toArray();
            dataOutputStream.writeInt(array.length);
            for (int i : array) {
                dataOutputStream.writeInt(i);
            }
            this.proto.build().writeTo(byteArrayOutputStream);
            write(byteArrayOutputStream);
        }

        private final void serializeClasses(Collection<? extends DeclarationDescriptor> classes, DescriptorSerializer parentSerializer, Project project) {
            for (ClassDescriptor classDescriptor : DescriptorSerializer.Companion.sort(classes)) {
                if (classDescriptor instanceof ClassDescriptor) {
                    ClassDescriptor classDescriptor2 = classDescriptor;
                    if (classDescriptor2.getKind() != ClassKind.ENUM_ENTRY) {
                        DescriptorSerializer descriptorSerializer = parentSerializer;
                        Project project2 = project;
                        DescriptorSerializer descriptorSerializerCreate = DescriptorSerializer.Companion.create(classDescriptor2, this.extension, descriptorSerializer, this.languageVersionSettings, project2);
                        MemberScope unsubstitutedInnerClassesScope = classDescriptor2.getUnsubstitutedInnerClassesScope();
                        unsubstitutedInnerClassesScope.getClass();
                        serializeClasses(ResolutionScope.DefaultImpls.getContributedDescriptors$default(unsubstitutedInnerClassesScope, DescriptorKindFilter.CLASSIFIERS, (Function1) null, 2, (Object) null), descriptorSerializerCreate, project2);
                        this.proto.addClass_(descriptorSerializerCreate.classProto(classDescriptor2).build());
                        parentSerializer = descriptorSerializer;
                        project = project2;
                    }
                }
            }
        }

        private final void serializeMembers(Collection<? extends DeclarationDescriptor> members, DescriptorSerializer serializer) {
            this.proto.setPackage(serializer.packagePartProto(this.packageFqName, members).build());
        }

        private final void serializeStringTable() {
            Pair pairBuildProto = this.extension.getStringTable().buildProto();
            ProtoBuf.StringTable stringTable = (ProtoBuf.StringTable) pairBuildProto.component1();
            ProtoBuf.QualifiedNameTable qualifiedNameTable = (ProtoBuf.QualifiedNameTable) pairBuildProto.component2();
            this.proto.setStrings(stringTable);
            this.proto.setQualifiedNames(qualifiedNameTable);
        }

        private final void write(ByteArrayOutputStream stream) {
            K1LegacyMetadataSerializer k1LegacyMetadataSerializer = this.this$0;
            k1LegacyMetadataSerializer.setTotalSize(k1LegacyMetadataSerializer.getTotalSize() + stream.size());
            this.this$0.setTotalFiles(this.this$0.getTotalFiles() + 1);
            this.destFile.isDirectory();
            this.destFile.getParentFile().mkdirs();
            File file = this.destFile;
            byte[] byteArray = stream.toByteArray();
            byteArray.getClass();
            FilesKt.writeBytes(file, byteArray);
        }

        public final void run() throws IOException {
            DescriptorSerializer descriptorSerializerCreateTopLevel = DescriptorSerializer.Companion.createTopLevel(this.extension, this.languageVersionSettings, this.project);
            serializeClasses(this.classes, descriptorSerializerCreateTopLevel, this.project);
            serializeMembers(this.members, descriptorSerializerCreateTopLevel);
            serializeStringTable();
            serializeBuiltInsFile();
        }

        public /* synthetic */ PackageSerializer(K1LegacyMetadataSerializer k1LegacyMetadataSerializer, Collection collection, Collection collection2, FqName fqName, File file, LanguageVersionSettings languageVersionSettings, Project project, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(k1LegacyMetadataSerializer, collection, collection2, fqName, file, languageVersionSettings, (i & 32) != 0 ? null : project);
        }
    }
}
