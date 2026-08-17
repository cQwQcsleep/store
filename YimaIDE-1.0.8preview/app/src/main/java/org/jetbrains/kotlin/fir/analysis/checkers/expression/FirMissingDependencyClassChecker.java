package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSingleCandidate;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u00032\u00020\u0004B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J-\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u00020\tR\u00020\u000bj\u0006\u0010\n\u001a\u00020\tj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingDependencyClassChecker extends FirExpressionChecker<FirQualifiedAccessExpression> implements FirMissingDependencyClassProxy {
    public static final FirMissingDependencyClassChecker INSTANCE = new FirMissingDependencyClassChecker();

    private FirMissingDependencyClassChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0086 A[EDGE_INSN: B:29:0x0086->B:30:0x0087 BREAK  A[LOOP:1: B:15:0x005c->B:178:?]] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirCallableSymbol resolvedCallableSymbol$default;
        List<FirValueParameterSymbol> valueParameterSymbols;
        LinkedHashMap<FirExpression, FirValueParameter> mapping;
        boolean z;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        List<FirElement> containingElements = checkerContext.getContainingElements();
        if (!FirReferenceUtilsKt.isError(calleeReference)) {
            List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression)});
            while (!listMutableListOf.isEmpty()) {
                ConeKotlinType coneKotlinType = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
                if (!(coneKotlinType instanceof ConeErrorType)) {
                    z = false;
                    break;
                }
                List<FirElement> list = containingElements;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            FirElement firElement = (FirElement) it.next();
                            FirFunctionCall firFunctionCall = firElement instanceof FirFunctionCall ? (FirFunctionCall) firElement : null;
                            if ((firFunctionCall != null ? firFunctionCall.getCalleeReference() : null) instanceof FirResolvedErrorReference) {
                                if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ForbidUsingExpressionTypesWithInaccessibleContent)) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        break;
                    }
                }
                z = false;
                break;
                INSTANCE.considerType(checkerContext, coneKotlinType, z ? linkedHashSet : linkedHashSet2);
                if (coneKotlinType instanceof ConeFlexibleType) {
                    ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                    listMutableListOf.add(coneFlexibleType.getLowerBound());
                    if (!coneFlexibleType.getIsTrivial()) {
                        listMutableListOf.add(coneFlexibleType.getUpperBound());
                    }
                } else if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                    listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
                } else if (coneKotlinType instanceof ConeIntersectionType) {
                    listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
                } else {
                    for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                            listMutableListOf.add(coneKotlinTypeProjection.getType());
                        }
                    }
                }
            }
        }
        if ((FirReferenceUtilsKt.isError(calleeReference) && !(((FirDiagnosticHolder) calleeReference).getDiagnostic() instanceof ConeDiagnosticWithSingleCandidate) && linkedHashSet2.isEmpty()) || (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null)) == null) {
            return;
        }
        considerType(checkerContext, resolvedCallableSymbol$default.getResolvedReturnTypeRef().getConeType(), linkedHashSet);
        ConeKotlinType resolvedReceiverType = resolvedCallableSymbol$default.getResolvedReceiverType();
        if (resolvedReceiverType != null) {
            INSTANCE.considerType(checkerContext, resolvedReceiverType, linkedHashSet);
            List listMutableListOf2 = CollectionsKt.mutableListOf(new ConeKotlinType[]{resolvedReceiverType});
            while (!listMutableListOf2.isEmpty()) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf2);
                INSTANCE.considerType(checkerContext, coneKotlinType2, ConeBuiltinTypeUtilsKt.isArrayTypeOrNullableArrayType(resolvedReceiverType) ? linkedHashSet : linkedHashSet2);
                if (coneKotlinType2 instanceof ConeFlexibleType) {
                    ConeFlexibleType coneFlexibleType2 = (ConeFlexibleType) coneKotlinType2;
                    listMutableListOf2.add(coneFlexibleType2.getLowerBound());
                    if (!coneFlexibleType2.getIsTrivial()) {
                        listMutableListOf2.add(coneFlexibleType2.getUpperBound());
                    }
                } else if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                    listMutableListOf2.add(((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
                } else if (coneKotlinType2 instanceof ConeIntersectionType) {
                    listMutableListOf2.addAll(((ConeIntersectionType) coneKotlinType2).getIntersectedTypes());
                } else {
                    for (ConeKotlinTypeProjection coneKotlinTypeProjection2 : coneKotlinType2.getTypeArguments()) {
                        if (coneKotlinTypeProjection2 instanceof ConeKotlinTypeProjection) {
                            listMutableListOf2.add(coneKotlinTypeProjection2.getType());
                        }
                    }
                }
            }
        }
        if (firQualifiedAccessExpression instanceof FirFunctionCall) {
            FirArgumentList argumentList = ((FirFunctionCall) firQualifiedAccessExpression).getArgumentList();
            FirResolvedArgumentList firResolvedArgumentList = argumentList instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) argumentList : null;
            HashSet hashSet = new HashSet();
            if (firResolvedArgumentList != null && (mapping = firResolvedArgumentList.getMapping()) != null) {
                Iterator<Map.Entry<FirExpression, FirValueParameter>> it2 = mapping.entrySet().iterator();
                while (it2.hasNext()) {
                    FirValueParameter value = it2.next().getValue();
                    hashSet.add(value.getSymbol());
                    ConeKotlinType coneType = FirTypeUtilsKt.getConeType(value.getReturnTypeRef());
                    INSTANCE.considerType(checkerContext, coneType, linkedHashSet);
                    List listMutableListOf3 = CollectionsKt.mutableListOf(new ConeKotlinType[]{coneType});
                    while (!listMutableListOf3.isEmpty()) {
                        ConeKotlinType coneKotlinType3 = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf3);
                        INSTANCE.considerType(checkerContext, coneKotlinType3, ConeBuiltinTypeUtilsKt.isArrayTypeOrNullableArrayType(coneType) ? linkedHashSet : linkedHashSet2);
                        if (coneKotlinType3 instanceof ConeFlexibleType) {
                            ConeFlexibleType coneFlexibleType3 = (ConeFlexibleType) coneKotlinType3;
                            listMutableListOf3.add(coneFlexibleType3.getLowerBound());
                            if (!coneFlexibleType3.getIsTrivial()) {
                                listMutableListOf3.add(coneFlexibleType3.getUpperBound());
                            }
                        } else if (coneKotlinType3 instanceof ConeDefinitelyNotNullType) {
                            listMutableListOf3.add(((ConeDefinitelyNotNullType) coneKotlinType3).getOriginal());
                        } else if (coneKotlinType3 instanceof ConeIntersectionType) {
                            listMutableListOf3.addAll(((ConeIntersectionType) coneKotlinType3).getIntersectedTypes());
                        } else {
                            for (ConeKotlinTypeProjection coneKotlinTypeProjection3 : coneKotlinType3.getTypeArguments()) {
                                if (coneKotlinTypeProjection3 instanceof ConeKotlinTypeProjection) {
                                    listMutableListOf3.add(coneKotlinTypeProjection3.getType());
                                }
                            }
                        }
                    }
                }
            }
            FirFunctionSymbol firFunctionSymbol = resolvedCallableSymbol$default instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol$default : null;
            if (firFunctionSymbol != null && (valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols()) != null) {
                for (FirValueParameterSymbol firValueParameterSymbol : valueParameterSymbols) {
                    if (!hashSet.contains(firValueParameterSymbol)) {
                        ConeKotlinType coneType2 = firValueParameterSymbol.getResolvedReturnTypeRef().getConeType();
                        if (ConeBuiltinTypeUtilsKt.isArrayTypeOrNullableArrayType(coneType2)) {
                            List listMutableListOf4 = CollectionsKt.mutableListOf(new ConeKotlinType[]{coneType2});
                            while (!listMutableListOf4.isEmpty()) {
                                ConeKotlinType coneKotlinType4 = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf4);
                                INSTANCE.considerType(checkerContext, coneKotlinType4, linkedHashSet);
                                if (coneKotlinType4 instanceof ConeFlexibleType) {
                                    ConeFlexibleType coneFlexibleType4 = (ConeFlexibleType) coneKotlinType4;
                                    listMutableListOf4.add(coneFlexibleType4.getLowerBound());
                                    if (!coneFlexibleType4.getIsTrivial()) {
                                        listMutableListOf4.add(coneFlexibleType4.getUpperBound());
                                    }
                                } else if (coneKotlinType4 instanceof ConeDefinitelyNotNullType) {
                                    listMutableListOf4.add(((ConeDefinitelyNotNullType) coneKotlinType4).getOriginal());
                                } else if (coneKotlinType4 instanceof ConeIntersectionType) {
                                    listMutableListOf4.addAll(((ConeIntersectionType) coneKotlinType4).getIntersectedTypes());
                                } else {
                                    for (ConeKotlinTypeProjection coneKotlinTypeProjection4 : coneKotlinType4.getTypeArguments()) {
                                        if (coneKotlinTypeProjection4 instanceof ConeKotlinTypeProjection) {
                                            listMutableListOf4.add(coneKotlinTypeProjection4.getType());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        reportMissingTypes(checkerContext, diagnosticReporter, firQualifiedAccessExpression.getSource(), linkedHashSet, FirMissingDependencyClassProxy.MissingTypeOrigin.Other.INSTANCE);
        if (linkedHashSet.isEmpty()) {
            reportMissingTypes(checkerContext, diagnosticReporter, firQualifiedAccessExpression.getSource(), linkedHashSet2, FirMissingDependencyClassProxy.MissingTypeOrigin.Expression.INSTANCE);
        }
    }
}
