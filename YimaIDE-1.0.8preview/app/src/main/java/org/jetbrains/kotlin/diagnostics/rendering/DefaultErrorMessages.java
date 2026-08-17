package org.jetbrains.kotlin.diagnostics.rendering;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.PsiElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.DescriptorWithRelation;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.Named;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.Errors;
import org.jetbrains.kotlin.diagnostics.RenderedDiagnostic;
import org.jetbrains.kotlin.diagnostics.TypeMismatchDueToTypeProjectionsData;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtCallableDeclaration;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtModifierListOwner;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtReferenceExpression;
import org.jetbrains.kotlin.psi.KtSimpleNameExpression;
import org.jetbrains.kotlin.psi.KtSuperTypeList;
import org.jetbrains.kotlin.psi.KtTypeAlias;
import org.jetbrains.kotlin.psi.KtTypeConstraint;
import org.jetbrains.kotlin.psi.KtTypeProjection;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.kotlin.resolve.VarianceConflictDiagnosticData;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.tower.WrongResolutionToClassifier;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualAnnotationsIncompatibilityType;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualCompatibility;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualMemberDiff;
import org.jetbrains.kotlin.serialization.deserialization.IncompatibleVersionErrorData;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.KotlinTypeKt;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DefaultErrorMessages {
    private static final DiagnosticFactoryToRendererMap MAP;
    private static final List<DiagnosticFactoryToRendererMap> RENDERER_MAPS;
    private static final List<String> RENDERER_PLATFORM_EXTENSIONS;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$diagnostics$Errors$BadNamedArgumentsTarget;

        static {
            int[] iArr = new int[Errors.BadNamedArgumentsTarget.values().length];
            $SwitchMap$org$jetbrains$kotlin$diagnostics$Errors$BadNamedArgumentsTarget = iArr;
            try {
                iArr[Errors.BadNamedArgumentsTarget.NON_KOTLIN_FUNCTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$diagnostics$Errors$BadNamedArgumentsTarget[Errors.BadNamedArgumentsTarget.INTEROP_FUNCTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$diagnostics$Errors$BadNamedArgumentsTarget[Errors.BadNamedArgumentsTarget.INVOKE_ON_FUNCTION_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$diagnostics$Errors$BadNamedArgumentsTarget[Errors.BadNamedArgumentsTarget.EXPECTED_CLASS_MEMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public interface Extension {
        DiagnosticFactoryToRendererMap getMap();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "org/jetbrains/kotlin/diagnostics/rendering/DefaultErrorMessages";
        } else {
            objArr[0] = "diagnostic";
        }
        if (i == 1 || i == 2) {
            objArr[1] = "render";
        } else {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/DefaultErrorMessages";
        }
        if (i != 1 && i != 2) {
            if (i != 3) {
                objArr[2] = "render";
            } else {
                objArr[2] = "getRendererForDiagnostic";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    static {
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap = new DiagnosticFactoryToRendererMap("Default");
        MAP = diagnosticFactoryToRendererMap;
        List<String> listListOf = CollectionsKt.listOf(new String[]{"org.jetbrains.kotlin.resolve.jvm.diagnostics.DefaultErrorMessagesJvm", "org.jetbrains.kotlin.js.resolve.diagnostics.DefaultErrorMessagesJs", "org.jetbrains.kotlin.resolve.konan.diagnostics.DefaultErrorMessagesNative", "org.jetbrains.kotlin.wasm.resolve.diagnostics.DefaultErrorMessagesWasm"});
        RENDERER_PLATFORM_EXTENSIONS = listListOf;
        ArrayList arrayList = new ArrayList(listListOf.size() + 1);
        arrayList.add(diagnosticFactoryToRendererMap);
        Iterator<String> it = listListOf.iterator();
        while (it.hasNext()) {
            try {
                Class<?> cls = Class.forName(it.next());
                if (!Extension.class.isAssignableFrom(cls)) {
                    throw new IllegalStateException(cls.getName() + " is not a " + Extension.class.getName());
                }
                arrayList.add(((Extension) cls.newInstance()).getMap());
            } catch (Exception e) {
                rc6.a(e);
                return;
            }
        }
        RENDERER_MAPS = Collections.unmodifiableList(arrayList);
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap2 = MAP;
        diagnosticFactoryToRendererMap2.put(Errors.UNRESOLVED_REFERENCE, "Unresolved reference: {0}", Renderers.ELEMENT_TEXT);
        DiagnosticFactory3<KtSimpleNameExpression, DeclarationDescriptor, DescriptorVisibility, DeclarationDescriptor> diagnosticFactory3 = Errors.INVISIBLE_REFERENCE;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer = Renderers.NAME;
        ContextIndependentParameterRenderer<DescriptorVisibility> contextIndependentParameterRenderer2 = Renderers.VISIBILITY;
        ContextIndependentParameterRenderer<DeclarationDescriptor> contextIndependentParameterRenderer3 = Renderers.NAME_OF_CONTAINING_DECLARATION_OR_FILE;
        diagnosticFactoryToRendererMap2.put(diagnosticFactory3, "Cannot access ''{0}'': it is {1} in {2}", contextIndependentParameterRenderer, contextIndependentParameterRenderer2, contextIndependentParameterRenderer3);
        diagnosticFactoryToRendererMap2.put(Errors.INVISIBLE_MEMBER, "Cannot access ''{0}'': it is {1} in {2}", contextIndependentParameterRenderer, contextIndependentParameterRenderer2, contextIndependentParameterRenderer3);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_ACCESS_BY_SHORT_NAME, "Access to this type by short name is deprecated, and soon is going to be removed. Please, add explicit qualifier or import", contextIndependentParameterRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_ACCESS_TO_ENUM_COMPANION_PROPERTY, "Ambiguous access to companion''s property ''{0}'' in enum is deprecated. Please, add explicit Companion qualifier to the class name", contextIndependentParameterRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_ACCESS_TO_ENUM_ENTRY_COMPANION_PROPERTY, "Ambiguous access to companion''s property 'entries' in enum is deprecated. Please, add explicit Companion qualifier to the class name");
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_ACCESS_TO_ENTRY_PROPERTY_FROM_ENUM, "Ambiguous access to property 'entries' from enum is deprecated. Please, add explicit qualifier to the call");
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_ACCESS_TO_ENUM_ENTRY_PROPERTY_AS_REFERENCE, "Ambiguous access to property 'entries' is deprecated. Please, specify type of the referenced expression explicitly");
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_DECLARATION_OF_ENUM_ENTRY, "Conflicting declarations: enum entry 'entries' and the property 'Enum.entries' (KT-48872). Please, rename the enum entry declaration");
        DiagnosticFactory2<PsiElement, PropertyDescriptor, ClassDescriptor> diagnosticFactory2 = Errors.DEPRECATED_RESOLVE_WITH_AMBIGUOUS_ENUM_ENTRY;
        ContextIndependentParameterRenderer<MemberDescriptor> contextIndependentParameterRenderer4 = Renderers.FQ_NAME;
        diagnosticFactoryToRendererMap2.put(diagnosticFactory2, "Ambiguous access to property ''{0}'' is deprecated because similar enum entry ''{1}'' is available. Please add explicit named import or use fully qualified name", contextIndependentParameterRenderer4, contextIndependentParameterRenderer4);
        diagnosticFactoryToRendererMap2.put(Errors.PROTECTED_CONSTRUCTOR_NOT_IN_SUPER_CALL, "Protected constructor ''{0}'' from other classes can only be used in super-call", Renderers.SHORT_NAMES_IN_TYPES);
        DiagnosticFactory3<PsiElement, EffectiveVisibility, DescriptorWithRelation, EffectiveVisibility> diagnosticFactory4 = Errors.EXPOSED_PROPERTY_TYPE;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer5 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap2.put(diagnosticFactory4, "''{0}'' property exposes its ''{2}'' type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_PROPERTY_TYPE_IN_CONSTRUCTOR, "''{0}'' property exposes its ''{2}'' type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_FUNCTION_RETURN_TYPE, "''{0}'' function exposes its ''{2}'' return type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_PARAMETER_TYPE, "''{0}'' function exposes its ''{2}'' parameter type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_RECEIVER_TYPE, "''{0}'' member exposes its ''{2}'' receiver type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_TYPE_PARAMETER_BOUND, "''{0}'' generic exposes its ''{2}'' parameter bound type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_SUPER_CLASS, "''{0}'' subclass exposes its ''{2}'' supertype{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_SUPER_INTERFACE, "''{0}'' sub-interface exposes its ''{2}'' supertype{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_TYPEALIAS_EXPANDED_TYPE, "''{0}'' typealias exposes ''{2}'' in expanded type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPOSED_FROM_PRIVATE_IN_FILE, "Deprecation: ''{0}'' element should not expose ''{2}'' type{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        DiagnosticFactory1<KtDeclaration, CallableMemberDescriptor> diagnosticFactory1 = Errors.EXTENSION_SHADOWED_BY_MEMBER;
        SmartDescriptorRenderer smartDescriptorRenderer = Renderers.COMPACT_WITH_MODIFIERS;
        diagnosticFactoryToRendererMap2.put(diagnosticFactory1, "Extension is shadowed by a member: {0}", smartDescriptorRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.EXTENSION_FUNCTION_SHADOWED_BY_INNER_CLASS_CONSTRUCTOR, "Extension function is shadowed by an inner class constructor: {0}", smartDescriptorRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.EXTENSION_FUNCTION_SHADOWED_BY_MEMBER_PROPERTY_WITH_INVOKE, "Extension function is shadowed by a member property ''{0}'' with {1}", contextIndependentParameterRenderer, smartDescriptorRenderer);
        DiagnosticFactory2<KtExpression, KotlinType, Collection<ClassDescriptor>> diagnosticFactory5 = Errors.INACCESSIBLE_TYPE;
        SmartTypeRenderer smartTypeRenderer = Renderers.RENDER_TYPE;
        diagnosticFactoryToRendererMap2.put(diagnosticFactory5, "Type {0} is inaccessible in this context due to: {1}", smartTypeRenderer, CommonRenderers.commaSeparated(Renderers.FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS));
        diagnosticFactoryToRendererMap2.put(Errors.REDECLARATION, "Conflicting declarations: {0}", CommonRenderers.commaSeparated(smartDescriptorRenderer));
        DiagnosticFactory1<PsiElement, String> diagnosticFactory6 = Errors.PACKAGE_OR_CLASSIFIER_REDECLARATION;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer6 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap2.put(diagnosticFactory6, "Redeclaration: {0}", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.DUPLICATE_PARAMETER_NAME_IN_FUNCTION_TYPE, "Duplicate parameter name in function type");
        diagnosticFactoryToRendererMap2.put(Errors.NAME_SHADOWING, "Name shadowed: {0}", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.ACCESSOR_PARAMETER_NAME_SHADOWING, "Accessor parameter name 'field' is shadowed by backing field variable");
        diagnosticFactoryToRendererMap2.put(Errors.TYPE_MISMATCH, "Type mismatch: inferred type is {1} but {0} was expected", smartTypeRenderer, smartTypeRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.TYPE_MISMATCH_WARNING, "Type mismatch: inferred type is {1} but {0} was expected", smartTypeRenderer, smartTypeRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.TYPE_MISMATCH_DUE_TO_EQUALS_LAMBDA_IN_FUN, "Inferred type is a function type, but a non-function type {0} was expected. Use either ''= ...'' or '''{ ... }'', but not both.", smartTypeRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.TYPE_MISMATCH_DUE_TO_TYPE_PROJECTIONS, "Type mismatch: inferred type is {1} but {0} was expected. Projected type {2} restricts use of {3}", new MultiRenderer() { // from class: ng3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.MultiRenderer
            public final String[] render(Object obj) {
                return DefaultErrorMessages.c((TypeMismatchDueToTypeProjectionsData) obj);
            }
        });
        diagnosticFactoryToRendererMap2.put(Errors.MEMBER_PROJECTED_OUT, "Out-projected type ''{1}'' prohibits the use of ''{0}''", Renderers.FQ_NAMES_IN_TYPES, smartTypeRenderer);
        diagnosticFactoryToRendererMap2.put(Errors.INCOMPATIBLE_MODIFIERS, "Modifier ''{0}'' is incompatible with ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_MODIFIER_PAIR, "Modifier ''{0}'' is deprecated in presence of ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.REPEATED_MODIFIER, "Repeated ''{0}''", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_MODIFIER_TARGET, "Modifier ''{0}'' is not applicable to ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_MODIFIER_FOR_TARGET, "Modifier ''{0}'' is deprecated for ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_MODIFIER, "Modifier ''{0}'' is deprecated, use ''{1}'' instead", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.REDUNDANT_MODIFIER_FOR_TARGET, "Modifier ''{0}'' is redundant for ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.NO_EXPLICIT_VISIBILITY_IN_API_MODE, "Visibility must be specified in explicit API mode");
        diagnosticFactoryToRendererMap2.put(Errors.NO_EXPLICIT_RETURN_TYPE_IN_API_MODE, "Return type must be specified in explicit API mode");
        diagnosticFactoryToRendererMap2.put(Errors.NO_EXPLICIT_VISIBILITY_IN_API_MODE_WARNING, "Visibility must be specified in explicit API mode");
        diagnosticFactoryToRendererMap2.put(Errors.NO_EXPLICIT_RETURN_TYPE_IN_API_MODE_WARNING, "Return type must be specified in explicit API mode");
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_MODIFIER_CONTAINING_DECLARATION, "Modifier ''{0}'' is not applicable inside ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.DEPRECATED_MODIFIER_CONTAINING_DECLARATION, "Modifier ''{0}'' is deprecated inside ''{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.ILLEGAL_INLINE_PARAMETER_MODIFIER, "Modifier ''{0}'' is allowed only for function parameters of an inline function", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.INLINE_SUSPEND_FUNCTION_TYPE_UNSUPPORTED, "Suspend inline lambda parameters of non-suspend function type are not supported. Add 'noinline' or 'crossinline' modifier.");
        diagnosticFactoryToRendererMap2.put(Errors.REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE, "Redundant 'suspend' modifier: lambda parameters of suspend function type uses existing continuation.");
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_ANNOTATION_TARGET, "This annotation is not applicable to target ''{0}''", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET, "This annotation is not applicable to target ''{0}'' and use site target ''@{1}''", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET_ON_TYPE, "Use of this annotation with target ''type'' and use site target ''@{0}'' is deprecated", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.REPEATED_ANNOTATION, "This annotation is not repeatable");
        diagnosticFactoryToRendererMap2.put(Errors.REPEATED_ANNOTATION_WARNING, "This annotation is not repeatable");
        diagnosticFactoryToRendererMap2.put(Errors.NON_SOURCE_ANNOTATION_ON_INLINED_LAMBDA_EXPRESSION, "The lambda expression here is an inlined argument so this annotation cannot be stored anywhere");
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_EXTENSION_FUNCTION_TYPE, "ExtensionFunctionType is forbidden on a function type without parameters or on a non-function type");
        diagnosticFactoryToRendererMap2.put(Errors.WRONG_EXTENSION_FUNCTION_TYPE_WARNING, "ExtensionFunctionType makes no sense on a non-function type. It will be an error in a future release. See https://youtrack.jetbrains.com/issue/KT-43527");
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_TARGET_ON_PROPERTY, "''@{0}:'' annotations could be applied only to property declarations", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_TARGET_ON_PROPERTY_WARNING, "''@{0}:'' annotations could be applied only to property declarations. It will be an error in a future release. See https://youtrack.jetbrains.com/issue/KT-15470", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_TARGET_PROPERTY_IMMUTABLE, "''@{0}:'' annotations could be applied only to mutable properties", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_TARGET_PROPERTY_HAS_NO_DELEGATE, "'@delegate:' annotations could be applied only to delegated properties");
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_TARGET_PROPERTY_HAS_NO_BACKING_FIELD, "'@field:' annotations could be applied only to properties with backing fields");
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_PARAM_TARGET, "'@param:' annotations could be applied only to primary constructor parameters");
        diagnosticFactoryToRendererMap2.put(Errors.REDUNDANT_ANNOTATION_TARGET, "Redundant annotation target ''{0}''", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.INAPPLICABLE_FILE_TARGET, "'@file:' annotations can only be applied before package declaration");
        diagnosticFactoryToRendererMap2.put(Errors.ILLEGAL_KOTLIN_VERSION_STRING_VALUE, "Invalid @{0} annotation value (should be ''major.minor'' or ''major.minor.patch'')", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.NEWER_VERSION_IN_SINCE_KOTLIN, "The version is greater than the specified API version {0}", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_USAGE, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_USAGE_ERROR, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_USAGE_FUTURE_ERROR, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_TO_INHERITANCE, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_TO_INHERITANCE_ERROR, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_OVERRIDE, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_OVERRIDE_ERROR, "{1}", contextIndependentParameterRenderer5, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_IS_NOT_ENABLED, "This annotation should be used with the compiler argument '-opt-in=kotlin.RequiresOptIn'");
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_CAN_ONLY_BE_USED_AS_ANNOTATION, "This class can only be used as an annotation");
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_MARKER_CAN_ONLY_BE_USED_AS_ANNOTATION_OR_ARGUMENT_IN_OPT_IN, "This class can only be used as an annotation or as an argument to @OptIn");
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_WITHOUT_ARGUMENTS, "@OptIn without any arguments has no effect");
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_ARGUMENT_IS_NOT_MARKER, "Annotation ''{0}'' is not an opt-in requirement marker, therefore its usage in @OptIn is ignored", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_MARKER_WITH_WRONG_TARGET, "Opt-in requirement marker annotation cannot be used on the following code elements: {0}. Please remove these targets", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_MARKER_WITH_WRONG_RETENTION, "Opt-in requirement marker annotation cannot be used with SOURCE retention. Please replace retention with BINARY");
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_MARKER_ON_WRONG_TARGET, "Opt-in requirement marker annotation cannot be used on {0}", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_MARKER_ON_OVERRIDE, "Opt-in requirement marker annotation on override requires the same marker on base declaration");
        diagnosticFactoryToRendererMap2.put(Errors.OPT_IN_MARKER_ON_OVERRIDE_WARNING, "Opt-in requirement marker annotation on override makes no sense without the same marker on base declaration");
        diagnosticFactoryToRendererMap2.put(Errors.SUBCLASS_OPT_IN_INAPPLICABLE, "@SubclassOptInRequired is inapplicable on {0}", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.SUBCLASS_OPT_IN_ARGUMENT_IS_NOT_MARKER, "Annotation ''{0}'' is not annotated with ''@RequiresOptIn''.", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.EXPERIMENTAL_UNSIGNED_LITERALS, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.EXPERIMENTAL_UNSIGNED_LITERALS_ERROR, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap2.put(Errors.NON_PARENTHESIZED_ANNOTATIONS_ON_FUNCTIONAL_TYPES, "Non-parenthesized annotations on function types without receiver aren't yet supported (see KT-31734 for details)");
        diagnosticFactoryToRendererMap2.put(Errors.ANNOTATION_IN_WHERE_CLAUSE_WARNING, "Type parameter annotations will not be allowed inside where clauses in future releases. You should probably move annotations to the type parameter declaration");
        diagnosticFactoryToRendererMap2.put(Errors.IGNORABILITY_ANNOTATIONS_WITH_CHECKER_DISABLED, "Ignorability-related annotations are experimental and cannot be used with -Xreturn-value-checker in disabled state.");
        diagnosticFactoryToRendererMap2.put(Errors.REDUNDANT_MODIFIER, "Modifier ''{0}'' is redundant because ''{1}'' is present", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap2.put(Errors.REDUNDANT_OPEN_IN_INTERFACE, "Modifier 'open' is redundant for abstract interface members");
        diagnosticFactoryToRendererMap2.put(Errors.REDUNDANT_MODIFIER_IN_GETTER, "Visibility modifiers are redundant in getter");
        diagnosticFactoryToRendererMap2.put(Errors.TYPE_PARAMETERS_IN_ENUM, "Enum class cannot have type parameters");
        diagnosticFactoryToRendererMap2.put(Errors.TYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM, "Type checking has run into a recursive problem. Easiest workaround: specify types of your declarations explicitly");
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap3 = MAP;
        diagnosticFactoryToRendererMap3.put(Errors.TYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM_IN_AUGMENTED_ASSIGNMENT, "Type checking has run into a recursive problem. Easiest workaround: specify types of your declarations explicitly");
        diagnosticFactoryToRendererMap3.put(Errors.RETURN_NOT_ALLOWED, "'return' is not allowed here");
        diagnosticFactoryToRendererMap3.put(Errors.PROJECTION_IN_IMMEDIATE_ARGUMENT_TO_SUPERTYPE, "Projections are not allowed for immediate arguments of a supertype");
        diagnosticFactoryToRendererMap3.put(Errors.LABEL_NAME_CLASH, "There is more than one label with such a name in this scope");
        diagnosticFactoryToRendererMap3.put(Errors.LABEL_RESOLVE_WILL_CHANGE, "This label is now resolved to ''{0}'' but soon it will be resolved to the closest ''{1}''. Please consider introducing or changing explicit label name", contextIndependentParameterRenderer5, contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap3.put(Errors.EXPRESSION_EXPECTED_PACKAGE_FOUND, "Expression expected, but a package name found");
        DiagnosticFactory1<KtSimpleNameExpression, ClassDescriptor> diagnosticFactory7 = Errors.CANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer7 = Renderers.NAME;
        diagnosticFactoryToRendererMap3.put(diagnosticFactory7, "Cannot import-on-demand from object ''{0}''", contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.CANNOT_BE_IMPORTED, "Cannot import ''{0}'', functions and properties can be imported only from packages or objects", contextIndependentParameterRenderer5);
        diagnosticFactoryToRendererMap3.put(Errors.PACKAGE_CANNOT_BE_IMPORTED, "Packages cannot be imported");
        diagnosticFactoryToRendererMap3.put(Errors.CONFLICTING_IMPORT, "Conflicting import, imported name ''{0}'' is ambiguous", contextIndependentParameterRenderer6);
        diagnosticFactoryToRendererMap3.put(Errors.OPERATOR_RENAMED_ON_IMPORT, "Operator renamed to a different operator on import");
        diagnosticFactoryToRendererMap3.put(Errors.PLATFORM_CLASS_MAPPED_TO_KOTLIN, "This class shouldn''t be used in Kotlin. Use {0} instead.", Renderers.CLASSES_OR_SEPARATED);
        diagnosticFactoryToRendererMap3.put(Errors.CANNOT_INFER_PARAMETER_TYPE, "Cannot infer a type for this parameter. Please specify it explicitly.");
        diagnosticFactoryToRendererMap3.put(Errors.MIXING_NAMED_AND_POSITIONED_ARGUMENTS, "Mixing named and positioned arguments is not allowed");
        diagnosticFactoryToRendererMap3.put(Errors.ARGUMENT_PASSED_TWICE, "An argument is already passed for this parameter");
        diagnosticFactoryToRendererMap3.put(Errors.NAMED_PARAMETER_NOT_FOUND, "Cannot find a parameter with this name: {0}", Renderers.ELEMENT_TEXT);
        diagnosticFactoryToRendererMap3.put(Errors.NAMED_ARGUMENTS_NOT_ALLOWED, "Named arguments are not allowed for {0}", new DiagnosticParameterRenderer() { // from class: cg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.s((Errors.BadNamedArgumentsTarget) obj, renderingContext);
            }
        });
        diagnosticFactoryToRendererMap3.put(Errors.VARARG_OUTSIDE_PARENTHESES, "Passing value as a vararg is only allowed inside a parenthesized argument list");
        diagnosticFactoryToRendererMap3.put(Errors.NON_VARARG_SPREAD, "The spread operator (*foo) may only be applied in a vararg position. See https://youtrack.jetbrains.com/issue/KT-48162 for more details.");
        diagnosticFactoryToRendererMap3.put(Errors.SPREAD_OF_NULLABLE, "The spread operator (*foo) may not be applied to an argument of nullable type");
        diagnosticFactoryToRendererMap3.put(Errors.SPREAD_OF_LAMBDA_OR_CALLABLE_REFERENCE, "The spread operator (*foo) cannot be applied to lambda argument or callable reference");
        diagnosticFactoryToRendererMap3.put(Errors.MANY_LAMBDA_EXPRESSION_ARGUMENTS, "Only one lambda expression is allowed outside a parenthesized argument list");
        diagnosticFactoryToRendererMap3.put(Errors.UNEXPECTED_TRAILING_LAMBDA_ON_A_NEW_LINE, "Expression is treated as a trailing lambda argument; consider separating it from call with semicolon");
        diagnosticFactoryToRendererMap3.put(Errors.PROPERTY_WITH_NO_TYPE_NO_INITIALIZER, "This property must either have a type annotation, be initialized or be delegated");
        diagnosticFactoryToRendererMap3.put(Errors.VARIABLE_WITH_NO_TYPE_NO_INITIALIZER, "This variable must either have a type annotation or be initialized");
        diagnosticFactoryToRendererMap3.put(Errors.INITIALIZER_REQUIRED_FOR_DESTRUCTURING_DECLARATION, "Initializer required for destructuring declaration");
        DiagnosticFactory2<KtExpression, Name, KotlinType> diagnosticFactory8 = Errors.COMPONENT_FUNCTION_MISSING;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer8 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap3.put(diagnosticFactory8, "Destructuring declaration initializer of type {1} must have a ''{0}()'' function", contextIndependentParameterRenderer8, smartTypeRenderer);
        diagnosticFactoryToRendererMap3.put(Errors.COMPONENT_FUNCTION_ON_NULLABLE, "Not nullable value required to call ''{0}()'' function of destructuring declaration initializer", contextIndependentParameterRenderer8);
        diagnosticFactoryToRendererMap3.put(Errors.COMPONENT_FUNCTION_AMBIGUITY, "Function ''{0}()'' is ambiguous for this expression: {1}", contextIndependentParameterRenderer8, Renderers.AMBIGUOUS_CALLS);
        DiagnosticFactory3<KtExpression, Name, KotlinType, KotlinType> diagnosticFactory9 = Errors.COMPONENT_FUNCTION_RETURN_TYPE_MISMATCH;
        SmartTypeRenderer smartTypeRenderer2 = Renderers.RENDER_TYPE;
        diagnosticFactoryToRendererMap3.put(diagnosticFactory9, "''{0}()'' function returns ''{1}'', but ''{2}'' is expected", contextIndependentParameterRenderer8, smartTypeRenderer2, smartTypeRenderer2);
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_PROPERTY_IN_PRIMARY_CONSTRUCTOR_PARAMETERS, "Property in primary constructor cannot be declared abstract");
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_PROPERTY_WITH_INITIALIZER, "Property with initializer cannot be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_PROPERTY_WITH_GETTER, "Property with getter implementation cannot be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_PROPERTY_WITH_SETTER, "Property with setter implementation cannot be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_DELEGATED_PROPERTY, "Delegated property cannot be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.ACCESSOR_FOR_DELEGATED_PROPERTY, "Delegated property cannot have accessors with non-default implementations");
        diagnosticFactoryToRendererMap3.put(Errors.DELEGATED_PROPERTY_IN_INTERFACE, "Delegated properties are not allowed in interfaces");
        DiagnosticFactory1<KtModifierListOwner, String> diagnosticFactory10 = Errors.INAPPLICABLE_LATEINIT_MODIFIER;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer9 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap3.put(diagnosticFactory10, "''lateinit'' modifier {0}", contextIndependentParameterRenderer9);
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_ON_NON_LITERAL, "This declaration can only be called on a property literal (e.g. 'Foo::bar')");
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_ON_NON_LATEINIT, "This declaration can only be called on a reference to a lateinit property");
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_IN_INLINE_FUNCTION, "This declaration can not be used inside an inline function");
        DiagnosticFactory1<PsiElement, PropertyDescriptor> diagnosticFactory11 = Errors.LATEINIT_INTRINSIC_CALL_ON_NON_ACCESSIBLE_PROPERTY;
        SmartDescriptorRenderer smartDescriptorRenderer2 = Renderers.COMPACT;
        diagnosticFactoryToRendererMap3.put(diagnosticFactory11, "Backing field of ''{0}'' is not accessible at this point", smartDescriptorRenderer2);
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_ON_NON_LITERAL_WARNING, "This declaration can only be called on a property literal (e.g. 'Foo::bar'). This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_ON_NON_LATEINIT_WARNING, "This declaration can only be called on a reference to a lateinit property. This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_IN_INLINE_FUNCTION_WARNING, "This declaration can not be used inside an inline function. This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.LATEINIT_INTRINSIC_CALL_ON_NON_ACCESSIBLE_PROPERTY_WARNING, "Backing field of ''{0}'' is not accessible at this point. This warning will become an error in future releases.", smartDescriptorRenderer2);
        diagnosticFactoryToRendererMap3.put(Errors.GETTER_VISIBILITY_DIFFERS_FROM_PROPERTY_VISIBILITY, "Getter visibility must be the same as property visibility");
        diagnosticFactoryToRendererMap3.put(Errors.SETTER_VISIBILITY_INCONSISTENT_WITH_PROPERTY_VISIBILITY, "Setter visibility must be the same or less permissive than property visibility");
        diagnosticFactoryToRendererMap3.put(Errors.PRIVATE_SETTER_FOR_ABSTRACT_PROPERTY, "Private setters are not allowed for abstract properties");
        diagnosticFactoryToRendererMap3.put(Errors.PRIVATE_SETTER_FOR_OPEN_PROPERTY, "Private setters are not allowed for open properties");
        diagnosticFactoryToRendererMap3.put(Errors.BACKING_FIELD_IN_INTERFACE, "Property in an interface cannot have a backing field");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED, "Property must be initialized");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_WARNING, "Property must be initialized. This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_OR_BE_FINAL, "Property must be initialized or be final");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_OR_BE_FINAL_WARNING, "Property must be initialized or be final. This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_OR_BE_ABSTRACT, "Property must be initialized or be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_OR_BE_ABSTRACT_WARNING, "Property must be initialized or be abstract. This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_OR_FINAL_OR_ABSTRACT, "Property must be initialized, be final, or be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.MUST_BE_INITIALIZED_OR_FINAL_OR_ABSTRACT_WARNING, "Property must be initialized, be final, or be abstract. This warning will become an error in future releases.");
        diagnosticFactoryToRendererMap3.put(Errors.EXTENSION_PROPERTY_MUST_HAVE_ACCESSORS_OR_BE_ABSTRACT, "Extension property must have accessors or be abstract");
        diagnosticFactoryToRendererMap3.put(Errors.UNNECESSARY_LATEINIT, "Lateinit is unnecessary: definitely initialized in constructors");
        diagnosticFactoryToRendererMap3.put(Errors.PROPERTY_INITIALIZER_IN_INTERFACE, "Property initializers are not allowed in interfaces");
        diagnosticFactoryToRendererMap3.put(Errors.PRIVATE_PROPERTY_IN_INTERFACE, "Abstract property in an interface cannot be private");
        diagnosticFactoryToRendererMap3.put(Errors.EXTENSION_PROPERTY_WITH_BACKING_FIELD, "Extension property cannot be initialized because it has no backing field");
        diagnosticFactoryToRendererMap3.put(Errors.CONTEXT_RECEIVERS_WITH_BACKING_FIELD, "Property with context receivers cannot be initialized because it has no backing field");
        diagnosticFactoryToRendererMap3.put(Errors.PROPERTY_INITIALIZER_NO_BACKING_FIELD, "Initializer is not allowed here because this property has no backing field");
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_PROPERTY_IN_NON_ABSTRACT_CLASS, "Abstract property ''{0}'' in non-abstract class ''{1}''", contextIndependentParameterRenderer9, contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS, "Abstract function ''{0}'' in non-abstract class ''{1}''", contextIndependentParameterRenderer9, contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.ABSTRACT_FUNCTION_WITH_BODY, "A function ''{0}'' with body cannot be abstract", contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.NON_ABSTRACT_FUNCTION_WITH_NO_BODY, "Function ''{0}'' without a body must be abstract", contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.PRIVATE_FUNCTION_WITH_NO_BODY, "Function ''{0}'' without body cannot be private", contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.NON_MEMBER_FUNCTION_NO_BODY, "Function ''{0}'' must have a body", contextIndependentParameterRenderer7);
        diagnosticFactoryToRendererMap3.put(Errors.FUNCTION_DECLARATION_WITH_NO_NAME, "Function declaration must have a name");
        diagnosticFactoryToRendererMap3.put(Errors.ANONYMOUS_FUNCTION_WITH_NAME, "Anonymous functions with names are prohibited");
        diagnosticFactoryToRendererMap3.put(Errors.NON_FINAL_MEMBER_IN_FINAL_CLASS, "'open' has no effect in a final class");
        diagnosticFactoryToRendererMap3.put(Errors.NON_FINAL_MEMBER_IN_OBJECT, "'open' has no effect in an object");
        diagnosticFactoryToRendererMap3.put(Errors.ANONYMOUS_FUNCTION_PARAMETER_WITH_DEFAULT_VALUE, "An anonymous function is not allowed to specify default values for its parameters");
        diagnosticFactoryToRendererMap3.put(Errors.USELESS_VARARG_ON_PARAMETER, "Vararg on this parameter is useless");
        diagnosticFactoryToRendererMap3.put(Errors.MULTIPLE_VARARG_PARAMETERS, "Multiple vararg-parameters are prohibited");
        diagnosticFactoryToRendererMap3.put(Errors.FORBIDDEN_VARARG_PARAMETER_TYPE, "Forbidden vararg parameter type: {0}", smartTypeRenderer2);
        diagnosticFactoryToRendererMap3.put(Errors.CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS, "Arguments execution order is going to be changed in a future release. The expression for named vararg argument will be executed in the order in which it was listed, not at the end. See KT-17691 for more details.");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_DECLARATION_WITH_BODY, "Expected declaration must not have a body");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_CLASS_CONSTRUCTOR_DELEGATION_CALL, "Explicit delegation call for constructor of an expected class is not allowed");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER, "Expected class constructor cannot have a property parameter");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_ENUM_CONSTRUCTOR, "Expected enum class cannot have a constructor");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_ENUM_ENTRY_WITH_BODY, "Expected enum entry cannot have a body");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_PROPERTY_INITIALIZER, "Expected property cannot have an initializer");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_DELEGATED_PROPERTY, "Expected property cannot be delegated");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_LATEINIT_PROPERTY, "Expected property cannot be lateinit");
        diagnosticFactoryToRendererMap3.put(Errors.SUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS, "Expected classes cannot initialize supertypes");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_PRIVATE_DECLARATION, "Expected declaration cannot be private");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_EXTERNAL_DECLARATION, "Expected declaration cannot be external");
        diagnosticFactoryToRendererMap3.put(Errors.EXPECTED_TAILREC_FUNCTION, "Expected function cannot have 'tailrec' modifier");
        diagnosticFactoryToRendererMap3.put(Errors.IMPLEMENTATION_BY_DELEGATION_IN_EXPECT_CLASS, "Implementation by delegation in expected classes is prohibited");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_TYPE_ALIAS_NOT_TO_CLASS, "Right-hand side of actual type alias should be a class, not another type alias");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_TYPE_ALIAS_TO_CLASS_WITH_DECLARATION_SITE_VARIANCE, "Aliased class should not have type parameters with declaration-site variance");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_TYPE_ALIAS_WITH_USE_SITE_VARIANCE, "Right-hand side of actual type alias cannot contain use-site variance or star projections");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_TYPE_ALIAS_WITH_COMPLEX_SUBSTITUTION, "Type arguments in the right-hand side of actual type alias should be its type parameters in the same order, e.g. 'actual typealias Foo<A, B> = Bar<A, B>'");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_TYPE_ALIAS_TO_NULLABLE_TYPE, "Right-hand side of actual type alias cannot be a nullable type");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_TYPE_ALIAS_TO_NOTHING, "Right-hand side of actual type alias cannot be of type kotlin.Nothing");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_FUNCTION_WITH_DEFAULT_ARGUMENTS, "Actual function cannot have default argument values, they should be declared in the expected function");
        diagnosticFactoryToRendererMap3.put(Errors.ACTUAL_ANNOTATION_CONFLICTING_DEFAULT_ARGUMENT_VALUE, "Parameter ''{0}'' has conflicting values in the expected and actual annotation", contextIndependentParameterRenderer7);
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap4 = MAP;
        diagnosticFactoryToRendererMap4.put(Errors.DEFAULT_ARGUMENTS_IN_EXPECT_WITH_ACTUAL_TYPEALIAS, "Default argument values inside expect declaration ''{0}'' are not allowed if it is actualized via typealias. Possible fix is to remove default argument values in members:{1}", contextIndependentParameterRenderer7, Renderers.DESCRIPTORS_ON_NEWLINE_WITH_INDENT);
        DiagnosticFactory3<KtNamedDeclaration, MemberDescriptor, ModuleDescriptor, Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, Collection<MemberDescriptor>>> diagnosticFactory12 = Errors.NO_ACTUAL_FOR_EXPECT;
        ContextIndependentParameterRenderer<DeclarationDescriptor> contextIndependentParameterRenderer10 = Renderers.DECLARATION_NAME_WITH_KIND;
        ContextIndependentParameterRenderer<ModuleDescriptor> contextIndependentParameterRenderer11 = Renderers.MODULE_WITH_PLATFORM;
        PlatformIncompatibilityDiagnosticRenderer platformIncompatibilityDiagnosticRenderer = PlatformIncompatibilityDiagnosticRenderer.TEXT;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory12, "Expected {0} has no actual declaration in module {1}{2}", contextIndependentParameterRenderer10, contextIndependentParameterRenderer11, adaptGenerics1(platformIncompatibilityDiagnosticRenderer));
        diagnosticFactoryToRendererMap4.put(Errors.IMPLICIT_JVM_ACTUALIZATION, "Expected {0} is implicitly actualized by Java declaration in module {1}. Please migrate to explicit ''actual typealias''. See: https://youtrack.jetbrains.com/issue/KT-58545", contextIndependentParameterRenderer10, contextIndependentParameterRenderer11);
        DiagnosticFactory2<KtNamedDeclaration, MemberDescriptor, Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, Collection<MemberDescriptor>>> diagnosticFactory13 = Errors.ACTUAL_WITHOUT_EXPECT;
        ContextIndependentParameterRenderer<DeclarationDescriptor> contextIndependentParameterRenderer12 = Renderers.CAPITALIZED_DECLARATION_NAME_WITH_KIND_AND_PLATFORM;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory13, "{0} has no corresponding expected declaration{1}", contextIndependentParameterRenderer12, adaptGenerics1(platformIncompatibilityDiagnosticRenderer));
        DiagnosticFactory2<KtNamedDeclaration, DeclarationDescriptor, Collection<ModuleDescriptor>> diagnosticFactory14 = Errors.AMBIGUOUS_ACTUALS;
        ContextIndependentParameterRenderer<ModuleDescriptor> contextIndependentParameterRenderer13 = Renderers.MODULE;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory14, "{0} has several compatible actual declarations in modules {1}", contextIndependentParameterRenderer12, CommonRenderers.commaSeparated(contextIndependentParameterRenderer13));
        diagnosticFactoryToRendererMap4.put(Errors.AMBIGUOUS_EXPECTS, "{0} has several compatible expect declarations in modules {1}", contextIndependentParameterRenderer12, CommonRenderers.commaSeparated(contextIndependentParameterRenderer13));
        DiagnosticFactory2<KtNamedDeclaration, ClassDescriptor, List<Pair<MemberDescriptor, Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, Collection<MemberDescriptor>>>>> diagnosticFactory15 = Errors.NO_ACTUAL_CLASS_MEMBER_FOR_EXPECTED_CLASS;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer14 = Renderers.NAME;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory15, "Actual class ''{0}'' has no corresponding members for expected class members:{1}", contextIndependentParameterRenderer14, adaptGenerics2(IncompatibleExpectedActualClassScopesRenderer.TEXT));
        diagnosticFactoryToRendererMap4.put(Errors.ACTUAL_MISSING, "Declaration must be marked with 'actual'");
        diagnosticFactoryToRendererMap4.put(Errors.EXPECT_AND_ACTUAL_IN_THE_SAME_MODULE, "{0}: expect and corresponding actual are declared in the same module, which will be prohibited in Kotlin 2.0. See https://youtrack.jetbrains.com/issue/KT-55177", contextIndependentParameterRenderer12);
        diagnosticFactoryToRendererMap4.put(Errors.ACTUAL_CLASSIFIER_MUST_HAVE_THE_SAME_MEMBERS_AS_NON_FINAL_EXPECT_CLASSIFIER_WARNING, "{0}: actual class and its non-final expect class must declare exactly the same non-private members. The following non-private members in actual class are mismatched:{1}\nThis error happens because the expect class ''{2}'' is non-final. This warning will become an error in future releases.\nAlso see https://youtrack.jetbrains.com/issue/KT-22841 for more details", contextIndependentParameterRenderer12, ExpectActualScopeDiffsRenderer.TEXT, contextIndependentParameterRenderer14);
        DiagnosticFactory1<KtCallableDeclaration, K1ExpectActualMemberDiff<CallableMemberDescriptor, ClassDescriptor>> diagnosticFactory16 = Errors.UNKNOWN_PROBLEM_DURING_NON_FINAL_CLASSIFIER_ACTUALIZATION_WARNING;
        ExpectActualScopeDiffRenderer expectActualScopeDiffRenderer = ExpectActualScopeDiffRenderer.INSTANCE;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory16, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.RETURN_TYPE_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.MODALITY_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.VISIBILITY_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.PARAMETER_NAME_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.PROPERTY_KIND_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.LATEINIT_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.VARARG_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.SETTER_VISIBILITY_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.TYPE_PARAMETER_NAMES_CHANGED_IN_NON_FINAL_EXPECT_CLASSIFIER_ACTUALIZATION_WARNING, "{0}. This warning will become an error in future releases. Also see https://youtrack.jetbrains.com/issue/KT-22841 for more details", expectActualScopeDiffRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING, "'expect'/'actual' classes (including interfaces, objects, annotations, enums, and 'actual' typealiases) are in Beta. You can use -Xexpect-actual-classes flag to suppress this warning. Also see: https://youtrack.jetbrains.com/issue/KT-61573");
        diagnosticFactoryToRendererMap4.put(Errors.OPTIONAL_EXPECTATION_NOT_ON_EXPECTED, "'@OptionalExpectation' can only be used on an expected annotation class");
        diagnosticFactoryToRendererMap4.put(Errors.OPTIONAL_DECLARATION_OUTSIDE_OF_ANNOTATION_ENTRY, "Declaration annotated with '@OptionalExpectation' can only be used inside an annotation entry");
        diagnosticFactoryToRendererMap4.put(Errors.OPTIONAL_DECLARATION_USAGE_IN_NON_COMMON_SOURCE, "Declaration annotated with '@OptionalExpectation' can only be used in common module sources");
        diagnosticFactoryToRendererMap4.put(Errors.NOT_A_MULTIPLATFORM_COMPILATION, "'expect' and 'actual' declarations can be used only in multiplatform projects. Learn more about Kotlin Multiplatform: https://kotl.in/multiplatform-setup");
        diagnosticFactoryToRendererMap4.put(Errors.EXPECT_ACTUAL_OPT_IN_ANNOTATION, "Opt-in annotations are prohibited to be `expect` or `actual`. Instead, declare annotation once in common sources.");
        DiagnosticFactory1<KtTypeAlias, ClassId> diagnosticFactory17 = Errors.ACTUAL_TYPEALIAS_TO_SPECIAL_ANNOTATION;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer15 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory17, "`actual typealias` to annotation which affects code compilation can lead to incorrect behavior. Instead, use ''{0}'' annotation directly.", contextIndependentParameterRenderer15);
        DiagnosticFactory4<KtNamedDeclaration, DeclarationDescriptor, DeclarationDescriptor, Optional<SourceElement>, ExpectActualAnnotationsIncompatibilityType<AnnotationDescriptor>> diagnosticFactory18 = Errors.ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT;
        SmartDescriptorRenderer smartDescriptorRenderer3 = ExpectActualAnnotationIncompatibilityDiagnosticRenderers.DESCRIPTOR_RENDERER;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory18, "{3}.\nAll annotations from expect `{0}` must be present with the same arguments on actual `{1}`, otherwise they might behave incorrectly.", smartDescriptorRenderer3, smartDescriptorRenderer3, Renderers.NOT_RENDERED, ExpectActualAnnotationIncompatibilityDiagnosticRenderers.INCOMPATIBILITY);
        diagnosticFactoryToRendererMap4.put(Errors.PROJECTION_ON_NON_CLASS_TYPE_ARGUMENT, "Projections are not allowed on type arguments of functions and properties");
        diagnosticFactoryToRendererMap4.put(Errors.SUPERTYPE_NOT_INITIALIZED, "This type has a constructor, and thus must be initialized here");
        diagnosticFactoryToRendererMap4.put(Errors.NOTHING_TO_OVERRIDE, "''{0}'' overrides nothing", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.VIRTUAL_MEMBER_HIDDEN, "''{0}'' hides member of supertype ''{2}'' and needs ''override'' modifier", contextIndependentParameterRenderer14, contextIndependentParameterRenderer14, contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.DATA_CLASS_OVERRIDE_CONFLICT, "Function ''{0}'' generated for the data class conflicts with member of supertype ''{1}''", contextIndependentParameterRenderer14, contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.DATA_CLASS_OVERRIDE_DEFAULT_VALUES, "Function ''{0}'' generated for the data class has default values for parameters, and conflicts with member of supertype ''{1}''", contextIndependentParameterRenderer14, contextIndependentParameterRenderer14);
        DiagnosticFactory2<KtModifierListOwner, CallableMemberDescriptor, CallableDescriptor> diagnosticFactory19 = Errors.CANNOT_OVERRIDE_INVISIBLE_MEMBER;
        SmartDescriptorRenderer smartDescriptorRenderer4 = Renderers.FQ_NAMES_IN_TYPES;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory19, "''{0}'' has no access to ''{1}'', so it cannot override it", smartDescriptorRenderer4, smartDescriptorRenderer4);
        DiagnosticFactory1<KtDeclaration, CallableMemberDescriptor> diagnosticFactory20 = Errors.CANNOT_INFER_VISIBILITY;
        SmartDescriptorRenderer smartDescriptorRenderer5 = Renderers.COMPACT;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory20, "Cannot infer visibility for ''{0}''. Please specify it explicitly", smartDescriptorRenderer5);
        diagnosticFactoryToRendererMap4.put(Errors.ENUM_ENTRY_SHOULD_BE_INITIALIZED, "Enum has no default constructor, use 'entry(parameters)'");
        diagnosticFactoryToRendererMap4.put(Errors.UNINITIALIZED_VARIABLE, "Variable ''{0}'' must be initialized", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNINITIALIZED_PARAMETER, "Parameter ''{0}'' is uninitialized here", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNINITIALIZED_PARAMETER_WARNING, "Parameter ''{0}'' is uninitialized here. This warning will be an error in future releases", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNINITIALIZED_ENUM_ENTRY, "Enum entry ''{0}'' is uninitialized here", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNINITIALIZED_ENUM_COMPANION, "Companion object of enum class ''{0}'' is uninitialized here", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_VARIABLE, "Variable ''{0}'' is never used", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNINITIALIZED_ENUM_COMPANION_WARNING, "Companion object of enum class ''{0}'' is uninitialized here. This warning will become an error in future releases. See https://youtrack.jetbrains.com/issue/KT-49110 for details", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_PARAMETER, "Parameter ''{0}'' is never used", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_ANONYMOUS_PARAMETER, "Parameter ''{0}'' is never used, could be renamed to _", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_DESTRUCTURED_PARAMETER_ENTRY, "Destructured parameter ''{0}'' is never used", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE, "Variable ''{0}'' is assigned but never accessed", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.VARIABLE_WITH_REDUNDANT_INITIALIZER, "Variable ''{0}'' initializer is redundant", contextIndependentParameterRenderer14);
        DiagnosticFactory2<KtBinaryExpression, KtElement, DeclarationDescriptor> diagnosticFactory21 = Errors.UNUSED_VALUE;
        ContextIndependentParameterRenderer<PsiElement> contextIndependentParameterRenderer16 = Renderers.ELEMENT_TEXT;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory21, "The value ''{0}'' assigned to ''{1}'' is never used", contextIndependentParameterRenderer16, smartDescriptorRenderer4);
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_CHANGED_VALUE, "The value changed at ''{0}'' is never used", contextIndependentParameterRenderer16);
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_EXPRESSION, "The expression is unused");
        diagnosticFactoryToRendererMap4.put(Errors.UNUSED_LAMBDA_EXPRESSION, "The lambda expression is unused. If you mean a block, you can use 'run { ... }'");
        diagnosticFactoryToRendererMap4.put(Errors.VAL_REASSIGNMENT, "Val cannot be reassigned", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.VAL_REASSIGNMENT_VIA_BACKING_FIELD, "Reassignment of read-only property via backing field", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.CAPTURED_VAL_INITIALIZATION, "Captured values initialization is forbidden due to possible reassignment", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.CAPTURED_MEMBER_VAL_INITIALIZATION, "Captured member values initialization is forbidden due to possible reassignment", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.SETTER_PROJECTED_OUT, "Setter for ''{0}'' is removed by type projection", contextIndependentParameterRenderer14);
        DiagnosticFactory3<PsiElement, DeclarationDescriptor, DescriptorVisibility, DeclarationDescriptor> diagnosticFactory22 = Errors.INVISIBLE_SETTER;
        ContextIndependentParameterRenderer<DescriptorVisibility> contextIndependentParameterRenderer17 = Renderers.VISIBILITY;
        ContextIndependentParameterRenderer<DeclarationDescriptor> contextIndependentParameterRenderer18 = Renderers.NAME_OF_CONTAINING_DECLARATION_OR_FILE;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory22, "Cannot assign to ''{0}'': the setter is {1} in {2}", contextIndependentParameterRenderer14, contextIndependentParameterRenderer17, contextIndependentParameterRenderer18);
        diagnosticFactoryToRendererMap4.put(Errors.INVISIBLE_SETTER_FROM_DERIVED, "Cannot assign to ''{0}'': the setter is {1} in {2}. This warning will be an error soon. See https://youtrack.jetbrains.com/issue/KT-56662 for details", contextIndependentParameterRenderer14, contextIndependentParameterRenderer17, contextIndependentParameterRenderer18);
        diagnosticFactoryToRendererMap4.put(Errors.INITIALIZATION_BEFORE_DECLARATION, "Variable cannot be initialized before declaration", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap4.put(Errors.VARIABLE_EXPECTED, "Variable expected");
        diagnosticFactoryToRendererMap4.put(Errors.VAL_OR_VAR_ON_LOOP_PARAMETER, "''{0}'' on loop parameter is not allowed", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.VAL_OR_VAR_ON_FUN_PARAMETER, "''{0}'' on function parameter is not allowed", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.VAL_OR_VAR_ON_CATCH_PARAMETER, "''{0}'' on catch parameter is not allowed", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.VAL_OR_VAR_ON_SECONDARY_CONSTRUCTOR_PARAMETER, "''{0}'' on secondary constructor parameter is not allowed", contextIndependentParameterRenderer15);
        DiagnosticFactory2<KtElement, Set<KtElement>, Set<KtElement>> diagnosticFactory23 = Errors.UNREACHABLE_CODE;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer19 = CommonRenderers.EMPTY;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory23, "Unreachable code", contextIndependentParameterRenderer19, contextIndependentParameterRenderer19);
        diagnosticFactoryToRendererMap4.put(Errors.MANY_COMPANION_OBJECTS, "Only one companion object is allowed per class");
        diagnosticFactoryToRendererMap4.put(Errors.SELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR, "Self references to members of containing class are prohibited in constructor of nested object");
        DiagnosticFactory2<PsiElement, DeclarationDescriptor, String> diagnosticFactory24 = Errors.DEPRECATION;
        SmartDescriptorRenderer smartDescriptorRenderer6 = Renderers.DEPRECATION_RENDERER;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer20 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap4.put(diagnosticFactory24, "''{0}'' is deprecated. {1}", smartDescriptorRenderer6, contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap4.put(Errors.DEPRECATION_ERROR, "Using ''{0}'' is an error. {1}", smartDescriptorRenderer6, contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap4.put(Errors.TYPEALIAS_EXPANSION_DEPRECATION, "''{0}'' uses ''{1}'', which is deprecated. {2}", smartDescriptorRenderer6, smartDescriptorRenderer6, contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap4.put(Errors.TYPEALIAS_EXPANSION_DEPRECATION_ERROR, "''{0}'' uses ''{1}'', which is an error. {2}", smartDescriptorRenderer6, smartDescriptorRenderer6, contextIndependentParameterRenderer20);
        DiagnosticParameterRenderer diagnosticParameterRenderer = new DiagnosticParameterRenderer() { // from class: dg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.o((Pair) obj, renderingContext);
            }
        };
        diagnosticFactoryToRendererMap4.put(Errors.VERSION_REQUIREMENT_DEPRECATION, "''{0}''{1} should not be used in Kotlin {2}", smartDescriptorRenderer6, new DiagnosticParameterRenderer() { // from class: eg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.g((VersionRequirement.Version) obj, renderingContext);
            }
        }, diagnosticParameterRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.VERSION_REQUIREMENT_DEPRECATION_ERROR, "''{0}''{1} cannot be used in Kotlin {2}", smartDescriptorRenderer6, new DiagnosticParameterRenderer() { // from class: fg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.r((VersionRequirement.Version) obj, renderingContext);
            }
        }, diagnosticParameterRenderer);
        diagnosticFactoryToRendererMap4.put(Errors.OVERRIDE_DEPRECATION, "This declaration overrides deprecated member but not marked as deprecated itself. {0}Please add @Deprecated annotation or suppress. See https://youtrack.jetbrains.com/issue/KT-47902 for details", contextIndependentParameterRenderer15, contextIndependentParameterRenderer19, contextIndependentParameterRenderer19);
        diagnosticFactoryToRendererMap4.put(Errors.DEPRECATED_SINCE_KOTLIN_WITHOUT_DEPRECATED, "DeprecatedSinceKotlin annotation can be used only together with Deprecated annotation");
        diagnosticFactoryToRendererMap4.put(Errors.DEPRECATED_SINCE_KOTLIN_WITH_DEPRECATED_LEVEL, "DeprecatedSinceKotlin annotation can be used only with unspecified deprecation level of Deprecated annotation");
        diagnosticFactoryToRendererMap4.put(Errors.DEPRECATED_SINCE_KOTLIN_WITH_UNORDERED_VERSIONS, "Values of DeprecatedSinceKotlin annotation should be ordered so 'warningSince' <= 'errorSince' <= 'hiddenSince' if specified");
        diagnosticFactoryToRendererMap4.put(Errors.DEPRECATED_SINCE_KOTLIN_WITHOUT_ARGUMENTS, "DeprecatedSinceKotlin annotation should have at least one argument");
        diagnosticFactoryToRendererMap4.put(Errors.DEPRECATED_SINCE_KOTLIN_OUTSIDE_KOTLIN_SUBPACKAGE, "DeprecatedSinceKotlin annotation cannot be used outside 'kotlin' subpackages");
        diagnosticFactoryToRendererMap4.put(Errors.API_NOT_AVAILABLE, "This declaration is only available since Kotlin {0} and cannot be used with the specified API version {1}", contextIndependentParameterRenderer20, contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap4.put(Errors.MISSING_DEPENDENCY_CLASS, "Cannot access class ''{0}''. Check your module classpath for missing or conflicting dependencies", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.MISSING_DEPENDENCY_SUPERCLASS, "Cannot access ''{0}'' which is a supertype of ''{1}''. Check your module classpath for missing or conflicting dependencies", contextIndependentParameterRenderer15, contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.MISSING_BUILT_IN_DECLARATION, "Cannot access built-in declaration ''{0}''. Ensure that you have a dependency on the Kotlin standard library", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.MISSING_SCRIPT_BASE_CLASS, "Cannot access script base class ''{0}''. Check your module classpath for missing or conflicting dependencies", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.MISSING_SCRIPT_STANDARD_TEMPLATE, "No script runtime was found in the classpath: class ''{0}'' not found. Please add kotlin-script-runtime.jar to the module dependencies.", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap4.put(Errors.MISSING_SCRIPT_RECEIVER_CLASS, "Cannot access implicit script receiver class ''{0}''. Check your module classpath for missing or conflicting dependencies", contextIndependentParameterRenderer15);
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap5 = MAP;
        diagnosticFactoryToRendererMap5.put(Errors.MISSING_IMPORTED_SCRIPT_FILE, "Cannot find imported script file ''{0}''. Check your script imports", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.MISSING_IMPORTED_SCRIPT_PSI, "Imported script file ''{0}'' is not loaded. Check your script imports", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.MISSING_SCRIPT_PROVIDED_PROPERTY_CLASS, "Cannot access script provided property class ''{0}''. Check your module classpath for missing or conflicting dependencies", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.PRE_RELEASE_CLASS, "{0} is compiled by a pre-release version of Kotlin and cannot be loaded by this version of the compiler", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.IR_WITH_UNSTABLE_ABI_COMPILED_CLASS, "{0} is compiled by an unstable version of the Kotlin compiler and cannot be loaded by this compiler", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.FIR_COMPILED_CLASS, "{0} is compiled by the new Kotlin compiler frontend and cannot be loaded by the old compiler", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.INCOMPATIBLE_CLASS, "{0} was compiled with an incompatible version of Kotlin. {1}", contextIndependentParameterRenderer15, new DiagnosticParameterRenderer() { // from class: gg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.l((IncompatibleVersionErrorData) obj, renderingContext);
            }
        });
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_OBJECT_NOT_ALLOWED, "Named object ''{0}'' is a singleton and cannot be local. Try to use anonymous object instead", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_INTERFACE_NOT_ALLOWED, "''{0}'' is an interface so it cannot be local. Try to use anonymous object or abstract class instead", contextIndependentParameterRenderer14);
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_PARAMETERS_IN_OBJECT, "Type parameters are not allowed for objects");
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_PARAMETERS_IN_ANONYMOUS_OBJECT, "Type parameters for anonymous objects are deprecated");
        diagnosticFactoryToRendererMap5.put(Errors.ENUM_CLASS_CONSTRUCTOR_CALL, "Enum types cannot be instantiated");
        diagnosticFactoryToRendererMap5.put(Errors.SEALED_CLASS_CONSTRUCTOR_CALL, "Sealed types cannot be instantiated");
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATION_IN_INTERFACE, "Interfaces cannot use delegation");
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATION_NOT_TO_INTERFACE, "Only interfaces can be delegated to");
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE, "Delegated member ''{0}'' hides supertype override: {1}. Please specify proper override explicitly", smartDescriptorRenderer5, CommonRenderers.commaSeparated(Renderers.SHORT_NAMES_IN_TYPES));
        diagnosticFactoryToRendererMap5.put(Errors.NO_CONSTRUCTOR, "This class does not have a constructor");
        diagnosticFactoryToRendererMap5.put(Errors.NO_CONSTRUCTOR_WARNING, "This class does not have a constructor. This warning will be an error in future releases");
        DiagnosticFactory3<KtReferenceExpression, ClassifierDescriptor, WrongResolutionToClassifier, String> diagnosticFactory25 = Errors.RESOLUTION_TO_CLASSIFIER;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer21 = Renderers.NAME;
        diagnosticFactoryToRendererMap5.put(diagnosticFactory25, "{2}", contextIndependentParameterRenderer21, contextIndependentParameterRenderer15, contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap5.put(Errors.NOT_A_CLASS, "Not a class");
        diagnosticFactoryToRendererMap5.put(Errors.ILLEGAL_ESCAPE_SEQUENCE, "Illegal escape sequence");
        diagnosticFactoryToRendererMap5.put(Errors.UNSIGNED_LITERAL_WITHOUT_DECLARATIONS_ON_CLASSPATH, "Type of the constant expression cannot be resolved. Please make sure you have the required dependencies for unsigned types in the classpath");
        diagnosticFactoryToRendererMap5.put(Errors.SIGNED_CONSTANT_CONVERTED_TO_UNSIGNED, "Conversion of signed constants to unsigned ones is prohibited");
        DiagnosticFactory1<KtExpression, KotlinType> diagnosticFactory26 = Errors.INTEGER_OPERATOR_RESOLVE_WILL_CHANGE;
        SmartTypeRenderer smartTypeRenderer3 = Renderers.RENDER_TYPE;
        diagnosticFactoryToRendererMap5.put(diagnosticFactory26, "This expression will be resolved to {0} in future releases. Please add explicit conversion call", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.NON_TRIVIAL_BOOLEAN_CONSTANT, "Compiler won''t reduce this expression to {0} in future. Please replace it with a boolean literal. See https://youtrack.jetbrains.com/issue/KT-39883 for details", contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS, "Left-hand side of callable reference matches expression syntax reserved for future releases");
        diagnosticFactoryToRendererMap5.put(Errors.PARENTHESIZED_COMPANION_LHS_DEPRECATION, "Access to companion object through parenthesized class name is deprecated. Please, add explicit Companion qualifier.");
        diagnosticFactoryToRendererMap5.put(Errors.INCORRECT_CALLABLE_REFERENCE_RESOLUTION_FOR_COMPANION_LHS, "Callable reference to the companion's member is incorrectly resolved as unbound. Please, add explicit Companion qualifier to the left-hand-side. See https://youtrack.jetbrains.com/issue/KT-54316 for details");
        diagnosticFactoryToRendererMap5.put(Errors.RESOLUTION_TO_PRIVATE_CONSTRUCTOR_OF_SEALED_CLASS, "The private constructor of a sealed class will become inaccessible here in future. See https://youtrack.jetbrains.com/issue/KT-44866 for details");
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_MISMATCH_WARNING_FOR_INCORRECT_CAPTURE_APPROXIMATION, "Type mismatch: inferred type is {1} but {0} was expected. This warning will be an error soon. See https://youtrack.jetbrains.com/issue/KT-49404 for details", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.RECEIVER_TYPE_MISMATCH_WARNING_FOR_INCORRECT_CAPTURE_APPROXIMATION, "Extension receiver type mismatch: inferred type is {1} but {0} was expected. This warning will be an error soon. See https://youtrack.jetbrains.com/issue/KT-49404 for details", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.RECEIVER_TYPE_MISMATCH, "Constraint error in receiver type argument: inferred type is {1} but {0} was expected", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_MISMATCH_IN_CONSTRAINT, "Type mismatch in constraint system: actual type is {1} but {0} was expected. Constraint position is {2}", smartTypeRenderer3, smartTypeRenderer3, contextIndependentParameterRenderer15);
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_EXTENSION_PROPERTY, "Local extension properties are not allowed");
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_VARIABLE_WITH_GETTER, "Local variables are not allowed to have getters");
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_VARIABLE_WITH_SETTER, "Local variables are not allowed to have setters");
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_VARIABLE_WITH_TYPE_PARAMETERS_WARNING, "Type parameters for local variables are deprecated");
        diagnosticFactoryToRendererMap5.put(Errors.LOCAL_VARIABLE_WITH_TYPE_PARAMETERS, "Local variables are not allowed to have type parameters");
        diagnosticFactoryToRendererMap5.put(Errors.VAL_WITH_SETTER, "A 'val'-property cannot have a setter");
        diagnosticFactoryToRendererMap5.put(Errors.DEPRECATED_IDENTITY_EQUALS, "Identity equality for arguments of types {0} and {1} is deprecated", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.IMPLICIT_BOXING_IN_IDENTITY_EQUALS, "Identity equality for arguments of types {0} and {1} can be unstable because of implicit boxing", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.FORBIDDEN_IDENTITY_EQUALS, "Identity equality for arguments of types {0} and {1} is forbidden", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.FORBIDDEN_SYNCHRONIZED_BY_VALUE_CLASSES_OR_PRIMITIVES, "Synchronizing by {0} is forbidden", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.NO_GET_METHOD, "No get method providing array access");
        diagnosticFactoryToRendererMap5.put(Errors.NO_SET_METHOD, "No set method providing array access");
        diagnosticFactoryToRendererMap5.put(Errors.INC_DEC_SHOULD_NOT_RETURN_UNIT, "Functions inc(), dec() shouldn't return Unit to be used by operators ++, --");
        diagnosticFactoryToRendererMap5.put(Errors.ASSIGNMENT_OPERATOR_SHOULD_RETURN_UNIT, "Function ''{0}'' should return Unit to be used by corresponding operator ''{1}''", contextIndependentParameterRenderer21, contextIndependentParameterRenderer16);
        DiagnosticFactory1<PsiElement, Collection<? extends ResolvedCall<?>>> diagnosticFactory27 = Errors.ASSIGN_OPERATOR_AMBIGUITY;
        ContextIndependentParameterRenderer<Collection<? extends ResolvedCall<?>>> contextIndependentParameterRenderer22 = Renderers.AMBIGUOUS_CALLS;
        diagnosticFactoryToRendererMap5.put(diagnosticFactory27, "Assignment operators ambiguity: {0}", contextIndependentParameterRenderer22);
        diagnosticFactoryToRendererMap5.put(Errors.EQUALS_MISSING, "No method 'equals(Any?): Boolean' available");
        diagnosticFactoryToRendererMap5.put(Errors.ASSIGNMENT_IN_EXPRESSION_CONTEXT, "Assignments are not expressions, and only expressions are allowed in this context");
        diagnosticFactoryToRendererMap5.put(Errors.SUPER_IS_NOT_AN_EXPRESSION, "''{0}'' is not an expression, it can only be used on the left-hand side of a dot (''.'')", contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap5.put(Errors.SUPER_CANT_BE_EXTENSION_RECEIVER, "''{0}'' is not an expression, it can not be used as a receiver for extension functions", contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap5.put(Errors.DECLARATION_IN_ILLEGAL_CONTEXT, "Declarations are not allowed in this position");
        diagnosticFactoryToRendererMap5.put(Errors.SETTER_PARAMETER_WITH_DEFAULT_VALUE, "Setter parameters cannot have default values");
        diagnosticFactoryToRendererMap5.put(Errors.NO_THIS, "'this' is not defined in this context");
        diagnosticFactoryToRendererMap5.put(Errors.SUPER_NOT_AVAILABLE, "No supertypes are accessible in this context");
        diagnosticFactoryToRendererMap5.put(Errors.SUPERCLASS_NOT_ACCESSIBLE_FROM_INTERFACE, "Superclass is not accessible from interface");
        diagnosticFactoryToRendererMap5.put(Errors.AMBIGUOUS_SUPER, "Many supertypes available, please specify the one you mean in angle brackets, e.g. 'super<Foo>'");
        diagnosticFactoryToRendererMap5.put(Errors.ABSTRACT_SUPER_CALL, "Abstract member cannot be accessed directly");
        diagnosticFactoryToRendererMap5.put(Errors.ABSTRACT_SUPER_CALL_WARNING, "Abstract fake override member access is deprecated. See https://youtrack.jetbrains.com/issue/KT-49017");
        diagnosticFactoryToRendererMap5.put(Errors.NOT_A_SUPERTYPE, "Not an immediate supertype");
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_ARGUMENTS_REDUNDANT_IN_SUPER_QUALIFIER, "Type arguments do not need to be specified in a 'super' qualifier");
        diagnosticFactoryToRendererMap5.put(Errors.QUALIFIED_SUPERTYPE_EXTENDED_BY_OTHER_SUPERTYPE, "Explicitly qualified supertype is extended by another supertype ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.USELESS_CAST, "No cast needed");
        diagnosticFactoryToRendererMap5.put(Errors.CAST_NEVER_SUCCEEDS, "This cast can never succeed");
        diagnosticFactoryToRendererMap5.put(Errors.DYNAMIC_NOT_ALLOWED, "Dynamic types are not allowed in this position");
        diagnosticFactoryToRendererMap5.put(Errors.IS_ENUM_ENTRY, "'is' over enum entry is not allowed, use comparison instead");
        diagnosticFactoryToRendererMap5.put(Errors.ENUM_ENTRY_AS_TYPE, "Use of enum entry names as types is not allowed, use enum type instead");
        diagnosticFactoryToRendererMap5.put(Errors.USELESS_NULLABLE_CHECK, "Non-null type is checked for instance of nullable type");
        DiagnosticFactory1<KtElement, Boolean> diagnosticFactory28 = Errors.USELESS_IS_CHECK;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer23 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap5.put(diagnosticFactory28, "Check for instance is always ''{0}''", contextIndependentParameterRenderer23);
        diagnosticFactoryToRendererMap5.put(Errors.WRONG_SETTER_PARAMETER_TYPE, "Setter parameter type must be equal to the type of the property, i.e. ''{0}''", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATE_USES_EXTENSION_PROPERTY_TYPE_PARAMETER, "It''s forbidden using extension property type parameter ''{0}'' in delegate. See https://youtrack.jetbrains.com/issue/KT-24643", contextIndependentParameterRenderer23);
        diagnosticFactoryToRendererMap5.put(Errors.WRONG_GETTER_RETURN_TYPE, "Getter return type must be equal to the type of the property, i.e. ''{0}''", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.WRONG_SETTER_RETURN_TYPE, "Setter return type must be Unit");
        diagnosticFactoryToRendererMap5.put(Errors.NO_COMPANION_OBJECT, "Classifier ''{0}'' does not have a companion object, and thus must be initialized here", contextIndependentParameterRenderer21);
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_PARAMETER_IS_NOT_AN_EXPRESSION, "Type parameter ''{0}'' is not an expression", contextIndependentParameterRenderer21);
        diagnosticFactoryToRendererMap5.put(Errors.TYPE_PARAMETER_ON_LHS_OF_DOT, "Type parameter ''{0}'' cannot have or inherit a companion object, so it cannot be on the left hand side of dot", contextIndependentParameterRenderer21);
        DiagnosticFactory1<KtExpression, ClassifierDescriptorWithTypeParameters> diagnosticFactory29 = Errors.NESTED_CLASS_ACCESSED_VIA_INSTANCE_REFERENCE;
        ContextIndependentParameterRenderer<ClassifierDescriptorWithTypeParameters> contextIndependentParameterRenderer24 = Renderers.RENDER_CLASS_OR_OBJECT_NAME;
        diagnosticFactoryToRendererMap5.put(diagnosticFactory29, "Nested {0} accessed via instance reference", contextIndependentParameterRenderer24);
        diagnosticFactoryToRendererMap5.put(Errors.NESTED_CLASS_SHOULD_BE_QUALIFIED, "Nested {0} should be qualified as ''{1}''", contextIndependentParameterRenderer24, contextIndependentParameterRenderer23);
        diagnosticFactoryToRendererMap5.put(Errors.INACCESSIBLE_OUTER_CLASS_EXPRESSION, "Expression is inaccessible from a nested class ''{0}''", contextIndependentParameterRenderer21);
        diagnosticFactoryToRendererMap5.put(Errors.NESTED_CLASS_NOT_ALLOWED, "{0} is not allowed here", contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap5.put(Errors.NESTED_CLASS_DEPRECATED, "{0} is deprecated here", contextIndependentParameterRenderer20);
        diagnosticFactoryToRendererMap5.put(Errors.HAS_NEXT_MISSING, "hasNext() cannot be called on iterator() of type ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.HAS_NEXT_FUNCTION_AMBIGUITY, "hasNext() is ambiguous for iterator() of type ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.HAS_NEXT_FUNCTION_NONE_APPLICABLE, "None of the hasNext() functions is applicable for iterator() of type ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.HAS_NEXT_FUNCTION_TYPE_MISMATCH, "The ''iterator().hasNext()'' function of the loop range must return Boolean, but returns {0}", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.NEXT_MISSING, "''next()'' cannot be called on ''iterator()'' of type ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.NEXT_AMBIGUITY, "''next()'' is ambiguous for ''iterator()'' of type ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.NEXT_NONE_APPLICABLE, "None of the ''next()'' functions is applicable for ''iterator()'' of type ''{0}''", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.ITERATOR_MISSING, "For-loop range must have an 'iterator()' method");
        diagnosticFactoryToRendererMap5.put(Errors.ITERATOR_ON_NULLABLE, "Not nullable value required to call an 'iterator()' method on for-loop range");
        diagnosticFactoryToRendererMap5.put(Errors.ITERATOR_AMBIGUITY, "Method ''iterator()'' is ambiguous for this expression: {0}", contextIndependentParameterRenderer22);
        DiagnosticFactory3<KtExpression, String, KotlinType, String> diagnosticFactory30 = Errors.DELEGATE_SPECIAL_FUNCTION_MISSING;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer25 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap5.put(diagnosticFactory30, "Type ''{1}'' has no method ''{0}'' and thus it cannot serve as a {2}", contextIndependentParameterRenderer25, smartTypeRenderer3, contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATE_SPECIAL_FUNCTION_AMBIGUITY, "Overload resolution ambiguity on method ''{0}'': {1}", contextIndependentParameterRenderer25, contextIndependentParameterRenderer22);
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE, "Property delegate must have a ''{0}'' method. None of the following functions is suitable: {1}", contextIndependentParameterRenderer25, contextIndependentParameterRenderer22);
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATE_SPECIAL_FUNCTION_RETURN_TYPE_MISMATCH, "The ''{0}'' function of property delegate is expected to return ''{1}'', but returns ''{2}''", contextIndependentParameterRenderer25, smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.DELEGATE_PD_METHOD_NONE_APPLICABLE, "''{0}'' method may be missing. None of the following functions will be called: {1}", contextIndependentParameterRenderer25, contextIndependentParameterRenderer22);
        diagnosticFactoryToRendererMap5.put(Errors.COMPARE_TO_TYPE_MISMATCH, "''compareTo()'' must return Int, but returns {0}", smartTypeRenderer3);
        diagnosticFactoryToRendererMap5.put(Errors.UNDERSCORE_IS_RESERVED, "Names _, __, ___, ..., are reserved in Kotlin");
        diagnosticFactoryToRendererMap5.put(Errors.UNDERSCORE_USAGE_WITHOUT_BACKTICKS, "Names _, __, ___, ... can be used only in back-ticks (`_`, `__`, `___`, ...)");
        diagnosticFactoryToRendererMap5.put(Errors.RESOLVED_TO_UNDERSCORE_NAMED_CATCH_PARAMETER, "Referencing to an underscore-named parameter is deprecated. It will be an error in a future release.");
        diagnosticFactoryToRendererMap5.put(Errors.YIELD_IS_RESERVED, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap5.put(Errors.INVALID_CHARACTERS, "Name {0}", contextIndependentParameterRenderer25);
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap6 = MAP;
        diagnosticFactoryToRendererMap6.put(Errors.INAPPLICABLE_OPERATOR_MODIFIER, "''operator'' modifier is inapplicable on this function: {0}", contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.INAPPLICABLE_INFIX_MODIFIER, "''infix'' modifier is inapplicable on this function: {0}", contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.OPERATOR_MODIFIER_REQUIRED, "''operator'' modifier is required on ''{0}'' in ''{1}''", contextIndependentParameterRenderer21, contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.INFIX_MODIFIER_REQUIRED, "''infix'' modifier is required on ''{0}'' in ''{1}''", contextIndependentParameterRenderer21, contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.PROPERTY_AS_OPERATOR, "Properties cannot be used in operator conventions: ''{0}'' in ''{1}''", contextIndependentParameterRenderer21, contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.INAPPLICABLE_MODIFIER, "''{0}'' modifier is inapplicable. The reason is that {1}", contextIndependentParameterRenderer23, contextIndependentParameterRenderer25);
        DiagnosticFactory1<PsiElement, CallableDescriptor> diagnosticFactory31 = Errors.DSL_SCOPE_VIOLATION;
        SmartDescriptorRenderer smartDescriptorRenderer7 = Renderers.COMPACT;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory31, "''{0}'' can''t be called in this context by implicit receiver. Use the explicit one if necessary", smartDescriptorRenderer7);
        diagnosticFactoryToRendererMap6.put(Errors.DSL_SCOPE_VIOLATION_WARNING, "''{0}'' shouldn't be called in this context by implicit receiver, it will become an error soon. Use the explicit one if necessary", smartDescriptorRenderer7);
        diagnosticFactoryToRendererMap6.put(Errors.NULLABLE_EXTENSION_OPERATOR_WITH_SAFE_CALL_RECEIVER, "Semantics of such combination of safe call and operator will change in next compiler version. Namely, the right part of the safe call will not be evaluated if receiver is null");
        diagnosticFactoryToRendererMap6.put(Errors.RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY, "Returns are not allowed for functions with expression body. Use block body in '{...}'");
        diagnosticFactoryToRendererMap6.put(Errors.NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY, "A 'return' expression required in a function with a block body ('{...}')");
        diagnosticFactoryToRendererMap6.put(Errors.NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY_MIGRATION, "A 'return' expression required in a function with a block body ('{...}'). If you got this error after the compiler update, then it's most likely due to a fix of a bug introduced in 1.3.0 (see KT-28061 for details)");
        diagnosticFactoryToRendererMap6.put(Errors.RETURN_TYPE_MISMATCH, "This function must return a value of type {0}", smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.EXPECTED_TYPE_MISMATCH, "Expected a value of type {0}", smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.ASSIGNMENT_TYPE_MISMATCH, "Expected a value of type {0}. Assignment operation is not an expression, so it does not return any value", smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.EXPECTED_PARAMETER_TYPE_MISMATCH, "Expected parameter of type {0}", smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.EXPECTED_PARAMETER_TYPE_MISMATCH_WARNING, "Expected parameter of type {0}", smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.EXPECTED_PARAMETERS_NUMBER_MISMATCH, "Expected {0,choice,0#no parameters|1#one parameter of type|1<{0,number,integer} parameters of types} {1}", (DiagnosticParameterRenderer) null, Renderers.RENDER_COLLECTION_OF_TYPES);
        diagnosticFactoryToRendererMap6.put(Errors.IMPLICIT_CAST_TO_ANY, "Conditional branch result of type {0} is implicitly cast to {1}", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.EXPRESSION_EXPECTED, "{0} is not an expression, and only expressions are allowed here", new DiagnosticParameterRenderer() { // from class: hg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.h((KtExpression) obj, renderingContext);
            }
        });
        diagnosticFactoryToRendererMap6.put(Errors.UPPER_BOUND_VIOLATED, "Type argument is not within its bounds: should be subtype of ''{0}''", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.UPPER_BOUND_VIOLATED_WARNING, "Type argument is not within its bounds: ''{1}'' should be subtype of ''{0}''. This warning will become an error in K2", smartTypeRenderer3, smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.FINAL_UPPER_BOUND, "''{0}'' is a final type, and thus a value of the type parameter is predetermined", smartTypeRenderer3);
        diagnosticFactoryToRendererMap6.put(Errors.UPPER_BOUND_IS_EXTENSION_FUNCTION_TYPE, "Extension function type can not be used as an upper bound");
        diagnosticFactoryToRendererMap6.put(Errors.ONLY_ONE_CLASS_BOUND_ALLOWED, "Only one of the upper bounds can be a class");
        diagnosticFactoryToRendererMap6.put(Errors.BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER, "Type parameter cannot have any other bounds if it's bounded by another type parameter");
        diagnosticFactoryToRendererMap6.put(Errors.REPEATED_BOUND, "Type parameter already has this bound");
        diagnosticFactoryToRendererMap6.put(Errors.DYNAMIC_UPPER_BOUND, "Dynamic type can not be used as an upper bound");
        DiagnosticFactory1<KtBinaryExpression, KotlinType> diagnosticFactory32 = Errors.USELESS_ELVIS;
        SmartTypeRenderer smartTypeRenderer4 = Renderers.RENDER_TYPE;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory32, "Elvis operator (?:) always returns the left operand of non-nullable type {0}", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.USELESS_ELVIS_RIGHT_IS_NULL, "Right operand of elvis operator (?:) is useless if it is null");
        DiagnosticFactory1<KtNamedDeclaration, TypeParameterDescriptor> diagnosticFactory33 = Errors.CONFLICTING_UPPER_BOUNDS;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer26 = Renderers.NAME;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory33, "Upper bounds of {0} have empty intersection", contextIndependentParameterRenderer26);
        diagnosticFactoryToRendererMap6.put(Errors.TOPLEVEL_TYPEALIASES_ONLY, "Nested and local type aliases are not supported");
        diagnosticFactoryToRendererMap6.put(Errors.RECURSIVE_TYPEALIAS_EXPANSION, "Recursive type alias in expansion: {0}", contextIndependentParameterRenderer26);
        diagnosticFactoryToRendererMap6.put(Errors.UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION, "Type argument resulting from type alias expansion is not within required bounds for ''{2}'': should be subtype of ''{0}'', substituted type is ''{1}''", smartTypeRenderer4, smartTypeRenderer4, contextIndependentParameterRenderer26);
        diagnosticFactoryToRendererMap6.put(Errors.UPPER_BOUND_VIOLATED_IN_TYPEALIAS_EXPANSION_WARNING, "Type argument resulting from type alias expansion is not within required bounds for ''{2}'': should be subtype of ''{0}'', substituted type is ''{1}''. This warning will become an error since 1.8. See https://youtrack.jetbrains.com/issue/KT-29168", smartTypeRenderer4, smartTypeRenderer4, contextIndependentParameterRenderer26);
        diagnosticFactoryToRendererMap6.put(Errors.CONFLICTING_PROJECTION_IN_TYPEALIAS_EXPANSION, "Conflicting projection in type alias expansion in intermediate type ''{0}''", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.TYPEALIAS_SHOULD_EXPAND_TO_CLASS, "Type alias expands to {0}, which is not a class, an interface, or an object", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.TYPEALIAS_EXPANDED_TO_MALFORMED_TYPE, "Type alias expanded to malformed type {0}: {1}", smartTypeRenderer4, contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.UNUSED_TYPEALIAS_PARAMETER, "Type alias parameter {0} is not used in the expanded type {1} and does not affect type checking", contextIndependentParameterRenderer26, smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.EXPANDED_TYPE_CANNOT_BE_CONSTRUCTED, "Expanded type {0} contains non-invariant projections in top-level arguments and cannot be constructed", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.EXPANDED_TYPE_CANNOT_BE_INHERITED, "Expanded type {0} contains non-invariant projections in top-level arguments and cannot be inherited from", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.DEPRECATED_SYNTAX_WITH_DEFINITELY_NOT_NULL, "Applying '!!' to the whole as/is expression without parentheses is deprecated. Please, put parentheses explicitly");
        diagnosticFactoryToRendererMap6.put(Errors.MODIFIER_LIST_NOT_ALLOWED, "Modifiers and annotations are not allowed here, because there are other modifiers or annotations outside of parenthesis");
        diagnosticFactoryToRendererMap6.put(Errors.MULTIPLE_CONTEXT_LISTS, "Multiple context receiver lists.");
        diagnosticFactoryToRendererMap6.put(Errors.PROGRESSIONS_CHANGING_RESOLVE, "Progressions and ranges types will start implementing Collection interface soon. This call will resolve to another declaration: {0}. See https://youtrack.jetbrains.com/issue/KT-49276 for more details. Please specify a progression type of argument explicitly through explicit cast to resolve to a proper declaration", smartDescriptorRenderer7);
        diagnosticFactoryToRendererMap6.put(Errors.TOO_MANY_ARGUMENTS, "Too many arguments for {0}", Renderers.FQ_NAMES_IN_TYPES);
        diagnosticFactoryToRendererMap6.put(Errors.CONSTANT_EXPECTED_TYPE_MISMATCH, "The {0} literal does not conform to the expected type {1}", contextIndependentParameterRenderer25, smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.DIVISION_BY_ZERO, "Division by zero");
        diagnosticFactoryToRendererMap6.put(Errors.INTEGER_OVERFLOW, "This operation has led to an overflow");
        diagnosticFactoryToRendererMap6.put(Errors.INT_LITERAL_OUT_OF_RANGE, "The value is out of range");
        diagnosticFactoryToRendererMap6.put(Errors.INT_LITERAL_WITH_LEADING_ZEROS, "Leading zeros are not allowed in integer literals.");
        diagnosticFactoryToRendererMap6.put(Errors.WRONG_LONG_SUFFIX, "Use 'L' instead of 'l'");
        diagnosticFactoryToRendererMap6.put(Errors.FLOAT_LITERAL_OUT_OF_RANGE, "The value is out of range");
        diagnosticFactoryToRendererMap6.put(Errors.FLOAT_LITERAL_CONFORMS_INFINITY, "Floating point number conforms to infinity");
        diagnosticFactoryToRendererMap6.put(Errors.FLOAT_LITERAL_CONFORMS_ZERO, "Floating point number conforms to zero");
        diagnosticFactoryToRendererMap6.put(Errors.INCORRECT_CHARACTER_LITERAL, "Incorrect character literal");
        diagnosticFactoryToRendererMap6.put(Errors.EMPTY_CHARACTER_LITERAL, "Empty character literal");
        diagnosticFactoryToRendererMap6.put(Errors.ILLEGAL_UNDERSCORE, "Illegal underscore");
        DiagnosticFactory1<KtConstantExpression, KtConstantExpression> diagnosticFactory34 = Errors.TOO_MANY_CHARACTERS_IN_CHARACTER_LITERAL;
        ContextIndependentParameterRenderer<PsiElement> contextIndependentParameterRenderer27 = Renderers.ELEMENT_TEXT;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory34, "Too many characters in a character literal ''{0}''", contextIndependentParameterRenderer27);
        diagnosticFactoryToRendererMap6.put(Errors.ILLEGAL_ESCAPE, "Illegal escape: ''{0}''", contextIndependentParameterRenderer27);
        diagnosticFactoryToRendererMap6.put(Errors.NULL_FOR_NONNULL_TYPE, "Null can not be a value of a non-null type {0}", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.NULL_FOR_NONNULL_TYPE_WARNING, "Null can not be a value of a non-null type {0}", smartTypeRenderer4);
        diagnosticFactoryToRendererMap6.put(Errors.ELSE_MISPLACED_IN_WHEN, "'else' entry must be the last one in a when-expression");
        diagnosticFactoryToRendererMap6.put(Errors.REDUNDANT_ELSE_IN_WHEN, "'when' is exhaustive so 'else' is redundant here");
        diagnosticFactoryToRendererMap6.put(Errors.COMMA_IN_WHEN_CONDITION_WITHOUT_ARGUMENT, "Deprecated syntax. Use '||' instead of commas in when-condition for 'when' without argument");
        diagnosticFactoryToRendererMap6.put(Errors.DUPLICATE_LABEL_IN_WHEN, "Duplicate label in when");
        diagnosticFactoryToRendererMap6.put(Errors.ILLEGAL_DECLARATION_IN_WHEN_SUBJECT, "Illegal variable declaration in ''when'' subject: {0}. Should be a simple val with an initializer", contextIndependentParameterRenderer25);
        DiagnosticFactory1<KtWhenExpression, List<WhenMissingCase>> diagnosticFactory35 = Errors.NO_ELSE_IN_WHEN;
        ContextIndependentParameterRenderer<List<? extends WhenMissingCase>> contextIndependentParameterRenderer28 = Renderers.RENDER_WHEN_MISSING_CASES;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory35, "''when'' expression must be exhaustive, add necessary {0}", contextIndependentParameterRenderer28);
        diagnosticFactoryToRendererMap6.put(Errors.NON_EXHAUSTIVE_WHEN, "''when'' expression on enum is recommended to be exhaustive, add {0}", contextIndependentParameterRenderer28);
        diagnosticFactoryToRendererMap6.put(Errors.NO_ELSE_IN_WHEN_WARNING, "''when'' expression must be exhaustive, add necessary {0}. See https://youtrack.jetbrains.com/issue/KT-44705 for details", contextIndependentParameterRenderer28);
        diagnosticFactoryToRendererMap6.put(Errors.NON_EXHAUSTIVE_WHEN_STATEMENT, "Non exhaustive ''when'' statements on {0} will be prohibited in 1.7, add {1}", contextIndependentParameterRenderer25, contextIndependentParameterRenderer28);
        diagnosticFactoryToRendererMap6.put(Errors.NON_EXHAUSTIVE_WHEN_ON_SEALED_CLASS, "''when'' expression on sealed classes is recommended to be exhaustive, add {0}", contextIndependentParameterRenderer28);
        diagnosticFactoryToRendererMap6.put(Errors.EXPECT_TYPE_IN_WHEN_WITHOUT_ELSE, "''when'' with expect {0} as subject can not be exhaustive without else branch", contextIndependentParameterRenderer25);
        diagnosticFactoryToRendererMap6.put(Errors.TYPE_MISMATCH_IN_RANGE, "Type mismatch: incompatible types of range and element checked in it");
        diagnosticFactoryToRendererMap6.put(Errors.CYCLIC_INHERITANCE_HIERARCHY, "There's a cycle in the inheritance hierarchy for this type");
        diagnosticFactoryToRendererMap6.put(Errors.CYCLIC_GENERIC_UPPER_BOUND, "Type parameter has cyclic upper bounds");
        diagnosticFactoryToRendererMap6.put(Errors.CYCLIC_SCOPES_WITH_COMPANION, "There's a cycle in scopes for that type. Most probably, there's a companion object that inherits some nested class (see KT-21515).\nSuch code is currently unstable, and its behavior may change in future releases");
        diagnosticFactoryToRendererMap6.put(Errors.MANY_CLASSES_IN_SUPERTYPE_LIST, "Only one class may appear in a supertype list");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_NOT_A_CLASS_OR_INTERFACE, "Only classes and interfaces may serve as supertypes");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_IS_EXTENSION_FUNCTION_TYPE, "Extension function type is not allowed as supertypes");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_IS_SUSPEND_EXTENSION_FUNCTION_TYPE, "Suspend extension function type is not allowed as supertypes. Such code will become error in future releases.");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_INITIALIZED_IN_INTERFACE, "Interfaces cannot initialize supertypes");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_IS_SUSPEND_FUNCTION_TYPE, "Suspend function type is allowed as a supertype only since version 1.6");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_IS_KSUSPEND_FUNCTION_TYPE, "KSuspendFunctionN interfaces are not allowed as supertypes");
        diagnosticFactoryToRendererMap6.put(Errors.MIXING_SUSPEND_AND_NON_SUSPEND_SUPERTYPES, "Mixing suspend and non-suspend supertypes is not allowed");
        diagnosticFactoryToRendererMap6.put(Errors.CLASS_IN_SUPERTYPE_FOR_ENUM, "Enum class cannot inherit from classes");
        diagnosticFactoryToRendererMap6.put(Errors.CONSTRUCTOR_IN_INTERFACE, "An interface may not have a constructor");
        diagnosticFactoryToRendererMap6.put(Errors.METHOD_OF_ANY_IMPLEMENTED_IN_INTERFACE, "An interface may not implement a method of 'Any'");
        diagnosticFactoryToRendererMap6.put(Errors.INTERFACE_WITH_SUPERCLASS, "An interface cannot inherit from a class");
        diagnosticFactoryToRendererMap6.put(Errors.SUPERTYPE_APPEARS_TWICE, "A supertype appears twice");
        diagnosticFactoryToRendererMap6.put(Errors.FINAL_SUPERTYPE, "This type is final, so it cannot be inherited from");
        diagnosticFactoryToRendererMap6.put(Errors.DATA_CLASS_CANNOT_HAVE_CLASS_SUPERTYPES, "Data class inheritance from other classes is forbidden");
        diagnosticFactoryToRendererMap6.put(Errors.SEALED_SUPERTYPE, "This type is sealed, so it can be inherited by only its own nested classes or objects");
        DiagnosticFactory2<KtTypeReference, String, ClassKind> diagnosticFactory36 = Errors.SEALED_SUPERTYPE_IN_LOCAL_CLASS;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer29 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory36, "{0} cannot extend a sealed {1}", contextIndependentParameterRenderer29, CommonRenderers.CLASS_KIND);
        DiagnosticFactory2<KtTypeReference, FqName, FqName> diagnosticFactory37 = Errors.SEALED_INHERITOR_IN_DIFFERENT_PACKAGE;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer30 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap6.put(diagnosticFactory37, "Inheritor of sealed class or interface declared in package {0} but it must be in package {1} where base class is declared", contextIndependentParameterRenderer30, contextIndependentParameterRenderer30);
        diagnosticFactoryToRendererMap6.put(Errors.SEALED_INHERITOR_IN_DIFFERENT_MODULE, "Inheritance of sealed classes or interfaces from different module is prohibited");
        diagnosticFactoryToRendererMap6.put(Errors.CLASS_INHERITS_JAVA_SEALED_CLASS, "Inheritance of java sealed classes is prohibited");
        diagnosticFactoryToRendererMap6.put(Errors.SINGLETON_IN_SUPERTYPE, "Cannot inherit from a singleton");
        diagnosticFactoryToRendererMap6.put(Errors.CLASS_CANNOT_BE_EXTENDED_DIRECTLY, "Class {0} cannot be extended directly", contextIndependentParameterRenderer26);
        diagnosticFactoryToRendererMap6.put(Errors.CYCLIC_CONSTRUCTOR_DELEGATION_CALL, "There's a cycle in the delegation calls chain");
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap7 = MAP;
        diagnosticFactoryToRendererMap7.put(Errors.CONSTRUCTOR_IN_OBJECT, "Constructors are not allowed for objects");
        diagnosticFactoryToRendererMap7.put(Errors.SUPERTYPE_INITIALIZED_WITHOUT_PRIMARY_CONSTRUCTOR, "Supertype initialization is impossible without primary constructor");
        diagnosticFactoryToRendererMap7.put(Errors.PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED, "Primary constructor call expected");
        diagnosticFactoryToRendererMap7.put(Errors.PRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED_IN_ENUM, "Primary constructor call expected. It's going to be an error in 1.5.");
        diagnosticFactoryToRendererMap7.put(Errors.DELEGATION_SUPER_CALL_IN_ENUM_CONSTRUCTOR, "Call to super is not allowed in enum constructor");
        diagnosticFactoryToRendererMap7.put(Errors.PRIMARY_CONSTRUCTOR_REQUIRED_FOR_DATA_CLASS, "Primary constructor required for data class");
        diagnosticFactoryToRendererMap7.put(Errors.EXPLICIT_DELEGATION_CALL_REQUIRED, "Explicit 'this' or 'super' call is required. There is no constructor in superclass that can be called without arguments");
        diagnosticFactoryToRendererMap7.put(Errors.INSTANCE_ACCESS_BEFORE_SUPER_CALL, "Cannot access ''{0}'' before the instance has been initialized", contextIndependentParameterRenderer26);
        diagnosticFactoryToRendererMap7.put(Errors.ILLEGAL_SELECTOR, "The expression cannot be a selector (occur after a dot)");
        diagnosticFactoryToRendererMap7.put(Errors.NO_TAIL_CALLS_FOUND, "A function is marked as tail-recursive but no tail calls are found");
        diagnosticFactoryToRendererMap7.put(Errors.TAILREC_ON_VIRTUAL_MEMBER, "Tailrec is not allowed on open members");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_PARAMETER_WITH_NO_TYPE_ANNOTATION, "A type annotation is required on a value parameter");
        diagnosticFactoryToRendererMap7.put(Errors.BREAK_OR_CONTINUE_OUTSIDE_A_LOOP, "'break' and 'continue' are only allowed inside a loop");
        diagnosticFactoryToRendererMap7.put(Errors.BREAK_OR_CONTINUE_IN_WHEN, "'break' and 'continue' are not allowed in 'when' statements. Consider using labels to continue/break from the outer loop");
        diagnosticFactoryToRendererMap7.put(Errors.BREAK_OR_CONTINUE_JUMPS_ACROSS_FUNCTION_BOUNDARY, "'break' or 'continue' jumps across a function or a class boundary");
        diagnosticFactoryToRendererMap7.put(Errors.NOT_A_LOOP_LABEL, "The label ''{0}'' does not denote a loop", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.NOT_A_FUNCTION_LABEL, "Target label does not denote a function");
        diagnosticFactoryToRendererMap7.put(Errors.NOT_A_FUNCTION_LABEL_WARNING, "Target label does not denote a function");
        diagnosticFactoryToRendererMap7.put(Errors.REDUNDANT_LABEL_WARNING, "Label is redundant, because it can not be referenced in either 'break', 'continue', or 'return' expression");
        diagnosticFactoryToRendererMap7.put(Errors.ANONYMOUS_INITIALIZER_IN_INTERFACE, "Anonymous initializers are not allowed in interfaces");
        diagnosticFactoryToRendererMap7.put(Errors.NULLABLE_SUPERTYPE, "A supertype cannot be nullable");
        diagnosticFactoryToRendererMap7.put(Errors.DYNAMIC_SUPERTYPE, "A supertype cannot be dynamic");
        diagnosticFactoryToRendererMap7.put(Errors.REDUNDANT_NULLABLE, "Redundant '?'");
        diagnosticFactoryToRendererMap7.put(Errors.NULLABLE_ON_DEFINITELY_NOT_NULLABLE, "'!!' type cannot be marked as nullable");
        diagnosticFactoryToRendererMap7.put(Errors.INCORRECT_LEFT_COMPONENT_OF_INTERSECTION, "Intersection types are only supported for definitely non-nullable types: left part should be a type parameter with nullable bounds");
        diagnosticFactoryToRendererMap7.put(Errors.INCORRECT_RIGHT_COMPONENT_OF_INTERSECTION, "Intersection types are only supported for definitely non-nullable types: right part should be non-nullable Any");
        diagnosticFactoryToRendererMap7.put(Errors.UNSAFE_CALL, "Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type {0}", smartTypeRenderer4);
        diagnosticFactoryToRendererMap7.put(Errors.UNSAFE_IMPLICIT_INVOKE_CALL, "Reference has a nullable type ''{0}'', use explicit ''?.invoke()'' to make a function-like call instead", smartTypeRenderer4);
        diagnosticFactoryToRendererMap7.put(Errors.AMBIGUOUS_LABEL, "Ambiguous label");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED, "Unsupported [{0}]", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_WARNING, "Unsupported [{0}]. This warning will be an error in future releases", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.NEW_INFERENCE_ERROR, "New inference error [{0}]", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.NEW_INFERENCE_DIAGNOSTIC, "New inference [{0}]", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.NEW_INFERENCE_UNKNOWN_ERROR, "Unknown error in new inference with applicability ''{0}'' and target ''{1}'', please report to https://youtrack.jetbrains.com/newIssue?project=KT", contextIndependentParameterRenderer30, contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.NON_APPLICABLE_CALL_FOR_BUILDER_INFERENCE, "Non-applicable call for builder inference");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_FEATURE, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, new LanguageFeatureMessageRenderer(LanguageFeatureMessageRenderer.Type.UNSUPPORTED));
        diagnosticFactoryToRendererMap7.put(Errors.EXPERIMENTAL_FEATURE_WARNING, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, new LanguageFeatureMessageRenderer(LanguageFeatureMessageRenderer.Type.WARNING));
        diagnosticFactoryToRendererMap7.put(Errors.EXPLICIT_BACKING_FIELDS_UNSUPPORTED, "Explicit backing field declarations are not supported in FE 1.0");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_INHERITANCE_FROM_JAVA_MEMBER_REFERENCING_KOTLIN_FUNCTION, "Inheritance of a Java member referencing 'kotlin.jvm.functions.FunctionN': {0} is unsupported", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_SEALED_WHEN, "'sealed' in front of 'when' is reserved");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_SEALED_FUN_INTERFACE, "'sealed fun interface' is unsupported");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_SUSPEND_TEST, "'suspend' functions annotated with @kotlin.test.Test are unsupported");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_REFERENCES_TO_VARIABLES_AND_PARAMETERS, "References to variables and parameters are unsupported");
        diagnosticFactoryToRendererMap7.put(Errors.UNSUPPORTED_CLASS_LITERALS_WITH_EMPTY_LHS, "Class literals with empty left hand side are unsupported");
        diagnosticFactoryToRendererMap7.put(Errors.EXCEPTION_FROM_ANALYZER, "Internal Error occurred while analyzing this expression:\n{0}", CommonRenderers.THROWABLE);
        diagnosticFactoryToRendererMap7.put(Errors.MISSING_STDLIB, "{0}. Ensure you have the standard Kotlin library in dependencies", contextIndependentParameterRenderer29);
        DiagnosticFactory1<PsiElement, KotlinType> diagnosticFactory38 = Errors.UNNECESSARY_SAFE_CALL;
        SmartTypeRenderer smartTypeRenderer5 = Renderers.RENDER_TYPE;
        diagnosticFactoryToRendererMap7.put(diagnosticFactory38, "Unnecessary safe call on a non-null receiver of type {0}", smartTypeRenderer5);
        diagnosticFactoryToRendererMap7.put(Errors.SAFE_CALL_WILL_CHANGE_NULLABILITY, "Safe call on a non-null receiver will have nullable type in future releases\n  Right now safe call on non nullable receiver has not null type: `\"hello\"?.length` has type Int\n  In future releases all safe calls will have nullable type: `\"hello\"?.length` will have type Int?");
        diagnosticFactoryToRendererMap7.put(Errors.UNEXPECTED_SAFE_CALL, "Safe-call is not allowed here");
        diagnosticFactoryToRendererMap7.put(Errors.UNNECESSARY_NOT_NULL_ASSERTION, "Unnecessary non-null assertion (!!) on a non-null receiver of type {0}", smartTypeRenderer5);
        diagnosticFactoryToRendererMap7.put(Errors.NOT_NULL_ASSERTION_ON_LAMBDA_EXPRESSION, "Non-null assertion (!!) is called on a lambda expression");
        diagnosticFactoryToRendererMap7.put(Errors.NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE, "Non-null assertion (!!) is called on a callable reference expression");
        diagnosticFactoryToRendererMap7.put(Errors.NAME_IN_CONSTRAINT_IS_NOT_A_TYPE_PARAMETER, "{0} does not refer to a type parameter of {1}", new DiagnosticParameterRenderer() { // from class: ig3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return ((KtTypeConstraint) obj).getSubjectTypeParameterName().getReferencedName();
            }
        }, Renderers.DECLARATION_NAME);
        diagnosticFactoryToRendererMap7.put(Errors.SMARTCAST_IMPOSSIBLE, "Smart cast to ''{0}'' is impossible, because ''{1}'' is a {2}", smartTypeRenderer5, contextIndependentParameterRenderer29, contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.DEPRECATED_SMARTCAST, "Smart cast to ''{0}'' is deprecated, because ''{1}'' is a {2}", smartTypeRenderer5, contextIndependentParameterRenderer29, contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.ALWAYS_NULL, "The result of the expression is always null");
        diagnosticFactoryToRendererMap7.put(Errors.MISSING_CONSTRUCTOR_KEYWORD, "Use 'constructor' keyword after modifiers of primary constructor");
        diagnosticFactoryToRendererMap7.put(Errors.MISSING_CONSTRUCTOR_BRACKETS, "Constructor requires brackets");
        diagnosticFactoryToRendererMap7.put(Errors.NON_PRIVATE_CONSTRUCTOR_IN_ENUM, "Constructor must be private in enum class");
        diagnosticFactoryToRendererMap7.put(Errors.NON_PRIVATE_CONSTRUCTOR_IN_SEALED, "Constructor must be private in sealed class");
        diagnosticFactoryToRendererMap7.put(Errors.NON_PRIVATE_OR_PROTECTED_CONSTRUCTOR_IN_SEALED, "Constructor must be private or protected in sealed class");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_NOT_TOP_LEVEL, "Value classes cannot be local or inner");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_NOT_FINAL, "Value classes can be only final");
        diagnosticFactoryToRendererMap7.put(Errors.ABSENCE_OF_PRIMARY_CONSTRUCTOR_FOR_VALUE_CLASS, "Primary constructor is required for value class");
        diagnosticFactoryToRendererMap7.put(Errors.INLINE_CLASS_CONSTRUCTOR_WRONG_PARAMETERS_SIZE, "Inline class must have exactly one primary constructor parameter");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_EMPTY_CONSTRUCTOR, "Value class must have at least one primary constructor parameter");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_CONSTRUCTOR_NOT_FINAL_READ_ONLY_PARAMETER, "Value class primary constructor must only have final read-only (val) property parameters");
        diagnosticFactoryToRendererMap7.put(Errors.PROPERTY_WITH_BACKING_FIELD_INSIDE_VALUE_CLASS, "Value class cannot have properties with backing fields");
        diagnosticFactoryToRendererMap7.put(Errors.DELEGATED_PROPERTY_INSIDE_VALUE_CLASS, "Value class cannot have delegated properties");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_HAS_INAPPLICABLE_PARAMETER_TYPE, "Value class cannot have value parameter of type ''{0}''", smartTypeRenderer5);
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_CANNOT_IMPLEMENT_INTERFACE_BY_DELEGATION, "Value class cannot implement an interface by delegation if expression is not a parameter");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_CANNOT_EXTEND_CLASSES, "Value class cannot extend classes");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_CANNOT_BE_RECURSIVE, "Value class cannot be recursive");
        diagnosticFactoryToRendererMap7.put(Errors.MULTI_FIELD_VALUE_CLASS_PRIMARY_CONSTRUCTOR_DEFAULT_PARAMETER, "Default parameters are not supported in the primary constructor of a multi-field value class");
        diagnosticFactoryToRendererMap7.put(Errors.RESERVED_MEMBER_INSIDE_VALUE_CLASS, "Member with the name ''{0}'' is reserved for future releases", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.TYPE_ARGUMENT_ON_TYPED_VALUE_CLASS_EQUALS, "Type arguments for typed value class equals must be only star projections");
        diagnosticFactoryToRendererMap7.put(Errors.INNER_CLASS_INSIDE_VALUE_CLASS, "Value class cannot have inner classes");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_CANNOT_BE_CLONEABLE, "Value class cannot be Cloneable");
        diagnosticFactoryToRendererMap7.put(Errors.INLINE_CLASS_DEPRECATED, "'inline' modifier is deprecated. Use 'value' instead");
        diagnosticFactoryToRendererMap7.put(Errors.VALUE_CLASS_CANNOT_HAVE_CONTEXT_RECEIVERS, "Value classes cannot have context receivers");
        diagnosticFactoryToRendererMap7.put(Errors.INEFFICIENT_EQUALS_OVERRIDING_IN_VALUE_CLASS, "Overriding ''equals'' from ''Any'' in value class without operator ''equals(other: {0}): Boolean'' leads to boxing on every equality comparison", smartTypeRenderer5);
        diagnosticFactoryToRendererMap7.put(Errors.ANNOTATION_ON_ILLEGAL_MULTI_FIELD_VALUE_CLASS_TYPED_TARGET, "Annotations on {0} of multi-field value class type are not supported", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.RESULT_CLASS_IN_RETURN_TYPE, "'kotlin.Result' cannot be used as a return type");
        diagnosticFactoryToRendererMap7.put(Errors.RESULT_CLASS_WITH_NULLABLE_OPERATOR, "Expression of type ''kotlin.Result'' cannot be used as a left operand of ''{0}''", contextIndependentParameterRenderer29);
        diagnosticFactoryToRendererMap7.put(Errors.FUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS, "Fun interfaces must have exactly one abstract method");
        diagnosticFactoryToRendererMap7.put(Errors.FUN_INTERFACE_CANNOT_HAVE_ABSTRACT_PROPERTIES, "Fun interfaces cannot have abstract properties");
        diagnosticFactoryToRendererMap7.put(Errors.FUN_INTERFACE_ABSTRACT_METHOD_WITH_TYPE_PARAMETERS, "Single abstract member cannot declare type parameters");
        diagnosticFactoryToRendererMap7.put(Errors.FUN_INTERFACE_ABSTRACT_METHOD_WITH_DEFAULT_VALUE, "Single abstract member cannot declare default values");
        diagnosticFactoryToRendererMap7.put(Errors.FUN_INTERFACE_CONSTRUCTOR_REFERENCE, "Functional interface constructor references are prohibited");
        diagnosticFactoryToRendererMap7.put(Errors.FUN_INTERFACE_WITH_SUSPEND_FUNCTION, "'suspend' modifier is not allowed on a single abstract member");
        diagnosticFactoryToRendererMap7.put(Errors.VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED, "Variance annotations are only allowed for type parameters of classes and interfaces");
        diagnosticFactoryToRendererMap7.put(Errors.BOUND_ON_TYPE_ALIAS_PARAMETER_NOT_ALLOWED, "Bounds are not allowed on type alias parameters");
        diagnosticFactoryToRendererMap7.put(Errors.DEPRECATED_TYPE_PARAMETER_SYNTAX, "Type parameters must be placed before the name of the function");
        diagnosticFactoryToRendererMap7.put(Errors.MISPLACED_TYPE_PARAMETER_CONSTRAINTS, "If a type parameter has multiple constraints, they all need to be placed in the 'where' clause");
        MultiRenderer multiRenderer = new MultiRenderer() { // from class: jg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.MultiRenderer
            public final String[] render(Object obj) {
                return DefaultErrorMessages.f((VarianceConflictDiagnosticData) obj);
            }
        };
        diagnosticFactoryToRendererMap7.put(Errors.TYPE_VARIANCE_CONFLICT, "Type parameter {0} is declared as ''{1}'' but occurs in ''{2}'' position in type {3}", multiRenderer);
        diagnosticFactoryToRendererMap7.put(Errors.TYPE_VARIANCE_CONFLICT_IN_EXPANDED_TYPE, "Type parameter {0} is declared as ''{1}'' but occurs in ''{2}'' position in abbreviated type {3}", multiRenderer);
        diagnosticFactoryToRendererMap7.put(Errors.FINITE_BOUNDS_VIOLATION, "This type parameter violates the Finite Bound Restriction");
        DiagnosticFactory1<PsiElement, String> diagnosticFactory39 = Errors.FINITE_BOUNDS_VIOLATION_IN_JAVA;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer31 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap7.put(diagnosticFactory39, "Violation of Finite Bound Restriction for {0}", contextIndependentParameterRenderer31);
        diagnosticFactoryToRendererMap7.put(Errors.EXPANSIVE_INHERITANCE, "This type parameter violates the Non-Expansive Inheritance Restriction");
        diagnosticFactoryToRendererMap7.put(Errors.EXPANSIVE_INHERITANCE_IN_JAVA, "Violation of Non-Expansive Inheritance Restriction for {0}", contextIndependentParameterRenderer31);
        DiagnosticFactory1<KtTypeProjection, ClassifierDescriptor> diagnosticFactory40 = Errors.REDUNDANT_PROJECTION;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer32 = Renderers.NAME;
        diagnosticFactoryToRendererMap7.put(diagnosticFactory40, "Projection is redundant: the corresponding type parameter of {0} has the same variance", contextIndependentParameterRenderer32);
        diagnosticFactoryToRendererMap7.put(Errors.CONFLICTING_PROJECTION, "Projection is conflicting with variance of the corresponding type parameter of {0}. Remove the projection or replace it with ''*''", contextIndependentParameterRenderer32);
        diagnosticFactoryToRendererMap7.put(Errors.TYPE_ARGUMENTS_FOR_OUTER_CLASS_WHEN_NESTED_REFERENCED, "Type arguments for outer class are redundant when nested class is referenced");
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap8 = MAP;
        diagnosticFactoryToRendererMap8.put(Errors.REIFIED_TYPE_IN_CATCH_CLAUSE, "Reified type is forbidden for catch parameter");
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_PARAMETER_IN_CATCH_CLAUSE, "Type parameter is forbidden for catch parameter");
        diagnosticFactoryToRendererMap8.put(Errors.GENERIC_THROWABLE_SUBCLASS, "Subclass of 'Throwable' may not have type parameters");
        diagnosticFactoryToRendererMap8.put(Errors.INNER_CLASS_OF_GENERIC_THROWABLE_SUBCLASS, "Inner class of generic class extending 'Throwable' is prohibited");
        diagnosticFactoryToRendererMap8.put(Errors.INNER_CLASS_OF_GENERIC_THROWABLE_SUBCLASS_WARNING, "Inner class of generic class extending 'Throwable' is deprecated");
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_MISMATCH_IN_FOR_LOOP, "The loop iterates over values of type {0} but the parameter is declared to be {1}", smartTypeRenderer5, smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.INCOMPATIBLE_TYPES, "Incompatible types: {0} and {1}", smartTypeRenderer5, smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.IMPLICIT_NOTHING_RETURN_TYPE, "'Nothing' return type needs to be specified explicitly");
        diagnosticFactoryToRendererMap8.put(Errors.IMPLICIT_NOTHING_PROPERTY_TYPE, "'Nothing' property type needs to be specified explicitly");
        diagnosticFactoryToRendererMap8.put(Errors.ABBREVIATED_NOTHING_RETURN_TYPE, "'Nothing' return type can't be specified with type alias");
        diagnosticFactoryToRendererMap8.put(Errors.ABBREVIATED_NOTHING_PROPERTY_TYPE, "'Nothing' property type can't be specified with type alias");
        diagnosticFactoryToRendererMap8.put(Errors.IMPLICIT_INTERSECTION_TYPE, "Inferred type {0} is an intersection, please specify the required type explicitly", smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.EXPECTED_CONDITION, "Expected condition of type Boolean");
        diagnosticFactoryToRendererMap8.put(Errors.DYNAMIC_RECEIVER_NOT_ALLOWED, "Dynamic receiver is prohibited");
        diagnosticFactoryToRendererMap8.put(Errors.CANNOT_CHECK_FOR_ERASED, "Cannot check for instance of erased type: {0}", smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.UNCHECKED_CAST, "Unchecked cast: {0} to {1}", smartTypeRenderer5, smartTypeRenderer5);
        DiagnosticFactory3<KtSuperTypeList, TypeParameterDescriptor, ClassDescriptor, Collection<KotlinType>> diagnosticFactory41 = Errors.INCONSISTENT_TYPE_PARAMETER_VALUES;
        DiagnosticParameterRenderer<Collection<? extends KotlinType>> diagnosticParameterRenderer2 = Renderers.RENDER_COLLECTION_OF_TYPES;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory41, "Type parameter {0} of ''{1}'' has inconsistent values: {2}", contextIndependentParameterRenderer32, contextIndependentParameterRenderer32, diagnosticParameterRenderer2);
        diagnosticFactoryToRendererMap8.put(Errors.INCONSISTENT_TYPE_PARAMETER_BOUNDS, "Type parameter {0} of ''{1}'' has inconsistent bounds: {2}", contextIndependentParameterRenderer32, contextIndependentParameterRenderer32, diagnosticParameterRenderer2);
        diagnosticFactoryToRendererMap8.put(Errors.EQUALITY_NOT_APPLICABLE, "Operator ''{0}'' cannot be applied to ''{1}'' and ''{2}''", new DiagnosticParameterRenderer() { // from class: kg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return ((KtSimpleNameExpression) obj).getReferencedName();
            }
        }, smartTypeRenderer5, smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.EQUALITY_NOT_APPLICABLE_WARNING, "Operator ''{0}'' cannot be applied to ''{1}'' and ''{2}''. It will became an error in future releases.", new DiagnosticParameterRenderer() { // from class: og3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return ((KtSimpleNameExpression) obj).getReferencedName();
            }
        }, smartTypeRenderer5, smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.INCOMPATIBLE_ENUM_COMPARISON, "Comparison of incompatible enums ''{0}'' and ''{1}'' is always unsuccessful", smartTypeRenderer5, smartTypeRenderer5);
        diagnosticFactoryToRendererMap8.put(Errors.INCOMPATIBLE_ENUM_COMPARISON_ERROR, "Comparison of incompatible enums ''{0}'' and ''{1}'' is always unsuccessful", smartTypeRenderer5, smartTypeRenderer5);
        DiagnosticFactory2<KtBinaryExpression, KtBinaryExpression, Boolean> diagnosticFactory42 = Errors.SENSELESS_COMPARISON;
        ContextIndependentParameterRenderer<PsiElement> contextIndependentParameterRenderer33 = Renderers.ELEMENT_TEXT;
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer34 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory42, "Condition ''{0}'' is always ''{1}''", contextIndependentParameterRenderer33, contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.SENSELESS_NULL_IN_WHEN, "Expression under 'when' is never equal to null");
        diagnosticFactoryToRendererMap8.put(Errors.INVALID_IF_AS_EXPRESSION, "'if' must have both main and 'else' branches if used as an expression");
        diagnosticFactoryToRendererMap8.put(Errors.CONFUSING_BRANCH_CONDITION, "The logical expressions may be understood ambiguously in when with subject branches. Please wrap it with parentheses");
        diagnosticFactoryToRendererMap8.put(Errors.INVALID_IF_AS_EXPRESSION_WARNING, "'if' must have both main and 'else' branches if used as an expression. See https://youtrack.jetbrains.com/issue/KT-44705 for details");
        diagnosticFactoryToRendererMap8.put(Errors.OVERRIDING_FINAL_MEMBER, "''{0}'' in ''{1}'' is final and cannot be overridden", contextIndependentParameterRenderer32, contextIndependentParameterRenderer32);
        DiagnosticFactory3<KtModifierListOwner, DescriptorVisibility, CallableMemberDescriptor, DeclarationDescriptor> diagnosticFactory43 = Errors.CANNOT_WEAKEN_ACCESS_PRIVILEGE;
        ContextIndependentParameterRenderer<DescriptorVisibility> contextIndependentParameterRenderer35 = Renderers.VISIBILITY;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory43, "Cannot weaken access privilege ''{0}'' for ''{1}'' in ''{2}''", contextIndependentParameterRenderer35, contextIndependentParameterRenderer32, contextIndependentParameterRenderer32);
        diagnosticFactoryToRendererMap8.put(Errors.CANNOT_CHANGE_ACCESS_PRIVILEGE, "Cannot change access privilege ''{0}'' for ''{1}'' in ''{2}''", contextIndependentParameterRenderer35, contextIndependentParameterRenderer32, contextIndependentParameterRenderer32);
        diagnosticFactoryToRendererMap8.put(Errors.RETURN_TYPE_MISMATCH_ON_OVERRIDE, "Return type of ''{0}'' is not a subtype of the return type of the overridden member ''{1}''", contextIndependentParameterRenderer32, Renderers.FQ_NAMES_IN_TYPES_ANNOTATIONS_WHITELIST);
        DiagnosticFactory2<KtClassOrObject, CallableMemberDescriptor, CallableMemberDescriptor> diagnosticFactory44 = Errors.RETURN_TYPE_MISMATCH_ON_INHERITANCE;
        SmartDescriptorRenderer smartDescriptorRenderer8 = Renderers.SHORT_NAMES_IN_TYPES;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory44, "''{0}'' clashes with ''{1}'': return types are incompatible", smartDescriptorRenderer8, smartDescriptorRenderer8);
        DiagnosticFactory2<KtNamedDeclaration, CallableMemberDescriptor, CallableMemberDescriptor> diagnosticFactory45 = Errors.PROPERTY_TYPE_MISMATCH_ON_OVERRIDE;
        SmartDescriptorRenderer smartDescriptorRenderer9 = Renderers.FQ_NAMES_IN_TYPES;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory45, "Type of ''{0}'' is not a subtype of the overridden property ''{1}''", contextIndependentParameterRenderer32, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.VAR_TYPE_MISMATCH_ON_OVERRIDE, "Type of ''{0}'' doesn''t match the type of the overridden var-property ''{1}''", contextIndependentParameterRenderer32, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.PROPERTY_TYPE_MISMATCH_ON_INHERITANCE, "''{0}'' clashes with ''{1}'': property types are incompatible", smartDescriptorRenderer8, smartDescriptorRenderer8);
        diagnosticFactoryToRendererMap8.put(Errors.VAR_TYPE_MISMATCH_ON_INHERITANCE, "''{0}'' clashes with ''{1}'': property types do not match", smartDescriptorRenderer8, smartDescriptorRenderer8);
        diagnosticFactoryToRendererMap8.put(Errors.OVERRIDING_FINAL_MEMBER_BY_DELEGATION, "''{0}'' implicitly overrides a final member ''{1}'' by delegation", smartDescriptorRenderer8, smartDescriptorRenderer8);
        diagnosticFactoryToRendererMap8.put(Errors.VAR_OVERRIDDEN_BY_VAL_BY_DELEGATION, "Val-property ''{0}'' implicitly overrides a var-property ''{1}'' by delegation", smartDescriptorRenderer8, smartDescriptorRenderer8);
        diagnosticFactoryToRendererMap8.put(Errors.RETURN_TYPE_MISMATCH_BY_DELEGATION, "Type of ''{0}'' is not a subtype of overridden by delegation ''{1}''", smartDescriptorRenderer8, smartDescriptorRenderer8);
        diagnosticFactoryToRendererMap8.put(Errors.PROPERTY_TYPE_MISMATCH_BY_DELEGATION, "Type of property ''{0}'' is not a subtype of overridden by delegation ''{1}''", smartDescriptorRenderer8, smartDescriptorRenderer8);
        diagnosticFactoryToRendererMap8.put(Errors.VAR_OVERRIDDEN_BY_VAL, "Var-property {0} cannot be overridden by val-property {1}", smartDescriptorRenderer9, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.CONFLICTING_INHERITED_MEMBERS, "{0} inherits conflicting members: {1}", contextIndependentParameterRenderer32, CommonRenderers.commaSeparated(smartDescriptorRenderer9));
        diagnosticFactoryToRendererMap8.put(Errors.CONFLICTING_INHERITED_MEMBERS_WARNING, "{0} inherits conflicting members: {1}; This warning will became error in future releases. See https://youtrack.jetbrains.com/issue/KT-51194", contextIndependentParameterRenderer32, CommonRenderers.commaSeparated(smartDescriptorRenderer9));
        DiagnosticFactory2<KtClassOrObject, KtClassOrObject, CallableMemberDescriptor> diagnosticFactory46 = Errors.ABSTRACT_MEMBER_NOT_IMPLEMENTED;
        ContextIndependentParameterRenderer<KtClassOrObject> contextIndependentParameterRenderer36 = Renderers.RENDER_CLASS_OR_OBJECT;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory46, "{0} is not abstract and does not implement abstract member {1}", contextIndependentParameterRenderer36, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED, "{0} is not abstract and does not implement abstract base class member {1}", contextIndependentParameterRenderer36, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED_WARNING, "Deprecated: {0} is not abstract and does not implement abstract base class member {1}. See https://youtrack.jetbrains.com/issue/KT-45508", contextIndependentParameterRenderer36, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.MANY_IMPL_MEMBER_NOT_IMPLEMENTED, "{0} must override {1} because it inherits many implementations of it", contextIndependentParameterRenderer36, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED, "{0} must override {1} because it inherits multiple interface methods of it", contextIndependentParameterRenderer36, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED_WARNING, "Deprecated: {0} must override {1} because it inherits multiple interface methods of it", contextIndependentParameterRenderer36, smartDescriptorRenderer9);
        diagnosticFactoryToRendererMap8.put(Errors.INVISIBLE_ABSTRACT_MEMBER_FROM_SUPER, "{0} inherits invisible abstract members: {1}", contextIndependentParameterRenderer32, CommonRenderers.commaSeparated(smartDescriptorRenderer9));
        diagnosticFactoryToRendererMap8.put(Errors.CONFLICTING_OVERLOADS, "Conflicting overloads: {0}", CommonRenderers.commaSeparated(smartDescriptorRenderer9));
        diagnosticFactoryToRendererMap8.put(Errors.FUNCTION_EXPECTED, "Expression ''{0}''{1} cannot be invoked as a function. The function ''" + OperatorNameConventions.INVOKE.asString() + "()'' is not found", contextIndependentParameterRenderer33, new DiagnosticParameterRenderer() { // from class: pg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.i((KotlinType) obj, renderingContext);
            }
        });
        diagnosticFactoryToRendererMap8.put(Errors.FUNCTION_CALL_EXPECTED, "Function invocation ''{0}({1})'' expected", contextIndependentParameterRenderer33, new DiagnosticParameterRenderer() { // from class: qg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return DefaultErrorMessages.m((Boolean) obj, renderingContext);
            }
        });
        diagnosticFactoryToRendererMap8.put(Errors.NON_TAIL_RECURSIVE_CALL, "Recursive call is not a tail call");
        diagnosticFactoryToRendererMap8.put(Errors.TAIL_RECURSION_IN_TRY_IS_NOT_SUPPORTED, "Tail recursion optimization inside try/catch/finally is not supported");
        DiagnosticFactory3<KtExpression, String, KotlinType, KotlinType> diagnosticFactory47 = Errors.RESULT_TYPE_MISMATCH;
        SmartTypeRenderer smartTypeRenderer6 = Renderers.RENDER_TYPE;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory47, "{0} must return {1} but returns {2}", contextIndependentParameterRenderer31, smartTypeRenderer6, smartTypeRenderer6);
        diagnosticFactoryToRendererMap8.put(Errors.UNSAFE_INFIX_CALL, "Infix call corresponds to a dot-qualified call ''{0}.{1}({2})'' which is not allowed on a nullable receiver ''{0}''. Use ''?.''-qualified call instead", contextIndependentParameterRenderer33, contextIndependentParameterRenderer31, contextIndependentParameterRenderer33);
        diagnosticFactoryToRendererMap8.put(Errors.UNSAFE_OPERATOR_CALL, "Operator call corresponds to a dot-qualified call ''{0}.{1}({2})'' which is not allowed on a nullable receiver ''{0}''.", contextIndependentParameterRenderer33, contextIndependentParameterRenderer31, contextIndependentParameterRenderer33);
        DiagnosticFactory1<PsiElement, Collection<? extends ResolvedCall<?>>> diagnosticFactory48 = Errors.OVERLOAD_RESOLUTION_AMBIGUITY;
        ContextIndependentParameterRenderer<Collection<? extends ResolvedCall<?>>> contextIndependentParameterRenderer37 = Renderers.AMBIGUOUS_CALLS;
        diagnosticFactoryToRendererMap8.put(diagnosticFactory48, "Overload resolution ambiguity: {0}", contextIndependentParameterRenderer37);
        diagnosticFactoryToRendererMap8.put(Errors.OVERLOAD_RESOLUTION_AMBIGUITY_BECAUSE_OF_STUB_TYPES, "No type argument for type parameter(s) `{1}` of the `{0}` builder specified. Cannot choose which overloaded function `{2}` to call. To disambiguate this call, either use an explicit type cast for a parameter or a receiver (see specific errors on them) or specify the type `{1}` explicitly.", contextIndependentParameterRenderer31, contextIndependentParameterRenderer31, contextIndependentParameterRenderer31);
        diagnosticFactoryToRendererMap8.put(Errors.STUB_TYPE_IN_ARGUMENT_CAUSES_AMBIGUITY, "The type of an argument hasn''t been inferred yet. To disambiguate this call, explicitly cast it to `{0}` if you want the builder''s type parameter(s) `{1}` to be inferred to `{2}`.", smartTypeRenderer6, contextIndependentParameterRenderer31, contextIndependentParameterRenderer31);
        diagnosticFactoryToRendererMap8.put(Errors.STUB_TYPE_IN_RECEIVER_CAUSES_AMBIGUITY, "The type of a receiver hasn''t been inferred yet. To disambiguate this call, explicitly cast it to `{0}` if you want the builder''s type parameter(s) `{1}` to be inferred to `{2}`.", smartTypeRenderer6, contextIndependentParameterRenderer31, contextIndependentParameterRenderer31, (DiagnosticParameterRenderer) null);
        diagnosticFactoryToRendererMap8.put(Errors.BUILDER_INFERENCE_MULTI_LAMBDA_RESTRICTION, "Unstable inference behaviour with multiple lambdas. Please either specify the type argument for generic parameter `{0}` of `{1}` explicitly", contextIndependentParameterRenderer34, contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.BUILDER_INFERENCE_STUB_RECEIVER, "The type of a receiver hasn''t been inferred yet. Please specify type argument for generic parameter `{0}` of `{1}` explicitly", contextIndependentParameterRenderer34, contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.BUILDER_INFERENCE_STUB_PARAMETER_TYPE, "The type of parameter `{0}` cannot be inferred by builder inference", contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.UPPER_BOUND_VIOLATION_IN_CONSTRAINT, "Upper bound violation for generic parameter `{0}` of `{1}`: {3} is not a subtype of {2}", contextIndependentParameterRenderer34, contextIndependentParameterRenderer34, smartTypeRenderer6, smartTypeRenderer6);
        diagnosticFactoryToRendererMap8.put(Errors.NONE_APPLICABLE, "None of the following functions can be called with the arguments supplied: {0}", contextIndependentParameterRenderer37);
        diagnosticFactoryToRendererMap8.put(Errors.CANNOT_COMPLETE_RESOLVE, "Cannot choose among the following candidates without completing type inference: {0}", contextIndependentParameterRenderer37);
        diagnosticFactoryToRendererMap8.put(Errors.UNRESOLVED_REFERENCE_WRONG_RECEIVER, "Unresolved reference. None of the following candidates is applicable because of receiver type mismatch: {0}", contextIndependentParameterRenderer37);
        diagnosticFactoryToRendererMap8.put(Errors.CALLABLE_REFERENCE_RESOLUTION_AMBIGUITY, "Callable reference resolution ambiguity: {0}", Renderers.AMBIGUOUS_CALLABLE_REFERENCES);
        diagnosticFactoryToRendererMap8.put(Errors.NO_VALUE_FOR_PARAMETER, "No value passed for parameter ''{0}''", contextIndependentParameterRenderer32);
        diagnosticFactoryToRendererMap8.put(Errors.MISSING_RECEIVER, "A receiver of type {0} is required", smartTypeRenderer6);
        diagnosticFactoryToRendererMap8.put(Errors.NO_RECEIVER_ALLOWED, "No receiver can be passed to this function or property");
        diagnosticFactoryToRendererMap8.put(Errors.ASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_FUNCTION, "Assigning single elements to varargs in named form is forbidden", contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION, "Redundant spread (*) operator");
        diagnosticFactoryToRendererMap8.put(Errors.ASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_ANNOTATION, "Assigning single elements to varargs in named form is forbidden");
        diagnosticFactoryToRendererMap8.put(Errors.REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_ANNOTATION, "Redundant spread (*) operator");
        diagnosticFactoryToRendererMap8.put(Errors.CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS, "Cannot create an instance of an abstract class");
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS, "Type inference failed: {0}", Renderers.TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS_RENDERER);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_CANNOT_CAPTURE_TYPES, "Type inference failed: {0}", Renderers.TYPE_INFERENCE_CANNOT_CAPTURE_TYPES_RENDERER);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER, "Type inference failed: {0}", Renderers.TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER_RENDERER);
        diagnosticFactoryToRendererMap8.put(Errors.NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER, "Not enough information to infer type variable {0}", contextIndependentParameterRenderer31);
        diagnosticFactoryToRendererMap8.put(Errors.INFERRED_INTO_DECLARED_UPPER_BOUNDS, "Type parameter for a type argument {0} can''t be inferred into declared upper bounds. Please provide any use-site type information. It will become an error in future releases.", contextIndependentParameterRenderer31);
        diagnosticFactoryToRendererMap8.put(Errors.COULD_BE_INFERRED_ONLY_WITH_UNRESTRICTED_BUILDER_INFERENCE, "Builder inference lambda contains inapplicable calls so {0} can''t be inferred. It could be resolved only with unrestricted builder inference. Please use -Xunrestricted-builder-inference compiler flag to enable it.", contextIndependentParameterRenderer31);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR, "Type inference failed: {0}", Renderers.TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR_RENDERER);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_INCORPORATION_ERROR, "Type inference failed. Please try to specify type arguments explicitly.");
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_ONLY_INPUT_TYPES, "Type inference failed. The value of the type parameter {0} should be mentioned in input types (argument types, receiver type or expected type). Try to specify it explicitly.", contextIndependentParameterRenderer32);
        diagnosticFactoryToRendererMap8.put(Errors.INFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION, "Type argument for a type parameter {0} can''t be inferred because it has incompatible upper bounds: {1} ({2}{3})", contextIndependentParameterRenderer34, diagnosticParameterRenderer2, contextIndependentParameterRenderer34, contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION, "Type argument for a type parameter {0} has possible incompatible upper bounds: {1} ({2}{3})", contextIndependentParameterRenderer34, diagnosticParameterRenderer2, contextIndependentParameterRenderer34, contextIndependentParameterRenderer34);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_UPPER_BOUND_VIOLATED, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, Renderers.TYPE_INFERENCE_UPPER_BOUND_VIOLATED_RENDERER);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH, "Type inference failed. Expected type mismatch: inferred type is {1} but {0} was expected", smartTypeRenderer6, smartTypeRenderer6);
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_CANDIDATE_WITH_SAM_AND_VARARG, "Please use spread operator to pass an array as vararg. It will be an error in 1.5.");
        diagnosticFactoryToRendererMap8.put(Errors.TYPE_INFERENCE_POSTPONED_VARIABLE_IN_RECEIVER_TYPE, "Postponed type variable (type variable of the builder inference) can't be used in the receiver type. Use a member function instead of extension one or specify type arguments of a function which uses the builder inference, explicitly.");
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap9 = MAP;
        diagnosticFactoryToRendererMap9.put(Errors.TYPE_INFERENCE_FAILED_ON_SPECIAL_CONSTRUCT, "Type inference for control flow expression failed. Please specify its type explicitly.");
        diagnosticFactoryToRendererMap9.put(Errors.WRONG_NUMBER_OF_TYPE_ARGUMENTS, "{0,choice,0#No type arguments|1#One type argument|1<{0,number,integer} type arguments} expected for {1}", (DiagnosticParameterRenderer) null, Renderers.COMPACT_WITHOUT_SUPERTYPES);
        diagnosticFactoryToRendererMap9.put(Errors.OUTER_CLASS_ARGUMENTS_REQUIRED, "Type arguments should be specified for an outer {0}. Use full class name to specify them", Renderers.RENDER_CLASS_OR_OBJECT_NAME);
        DiagnosticFactory2<KtTypeReference, Integer, String> diagnosticFactory49 = Errors.NO_TYPE_ARGUMENTS_ON_RHS;
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer38 = CommonRenderers.STRING;
        diagnosticFactoryToRendererMap9.put(diagnosticFactory49, "{0,choice,0#No type arguments|1#One type argument|1<{0,number,integer} type arguments} expected. Use ''{1}'' if you don''t want to pass type arguments", (DiagnosticParameterRenderer) null, contextIndependentParameterRenderer38);
        diagnosticFactoryToRendererMap9.put(Errors.TYPE_ARGUMENTS_NOT_ALLOWED, "Type arguments are not allowed {0}", contextIndependentParameterRenderer38);
        DiagnosticFactory1<PsiElement, TypeParameterDescriptor> diagnosticFactory50 = Errors.TYPE_PARAMETER_AS_REIFIED;
        ContextIndependentParameterRenderer<Named> contextIndependentParameterRenderer39 = Renderers.NAME;
        diagnosticFactoryToRendererMap9.put(diagnosticFactory50, "Cannot use ''{0}'' as reified type parameter. Use a class instead.", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.DEFINITELY_NON_NULLABLE_AS_REIFIED, "Cannot use definitely-non-nullable type as reified type argument");
        diagnosticFactoryToRendererMap9.put(Errors.TYPE_PARAMETER_AS_REIFIED_ARRAY, "Cannot use ''{0}'' as reified type parameter, since the array type parameter is not reified.", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.REIFIED_TYPE_PARAMETER_NO_INLINE, "Only type parameters of inline functions can be reified");
        diagnosticFactoryToRendererMap9.put(Errors.REIFIED_TYPE_FORBIDDEN_SUBSTITUTION, "Cannot use ''{0}'' as reified type parameter", smartTypeRenderer6);
        diagnosticFactoryToRendererMap9.put(Errors.REIFIED_TYPE_UNSAFE_SUBSTITUTION, "It may be not safe to use ''{0}'' as an argument for a reified type parameter. Use a non-generic type or * if possible", smartTypeRenderer6);
        diagnosticFactoryToRendererMap9.put(Errors.TYPE_PARAMETERS_NOT_ALLOWED, "Type parameters are not allowed here");
        diagnosticFactoryToRendererMap9.put(Errors.CANDIDATE_CHOSEN_USING_OVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION, "Candidate was chosen only by @OverloadResolutionByLambdaReturnType annotation");
        diagnosticFactoryToRendererMap9.put(Errors.COMPATIBILITY_WARNING, "Candidate resolution will be changed soon, please use fully qualified name to invoke the following closer candidate explicitly ''{0}''", Renderers.COMPATIBILITY_CANDIDATE);
        diagnosticFactoryToRendererMap9.put(Errors.TYPE_PARAMETER_OF_PROPERTY_NOT_USED_IN_RECEIVER, "Type parameter of a property must be used in its receiver type");
        diagnosticFactoryToRendererMap9.put(Errors.SUPERTYPES_FOR_ANNOTATION_CLASS, "Annotation class cannot have supertypes");
        diagnosticFactoryToRendererMap9.put(Errors.MISSING_VAL_ON_ANNOTATION_PARAMETER, "'val' keyword is missing on annotation parameter");
        diagnosticFactoryToRendererMap9.put(Errors.VAR_ANNOTATION_PARAMETER, "An annotation parameter cannot be 'var'");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_CLASS_CONSTRUCTOR_CALL, "Annotation class cannot be instantiated");
        diagnosticFactoryToRendererMap9.put(Errors.NOT_AN_ANNOTATION_CLASS, "''{0}'' is not an annotation class", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_CLASS_MEMBER, "Members are not allowed in annotation class");
        diagnosticFactoryToRendererMap9.put(Errors.INVALID_TYPE_OF_ANNOTATION_MEMBER, "Invalid type of annotation member");
        diagnosticFactoryToRendererMap9.put(Errors.NULLABLE_TYPE_OF_ANNOTATION_MEMBER, "An annotation parameter cannot be nullable");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ARGUMENT_MUST_BE_CONST, "An annotation argument must be a compile-time constant");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ARGUMENT_MUST_BE_ENUM_CONST, "An enum annotation argument must be a enum constant");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ARGUMENT_MUST_BE_KCLASS_LITERAL, "An annotation argument must be a class literal (T::class)");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ARGUMENT_KCLASS_LITERAL_OF_TYPE_PARAMETER, "Type parameter in a class literal is not allowed in an annotation argument");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ARGUMENT_KCLASS_LITERAL_OF_TYPE_PARAMETER_ERROR, "Type parameter in a class literal is deprecated in an annotation argument");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_PARAMETER_DEFAULT_VALUE_MUST_BE_CONSTANT, "Default value of annotation parameter must be a compile-time constant");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATIONS_ON_BLOCK_LEVEL_EXPRESSION_ON_THE_SAME_LINE, "Annotations on block-level expressions are being parsed differently depending on presence of a new line after them. Use new line if whole block-level expression must be annotated or wrap annotated expression in parentheses");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_USED_AS_ANNOTATION_ARGUMENT, "An annotation can't be used as the annotations argument");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ARGUMENT_IS_NON_CONST, "An annotation argument must be a compile-time constant");
        diagnosticFactoryToRendererMap9.put(Errors.CYCLE_IN_ANNOTATION_PARAMETER, "Type of this parameter is cyclic");
        diagnosticFactoryToRendererMap9.put(Errors.RESTRICTED_RETENTION_FOR_EXPRESSION_ANNOTATION, "Expression annotations with retention other than SOURCE are prohibited");
        diagnosticFactoryToRendererMap9.put(Errors.LOCAL_ANNOTATION_CLASS, "Annotation class cannot be local");
        diagnosticFactoryToRendererMap9.put(Errors.ANNOTATION_ON_SUPERCLASS, "Annotations on superclass are meaningless");
        diagnosticFactoryToRendererMap9.put(Errors.CONST_VAL_NOT_TOP_LEVEL_OR_OBJECT, "Const 'val' are only allowed on top level, in named objects, or in companion objects");
        diagnosticFactoryToRendererMap9.put(Errors.CONST_VAL_WITH_DELEGATE, "Const 'val' should not have a delegate");
        diagnosticFactoryToRendererMap9.put(Errors.CONST_VAL_WITH_GETTER, "Const 'val' should not have a getter");
        diagnosticFactoryToRendererMap9.put(Errors.TYPE_CANT_BE_USED_FOR_CONST_VAL, "Const ''val'' has type ''{0}''. Only primitives and String are allowed", smartTypeRenderer6);
        diagnosticFactoryToRendererMap9.put(Errors.CONST_VAL_WITHOUT_INITIALIZER, "Const 'val' should have an initializer");
        diagnosticFactoryToRendererMap9.put(Errors.CONST_VAL_WITH_NON_CONST_INITIALIZER, "Const 'val' initializer should be a constant value");
        diagnosticFactoryToRendererMap9.put(Errors.NON_CONST_VAL_USED_IN_CONSTANT_EXPRESSION, "Only 'const val' can be used in constant expressions");
        diagnosticFactoryToRendererMap9.put(Errors.DEFAULT_VALUE_NOT_ALLOWED_IN_OVERRIDE, "An overriding function is not allowed to specify default values for its parameters");
        DiagnosticFactory1<KtParameter, ValueParameterDescriptor> diagnosticFactory51 = Errors.MULTIPLE_DEFAULTS_INHERITED_FROM_SUPERTYPES;
        SmartDescriptorRenderer smartDescriptorRenderer10 = Renderers.FQ_NAMES_IN_TYPES;
        diagnosticFactoryToRendererMap9.put(diagnosticFactory51, "More than one overridden descriptor declares a default value for ''{0}''. As the compiler can not make sure these values agree, this is not allowed.", smartDescriptorRenderer10);
        diagnosticFactoryToRendererMap9.put(Errors.MULTIPLE_DEFAULTS_INHERITED_FROM_SUPERTYPES_WHEN_NO_EXPLICIT_OVERRIDE, "More than one overridden descriptor declares a default value for ''{0}''. As the compiler can not make sure these values agree, this is not allowed.", smartDescriptorRenderer10);
        diagnosticFactoryToRendererMap9.put(Errors.PARAMETER_NAME_CHANGED_ON_OVERRIDE, "The corresponding parameter in the supertype ''{0}'' is named ''{1}''. This may cause problems when calling this function with named arguments.", contextIndependentParameterRenderer39, contextIndependentParameterRenderer39);
        DiagnosticFactory2<KtClassOrObject, Collection<? extends CallableMemberDescriptor>, Integer> diagnosticFactory52 = Errors.DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES;
        DiagnosticParameterRenderer diagnosticParameterRendererCommaSeparated = CommonRenderers.commaSeparated(smartDescriptorRenderer10);
        ContextIndependentParameterRenderer<Object> contextIndependentParameterRenderer40 = Renderers.TO_STRING;
        diagnosticFactoryToRendererMap9.put(diagnosticFactory52, "Names of the parameter #{1} conflict in the following members of supertypes: ''{0}''. This may cause problems when calling this function with named arguments.", diagnosticParameterRendererCommaSeparated, contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap9.put(Errors.NAME_FOR_AMBIGUOUS_PARAMETER, "Named argument is not allowed for a parameter with an ambiguous name");
        diagnosticFactoryToRendererMap9.put(Errors.DATA_CLASS_WITHOUT_PARAMETERS, "Data class must have at least one primary constructor parameter");
        diagnosticFactoryToRendererMap9.put(Errors.DATA_CLASS_VARARG_PARAMETER, "Primary constructor vararg parameters are forbidden for data classes");
        diagnosticFactoryToRendererMap9.put(Errors.DATA_CLASS_NOT_PROPERTY_PARAMETER, "Data class primary constructor must only have property (val / var) parameters");
        diagnosticFactoryToRendererMap9.put(Errors.DATA_OBJECT_CUSTOM_EQUALS_OR_HASH_CODE, "Data object cannot have a custom implementation of 'equals' or 'hashCode'");
        diagnosticFactoryToRendererMap9.put(Errors.CATCH_PARAMETER_WITH_DEFAULT_VALUE, "Catch clause parameter may not have a default value");
        diagnosticFactoryToRendererMap9.put(Errors.AMBIGUOUS_ANONYMOUS_TYPE_INFERRED, "Right-hand side has anonymous type. Please specify type explicitly", contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap9.put(Errors.APPROXIMATED_LOCAL_TYPE_WILL_BECOME_NULLABLE, "Declaration return type inferred as ''{0}'' instead of ''{0}?''. This will be fixed in Kotlin 1.9. Please specify the type explicitly to avoid future errors or unexpected changes in behavior. See https://youtrack.jetbrains.com/issue/KT-53982 for details.", contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap9.put(Errors.APPROXIMATED_LOCAL_TYPE_WILL_BECOME_FLEXIBLE, "Declaration return type inferred as ''{0}'' but it may be nullable. This will be fixed in Kotlin 1.9. Please specify the type explicitly to avoid unexpected changes in behavior. See https://youtrack.jetbrains.com/issue/KT-53982 for details.", contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap9.put(Errors.KCLASS_WITH_NULLABLE_TYPE_PARAMETER_IN_SIGNATURE, "Declaration has an inconsistent return type. Please add upper bound Any for type parameter ''{0}'' or specify return type explicitly", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.EXTENSION_IN_CLASS_REFERENCE_NOT_ALLOWED, "''{0}'' is a member and an extension at the same time. References to such elements are not allowed", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.CALLABLE_REFERENCE_LHS_NOT_A_CLASS, "Left-hand side of a callable reference cannot be a type parameter");
        diagnosticFactoryToRendererMap9.put(Errors.CALLABLE_REFERENCE_TO_ANNOTATION_CONSTRUCTOR, "Annotation class cannot be instantiated");
        diagnosticFactoryToRendererMap9.put(Errors.CLASS_LITERAL_LHS_NOT_A_CLASS, "Only classes are allowed on the left hand side of a class literal");
        diagnosticFactoryToRendererMap9.put(Errors.ARRAY_CLASS_LITERAL_REQUIRES_ARGUMENT, "Array class literal requires a type argument, please specify one in angle brackets");
        diagnosticFactoryToRendererMap9.put(Errors.NULLABLE_TYPE_IN_CLASS_LITERAL_LHS, "Type in a class literal must not be nullable");
        diagnosticFactoryToRendererMap9.put(Errors.EXPRESSION_OF_NULLABLE_TYPE_IN_CLASS_LITERAL_LHS, "Expression in a class literal has a nullable type ''{0}'', use !! to make the type non-nullable", Renderers.RENDER_TYPE);
        diagnosticFactoryToRendererMap9.put(Errors.ADAPTED_CALLABLE_REFERENCE_AGAINST_REFLECTION_TYPE, "Adapted callable reference cannot be resolved against reflective types");
        DiagnosticFactory2<KtElement, DeclarationDescriptor, DeclarationDescriptor> diagnosticFactory53 = Errors.NON_PUBLIC_CALL_FROM_PUBLIC_INLINE;
        SmartDescriptorRenderer smartDescriptorRenderer11 = Renderers.SHORT_NAMES_IN_TYPES;
        diagnosticFactoryToRendererMap9.put(diagnosticFactory53, "Public-API inline function cannot access non-public-API ''{0}''", smartDescriptorRenderer11, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.DEPRECATED_IMPLICIT_NON_PUBLIC_API_ACCESS, "Deprecated implicit access of non-public-API from public-API inline function");
        diagnosticFactoryToRendererMap9.put(Errors.PRIVATE_CLASS_MEMBER_FROM_INLINE, "Non-private inline function cannot access members of private classes: ''{0}''", smartDescriptorRenderer11, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.PRIVATE_CLASS_MEMBER_FROM_INLINE_WARNING, "Non-private inline function cannot access members of private classes: ''{0}''. This warning will become an error in 2.0", smartDescriptorRenderer11, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.NOT_YET_SUPPORTED_IN_INLINE, "{0} are not yet supported in inline functions", contextIndependentParameterRenderer38);
        diagnosticFactoryToRendererMap9.put(Errors.DECLARATION_CANT_BE_INLINED, "'inline' modifier is not allowed on virtual members. Only private or final members can be inlined");
        diagnosticFactoryToRendererMap9.put(Errors.DECLARATION_CANT_BE_INLINED_WARNING, "'inline' modifier is not allowed on virtual enum members. Only private or final members can be inlined. This warning will become an error in K2");
        diagnosticFactoryToRendererMap9.put(Errors.OVERRIDE_BY_INLINE, "Override by an inline function");
        diagnosticFactoryToRendererMap9.put(Errors.REIFIED_TYPE_PARAMETER_IN_OVERRIDE, "Override by a function with reified type parameter");
        diagnosticFactoryToRendererMap9.put(Errors.NOTHING_TO_INLINE, "Expected performance impact from inlining is insignificant. Inlining works best for functions with parameters of functional types");
        DiagnosticFactory2<KtElement, KtExpression, DeclarationDescriptor> diagnosticFactory54 = Errors.USAGE_IS_NOT_INLINABLE;
        ContextIndependentParameterRenderer<PsiElement> contextIndependentParameterRenderer41 = Renderers.ELEMENT_TEXT;
        diagnosticFactoryToRendererMap9.put(diagnosticFactory54, "Illegal usage of inline-parameter ''{0}'' in ''{1}''. Add ''noinline'' modifier to the parameter declaration", contextIndependentParameterRenderer41, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.USAGE_IS_NOT_INLINABLE_WARNING, "Deprecated usage of inline-parameter ''{0}'' in ''{1}''. Add ''noinline'' modifier to the parameter declaration. See https://youtrack.jetbrains.com/issue/KT-52502", contextIndependentParameterRenderer41, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.NULLABLE_INLINE_PARAMETER, "Inline-parameter ''{0}'' of ''{1}'' must not be nullable. Add ''noinline'' modifier to the parameter declaration or make its type not nullable", contextIndependentParameterRenderer41, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.RECURSION_IN_INLINE, "Inline function ''{1}'' cannot be recursive", contextIndependentParameterRenderer41, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.INLINE_PROPERTY_WITH_BACKING_FIELD, "Inline property cannot have backing field");
        diagnosticFactoryToRendererMap9.put(Errors.NON_INTERNAL_PUBLISHED_API, "@PublishedApi annotation is only applicable for internal declaration");
        diagnosticFactoryToRendererMap9.put(Errors.PROTECTED_CALL_FROM_PUBLIC_INLINE, "Protected function call from public-API inline function is prohibited", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.PROTECTED_CONSTRUCTOR_CALL_FROM_PUBLIC_INLINE, "Protected constructor call from public-API inline function is deprecated", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.SUPER_CALL_FROM_PUBLIC_INLINE, "Accessing super members from public-API inline function is deprecated", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.INVALID_DEFAULT_FUNCTIONAL_PARAMETER_FOR_INLINE, "Invalid default value for inline parameter: ''{0}''. Only lambdas, anonymous functions, and callable references are supported", contextIndependentParameterRenderer41, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.NOT_SUPPORTED_INLINE_PARAMETER_IN_INLINE_PARAMETER_DEFAULT_VALUE, "Usage of inline parameter ''{0}'' in default value for another inline parameter is not supported", contextIndependentParameterRenderer41, smartDescriptorRenderer11);
        diagnosticFactoryToRendererMap9.put(Errors.PRIVATE_INLINE_FUNCTIONS_RETURNING_ANONYMOUS_OBJECTS, "Return type of the private inline function can't be anonymous. It will be approximated to Any in a future release. See KT-33917 for more details");
        diagnosticFactoryToRendererMap9.put(Errors.NON_LOCAL_RETURN_NOT_ALLOWED, "Can''t inline ''{0}'' here: it may contain non-local returns. Add ''crossinline'' modifier to parameter declaration ''{0}''", contextIndependentParameterRenderer41);
        diagnosticFactoryToRendererMap9.put(Errors.NON_LOCAL_SUSPENSION_POINT, "Suspension functions can be called only within coroutine body");
        diagnosticFactoryToRendererMap9.put(Errors.ILLEGAL_SUSPEND_FUNCTION_CALL, "Suspend function ''{0}'' should be called only from a coroutine or another suspend function", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.ILLEGAL_SUSPEND_PROPERTY_ACCESS, "Suspend property ''{0}'' should be accessed only from a coroutine or suspend function", contextIndependentParameterRenderer39);
        diagnosticFactoryToRendererMap9.put(Errors.ILLEGAL_RESTRICTED_SUSPENDING_FUNCTION_CALL, "Restricted suspending functions can only invoke member or extension suspending functions on their restricted coroutine scope");
        diagnosticFactoryToRendererMap9.put(Errors.NON_MODIFIER_FORM_FOR_BUILT_IN_SUSPEND, "''suspend'' function can only be called in a form of modifier of a lambda: suspend { ... }");
        diagnosticFactoryToRendererMap9.put(Errors.IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION, "Returning type parameter has been inferred to Nothing implicitly. Please specify type arguments explicitly to hide this warning. Nothing can produce an exception at runtime.");
        diagnosticFactoryToRendererMap9.put(Errors.IMPLICIT_NOTHING_TYPE_ARGUMENT_AGAINST_NOT_NOTHING_EXPECTED_TYPE, "Returning type parameter has been inferred to Nothing implicitly because Nothing is more specific than specified expected type. Please specify type arguments explicitly in accordance with expected type to hide this warning. Nothing can produce an exception at runtime. See KT-36776 for more details.");
        diagnosticFactoryToRendererMap9.put(Errors.RETURN_FOR_BUILT_IN_SUSPEND, "Using implicit label for this lambda is prohibited");
        diagnosticFactoryToRendererMap9.put(Errors.MODIFIER_FORM_FOR_NON_BUILT_IN_SUSPEND, "Calls having a form of ''suspend {}'' are deprecated because ''suspend'' in the context will have a meaning of a modifier. Surround the lambda with parentheses: ''suspend({ ... })''");
        diagnosticFactoryToRendererMap9.put(Errors.MODIFIER_FORM_FOR_NON_BUILT_IN_SUSPEND_FUN, "Calls having a form of ''suspend fun'' are deprecated because ''suspend'' in the context will have a meaning of a modifier. Surround the argument of the call with parens: ''suspend(fun() { ... })''. See https://youtrack.jetbrains.com/issue/KT-49264");
        diagnosticFactoryToRendererMap9.put(Errors.PLUGIN_ERROR, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, new DiagnosticParameterRenderer() { // from class: rg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return ((RenderedDiagnostic) obj).getText();
            }
        });
        DiagnosticFactoryToRendererMap diagnosticFactoryToRendererMap10 = MAP;
        diagnosticFactoryToRendererMap10.put(Errors.PLUGIN_WARNING, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, new DiagnosticParameterRenderer() { // from class: sg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return ((RenderedDiagnostic) obj).getText();
            }
        });
        diagnosticFactoryToRendererMap10.put(Errors.PLUGIN_INFO, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, new DiagnosticParameterRenderer() { // from class: tg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return ((RenderedDiagnostic) obj).getText();
            }
        });
        diagnosticFactoryToRendererMap10.put(Errors.ERROR_IN_CONTRACT_DESCRIPTION, "Error in contract description: {0}", contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap10.put(Errors.CONTRACT_NOT_ALLOWED, BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap10.put(Errors.NO_CONTEXT_RECEIVER, "No required context receiver found: {0}", contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap10.put(Errors.MULTIPLE_ARGUMENTS_APPLICABLE_FOR_CONTEXT_RECEIVER, "Multiple arguments applicable for context receiver: {0}", contextIndependentParameterRenderer40);
        diagnosticFactoryToRendererMap10.put(Errors.AMBIGUOUS_CALL_WITH_IMPLICIT_CONTEXT_RECEIVER, "With implicit context receiver, call is ambiguous. Specify the receiver explicitly");
        diagnosticFactoryToRendererMap10.put(Errors.UNSUPPORTED_CONTEXTUAL_DECLARATION_CALL, "To use contextual declarations, specify the `-Xcontext-receivers` compiler option");
        diagnosticFactoryToRendererMap10.put(Errors.SUBTYPING_BETWEEN_CONTEXT_RECEIVERS, "Subtyping relation between context receivers is prohibited");
        diagnosticFactoryToRendererMap10.put(Errors.CONTEXT_PARAMETERS_UNSUPPORTED, "Context parameters are not supported in K1 mode. Consider using a more recent language version and switching to K2 mode.");
        diagnosticFactoryToRendererMap10.put(Errors.VOLATILE_ON_VALUE, "'@Volatile' annotation cannot be used on immutable properties");
        diagnosticFactoryToRendererMap10.put(Errors.VOLATILE_ON_DELEGATE, "'@Volatile' annotation cannot be used on delegated properties");
        diagnosticFactoryToRendererMap10.setImmutable();
        for (Field field : Errors.class.getFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    Object obj = field.get(null);
                    if ((obj instanceof DiagnosticFactory) && MAP.get((DiagnosticFactory) obj) == null) {
                        throw new IllegalStateException("No default diagnostic renderer is provided for " + ((DiagnosticFactory) obj).getName());
                    }
                } catch (IllegalAccessException e2) {
                    e7f.a(e2);
                    return;
                }
            }
        }
    }

    private DefaultErrorMessages() {
    }

    public static DiagnosticParameterRenderer<Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, Collection<MemberDescriptor>>> adaptGenerics1(final DiagnosticParameterRenderer<Map<K1ExpectActualCompatibility.Incompatible<? extends MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>> diagnosticParameterRenderer) {
        return new DiagnosticParameterRenderer() { // from class: bg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return diagnosticParameterRenderer.render((Map) obj, renderingContext);
            }
        };
    }

    public static DiagnosticParameterRenderer<List<Pair<MemberDescriptor, Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, Collection<MemberDescriptor>>>>> adaptGenerics2(final DiagnosticParameterRenderer<List<? extends Pair<? extends MemberDescriptor, ? extends Map<K1ExpectActualCompatibility.Incompatible<? extends MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>>>> diagnosticParameterRenderer) {
        return new DiagnosticParameterRenderer() { // from class: mg3
            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public final String render(Object obj, RenderingContext renderingContext) {
                return diagnosticParameterRenderer.render((List) obj, renderingContext);
            }
        };
    }

    public static /* synthetic */ String[] c(TypeMismatchDueToTypeProjectionsData typeMismatchDueToTypeProjectionsData) {
        RenderingContext renderingContextOf = RenderingContext.of(typeMismatchDueToTypeProjectionsData.getExpectedType(), typeMismatchDueToTypeProjectionsData.getExpressionType(), typeMismatchDueToTypeProjectionsData.getReceiverType(), typeMismatchDueToTypeProjectionsData.getCallableDescriptor());
        SmartTypeRenderer smartTypeRenderer = Renderers.RENDER_TYPE;
        return new String[]{smartTypeRenderer.render(typeMismatchDueToTypeProjectionsData.getExpectedType(), renderingContextOf), smartTypeRenderer.render(typeMismatchDueToTypeProjectionsData.getExpressionType(), renderingContextOf), smartTypeRenderer.render(typeMismatchDueToTypeProjectionsData.getReceiverType(), renderingContextOf), Renderers.FQ_NAMES_IN_TYPES.render((DeclarationDescriptor) typeMismatchDueToTypeProjectionsData.getCallableDescriptor(), renderingContextOf)};
    }

    public static /* synthetic */ String[] f(VarianceConflictDiagnosticData varianceConflictDiagnosticData) {
        RenderingContext renderingContextOf = RenderingContext.of(varianceConflictDiagnosticData.getTypeParameter(), varianceConflictDiagnosticData.getTypeParameter().getVariance(), varianceConflictDiagnosticData.getOccurrencePosition(), varianceConflictDiagnosticData.getContainingType());
        String strRender = Renderers.NAME.render((Named) varianceConflictDiagnosticData.getTypeParameter(), renderingContextOf);
        ContextIndependentParameterRenderer<Variance> contextIndependentParameterRenderer = CommonRenderers.RENDER_POSITION_VARIANCE;
        return new String[]{strRender, contextIndependentParameterRenderer.render(varianceConflictDiagnosticData.getTypeParameter().getVariance(), renderingContextOf), contextIndependentParameterRenderer.render(varianceConflictDiagnosticData.getOccurrencePosition(), renderingContextOf), Renderers.RENDER_TYPE.render(varianceConflictDiagnosticData.getContainingType(), renderingContextOf)};
    }

    public static /* synthetic */ String g(VersionRequirement.Version version, RenderingContext renderingContext) {
        if (version.equals(VersionRequirement.Version.INFINITY)) {
            return Argument.Delimiters.none;
        }
        return " is only supported since Kotlin " + version.asString() + " and";
    }

    public static DiagnosticRenderer getRendererForDiagnostic(final UnboundDiagnostic unboundDiagnostic) {
        if (unboundDiagnostic == null) {
            $$$reportNull$$$0(3);
        }
        DiagnosticRenderer diagnosticRenderer = (DiagnosticRenderer) AddToStdlibKt.firstNotNullResult(RENDERER_MAPS, new Function1() { // from class: lg3
            public final Object invoke(Object obj) {
                return ((DiagnosticFactoryToRendererMap) obj).get(unboundDiagnostic.getFactory());
            }
        });
        return diagnosticRenderer != null ? diagnosticRenderer : unboundDiagnostic.getFactory().getDefaultRenderer();
    }

    public static /* synthetic */ String h(KtExpression ktExpression, RenderingContext renderingContext) {
        String string = ktExpression.toString();
        return string.substring(0, 1) + string.substring(1).toLowerCase();
    }

    public static /* synthetic */ String i(KotlinType kotlinType, RenderingContext renderingContext) {
        if (KotlinTypeKt.isError(kotlinType)) {
            return Argument.Delimiters.none;
        }
        return " of type '" + Renderers.RENDER_TYPE.render(kotlinType, renderingContext) + "'";
    }

    public static /* synthetic */ String l(IncompatibleVersionErrorData incompatibleVersionErrorData, RenderingContext renderingContext) {
        String str;
        StringBuilder sb = new StringBuilder("The actual metadata version is ");
        sb.append(incompatibleVersionErrorData.getActualVersion());
        sb.append(", but the compiler version ");
        sb.append(incompatibleVersionErrorData.getCompilerVersion());
        if (incompatibleVersionErrorData.getLanguageVersion().equals(incompatibleVersionErrorData.getCompilerVersion())) {
            str = Argument.Delimiters.none;
        } else {
            str = " [with language version " + incompatibleVersionErrorData.getLanguageVersion() + "]";
        }
        sb.append(str);
        sb.append(" can read versions up to ");
        sb.append(incompatibleVersionErrorData.getExpectedVersion());
        sb.append(".\nThe class is loaded from ");
        sb.append(FileUtil.toSystemIndependentName(incompatibleVersionErrorData.getFilePath()));
        return sb.toString();
    }

    public static /* synthetic */ String m(Boolean bool, RenderingContext renderingContext) {
        return bool.booleanValue() ? "..." : Argument.Delimiters.none;
    }

    public static /* synthetic */ String o(Pair pair, RenderingContext renderingContext) {
        String str = (String) pair.getSecond();
        StringBuilder sb = new StringBuilder();
        sb.append((String) pair.getFirst());
        sb.append(str != null ? ". ".concat(str) : Argument.Delimiters.none);
        return sb.toString();
    }

    public static /* synthetic */ String r(VersionRequirement.Version version, RenderingContext renderingContext) {
        if (version.equals(VersionRequirement.Version.INFINITY)) {
            return Argument.Delimiters.none;
        }
        return " is only available since Kotlin " + version.asString() + " and";
    }

    public static String render(UnboundDiagnostic unboundDiagnostic) {
        if (unboundDiagnostic == null) {
            $$$reportNull$$$0(0);
        }
        DiagnosticRenderer rendererForDiagnostic = getRendererForDiagnostic(unboundDiagnostic);
        if (rendererForDiagnostic != null) {
            String strRender = rendererForDiagnostic.render(unboundDiagnostic);
            if (strRender == null) {
                $$$reportNull$$$0(1);
            }
            return strRender;
        }
        return unboundDiagnostic.toString() + " (error: could not render message)";
    }

    public static /* synthetic */ String s(Errors.BadNamedArgumentsTarget badNamedArgumentsTarget, RenderingContext renderingContext) {
        int i = AnonymousClass1.$SwitchMap$org$jetbrains$kotlin$diagnostics$Errors$BadNamedArgumentsTarget[badNamedArgumentsTarget.ordinal()];
        if (i == 1) {
            return "non-Kotlin functions";
        }
        if (i == 2) {
            return "interop functions with ambiguous parameter names";
        }
        if (i == 3) {
            return "function types";
        }
        if (i == 4) {
            return "members of expected classes";
        }
        x01.a(badNamedArgumentsTarget);
        return null;
    }
}
