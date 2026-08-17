package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ExplicitFieldsUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.NotFunctionAsOperator;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer;
import org.jetbrains.kotlin.fir.resolve.dfa.Flow;
import org.jetbrains.kotlin.fir.resolve.dfa.PersistentTypeStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.SyntheticVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNotFunctionAsOperator;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0002\u001aH\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\"\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"createExplicitReceiverForInvoke", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "invokeBuiltinExtensionMode", Argument.Delimiters.none, "extensionReceiverExpression", "createExplicitReceiverForInvokeByCallable", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "nonFatalDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeNotFunctionAsOperator;", "fakeSourceForImplicitInvokeCallReceiver", "Lorg/jetbrains/kotlin/KtSourceElement;", "getFakeSourceForImplicitInvokeCallReceiver", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;)Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInvokeResolveTowerExtensionKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression createExplicitReceiverForInvoke(BodyResolveComponents bodyResolveComponents, Candidate candidate, CallInfo callInfo, boolean z, FirExpression firExpression) {
        List listEmptyList;
        if (candidate.getLowestApplicability() == CandidateApplicability.K2_NOT_FUNCTION_AS_OPERATOR) {
            List<ResolutionDiagnostic> diagnostics = candidate.getDiagnostics();
            ArrayList arrayList = new ArrayList();
            for (Object obj : diagnostics) {
                if (obj instanceof NotFunctionAsOperator) {
                    arrayList.add(obj);
                }
            }
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                listEmptyList.add(new ConeNotFunctionAsOperator(((NotFunctionAsOperator) it.next()).getSymbol()));
            }
        } else {
            listEmptyList = null;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list = listEmptyList;
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        if (symbol instanceof FirCallableSymbol) {
            return createExplicitReceiverForInvokeByCallable(bodyResolveComponents, candidate, callInfo, z, firExpression, (FirCallableSymbol) symbol, list);
        }
        if (!(symbol instanceof FirClassLikeSymbol)) {
            x1f.a();
            return null;
        }
        FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) symbol;
        KtSourceElement fakeSourceForImplicitInvokeCallReceiver = getFakeSourceForImplicitInvokeCallReceiver(callInfo);
        FirExpression explicitReceiver = callInfo.getExplicitReceiver();
        return ResolveUtilsKt.buildResolvedQualifierForClass$default(bodyResolveComponents, firClassLikeSymbol, fakeSourceForImplicitInvokeCallReceiver, explicitReceiver instanceof FirResolvedQualifier ? (FirResolvedQualifier) explicitReceiver : null, null, null, list, null, null, 216, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:48:0x010c  */
    /* JADX WARN: Code duplicated, block: B:51:0x0114  */
    /* JADX WARN: Code duplicated, block: B:54:0x011b  */
    /* JADX WARN: Code duplicated, block: B:58:0x0125  */
    /* JADX WARN: Code duplicated, block: B:60:0x0128  */
    /* JADX WARN: Code duplicated, block: B:61:0x012d  */
    /* JADX WARN: Code duplicated, block: B:63:0x0130  */
    /* JADX WARN: Code duplicated, block: B:66:0x0144  */
    /* JADX WARN: Code duplicated, block: B:67:0x0149  */
    /* JADX WARN: Code duplicated, block: B:69:0x014c  */
    private static final FirExpression createExplicitReceiverForInvokeByCallable(BodyResolveComponents bodyResolveComponents, Candidate candidate, CallInfo callInfo, boolean z, FirExpression firExpression, FirCallableSymbol<?> firCallableSymbol, List<ConeNotFunctionAsOperator> list) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirNamedReference firNamedReferenceWithCandidate;
        TypeStatement typeStatementExtractTypeStatementFrom;
        DataFlowVariable variable;
        Set<ConeKotlinType> setEmptySet;
        Set<DfaType> setEmptySet2;
        DataFlowVariable variable2;
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        KtSourceElement fakeSourceForImplicitInvokeCallReceiver = getFakeSourceForImplicitInvokeCallReceiver(callInfo);
        FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnType = bodyResolveComponents.getReturnTypeCalculator().tryCalculateReturnType((FirCallableDeclaration) firCallableSymbol.getFir());
        if (firResolvedTypeRefTryCalculateReturnType instanceof FirErrorTypeRef) {
            firNamedReferenceWithCandidate = new FirErrorReferenceWithCandidate(fakeSourceForImplicitInvokeCallReceiver, firCallableSymbol.getName(), candidate, ((FirErrorTypeRef) firResolvedTypeRefTryCalculateReturnType).getDiagnostic());
        } else {
            firNamedReferenceWithCandidate = candidate.isSuccessful() ? new FirNamedReferenceWithCandidate(fakeSourceForImplicitInvokeCallReceiver, firCallableSymbol.getName(), candidate) : new FirErrorReferenceWithCandidate(fakeSourceForImplicitInvokeCallReceiver, firCallableSymbol.getName(), candidate, ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidate.getApplicability(), candidate));
        }
        firPropertyAccessExpressionBuilder.setCalleeReference(firNamedReferenceWithCandidate);
        firPropertyAccessExpressionBuilder.setDispatchReceiver(candidate.dispatchReceiverExpression());
        firPropertyAccessExpressionBuilder.setConeTypeOrNull(firResolvedTypeRefTryCalculateReturnType.getConeType());
        if (!z) {
            firPropertyAccessExpressionBuilder.setExtensionReceiver(firExpression);
            firPropertyAccessExpressionBuilder.setExplicitReceiver(callInfo.getExplicitReceiver());
        }
        firPropertyAccessExpressionBuilder.getNonFatalDiagnostics().addAll(list);
        candidate.updateSourcesOfReceivers();
        firPropertyAccessExpressionBuilder.setSource(fakeSourceForImplicitInvokeCallReceiver);
        FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) FirCallCompleter.completeCall$default(bodyResolveComponents.getCallCompleter(), firPropertyAccessExpressionBuilder.mo288build(), ResolutionMode.ReceiverResolution.INSTANCE, false, 4, null);
        FirDataFlowAnalyzer.SmartCastStatement smartCastStatementBuildSmartCastStatement = null;
        FirPropertySymbol firPropertySymbol = firCallableSymbol instanceof FirPropertySymbol ? (FirPropertySymbol) firCallableSymbol : null;
        FirBackingFieldSymbol firBackingFieldSymbolTryAccessExplicitFieldSymbol = firPropertySymbol != null ? ExplicitFieldsUtilsKt.tryAccessExplicitFieldSymbol(firPropertySymbol, bodyResolveComponents.getInlineFunction(), bodyResolveComponents.getSession(), candidate.getHasVisibleBackingField()) : null;
        FirDataFlowAnalyzer dataFlowAnalyzer = bodyResolveComponents.getDataFlowAnalyzer();
        Flow currentSmartCastPosition = dataFlowAnalyzer.getCurrentSmartCastPosition();
        if (currentSmartCastPosition != null) {
            DataFlowVariable syntheticVariable = new SyntheticVariable(firPropertyAccessExpression);
            TypeStatement typeStatementExtractTypeStatementFrom2 = dataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable);
            if (firBackingFieldSymbolTryAccessExplicitFieldSymbol != null) {
                if (typeStatementExtractTypeStatementFrom2 == null || (variable2 = typeStatementExtractTypeStatementFrom2.getVariable()) == null) {
                    variable2 = syntheticVariable;
                }
                Set<ConeKotlinType> upperTypes = typeStatementExtractTypeStatementFrom2 != null ? typeStatementExtractTypeStatementFrom2.getUpperTypes() : null;
                if (upperTypes == null) {
                    upperTypes = SetsKt.emptySet();
                }
                PersistentSet persistentSet = ExtensionsKt.toPersistentSet(SetsKt.plus(upperTypes, firBackingFieldSymbolTryAccessExplicitFieldSymbol.getResolvedReturnType()));
                Set<DfaType> lowerTypes = typeStatementExtractTypeStatementFrom2 != null ? typeStatementExtractTypeStatementFrom2.getLowerTypes() : null;
                if (lowerTypes == null) {
                    lowerTypes = SetsKt.emptySet();
                }
                typeStatementExtractTypeStatementFrom2 = new PersistentTypeStatement(variable2, persistentSet, ExtensionsKt.toPersistentSet(lowerTypes));
            }
            if (typeStatementExtractTypeStatementFrom2 == null) {
                syntheticVariable = dataFlowAnalyzer.getVariableWithoutUnwrappingAlias(currentSmartCastPosition, firPropertyAccessExpression, false);
                if (syntheticVariable != null) {
                    typeStatementExtractTypeStatementFrom = dataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable);
                    if (firBackingFieldSymbolTryAccessExplicitFieldSymbol != null) {
                        if (typeStatementExtractTypeStatementFrom != null || (variable = typeStatementExtractTypeStatementFrom.getVariable()) == null) {
                            variable = syntheticVariable;
                        }
                        if (typeStatementExtractTypeStatementFrom != null) {
                            setEmptySet = typeStatementExtractTypeStatementFrom.getUpperTypes();
                        } else {
                            setEmptySet = null;
                        }
                        if (setEmptySet == null) {
                            setEmptySet = SetsKt.emptySet();
                        }
                        PersistentSet persistentSet2 = ExtensionsKt.toPersistentSet(SetsKt.plus(setEmptySet, firBackingFieldSymbolTryAccessExplicitFieldSymbol.getResolvedReturnType()));
                        if (typeStatementExtractTypeStatementFrom != null) {
                            setEmptySet2 = typeStatementExtractTypeStatementFrom.getLowerTypes();
                        } else {
                            setEmptySet2 = null;
                        }
                        if (setEmptySet2 == null) {
                            setEmptySet2 = SetsKt.emptySet();
                        }
                        typeStatementExtractTypeStatementFrom = new PersistentTypeStatement(variable, persistentSet2, ExtensionsKt.toPersistentSet(setEmptySet2));
                    }
                    if (typeStatementExtractTypeStatementFrom != null && typeStatementExtractTypeStatementFrom.isNotEmpty()) {
                        smartCastStatementBuildSmartCastStatement = typeStatementExtractTypeStatementFrom;
                    }
                    typeStatementExtractTypeStatementFrom2 = smartCastStatementBuildSmartCastStatement;
                    smartCastStatementBuildSmartCastStatement = dataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatementExtractTypeStatementFrom2);
                }
            } else {
                if (!typeStatementExtractTypeStatementFrom2.isNotEmpty()) {
                    typeStatementExtractTypeStatementFrom2 = null;
                }
                if (typeStatementExtractTypeStatementFrom2 == null) {
                    syntheticVariable = dataFlowAnalyzer.getVariableWithoutUnwrappingAlias(currentSmartCastPosition, firPropertyAccessExpression, false);
                    if (syntheticVariable != null) {
                        typeStatementExtractTypeStatementFrom = dataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable);
                        if (firBackingFieldSymbolTryAccessExplicitFieldSymbol != null) {
                            if (typeStatementExtractTypeStatementFrom != null) {
                                variable = syntheticVariable;
                            } else {
                                variable = syntheticVariable;
                            }
                            if (typeStatementExtractTypeStatementFrom != null) {
                                setEmptySet = typeStatementExtractTypeStatementFrom.getUpperTypes();
                            } else {
                                setEmptySet = null;
                            }
                            if (setEmptySet == null) {
                                setEmptySet = SetsKt.emptySet();
                            }
                            PersistentSet persistentSet3 = ExtensionsKt.toPersistentSet(SetsKt.plus(setEmptySet, firBackingFieldSymbolTryAccessExplicitFieldSymbol.getResolvedReturnType()));
                            if (typeStatementExtractTypeStatementFrom != null) {
                                setEmptySet2 = typeStatementExtractTypeStatementFrom.getLowerTypes();
                            } else {
                                setEmptySet2 = null;
                            }
                            if (setEmptySet2 == null) {
                                setEmptySet2 = SetsKt.emptySet();
                            }
                            typeStatementExtractTypeStatementFrom = new PersistentTypeStatement(variable, persistentSet3, ExtensionsKt.toPersistentSet(setEmptySet2));
                        }
                        if (typeStatementExtractTypeStatementFrom != null) {
                            smartCastStatementBuildSmartCastStatement = typeStatementExtractTypeStatementFrom;
                        }
                        typeStatementExtractTypeStatementFrom2 = smartCastStatementBuildSmartCastStatement;
                        smartCastStatementBuildSmartCastStatement = dataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatementExtractTypeStatementFrom2);
                    }
                } else {
                    smartCastStatementBuildSmartCastStatement = dataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatementExtractTypeStatementFrom2);
                }
            }
        }
        return smartCastStatementBuildSmartCastStatement == null ? firPropertyAccessExpression : ResolveUtilsKt.transformExpressionUsingSmartcastInfo(bodyResolveComponents, firPropertyAccessExpression, smartCastStatementBuildSmartCastStatement);
    }

    private static final KtSourceElement getFakeSourceForImplicitInvokeCallReceiver(CallInfo callInfo) {
        FirNamedReference calleeReference;
        KtSourceElement source;
        FirElement callSite = callInfo.getCallSite();
        FirFunctionCall firFunctionCall = callSite instanceof FirFunctionCall ? (FirFunctionCall) callSite : null;
        if (firFunctionCall == null || (calleeReference = firFunctionCall.getCalleeReference()) == null || (source = calleeReference.getSource()) == null) {
            return null;
        }
        return KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitInvokeCall.INSTANCE, null, 2, null);
    }
}
