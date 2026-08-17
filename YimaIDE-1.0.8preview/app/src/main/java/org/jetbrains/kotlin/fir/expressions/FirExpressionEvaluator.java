package org.jetbrains.kotlin.fir.expressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirNamedArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSpreadArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVarargArgumentsExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u00010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010J$\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010Jh\u0010\u0014\u001a\u0004\u0018\u00010\n\"\b\b\u0000\u0010\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u0002H\u00152\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\u0002\b\u001c2\u0019\u0010\u001d\u001a\u0015\u0012\u0004\u0012\u0002H\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0019¢\u0006\u0002\b\u001cH\u0082\b¢\u0006\u0002\u0010\u001fJ.\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\n0!2\u0006\u0010#\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u001e\u0010%\u001a\u0004\u0018\u00010\n2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000eH\u0007b\u0002\b'J\u0016\u0010(\u001a\u00020\u001b*\u0004\u0018\u00010\u001e2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010)\u001a\u00020\n*\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J*\u0010*\u001a\u00020\n*\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J*\u0010+\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0015*\u0006\u0012\u0002\b\u00030\u00072\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00150-H\u0082\b¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u001b*\u0006\u0012\u0002\b\u00030\u0007H\u0002R,\u0010\u0004\u001a \u0012\u001c\u0012\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007`\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirExpressionEvaluator;", Argument.Delimiters.none, "<init>", "()V", "visitedCallables", "Ljava/lang/ThreadLocal;", "Ljava/util/HashSet;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lkotlin/collections/HashSet;", "evaluatePropertyInitializer", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "firFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "evaluateParameterDefaultValue", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "evaluateVariableValue", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "variable", "isAllowedType", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "value", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "evaluateAnnotationArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "evaluateExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/PrivateConstantEvaluatorAPI;", "canBeEvaluated", "evaluate", "evaluateAndAdjustType", "visit", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "wasVisited", "EvaluationVisitor", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionEvaluator {
    public static final FirExpressionEvaluator INSTANCE = new FirExpressionEvaluator();
    private static final ThreadLocal<HashSet<FirCallableSymbol<?>>> visitedCallables;

    static {
        ThreadLocal<HashSet<FirCallableSymbol<?>>> threadLocalWithInitial = ThreadLocal.withInitial(new Supplier() { // from class: m65
            @Override // java.util.function.Supplier
            public final Object get() {
                return FirExpressionEvaluator.visitedCallables$hashSetOf();
            }
        });
        threadLocalWithInitial.getClass();
        visitedCallables = threadLocalWithInitial;
    }

    private FirExpressionEvaluator() {
    }

    private final boolean canBeEvaluated(FirExpression firExpression, FirSession firSession) {
        if (firExpression == null || (firExpression instanceof FirLazyExpression) || !FirTypeUtilsKt.getHasResolvedType(firExpression)) {
            return false;
        }
        return FirConstChecksKt.canBeEvaluatedAtCompileTime(firExpression, firSession, false, false);
    }

    private final FirEvaluatorResult evaluate(FirExpression firExpression, FirSession firSession, FirFile firFile) {
        return new EvaluationVisitor(firSession, firFile).evaluate(firExpression);
    }

    public static /* synthetic */ FirEvaluatorResult evaluate$default(FirExpressionEvaluator firExpressionEvaluator, FirExpression firExpression, FirSession firSession, FirFile firFile, int i, Object obj) {
        if ((i & 2) != 0) {
            firFile = null;
        }
        return firExpressionEvaluator.evaluate(firExpression, firSession, firFile);
    }

    private final FirEvaluatorResult evaluateAndAdjustType(FirExpression firExpression, FirSession firSession, FirFile firFile, FirVariable firVariable) {
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        Object value;
        FirLiteralExpression firLiteralExpressionAdjustTypeAndConvertToLiteral;
        FirEvaluatorResult firEvaluatorResultWrap;
        FirEvaluatorResult firEvaluatorResultEvaluate = evaluate(firExpression, firSession, firFile);
        if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
            return firEvaluatorResultEvaluate;
        }
        FirLiteralExpression firLiteralExpression = null;
        if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
            FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
            firLiteralExpression = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
        }
        return (firVariable == null || (returnTypeRef = firVariable.getReturnTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) == null || firLiteralExpression == null || (value = firLiteralExpression.getValue()) == null || (firLiteralExpressionAdjustTypeAndConvertToLiteral = FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(value, firLiteralExpression, coneType)) == null || (firEvaluatorResultWrap = FirExpressionEvaluatorKt.wrap(firLiteralExpressionAdjustTypeAndConvertToLiteral)) == null) ? firEvaluatorResultEvaluate : firEvaluatorResultWrap;
    }

    public static /* synthetic */ Map evaluateAnnotationArguments$default(FirExpressionEvaluator firExpressionEvaluator, FirAnnotation firAnnotation, FirSession firSession, FirFile firFile, int i, Object obj) {
        if ((i & 4) != 0) {
            firFile = null;
        }
        return firExpressionEvaluator.evaluateAnnotationArguments(firAnnotation, firSession, firFile);
    }

    public static /* synthetic */ FirEvaluatorResult evaluateParameterDefaultValue$default(FirExpressionEvaluator firExpressionEvaluator, FirValueParameter firValueParameter, FirSession firSession, FirFile firFile, int i, Object obj) {
        if ((i & 4) != 0) {
            firFile = null;
        }
        return firExpressionEvaluator.evaluateParameterDefaultValue(firValueParameter, firSession, firFile);
    }

    public static /* synthetic */ FirEvaluatorResult evaluatePropertyInitializer$default(FirExpressionEvaluator firExpressionEvaluator, FirProperty firProperty, FirSession firSession, FirFile firFile, int i, Object obj) {
        if ((i & 4) != 0) {
            firFile = null;
        }
        return firExpressionEvaluator.evaluatePropertyInitializer(firProperty, firSession, firFile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HashSet visitedCallables$hashSetOf() {
        return new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean wasVisited(FirCallableSymbol<?> firCallableSymbol) {
        return visitedCallables.get().contains(firCallableSymbol);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x006a  */
    public final Map<Name, FirEvaluatorResult> evaluateAnnotationArguments(FirAnnotation annotation, FirSession session, FirFile firFile) {
        Map mapEmptyMap;
        Collection<FirValueParameter> collectionValues;
        annotation.getClass();
        session.getClass();
        Map<Name, FirExpression> mapping = annotation.getArgumentMapping().getMapping();
        FirAnnotationCall firAnnotationCall = annotation instanceof FirAnnotationCall ? (FirAnnotationCall) annotation : null;
        if (firAnnotationCall != null) {
            FirArgumentList argumentList = firAnnotationCall.getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping2 = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping2 == null || (collectionValues = mapping2.values()) == null) {
                mapEmptyMap = MapsKt.emptyMap();
            } else {
                Collection<FirValueParameter> collection = collectionValues;
                mapEmptyMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection, 10)), 16));
                for (FirValueParameter firValueParameter : collection) {
                    Pair pair = TuplesKt.to(firValueParameter.getName(), firValueParameter);
                    mapEmptyMap.put(pair.getFirst(), pair.getSecond());
                }
            }
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapping.size()));
        Iterator<T> it = mapping.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), INSTANCE.evaluateAndAdjustType((FirExpression) entry.getValue(), session, firFile, (FirVariable) mapEmptyMap.get((Name) entry.getKey())));
        }
        return linkedHashMap;
    }

    @PrivateConstantEvaluatorAPI
    public final FirEvaluatorResult evaluateExpression(FirExpression expression, FirSession session) {
        expression.getClass();
        session.getClass();
        if (canBeEvaluated(expression, session)) {
            return evaluate$default(this, expression, session, null, 2, null);
        }
        return null;
    }

    public final FirEvaluatorResult evaluateParameterDefaultValue(FirValueParameter parameter, FirSession session, FirFile firFile) {
        FirExpression defaultValue;
        parameter.getClass();
        session.getClass();
        ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(parameter.getReturnTypeRef());
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = coneTypeOrNull != null ? TypeExpansionUtilsKt.fullyExpandedType$default(coneTypeOrNull, session, (Function1) null, 2, (Object) null) : null;
        if (coneKotlinTypeFullyExpandedType$default == null || (coneKotlinTypeFullyExpandedType$default instanceof ConeErrorType) || (defaultValue = parameter.getDefaultValue()) == null || !canBeEvaluated(defaultValue, session)) {
            return null;
        }
        return evaluateAndAdjustType(defaultValue, session, firFile, parameter);
    }

    public final FirEvaluatorResult evaluatePropertyInitializer(FirProperty property, FirSession session, FirFile firFile) {
        FirExpression initializer;
        property.getClass();
        session.getClass();
        FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(property);
        if (evaluatedInitializer != null) {
            return evaluatedInitializer;
        }
        if (!property.getStatus().isConst()) {
            return null;
        }
        ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(property.getReturnTypeRef());
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = coneTypeOrNull != null ? TypeExpansionUtilsKt.fullyExpandedType$default(coneTypeOrNull, session, (Function1) null, 2, (Object) null) : null;
        if (coneKotlinTypeFullyExpandedType$default == null || (coneKotlinTypeFullyExpandedType$default instanceof ConeErrorType) || !FirConstChecksKt.canBeUsedForConstVal(coneKotlinTypeFullyExpandedType$default) || (initializer = property.getInitializer()) == null || !canBeEvaluated(initializer, session)) {
            return null;
        }
        return evaluateAndAdjustType(initializer, session, firFile, property);
    }

    @Metadata(d1 = {"\u0000ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001a\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020*2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u0002002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00101\u001a\u00020\u00022\u0006\u00102\u001a\u0002032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00104\u001a\u00020\u00022\u0006\u00105\u001a\u0002062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u0002092\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010:\u001a\u00020\u00022\u0006\u0010;\u001a\u00020<2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u0018\u0010=\u001a\u00020\u00022\u0006\u0010;\u001a\u00020<2\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020\u00022\u0006\u0010A\u001a\u00020<H\u0002J\u001a\u0010B\u001a\u00020\u00022\u0006\u0010C\u001a\u00020D2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010E\u001a\u00020\u00022\u0006\u0010F\u001a\u00020G2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010H\u001a\u00020\u00022\u0006\u0010I\u001a\u00020J2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020M2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010N\u001a\u00020\u00022\u0006\u0010O\u001a\u00020P2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u00020S2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010T\u001a\u00020\u00022\u0006\u0010U\u001a\u00020V2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010W\u001a\u00020\u00022\u0006\u0010X\u001a\u00020Y2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010Z\u001a\u00020\u00022\u0006\u0010[\u001a\u00020\\2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010]\u001a\u00020\u00022\u0006\u0010^\u001a\u00020_2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirExpressionEvaluator$EvaluationVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "firFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "evaluate", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "visitResolvedNamedReference", "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "visitArgumentList", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "visitCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "visitSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitNamedFunction", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "visitConstructorCall", "constructorCall", "visitIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "visitEnumEntryDeserializedAccessExpression", "enumEntryDeserializedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "visitClassReferenceExpression", "classReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EvaluationVisitor extends FirVisitor {
        private final FirFile firFile;
        private final FirSession session;

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[FirOperation.values().length];
                try {
                    iArr[FirOperation.LT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FirOperation.LT_EQ.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[FirOperation.GT.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[FirOperation.GT_EQ.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[FirOperation.EQ.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[FirOperation.NOT_EQ.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[LogicOperationKind.values().length];
                try {
                    iArr2[LogicOperationKind.AND.ordinal()] = 1;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr2[LogicOperationKind.OR.ordinal()] = 2;
                } catch (NoSuchFieldError unused8) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public EvaluationVisitor(FirSession firSession, FirFile firFile) {
            firSession.getClass();
            this.session = firSession;
            this.firFile = firFile;
        }

        public static CharSequence b(FirLiteralExpression firLiteralExpression) {
            firLiteralExpression.getClass();
            return String.valueOf(FirExpressionEvaluatorKt.convertToGivenKind(firLiteralExpression.getKind(), firLiteralExpression.getValue()));
        }

        private final FirEvaluatorResult visitConstructorCall(FirFunctionCall constructorCall) {
            Object value;
            FirLiteralExpression firLiteralExpression = null;
            FirResolvedArgumentList firResolvedArgumentList = null;
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getResolvedType(constructorCall), this.session, (Function1) null, 2, (Object) null));
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneRigidTypeLowerBoundIfFlexible, this.session);
            if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) != ClassKind.ANNOTATION_CLASS) {
                if (!ConeBuiltinTypeUtilsKt.isUnsignedType(coneRigidTypeLowerBoundIfFlexible)) {
                    return FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) CollectionsKt.first(constructorCall.getArgumentList().getArguments()));
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                    return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                }
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                    firLiteralExpression = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
                }
                return (firLiteralExpression == null || (value = firLiteralExpression.getValue()) == null) ? FirEvaluatorResult.NotEvaluated.INSTANCE : FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(value, constructorCall);
            }
            FirEvaluatorResult firEvaluatorResult = (FirEvaluatorResult) constructorCall.getArgumentList().accept(this, null);
            if (firEvaluatorResult instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResult;
            }
            if (firEvaluatorResult instanceof FirEvaluatorResult.Evaluated) {
                FirElement result2 = ((FirEvaluatorResult.Evaluated) firEvaluatorResult).getResult();
                firResolvedArgumentList = (FirResolvedArgumentList) (result2 instanceof FirResolvedArgumentList ? result2 : null);
            }
            if (firResolvedArgumentList == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            firFunctionCallBuilder.setConeTypeOrNull(constructorCall.getConeTypeOrNull());
            firFunctionCallBuilder.getAnnotations().addAll(constructorCall.getAnnotations());
            firFunctionCallBuilder.getTypeArguments().addAll(constructorCall.getTypeArguments());
            firFunctionCallBuilder.setSource(constructorCall.getSource());
            firFunctionCallBuilder.getNonFatalDiagnostics().addAll(constructorCall.getNonFatalDiagnostics());
            firFunctionCallBuilder.setArgumentList(firResolvedArgumentList);
            firFunctionCallBuilder.setCalleeReference(constructorCall.getCalleeReference());
            firFunctionCallBuilder.setOrigin(constructorCall.getOrigin());
            return FirExpressionEvaluatorKt.wrap(firFunctionCallBuilder.mo288build());
        }

        private final FirEvaluatorResult visitNamedFunction(FirFunctionCall functionCall, FirNamedFunctionSymbol symbol) {
            FirEvaluatorResult firEvaluatorResultAdjustTypeAndConvertToLiteral;
            FirEvaluatorResult firEvaluatorResultAdjustTypeAndConvertToLiteral2;
            List listPlus = CollectionsKt.plus(CollectionsKt.listOfNotNull(new FirExpression[]{functionCall.getDispatchReceiver(), functionCall.getExtensionReceiver()}), functionCall.getArgumentList().getArguments());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) it.next());
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                    return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                }
                FirElement firElement = null;
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                    firElement = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
                }
                if (firElement == null) {
                    return FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                arrayList.add(firElement);
            }
            int size = arrayList.size();
            if (size == 1) {
                Object objEvaluateUnary = FirExpressionEvaluatorKt.evaluateUnary((FirExpression) CollectionsKt.first(arrayList), symbol.getCallableId());
                return (objEvaluateUnary == null || (firEvaluatorResultAdjustTypeAndConvertToLiteral = FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(objEvaluateUnary, functionCall)) == null) ? FirEvaluatorResult.NotEvaluated.INSTANCE : firEvaluatorResultAdjustTypeAndConvertToLiteral;
            }
            if (size != 2) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            Object objEvaluateBinary = FirExpressionEvaluatorKt.evaluateBinary((FirExpression) CollectionsKt.first(arrayList), symbol.getCallableId(), (FirExpression) arrayList.get(1));
            return (objEvaluateBinary == null || (firEvaluatorResultAdjustTypeAndConvertToLiteral2 = FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(objEvaluateBinary, functionCall)) == null) ? FirEvaluatorResult.NotEvaluated.INSTANCE : firEvaluatorResultAdjustTypeAndConvertToLiteral2;
        }

        private static final FirEvaluatorResult visitPropertyAccessExpression$evaluateWithSourceCopy(FirCallableSymbol<?> firCallableSymbol, FirPropertyAccessExpression firPropertyAccessExpression, EvaluationVisitor evaluationVisitor, FirExpression firExpression) {
            FirEvaluatorResult firEvaluatorResultWrap;
            HashSet hashSet = (HashSet) FirExpressionEvaluator.visitedCallables.get();
            hashSet.add(firCallableSymbol);
            try {
                if (!(firExpression instanceof FirLiteralExpression)) {
                    FirEvaluatorResult firEvaluatorResultEvaluate = evaluationVisitor.evaluate(firExpression);
                    if ((firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) && (((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult() instanceof FirLiteralExpression)) {
                        FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                        result.getClass();
                        firEvaluatorResultWrap = FirExpressionEvaluatorKt.wrap(FirExpressionEvaluatorKt.copy((FirLiteralExpression) result, firPropertyAccessExpression));
                    }
                    return firEvaluatorResultEvaluate;
                }
                firEvaluatorResultWrap = FirExpressionEvaluatorKt.wrap(FirExpressionEvaluatorKt.copy((FirLiteralExpression) firExpression, firPropertyAccessExpression));
                return firEvaluatorResultWrap;
            } finally {
                hashSet.remove(firCallableSymbol);
                if (hashSet.isEmpty()) {
                    FirExpressionEvaluator.visitedCallables.remove();
                }
            }
        }

        public final FirEvaluatorResult evaluate(FirExpression expression) {
            FirEvaluatorResult firEvaluatorResult;
            return (expression == null || (firEvaluatorResult = (FirEvaluatorResult) expression.accept(this, null)) == null) ? FirEvaluatorResult.NotEvaluated.INSTANCE : firEvaluatorResult;
        }

        public final FirSession getSession() {
            return this.session;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitAnnotation(FirAnnotation annotation, Void data) {
            annotation.getClass();
            Map<Name, FirExpression> mapping = annotation.getArgumentMapping().getMapping();
            if (mapping.isEmpty()) {
                return FirExpressionEvaluatorKt.wrap(annotation);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<Name, FirExpression> entry : mapping.entrySet()) {
                Name key = entry.getKey();
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate(entry.getValue());
                if (!(firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated)) {
                    return firEvaluatorResultEvaluate;
                }
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                result.getClass();
                linkedHashMap.put(key, (FirExpression) result);
            }
            FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
            firAnnotationBuilder.setSource(annotation.getSource());
            firAnnotationBuilder.setUseSiteTarget(annotation.getUseSiteTarget());
            firAnnotationBuilder.setAnnotationTypeRef(annotation.getAnnotationTypeRef());
            firAnnotationBuilder.setArgumentMapping(annotation.getArgumentMapping());
            firAnnotationBuilder.getTypeArguments().addAll(annotation.getTypeArguments());
            FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
            firAnnotationArgumentMappingBuilder.getMapping().putAll(linkedHashMap);
            firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuilder.build());
            return FirExpressionEvaluatorKt.wrap(firAnnotationBuilder.mo288build());
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitAnnotationCall(FirAnnotationCall annotationCall, Void data) {
            annotationCall.getClass();
            return visitAnnotation((FirAnnotation) annotationCall, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitArgumentList(FirArgumentList argumentList, Void data) {
            FirArgumentList firArgumentListBuild;
            FirExpression firExpression;
            FirExpression firExpression2;
            argumentList.getClass();
            if (argumentList instanceof FirResolvedArgumentList) {
                FirResolvedArgumentList firResolvedArgumentList = (FirResolvedArgumentList) argumentList;
                FirArgumentList originalArgumentList = firResolvedArgumentList.getOriginalArgumentList();
                LinkedHashMap<FirExpression, FirValueParameter> mapping = firResolvedArgumentList.getMapping();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Iterator<T> it = mapping.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) entry.getKey());
                    if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                        return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                    }
                    if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                        FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                        if (!(result instanceof FirExpression)) {
                            result = null;
                        }
                        firExpression2 = (FirExpression) result;
                    } else {
                        firExpression2 = null;
                    }
                    if (firExpression2 == null) {
                        return FirEvaluatorResult.NotEvaluated.INSTANCE;
                    }
                    linkedHashMap.put(firExpression2, entry.getValue());
                }
                firArgumentListBuild = FirArgumentUtilKt.buildResolvedArgumentList(originalArgumentList, linkedHashMap);
            } else {
                FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
                firArgumentListBuilder.setSource(argumentList.getSource());
                List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                List<FirExpression> arguments2 = argumentList.getArguments();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments2, 10));
                Iterator<T> it2 = arguments2.iterator();
                while (it2.hasNext()) {
                    FirEvaluatorResult firEvaluatorResultEvaluate2 = evaluate((FirExpression) it2.next());
                    if (firEvaluatorResultEvaluate2 instanceof FirEvaluatorResult.CompileTimeException) {
                        return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate2;
                    }
                    if (firEvaluatorResultEvaluate2 instanceof FirEvaluatorResult.Evaluated) {
                        FirElement result2 = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate2).getResult();
                        if (!(result2 instanceof FirExpression)) {
                            result2 = null;
                        }
                        firExpression = (FirExpression) result2;
                    } else {
                        firExpression = null;
                    }
                    if (firExpression == null) {
                        return FirEvaluatorResult.NotEvaluated.INSTANCE;
                    }
                    arrayList.add(firExpression);
                }
                arguments.addAll(arrayList);
                firArgumentListBuild = firArgumentListBuilder.build();
            }
            return FirExpressionEvaluatorKt.wrap(firArgumentListBuild);
        }

        /* JADX WARN: Code duplicated, block: B:46:0x008b  */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, Void data) {
            FirLiteralExpression firLiteralExpression;
            FirLiteralExpression firLiteralExpression2;
            booleanOperatorExpression.getClass();
            FirEvaluatorResult firEvaluatorResultEvaluate = evaluate(booleanOperatorExpression.getLeftOperand());
            FirEvaluatorResult firEvaluatorResultEvaluate2 = evaluate(booleanOperatorExpression.getRightOperand());
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
            }
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                if (!(result instanceof FirLiteralExpression)) {
                    result = null;
                }
                firLiteralExpression = (FirLiteralExpression) result;
            } else {
                firLiteralExpression = null;
            }
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            Boolean bool = value instanceof Boolean ? (Boolean) value : null;
            if (bool == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            boolean zBooleanValue = bool.booleanValue();
            if (firEvaluatorResultEvaluate2 instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate2;
            }
            if (firEvaluatorResultEvaluate2 instanceof FirEvaluatorResult.Evaluated) {
                FirElement result2 = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate2).getResult();
                if (!(result2 instanceof FirLiteralExpression)) {
                    result2 = null;
                }
                firLiteralExpression2 = (FirLiteralExpression) result2;
            } else {
                firLiteralExpression2 = null;
            }
            Object value2 = firLiteralExpression2 != null ? firLiteralExpression2.getValue() : null;
            Boolean bool2 = value2 instanceof Boolean ? (Boolean) value2 : null;
            if (bool2 == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            boolean zBooleanValue2 = bool2.booleanValue();
            int i = WhenMappings.$EnumSwitchMapping$1[booleanOperatorExpression.getKind().ordinal()];
            boolean z = false;
            if (i != 1) {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                if (zBooleanValue || zBooleanValue2) {
                    z = true;
                }
            } else if (zBooleanValue && zBooleanValue2) {
                z = true;
            }
            return FirExpressionEvaluatorKt.wrap(FirExpressionEvaluatorKt.toConstExpression(Boolean.valueOf(z), ConstantValueKind.Boolean.INSTANCE, booleanOperatorExpression));
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, Void data) {
            callableReferenceAccess.getClass();
            return FirExpressionEvaluatorKt.wrap(FirReferenceUtilsKt.getResolved(callableReferenceAccess.getCalleeReference()));
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitClassReferenceExpression(FirClassReferenceExpression classReferenceExpression, Void data) {
            classReferenceExpression.getClass();
            return FirExpressionEvaluatorKt.wrap(classReferenceExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitCollectionLiteral(FirCollectionLiteral collectionLiteral, Void data) {
            collectionLiteral.getClass();
            FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
            firCollectionLiteralBuilder.setSource(collectionLiteral.getSource());
            firCollectionLiteralBuilder.setConeTypeOrNull(collectionLiteral.getConeTypeOrNull());
            firCollectionLiteralBuilder.getAnnotations().addAll(collectionLiteral.getAnnotations());
            FirEvaluatorResult firEvaluatorResultVisitArgumentList = visitArgumentList(collectionLiteral.getArgumentList(), data);
            if (firEvaluatorResultVisitArgumentList instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultVisitArgumentList;
            }
            FirArgumentList firArgumentList = null;
            if (firEvaluatorResultVisitArgumentList instanceof FirEvaluatorResult.Evaluated) {
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultVisitArgumentList).getResult();
                firArgumentList = (FirArgumentList) (result instanceof FirArgumentList ? result : null);
            }
            if (firArgumentList == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            firCollectionLiteralBuilder.setArgumentList(firArgumentList);
            return FirExpressionEvaluatorKt.wrap(firCollectionLiteralBuilder.mo288build());
        }

        /* JADX WARN: Code duplicated, block: B:30:0x0057  */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitComparisonExpression(FirComparisonExpression comparisonExpression, Void data) {
            FirLiteralExpression firLiteralExpression;
            comparisonExpression.getClass();
            FirEvaluatorResult firEvaluatorResultVisitFunctionCall = visitFunctionCall(comparisonExpression.getCompareToCall(), data);
            if (firEvaluatorResultVisitFunctionCall instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultVisitFunctionCall;
            }
            if (firEvaluatorResultVisitFunctionCall instanceof FirEvaluatorResult.Evaluated) {
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultVisitFunctionCall).getResult();
                if (!(result instanceof FirLiteralExpression)) {
                    result = null;
                }
                firLiteralExpression = (FirLiteralExpression) result;
            } else {
                firLiteralExpression = null;
            }
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            Integer num = value instanceof Integer ? (Integer) value : null;
            if (num == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            int iIntValue = num.intValue();
            int i = WhenMappings.$EnumSwitchMapping$0[comparisonExpression.getOperation().ordinal()];
            boolean z = false;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            c2f.a("Unsupported comparison operation type \"", comparisonExpression.getOperation().name(), 34);
                            return null;
                        }
                        if (iIntValue >= 0) {
                            z = true;
                        }
                    } else if (iIntValue > 0) {
                        z = true;
                    }
                } else if (iIntValue <= 0) {
                    z = true;
                }
            } else if (iIntValue < 0) {
                z = true;
            }
            return FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(Boolean.valueOf(z), comparisonExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitEnumEntryDeserializedAccessExpression(FirEnumEntryDeserializedAccessExpression enumEntryDeserializedAccessExpression, Void data) {
            enumEntryDeserializedAccessExpression.getClass();
            return FirExpressionEvaluatorKt.wrap(enumEntryDeserializedAccessExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Void data) {
            equalityOperatorCall.getClass();
            List<FirExpression> arguments = equalityOperatorCall.getArgumentList().getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
            Iterator<T> it = arguments.iterator();
            while (true) {
                FirElement firElement = null;
                if (!it.hasNext()) {
                    if (arrayList.size() != 2) {
                        return FirEvaluatorResult.NotEvaluated.INSTANCE;
                    }
                    boolean zAreEqual = false;
                    FirLiteralExpression firLiteralExpression = (FirLiteralExpression) arrayList.get(0);
                    FirLiteralExpression firLiteralExpression2 = (FirLiteralExpression) arrayList.get(1);
                    Object objConvertToGivenKind = FirExpressionEvaluatorKt.convertToGivenKind(firLiteralExpression.getKind(), firLiteralExpression.getValue());
                    Object objConvertToGivenKind2 = FirExpressionEvaluatorKt.convertToGivenKind(firLiteralExpression2.getKind(), firLiteralExpression2.getValue());
                    int i = WhenMappings.$EnumSwitchMapping$0[equalityOperatorCall.getOperation().ordinal()];
                    if (i == 5) {
                        zAreEqual = Intrinsics.areEqual(objConvertToGivenKind, objConvertToGivenKind2);
                    } else {
                        if (i != 6) {
                            b88.a("Operation \"", equalityOperatorCall.getOperation(), "\" is not supported in compile time evaluation");
                            return null;
                        }
                        if (!Intrinsics.areEqual(objConvertToGivenKind, objConvertToGivenKind2)) {
                            zAreEqual = true;
                        }
                    }
                    return FirExpressionEvaluatorKt.wrap(FirExpressionEvaluatorKt.toConstExpression(Boolean.valueOf(zAreEqual), ConstantValueKind.Boolean.INSTANCE, equalityOperatorCall));
                }
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) it.next());
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                    return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                }
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                    firElement = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
                }
                if (firElement == null) {
                    return FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                arrayList.add(firElement);
            }
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitErrorResolvedQualifier(FirErrorResolvedQualifier errorResolvedQualifier, Void data) {
            errorResolvedQualifier.getClass();
            return FirExpressionEvaluatorKt.wrap(errorResolvedQualifier);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitFunctionCall(FirFunctionCall functionCall, Void data) {
            functionCall.getClass();
            FirNamedReference calleeReference = functionCall.getCalleeReference();
            if (!(calleeReference instanceof FirResolvedNamedReference)) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            FirBasedSymbol<?> resolvedSymbol = ((FirResolvedNamedReference) calleeReference).getResolvedSymbol();
            if (resolvedSymbol instanceof FirNamedFunctionSymbol) {
                return visitNamedFunction(functionCall, (FirNamedFunctionSymbol) resolvedSymbol);
            }
            return resolvedSymbol instanceof FirConstructorSymbol ? visitConstructorCall(functionCall) : FirEvaluatorResult.NotEvaluated.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitGetClassCall(FirGetClassCall getClassCall, Void data) {
            getClassCall.getClass();
            return FirExpressionEvaluatorKt.wrap(getClassCall);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, Void data) {
            integerLiteralOperatorCall.getClass();
            return visitFunctionCall((FirFunctionCall) integerLiteralOperatorCall, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitLiteralExpression(FirLiteralExpression literalExpression, Void data) {
            literalExpression.getClass();
            return FirExpressionEvaluatorKt.wrap(literalExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, Void data) {
            namedArgumentExpression.getClass();
            FirNamedArgumentExpressionBuilder firNamedArgumentExpressionBuilder = new FirNamedArgumentExpressionBuilder();
            firNamedArgumentExpressionBuilder.setSource(namedArgumentExpression.getSource());
            firNamedArgumentExpressionBuilder.getAnnotations().addAll(namedArgumentExpression.getAnnotations());
            FirEvaluatorResult firEvaluatorResultEvaluate = evaluate(namedArgumentExpression.getExpression());
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
            }
            FirExpression firExpression = null;
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                firExpression = (FirExpression) (result instanceof FirExpression ? result : null);
            }
            if (firExpression == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            firNamedArgumentExpressionBuilder.setExpression(firExpression);
            firNamedArgumentExpressionBuilder.setSpread(namedArgumentExpression.getIsSpread());
            firNamedArgumentExpressionBuilder.setName(namedArgumentExpression.getName());
            return FirExpressionEvaluatorKt.wrap(firNamedArgumentExpressionBuilder.mo288build());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, Void data) {
            CallableId callableId;
            propertyAccessExpression.getClass();
            Name name = null;
            FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(propertyAccessExpression.getCalleeReference(), false, 1, null);
            if (resolvedCallableSymbol$default == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            if (FirExpressionEvaluator.INSTANCE.wasVisited(resolvedCallableSymbol$default)) {
                return FirEvaluatorResult.RecursionInInitializer.INSTANCE;
            }
            if (!(resolvedCallableSymbol$default instanceof FirPropertySymbol)) {
                if (!(resolvedCallableSymbol$default instanceof FirFieldSymbol)) {
                    return resolvedCallableSymbol$default instanceof FirEnumEntrySymbol ? FirExpressionEvaluatorKt.wrap(propertyAccessExpression) : FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                FirFieldSymbol firFieldSymbol = (FirFieldSymbol) resolvedCallableSymbol$default;
                FirEvaluatorResult firEvaluatorResultVisitPropertyAccessExpression$evaluateWithSourceCopy = visitPropertyAccessExpression$evaluateWithSourceCopy(resolvedCallableSymbol$default, propertyAccessExpression, this, firFieldSymbol.getResolvedInitializer());
                FirInlineConstTrackerComponentKt.getInlineConstTracker(this.session).report((FirField) firFieldSymbol.getFir(), this.firFile, firEvaluatorResultVisitPropertyAccessExpression$evaluateWithSourceCopy);
                return firEvaluatorResultVisitPropertyAccessExpression$evaluateWithSourceCopy;
            }
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) resolvedCallableSymbol$default;
            FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer((FirVariable) firPropertySymbol.getFir());
            if (evaluatedInitializer != null) {
                return evaluatedInitializer;
            }
            CallableId callableId2 = firPropertySymbol.getCallableId();
            if ((callableId2 != null && FirExpressionEvaluatorKt.isStringLength(callableId2)) || ((callableId = firPropertySymbol.getCallableId()) != null && FirExpressionEvaluatorKt.isCharCode(callableId))) {
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate(propertyAccessExpression.getExplicitReceiver());
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                    return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                }
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                    Name result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                    name = (FirExpression) (result instanceof FirExpression ? result : null);
                }
                if (name == null) {
                    return FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                CallableId callableId3 = firPropertySymbol.getCallableId();
                callableId3.getClass();
                return FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(FirExpressionEvaluatorKt.evaluateUnary(name, callableId3), propertyAccessExpression);
            }
            CallableId callableId4 = firPropertySymbol.getCallableId();
            if (!Intrinsics.areEqual(callableId4 != null ? callableId4.getCallableName() : null, StandardNames.NAME)) {
                return !resolvedCallableSymbol$default.getRawStatus().isConst() ? FirEvaluatorResult.NotEvaluated.INSTANCE : visitPropertyAccessExpression$evaluateWithSourceCopy(resolvedCallableSymbol$default, propertyAccessExpression, this, firPropertySymbol.getResolvedInitializer());
            }
            FirEvaluatorResult firEvaluatorResultEvaluate2 = evaluate(propertyAccessExpression.getExplicitReceiver());
            if (!(firEvaluatorResultEvaluate2 instanceof FirEvaluatorResult.Evaluated)) {
                return firEvaluatorResultEvaluate2;
            }
            FirElement result2 = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate2).getResult();
            if (result2 instanceof FirPropertyAccessExpression) {
                String strAsString = ((FirPropertyAccessExpression) result2).getCalleeReference().getName().asString();
                strAsString.getClass();
                return FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(strAsString, propertyAccessExpression);
            }
            if (!(result2 instanceof FirResolvedCallableReference)) {
                return visitPropertyAccessExpression$evaluateWithSourceCopy(resolvedCallableSymbol$default, propertyAccessExpression, this, firPropertySymbol.getResolvedInitializer());
            }
            FirResolvedCallableReference firResolvedCallableReference = (FirResolvedCallableReference) result2;
            String strAsString2 = firResolvedCallableReference.getResolvedSymbol() instanceof FirConstructorSymbol ? SpecialNames.INIT.asString() : firResolvedCallableReference.getName().asString();
            strAsString2.getClass();
            return FirExpressionEvaluatorKt.adjustTypeAndConvertToLiteral(strAsString2, propertyAccessExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitResolvedNamedReference(FirResolvedNamedReference resolvedNamedReference, Void data) {
            resolvedNamedReference.getClass();
            return FirExpressionEvaluatorKt.wrap(resolvedNamedReference);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitResolvedQualifier(FirResolvedQualifier resolvedQualifier, Void data) {
            resolvedQualifier.getClass();
            return FirExpressionEvaluatorKt.wrap(resolvedQualifier);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitSpreadArgumentExpression(FirSpreadArgumentExpression spreadArgumentExpression, Void data) {
            spreadArgumentExpression.getClass();
            FirSpreadArgumentExpressionBuilder firSpreadArgumentExpressionBuilder = new FirSpreadArgumentExpressionBuilder();
            firSpreadArgumentExpressionBuilder.setSource(spreadArgumentExpression.getSource());
            firSpreadArgumentExpressionBuilder.getAnnotations().addAll(spreadArgumentExpression.getAnnotations());
            FirEvaluatorResult firEvaluatorResultEvaluate = evaluate(spreadArgumentExpression.getExpression());
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
            }
            FirExpression firExpression = null;
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                firExpression = (FirExpression) (result instanceof FirExpression ? result : null);
            }
            if (firExpression == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            firSpreadArgumentExpressionBuilder.setExpression(firExpression);
            return FirExpressionEvaluatorKt.wrap(firSpreadArgumentExpressionBuilder.mo288build());
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, Void data) {
            stringConcatenationCall.getClass();
            List<FirExpression> arguments = stringConcatenationCall.getArgumentList().getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) it.next());
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                    return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                }
                FirElement firElement = null;
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                    firElement = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
                }
                if (firElement == null) {
                    return FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                arrayList.add(firElement);
            }
            return FirExpressionEvaluatorKt.wrap(FirExpressionEvaluatorKt.toConstExpression(CollectionsKt.joinToString$default(arrayList, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.fir.expressions.c
                public final Object invoke(Object obj) {
                    return FirExpressionEvaluator.EvaluationVisitor.b((FirLiteralExpression) obj);
                }
            }, 30, (Object) null), ConstantValueKind.String.INSTANCE, stringConcatenationCall));
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, Void data) {
            thisReceiverExpression.getClass();
            return FirExpressionEvaluatorKt.wrap(thisReceiverExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, Void data) {
            typeOperatorCall.getClass();
            if (typeOperatorCall.getOperation() != FirOperation.AS) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments()));
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
            }
            FirLiteralExpression firLiteralExpression = null;
            if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                firLiteralExpression = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
            }
            if (firLiteralExpression == null) {
                return FirEvaluatorResult.NotEvaluated.INSTANCE;
            }
            return TypeUtilsKt.isSubtypeOf$default(FirTypeUtilsKt.getResolvedType(firLiteralExpression), FirTypeUtilsKt.getResolvedType(typeOperatorCall), this.session, false, 4, null) ? FirExpressionEvaluatorKt.wrap(firLiteralExpression) : FirExpressionEvaluatorKt.wrap(typeOperatorCall);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, Void data) {
            varargArgumentsExpression.getClass();
            FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
            firVarargArgumentsExpressionBuilder.setSource(varargArgumentsExpression.getSource());
            firVarargArgumentsExpressionBuilder.setConeTypeOrNull(varargArgumentsExpression.getConeTypeOrNull());
            firVarargArgumentsExpressionBuilder.getAnnotations().addAll(varargArgumentsExpression.getAnnotations());
            List<FirExpression> arguments = firVarargArgumentsExpressionBuilder.getArguments();
            List<FirExpression> arguments2 = varargArgumentsExpression.getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments2, 10));
            Iterator<T> it = arguments2.iterator();
            while (it.hasNext()) {
                FirEvaluatorResult firEvaluatorResultEvaluate = evaluate((FirExpression) it.next());
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.CompileTimeException) {
                    return (FirEvaluatorResult.CompileTimeException) firEvaluatorResultEvaluate;
                }
                FirElement firElement = null;
                if (firEvaluatorResultEvaluate instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluate).getResult();
                    firElement = (FirExpression) (result instanceof FirExpression ? result : null);
                }
                if (firElement == null) {
                    return FirEvaluatorResult.NotEvaluated.INSTANCE;
                }
                arrayList.add(firElement);
            }
            arguments.addAll(arrayList);
            firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(varargArgumentsExpression.getConeElementTypeOrNull());
            return FirExpressionEvaluatorKt.wrap(firVarargArgumentsExpressionBuilder.mo288build());
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public FirEvaluatorResult visitElement(FirElement element, Void data) {
            element.getClass();
            return FirEvaluatorResult.NotEvaluated.INSTANCE;
        }

        public /* synthetic */ EvaluationVisitor(FirSession firSession, FirFile firFile, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(firSession, (i & 2) != 0 ? null : firFile);
        }
    }
}
