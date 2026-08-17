package org.jetbrains.kotlin.fir.resolve.transformers.mpp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirAnnotationArgumentMappingImplKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.resolve.transformers.mpp.AnnotationCompareUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualCollectionArgumentsCompatibilityCheckStrategy;
import org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualMatchingContext;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a$\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a(\u0010\f\u001a\u00020\u0001*\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\u0010"}, d2 = {"areFirAnnotationsEqual", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "annotation1", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "annotation2", "collectionArgumentsCompatibilityCheckStrategy", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy;", "mappingsAreEqual", "argumentMapping1", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "argumentMapping2", "areAnnotationArgumentsEqual", "expression1", "Lorg/jetbrains/kotlin/fir/FirElement;", "expression2", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationCompareUtilsKt {
    public static boolean a(FirExpectActualMatchingContext firExpectActualMatchingContext, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, FirExpression firExpression, FirExpression firExpression2) {
        firExpression.getClass();
        firExpression2.getClass();
        return areAnnotationArgumentsEqual(firExpectActualMatchingContext, firExpression, firExpression2, expectActualCollectionArgumentsCompatibilityCheckStrategy);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean areAnnotationArgumentsEqual(FirExpectActualMatchingContext firExpectActualMatchingContext, FirElement firElement, FirElement firElement2, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (firElement == null || firElement2 == null) {
            return (firElement == null) == (firElement2 == null);
        }
        if ((firElement instanceof FirLiteralExpression) && (firElement2 instanceof FirLiteralExpression)) {
            return Intrinsics.areEqual(((FirLiteralExpression) firElement).getValue(), ((FirLiteralExpression) firElement2).getValue());
        }
        if ((firElement instanceof FirGetClassCall) && (firElement2 instanceof FirGetClassCall)) {
            return ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType((FirExpression) firElement), FirTypeUtilsKt.getResolvedType((FirExpression) firElement2), false, false, 12, (Object) null);
        }
        if (firElement instanceof FirQualifiedAccessExpression) {
            return areAnnotationArgumentsEqual$isEqualTo$3((FirQualifiedAccessExpression) firElement, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firElement2);
        }
        if (firElement2 instanceof FirQualifiedAccessExpression) {
            return areAnnotationArgumentsEqual$isEqualTo$3((FirQualifiedAccessExpression) firElement2, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firElement);
        }
        if (firElement instanceof FirAnnotation) {
            return areAnnotationArgumentsEqual$isEqualTo$5((FirAnnotation) firElement, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firElement2);
        }
        if (firElement2 instanceof FirAnnotation) {
            return areAnnotationArgumentsEqual$isEqualTo$5((FirAnnotation) firElement2, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firElement);
        }
        if (firElement instanceof FirEnumEntryDeserializedAccessExpression) {
            return areAnnotationArgumentsEqual$isEqualTo$4((FirEnumEntryDeserializedAccessExpression) firElement, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firElement2);
        }
        if (firElement2 instanceof FirEnumEntryDeserializedAccessExpression) {
            return areAnnotationArgumentsEqual$isEqualTo$4((FirEnumEntryDeserializedAccessExpression) firElement2, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firElement);
        }
        if (firElement instanceof FirCollectionLiteral) {
            return areAnnotationArgumentsEqual$isEqualTo$2((FirCollectionLiteral) firElement, expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firElement2);
        }
        if (firElement2 instanceof FirCollectionLiteral) {
            return areAnnotationArgumentsEqual$isEqualTo$2((FirCollectionLiteral) firElement2, expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firElement);
        }
        if (firElement instanceof FirVarargArgumentsExpression) {
            return areAnnotationArgumentsEqual$isEqualTo((FirVarargArgumentsExpression) firElement, expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firElement2);
        }
        if (firElement2 instanceof FirVarargArgumentsExpression) {
            return areAnnotationArgumentsEqual$isEqualTo((FirVarargArgumentsExpression) firElement2, expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firElement);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Not handled expression types", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "expression1", firElement);
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "expression2", firElement2);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private static final boolean areAnnotationArgumentsEqual$argumentsOfArrayAreEqual(final ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, final FirExpectActualMatchingContext firExpectActualMatchingContext, List<? extends FirExpression> list, List<? extends FirExpression> list2) {
        return expectActualCollectionArgumentsCompatibilityCheckStrategy.areCompatible(areAnnotationArgumentsEqual$unwrapSpreadOperator(list), areAnnotationArgumentsEqual$unwrapSpreadOperator(list2), new Function2() { // from class: p80
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(AnnotationCompareUtilsKt.a(firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, (FirExpression) obj, (FirExpression) obj2));
            }
        });
    }

    private static final boolean areAnnotationArgumentsEqual$isEqualTo(FirVarargArgumentsExpression firVarargArgumentsExpression, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, FirExpectActualMatchingContext firExpectActualMatchingContext, FirElement firElement) {
        if (firElement instanceof FirVarargArgumentsExpression) {
            return areAnnotationArgumentsEqual$argumentsOfArrayAreEqual(expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firVarargArgumentsExpression.getArguments(), ((FirVarargArgumentsExpression) firElement).getArguments());
        }
        if (firElement instanceof FirCollectionLiteral) {
            return areAnnotationArgumentsEqual$argumentsOfArrayAreEqual(expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firVarargArgumentsExpression.getArguments(), ((FirCall) firElement).getArgumentList().getArguments());
        }
        return false;
    }

    private static final boolean areAnnotationArgumentsEqual$isEqualTo$2(FirCollectionLiteral firCollectionLiteral, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, FirExpectActualMatchingContext firExpectActualMatchingContext, FirElement firElement) {
        if (firElement instanceof FirVarargArgumentsExpression) {
            return areAnnotationArgumentsEqual$isEqualTo((FirVarargArgumentsExpression) firElement, expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firCollectionLiteral);
        }
        if (firElement instanceof FirCollectionLiteral) {
            return areAnnotationArgumentsEqual$argumentsOfArrayAreEqual(expectActualCollectionArgumentsCompatibilityCheckStrategy, firExpectActualMatchingContext, firCollectionLiteral.getArgumentList().getArguments(), ((FirCall) firElement).getArgumentList().getArguments());
        }
        return false;
    }

    private static final boolean areAnnotationArgumentsEqual$isEqualTo$3(FirQualifiedAccessExpression firQualifiedAccessExpression, FirExpectActualMatchingContext firExpectActualMatchingContext, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, FirElement firElement) {
        if (!(firElement instanceof FirQualifiedAccessExpression)) {
            if (firElement instanceof FirEnumEntryDeserializedAccessExpression) {
                FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
                return (resolvedCallableSymbol instanceof FirEnumEntrySymbol) && ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression), FirTypeUtilsKt.getResolvedType((FirExpression) firElement), false, false, 12, (Object) null) && Intrinsics.areEqual(((FirEnumEntrySymbol) resolvedCallableSymbol).getName(), ((FirEnumEntryDeserializedAccessExpression) firElement).getEnumEntryName());
            }
            if (!(firElement instanceof FirAnnotation) || !(ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression) instanceof FirConstructorSymbol)) {
                return false;
            }
            firQualifiedAccessExpression.getClass();
            FirArgumentList argumentList = ((FirFunctionCall) firQualifiedAccessExpression).getArgumentList();
            argumentList.getClass();
            return ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression), FirTypeUtilsKt.getResolvedType((FirExpression) firElement), false, false, 12, (Object) null) && mappingsAreEqual(firExpectActualMatchingContext, FirAnnotationArgumentMappingImplKt.toAnnotationArgumentMapping((FirResolvedArgumentList) argumentList), ((FirAnnotation) firElement).getArgumentMapping(), expectActualCollectionArgumentsCompatibilityCheckStrategy);
        }
        FirCallableSymbol<?> resolvedCallableSymbol2 = ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
        FirCallableSymbol<?> resolvedCallableSymbol3 = ReferenceUtilsKt.toResolvedCallableSymbol((FirResolvable) firElement);
        if ((resolvedCallableSymbol2 instanceof FirEnumEntrySymbol) && (resolvedCallableSymbol3 instanceof FirEnumEntrySymbol)) {
            return ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression), FirTypeUtilsKt.getResolvedType((FirExpression) firElement), false, false, 12, (Object) null) && Intrinsics.areEqual(((FirEnumEntrySymbol) resolvedCallableSymbol2).getName(), ((FirEnumEntrySymbol) resolvedCallableSymbol3).getName());
        }
        if ((resolvedCallableSymbol2 instanceof FirConstructorSymbol) && (resolvedCallableSymbol3 instanceof FirConstructorSymbol)) {
            firQualifiedAccessExpression.getClass();
            FirArgumentList argumentList2 = ((FirFunctionCall) firQualifiedAccessExpression).getArgumentList();
            argumentList2.getClass();
            FirAnnotationArgumentMapping annotationArgumentMapping = FirAnnotationArgumentMappingImplKt.toAnnotationArgumentMapping((FirResolvedArgumentList) argumentList2);
            FirArgumentList argumentList3 = ((FirFunctionCall) firElement).getArgumentList();
            argumentList3.getClass();
            FirAnnotationArgumentMapping annotationArgumentMapping2 = FirAnnotationArgumentMappingImplKt.toAnnotationArgumentMapping((FirResolvedArgumentList) argumentList3);
            if (ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression), FirTypeUtilsKt.getResolvedType((FirExpression) firElement), false, false, 12, (Object) null) && mappingsAreEqual(firExpectActualMatchingContext, annotationArgumentMapping, annotationArgumentMapping2, expectActualCollectionArgumentsCompatibilityCheckStrategy)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean areAnnotationArgumentsEqual$isEqualTo$4(FirEnumEntryDeserializedAccessExpression firEnumEntryDeserializedAccessExpression, FirExpectActualMatchingContext firExpectActualMatchingContext, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, FirElement firElement) {
        if (firElement instanceof FirQualifiedAccessExpression) {
            return areAnnotationArgumentsEqual$isEqualTo$3((FirQualifiedAccessExpression) firElement, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firEnumEntryDeserializedAccessExpression);
        }
        return (firElement instanceof FirEnumEntryDeserializedAccessExpression) && ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firEnumEntryDeserializedAccessExpression), FirTypeUtilsKt.getResolvedType((FirExpression) firElement), false, false, 12, (Object) null) && Intrinsics.areEqual(firEnumEntryDeserializedAccessExpression.getEnumEntryName(), ((FirEnumEntryDeserializedAccessExpression) firElement).getEnumEntryName());
    }

    private static final boolean areAnnotationArgumentsEqual$isEqualTo$5(FirAnnotation firAnnotation, FirExpectActualMatchingContext firExpectActualMatchingContext, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy, FirElement firElement) {
        if (firElement instanceof FirQualifiedAccessExpression) {
            return areAnnotationArgumentsEqual$isEqualTo$3((FirQualifiedAccessExpression) firElement, firExpectActualMatchingContext, expectActualCollectionArgumentsCompatibilityCheckStrategy, firAnnotation);
        }
        return (firElement instanceof FirAnnotation) && ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firAnnotation), FirTypeUtilsKt.getResolvedType((FirExpression) firElement), false, false, 12, (Object) null) && mappingsAreEqual(firExpectActualMatchingContext, firAnnotation.getArgumentMapping(), ((FirAnnotation) firElement).getArgumentMapping(), expectActualCollectionArgumentsCompatibilityCheckStrategy);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0038  */
    private static final List<FirExpression> areAnnotationArgumentsEqual$unwrapSpreadOperator(List<? extends FirExpression> list) {
        List<FirExpression> listListOf;
        ArrayList arrayList = new ArrayList();
        for (FirExpression firExpression : list) {
            if (firExpression instanceof FirSpreadArgumentExpression) {
                FirSpreadArgumentExpression firSpreadArgumentExpression = (FirSpreadArgumentExpression) firExpression;
                if (firSpreadArgumentExpression.getExpression() instanceof FirCollectionLiteral) {
                    FirExpression expression = firSpreadArgumentExpression.getExpression();
                    expression.getClass();
                    listListOf = ((FirCollectionLiteral) expression).getArgumentList().getArguments();
                } else {
                    listListOf = CollectionsKt.listOf(firExpression);
                }
            } else {
                listListOf = CollectionsKt.listOf(firExpression);
            }
            CollectionsKt.addAll(arrayList, listListOf);
        }
        return arrayList;
    }

    public static final boolean areFirAnnotationsEqual(FirExpectActualMatchingContext firExpectActualMatchingContext, FirAnnotation firAnnotation, FirAnnotation firAnnotation2, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy) {
        firExpectActualMatchingContext.getClass();
        firAnnotation.getClass();
        firAnnotation2.getClass();
        expectActualCollectionArgumentsCompatibilityCheckStrategy.getClass();
        if (!areFirAnnotationsEqual$hasResolvedArguments(firAnnotation) || !areFirAnnotationsEqual$hasResolvedArguments(firAnnotation2)) {
            k2d.a("By this time compared annotations are expected to have resolved arguments");
            return false;
        }
        if (!ExpectActualMatchingContext.areCompatibleExpectActualTypes$default(firExpectActualMatchingContext, FirTypeUtilsKt.getResolvedType(firAnnotation), FirTypeUtilsKt.getResolvedType(firAnnotation2), false, false, 8, (Object) null)) {
            return false;
        }
        Map<Name, FirExpression> mapping = firAnnotation.getArgumentMapping().getMapping();
        Map<Name, FirExpression> mapping2 = firAnnotation2.getArgumentMapping().getMapping();
        if (mapping.size() != mapping2.size()) {
            return false;
        }
        if (mapping.isEmpty()) {
            return true;
        }
        for (Map.Entry<Name, FirExpression> entry : mapping.entrySet()) {
            if (!areAnnotationArgumentsEqual(firExpectActualMatchingContext, entry.getValue(), mapping2.get(entry.getKey()), expectActualCollectionArgumentsCompatibilityCheckStrategy)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean areFirAnnotationsEqual$hasResolvedArguments(FirAnnotation firAnnotation) {
        if (FirAnnotationUtilsKt.getResolved(firAnnotation)) {
            return true;
        }
        return (firAnnotation instanceof FirAnnotationCall) && ((FirCall) firAnnotation).getArgumentList().getArguments().isEmpty();
    }

    private static final boolean mappingsAreEqual(FirExpectActualMatchingContext firExpectActualMatchingContext, FirAnnotationArgumentMapping firAnnotationArgumentMapping, FirAnnotationArgumentMapping firAnnotationArgumentMapping2, ExpectActualCollectionArgumentsCompatibilityCheckStrategy expectActualCollectionArgumentsCompatibilityCheckStrategy) {
        Set<Name> setKeySet = firAnnotationArgumentMapping.getMapping().keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            return true;
        }
        for (Name name : setKeySet) {
            if (!areAnnotationArgumentsEqual(firExpectActualMatchingContext, firAnnotationArgumentMapping.getMapping().get(name), firAnnotationArgumentMapping2.getMapping().get(name), expectActualCollectionArgumentsCompatibilityCheckStrategy)) {
                return false;
            }
        }
        return true;
    }
}
