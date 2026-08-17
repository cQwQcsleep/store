package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.util.MultimapKt;
import org.jetbrains.kotlin.fir.util.SetMultimap;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\u000bJ\u0006\u0010\f\u001a\u00020\u0000JF\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00142\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0014J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0017\u001a\u00020\u0006J\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J!\u0010\u001a\u001a\u00020\u0006*\u00020\u00062\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0014H\u0082\bJ4\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\u001a\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u001d0!J\u000f\u0010\"\u001a\u0004\u0018\u00010\u0010*\u00020\u0010H\u0082\u0010J\u0014\u0010#\u001a\u0006\u0012\u0002\b\u00030$*\u0006\u0012\u0002\b\u00030$H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "realVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "memberVariables", "Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/util/SetMultimap;)V", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "createSnapshot", "get", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "fir", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "createReal", Argument.Delimiters.none, "unwrapAlias", "Lkotlin/Function1;", "unwrapAliasInReceivers", "getKnown", "variable", "remember", "rememberWithKnownReceivers", "mapReceivers", "block", "replaceReceiverReferencesInMembers", Argument.Delimiters.none, "from", "to", "processMember", "Lkotlin/Function2;", "unwrapElement", "unwrapFakeOverridesIfNecessary", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VariableStorage {
    private final SetMultimap<RealVariable, RealVariable> memberVariables;
    private final Map<RealVariable, RealVariable> realVariables;
    private final FirSession session;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VariableStorage(FirSession firSession) {
        this(firSession, new HashMap(), MultimapKt.setMultimapOf());
        firSession.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DataFlowVariable get$default(VariableStorage variableStorage, FirExpression firExpression, boolean z, Function1 function1, Function1 function2, int i, Object obj) {
        if ((i & 8) != 0) {
            function2 = function1;
        }
        return variableStorage.get(firExpression, z, function1, function2);
    }

    private final RealVariable rememberWithKnownReceivers(RealVariable variable) {
        Map<RealVariable, RealVariable> map = this.realVariables;
        RealVariable realVariable = map.get(variable);
        if (realVariable == null) {
            RealVariable dispatchReceiver = variable.getDispatchReceiver();
            if (dispatchReceiver != null) {
                this.memberVariables.put(dispatchReceiver, variable);
            }
            RealVariable extensionReceiver = variable.getExtensionReceiver();
            if (extensionReceiver != null) {
                this.memberVariables.put(extensionReceiver, variable);
            }
            map.put(variable, variable);
        } else {
            variable = realVariable;
        }
        return variable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r2v1, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r2v11, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r2v14, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r2v16, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r2v3, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    private final FirExpression unwrapElement(FirExpression firExpression) {
        while (true) {
            if (firExpression instanceof FirSmartCastExpression) {
                firExpression = ((FirSmartCastExpression) firExpression).getOriginalExpression();
            } else if (firExpression instanceof FirSafeCallExpression) {
                FirStatement selector = ((FirSafeCallExpression) firExpression).getSelector();
                firExpression = selector instanceof FirExpression ? (FirExpression) selector : 0;
                if (firExpression == 0) {
                    return null;
                }
            } else if (firExpression instanceof FirCheckedSafeCallSubject) {
                firExpression = ((FirCheckedSafeCallSubject) firExpression).getOriginalReceiverRef().getValue();
            } else if (firExpression instanceof FirCheckNotNullCall) {
                firExpression = (FirExpression) CollectionsKt.first(((FirCall) firExpression).getArgumentList().getArguments());
            } else {
                if (!(firExpression instanceof FirDesugaredAssignmentValueReferenceExpression)) {
                    return firExpression;
                }
                firExpression = ((FirDesugaredAssignmentValueReferenceExpression) firExpression).getExpressionRef().getValue();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirBasedSymbol<?> unwrapFakeOverridesIfNecessary(FirBasedSymbol<?> firBasedSymbol) {
        if (!(firBasedSymbol instanceof FirCallableSymbol)) {
            return firBasedSymbol;
        }
        FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
        if (firCallableSymbol.getDispatchReceiverType() == null) {
            return firBasedSymbol;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        while (!ClassMembersKt.isIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol != null) {
            return symbol;
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
        return null;
    }

    public final VariableStorage createSnapshot() {
        FirSession firSession = this.session;
        Map mutableMap = MapsKt.toMutableMap(this.realVariables);
        SetMultimap multimapOf = MultimapKt.setMultimapOf();
        Iterator it = this.memberVariables.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            multimapOf.putAll((RealVariable) entry.getKey(), (Set) entry.getValue());
        }
        Unit unit = Unit.INSTANCE;
        return new VariableStorage(firSession, mutableMap, multimapOf);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x003f  */
    /* JADX WARN: Multi-variable type inference failed */
    public final DataFlowVariable get(FirExpression fir, boolean createReal, Function1<? super RealVariable, RealVariable> unwrapAlias, Function1<? super RealVariable, RealVariable> unwrapAliasInReceivers) {
        FirBasedSymbol<?> symbol;
        FirBasedSymbol<?> firBasedSymbolUnwrapFakeOverridesIfNecessary;
        RealVariable realVariable;
        RealVariable realVariable2;
        RealVariable realVariableRememberWithKnownReceivers;
        FirExpression extensionReceiver;
        FirExpression dispatchReceiver;
        FirCallableSymbol<?> resolvedCallableSymbol;
        fir.getClass();
        unwrapAlias.getClass();
        unwrapAliasInReceivers.getClass();
        FirExpression firExpressionUnwrapElement = unwrapElement(fir);
        if (firExpressionUnwrapElement == 0) {
            return null;
        }
        boolean z = true;
        if (!(firExpressionUnwrapElement instanceof FirThisReceiverExpression) && ((resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firExpressionUnwrapElement, this.session)) == null || !DeclarationUtilsKt.isContextParameter(resolvedCallableSymbol))) {
            z = false;
        }
        boolean z2 = z;
        if (firExpressionUnwrapElement instanceof FirResolvedQualifier) {
            FirClassLikeSymbol<?> symbol2 = ((FirResolvedQualifier) firExpressionUnwrapElement).getSymbol();
            if (symbol2 != null) {
                symbol = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(symbol2, this.session);
            } else {
                symbol = null;
            }
        } else if (!(firExpressionUnwrapElement instanceof FirCallableReferenceAccess) && (firExpressionUnwrapElement instanceof FirResolvable)) {
            symbol = FirReferenceUtilsKt.getSymbol(((FirResolvable) firExpressionUnwrapElement).getCalleeReference());
        } else {
            symbol = null;
        }
        if (symbol != null) {
            if (!z2 && !(symbol instanceof FirClassSymbol) && (!(symbol instanceof FirVariableSymbol) || (symbol instanceof FirSyntheticPropertySymbol))) {
                symbol = null;
            }
            if (symbol != null && (firBasedSymbolUnwrapFakeOverridesIfNecessary = unwrapFakeOverridesIfNecessary(symbol)) != null) {
                FirQualifiedAccessExpression firQualifiedAccessExpression = firExpressionUnwrapElement instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpressionUnwrapElement : null;
                if (firQualifiedAccessExpression == null || (dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver()) == null) {
                    realVariable = null;
                } else {
                    DataFlowVariable dataFlowVariable = get$default(this, dispatchReceiver, createReal, unwrapAliasInReceivers, null, 8, null);
                    if (dataFlowVariable == null) {
                        return null;
                    }
                    realVariable = dataFlowVariable instanceof RealVariable ? (RealVariable) dataFlowVariable : null;
                    if (realVariable == null) {
                        return new SyntheticVariable(firExpressionUnwrapElement);
                    }
                }
                if (firQualifiedAccessExpression == null || (extensionReceiver = firQualifiedAccessExpression.getExtensionReceiver()) == null) {
                    realVariable2 = null;
                } else {
                    DataFlowVariable dataFlowVariable2 = get$default(this, extensionReceiver, createReal, unwrapAliasInReceivers, null, 8, null);
                    if (dataFlowVariable2 == null) {
                        return null;
                    }
                    RealVariable realVariable3 = dataFlowVariable2 instanceof RealVariable ? (RealVariable) dataFlowVariable2 : null;
                    if (realVariable3 == null) {
                        return new SyntheticVariable(firExpressionUnwrapElement);
                    }
                    realVariable2 = realVariable3;
                }
                RealVariable realVariable4 = new RealVariable(firBasedSymbolUnwrapFakeOverridesIfNecessary, z2, realVariable, realVariable2, FirTypeUtilsKt.getResolvedType(firExpressionUnwrapElement));
                if (createReal) {
                    realVariableRememberWithKnownReceivers = rememberWithKnownReceivers(realVariable4);
                } else {
                    realVariableRememberWithKnownReceivers = this.realVariables.get(realVariable4);
                    if (realVariableRememberWithKnownReceivers == null) {
                        return null;
                    }
                }
                return (DataFlowVariable) unwrapAlias.invoke(realVariableRememberWithKnownReceivers);
            }
        }
        return new SyntheticVariable(firExpressionUnwrapElement);
    }

    public final RealVariable getKnown(RealVariable variable) {
        variable.getClass();
        return this.realVariables.get(variable);
    }

    public final RealVariable remember(RealVariable variable) {
        variable.getClass();
        FirBasedSymbol<?> symbol = variable.getSymbol();
        boolean zIsImplicit = variable.getIsImplicit();
        RealVariable dispatchReceiver = variable.getDispatchReceiver();
        RealVariable realVariableRemember = dispatchReceiver != null ? remember(dispatchReceiver) : null;
        RealVariable extensionReceiver = variable.getExtensionReceiver();
        return rememberWithKnownReceivers(new RealVariable(symbol, zIsImplicit, realVariableRemember, extensionReceiver != null ? remember(extensionReceiver) : null, variable.getOriginalType()));
    }

    public final void replaceReceiverReferencesInMembers(RealVariable from, RealVariable to, Function2<? super RealVariable, ? super RealVariable, Unit> processMember) {
        RealVariable realVariable;
        from.getClass();
        processMember.getClass();
        for (RealVariable realVariable2 : (Set) this.memberVariables.get(from)) {
            RealVariable realVariableRememberWithKnownReceivers = null;
            if (to != null) {
                FirBasedSymbol<?> symbol = realVariable2.getSymbol();
                boolean zIsImplicit = realVariable2.getIsImplicit();
                RealVariable dispatchReceiver = realVariable2.getDispatchReceiver();
                if (dispatchReceiver != null) {
                    if (Intrinsics.areEqual(dispatchReceiver, from)) {
                        dispatchReceiver = to;
                    }
                    realVariable = dispatchReceiver;
                } else {
                    realVariable = null;
                }
                RealVariable extensionReceiver = realVariable2.getExtensionReceiver();
                realVariableRememberWithKnownReceivers = rememberWithKnownReceivers(new RealVariable(symbol, zIsImplicit, realVariable, extensionReceiver != null ? Intrinsics.areEqual(extensionReceiver, from) ? to : extensionReceiver : null, realVariable2.getOriginalType()));
            }
            processMember.invoke(realVariable2, realVariableRememberWithKnownReceivers);
        }
    }

    private VariableStorage(FirSession firSession, Map<RealVariable, RealVariable> map, SetMultimap<RealVariable, RealVariable> setMultimap) {
        this.session = firSession;
        this.realVariables = map;
        this.memberVariables = setMultimap;
    }
}
