package org.eclipse.jdt.internal.compiler.parser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.internal.compiler.classfmt.ExternalAnnotationProvider;
import org.eclipse.jdt.internal.compiler.lookup.ExtraCompilerModifiers;
import org.eclipse.jdt.internal.compiler.lookup.TagBits;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ScannerHelper {
    public static final long[] Bits = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, TagBits.AreMethodsComplete, TagBits.HasNoMemberTypes, TagBits.HierarchyHasProblems, TagBits.TypeVariablesAreConnected, TagBits.PauseHierarchyCheck, TagBits.HasNullTypeAnnotation, TagBits.HasTypeAnnotations, TagBits.PassedBoundCheck, 8388608, TagBits.HasUnresolvedTypeVariables, TagBits.HasUnresolvedSuperclass, TagBits.HasUnresolvedSuperinterfaces, TagBits.HasUnresolvedEnclosingType, TagBits.HasUnresolvedMemberTypes, TagBits.HasTypeVariable, 1073741824, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, 1152921504606846976L, 2305843009213693952L, 4611686018427387904L, Long.MIN_VALUE};
    public static final int C_DIGIT = 4;
    public static final int C_IDENT_PART = 8;
    public static final int C_IDENT_START = 64;
    public static final int C_JLS_SPACE = 256;
    public static final int C_LOWER_LETTER = 16;
    public static final int C_SEPARATOR = 2;
    public static final int C_SPACE = 1;
    public static final int C_SPECIAL = 128;
    public static final int C_UPPER_LETTER = 32;
    public static final int MAX_OBVIOUS = 128;
    public static final int[] OBVIOUS_IDENT_CHAR_NATURES;
    private static final int PART_INDEX = 1;
    private static final int START_INDEX = 0;
    private static long[][][] Tables;
    private static long[][][] Tables11;
    private static long[][][] Tables12;
    private static long[][][] Tables13;
    private static long[][][] Tables15;
    private static long[][][] Tables7;
    private static long[][][] Tables8;
    private static long[][][] Tables9;

    static {
        int[] iArr = new int[128];
        OBVIOUS_IDENT_CHAR_NATURES = iArr;
        iArr[0] = 8;
        iArr[1] = 8;
        iArr[2] = 8;
        iArr[3] = 8;
        iArr[4] = 8;
        iArr[5] = 8;
        iArr[6] = 8;
        iArr[7] = 8;
        iArr[8] = 8;
        iArr[14] = 8;
        iArr[15] = 8;
        iArr[16] = 8;
        iArr[17] = 8;
        iArr[18] = 8;
        iArr[19] = 8;
        iArr[20] = 8;
        iArr[21] = 8;
        iArr[22] = 8;
        iArr[23] = 8;
        iArr[24] = 8;
        iArr[25] = 8;
        iArr[26] = 8;
        iArr[27] = 8;
        iArr[127] = 8;
        for (int i = 48; i <= 57; i++) {
            OBVIOUS_IDENT_CHAR_NATURES[i] = 12;
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            OBVIOUS_IDENT_CHAR_NATURES[i2] = 88;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            OBVIOUS_IDENT_CHAR_NATURES[i3] = 104;
        }
        int[] iArr2 = OBVIOUS_IDENT_CHAR_NATURES;
        iArr2[95] = 200;
        iArr2[36] = 200;
        iArr2[9] = 257;
        iArr2[10] = 257;
        iArr2[11] = 1;
        iArr2[12] = 257;
        iArr2[13] = 257;
        iArr2[28] = 1;
        iArr2[29] = 1;
        iArr2[30] = 1;
        iArr2[31] = 1;
        iArr2[32] = 257;
        iArr2[46] = 2;
        iArr2[58] = 2;
        iArr2[59] = 2;
        iArr2[44] = 2;
        iArr2[91] = 2;
        iArr2[93] = 2;
        iArr2[40] = 2;
        iArr2[41] = 2;
        iArr2[123] = 2;
        iArr2[125] = 2;
        iArr2[43] = 2;
        iArr2[45] = 2;
        iArr2[42] = 2;
        iArr2[47] = 2;
        iArr2[61] = 2;
        iArr2[38] = 2;
        iArr2[124] = 2;
        iArr2[63] = 2;
        iArr2[60] = 2;
        iArr2[62] = 2;
        iArr2[33] = 2;
        iArr2[37] = 2;
        iArr2[94] = 2;
        iArr2[126] = 2;
        iArr2[34] = 2;
        iArr2[39] = 2;
    }

    public static int digit(char c, int i) {
        if (c < 128) {
            if (i == 8) {
                if (c < '0' || c > '7') {
                    return -1;
                }
                return c - ExternalAnnotationProvider.NULLABLE;
            }
            if (i == 10) {
                if (c < '0' || c > '9') {
                    return -1;
                }
                return c - ExternalAnnotationProvider.NULLABLE;
            }
            if (i == 16) {
                if (c >= '0' && c <= '9') {
                    return c - ExternalAnnotationProvider.NULLABLE;
                }
                if (c >= 'A' && c <= 'F') {
                    return c - '7';
                }
                if (c < 'a' || c > 'f') {
                    return -1;
                }
                return c - 'W';
            }
        }
        return Character.digit(c, i);
    }

    public static int getHexadecimalValue(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                switch (c) {
                    case 'A':
                        return 10;
                    case 'B':
                        return 11;
                    case 'C':
                        return 12;
                    case 'D':
                        return 13;
                    case 'E':
                        return 14;
                    case 'F':
                        return 15;
                    default:
                        switch (c) {
                            case TerminalTokens.TokenNameDIVIDE_EQUAL /* 97 */:
                                return 10;
                            case TerminalTokens.TokenNameAND_EQUAL /* 98 */:
                                return 11;
                            case 'c':
                                return 12;
                            case 'd':
                                return 13;
                            case TerminalTokens.TokenNameREMAINDER_EQUAL /* 101 */:
                                return 14;
                            case TerminalTokens.TokenNameLEFT_SHIFT_EQUAL /* 102 */:
                                return 15;
                            default:
                                return -1;
                        }
                }
        }
    }

    public static int getNumericValue(char c) {
        if (c < 128) {
            int i = OBVIOUS_IDENT_CHAR_NATURES[c];
            if (i == 4) {
                return c - '0';
            }
            if (i == 16) {
                return c - 'W';
            }
            if (i == 32) {
                return c - '7';
            }
        }
        return Character.getNumericValue(c);
    }

    public static void initializeTable() {
        Tables = initializeTables("unicode");
    }

    public static void initializeTable17() {
        Tables7 = initializeTables("unicode6");
    }

    public static void initializeTable18() {
        Tables8 = initializeTables("unicode6_2");
    }

    public static void initializeTable19() {
        Tables9 = initializeTables("unicode8");
    }

    public static void initializeTableJava11() {
        Tables11 = initializeTables("unicode10");
    }

    public static void initializeTableJava12() {
        Tables12 = initializeTables("unicode11");
    }

    public static void initializeTableJava13() {
        Tables13 = initializeTables("unicode12_1");
    }

    public static void initializeTableJava15() {
        Tables15 = initializeTables13andPlus("unicode13");
    }

    public static long[][][] initializeTables(String str) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        long[][][] jArr = {new long[3][], new long[4][]};
        Throwable th7 = null;
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start0.rsc")));
            try {
                long[] jArr2 = new long[1024];
                for (int i = 0; i < 1024; i++) {
                    jArr2[i] = dataInputStream.readLong();
                }
                jArr[0][0] = jArr2;
                dataInputStream.close();
                try {
                    DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start1.rsc")));
                    try {
                        long[] jArr3 = new long[1024];
                        for (int i2 = 0; i2 < 1024; i2++) {
                            jArr3[i2] = dataInputStream2.readLong();
                        }
                        jArr[0][1] = jArr3;
                        dataInputStream2.close();
                        try {
                            DataInputStream dataInputStream3 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start2.rsc")));
                            try {
                                long[] jArr4 = new long[1024];
                                for (int i3 = 0; i3 < 1024; i3++) {
                                    jArr4[i3] = dataInputStream3.readLong();
                                }
                                jArr[0][2] = jArr4;
                                dataInputStream3.close();
                                try {
                                    DataInputStream dataInputStream4 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part0.rsc")));
                                    try {
                                        long[] jArr5 = new long[1024];
                                        for (int i4 = 0; i4 < 1024; i4++) {
                                            jArr5[i4] = dataInputStream4.readLong();
                                        }
                                        jArr[1][0] = jArr5;
                                        dataInputStream4.close();
                                        try {
                                            DataInputStream dataInputStream5 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part1.rsc")));
                                            try {
                                                long[] jArr6 = new long[1024];
                                                for (int i5 = 0; i5 < 1024; i5++) {
                                                    jArr6[i5] = dataInputStream5.readLong();
                                                }
                                                jArr[1][1] = jArr6;
                                                dataInputStream5.close();
                                                try {
                                                    DataInputStream dataInputStream6 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part2.rsc")));
                                                    try {
                                                        long[] jArr7 = new long[1024];
                                                        for (int i6 = 0; i6 < 1024; i6++) {
                                                            jArr7[i6] = dataInputStream6.readLong();
                                                        }
                                                        jArr[1][2] = jArr7;
                                                        dataInputStream6.close();
                                                        try {
                                                            DataInputStream dataInputStream7 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part14.rsc")));
                                                            try {
                                                                long[] jArr8 = new long[1024];
                                                                for (int i7 = 0; i7 < 1024; i7++) {
                                                                    jArr8[i7] = dataInputStream7.readLong();
                                                                }
                                                                jArr[1][3] = jArr8;
                                                                return jArr;
                                                            } finally {
                                                                dataInputStream7.close();
                                                            }
                                                        } catch (Throwable th8) {
                                                            if (0 == 0) {
                                                                throw th8;
                                                            }
                                                            if (null != th8) {
                                                                try {
                                                                    th7.addSuppressed(th8);
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                            throw null;
                                                        }
                                                    } catch (Throwable th9) {
                                                        try {
                                                            dataInputStream6.close();
                                                            throw th9;
                                                        } catch (Throwable th10) {
                                                            th6 = th9;
                                                            th = th10;
                                                            if (th6 == null) {
                                                                throw th;
                                                            }
                                                            if (th6 != th) {
                                                                try {
                                                                    th6.addSuppressed(th);
                                                                } catch (IOException e2) {
                                                                    e2.printStackTrace();
                                                                }
                                                            }
                                                            throw th6;
                                                        }
                                                    }
                                                } catch (Throwable th11) {
                                                    th = th11;
                                                    th6 = null;
                                                }
                                            } catch (Throwable th12) {
                                                th5 = th12;
                                                try {
                                                    dataInputStream5.close();
                                                    throw th5;
                                                } catch (Throwable th13) {
                                                    th = th13;
                                                    if (th5 == null) {
                                                        throw th;
                                                    }
                                                    if (th5 != th) {
                                                        try {
                                                            th5.addSuppressed(th);
                                                        } catch (IOException e3) {
                                                            e3.printStackTrace();
                                                        }
                                                    }
                                                    throw th5;
                                                }
                                            }
                                        } catch (Throwable th14) {
                                            th = th14;
                                            th5 = null;
                                        }
                                    } catch (Throwable th15) {
                                        th4 = th15;
                                        try {
                                            dataInputStream4.close();
                                            throw th4;
                                        } catch (Throwable th16) {
                                            th = th16;
                                            if (th4 == null) {
                                                throw th;
                                            }
                                            if (th4 != th) {
                                                try {
                                                    th4.addSuppressed(th);
                                                } catch (IOException e4) {
                                                    e4.printStackTrace();
                                                }
                                            }
                                            throw th4;
                                        }
                                    }
                                } catch (Throwable th17) {
                                    th = th17;
                                    th4 = null;
                                }
                            } catch (Throwable th18) {
                                th3 = th18;
                                try {
                                    dataInputStream3.close();
                                    throw th3;
                                } catch (Throwable th19) {
                                    th = th19;
                                    if (th3 == null) {
                                        throw th;
                                    }
                                    if (th3 != th) {
                                        try {
                                            th3.addSuppressed(th);
                                        } catch (IOException e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    throw th3;
                                }
                            }
                        } catch (Throwable th20) {
                            th = th20;
                            th3 = null;
                        }
                    } catch (Throwable th21) {
                        th2 = th21;
                        try {
                            dataInputStream2.close();
                            throw th2;
                        } catch (Throwable th22) {
                            th = th22;
                            if (th2 == null) {
                                throw th;
                            }
                            if (th2 != th) {
                                try {
                                    th2.addSuppressed(th);
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th23) {
                    th = th23;
                    th2 = null;
                }
            } catch (Throwable th24) {
                th = th24;
                try {
                    dataInputStream.close();
                    throw th;
                } catch (Throwable th25) {
                    th = th25;
                    if (th == null) {
                        throw th;
                    }
                    if (th != th) {
                        try {
                            th.addSuppressed(th);
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th26) {
            th = th26;
            th = null;
        }
    }

    public static long[][][] initializeTables13andPlus(String str) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        long[][][] jArr = {new long[4][], new long[5][]};
        Throwable th9 = null;
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start0.rsc")));
            try {
                long[] jArr2 = new long[1024];
                for (int i = 0; i < 1024; i++) {
                    jArr2[i] = dataInputStream.readLong();
                }
                jArr[0][0] = jArr2;
                dataInputStream.close();
                try {
                    DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start1.rsc")));
                    try {
                        long[] jArr3 = new long[1024];
                        for (int i2 = 0; i2 < 1024; i2++) {
                            jArr3[i2] = dataInputStream2.readLong();
                        }
                        jArr[0][1] = jArr3;
                        dataInputStream2.close();
                        try {
                            DataInputStream dataInputStream3 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start2.rsc")));
                            try {
                                long[] jArr4 = new long[1024];
                                for (int i3 = 0; i3 < 1024; i3++) {
                                    jArr4[i3] = dataInputStream3.readLong();
                                }
                                jArr[0][2] = jArr4;
                                dataInputStream3.close();
                                try {
                                    DataInputStream dataInputStream4 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/start3.rsc")));
                                    try {
                                        long[] jArr5 = new long[1024];
                                        for (int i4 = 0; i4 < 1024; i4++) {
                                            jArr5[i4] = dataInputStream4.readLong();
                                        }
                                        jArr[0][3] = jArr5;
                                        dataInputStream4.close();
                                        try {
                                            DataInputStream dataInputStream5 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part0.rsc")));
                                            try {
                                                long[] jArr6 = new long[1024];
                                                for (int i5 = 0; i5 < 1024; i5++) {
                                                    jArr6[i5] = dataInputStream5.readLong();
                                                }
                                                jArr[1][0] = jArr6;
                                                dataInputStream5.close();
                                                try {
                                                    DataInputStream dataInputStream6 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part1.rsc")));
                                                    try {
                                                        long[] jArr7 = new long[1024];
                                                        for (int i6 = 0; i6 < 1024; i6++) {
                                                            jArr7[i6] = dataInputStream6.readLong();
                                                        }
                                                        jArr[1][1] = jArr7;
                                                        dataInputStream6.close();
                                                        try {
                                                            DataInputStream dataInputStream7 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part2.rsc")));
                                                            try {
                                                                long[] jArr8 = new long[1024];
                                                                for (int i7 = 0; i7 < 1024; i7++) {
                                                                    jArr8[i7] = dataInputStream7.readLong();
                                                                }
                                                                jArr[1][2] = jArr8;
                                                                dataInputStream7.close();
                                                                try {
                                                                    DataInputStream dataInputStream8 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part3.rsc")));
                                                                    try {
                                                                        long[] jArr9 = new long[1024];
                                                                        for (int i8 = 0; i8 < 1024; i8++) {
                                                                            jArr9[i8] = dataInputStream8.readLong();
                                                                        }
                                                                        jArr[1][3] = jArr9;
                                                                        dataInputStream8.close();
                                                                        try {
                                                                            DataInputStream dataInputStream9 = new DataInputStream(new BufferedInputStream(ScannerHelper.class.getResourceAsStream(str + "/part14.rsc")));
                                                                            try {
                                                                                long[] jArr10 = new long[1024];
                                                                                for (int i9 = 0; i9 < 1024; i9++) {
                                                                                    jArr10[i9] = dataInputStream9.readLong();
                                                                                }
                                                                                jArr[1][4] = jArr10;
                                                                                return jArr;
                                                                            } finally {
                                                                                dataInputStream9.close();
                                                                            }
                                                                        } catch (Throwable th10) {
                                                                            if (0 == 0) {
                                                                                throw th10;
                                                                            }
                                                                            if (null != th10) {
                                                                                try {
                                                                                    th9.addSuppressed(th10);
                                                                                } catch (IOException e) {
                                                                                    e.printStackTrace();
                                                                                }
                                                                            }
                                                                            throw null;
                                                                        }
                                                                    } catch (Throwable th11) {
                                                                        th8 = th11;
                                                                        try {
                                                                            dataInputStream8.close();
                                                                            throw th8;
                                                                        } catch (Throwable th12) {
                                                                            th = th12;
                                                                            if (th8 == null) {
                                                                                throw th;
                                                                            }
                                                                            if (th8 != th) {
                                                                                try {
                                                                                    th8.addSuppressed(th);
                                                                                } catch (IOException e2) {
                                                                                    e2.printStackTrace();
                                                                                }
                                                                            }
                                                                            throw th8;
                                                                        }
                                                                    }
                                                                } catch (Throwable th13) {
                                                                    th = th13;
                                                                    th8 = null;
                                                                }
                                                            } catch (Throwable th14) {
                                                                try {
                                                                    dataInputStream7.close();
                                                                    throw th14;
                                                                } catch (Throwable th15) {
                                                                    th7 = th14;
                                                                    th = th15;
                                                                    if (th7 == null) {
                                                                        throw th;
                                                                    }
                                                                    if (th7 != th) {
                                                                        try {
                                                                            th7.addSuppressed(th);
                                                                        } catch (IOException e3) {
                                                                            e3.printStackTrace();
                                                                        }
                                                                    }
                                                                    throw th7;
                                                                }
                                                            }
                                                        } catch (Throwable th16) {
                                                            th = th16;
                                                            th7 = null;
                                                        }
                                                    } catch (Throwable th17) {
                                                        th6 = th17;
                                                        try {
                                                            dataInputStream6.close();
                                                            throw th6;
                                                        } catch (Throwable th18) {
                                                            th = th18;
                                                            if (th6 == null) {
                                                                throw th;
                                                            }
                                                            if (th6 != th) {
                                                                try {
                                                                    th6.addSuppressed(th);
                                                                } catch (IOException e4) {
                                                                    e4.printStackTrace();
                                                                }
                                                            }
                                                            throw th6;
                                                        }
                                                    }
                                                } catch (Throwable th19) {
                                                    th = th19;
                                                    th6 = null;
                                                }
                                            } catch (Throwable th20) {
                                                th5 = th20;
                                                try {
                                                    dataInputStream5.close();
                                                    throw th5;
                                                } catch (Throwable th21) {
                                                    th = th21;
                                                    if (th5 == null) {
                                                        throw th;
                                                    }
                                                    if (th5 != th) {
                                                        try {
                                                            th5.addSuppressed(th);
                                                        } catch (IOException e5) {
                                                            e5.printStackTrace();
                                                        }
                                                    }
                                                    throw th5;
                                                }
                                            }
                                        } catch (Throwable th22) {
                                            th = th22;
                                            th5 = null;
                                        }
                                    } catch (Throwable th23) {
                                        th4 = th23;
                                        try {
                                            dataInputStream4.close();
                                            throw th4;
                                        } catch (Throwable th24) {
                                            th = th24;
                                            if (th4 == null) {
                                                throw th;
                                            }
                                            if (th4 != th) {
                                                try {
                                                    th4.addSuppressed(th);
                                                } catch (IOException e6) {
                                                    e6.printStackTrace();
                                                }
                                            }
                                            throw th4;
                                        }
                                    }
                                } catch (Throwable th25) {
                                    th = th25;
                                    th4 = null;
                                }
                            } catch (Throwable th26) {
                                th3 = th26;
                                try {
                                    dataInputStream3.close();
                                    throw th3;
                                } catch (Throwable th27) {
                                    th = th27;
                                    if (th3 == null) {
                                        throw th;
                                    }
                                    if (th3 != th) {
                                        try {
                                            th3.addSuppressed(th);
                                        } catch (IOException e7) {
                                            e7.printStackTrace();
                                        }
                                    }
                                    throw th3;
                                }
                            }
                        } catch (Throwable th28) {
                            th = th28;
                            th3 = null;
                        }
                    } catch (Throwable th29) {
                        th2 = th29;
                        try {
                            dataInputStream2.close();
                            throw th2;
                        } catch (Throwable th30) {
                            th = th30;
                            if (th2 == null) {
                                throw th;
                            }
                            if (th2 != th) {
                                try {
                                    th2.addSuppressed(th);
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th31) {
                    th = th31;
                    th2 = null;
                }
            } catch (Throwable th32) {
                th = th32;
                try {
                    dataInputStream.close();
                    throw th;
                } catch (Throwable th33) {
                    th = th33;
                    if (th == null) {
                        throw th;
                    }
                    if (th != th) {
                        try {
                            th.addSuppressed(th);
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th34) {
            th = th34;
            th = null;
        }
    }

    private static final boolean isBitSet(long[] jArr, int i) {
        try {
            return (Bits[i % 64] & jArr[i / 64]) != 0;
        } catch (NullPointerException unused) {
        }
    }

    public static boolean isDigit(char c) throws InvalidInputException {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 4) != 0;
        }
        return Character.isDigit(c);
    }

    public static boolean isJavaIdentifierPart(long j, int i) {
        if (j <= 3276800) {
            if (Tables == null) {
                initializeTable();
            }
            return isJavaIdentifierPart0(i, Tables);
        }
        if (j <= 3342336) {
            if (Tables7 == null) {
                initializeTable17();
            }
            return isJavaIdentifierPart0(i, Tables7);
        }
        if (j <= 3407872) {
            if (Tables8 == null) {
                initializeTable18();
            }
            return isJavaIdentifierPart0(i, Tables8);
        }
        if (j <= 3538944) {
            if (Tables9 == null) {
                initializeTable19();
            }
            return isJavaIdentifierPart0(i, Tables9);
        }
        if (j <= 3604480) {
            if (Tables11 == null) {
                initializeTableJava11();
            }
            return isJavaIdentifierPart0(i, Tables11);
        }
        if (j <= 3670016) {
            if (Tables12 == null) {
                initializeTableJava12();
            }
            return isJavaIdentifierPart0(i, Tables12);
        }
        if (j <= 3801088) {
            if (Tables13 == null) {
                initializeTableJava13();
            }
            return isJavaIdentifierPart0(i, Tables13);
        }
        if (Tables15 == null) {
            initializeTableJava15();
        }
        return isJavaIdentifierPart0(i, Tables15, true);
    }

    private static boolean isJavaIdentifierPart0(int i, long[][][] jArr, boolean z) {
        int i2 = (2031616 & i) >> 16;
        if (i2 == 0) {
            return isBitSet(jArr[1][0], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (i2 == 1) {
            return isBitSet(jArr[1][1], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (i2 == 2) {
            return isBitSet(jArr[1][2], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (i2 != 3) {
            if (i2 != 14) {
                return false;
            }
            return z ? isBitSet(jArr[1][4], i & ExtraCompilerModifiers.AccJustFlag) : isBitSet(jArr[1][3], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (z) {
            return isBitSet(jArr[1][3], i & ExtraCompilerModifiers.AccJustFlag);
        }
        return false;
    }

    public static boolean isJavaIdentifierStart(long j, int i) {
        if (j <= 3276800) {
            if (Tables == null) {
                initializeTable();
            }
            return isJavaIdentifierStart0(i, Tables);
        }
        if (j <= 3342336) {
            if (Tables7 == null) {
                initializeTable17();
            }
            return isJavaIdentifierStart0(i, Tables7);
        }
        if (j <= 3407872) {
            if (Tables8 == null) {
                initializeTable18();
            }
            return isJavaIdentifierStart0(i, Tables8);
        }
        if (j <= 3538944) {
            if (Tables9 == null) {
                initializeTable19();
            }
            return isJavaIdentifierStart0(i, Tables9);
        }
        if (j <= 3604480) {
            if (Tables11 == null) {
                initializeTableJava11();
            }
            return isJavaIdentifierStart0(i, Tables11);
        }
        if (j <= 3670016) {
            if (Tables12 == null) {
                initializeTableJava12();
            }
            return isJavaIdentifierStart0(i, Tables12);
        }
        if (j <= 3801088) {
            if (Tables13 == null) {
                initializeTableJava13();
            }
            return isJavaIdentifierStart0(i, Tables13);
        }
        if (Tables15 == null) {
            initializeTableJava15();
        }
        return isJavaIdentifierStart0(i, Tables15, true);
    }

    private static boolean isJavaIdentifierStart0(int i, long[][][] jArr, boolean z) {
        int i2 = (2031616 & i) >> 16;
        if (i2 == 0) {
            return isBitSet(jArr[0][0], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (i2 == 1) {
            return isBitSet(jArr[0][1], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (i2 == 2) {
            return isBitSet(jArr[0][2], i & ExtraCompilerModifiers.AccJustFlag);
        }
        if (i2 == 3 && z) {
            return isBitSet(jArr[0][3], i & ExtraCompilerModifiers.AccJustFlag);
        }
        return false;
    }

    public static boolean isLetter(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 48) != 0;
        }
        return Character.isLetter(c);
    }

    public static boolean isLetterOrDigit(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 52) != 0;
        }
        return Character.isLetterOrDigit(c);
    }

    public static boolean isLowerCase(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 16) != 0;
        }
        return Character.isLowerCase(c);
    }

    public static boolean isUpperCase(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 32) != 0;
        }
        return Character.isUpperCase(c);
    }

    public static boolean isWhitespace(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 1) != 0;
        }
        return Character.isWhitespace(c);
    }

    private static int toCodePoint(char c, char c2) {
        return ((c - Scanner.HIGH_SURROGATE_MIN_VALUE) * 1024) + (c2 - Scanner.LOW_SURROGATE_MIN_VALUE) + 65536;
    }

    public static char toLowerCase(char c) {
        if (c < 128) {
            int i = OBVIOUS_IDENT_CHAR_NATURES[c];
            if ((i & 16) != 0) {
                return c;
            }
            if ((i & 32) != 0) {
                return (char) (c + ' ');
            }
        }
        return Character.toLowerCase(c);
    }

    public static char toUpperCase(char c) {
        if (c < 128) {
            int i = OBVIOUS_IDENT_CHAR_NATURES[c];
            if ((i & 32) != 0) {
                return c;
            }
            if ((i & 16) != 0) {
                return (char) (c - ' ');
            }
        }
        return Character.toUpperCase(c);
    }

    private static boolean isJavaIdentifierStart0(int i, long[][][] jArr) {
        return isJavaIdentifierStart0(i, jArr, false);
    }

    private static boolean isJavaIdentifierPart0(int i, long[][][] jArr) {
        return isJavaIdentifierPart0(i, jArr, false);
    }

    public static boolean isJavaIdentifierPart(long j, char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 8) != 0;
        }
        return isJavaIdentifierPart(j, (int) c);
    }

    public static boolean isJavaIdentifierStart(long j, char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 64) != 0;
        }
        return isJavaIdentifierStart(j, (int) c);
    }

    public static boolean isJavaIdentifierPart(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 8) != 0;
        }
        return Character.isJavaIdentifierPart(c);
    }

    public static boolean isJavaIdentifierStart(long j, char c, char c2) {
        return isJavaIdentifierStart(j, toCodePoint(c, c2));
    }

    public static boolean isJavaIdentifierStart(char c) {
        if (c < 128) {
            return (OBVIOUS_IDENT_CHAR_NATURES[c] & 64) != 0;
        }
        return Character.isJavaIdentifierStart(c);
    }

    public static boolean isJavaIdentifierPart(long j, char c, char c2) {
        return isJavaIdentifierPart(j, toCodePoint(c, c2));
    }
}
