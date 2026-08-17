package org.jetbrains.kotlin.fir.analysis.diagnostics.jvm;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingUtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrorsDefaultMessages;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/jvm/FirJvmErrorsDefaultMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmErrorsDefaultMessages extends BaseDiagnosticRendererFactory {
    public static final FirJvmErrorsDefaultMessages INSTANCE = new FirJvmErrorsDefaultMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("FIR", new Function1() { // from class: v95
        public final Object invoke(Object obj) {
            return FirJvmErrorsDefaultMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private FirJvmErrorsDefaultMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        FirJvmErrors firJvmErrors = FirJvmErrors.INSTANCE;
        KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> java_type_mismatch = firJvmErrors.getJAVA_TYPE_MISMATCH();
        FirDiagnosticRenderers firDiagnosticRenderers = FirDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(java_type_mismatch, "Java type mismatch: expected ''{0}'' but found ''{1}''. Use explicit cast.", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> receiver_nullability_mismatch_based_on_java_annotations = firJvmErrors.getRECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS();
        DiagnosticParameterRenderer<ConeKotlinType> render_type = firDiagnosticRenderers.getRENDER_TYPE();
        KtDiagnosticRenderers ktDiagnosticRenderers = KtDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(receiver_nullability_mismatch_based_on_java_annotations, "Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type ''{0}''.{2}", render_type, ktDiagnosticRenderers.getNOT_RENDERED(), firDiagnosticRenderers.getOPTIONAL_SENTENCE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getRECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), "Java type mismatch: inferred receiver type is ''{0}'', but a member from ''{1}'' is called on it.", firDiagnosticRenderers.getRENDER_TYPE(), ktDiagnosticRenderers.getCLASS_ID());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), "Java type mismatch: inferred type is ''{0}'', but ''{1}'' was expected.{2}", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getOPTIONAL_SENTENCE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getNULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA(), "Java type mismatch because of nullable type argument ''{0}'', as not-null ''{1}'' was expected.{2}", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getOPTIONAL_SENTENCE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getTYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES(), RenderingUtilsKt.toDeprecationWarningMessage("Argument type mismatch: actual type is ''{0}'', but ''{1}'' was expected.", LanguageFeature.ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces), firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_CLASS_ON_COMPANION(), "The resulting type of this ''javaClass'' call is ''{0}'' and not ''{1}''. Use ''::class.java'' to access type ''{1}''.", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getUNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS(), "''when'' expression over a subject of type ''{0}'' is not exhaustive. Add a ''null'' or ''else'' branch.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getWRONG_TYPE_FOR_JAVA_OVERRIDE(), "Override ''{0}'' has incorrect nullability/mutability in its signature compared to the overridden declaration ''{1}''.", firDiagnosticRenderers.getSYMBOL(), firDiagnosticRenderers.getSYMBOL());
        KtDiagnosticFactory3<FirNamedFunctionSymbol, String, FirNamedFunctionSymbol> accidental_override_clash_by_jvm_signature = firJvmErrors.getACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE();
        ContextIndependentParameterRenderer<FirBasedSymbol<?>> symbol_with_containing_declaration = firDiagnosticRenderers.getSYMBOL_WITH_CONTAINING_DECLARATION();
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer = CommonRenderers.STRING;
        ktDiagnosticFactoryToRendererMap.put(accidental_override_clash_by_jvm_signature, "This function accidentally overrides both {0} and {1} {2} from JVM point of view because of mixed Java/Kotlin hierarchy.\nThis situation provokes a JVM clash and is forbidden. To fix it, delete either this or one of the overridden functions.", symbol_with_containing_declaration, contextIndependentParameterRenderer, firDiagnosticRenderers.getSYMBOL_WITH_CONTAINING_DECLARATION());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getIMPLEMENTATION_BY_DELEGATION_WITH_DIFFERENT_GENERIC_SIGNATURE(), "The function {0} from an interface is generic, but the function {1} from a delegate is not.\nSuch an implementation can provoke runtime errors.", firDiagnosticRenderers.getSYMBOL_WITH_CONTAINING_DECLARATION(), firDiagnosticRenderers.getSYMBOL_WITH_CONTAINING_DECLARATION());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getNOT_YET_SUPPORTED_LOCAL_INLINE_FUNCTION(), "Local inline functions are not yet supported.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getUPPER_BOUND_VIOLATED_BASED_ON_JAVA_ANNOTATIONS(), "Type argument is not within its bounds: type parameter ''{2}'' must be subtype of ''{0}'', but actual: ''{1}''.", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getUPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_BASED_ON_JAVA_ANNOTATIONS(), "Type argument is not within its bounds: type parameter ''{2}'' must be subtype of ''{0}'', but actual: ''{1}''.", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getPROPERTY_HIDES_JAVA_FIELD(), "This property hides Java field ''{0}'' thus making it inaccessible.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getUPPER_BOUND_CANNOT_BE_ARRAY(), "Upper bound of type parameter cannot be an array.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSTRICTFP_ON_CLASS(), "'@Strictfp' annotation on classes is not yet supported.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_ON_ABSTRACT(), "'@Synchronized' annotation cannot be used on abstract functions.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_ON_INLINE(), "'@Synchronized' annotation has no effect on inline functions.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_ON_VALUE_CLASS(), "'@Synchronized' annotation has no effect on value classes.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS(), "Synchronizing on an instance of Java value-based class ''{0}'' will produce a runtime exception in future JVM releases.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE(), "Synchronizing on ''{0}'' is forbidden.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_ON_SUSPEND(), "'@Synchronized' annotation is not applicable to 'suspend' functions and lambdas.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_IN_INTERFACE(), "'@Synchronized' annotation cannot be used on interface members.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNCHRONIZED_IN_ANNOTATION(), "'@Synchronized' annotation cannot be used on annotation parameters.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERLOADS_WITHOUT_DEFAULT_ARGUMENTS(), "'@JvmOverloads' annotation has no effect for methods without default arguments.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERLOADS_ABSTRACT(), "'@JvmOverloads' annotation cannot be used on abstract methods.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERLOADS_INTERFACE(), "'@JvmOverloads' annotation cannot be used on interface methods.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERLOADS_PRIVATE(), "'@JvmOverloads' annotation has no effect on private declarations.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERLOADS_LOCAL(), "'@JvmOverloads' annotation cannot be used on local declarations.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERLOADS_ANNOTATION_CLASS_CONSTRUCTOR_ERROR(), "'@JvmOverloads' annotation cannot be used on constructors of annotation classes.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getDEPRECATED_JAVA_ANNOTATION(), "This annotation is deprecated in Kotlin. Use ''@{0}'' instead.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getPOSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION(), "Only named arguments are available for Java annotations.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_PACKAGE_NAME_CANNOT_BE_EMPTY(), "'@JvmPackageName' annotation value cannot be empty.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_PACKAGE_NAME_MUST_BE_VALID_NAME(), "'@JvmPackageName' annotation value must be a valid dot-qualified name of a package.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_PACKAGE_NAME_NOT_SUPPORTED_IN_FILES_WITH_CLASSES(), "'@JvmPackageName' annotation is not supported for files with class declarations.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getLOCAL_JVM_RECORD(), "Local '@JvmRecord' classes are prohibited.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getNON_FINAL_JVM_RECORD(), "'@JvmRecord' class must be final.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getENUM_JVM_RECORD(), "'@JvmRecord' class cannot be an enum.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS(), "Primary constructor with parameters is required for '@JvmRecord' class.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_RECORD_NOT_VAL_PARAMETER(), "Constructor parameter of '@JvmRecord' class must be a 'val'.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_RECORD_NOT_LAST_VARARG_PARAMETER(), "Only the last constructor parameter of '@JvmRecord' can be a vararg.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_RECORD_EXTENDS_CLASS(), "Record cannot extend a class.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINNER_JVM_RECORD(), "'@JvmRecord' class cannot be inner.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getFIELD_IN_JVM_RECORD(), "Non-constructor properties with backing field in '@JvmRecord' class are prohibited.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getDELEGATION_BY_IN_JVM_RECORD(), "Delegation is prohibited for '@JvmRecord' classes.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getNON_DATA_CLASS_JVM_RECORD(), "Only data classes are allowed to be marked as '@JvmRecord'.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getILLEGAL_JAVA_LANG_RECORD_SUPERTYPE(), "Classes cannot have explicit 'java.lang.Record' supertype.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_RECORDS_ILLEGAL_BYTECODE_TARGET(), "Using @JvmRecord is only allowed with -jvm-target 16 or later (or -jvm-target 15 with the -Xjvm-enable-preview flag enabled).");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE(), "Symbol is declared in module ''{0}'', which the current module does not depend on.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE(), "Symbol is declared in an unnamed module which is not read by current module.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_MODULE_DOES_NOT_EXPORT_PACKAGE(), "Symbol is declared in module ''{0}'' which does not export package ''{1}''.", contextIndependentParameterRenderer, contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getOVERRIDE_CANNOT_BE_STATIC(), "Override member cannot be '@JvmStatic' in an object.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_STATIC_NOT_IN_OBJECT_OR_CLASS_COMPANION(), "Only members in named objects and companion objects of classes can be annotated with '@JvmStatic'.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_STATIC_NOT_IN_OBJECT_OR_COMPANION(), "Only members in named objects and companion objects can be annotated with '@JvmStatic'.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_STATIC_ON_NON_PUBLIC_MEMBER(), "Only public members in interface companion objects can be annotated with '@JvmStatic'.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_STATIC_ON_CONST_OR_JVM_FIELD(), "'@JvmStatic' annotation is redundant for const or '@JvmField' properties.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_STATIC_ON_EXTERNAL_IN_INTERFACE(), "'@JvmStatic' annotation cannot be used on 'external' members of interface companions.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINAPPLICABLE_JVM_NAME(), "'@JvmName' annotation is not applicable to this declaration.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getILLEGAL_JVM_NAME(), "Illegal JVM name.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getFUNCTION_DELEGATE_MEMBER_NAME_CLASH(), "Spread operator is prohibited for arguments to signature-polymorphic calls.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getVALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION(), "Value classes without '@JvmInline' annotation are not yet supported.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_INLINE_WITHOUT_VALUE_CLASS(), "'@JvmInline' annotation is applicable only to value classes.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINAPPLICABLE_JVM_EXPOSE_BOXED_WITH_NAME(), "'@JvmExposeBoxed' with name is applicable only to functions, getters and setters.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getUSELESS_JVM_EXPOSE_BOXED(), "'@JvmExposeBoxed' has no effect when applied to a callable declaration with no inline value class in its signature.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SUSPEND(), "Suspend functions cannot be exposed by @JvmExposeBoxed.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_REQUIRES_NAME(), "This declaration requires a name in the '@JvmExposeBoxed' annotation.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME(), "The name in '@JvmExposeBoxed' cannot coincide with that of the declaration.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_BE_THE_SAME_AS_JVM_NAME(), "The name in '@JvmExposeBoxed' cannot coincide with the one in '@JvmName'.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_OPEN_ABSTRACT(), "'@JvmExposeBoxed' cannot expose functions which are open or abstract, or member of an interface.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_SYNTHETIC(), "'@JvmExposeBoxed' cannot expose synthetic functions.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_LOCALS(), "'@JvmExposeBoxed' cannot expose local functions.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_EXPOSE_BOXED_CANNOT_EXPOSE_REIFIED(), "'@JvmExposeBoxed' cannot expose inline functions with reified type parameters.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_DEFAULT_WITHOUT_COMPATIBILITY_NOT_IN_ENABLE_MODE(), "Usage of '@JvmDefaultWithoutCompatibility' is only allowed with '-jvm-default=enable' option.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_DEFAULT_WITH_COMPATIBILITY_NOT_IN_NO_COMPATIBILITY_MODE(), "Usage of '@JvmDefaultWithCompatibility' is only allowed with '-jvm-default=no-compatibility' option.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getEXTERNAL_DECLARATION_IN_INTERFACE(), "Members of interfaces cannot be external.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getEXTERNAL_DECLARATION_CANNOT_BE_ABSTRACT(), "External declaration cannot be abstract.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getEXTERNAL_DECLARATION_CANNOT_HAVE_BODY(), "External declaration cannot have a body.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getEXTERNAL_DECLARATION_CANNOT_BE_INLINED(), "Inline functions cannot be external.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINAPPLICABLE_JVM_FIELD(), "{0}.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINAPPLICABLE_JVM_FIELD_WARNING(), RenderingUtilsKt.toDeprecationWarningMessage("{0}.", LanguageFeature.ForbidJvmAnnotationsOnAnnotationParameters), contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_SYNTHETIC_ON_DELEGATE(), "'@JvmSynthetic' annotation cannot be used on delegated properties.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getNON_SOURCE_REPEATED_ANNOTATION(), "Repeatable annotations with non-SOURCE retention are supported only in Kotlin 1.6 and later.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREPEATED_ANNOTATION_WITH_CONTAINER(), "Repeated annotation ''@{0}'' cannot be used on a declaration that is annotated with its container annotation ''@{1}''.", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER(), "Calling JVM-default members via super is supported only in Kotlin 2.1 and later, or with -jvm-default=enable/no-compatibility.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSUBCLASS_CANT_CALL_COMPANION_PROTECTED_NON_STATIC(), "Using protected members that are not '@JvmStatic' in the superclass companion is not yet supported.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREPEATABLE_CONTAINER_MUST_HAVE_VALUE_ARRAY_ERROR(), "Container annotation ''{0}'' must have a property ''value'' of type ''Array<{1}>''.", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREPEATABLE_CONTAINER_HAS_NON_DEFAULT_PARAMETER_ERROR(), "Container annotation ''{0}'' does not have a default value for ''{1}''.", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREPEATABLE_CONTAINER_HAS_SHORTER_RETENTION_ERROR(), "Container annotation ''{0}'' has shorter retention (''{1}'') than the repeatable annotation ''{2}'' (''{3}'').", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREPEATABLE_CONTAINER_TARGET_SET_NOT_A_SUBSET_ERROR(), "Target set of container annotation ''{0}'' must be a subset of the target set of contained annotation ''{1}''.", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR(), "Repeatable annotation cannot have a nested class named 'Container'. This name is reserved for auto-generated container class.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSUSPENSION_POINT_INSIDE_CRITICAL_SECTION(), "The ''{0}'' suspension point is inside a critical section.", firDiagnosticRenderers.getDECLARATION_NAME());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINLINE_FROM_HIGHER_PLATFORM(), "Cannot inline bytecode built with {0} into bytecode that is being built with {1}. Specify proper ''-jvm-target'' option.", contextIndependentParameterRenderer, contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getCONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR(), "Method 'contains' from ConcurrentHashMap might have unexpected semantics: it calls 'containsValue' instead of 'containsKey'. Use the explicit form of the call to 'containsKey'/'containsValue'/'contains' or cast the value to 'kotlin.collections.Map' instead. See https://youtrack.jetbrains.com/issue/KT-18053 for more details.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR(), "Spread operator is prohibited for arguments to signature-polymorphic calls.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_SAM_INTERFACE_CONSTRUCTOR_REFERENCE(), "Java SAM interface constructor references are prohibited.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getREDUNDANT_REPEATABLE_ANNOTATION(), "Remove the ''{0}'' annotation, as it is redundant in presence of ''{1}''.", ktDiagnosticRenderers.getTO_STRING(), ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getTHROWS_IN_ANNOTATION(), "'@Throws' annotation cannot be used on annotation parameters.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJVM_SERIALIZABLE_LAMBDA_ON_INLINED_FUNCTION_LITERALS(), "'@JvmSerializableLambda' is not applicable to inlined function literals.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getINCOMPATIBLE_ANNOTATION_TARGETS(), "Incompatible annotation targets: Java {0} missing, corresponding to Kotlin {1}.", firDiagnosticRenderers.getSTRING_TARGETS(), firDiagnosticRenderers.getSTRING_TARGETS());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getANNOTATION_TARGETS_ONLY_IN_JAVA(), "No Kotlin '@Target' annotation specified (implicitly targeting everything), but one exists for Java.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getNO_REFLECTION_IN_CLASS_PATH(), "Call uses reflection API which is not found in compilation classpath. Make sure you have kotlin-reflect.jar in the classpath.");
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getSYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN(), "This synthetic property is based on the getter function ''{0}'' from Kotlin. In the future, synthetic properties will be available only if the base getter function came from Java. Consider replacing this property access with a ''{1}()'' function call.", firDiagnosticRenderers.getSYMBOL(), CommonRenderers.NAME);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_FIELD_SHADOWED_BY_KOTLIN_PROPERTY(), "This variable access is resolved to Java field, but it is clashed with Kotlin property ''{0}'' with backing field which leads to the incorrect bytecode generation and failure at runtime. So such calls are prohibited until corresponding bug will be fixed. See https://youtrack.jetbrains.com/issue/KT-56386 for more details.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getJAVA_CLASS_INHERITS_KT_PRIVATE_CLASS(), "Java class ''{0}'' declaring this member directly or indirectly extends the private Kotlin class ''{1}''.", ktDiagnosticRenderers.getTO_STRING(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getMISSING_BUILT_IN_DECLARATION(), "Cannot access built-in declaration ''{0}''. Ensure that you have a dependency on the Kotlin standard library.", firDiagnosticRenderers.getDECLARATION_FQ_NAME());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getDANGEROUS_CHARACTERS(), "Name contains character(s) that can cause problems on Windows: {0}", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getIDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE(), "Identity-sensitive operation on an instance of value type ''{0}'' may cause unexpected behavior or errors.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJvmErrors.getCONFLICT_VERSION_AND_JVM_OVERLOADS_ANNOTATION(), "'@JvmOverloads' annotation may generate conflicting overloads with the '@IntroducedAt' annotation.");
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
