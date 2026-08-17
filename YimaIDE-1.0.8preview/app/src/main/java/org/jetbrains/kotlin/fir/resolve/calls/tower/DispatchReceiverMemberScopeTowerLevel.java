package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorFilter;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorProcessingKt;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticPropertiesScope;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStagesKt;
import org.jetbrains.kotlin.fir.resolve.calls.tower.DispatchReceiverMemberScopeTowerLevel;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002:\u00029:B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ`\u0010\u000f\u001a\u00020\u0010\"\f\b\u0000\u0010\u0011*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u001628\u0010\u0017\u001a4\u0012\u0004\u0012\u00020\u0019\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001b0\u0018¢\u0006\u0002\b\u001fH\u0002J\u000e\u0010 \u001a\u0004\u0018\u00010!*\u00020\"H\u0002J%\u0010#\u001a\u0004\u0018\u0001H\u0011\"\f\b\u0000\u0010\u0011*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010$\u001a\u0002H\u0011H\u0002¢\u0006\u0002\u0010%Jk\u0010&\u001a\u00020\u0010\"\f\b\u0000\u0010\u0011*\u0006\u0012\u0002\b\u00030\u0012*\u00020!28\u0010\u0017\u001a4\u0012\u0004\u0012\u00020\u0019\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001b0\u0018¢\u0006\u0002\b\u001f2\u0014\b\u0004\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u001b0\u001aH\u0082\bJ8\u0010(\u001a\u00020\u001b\"\f\b\u0000\u0010\u0011*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0018\u0010)\u001a\u0014\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110+0*H\u0002J\n\u0010,\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010-\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0016H\u0016J\u0018\u0010.\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0016H\u0016J\u0018\u0010/\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0016H\u0016J\u001a\u00100\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u00122\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u00101\u001a\u000202X\u0096\u0005¢\u0006\u0006\u001a\u0004\b3\u00104R\u0012\u00105\u001a\u000206X\u0096\u0005¢\u0006\u0006\u001a\u0004\b7\u00108¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "bodyResolveComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "dispatchReceiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "givenExtensionReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "skipSynthetics", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Z)V", "getDispatchReceiverValue", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "processMembers", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/ProcessResult;", "T", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "output", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevelProcessor;", "processScopeMembers", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lkotlin/Function1;", Argument.Delimiters.none, "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "processor", "Lkotlin/ExtensionFunctionType;", "cachedScopeIfAvailable", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "unwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer", "candidateFromSmartCast", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processCandidates", "candidateProcessor", "consumeCandidates", "candidatesWithSmartcast", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$MemberFromSmartcastScope;", "getOriginalReceiverExpressionIfStableSmartCast", "processFunctionsByName", "processPropertiesByName", "processObjectsByName", "hasConsistentExtensionReceiver", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "DispatchReceiverToUse", "MemberFromSmartcastScope", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DispatchReceiverMemberScopeTowerLevel extends TowerLevel implements SessionAndScopeSessionHolder {
    private final BodyResolveComponents bodyResolveComponents;
    private final ReceiverValue dispatchReceiverValue;
    private final FirExpression givenExtensionReceiver;
    private final boolean skipSynthetics;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$DispatchReceiverToUse;", Argument.Delimiters.none, "unwrapSmartcast", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;IZ)V", "getUnwrapSmartcast", "()Z", "UnwrapSmartcast", "SmartcastWithoutUnwrapping", "SmartcastIfUnwrappedInvisible", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum DispatchReceiverToUse {
        UnwrapSmartcast(true),
        SmartcastWithoutUnwrapping(false),
        SmartcastIfUnwrappedInvisible(true);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final boolean unwrapSmartcast;

        DispatchReceiverToUse(boolean z) {
            this.unwrapSmartcast = z;
        }

        public static EnumEntries<DispatchReceiverToUse> getEntries() {
            return $ENTRIES;
        }

        public final boolean getUnwrapSmartcast() {
            return this.unwrapSmartcast;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$MemberFromSmartcastScope;", "T", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", Argument.Delimiters.none, "memberWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "dispatchReceiverToUse", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$DispatchReceiverToUse;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$DispatchReceiverToUse;)V", "getMemberWithBaseScope", "()Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "getDispatchReceiverToUse", "()Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$DispatchReceiverToUse;", "setDispatchReceiverToUse", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel$DispatchReceiverToUse;)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MemberFromSmartcastScope<T extends FirCallableSymbol<?>> {
        private DispatchReceiverToUse dispatchReceiverToUse;
        private final MemberWithBaseScope<T> memberWithBaseScope;

        /* JADX WARN: Multi-variable type inference failed */
        public MemberFromSmartcastScope(MemberWithBaseScope<? extends T> memberWithBaseScope, DispatchReceiverToUse dispatchReceiverToUse) {
            memberWithBaseScope.getClass();
            dispatchReceiverToUse.getClass();
            this.memberWithBaseScope = memberWithBaseScope;
            this.dispatchReceiverToUse = dispatchReceiverToUse;
        }

        public final DispatchReceiverToUse getDispatchReceiverToUse() {
            return this.dispatchReceiverToUse;
        }

        public final MemberWithBaseScope<T> getMemberWithBaseScope() {
            return this.memberWithBaseScope;
        }

        public final void setDispatchReceiverToUse(DispatchReceiverToUse dispatchReceiverToUse) {
            dispatchReceiverToUse.getClass();
            this.dispatchReceiverToUse = dispatchReceiverToUse;
        }
    }

    public DispatchReceiverMemberScopeTowerLevel(BodyResolveComponents bodyResolveComponents, ReceiverValue receiverValue, FirExpression firExpression, boolean z) {
        bodyResolveComponents.getClass();
        receiverValue.getClass();
        this.bodyResolveComponents = bodyResolveComponents;
        this.dispatchReceiverValue = receiverValue;
        this.givenExtensionReceiver = firExpression;
        this.skipSynthetics = z;
    }

    public static Unit c(final FirLookupTrackerComponent firLookupTrackerComponent, final CallInfo callInfo, DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel, FirScope firScope, final Function1 function1) {
        firScope.getClass();
        function1.getClass();
        if (firLookupTrackerComponent != null) {
            FirLookupTrackerComponentKt.recordCallLookup(firLookupTrackerComponent, callInfo, dispatchReceiverMemberScopeTowerLevel.dispatchReceiverValue.getType());
        }
        firScope.processPropertiesByName(callInfo.getName(), new Function1() { // from class: lu3
            public final Object invoke(Object obj) {
                return DispatchReceiverMemberScopeTowerLevel.processPropertiesByName$lambda$0$0(firLookupTrackerComponent, callInfo, function1, (FirVariableSymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    private final FirTypeScope cachedScopeIfAvailable(ExpressionReceiverValue expressionReceiverValue) {
        FirTypeScope firTypeScopeCachedScopeIfAvailable$implicitReceiverScope;
        FirExpression receiverExpression = expressionReceiverValue.getReceiverExpression();
        FirThisReceiverExpression firThisReceiverExpression = receiverExpression instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) receiverExpression : null;
        return (firThisReceiverExpression == null || (firTypeScopeCachedScopeIfAvailable$implicitReceiverScope = cachedScopeIfAvailable$implicitReceiverScope(firThisReceiverExpression, this)) == null) ? cachedScopeIfAvailable$cachedLocalVariableScopeIfAvailable(expressionReceiverValue, this) : firTypeScopeCachedScopeIfAvailable$implicitReceiverScope;
    }

    private static final FirTypeScope cachedScopeIfAvailable$cachedLocalVariableScopeIfAvailable(final ExpressionReceiverValue expressionReceiverValue, final DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel) {
        return dispatchReceiverMemberScopeTowerLevel.bodyResolveComponents.getTowerDataContext().getLocalVariableScopeStorage().getScope(dispatchReceiverMemberScopeTowerLevel, expressionReceiverValue, new Function0() { // from class: hu3
            public final Object invoke() {
                return DispatchReceiverMemberScopeTowerLevel.g(this.b, expressionReceiverValue);
            }
        });
    }

    private static final FirTypeScope cachedScopeIfAvailable$implicitReceiverScope(FirThisReceiverExpression firThisReceiverExpression, DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel) {
        FirThisOwnerSymbol<?> boundSymbol = firThisReceiverExpression.getCalleeReference().getBoundSymbol();
        if (boundSymbol != null) {
            Object bySymbol = dispatchReceiverMemberScopeTowerLevel.bodyResolveComponents.getImplicitValueStorage().getBySymbol(boundSymbol);
            ReceiverValue receiverValue = bySymbol instanceof ReceiverValue ? (ReceiverValue) bySymbol : null;
            if (receiverValue != null) {
                return receiverValue.scope(dispatchReceiverMemberScopeTowerLevel);
            }
        }
        return null;
    }

    private final <T extends FirCallableSymbol<?>> void consumeCandidates(TowerLevelProcessor output, Map<T, MemberFromSmartcastScope<T>> candidatesWithSmartcast) {
        for (MemberFromSmartcastScope<T> memberFromSmartcastScope : candidatesWithSmartcast.values()) {
            MemberWithBaseScope<T> memberWithBaseScope = memberFromSmartcastScope.getMemberWithBaseScope();
            FirCallableSymbol<?> firCallableSymbolComponent1 = memberWithBaseScope.component1();
            FirTypeScope baseScope = memberWithBaseScope.getBaseScope();
            if (hasConsistentExtensionReceiver(firCallableSymbolComponent1, this.givenExtensionReceiver)) {
                DispatchReceiverToUse dispatchReceiverToUse = memberFromSmartcastScope.getDispatchReceiverToUse();
                boolean unwrapSmartcast = dispatchReceiverToUse.getUnwrapSmartcast();
                TowerLevelProcessor towerLevelProcessor = output;
                if (TowerLevelProcessor.consumeCandidate$default(towerLevelProcessor, firCallableSymbolComponent1, unwrapSmartcast ? getOriginalReceiverExpressionIfStableSmartCast() : this.dispatchReceiverValue.getReceiverExpression(), this.givenExtensionReceiver, baseScope, false, unwrapSmartcast, 16, null) == CandidateApplicability.K2_VISIBILITY_ERROR && dispatchReceiverToUse == DispatchReceiverToUse.SmartcastIfUnwrappedInvisible) {
                    TowerLevelProcessor.consumeCandidate$default(towerLevelProcessor, firCallableSymbolComponent1, this.dispatchReceiverValue.getReceiverExpression(), this.givenExtensionReceiver, baseScope, false, false, 16, null);
                }
                output = towerLevelProcessor;
            }
        }
    }

    public static Unit d(Ref.ObjectRef objectRef, TowerLevelProcessor towerLevelProcessor, DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel, FirTypeScope firTypeScope, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        objectRef.element = ProcessResult.FOUND;
        TowerLevelProcessor.consumeCandidate$default(towerLevelProcessor, firCallableSymbol, dispatchReceiverMemberScopeTowerLevel.dispatchReceiverValue.getReceiverExpression(), null, firTypeScope, false, false, 48, null);
        return Unit.INSTANCE;
    }

    public static Unit f(final FirLookupTrackerComponent firLookupTrackerComponent, final CallInfo callInfo, DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel, FirScope firScope, final Function1 function1) {
        firScope.getClass();
        function1.getClass();
        if (firLookupTrackerComponent != null) {
            FirLookupTrackerComponentKt.recordCallLookup(firLookupTrackerComponent, callInfo, dispatchReceiverMemberScopeTowerLevel.dispatchReceiverValue.getType());
        }
        ConstructorProcessingKt.processFunctionsAndConstructorsByName(firScope, callInfo, dispatchReceiverMemberScopeTowerLevel.bodyResolveComponents, ConstructorFilter.OnlyInner, new Function1() { // from class: ju3
            public final Object invoke(Object obj) {
                return DispatchReceiverMemberScopeTowerLevel.processFunctionsByName$lambda$0$0(firLookupTrackerComponent, callInfo, function1, (FirCallableSymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static DataFlowVariable g(DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel, ExpressionReceiverValue expressionReceiverValue) {
        return dispatchReceiverMemberScopeTowerLevel.bodyResolveComponents.getDataFlowAnalyzer().getOrCreateVariable(expressionReceiverValue.getReceiverExpression());
    }

    private final FirExpression getOriginalReceiverExpressionIfStableSmartCast() {
        FirExpression receiverExpression = this.dispatchReceiverValue.getReceiverExpression();
        FirSmartCastExpression firSmartCastExpression = receiverExpression instanceof FirSmartCastExpression ? (FirSmartCastExpression) receiverExpression : null;
        if (firSmartCastExpression != null) {
            if (!firSmartCastExpression.isStable()) {
                firSmartCastExpression = null;
            }
            if (firSmartCastExpression != null) {
                return firSmartCastExpression.getOriginalExpression();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasConsistentExtensionReceiver(FirCallableSymbol<?> firCallableSymbol, FirExpression firExpression) {
        return (firExpression != null) == TowerLevelsKt.hasExtensionReceiver(firCallableSymbol);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processFunctionsByName$lambda$0$0(FirLookupTrackerComponent firLookupTrackerComponent, CallInfo callInfo, Function1 function1, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firLookupTrackerComponent != null) {
            FirLookupTrackerComponentKt.recordCallableCandidateAsLookup(firLookupTrackerComponent, firCallableSymbol, callInfo.getCallSite().getSource(), callInfo.getContainingFile().getSource());
        }
        function1.invoke((FirFunctionSymbol) firCallableSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final <T extends FirCallableSymbol<?>> ProcessResult processMembers(CallInfo info, final TowerLevelProcessor output, Function2<? super FirScope, ? super Function1<? super T, Unit>, Unit> processScopeMembers) throws KotlinIllegalArgumentExceptionWithAttachments {
        final TowerLevelProcessor towerLevelProcessor;
        ConeKotlinType coneKotlinType;
        FirTypeScope firTypeScope;
        final FirTypeScope firTypeScopeCachedScopeIfAvailable = ((this.dispatchReceiverValue instanceof ExpressionReceiverValue) && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.CacheLocalVariableScopes)) ? cachedScopeIfAvailable((ExpressionReceiverValue) this.dispatchReceiverValue) : this.dispatchReceiverValue.scope(this);
        if (firTypeScopeCachedScopeIfAvailable == null) {
            return ProcessResult.SCOPE_EMPTY;
        }
        FirExpression originalReceiverExpressionIfStableSmartCast = getOriginalReceiverExpressionIfStableSmartCast();
        ConeKotlinType resolvedType = originalReceiverExpressionIfStableSmartCast != null ? FirTypeUtilsKt.getResolvedType(originalReceiverExpressionIfStableSmartCast) : null;
        final FirTypeScope firTypeScopeScope = resolvedType != null ? ScopeUtilsKt.scope(this, resolvedType, this.bodyResolveComponents.getReturnTypeCalculator().getCallableCopyTypeCalculator(), FirResolvePhase.STATUS) : null;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (firTypeScopeScope == null) {
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = ProcessResult.SCOPE_EMPTY;
            towerLevelProcessor = output;
            processScopeMembers.invoke(firTypeScopeCachedScopeIfAvailable, new Function1<T, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.DispatchReceiverMemberScopeTowerLevel$processMembers$$inlined$processCandidates$1
                /* JADX WARN: Incorrect types in method signature: (TT;)V */
                public final void invoke(FirCallableSymbol firCallableSymbol) {
                    firCallableSymbol.getClass();
                    objectRef2.element = ProcessResult.FOUND;
                    DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel = this;
                    if (dispatchReceiverMemberScopeTowerLevel.hasConsistentExtensionReceiver(firCallableSymbol, dispatchReceiverMemberScopeTowerLevel.givenExtensionReceiver)) {
                        TowerLevelProcessor.consumeCandidate$default(output, firCallableSymbol, this.getDispatchReceiverValue().getReceiverExpression(), this.givenExtensionReceiver, firTypeScopeCachedScopeIfAvailable, false, false, 16, null);
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirCallableSymbol) obj);
                    return Unit.INSTANCE;
                }
            });
            objectRef.element = (ProcessResult) objectRef2.element;
        } else {
            towerLevelProcessor = output;
            final LinkedHashMap linkedHashMap = new LinkedHashMap();
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            ProcessResult processResult = ProcessResult.SCOPE_EMPTY;
            objectRef3.element = processResult;
            processScopeMembers.invoke(firTypeScopeScope, new Function1<T, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.DispatchReceiverMemberScopeTowerLevel$processMembers$$inlined$processCandidates$2
                /* JADX WARN: Incorrect types in method signature: (TT;)V */
                public final void invoke(FirCallableSymbol firCallableSymbol) {
                    firCallableSymbol.getClass();
                    objectRef3.element = ProcessResult.FOUND;
                    DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel = this;
                    if (dispatchReceiverMemberScopeTowerLevel.hasConsistentExtensionReceiver(firCallableSymbol, dispatchReceiverMemberScopeTowerLevel.givenExtensionReceiver)) {
                        linkedHashMap.put(firCallableSymbol, new DispatchReceiverMemberScopeTowerLevel.MemberFromSmartcastScope(new MemberWithBaseScope(firCallableSymbol, firTypeScopeScope), DispatchReceiverMemberScopeTowerLevel.DispatchReceiverToUse.UnwrapSmartcast));
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirCallableSymbol) obj);
                    return Unit.INSTANCE;
                }
            });
            ProcessResult processResult2 = (ProcessResult) objectRef3.element;
            objectRef.element = processResult2;
            final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
            objectRef4.element = processResult;
            final ConeKotlinType coneKotlinType2 = resolvedType;
            Function1<T, Unit> function1 = new Function1<T, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.DispatchReceiverMemberScopeTowerLevel$processMembers$$inlined$processCandidates$3
                /* JADX WARN: Incorrect types in method signature: (TT;)V */
                public final void invoke(FirCallableSymbol firCallableSymbol) {
                    firCallableSymbol.getClass();
                    objectRef4.element = ProcessResult.FOUND;
                    DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel = this;
                    if (dispatchReceiverMemberScopeTowerLevel.hasConsistentExtensionReceiver(firCallableSymbol, dispatchReceiverMemberScopeTowerLevel.givenExtensionReceiver)) {
                        FirCallableSymbol firCallableSymbolUnwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer = this.unwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer(firCallableSymbol);
                        if (firCallableSymbolUnwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer == null) {
                            firCallableSymbolUnwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer = firCallableSymbol;
                        }
                        DispatchReceiverMemberScopeTowerLevel.MemberFromSmartcastScope memberFromSmartcastScope = (DispatchReceiverMemberScopeTowerLevel.MemberFromSmartcastScope) linkedHashMap.get(firCallableSymbolUnwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer);
                        if (memberFromSmartcastScope == null || ConeBuiltinTypeUtilsKt.isNullableNothing(this.getDispatchReceiverValue().getType()) || TypeUtilsKt.canBeNull$default(coneKotlinType2, this.getSession(), false, null, 6, null)) {
                            linkedHashMap.put(firCallableSymbol, new DispatchReceiverMemberScopeTowerLevel.MemberFromSmartcastScope(new MemberWithBaseScope(firCallableSymbol, firTypeScopeCachedScopeIfAvailable), DispatchReceiverMemberScopeTowerLevel.DispatchReceiverToUse.SmartcastWithoutUnwrapping));
                        } else {
                            memberFromSmartcastScope.setDispatchReceiverToUse(DispatchReceiverMemberScopeTowerLevel.DispatchReceiverToUse.SmartcastIfUnwrappedInvisible);
                        }
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirCallableSymbol) obj);
                    return Unit.INSTANCE;
                }
            };
            firTypeScopeCachedScopeIfAvailable = firTypeScopeCachedScopeIfAvailable;
            processScopeMembers.invoke(firTypeScopeCachedScopeIfAvailable, function1);
            objectRef.element = processResult2.plus((ProcessResult) objectRef4.element);
            consumeCandidates(towerLevelProcessor, linkedHashMap);
        }
        if (this.givenExtensionReceiver == null && !this.skipSynthetics) {
            ConeKotlinType type = this.dispatchReceiverValue.getType();
            if (TypeUtilsKt.isRaw(type)) {
                ConeKotlinType coneKotlinTypeConvertToNonRawVersion = TypeUtilsKt.convertToNonRawVersion(type);
                FirTypeScope firTypeScopeScope2 = ScopeUtilsKt.scope(this, coneKotlinTypeConvertToNonRawVersion, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
                if (firTypeScopeScope2 == null) {
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("No scope for flexible type scope, while it's not null", (Throwable) null);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, "dispatchReceiverType", type);
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
                coneKotlinType = coneKotlinTypeConvertToNonRawVersion;
                firTypeScope = firTypeScopeScope2;
            } else {
                coneKotlinType = type;
                firTypeScope = firTypeScopeCachedScopeIfAvailable;
            }
            FirSyntheticPropertiesScope firSyntheticPropertiesScopeCreateIfSyntheticNamesProviderIsDefined = FirSyntheticPropertiesScope.INSTANCE.createIfSyntheticNamesProviderIsDefined(getSession(), coneKotlinType, firTypeScope, this.bodyResolveComponents.getReturnTypeCalculator(), ResolutionStagesKt.isSuperCall(info.getCallSite()));
            if (firSyntheticPropertiesScopeCreateIfSyntheticNamesProviderIsDefined != null) {
                processScopeMembers.invoke(firSyntheticPropertiesScopeCreateIfSyntheticNamesProviderIsDefined, new Function1() { // from class: gu3
                    public final Object invoke(Object obj) {
                        return DispatchReceiverMemberScopeTowerLevel.d(objectRef, towerLevelProcessor, this, firTypeScopeCachedScopeIfAvailable, (FirCallableSymbol) obj);
                    }
                });
            }
        }
        return (ProcessResult) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processPropertiesByName$lambda$0$0(FirLookupTrackerComponent firLookupTrackerComponent, CallInfo callInfo, Function1 function1, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firLookupTrackerComponent != null) {
            FirLookupTrackerComponentKt.recordCallableCandidateAsLookup(firLookupTrackerComponent, firVariableSymbol, callInfo.getCallSite().getSource(), callInfo.getContainingFile().getSource());
        }
        function1.invoke(firVariableSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T extends FirCallableSymbol<?>> T unwrapSubstitutionOverrideForSmartcastedThisAccessInAnonymousInitializer(T candidateFromSmartCast) {
        if (!ClassMembersKt.isSubstitutionOverride(candidateFromSmartCast)) {
            return null;
        }
        FirExpression receiverExpression = this.dispatchReceiverValue.getReceiverExpression();
        FirSmartCastExpression firSmartCastExpression = receiverExpression instanceof FirSmartCastExpression ? (FirSmartCastExpression) receiverExpression : null;
        if (firSmartCastExpression == null) {
            return null;
        }
        FirExpression originalExpression = firSmartCastExpression.getOriginalExpression();
        FirThisReceiverExpression firThisReceiverExpression = originalExpression instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) originalExpression : null;
        if (firThisReceiverExpression == null) {
            return null;
        }
        FirThisOwnerSymbol<?> boundSymbol = firThisReceiverExpression.getCalleeReference().getBoundSymbol();
        FirClassSymbol firClassSymbol = boundSymbol instanceof FirClassSymbol ? (FirClassSymbol) boundSymbol : null;
        if (firClassSymbol == null || !this.bodyResolveComponents.getTowerDataContext().getClassesUnderInitialization().contains(firClassSymbol)) {
            return null;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) candidateFromSmartCast.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
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

    public final ReceiverValue getDispatchReceiverValue() {
        return this.dispatchReceiverValue;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.bodyResolveComponents.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.bodyResolveComponents.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.TowerLevel
    public ProcessResult processFunctionsByName(final CallInfo info, TowerLevelProcessor processor) {
        info.getClass();
        processor.getClass();
        final FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        return processMembers(info, processor, new Function2() { // from class: iu3
            public final Object invoke(Object obj, Object obj2) {
                return DispatchReceiverMemberScopeTowerLevel.f(lookupTracker, info, this, (FirScope) obj, (Function1) obj2);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.TowerLevel
    public ProcessResult processObjectsByName(CallInfo info, TowerLevelProcessor processor) {
        info.getClass();
        processor.getClass();
        return ProcessResult.FOUND;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.TowerLevel
    public ProcessResult processPropertiesByName(final CallInfo info, TowerLevelProcessor processor) {
        info.getClass();
        processor.getClass();
        final FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        return processMembers(info, processor, new Function2() { // from class: ku3
            public final Object invoke(Object obj, Object obj2) {
                return DispatchReceiverMemberScopeTowerLevel.c(lookupTracker, info, this, (FirScope) obj, (Function1) obj2);
            }
        });
    }
}
