package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class DoubleComparators {
    public static final DoubleComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final DoubleComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static DoubleComparator asDoubleComparator(final Comparator<? super Double> comparator) {
        return (comparator == null || (comparator instanceof DoubleComparator)) ? (DoubleComparator) comparator : new DoubleComparator() { // from class: it.unimi.dsi.fastutil.doubles.DoubleComparators.1
            @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator
            public int compare(double d, double d2) {
                return comparator.compare(Double.valueOf(d), Double.valueOf(d2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator, java.util.Comparator
            public int compare(Double d, Double d2) {
                return comparator.compare(d, d2);
            }
        };
    }

    public static DoubleComparator oppositeComparator(DoubleComparator doubleComparator) {
        return doubleComparator instanceof OppositeComparator ? ((OppositeComparator) doubleComparator).comparator : new OppositeComparator(doubleComparator);
    }

    public static class NaturalImplicitComparator implements DoubleComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return DoubleComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator
        public final int compare(double d, double d2) {
            return Double.compare(d, d2);
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Double> reversed2() {
            return DoubleComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements DoubleComparator, Serializable {
        private static final long serialVersionUID = 1;
        final DoubleComparator comparator;

        public OppositeComparator(DoubleComparator doubleComparator) {
            this.comparator = doubleComparator;
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator
        public final int compare(double d, double d2) {
            return this.comparator.compare(d2, d);
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Double> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements DoubleComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return DoubleComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator
        public final int compare(double d, double d2) {
            return -Double.compare(d, d2);
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Double> reversed2() {
            return DoubleComparators.NATURAL_COMPARATOR;
        }
    }
}
