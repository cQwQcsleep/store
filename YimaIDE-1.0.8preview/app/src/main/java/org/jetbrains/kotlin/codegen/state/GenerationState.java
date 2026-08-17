package org.jetbrains.kotlin.codegen.state;

import com.intellij.openapi.project.Project;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.jvm.extensions.ClassBuilderExtensionAdapter;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilderFactories;
import org.jetbrains.kotlin.codegen.ClassBuilderFactory;
import org.jetbrains.kotlin.codegen.ClassBuilderMode;
import org.jetbrains.kotlin.codegen.ClassFileFactory;
import org.jetbrains.kotlin.codegen.JvmBackendClassResolver;
import org.jetbrains.kotlin.codegen.JvmBackendClassResolverForModuleWithDependencies;
import org.jetbrains.kotlin.codegen.JvmCodegenUtil;
import org.jetbrains.kotlin.codegen.extensions.ClassFileFactoryFinalizerExtension;
import org.jetbrains.kotlin.codegen.extensions.ClassGeneratorExtensionAdapter;
import org.jetbrains.kotlin.codegen.inline.GlobalInlineContext;
import org.jetbrains.kotlin.codegen.inline.InlineCache;
import org.jetbrains.kotlin.codegen.optimization.OptimizationClassBuilderFactory;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.impl.DiagnosticsCollectorImpl;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCache;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.BindingTraceFilter;
import org.jetbrains.kotlin.resolve.DelegatingBindingTrace;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationResolver;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationSettings;
import org.jetbrains.kotlin.resolve.diagnostics.KotlinSuppressCache;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeApproximator;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002wxBw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u000e\u0010u\u001a\b\u0012\u0004\u0012\u00020v0GH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u00102\u001a\u0004\u0018\u000103¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u000207¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR#\u0010D\u001a\u0014\u0012\u0004\u0012\u00020F\u0012\n\u0012\b\u0012\u0004\u0012\u00020H0G0E¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u0011\u0010K\u001a\u00020L¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u0011\u0010O\u001a\u00020P¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0011\u0010S\u001a\u00020T¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR&\u0010W\u001a\u000e\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020F0XX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R(\u0010^\u001a\u0010\u0012\u0004\u0012\u00020Y\u0012\u0006\u0012\u0004\u0018\u00010_0XX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010[\"\u0004\ba\u0010]R2\u0010b\u001a\u001a\u0012\u0004\u0012\u00020d\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020e0cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u0011\u0010j\u001a\u00020k¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR)\u0010n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u00020r0p0o¢\u0006\b\n\u0000\u001a\u0004\bs\u0010t¨\u0006y"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/GenerationState;", Argument.Delimiters.none, "project", "Lcom/intellij/openapi/project/Project;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "builderFactory", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "generateDeclaredClassFilter", "Lorg/jetbrains/kotlin/codegen/state/GenerationState$GenerateClassFilter;", "targetId", "Lorg/jetbrains/kotlin/modules/TargetId;", "moduleName", Argument.Delimiters.none, "jvmBackendClassResolver", "Lorg/jetbrains/kotlin/codegen/JvmBackendClassResolver;", "ignoreErrors", Argument.Delimiters.none, "diagnosticReporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "compiledCodeProvider", "Lorg/jetbrains/kotlin/codegen/state/CompiledCodeProvider;", "<init>", "(Lcom/intellij/openapi/project/Project;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;Lorg/jetbrains/kotlin/codegen/state/GenerationState$GenerateClassFilter;Lorg/jetbrains/kotlin/modules/TargetId;Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/JvmBackendClassResolver;ZLorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/codegen/state/CompiledCodeProvider;)V", "getProject", "()Lcom/intellij/openapi/project/Project;", "getModule", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getGenerateDeclaredClassFilter", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState$GenerateClassFilter;", "getTargetId", "()Lorg/jetbrains/kotlin/modules/TargetId;", "getJvmBackendClassResolver", "()Lorg/jetbrains/kotlin/codegen/JvmBackendClassResolver;", "getIgnoreErrors", "()Z", "getDiagnosticReporter", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "config", "Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", "getConfig", "()Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", "inlineCache", "Lorg/jetbrains/kotlin/codegen/inline/InlineCache;", "getInlineCache", "()Lorg/jetbrains/kotlin/codegen/inline/InlineCache;", "incrementalCacheForThisTarget", "Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCache;", "getIncrementalCacheForThisTarget", "()Lorg/jetbrains/kotlin/load/kotlin/incremental/components/IncrementalCache;", "deprecationProvider", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationResolver;", "getDeprecationProvider", "()Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationResolver;", "getModuleName", "()Ljava/lang/String;", "classBuilderMode", "Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;", "getClassBuilderMode", "()Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;", "bindingTrace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "getBindingTrace", "()Lorg/jetbrains/kotlin/resolve/BindingTrace;", "localDelegatedProperties", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "getLocalDelegatedProperties", "()Ljava/util/Map;", "globalInlineContext", "Lorg/jetbrains/kotlin/codegen/inline/GlobalInlineContext;", "getGlobalInlineContext", "()Lorg/jetbrains/kotlin/codegen/inline/GlobalInlineContext;", "factory", "Lorg/jetbrains/kotlin/codegen/ClassFileFactory;", "getFactory", "()Lorg/jetbrains/kotlin/codegen/ClassFileFactory;", "globalSerializationBindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "getGlobalSerializationBindings", "()Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "mapInlineClass", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getMapInlineClass", "()Lkotlin/jvm/functions/Function1;", "setMapInlineClass", "(Lkotlin/jvm/functions/Function1;)V", "multiFieldValueClassUnboxInfo", "Lorg/jetbrains/kotlin/codegen/state/GenerationState$MultiFieldValueClassUnboxInfo;", "getMultiFieldValueClassUnboxInfo", "setMultiFieldValueClassUnboxInfo", "reportDuplicateClassNameError", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", Argument.Delimiters.none, "getReportDuplicateClassNameError", "()Lkotlin/jvm/functions/Function3;", "setReportDuplicateClassNameError", "(Lkotlin/jvm/functions/Function3;)V", "typeApproximator", "Lorg/jetbrains/kotlin/types/TypeApproximator;", "getTypeApproximator", "()Lorg/jetbrains/kotlin/types/TypeApproximator;", "newFragmentCaptureParameters", Argument.Delimiters.none, "Lkotlin/Triple;", "Lorg/jetbrains/kotlin/types/KotlinType;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getNewFragmentCaptureParameters", "()Ljava/util/List;", "loadClassBuilderInterceptors", "Lorg/jetbrains/kotlin/codegen/extensions/ClassGeneratorExtensionAdapter;", "GenerateClassFilter", "MultiFieldValueClassUnboxInfo", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GenerationState {
    private final BindingTrace bindingTrace;
    private final ClassBuilderMode classBuilderMode;
    private final JvmBackendConfig config;
    private final CompilerConfiguration configuration;
    private final DeprecationResolver deprecationProvider;
    private final DiagnosticReporter diagnosticReporter;
    private final ClassFileFactory factory;
    private final GenerateClassFilter generateDeclaredClassFilter;
    private final GlobalInlineContext globalInlineContext;
    private final JvmSerializationBindings globalSerializationBindings;
    private final boolean ignoreErrors;
    private final IncrementalCache incrementalCacheForThisTarget;
    private final InlineCache inlineCache;
    private final JvmBackendClassResolver jvmBackendClassResolver;
    private final Map<Type, List<VariableDescriptorWithAccessors>> localDelegatedProperties;
    public Function1<? super ClassDescriptor, Type> mapInlineClass;
    private final ModuleDescriptor module;
    private final String moduleName;
    private Function1<? super ClassDescriptor, MultiFieldValueClassUnboxInfo> multiFieldValueClassUnboxInfo;
    private final List<Triple<String, KotlinType, DeclarationDescriptor>> newFragmentCaptureParameters;
    private final Project project;
    public Function3<? super JvmDeclarationOrigin, ? super String, ? super String, Unit> reportDuplicateClassNameError;
    private final TargetId targetId;
    private final TypeApproximator typeApproximator;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u001e\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bR)\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/GenerationState$MultiFieldValueClassUnboxInfo;", Argument.Delimiters.none, "unboxedTypesAndMethodNamesAndFieldNames", Argument.Delimiters.none, "Lkotlin/Triple;", "Lorg/jetbrains/org/objectweb/asm/Type;", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getUnboxedTypesAndMethodNamesAndFieldNames", "()Ljava/util/List;", "unboxedTypes", "getUnboxedTypes", "unboxedMethodNames", "getUnboxedMethodNames", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MultiFieldValueClassUnboxInfo {
        private final List<String> unboxedMethodNames;
        private final List<Type> unboxedTypes;
        private final List<Triple<Type, String, String>> unboxedTypesAndMethodNamesAndFieldNames;

        public MultiFieldValueClassUnboxInfo(List<Triple<Type, String, String>> list) {
            list.getClass();
            this.unboxedTypesAndMethodNamesAndFieldNames = list;
            List<Triple<Type, String, String>> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add((Type) ((Triple) it.next()).component1());
            }
            this.unboxedTypes = arrayList;
            List<Triple<Type, String, String>> list3 = this.unboxedTypesAndMethodNamesAndFieldNames;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator<T> it2 = list3.iterator();
            while (it2.hasNext()) {
                arrayList2.add((String) ((Triple) it2.next()).component2());
            }
            this.unboxedMethodNames = arrayList2;
        }

        public final List<String> getUnboxedMethodNames() {
            return this.unboxedMethodNames;
        }

        public final List<Type> getUnboxedTypes() {
            return this.unboxedTypes;
        }

        public final List<Triple<Type, String, String>> getUnboxedTypesAndMethodNamesAndFieldNames() {
            return this.unboxedTypesAndMethodNamesAndFieldNames;
        }
    }

    public GenerationState(Project project, ModuleDescriptor moduleDescriptor, CompilerConfiguration compilerConfiguration, ClassBuilderFactory classBuilderFactory, GenerateClassFilter generateClassFilter, TargetId targetId, String str, JvmBackendClassResolver jvmBackendClassResolver, boolean z, DiagnosticReporter diagnosticReporter, CompiledCodeProvider compiledCodeProvider) {
        String moduleName;
        TargetId targetId2;
        project.getClass();
        moduleDescriptor.getClass();
        compilerConfiguration.getClass();
        classBuilderFactory.getClass();
        jvmBackendClassResolver.getClass();
        compiledCodeProvider.getClass();
        this.project = project;
        this.module = moduleDescriptor;
        this.configuration = compilerConfiguration;
        this.generateDeclaredClassFilter = generateClassFilter;
        this.targetId = targetId;
        this.jvmBackendClassResolver = jvmBackendClassResolver;
        this.ignoreErrors = z;
        this.diagnosticReporter = diagnosticReporter == null ? new DiagnosticsCollectorImpl() : diagnosticReporter;
        JvmBackendConfig jvmBackendConfig = new JvmBackendConfig(compilerConfiguration);
        this.config = jvmBackendConfig;
        this.inlineCache = new InlineCache(compiledCodeProvider);
        IncrementalCompilationComponents incrementalCompilationComponents = JVMConfigurationKeysKt.getIncrementalCompilationComponents(compilerConfiguration);
        IncrementalCache incrementalCache = null;
        if (incrementalCompilationComponents != null) {
            if (targetId == null) {
                targetId2 = str != null ? new TargetId(str, ModuleXmlParser.TYPE_PRODUCTION) : null;
                if (targetId2 == null) {
                    k2d.a("Target ID should be specified for incremental compilation");
                    throw null;
                }
            } else {
                targetId2 = targetId;
            }
            incrementalCache = incrementalCompilationComponents.getIncrementalCache(targetId2);
        }
        this.incrementalCacheForThisTarget = incrementalCache;
        StorageManager storageManager = LockBasedStorageManager.NO_LOCKS;
        storageManager.getClass();
        this.deprecationProvider = new DeprecationResolver(storageManager, jvmBackendConfig.getLanguageVersionSettings(), DeprecationSettings.Default.INSTANCE);
        if (str == null) {
            moduleName = JvmCodegenUtil.getModuleName(moduleDescriptor);
            moduleName.getClass();
        } else {
            moduleName = str;
        }
        this.moduleName = moduleName;
        ClassBuilderMode classBuilderMode = classBuilderFactory.getBuilderMode();
        classBuilderMode.getClass();
        this.classBuilderMode = classBuilderMode;
        BindingContext bindingContext = BindingContext.EMPTY;
        bindingContext.getClass();
        this.bindingTrace = new DelegatingBindingTrace(bindingContext, "trace in GenerationState", false, (BindingTraceFilter) null, false, (KotlinSuppressCache) null, 60, (DefaultConstructorMarker) null);
        this.localDelegatedProperties = new LinkedHashMap();
        this.globalInlineContext = new GlobalInlineContext();
        ClassBuilderFactory builderFactoryForDuplicateClassNameDiagnostics = new BuilderFactoryForDuplicateClassNameDiagnostics(classBuilderMode.generateBodies ? new OptimizationClassBuilderFactory(classBuilderFactory, this) : classBuilderFactory, this);
        Iterator<T> it = loadClassBuilderInterceptors().iterator();
        while (it.hasNext()) {
            builderFactoryForDuplicateClassNameDiagnostics = ((ClassGeneratorExtensionAdapter) it.next()).interceptClassBuilderFactory(builderFactoryForDuplicateClassNameDiagnostics);
        }
        this.factory = new ClassFileFactory(this, builderFactoryForDuplicateClassNameDiagnostics, ExtensionPointUtilsKt.getCompilerExtensions(this.configuration, ClassFileFactoryFinalizerExtension.INSTANCE));
        this.globalSerializationBindings = new JvmSerializationBindings();
        this.multiFieldValueClassUnboxInfo = new Function1() { // from class: xy5
            public final Object invoke(Object obj) {
                return GenerationState.a((ClassDescriptor) obj);
            }
        };
        this.typeApproximator = new TypeApproximator(this.module.getBuiltIns(), this.config.getLanguageVersionSettings());
        this.newFragmentCaptureParameters = new ArrayList();
    }

    public static MultiFieldValueClassUnboxInfo a(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        return null;
    }

    private final List<ClassGeneratorExtensionAdapter> loadClassBuilderInterceptors() throws Throwable {
        try {
            ClassBuilderExtensionAdapter classBuilderExtensionAdapter = ClassBuilderExtensionAdapter.INSTANCE;
            Object objInvoke = ClassBuilderExtensionAdapter.class.getDeclaredMethod("getExtensions", CompilerConfiguration.class).invoke(null, this.configuration);
            objInvoke.getClass();
            return (List) objInvoke;
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            targetException.getClass();
            throw targetException;
        }
    }

    public final BindingTrace getBindingTrace() {
        return this.bindingTrace;
    }

    public final ClassBuilderMode getClassBuilderMode() {
        return this.classBuilderMode;
    }

    public final JvmBackendConfig getConfig() {
        return this.config;
    }

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final DeprecationResolver getDeprecationProvider() {
        return this.deprecationProvider;
    }

    public final DiagnosticReporter getDiagnosticReporter() {
        return this.diagnosticReporter;
    }

    public final ClassFileFactory getFactory() {
        return this.factory;
    }

    public final GenerateClassFilter getGenerateDeclaredClassFilter() {
        return this.generateDeclaredClassFilter;
    }

    public final GlobalInlineContext getGlobalInlineContext() {
        return this.globalInlineContext;
    }

    public final JvmSerializationBindings getGlobalSerializationBindings() {
        return this.globalSerializationBindings;
    }

    public final boolean getIgnoreErrors() {
        return this.ignoreErrors;
    }

    public final IncrementalCache getIncrementalCacheForThisTarget() {
        return this.incrementalCacheForThisTarget;
    }

    public final InlineCache getInlineCache() {
        return this.inlineCache;
    }

    public final JvmBackendClassResolver getJvmBackendClassResolver() {
        return this.jvmBackendClassResolver;
    }

    public final Map<Type, List<VariableDescriptorWithAccessors>> getLocalDelegatedProperties() {
        return this.localDelegatedProperties;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Function1<ClassDescriptor, Type> getMapInlineClass() throws UninitializedPropertyAccessException {
        Function1<? super ClassDescriptor, Type> function1 = this.mapInlineClass;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mapInlineClass");
        return null;
    }

    public final ModuleDescriptor getModule() {
        return this.module;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final Function1<ClassDescriptor, MultiFieldValueClassUnboxInfo> getMultiFieldValueClassUnboxInfo() {
        return this.multiFieldValueClassUnboxInfo;
    }

    public final List<Triple<String, KotlinType, DeclarationDescriptor>> getNewFragmentCaptureParameters() {
        return this.newFragmentCaptureParameters;
    }

    public final Project getProject() {
        return this.project;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Function3<JvmDeclarationOrigin, String, String, Unit> getReportDuplicateClassNameError() throws UninitializedPropertyAccessException {
        Function3<? super JvmDeclarationOrigin, ? super String, ? super String, Unit> function3 = this.reportDuplicateClassNameError;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("reportDuplicateClassNameError");
        return null;
    }

    public final TargetId getTargetId() {
        return this.targetId;
    }

    public final TypeApproximator getTypeApproximator() {
        return this.typeApproximator;
    }

    public final void setMapInlineClass(Function1<? super ClassDescriptor, Type> function1) {
        function1.getClass();
        this.mapInlineClass = function1;
    }

    public final void setMultiFieldValueClassUnboxInfo(Function1<? super ClassDescriptor, MultiFieldValueClassUnboxInfo> function1) {
        function1.getClass();
        this.multiFieldValueClassUnboxInfo = function1;
    }

    public final void setReportDuplicateClassNameError(Function3<? super JvmDeclarationOrigin, ? super String, ? super String, Unit> function3) {
        function3.getClass();
        this.reportDuplicateClassNameError = function3;
    }

    public /* synthetic */ GenerationState(Project project, ModuleDescriptor moduleDescriptor, CompilerConfiguration compilerConfiguration, ClassBuilderFactory classBuilderFactory, GenerateClassFilter generateClassFilter, TargetId targetId, String str, JvmBackendClassResolver jvmBackendClassResolver, boolean z, DiagnosticReporter diagnosticReporter, CompiledCodeProvider compiledCodeProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ClassBuilderFactory classBuilderFactory2;
        ModuleDescriptor moduleDescriptor2;
        JvmBackendClassResolver jvmBackendClassResolverForModuleWithDependencies;
        if ((i & 8) != 0) {
            ClassBuilderFactory classBuilderFactory3 = ClassBuilderFactories.BINARIES;
            classBuilderFactory3.getClass();
            classBuilderFactory2 = classBuilderFactory3;
        } else {
            classBuilderFactory2 = classBuilderFactory;
        }
        GenerateClassFilter generateClassFilter2 = (i & 16) != 0 ? null : generateClassFilter;
        TargetId targetId2 = (i & 32) != 0 ? null : targetId;
        String moduleName = (i & 64) != 0 ? CommonConfigurationKeysKt.getModuleName(compilerConfiguration) : str;
        if ((i & 128) != 0) {
            moduleDescriptor2 = moduleDescriptor;
            jvmBackendClassResolverForModuleWithDependencies = new JvmBackendClassResolverForModuleWithDependencies(moduleDescriptor2);
        } else {
            moduleDescriptor2 = moduleDescriptor;
            jvmBackendClassResolverForModuleWithDependencies = jvmBackendClassResolver;
        }
        this(project, moduleDescriptor2, compilerConfiguration, classBuilderFactory2, generateClassFilter2, targetId2, moduleName, jvmBackendClassResolverForModuleWithDependencies, (i & 256) != 0 ? false : z, (i & 512) != 0 ? null : diagnosticReporter, (i & 1024) != 0 ? CompiledCodeProvider.Empty.INSTANCE : compiledCodeProvider);
    }
}
