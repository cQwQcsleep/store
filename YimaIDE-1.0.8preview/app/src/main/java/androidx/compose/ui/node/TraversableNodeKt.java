package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u0005¢\u0006\u0002\u0010\u0006\u001a(\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\u0007\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\r\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\r\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\n\u001a-\u0010\u000e\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0002\u0010\f¨\u0006\u0010"}, d2 = {"findNearestAncestor", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/DelegatableNode;", "key", "", "T", "(Landroidx/compose/ui/node/TraversableNode;)Landroidx/compose/ui/node/TraversableNode;", "traverseAncestors", "", "block", "Lkotlin/Function1;", "", "(Landroidx/compose/ui/node/TraversableNode;Lkotlin/jvm/functions/Function1;)V", "traverseChildren", "traverseDescendants", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TraversableNodeKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    public static final <T extends TraversableNode> T findNearestAncestor(T t) {
        NodeChain nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(262144);
        if (!t.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = t.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(t);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4949constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                T t2 = (T) nodePop;
                                if (Intrinsics.areEqual(t.getTraverseKey(), t2.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, t2)) {
                                    return t2;
                                }
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui != null) {
                                    if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                mutableVector.add(nodePop);
                                                nodePop = 0;
                                            }
                                            mutableVector.add(delegate$ui);
                                        }
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v14 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    public static final <T extends androidx.compose.ui.node.TraversableNode> void traverseAncestors(T r11, kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> r12) {
        /*
            r0 = 262144(0x40000, float:3.67342E-40)
            int r0 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r11.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 != 0) goto L16
            java.lang.String r1 = "visitAncestors called on an unattached node"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r1)
        L16:
            androidx.compose.ui.Modifier$Node r1 = r11.getNode()
            androidx.compose.ui.Modifier$Node r1 = r1.getParent()
            androidx.compose.ui.node.LayoutNode r2 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r11)
        L22:
            if (r2 == 0) goto Lc7
            androidx.compose.ui.node.NodeChain r3 = r2.getNodes()
            androidx.compose.ui.Modifier$Node r3 = r3.getHead()
            int r3 = r3.getAggregateChildKindSet()
            r3 = r3 & r0
            r4 = 0
            if (r3 == 0) goto Lb2
        L34:
            if (r1 == 0) goto Lb2
            int r3 = r1.getKindSet()
            r3 = r3 & r0
            if (r3 == 0) goto Lad
            r3 = r1
            r5 = r4
        L3f:
            if (r3 == 0) goto Lad
            boolean r6 = r3 instanceof androidx.compose.ui.node.TraversableNode
            r7 = 1
            if (r6 == 0) goto L69
            androidx.compose.ui.node.TraversableNode r3 = (androidx.compose.ui.node.TraversableNode) r3
            java.lang.Object r6 = r11.getTraverseKey()
            java.lang.Object r8 = r3.getTraverseKey()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r8)
            if (r6 == 0) goto L66
            boolean r6 = androidx.compose.ui.Actual_jvmKt.areObjectsOfSameType(r11, r3)
            if (r6 == 0) goto L66
            java.lang.Object r3 = r12.invoke(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r7 = r3.booleanValue()
        L66:
            if (r7 != 0) goto La8
            goto Lc7
        L69:
            int r6 = r3.getKindSet()
            r6 = r6 & r0
            if (r6 == 0) goto La8
            boolean r6 = r3 instanceof androidx.compose.ui.node.DelegatingNode
            if (r6 == 0) goto La8
            r6 = r3
            androidx.compose.ui.node.DelegatingNode r6 = (androidx.compose.ui.node.DelegatingNode) r6
            androidx.compose.ui.Modifier$Node r6 = r6.getDelegate()
            r8 = 0
            r9 = r8
        L7d:
            if (r6 == 0) goto La5
            int r10 = r6.getKindSet()
            r10 = r10 & r0
            if (r10 == 0) goto La0
            int r9 = r9 + 1
            if (r9 != r7) goto L8c
            r3 = r6
            goto La0
        L8c:
            if (r5 != 0) goto L97
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            r10 = 16
            androidx.compose.ui.Modifier$Node[] r10 = new androidx.compose.ui.Modifier.Node[r10]
            r5.<init>(r10, r8)
        L97:
            if (r3 == 0) goto L9d
            r5.add(r3)
            r3 = r4
        L9d:
            r5.add(r6)
        La0:
            androidx.compose.ui.Modifier$Node r6 = r6.getChild()
            goto L7d
        La5:
            if (r9 != r7) goto La8
            goto L3f
        La8:
            androidx.compose.ui.Modifier$Node r3 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r5)
            goto L3f
        Lad:
            androidx.compose.ui.Modifier$Node r1 = r1.getParent()
            goto L34
        Lb2:
            androidx.compose.ui.node.LayoutNode r2 = r2.getParent$ui()
            if (r2 == 0) goto Lc4
            androidx.compose.ui.node.NodeChain r1 = r2.getNodes()
            if (r1 == 0) goto Lc4
            androidx.compose.ui.Modifier$Node r1 = r1.getTail()
            goto L22
        Lc4:
            r1 = r4
            goto L22
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseAncestors(androidx.compose.ui.node.TraversableNode, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v19 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    public static final <T extends androidx.compose.ui.node.TraversableNode> void traverseChildren(T r11, kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> r12) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseChildren(androidx.compose.ui.node.TraversableNode, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v10 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    public static final <T extends androidx.compose.ui.node.TraversableNode> void traverseDescendants(T r13, kotlin.jvm.functions.Function1<? super T, ? extends androidx.compose.ui.node.TraversableNode.Companion.TraverseDescendantsAction> r14) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseDescendants(androidx.compose.ui.node.TraversableNode, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12 */
    public static final TraversableNode findNearestAncestor(DelegatableNode delegatableNode, Object obj) {
        NodeChain nodes;
        int iM4949constructorimpl = NodeKind.m4949constructorimpl(262144);
        if (!delegatableNode.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM4949constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM4949constructorimpl) != 0) {
                        Modifier.Node nodePop = parent;
                        MutableVector mutableVector = null;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (Intrinsics.areEqual(obj, traversableNode.getTraverseKey())) {
                                    return traversableNode;
                                }
                            } else if ((nodePop.getKindSet() & iM4949constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui != null) {
                                    if ((delegate$ui.getKindSet() & iM4949constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                mutableVector.add(nodePop);
                                                nodePop = 0;
                                            }
                                            mutableVector.add(delegate$ui);
                                        }
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v14 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    public static final void traverseAncestors(androidx.compose.ui.node.DelegatableNode r10, java.lang.Object r11, kotlin.jvm.functions.Function1<? super androidx.compose.ui.node.TraversableNode, java.lang.Boolean> r12) {
        /*
            r0 = 262144(0x40000, float:3.67342E-40)
            int r0 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r10.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 != 0) goto L16
            java.lang.String r1 = "visitAncestors called on an unattached node"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r1)
        L16:
            androidx.compose.ui.Modifier$Node r1 = r10.getNode()
            androidx.compose.ui.Modifier$Node r1 = r1.getParent()
            androidx.compose.ui.node.LayoutNode r10 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutNode(r10)
        L22:
            if (r10 == 0) goto Lbd
            androidx.compose.ui.node.NodeChain r2 = r10.getNodes()
            androidx.compose.ui.Modifier$Node r2 = r2.getHead()
            int r2 = r2.getAggregateChildKindSet()
            r2 = r2 & r0
            r3 = 0
            if (r2 == 0) goto La8
        L34:
            if (r1 == 0) goto La8
            int r2 = r1.getKindSet()
            r2 = r2 & r0
            if (r2 == 0) goto La3
            r2 = r1
            r4 = r3
        L3f:
            if (r2 == 0) goto La3
            boolean r5 = r2 instanceof androidx.compose.ui.node.TraversableNode
            r6 = 1
            if (r5 == 0) goto L5f
            androidx.compose.ui.node.TraversableNode r2 = (androidx.compose.ui.node.TraversableNode) r2
            java.lang.Object r5 = r2.getTraverseKey()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r5)
            if (r5 == 0) goto L5c
            java.lang.Object r2 = r12.invoke(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r6 = r2.booleanValue()
        L5c:
            if (r6 != 0) goto L9e
            goto Lbd
        L5f:
            int r5 = r2.getKindSet()
            r5 = r5 & r0
            if (r5 == 0) goto L9e
            boolean r5 = r2 instanceof androidx.compose.ui.node.DelegatingNode
            if (r5 == 0) goto L9e
            r5 = r2
            androidx.compose.ui.node.DelegatingNode r5 = (androidx.compose.ui.node.DelegatingNode) r5
            androidx.compose.ui.Modifier$Node r5 = r5.getDelegate()
            r7 = 0
            r8 = r7
        L73:
            if (r5 == 0) goto L9b
            int r9 = r5.getKindSet()
            r9 = r9 & r0
            if (r9 == 0) goto L96
            int r8 = r8 + 1
            if (r8 != r6) goto L82
            r2 = r5
            goto L96
        L82:
            if (r4 != 0) goto L8d
            androidx.compose.runtime.collection.MutableVector r4 = new androidx.compose.runtime.collection.MutableVector
            r9 = 16
            androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r9]
            r4.<init>(r9, r7)
        L8d:
            if (r2 == 0) goto L93
            r4.add(r2)
            r2 = r3
        L93:
            r4.add(r5)
        L96:
            androidx.compose.ui.Modifier$Node r5 = r5.getChild()
            goto L73
        L9b:
            if (r8 != r6) goto L9e
            goto L3f
        L9e:
            androidx.compose.ui.Modifier$Node r2 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r4)
            goto L3f
        La3:
            androidx.compose.ui.Modifier$Node r1 = r1.getParent()
            goto L34
        La8:
            androidx.compose.ui.node.LayoutNode r10 = r10.getParent$ui()
            if (r10 == 0) goto Lba
            androidx.compose.ui.node.NodeChain r1 = r10.getNodes()
            if (r1 == 0) goto Lba
            androidx.compose.ui.Modifier$Node r1 = r1.getTail()
            goto L22
        Lba:
            r1 = r3
            goto L22
        Lbd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseAncestors(androidx.compose.ui.node.DelegatableNode, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v17 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    public static final void traverseChildren(androidx.compose.ui.node.DelegatableNode r10, java.lang.Object r11, kotlin.jvm.functions.Function1<? super androidx.compose.ui.node.TraversableNode, java.lang.Boolean> r12) {
        /*
            r0 = 262144(0x40000, float:3.67342E-40)
            int r0 = androidx.compose.ui.node.NodeKind.m4949constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r10.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 != 0) goto L16
            java.lang.String r1 = "visitChildren called on an unattached node"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r1)
        L16:
            androidx.compose.runtime.collection.MutableVector r1 = new androidx.compose.runtime.collection.MutableVector
            r2 = 16
            androidx.compose.ui.Modifier$Node[] r3 = new androidx.compose.ui.Modifier.Node[r2]
            r4 = 0
            r1.<init>(r3, r4)
            androidx.compose.ui.Modifier$Node r3 = r10.getNode()
            androidx.compose.ui.Modifier$Node r3 = r3.getChild()
            if (r3 != 0) goto L32
            androidx.compose.ui.Modifier$Node r10 = r10.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r10, r4)
            goto L35
        L32:
            r1.add(r3)
        L35:
            int r10 = r1.getSize()
            if (r10 == 0) goto Lc4
            int r10 = r1.getSize()
            r3 = 1
            int r10 = r10 - r3
            java.lang.Object r10 = r1.removeAt(r10)
            androidx.compose.ui.Modifier$Node r10 = (androidx.compose.ui.Modifier.Node) r10
            int r5 = r10.getAggregateChildKindSet()
            r5 = r5 & r0
            if (r5 != 0) goto L52
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r10, r4)
            goto L35
        L52:
            if (r10 == 0) goto L35
            int r5 = r10.getKindSet()
            r5 = r5 & r0
            if (r5 == 0) goto Lbf
            r5 = 0
            r6 = r5
        L5d:
            if (r10 == 0) goto L35
            boolean r7 = r10 instanceof androidx.compose.ui.node.TraversableNode
            if (r7 == 0) goto L7e
            androidx.compose.ui.node.TraversableNode r10 = (androidx.compose.ui.node.TraversableNode) r10
            java.lang.Object r7 = r10.getTraverseKey()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r7)
            if (r7 == 0) goto L7a
            java.lang.Object r10 = r12.invoke(r10)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            goto L7b
        L7a:
            r10 = r3
        L7b:
            if (r10 != 0) goto Lba
            return
        L7e:
            int r7 = r10.getKindSet()
            r7 = r7 & r0
            if (r7 == 0) goto Lba
            boolean r7 = r10 instanceof androidx.compose.ui.node.DelegatingNode
            if (r7 == 0) goto Lba
            r7 = r10
            androidx.compose.ui.node.DelegatingNode r7 = (androidx.compose.ui.node.DelegatingNode) r7
            androidx.compose.ui.Modifier$Node r7 = r7.getDelegate()
            r8 = r4
        L91:
            if (r7 == 0) goto Lb7
            int r9 = r7.getKindSet()
            r9 = r9 & r0
            if (r9 == 0) goto Lb2
            int r8 = r8 + 1
            if (r8 != r3) goto La0
            r10 = r7
            goto Lb2
        La0:
            if (r6 != 0) goto La9
            androidx.compose.runtime.collection.MutableVector r6 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r2]
            r6.<init>(r9, r4)
        La9:
            if (r10 == 0) goto Laf
            r6.add(r10)
            r10 = r5
        Laf:
            r6.add(r7)
        Lb2:
            androidx.compose.ui.Modifier$Node r7 = r7.getChild()
            goto L91
        Lb7:
            if (r8 != r3) goto Lba
            goto L5d
        Lba:
            androidx.compose.ui.Modifier$Node r10 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r6)
            goto L5d
        Lbf:
            androidx.compose.ui.Modifier$Node r10 = r10.getChild()
            goto L52
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseChildren(androidx.compose.ui.node.DelegatableNode, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /*  JADX ERROR: NullPointerException in pass: PrepareForCodeGen
        java.lang.NullPointerException
        */
    public static final void traverseDescendants(androidx.compose.ui.node.DelegatableNode r12, java.lang.Object r13, kotlin.jvm.functions.Function1<? super androidx.compose.ui.node.TraversableNode, ? extends androidx.compose.ui.node.TraversableNode.Companion.TraverseDescendantsAction> r14) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.TraversableNodeKt.traverseDescendants(androidx.compose.ui.node.DelegatableNode, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }
}
