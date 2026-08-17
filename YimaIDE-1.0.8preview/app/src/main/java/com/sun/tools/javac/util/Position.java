package com.sun.tools.javac.util;

import java.util.BitSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Position {
    public static final int FIRSTCOLUMN = 1;
    public static final int FIRSTLINE = 1;
    public static final int FIRSTPOS = 0;
    public static final int LINESHIFT = 10;
    public static final int MAXCOLUMN = 1023;
    public static final int MAXLINE = 4194303;
    public static final int MAXPOS = Integer.MAX_VALUE;
    public static final int NOPOS = -1;

    public interface LineMap extends com.sun.source.tree.LineMap {
        int getColumnNumber(int i);

        int getLineNumber(int i);

        int getPosition(int i, int i2);

        int getStartPosition(int i);
    }

    private Position() {
    }

    public static int encodePosition(int i, int i2) {
        if (i < 1) {
            w01.a("line must be greater than 0");
            return 0;
        }
        if (i2 < 1) {
            w01.a("column must be greater than 0");
            return 0;
        }
        if (i > 4194303 || i2 > 1023) {
            return -1;
        }
        return (i << 10) + i2;
    }

    public static LineMap makeLineMap(char[] cArr, int i, boolean z) {
        LineMapImpl lineTabMapImpl = z ? new LineTabMapImpl(i) : new LineMapImpl();
        lineTabMapImpl.build(cArr, i);
        return lineTabMapImpl;
    }

    public static class LineTabMapImpl extends LineMapImpl {
        private BitSet tabMap;

        public LineTabMapImpl(int i) {
            this.tabMap = new BitSet(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public int getColumnNumber(int i) {
            int iTabulate = 0;
            for (int i2 = this.startPosition[getLineNumber(i) - 1]; i2 < i; i2++) {
                iTabulate = this.tabMap.get(i2) ? LayoutCharacters.tabulate(iTabulate) : iTabulate + 1;
            }
            return iTabulate + 1;
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public /* bridge */ /* synthetic */ int getLineNumber(int i) {
            return super.getLineNumber(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public int getPosition(int i, int i2) {
            int i3 = this.startPosition[i - 1];
            int i4 = i2 - 1;
            int iTabulate = 0;
            while (iTabulate < i4) {
                i3++;
                iTabulate = this.tabMap.get(i3) ? LayoutCharacters.tabulate(iTabulate) : iTabulate + 1;
            }
            return i3;
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public /* bridge */ /* synthetic */ int getStartPosition(int i) {
            return super.getStartPosition(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl
        public void setTabPosition(int i) {
            this.tabMap.set(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getLineNumber(long j) {
            return super.getLineNumber(j);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getStartPosition(long j) {
            return super.getStartPosition(j);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getPosition(long j, long j2) {
            return super.getPosition(j, j2);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getColumnNumber(long j) {
            return super.getColumnNumber(j);
        }
    }

    public static class LineMapImpl implements LineMap {
        protected int[] startPosition;
        private int lastPosition = 0;
        private int lastLine = 1;

        private static int longToInt(long j) {
            int i = (int) j;
            if (i == j) {
                return i;
            }
            qc6.a();
            return 0;
        }

        public void build(char[] cArr, int i) {
            int i2;
            int[] iArr = new int[i];
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                int i5 = i4 + 1;
                iArr[i4] = i3;
                do {
                    char c = cArr[i3];
                    if (c == '\r' || c == '\n') {
                        if (c != '\r' || (i2 = i3 + 1) >= i || cArr[i2] != '\n') {
                            i3++;
                            break;
                        } else {
                            i3 += 2;
                            break;
                        }
                    }
                    if (c == '\t') {
                        setTabPosition(i3);
                    }
                    i3++;
                } while (i3 < i);
                i4 = i5;
            }
            int[] iArr2 = new int[i4];
            this.startPosition = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, i4);
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getColumnNumber(int i) {
            return (i - this.startPosition[getLineNumber(i) - 1]) + 1;
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getLineNumber(int i) {
            if (i == this.lastPosition) {
                return this.lastLine;
            }
            this.lastPosition = i;
            int length = this.startPosition.length - 1;
            int i2 = 0;
            while (i2 <= length) {
                int i3 = (i2 + length) >> 1;
                int i4 = this.startPosition[i3];
                if (i4 < i) {
                    i2 = i3 + 1;
                } else {
                    if (i4 <= i) {
                        int i5 = i3 + 1;
                        this.lastLine = i5;
                        return i5;
                    }
                    length = i3 - 1;
                }
            }
            this.lastLine = i2;
            return i2;
        }

        @Override // com.sun.source.tree.LineMap
        public long getPosition(long j, long j2) {
            return getPosition(longToInt(j), longToInt(j2));
        }

        @Override // com.sun.source.tree.LineMap
        public long getStartPosition(long j) {
            return getStartPosition(longToInt(j));
        }

        public void setTabPosition(int i) {
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getStartPosition(int i) {
            return this.startPosition[i - 1];
        }

        @Override // com.sun.source.tree.LineMap
        public long getColumnNumber(long j) {
            return getColumnNumber(longToInt(j));
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getPosition(int i, int i2) {
            return (this.startPosition[i - 1] + i2) - 1;
        }

        @Override // com.sun.source.tree.LineMap
        public long getLineNumber(long j) {
            return getLineNumber(longToInt(j));
        }
    }
}
