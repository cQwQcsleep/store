package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a;\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u000bH\u0000¢\u0006\u0004\b\f\u0010\r\u001a/\u0010\u000e\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u000bH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a7\u0010\u0011\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a7\u0010\u0015\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u000bH\u0002¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001a\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u001bH\u0002\u001a+\u0010\u001c\u001a\u0004\u0018\u00010\u0005*\b\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a/\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0004\b$\u0010%\u001a/\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0004\b*\u0010%\u001a\f\u0010+\u001a\u00020\t*\u00020\tH\u0002\u001a\f\u0010,\u001a\u00020\t*\u00020\tH\u0002\u001a\f\u0010-\u001a\u00020\u0005*\u00020\u0005H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"InvalidFocusDirection", "", "NoActiveChild", "twoDimensionalFocusSearch", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "onFound", "Lkotlin/Function1;", "twoDimensionalFocusSearch-sMXa3k8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILandroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "findChildCorrespondingToFocusEnter", "findChildCorrespondingToFocusEnter--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Z", "generateAndSearchChildren", "focusedItem", "generateAndSearchChildren-4C6V_qg", "(Landroidx/compose/ui/focus/FocusTargetNode;Landroidx/compose/ui/geometry/Rect;ILkotlin/jvm/functions/Function1;)Z", "searchChildren", "searchChildren-4C6V_qg", "collectAccessibleChildren", "", "Landroidx/compose/ui/node/DelegatableNode;", "accessibleChildren", "Landroidx/compose/runtime/collection/MutableVector;", "findBestCandidate", "focusRect", "findBestCandidate-4WY_MpI", "(Landroidx/compose/runtime/collection/MutableVector;Landroidx/compose/ui/geometry/Rect;I)Landroidx/compose/ui/focus/FocusTargetNode;", "isBetterCandidate", "proposedCandidate", "currentCandidate", "focusedRect", "isBetterCandidate-I7lrPNg", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;I)Z", "beamBeats", "source", "rect1", "rect2", "beamBeats-I7lrPNg", "topLeft", "bottomRight", "activeNode", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TwoDimensionalFocusSearchKt {
    private static final String InvalidFocusDirection = "This function should only be used for 2-D focus search";
    private static final String NoActiveChild = "ActiveParent must have a focusedChild";

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final FocusTargetNode activeNode(FocusTargetNode focusTargetNode) {
        if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
            k2d.a("Searching for active node in inactive hierarchy");
            return null;
        }
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(focusTargetNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            return focusTargetNodeFindActiveFocusNode;
        }
        k2d.a(NoActiveChild);
        return null;
    }

    /* JADX INFO: renamed from: beamBeats-I7lrPNg, reason: not valid java name */
    private static final boolean m2833beamBeatsI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (beamBeats_I7lrPNg$inSourceBeam(rect3, i, rect) || !beamBeats_I7lrPNg$inSourceBeam(rect2, i, rect)) {
            return false;
        }
        if (!beamBeats_I7lrPNg$isInDirectionOfSearch(rect3, i, rect)) {
            return true;
        }
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        return FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s()) || FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s()) || beamBeats_I7lrPNg$majorAxisDistance(rect2, i, rect) < beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(rect3, i, rect);
    }

    private static final boolean beamBeats_I7lrPNg$inSourceBeam(Rect rect, int i, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s()) || FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
            return rect.getBottom() > rect2.getTop() && rect.getTop() < rect2.getBottom();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s()) || FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
            return rect.getRight() > rect2.getLeft() && rect.getLeft() < rect2.getRight();
        }
        k2d.a(InvalidFocusDirection);
        return false;
    }

    private static final boolean beamBeats_I7lrPNg$isInDirectionOfSearch(Rect rect, int i, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
            return rect2.getLeft() >= rect.getRight();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
            return rect2.getRight() <= rect.getLeft();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
            return rect2.getTop() >= rect.getBottom();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
            return rect2.getBottom() <= rect.getTop();
        }
        k2d.a(InvalidFocusDirection);
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0056 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0057 A[RETURN] */
    private static final float beamBeats_I7lrPNg$majorAxisDistance(Rect rect, int i, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
            if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
                    k2d.a(InvalidFocusDirection);
                    return 0.0f;
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f = top - bottom;
            if (f < 0.0f) {
                return 0.0f;
            }
            return f;
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f = top2 - bottom2;
        if (f < 0.0f) {
            return 0.0f;
        }
        return f;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0057 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0058 A[RETURN] */
    private static final float beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(Rect rect, int i, Rect rect2) {
        float bottom;
        float bottom2;
        float top;
        float top2;
        float f;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
            if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
                bottom = rect.getRight();
                bottom2 = rect2.getRight();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
                top = rect2.getTop();
                top2 = rect.getTop();
            } else {
                if (!FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
                    k2d.a(InvalidFocusDirection);
                    return 0.0f;
                }
                bottom = rect.getBottom();
                bottom2 = rect2.getBottom();
            }
            f = bottom - bottom2;
            if (f < 1.0f) {
                return 1.0f;
            }
            return f;
        }
        top = rect2.getLeft();
        top2 = rect.getLeft();
        f = top - top2;
        if (f < 1.0f) {
            return 1.0f;
        }
        return f;
    }

    private static final Rect bottomRight(Rect rect) {
        return new Rect(rect.getRight(), rect.getBottom(), rect.getRight(), rect.getBottom());
    }

    private static final void collectAccessibleChildren(DelegatableNode delegatableNode, MutableVector<FocusTargetNode> mutableVector) {
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, delegatableNode.getNode(), false);
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.getSize() != 0) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop, false);
            } else {
                while (nodePop != null) {
                    if ((nodePop.getKindSet() & iM4949constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode = (FocusTargetNode) nodePop;
                                if (focusTargetNode.getIsAttached() && !DelegatableNodeKt.requireLayoutNode(focusTargetNode).getIsDeactivated()) {
                                    if (focusTargetNode.fetchFocusProperties$ui().getCanFocus()) {
                                        mutableVector.add(focusTargetNode);
                                    } else {
                                        collectAccessibleChildren(focusTargetNode, mutableVector);
                                    }
                                }
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                    if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector3.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector3.add(delegate$ui);
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                        }
                        break;
                    }
                    nodePop = nodePop.getChild();
                }
            }
        }
    }

    /* JADX INFO: renamed from: findBestCandidate-4WY_MpI, reason: not valid java name */
    private static final FocusTargetNode m2834findBestCandidate4WY_MpI(MutableVector<FocusTargetNode> mutableVector, Rect rect, int i) {
        Rect rectTranslate;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        FocusTargetNode focusTargetNode = null;
        if (FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
            rectTranslate = rect.translate((rect.getRight() - rect.getLeft()) + 1.0f, 0.0f);
        } else if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
            rectTranslate = rect.translate(-((rect.getRight() - rect.getLeft()) + 1.0f), 0.0f);
        } else if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
            rectTranslate = rect.translate(0.0f, (rect.getBottom() - rect.getTop()) + 1.0f);
        } else {
            if (!FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
                k2d.a(InvalidFocusDirection);
                return null;
            }
            rectTranslate = rect.translate(0.0f, -((rect.getBottom() - rect.getTop()) + 1.0f));
        }
        FocusTargetNode[] focusTargetNodeArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i2 = 0; i2 < size; i2++) {
            FocusTargetNode focusTargetNode2 = focusTargetNodeArr[i2];
            if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2)) {
                Rect rectFocusRect = FocusTraversalKt.focusRect(focusTargetNode2);
                if (m2837isBetterCandidateI7lrPNg(rectFocusRect, rectTranslate, rect, i)) {
                    focusTargetNode = focusTargetNode2;
                    rectTranslate = rectFocusRect;
                }
            }
        }
        return focusTargetNode;
    }

    /* JADX INFO: renamed from: findChildCorrespondingToFocusEnter--OM-vw8, reason: not valid java name */
    public static final boolean m2835findChildCorrespondingToFocusEnterOMvw8(FocusTargetNode focusTargetNode, int i, Function1<? super FocusTargetNode, Boolean> function1) {
        Rect rectBottomRight;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16], 0);
        collectAccessibleChildren(focusTargetNode, mutableVector);
        if (mutableVector.getSize() <= 1) {
            FocusTargetNode focusTargetNode2 = (FocusTargetNode) (mutableVector.getSize() == 0 ? null : mutableVector.content[0]);
            if (focusTargetNode2 != null) {
                return ((Boolean) function1.invoke(focusTargetNode2)).booleanValue();
            }
            return false;
        }
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m2759equalsimpl0(i, companion.m2764getEnterdhqQ8s())) {
            i = companion.m2769getRightdhqQ8s();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s()) || FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
            rectBottomRight = topLeft(FocusTraversalKt.focusRect(focusTargetNode));
        } else {
            if (!FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s()) && !FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
                k2d.a(InvalidFocusDirection);
                return false;
            }
            rectBottomRight = bottomRight(FocusTraversalKt.focusRect(focusTargetNode));
        }
        FocusTargetNode focusTargetNodeM2834findBestCandidate4WY_MpI = m2834findBestCandidate4WY_MpI(mutableVector, rectBottomRight, i);
        if (focusTargetNodeM2834findBestCandidate4WY_MpI != null) {
            return ((Boolean) function1.invoke(focusTargetNodeM2834findBestCandidate4WY_MpI)).booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    private static final boolean m2836generateAndSearchChildren4C6V_qg(final FocusTargetNode focusTargetNode, final Rect rect, final int i, final Function1<? super FocusTargetNode, Boolean> function1) {
        if (m2838searchChildren4C6V_qg(focusTargetNode, rect, i, function1)) {
            return true;
        }
        final FocusTargetNode activeFocusTargetNode = DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().getActiveFocusTargetNode();
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m2754searchBeyondBoundsOMvw8(focusTargetNode, i, new Function1<BeyondBoundsLayout.BeyondBoundsScope, Boolean>() { // from class: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public final Boolean invoke(BeyondBoundsLayout.BeyondBoundsScope beyondBoundsScope) {
                if (activeFocusTargetNode != DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().getActiveFocusTargetNode()) {
                    return Boolean.TRUE;
                }
                boolean zM2838searchChildren4C6V_qg = TwoDimensionalFocusSearchKt.m2838searchChildren4C6V_qg(focusTargetNode, rect, i, function1);
                Boolean boolValueOf = Boolean.valueOf(zM2838searchChildren4C6V_qg);
                if (zM2838searchChildren4C6V_qg || !beyondBoundsScope.getHasMoreContent()) {
                    return boolValueOf;
                }
                return null;
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: isBetterCandidate-I7lrPNg, reason: not valid java name */
    public static final boolean m2837isBetterCandidateI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (!isBetterCandidate_I7lrPNg$isCandidate(rect, i, rect3)) {
            return false;
        }
        if (isBetterCandidate_I7lrPNg$isCandidate(rect2, i, rect3) && !m2833beamBeatsI7lrPNg(rect3, rect, rect2, i)) {
            return !m2833beamBeatsI7lrPNg(rect3, rect2, rect, i) && isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect) < isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect2);
        }
        return true;
    }

    private static final boolean isBetterCandidate_I7lrPNg$isCandidate(Rect rect, int i, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
            return (rect2.getRight() > rect.getRight() || rect2.getLeft() >= rect.getRight()) && rect2.getLeft() > rect.getLeft();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
            return (rect2.getLeft() < rect.getLeft() || rect2.getRight() <= rect.getLeft()) && rect2.getRight() < rect.getRight();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
            return (rect2.getBottom() > rect.getBottom() || rect2.getTop() >= rect.getBottom()) && rect2.getTop() > rect.getTop();
        }
        if (FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
            return (rect2.getTop() < rect.getTop() || rect2.getBottom() <= rect.getTop()) && rect2.getBottom() < rect.getBottom();
        }
        k2d.a(InvalidFocusDirection);
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0056 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0057 A[RETURN] */
    private static final float isBetterCandidate_I7lrPNg$majorAxisDistance(Rect rect, int i, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s())) {
            if (FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
                    k2d.a(InvalidFocusDirection);
                    return 0.0f;
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f = top - bottom;
            if (f < 0.0f) {
                return 0.0f;
            }
            return f;
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f = top2 - bottom2;
        if (f < 0.0f) {
            return 0.0f;
        }
        return f;
    }

    private static final float isBetterCandidate_I7lrPNg$minorAxisDistance(Rect rect, int i, Rect rect2) {
        float top;
        float top2;
        float bottom;
        float top3;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m2759equalsimpl0(i, companion.m2766getLeftdhqQ8s()) || FocusDirection.m2759equalsimpl0(i, companion.m2769getRightdhqQ8s())) {
            top = rect2.getTop() + ((rect2.getBottom() - rect2.getTop()) / 2.0f);
            top2 = rect.getTop();
            bottom = rect.getBottom();
            top3 = rect.getTop();
        } else {
            if (!FocusDirection.m2759equalsimpl0(i, companion.m2770getUpdhqQ8s()) && !FocusDirection.m2759equalsimpl0(i, companion.m2763getDowndhqQ8s())) {
                k2d.a(InvalidFocusDirection);
                return 0.0f;
            }
            top = rect2.getLeft() + ((rect2.getRight() - rect2.getLeft()) / 2.0f);
            top2 = rect.getLeft();
            bottom = rect.getRight();
            top3 = rect.getLeft();
        }
        return top - (top2 + ((bottom - top3) / 2.0f));
    }

    private static final long isBetterCandidate_I7lrPNg$weightedDistance(int i, Rect rect, Rect rect2) {
        long jIsBetterCandidate_I7lrPNg$majorAxisDistance = (long) isBetterCandidate_I7lrPNg$majorAxisDistance(rect2, i, rect);
        long jIsBetterCandidate_I7lrPNg$minorAxisDistance = (long) isBetterCandidate_I7lrPNg$minorAxisDistance(rect2, i, rect);
        return (13 * jIsBetterCandidate_I7lrPNg$majorAxisDistance * jIsBetterCandidate_I7lrPNg$majorAxisDistance) + (jIsBetterCandidate_I7lrPNg$minorAxisDistance * jIsBetterCandidate_I7lrPNg$minorAxisDistance);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m2838searchChildren4C6V_qg(FocusTargetNode focusTargetNode, Rect rect, int i, Function1<? super FocusTargetNode, Boolean> function1) {
        FocusTargetNode focusTargetNodeM2834findBestCandidate4WY_MpI;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16], 0);
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusTargetNode.getNode(), false);
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.getSize() != 0) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet() & iM4949constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop, false);
            } else {
                while (nodePop != null) {
                    if ((nodePop.getKindSet() & iM4949constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                                if (focusTargetNode2.getIsAttached()) {
                                    mutableVector.add(focusTargetNode2);
                                }
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate(); delegate$ui != null; delegate$ui = delegate$ui.getChild()) {
                                    if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            nodePop = delegate$ui;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector3.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector3.add(delegate$ui);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                        }
                        break;
                    }
                    nodePop = nodePop.getChild();
                }
            }
        }
        while (mutableVector.getSize() != 0 && (focusTargetNodeM2834findBestCandidate4WY_MpI = m2834findBestCandidate4WY_MpI(mutableVector, rect, i)) != null) {
            if (focusTargetNodeM2834findBestCandidate4WY_MpI.fetchFocusProperties$ui().getCanFocus()) {
                return ((Boolean) function1.invoke(focusTargetNodeM2834findBestCandidate4WY_MpI)).booleanValue();
            }
            if (m2836generateAndSearchChildren4C6V_qg(focusTargetNodeM2834findBestCandidate4WY_MpI, rect, i, function1)) {
                return true;
            }
            mutableVector.remove(focusTargetNodeM2834findBestCandidate4WY_MpI);
        }
        return false;
    }

    private static final Rect topLeft(Rect rect) {
        return new Rect(rect.getLeft(), rect.getTop(), rect.getLeft(), rect.getTop());
    }

    /* JADX INFO: renamed from: twoDimensionalFocusSearch-sMXa3k8, reason: not valid java name */
    public static final Boolean m2839twoDimensionalFocusSearchsMXa3k8(FocusTargetNode focusTargetNode, int i, Rect rect, Function1<? super FocusTargetNode, Boolean> function1) {
        FocusStateImpl focusState = focusTargetNode.getFocusState();
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i2 = iArr[focusState.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                return Boolean.valueOf(m2835findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, function1));
            }
            if (i2 != 4) {
                bu8.a();
                return null;
            }
            if (focusTargetNode.fetchFocusProperties$ui().getCanFocus()) {
                return (Boolean) function1.invoke(focusTargetNode);
            }
            return rect == null ? Boolean.valueOf(m2835findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, function1)) : Boolean.valueOf(m2838searchChildren4C6V_qg(focusTargetNode, rect, i, function1));
        }
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild == null) {
            k2d.a(NoActiveChild);
            return null;
        }
        int i3 = iArr[activeChild.getFocusState().ordinal()];
        if (i3 == 1) {
            Boolean boolM2839twoDimensionalFocusSearchsMXa3k8 = m2839twoDimensionalFocusSearchsMXa3k8(activeChild, i, rect, function1);
            if (!Intrinsics.areEqual(boolM2839twoDimensionalFocusSearchsMXa3k8, Boolean.FALSE)) {
                return boolM2839twoDimensionalFocusSearchsMXa3k8;
            }
            if (rect == null) {
                rect = FocusTraversalKt.focusRect(activeNode(activeChild));
            }
            return Boolean.valueOf(m2836generateAndSearchChildren4C6V_qg(focusTargetNode, rect, i, function1));
        }
        if (i3 == 2 || i3 == 3) {
            if (rect == null) {
                rect = FocusTraversalKt.focusRect(activeChild);
            }
            return Boolean.valueOf(m2836generateAndSearchChildren4C6V_qg(focusTargetNode, rect, i, function1));
        }
        if (i3 != 4) {
            bu8.a();
            return null;
        }
        k2d.a(NoActiveChild);
        return null;
    }
}
