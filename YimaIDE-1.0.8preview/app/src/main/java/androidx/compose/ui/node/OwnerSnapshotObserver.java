package androidx.compose.ui.node;

import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001B0\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nJ&\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b\u0018J&\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b\u001aJ&\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b\u001cJ&\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b\u001eJ&\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b J&\u0010!\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b\"J&\u0010#\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0002\b$JH\u0010%\u001a\u00020\u0005\"\b\b\u0000\u0010&*\u00020'2\u0006\u0010(\u001a\u0002H&2\u0014\b\b\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0080\b¢\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\u0005H\u0000¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0001H\u0000¢\u0006\u0002\b/J\r\u00100\u001a\u00020\u0005H\u0000¢\u0006\u0002\b1J\r\u00102\u001a\u00020\u0005H\u0000¢\u0006\u0002\b3R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Landroidx/compose/ui/node/OwnerSnapshotObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", "name", "callback", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "observer", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "onCommitAffectingLookaheadMeasure", "Landroidx/compose/ui/node/LayoutNode;", "onCommitAffectingMeasure", "onCommitAffectingSemantics", "onCommitAffectingLayout", "onCommitAffectingLayoutModifier", "onCommitAffectingLayoutModifierInLookahead", "onCommitAffectingLookahead", "observeLayoutSnapshotReads", "node", "block", "observeLayoutSnapshotReads$ui", "observeLayoutSnapshotReadsAffectingLookahead", "observeLayoutSnapshotReadsAffectingLookahead$ui", "observeLayoutModifierSnapshotReads", "observeLayoutModifierSnapshotReads$ui", "observeLayoutModifierSnapshotReadsAffectingLookahead", "observeLayoutModifierSnapshotReadsAffectingLookahead$ui", "observeMeasureSnapshotReads", "observeMeasureSnapshotReads$ui", "observeMeasureSnapshotReadsAffectingLookahead", "observeMeasureSnapshotReadsAffectingLookahead$ui", "observeSemanticsReads", "observeSemanticsReads$ui", "observeReads", "T", "Landroidx/compose/ui/node/OwnerScope;", "target", "onChanged", "observeReads$ui", "(Landroidx/compose/ui/node/OwnerScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "clearInvalidObservations", "clearInvalidObservations$ui", "clear", "clear$ui", "startObserving", "startObserving$ui", "stopObserving", "stopObserving$ui", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OwnerSnapshotObserver {
    public static final int $stable = SnapshotStateObserver.$stable;
    private final SnapshotStateObserver observer;
    private final Function1<LayoutNode, Unit> onCommitAffectingLookaheadMeasure = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLookaheadMeasure$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                LayoutNode.requestLookaheadRemeasure$ui$default(layoutNode, false, false, false, 7, null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1<LayoutNode, Unit> onCommitAffectingMeasure = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingMeasure$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                LayoutNode.requestRemeasure$ui$default(layoutNode, false, false, false, 7, null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1<LayoutNode, Unit> onCommitAffectingSemantics = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingSemantics$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                layoutNode.invalidateSemantics$ui();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1<LayoutNode, Unit> onCommitAffectingLayout = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayout$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                LayoutNode.requestRelayout$ui$default(layoutNode, false, 1, null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1<LayoutNode, Unit> onCommitAffectingLayoutModifier = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayoutModifier$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                LayoutNode.requestRelayout$ui$default(layoutNode, false, 1, null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1<LayoutNode, Unit> onCommitAffectingLayoutModifierInLookahead = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayoutModifierInLookahead$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                LayoutNode.requestLookaheadRelayout$ui$default(layoutNode, false, 1, null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1<LayoutNode, Unit> onCommitAffectingLookahead = new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLookahead$1
        public final void invoke(LayoutNode layoutNode) {
            if (layoutNode.isValidOwnerScope()) {
                LayoutNode.requestLookaheadRelayout$ui$default(layoutNode, false, 1, null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutNode) obj);
            return Unit.INSTANCE;
        }
    };

    public OwnerSnapshotObserver(Function1<? super Function0<Unit>, Unit> function1) {
        this.observer = new SnapshotStateObserver(function1);
    }

    public final void clear$ui(Object target) {
        this.observer.clear(target);
    }

    public final void clearInvalidObservations$ui() {
        this.observer.clearIf(new Function1<Object, Boolean>() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$clearInvalidObservations$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m5017invoke(Object obj) {
                obj.getClass();
                return Boolean.valueOf(!((OwnerScope) obj).isValidOwnerScope());
            }
        });
    }

    public final void observeLayoutModifierSnapshotReads$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingLayoutModifier, block);
    }

    public final void observeLayoutModifierSnapshotReadsAffectingLookahead$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingLayoutModifierInLookahead, block);
    }

    public final void observeLayoutSnapshotReads$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingLayout, block);
    }

    public final void observeLayoutSnapshotReadsAffectingLookahead$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingLookahead, block);
    }

    public final void observeMeasureSnapshotReads$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingMeasure, block);
    }

    public final void observeMeasureSnapshotReadsAffectingLookahead$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingLookaheadMeasure, block);
    }

    public final <T extends OwnerScope> void observeReads$ui(T target, Function1<? super T, Unit> onChanged, Function0<Unit> block) {
        this.observer.observeReads(target, onChanged, block);
    }

    public final void observeSemanticsReads$ui(LayoutNode node, Function0<Unit> block) {
        this.observer.observeReads(node, this.onCommitAffectingSemantics, block);
    }

    public final void startObserving$ui() {
        this.observer.start();
    }

    public final void stopObserving$ui() {
        this.observer.stop();
        this.observer.clear();
    }
}
