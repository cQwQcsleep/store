package com.sun.nio.zipfs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ZipConstants {
    static final int CENATT = 36;
    static final int CENATX = 38;
    static final int CENCOM = 32;
    static final int CENCRC = 16;
    static final int CENDSK = 34;
    static final int CENEXT = 30;
    static final int CENFLG = 8;
    static final int CENHDR = 46;
    static final int CENHOW = 10;
    static final int CENLEN = 24;
    static final int CENNAM = 28;
    static final int CENOFF = 42;
    static long CENSIG = 33639248;
    static final int CENSIZ = 20;
    static final int CENTIM = 12;
    static final int CENVEM = 4;
    static final int CENVER = 6;
    static final int ENDCOM = 20;
    static final int ENDHDR = 22;
    static final int ENDOFF = 16;
    static long ENDSIG = 101010256;
    static final int ENDSIZ = 12;
    static final int ENDSUB = 8;
    static final int ENDTOT = 10;
    static final long END_MAXLEN = 65557;
    static final int EXTCRC = 4;
    static final int EXTHDR = 16;
    static final int EXTID_EFS = 23;
    static final int EXTID_EXTT = 21589;
    static final int EXTID_NTFS = 10;
    static final int EXTID_UNIX = 13;
    static final int EXTID_ZIP64 = 1;
    static final int EXTLEN = 12;
    static long EXTSIG = 134695760;
    static final int EXTSIZ = 8;
    static final int FLAG_DATADESCR = 8;
    static final int FLAG_EFS = 2048;
    static final int FLAG_ENCRYPTED = 1;
    static final int LOCCRC = 14;
    static final int LOCEXT = 28;
    static final int LOCFLG = 6;
    static final int LOCHDR = 30;
    static final int LOCHOW = 8;
    static final int LOCLEN = 22;
    static final int LOCNAM = 26;
    static long LOCSIG = 67324752;
    static final int LOCSIZ = 18;
    static final int LOCTIM = 10;
    static final int LOCVER = 4;
    static final int METHOD_AES = 99;
    static final int METHOD_BZIP2 = 12;
    static final int METHOD_DEFLATED = 8;
    static final int METHOD_DEFLATED64 = 9;
    static final int METHOD_LZ77 = 19;
    static final int METHOD_LZMA = 14;
    static final int METHOD_STORED = 0;
    static final int READBLOCKSZ = 128;
    static final int ZIP64_ENDDSK = 20;
    static final int ZIP64_ENDEXT = 56;
    static final int ZIP64_ENDHDR = 56;
    static final int ZIP64_ENDLEN = 4;
    static final int ZIP64_ENDNMD = 16;
    static final int ZIP64_ENDOFF = 48;
    static final long ZIP64_ENDSIG = 101075792;
    static final int ZIP64_ENDSIZ = 40;
    static final int ZIP64_ENDTOD = 24;
    static final int ZIP64_ENDTOT = 32;
    static final int ZIP64_ENDVEM = 12;
    static final int ZIP64_ENDVER = 14;
    static final int ZIP64_EXTCRC = 4;
    static final int ZIP64_EXTHDR = 24;
    static final int ZIP64_EXTID = 1;
    static final int ZIP64_EXTLEN = 16;
    static final int ZIP64_EXTSIZ = 8;
    static final int ZIP64_LOCDSK = 4;
    static final int ZIP64_LOCHDR = 20;
    static final int ZIP64_LOCOFF = 8;
    static final long ZIP64_LOCSIG = 117853008;
    static final int ZIP64_LOCTOT = 16;
    static final long ZIP64_MINVAL = 4294967295L;
    static final int ZIP64_MINVAL32 = 65535;

    public static final int CENATT(byte[] bArr, int i) {
        return SH(bArr, i + 36);
    }

    public static final long CENATX(byte[] bArr, int i) {
        return LG(bArr, i + 38);
    }

    public static final int CENCOM(byte[] bArr, int i) {
        return SH(bArr, i + 32);
    }

    public static final long CENCRC(byte[] bArr, int i) {
        return LG(bArr, i + 16);
    }

    public static final int CENDSK(byte[] bArr, int i) {
        return SH(bArr, i + 34);
    }

    public static final int CENEXT(byte[] bArr, int i) {
        return SH(bArr, i + 30);
    }

    public static final int CENFLG(byte[] bArr, int i) {
        return SH(bArr, i + 8);
    }

    public static final int CENHOW(byte[] bArr, int i) {
        return SH(bArr, i + 10);
    }

    public static final long CENLEN(byte[] bArr, int i) {
        return LG(bArr, i + 24);
    }

    public static final int CENNAM(byte[] bArr, int i) {
        return SH(bArr, i + 28);
    }

    public static final long CENOFF(byte[] bArr, int i) {
        return LG(bArr, i + 42);
    }

    public static final long CENSIG(byte[] bArr, int i) {
        return LG(bArr, i);
    }

    public static final long CENSIZ(byte[] bArr, int i) {
        return LG(bArr, i + 20);
    }

    public static final long CENTIM(byte[] bArr, int i) {
        return LG(bArr, i + 12);
    }

    public static final int CENVEM(byte[] bArr, int i) {
        return SH(bArr, i + 4);
    }

    public static final int CENVER(byte[] bArr, int i) {
        return SH(bArr, i + 6);
    }

    public static final int CH(byte[] bArr, int i) {
        return Byte.toUnsignedInt(bArr[i]);
    }

    public static final int ENDCOM(byte[] bArr) {
        return SH(bArr, 20);
    }

    public static final long ENDOFF(byte[] bArr) {
        return LG(bArr, 16);
    }

    public static final long ENDSIZ(byte[] bArr) {
        return LG(bArr, 12);
    }

    public static final int ENDSUB(byte[] bArr) {
        return SH(bArr, 8);
    }

    public static final int ENDTOT(byte[] bArr) {
        return SH(bArr, 10);
    }

    public static final long EXTCRC(byte[] bArr) {
        return LG(bArr, 4);
    }

    public static final long EXTLEN(byte[] bArr) {
        return LG(bArr, 12);
    }

    public static final long EXTSIZ(byte[] bArr) {
        return LG(bArr, 8);
    }

    public static final long LG(byte[] bArr, int i) {
        return ((long) ((SH(bArr, i + 2) << 16) | SH(bArr, i))) & ZIP64_MINVAL;
    }

    public static final long LL(byte[] bArr, int i) {
        return (LG(bArr, i + 4) << 32) | LG(bArr, i);
    }

    public static final long LOCCRC(byte[] bArr) {
        return LG(bArr, 14);
    }

    public static final int LOCEXT(byte[] bArr) {
        return SH(bArr, 28);
    }

    public static final int LOCFLG(byte[] bArr) {
        return SH(bArr, 6);
    }

    public static final int LOCHOW(byte[] bArr) {
        return SH(bArr, 8);
    }

    public static final long LOCLEN(byte[] bArr) {
        return LG(bArr, 22);
    }

    public static final int LOCNAM(byte[] bArr) {
        return SH(bArr, 26);
    }

    public static final long LOCSIG(byte[] bArr) {
        return LG(bArr, 0);
    }

    public static final long LOCSIZ(byte[] bArr) {
        return LG(bArr, 18);
    }

    public static final long LOCTIM(byte[] bArr) {
        return LG(bArr, 10);
    }

    public static final int LOCVER(byte[] bArr) {
        return SH(bArr, 4);
    }

    public static final int SH(byte[] bArr, int i) {
        return (Byte.toUnsignedInt(bArr[i + 1]) << 8) | Byte.toUnsignedInt(bArr[i]);
    }

    public static final long ZIP64_ENDOFF(byte[] bArr) {
        return LL(bArr, 48);
    }

    public static final long ZIP64_ENDSIZ(byte[] bArr) {
        return LL(bArr, 40);
    }

    public static final long ZIP64_ENDTOD(byte[] bArr) {
        return LL(bArr, 24);
    }

    public static final long ZIP64_ENDTOT(byte[] bArr) {
        return LL(bArr, 32);
    }

    public static final long ZIP64_LOCOFF(byte[] bArr) {
        return LL(bArr, 8);
    }

    public static boolean cenSigAt(byte[] bArr, int i) {
        return pkSigAt(bArr, i, 1, 2);
    }

    public static boolean end64SigAt(byte[] bArr, int i) {
        return pkSigAt(bArr, i, 6, 6);
    }

    public static boolean endSigAt(byte[] bArr, int i) {
        return pkSigAt(bArr, i, 5, 6);
    }

    public static boolean extSigAt(byte[] bArr, int i) {
        return pkSigAt(bArr, i, 7, 8);
    }

    public static long getSig(byte[] bArr, int i) {
        return LG(bArr, i);
    }

    public static boolean locSigAt(byte[] bArr, int i) {
        return pkSigAt(bArr, i, 3, 4);
    }

    public static boolean locator64SigAt(byte[] bArr, int i) {
        return pkSigAt(bArr, i, 6, 7);
    }

    private static boolean pkSigAt(byte[] bArr, int i, int i2, int i3) {
        return (bArr[i + 2] == i2) & (bArr[i] == 80) & (bArr[i + 1] == 75) & (bArr[i + 3] == i3);
    }

    public static final int ENDCOM(byte[] bArr, int i) {
        return SH(bArr, i + 20);
    }
}
