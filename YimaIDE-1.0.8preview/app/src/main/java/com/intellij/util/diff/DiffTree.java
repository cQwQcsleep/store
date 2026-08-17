package com.intellij.util.diff;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Ref;
import com.intellij.util.ThreeState;
import com.intellij.util.text.CharArrayUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DiffTree<OldNode, NewNode> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final DiffTreeChangeBuilder<?, ?> EMPTY_CONSUMER = new DiffTreeChangeBuilder<Object, Object>() { // from class: com.intellij.util.diff.DiffTree.1
        /* JADX WARN: Code duplicated, block: B:11:0x0023  */
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "newChild";
            } else if (i == 2) {
                objArr[0] = "oldParent";
            } else if (i == 3) {
                objArr[0] = "oldNode";
            } else if (i == 4) {
                objArr[0] = "oldParent";
            } else if (i != 5) {
                objArr[0] = "oldChild";
            } else {
                objArr[0] = "newNode";
            }
            objArr[1] = "com/intellij/util/diff/DiffTree$1";
            if (i == 2 || i == 3) {
                objArr[2] = "nodeDeleted";
            } else if (i == 4 || i == 5) {
                objArr[2] = "nodeInserted";
            } else {
                objArr[2] = "nodeReplaced";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.diff.DiffTreeChangeBuilder
        public void nodeDeleted(Object obj, Object obj2) {
            if (obj == null) {
                $$$reportNull$$$0(2);
            }
            if (obj2 == null) {
                $$$reportNull$$$0(3);
            }
        }

        @Override // com.intellij.util.diff.DiffTreeChangeBuilder
        public void nodeInserted(Object obj, Object obj2, int i) {
            if (obj == null) {
                $$$reportNull$$$0(4);
            }
            if (obj2 == null) {
                $$$reportNull$$$0(5);
            }
        }

        @Override // com.intellij.util.diff.DiffTreeChangeBuilder
        public void nodeReplaced(Object obj, Object obj2) {
            if (obj == null) {
                $$$reportNull$$$0(0);
            }
            if (obj2 == null) {
                $$$reportNull$$$0(1);
            }
        }
    };
    private final ShallowNodeComparator<? super OldNode, ? super NewNode> myComparator;
    private final List<Ref<NewNode[]>> myNewChildrenLists;
    private final CharSequence myNewText;
    private final FlyweightCapableTreeStructure<NewNode> myNewTree;
    private final int myNewTreeStart;
    private final List<Ref<OldNode[]>> myOldChildrenLists;
    private final CharSequence myOldText;
    private final FlyweightCapableTreeStructure<OldNode> myOldTree;
    private final int myOldTreeStart;

    public enum CompareResult {
        EQUAL,
        DRILL_DOWN_NEEDED,
        TYPE_ONLY,
        NOT_EQUAL
    }

    public enum ThreeElementMatchResult {
        FULL_START_MATCH,
        DRILL_DOWN_START_MATCH,
        REPLACE_START,
        SKIP_NEW_1,
        SKIP_NEW_2,
        SKIP_OLD_1,
        SKIP_OLD_2,
        NO_MATCH;

        public boolean hasStartMatch() {
            return this == FULL_START_MATCH || this == DRILL_DOWN_START_MATCH || this == REPLACE_START;
        }

        public int skipNewCount() {
            if (this == SKIP_NEW_1) {
                return 1;
            }
            return this == SKIP_NEW_2 ? 2 : 0;
        }

        public int skipOldCount() {
            if (this == SKIP_OLD_1) {
                return 1;
            }
            return this == SKIP_OLD_2 ? 2 : 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x000e  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 9 && i != 13) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 9 && i != 13) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 5:
                objArr[0] = "newTree";
                break;
            case 2:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "comparator";
                break;
            case 3:
            case 8:
                objArr[0] = "oldText";
                break;
            case 4:
            default:
                objArr[0] = "oldTree";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 12:
            case 14:
                objArr[0] = "consumer";
                break;
            case 9:
            case 13:
            case 17:
            case 18:
            case 19:
            case 20:
                objArr[0] = "com/intellij/util/diff/DiffTree";
                break;
            case 10:
                objArr[0] = "oldNode";
                break;
            case 11:
                objArr[0] = "newNode";
                break;
            case 15:
                objArr[0] = "oldChild";
                break;
            case 16:
                objArr[0] = "newChild";
                break;
        }
        if (i == 9) {
            objArr[1] = "emptyConsumer";
        } else if (i != 13) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                    objArr[1] = "looksEqual";
                    break;
                default:
                    objArr[1] = "com/intellij/util/diff/DiffTree";
                    break;
            }
        } else {
            objArr[1] = "build";
        }
        switch (i) {
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "diff";
                break;
            case 9:
            case 13:
            case 17:
            case 18:
            case 19:
            case 20:
                break;
            case 10:
            case 11:
            case 12:
                objArr[2] = "build";
                break;
            case 14:
                objArr[2] = "matchLastChildren";
                break;
            case 15:
            case 16:
                objArr[2] = "textMatch";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 9 && i != 13) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    private DiffTree(FlyweightCapableTreeStructure<OldNode> flyweightCapableTreeStructure, FlyweightCapableTreeStructure<NewNode> flyweightCapableTreeStructure2, ShallowNodeComparator<? super OldNode, ? super NewNode> shallowNodeComparator, CharSequence charSequence) {
        if (flyweightCapableTreeStructure == null) {
            $$$reportNull$$$0(0);
        }
        if (flyweightCapableTreeStructure2 == null) {
            $$$reportNull$$$0(1);
        }
        if (shallowNodeComparator == null) {
            $$$reportNull$$$0(2);
        }
        if (charSequence == null) {
            $$$reportNull$$$0(3);
        }
        this.myOldChildrenLists = new ArrayList();
        this.myNewChildrenLists = new ArrayList();
        this.myOldTree = flyweightCapableTreeStructure;
        this.myNewTree = flyweightCapableTreeStructure2;
        this.myComparator = shallowNodeComparator;
        this.myOldText = charSequence;
        this.myOldTreeStart = flyweightCapableTreeStructure.getStartOffset(flyweightCapableTreeStructure.getRoot());
        this.myNewText = flyweightCapableTreeStructure2.toString(flyweightCapableTreeStructure2.getRoot());
        this.myNewTreeStart = flyweightCapableTreeStructure2.getStartOffset(flyweightCapableTreeStructure2.getRoot());
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00e6  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private CompareResult build(OldNode oldnode, NewNode newnode, int i, DiffTreeChangeBuilder<? super OldNode, ? super NewNode> diffTreeChangeBuilder) {
        Object[] objArr;
        DiffTreeChangeBuilder<? super OldNode, ? super NewNode> diffTreeChangeBuilder2;
        CompareResult compareResult;
        CompareResult compareResult2;
        DiffTree diffTree;
        DiffTree diffTree2 = this;
        DiffTreeChangeBuilder<? super OldNode, ? super NewNode> diffTreeChangeBuilder3 = diffTreeChangeBuilder;
        if (oldnode == null) {
            $$$reportNull$$$0(10);
        }
        if (newnode == null) {
            $$$reportNull$$$0(11);
        }
        if (diffTreeChangeBuilder3 == null) {
            $$$reportNull$$$0(12);
        }
        if (i == diffTree2.myNewChildrenLists.size()) {
            diffTree2.myNewChildrenLists.add(new Ref<>());
            diffTree2.myOldChildrenLists.add(new Ref<>());
        }
        Ref<OldNode[]> ref = diffTree2.myOldChildrenLists.get(i);
        int children = diffTree2.myOldTree.getChildren(oldnode, ref);
        Object[] objArr2 = (Object[]) ref.get();
        Ref<NewNode[]> ref2 = diffTree2.myNewChildrenLists.get(i);
        int children2 = diffTree2.myNewTree.getChildren(newnode, ref2);
        Object[] objArr3 = (Object[]) ref2.get();
        if (Math.abs(children - children2) <= 20) {
            if (children != 0 || children2 != 0) {
                int iMin = Math.min(children, children2);
                objArr = objArr2;
                int iMatch = diffTree2.match(objArr, children - 1, objArr3, children2 - 1, i, -1, iMin);
                DiffTree diffTree3 = this;
                int iMatch2 = diffTree3.match(objArr, 0, objArr3, 0, i, 1, (iMin - iMatch) - ((children != children2 || iMatch >= iMin) ? 0 : 1));
                if (children == children2 && iMatch + iMatch2 == children) {
                    compareResult = CompareResult.EQUAL;
                    diffTree = diffTree3;
                } else if (diffTreeChangeBuilder3 == emptyConsumer()) {
                    compareResult = CompareResult.NOT_EQUAL;
                    diffTree = diffTree3;
                } else {
                    int i2 = iMatch2;
                    int i3 = i2;
                    DiffTree diffTree4 = diffTree3;
                    while (true) {
                        int i4 = children - iMatch;
                        if (i3 >= i4 && i2 >= children2 - iMatch) {
                            break;
                        }
                        int i5 = children2 - iMatch;
                        Object[] objArr4 = objArr3;
                        int i6 = i3;
                        ThreeElementMatchResult threeElementMatchResultMatchNext3Children = diffTree4.matchNext3Children(objArr, objArr4, i6, i2, i4, i5);
                        if (threeElementMatchResultMatchNext3Children.hasStartMatch()) {
                            if (threeElementMatchResultMatchNext3Children == ThreeElementMatchResult.DRILL_DOWN_START_MATCH) {
                                diffTree4.build(objArr[i6], objArr4[i2], i + 1, diffTreeChangeBuilder3);
                            } else if (threeElementMatchResultMatchNext3Children == ThreeElementMatchResult.REPLACE_START) {
                                diffTreeChangeBuilder3.nodeReplaced(objArr[i6], objArr4[i2]);
                            }
                            i3 = i6 + 1;
                            i2++;
                        } else {
                            if (threeElementMatchResultMatchNext3Children != ThreeElementMatchResult.NO_MATCH) {
                                for (int iSkipOldCount = threeElementMatchResultMatchNext3Children.skipOldCount() - 1; iSkipOldCount >= 0; iSkipOldCount--) {
                                    diffTreeChangeBuilder3.nodeDeleted(oldnode, objArr[i6]);
                                    i6++;
                                }
                                for (int iSkipNewCount = threeElementMatchResultMatchNext3Children.skipNewCount() - 1; iSkipNewCount >= 0; iSkipNewCount--) {
                                    diffTreeChangeBuilder3.nodeInserted(oldnode, objArr4[i2], i2);
                                    i2++;
                                }
                                i3 = i6;
                            } else {
                                i3 = i6;
                                objArr4 = objArr4;
                                diffTreeChangeBuilder2 = diffTreeChangeBuilder3;
                                int i7 = i2;
                                Object[] objArr5 = objArr;
                                int iMatchLastChildren = diffTree4.matchLastChildren(i, diffTreeChangeBuilder2, i4, objArr5, i3, i5, objArr4, i7);
                                objArr = objArr5;
                                i2 = i7;
                                if (iMatchLastChildren > 0) {
                                    iMatch += iMatchLastChildren;
                                } else {
                                    diffTreeChangeBuilder2.nodeReplaced(objArr[i3], objArr4[i2]);
                                    i3++;
                                    i2++;
                                }
                            }
                            diffTree4 = this;
                            diffTreeChangeBuilder3 = diffTreeChangeBuilder2;
                            objArr3 = objArr4;
                        }
                        diffTreeChangeBuilder2 = diffTreeChangeBuilder3;
                        diffTree4 = this;
                        diffTreeChangeBuilder3 = diffTreeChangeBuilder2;
                        objArr3 = objArr4;
                    }
                    compareResult = CompareResult.NOT_EQUAL;
                    diffTree = diffTree4;
                }
            } else if (diffTree2.myComparator.typesEqual(oldnode, newnode) && diffTree2.myComparator.hashCodesEqual(oldnode, newnode)) {
                compareResult2 = CompareResult.EQUAL;
            } else {
                diffTreeChangeBuilder3.nodeReplaced(oldnode, newnode);
                compareResult2 = CompareResult.NOT_EQUAL;
            }
            diffTree.myOldTree.disposeChildren(objArr, children);
            diffTree.myNewTree.disposeChildren(objArr3, children2);
            if (compareResult == null) {
                $$$reportNull$$$0(13);
            }
            return compareResult;
        }
        diffTreeChangeBuilder3.nodeReplaced(oldnode, newnode);
        compareResult2 = CompareResult.NOT_EQUAL;
        compareResult = compareResult2;
        objArr = objArr2;
        diffTree = diffTree2;
        diffTree.myOldTree.disposeChildren(objArr, children);
        diffTree.myNewTree.disposeChildren(objArr3, children2);
        if (compareResult == null) {
            $$$reportNull$$$0(13);
        }
        return compareResult;
    }

    public static <OldNode, NewNode> void diff(FlyweightCapableTreeStructure<OldNode> flyweightCapableTreeStructure, FlyweightCapableTreeStructure<NewNode> flyweightCapableTreeStructure2, ShallowNodeComparator<? super OldNode, ? super NewNode> shallowNodeComparator, DiffTreeChangeBuilder<? super OldNode, ? super NewNode> diffTreeChangeBuilder, CharSequence charSequence) {
        if (flyweightCapableTreeStructure == null) {
            $$$reportNull$$$0(4);
        }
        if (flyweightCapableTreeStructure2 == null) {
            $$$reportNull$$$0(5);
        }
        if (shallowNodeComparator == null) {
            $$$reportNull$$$0(6);
        }
        if (diffTreeChangeBuilder == null) {
            $$$reportNull$$$0(7);
        }
        if (charSequence == null) {
            $$$reportNull$$$0(8);
        }
        new DiffTree(flyweightCapableTreeStructure, flyweightCapableTreeStructure2, shallowNodeComparator, charSequence).build(flyweightCapableTreeStructure.getRoot(), flyweightCapableTreeStructure2.getRoot(), 0, diffTreeChangeBuilder);
    }

    private static <OldNode, NewNode> DiffTreeChangeBuilder<OldNode, NewNode> emptyConsumer() {
        DiffTreeChangeBuilder<OldNode, NewNode> diffTreeChangeBuilder = (DiffTreeChangeBuilder<OldNode, NewNode>) EMPTY_CONSUMER;
        if (diffTreeChangeBuilder == null) {
            $$$reportNull$$$0(9);
        }
        return diffTreeChangeBuilder;
    }

    private CompareResult looksEqual(OldNode oldnode, NewNode newnode) {
        if (oldnode == null || newnode == null || !this.myComparator.typesEqual(oldnode, newnode)) {
            CompareResult compareResult = CompareResult.NOT_EQUAL;
            if (compareResult == null) {
                $$$reportNull$$$0(17);
            }
            return compareResult;
        }
        ThreeState threeStateDeepEqual = this.myComparator.deepEqual(oldnode, newnode);
        if (threeStateDeepEqual == ThreeState.YES) {
            CompareResult compareResult2 = CompareResult.EQUAL;
            if (compareResult2 == null) {
                $$$reportNull$$$0(18);
            }
            return compareResult2;
        }
        if (threeStateDeepEqual == ThreeState.UNSURE) {
            CompareResult compareResult3 = CompareResult.DRILL_DOWN_NEEDED;
            if (compareResult3 == null) {
                $$$reportNull$$$0(19);
            }
            return compareResult3;
        }
        CompareResult compareResult4 = CompareResult.TYPE_ONLY;
        if (compareResult4 == null) {
            $$$reportNull$$$0(20);
        }
        return compareResult4;
    }

    private int match(OldNode[] oldnodeArr, int i, NewNode[] newnodeArr, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        while (i6 != i5 * i4) {
            OldNode oldnode = oldnodeArr[i + i6];
            NewNode newnode = newnodeArr[i2 + i6];
            CompareResult compareResultLooksEqual = looksEqual(oldnode, newnode);
            if (compareResultLooksEqual == CompareResult.DRILL_DOWN_NEEDED) {
                compareResultLooksEqual = textMatch(oldnode, newnode) ? build(oldnode, newnode, i3 + 1, emptyConsumer()) : CompareResult.NOT_EQUAL;
            }
            if (compareResultLooksEqual != CompareResult.EQUAL) {
                break;
            }
            i6 += i4;
        }
        return i6 * i4;
    }

    private int matchLastChildren(int i, DiffTreeChangeBuilder<? super OldNode, ? super NewNode> diffTreeChangeBuilder, int i2, OldNode[] oldnodeArr, int i3, int i4, NewNode[] newnodeArr, int i5) {
        int i6;
        OldNode oldnode;
        NewNode newnode;
        CompareResult compareResultLooksEqual;
        if (diffTreeChangeBuilder == null) {
            $$$reportNull$$$0(14);
        }
        int i7 = 0;
        while (true) {
            int i8 = i2 - i7;
            if (i3 >= i8 || i5 >= (i6 = i4 - i7) || (compareResultLooksEqual = looksEqual((oldnode = oldnodeArr[i8 - 1]), (newnode = newnodeArr[i6 - 1]))) == CompareResult.NOT_EQUAL) {
                break;
            }
            if (compareResultLooksEqual == CompareResult.DRILL_DOWN_NEEDED) {
                build(oldnode, newnode, i + 1, diffTreeChangeBuilder);
            } else if (compareResultLooksEqual == CompareResult.TYPE_ONLY) {
                diffTreeChangeBuilder.nodeReplaced(oldnode, newnode);
            }
            i7++;
        }
        return i7;
    }

    private ThreeElementMatchResult matchNext3Children(OldNode[] oldnodeArr, NewNode[] newnodeArr, int i, int i2, int i3, int i4) {
        if (i >= i3) {
            return ThreeElementMatchResult.SKIP_NEW_1;
        }
        if (i2 >= i4) {
            return ThreeElementMatchResult.SKIP_OLD_1;
        }
        OldNode oldnode = oldnodeArr[i];
        NewNode newnode = newnodeArr[i2];
        CompareResult compareResultLooksEqual = looksEqual(oldnode, newnode);
        CompareResult compareResult = CompareResult.EQUAL;
        if (compareResultLooksEqual == compareResult) {
            return ThreeElementMatchResult.FULL_START_MATCH;
        }
        CompareResult compareResult2 = CompareResult.DRILL_DOWN_NEEDED;
        if (compareResultLooksEqual == compareResult2) {
            return ThreeElementMatchResult.DRILL_DOWN_START_MATCH;
        }
        OldNode oldnode2 = i < i3 + (-1) ? oldnodeArr[i + 1] : null;
        CompareResult compareResultLooksEqual2 = looksEqual(oldnode, i2 < i4 + (-1) ? newnodeArr[i2 + 1] : null);
        if (compareResultLooksEqual2 == compareResult || compareResultLooksEqual2 == compareResult2) {
            return ThreeElementMatchResult.SKIP_NEW_1;
        }
        CompareResult compareResultLooksEqual3 = looksEqual(oldnode2, newnode);
        if (compareResultLooksEqual3 == compareResult || compareResultLooksEqual3 == compareResult2) {
            return ThreeElementMatchResult.SKIP_OLD_1;
        }
        CompareResult compareResult3 = CompareResult.TYPE_ONLY;
        if (compareResultLooksEqual == compareResult3) {
            return ThreeElementMatchResult.REPLACE_START;
        }
        if (compareResultLooksEqual2 == compareResult3) {
            return ThreeElementMatchResult.SKIP_NEW_1;
        }
        if (compareResultLooksEqual3 == compareResult3) {
            return ThreeElementMatchResult.SKIP_OLD_1;
        }
        OldNode oldnode3 = i < i3 + (-2) ? oldnodeArr[i + 2] : null;
        CompareResult compareResultLooksEqual4 = looksEqual(oldnode, i2 < i4 + (-2) ? newnodeArr[i2 + 2] : null);
        CompareResult compareResult4 = CompareResult.NOT_EQUAL;
        if (compareResultLooksEqual4 != compareResult4) {
            return ThreeElementMatchResult.SKIP_NEW_2;
        }
        return looksEqual(oldnode3, newnode) != compareResult4 ? ThreeElementMatchResult.SKIP_OLD_2 : ThreeElementMatchResult.NO_MATCH;
    }

    private boolean textMatch(OldNode oldnode, NewNode newnode) {
        if (oldnode == null) {
            $$$reportNull$$$0(15);
        }
        if (newnode == null) {
            $$$reportNull$$$0(16);
        }
        return CharArrayUtil.regionMatches(this.myOldText, this.myOldTree.getStartOffset(oldnode) - this.myOldTreeStart, this.myOldTree.getEndOffset(oldnode) - this.myOldTreeStart, this.myNewText, this.myNewTree.getStartOffset(newnode) - this.myNewTreeStart, this.myNewTree.getEndOffset(newnode) - this.myNewTreeStart);
    }
}
