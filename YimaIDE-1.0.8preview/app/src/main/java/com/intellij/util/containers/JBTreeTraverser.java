package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.util.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class JBTreeTraverser<T> extends FilteredTraverserBase<T, JBTreeTraverser<T>> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 7) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "meta";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "function";
                break;
            case 4:
                objArr[0] = "reverse";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "com/intellij/util/containers/JBTreeTraverser";
                break;
            default:
                objArr[0] = "treeStructure";
                break;
        }
        if (i == 5 || i == 7) {
            objArr[1] = "map";
        } else {
            objArr[1] = "com/intellij/util/containers/JBTreeTraverser";
        }
        switch (i) {
            case 1:
                objArr[2] = "of";
                break;
            case 2:
                objArr[2] = "newInstance";
                break;
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "map";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                break;
            default:
                objArr[2] = "from";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public JBTreeTraverser(Function<? super T, ? extends Iterable<? extends T>> function) {
        super(FilteredTraverserBase.Meta.create(function));
    }

    public static <T> JBTreeTraverser<T> from(Function<? super T, ? extends Iterable<? extends T>> function) {
        if (function == null) {
            $$$reportNull$$$0(0);
        }
        return new JBTreeTraverser<>(function);
    }

    @Override // com.intellij.util.containers.FilteredTraverserBase
    public JBTreeTraverser<T> newInstance(FilteredTraverserBase.Meta<T> meta) {
        if (meta == null) {
            $$$reportNull$$$0(2);
        }
        return meta == this.myMeta ? this : new JBTreeTraverser<>(meta);
    }

    public JBTreeTraverser(FilteredTraverserBase.Meta<T> meta) {
        super(meta);
    }
}
