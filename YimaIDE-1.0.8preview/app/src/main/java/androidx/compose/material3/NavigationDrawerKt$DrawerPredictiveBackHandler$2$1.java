package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.material3.internal.PredictiveBack;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003H\n"}, d2 = {"<anonymous>", "", NotificationCompat.CATEGORY_PROGRESS, "Lkotlinx/coroutines/flow/Flow;", "Landroidx/activity/BackEventCompat;", "Landroidx/compose/material3/internal/BackEventCompat;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$DrawerPredictiveBackHandler$2$1", f = "NavigationDrawer.kt", i = {}, l = {957, 983, 983, 983}, m = "invokeSuspend", n = {}, s = {})
public final class NavigationDrawerKt$DrawerPredictiveBackHandler$2$1 extends SuspendLambda implements Function2<Flow<? extends BackEventCompat>, Continuation<? super Unit>, Object> {
    final /* synthetic */ DrawerPredictiveBackState $drawerPredictiveBackState;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ boolean $isRtl;
    final /* synthetic */ Ref.FloatRef $maxScaleXDistanceGrow;
    final /* synthetic */ Ref.FloatRef $maxScaleXDistanceShrink;
    final /* synthetic */ Ref.FloatRef $maxScaleYDistance;
    final /* synthetic */ CoroutineScope $scope;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationDrawerKt$DrawerPredictiveBackHandler$2$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.NavigationDrawerKt$DrawerPredictiveBackHandler$2$1$2", f = "NavigationDrawer.kt", i = {}, l = {974}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DrawerPredictiveBackState $drawerPredictiveBackState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DrawerPredictiveBackState drawerPredictiveBackState, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$drawerPredictiveBackState = drawerPredictiveBackState;
        }

        public static Unit b(DrawerPredictiveBackState drawerPredictiveBackState, float f, float f2) {
            drawerPredictiveBackState.setScaleXDistance(f);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$drawerPredictiveBackState, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            AnonymousClass2 anonymousClass2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                float scaleXDistance = this.$drawerPredictiveBackState.getScaleXDistance();
                final DrawerPredictiveBackState drawerPredictiveBackState = this.$drawerPredictiveBackState;
                Function2 function2 = new Function2() { // from class: androidx.compose.material3.x2
                    public final Object invoke(Object obj2, Object obj3) {
                        return NavigationDrawerKt$DrawerPredictiveBackHandler$2$1.AnonymousClass2.b(drawerPredictiveBackState, ((Float) obj2).floatValue(), ((Float) obj3).floatValue());
                    }
                };
                this.label = 1;
                anonymousClass2 = this;
                if (SuspendAnimationKt.animate$default(scaleXDistance, 0.0f, 0.0f, (AnimationSpec) null, function2, anonymousClass2, 12, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                anonymousClass2 = this;
            }
            anonymousClass2.$drawerPredictiveBackState.clear();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationDrawerKt$DrawerPredictiveBackHandler$2$1(DrawerPredictiveBackState drawerPredictiveBackState, CoroutineScope coroutineScope, DrawerState drawerState, boolean z, Ref.FloatRef floatRef, Ref.FloatRef floatRef2, Ref.FloatRef floatRef3, Continuation<? super NavigationDrawerKt$DrawerPredictiveBackHandler$2$1> continuation) {
        super(2, continuation);
        this.$drawerPredictiveBackState = drawerPredictiveBackState;
        this.$scope = coroutineScope;
        this.$drawerState = drawerState;
        this.$isRtl = z;
        this.$maxScaleXDistanceGrow = floatRef;
        this.$maxScaleXDistanceShrink = floatRef2;
        this.$maxScaleYDistance = floatRef3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NavigationDrawerKt$DrawerPredictiveBackHandler$2$1 navigationDrawerKt$DrawerPredictiveBackHandler$2$1 = new NavigationDrawerKt$DrawerPredictiveBackHandler$2$1(this.$drawerPredictiveBackState, this.$scope, this.$drawerState, this.$isRtl, this.$maxScaleXDistanceGrow, this.$maxScaleXDistanceShrink, this.$maxScaleYDistance, continuation);
        navigationDrawerKt$DrawerPredictiveBackHandler$2$1.L$0 = obj;
        return navigationDrawerKt$DrawerPredictiveBackHandler$2$1;
    }

    public final Object invoke(Flow<BackEventCompat> flow, Continuation<? super Unit> continuation) {
        return create(flow, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0070, code lost:
    
        if (r14.close(r13) == r1) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0098, code lost:
    
        if (r14.close(r13) == r1) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = (Flow) this.L$0;
                    final DrawerPredictiveBackState drawerPredictiveBackState = this.$drawerPredictiveBackState;
                    final boolean z = this.$isRtl;
                    final Ref.FloatRef floatRef = this.$maxScaleXDistanceGrow;
                    final Ref.FloatRef floatRef2 = this.$maxScaleXDistanceShrink;
                    final Ref.FloatRef floatRef3 = this.$maxScaleYDistance;
                    FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerPredictiveBackHandler$2$1.1
                        public final Object emit(BackEventCompat backEventCompat, Continuation<? super Unit> continuation) {
                            drawerPredictiveBackState.update(PredictiveBack.INSTANCE.transform$material3(backEventCompat.getProgress()), backEventCompat.getSwipeEdge() == 0, z, floatRef.element, floatRef2.element, floatRef3.element);
                            return Unit.INSTANCE;
                        }

                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((BackEventCompat) obj2, (Continuation<? super Unit>) continuation);
                        }
                    };
                    this.label = 1;
                    if (flow.collect(flowCollector, this) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2 && i != 3) {
                        if (i != 4) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        Throwable th = (Throwable) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        throw th;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
                if (this.$drawerPredictiveBackState.getSwipeEdgeMatchesDrawer()) {
                    BuildersKt.launch$default(this.$scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$drawerPredictiveBackState, null), 3, (Object) null);
                }
                DrawerState drawerState = this.$drawerState;
                this.label = 2;
            } catch (CancellationException unused) {
                this.$drawerPredictiveBackState.clear();
                if (this.$drawerPredictiveBackState.getSwipeEdgeMatchesDrawer()) {
                    BuildersKt.launch$default(this.$scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$drawerPredictiveBackState, null), 3, (Object) null);
                }
                DrawerState drawerState2 = this.$drawerState;
                this.label = 3;
            }
        } catch (Throwable th2) {
            if (this.$drawerPredictiveBackState.getSwipeEdgeMatchesDrawer()) {
                BuildersKt.launch$default(this.$scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$drawerPredictiveBackState, null), 3, (Object) null);
            }
            DrawerState drawerState3 = this.$drawerState;
            this.L$0 = th2;
            this.label = 4;
            if (drawerState3.close(this) != coroutine_suspended) {
                throw th2;
            }
        }
    }
}
