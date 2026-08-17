package androidx.compose.runtime;

import androidx.collection.ScatterSet;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.apk.ApkUtil;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020#2\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\"0%¢\u0006\u0002\b&H ¢\u0006\u0004\b'\u0010(J8\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u001d\u001a\u00020#2\u0006\u0010,\u001a\u00020-2\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\"0%¢\u0006\u0002\b&H ¢\u0006\u0004\b.\u0010/J1\u00100\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u001d\u001a\u00020#2\u0006\u0010,\u001a\u00020-2\f\u00101\u001a\b\u0012\u0004\u0012\u00020+0*H ¢\u0006\u0002\b2J\u0015\u00103\u001a\u00020\"2\u0006\u00104\u001a\u00020+H ¢\u0006\u0002\b5J\u0015\u00106\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020#H ¢\u0006\u0002\b7J\u0015\u00108\u001a\u00020\"2\u0006\u00104\u001a\u00020+H ¢\u0006\u0002\b9J\u001b\u0010:\u001a\u00020\"2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020=0<H\u0010¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020AH\u0010¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020\"2\u0006\u0010@\u001a\u00020AH\u0010¢\u0006\u0002\bDJ\u0015\u0010E\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020#H ¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020#H ¢\u0006\u0002\bHJ\r\u0010I\u001a\u00020JH\u0010¢\u0006\u0002\bKJ\r\u0010L\u001a\u00020\"H\u0010¢\u0006\u0002\bMJ\r\u0010N\u001a\u00020\"H\u0010¢\u0006\u0002\bOJ\u0015\u0010P\u001a\u00020\"2\u0006\u0010Q\u001a\u00020RH ¢\u0006\u0002\bSJ\u0015\u0010T\u001a\u00020\"2\u0006\u0010Q\u001a\u00020RH ¢\u0006\u0002\bUJ)\u0010V\u001a\u00020\"2\u0006\u0010Q\u001a\u00020R2\u0006\u0010W\u001a\u00020X2\n\u0010Y\u001a\u0006\u0012\u0002\b\u00030ZH ¢\u0006\u0002\b[J\u0017\u0010\\\u001a\u0004\u0018\u00010X2\u0006\u0010Q\u001a\u00020RH\u0010¢\u0006\u0002\b]J\u0015\u0010^\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020#H ¢\u0006\u0002\b_J\u0016\u0010`\u001a\u00020a2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\"0%H&R\u0016\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X \u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX \u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\nX \u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0012\u0010\u000f\u001a\u00020\nX \u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0012\u0010\u0011\u001a\u00020\nX \u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00148PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u0018X \u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u001eX \u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006c"}, d2 = {"Landroidx/compose/runtime/CompositionContext;", "", "<init>", "()V", "compositeKeyHashCode", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "getCompositeKeyHashCode$runtime", "()J", "collectingParameterInformation", "", "getCollectingParameterInformation$runtime", "()Z", "collectingSourceInformation", "getCollectingSourceInformation$runtime", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "stackTraceEnabled", "getStackTraceEnabled$runtime", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime", "composition", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "composeInitial", "", "Landroidx/compose/runtime/ControlledComposition;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "reportPausedScope", "scope", "reportPausedScope$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "recordInspectionTable", "table", "", "Landroidx/compose/runtime/tooling/CompositionData;", "recordInspectionTable$runtime", "registerComposer", "composer", "Landroidx/compose/runtime/Composer;", "registerComposer$runtime", "unregisterComposer", "unregisterComposer$runtime", "registerComposition", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "getCompositionLocalScope", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "getCompositionLocalScope$runtime", "startComposing", "startComposing$runtime", "doneComposing", "doneComposing$runtime", "insertMovableContent", "reference", "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateReleased", ApkUtil.NAME_data, "Landroidx/compose/runtime/MovableContentState;", "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "movableContentStateResolve", "movableContentStateResolve$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class CompositionContext {
    public static final int $stable = 0;

    public abstract void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content);

    public abstract ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content);

    public abstract void deletedMovableContent$runtime(MovableContentStateReference reference);

    public void doneComposing$runtime() {
    }

    public abstract boolean getCollectingCallByInformation$runtime();

    /* JADX INFO: renamed from: getCollectingParameterInformation$runtime */
    public abstract boolean getCollectingParameterInformation();

    /* JADX INFO: renamed from: getCollectingSourceInformation$runtime */
    public abstract boolean getCollectingSourceInformation();

    /* JADX INFO: renamed from: getCompositeKeyHashCode$runtime */
    public abstract long getCompositeKeyHashCode();

    public abstract Composition getComposition$runtime();

    public PersistentCompositionLocalMap getCompositionLocalScope$runtime() {
        return CompositionContextKt.EmptyPersistentCompositionLocalMap;
    }

    public abstract CoroutineContext getEffectCoroutineContext();

    /* JADX INFO: renamed from: getObserverHolder$runtime */
    public CompositionObserverHolder getObserverHolder() {
        return null;
    }

    public abstract CoroutineContext getRecomposeCoroutineContext$runtime();

    public abstract boolean getStackTraceEnabled$runtime();

    public abstract void insertMovableContent$runtime(MovableContentStateReference reference);

    public abstract void invalidate$runtime(ControlledComposition composition);

    public abstract void invalidateScope$runtime(RecomposeScopeImpl scope);

    public abstract void movableContentStateReleased$runtime(MovableContentStateReference reference, MovableContentState data, Applier<?> applier);

    public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference reference) {
        return null;
    }

    public abstract ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes);

    public void recordInspectionTable$runtime(Set<CompositionData> table) {
    }

    public void registerComposer$runtime(Composer composer) {
    }

    public abstract void registerComposition$runtime(ControlledComposition composition);

    public abstract void reportPausedScope$runtime(RecomposeScopeImpl scope);

    public abstract void reportRemovedComposition$runtime(ControlledComposition composition);

    public abstract CancellationHandle scheduleFrameEndCallback(Function0<Unit> action);

    public void startComposing$runtime() {
    }

    public void unregisterComposer$runtime(Composer composer) {
    }

    public abstract void unregisterComposition$runtime(ControlledComposition composition);
}
