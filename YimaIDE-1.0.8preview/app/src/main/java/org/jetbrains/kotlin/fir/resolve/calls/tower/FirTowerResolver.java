package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.MissingInnerClassConstructorReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ,\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u0013J0\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eH\u0002J*\u0010\u0018\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u001d\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001e\u001a\u00020\u001fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b \u0010!R\u0012\u0010\"\u001a\u00020#X\u0096\u0005¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirTowerResolver;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;)V", "manager", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", "runResolver", "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "externalCollector", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "enqueueResolutionTasks", Argument.Delimiters.none, "candidateFactoriesAndCollectors", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/CandidateFactoriesAndCollectors;", "runResolverForDelegatingConstructor", "constructedType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "derivedClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "reset", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTowerResolver implements SessionAndScopeSessionHolder {
    private final CandidateCollector collector;
    private final BodyResolveComponents components;
    private final TowerResolveManager manager;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver$enqueueResolutionTasks$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ FirTowerResolveTask $mainTask;
        final /* synthetic */ FirExpression $receiver;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, FirExpression firExpression, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$mainTask = firTowerResolveTask;
            this.$info = callInfo;
            this.$receiver = firExpression;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$mainTask, this.$info, this.$receiver, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FirTowerResolveTask firTowerResolveTask = this.$mainTask;
                CallInfo callInfo = this.$info;
                FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) this.$receiver;
                this.label = 1;
                if (firTowerResolveTask.runResolverForQualifierReceiver(callInfo, firResolvedQualifier, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver$enqueueResolutionTasks$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ FirTowerResolveTask $mainTask;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$mainTask = firTowerResolveTask;
            this.$info = callInfo;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$mainTask, this.$info, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FirTowerResolveTask firTowerResolveTask = this.$mainTask;
                CallInfo callInfo = this.$info;
                this.label = 1;
                if (FirTowerResolveTask.runResolverForNoReceiver$default(firTowerResolveTask, callInfo, false, this, 2, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver$enqueueResolutionTasks$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ FirTowerResolveTask $mainTask;
        final /* synthetic */ FirExpression $receiver;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, FirExpression firExpression, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$mainTask = firTowerResolveTask;
            this.$info = callInfo;
            this.$receiver = firExpression;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$mainTask, this.$info, this.$receiver, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FirTowerResolveTask firTowerResolveTask = this.$mainTask;
                CallInfo callInfo = this.$info;
                FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) this.$receiver;
                this.label = 1;
                if (firTowerResolveTask.runResolverForSuperReceiver(callInfo, firQualifiedAccessExpression, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver$enqueueResolutionTasks$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass4 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ FirTowerResolveTask $mainTask;
        final /* synthetic */ FirExpression $receiver;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, FirExpression firExpression, Continuation<? super AnonymousClass4> continuation) {
            super(1, continuation);
            this.$mainTask = firTowerResolveTask;
            this.$info = callInfo;
            this.$receiver = firExpression;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass4(this.$mainTask, this.$info, this.$receiver, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FirTowerResolveTask firTowerResolveTask = this.$mainTask;
                CallInfo callInfo = this.$info;
                FirExpression firExpression = this.$receiver;
                this.label = 1;
                if (FirTowerResolveTask.runResolverForExpressionReceiver$default(firTowerResolveTask, callInfo, firExpression, null, this, 4, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public FirTowerResolver(BodyResolveComponents bodyResolveComponents, ResolutionStageRunner resolutionStageRunner, CandidateCollector candidateCollector) {
        bodyResolveComponents.getClass();
        resolutionStageRunner.getClass();
        candidateCollector.getClass();
        this.components = bodyResolveComponents;
        this.collector = candidateCollector;
        this.manager = new TowerResolveManager(candidateCollector);
    }

    public static Unit b(CandidateCollector candidateCollector, CandidateFactory candidateFactory, CallInfo callInfo, FirTypeScope firTypeScope, ImplicitReceiverValue implicitReceiverValue, ResolutionContext resolutionContext, ConeClassLikeType coneClassLikeType, ConeClassLikeType coneClassLikeType2, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        TowerGroup member = TowerGroup.INSTANCE.getMember();
        Candidate candidateCreateCandidate$default = CandidateFactory.createCandidate$default(candidateFactory, callInfo, firConstructorSymbol, ExplicitReceiverKind.NO_EXPLICIT_RECEIVER, firTypeScope, implicitReceiverValue != null ? implicitReceiverValue.getReceiverExpression() : null, null, false, false, 192, null);
        if (coneClassLikeType != null && implicitReceiverValue == null) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeType2, resolutionContext.getSession());
            candidateCreateCandidate$default.addDiagnostic(regularClassSymbol != null ? new MissingInnerClassConstructorReceiver(regularClassSymbol) : InapplicableCandidate.INSTANCE);
        }
        Unit unit = Unit.INSTANCE;
        candidateCollector.consumeCandidate(member, candidateCreateCandidate$default, resolutionContext);
        return Unit.INSTANCE;
    }

    private final void enqueueResolutionTasks(ResolutionContext context, TowerResolveManager manager, CandidateFactoriesAndCollectors candidateFactoriesAndCollectors, CallInfo info) {
        FirInvokeResolveTowerExtension firInvokeResolveTowerExtension = new FirInvokeResolveTowerExtension(context, manager, candidateFactoriesAndCollectors);
        FirTowerResolveTask firTowerResolveTask = new FirTowerResolveTask(this.components, manager, new TowerDataElementsForName(info.getName(), this.components.getTowerDataContext()), candidateFactoriesAndCollectors.getResultCollector(), candidateFactoriesAndCollectors.getCandidateFactory());
        FirExpression explicitReceiver = info.getExplicitReceiver();
        if (explicitReceiver instanceof FirResolvedQualifier) {
            TowerResolveManager.enqueueResolverTask$default(manager, null, new AnonymousClass1(firTowerResolveTask, info, explicitReceiver, null), 1, null);
            firInvokeResolveTowerExtension.enqueueResolveTasksForQualifier(info, (FirResolvedQualifier) explicitReceiver);
            return;
        }
        if (explicitReceiver == null) {
            TowerResolveManager.enqueueResolverTask$default(manager, null, new AnonymousClass2(firTowerResolveTask, info, null), 1, null);
            firInvokeResolveTowerExtension.enqueueResolveTasksForNoReceiver(info);
        } else if (explicitReceiver instanceof FirSuperReceiverExpression) {
            TowerResolveManager.enqueueResolverTask$default(manager, null, new AnonymousClass3(firTowerResolveTask, info, explicitReceiver, null), 1, null);
            firInvokeResolveTowerExtension.enqueueResolveTasksForSuperReceiver(info, (FirQualifiedAccessExpression) explicitReceiver);
        } else if (info.isImplicitInvoke()) {
            firInvokeResolveTowerExtension.enqueueResolveTasksForImplicitInvokeCall(info, explicitReceiver);
        } else {
            TowerResolveManager.enqueueResolverTask$default(manager, null, new AnonymousClass4(firTowerResolveTask, info, explicitReceiver, null), 1, null);
            firInvokeResolveTowerExtension.enqueueResolveTasksForExpressionReceiver(info, explicitReceiver);
        }
    }

    public static /* synthetic */ CandidateCollector runResolver$default(FirTowerResolver firTowerResolver, CallInfo callInfo, ResolutionContext resolutionContext, CandidateCollector candidateCollector, TowerResolveManager towerResolveManager, CandidateFactory candidateFactory, int i, Object obj) {
        if ((i & 16) != 0) {
            candidateFactory = new CandidateFactory(resolutionContext, callInfo);
        }
        return firTowerResolver.runResolver(callInfo, resolutionContext, candidateCollector, towerResolveManager, candidateFactory);
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.components.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.components.getSession();
    }

    public final void reset() {
        this.collector.newDataSet();
        this.manager.reset();
    }

    public final CandidateCollector runResolver(CallInfo info, ResolutionContext context, CandidateCollector collector, TowerResolveManager manager, CandidateFactory candidateFactory) {
        info.getClass();
        context.getClass();
        collector.getClass();
        manager.getClass();
        candidateFactory.getClass();
        enqueueResolutionTasks(context, manager, new CandidateFactoriesAndCollectors(candidateFactory, collector), info);
        manager.runTasks();
        return collector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue] */
    public final CandidateCollector runResolverForDelegatingConstructor(final CallInfo info, final ConeClassLikeType constructedType, FirClassSymbol<?> derivedClass, final ResolutionContext context) {
        info.getClass();
        constructedType.getClass();
        derivedClass.getClass();
        context.getClass();
        final ConeClassLikeType coneClassLikeTypeOuterType = this.components.getOuterClassManager().outerType(constructedType);
        final FirTypeScope firTypeScopeDelegatingConstructorScope = ScopeUtilsKt.delegatingConstructorScope(this, constructedType, derivedClass, coneClassLikeTypeOuterType);
        if (firTypeScopeDelegatingConstructorScope == null) {
            return this.collector;
        }
        Object obj = null;
        if (coneClassLikeTypeOuterType != null) {
            for (Object obj2 : CollectionsKt.drop(this.components.getImplicitValueStorage().receiversAsReversed(), 1)) {
                if (AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(this.components.getSession()), ((ImplicitReceiverValue) obj2).getType(), coneClassLikeTypeOuterType, false, 8, (Object) null)) {
                    obj = obj2;
                    break;
                }
            }
            obj = (ImplicitReceiverValue) obj;
        }
        final ?? r6 = obj;
        final CandidateFactory candidateFactory = new CandidateFactory(context, info);
        final CandidateCollector candidateCollector = this.collector;
        firTypeScopeDelegatingConstructorScope.processDeclaredConstructors(new Function1() { // from class: if5
            public final Object invoke(Object obj3) {
                return FirTowerResolver.b(candidateCollector, candidateFactory, info, firTypeScopeDelegatingConstructorScope, r6, context, coneClassLikeTypeOuterType, constructedType, (FirConstructorSymbol) obj3);
            }
        });
        return this.collector;
    }

    public static /* synthetic */ CandidateCollector runResolver$default(FirTowerResolver firTowerResolver, CallInfo callInfo, ResolutionContext resolutionContext, CandidateCollector candidateCollector, CandidateFactory candidateFactory, int i, Object obj) {
        if ((i & 4) != 0) {
            candidateCollector = null;
        }
        if ((i & 8) != 0) {
            candidateFactory = new CandidateFactory(resolutionContext, callInfo);
        }
        return firTowerResolver.runResolver(callInfo, resolutionContext, candidateCollector, candidateFactory);
    }

    public /* synthetic */ FirTowerResolver(BodyResolveComponents bodyResolveComponents, ResolutionStageRunner resolutionStageRunner, CandidateCollector candidateCollector, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bodyResolveComponents, resolutionStageRunner, (i & 4) != 0 ? new CandidateCollector(bodyResolveComponents, resolutionStageRunner) : candidateCollector);
    }

    public final CandidateCollector runResolver(CallInfo info, ResolutionContext context, CandidateCollector externalCollector, CandidateFactory candidateFactory) {
        info.getClass();
        context.getClass();
        candidateFactory.getClass();
        if (externalCollector == null) {
            externalCollector = this.collector;
        }
        return runResolver(info, context, externalCollector, this.manager, candidateFactory);
    }
}
