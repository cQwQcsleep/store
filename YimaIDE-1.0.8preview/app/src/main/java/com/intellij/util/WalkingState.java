package com.intellij.util;

import androidx.collection.ScatterMapKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class WalkingState<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean isUnitTestMode = false;
    private boolean isDown;
    private final TreeGuide<T> myWalker;
    protected boolean startedWalking;
    private boolean stopped;

    public interface TreeGuide<T> {
        T getFirstChild(T t);

        T getNextSibling(T t);

        T getParent(T t);

        T getPrevSibling(T t);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            switch (i) {
                case 4:
                case 5:
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                    objArr[0] = "root";
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                    objArr[0] = "treeGuide";
                    break;
                case 8:
                    objArr[0] = "processor";
                    break;
                default:
                    objArr[0] = "element";
                    break;
            }
        } else {
            objArr[0] = "delegate";
        }
        objArr[1] = "com/intellij/util/WalkingState";
        switch (i) {
            case 1:
                objArr[2] = "<init>";
                break;
            case 2:
                objArr[2] = "visit";
                break;
            case 3:
                objArr[2] = "elementStarted";
                break;
            case 4:
                objArr[2] = "walkChildren";
                break;
            case 5:
                objArr[2] = "next";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "processAll";
                break;
            default:
                objArr[2] = "elementFinished";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public WalkingState(TreeGuide<T> treeGuide) {
        if (treeGuide == null) {
            $$$reportNull$$$0(1);
        }
        this.myWalker = treeGuide;
    }

    public static <T> boolean processAll(T t, TreeGuide<T> treeGuide, final Processor<? super T> processor) {
        if (t == null) {
            $$$reportNull$$$0(6);
        }
        if (treeGuide == null) {
            $$$reportNull$$$0(7);
        }
        if (processor == null) {
            $$$reportNull$$$0(8);
        }
        final boolean[] zArr = {true};
        new WalkingState<T>(treeGuide) { // from class: com.intellij.util.WalkingState.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/intellij/util/WalkingState$1", "visit"));
            }

            @Override // com.intellij.util.WalkingState
            public void visit(T t2) {
                if (t2 == null) {
                    $$$reportNull$$$0(0);
                }
                if (processor.process(t2)) {
                    super.visit(t2);
                } else {
                    stopWalking();
                    zArr[0] = false;
                }
            }
        }.visit(t);
        return zArr[0];
    }

    public static void setUnitTestMode() {
        isUnitTestMode = true;
    }

    private void walkChildren(T t) {
        if (t == null) {
            $$$reportNull$$$0(4);
        }
        T next = next(t, t, this.isDown);
        while (next != null && !this.stopped) {
            this.isDown = false;
            if (isUnitTestMode) {
                this.myWalker.getParent(next);
                this.myWalker.getNextSibling(next);
                visit(next);
            } else {
                visit(next);
            }
            next = next(next, t, this.isDown);
        }
    }

    public void elementFinished(T t) {
        if (t == null) {
            $$$reportNull$$$0(0);
        }
    }

    public void elementStarted(T t) {
        if (t == null) {
            $$$reportNull$$$0(3);
        }
        this.isDown = true;
        if (this.startedWalking) {
            return;
        }
        this.startedWalking = true;
        try {
            walkChildren(t);
        } finally {
            this.startedWalking = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public T next(T t, T t2, boolean z) {
        T firstChild;
        if (t2 == null) {
            $$$reportNull$$$0(5);
        }
        if (z && (firstChild = this.myWalker.getFirstChild(t)) != null) {
            return firstChild;
        }
        while (t != t2 && t != 0) {
            T t3 = (T) this.myWalker.getNextSibling(t);
            elementFinished(t);
            TreeGuide<T> treeGuide = this.myWalker;
            if (t3 != null) {
                Object prevSibling = treeGuide.getPrevSibling(t3);
                if (prevSibling != t) {
                    StringBuilder sb = new StringBuilder("Element: ");
                    sb.append(t);
                    sb.append("; next: ");
                    sb.append(t3);
                    sb.append("; next.prev: ");
                    sb.append(prevSibling);
                    do {
                        t = this.myWalker.getParent(t);
                        if (t == 0) {
                            break;
                        }
                    } while (t != t2);
                }
                return t3;
            }
            t = (T) treeGuide.getParent(t);
        }
        if (t == 0) {
            return null;
        }
        elementFinished(t);
        return null;
    }

    public void startedWalking() {
        this.startedWalking = true;
    }

    public void stopWalking() {
        this.stopped = true;
    }

    public void visit(T t) {
        if (t == null) {
            $$$reportNull$$$0(2);
        }
        elementStarted(t);
    }
}
