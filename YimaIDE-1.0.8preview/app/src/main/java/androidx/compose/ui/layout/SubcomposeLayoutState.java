package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002*+B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006B\u0011\b\u0017\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0004\u0010\tJ(\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u00012\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00120\"¢\u0006\u0002\b#¢\u0006\u0002\u0010$J(\u0010%\u001a\u00020&2\b\u0010 \u001a\u0004\u0018\u00010\u00012\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00120\"¢\u0006\u0002\b#¢\u0006\u0002\u0010'J\r\u0010(\u001a\u00020\u0012H\u0000¢\u0006\u0002\b)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR+\u0010\u000f\u001a\u0019\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R+\u0010\u0016\u001a\u0019\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015RB\u0010\u0019\u001a0\u0012\u0004\u0012\u00020\u0011\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u0010¢\u0006\u0002\b\u0013\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015¨\u0006,"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeLayoutState;", "", "slotReusePolicy", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "<init>", "(Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "()V", "maxSlotsToRetainForReuse", "", "(I)V", "_state", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "state", "getState", "()Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "setRoot", "Lkotlin/Function2;", "Landroidx/compose/ui/node/LayoutNode;", "", "Lkotlin/ExtensionFunctionType;", "getSetRoot$ui", "()Lkotlin/jvm/functions/Function2;", "setCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "getSetCompositionContext$ui", "setMeasurePolicy", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "getSetMeasurePolicy$ui", "precompose", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "slotId", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "createPausedPrecomposition", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PausedPrecomposition;", "forceRecomposeChildren", "forceRecomposeChildren$ui", "PausedPrecomposition", "PrecomposedSlotHandle", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SubcomposeLayoutState {
    public static final int $stable = 8;
    private LayoutNodeSubcompositionsState _state;
    private final Function2<LayoutNode, CompositionContext, Unit> setCompositionContext;
    private final Function2<LayoutNode, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult>, Unit> setMeasurePolicy;
    private final Function2<LayoutNode, SubcomposeLayoutState, Unit> setRoot;
    private final SubcomposeSlotReusePolicy slotReusePolicy;

    public SubcomposeLayoutState(SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        this.slotReusePolicy = subcomposeSlotReusePolicy;
        this.setRoot = new Function2<LayoutNode, SubcomposeLayoutState, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutState$setRoot$1
            {
                super(2);
            }

            public final void invoke(LayoutNode layoutNode, SubcomposeLayoutState subcomposeLayoutState) {
                SubcomposeLayoutState subcomposeLayoutState2 = this.this$0;
                LayoutNodeSubcompositionsState subcompositionsState = layoutNode.getSubcompositionsState();
                if (subcompositionsState == null) {
                    subcompositionsState = new LayoutNodeSubcompositionsState(layoutNode, this.this$0.slotReusePolicy);
                    layoutNode.setSubcompositionsState$ui(subcompositionsState);
                }
                subcomposeLayoutState2._state = subcompositionsState;
                this.this$0.getState().makeSureStateIsConsistent();
                this.this$0.getState().setSlotReusePolicy(this.this$0.slotReusePolicy);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (SubcomposeLayoutState) obj2);
                return Unit.INSTANCE;
            }
        };
        this.setCompositionContext = new Function2<LayoutNode, CompositionContext, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutState$setCompositionContext$1
            {
                super(2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (CompositionContext) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(LayoutNode layoutNode, CompositionContext compositionContext) {
                this.this$0.getState().setCompositionContext(compositionContext);
            }
        };
        this.setMeasurePolicy = new Function2<LayoutNode, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult>, Unit>() { // from class: androidx.compose.ui.layout.SubcomposeLayoutState$setMeasurePolicy$1
            {
                super(2);
            }

            public final void invoke(LayoutNode layoutNode, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> function2) {
                layoutNode.setMeasurePolicy(this.this$0.getState().createMeasurePolicy(function2));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((LayoutNode) obj, (Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult>) obj2);
                return Unit.INSTANCE;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutNodeSubcompositionsState getState() {
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this._state;
        if (layoutNodeSubcompositionsState != null) {
            return layoutNodeSubcompositionsState;
        }
        w01.a("SubcomposeLayoutState is not attached to SubcomposeLayout");
        return null;
    }

    public final PausedPrecomposition createPausedPrecomposition(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        return getState().precomposePaused(slotId, content);
    }

    public final void forceRecomposeChildren$ui() {
        getState().forceRecomposeChildren();
    }

    public final Function2<LayoutNode, CompositionContext, Unit> getSetCompositionContext$ui() {
        return this.setCompositionContext;
    }

    public final Function2<LayoutNode, Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult>, Unit> getSetMeasurePolicy$ui() {
        return this.setMeasurePolicy;
    }

    public final Function2<LayoutNode, SubcomposeLayoutState, Unit> getSetRoot$ui() {
        return this.setRoot;
    }

    public final PrecomposedSlotHandle precompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        return getState().precompose(slotId, content);
    }

    public SubcomposeLayoutState() {
        this(NoOpSubcomposeSlotReusePolicy.INSTANCE);
    }

    @Deprecated(message = "This constructor is deprecated", replaceWith = @ReplaceWith(expression = "SubcomposeLayoutState(SubcomposeSlotReusePolicy(maxSlotsToRetainForReuse))", imports = {"androidx.compose.ui.layout.SubcomposeSlotReusePolicy"}))
    public SubcomposeLayoutState(int i) {
        this(SubcomposeLayoutKt.SubcomposeSlotReusePolicy(i));
    }
}
