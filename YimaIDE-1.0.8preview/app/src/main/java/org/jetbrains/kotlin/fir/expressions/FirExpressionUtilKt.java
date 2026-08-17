package org.jetbrains.kotlin.fir.expressions;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirPureAbstractElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorLoopBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirBlockImpl;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.TransformData;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000þ\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aE\u0010\t\u001a\u00020\n\"\u0004\b\u0000\u0010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u0001H\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017\u001a\u0018\u00102\u001a\u0002032\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u00104\u001a\u000205\u001a$\u00106\u001a\u0002072\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u00104\u001a\u0002052\n\b\u0002\u00108\u001a\u0004\u0018\u000109\u001a8\u0010:\u001a\u00020;\"\u0004\b\u0000\u0010<*\u00020;2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H<0>2\u0018\u0010?\u001a\u0014\u0012\u0004\u0012\u00020A\u0012\n\u0012\b\u0012\u0004\u0012\u0002H<0B0@\u001a-\u0010C\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020D*\u00020;2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020D0@¢\u0006\u0002\u0010F\u001a\n\u0010G\u001a\u00020\n*\u00020\n\u001a\n\u0010H\u001a\u00020\n*\u00020\n\u001a\u0018\u0010I\u001a\b\u0012\u0004\u0012\u00020\n0\u0019*\u00020\n2\u0006\u0010J\u001a\u00020'\u001a\"\u0010K\u001a\u00020L*\u00020\n2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\n0N2\u0006\u0010J\u001a\u00020'H\u0002\u001a\f\u0010Z\u001a\u0004\u0018\u00010[*\u00020P\u001a\n\u0010\\\u001a\u00020\n*\u00020\n\u001a\n\u0010]\u001a\u00020\n*\u00020\n\u001a\n\u0010a\u001a\u00020\n*\u00020\n\u001a\n\u0010b\u001a\u00020\n*\u00020\n\u001a*\u0010q\u001a\u00020L*\u00020(2\u0018\u0010r\u001a\u0014\u0012\u0004\u0012\u00020t\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020L0sH\u0086\bø\u0001\u0000\u001a\u000f\u0010v\u001a\u0004\u0018\u00010w*\u00020\nH\u0086\u0010\u001a\r\u0010x\u001a\u00020y*\u00020yH\u0086\u0010\"\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0019*\u00020\u001a8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u001d\u001a\u00020\n*\u00020\u001a8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0018\u0010 \u001a\u0004\u0018\u00010!*\u00020\u001a8Æ\u0002¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u001e\u0010$\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u001a8Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010\u001c\"\u0016\u0010&\u001a\u00020'*\u00020(8Æ\u0002¢\u0006\u0006\u001a\u0004\b&\u0010)\"6\u0010*\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020,\u0018\u00010+j\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020,\u0018\u0001`-*\u00020\u001a8Æ\u0002¢\u0006\u0006\u001a\u0004\b.\u0010/\"6\u00100\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020,\u0018\u00010+j\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020,\u0018\u0001`-*\u00020\u001a8Æ\u0002¢\u0006\u0006\u001a\u0004\b1\u0010/\"\u0017\u0010O\u001a\u0004\u0018\u00010\n*\u00020P8F¢\u0006\u0006\u001a\u0004\bQ\u0010R\"\u0017\u0010S\u001a\u0004\u0018\u00010\n*\u00020P8F¢\u0006\u0006\u001a\u0004\bT\u0010R\"\u0017\u0010U\u001a\u0004\u0018\u00010\n*\u00020P8F¢\u0006\u0006\u001a\u0004\bV\u0010R\"\u001b\u0010W\u001a\b\u0012\u0004\u0012\u00020\n0\u0019*\u00020P8F¢\u0006\u0006\u001a\u0004\bX\u0010Y\"\u0015\u0010^\u001a\u00020'*\u00020_8F¢\u0006\u0006\u001a\u0004\b^\u0010`\"\u0017\u0010c\u001a\u0004\u0018\u00010d*\u00020e8F¢\u0006\u0006\u001a\u0004\bf\u0010g\"\u0017\u0010h\u001a\u0004\u0018\u00010\n*\u00020e8F¢\u0006\u0006\u001a\u0004\bi\u0010j\"\u0015\u0010k\u001a\u00020'*\u00020l8F¢\u0006\u0006\u001a\u0004\bk\u0010m\"\u001b\u0010n\u001a\b\u0012\u0004\u0012\u00020\n0\u0019*\u00020[8F¢\u0006\u0006\u001a\u0004\bo\u0010p\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006z"}, d2 = {"unexpandedConeClassLikeType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getUnexpandedConeClassLikeType", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "unexpandedClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "getUnexpandedClassId", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Lorg/jetbrains/kotlin/name/ClassId;", "buildConstOrErrorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "T", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "kind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "value", "literalType", Argument.Delimiters.none, "literalValue", Argument.Delimiters.none, "diagnosticKind", "Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/types/ConstantValueKind;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "getArguments", "(Lorg/jetbrains/kotlin/fir/expressions/FirCall;)Ljava/util/List;", "argument", "getArgument", "(Lorg/jetbrains/kotlin/fir/expressions/FirCall;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "dynamicVararg", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "getDynamicVararg", "(Lorg/jetbrains/kotlin/fir/expressions/FirCall;)Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "dynamicVarargArguments", "getDynamicVarargArguments", "isCalleeDynamic", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Z", "resolvedArgumentMapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "getResolvedArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirCall;)Ljava/util/LinkedHashMap;", "resolvedArgumentMappingIncludingContextArguments", "getResolvedArgumentMappingIncludingContextArguments", "buildErrorLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "buildErrorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformStatementsIndexed", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "dataProducer", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/visitors/TransformData;", "replaceFirstStatement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "factory", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "unwrapErrorExpression", "unwrapArgument", "unwrapAndFlattenArgument", "flattenArrays", "unwrapAndFlattenArgumentTo", Argument.Delimiters.none, "list", Argument.Delimiters.none, "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "getExplicitReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "dispatchReceiver", "getDispatchReceiver", "extensionReceiver", "getExtensionReceiver", "contextArguments", "getContextArguments", "(Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;)Ljava/util/List;", "unwrapLValue", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "unwrapExpression", "unwrapReplExpressionRef", "isImplicitWhenSubjectVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)Z", "unwrapSmartcastExpression", "unwrapDesugaredAssignmentValueRef", "whenSubjectVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "getWhenSubjectVariable", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;)Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "whenSubject", "getWhenSubject", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isBound", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;)Z", "allReceiverExpressions", "getAllReceiverExpressions", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Ljava/util/List;", "forAllReifiedTypeParameters", "block", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;", "unwrapAnonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "firstQualifierPart", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionUtilKt {
    public static final <T> FirExpression buildConstOrErrorExpression(KtSourceElement ktSourceElement, ConstantValueKind constantValueKind, T t, String str, Object obj, DiagnosticKind diagnosticKind) {
        KtSourceElement ktSourceElement2;
        constantValueKind.getClass();
        str.getClass();
        obj.getClass();
        diagnosticKind.getClass();
        if (t != null) {
            ktSourceElement2 = ktSourceElement;
            FirLiteralExpression firLiteralExpressionBuildLiteralExpression$default = FirConstExpressionBuilderKt.buildLiteralExpression$default(ktSourceElement2, constantValueKind, t, null, false, null, 40, null);
            if (firLiteralExpressionBuildLiteralExpression$default != null) {
                return firLiteralExpressionBuildLiteralExpression$default;
            }
        } else {
            ktSourceElement2 = ktSourceElement;
        }
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setSource(ktSourceElement2);
        firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("Incorrect " + str + ": " + obj, diagnosticKind));
        return firErrorExpressionBuilder.mo289build();
    }

    public static final FirErrorExpression buildErrorExpression(KtSourceElement ktSourceElement, ConeDiagnostic coneDiagnostic, FirElement firElement) {
        coneDiagnostic.getClass();
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setSource(ktSourceElement);
        firErrorExpressionBuilder.setDiagnostic(coneDiagnostic);
        boolean z = firElement instanceof FirExpression;
        firErrorExpressionBuilder.setExpression(z ? (FirExpression) firElement : null);
        if (z) {
            firElement = null;
        }
        firErrorExpressionBuilder.setNonExpressionElement(firElement);
        return firErrorExpressionBuilder.mo289build();
    }

    public static /* synthetic */ FirErrorExpression buildErrorExpression$default(KtSourceElement ktSourceElement, ConeDiagnostic coneDiagnostic, FirElement firElement, int i, Object obj) {
        if ((i & 4) != 0) {
            firElement = null;
        }
        return buildErrorExpression(ktSourceElement, coneDiagnostic, firElement);
    }

    public static final FirErrorLoop buildErrorLoop(KtSourceElement ktSourceElement, ConeDiagnostic coneDiagnostic) {
        coneDiagnostic.getClass();
        FirErrorLoopBuilder firErrorLoopBuilder = new FirErrorLoopBuilder();
        firErrorLoopBuilder.setSource(ktSourceElement);
        firErrorLoopBuilder.setDiagnostic(coneDiagnostic);
        FirErrorLoop firErrorLoopBuild = firErrorLoopBuilder.mo289build();
        firErrorLoopBuild.getBlock().replaceConeTypeOrNull(new ConeErrorType(coneDiagnostic, false, null, null, null, null, null, 126, null));
        return firErrorLoopBuild;
    }

    public static final FirResolvedQualifier firstQualifierPart(FirResolvedQualifier firResolvedQualifier) {
        firResolvedQualifier.getClass();
        while (true) {
            FirResolvedQualifier explicitParent = firResolvedQualifier.getExplicitParent();
            if (explicitParent == null) {
                return firResolvedQualifier;
            }
            firResolvedQualifier = explicitParent;
        }
    }

    public static final void forAllReifiedTypeParameters(FirFunctionCall firFunctionCall, Function2<? super ConeKotlinType, ? super FirTypeProjectionWithVariance, Unit> function2) {
        ConeKotlinType coneTypeOrNull;
        firFunctionCall.getClass();
        function2.getClass();
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedNamedFunctionSymbol$default == null) {
            return;
        }
        for (Pair pair : CollectionsKt.zip(resolvedNamedFunctionSymbol$default.getTypeParameterSymbols(), firFunctionCall.getTypeArguments())) {
            FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) pair.component1();
            FirTypeProjection firTypeProjection = (FirTypeProjection) pair.component2();
            if (firTypeParameterSymbol.isReified() && (firTypeProjection instanceof FirTypeProjectionWithVariance) && (coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef())) != null) {
                function2.invoke(coneTypeOrNull, firTypeProjection);
            }
        }
    }

    public static final List<FirExpression> getAllReceiverExpressions(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firQualifiedAccessExpression.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list, firQualifiedAccessExpression.getDispatchReceiver());
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list, firQualifiedAccessExpression.getExtensionReceiver());
        listCreateListBuilder.addAll(firQualifiedAccessExpression.getContextArguments());
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static final FirExpression getArgument(FirCall firCall) {
        firCall.getClass();
        return (FirExpression) CollectionsKt.first(firCall.getArgumentList().getArguments());
    }

    public static final List<FirExpression> getArguments(FirCall firCall) {
        firCall.getClass();
        return firCall.getArgumentList().getArguments();
    }

    public static final List<FirExpression> getContextArguments(FirVariableAssignment firVariableAssignment) {
        List<FirExpression> contextArguments;
        firVariableAssignment.getClass();
        FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue = unwrapLValue(firVariableAssignment);
        return (firQualifiedAccessExpressionUnwrapLValue == null || (contextArguments = firQualifiedAccessExpressionUnwrapLValue.getContextArguments()) == null) ? CollectionsKt.emptyList() : contextArguments;
    }

    public static final FirExpression getDispatchReceiver(FirVariableAssignment firVariableAssignment) {
        firVariableAssignment.getClass();
        FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue = unwrapLValue(firVariableAssignment);
        if (firQualifiedAccessExpressionUnwrapLValue != null) {
            return firQualifiedAccessExpressionUnwrapLValue.getDispatchReceiver();
        }
        return null;
    }

    public static final FirVarargArgumentsExpression getDynamicVararg(FirCall firCall) {
        firCall.getClass();
        Object objFirstOrNull = CollectionsKt.firstOrNull(firCall.getArgumentList().getArguments());
        if (objFirstOrNull instanceof FirVarargArgumentsExpression) {
            return (FirVarargArgumentsExpression) objFirstOrNull;
        }
        return null;
    }

    public static final List<FirExpression> getDynamicVarargArguments(FirCall firCall) {
        firCall.getClass();
        Object objFirstOrNull = CollectionsKt.firstOrNull(firCall.getArgumentList().getArguments());
        FirVarargArgumentsExpression firVarargArgumentsExpression = objFirstOrNull instanceof FirVarargArgumentsExpression ? (FirVarargArgumentsExpression) objFirstOrNull : null;
        if (firVarargArgumentsExpression != null) {
            return firVarargArgumentsExpression.getArguments();
        }
        return null;
    }

    public static final FirExpression getExplicitReceiver(FirVariableAssignment firVariableAssignment) {
        firVariableAssignment.getClass();
        FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue = unwrapLValue(firVariableAssignment);
        if (firQualifiedAccessExpressionUnwrapLValue != null) {
            return firQualifiedAccessExpressionUnwrapLValue.getExplicitReceiver();
        }
        return null;
    }

    public static final FirExpression getExtensionReceiver(FirVariableAssignment firVariableAssignment) {
        firVariableAssignment.getClass();
        FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue = unwrapLValue(firVariableAssignment);
        if (firQualifiedAccessExpressionUnwrapLValue != null) {
            return firQualifiedAccessExpressionUnwrapLValue.getExtensionReceiver();
        }
        return null;
    }

    public static final LinkedHashMap<FirExpression, FirValueParameter> getResolvedArgumentMapping(FirCall firCall) {
        firCall.getClass();
        FirArgumentList argumentList = firCall.getArgumentList();
        if (argumentList instanceof FirResolvedArgumentList) {
            return ((FirResolvedArgumentList) argumentList).getMapping();
        }
        return null;
    }

    public static final LinkedHashMap<FirExpression, FirValueParameter> getResolvedArgumentMappingIncludingContextArguments(FirCall firCall) {
        firCall.getClass();
        FirArgumentList argumentList = firCall.getArgumentList();
        if (argumentList instanceof FirResolvedArgumentList) {
            return ((FirResolvedArgumentList) argumentList).getMappingIncludingContextArguments();
        }
        return null;
    }

    public static final ClassId getUnexpandedClassId(FirAnnotation firAnnotation) {
        ConeClassLikeLookupTag lookupTag;
        firAnnotation.getClass();
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) {
            return null;
        }
        return lookupTag.getClassId();
    }

    public static final ConeClassLikeType getUnexpandedConeClassLikeType(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType instanceof ConeClassLikeType) {
            return (ConeClassLikeType) coneType;
        }
        return null;
    }

    public static final FirExpression getWhenSubject(FirWhenSubjectExpression firWhenSubjectExpression) {
        firWhenSubjectExpression.getClass();
        FirProperty whenSubjectVariable = getWhenSubjectVariable(firWhenSubjectExpression);
        if (whenSubjectVariable != null) {
            return whenSubjectVariable.getInitializer();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirProperty getWhenSubjectVariable(FirWhenSubjectExpression firWhenSubjectExpression) {
        firWhenSubjectExpression.getClass();
        FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(firWhenSubjectExpression.getCalleeReference());
        FirPropertySymbol firPropertySymbol = symbol instanceof FirPropertySymbol ? (FirPropertySymbol) symbol : null;
        if (firPropertySymbol != null) {
            return (FirProperty) firPropertySymbol.getFir();
        }
        return null;
    }

    public static final boolean isBound(FirCallableReferenceAccess firCallableReferenceAccess) {
        FirCallableSymbol resolvedCallableSymbol$default;
        firCallableReferenceAccess.getClass();
        return !(firCallableReferenceAccess.getDispatchReceiver() == null && firCallableReferenceAccess.getExtensionReceiver() == null) && ((resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firCallableReferenceAccess.getCalleeReference(), false, 1, null)) == null || !resolvedCallableSymbol$default.getRawStatus().isStatic());
    }

    public static final boolean isCalleeDynamic(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        return Intrinsics.areEqual(resolvedNamedFunctionSymbol$default != null ? resolvedNamedFunctionSymbol$default.getOrigin() : null, FirDeclarationOrigin.DynamicScope.INSTANCE);
    }

    public static final boolean isImplicitWhenSubjectVariable(FirVariable firVariable) {
        firVariable.getClass();
        return Intrinsics.areEqual(firVariable.getOrigin(), FirDeclarationOrigin.Synthetic.ImplicitWhenSubject.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends FirStatement> T replaceFirstStatement(FirBlock firBlock, Function1<? super T, ? extends FirStatement> function1) {
        firBlock.getClass();
        function1.getClass();
        if (!(firBlock instanceof FirBlockImpl)) {
            dt1.a("replaceFirstStatement should not be called for ", Reflection.getOrCreateKotlinClass(firBlock.getClass()).getSimpleName());
            return null;
        }
        FirBlockImpl firBlockImpl = (FirBlockImpl) firBlock;
        FirStatement firStatement = firBlockImpl.getStatements().get(0);
        firStatement.getClass();
        T t = (T) firStatement;
        firBlockImpl.getStatements().set(0, function1.invoke(t));
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <D> FirBlock transformStatementsIndexed(FirBlock firBlock, FirTransformer<? super D> firTransformer, Function1<? super Integer, ? extends TransformData<? extends D>> function1) {
        Object value;
        firBlock.getClass();
        firTransformer.getClass();
        function1.getClass();
        int i = 0;
        if (firBlock instanceof FirBlockImpl) {
            ListIterator<FirStatement> listIterator = ((FirBlockImpl) firBlock).getStatements().listIterator();
            while (listIterator.hasNext()) {
                Object next = listIterator.next();
                next.getClass();
                FirPureAbstractElement firPureAbstractElement = (FirPureAbstractElement) next;
                int i2 = i + 1;
                TransformData transformData = (TransformData) function1.invoke(Integer.valueOf(i));
                if (transformData instanceof TransformData.Data) {
                    FirElement firElementTransform = firPureAbstractElement.transform(firTransformer, ((TransformData.Data) transformData).getValue());
                    if (firElementTransform != firPureAbstractElement) {
                        listIterator.set(firElementTransform);
                    }
                } else if (!Intrinsics.areEqual(transformData, TransformData.Nothing.INSTANCE)) {
                    bu8.a();
                    return null;
                }
                i = i2;
            }
        } else if (firBlock instanceof FirSingleExpressionBlock) {
            Object objInvoke = function1.invoke(0);
            TransformData.Data data = objInvoke instanceof TransformData.Data ? (TransformData.Data) objInvoke : null;
            if (data != null && (value = data.getValue()) != null) {
                ((FirSingleExpressionBlock) firBlock).transformStatements(firTransformer, value);
            }
        }
        return firBlock;
    }

    public static final List<FirExpression> unwrapAndFlattenArgument(FirExpression firExpression, boolean z) {
        firExpression.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        unwrapAndFlattenArgumentTo(firExpression, listCreateListBuilder, z);
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void unwrapAndFlattenArgumentTo(FirExpression firExpression, List<FirExpression> list, boolean z) {
        FirAnnotationContainer firAnnotationContainerUnwrapArgument = unwrapArgument(firExpression);
        if ((firAnnotationContainerUnwrapArgument instanceof FirCollectionLiteral) || (firAnnotationContainerUnwrapArgument instanceof FirFunctionCall)) {
            if (!z) {
                list.add(firAnnotationContainerUnwrapArgument);
                return;
            }
            Iterator<T> it = ((FirCall) firAnnotationContainerUnwrapArgument).getArgumentList().getArguments().iterator();
            while (it.hasNext()) {
                unwrapAndFlattenArgumentTo((FirExpression) it.next(), list, z);
            }
            return;
        }
        if (!(firAnnotationContainerUnwrapArgument instanceof FirVarargArgumentsExpression)) {
            list.add(firAnnotationContainerUnwrapArgument);
            return;
        }
        Iterator<T> it2 = ((FirVarargArgumentsExpression) firAnnotationContainerUnwrapArgument).getArguments().iterator();
        while (it2.hasNext()) {
            unwrapAndFlattenArgumentTo((FirExpression) it2.next(), list, z);
        }
    }

    public static final FirAnonymousFunction unwrapAnonymousFunctionExpression(FirExpression firExpression) {
        firExpression.getClass();
        while (!(firExpression instanceof FirAnonymousFunctionExpression)) {
            if (!(firExpression instanceof FirWrappedArgumentExpression)) {
                return null;
            }
            firExpression = ((FirWrappedArgumentExpression) firExpression).getExpression();
        }
        return ((FirAnonymousFunctionExpression) firExpression).getAnonymousFunction();
    }

    public static final FirExpression unwrapArgument(FirExpression firExpression) {
        FirExpression expression;
        firExpression.getClass();
        FirWrappedArgumentExpression firWrappedArgumentExpression = firExpression instanceof FirWrappedArgumentExpression ? (FirWrappedArgumentExpression) firExpression : null;
        return (firWrappedArgumentExpression == null || (expression = firWrappedArgumentExpression.getExpression()) == null) ? firExpression : expression;
    }

    public static final FirExpression unwrapDesugaredAssignmentValueRef(FirExpression firExpression) {
        firExpression.getClass();
        return firExpression instanceof FirDesugaredAssignmentValueReferenceExpression ? ((FirDesugaredAssignmentValueReferenceExpression) firExpression).getExpressionRef().getValue() : firExpression;
    }

    public static final FirExpression unwrapErrorExpression(FirExpression firExpression) {
        FirExpression expression;
        FirExpression firExpressionUnwrapErrorExpression;
        firExpression.getClass();
        FirErrorExpression firErrorExpression = firExpression instanceof FirErrorExpression ? (FirErrorExpression) firExpression : null;
        return (firErrorExpression == null || (expression = firErrorExpression.getExpression()) == null || (firExpressionUnwrapErrorExpression = unwrapErrorExpression(expression)) == null) ? firExpression : firExpressionUnwrapErrorExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirExpression unwrapExpression(FirExpression firExpression) {
        firExpression.getClass();
        if (firExpression instanceof FirSmartCastExpression) {
            return unwrapExpression(((FirSmartCastExpression) firExpression).getOriginalExpression());
        }
        if (firExpression instanceof FirCheckedSafeCallSubject) {
            return unwrapExpression(((FirCheckedSafeCallSubject) firExpression).getOriginalReceiverRef().getValue());
        }
        if (firExpression instanceof FirCheckNotNullCall) {
            return unwrapExpression((FirExpression) CollectionsKt.first(((FirCall) firExpression).getArgumentList().getArguments()));
        }
        return firExpression instanceof FirDesugaredAssignmentValueReferenceExpression ? unwrapExpression(((FirDesugaredAssignmentValueReferenceExpression) firExpression).getExpressionRef().getValue()) : firExpression;
    }

    public static final FirQualifiedAccessExpression unwrapLValue(FirVariableAssignment firVariableAssignment) {
        FirExpressionRef<FirExpression> expressionRef;
        firVariableAssignment.getClass();
        FirExpression lValue = firVariableAssignment.getLValue();
        FirQualifiedAccessExpression firQualifiedAccessExpression = lValue instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) lValue : null;
        if (firQualifiedAccessExpression != null) {
            return firQualifiedAccessExpression;
        }
        FirDesugaredAssignmentValueReferenceExpression firDesugaredAssignmentValueReferenceExpression = lValue instanceof FirDesugaredAssignmentValueReferenceExpression ? (FirDesugaredAssignmentValueReferenceExpression) lValue : null;
        FirExpression value = (firDesugaredAssignmentValueReferenceExpression == null || (expressionRef = firDesugaredAssignmentValueReferenceExpression.getExpressionRef()) == null) ? null : expressionRef.getValue();
        if (value instanceof FirQualifiedAccessExpression) {
            return (FirQualifiedAccessExpression) value;
        }
        return null;
    }

    public static final FirExpression unwrapReplExpressionRef(FirExpression firExpression) {
        firExpression.getClass();
        return firExpression instanceof FirReplExpressionReference ? unwrapReplExpressionRef(((FirReplExpressionReference) firExpression).getExpressionRef().getValue()) : firExpression;
    }

    public static final FirExpression unwrapSmartcastExpression(FirExpression firExpression) {
        firExpression.getClass();
        return firExpression instanceof FirSmartCastExpression ? ((FirSmartCastExpression) firExpression).getOriginalExpression() : firExpression;
    }
}
