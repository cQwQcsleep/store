package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitPropertyTypeMakesBehaviorOrderDependant;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ImplicitInvokeMode;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.ImplicitBodyResolveComputationSession;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.ReturnTypeCalculatorWithJump;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0018JB\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00112*\b\u0004\u0010\u001b\u001a$\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cH\u0082\b¢\u0006\u0002\u0010\u001fJV\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2$\b\u0004\u0010&\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u00010'H\u0082\b¢\u0006\u0002\u0010(J(\u0010)\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J$\u0010.\u001a\u00020\u000f2\n\u0010/\u001a\u0006\u0012\u0002\b\u0003002\u0006\u00101\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-H\u0002J0\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u0002052\u0006\u0010$\u001a\u00020%2\u0006\u00106\u001a\u00020%2\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u00107\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0018J\"\u00109\u001a\u00020:2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010;\u001a\u00020<H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006="}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirInvokeResolveTowerExtension;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "manager", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", "candidateFactoriesAndCollectors", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/CandidateFactoriesAndCollectors;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/CandidateFactoriesAndCollectors;)V", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "enqueueResolveTasksForQualifier", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "enqueueResolveTasksForNoReceiver", "enqueueResolveTasksForSuperReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "enqueueResolveTasksForExpressionReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "enqueueResolveForExplicitReceiver", "originalCallInfo", "invokeAction", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirTowerResolveTask;", "Lkotlin/coroutines/Continuation;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lkotlin/jvm/functions/Function3;)V", "enqueueInvokeReceiverTask", "invokeReceiverInfo", "towerDataElementsForName", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", "invokeBuiltinExtensionMode", Argument.Delimiters.none, "runResolutionForInvokeReceiverVariable", "Lkotlin/Function2;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;ZLkotlin/jvm/functions/Function2;)V", "enqueueResolverTasksForInvokeReceiverCandidates", "receiverGroup", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "checkImplicitPropertyTypeMakesBehaviorOrderDependant", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "callInfo", "enqueueResolverTasksForInvoke", "invokeFunctionInfo", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "useImplicitReceiverAsBuiltinInvokeArgument", "enqueueResolveTasksForImplicitInvokeCall", "receiverExpression", "createInvokeFunctionResolveTask", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/InvokeFunctionResolveTask;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInvokeResolveTowerExtension {
    private final CandidateFactoriesAndCollectors candidateFactoriesAndCollectors;
    private final ResolutionContext context;
    private final TowerResolveManager manager;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirInvokeResolveTowerExtension$enqueueResolveTasksForImplicitInvokeCall$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ExpressionReceiverValue $explicitReceiverValue;
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ InvokeFunctionResolveTask $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(InvokeFunctionResolveTask invokeFunctionResolveTask, CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$task = invokeFunctionResolveTask;
            this.$info = callInfo;
            this.$explicitReceiverValue = expressionReceiverValue;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$task, this.$info, this.$explicitReceiverValue, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                InvokeFunctionResolveTask invokeFunctionResolveTask = this.$task;
                CallInfo callInfo = this.$info;
                ExpressionReceiverValue expressionReceiverValue = this.$explicitReceiverValue;
                this.label = 1;
                if (invokeFunctionResolveTask.runResolverForInvoke(callInfo, expressionReceiverValue, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirInvokeResolveTowerExtension$enqueueResolveTasksForImplicitInvokeCall$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ExpressionReceiverValue $explicitReceiverValue;
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ InvokeFunctionResolveTask $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(InvokeFunctionResolveTask invokeFunctionResolveTask, CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$task = invokeFunctionResolveTask;
            this.$info = callInfo;
            this.$explicitReceiverValue = expressionReceiverValue;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$task, this.$info, this.$explicitReceiverValue, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                InvokeFunctionResolveTask invokeFunctionResolveTask = this.$task;
                CallInfo callInfo = this.$info;
                ExpressionReceiverValue expressionReceiverValue = this.$explicitReceiverValue;
                this.label = 1;
                if (invokeFunctionResolveTask.runResolverForBuiltinInvokeExtensionWithExplicitArgument(callInfo, expressionReceiverValue, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirInvokeResolveTowerExtension$enqueueResolveTasksForImplicitInvokeCall$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ExpressionReceiverValue $explicitReceiverValue;
        final /* synthetic */ CallInfo $info;
        final /* synthetic */ InvokeFunctionResolveTask $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(InvokeFunctionResolveTask invokeFunctionResolveTask, CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$task = invokeFunctionResolveTask;
            this.$info = callInfo;
            this.$explicitReceiverValue = expressionReceiverValue;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$task, this.$info, this.$explicitReceiverValue, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                InvokeFunctionResolveTask invokeFunctionResolveTask = this.$task;
                CallInfo callInfo = this.$info;
                ExpressionReceiverValue expressionReceiverValue = this.$explicitReceiverValue;
                this.label = 1;
                if (invokeFunctionResolveTask.runResolverForBuiltinInvokeExtensionWithImplicitArgument(callInfo, expressionReceiverValue, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirInvokeResolveTowerExtension$enqueueResolverTasksForInvoke$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00381 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ExpressionReceiverValue $explicitReceiver;
        final /* synthetic */ CallInfo $invokeFunctionInfo;
        final /* synthetic */ InvokeFunctionResolveTask $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00381(InvokeFunctionResolveTask invokeFunctionResolveTask, CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super C00381> continuation) {
            super(1, continuation);
            this.$task = invokeFunctionResolveTask;
            this.$invokeFunctionInfo = callInfo;
            this.$explicitReceiver = expressionReceiverValue;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00381(this.$task, this.$invokeFunctionInfo, this.$explicitReceiver, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                InvokeFunctionResolveTask invokeFunctionResolveTask = this.$task;
                CallInfo callInfo = this.$invokeFunctionInfo;
                ExpressionReceiverValue expressionReceiverValue = this.$explicitReceiver;
                this.label = 1;
                if (invokeFunctionResolveTask.runResolverForBuiltinInvokeExtensionWithExplicitArgument(callInfo, expressionReceiverValue, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirInvokeResolveTowerExtension$enqueueResolverTasksForInvoke$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00392 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ExpressionReceiverValue $explicitReceiver;
        final /* synthetic */ CallInfo $invokeFunctionInfo;
        final /* synthetic */ InvokeFunctionResolveTask $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00392(InvokeFunctionResolveTask invokeFunctionResolveTask, CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super C00392> continuation) {
            super(1, continuation);
            this.$task = invokeFunctionResolveTask;
            this.$invokeFunctionInfo = callInfo;
            this.$explicitReceiver = expressionReceiverValue;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00392(this.$task, this.$invokeFunctionInfo, this.$explicitReceiver, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                InvokeFunctionResolveTask invokeFunctionResolveTask = this.$task;
                CallInfo callInfo = this.$invokeFunctionInfo;
                ExpressionReceiverValue expressionReceiverValue = this.$explicitReceiver;
                this.label = 1;
                if (invokeFunctionResolveTask.runResolverForBuiltinInvokeExtensionWithImplicitArgument(callInfo, expressionReceiverValue, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirInvokeResolveTowerExtension$enqueueResolverTasksForInvoke$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {SpecialNames.ANONYMOUS_STRING, Argument.Delimiters.none}, k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00403 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ExpressionReceiverValue $explicitReceiver;
        final /* synthetic */ CallInfo $invokeFunctionInfo;
        final /* synthetic */ InvokeFunctionResolveTask $task;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00403(InvokeFunctionResolveTask invokeFunctionResolveTask, CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super C00403> continuation) {
            super(1, continuation);
            this.$task = invokeFunctionResolveTask;
            this.$invokeFunctionInfo = callInfo;
            this.$explicitReceiver = expressionReceiverValue;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00403(this.$task, this.$invokeFunctionInfo, this.$explicitReceiver, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                InvokeFunctionResolveTask invokeFunctionResolveTask = this.$task;
                CallInfo callInfo = this.$invokeFunctionInfo;
                ExpressionReceiverValue expressionReceiverValue = this.$explicitReceiver;
                this.label = 1;
                if (invokeFunctionResolveTask.runResolverForInvoke(callInfo, expressionReceiverValue, this) == coroutine_suspended) {
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

    public FirInvokeResolveTowerExtension(ResolutionContext resolutionContext, TowerResolveManager towerResolveManager, CandidateFactoriesAndCollectors candidateFactoriesAndCollectors) {
        resolutionContext.getClass();
        towerResolveManager.getClass();
        candidateFactoriesAndCollectors.getClass();
        this.context = resolutionContext;
        this.manager = towerResolveManager;
        this.candidateFactoriesAndCollectors = candidateFactoriesAndCollectors;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkImplicitPropertyTypeMakesBehaviorOrderDependant(FirCallableSymbol<?> symbol, CallInfo callInfo, CandidateCollector collector) {
        ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession;
        if (symbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) symbol;
            FirTypeRef returnTypeRef = ((FirProperty) firPropertySymbol.getFir()).getReturnTypeRef();
            if (!(returnTypeRef instanceof FirImplicitTypeRef)) {
                KtSourceElement source = returnTypeRef.getSource();
                if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE)) {
                    return;
                }
            }
            if (firPropertySymbol.isLocal() || callInfo.getExplicitReceiver() == null) {
                return;
            }
            ReturnTypeCalculator returnTypeCalculator = this.context.getReturnTypeCalculator();
            ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump = returnTypeCalculator instanceof ReturnTypeCalculatorWithJump ? (ReturnTypeCalculatorWithJump) returnTypeCalculator : null;
            if (returnTypeCalculatorWithJump == null || (implicitBodyResolveComputationSession = returnTypeCalculatorWithJump.getImplicitBodyResolveComputationSession()) == null || !implicitBodyResolveComputationSession.belongToSomeNonTrivialLoop(symbol)) {
                return;
            }
            collector.addForwardedDiagnostic(new ImplicitPropertyTypeMakesBehaviorOrderDependant(firPropertySymbol));
        }
    }

    private final InvokeFunctionResolveTask createInvokeFunctionResolveTask(CallInfo info, TowerGroup receiverGroup, CandidateFactory candidateFactory) {
        return new InvokeFunctionResolveTask(getComponents(), this.manager, new TowerDataElementsForName(info.getName(), getComponents().getTowerDataContext()), receiverGroup, this.candidateFactoriesAndCollectors.getResultCollector(), candidateFactory);
    }

    public static /* synthetic */ InvokeFunctionResolveTask createInvokeFunctionResolveTask$default(FirInvokeResolveTowerExtension firInvokeResolveTowerExtension, CallInfo callInfo, TowerGroup towerGroup, CandidateFactory candidateFactory, int i, Object obj) {
        if ((i & 4) != 0) {
            candidateFactory = firInvokeResolveTowerExtension.candidateFactoriesAndCollectors.getCandidateFactory();
        }
        return firInvokeResolveTowerExtension.createInvokeFunctionResolveTask(callInfo, towerGroup, candidateFactory);
    }

    private final void enqueueResolverTasksForInvoke(CallInfo invokeFunctionInfo, ExpressionReceiverValue explicitReceiver, boolean invokeBuiltinExtensionMode, boolean useImplicitReceiverAsBuiltinInvokeArgument, TowerGroup receiverGroup) {
        InvokeFunctionResolveTask invokeFunctionResolveTaskCreateInvokeFunctionResolveTask = createInvokeFunctionResolveTask(invokeFunctionInfo, receiverGroup, new CandidateFactory(this.context, invokeFunctionInfo));
        if (invokeBuiltinExtensionMode) {
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new C00381(invokeFunctionResolveTaskCreateInvokeFunctionResolveTask, invokeFunctionInfo, explicitReceiver, null), 1, null);
            return;
        }
        if (useImplicitReceiverAsBuiltinInvokeArgument) {
            if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
                FirSession session = this.context.getSession();
                ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(explicitReceiver.getType(), session, (Function1) null, 2, (Object) null);
                ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(session).approximateToSuperType(coneKotlinTypeFullyExpandedType$default, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE);
                if (coneKotlinTypeApproximateToSuperType != null) {
                    coneKotlinTypeFullyExpandedType$default = coneKotlinTypeApproximateToSuperType;
                }
                if (!CompilerConeAttributesKt.isExtensionFunctionType(coneKotlinTypeFullyExpandedType$default)) {
                    w01.a("Failed requirement.");
                    return;
                }
            }
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new C00392(invokeFunctionResolveTaskCreateInvokeFunctionResolveTask, invokeFunctionInfo, explicitReceiver, null), 1, null);
        }
        TowerResolveManager.enqueueResolverTask$default(this.manager, null, new C00403(invokeFunctionResolveTaskCreateInvokeFunctionResolveTask, invokeFunctionInfo, explicitReceiver, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueueResolverTasksForInvokeReceiverCandidates(boolean invokeBuiltinExtensionMode, CallInfo info, TowerGroup receiverGroup, CandidateCollector collector) {
        FirInvokeResolveTowerExtension firInvokeResolveTowerExtension;
        boolean zIsExtensionFunctionType;
        boolean z = invokeBuiltinExtensionMode;
        CallInfo callInfo = info;
        Iterator<Candidate> it = collector.bestCandidates().iterator();
        while (it.hasNext()) {
            Candidate next = it.next();
            FirBasedSymbol<?> symbol = next.getSymbol();
            boolean z2 = false;
            if (symbol instanceof FirCallableSymbol) {
                FirCallableSymbol<?> firCallableSymbol = (FirCallableSymbol) symbol;
                FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnType = getComponents().getReturnTypeCalculator().tryCalculateReturnType(firCallableSymbol);
                firInvokeResolveTowerExtension = this;
                firInvokeResolveTowerExtension.checkImplicitPropertyTypeMakesBehaviorOrderDependant(firCallableSymbol, callInfo, collector);
                zIsExtensionFunctionType = TypeUtilsKt.isExtensionFunctionType((FirTypeRef) firResolvedTypeRefTryCalculateReturnType, firInvokeResolveTowerExtension.getComponents().getSession());
            } else {
                firInvokeResolveTowerExtension = this;
                if (symbol instanceof FirClassLikeSymbol) {
                    zIsExtensionFunctionType = false;
                }
                z = invokeBuiltinExtensionMode;
                callInfo = info;
            }
            if (!z || zIsExtensionFunctionType) {
                FirExpression firExpressionChosenExtensionReceiverExpression = next.chosenExtensionReceiverExpression();
                if (!z && zIsExtensionFunctionType && next.getExplicitReceiverKind() == ExplicitReceiverKind.NO_EXPLICIT_RECEIVER) {
                    z2 = true;
                }
                boolean z3 = z2;
                FirExpression firExpressionCreateExplicitReceiverForInvoke = FirInvokeResolveTowerExtensionKt.createExplicitReceiverForInvoke(firInvokeResolveTowerExtension.getComponents(), next, callInfo, z, firExpressionChosenExtensionReceiverExpression);
                if (firExpressionCreateExplicitReceiverForInvoke != null) {
                    if (!(FirTypeUtilsKt.getResolvedType(firExpressionCreateExplicitReceiverForInvoke) instanceof ConeErrorType)) {
                        Name name = OperatorNameConventions.INVOKE;
                        ImplicitInvokeMode implicitInvokeMode = ImplicitInvokeMode.Regular;
                        if (z) {
                            next = null;
                        }
                        CallInfo callInfoCopy$default = CallInfo.copy$default(callInfo, null, null, null, firExpressionCreateExplicitReceiverForInvoke, name, implicitInvokeMode, next, null, 135, null);
                        if (z) {
                            FirExpression explicitReceiver = info.getExplicitReceiver();
                            explicitReceiver.getClass();
                            callInfoCopy$default = callInfoCopy$default.withReceiverAsArgument(explicitReceiver);
                        }
                        enqueueResolverTasksForInvoke(callInfoCopy$default, new ExpressionReceiverValue(firExpressionCreateExplicitReceiverForInvoke), z, z3, receiverGroup);
                    }
                    z = invokeBuiltinExtensionMode;
                    callInfo = info;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BodyResolveComponents getComponents() {
        return this.context.getBodyResolveComponents();
    }

    public final void enqueueResolveTasksForExpressionReceiver(CallInfo info, FirExpression receiver) {
        info.getClass();
        receiver.getClass();
        if (Intrinsics.areEqual(info.getCallKind(), CallKind.Function.INSTANCE)) {
            CallInfo callInfoReplaceWithVariableAccess = info.replaceWithVariableAccess();
            TowerDataElementsForName towerDataElementsForName = new TowerDataElementsForName(callInfoReplaceWithVariableAccess.getName(), getComponents().getTowerDataContext());
            CandidateCollector candidateCollector = new CandidateCollector(getComponents(), getComponents().getResolutionStageRunner());
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new FirInvokeResolveTowerExtension$enqueueResolveTasksForExpressionReceiver$$inlined$enqueueResolveForExplicitReceiver$1(new InvokeReceiverResolveTask(getComponents(), this.manager, towerDataElementsForName, candidateCollector, new CandidateFactory(this.context, callInfoReplaceWithVariableAccess), new FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(this, false, info, candidateCollector)), null, callInfoReplaceWithVariableAccess, receiver), 1, null);
            CallInfo callInfoReplaceExplicitReceiver = callInfoReplaceWithVariableAccess.replaceExplicitReceiver(null);
            CandidateCollector candidateCollector2 = new CandidateCollector(getComponents(), getComponents().getResolutionStageRunner());
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new FirInvokeResolveTowerExtension$enqueueResolveForExplicitReceiver$$inlined$enqueueInvokeReceiverTask$2(new InvokeReceiverResolveTask(getComponents(), this.manager, towerDataElementsForName, candidateCollector2, new CandidateFactory(this.context, callInfoReplaceExplicitReceiver), new FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(this, true, info, candidateCollector2)), null, callInfoReplaceExplicitReceiver), 1, null);
        }
    }

    public final void enqueueResolveTasksForImplicitInvokeCall(CallInfo info, FirExpression receiverExpression) {
        info.getClass();
        receiverExpression.getClass();
        ExpressionReceiverValue expressionReceiverValue = new ExpressionReceiverValue(receiverExpression);
        InvokeFunctionResolveTask invokeFunctionResolveTaskCreateInvokeFunctionResolveTask$default = createInvokeFunctionResolveTask$default(this, info, TowerGroup.INSTANCE.getEmptyRootForInvokeReceiver(), null, 4, null);
        TowerResolveManager.enqueueResolverTask$default(this.manager, null, new AnonymousClass1(invokeFunctionResolveTaskCreateInvokeFunctionResolveTask$default, info, expressionReceiverValue, null), 1, null);
        TowerResolveManager.enqueueResolverTask$default(this.manager, null, new AnonymousClass2(invokeFunctionResolveTaskCreateInvokeFunctionResolveTask$default, info, expressionReceiverValue, null), 1, null);
        TowerResolveManager.enqueueResolverTask$default(this.manager, null, new AnonymousClass3(invokeFunctionResolveTaskCreateInvokeFunctionResolveTask$default, info, expressionReceiverValue, null), 1, null);
    }

    public final void enqueueResolveTasksForNoReceiver(CallInfo info) {
        info.getClass();
        if (Intrinsics.areEqual(info.getCallKind(), CallKind.Function.INSTANCE)) {
            CallInfo callInfoReplaceWithVariableAccess = info.replaceWithVariableAccess();
            TowerDataElementsForName towerDataElementsForName = new TowerDataElementsForName(callInfoReplaceWithVariableAccess.getName(), getComponents().getTowerDataContext());
            CandidateCollector candidateCollector = new CandidateCollector(getComponents(), getComponents().getResolutionStageRunner());
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new FirInvokeResolveTowerExtension$enqueueResolveTasksForNoReceiver$$inlined$enqueueInvokeReceiverTask$default$1(new InvokeReceiverResolveTask(getComponents(), this.manager, towerDataElementsForName, candidateCollector, new CandidateFactory(this.context, callInfoReplaceWithVariableAccess), new FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(this, false, info, candidateCollector)), null, callInfoReplaceWithVariableAccess), 1, null);
        }
    }

    public final void enqueueResolveTasksForQualifier(CallInfo info, FirResolvedQualifier receiver) {
        info.getClass();
        receiver.getClass();
        if (Intrinsics.areEqual(info.getCallKind(), CallKind.Function.INSTANCE)) {
            CallInfo callInfoReplaceWithVariableAccess = info.replaceWithVariableAccess();
            TowerDataElementsForName towerDataElementsForName = new TowerDataElementsForName(callInfoReplaceWithVariableAccess.getName(), getComponents().getTowerDataContext());
            CandidateCollector candidateCollector = new CandidateCollector(getComponents(), getComponents().getResolutionStageRunner());
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new FirInvokeResolveTowerExtension$enqueueResolveTasksForQualifier$$inlined$enqueueResolveForExplicitReceiver$1(new InvokeReceiverResolveTask(getComponents(), this.manager, towerDataElementsForName, candidateCollector, new CandidateFactory(this.context, callInfoReplaceWithVariableAccess), new FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(this, false, info, candidateCollector)), null, callInfoReplaceWithVariableAccess, receiver), 1, null);
            CallInfo callInfoReplaceExplicitReceiver = callInfoReplaceWithVariableAccess.replaceExplicitReceiver(null);
            CandidateCollector candidateCollector2 = new CandidateCollector(getComponents(), getComponents().getResolutionStageRunner());
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new FirInvokeResolveTowerExtension$enqueueResolveForExplicitReceiver$$inlined$enqueueInvokeReceiverTask$2(new InvokeReceiverResolveTask(getComponents(), this.manager, towerDataElementsForName, candidateCollector2, new CandidateFactory(this.context, callInfoReplaceExplicitReceiver), new FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(this, true, info, candidateCollector2)), null, callInfoReplaceExplicitReceiver), 1, null);
        }
    }

    public final void enqueueResolveTasksForSuperReceiver(CallInfo info, FirQualifiedAccessExpression receiver) {
        info.getClass();
        receiver.getClass();
        if (Intrinsics.areEqual(info.getCallKind(), CallKind.Function.INSTANCE)) {
            CallInfo callInfoReplaceWithVariableAccess = info.replaceWithVariableAccess();
            TowerDataElementsForName towerDataElementsForName = new TowerDataElementsForName(callInfoReplaceWithVariableAccess.getName(), getComponents().getTowerDataContext());
            CandidateCollector candidateCollector = new CandidateCollector(getComponents(), getComponents().getResolutionStageRunner());
            TowerResolveManager.enqueueResolverTask$default(this.manager, null, new FirInvokeResolveTowerExtension$enqueueResolveTasksForSuperReceiver$$inlined$enqueueInvokeReceiverTask$default$1(new InvokeReceiverResolveTask(getComponents(), this.manager, towerDataElementsForName, candidateCollector, new CandidateFactory(this.context, callInfoReplaceWithVariableAccess), new FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(this, false, info, candidateCollector)), null, callInfoReplaceWithVariableAccess, receiver), 1, null);
        }
    }
}
