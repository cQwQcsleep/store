package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class FloatComparators {
    public static final FloatComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final FloatComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static FloatComparator asFloatComparator(final Comparator<? super Float> comparator) {
        return (comparator == null || (comparator instanceof FloatComparator)) ? (FloatComparator) comparator : new FloatComparator() { // from class: it.unimi.dsi.fastutil.floats.FloatComparators.1
            @Override // it.unimi.dsi.fastutil.floats.FloatComparator
            public int compare(float f, float f2) {
                return comparator.compare(Float.valueOf(f), Float.valueOf(f2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.floats.FloatComparator, java.util.Comparator
            public int compare(Float f, Float f2) {
                return comparator.compare(f, f2);
            }
        };
    }

    public static FloatComparator oppositeComparator(FloatComparator floatComparator) {
        return floatComparator instanceof OppositeComparator ? ((OppositeComparator) floatComparator).comparator : new OppositeComparator(floatComparator);
    }

    public static class NaturalImplicitComparator implements FloatComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return FloatComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.floats.FloatComparator
        public final int compare(float f, float f2) {
            return Float.compare(f, f2);
        }

        @Override // it.unimi.dsi.fastutil.floats.FloatComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Float> reversed2() {
            return FloatComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements FloatComparator, Serializable {
        private static final long serialVersionUID = 1;
        final FloatComparator comparator;

        public OppositeComparator(FloatComparator floatComparator) {
            this.comparator = floatComparator;
        }

        @Override // it.unimi.dsi.fastutil.floats.FloatComparator
        public final int compare(float f, float f2) {
            return this.comparator.compare(f2, f);
        }

        @Override // it.unimi.dsi.fastutil.floats.FloatComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Float> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements FloatComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return FloatComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.floats.FloatComparator
        public final int compare(float f, float f2) {
            return -Float.compare(f, f2);
        }

        @Override // it.unimi.dsi.fastutil.floats.FloatComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Float> reversed2() {
            return FloatComparators.NATURAL_COMPARATOR;
        }
    }
}
