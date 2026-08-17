package org.jetbrains.kotlin.codegen.serialization;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.FunctionTypesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilderMode;
import org.jetbrains.kotlin.codegen.FakeDescriptorsForReferencesKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyGetterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertySetterDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.descriptors.annotations.Annotated;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.LocalVariableDescriptor;
import org.jetbrains.kotlin.load.java.DescriptorsJvmAbiUtil;
import org.jetbrains.kotlin.load.java.lazy.types.RawTypeImpl;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmFlags;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.jvm.InlineClassManglingRulesKt;
import org.jetbrains.kotlin.serialization.DescriptorSerializer;
import org.jetbrains.kotlin.serialization.SerializerExtension;
import org.jetbrains.kotlin.types.FlexibleType;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeApproximator;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010*\u001a\u00020\u0010H\u0016J(\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.H\u0002J \u00106\u001a\u00020,2\u0006\u00107\u001a\u00020.2\u0006\u00108\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002J\u0018\u00109\u001a\u00020,2\u0006\u0010:\u001a\u00020;2\u0006\u0010/\u001a\u00020<H\u0016J\u0016\u0010=\u001a\u00020,2\u0006\u0010/\u001a\u00020<2\u0006\u0010>\u001a\u00020'J]\u0010?\u001a\u00020,\"\u000e\b\u0000\u0010@*\b\u0012\u0004\u0012\u0002H@0A\"\u0014\b\u0001\u0010B*\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002HB0C2\u0006\u0010/\u001a\u0002HB2\u0006\u0010D\u001a\u00020'2\u0018\u0010E\u001a\u0014\u0012\u0004\u0012\u0002H@\u0012\n\u0012\b\u0012\u0004\u0012\u00020G0(0FH\u0002¢\u0006\u0002\u0010HJ \u0010I\u001a\u00020,2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020MH\u0016J\u0018\u0010O\u001a\u00020,2\u0006\u0010P\u001a\u00020Q2\u0006\u0010/\u001a\u00020MH\u0016J\u0018\u0010R\u001a\u00020,2\u0006\u0010S\u001a\u00020T2\u0006\u0010/\u001a\u00020UH\u0016J \u0010V\u001a\u00020,2\u0006\u0010-\u001a\u00020W2\u0006\u0010/\u001a\u00020X2\u0006\u00103\u001a\u000204H\u0016J*\u0010Y\u001a\u00020,2\u0006\u0010-\u001a\u00020Z2\u0006\u0010/\u001a\u00020[2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0016J \u0010\\\u001a\u00020,*\u0002022\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020_\u0012\u0004\u0012\u00020,0^H\u0002J \u0010`\u001a\u00020,*\u0002022\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020_\u0012\u0004\u0012\u00020,0^H\u0002J \u0010a\u001a\u00020,*\u0002022\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020_\u0012\u0004\u0012\u00020,0^H\u0002J\f\u0010b\u001a\u00020\u0010*\u00020ZH\u0002J*\u0010c\u001a\u00020,2\u0006\u0010-\u001a\u00020d2\u0006\u0010/\u001a\u00020e2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0016J\f\u0010f\u001a\u00020\u0010*\u00020dH\u0002J\u0018\u0010g\u001a\u00020,2\u0006\u0010P\u001a\u00020Q2\u0006\u00108\u001a\u00020MH\u0016J;\u0010h\u001a\u0004\u0018\u0001Hi\"\b\b\u0000\u0010j*\u00020k\"\u0004\b\u0001\u0010i2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u0002Hj\u0012\u0004\u0012\u0002Hi0m2\u0006\u0010n\u001a\u0002HjH\u0002¢\u0006\u0002\u0010oR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010%\u001a\u0014\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(0&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006p"}, d2 = {"Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializerExtension;", "Lorg/jetbrains/kotlin/serialization/SerializerExtension;", "bindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "<init>", "(Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;Lorg/jetbrains/kotlin/codegen/state/GenerationState;Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;)V", "globalBindings", "stringTable", "Lorg/jetbrains/kotlin/codegen/serialization/JvmCodegenStringTable;", "getStringTable", "()Lorg/jetbrains/kotlin/codegen/serialization/JvmCodegenStringTable;", "useTypeTable", Argument.Delimiters.none, "moduleName", Argument.Delimiters.none, "classBuilderMode", "Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "isParamAssertionsDisabled", "unifiedNullChecks", "functionsWithInlineClassReturnTypesMangled", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "jvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "approximator", "Lorg/jetbrains/kotlin/types/TypeApproximator;", "useOldManglingScheme", "signatureSerializer", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSignatureSerializerImpl;", "localDelegatedProperties", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "shouldUseTypeTable", "serializeClass", Argument.Delimiters.none, "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "childSerializer", "Lorg/jetbrains/kotlin/serialization/DescriptorSerializer;", "isInCompatibilityMode", "writeVersionRequirementForJvmDefaultIfNeeded", "classDescriptor", "builder", "serializePackage", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package$Builder;", "serializeJvmPackage", "partAsmType", "writeLocalProperties", "MessageType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "BuilderType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableBuilder;", "classAsmType", "extension", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "(Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableBuilder;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;)V", "serializeFlexibleType", "flexibleType", "Lorg/jetbrains/kotlin/types/FlexibleType;", "lowerProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "upperProto", "serializeType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/KotlinType;", "serializeTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Builder;", "serializeConstructor", "Lorg/jetbrains/kotlin/descriptors/ConstructorDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "serializeFunction", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "writeInlineParameterNullCheckRequirement", "add", "Lkotlin/Function1;", Argument.Delimiters.none, "writeFunctionNameManglingForReturnTypeRequirement", "writeNewFunctionNameManglingRequirement", "needsInlineParameterNullCheckRequirement", "serializeProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "isJvmFieldPropertyInInterfaceCompanion", "serializeErrorType", "getBinding", "V", "K", Argument.Delimiters.none, "slice", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings$SerializationMappingSlice;", "key", "(Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings$SerializationMappingSlice;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmSerializerExtension extends SerializerExtension {
    private final TypeApproximator approximator;
    private final JvmSerializationBindings bindings;
    private final ClassBuilderMode classBuilderMode;
    private final boolean functionsWithInlineClassReturnTypesMangled;
    private final JvmSerializationBindings globalBindings;
    private final boolean isParamAssertionsDisabled;
    private final JvmDefaultMode jvmDefaultMode;
    private final LanguageVersionSettings languageVersionSettings;
    private final Map<Type, List<VariableDescriptorWithAccessors>> localDelegatedProperties;
    private final BinaryVersion metadataVersion;
    private final String moduleName;
    private final JvmSignatureSerializerImpl signatureSerializer;
    private final JvmCodegenStringTable stringTable;
    private final KotlinTypeMapperBase typeMapper;
    private final boolean unifiedNullChecks;
    private final boolean useOldManglingScheme;
    private final boolean useTypeTable;

    public JvmSerializerExtension(JvmSerializationBindings jvmSerializationBindings, GenerationState generationState, KotlinTypeMapperBase kotlinTypeMapperBase) {
        jvmSerializationBindings.getClass();
        generationState.getClass();
        kotlinTypeMapperBase.getClass();
        this.bindings = jvmSerializationBindings;
        this.typeMapper = kotlinTypeMapperBase;
        this.globalBindings = generationState.getGlobalSerializationBindings();
        this.stringTable = new JvmCodegenStringTable(kotlinTypeMapperBase, null, 2, null);
        this.useTypeTable = generationState.getConfig().getUseTypeTableInSerializer();
        this.moduleName = generationState.getModuleName();
        this.classBuilderMode = generationState.getClassBuilderMode();
        this.languageVersionSettings = generationState.getConfig().getLanguageVersionSettings();
        this.isParamAssertionsDisabled = generationState.getConfig().getIsParamAssertionsDisabled();
        this.unifiedNullChecks = generationState.getConfig().getUnifiedNullChecks();
        this.functionsWithInlineClassReturnTypesMangled = generationState.getConfig().getFunctionsWithInlineClassReturnTypesMangled();
        this.metadataVersion = generationState.getConfig().getMetadataVersion();
        this.jvmDefaultMode = generationState.getConfig().getJvmDefaultMode();
        this.approximator = generationState.getTypeApproximator();
        this.useOldManglingScheme = generationState.getConfig().getUseOldManglingSchemeForFunctionsWithInlineClassesInSignatures();
        this.signatureSerializer = new JvmSignatureSerializerImpl(m75getStringTable());
        this.localDelegatedProperties = generationState.getLocalDelegatedProperties();
    }

    private final <K, V> V getBinding(JvmSerializationBindings.SerializationMappingSlice<K, V> slice, K key) {
        V v = (V) this.bindings.get(slice, key);
        return v == null ? (V) this.globalBindings.get(slice, key) : v;
    }

    private final boolean isInCompatibilityMode(ClassDescriptor descriptor) {
        Annotations annotations = descriptor.getAnnotations();
        if (this.jvmDefaultMode != JvmDefaultMode.ENABLE || annotations.hasAnnotation(JvmStandardClassIds.INSTANCE.getJVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME())) {
            return this.jvmDefaultMode == JvmDefaultMode.NO_COMPATIBILITY && annotations.hasAnnotation(JvmStandardClassIds.INSTANCE.getJVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME());
        }
        return true;
    }

    private final boolean isJvmFieldPropertyInInterfaceCompanion(PropertyDescriptor propertyDescriptor) {
        if (!DescriptorsJvmAbiUtil.hasJvmFieldAnnotation(propertyDescriptor)) {
            return false;
        }
        ClassDescriptor declarationDescriptor = propertyDescriptor.getDeclarationDescriptor();
        declarationDescriptor.getClass();
        if (!DescriptorUtils.isCompanionObject(declarationDescriptor)) {
            return false;
        }
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        containingDeclaration.getClass();
        return DescriptorUtils.isInterface(containingDeclaration) || DescriptorUtils.isAnnotationClass(containingDeclaration);
    }

    private final boolean needsInlineParameterNullCheckRequirement(FunctionDescriptor functionDescriptor) {
        ParameterDescriptor extensionReceiverParameter;
        KotlinType type;
        if (!functionDescriptor.isInline() || functionDescriptor.isSuspend() || this.isParamAssertionsDisabled || DescriptorVisibilities.isPrivate(functionDescriptor.getVisibility())) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        List<ValueParameterDescriptor> list = valueParameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            extensionReceiverParameter = functionDescriptor.getExtensionReceiverParameter();
            return extensionReceiverParameter != null ? false : false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            KotlinType type2 = ((ValueParameterDescriptor) it.next()).getType();
            type2.getClass();
            if (FunctionTypesKt.isFunctionType(type2)) {
            }
        }
        extensionReceiverParameter = functionDescriptor.getExtensionReceiverParameter();
        if (extensionReceiverParameter != null || (type = extensionReceiverParameter.getType()) == null || !FunctionTypesKt.isFunctionType(type)) {
            return false;
        }
        return true;
    }

    private final void writeFunctionNameManglingForReturnTypeRequirement(MutableVersionRequirementTable mutableVersionRequirementTable, Function1<? super Integer, Unit> function1) {
        if (this.functionsWithInlineClassReturnTypesMangled) {
            function1.invoke(Integer.valueOf(DescriptorSerializer.Companion.writeVersionRequirement(1, 4, 0, ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION, mutableVersionRequirementTable)));
        }
    }

    private final void writeInlineParameterNullCheckRequirement(MutableVersionRequirementTable mutableVersionRequirementTable, Function1<? super Integer, Unit> function1) {
        if (this.unifiedNullChecks) {
            function1.invoke(Integer.valueOf(DescriptorSerializer.Companion.writeVersionRequirement(1, 3, 50, ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION, mutableVersionRequirementTable)));
        }
    }

    private final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void writeLocalProperties(BuilderType proto, Type classAsmType, GeneratedMessageLite.GeneratedExtension<MessageType, List<ProtoBuf.Property>> extension) {
        ProtoBuf.Property propertyBuild;
        List<VariableDescriptorWithAccessors> listEmptyList = this.localDelegatedProperties.get(classAsmType);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        for (VariableDescriptorWithAccessors variableDescriptorWithAccessors : listEmptyList) {
            if (variableDescriptorWithAccessors instanceof LocalVariableDescriptor) {
                ProtoBuf.Property.Builder builderPropertyProto = DescriptorSerializer.Companion.createForLambda(this, this.languageVersionSettings).propertyProto(FakeDescriptorsForReferencesKt.createFreeFakeLocalPropertyDescriptor((LocalVariableDescriptor) variableDescriptorWithAccessors, this.approximator));
                if (builderPropertyProto != null && (propertyBuild = builderPropertyProto.build()) != null) {
                    proto.addExtension(extension, propertyBuild);
                }
            }
        }
    }

    private final void writeNewFunctionNameManglingRequirement(MutableVersionRequirementTable mutableVersionRequirementTable, Function1<? super Integer, Unit> function1) {
        function1.invoke(Integer.valueOf(DescriptorSerializer.Companion.writeVersionRequirement(1, 4, 30, ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION, mutableVersionRequirementTable)));
    }

    private final void writeVersionRequirementForJvmDefaultIfNeeded(ClassDescriptor classDescriptor, ProtoBuf.Class.Builder builder, MutableVersionRequirementTable versionRequirementTable) {
        if (DescriptorUtils.isInterface(classDescriptor) && this.jvmDefaultMode == JvmDefaultMode.NO_COMPATIBILITY) {
            builder.addVersionRequirement(DescriptorSerializer.Companion.writeVersionRequirement(1, 4, 0, ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION, versionRequirementTable));
        }
    }

    public BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public void serializeClass(ClassDescriptor descriptor, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, DescriptorSerializer childSerializer) {
        descriptor.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        if (!Intrinsics.areEqual(this.moduleName, "main")) {
            proto.setExtension(JvmProtoBuf.classModuleName, Integer.valueOf(m75getStringTable().getStringIndex(this.moduleName)));
        }
        Type typeMapClass = (!DescriptorUtils.isInterface(descriptor) || this.jvmDefaultMode.isEnabled()) ? this.typeMapper.mapClass(descriptor) : this.typeMapper.mapDefaultImpls(descriptor);
        GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.classLocalVariable;
        generatedExtension.getClass();
        writeLocalProperties(proto, typeMapClass, generatedExtension);
        writeVersionRequirementForJvmDefaultIfNeeded(descriptor, proto, versionRequirementTable);
        if (this.jvmDefaultMode.isEnabled() && DescriptorUtils.isInterface(descriptor)) {
            proto.setExtension(JvmProtoBuf.jvmClassFlags, Integer.valueOf(JvmFlags.INSTANCE.getClassFlags(true, isInCompatibilityMode(descriptor))));
        }
    }

    public void serializeConstructor(ConstructorDescriptor descriptor, ProtoBuf.Constructor.Builder proto, DescriptorSerializer childSerializer) {
        JvmProtoBuf.JvmMethodSignature jvmMethodSignatureMethodSignature;
        descriptor.getClass();
        proto.getClass();
        childSerializer.getClass();
        JvmSerializationBindings.SerializationMappingSlice<FunctionDescriptor, Method> serializationMappingSlice = JvmSerializationBindings.METHOD_FOR_FUNCTION;
        serializationMappingSlice.getClass();
        Method method = (Method) getBinding(serializationMappingSlice, descriptor);
        if (method == null || (jvmMethodSignatureMethodSignature = this.signatureSerializer.methodSignature(descriptor, descriptor.getName(), method)) == null) {
            return;
        }
        proto.setExtension(JvmProtoBuf.constructorSignature, jvmMethodSignatureMethodSignature);
    }

    public void serializeErrorType(KotlinType type, ProtoBuf.Type.Builder builder) {
        type.getClass();
        builder.getClass();
        if (this.classBuilderMode == ClassBuilderMode.KAPT3) {
            builder.setClassName(m75getStringTable().getStringIndex("error/NonExistentClass"));
        } else {
            super.serializeErrorType(type, builder);
        }
    }

    public void serializeFlexibleType(FlexibleType flexibleType, ProtoBuf.Type.Builder lowerProto, ProtoBuf.Type.Builder upperProto) {
        flexibleType.getClass();
        lowerProto.getClass();
        upperProto.getClass();
        lowerProto.setFlexibleTypeCapabilitiesId(m75getStringTable().getStringIndex("kotlin.jvm.PlatformType"));
        if (flexibleType instanceof RawTypeImpl) {
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.isRaw;
            Boolean bool = Boolean.TRUE;
            lowerProto.setExtension(generatedExtension, bool);
            upperProto.setExtension(generatedExtension, bool);
        }
    }

    public void serializeFunction(FunctionDescriptor descriptor, ProtoBuf.Function.Builder proto, MutableVersionRequirementTable versionRequirementTable, DescriptorSerializer childSerializer) {
        JvmProtoBuf.JvmMethodSignature jvmMethodSignatureMethodSignature;
        descriptor.getClass();
        proto.getClass();
        childSerializer.getClass();
        JvmSerializationBindings.SerializationMappingSlice<FunctionDescriptor, Method> serializationMappingSlice = JvmSerializationBindings.METHOD_FOR_FUNCTION;
        serializationMappingSlice.getClass();
        Method method = (Method) getBinding(serializationMappingSlice, descriptor);
        if (method != null && (jvmMethodSignatureMethodSignature = this.signatureSerializer.methodSignature(descriptor, descriptor.getName(), method)) != null) {
            proto.setExtension(JvmProtoBuf.methodSignature, jvmMethodSignatureMethodSignature);
        }
        if (needsInlineParameterNullCheckRequirement(descriptor) && versionRequirementTable != null) {
            writeInlineParameterNullCheckRequirement(versionRequirementTable, new AnonymousClass1(proto));
        }
        if (InlineClassManglingRulesKt.requiresFunctionNameManglingForReturnType(descriptor) && !DescriptorUtils.hasJvmNameAnnotation(descriptor) && !InlineClassManglingRulesKt.requiresFunctionNameManglingForParameterTypes(descriptor) && versionRequirementTable != null) {
            writeFunctionNameManglingForReturnTypeRequirement(versionRequirementTable, new AnonymousClass2(proto));
        }
        if ((!InlineClassManglingRulesKt.requiresFunctionNameManglingForReturnType(descriptor) && !InlineClassManglingRulesKt.requiresFunctionNameManglingForParameterTypes(descriptor)) || DescriptorUtils.hasJvmNameAnnotation(descriptor) || this.useOldManglingScheme || versionRequirementTable == null) {
            return;
        }
        writeNewFunctionNameManglingRequirement(versionRequirementTable, new AnonymousClass3(proto));
    }

    public final void serializeJvmPackage(ProtoBuf.Package.Builder proto, Type partAsmType) {
        proto.getClass();
        partAsmType.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.packageLocalVariable;
        generatedExtension.getClass();
        writeLocalProperties(proto, partAsmType, generatedExtension);
    }

    public void serializePackage(FqName packageFqName, ProtoBuf.Package.Builder proto) {
        packageFqName.getClass();
        proto.getClass();
        if (Intrinsics.areEqual(this.moduleName, "main")) {
            return;
        }
        proto.setExtension(JvmProtoBuf.packageModuleName, Integer.valueOf(m75getStringTable().getStringIndex(this.moduleName)));
    }

    public void serializeProperty(PropertyDescriptor descriptor, ProtoBuf.Property.Builder proto, MutableVersionRequirementTable versionRequirementTable, DescriptorSerializer childSerializer) {
        Method method;
        Method method2;
        Type type;
        String descriptor2;
        Type type2;
        descriptor.getClass();
        proto.getClass();
        childSerializer.getClass();
        PropertyGetterDescriptor getter = descriptor.getGetter();
        PropertySetterDescriptor setter = descriptor.getSetter();
        if (getter == null) {
            method = null;
        } else {
            JvmSerializationBindings.SerializationMappingSlice<FunctionDescriptor, Method> serializationMappingSlice = JvmSerializationBindings.METHOD_FOR_FUNCTION;
            serializationMappingSlice.getClass();
            method = (Method) getBinding(serializationMappingSlice, getter);
        }
        if (setter == null) {
            method2 = null;
        } else {
            JvmSerializationBindings.SerializationMappingSlice<FunctionDescriptor, Method> serializationMappingSlice2 = JvmSerializationBindings.METHOD_FOR_FUNCTION;
            serializationMappingSlice2.getClass();
            method2 = (Method) getBinding(serializationMappingSlice2, setter);
        }
        JvmSerializationBindings.SerializationMappingSlice<PropertyDescriptor, Pair<Type, String>> serializationMappingSlice3 = JvmSerializationBindings.FIELD_FOR_PROPERTY;
        serializationMappingSlice3.getClass();
        Pair pair = (Pair) getBinding(serializationMappingSlice3, descriptor);
        JvmSerializationBindings.SerializationMappingSlice<PropertyDescriptor, Method> serializationMappingSlice4 = JvmSerializationBindings.SYNTHETIC_METHOD_FOR_PROPERTY;
        serializationMappingSlice4.getClass();
        Method method3 = (Method) getBinding(serializationMappingSlice4, descriptor);
        JvmSerializationBindings.SerializationMappingSlice<PropertyDescriptor, Method> serializationMappingSlice5 = JvmSerializationBindings.DELEGATE_METHOD_FOR_PROPERTY;
        serializationMappingSlice5.getClass();
        Method method4 = (Method) getBinding(serializationMappingSlice5, descriptor);
        descriptor.isDelegated();
        JvmSignatureSerializerImpl jvmSignatureSerializerImpl = this.signatureSerializer;
        Name name = descriptor.getName();
        name.getClass();
        JvmProtoBuf.JvmPropertySignature jvmPropertySignaturePropertySignature = jvmSignatureSerializerImpl.propertySignature(name, pair != null ? (String) pair.getSecond() : null, (pair == null || (type2 = (Type) pair.getFirst()) == null) ? null : type2.getDescriptor(), method3 != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method3) : null, method4 != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method4) : null, method != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method) : null, method2 != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method2) : null, (pair == null || (type = (Type) pair.getFirst()) == null || (descriptor2 = type.getDescriptor()) == null) ? false : this.signatureSerializer.requiresPropertySignature(descriptor, descriptor2));
        if (jvmPropertySignaturePropertySignature != null) {
            proto.setExtension(JvmProtoBuf.propertySignature, jvmPropertySignaturePropertySignature);
        }
        if (isJvmFieldPropertyInInterfaceCompanion(descriptor) && versionRequirementTable != null) {
            proto.setExtension(JvmProtoBuf.flags, Integer.valueOf(JvmFlags.INSTANCE.getPropertyFlags(true)));
        }
        if (((getter != null && needsInlineParameterNullCheckRequirement(getter)) || (setter != null && needsInlineParameterNullCheckRequirement(setter))) && versionRequirementTable != null) {
            writeInlineParameterNullCheckRequirement(versionRequirementTable, new C00072(proto));
        }
        if (DescriptorUtils.hasJvmNameAnnotation(descriptor) || !InlineClassManglingRulesKt.requiresFunctionNameManglingForReturnType(descriptor)) {
            return;
        }
        if (!this.useOldManglingScheme && versionRequirementTable != null) {
            writeNewFunctionNameManglingRequirement(versionRequirementTable, new C00083(proto));
        }
        if (versionRequirementTable != null) {
            writeFunctionNameManglingForReturnTypeRequirement(versionRequirementTable, new AnonymousClass4(proto));
        }
    }

    public void serializeType(KotlinType type, ProtoBuf.Type.Builder proto) {
        type.getClass();
        proto.getClass();
        Iterator it = DescriptorUtilsKt.getNonSourceAnnotations(type).iterator();
        while (it.hasNext()) {
            proto.addAnnotation(getAnnotationSerializer().serializeAnnotation((AnnotationDescriptor) it.next()));
        }
    }

    public void serializeTypeParameter(TypeParameterDescriptor typeParameter, ProtoBuf.TypeParameter.Builder proto) {
        typeParameter.getClass();
        proto.getClass();
        Iterator it = DescriptorUtilsKt.getNonSourceAnnotations((Annotated) typeParameter).iterator();
        while (it.hasNext()) {
            proto.addAnnotation(getAnnotationSerializer().serializeAnnotation((AnnotationDescriptor) it.next()));
        }
    }

    /* JADX INFO: renamed from: shouldUseTypeTable, reason: from getter */
    public boolean getUseTypeTable() {
        return this.useTypeTable;
    }

    /* JADX INFO: renamed from: getStringTable, reason: from getter and merged with bridge method [inline-methods] */
    public JvmCodegenStringTable m75getStringTable() {
        return this.stringTable;
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension$serializeFunction$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, ProtoBuf.Function.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Function.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension$serializeFunction$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public AnonymousClass2(Object obj) {
            super(1, obj, ProtoBuf.Function.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Function.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension$serializeFunction$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public AnonymousClass3(Object obj) {
            super(1, obj, ProtoBuf.Function.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Function.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension$serializeProperty$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00072 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public C00072(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension$serializeProperty$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00083 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public C00083(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension$serializeProperty$4, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass4 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public AnonymousClass4(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }
}
