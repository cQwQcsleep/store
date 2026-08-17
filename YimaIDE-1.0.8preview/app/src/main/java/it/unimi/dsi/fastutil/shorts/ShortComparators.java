package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ShortComparators {
    public static final ShortComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final ShortComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static ShortComparator asShortComparator(final Comparator<? super Short> comparator) {
        return (comparator == null || (comparator instanceof ShortComparator)) ? (ShortComparator) comparator : new ShortComparator() { // from class: it.unimi.dsi.fastutil.shorts.ShortComparators.1
            @Override // it.unimi.dsi.fastutil.shorts.ShortComparator
            public int compare(short s, short s2) {
                return comparator.compare(Short.valueOf(s), Short.valueOf(s2));
            }

            @Override // it.unimi.dsi.fastutil.shorts.ShortComparator, java.util.Comparator
            public int compare(Short sh, Short sh2) {
                return comparator.compare(sh, sh2);
            }
        };
    }

    public static ShortComparator oppositeComparator(ShortComparator shortComparator) {
        return shortComparator instanceof OppositeComparator ? ((OppositeComparator) shortComparator).comparator : new OppositeComparator(shortComparator);
    }

    public static class NaturalImplicitComparator implements ShortComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return ShortComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortComparator
        public final int compare(short s, short s2) {
            return Short.compare(s, s2);
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Short> reversed2() {
            return ShortComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements ShortComparator, Serializable {
        private static final long serialVersionUID = 1;
        final ShortComparator comparator;

        public OppositeComparator(ShortComparator shortComparator) {
            this.comparator = shortComparator;
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortComparator
        public final int compare(short s, short s2) {
            return this.comparator.compare(s2, s);
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Short> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements ShortComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return ShortComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortComparator
        public final int compare(short s, short s2) {
            return -Short.compare(s, s2);
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Short> reversed2() {
            return ShortComparators.NATURAL_COMPARATOR;
        }
    }
}
