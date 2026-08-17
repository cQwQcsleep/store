package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.layout.BeyondBoundsLayoutProviderModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a3\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0011\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a1\u0010\u0013\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a1\u0010\u0014\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\nH\u0080\b\u001a)\u0010\u0015\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a3\u0010\u0015\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a)\u0010\u0016\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a>\u0010\u0017\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001b\u0010\u001c\u001a>\u0010\u0015\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001d\u0010\u001c\u001a>\u0010\u0016\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001e\u0010\u001c\u001aH\u0010\u0004\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001f\u0010 \u001aJ\u0010!\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b#\u0010$\u001a<\u0010%\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010&\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0080\b¢\u0006\u0004\b'\u0010(\u001a<\u0010)\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010*\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0080\b¢\u0006\u0004\b+\u0010,\u001a0\u0010\f\u001a\u0004\u0018\u0001H\u0018\"\n\b\u0000\u0010\u0018\u0018\u0001*\u00020-*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0080\b¢\u0006\u0004\b.\u0010/\u001aH\u0010\u0013\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b0\u0010 \u001aH\u00101\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b2\u0010 \u001aH\u0010\u0014\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00010\nH\u0080\b¢\u0006\u0004\b3\u0010 \u001aH\u00104\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b5\u0010 \u001a\u001f\u00106\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0000¢\u0006\u0004\b7\u00108\u001a\u001f\u00109\u001a\u00020:*\u00020\u00022\n\u0010;\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0000¢\u0006\u0004\b<\u0010=\u001a\f\u0010>\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\f\u0010?\u001a\u00020@*\u00020\u0002H\u0000\u001a\f\u0010A\u001a\u00020B*\u00020\u0002H\u0000\u001a\n\u0010C\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010D\u001a\u00020E*\u00020\u0002\u001a\n\u0010F\u001a\u00020G*\u00020\u0002\u001a\n\u0010H\u001a\u00020I*\u00020\u0002\u001a\n\u0010J\u001a\u00020K*\u00020\u0002\u001a\n\u0010L\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010M\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010N\u001a\u00020\u0005*\u00020\u0002\u001a\u0019\u0010O\u001a\u00020\u0005*\u00020\u00022\u0006\u0010P\u001a\u00020Q¢\u0006\u0004\bR\u0010S\u001a\f\u0010T\u001a\u0004\u0018\u00010U*\u00020\u0002\u001a\u000e\u0010V\u001a\u0004\u0018\u00010W*\u00020\u000bH\u0000\u001a>\u0010X\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u000b2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\bY\u0010Z\u001a\u0016\u0010[\u001a\u0004\u0018\u00010\u000b*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000eH\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\\"}, d2 = {"isDelegationRoot", "", "Landroidx/compose/ui/node/DelegatableNode;", "(Landroidx/compose/ui/node/DelegatableNode;)Z", "visitAncestors", "", "mask", "", "includeSelf", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier$Node;", "nearestAncestor", "getChildren", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/LayoutNode;", "zOrder", "addLayoutNodeChildren", "node", "visitChildren", "visitSubtreeIf", "visitLocalDescendants", "visitLocalAncestors", "visitSelfAndLocalDescendants", "T", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "visitSelfAndLocalDescendants-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;)V", "visitLocalDescendants-6rFNWt0", "visitLocalAncestors-6rFNWt0", "visitAncestors-Y-YKmho", "(Landroidx/compose/ui/node/DelegatableNode;IZLkotlin/jvm/functions/Function1;)V", "visitSelfAndAncestors", "untilType", "visitSelfAndAncestors-5BbP62I", "(Landroidx/compose/ui/node/DelegatableNode;IILkotlin/jvm/functions/Function1;)V", "ancestors", "", "ancestors-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;IZ)Ljava/util/List;", "setOfAncestors", "", "setOfAncestors-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;IZ)Ljava/util/Set;", "", "nearestAncestor-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "visitChildren-Y-YKmho", "visitSelfAndChildren", "visitSelfAndChildren-Y-YKmho", "visitSubtreeIf-Y-YKmho", "visitSubtree", "visitSubtree-Y-YKmho", "has", "has-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Z", "requireCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "kind", "requireCoordinator-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Landroidx/compose/ui/node/NodeCoordinator;", "requireLayoutNode", "requireSemanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "requireOwner", "Landroidx/compose/ui/node/Owner;", "requestAutofill", "requireDensity", "Landroidx/compose/ui/unit/Density;", "requireGraphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "requireLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "requireLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invalidateSubtree", "invalidateMeasurementForSubtree", "invalidateDrawForSubtree", "dispatchOnScrollChanged", "delta", "Landroidx/compose/ui/geometry/Offset;", "dispatchOnScrollChanged-Uv8p0NA", "(Landroidx/compose/ui/node/DelegatableNode;J)V", "findNearestBeyondBoundsLayoutAncestor", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "asLayoutModifierNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "dispatchForKind", "dispatchForKind-6rFNWt0", "(Landroidx/compose/ui/Modifier$Node;ILkotlin/jvm/functions/Function1;)V", "pop", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DelegatableNodeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void addLayoutNodeChildren(MutableVector<Modifier.Node> mutableVector, Modifier.Node node, boolean z) {
        MutableVector<LayoutNode> children = getChildren(requireLayoutNode(node), z);
        int size = children.getSize() - 1;
        LayoutNode[] layoutNodeArr = children.content;
        if (size < layoutNodeArr.length) {
            while (size >= 0) {
                mutableVector.add(layoutNodeArr[size].getNodes().getHead());
                size--;
            }
        }
    }

    /* JADX INFO: renamed from: ancestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> List<T> m4781ancestors6rFNWt0(DelegatableNode delegatableNode, int i, boolean z) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        ArrayList arrayList = null;
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(nodePop);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: ancestors-6rFNWt0$default, reason: not valid java name */
    public static /* synthetic */ List m4782ancestors6rFNWt0$default(DelegatableNode delegatableNode, int i, boolean z, int i2, Object obj) {
        NodeChain nodes;
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        ArrayList arrayList = null;
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(nodePop);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        if ((NodeKind.m4949constructorimpl(2) & node.getKindSet()) != 0) {
            if (node instanceof LayoutModifierNode) {
                return (LayoutModifierNode) node;
            }
            if (node instanceof DelegatingNode) {
                Modifier.Node delegate$ui = ((DelegatingNode) node).getDelegate();
                while (delegate$ui != 0) {
                    if (delegate$ui instanceof LayoutModifierNode) {
                        return (LayoutModifierNode) delegate$ui;
                    }
                    delegate$ui = (!(delegate$ui instanceof DelegatingNode) || (NodeKind.m4949constructorimpl(2) & delegate$ui.getKindSet()) == 0) ? delegate$ui.getChild() : ((DelegatingNode) delegate$ui).getDelegate();
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: dispatchForKind-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4783dispatchForKind6rFNWt0(Modifier.Node node, int i, Function1<? super T, Unit> function1) {
        while (node != null) {
            Intrinsics.reifiedOperationMarker(3, "T");
            function1.invoke(node);
            node = pop(null);
        }
    }

    /* JADX INFO: renamed from: dispatchOnScrollChanged-Uv8p0NA, reason: not valid java name */
    public static final void m4784dispatchOnScrollChangedUv8p0NA(DelegatableNode delegatableNode, long j) {
        requireOwner(delegatableNode).mo5015dispatchOnScrollChangedk4lQ0M(j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static final BeyondBoundsLayout findNearestBeyondBoundsLayoutAncestor(DelegatableNode delegatableNode) {
        NodeChain nodes;
        ?? parent;
        ?? r6;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(8388608) | NodeKind.m4949constructorimpl(32);
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent2 = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                while (parent != 0) {
                    if ((parent.getKindSet() & iM4949constructorimpl) != 0) {
                        if ((NodeKind.m4949constructorimpl(8388608) & parent.getKindSet()) != 0) {
                            if (!(parent instanceof BeyondBoundsLayoutProviderModifierNode)) {
                                if (parent instanceof DelegatingNode) {
                                    Modifier.Node delegate$ui = ((DelegatingNode) parent).getDelegate();
                                    parent = 0;
                                    while (delegate$ui != null) {
                                        if (delegate$ui instanceof BeyondBoundsLayoutProviderModifierNode) {
                                            parent = delegate$ui;
                                        }
                                        delegate$ui = delegate$ui.getChild();
                                        parent = parent;
                                    }
                                } else {
                                    parent = 0;
                                }
                            }
                            BeyondBoundsLayoutProviderModifierNode beyondBoundsLayoutProviderModifierNode = (BeyondBoundsLayoutProviderModifierNode) parent;
                            if (beyondBoundsLayoutProviderModifierNode != null) {
                                return beyondBoundsLayoutProviderModifierNode.getBeyondBoundsLayout();
                            }
                            return null;
                        }
                        if ((NodeKind.m4949constructorimpl(32) & parent.getKindSet()) == 0) {
                            continue;
                        } else {
                            if (parent instanceof ModifierLocalModifierNode) {
                                r6 = parent;
                            } else if (parent instanceof DelegatingNode) {
                                Modifier.Node delegate$ui2 = ((DelegatingNode) parent).getDelegate();
                                r6 = 0;
                                while (delegate$ui2 != null) {
                                    if (delegate$ui2 instanceof ModifierLocalModifierNode) {
                                        r6 = delegate$ui2;
                                    }
                                    delegate$ui2 = delegate$ui2.getChild();
                                    r6 = r6;
                                }
                            } else {
                                r6 = 0;
                            }
                            ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) r6;
                            if (modifierLocalModifierNode != null && modifierLocalModifierNode.getProvidedValues().contains$ui(BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout())) {
                                return (BeyondBoundsLayout) modifierLocalModifierNode.getProvidedValues().get$ui(BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout());
                            }
                        }
                    }
                    parent = parent.getParent();
                }
            }
            parent = parent2;
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    private static final MutableVector<LayoutNode> getChildren(LayoutNode layoutNode, boolean z) {
        return z ? layoutNode.getZSortedChildren() : layoutNode.get_children$ui();
    }

    /* JADX INFO: renamed from: has-64DMado, reason: not valid java name */
    public static final boolean m4785has64DMado(DelegatableNode delegatableNode, int i) {
        return (delegatableNode.getNode().getAggregateChildKindSet() & i) != 0;
    }

    public static final void invalidateDrawForSubtree(DelegatableNode delegatableNode) {
        if (delegatableNode.getNode().getIsAttached()) {
            LayoutNode.invalidateDrawForSubtree$default(requireLayoutNode(delegatableNode), false, 1, null);
        }
    }

    public static final void invalidateMeasurementForSubtree(DelegatableNode delegatableNode) {
        if (delegatableNode.getNode().getIsAttached()) {
            requireLayoutNode(delegatableNode).invalidateMeasurementForSubtree();
        }
    }

    public static final void invalidateSubtree(DelegatableNode delegatableNode) {
        if (delegatableNode.getNode().getIsAttached()) {
            LayoutNode.invalidateSubtree$default(requireLayoutNode(delegatableNode), false, 1, null);
        }
    }

    public static final boolean isDelegationRoot(DelegatableNode delegatableNode) {
        return delegatableNode.getNode() == delegatableNode;
    }

    public static final Modifier.Node nearestAncestor(DelegatableNode delegatableNode, int i) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("nearestAncestor called on an unattached node");
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i) != 0) {
                        return parent;
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX INFO: renamed from: nearestAncestor-64DMado, reason: not valid java name */
    public static final /* synthetic */ <T> T m4786nearestAncestor64DMado(DelegatableNode delegatableNode, int i) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Object parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (parent != null) {
                    if ((((Modifier.Node) parent).getKindSet() & i) != 0) {
                        Intrinsics.reifiedOperationMarker(3, "T");
                        return (T) parent;
                    }
                    parent = (T) ((Modifier.Node) parent).getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? (T) null : (T) nodes.getTail();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier.Node pop(MutableVector<Modifier.Node> mutableVector) {
        if (mutableVector == null || mutableVector.getSize() == 0) {
            return null;
        }
        return mutableVector.removeAt(mutableVector.getSize() - 1);
    }

    public static final void requestAutofill(DelegatableNode delegatableNode) {
        requireLayoutNode(delegatableNode).requestAutofill$ui();
    }

    /* JADX INFO: renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m4787requireCoordinator64DMado(DelegatableNode delegatableNode, int i) {
        NodeCoordinator coordinator = delegatableNode.getNode().getCoordinator();
        coordinator.getClass();
        if (coordinator.getTail() != delegatableNode || !NodeKindKt.m4958getIncludeSelfInTraversalH91voCI(i)) {
            return coordinator;
        }
        NodeCoordinator wrapped$ui = coordinator.getWrapped();
        wrapped$ui.getClass();
        return wrapped$ui;
    }

    public static final Density requireDensity(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode).getDensity();
    }

    public static final GraphicsContext requireGraphicsContext(DelegatableNode delegatableNode) {
        return requireOwner(delegatableNode).getGraphicsContext();
    }

    public static final LayoutCoordinates requireLayoutCoordinates(DelegatableNode delegatableNode) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
        }
        LayoutCoordinates coordinates = m4787requireCoordinator64DMado(delegatableNode, NodeKind.m4949constructorimpl(2)).getCoordinates();
        if (!coordinates.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates is not attached.");
        }
        return coordinates;
    }

    public static final LayoutDirection requireLayoutDirection(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode).getLayoutDirection();
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode delegatableNode) {
        NodeCoordinator coordinator = delegatableNode.getNode().getCoordinator();
        if (coordinator != null) {
            return coordinator.getLayoutNode();
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Cannot obtain node coordinator. Is the Modifier.Node attached?");
        wq6.a();
        return null;
    }

    public static final Owner requireOwner(DelegatableNode delegatableNode) {
        Owner owner = requireLayoutNode(delegatableNode).getOwner();
        if (owner != null) {
            return owner;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("This node does not have an owner.");
        wq6.a();
        return null;
    }

    public static final SemanticsInfo requireSemanticsInfo(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode);
    }

    /* JADX INFO: renamed from: setOfAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> Set<T> m4788setOfAncestors6rFNWt0(DelegatableNode delegatableNode, int i, boolean z) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        LinkedHashSet linkedHashSet = null;
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (linkedHashSet == null) {
                                linkedHashSet = new LinkedHashSet();
                            }
                            linkedHashSet.add(nodePop);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return linkedHashSet;
    }

    /* JADX INFO: renamed from: setOfAncestors-6rFNWt0$default, reason: not valid java name */
    public static /* synthetic */ Set m4789setOfAncestors6rFNWt0$default(DelegatableNode delegatableNode, int i, boolean z, int i2, Object obj) {
        NodeChain nodes;
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        LinkedHashSet linkedHashSet = null;
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            if (linkedHashSet == null) {
                                linkedHashSet = new LinkedHashSet();
                            }
                            linkedHashSet.add(nodePop);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return linkedHashSet;
    }

    public static final void visitAncestors(DelegatableNode delegatableNode, int i, boolean z, Function1<? super Modifier.Node, Unit> function1) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        function1.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static /* synthetic */ void visitAncestors$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        NodeChain nodes;
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        function1.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m4790visitAncestorsYYKmho(DelegatableNode delegatableNode, int i, boolean z, Function1<? super T, Unit> function1) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m4791visitAncestorsYYKmho$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        NodeChain nodes;
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = z ? delegatableNode.getNode() : delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                        }
                    }
                    node = node.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final void visitChildren(DelegatableNode delegatableNode, int i, boolean z, Function1<? super Modifier.Node, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node child2 = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((child2.getAggregateChildKindSet() & i) == 0) {
                addLayoutNodeChildren(mutableVector, child2, z);
            } else {
                while (child2 != null) {
                    if ((child2.getKindSet() & i) != 0) {
                        function1.invoke(child2);
                        break;
                    }
                    child2 = child2.getChild();
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitChildren-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m4792visitChildrenYYKmho(DelegatableNode delegatableNode, int i, boolean z, Function1<? super T, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet() & i) == 0) {
                addLayoutNodeChildren(mutableVector, nodePop, z);
            } else {
                while (nodePop != null) {
                    if ((nodePop.getKindSet() & i) != 0) {
                        while (nodePop != null) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                            nodePop = pop(null);
                        }
                        break;
                    }
                    nodePop = nodePop.getChild();
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitChildren-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m4793visitChildrenYYKmho$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet() & i) == 0) {
                addLayoutNodeChildren(mutableVector, nodePop, z);
            } else {
                while (nodePop != null) {
                    if ((nodePop.getKindSet() & i) != 0) {
                        while (nodePop != null) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                            nodePop = pop(null);
                        }
                        break;
                    }
                    nodePop = nodePop.getChild();
                }
            }
        }
    }

    public static final void visitLocalAncestors(DelegatableNode delegatableNode, int i, Function1<? super Modifier.Node, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
        }
        for (Modifier.Node parent = delegatableNode.getNode().getParent(); parent != null; parent = parent.getParent()) {
            if ((parent.getKindSet() & i) != 0) {
                function1.invoke(parent);
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4794visitLocalAncestors6rFNWt0(DelegatableNode delegatableNode, int i, Function1<? super T, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
        }
        for (Modifier.Node parent = delegatableNode.getNode().getParent(); parent != null; parent = parent.getParent()) {
            if ((parent.getKindSet() & i) != 0) {
                for (Modifier.Node nodePop = parent; nodePop != null; nodePop = pop(null)) {
                    Intrinsics.reifiedOperationMarker(3, "T");
                    function1.invoke(nodePop);
                }
            }
        }
    }

    public static final void visitLocalDescendants(DelegatableNode delegatableNode, int i, boolean z, Function1<? super Modifier.Node, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i) == 0) {
            return;
        }
        if (!z) {
            node = node.getChild();
        }
        while (node != null) {
            if ((node.getKindSet() & i) != 0) {
                function1.invoke(node);
            }
            node = node.getChild();
        }
    }

    public static /* synthetic */ void visitLocalDescendants$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i) == 0) {
            return;
        }
        if (!z) {
            node = node.getChild();
        }
        while (node != null) {
            if ((node.getKindSet() & i) != 0) {
                function1.invoke(node);
            }
            node = node.getChild();
        }
    }

    /* JADX INFO: renamed from: visitLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4795visitLocalDescendants6rFNWt0(DelegatableNode delegatableNode, int i, Function1<? super T, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i) != 0) {
            for (Modifier.Node child = node.getChild(); child != null; child = child.getChild()) {
                if ((child.getKindSet() & i) != 0) {
                    for (Modifier.Node nodePop = child; nodePop != null; nodePop = pop(null)) {
                        Intrinsics.reifiedOperationMarker(3, "T");
                        function1.invoke(nodePop);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndAncestors-5BbP62I, reason: not valid java name */
    public static final /* synthetic */ <T> void m4796visitSelfAndAncestors5BbP62I(DelegatableNode delegatableNode, int i, int i2, Function1<? super T, Unit> function1) {
        NodeChain nodes;
        Modifier.Node node = delegatableNode.getNode();
        int i3 = i | i2;
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = delegatableNode.getNode();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i3) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i3) != 0) {
                        if (node2 != node && (node2.getKindSet() & i2) != 0) {
                            return;
                        }
                        if ((node2.getKindSet() & i) != 0) {
                            for (Modifier.Node nodePop = node2; nodePop != null; nodePop = pop(null)) {
                                Intrinsics.reifiedOperationMarker(3, "T");
                                function1.invoke(nodePop);
                            }
                        }
                    }
                    node2 = node2.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m4797visitSelfAndChildrenYYKmho(DelegatableNode delegatableNode, int i, boolean z, Function1<? super T, Unit> function1) {
        for (Modifier.Node node = delegatableNode.getNode(); node != null; node = pop(null)) {
            Intrinsics.reifiedOperationMarker(3, "T");
            function1.invoke(node);
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet() & i) == 0) {
                addLayoutNodeChildren(mutableVector, nodePop, z);
            } else {
                while (nodePop != null) {
                    if ((nodePop.getKindSet() & i) != 0) {
                        while (nodePop != null) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                            nodePop = pop(null);
                        }
                        break;
                    }
                    nodePop = nodePop.getChild();
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m4798visitSelfAndChildrenYYKmho$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        for (Modifier.Node node = delegatableNode.getNode(); node != null; node = pop(null)) {
            Intrinsics.reifiedOperationMarker(3, "T");
            function1.invoke(node);
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet() & i) == 0) {
                addLayoutNodeChildren(mutableVector, nodePop, z);
            } else {
                while (nodePop != null) {
                    if ((nodePop.getKindSet() & i) != 0) {
                        while (nodePop != null) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                            nodePop = pop(null);
                        }
                        break;
                    }
                    nodePop = nodePop.getChild();
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m4799visitSelfAndLocalDescendants6rFNWt0(DelegatableNode delegatableNode, int i, Function1<? super T, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i) != 0) {
            while (node != null) {
                if ((node.getKindSet() & i) != 0) {
                    for (Modifier.Node nodePop = node; nodePop != null; nodePop = pop(null)) {
                        Intrinsics.reifiedOperationMarker(3, "T");
                        function1.invoke(nodePop);
                    }
                }
                node = node.getChild();
            }
        }
    }

    /* JADX INFO: renamed from: visitSubtree-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m4800visitSubtreeYYKmho(DelegatableNode delegatableNode, int i, boolean z, Function1<? super T, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & i) != 0) {
                for (Modifier.Node child2 = node; child2 != null && child2.getIsAttached(); child2 = child2.getChild()) {
                    if ((child2.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = child2; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                        }
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node, z);
        }
    }

    /* JADX INFO: renamed from: visitSubtree-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m4801visitSubtreeYYKmho$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & i) != 0) {
                for (Modifier.Node child2 = node; child2 != null && child2.getIsAttached(); child2 = child2.getChild()) {
                    if ((child2.getKindSet() & i) != 0) {
                        for (Modifier.Node nodePop = child2; nodePop != null; nodePop = pop(null)) {
                            Intrinsics.reifiedOperationMarker(3, "T");
                            function1.invoke(nodePop);
                        }
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node, z);
        }
    }

    public static final void visitSubtreeIf(DelegatableNode delegatableNode, int i, boolean z, Function1<? super Modifier.Node, Boolean> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & i) != 0) {
                Modifier.Node child2 = node;
                while (true) {
                    if (child2 != null && child2.getIsAttached()) {
                        if ((child2.getKindSet() & i) != 0 && !((Boolean) function1.invoke(child2)).booleanValue()) {
                            break;
                        } else {
                            child2 = child2.getChild();
                        }
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node, z);
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m4802visitSubtreeIfYYKmho(DelegatableNode delegatableNode, int i, boolean z, Function1<? super T, Boolean> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & i) != 0) {
                Modifier.Node child2 = node;
                while (true) {
                    if (child2 != null && child2.getIsAttached()) {
                        if ((child2.getKindSet() & i) != 0) {
                            for (Modifier.Node nodePop = child2; nodePop != null; nodePop = pop(null)) {
                                Intrinsics.reifiedOperationMarker(3, "T");
                                if (!((Boolean) function1.invoke(nodePop)).booleanValue()) {
                                    break;
                                }
                            }
                        }
                        child2 = child2.getChild();
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node, z);
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m4803visitSubtreeIfYYKmho$default(DelegatableNode delegatableNode, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(mutableVector, delegatableNode.getNode(), z);
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.getSize() != 0) {
            Modifier.Node node = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((node.getAggregateChildKindSet() & i) != 0) {
                Modifier.Node child2 = node;
                while (true) {
                    if (child2 != null && child2.getIsAttached()) {
                        if ((child2.getKindSet() & i) != 0) {
                            for (Modifier.Node nodePop = child2; nodePop != null; nodePop = pop(null)) {
                                Intrinsics.reifiedOperationMarker(3, "T");
                                if (!((Boolean) function1.invoke(nodePop)).booleanValue()) {
                                    break;
                                }
                            }
                        }
                        child2 = child2.getChild();
                    }
                }
            }
            addLayoutNodeChildren(mutableVector, node, z);
        }
    }

    public static final void visitLocalDescendants(DelegatableNode delegatableNode, int i, Function1<? super Modifier.Node, Unit> function1) {
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        if ((node.getAggregateChildKindSet() & i) != 0) {
            for (Modifier.Node child = node.getChild(); child != null; child = child.getChild()) {
                if ((child.getKindSet() & i) != 0) {
                    function1.invoke(child);
                }
            }
        }
    }
}
