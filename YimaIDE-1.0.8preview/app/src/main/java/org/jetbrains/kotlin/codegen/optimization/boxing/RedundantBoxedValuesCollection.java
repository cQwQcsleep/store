package org.jetbrains.kotlin.codegen.optimization.boxing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class RedundantBoxedValuesCollection implements Iterable<BoxedValueDescriptor> {
    private final Set<BoxedValueDescriptor> safeToDeleteValues = new HashSet();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 4 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 4 ? 3 : 2];
        if (i == 2) {
            objArr[0] = "v";
        } else if (i == 3) {
            objArr[0] = "w";
        } else if (i != 4) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "org/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection";
        }
        if (i != 4) {
            objArr[1] = "org/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection";
        } else {
            objArr[1] = "iterator";
        }
        if (i == 1) {
            objArr[2] = "remove";
        } else if (i == 2 || i == 3) {
            objArr[2] = "merge";
        } else if (i != 4) {
            objArr[2] = "add";
        }
        String str2 = String.format(str, objArr);
        if (i == 4) {
            throw new IllegalStateException(str2);
        }
    }

    public void add(BoxedValueDescriptor boxedValueDescriptor) {
        if (boxedValueDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        this.safeToDeleteValues.add(boxedValueDescriptor);
    }

    public boolean isEmpty() {
        return this.safeToDeleteValues.isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<BoxedValueDescriptor> iterator() {
        Iterator<BoxedValueDescriptor> it = this.safeToDeleteValues.iterator();
        if (it == null) {
            $$$reportNull$$$0(4);
        }
        return it;
    }

    public void merge(BoxedValueDescriptor boxedValueDescriptor, BoxedValueDescriptor boxedValueDescriptor2) {
        if (boxedValueDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        if (boxedValueDescriptor2 == null) {
            $$$reportNull$$$0(3);
        }
        boxedValueDescriptor.addMergedWith(boxedValueDescriptor2);
        boxedValueDescriptor2.addMergedWith(boxedValueDescriptor);
        if (boxedValueDescriptor.getIsSafeToRemove() && !boxedValueDescriptor2.getIsSafeToRemove()) {
            remove(boxedValueDescriptor);
        }
        if (boxedValueDescriptor.getIsSafeToRemove() || !boxedValueDescriptor2.getIsSafeToRemove()) {
            return;
        }
        remove(boxedValueDescriptor2);
    }

    public void remove(BoxedValueDescriptor boxedValueDescriptor) {
        if (boxedValueDescriptor == null) {
            $$$reportNull$$$0(1);
        }
        if (this.safeToDeleteValues.contains(boxedValueDescriptor)) {
            this.safeToDeleteValues.remove(boxedValueDescriptor);
            boxedValueDescriptor.markAsUnsafeToRemove();
            Iterator<BoxedValueDescriptor> it = boxedValueDescriptor.getMergedWith().iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
        }
    }
}
