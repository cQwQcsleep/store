package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CharComparators {
    public static final CharComparator NATURAL_COMPARATOR = new NaturalImplicitComparator();
    public static final CharComparator OPPOSITE_COMPARATOR = new OppositeImplicitComparator();

    public static CharComparator asCharComparator(final Comparator<? super Character> comparator) {
        return (comparator == null || (comparator instanceof CharComparator)) ? (CharComparator) comparator : new CharComparator() { // from class: it.unimi.dsi.fastutil.chars.CharComparators.1
            @Override // it.unimi.dsi.fastutil.chars.CharComparator
            public int compare(char c, char c2) {
                return comparator.compare(Character.valueOf(c), Character.valueOf(c2));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // it.unimi.dsi.fastutil.chars.CharComparator, java.util.Comparator
            public int compare(Character ch, Character ch2) {
                return comparator.compare(ch, ch2);
            }
        };
    }

    public static CharComparator oppositeComparator(CharComparator charComparator) {
        return charComparator instanceof OppositeComparator ? ((OppositeComparator) charComparator).comparator : new OppositeComparator(charComparator);
    }

    public static class NaturalImplicitComparator implements CharComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return CharComparators.NATURAL_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharComparator
        public final int compare(char c, char c2) {
            return Character.compare(c, c2);
        }

        @Override // it.unimi.dsi.fastutil.chars.CharComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Character> reversed2() {
            return CharComparators.OPPOSITE_COMPARATOR;
        }
    }

    public static class OppositeComparator implements CharComparator, Serializable {
        private static final long serialVersionUID = 1;
        final CharComparator comparator;

        public OppositeComparator(CharComparator charComparator) {
            this.comparator = charComparator;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharComparator
        public final int compare(char c, char c2) {
            return this.comparator.compare(c2, c);
        }

        @Override // it.unimi.dsi.fastutil.chars.CharComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public final Comparator<Character> reversed2() {
            return this.comparator;
        }
    }

    public static class OppositeImplicitComparator implements CharComparator, Serializable {
        private static final long serialVersionUID = 1;

        private Object readResolve() {
            return CharComparators.OPPOSITE_COMPARATOR;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharComparator
        public final int compare(char c, char c2) {
            return -Character.compare(c, c2);
        }

        @Override // it.unimi.dsi.fastutil.chars.CharComparator, java.util.Comparator
        /* JADX INFO: renamed from: reversed */
        public Comparator<Character> reversed2() {
            return CharComparators.NATURAL_COMPARATOR;
        }
    }
}
