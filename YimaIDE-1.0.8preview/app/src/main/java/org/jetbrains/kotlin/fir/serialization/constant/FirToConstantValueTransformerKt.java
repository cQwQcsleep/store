package org.jetbrains.kotlin.fir.serialization.constant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.AnnotationValue;
import org.jetbrains.kotlin.constant.ArrayValue;
import org.jetbrains.kotlin.constant.BooleanValue;
import org.jetbrains.kotlin.constant.ByteValue;
import org.jetbrains.kotlin.constant.CharValue;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.constant.DoubleValue;
import org.jetbrains.kotlin.constant.EnumValue;
import org.jetbrains.kotlin.constant.FloatValue;
import org.jetbrains.kotlin.constant.IntValue;
import org.jetbrains.kotlin.constant.LongValue;
import org.jetbrains.kotlin.constant.NullValue;
import org.jetbrains.kotlin.constant.ShortValue;
import org.jetbrains.kotlin.constant.StringValue;
import org.jetbrains.kotlin.constant.UByteValue;
import org.jetbrains.kotlin.constant.UIntValue;
import org.jetbrains.kotlin.constant.ULongValue;
import org.jetbrains.kotlin.constant.UShortValue;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirAnnotationArgumentMappingImplKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\u001a0\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u000e\b\u0000\u0010\u0001\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u00020\u0005H\u0080\bR\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006\u001a\u0014\u0010\u0007\u001a\u00020\b*\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n\u001a#\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u00020\fH\u0002R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011\u001a5\u0010\u0012\u001a\u00020\u000f*\u00020\u00102\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0014H\u0002R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0016\u001a<\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0014*\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\t\u001a\u00020\n\"\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"toConstantValue", "T", "Lorg/jetbrains/kotlin/constant/ConstantValue;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/constant/ConstantValue;", "hasConstantValue", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "toConstantValueImpl", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/FirElement;)Lorg/jetbrains/kotlin/constant/ConstantValue;", "evaluateToAnnotationValue", "Lorg/jetbrains/kotlin/constant/AnnotationValue;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Lorg/jetbrains/kotlin/constant/AnnotationValue;", "toAnnotationValue", "mapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Ljava/util/Map;)Lorg/jetbrains/kotlin/constant/AnnotationValue;", "fillEmptyArray", "annotationConstructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "constantIntrinsicCalls", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-serialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirToConstantValueTransformerKt {
    private static final Set<Name> constantIntrinsicCalls = SetsKt.plus(OperatorNameConventions.NUMBER_CONVERSIONS, OperatorNameConventions.UNARY_MINUS);

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotationValue evaluateToAnnotationValue(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirAnnotation firAnnotation) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Map.Entry<Name, FirExpression> entry : firAnnotation.getArgumentMapping().getMapping().entrySet()) {
            Name key = entry.getKey();
            ConstantValue<?> constantValueImpl = toConstantValueImpl(sessionAndScopeSessionHolder, entry.getValue());
            if (constantValueImpl != null) {
                mapCreateMapBuilder.put(key, constantValueImpl);
            }
        }
        return toAnnotationValue(sessionAndScopeSessionHolder, firAnnotation, MapsKt.build(mapCreateMapBuilder));
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0077  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Map<Name, ConstantValue<?>> fillEmptyArray(Map<Name, ? extends ConstantValue<?>> map, FirConstructorSymbol firConstructorSymbol, FirSession firSession) {
        FirValueParameterSymbol firValueParameterSymbol;
        List<FirValueParameterSymbol> valueParameterSymbols;
        Object next;
        map.getClass();
        firSession.getClass();
        if (firConstructorSymbol == null) {
            return map;
        }
        FirFunctionSymbol<?> singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull((FirFunctionSymbol<?>) firConstructorSymbol);
        List<FirValueParameterSymbol> valueParameterSymbols2 = firConstructorSymbol.getValueParameterSymbols();
        ArrayList arrayList = new ArrayList();
        for (FirValueParameterSymbol firValueParameterSymbol2 : valueParameterSymbols2) {
            Pair pair = null;
            if (map.get(firValueParameterSymbol2.getName()) == null && ConeBuiltinTypeUtilsKt.isArrayType(TypeExpansionUtilsKt.fullyExpandedType$default(firValueParameterSymbol2.getResolvedReturnTypeRef().getConeType(), firSession, (Function1) null, 2, (Object) null))) {
                if (singleMatchedExpectForActualOrNull == null || (valueParameterSymbols = singleMatchedExpectForActualOrNull.getValueParameterSymbols()) == null) {
                    firValueParameterSymbol = firValueParameterSymbol2;
                } else {
                    Iterator<T> it = valueParameterSymbols.iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual(((FirValueParameterSymbol) next).getName(), firValueParameterSymbol2.getName()));
                    firValueParameterSymbol = (FirValueParameterSymbol) next;
                    if (firValueParameterSymbol == null) {
                        firValueParameterSymbol = firValueParameterSymbol2;
                    }
                }
                if (!firValueParameterSymbol.getHasDefaultValue()) {
                    pair = TuplesKt.to(firValueParameterSymbol2.getName(), new ArrayValue(CollectionsKt.emptyList()));
                }
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt.plus(map, arrayList);
    }

    public static final boolean hasConstantValue(FirExpression firExpression, FirSession firSession) {
        firSession.getClass();
        return firExpression != null && ((Boolean) firExpression.accept(FirToConstantValueChecker.INSTANCE, firSession)).booleanValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final AnnotationValue toAnnotationValue(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirAnnotation firAnnotation, Map<Name, ? extends ConstantValue<?>> map) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<FirConstructorSymbol> declaredConstructors;
        ClassId classId = ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType(sessionAndScopeSessionHolder, FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef())));
        FirConstructorSymbol firConstructorSymbol = null;
        if (classId != null) {
            FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(sessionAndScopeSessionHolder, FirTypeUtilsKt.getResolvedType(firAnnotation), CallableCopyTypeCalculator.CalculateDeferredForceLazyResolution.INSTANCE, FirResolvePhase.TYPES);
            if (firTypeScopeScope != null && (declaredConstructors = FirScopeKt.getDeclaredConstructors(firTypeScopeScope)) != null) {
                firConstructorSymbol = (FirConstructorSymbol) CollectionsKt.firstOrNull(declaredConstructors);
            }
            return AnnotationValue.INSTANCE.create(classId, fillEmptyArray(map, firConstructorSymbol, sessionAndScopeSessionHolder.getSession()));
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Annotation without proper lookup tag }", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "annotation", firAnnotation);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final /* synthetic */ <T extends ConstantValue<?>> T toConstantValue(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirExpression firExpression) {
        sessionAndScopeSessionHolder.getClass();
        firExpression.getClass();
        AnnotationValue annotationValueEvaluateToAnnotationValue = firExpression instanceof FirAnnotation ? evaluateToAnnotationValue(sessionAndScopeSessionHolder, (FirAnnotation) firExpression) : (T) toConstantValueImpl(sessionAndScopeSessionHolder, firExpression);
        Intrinsics.reifiedOperationMarker(2, "T");
        return annotationValueEvaluateToAnnotationValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConstantValue<?> toConstantValueImpl(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirElement firElement) {
        List<FirExpression> arguments;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirRegularClassSymbol regularClassSymbol;
        long jLongValue;
        int iIntValue;
        short sShortValue;
        byte bByteValue;
        if (firElement instanceof FirLiteralExpression) {
            FirLiteralExpression firLiteralExpression = (FirLiteralExpression) firElement;
            Object value = firLiteralExpression.getValue();
            ConstantValueKind kind = firLiteralExpression.getKind();
            if (Intrinsics.areEqual(kind, ConstantValueKind.Boolean.INSTANCE)) {
                value.getClass();
                return new BooleanValue(((Boolean) value).booleanValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Char.INSTANCE)) {
                value.getClass();
                return new CharValue(((Character) value).charValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Byte.INSTANCE)) {
                value.getClass();
                return new ByteValue(((Number) value).byteValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.UnsignedByte.INSTANCE)) {
                Number number = value instanceof Number ? (Number) value : null;
                if (number != null) {
                    bByteValue = number.byteValue();
                } else {
                    value.getClass();
                    bByteValue = ((UByte) value).unbox-impl();
                }
                return new UByteValue(bByteValue);
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Short.INSTANCE)) {
                value.getClass();
                return new ShortValue(((Number) value).shortValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.UnsignedShort.INSTANCE)) {
                Number number2 = value instanceof Number ? (Number) value : null;
                if (number2 != null) {
                    sShortValue = number2.shortValue();
                } else {
                    value.getClass();
                    sShortValue = ((UShort) value).unbox-impl();
                }
                return new UShortValue(sShortValue);
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Int.INSTANCE)) {
                value.getClass();
                return new IntValue(((Number) value).intValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.UnsignedInt.INSTANCE)) {
                Number number3 = value instanceof Number ? (Number) value : null;
                if (number3 != null) {
                    iIntValue = number3.intValue();
                } else {
                    value.getClass();
                    iIntValue = ((UInt) value).unbox-impl();
                }
                return new UIntValue(iIntValue);
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Long.INSTANCE)) {
                value.getClass();
                return new LongValue(((Number) value).longValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.UnsignedLong.INSTANCE)) {
                Number number4 = value instanceof Number ? (Number) value : null;
                if (number4 != null) {
                    jLongValue = number4.longValue();
                } else {
                    value.getClass();
                    jLongValue = ((ULong) value).unbox-impl();
                }
                return new ULongValue(jLongValue);
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.String.INSTANCE)) {
                value.getClass();
                return new StringValue((String) value);
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Float.INSTANCE)) {
                value.getClass();
                return new FloatValue(((Number) value).floatValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Double.INSTANCE)) {
                value.getClass();
                return new DoubleValue(((Number) value).doubleValue());
            }
            if (Intrinsics.areEqual(kind, ConstantValueKind.Null.INSTANCE)) {
                return NullValue.INSTANCE;
            }
            return null;
        }
        if (firElement instanceof FirQualifiedAccessExpression) {
            FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol((FirResolvable) firElement);
            if (resolvedCallableSymbol instanceof FirEnumEntrySymbol) {
                FirEnumEntrySymbol firEnumEntrySymbol = (FirEnumEntrySymbol) resolvedCallableSymbol;
                ClassId classId = firEnumEntrySymbol.getCallableId().getClassId();
                if (classId == null) {
                    return null;
                }
                return new EnumValue(classId, firEnumEntrySymbol.getName());
            }
            if (!(resolvedCallableSymbol instanceof FirConstructorSymbol) || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(resolvedCallableSymbol)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) sessionAndScopeSessionHolder, coneClassLikeLookupTagContainingClassLookupTag)) == null || regularClassSymbol.getClassKind() != ClassKind.ANNOTATION_CLASS) {
                return null;
            }
            FirArgumentList argumentList = ((FirFunctionCall) firElement).getArgumentList();
            argumentList.getClass();
            Map<Name, FirExpression> mapping = FirAnnotationArgumentMappingImplKt.toAnnotationArgumentMapping((FirResolvedArgumentList) argumentList).getMapping();
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapping.size()));
            Iterator<T> it = mapping.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                ConstantValue<?> constantValueImpl = toConstantValueImpl(sessionAndScopeSessionHolder, (FirElement) entry.getValue());
                if (constantValueImpl == null) {
                    return null;
                }
                linkedHashMap.put(key, constantValueImpl);
            }
            return AnnotationValue.INSTANCE.create(regularClassSymbol.getClassId(), fillEmptyArray(linkedHashMap, (FirConstructorSymbol) resolvedCallableSymbol, sessionAndScopeSessionHolder.getSession()));
        }
        if (firElement instanceof FirAnnotation) {
            FirAnnotation firAnnotation = (FirAnnotation) firElement;
            Map<Name, FirExpression> mapping2 = firAnnotation.getArgumentMapping().getMapping();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(mapping2.size()));
            Iterator<T> it2 = mapping2.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it2.next();
                Object key2 = entry2.getKey();
                ConstantValue<?> constantValueImpl2 = toConstantValueImpl(sessionAndScopeSessionHolder, (FirElement) entry2.getValue());
                if (constantValueImpl2 == null) {
                    return null;
                }
                linkedHashMap2.put(key2, constantValueImpl2);
            }
            return toAnnotationValue(sessionAndScopeSessionHolder, firAnnotation, linkedHashMap2);
        }
        if (firElement instanceof FirGetClassCall) {
            return ConstantValueUtilsKt.create(FirTypeUtilsKt.getResolvedType(((FirGetClassCall) firElement).getArgument()), sessionAndScopeSessionHolder.getSession());
        }
        if (firElement instanceof FirEnumEntryDeserializedAccessExpression) {
            FirEnumEntryDeserializedAccessExpression firEnumEntryDeserializedAccessExpression = (FirEnumEntryDeserializedAccessExpression) firElement;
            return new EnumValue(firEnumEntryDeserializedAccessExpression.getEnumClassId(), firEnumEntryDeserializedAccessExpression.getEnumEntryName());
        }
        if (firElement instanceof FirCollectionLiteral) {
            List<FirExpression> arguments2 = ((FirCollectionLiteral) firElement).getArgumentList().getArguments();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it3 = arguments2.iterator();
            while (it3.hasNext()) {
                ConstantValue<?> constantValueImpl3 = toConstantValueImpl(sessionAndScopeSessionHolder, (FirExpression) it3.next());
                if (constantValueImpl3 != null) {
                    arrayList.add(constantValueImpl3);
                }
            }
            return new ArrayValue(arrayList);
        }
        if (!(firElement instanceof FirVarargArgumentsExpression)) {
            return null;
        }
        List<FirExpression> arguments3 = ((FirVarargArgumentsExpression) firElement).getArguments();
        FirExpression firExpression = (FirExpression) CollectionsKt.singleOrNull(arguments3);
        FirExpression firExpressionUnwrapArgument = firExpression != null ? FirExpressionUtilKt.unwrapArgument(firExpression) : null;
        FirCollectionLiteral firCollectionLiteral = firExpressionUnwrapArgument instanceof FirCollectionLiteral ? (FirCollectionLiteral) firExpressionUnwrapArgument : null;
        if (firCollectionLiteral != null && (arguments = firCollectionLiteral.getArgumentList().getArguments()) != null) {
            arguments3 = arguments;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it4 = arguments3.iterator();
        while (it4.hasNext()) {
            ConstantValue<?> constantValueImpl4 = toConstantValueImpl(sessionAndScopeSessionHolder, (FirExpression) it4.next());
            if (constantValueImpl4 != null) {
                arrayList2.add(constantValueImpl4);
            }
        }
        return new ArrayValue(arrayList2);
    }
}
