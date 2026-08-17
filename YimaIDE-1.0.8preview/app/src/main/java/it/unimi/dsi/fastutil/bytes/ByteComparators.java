package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ByteComparators {
    public static final ByteComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final ByteComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static ByteComparator asByteComparator(final Comparator<? super Byte> comparator) {
        return (comparator == null || (comparator instanceof ByteComparator)) ? (ByteComparator) comparator : new ByteComparator() { // from class: it.unimi.dsi.fastutil.bytes.ByteComparators.1
            @Override // it.unimi.dsi.fastutil.bytes.ByteComparator
            public int compare(byte b, byte b2) {
                return comparator.compare(Byte.valueOf(b), Byte.valueOf(b2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.bytes.ByteComparator, java.util.Comparator
            public int compare(Byte b, Byte b2) {
                return comparator.compare(b, b2);
            }
        };
    }

    public static ByteComparator oppositeComparator(ByteComparator byteComparator) {
        return byteComparator instanceof OppositeComparator ? ((OppositeComparator) byteComparator).comparator : new OppositeComparator(byteComparator);
    }

    public static class NaturalImplicitComparator implements ByteComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return ByteComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteComparator
        public final int compare(byte b, byte b2) {
            return Byte.compare(b, b2);
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Byte> reversed2() {
            return ByteComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements ByteComparator, Serializable {
        private static final long serialVersionUID = 1;
        final ByteComparator comparator;

        public OppositeComparator(ByteComparator byteComparator) {
            this.comparator = byteComparator;
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteComparator
        public final int compare(byte b, byte b2) {
            return this.comparator.compare(b2, b);
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Byte> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements ByteComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return ByteComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteComparator
        public final int compare(byte b, byte b2) {
            return -Byte.compare(b, b2);
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Byte> reversed2() {
            return ByteComparators.NATURAL_COMPARATOR;
        }
    }
}
