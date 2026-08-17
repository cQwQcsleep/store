package org.jetbrains.kotlin.codegen.state;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.TypeMappingUtil;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.load.kotlin.TypeMappingMode;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.constants.ConstantValue;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.UtilsKt;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.checker.ClassicTypeSystemContextKt;
import org.jetbrains.kotlin.types.checker.SimpleClassicTypeSystemContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u000e\u001a\u00020\f*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a,\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a3\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001¢\u0006\u0002\u0010\u001b\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0001*\u00020\u001dH\u0002¢\u0006\u0002\u0010\u001e\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0001*\u00020\u001fH\u0002¢\u0006\u0002\u0010 \"\u0017\u0010\b\u001a\u00020\u0001*\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\n\"\u0017\u0010\u000b\u001a\u00020\u0001*\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r\"\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"isMostPreciseContravariantArgument", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "isMostPreciseCovariantArgument", "canHaveSubtypesIgnoringNullability", "kotlinType", "isMethodWithDeclarationSiteWildcards", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Z", "isMethodWithDeclarationSiteWildcardsFqName", "Lorg/jetbrains/kotlin/name/FqName;", "(Lorg/jetbrains/kotlin/name/FqName;)Z", "child", ModuleXmlParser.NAME, Argument.Delimiters.none, "METHODS_WITH_DECLARATION_SITE_WILDCARDS", Argument.Delimiters.none, "extractTypeMappingModeFromAnnotation", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "callableDescriptor", "outerType", "Lorg/jetbrains/kotlin/types/KotlinType;", "isForAnnotationParameter", "mapTypeAliases", "callableSuppressWildcardsMode", "(Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;Ljava/lang/Boolean;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;ZZ)Lorg/jetbrains/kotlin/load/kotlin/TypeMappingMode;", "suppressWildcardsMode", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)Ljava/lang/Boolean;", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeMappingUtil {
    private static final Set<FqName> METHODS_WITH_DECLARATION_SITE_WILDCARDS = SetsKt.setOf(new FqName[]{child(StandardNames.FqNames.mutableCollection, "addAll"), child(StandardNames.FqNames.mutableList, "addAll"), child(StandardNames.FqNames.mutableMap, "putAll")});

    public static AnnotationDescriptor a(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return declarationDescriptor.getAnnotations().mo108findAnnotation(JvmStandardClassIds.INSTANCE.getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME());
    }

    public static boolean b(CallableMemberDescriptor callableMemberDescriptor) {
        callableMemberDescriptor.getClass();
        return isMethodWithDeclarationSiteWildcardsFqName(DescriptorUtilsKt.fqNameOrNull(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor)));
    }

    private static final boolean canHaveSubtypesIgnoringNullability(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker);
        if (!typeSystemCommonBackendContext.isClassTypeConstructor(typeConstructorMarkerTypeConstructor) || !typeSystemCommonBackendContext.isFinalClassOrEnumEntryOrAnnotationClassConstructor(typeConstructorMarkerTypeConstructor)) {
            return true;
        }
        int iParametersCount = typeSystemCommonBackendContext.parametersCount(typeConstructorMarkerTypeConstructor);
        for (int i = 0; i < iParametersCount; i++) {
            TypeParameterMarker parameter = typeSystemCommonBackendContext.getParameter(typeConstructorMarkerTypeConstructor, i);
            TypeArgumentMarker argument = typeSystemCommonBackendContext.getArgument(kotlinTypeMarker, i);
            KotlinTypeMarker type = typeSystemCommonBackendContext.getType(argument);
            if (type == null) {
                return true;
            }
            Variance effectiveVariance = UtilsKt.getEffectiveVariance(ClassicTypeSystemContextKt.convertVariance(typeSystemCommonBackendContext.getVariance(parameter)), ClassicTypeSystemContextKt.convertVariance(typeSystemCommonBackendContext.getVariance(argument)));
            if (effectiveVariance == Variance.OUT_VARIANCE && !isMostPreciseCovariantArgument(typeSystemCommonBackendContext, type)) {
                return true;
            }
            if (effectiveVariance == Variance.IN_VARIANCE && !isMostPreciseContravariantArgument(typeSystemCommonBackendContext, type)) {
                return true;
            }
        }
        return false;
    }

    private static final FqName child(FqName fqName, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return fqName.child(nameIdentifier);
    }

    public static final TypeMappingMode extractTypeMappingModeFromAnnotation(TypeSystemCommonBackendContext typeSystemCommonBackendContext, Boolean bool, KotlinTypeMarker kotlinTypeMarker, boolean z, boolean z2) {
        boolean zBooleanValue;
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        Boolean boolSuppressWildcardsMode = org.jetbrains.kotlin.types.TypeMappingUtil.suppressWildcardsMode(kotlinTypeMarker, typeSystemCommonBackendContext);
        if (boolSuppressWildcardsMode != null) {
            zBooleanValue = boolSuppressWildcardsMode.booleanValue();
        } else {
            if (bool == null) {
                return null;
            }
            zBooleanValue = bool.booleanValue();
        }
        return typeSystemCommonBackendContext.argumentsCount(kotlinTypeMarker) == 0 ? TypeMappingMode.DEFAULT : TypeMappingMode.Companion.createWithConstantDeclarationSiteWildcardsMode$default(TypeMappingMode.Companion, zBooleanValue, z, !typeSystemCommonBackendContext.isInlineClass(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker)), z2, false, (TypeMappingMode) null, 48, (Object) null);
    }

    public static final boolean isMethodWithDeclarationSiteWildcards(CallableDescriptor callableDescriptor) {
        if (!(callableDescriptor instanceof CallableMemberDescriptor)) {
            return false;
        }
        CallableMemberDescriptor original = ((CallableMemberDescriptor) callableDescriptor).getOriginal();
        original.getClass();
        return DescriptorUtilsKt.firstOverridden(original, true, new Function1() { // from class: eve
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TypeMappingUtil.b((CallableMemberDescriptor) obj));
            }
        }) != null;
    }

    public static final boolean isMethodWithDeclarationSiteWildcardsFqName(FqName fqName) {
        return CollectionsKt.contains(METHODS_WITH_DECLARATION_SITE_WILDCARDS, fqName);
    }

    public static final boolean isMostPreciseContravariantArgument(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        return typeSystemCommonBackendContext.isAnyConstructor(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
    }

    public static final boolean isMostPreciseCovariantArgument(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        return !canHaveSubtypesIgnoringNullability(typeSystemCommonBackendContext, kotlinTypeMarker);
    }

    private static final Boolean suppressWildcardsMode(AnnotationDescriptor annotationDescriptor) {
        ConstantValue constantValue = (ConstantValue) CollectionsKt.firstOrNull(annotationDescriptor.getAllValueArguments().values());
        Object value = constantValue != null ? constantValue.getValue() : null;
        Boolean bool = value instanceof Boolean ? (Boolean) value : null;
        return Boolean.valueOf(bool != null ? bool.booleanValue() : true);
    }

    private static final Boolean suppressWildcardsMode(DeclarationDescriptor declarationDescriptor) {
        AnnotationDescriptor annotationDescriptor = (AnnotationDescriptor) SequencesKt.firstOrNull(SequencesKt.mapNotNull(DescriptorUtilsKt.getParentsWithSelf(declarationDescriptor), new Function1() { // from class: dve
            public final Object invoke(Object obj) {
                return TypeMappingUtil.a((DeclarationDescriptor) obj);
            }
        }));
        if (annotationDescriptor != null) {
            return suppressWildcardsMode(annotationDescriptor);
        }
        return null;
    }

    public static final TypeMappingMode extractTypeMappingModeFromAnnotation(CallableDescriptor callableDescriptor, KotlinType kotlinType, boolean z, boolean z2) {
        kotlinType.getClass();
        return extractTypeMappingModeFromAnnotation(SimpleClassicTypeSystemContext.INSTANCE, callableDescriptor != null ? suppressWildcardsMode(callableDescriptor) : null, kotlinType, z, z2);
    }
}
