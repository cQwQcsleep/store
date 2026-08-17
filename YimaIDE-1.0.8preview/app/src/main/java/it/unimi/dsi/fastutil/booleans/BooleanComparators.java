package it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class BooleanComparators {
    public static final BooleanComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final BooleanComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static BooleanComparator asBooleanComparator(final Comparator<? super Boolean> comparator) {
        return (comparator == null || (comparator instanceof BooleanComparator)) ? (BooleanComparator) comparator : new BooleanComparator() { // from class: it.unimi.dsi.fastutil.booleans.BooleanComparators.1
            @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator
            public int compare(boolean z, boolean z2) {
                return comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator, java.util.Comparator
            public int compare(Boolean bool, Boolean bool2) {
                return comparator.compare(bool, bool2);
            }
        };
    }

    public static BooleanComparator oppositeComparator(BooleanComparator booleanComparator) {
        return booleanComparator instanceof OppositeComparator ? ((OppositeComparator) booleanComparator).comparator : new OppositeComparator(booleanComparator);
    }

    public static class NaturalImplicitComparator implements BooleanComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return BooleanComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator
        public final int compare(boolean z, boolean z2) {
            return Boolean.compare(z, z2);
        }

        @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Boolean> reversed2() {
            return BooleanComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements BooleanComparator, Serializable {
        private static final long serialVersionUID = 1;
        final BooleanComparator comparator;

        public OppositeComparator(BooleanComparator booleanComparator) {
            this.comparator = booleanComparator;
        }

        @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator
        public final int compare(boolean z, boolean z2) {
            return this.comparator.compare(z2, z);
        }

        @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Boolean> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements BooleanComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return BooleanComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator
        public final int compare(boolean z, boolean z2) {
            return -Boolean.compare(z, z2);
        }

        @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Boolean> reversed2() {
            return BooleanComparators.NATURAL_COMPARATOR;
        }
    }
}
