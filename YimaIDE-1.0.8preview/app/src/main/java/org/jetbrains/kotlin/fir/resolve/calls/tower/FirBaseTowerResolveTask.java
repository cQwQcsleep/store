package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.CompanionExtensionPolicy;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorFilter;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u0015H\u0016JP\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\"2\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00180\"H\u0084H¢\u0006\u0002\u0010$J@\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\"H\u0084H¢\u0006\u0002\u0010&JB\u0010'\u001a\u00020\u001b*\u00020(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\n\b\u0002\u00101\u001a\u0004\u0018\u000102H\u0004J\u000e\u00103\u001a\u00020.*\u0004\u0018\u00010*H\u0002J$\u00104\u001a\u00020\u001b*\u00020(2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\n\b\u0002\u00107\u001a\u0004\u0018\u000108H\u0004J\"\u00109\u001a\u00020:*\u00020*2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010;\u001a\u00020,H\u0004Jr\u0010<\u001a\u00020\u00182\b\b\u0002\u0010=\u001a\u00020\u00152 \u0010>\u001a\u001c\u0012\u0004\u0012\u00020(\u0012\u0006\u0012\u0004\u0018\u000106\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180?2\u001c\u0010@\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030B\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180A2\u001a\b\u0002\u0010C\u001a\u0014\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180AH\u0084\bø\u0001\u0000J.\u0010\u0019\u001a\u00020,2\u0006\u0010\u001a\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0082@¢\u0006\u0002\u0010DR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010E\u001a\u00020FX\u0096\u0005¢\u0006\u0006\u001a\u0004\bG\u0010H\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006I"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirBaseTowerResolveTask;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "manager", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", "towerDataElementsForName", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "getTowerDataElementsForName", "()Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", "handler", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevelHandler;", "interceptTowerGroup", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "towerGroup", "onSuccessfulLevel", Argument.Delimiters.none, "processLevel", "towerLevel", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/ScopeBasedTowerLevel;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "group", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "onEmptyLevel", "Lkotlin/Function0;", "onNoCompanionExtensions", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/ScopeBasedTowerLevel;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toScopeBasedTowerLevel", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "extensionReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "withHideMembersOnly", Argument.Delimiters.none, "constructorFilter", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConstructorFilter;", "companionExtensionPolicy", "Lorg/jetbrains/kotlin/fir/resolve/calls/CompanionExtensionPolicy;", "dispatchReceiverForStatics", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "toConstructorFilter", "toScopeBasedTowerLevelForStaticWithImplicitDispatchReceiver", "staticOwnerOwnerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "toDispatchReceiverMemberScopeTowerLevel", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/DispatchReceiverMemberScopeTowerLevel;", "skipSynthetics", "enumerateTowerLevels", "parentGroup", "onScope", "Lkotlin/Function3;", "onImplicitReceiver", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "onStaticScopeOwnerSymbol", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirBaseTowerResolveTask implements SessionHolder {
    private final CandidateFactory candidateFactory;
    private final CandidateCollector collector;
    private final BodyResolveComponents components;
    private final TowerLevelHandler handler;
    private final TowerResolveManager manager;
    private final TowerDataElementsForName towerDataElementsForName;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask$processLevel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
    public static final class C00371 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C00371(Continuation<? super C00371> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirBaseTowerResolveTask.this.processLevel(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask$processLevel$4, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
    public static final class AnonymousClass4 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirBaseTowerResolveTask.this.processLevel(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask$processLevel$6, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass6 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirBaseTowerResolveTask.this.processLevel(null, null, null, null, this);
        }
    }

    public FirBaseTowerResolveTask(BodyResolveComponents bodyResolveComponents, TowerResolveManager towerResolveManager, TowerDataElementsForName towerDataElementsForName, CandidateCollector candidateCollector, CandidateFactory candidateFactory) {
        bodyResolveComponents.getClass();
        towerResolveManager.getClass();
        towerDataElementsForName.getClass();
        candidateCollector.getClass();
        candidateFactory.getClass();
        this.components = bodyResolveComponents;
        this.manager = towerResolveManager;
        this.towerDataElementsForName = towerDataElementsForName;
        this.collector = candidateCollector;
        this.candidateFactory = candidateFactory;
        this.handler = new TowerLevelHandler();
    }

    public static /* synthetic */ void enumerateTowerLevels$default(FirBaseTowerResolveTask firBaseTowerResolveTask, TowerGroup towerGroup, Function3 function3, Function2 function2, Function2 function4, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: enumerateTowerLevels");
            return;
        }
        if ((i & 1) != 0) {
            towerGroup = TowerGroup.INSTANCE.getEmptyRoot();
        }
        if ((i & 8) != 0) {
            function4 = new Function2<FirRegularClassSymbol, TowerGroup, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask.enumerateTowerLevels.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((FirRegularClassSymbol) obj2, (TowerGroup) obj3);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirRegularClassSymbol firRegularClassSymbol, TowerGroup towerGroup2) {
                    firRegularClassSymbol.getClass();
                    towerGroup2.getClass();
                }
            };
        }
        towerGroup.getClass();
        function3.getClass();
        function2.getClass();
        function4.getClass();
        for (IndexedValue<FirLocalScope> indexedValue : firBaseTowerResolveTask.towerDataElementsForName.getReversedFilteredLocalScopes()) {
            function3.invoke(indexedValue.component2(), (Object) null, towerGroup.Local(indexedValue.getIndex()));
        }
        int i2 = 0;
        for (FirTowerDataElement firTowerDataElement : firBaseTowerResolveTask.towerDataElementsForName.getNonLocalTowerDataElements()) {
            int i3 = i2 + 1;
            if (!firTowerDataElement.getIsLocal()) {
                FirScope scope = firTowerDataElement.getScope();
                FirRegularClassSymbol staticScopeOwnerSymbol = firTowerDataElement.getStaticScopeOwnerSymbol();
                if (scope != null) {
                    function3.invoke(scope, staticScopeOwnerSymbol, towerGroup.NonLocal(i2));
                }
                if (staticScopeOwnerSymbol != null) {
                    function4.invoke(staticScopeOwnerSymbol, towerGroup.NonLocal(i2));
                }
            }
            ImplicitReceiverValue<?> implicitReceiver = firTowerDataElement.getImplicitReceiver();
            if (implicitReceiver != null) {
                function2.invoke(implicitReceiver, towerGroup.Implicit(i2));
            }
            i2 = i3;
        }
    }

    public static /* synthetic */ Object processLevel$default(FirBaseTowerResolveTask firBaseTowerResolveTask, ScopeBasedTowerLevel scopeBasedTowerLevel, CallInfo callInfo, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Function0 function0, Function0 function1, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: processLevel");
            return null;
        }
        if ((i & 8) != 0) {
            explicitReceiverKind = ExplicitReceiverKind.NO_EXPLICIT_RECEIVER;
        }
        ExplicitReceiverKind explicitReceiverKind2 = explicitReceiverKind;
        if ((i & 16) != 0) {
            function0 = new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask.processLevel.2
                public /* bridge */ /* synthetic */ Object invoke() {
                    m587invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m587invoke() {
                }
            };
        }
        if ((i & 32) != 0) {
            function1 = new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask.processLevel.3
                public /* bridge */ /* synthetic */ Object invoke() {
                    m588invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m588invoke() {
                }
            };
        }
        scopeBasedTowerLevel.getClass();
        InlineMarker.mark(0);
        Object objProcessLevel = firBaseTowerResolveTask.processLevel(scopeBasedTowerLevel, callInfo, towerGroup, explicitReceiverKind2, continuation);
        InlineMarker.mark(1);
        if (((Boolean) objProcessLevel).booleanValue()) {
            function0.invoke();
        } else if (!scopeBasedTowerLevel.getHasFoundCompanionExtensions()) {
            function1.invoke();
        }
        return Unit.INSTANCE;
    }

    private final ConstructorFilter toConstructorFilter(ReceiverValue receiverValue) {
        return receiverValue == null ? ConstructorFilter.OnlyNested : ConstructorFilter.Both;
    }

    public static /* synthetic */ DispatchReceiverMemberScopeTowerLevel toDispatchReceiverMemberScopeTowerLevel$default(FirBaseTowerResolveTask firBaseTowerResolveTask, ReceiverValue receiverValue, ReceiverValue receiverValue2, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toDispatchReceiverMemberScopeTowerLevel");
            return null;
        }
        if ((i & 1) != 0) {
            receiverValue2 = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return firBaseTowerResolveTask.toDispatchReceiverMemberScopeTowerLevel(receiverValue, receiverValue2, z);
    }

    public static /* synthetic */ ScopeBasedTowerLevel toScopeBasedTowerLevel$default(FirBaseTowerResolveTask firBaseTowerResolveTask, FirScope firScope, ReceiverValue receiverValue, boolean z, ConstructorFilter constructorFilter, CompanionExtensionPolicy companionExtensionPolicy, ExpressionReceiverValue expressionReceiverValue, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toScopeBasedTowerLevel");
            return null;
        }
        if ((i & 1) != 0) {
            receiverValue = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            constructorFilter = firBaseTowerResolveTask.toConstructorFilter(receiverValue);
        }
        if ((i & 8) != 0) {
            companionExtensionPolicy = CompanionExtensionPolicy.NoCompanionExtensions;
        }
        if ((i & 16) != 0) {
            expressionReceiverValue = null;
        }
        return firBaseTowerResolveTask.toScopeBasedTowerLevel(firScope, receiverValue, z, constructorFilter, companionExtensionPolicy, expressionReceiverValue);
    }

    public static /* synthetic */ ScopeBasedTowerLevel toScopeBasedTowerLevelForStaticWithImplicitDispatchReceiver$default(FirBaseTowerResolveTask firBaseTowerResolveTask, FirScope firScope, FirRegularClassSymbol firRegularClassSymbol, KtSourceElement ktSourceElement, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toScopeBasedTowerLevelForStaticWithImplicitDispatchReceiver");
            return null;
        }
        if ((i & 1) != 0) {
            firRegularClassSymbol = null;
        }
        if ((i & 2) != 0) {
            ktSourceElement = null;
        }
        return firBaseTowerResolveTask.toScopeBasedTowerLevelForStaticWithImplicitDispatchReceiver(firScope, firRegularClassSymbol, ktSourceElement);
    }

    public final void enumerateTowerLevels(TowerGroup parentGroup, Function3<? super FirScope, ? super FirRegularClassSymbol, ? super TowerGroup, Unit> onScope, Function2<? super ImplicitReceiverValue<?>, ? super TowerGroup, Unit> onImplicitReceiver, Function2<? super FirRegularClassSymbol, ? super TowerGroup, Unit> onStaticScopeOwnerSymbol) {
        parentGroup.getClass();
        onScope.getClass();
        onImplicitReceiver.getClass();
        onStaticScopeOwnerSymbol.getClass();
        for (IndexedValue<FirLocalScope> indexedValue : this.towerDataElementsForName.getReversedFilteredLocalScopes()) {
            onScope.invoke(indexedValue.component2(), (Object) null, parentGroup.Local(indexedValue.getIndex()));
        }
        int i = 0;
        for (FirTowerDataElement firTowerDataElement : this.towerDataElementsForName.getNonLocalTowerDataElements()) {
            int i2 = i + 1;
            if (!firTowerDataElement.getIsLocal()) {
                FirScope scope = firTowerDataElement.getScope();
                FirRegularClassSymbol staticScopeOwnerSymbol = firTowerDataElement.getStaticScopeOwnerSymbol();
                if (scope != null) {
                    onScope.invoke(scope, staticScopeOwnerSymbol, parentGroup.NonLocal(i));
                }
                if (staticScopeOwnerSymbol != null) {
                    onStaticScopeOwnerSymbol.invoke(staticScopeOwnerSymbol, parentGroup.NonLocal(i));
                }
            }
            ImplicitReceiverValue<?> implicitReceiver = firTowerDataElement.getImplicitReceiver();
            if (implicitReceiver != null) {
                onImplicitReceiver.invoke(implicitReceiver, parentGroup.Implicit(i));
            }
            i = i2;
        }
    }

    public final BodyResolveComponents getComponents() {
        return this.components;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.components.getSession();
    }

    public final TowerDataElementsForName getTowerDataElementsForName() {
        return this.towerDataElementsForName;
    }

    public TowerGroup interceptTowerGroup(TowerGroup towerGroup) {
        towerGroup.getClass();
        return towerGroup;
    }

    public void onSuccessfulLevel(TowerGroup towerGroup) {
        towerGroup.getClass();
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object processLevel(ScopeBasedTowerLevel scopeBasedTowerLevel, CallInfo callInfo, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Function0<Unit> function0, Function0<Unit> function1, Continuation<? super Unit> continuation) {
        C00371 c00371;
        ScopeBasedTowerLevel scopeBasedTowerLevel2;
        if (continuation instanceof C00371) {
            c00371 = (C00371) continuation;
            int i = c00371.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00371.label = i - Integer.MIN_VALUE;
            } else {
                c00371 = new C00371(continuation);
            }
        } else {
            c00371 = new C00371(continuation);
        }
        C00371 c00372 = c00371;
        Object objProcessLevel = c00372.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00372.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            scopeBasedTowerLevel.getClass();
            c00372.L$0 = scopeBasedTowerLevel;
            c00372.L$1 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00372.L$2 = SpillingKt.nullOutSpilledVariable(towerGroup);
            c00372.L$3 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            c00372.L$4 = function0;
            c00372.L$5 = function1;
            c00372.label = 1;
            objProcessLevel = processLevel(scopeBasedTowerLevel, callInfo, towerGroup, explicitReceiverKind, c00372);
            if (objProcessLevel == coroutine_suspended) {
                return coroutine_suspended;
            }
            scopeBasedTowerLevel2 = scopeBasedTowerLevel;
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            function1 = (Function0) c00372.L$5;
            function0 = (Function0) c00372.L$4;
            scopeBasedTowerLevel2 = (ScopeBasedTowerLevel) c00372.L$0;
            ResultKt.throwOnFailure(objProcessLevel);
        }
        if (((Boolean) objProcessLevel).booleanValue()) {
            function0.invoke();
        } else if (!scopeBasedTowerLevel2.getHasFoundCompanionExtensions()) {
            function1.invoke();
        }
        return Unit.INSTANCE;
    }

    public final DispatchReceiverMemberScopeTowerLevel toDispatchReceiverMemberScopeTowerLevel(ReceiverValue receiverValue, ReceiverValue receiverValue2, boolean z) {
        receiverValue.getClass();
        return new DispatchReceiverMemberScopeTowerLevel(this.components, receiverValue, receiverValue2 != null ? receiverValue2.getReceiverExpression() : null, z);
    }

    public final ScopeBasedTowerLevel toScopeBasedTowerLevel(FirScope firScope, ReceiverValue receiverValue, boolean z, ConstructorFilter constructorFilter, CompanionExtensionPolicy companionExtensionPolicy, ExpressionReceiverValue expressionReceiverValue) {
        firScope.getClass();
        constructorFilter.getClass();
        companionExtensionPolicy.getClass();
        return new ScopeBasedTowerLevel(this.components, firScope, receiverValue != null ? receiverValue.getReceiverExpression() : null, z, constructorFilter, companionExtensionPolicy, expressionReceiverValue);
    }

    public final ScopeBasedTowerLevel toScopeBasedTowerLevelForStaticWithImplicitDispatchReceiver(FirScope firScope, FirRegularClassSymbol firRegularClassSymbol, KtSourceElement ktSourceElement) {
        ExpressionReceiverValue expressionReceiverValue;
        firScope.getClass();
        ConstructorFilter constructorFilter = ConstructorFilter.OnlyNested;
        if (firRegularClassSymbol != null) {
            expressionReceiverValue = new ExpressionReceiverValue(ResolveUtilsKt.toImplicitResolvedQualifierReceiver(firRegularClassSymbol, this.components, ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ImplicitReceiver.INSTANCE, null, 2, null) : null));
        } else {
            expressionReceiverValue = null;
        }
        return toScopeBasedTowerLevel$default(this, firScope, null, false, constructorFilter, null, expressionReceiverValue, 8, null);
    }

    public static /* synthetic */ Object processLevel$default(FirBaseTowerResolveTask firBaseTowerResolveTask, TowerLevel towerLevel, CallInfo callInfo, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Function0 function0, Continuation continuation, int i, Object obj) {
        if (obj == null) {
            if ((i & 8) != 0) {
                explicitReceiverKind = ExplicitReceiverKind.NO_EXPLICIT_RECEIVER;
            }
            ExplicitReceiverKind explicitReceiverKind2 = explicitReceiverKind;
            if ((i & 16) != 0) {
                function0 = new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask.processLevel.5
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m589invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m589invoke() {
                    }
                };
            }
            InlineMarker.mark(0);
            Object objProcessLevel = firBaseTowerResolveTask.processLevel(towerLevel, callInfo, towerGroup, explicitReceiverKind2, continuation);
            InlineMarker.mark(1);
            if (((Boolean) objProcessLevel).booleanValue()) {
                function0.invoke();
            }
            return Unit.INSTANCE;
        }
        c41.a("Super calls with default arguments not supported in this target, function: processLevel");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object processLevel(TowerLevel towerLevel, CallInfo callInfo, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Function0<Unit> function0, Continuation<? super Unit> continuation) {
        AnonymousClass4 anonymousClass4;
        if (continuation instanceof AnonymousClass4) {
            anonymousClass4 = (AnonymousClass4) continuation;
            int i = anonymousClass4.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass4.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass4 = new AnonymousClass4(continuation);
            }
        } else {
            anonymousClass4 = new AnonymousClass4(continuation);
        }
        AnonymousClass4 anonymousClass5 = anonymousClass4;
        Object objProcessLevel = anonymousClass5.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass5.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            anonymousClass5.L$0 = SpillingKt.nullOutSpilledVariable(towerLevel);
            anonymousClass5.L$1 = SpillingKt.nullOutSpilledVariable(callInfo);
            anonymousClass5.L$2 = SpillingKt.nullOutSpilledVariable(towerGroup);
            anonymousClass5.L$3 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            anonymousClass5.L$4 = function0;
            anonymousClass5.label = 1;
            objProcessLevel = processLevel(towerLevel, callInfo, towerGroup, explicitReceiverKind, anonymousClass5);
            if (objProcessLevel == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            function0 = (Function0) anonymousClass5.L$4;
            ResultKt.throwOnFailure(objProcessLevel);
        }
        if (((Boolean) objProcessLevel).booleanValue()) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public final Object processLevel(TowerLevel towerLevel, CallInfo callInfo, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Continuation<? super Boolean> continuation) {
        AnonymousClass6 anonymousClass6;
        TowerLevel towerLevel2;
        CallInfo callInfo2;
        ExplicitReceiverKind explicitReceiverKind2;
        TowerGroup towerGroup2;
        if (continuation instanceof AnonymousClass6) {
            anonymousClass6 = (AnonymousClass6) continuation;
            int i = anonymousClass6.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass6.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass6 = new AnonymousClass6(continuation);
            }
        } else {
            anonymousClass6 = new AnonymousClass6(continuation);
        }
        Object obj = anonymousClass6.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass6.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            TowerGroup towerGroupInterceptTowerGroup = interceptTowerGroup(towerGroup);
            TowerResolveManager towerResolveManager = this.manager;
            anonymousClass6.L$0 = towerLevel;
            anonymousClass6.L$1 = callInfo;
            anonymousClass6.L$2 = SpillingKt.nullOutSpilledVariable(towerGroup);
            anonymousClass6.L$3 = explicitReceiverKind;
            anonymousClass6.L$4 = towerGroupInterceptTowerGroup;
            anonymousClass6.label = 1;
            if (towerResolveManager.requestGroup(towerGroupInterceptTowerGroup, anonymousClass6) == coroutine_suspended) {
                return coroutine_suspended;
            }
            towerLevel2 = towerLevel;
            callInfo2 = callInfo;
            explicitReceiverKind2 = explicitReceiverKind;
            towerGroup2 = towerGroupInterceptTowerGroup;
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            TowerGroup towerGroup3 = (TowerGroup) anonymousClass6.L$4;
            ExplicitReceiverKind explicitReceiverKind3 = (ExplicitReceiverKind) anonymousClass6.L$3;
            CallInfo callInfo3 = (CallInfo) anonymousClass6.L$1;
            TowerLevel towerLevel3 = (TowerLevel) anonymousClass6.L$0;
            ResultKt.throwOnFailure(obj);
            towerGroup2 = towerGroup3;
            explicitReceiverKind2 = explicitReceiverKind3;
            towerLevel2 = towerLevel3;
            callInfo2 = callInfo3;
        }
        ProcessResult processResultHandleLevel = this.handler.handleLevel(this.collector, this.candidateFactory, callInfo2, explicitReceiverKind2, towerGroup2, towerLevel2);
        if (this.collector.isSuccess()) {
            onSuccessfulLevel(towerGroup2);
        }
        return Boxing.boxBoolean(processResultHandleLevel == ProcessResult.SCOPE_EMPTY);
    }
}
