package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class IntComparators {
    public static final IntComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final IntComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static IntComparator asIntComparator(final Comparator<? super Integer> comparator) {
        return (comparator == null || (comparator instanceof IntComparator)) ? (IntComparator) comparator : new IntComparator() { // from class: it.unimi.dsi.fastutil.ints.IntComparators.1
            @Override // it.unimi.dsi.fastutil.ints.IntComparator
            public int compare(int i, int i2) {
                return comparator.compare(Integer.valueOf(i), Integer.valueOf(i2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.ints.IntComparator, java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return comparator.compare(num, num2);
            }
        };
    }

    public static IntComparator oppositeComparator(IntComparator intComparator) {
        return intComparator instanceof OppositeComparator ? ((OppositeComparator) intComparator).comparator : new OppositeComparator(intComparator);
    }

    public static class NaturalImplicitComparator implements IntComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return IntComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntComparator
        public final int compare(int i, int i2) {
            return Integer.compare(i, i2);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Integer> reversed2() {
            return IntComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements IntComparator, Serializable {
        private static final long serialVersionUID = 1;
        final IntComparator comparator;

        public OppositeComparator(IntComparator intComparator) {
            this.comparator = intComparator;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntComparator
        public final int compare(int i, int i2) {
            return this.comparator.compare(i2, i);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Integer> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements IntComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return IntComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntComparator
        public final int compare(int i, int i2) {
            return -Integer.compare(i, i2);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Integer> reversed2() {
            return IntComparators.NATURAL_COMPARATOR;
        }
    }
}
