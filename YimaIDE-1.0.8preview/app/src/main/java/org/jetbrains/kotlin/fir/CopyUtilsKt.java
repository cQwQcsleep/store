package org.jetbrains.kotlin.fir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirImplicitInvokeCallBuilder;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirTypeAttributeExtension;
import org.jetbrains.kotlin.fir.extensions.FirTypeAttributeExtensionKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttribute;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.ParameterNameTypeAttribute;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a>\u0010\u000f\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0012\b\u0002\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018\u001a\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u0012H\u0002¢\u0006\u0002\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"}, d2 = {"copyAsImplicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "setupCopy", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirImplicitInvokeCallBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "resolvedTypeFromPrototype", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "fallbackSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "computeTypeAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "predefined", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "allowExtensionFunctionType", Argument.Delimiters.none, "shouldExpandTypeAliases", "tryExpandClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "extractContextParameterCount", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Ljava/lang/Integer;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CopyUtilsKt {
    public static ConeClassLikeType a(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        return FirDeclarationUtilKt.getExpandedConeType(firTypeAlias);
    }

    public static final ConeAttributes computeTypeAttributes(List<? extends FirAnnotation> list, FirSession firSession, List<? extends ConeAttribute<?>> list2, boolean z, boolean z2) {
        ClassId classId;
        ParameterNameTypeAttribute parameterNameTypeAttribute;
        ConeAttribute<?> coneAttributeExtractAttributeFromAnnotation;
        list.getClass();
        firSession.getClass();
        list2.getClass();
        if (list.isEmpty()) {
            return list2.isEmpty() ? ConeAttributes.INSTANCE.getEmpty() : ConeAttributes.INSTANCE.create(list2);
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, list2);
        ArrayList arrayList2 = new ArrayList();
        ParameterNameTypeAttribute parameterNameTypeAttribute2 = null;
        for (FirAnnotation firAnnotation : list) {
            if (z2) {
                classId = tryExpandClassId(firAnnotation, firSession);
            } else {
                if (z2) {
                    bu8.a();
                    return null;
                }
                classId = ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firAnnotation));
            }
            CompilerConeAttributes.Exact exact = CompilerConeAttributes.Exact.INSTANCE;
            if (Intrinsics.areEqual(classId, exact.getANNOTATION_CLASS_ID())) {
                arrayList.add(exact);
            } else {
                CompilerConeAttributes.NoInfer noInfer = CompilerConeAttributes.NoInfer.INSTANCE;
                if (Intrinsics.areEqual(classId, noInfer.getANNOTATION_CLASS_ID())) {
                    arrayList.add(noInfer);
                } else {
                    CompilerConeAttributes.ExtensionFunctionType extensionFunctionType = CompilerConeAttributes.ExtensionFunctionType.INSTANCE;
                    if (Intrinsics.areEqual(classId, extensionFunctionType.getANNOTATION_CLASS_ID())) {
                        if (z) {
                            arrayList.add(extensionFunctionType);
                        }
                    } else if (Intrinsics.areEqual(classId, CompilerConeAttributes.ContextFunctionTypeParams.Companion.getANNOTATION_CLASS_ID())) {
                        Integer numExtractContextParameterCount = extractContextParameterCount(firAnnotation);
                        arrayList.add(new CompilerConeAttributes.ContextFunctionTypeParams(numExtractContextParameterCount != null ? numExtractContextParameterCount.intValue() : 0));
                    } else if (Intrinsics.areEqual(classId, ParameterNameTypeAttribute.Companion.getANNOTATION_CLASS_ID())) {
                        if (parameterNameTypeAttribute2 == null) {
                            String stringArgument = FirAnnotationUtilsKt.getStringArgument(firAnnotation, StandardNames.NAME);
                            parameterNameTypeAttribute = new ParameterNameTypeAttribute(stringArgument != null ? Name.identifier(stringArgument) : null, CollectionsKt.listOf(firAnnotation));
                        } else {
                            parameterNameTypeAttribute = new ParameterNameTypeAttribute(parameterNameTypeAttribute2.getName(), CollectionsKt.plus(parameterNameTypeAttribute2.getAnnotations(), firAnnotation));
                        }
                        parameterNameTypeAttribute2 = parameterNameTypeAttribute;
                    } else {
                        CompilerConeAttributes.UnsafeVariance unsafeVariance = CompilerConeAttributes.UnsafeVariance.INSTANCE;
                        if (Intrinsics.areEqual(classId, unsafeVariance.getANNOTATION_CLASS_ID())) {
                            arrayList.add(unsafeVariance);
                        } else {
                            CompilerConeAttributes.EnhancedNullability enhancedNullability = CompilerConeAttributes.EnhancedNullability.INSTANCE;
                            if (Intrinsics.areEqual(classId, enhancedNullability.getANNOTATION_CLASS_ID())) {
                                arrayList.add(enhancedNullability);
                            } else {
                                Iterator<T> it = FirTypeAttributeExtensionKt.getTypeAttributeExtensions(FirExtensionServiceKt.getExtensionService(firSession)).iterator();
                                do {
                                    if (!it.hasNext()) {
                                        coneAttributeExtractAttributeFromAnnotation = null;
                                        break;
                                    }
                                    coneAttributeExtractAttributeFromAnnotation = ((FirTypeAttributeExtension) it.next()).extractAttributeFromAnnotation(firAnnotation);
                                } while (coneAttributeExtractAttributeFromAnnotation == null);
                                if (coneAttributeExtractAttributeFromAnnotation != null) {
                                    arrayList.add(coneAttributeExtractAttributeFromAnnotation);
                                } else {
                                    arrayList2.add(firAnnotation);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (parameterNameTypeAttribute2 != null) {
            arrayList.add(parameterNameTypeAttribute2);
        }
        if (!arrayList2.isEmpty()) {
            arrayList.add(new CustomAnnotationTypeAttribute(arrayList2));
        }
        return ConeAttributes.INSTANCE.create(arrayList);
    }

    public static /* synthetic */ ConeAttributes computeTypeAttributes$default(List list, FirSession firSession, List list2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return computeTypeAttributes(list, firSession, list2, z, z2);
    }

    public static final FirImplicitInvokeCall copyAsImplicitInvokeCall(FirFunctionCall firFunctionCall, Function1<? super FirImplicitInvokeCallBuilder, Unit> function1) {
        firFunctionCall.getClass();
        function1.getClass();
        FirImplicitInvokeCallBuilder firImplicitInvokeCallBuilder = new FirImplicitInvokeCallBuilder();
        firImplicitInvokeCallBuilder.setSource(firFunctionCall.getSource());
        firImplicitInvokeCallBuilder.getAnnotations().addAll(firFunctionCall.getAnnotations());
        firImplicitInvokeCallBuilder.getTypeArguments().addAll(firFunctionCall.getTypeArguments());
        firImplicitInvokeCallBuilder.setExplicitReceiver(firFunctionCall.getExplicitReceiver());
        firImplicitInvokeCallBuilder.setDispatchReceiver(firFunctionCall.getDispatchReceiver());
        firImplicitInvokeCallBuilder.setExtensionReceiver(firFunctionCall.getExtensionReceiver());
        firImplicitInvokeCallBuilder.setArgumentList(firFunctionCall.getArgumentList());
        firImplicitInvokeCallBuilder.setCalleeReference(firFunctionCall.getCalleeReference());
        function1.invoke(firImplicitInvokeCallBuilder);
        return firImplicitInvokeCallBuilder.mo288build();
    }

    private static final Integer extractContextParameterCount(FirAnnotation firAnnotation) {
        FirExpression firExpression = firAnnotation.getArgumentMapping().getMapping().get(StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME);
        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        if (value instanceof Integer) {
            return (Integer) value;
        }
        return null;
    }

    public static final FirResolvedTypeRef resolvedTypeFromPrototype(FirTypeRef firTypeRef, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement) {
        firTypeRef.getClass();
        coneKotlinType.getClass();
        if (!(firTypeRef instanceof FirResolvedTypeRef)) {
            KtSourceElement source = firTypeRef.getSource();
            if (source != null) {
                ktSourceElement = source;
            }
            return UtilsKt.toFirResolvedTypeRef(coneKotlinType, ktSourceElement, firTypeRef instanceof FirUserTypeRef ? (FirUserTypeRef) firTypeRef : null);
        }
        FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) firTypeRef;
        KtSourceElement source2 = firResolvedTypeRef.getSource();
        if (source2 != null) {
            ktSourceElement = source2;
        }
        return org.jetbrains.kotlin.fir.types.TypeUtilsKt.withReplacedSourceAndType(firResolvedTypeRef, ktSourceElement, coneKotlinType);
    }

    private static final ClassId tryExpandClassId(FirAnnotation firAnnotation, FirSession firSession) {
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        ConeClassLikeType coneClassLikeTypeDirectExpansionType = coneClassLikeType != null ? TypeExpansionUtilsKt.directExpansionType(coneClassLikeType, firSession, new Function1() { // from class: dy2
            public final Object invoke(Object obj) {
                return CopyUtilsKt.a((FirTypeAlias) obj);
            }
        }) : null;
        if (coneClassLikeTypeDirectExpansionType != null) {
            return ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeTypeDirectExpansionType, firSession, (Function1) null, 2, (Object) null));
        }
        FirResolvedTypeRef annotationTypeRef2 = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef2 = annotationTypeRef2 instanceof FirResolvedTypeRef ? annotationTypeRef2 : null;
        ConeKotlinType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
        ConeClassLikeType coneClassLikeType2 = coneType2 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType2 : null;
        if (coneClassLikeType2 != null) {
            return ConeTypeUtilsKt.getClassId(coneClassLikeType2);
        }
        return null;
    }
}
