package org.jetbrains.kotlin.codegen.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.BuiltInsPackageFragment;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.builtins.functions.FunctionClassDescriptor;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.CommonVariableAsmNameManglingUtils;
import org.jetbrains.kotlin.codegen.JvmCodegenUtil;
import org.jetbrains.kotlin.codegen.signature.AsmTypeFactory;
import org.jetbrains.kotlin.codegen.signature.JvmSignatureWriter;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.ClassOrPackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PossiblyInnerType;
import org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyGetterDescriptor;
import org.jetbrains.kotlin.descriptors.ScriptDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterUtilsKt;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor;
import org.jetbrains.kotlin.fileClasses.JvmFileClassInfo;
import org.jetbrains.kotlin.fileClasses.JvmFileClassUtil;
import org.jetbrains.kotlin.load.java.BuiltinMethodsWithSpecialGenericSignature;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.load.java.SpecialBuiltinMembers;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.load.java.descriptors.JavaCallableMemberDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.JavaMethodDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.UtilKt;
import org.jetbrains.kotlin.load.java.lazy.descriptors.LazyJavaPackageFragment;
import org.jetbrains.kotlin.load.kotlin.DescriptorBasedTypeSignatureMappingKt;
import org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource;
import org.jetbrains.kotlin.load.kotlin.MethodSignatureMappingKt;
import org.jetbrains.kotlin.load.kotlin.TypeMappingConfiguration;
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode;
import org.jetbrains.kotlin.load.kotlin.TypeMappingModeExtensionsKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.ProtoBufUtilKt;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.DescriptorToSourceUtils;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.FunctionImportedFromObject;
import org.jetbrains.kotlin.resolve.InlineClassDescriptorResolver;
import org.jetbrains.kotlin.resolve.InlineClassesUtilsKt;
import org.jetbrains.kotlin.resolve.annotations.AnnotationUtilKt;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.jvm.JdkClassesKt;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.kotlin.resolve.jvm.jvmSignature.JvmMethodGenericSignature;
import org.jetbrains.kotlin.resolve.jvm.jvmSignature.JvmMethodParameterKind;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DescriptorWithContainerSource;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import org.jetbrains.kotlin.types.CommonSupertypes;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeProjection;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.TypeUtils;
import org.jetbrains.kotlin.types.UtilsKt;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.checker.ClassicTypeSystemContextKt;
import org.jetbrains.kotlin.types.checker.SimpleClassicTypeSystemContext;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingUtils;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContext;
import org.jetbrains.kotlin.types.model.TypeVariance;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ë\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0017\u0018\u0000 Y2\u00020\u0001:\u0003WXYBY\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t\u0012\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\t\u001a\u0002\b\u000f¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007b\u0002\b\u000fJ\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0002J\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J*\u0010(\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\n2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010&\u001a\u00020'H\u0007b\u0002\b\u000fJ*\u0010*\u001a\u00020+2\u0006\u0010$\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u001a2\b\u0010)\u001a\u0004\u0018\u00010\u001e2\u0006\u0010&\u001a\u00020'H\u0002J.\u0010-\u001a\u00020+2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'2\u0006\u00101\u001a\u000202H\u0002J \u00103\u001a\u00020+2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010$\u001a\u0002002\u0006\u0010&\u001a\u00020'H\u0002J4\u00103\u001a\u00020+2\u0006\u0010)\u001a\u00020\u001e2\f\u00104\u001a\b\u0012\u0004\u0012\u0002050/2\f\u00106\u001a\b\u0012\u0004\u0012\u0002070/2\u0006\u0010&\u001a\u00020'H\u0002J\u000e\u00108\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u000209J\f\u0010:\u001a\u00020\u0007*\u00020\fH\u0002J\u0018\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020>H\u0002J\u000e\u0010@\u001a\u00020A2\u0006\u0010\u001b\u001a\u000209J\u0018\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020\u0007H\u0002J\u0010\u0010F\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u000209H\u0002J\u0010\u0010G\u001a\u00020\u00072\u0006\u0010H\u001a\u00020\nH\u0002J\u0010\u0010I\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u000209H\u0002J\u001e\u0010J\u001a\u00020+2\u0006\u0010K\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010N\u001a\u00020+2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002070/2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\"\u0010P\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u00010\u001cH\u0002J*\u0010P\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020S2\u0006\u0010$\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u00010\u001cH\u0002J \u0010T\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u00010\u001cJ\u0018\u0010U\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020V2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010;\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper;", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "moduleName", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "useOldInlineClassesManglingScheme", Argument.Delimiters.none, "typePreprocessor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/types/KotlinType;", "namePreprocessor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/JvmOverloads;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "typeSystem", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "getTypeSystem", "()Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "typeMappingConfiguration", "org/jetbrains/kotlin/codegen/state/KotlinTypeMapper$typeMappingConfiguration$1", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$typeMappingConfiguration$1;", "mapReturnType", "Lorg/jetbrains/org/objectweb/asm/Type;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "sw", "Lorg/jetbrains/kotlin/codegen/signature/JvmSignatureWriter;", "returnType", "mapClass", "classifier", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "mapTypeCommon", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "mode", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "mapType", "signatureVisitor", "writeGenericType", Argument.Delimiters.none, "asmType", "writeInnerParts", "innerTypesAsList", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/PossiblyInnerType;", "index", Argument.Delimiters.none, "writeGenericArguments", "arguments", "Lorg/jetbrains/kotlin/types/TypeProjection;", "parameters", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "mapFunctionName", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "hasJavaLangRecordSupertype", "shouldMangleByReturnType", "mangleMemberNameIfRequired", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "getModuleName", "mapAsmMethod", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "mapSignature", "Lorg/jetbrains/kotlin/resolve/jvm/jvmSignature/JvmMethodGenericSignature;", "f", "skipGenericSignature", "forceBoxedReturnType", "isJvmPrimitive", "kotlinType", "isBoxMethodForInlineClass", "writeFieldSignature", "backingFieldType", "variableDescriptor", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "writeFormalTypeParameters", "typeParameters", "writeParameter", "callableDescriptor", "kind", "Lorg/jetbrains/kotlin/resolve/jvm/jvmSignature/JvmMethodParameterKind;", "writeParameterType", "writeAdditionalConstructorParameters", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "ContainingClassesInfo", "InternalNameMapper", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinTypeMapper extends KotlinTypeMapperBase {
    private final LanguageVersionSettings languageVersionSettings;
    private final String moduleName;
    private final Function1<ClassDescriptor, String> namePreprocessor;
    private final boolean shouldMangleByReturnType;
    private final KotlinTypeMapper$typeMappingConfiguration$1 typeMappingConfiguration;
    private final Function1<KotlinType, KotlinType> typePreprocessor;
    private final boolean useOldInlineClassesManglingScheme;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ClassId FAKE_CLASS_ID_FOR_BUILTINS = new ClassId(new FqName("kotlin.jvm.internal"), new FqName("Intrinsics.Kotlin"), false);
    public static final String BOX_JVM_METHOD_NAME = InlineClassDescriptorResolver.BOX_METHOD_NAME.toString() + "-impl";
    public static final String UNBOX_JVM_METHOD_NAME = InlineClassDescriptorResolver.UNBOX_METHOD_NAME.toString() + "-impl";

    /* JADX WARN: Type inference failed for: r1v1, types: [org.jetbrains.kotlin.codegen.state.KotlinTypeMapper$typeMappingConfiguration$1] */
    public KotlinTypeMapper(String str, LanguageVersionSettings languageVersionSettings, boolean z, Function1<? super KotlinType, ? extends KotlinType> function1, Function1<? super ClassDescriptor, String> function2) {
        str.getClass();
        languageVersionSettings.getClass();
        this.moduleName = str;
        this.languageVersionSettings = languageVersionSettings;
        this.useOldInlineClassesManglingScheme = z;
        this.typePreprocessor = function1;
        this.namePreprocessor = function2;
        this.typeMappingConfiguration = new TypeMappingConfiguration<Type>() { // from class: org.jetbrains.kotlin.codegen.state.KotlinTypeMapper$typeMappingConfiguration$1
            public KotlinType commonSupertype(Collection<? extends KotlinType> types) {
                types.getClass();
                KotlinType kotlinTypeCommonSupertype = CommonSupertypes.commonSupertype(types);
                kotlinTypeCommonSupertype.getClass();
                return kotlinTypeCommonSupertype;
            }

            public String getPredefinedFullInternalNameForClass(ClassDescriptor classDescriptor) {
                classDescriptor.getClass();
                Function1 function3 = this.this$0.namePreprocessor;
                if (function3 != null) {
                    return (String) function3.invoke(classDescriptor);
                }
                return null;
            }

            public String getPredefinedInternalNameForClass(ClassDescriptor classDescriptor) {
                classDescriptor.getClass();
                Type predefinedTypeForClass = getPredefinedTypeForClass(classDescriptor);
                if (predefinedTypeForClass != null) {
                    return predefinedTypeForClass.getInternalName();
                }
                return null;
            }

            public Type getPredefinedTypeForClass(ClassDescriptor classDescriptor) {
                classDescriptor.getClass();
                return null;
            }

            public KotlinType preprocessType(KotlinType kotlinType) {
                kotlinType.getClass();
                Function1 function3 = this.this$0.typePreprocessor;
                if (function3 != null) {
                    return (KotlinType) function3.invoke(kotlinType);
                }
                return null;
            }

            public void processErrorType(KotlinType kotlinType, ClassDescriptor descriptor) {
                kotlinType.getClass();
                descriptor.getClass();
            }
        };
        this.shouldMangleByReturnType = languageVersionSettings.supportsFeature(LanguageFeature.MangleClassMembersReturningInlineClasses);
    }

    public static Type a(KotlinTypeMapper kotlinTypeMapper, JvmSignatureWriter jvmSignatureWriter, KotlinTypeMarker kotlinTypeMarker, TypeMappingMode typeMappingMode) {
        kotlinTypeMarker.getClass();
        typeMappingMode.getClass();
        return kotlinTypeMapper.mapType((KotlinType) kotlinTypeMarker, jvmSignatureWriter, typeMappingMode);
    }

    public static Unit b(KotlinTypeMapper kotlinTypeMapper, JvmSignatureWriter jvmSignatureWriter, KotlinType kotlinType, Type type, TypeMappingMode typeMappingMode) {
        kotlinType.getClass();
        type.getClass();
        typeMappingMode.getClass();
        kotlinTypeMapper.writeGenericType(kotlinType, type, jvmSignatureWriter, typeMappingMode);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x005d  */
    /* JADX WARN: Code duplicated, block: B:27:0x0063  */
    /* JADX WARN: Code duplicated, block: B:30:0x0068 A[ADDED_TO_REGION, ORIG_RETURN, RETURN] */
    private final boolean forceBoxedReturnType(FunctionDescriptor descriptor) {
        if (isBoxMethodForInlineClass(descriptor)) {
            return true;
        }
        KotlinType returnType = descriptor.getReturnType();
        returnType.getClass();
        if ((ExpressionTypingUtils.isFunctionExpression(descriptor) || ExpressionTypingUtils.isFunctionLiteral(descriptor)) && InlineClassesUtilsKt.isInlineClassType(returnType)) {
            return true;
        }
        if (!isJvmPrimitive(returnType)) {
            if (InlineClassesUtilsKt.isInlineClassType(returnType)) {
                return false;
            }
            return false;
        }
        Set allOverriddenDescriptors = DescriptorUtils.getAllOverriddenDescriptors(descriptor);
        allOverriddenDescriptors.getClass();
        Set set = allOverriddenDescriptors;
        if ((set instanceof Collection) && set.isEmpty()) {
            if (InlineClassesUtilsKt.isInlineClassType(returnType)) {
                return false;
            }
            return false;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            KotlinType returnType2 = ((FunctionDescriptor) it.next()).getReturnType();
            returnType2.getClass();
            if (!isJvmPrimitive(returnType2)) {
            }
        }
        if (InlineClassesUtilsKt.isInlineClassType(returnType) || !(descriptor instanceof JavaMethodDescriptor)) {
            return false;
        }
        return true;
    }

    private final String getModuleName(CallableMemberDescriptor descriptor) {
        String string;
        DeserializedClassDescriptor deserializedClassDescriptor = (ClassOrPackageFragmentDescriptor) DescriptorUtils.getParentOfType(descriptor, ClassOrPackageFragmentDescriptor.class, false);
        if (!(deserializedClassDescriptor instanceof DeserializedClassDescriptor)) {
            if (descriptor instanceof DeserializedMemberDescriptor) {
                JvmPackagePartSource containerSource = ((DeserializedMemberDescriptor) descriptor).getContainerSource();
                if (containerSource instanceof JvmPackagePartSource) {
                    return containerSource.getModuleName();
                }
            }
            return this.moduleName;
        }
        DeserializedClassDescriptor deserializedClassDescriptor2 = deserializedClassDescriptor;
        ProtoBuf.Class classProto = deserializedClassDescriptor2.getClassProto();
        NameResolver nameResolver = deserializedClassDescriptor2.getC().getNameResolver();
        GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.classModuleName;
        generatedExtension.getClass();
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(classProto, generatedExtension);
        return (num == null || (string = nameResolver.getString(num.intValue())) == null) ? "main" : string;
    }

    private final boolean hasJavaLangRecordSupertype(ClassDescriptor classDescriptor) {
        Collection supertypes = classDescriptor.getTypeConstructor().getSupertypes();
        supertypes.getClass();
        Collection collection = supertypes;
        if (collection.isEmpty()) {
            return false;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (KotlinBuiltIns.isConstructedFromGivenClass((KotlinType) it.next(), JdkClassesKt.getJAVA_LANG_RECORD_FQ_NAME())) {
                return true;
            }
        }
        return false;
    }

    private final boolean isBoxMethodForInlineClass(FunctionDescriptor descriptor) {
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        containingDeclaration.getClass();
        return InlineClassesUtilsKt.isInlineClass(containingDeclaration) && descriptor.getKind() == CallableMemberDescriptor.Kind.SYNTHESIZED && Intrinsics.areEqual(descriptor.getName(), InlineClassDescriptorResolver.BOX_METHOD_NAME);
    }

    private final boolean isJvmPrimitive(KotlinType kotlinType) {
        return KotlinBuiltIns.isPrimitiveType(kotlinType);
    }

    private final String mangleMemberNameIfRequired(String name, CallableMemberDescriptor descriptor) {
        String partSimpleNameForMangling;
        DeclarationDescriptor containingDeclaration = descriptor.getDeclarationDescriptor();
        containingDeclaration.getClass();
        if ((containingDeclaration instanceof ScriptDescriptor) && (descriptor instanceof PropertyDescriptor)) {
            return name;
        }
        if (InlineClassDescriptorResolver.isSynthesizedBoxMethod(descriptor)) {
            return BOX_JVM_METHOD_NAME;
        }
        if (InlineClassDescriptorResolver.isSynthesizedUnboxMethod(descriptor)) {
            return UNBOX_JVM_METHOD_NAME;
        }
        if (InlineClassDescriptorResolver.isSpecializedEqualsMethod(descriptor) || (descriptor instanceof ConstructorDescriptor)) {
            return name;
        }
        String manglingSuffixBasedOnKotlinSignature = InlineClassManglingUtilsKt.getManglingSuffixBasedOnKotlinSignature(descriptor, this.shouldMangleByReturnType, this.useOldInlineClassesManglingScheme);
        if (manglingSuffixBasedOnKotlinSignature != null) {
            name = name + manglingSuffixBasedOnKotlinSignature;
        }
        String strSanitizeNameIfNeeded = CommonVariableAsmNameManglingUtils.sanitizeNameIfNeeded(name, this.languageVersionSettings);
        if (DescriptorUtils.isTopLevelDeclaration(descriptor)) {
            if (DescriptorVisibilities.isPrivate(descriptor.getVisibility()) && !Intrinsics.areEqual("<clinit>", strSanitizeNameIfNeeded) && (partSimpleNameForMangling = INSTANCE.getPartSimpleNameForMangling(descriptor)) != null) {
                return strSanitizeNameIfNeeded + '$' + partSimpleNameForMangling;
            }
        } else if (descriptor.getVisibility() == DescriptorVisibilities.INTERNAL && !DescriptorUtilsKt.isPublishedApi(descriptor)) {
            return InternalNameMapper.INSTANCE.mangleInternalName(strSanitizeNameIfNeeded, getModuleName(descriptor));
        }
        return strSanitizeNameIfNeeded;
    }

    public static /* synthetic */ Type mapReturnType$default(KotlinTypeMapper kotlinTypeMapper, CallableDescriptor callableDescriptor, JvmSignatureWriter jvmSignatureWriter, int i, Object obj) {
        if ((i & 2) != 0) {
            jvmSignatureWriter = null;
        }
        return kotlinTypeMapper.mapReturnType(callableDescriptor, jvmSignatureWriter);
    }

    private final JvmMethodGenericSignature mapSignature(FunctionDescriptor f, boolean skipGenericSignature) {
        List listListOf;
        if (f.getInitialSignatureDescriptor() != null && !Intrinsics.areEqual(f, f.getInitialSignatureDescriptor()) && (SpecialBuiltinMembers.getOverriddenBuiltinReflectingJvmDescriptor(f) == null || (f.getContainingDeclaration().m84getOriginal() instanceof JavaClassDescriptor))) {
            FunctionDescriptor initialSignatureDescriptor = f.getInitialSignatureDescriptor();
            initialSignatureDescriptor.getClass();
            return mapSignature(initialSignatureDescriptor, skipGenericSignature);
        }
        if (f instanceof TypeAliasConstructorDescriptor) {
            return mapSignature(((TypeAliasConstructorDescriptor) f).getUnderlyingConstructorDescriptor().m12getOriginal(), skipGenericSignature);
        }
        if (f instanceof FunctionImportedFromObject) {
            return mapSignature((FunctionDescriptor) ((FunctionImportedFromObject) f).getCallableFromObject(), skipGenericSignature);
        }
        if (JvmCodegenUtil.isDeclarationOfBigArityFunctionInvoke(f) || JvmCodegenUtil.isDeclarationOfBigArityCreateCoroutineMethod(f)) {
            listListOf = CollectionsKt.listOf(DescriptorUtilsKt.getBuiltIns(f).getArrayType(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns(f).getNullableAnyType()));
        } else {
            List<ValueParameterDescriptor> valueParameters = f.getValueParameters();
            valueParameters.getClass();
            List<ValueParameterDescriptor> list = valueParameters;
            listListOf = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                listListOf.add(((ValueParameterDescriptor) it.next()).getType());
            }
        }
        JvmSignatureWriter jvmSignatureWriter = new JvmSignatureWriter();
        if (f instanceof ClassConstructorDescriptor) {
            jvmSignatureWriter.writeParametersStart();
            writeAdditionalConstructorParameters((ClassConstructorDescriptor) f, jvmSignatureWriter);
            for (Object obj : listListOf) {
                obj.getClass();
                writeParameter(jvmSignatureWriter, (KotlinType) obj, f);
            }
            INSTANCE.writeVoidReturn(jvmSignatureWriter);
        } else {
            List<TypeParameterDescriptor> typeParameters = DescriptorUtils.getDirectMember(f).getTypeParameters();
            typeParameters.getClass();
            writeFormalTypeParameters(typeParameters, jvmSignatureWriter);
            jvmSignatureWriter.writeParametersStart();
            for (ParameterDescriptor parameterDescriptor : f.getContextReceiverParameters()) {
                JvmMethodParameterKind jvmMethodParameterKind = JvmMethodParameterKind.CONTEXT_RECEIVER;
                KotlinType type = parameterDescriptor.getType();
                type.getClass();
                writeParameter(jvmSignatureWriter, jvmMethodParameterKind, type, f);
            }
            ParameterDescriptor extensionReceiverParameter = f.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                JvmMethodParameterKind jvmMethodParameterKind2 = JvmMethodParameterKind.RECEIVER;
                KotlinType type2 = extensionReceiverParameter.getType();
                type2.getClass();
                writeParameter(jvmSignatureWriter, jvmMethodParameterKind2, type2, f);
            }
            for (Object obj2 : listListOf) {
                obj2.getClass();
                KotlinType kotlinTypeMakeNullable = (KotlinType) obj2;
                if (MethodSignatureMappingKt.forceSingleValueParameterBoxing(f)) {
                    kotlinTypeMakeNullable = TypeUtils.makeNullable(kotlinTypeMakeNullable);
                }
                kotlinTypeMakeNullable.getClass();
                writeParameter(jvmSignatureWriter, kotlinTypeMakeNullable, f);
            }
            jvmSignatureWriter.writeReturnType();
            mapReturnType(f, jvmSignatureWriter);
            jvmSignatureWriter.writeReturnTypeEnd();
        }
        JvmMethodGenericSignature jvmMethodGenericSignatureMakeJvmMethodSignature = jvmSignatureWriter.makeJvmMethodSignature(mapFunctionName(f));
        jvmMethodGenericSignatureMakeJvmMethodSignature.getClass();
        SpecialGenericSignatures.SpecialSignatureInfo specialSignatureInfo = BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo(f);
        if (specialSignatureInfo == null) {
            return jvmMethodGenericSignatureMakeJvmMethodSignature;
        }
        return new JvmMethodGenericSignature(jvmMethodGenericSignatureMakeJvmMethodSignature.getAsmMethod(), jvmMethodGenericSignatureMakeJvmMethodSignature.getParameters(), CodegenUtilKt.replaceValueParametersIn(specialSignatureInfo, jvmMethodGenericSignatureMakeJvmMethodSignature.getGenericsSignature()));
    }

    public static /* synthetic */ Type mapType$default(KotlinTypeMapper kotlinTypeMapper, KotlinType kotlinType, JvmSignatureWriter jvmSignatureWriter, TypeMappingMode typeMappingMode, int i, Object obj) {
        if ((i & 2) != 0) {
            jvmSignatureWriter = null;
        }
        if ((i & 4) != 0) {
            typeMappingMode = TypeMappingMode.DEFAULT;
        }
        return kotlinTypeMapper.mapType(kotlinType, jvmSignatureWriter, typeMappingMode);
    }

    @JvmStatic
    public static final Type mapUnderlyingTypeOfInlineClassType(KotlinTypeMarker kotlinTypeMarker, KotlinTypeMapperBase kotlinTypeMapperBase) {
        return INSTANCE.mapUnderlyingTypeOfInlineClassType(kotlinTypeMarker, kotlinTypeMapperBase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void writeAdditionalConstructorParameters(ClassConstructorDescriptor descriptor, JvmSignatureWriter sw) {
        boolean z = descriptor.getKind() == CallableMemberDescriptor.Kind.SYNTHESIZED;
        ClassDescriptor dispatchReceiverParameterForConstructorCall = JvmCodegenUtil.getDispatchReceiverParameterForConstructorCall(descriptor);
        if (!z && dispatchReceiverParameterForConstructorCall != null) {
            JvmMethodParameterKind jvmMethodParameterKind = JvmMethodParameterKind.OUTER;
            SimpleType defaultType = dispatchReceiverParameterForConstructorCall.getDefaultType();
            defaultType.getClass();
            writeParameter(sw, jvmMethodParameterKind, defaultType, descriptor);
        }
        ClassDescriptor containingDeclaration = descriptor.m6getContainingDeclaration();
        if (z) {
            return;
        }
        if (containingDeclaration.getKind() == ClassKind.ENUM_CLASS || containingDeclaration.getKind() == ClassKind.ENUM_ENTRY) {
            JvmMethodParameterKind jvmMethodParameterKind2 = JvmMethodParameterKind.ENUM_NAME_OR_ORDINAL;
            SimpleType stringType = DescriptorUtilsKt.getBuiltIns(descriptor).getStringType();
            stringType.getClass();
            writeParameter(sw, jvmMethodParameterKind2, stringType, descriptor);
            SimpleType intType = DescriptorUtilsKt.getBuiltIns(descriptor).getIntType();
            intType.getClass();
            writeParameter(sw, jvmMethodParameterKind2, intType, descriptor);
        }
    }

    @JvmStatic
    public static final void writeFormalTypeParameter(TypeSystemCommonBackendContext typeSystemCommonBackendContext, TypeParameterMarker typeParameterMarker, JvmSignatureWriter jvmSignatureWriter, Function2<? super KotlinTypeMarker, ? super TypeMappingMode, Type> function2) {
        INSTANCE.writeFormalTypeParameter(typeSystemCommonBackendContext, typeParameterMarker, jvmSignatureWriter, function2);
    }

    private final void writeFormalTypeParameters(List<? extends TypeParameterDescriptor> typeParameters, final JvmSignatureWriter sw) {
        if (sw.skipGenericSignature()) {
            return;
        }
        for (TypeParameterDescriptor typeParameterDescriptor : typeParameters) {
            if (!typeParameterDescriptor.getName().isSpecial()) {
                INSTANCE.writeFormalTypeParameter(SimpleClassicTypeSystemContext.INSTANCE, typeParameterDescriptor, sw, new Function2() { // from class: md8
                    public final Object invoke(Object obj, Object obj2) {
                        return KotlinTypeMapper.a(this.b, sw, (KotlinTypeMarker) obj, (TypeMappingMode) obj2);
                    }
                });
            }
        }
    }

    private final void writeGenericArguments(JvmSignatureWriter signatureVisitor, PossiblyInnerType type, TypeMappingMode mode) {
        FunctionClassDescriptor classDescriptor = type.getClassDescriptor();
        List<? extends TypeParameterDescriptor> declaredTypeParameters = classDescriptor.getDeclaredTypeParameters();
        declaredTypeParameters.getClass();
        List<? extends TypeProjection> arguments = type.getArguments();
        if (classDescriptor instanceof FunctionClassDescriptor) {
            FunctionClassDescriptor functionClassDescriptor = classDescriptor;
            if (functionClassDescriptor.hasBigArity() || Intrinsics.areEqual(functionClassDescriptor.getFunctionTypeKind(), FunctionTypeKind.KFunction.INSTANCE) || Intrinsics.areEqual(functionClassDescriptor.getFunctionTypeKind(), FunctionTypeKind.KSuspendFunction.INSTANCE)) {
                writeGenericArguments(signatureVisitor, CollectionsKt.listOf(CollectionsKt.last(arguments)), CollectionsKt.listOf(CollectionsKt.last(declaredTypeParameters)), mode);
                return;
            }
        }
        writeGenericArguments(signatureVisitor, arguments, declaredTypeParameters, mode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type writeGenericArguments$lambda$0$0(KotlinTypeMapper kotlinTypeMapper, KotlinTypeMarker kotlinTypeMarker, JvmSignatureWriter jvmSignatureWriter, TypeMappingMode typeMappingMode) {
        kotlinTypeMarker.getClass();
        jvmSignatureWriter.getClass();
        typeMappingMode.getClass();
        return kotlinTypeMapper.mapType((KotlinType) kotlinTypeMarker, jvmSignatureWriter, typeMappingMode);
    }

    private final void writeGenericType(KotlinType type, Type asmType, JvmSignatureWriter signatureVisitor, TypeMappingMode mode) {
        if (signatureVisitor == null) {
            return;
        }
        if (signatureVisitor.skipGenericSignature() || INSTANCE.hasNothingInNonContravariantPosition(type) || type.getArguments().isEmpty()) {
            signatureVisitor.writeAsmType(asmType);
            return;
        }
        PossiblyInnerType possiblyInnerTypeBuildPossiblyInnerType = TypeParameterUtilsKt.buildPossiblyInnerType(type);
        if (possiblyInnerTypeBuildPossiblyInnerType == null) {
            k2d.a("possiblyInnerType with arguments should not be null");
            return;
        }
        List<PossiblyInnerType> listSegments = possiblyInnerTypeBuildPossiblyInnerType.segments();
        Iterator<PossiblyInnerType> it = listSegments.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (!it.next().getArguments().isEmpty()) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0 || listSegments.size() == 1) {
            signatureVisitor.writeClassBegin(asmType);
            writeGenericArguments(signatureVisitor, possiblyInnerTypeBuildPossiblyInnerType, mode);
        } else {
            PossiblyInnerType possiblyInnerType = listSegments.get(i);
            SimpleType defaultType = possiblyInnerType.getClassDescriptor().getDefaultType();
            defaultType.getClass();
            signatureVisitor.writeOuterClassBegin(asmType, mapType$default(this, defaultType, null, null, 6, null).getInternalName());
            writeGenericArguments(signatureVisitor, possiblyInnerType, mode);
            writeInnerParts(listSegments, signatureVisitor, mode, i + 1);
        }
        signatureVisitor.writeClassEnd();
    }

    private final void writeInnerParts(List<PossiblyInnerType> innerTypesAsList, JvmSignatureWriter signatureVisitor, TypeMappingMode mode, int index) {
        for (PossiblyInnerType possiblyInnerType : innerTypesAsList.subList(index, innerTypesAsList.size())) {
            signatureVisitor.writeInnerClass(INSTANCE.getJvmShortName(possiblyInnerType.getClassDescriptor()));
            writeGenericArguments(signatureVisitor, possiblyInnerType, mode);
        }
    }

    private final void writeParameter(JvmSignatureWriter sw, JvmMethodParameterKind kind, KotlinType type, CallableDescriptor callableDescriptor) {
        sw.writeParameterType(kind);
        writeParameterType(sw, type, callableDescriptor);
        sw.writeParameterTypeEnd();
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    @Override // org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase
    public TypeSystemCommonBackendContext getTypeSystem() {
        return SimpleClassicTypeSystemContext.INSTANCE;
    }

    public final Method mapAsmMethod(FunctionDescriptor descriptor) {
        descriptor.getClass();
        Method asmMethod = mapSignature(descriptor, true).getAsmMethod();
        asmMethod.getClass();
        return asmMethod;
    }

    @Override // org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase
    public Type mapClass(ClassifierDescriptor classifier) {
        classifier.getClass();
        SimpleType defaultType = classifier.getDefaultType();
        defaultType.getClass();
        return mapType(defaultType, null, TypeMappingMode.CLASS_DECLARATION);
    }

    public final String mapFunctionName(FunctionDescriptor descriptor) {
        String jvmName;
        descriptor.getClass();
        if (!(descriptor instanceof JavaCallableMemberDescriptor) && (jvmName = DescriptorUtils.getJvmName(descriptor)) != null) {
            return jvmName;
        }
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(descriptor);
        if (jvmMethodNameIfSpecial != null) {
            return jvmMethodNameIfSpecial;
        }
        if (!(descriptor instanceof PropertyAccessorDescriptor)) {
            if (ExpressionTypingUtils.isFunctionLiteral(descriptor)) {
                String strAsString = OperatorNameConventions.INVOKE.asString();
                strAsString.getClass();
                return strAsString;
            }
            if (ExpressionTypingUtils.isLocalFunction(descriptor) || ExpressionTypingUtils.isFunctionExpression(descriptor)) {
                String strAsString2 = OperatorNameConventions.INVOKE.asString();
                strAsString2.getClass();
                return strAsString2;
            }
            String strAsString3 = descriptor.getName().asString();
            strAsString3.getClass();
            return mangleMemberNameIfRequired(strAsString3, descriptor);
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) descriptor).getCorrespondingProperty();
        correspondingProperty.getClass();
        DeclarationDescriptor containingDeclaration = correspondingProperty.getDeclarationDescriptor();
        containingDeclaration.getClass();
        if (DescriptorUtils.isAnnotationClass(containingDeclaration) && !AnnotationUtilKt.hasJvmStaticAnnotation(correspondingProperty) && !AnnotationUtilKt.hasJvmStaticAnnotation(descriptor)) {
            String strAsString4 = correspondingProperty.getName().asString();
            strAsString4.getClass();
            return strAsString4;
        }
        ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
        if (classDescriptor == null || !hasJavaLangRecordSupertype(classDescriptor)) {
            String strAsString5 = correspondingProperty.getName().asString();
            strAsString5.getClass();
            return mangleMemberNameIfRequired(descriptor instanceof PropertyGetterDescriptor ? JvmAbi.getterName(strAsString5) : JvmAbi.setterName(strAsString5), descriptor);
        }
        String strAsString6 = correspondingProperty.getName().asString();
        strAsString6.getClass();
        return strAsString6;
    }

    public final Type mapReturnType(CallableDescriptor descriptor, JvmSignatureWriter sw) {
        descriptor.getClass();
        KotlinType returnType = descriptor.getReturnType();
        if (returnType == null) {
            w04.a("Function has no return type: ", descriptor);
            return null;
        }
        if (descriptor instanceof ConstructorDescriptor) {
            Type type = Type.VOID_TYPE;
            type.getClass();
            return type;
        }
        if (DescriptorBasedTypeSignatureMappingKt.hasVoidReturnType(descriptor)) {
            if (sw != null) {
                sw.writeAsmType(Type.VOID_TYPE);
            }
            Type type2 = Type.VOID_TYPE;
            type2.getClass();
            return type2;
        }
        if (!(descriptor instanceof FunctionDescriptor) || !forceBoxedReturnType((FunctionDescriptor) descriptor)) {
            return mapReturnType(descriptor, sw, returnType);
        }
        KotlinType returnType2 = descriptor.getReturnType();
        returnType2.getClass();
        return mapType(returnType2, sw, TypeMappingMode.RETURN_TYPE_BOXED);
    }

    public final Type mapType(KotlinType type, final JvmSignatureWriter signatureVisitor, TypeMappingMode mode) {
        type.getClass();
        mode.getClass();
        return (Type) DescriptorBasedTypeSignatureMappingKt.mapType(type, AsmTypeFactory.INSTANCE, mode, this.typeMappingConfiguration, signatureVisitor, new Function3() { // from class: ld8
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return KotlinTypeMapper.b(this.b, signatureVisitor, (KotlinType) obj, (Type) obj2, (TypeMappingMode) obj3);
            }
        });
    }

    @Override // org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase
    public Type mapTypeCommon(KotlinTypeMarker type, TypeMappingMode mode) {
        type.getClass();
        mode.getClass();
        return mapType((KotlinType) type, null, mode);
    }

    public final void writeFieldSignature(KotlinType backingFieldType, VariableDescriptor variableDescriptor, JvmSignatureWriter sw) {
        backingFieldType.getClass();
        variableDescriptor.getClass();
        sw.getClass();
        if (variableDescriptor.isVar()) {
            writeParameterType(sw, backingFieldType, variableDescriptor);
        } else {
            mapReturnType(variableDescriptor, sw, backingFieldType);
        }
    }

    public final void writeParameterType(JvmSignatureWriter sw, KotlinType type, CallableDescriptor callableDescriptor) {
        sw.getClass();
        type.getClass();
        if (!sw.skipGenericSignature()) {
            TypeMappingMode typeMappingModeExtractTypeMappingModeFromAnnotation = TypeMappingUtil.extractTypeMappingModeFromAnnotation(callableDescriptor, type, false, false);
            if (typeMappingModeExtractTypeMappingModeFromAnnotation == null) {
                typeMappingModeExtractTypeMappingModeFromAnnotation = (!TypeMappingUtil.isMethodWithDeclarationSiteWildcards(callableDescriptor) || type.getArguments().isEmpty()) ? TypeMappingModeExtensionsKt.getOptimalModeForValueParameter(getTypeSystem(), type) : TypeMappingMode.GENERIC_ARGUMENT;
            }
            mapType(type, sw, typeMappingModeExtractTypeMappingModeFromAnnotation);
            return;
        }
        if (InlineClassesUtilsKt.isInlineClassType(type) && (callableDescriptor instanceof JavaMethodDescriptor)) {
            mapType(type, sw, TypeMappingMode.GENERIC_ARGUMENT);
        } else {
            mapType(type, sw, TypeMappingMode.DEFAULT);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$ContainingClassesInfo;", Argument.Delimiters.none, "facadeClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "implClassId", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/name/ClassId;)V", "getFacadeClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getImplClassId", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ContainingClassesInfo {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final ClassId facadeClassId;
        private final ClassId implClassId;

        public ContainingClassesInfo(ClassId classId, ClassId classId2) {
            classId.getClass();
            classId2.getClass();
            this.facadeClassId = classId;
            this.implClassId = classId2;
        }

        public final ClassId getFacadeClassId() {
            return this.facadeClassId;
        }

        public final ClassId getImplClassId() {
            return this.implClassId;
        }

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$ContainingClassesInfo$Companion;", Argument.Delimiters.none, "<init>", "()V", "forPackageMember", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$ContainingClassesInfo;", "facadeName", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "partName", "forPackageMember$org_jetbrains_kotlin_backend", "forClassMember", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "forClassMember$org_jetbrains_kotlin_backend", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final ContainingClassesInfo forClassMember$org_jetbrains_kotlin_backend(ClassId classId) {
                classId.getClass();
                return new ContainingClassesInfo(classId, classId);
            }

            public final ContainingClassesInfo forPackageMember$org_jetbrains_kotlin_backend(JvmClassName facadeName, JvmClassName partName) {
                facadeName.getClass();
                partName.getClass();
                ClassId.Companion companion = ClassId.Companion;
                FqName fqNameForTopLevelClassMaybeWithDollars = facadeName.getFqNameForTopLevelClassMaybeWithDollars();
                fqNameForTopLevelClassMaybeWithDollars.getClass();
                ClassId classId = companion.topLevel(fqNameForTopLevelClassMaybeWithDollars);
                FqName fqNameForTopLevelClassMaybeWithDollars2 = partName.getFqNameForTopLevelClassMaybeWithDollars();
                fqNameForTopLevelClassMaybeWithDollars2.getClass();
                return new ContainingClassesInfo(classId, companion.topLevel(fqNameForTopLevelClassMaybeWithDollars2));
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0002J\u001c\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007b\u0002\b\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u001bH\u0002J\u0012\u0010\u0019\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012J\u001e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J$\u0010\u001e\u001a\u00020\u001f*\u00020&2\b\u0010 \u001a\u0004\u0018\u00010'2\u0006\u0010\"\u001a\u00020(2\u0006\u0010$\u001a\u00020%JV\u0010)\u001a\u00020**\u00020&2\u0006\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020(0.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020'0.2\u0006\u0010$\u001a\u00020%2\u001e\u00100\u001a\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001001J³\u0001\u00102\u001a\u00020**\u00020&2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020(0.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020'0.2\u0006\u0010$\u001a\u00020%2\f\u00103\u001a\b\u0012\u0004\u0012\u00020*042u\u00105\u001aq\u0012\u0013\u0012\u001107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0013\u0012\u00110%¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b($\u0012\u0004\u0012\u00020*06J\u0012\u0010@\u001a\u0004\u0018\u00010\u00172\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020,H\u0002J:\u0010E\u001a\u00020**\u00020&2\u0006\u0010F\u001a\u00020'2\u0006\u0010D\u001a\u00020,2\u0018\u00100\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00100GH\u0007b\u0002\b\u0015R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010=\u001a\u00020\u00178\u0006X\u0087\u0004\u0092\u0002\u0002\b>¢\u0006\u0002\n\u0000R\u0015\u0010?\u001a\u00020\u00178\u0006X\u0087\u0004\u0092\u0002\u0002\b>¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$Companion;", Argument.Delimiters.none, "<init>", "()V", "getContainingClassesForDeserializedCallable", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$ContainingClassesInfo;", "deserializedDescriptor", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DescriptorWithContainerSource;", "getContainerClassIdForClassDescriptor", "Lorg/jetbrains/kotlin/name/ClassId;", "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "FAKE_CLASS_ID_FOR_BUILTINS", "getPackageMemberContainingClassesInfo", "descriptor", "mapUnderlyingTypeOfInlineClassType", "Lorg/jetbrains/org/objectweb/asm/Type;", "kotlinType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "Lkotlin/jvm/JvmStatic;", "getJvmShortName", Argument.Delimiters.none, "klass", "hasNothingInNonContravariantPosition", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/KotlinType;", "Lorg/jetbrains/kotlin/types/model/TypeSystemContext;", ModuleXmlParser.TYPE, "getVarianceForWildcard", "Lorg/jetbrains/kotlin/types/Variance;", "parameter", "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "projection", "Lorg/jetbrains/kotlin/types/TypeProjection;", "mode", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "writeGenericArguments", Argument.Delimiters.none, "signatureVisitor", "Lorg/jetbrains/kotlin/codegen/signature/JvmSignatureWriter;", "arguments", Argument.Delimiters.none, "parameters", "mapType", "Lkotlin/Function3;", "processGenericArguments", "processUnboundedWildcard", "Lkotlin/Function0;", "processTypeArgument", "Lkotlin/Function5;", Argument.Delimiters.none, "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "index", "projectionKind", "parameterVariance", "BOX_JVM_METHOD_NAME", "Lkotlin/jvm/JvmField;", "UNBOX_JVM_METHOD_NAME", "getPartSimpleNameForMangling", "callableDescriptor", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "writeVoidReturn", "sw", "writeFormalTypeParameter", "typeParameter", "Lkotlin/Function2;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static Unit a(JvmSignatureWriter jvmSignatureWriter, Function3 function3, int i, KotlinTypeMarker kotlinTypeMarker, Variance variance, Variance variance2, TypeMappingMode typeMappingMode) {
            kotlinTypeMarker.getClass();
            variance.getClass();
            variance2.getClass();
            typeMappingMode.getClass();
            jvmSignatureWriter.writeTypeArgument(variance);
            function3.invoke(kotlinTypeMarker, jvmSignatureWriter, typeMappingMode);
            jvmSignatureWriter.writeTypeArgumentEnd();
            return Unit.INSTANCE;
        }

        public static Unit b(JvmSignatureWriter jvmSignatureWriter) {
            jvmSignatureWriter.writeUnboundedWildcard();
            return Unit.INSTANCE;
        }

        private final ClassId getContainerClassIdForClassDescriptor(ClassDescriptor classDescriptor) {
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            if (classId == null) {
                w04.a("Deserialized class should have a ClassId: ", classDescriptor);
                return null;
            }
            String str = DescriptorUtils.isInterface(classDescriptor) ? "$DefaultImpls" : null;
            if (str == null) {
                return classId;
            }
            String str2 = classId.getRelativeClassName().shortName().asString() + str;
            FqName packageFqName = classId.getPackageFqName();
            Name nameIdentifier = Name.identifier(str2);
            nameIdentifier.getClass();
            return new ClassId(packageFqName, nameIdentifier);
        }

        private final ContainingClassesInfo getContainingClassesForDeserializedCallable(DescriptorWithContainerSource deserializedDescriptor) {
            ContainingClassesInfo containingClassesInfoForClassMember$org_jetbrains_kotlin_backend;
            DeclarationDescriptor containingDeclaration = deserializedDescriptor.getDeclarationDescriptor();
            containingDeclaration.getClass();
            if (containingDeclaration instanceof PackageFragmentDescriptor) {
                containingClassesInfoForClassMember$org_jetbrains_kotlin_backend = getPackageMemberContainingClassesInfo(deserializedDescriptor);
            } else {
                containingClassesInfoForClassMember$org_jetbrains_kotlin_backend = ContainingClassesInfo.INSTANCE.forClassMember$org_jetbrains_kotlin_backend(getContainerClassIdForClassDescriptor((ClassDescriptor) containingDeclaration));
            }
            if (containingClassesInfoForClassMember$org_jetbrains_kotlin_backend != null) {
                return containingClassesInfoForClassMember$org_jetbrains_kotlin_backend;
            }
            sle.a("Couldn't find container for ", deserializedDescriptor.getName());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getJvmShortName(ClassDescriptor klass) {
            Name shortClassName;
            String strAsString;
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            FqNameUnsafe fqName = DescriptorUtils.getFqName(klass);
            fqName.getClass();
            ClassId classIdMapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(fqName);
            if (classIdMapKotlinToJava != null && (shortClassName = classIdMapKotlinToJava.getShortClassName()) != null && (strAsString = shortClassName.asString()) != null) {
                return strAsString;
            }
            String identifier = SpecialNames.safeIdentifier(klass.getName()).getIdentifier();
            identifier.getClass();
            return identifier;
        }

        private final ContainingClassesInfo getPackageMemberContainingClassesInfo(DescriptorWithContainerSource descriptor) {
            JvmClassName facadeNameForPartName;
            LazyJavaPackageFragment containingDeclaration = descriptor.getDeclarationDescriptor();
            containingDeclaration.getClass();
            if (containingDeclaration instanceof BuiltInsPackageFragment) {
                return new ContainingClassesInfo(KotlinTypeMapper.FAKE_CLASS_ID_FOR_BUILTINS, KotlinTypeMapper.FAKE_CLASS_ID_FOR_BUILTINS);
            }
            JvmClassName implClassNameForDeserialized = UtilKt.getImplClassNameForDeserialized(descriptor);
            if (implClassNameForDeserialized == null) {
                w04.a("No implClassName for ", descriptor);
                return null;
            }
            if (containingDeclaration instanceof LazyJavaPackageFragment) {
                facadeNameForPartName = containingDeclaration.getFacadeNameForPartName(implClassNameForDeserialized);
                if (facadeNameForPartName == null) {
                    return null;
                }
            } else {
                if (!(containingDeclaration instanceof PackageFragmentDescriptor)) {
                    StringBuilder sb = new StringBuilder("Unexpected package fragment for ");
                    sb.append(descriptor);
                    sb.append(": ");
                    sb.append(containingDeclaration);
                    String simpleName = containingDeclaration.getClass().getSimpleName();
                    sb.append(" (");
                    sb.append(simpleName);
                    sb.append(')');
                    throw new AssertionError(sb.toString());
                }
                facadeNameForPartName = implClassNameForDeserialized;
            }
            return ContainingClassesInfo.INSTANCE.forPackageMember$org_jetbrains_kotlin_backend(facadeNameForPartName, implClassNameForDeserialized);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getPartSimpleNameForMangling(CallableMemberDescriptor callableDescriptor) {
            KtFile containingFile = DescriptorToSourceUtils.getContainingFile(callableDescriptor);
            if (containingFile != null) {
                JvmFileClassInfo fileClassInfoNoResolve = JvmFileClassUtil.getFileClassInfoNoResolve(containingFile);
                if (fileClassInfoNoResolve.getWithJvmMultifileClass()) {
                    return fileClassInfoNoResolve.getFileClassFqName().shortName().asString();
                }
                return null;
            }
            DeserializedCallableMemberDescriptor directMember = DescriptorUtils.getDirectMember(callableDescriptor);
            directMember.getClass();
            ContainingClassesInfo containingClassesForDeserializedCallable = getContainingClassesForDeserializedCallable(directMember);
            String strAsString = containingClassesForDeserializedCallable.getFacadeClassId().getShortClassName().asString();
            strAsString.getClass();
            String strAsString2 = containingClassesForDeserializedCallable.getImplClassId().getShortClassName().asString();
            strAsString2.getClass();
            if (Intrinsics.areEqual(strAsString, strAsString2)) {
                return null;
            }
            return strAsString2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void writeVoidReturn(JvmSignatureWriter sw) {
            sw.writeReturnType();
            sw.writeAsmType(Type.VOID_TYPE);
            sw.writeReturnTypeEnd();
        }

        public final Variance getVarianceForWildcard(TypeSystemCommonBackendContext typeSystemCommonBackendContext, TypeParameterMarker typeParameterMarker, TypeArgumentMarker typeArgumentMarker, TypeMappingMode typeMappingMode) {
            Variance varianceConvertVariance;
            TypeVariance variance;
            typeSystemCommonBackendContext.getClass();
            typeArgumentMarker.getClass();
            typeMappingMode.getClass();
            Variance varianceConvertVariance2 = ClassicTypeSystemContextKt.convertVariance(typeSystemCommonBackendContext.getVariance(typeArgumentMarker));
            if (typeParameterMarker == null || (variance = typeSystemCommonBackendContext.getVariance(typeParameterMarker)) == null || (varianceConvertVariance = ClassicTypeSystemContextKt.convertVariance(variance)) == null) {
                varianceConvertVariance = Variance.INVARIANT;
            }
            Variance variance2 = Variance.INVARIANT;
            if (varianceConvertVariance == variance2) {
                return varianceConvertVariance2;
            }
            if (!typeMappingMode.getSkipDeclarationSiteWildcards()) {
                if (varianceConvertVariance2 != variance2 && varianceConvertVariance2 != varianceConvertVariance) {
                    return Variance.OUT_VARIANCE;
                }
                KotlinTypeMarker type = typeSystemCommonBackendContext.getType(typeArgumentMarker);
                if (!typeMappingMode.getSkipDeclarationSiteWildcardsIfPossible() || type == null || ((varianceConvertVariance != Variance.OUT_VARIANCE || !TypeMappingUtil.isMostPreciseCovariantArgument(typeSystemCommonBackendContext, type)) && (varianceConvertVariance != Variance.IN_VARIANCE || !TypeMappingUtil.isMostPreciseContravariantArgument(typeSystemCommonBackendContext, type)))) {
                    return varianceConvertVariance;
                }
            }
            return variance2;
        }

        public final boolean hasNothingInNonContravariantPosition(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker) {
            typeSystemContext.getClass();
            kotlinTypeMarker.getClass();
            if (typeSystemContext.isError(kotlinTypeMarker)) {
                return false;
            }
            TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(kotlinTypeMarker);
            int iArgumentsCount = typeSystemContext.argumentsCount(kotlinTypeMarker);
            for (int i = 0; i < iArgumentsCount; i++) {
                KotlinTypeMarker type = typeSystemContext.getType(typeSystemContext.getArgument(kotlinTypeMarker, i));
                if (type != null) {
                    if (typeSystemContext.isNullableNothing(type)) {
                        return true;
                    }
                    if (typeSystemContext.isNothing(type) && typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructorMarkerTypeConstructor, i)) != TypeVariance.IN) {
                        return true;
                    }
                }
            }
            return false;
        }

        @JvmStatic
        public final Type mapUnderlyingTypeOfInlineClassType(KotlinTypeMarker kotlinType, KotlinTypeMapperBase typeMapper) {
            kotlinType.getClass();
            typeMapper.getClass();
            KotlinTypeMarker unsubstitutedUnderlyingType = typeMapper.getTypeSystem().getUnsubstitutedUnderlyingType(kotlinType);
            if (unsubstitutedUnderlyingType != null) {
                return typeMapper.mapTypeCommon(unsubstitutedUnderlyingType, TypeMappingMode.DEFAULT);
            }
            qu7.a("There should be underlying type for inline class type: ", kotlinType);
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:27:0x009b  */
        public final void processGenericArguments(TypeSystemCommonBackendContext typeSystemCommonBackendContext, List<? extends TypeArgumentMarker> list, List<? extends TypeParameterMarker> list2, TypeMappingMode typeMappingMode, Function0<Unit> function0, Function5<? super Integer, ? super KotlinTypeMarker, ? super Variance, ? super Variance, ? super TypeMappingMode, Unit> function5) {
            Variance varianceConvertVariance;
            TypeVariance variance;
            typeSystemCommonBackendContext.getClass();
            list.getClass();
            list2.getClass();
            typeMappingMode.getClass();
            function0.getClass();
            function5.getClass();
            int i = 0;
            for (Pair pair : AddToStdlibKt.zipWithNulls(list2, list)) {
                int i2 = i + 1;
                TypeParameterMarker typeParameterMarker = (TypeParameterMarker) pair.component1();
                TypeArgumentMarker typeArgumentMarker = (TypeArgumentMarker) pair.component2();
                if (typeArgumentMarker == null) {
                    return;
                }
                KotlinTypeMarker type = typeSystemCommonBackendContext.getType(typeArgumentMarker);
                if (type == null) {
                    function0.invoke();
                } else {
                    if (typeSystemCommonBackendContext.isNothing(type)) {
                        if ((typeParameterMarker != null ? typeSystemCommonBackendContext.getVariance(typeParameterMarker) : null) == TypeVariance.IN) {
                            function0.invoke();
                        }
                    }
                    TypeMappingMode typeMappingModeUpdateArgumentModeFromAnnotations$default = org.jetbrains.kotlin.types.TypeMappingUtil.updateArgumentModeFromAnnotations$default(typeMappingMode, type, typeSystemCommonBackendContext, (Boolean) null, 4, (Object) null);
                    Variance varianceForWildcard = getVarianceForWildcard(typeSystemCommonBackendContext, typeParameterMarker, typeArgumentMarker, typeMappingModeUpdateArgumentModeFromAnnotations$default);
                    if (typeParameterMarker == null || (variance = typeSystemCommonBackendContext.getVariance(typeParameterMarker)) == null || (varianceConvertVariance = ClassicTypeSystemContextKt.convertVariance(variance)) == null) {
                        varianceConvertVariance = Variance.INVARIANT;
                    }
                    Variance variance2 = varianceConvertVariance;
                    function5.invoke(Integer.valueOf(i), type, varianceForWildcard, variance2, TypeMappingMode.toGenericArgumentMode$default(typeMappingModeUpdateArgumentModeFromAnnotations$default, UtilsKt.getEffectiveVariance(variance2, ClassicTypeSystemContextKt.convertVariance(typeSystemCommonBackendContext.getVariance(typeArgumentMarker))), false, 2, (Object) null));
                }
                i = i2;
            }
        }

        @JvmStatic
        public final void writeFormalTypeParameter(TypeSystemCommonBackendContext typeSystemCommonBackendContext, TypeParameterMarker typeParameterMarker, JvmSignatureWriter jvmSignatureWriter, Function2<? super KotlinTypeMarker, ? super TypeMappingMode, Type> function2) {
            typeSystemCommonBackendContext.getClass();
            typeParameterMarker.getClass();
            jvmSignatureWriter.getClass();
            function2.getClass();
            jvmSignatureWriter.writeFormalTypeParameter(typeSystemCommonBackendContext.getName(typeParameterMarker).asString());
            jvmSignatureWriter.writeClassBound();
            int iUpperBoundCount = typeSystemCommonBackendContext.upperBoundCount(typeParameterMarker);
            for (int i = 0; i < iUpperBoundCount; i++) {
                KotlinTypeMarker upperBound = typeSystemCommonBackendContext.getUpperBound(typeParameterMarker, i);
                if (typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(upperBound)) == null && !typeSystemCommonBackendContext.isInterfaceOrAnnotationClass(upperBound)) {
                    function2.invoke(upperBound, TypeMappingMode.GENERIC_ARGUMENT);
                    break;
                }
            }
            jvmSignatureWriter.writeClassBoundEnd();
            int iUpperBoundCount2 = typeSystemCommonBackendContext.upperBoundCount(typeParameterMarker);
            for (int i2 = 0; i2 < iUpperBoundCount2; i2++) {
                KotlinTypeMarker upperBound2 = typeSystemCommonBackendContext.getUpperBound(typeParameterMarker, i2);
                if (typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(upperBound2)) != null || typeSystemCommonBackendContext.isInterfaceOrAnnotationClass(upperBound2)) {
                    jvmSignatureWriter.writeInterfaceBound();
                    function2.invoke(upperBound2, TypeMappingMode.GENERIC_ARGUMENT);
                    jvmSignatureWriter.writeInterfaceBoundEnd();
                }
            }
        }

        public final void writeGenericArguments(TypeSystemCommonBackendContext typeSystemCommonBackendContext, final JvmSignatureWriter jvmSignatureWriter, List<? extends TypeArgumentMarker> list, List<? extends TypeParameterMarker> list2, TypeMappingMode typeMappingMode, final Function3<? super KotlinTypeMarker, ? super JvmSignatureWriter, ? super TypeMappingMode, Type> function3) {
            typeSystemCommonBackendContext.getClass();
            jvmSignatureWriter.getClass();
            list.getClass();
            list2.getClass();
            typeMappingMode.getClass();
            function3.getClass();
            processGenericArguments(typeSystemCommonBackendContext, list, list2, typeMappingMode, new Function0() { // from class: od8
                public final Object invoke() {
                    return KotlinTypeMapper.Companion.b(jvmSignatureWriter);
                }
            }, new Function5() { // from class: pd8
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    return KotlinTypeMapper.Companion.a(jvmSignatureWriter, function3, ((Integer) obj).intValue(), (KotlinTypeMarker) obj2, (Variance) obj3, (Variance) obj4, (TypeMappingMode) obj5);
                }
            });
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasNothingInNonContravariantPosition(KotlinType kotlinType) {
            return hasNothingInNonContravariantPosition(SimpleClassicTypeSystemContext.INSTANCE, kotlinType);
        }

        public final Variance getVarianceForWildcard(TypeParameterDescriptor parameter, TypeProjection projection, TypeMappingMode mode) {
            parameter.getClass();
            projection.getClass();
            mode.getClass();
            return getVarianceForWildcard(SimpleClassicTypeSystemContext.INSTANCE, parameter, projection, mode);
        }
    }

    private final void writeParameter(JvmSignatureWriter sw, KotlinType type, CallableDescriptor callableDescriptor) {
        writeParameter(sw, JvmMethodParameterKind.VALUE, type, callableDescriptor);
    }

    public final Type mapType(KotlinType kotlinType, JvmSignatureWriter jvmSignatureWriter) {
        kotlinType.getClass();
        return mapType$default(this, kotlinType, jvmSignatureWriter, null, 4, null);
    }

    public final Type mapType(KotlinType kotlinType) {
        kotlinType.getClass();
        return mapType$default(this, kotlinType, null, null, 6, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KotlinTypeMapper(String str, LanguageVersionSettings languageVersionSettings, boolean z, Function1<? super KotlinType, ? extends KotlinType> function1) {
        this(str, languageVersionSettings, z, function1, null, 16, null);
        str.getClass();
        languageVersionSettings.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KotlinTypeMapper(String str, LanguageVersionSettings languageVersionSettings, boolean z) {
        this(str, languageVersionSettings, z, null, null, 24, null);
        str.getClass();
        languageVersionSettings.getClass();
    }

    public /* synthetic */ KotlinTypeMapper(String str, LanguageVersionSettings languageVersionSettings, boolean z, Function1 function1, Function1 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, languageVersionSettings, z, (i & 8) != 0 ? null : function1, (i & 16) != 0 ? null : function2);
    }

    private final void writeGenericArguments(JvmSignatureWriter signatureVisitor, List<? extends TypeProjection> arguments, List<? extends TypeParameterDescriptor> parameters, TypeMappingMode mode) {
        INSTANCE.writeGenericArguments(SimpleClassicTypeSystemContext.INSTANCE, signatureVisitor, arguments, parameters, mode, new Function3() { // from class: nd8
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return KotlinTypeMapper.writeGenericArguments$lambda$0$0(this.b, (KotlinTypeMarker) obj, (JvmSignatureWriter) obj2, (TypeMappingMode) obj3);
            }
        });
    }

    public final Type mapReturnType(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return mapReturnType$default(this, callableDescriptor, null, 2, null);
    }

    private final Type mapReturnType(CallableDescriptor descriptor, JvmSignatureWriter sw, KotlinType returnType) {
        boolean zIsAnnotationClass = DescriptorUtils.isAnnotationClass(descriptor.getDeclarationDescriptor());
        if (sw != null && !sw.skipGenericSignature()) {
            TypeMappingMode typeMappingModeExtractTypeMappingModeFromAnnotation = TypeMappingUtil.extractTypeMappingModeFromAnnotation(descriptor, returnType, zIsAnnotationClass, false);
            if (typeMappingModeExtractTypeMappingModeFromAnnotation != null) {
                return mapType(returnType, sw, typeMappingModeExtractTypeMappingModeFromAnnotation);
            }
            return mapType(returnType, sw, TypeMappingModeExtensionsKt.getOptimalModeForReturnType(getTypeSystem(), returnType, zIsAnnotationClass));
        }
        return mapType(returnType, sw, TypeMappingMode.Companion.getModeForReturnTypeNoGeneric(zIsAnnotationClass));
    }
}
