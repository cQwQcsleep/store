package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class LongComparators {
    public static final LongComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final LongComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static LongComparator asLongComparator(final Comparator<? super Long> comparator) {
        return (comparator == null || (comparator instanceof LongComparator)) ? (LongComparator) comparator : new LongComparator() { // from class: it.unimi.dsi.fastutil.longs.LongComparators.1
            @Override // it.unimi.dsi.fastutil.longs.LongComparator
            public int compare(long j, long j2) {
                return comparator.compare(Long.valueOf(j), Long.valueOf(j2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.longs.LongComparator, java.util.Comparator
            public int compare(Long l, Long l2) {
                return comparator.compare(l, l2);
            }
        };
    }

    public static LongComparator oppositeComparator(LongComparator longComparator) {
        return longComparator instanceof OppositeComparator ? ((OppositeComparator) longComparator).comparator : new OppositeComparator(longComparator);
    }

    public static class NaturalImplicitComparator implements LongComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return LongComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongComparator
        public final int compare(long j, long j2) {
            return Long.compare(j, j2);
        }

        @Override // it.unimi.dsi.fastutil.longs.LongComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Long> reversed2() {
            return LongComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements LongComparator, Serializable {
        private static final long serialVersionUID = 1;
        final LongComparator comparator;

        public OppositeComparator(LongComparator longComparator) {
            this.comparator = longComparator;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongComparator
        public final int compare(long j, long j2) {
            return this.comparator.compare(j2, j);
        }

        @Override // it.unimi.dsi.fastutil.longs.LongComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Long> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements LongComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return LongComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongComparator
        public final int compare(long j, long j2) {
            return -Long.compare(j, j2);
        }

        @Override // it.unimi.dsi.fastutil.longs.LongComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Long> reversed2() {
            return LongComparators.NATURAL_COMPARATOR;
        }
    }
}
