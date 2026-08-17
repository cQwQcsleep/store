package org.bouncycastle.pqc.crypto.picnic;

import defpackage.qf1;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.logging.Logger;
import kotlin.UByte;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.raw.Bits;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class PicnicEngine {
    private static final Logger LOG = Logger.getLogger(PicnicEngine.class.getName());
    protected static final int LOWMC_MAX_AND_GATES = 1144;
    protected static final int LOWMC_MAX_KEY_BITS = 256;
    private static final int LOWMC_MAX_STATE_SIZE = 64;
    protected static final int LOWMC_MAX_WORDS = 16;
    private static final int MAX_AUX_BYTES = 176;
    private static final int MAX_DIGEST_SIZE = 64;
    private static final int PICNIC_MAX_LOWMC_BLOCK_SIZE = 32;
    private static final int TRANSFORM_FS = 0;
    private static final int TRANSFORM_INVALID = 255;
    private static final int TRANSFORM_UR = 1;
    private static final int WORD_SIZE_BITS = 32;
    protected static final int saltSizeBytes = 32;
    private final int CRYPTO_BYTES;
    private final int CRYPTO_PUBLICKEYBYTES;
    private final int CRYPTO_SECRETKEYBYTES;
    protected final int UnruhGWithInputBytes;
    protected final int UnruhGWithoutInputBytes;
    protected final int andSizeBytes;
    protected final Xof digest;
    protected final int digestSizeBytes;
    protected final LowmcConstants lowmcConstants;
    protected final int numMPCParties;
    protected final int numMPCRounds;
    protected final int numOpenedRounds;
    protected final int numRounds;
    protected final int numSboxes;
    private final int parameters;
    protected final int pqSecurityLevel;
    protected final int seedSizeBytes;
    private int signatureLength;
    protected final int stateSizeBits;
    protected final int stateSizeBytes;
    protected final int stateSizeWords;
    private final int transform;

    /* JADX WARN: Code duplicated, block: B:21:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:23:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:24:0x0101  */
    /* JADX WARN: Code duplicated, block: B:25:0x0109  */
    /* JADX WARN: Code duplicated, block: B:26:0x0114  */
    /* JADX WARN: Code duplicated, block: B:27:0x011c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0124  */
    /* JADX WARN: Code duplicated, block: B:29:0x012f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0137  */
    /* JADX WARN: Code duplicated, block: B:31:0x013f  */
    /* JADX WARN: Code duplicated, block: B:32:0x0147  */
    /* JADX WARN: Code duplicated, block: B:33:0x014f  */
    /* JADX WARN: Code duplicated, block: B:34:0x0159  */
    /* JADX WARN: Code duplicated, block: B:37:0x018b  */
    /* JADX WARN: Code duplicated, block: B:38:0x018e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0192  */
    /* JADX WARN: Code duplicated, block: B:42:0x0199  */
    /* JADX WARN: Code duplicated, block: B:43:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:45:0x01a6 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:50:0x01b3  */
    public PicnicEngine(int i, LowmcConstants lowmcConstants) {
        int i2;
        int iNumBytes;
        int iNumBytes2;
        int iNumBytes3;
        int i3;
        SHAKEDigest sHAKEDigest;
        this.lowmcConstants = lowmcConstants;
        this.parameters = i;
        switch (i) {
            case 1:
            case 2:
                this.pqSecurityLevel = 64;
                this.stateSizeBits = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                this.numMPCRounds = 219;
                this.numMPCParties = 3;
                this.numSboxes = 10;
                this.numRounds = 20;
                this.digestSizeBytes = 32;
                this.numOpenedRounds = 0;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i4 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i4;
                    this.UnruhGWithInputBytes = i4 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128 || i3 == 129) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(256);
                }
                this.digest = sHAKEDigest;
                return;
            case 3:
            case 4:
                this.pqSecurityLevel = 96;
                this.stateSizeBits = 192;
                this.numMPCRounds = 329;
                this.numMPCParties = 3;
                this.numSboxes = 10;
                this.numRounds = 30;
                this.digestSizeBytes = 48;
                this.numOpenedRounds = 0;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i5 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i5;
                    this.UnruhGWithInputBytes = i5 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case 5:
            case 6:
                this.pqSecurityLevel = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                this.stateSizeBits = 256;
                this.numMPCRounds = 438;
                this.numMPCParties = 3;
                this.numSboxes = 10;
                this.numRounds = 38;
                this.digestSizeBytes = 64;
                this.numOpenedRounds = 0;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i6 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i6;
                    this.UnruhGWithInputBytes = i6 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case 7:
                this.pqSecurityLevel = 64;
                this.stateSizeBits = 129;
                this.numMPCRounds = 250;
                this.numOpenedRounds = 36;
                this.numMPCParties = 16;
                this.numSboxes = 43;
                this.numRounds = 4;
                this.digestSizeBytes = 32;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i7 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i7;
                    this.UnruhGWithInputBytes = i7 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case 8:
                this.pqSecurityLevel = 96;
                this.stateSizeBits = 192;
                this.numMPCRounds = 419;
                this.numOpenedRounds = 52;
                this.numMPCParties = 16;
                this.numSboxes = 64;
                this.numRounds = 4;
                this.digestSizeBytes = 48;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i8 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i8;
                    this.UnruhGWithInputBytes = i8 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case 9:
                this.pqSecurityLevel = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                this.stateSizeBits = 255;
                this.numMPCRounds = 601;
                this.numOpenedRounds = 68;
                this.numMPCParties = 16;
                this.numSboxes = 85;
                this.numRounds = 4;
                this.digestSizeBytes = 64;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i9 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i9;
                    this.UnruhGWithInputBytes = i9 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case 10:
                this.pqSecurityLevel = 64;
                this.stateSizeBits = 129;
                this.numMPCRounds = 219;
                this.numMPCParties = 3;
                this.numSboxes = 43;
                this.numRounds = 4;
                this.digestSizeBytes = 32;
                this.numOpenedRounds = 0;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i10 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i10;
                    this.UnruhGWithInputBytes = i10 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case 11:
                this.pqSecurityLevel = 96;
                this.stateSizeBits = 192;
                this.numMPCRounds = 329;
                this.numMPCParties = 3;
                this.numSboxes = 64;
                this.numRounds = 4;
                this.digestSizeBytes = 48;
                this.numOpenedRounds = 0;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i11 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i11;
                    this.UnruhGWithInputBytes = i11 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                this.pqSecurityLevel = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                this.stateSizeBits = 255;
                this.numMPCRounds = 438;
                this.numMPCParties = 3;
                this.numSboxes = 85;
                this.numRounds = 4;
                this.digestSizeBytes = 64;
                this.numOpenedRounds = 0;
                switch (i) {
                    case 1:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 34036;
                        break;
                    case 2:
                        this.CRYPTO_SECRETKEYBYTES = 49;
                        this.CRYPTO_PUBLICKEYBYTES = 33;
                        i2 = 53965;
                        break;
                    case 3:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 76784;
                        break;
                    case 4:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 121857;
                        break;
                    case 5:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 132876;
                        break;
                    case 6:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 209526;
                        break;
                    case 7:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 14612;
                        break;
                    case 8:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 35028;
                        break;
                    case 9:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 61028;
                        break;
                    case 10:
                        this.CRYPTO_SECRETKEYBYTES = 52;
                        this.CRYPTO_PUBLICKEYBYTES = 35;
                        i2 = 32061;
                        break;
                    case 11:
                        this.CRYPTO_SECRETKEYBYTES = 73;
                        this.CRYPTO_PUBLICKEYBYTES = 49;
                        i2 = 71179;
                        break;
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.CRYPTO_SECRETKEYBYTES = 97;
                        this.CRYPTO_PUBLICKEYBYTES = 65;
                        i2 = 126286;
                        break;
                    default:
                        i2 = -1;
                        this.CRYPTO_SECRETKEYBYTES = -1;
                        this.CRYPTO_PUBLICKEYBYTES = -1;
                        break;
                }
                this.CRYPTO_BYTES = i2;
                iNumBytes = Utils.numBytes(this.numSboxes * 3 * this.numRounds);
                this.andSizeBytes = iNumBytes;
                iNumBytes2 = Utils.numBytes(this.stateSizeBits);
                this.stateSizeBytes = iNumBytes2;
                iNumBytes3 = Utils.numBytes(this.pqSecurityLevel * 2);
                this.seedSizeBytes = iNumBytes3;
                i3 = this.stateSizeBits;
                this.stateSizeWords = (i3 + 31) / 32;
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                        this.transform = 0;
                        break;
                    case 2:
                    case 4:
                    case 6:
                        this.transform = 1;
                        break;
                    default:
                        this.transform = 255;
                        break;
                }
                if (this.transform == 1) {
                    int i12 = iNumBytes3 + iNumBytes;
                    this.UnruhGWithoutInputBytes = i12;
                    this.UnruhGWithInputBytes = i12 + iNumBytes2;
                } else {
                    this.UnruhGWithoutInputBytes = 0;
                    this.UnruhGWithInputBytes = 0;
                }
                if (i3 != 128) {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                } else {
                    sHAKEDigest = new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
                }
                this.digest = sHAKEDigest;
                return;
            default:
                qf1.a("unknown parameter set ", i);
                throw null;
        }
    }

    private void Commit(byte[] bArr, int i, View view, byte[] bArr2) {
        this.digest.update((byte) 4);
        this.digest.update(bArr, i, this.seedSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        this.digest.update((byte) 0);
        this.digest.update(bArr2, 0, this.digestSizeBytes);
        this.digest.update(Pack.intToLittleEndian(view.inputShare), 0, this.stateSizeBytes);
        this.digest.update(view.communicatedBits, 0, this.andSizeBytes);
        this.digest.update(Pack.intToLittleEndian(view.outputShare), 0, this.stateSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
    }

    private void G(int i, byte[] bArr, int i2, View view, byte[] bArr2) {
        int i3 = this.seedSizeBytes + this.andSizeBytes;
        this.digest.update((byte) 5);
        this.digest.update(bArr, i2, this.seedSizeBytes);
        this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        this.digest.update(bArr2, 0, this.digestSizeBytes);
        if (i == 2) {
            this.digest.update(Pack.intToLittleEndian(view.inputShare), 0, this.stateSizeBytes);
            i3 += this.stateSizeBytes;
        }
        this.digest.update(view.communicatedBits, 0, this.andSizeBytes);
        this.digest.update(Pack.intToLittleEndian(i3), 0, 2);
        this.digest.doFinal(bArr2, 0, i3);
    }

    private void H3(int[] iArr, int[] iArr2, View[][] viewArr, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        this.digest.update((byte) 1);
        byte[] bArr6 = new byte[this.stateSizeWords * 4];
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                Pack.intToLittleEndian(viewArr[i][i2].outputShare, bArr6, 0);
                this.digest.update(bArr6, 0, this.stateSizeBytes);
            }
        }
        implH3(iArr, iArr2, bArr, bArr2, bArr3, bArr4, bArr5);
    }

    private void HCP(byte[] bArr, int[] iArr, int[] iArr2, byte[][] bArr2, byte[] bArr3, byte[] bArr4, int[] iArr3, int[] iArr4, byte[] bArr5) {
        Xof xof;
        int i = 0;
        while (true) {
            int i2 = this.numMPCRounds;
            xof = this.digest;
            if (i >= i2) {
                break;
            }
            xof.update(bArr2[i], 0, this.digestSizeBytes);
            i++;
        }
        byte[] bArr6 = new byte[32];
        xof.update(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr4, 0, 32);
        updateDigest(iArr3, bArr6);
        updateDigest(iArr4, bArr6);
        this.digest.update(bArr5, 0, bArr5.length);
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
        if (iArr == null || iArr2 == null) {
            return;
        }
        expandChallengeHash(bArr, iArr, iArr2);
    }

    private void LowMCEnc(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[16];
        if (iArr != iArr2) {
            System.arraycopy(iArr, 0, iArr2, 0, this.stateSizeWords);
        }
        KMatricesWithPointer kMatricesWithPointerKMatrix = this.lowmcConstants.KMatrix(this, 0);
        matrix_mul(iArr4, iArr3, kMatricesWithPointerKMatrix.getData(), kMatricesWithPointerKMatrix.getMatrixPointer());
        xor_array(iArr2, iArr2, iArr4, 0);
        for (int i = 1; i <= this.numRounds; i++) {
            KMatricesWithPointer kMatricesWithPointerKMatrix2 = this.lowmcConstants.KMatrix(this, i);
            matrix_mul(iArr4, iArr3, kMatricesWithPointerKMatrix2.getData(), kMatricesWithPointerKMatrix2.getMatrixPointer());
            substitution(iArr2);
            int i2 = i - 1;
            KMatricesWithPointer kMatricesWithPointerLMatrix = this.lowmcConstants.LMatrix(this, i2);
            matrix_mul(iArr2, iArr2, kMatricesWithPointerLMatrix.getData(), kMatricesWithPointerLMatrix.getMatrixPointer());
            KMatricesWithPointer kMatricesWithPointerRConstant = this.lowmcConstants.RConstant(this, i2);
            xor_array(iArr2, iArr2, kMatricesWithPointerRConstant.getData(), kMatricesWithPointerRConstant.getMatrixPointer());
            xor_array(iArr2, iArr2, iArr4, 0);
        }
    }

    public static int appendUnique(int[] iArr, int i, int i2) {
        if (i2 == 0) {
            iArr[i2] = i;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                if (iArr[i3] == i) {
                    return i2;
                }
            }
            iArr[i2] = i;
        }
        return i2 + 1;
    }

    private boolean arePaddingBitsZero(byte[] bArr, int i) {
        int iNumBytes = Utils.numBytes(i);
        while (i < iNumBytes * 8) {
            if (Utils.getBit(bArr, i) != 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    private void aux_mpc_AND(int i, int i2, int i3, Tape tape) {
        int i4 = this.numMPCParties - 1;
        Utils.setBit(tape.tapes[i4], tape.pos - 1, (byte) ((((i & i2) ^ (Utils.parity16(tape.tapesToWord()) ^ Utils.getBit(tape.tapes[i4], tape.pos - 1))) ^ i3) & 255));
    }

    public static int bitsToChunks(int i, byte[] bArr, int i2, int[] iArr) {
        int i3 = i2 * 8;
        if (i > i3) {
            return 0;
        }
        int i4 = i3 / i;
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i5] = 0;
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i5] = iArr[i5] + (Utils.getBit(bArr, (i5 * i) + i6) << i6);
            }
        }
        return i4;
    }

    private void commit(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2) {
        this.digest.update(bArr2, 0, this.seedSizeBytes);
        if (bArr3 != null) {
            this.digest.update(bArr3, 0, this.andSizeBytes);
        }
        this.digest.update(bArr4, 0, 32);
        this.digest.update(Pack.intToLittleEndian(i), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void commit_h(byte[] bArr, byte[][] bArr2) {
        int i = 0;
        while (true) {
            int i2 = this.numMPCParties;
            Xof xof = this.digest;
            if (i >= i2) {
                xof.doFinal(bArr, 0, this.digestSizeBytes);
                return;
            } else {
                xof.update(bArr2[i], 0, this.digestSizeBytes);
                i++;
            }
        }
    }

    private void commit_v(byte[] bArr, byte[] bArr2, Msg msg) {
        this.digest.update(bArr2, 0, this.stateSizeBytes);
        for (int i = 0; i < this.numMPCParties; i++) {
            this.digest.update(msg.msgs[i], 0, Utils.numBytes(msg.pos));
        }
        this.digest.doFinal(bArr, 0, this.digestSizeBytes);
    }

    private void computeSaltAndRootSeed(byte[] bArr, int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr2) {
        byte[] bArr3 = new byte[32];
        updateDigest(iArr, bArr3);
        this.digest.update(bArr2, 0, bArr2.length);
        updateDigest(iArr2, bArr3);
        updateDigest(iArr3, bArr3);
        Pack.shortToLittleEndian((short) this.stateSizeBits, bArr3, 0);
        this.digest.update(bArr3, 0, 2);
        this.digest.doFinal(bArr, 0, bArr.length);
    }

    private byte[] computeSeeds(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr) {
        byte[] bArr2 = new byte[(this.seedSizeBytes * this.numMPCParties * this.numMPCRounds) + 32];
        byte[] bArr3 = new byte[32];
        updateDigest(iArr, bArr3);
        this.digest.update(bArr, 0, bArr.length);
        updateDigest(iArr2, bArr3);
        updateDigest(iArr3, bArr3);
        this.digest.update(Pack.intToLittleEndian(this.stateSizeBits), 0, 2);
        this.digest.doFinal(bArr2, 0, (this.seedSizeBytes * this.numMPCParties * this.numMPCRounds) + 32);
        return bArr2;
    }

    private boolean contains(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private int countNonZeroChallenges(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        int i4 = 0;
        int iBitCount = 0;
        while (true) {
            int i5 = i3 + 16;
            i2 = this.numMPCRounds;
            if (i5 > i2) {
                break;
            }
            int iLittleEndianToInt = Pack.littleEndianToInt(bArr, (i3 >>> 2) + i);
            int i6 = iLittleEndianToInt >>> 1;
            i4 |= iLittleEndianToInt & i6;
            iBitCount += Integers.bitCount((iLittleEndianToInt ^ i6) & 1431655765);
            i3 = i5;
        }
        int i7 = (i2 - i3) * 2;
        if (i7 > 0) {
            int iLittleEndianToInt_Low = Pack.littleEndianToInt_Low(bArr, i + (i3 >>> 2), (i7 + 7) / 8) & Utils.getTrailingBitsMask(i7);
            int i8 = iLittleEndianToInt_Low >>> 1;
            i4 |= iLittleEndianToInt_Low & i8;
            iBitCount += Integers.bitCount((iLittleEndianToInt_Low ^ i8) & 1431655765);
        }
        if ((i4 & 1431655765) == 0) {
            return iBitCount;
        }
        return -1;
    }

    private boolean createRandomTape(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        if (i4 < this.digestSizeBytes) {
            return false;
        }
        this.digest.update((byte) 2);
        this.digest.update(bArr, i, this.seedSizeBytes);
        this.digest.doFinal(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr3, 0, this.digestSizeBytes);
        this.digest.update(bArr2, 0, 32);
        this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i3), 0, 2);
        this.digest.update(Pack.intToLittleEndian(i4), 0, 2);
        this.digest.doFinal(bArr3, 0, i4);
        return true;
    }

    private void createRandomTapes(Tape tape, byte[][] bArr, int i, byte[] bArr2, int i2) {
        int i3 = this.andSizeBytes * 2;
        for (int i4 = 0; i4 < this.numMPCParties; i4++) {
            this.digest.update(bArr[i4 + i], 0, this.seedSizeBytes);
            this.digest.update(bArr2, 0, 32);
            this.digest.update(Pack.intToLittleEndian(i2), 0, 2);
            this.digest.update(Pack.intToLittleEndian(i4), 0, 2);
            this.digest.doFinal(tape.tapes[i4], 0, i3);
        }
    }

    private int deserializeSignature(Signature signature, byte[] bArr, int i, int i2) {
        int iCountNonZeroChallenges;
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr2 = signature.challengeBits;
        int iNumBytes = Utils.numBytes(this.numMPCRounds * 2);
        if (i < iNumBytes || (iCountNonZeroChallenges = countNonZeroChallenges(bArr, i2)) < 0) {
            return -1;
        }
        int i3 = this.stateSizeBytes * iCountNonZeroChallenges;
        int i4 = this.numMPCRounds;
        int i5 = iNumBytes + 32 + (((this.seedSizeBytes * 2) + this.andSizeBytes + this.digestSizeBytes) * i4) + i3;
        if (this.transform == 1) {
            i5 = i5 + (this.UnruhGWithInputBytes * (i4 - iCountNonZeroChallenges)) + (this.UnruhGWithoutInputBytes * iCountNonZeroChallenges);
        }
        if (i != i5) {
            LOG.fine("sigBytesLen = " + i + ", expected bytesRequired = " + i5);
            return -1;
        }
        System.arraycopy(bArr, i2, bArr2, 0, iNumBytes);
        int i6 = i2 + iNumBytes;
        System.arraycopy(bArr, i6, signature.salt, 0, 32);
        int i7 = i6 + 32;
        for (int i8 = 0; i8 < this.numMPCRounds; i8++) {
            int challenge = getChallenge(bArr2, i8);
            System.arraycopy(bArr, i7, proofArr[i8].view3Commitment, 0, this.digestSizeBytes);
            int i9 = i7 + this.digestSizeBytes;
            if (this.transform == 1) {
                int i10 = challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes;
                System.arraycopy(bArr, i9, proofArr[i8].view3UnruhG, 0, i10);
                i9 += i10;
            }
            System.arraycopy(bArr, i9, proofArr[i8].communicatedBits, 0, this.andSizeBytes);
            int i11 = i9 + this.andSizeBytes;
            System.arraycopy(bArr, i11, proofArr[i8].seed1, 0, this.seedSizeBytes);
            int i12 = this.seedSizeBytes;
            int i13 = i11 + i12;
            System.arraycopy(bArr, i13, proofArr[i8].seed2, 0, i12);
            i7 = i13 + this.seedSizeBytes;
            if (challenge == 1 || challenge == 2) {
                Pack.littleEndianToInt(bArr, i7, proofArr[i8].inputShare, 0, this.stateSizeBytes / 4);
                int i14 = this.stateSizeBits;
                if (i14 == 129) {
                    proofArr[i8].inputShare[this.stateSizeWords - 1] = bArr[(this.stateSizeBytes + i7) - 1] & UByte.MAX_VALUE;
                }
                i7 += this.stateSizeBytes;
                if (!arePaddingBitsZero(proofArr[i8].inputShare, i14)) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x012b A[PHI: r13
      0x012b: PHI (r13v8 int) = (r13v7 int), (r13v12 int) binds: [B:25:0x0103, B:27:0x0124] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:32:0x015e  */
    /* JADX WARN: Code duplicated, block: B:40:0x0158 A[SYNTHETIC] */
    private int deserializeSignature2(Signature2 signature2, byte[] bArr, int i, int i2) {
        int i3;
        Logger logger;
        String str;
        int i4 = this.digestSizeBytes;
        int i5 = i4 + 32;
        if (bArr.length < i5) {
            return -1;
        }
        System.arraycopy(bArr, i2, signature2.challengeHash, 0, i4);
        int i6 = i2 + this.digestSizeBytes;
        System.arraycopy(bArr, i6, signature2.salt, 0, 32);
        int i7 = i6 + 32;
        expandChallengeHash(signature2.challengeHash, signature2.challengeC, signature2.challengeP);
        int iRevealSeedsSize = new Tree(this, this.numMPCRounds, this.seedSizeBytes).revealSeedsSize(signature2.challengeC, this.numOpenedRounds);
        signature2.iSeedInfoLen = iRevealSeedsSize;
        int i8 = i5 + iRevealSeedsSize;
        int iOpenMerkleTreeSize = new Tree(this, this.numMPCRounds, this.digestSizeBytes).openMerkleTreeSize(getMissingLeavesList(signature2.challengeC), this.numMPCRounds - this.numOpenedRounds);
        signature2.cvInfoLen = iOpenMerkleTreeSize;
        int i9 = i8 + iOpenMerkleTreeSize;
        int iRevealSeedsSize2 = new Tree(this, this.numMPCParties, this.seedSizeBytes).revealSeedsSize(new int[1], 1);
        for (int i10 = 0; i10 < this.numMPCRounds; i10++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i10)) {
                if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i10)] != this.numMPCParties - 1) {
                    i9 += this.andSizeBytes;
                }
                i9 = i9 + iRevealSeedsSize2 + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes;
            }
        }
        if (i == i9) {
            int i11 = signature2.iSeedInfoLen;
            byte[] bArr2 = new byte[i11];
            signature2.iSeedInfo = bArr2;
            System.arraycopy(bArr, i7, bArr2, 0, i11);
            int i12 = i7 + signature2.iSeedInfoLen;
            int i13 = signature2.cvInfoLen;
            byte[] bArr3 = new byte[i13];
            signature2.cvInfo = bArr3;
            System.arraycopy(bArr, i12, bArr3, 0, i13);
            int i14 = i12 + signature2.cvInfoLen;
            for (int i15 = 0; i15 < this.numMPCRounds; i15++) {
                if (contains(signature2.challengeC, this.numOpenedRounds, i15)) {
                    signature2.proofs[i15] = new Signature2.Proof2(this);
                    Signature2.Proof2 proof2 = signature2.proofs[i15];
                    proof2.seedInfoLen = iRevealSeedsSize2;
                    byte[] bArr4 = new byte[iRevealSeedsSize2];
                    proof2.seedInfo = bArr4;
                    System.arraycopy(bArr, i14, bArr4, 0, iRevealSeedsSize2);
                    int i16 = i14 + signature2.proofs[i15].seedInfoLen;
                    if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i15)] != this.numMPCParties - 1) {
                        System.arraycopy(bArr, i16, signature2.proofs[i15].aux, 0, this.andSizeBytes);
                        i16 += this.andSizeBytes;
                        if (arePaddingBitsZero(signature2.proofs[i15].aux, this.numRounds * 3 * this.numSboxes)) {
                            System.arraycopy(bArr, i16, signature2.proofs[i15].input, 0, this.stateSizeBytes);
                            int i17 = i16 + this.stateSizeBytes;
                            int i18 = this.andSizeBytes;
                            System.arraycopy(bArr, i17, signature2.proofs[i15].msgs, 0, i18);
                            i3 = i17 + i18;
                            if (arePaddingBitsZero(signature2.proofs[i15].msgs, this.numRounds * 3 * this.numSboxes)) {
                                System.arraycopy(bArr, i3, signature2.proofs[i15].C, 0, this.digestSizeBytes);
                                i14 = i3 + this.digestSizeBytes;
                            } else {
                                logger = LOG;
                                str = "failed while deserializing msgs bits";
                            }
                        } else {
                            logger = LOG;
                            str = "failed while deserializing aux bits";
                        }
                    } else {
                        System.arraycopy(bArr, i16, signature2.proofs[i15].input, 0, this.stateSizeBytes);
                        int i19 = i16 + this.stateSizeBytes;
                        int i110 = this.andSizeBytes;
                        System.arraycopy(bArr, i19, signature2.proofs[i15].msgs, 0, i110);
                        i3 = i19 + i110;
                        if (arePaddingBitsZero(signature2.proofs[i15].msgs, this.numRounds * 3 * this.numSboxes)) {
                            logger = LOG;
                            str = "failed while deserializing msgs bits";
                        } else {
                            System.arraycopy(bArr, i3, signature2.proofs[i15].C, 0, this.digestSizeBytes);
                            i14 = i3 + this.digestSizeBytes;
                        }
                    }
                }
            }
            return 0;
        }
        logger = LOG;
        str = "sigLen = " + i + ", expected bytesRequired = " + i9;
        logger.fine(str);
        return -1;
    }

    private void expandChallengeHash(byte[] bArr, int[] iArr, int[] iArr2) {
        int iCeil_log2 = Utils.ceil_log2(this.numMPCRounds);
        int iCeil_log3 = Utils.ceil_log2(this.numMPCParties);
        int[] iArr3 = new int[(this.digestSizeBytes * 8) / Math.min(iCeil_log2, iCeil_log3)];
        byte[] bArr2 = new byte[64];
        System.arraycopy(bArr, 0, bArr2, 0, this.digestSizeBytes);
        int iAppendUnique = 0;
        while (iAppendUnique < this.numOpenedRounds) {
            int iBitsToChunks = bitsToChunks(iCeil_log2, bArr2, this.digestSizeBytes, iArr3);
            for (int i = 0; i < iBitsToChunks; i++) {
                int i2 = iArr3[i];
                if (i2 < this.numMPCRounds) {
                    iAppendUnique = appendUnique(iArr, i2, iAppendUnique);
                }
                if (iAppendUnique == this.numOpenedRounds) {
                    break;
                }
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr2, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        }
        int i3 = 0;
        while (i3 < this.numOpenedRounds) {
            int iBitsToChunks2 = bitsToChunks(iCeil_log3, bArr2, this.digestSizeBytes, iArr3);
            for (int i4 = 0; i4 < iBitsToChunks2; i4++) {
                int i5 = iArr3[i4];
                if (i5 < this.numMPCParties) {
                    iArr2[i3] = i5;
                    i3++;
                }
                if (i3 == this.numOpenedRounds) {
                    break;
                }
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr2, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr2, 0, this.digestSizeBytes);
        }
    }

    public static int extend(int i) {
        return ~(i - 1);
    }

    private void getAuxBits(byte[] bArr, Tape tape) {
        byte[] bArr2 = tape.tapes[this.numMPCParties - 1];
        int i = this.stateSizeBits;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.numRounds; i4++) {
            i2 += i;
            int i5 = 0;
            while (i5 < i) {
                Utils.setBit(bArr, i3, Utils.getBit(bArr2, i2));
                i5++;
                i3++;
                i2++;
            }
        }
    }

    private int[] getMissingLeavesList(int[] iArr) {
        int[] iArr2 = new int[this.numMPCRounds - this.numOpenedRounds];
        int i = 0;
        for (int i2 = 0; i2 < this.numMPCRounds; i2++) {
            if (!contains(iArr, this.numOpenedRounds, i2)) {
                iArr2[i] = i2;
                i++;
            }
        }
        return iArr2;
    }

    private void implH3(int[] iArr, int[] iArr2, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        byte[] bArr6 = new byte[this.digestSizeBytes];
        bArr2[Utils.numBytes(this.numMPCRounds * 2) - 1] = 0;
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.digest.update(bArr[i][i2], 0, this.digestSizeBytes);
            }
        }
        if (this.transform == 1) {
            for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
                int i4 = 0;
                while (i4 < 3) {
                    this.digest.update(bArr5[i3][i4], 0, i4 == 2 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
                    i4++;
                }
            }
        }
        this.digest.update(Pack.intToLittleEndian(iArr), 0, this.stateSizeBytes);
        this.digest.update(Pack.intToLittleEndian(iArr2), 0, this.stateSizeBytes);
        this.digest.update(bArr3, 0, 32);
        this.digest.update(bArr4, 0, bArr4.length);
        this.digest.doFinal(bArr6, 0, this.digestSizeBytes);
        boolean z = true;
        int i5 = 0;
        while (z) {
            for (int i6 = 0; i6 < this.digestSizeBytes; i6++) {
                byte b = bArr6[i6];
                for (int i7 = 0; i7 < 8; i7 += 2) {
                    int i8 = (b >>> (6 - i7)) & 3;
                    if (i8 < 3) {
                        setChallenge(bArr2, i5, i8);
                        i5++;
                        if (i5 == this.numMPCRounds) {
                            z = false;
                            break;
                        }
                    }
                }
                if (!z) {
                    break;
                }
            }
            if (!z) {
                return;
            }
            this.digest.update((byte) 1);
            this.digest.update(bArr6, 0, this.digestSizeBytes);
            this.digest.doFinal(bArr6, 0, this.digestSizeBytes);
        }
    }

    public static int indexOf(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    public static boolean is_picnic3(int i) {
        return i == 7 || i == 8 || i == 9;
    }

    private void mpc_AND(int[] iArr, int[] iArr2, int[] iArr3, Tape tape, View[] viewArr) {
        byte bit = Utils.getBit(tape.tapes[0], tape.pos);
        byte bit2 = Utils.getBit(tape.tapes[1], tape.pos);
        byte bit3 = Utils.getBit(tape.tapes[2], tape.pos);
        int i = iArr[0];
        int i2 = iArr2[1];
        int i3 = iArr[1];
        int i4 = iArr2[0];
        int i5 = (((i & i4) ^ ((i & i2) ^ (i3 & i4))) ^ bit) ^ bit2;
        iArr3[0] = i5;
        int i6 = iArr2[2];
        int i7 = iArr[2];
        iArr3[1] = (bit2 ^ ((i2 & i3) ^ ((i3 & i6) ^ (i7 & i2)))) ^ bit3;
        iArr3[2] = ((((iArr[0] & i6) ^ (iArr2[0] & i7)) ^ (i7 & i6)) ^ bit3) ^ bit;
        Utils.setBit(viewArr[0].communicatedBits, tape.pos, (byte) i5);
        Utils.setBit(viewArr[1].communicatedBits, tape.pos, (byte) iArr3[1]);
        Utils.setBit(viewArr[2].communicatedBits, tape.pos, (byte) iArr3[2]);
        tape.pos++;
    }

    private void mpc_LowMC(Tape tape, View[] viewArr, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i = this.stateSizeWords;
        mpc_xor_constant(iArr2, i * 3, iArr, 0, i);
        KMatricesWithPointer kMatricesWithPointerKMatrix = this.lowmcConstants.KMatrix(this, 0);
        for (int i2 = 0; i2 < 3; i2++) {
            matrix_mul_offset(iArr2, i2 * this.stateSizeWords, viewArr[i2].inputShare, 0, kMatricesWithPointerKMatrix.getData(), kMatricesWithPointerKMatrix.getMatrixPointer());
        }
        int[] iArr3 = iArr2;
        mpc_xor(iArr3, iArr3, 3);
        for (int i3 = 1; i3 <= this.numRounds; i3++) {
            KMatricesWithPointer kMatricesWithPointerKMatrix2 = this.lowmcConstants.KMatrix(this, i3);
            for (int i4 = 0; i4 < 3; i4++) {
                matrix_mul_offset(iArr3, this.stateSizeWords * i4, viewArr[i4].inputShare, 0, kMatricesWithPointerKMatrix2.getData(), kMatricesWithPointerKMatrix2.getMatrixPointer());
            }
            mpc_substitution(iArr3, tape, viewArr);
            int i5 = i3 - 1;
            KMatricesWithPointer kMatricesWithPointerLMatrix = this.lowmcConstants.LMatrix(this, i5);
            int i6 = this.stateSizeWords;
            mpc_matrix_mul(iArr3, i6 * 3, iArr2, i6 * 3, kMatricesWithPointerLMatrix.getData(), kMatricesWithPointerLMatrix.getMatrixPointer(), 3);
            KMatricesWithPointer kMatricesWithPointerRConstant = this.lowmcConstants.RConstant(this, i5);
            iArr3 = iArr2;
            mpc_xor_constant(iArr3, this.stateSizeWords * 3, kMatricesWithPointerRConstant.getData(), kMatricesWithPointerRConstant.getMatrixPointer(), this.stateSizeWords);
            mpc_xor(iArr3, iArr3, 3);
        }
        for (int i7 = 0; i7 < 3; i7++) {
            int i8 = this.stateSizeWords;
            System.arraycopy(iArr3, (i7 + 3) * i8, viewArr[i7].outputShare, 0, i8);
        }
    }

    private void mpc_matrix_mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = this.stateSizeWords;
            matrix_mul_offset(iArr, (i5 * i6) + i, iArr2, i2 + (i6 * i5), iArr3, i3);
        }
    }

    private void mpc_sbox(int[] iArr, int[] iArr2, Tape tape, Msg msg) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = iArr2[i2];
            int i4 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i4);
            int i5 = iArr2[i4];
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            int i6 = iArr2[i];
            int iMpc_AND = mpc_AND(bitFromWordArray, bitFromWordArray2, i3, i5, tape, msg);
            int iMpc_AND2 = mpc_AND(bitFromWordArray2, bitFromWordArray3, i5, i6, tape, msg);
            int iMpc_AND3 = mpc_AND(bitFromWordArray3, bitFromWordArray, i6, i3, tape, msg);
            int i7 = bitFromWordArray ^ iMpc_AND2;
            int i8 = bitFromWordArray ^ bitFromWordArray2;
            Utils.setBitInWordArray(iArr, i2, i7);
            Utils.setBitInWordArray(iArr, i4, iMpc_AND3 ^ i8);
            Utils.setBitInWordArray(iArr, i, (i8 ^ bitFromWordArray3) ^ iMpc_AND);
        }
    }

    private void mpc_substitution(int[] iArr, Tape tape, View[] viewArr) {
        int[] iArr2 = new int[3];
        int[] iArr3 = new int[3];
        int[] iArr4 = new int[3];
        int[] iArr5 = new int[3];
        int[] iArr6 = new int[3];
        int[] iArr7 = new int[3];
        int i = 0;
        while (i < this.numSboxes * 3) {
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = ((i2 + 3) * this.stateSizeWords * 32) + i;
                iArr2[i2] = Utils.getBitFromWordArray(iArr, i3 + 2);
                iArr3[i2] = Utils.getBitFromWordArray(iArr, i3 + 1);
                iArr4[i2] = Utils.getBitFromWordArray(iArr, i3);
            }
            mpc_AND(iArr2, iArr3, iArr5, tape, viewArr);
            int[] iArr8 = iArr4;
            int[] iArr9 = iArr2;
            int[] iArr10 = iArr3;
            int[] iArr11 = iArr6;
            int[] iArr12 = iArr5;
            mpc_AND(iArr10, iArr8, iArr11, tape, viewArr);
            int[] iArr13 = iArr7;
            mpc_AND(iArr8, iArr9, iArr13, tape, viewArr);
            iArr2 = iArr9;
            for (int i4 = 0; i4 < 3; i4++) {
                int i5 = ((i4 + 3) * this.stateSizeWords * 32) + i;
                Utils.setBitInWordArray(iArr, i5 + 2, iArr2[i4] ^ iArr11[i4]);
                Utils.setBitInWordArray(iArr, i5 + 1, (iArr2[i4] ^ iArr10[i4]) ^ iArr13[i4]);
                Utils.setBitInWordArray(iArr, i5, ((iArr2[i4] ^ iArr10[i4]) ^ iArr8[i4]) ^ iArr12[i4]);
            }
            i += 3;
            iArr4 = iArr8;
            iArr3 = iArr10;
            iArr7 = iArr13;
            iArr5 = iArr12;
            iArr6 = iArr11;
        }
    }

    private void mpc_xor(int[] iArr, int[] iArr2, int i) {
        int i2 = this.stateSizeWords * i;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (this.stateSizeWords * i) + i3;
            iArr[i4] = iArr[i4] ^ iArr2[i3];
        }
    }

    private void mpc_xor_constant(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 + i;
            iArr[i5] = iArr[i5] ^ iArr2[i4 + i2];
        }
    }

    private void mpc_xor_constant_verify(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4;
        if (i3 == 0) {
            i4 = this.stateSizeWords * 2;
        } else if (i3 != 2) {
            return;
        } else {
            i4 = this.stateSizeWords * 3;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 + i4;
            iArr[i6] = iArr[i6] ^ iArr2[i5 + i];
        }
    }

    private void picnic_keygen(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        int[] iArr = new int[bArr3.length / 4];
        int[] iArr2 = new int[bArr.length / 4];
        int[] iArr3 = new int[bArr2.length / 4];
        secureRandom.nextBytes(bArr3);
        Pack.littleEndianToInt(bArr3, 0, iArr);
        Utils.zeroTrailingBits(iArr, this.stateSizeBits);
        secureRandom.nextBytes(bArr);
        Pack.littleEndianToInt(bArr, 0, iArr2);
        Utils.zeroTrailingBits(iArr2, this.stateSizeBits);
        LowMCEnc(iArr2, iArr3, iArr);
        Pack.intToLittleEndian(iArr, bArr3, 0);
        Pack.intToLittleEndian(iArr2, bArr, 0);
        Pack.intToLittleEndian(iArr3, bArr2, 0);
    }

    private void picnic_read_public_key(int[] iArr, int[] iArr2, byte[] bArr) {
        int i = this.stateSizeBytes;
        int i2 = i + 1;
        int i3 = i / 4;
        Pack.littleEndianToInt(bArr, 1, iArr, 0, i3);
        Pack.littleEndianToInt(bArr, i2, iArr2, 0, i3);
        if (i3 < this.stateSizeWords) {
            int i4 = i3 * 4;
            int i5 = this.stateSizeBytes - i4;
            iArr[i3] = Pack.littleEndianToInt_Low(bArr, i4 + 1, i5);
            iArr2[i3] = Pack.littleEndianToInt_Low(bArr, i2 + i4, i5);
        }
    }

    private boolean picnic_sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        PicnicEngine picnicEngine;
        int iSerializeSignature2;
        int i = this.stateSizeWords;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        int[] iArr3 = new int[i];
        int i2 = this.stateSizeBytes;
        int i3 = i2 + 1;
        int i4 = (i2 * 2) + 1;
        int i5 = i2 / 4;
        Pack.littleEndianToInt(bArr, 1, iArr, 0, i5);
        Pack.littleEndianToInt(bArr, i3, iArr2, 0, i5);
        Pack.littleEndianToInt(bArr, i4, iArr3, 0, i5);
        if (i5 < this.stateSizeWords) {
            int i6 = i5 * 4;
            int i7 = this.stateSizeBytes - i6;
            iArr[i5] = Pack.littleEndianToInt_Low(bArr, i6 + 1, i7);
            iArr2[i5] = Pack.littleEndianToInt_Low(bArr, i3 + i6, i7);
            iArr3[i5] = Pack.littleEndianToInt_Low(bArr, i4 + i6, i7);
        }
        if (is_picnic3(this.parameters)) {
            picnicEngine = this;
            Signature2 signature2 = new Signature2(picnicEngine);
            if (!picnicEngine.sign_picnic3(iArr, iArr2, iArr3, bArr2, signature2)) {
                LOG.fine("Failed to create signature");
                return false;
            }
            iSerializeSignature2 = picnicEngine.serializeSignature2(signature2, bArr3, bArr2.length + 4);
            if (iSerializeSignature2 < 0) {
                LOG.fine("Failed to serialize signature");
                return false;
            }
        } else {
            Signature signature = new Signature(this);
            picnicEngine = this;
            if (picnicEngine.sign_picnic1(iArr, iArr2, iArr3, bArr2, signature) != 0) {
                LOG.fine("Failed to create signature");
                return false;
            }
            iSerializeSignature2 = picnicEngine.serializeSignature(signature, bArr3, bArr2.length + 4);
            if (iSerializeSignature2 < 0) {
                LOG.fine("Failed to serialize signature");
                return false;
            }
        }
        picnicEngine.signatureLength = iSerializeSignature2;
        Pack.intToLittleEndian(iSerializeSignature2, bArr3, 0);
        return true;
    }

    private int picnic_verify(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        Logger logger;
        String str;
        int i2 = this.stateSizeWords;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        picnic_read_public_key(iArr, iArr2, bArr);
        if (is_picnic3(this.parameters)) {
            Signature2 signature2 = new Signature2(this);
            if (deserializeSignature2(signature2, bArr3, i, bArr2.length + 4) == 0) {
                return verify_picnic3(signature2, iArr, iArr2, bArr2);
            }
            logger = LOG;
            str = "Error couldn't deserialize signature (2)!";
        } else {
            Signature signature = new Signature(this);
            if (deserializeSignature(signature, bArr3, i, bArr2.length + 4) == 0) {
                return verify(signature, iArr, iArr2, bArr2);
            }
            logger = LOG;
            str = "Error couldn't deserialize signature!";
        }
        logger.fine(str);
        return -1;
    }

    private int picnic_write_private_key(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int i = this.stateSizeBytes;
        int i2 = (i * 3) + 1;
        if (bArr4.length < i2) {
            LOG.fine("Failed writing private key!");
            return -1;
        }
        bArr4[0] = (byte) this.parameters;
        System.arraycopy(bArr, 0, bArr4, 1, i);
        int i3 = this.stateSizeBytes;
        System.arraycopy(bArr2, 0, bArr4, i3 + 1, i3);
        int i4 = this.stateSizeBytes;
        System.arraycopy(bArr3, 0, bArr4, (i4 * 2) + 1, i4);
        return i2;
    }

    private int picnic_write_public_key(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.stateSizeBytes;
        int i2 = (i * 2) + 1;
        if (bArr3.length < i2) {
            LOG.fine("Failed writing public key!");
            return -1;
        }
        bArr3[0] = (byte) this.parameters;
        System.arraycopy(bArr, 0, bArr3, 1, i);
        int i3 = this.stateSizeBytes;
        System.arraycopy(bArr2, 0, bArr3, i3 + 1, i3);
        return i2;
    }

    private int serializeSignature2(Signature2 signature2, byte[] bArr, int i) {
        int i2 = this.digestSizeBytes + 32 + signature2.iSeedInfoLen + signature2.cvInfoLen;
        for (int i3 = 0; i3 < this.numMPCRounds; i3++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i3)) {
                int i4 = signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i3)];
                int i5 = i2 + signature2.proofs[i3].seedInfoLen;
                if (i4 != this.numMPCParties - 1) {
                    i5 += this.andSizeBytes;
                }
                i2 = i5 + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes;
            }
        }
        if (bArr.length < i2) {
            return -1;
        }
        System.arraycopy(signature2.challengeHash, 0, bArr, i, this.digestSizeBytes);
        int i6 = this.digestSizeBytes + i;
        System.arraycopy(signature2.salt, 0, bArr, i6, 32);
        int i7 = i6 + 32;
        System.arraycopy(signature2.iSeedInfo, 0, bArr, i7, signature2.iSeedInfoLen);
        int i8 = i7 + signature2.iSeedInfoLen;
        System.arraycopy(signature2.cvInfo, 0, bArr, i8, signature2.cvInfoLen);
        int i9 = i8 + signature2.cvInfoLen;
        for (int i10 = 0; i10 < this.numMPCRounds; i10++) {
            if (contains(signature2.challengeC, this.numOpenedRounds, i10)) {
                Signature2.Proof2 proof2 = signature2.proofs[i10];
                System.arraycopy(proof2.seedInfo, 0, bArr, i9, proof2.seedInfoLen);
                int i11 = i9 + signature2.proofs[i10].seedInfoLen;
                if (signature2.challengeP[indexOf(signature2.challengeC, this.numOpenedRounds, i10)] != this.numMPCParties - 1) {
                    System.arraycopy(signature2.proofs[i10].aux, 0, bArr, i11, this.andSizeBytes);
                    i11 += this.andSizeBytes;
                }
                System.arraycopy(signature2.proofs[i10].input, 0, bArr, i11, this.stateSizeBytes);
                int i12 = i11 + this.stateSizeBytes;
                System.arraycopy(signature2.proofs[i10].msgs, 0, bArr, i12, this.andSizeBytes);
                int i13 = i12 + this.andSizeBytes;
                System.arraycopy(signature2.proofs[i10].C, 0, bArr, i13, this.digestSizeBytes);
                i9 = i13 + this.digestSizeBytes;
            }
        }
        return i9 - i;
    }

    private void setChallenge(byte[] bArr, int i, int i2) {
        int i3 = i * 2;
        Utils.setBit(bArr, i3, (byte) (i2 & 1));
        Utils.setBit(bArr, i3 + 1, (byte) ((i2 >>> 1) & 1));
    }

    private int sign_picnic1(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr, Signature signature) {
        int i;
        int i2;
        byte[] bArr2;
        PicnicEngine picnicEngine = this;
        int i3 = 2;
        int i4 = 1;
        int i5 = 0;
        View[][] viewArr = (View[][]) Array.newInstance((Class<?>) View.class, picnicEngine.numMPCRounds, 3);
        int[] iArr4 = {picnicEngine.numMPCRounds, picnicEngine.numMPCParties, picnicEngine.digestSizeBytes};
        Class cls = Byte.TYPE;
        byte[][][] bArr3 = (byte[][][]) Array.newInstance((Class<?>) cls, iArr4);
        byte[][][] bArr4 = (byte[][][]) Array.newInstance((Class<?>) cls, picnicEngine.numMPCRounds, 3, picnicEngine.UnruhGWithInputBytes);
        byte[] bArrComputeSeeds = computeSeeds(iArr, iArr2, iArr3, bArr);
        int i6 = picnicEngine.numMPCParties * picnicEngine.seedSizeBytes;
        System.arraycopy(bArrComputeSeeds, picnicEngine.numMPCRounds * i6, signature.salt, 0, 32);
        Tape tape = new Tape(picnicEngine);
        int i7 = picnicEngine.stateSizeBytes;
        int iMax = Math.max(i7 * 9, i7 + picnicEngine.andSizeBytes);
        byte[] bArr5 = new byte[iMax];
        int i8 = 0;
        while (i8 < picnicEngine.numMPCRounds) {
            viewArr[i8][i5] = new View(picnicEngine);
            viewArr[i8][i4] = new View(picnicEngine);
            viewArr[i8][i3] = new View(picnicEngine);
            int i9 = i5;
            while (true) {
                i = picnicEngine.seedSizeBytes;
                i2 = i4;
                if (i9 < i3) {
                    int i10 = (i6 * i8) + (i * i9);
                    int i11 = iMax;
                    int i12 = i3;
                    Tape tape2 = tape;
                    byte[] bArr6 = bArrComputeSeeds;
                    byte[] bArr7 = bArr5;
                    if (!picnicEngine.createRandomTape(bArr6, i10, signature.salt, i8, i9, bArr5, picnicEngine.stateSizeBytes + picnicEngine.andSizeBytes)) {
                        LOG.fine("createRandomTape failed");
                        return -1;
                    }
                    int[] iArr5 = viewArr[i8][i9].inputShare;
                    Pack.littleEndianToInt(bArr7, 0, iArr5);
                    Utils.zeroTrailingBits(iArr5, picnicEngine.stateSizeBits);
                    System.arraycopy(bArr7, picnicEngine.stateSizeBytes, tape2.tapes[i9], 0, picnicEngine.andSizeBytes);
                    i9++;
                    bArrComputeSeeds = bArr6;
                    bArr5 = bArr7;
                    tape = tape2;
                    iMax = i11;
                    i4 = i2;
                    i3 = i12;
                }
            }
            int i13 = iMax;
            int i14 = i3;
            Tape tape3 = tape;
            byte[] bArr8 = bArrComputeSeeds;
            int i15 = i6 * i8;
            byte[] bArr9 = bArr5;
            View[][] viewArr2 = viewArr;
            int i16 = i8;
            if (!picnicEngine.createRandomTape(bArr8, (i * 2) + i15, signature.salt, i8, 2, tape3.tapes[i14], picnicEngine.andSizeBytes)) {
                LOG.fine("createRandomTape failed");
                return -1;
            }
            View[] viewArr3 = viewArr2[i16];
            picnicEngine.xor_three(viewArr3[i14].inputShare, iArr, viewArr3[0].inputShare, viewArr3[i2].inputShare);
            tape3.pos = 0;
            int[] iArrLittleEndianToInt = Pack.littleEndianToInt(bArr9, 0, i13 / 4);
            picnicEngine.mpc_LowMC(tape3, viewArr2[i16], iArr3, iArrLittleEndianToInt);
            Pack.intToLittleEndian(iArrLittleEndianToInt, bArr9, 0);
            int[] iArr6 = new int[16];
            View[] viewArr4 = viewArr2[i16];
            picnicEngine.xor_three(iArr6, viewArr4[0].outputShare, viewArr4[i2].outputShare, viewArr4[i14].outputShare);
            if (!subarrayEquals(iArr6, iArr2, picnicEngine.stateSizeWords)) {
                LOG.fine("Simulation failed; output does not match public key (round = " + i16 + ")");
                return -1;
            }
            picnicEngine.Commit(bArr8, i15, viewArr2[i16][0], bArr3[i16][0]);
            picnicEngine.Commit(bArr8, picnicEngine.seedSizeBytes + i15, viewArr2[i16][i2], bArr3[i16][i2]);
            picnicEngine.Commit(bArr8, (picnicEngine.seedSizeBytes * 2) + i15, viewArr2[i16][i14], bArr3[i16][i14]);
            if (picnicEngine.transform == i2) {
                picnicEngine.G(0, bArr8, i15, viewArr2[i16][0], bArr4[i16][0]);
                picnicEngine.G(1, bArr8, i15 + picnicEngine.seedSizeBytes, viewArr2[i16][i2], bArr4[i16][i2]);
                picnicEngine.G(2, bArr8, i15 + (picnicEngine.seedSizeBytes * 2), viewArr2[i16][i14], bArr4[i16][i14]);
                bArr2 = bArr8;
            } else {
                bArr2 = bArr8;
            }
            i8 = i16 + 1;
            picnicEngine = this;
            tape = tape3;
            bArr5 = bArr9;
            bArrComputeSeeds = bArr2;
            viewArr = viewArr2;
            i3 = i14;
            iMax = i13;
            i4 = 1;
            i5 = 0;
        }
        View[][] viewArr5 = viewArr;
        byte[] bArr10 = bArrComputeSeeds;
        PicnicEngine picnicEngine2 = this;
        picnicEngine2.H3(iArr2, iArr3, viewArr5, bArr3, signature.challengeBits, signature.salt, bArr, bArr4);
        int i17 = 0;
        while (i17 < picnicEngine2.numMPCRounds) {
            byte[] bArr11 = bArr10;
            picnicEngine2.prove(signature.proofs[i17], picnicEngine2.getChallenge(signature.challengeBits, i17), bArr11, i6 * i17, viewArr5[i17], bArr3[i17], picnicEngine2.transform != 1 ? null : bArr4[i17]);
            i17++;
            picnicEngine2 = this;
            bArr10 = bArr11;
        }
        return 0;
    }

    private boolean sign_picnic3(int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr, Signature2 signature2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = this.seedSizeBytes + 32;
        byte[] bArr2 = new byte[i6];
        computeSaltAndRootSeed(bArr2, iArr, iArr2, iArr3, bArr);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr2, 32, i6);
        signature2.salt = Arrays.copyOfRange(bArr2, 0, 32);
        Tree tree = new Tree(this, this.numMPCRounds, this.seedSizeBytes);
        tree.generateSeeds(bArrCopyOfRange, signature2.salt, 0);
        byte[][] leaves = tree.getLeaves();
        int leavesOffset = tree.getLeavesOffset();
        int i7 = this.numMPCRounds;
        Tape[] tapeArr = new Tape[i7];
        Tree[] treeArr = new Tree[i7];
        int i8 = 0;
        while (true) {
            i = this.numMPCRounds;
            if (i8 >= i) {
                break;
            }
            tapeArr[i8] = new Tape(this);
            Tree tree2 = new Tree(this, this.numMPCParties, this.seedSizeBytes);
            treeArr[i8] = tree2;
            tree2.generateSeeds(leaves[i8 + leavesOffset], signature2.salt, i8);
            createRandomTapes(tapeArr[i8], treeArr[i8].getLeaves(), treeArr[i8].getLeavesOffset(), signature2.salt, i8);
            i8++;
        }
        int[] iArr4 = {i, this.stateSizeWords * 4};
        Class cls = Byte.TYPE;
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) cls, iArr4);
        byte[] bArr4 = new byte[MAX_AUX_BYTES];
        int i9 = 0;
        while (true) {
            i2 = this.numMPCRounds;
            if (i9 >= i2) {
                break;
            }
            tapeArr[i9].computeAuxTape(bArr3[i9]);
            i9++;
        }
        byte[][][] bArr5 = (byte[][][]) Array.newInstance((Class<?>) cls, i2, this.numMPCParties, this.digestSizeBytes);
        int i10 = 0;
        while (true) {
            i3 = this.numMPCRounds;
            if (i10 >= i3) {
                break;
            }
            int i11 = 0;
            while (true) {
                i5 = this.numMPCParties;
                if (i11 < i5 - 1) {
                    commit(bArr5[i10][i11], treeArr[i10].getLeaf(i11), null, signature2.salt, i10, i11);
                    i11++;
                }
            }
            int i12 = i5 - 1;
            getAuxBits(bArr4, tapeArr[i10]);
            commit(bArr5[i10][i12], treeArr[i10].getLeaf(i12), bArr4, signature2.salt, i10, i12);
            i10++;
        }
        Msg[] msgArr = new Msg[i3];
        int[] iArr5 = new int[this.stateSizeBits];
        int i13 = 0;
        while (true) {
            int i14 = this.numMPCRounds;
            if (i13 >= i14) {
                byte[][] bArr6 = (byte[][]) Array.newInstance((Class<?>) cls, i14, this.digestSizeBytes);
                byte[][] bArr7 = (byte[][]) Array.newInstance((Class<?>) cls, this.numMPCRounds, this.digestSizeBytes);
                for (int i15 = 0; i15 < this.numMPCRounds; i15++) {
                    commit_h(bArr6[i15], bArr5[i15]);
                    commit_v(bArr7[i15], bArr3[i15], msgArr[i15]);
                }
                Tree tree3 = new Tree(this, this.numMPCRounds, this.digestSizeBytes);
                tree3.buildMerkleTree(bArr7, signature2.salt);
                int i16 = this.numOpenedRounds;
                int[] iArr6 = new int[i16];
                signature2.challengeC = iArr6;
                int[] iArr7 = new int[i16];
                signature2.challengeP = iArr7;
                byte[] bArr8 = new byte[this.digestSizeBytes];
                signature2.challengeHash = bArr8;
                int i17 = 0;
                HCP(bArr8, iArr6, iArr7, bArr6, tree3.nodes[0], signature2.salt, iArr2, iArr3, bArr);
                int[] iArr8 = new int[1];
                signature2.cvInfo = tree3.openMerkleTree(getMissingLeavesList(signature2.challengeC), this.numMPCRounds - this.numOpenedRounds, iArr8);
                signature2.cvInfoLen = iArr8[0];
                int i18 = this.numMPCRounds;
                int i19 = this.seedSizeBytes;
                byte[] bArr9 = new byte[i18 * i19];
                signature2.iSeedInfo = bArr9;
                signature2.iSeedInfoLen = tree.revealSeeds(signature2.challengeC, this.numOpenedRounds, bArr9, i18 * i19);
                signature2.proofs = new Signature2.Proof2[this.numMPCRounds];
                int i20 = 0;
                while (i20 < this.numMPCRounds) {
                    if (contains(signature2.challengeC, this.numOpenedRounds, i20)) {
                        signature2.proofs[i20] = new Signature2.Proof2(this);
                        int iIndexOf = indexOf(signature2.challengeC, this.numOpenedRounds, i20);
                        int[] iArr9 = {signature2.challengeP[iIndexOf]};
                        Signature2.Proof2 proof2 = signature2.proofs[i20];
                        int i21 = this.numMPCParties;
                        int i22 = this.seedSizeBytes;
                        byte[] bArr10 = new byte[i21 * i22];
                        proof2.seedInfo = bArr10;
                        proof2.seedInfoLen = treeArr[i20].revealSeeds(iArr9, 1, bArr10, i21 * i22);
                        if (signature2.challengeP[iIndexOf] != this.numMPCParties - 1) {
                            getAuxBits(signature2.proofs[i20].aux, tapeArr[i20]);
                        }
                        i4 = i17;
                        System.arraycopy(bArr3[i20], i4, signature2.proofs[i20].input, i4, this.stateSizeBytes);
                        System.arraycopy(msgArr[i20].msgs[signature2.challengeP[iIndexOf]], i4, signature2.proofs[i20].msgs, i4, this.andSizeBytes);
                        System.arraycopy(bArr5[i20][signature2.challengeP[iIndexOf]], i4, signature2.proofs[i20].C, i4, this.digestSizeBytes);
                    } else {
                        i4 = i17;
                    }
                    i20++;
                    i17 = i4;
                }
                return true;
            }
            msgArr[i13] = new Msg(this);
            int[] iArrLittleEndianToInt = Pack.littleEndianToInt(bArr3[i13], 0, this.stateSizeWords);
            xor_array(iArrLittleEndianToInt, iArrLittleEndianToInt, iArr, 0);
            int i23 = i13;
            if (simulateOnline(iArrLittleEndianToInt, tapeArr[i23], iArr5, msgArr[i23], iArr3, iArr2) != 0) {
                LOG.fine("MPC simulation failed, aborting signature");
                return false;
            }
            Pack.intToLittleEndian(iArrLittleEndianToInt, bArr3[i23], 0);
            i13 = i23 + 1;
        }
    }

    private int simulateOnline(int[] iArr, Tape tape, int[] iArr2, Msg msg, int[] iArr3, int[] iArr4) {
        int[] iArr5 = new int[16];
        int[] iArr6 = new int[16];
        KMatricesWithPointer kMatricesWithPointerKMatrix = this.lowmcConstants.KMatrix(this, 0);
        matrix_mul(iArr5, iArr, kMatricesWithPointerKMatrix.getData(), kMatricesWithPointerKMatrix.getMatrixPointer());
        xor_array(iArr6, iArr5, iArr3, 0);
        for (int i = 1; i <= this.numRounds; i++) {
            tapesToWords(iArr2, tape);
            mpc_sbox(iArr6, iArr2, tape, msg);
            int i2 = i - 1;
            KMatricesWithPointer kMatricesWithPointerLMatrix = this.lowmcConstants.LMatrix(this, i2);
            matrix_mul(iArr6, iArr6, kMatricesWithPointerLMatrix.getData(), kMatricesWithPointerLMatrix.getMatrixPointer());
            KMatricesWithPointer kMatricesWithPointerRConstant = this.lowmcConstants.RConstant(this, i2);
            xor_array(iArr6, iArr6, kMatricesWithPointerRConstant.getData(), kMatricesWithPointerRConstant.getMatrixPointer());
            KMatricesWithPointer kMatricesWithPointerKMatrix2 = this.lowmcConstants.KMatrix(this, i);
            matrix_mul(iArr5, iArr, kMatricesWithPointerKMatrix2.getData(), kMatricesWithPointerKMatrix2.getMatrixPointer());
            xor_array(iArr6, iArr5, iArr6, 0);
        }
        return !subarrayEquals(iArr6, iArr4, this.stateSizeWords) ? -1 : 0;
    }

    private static boolean subarrayEquals(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length < i || bArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void substitution(int[] iArr) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i3);
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            Utils.setBitInWordArray(iArr, i2, (bitFromWordArray2 & bitFromWordArray3) ^ bitFromWordArray);
            int i4 = bitFromWordArray ^ bitFromWordArray2;
            Utils.setBitInWordArray(iArr, i3, (bitFromWordArray & bitFromWordArray3) ^ i4);
            Utils.setBitInWordArray(iArr, i, (i4 ^ bitFromWordArray3) ^ (bitFromWordArray & bitFromWordArray2));
        }
    }

    private void tapesToWords(int[] iArr, Tape tape) {
        for (int i = 0; i < this.stateSizeBits; i++) {
            iArr[i] = tape.tapesToWord();
        }
    }

    private void updateDigest(int[] iArr, byte[] bArr) {
        Pack.intToLittleEndian(iArr, bArr, 0);
        this.digest.update(bArr, 0, this.stateSizeBytes);
    }

    private int verify(Signature signature, int[] iArr, int[] iArr2, byte[] bArr) {
        int i;
        int i2;
        PicnicEngine picnicEngine = this;
        char c = 3;
        char c2 = 2;
        int[] iArr3 = {picnicEngine.numMPCRounds, picnicEngine.numMPCParties, picnicEngine.digestSizeBytes};
        Class cls = Byte.TYPE;
        byte[][][] bArr2 = (byte[][][]) Array.newInstance((Class<?>) cls, iArr3);
        byte[][][] bArr3 = (byte[][][]) Array.newInstance((Class<?>) cls, picnicEngine.numMPCRounds, 3, picnicEngine.UnruhGWithInputBytes);
        int[][][] iArr4 = (int[][][]) Array.newInstance((Class<?>) Integer.TYPE, picnicEngine.numMPCRounds, 3, picnicEngine.stateSizeBytes);
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr4 = signature.challengeBits;
        int i3 = picnicEngine.stateSizeBytes;
        byte[] bArr5 = new byte[Math.max(i3 * 6, i3 + picnicEngine.andSizeBytes)];
        Tape tape = new Tape(picnicEngine);
        int i4 = picnicEngine.numMPCRounds;
        View[] viewArr = new View[i4];
        View[] viewArr2 = new View[i4];
        int i5 = 0;
        while (true) {
            int i6 = picnicEngine.numMPCRounds;
            if (i5 >= i6) {
                byte[] bArr6 = new byte[Utils.numBytes(i6 * 2)];
                picnicEngine.H3(iArr, iArr2, iArr4, bArr2, bArr6, signature.salt, bArr, bArr3);
                if (!subarrayEquals(bArr4, bArr6, Utils.numBytes(picnicEngine.numMPCRounds * 2))) {
                    break;
                }
                return 0;
            }
            viewArr[i5] = new View(picnicEngine);
            View[] viewArr3 = viewArr2;
            View view = new View(picnicEngine);
            viewArr3[i5] = view;
            Signature.Proof[] proofArr2 = proofArr;
            View[] viewArr4 = viewArr;
            char c3 = c;
            byte[] bArr7 = bArr4;
            char c4 = c2;
            if (!picnicEngine.verifyProof(proofArr2[i5], viewArr[i5], view, picnicEngine.getChallenge(bArr4, i5), signature.salt, i5, bArr5, iArr2, tape)) {
                break;
            }
            int challenge = picnicEngine.getChallenge(bArr7, i5);
            picnicEngine.Commit(proofArr2[i5].seed1, 0, viewArr4[i5], bArr2[i5][challenge]);
            int i7 = (challenge + 1) % 3;
            picnicEngine.Commit(proofArr2[i5].seed2, 0, viewArr3[i5], bArr2[i5][i7]);
            int i8 = (challenge + 2) % 3;
            System.arraycopy(proofArr2[i5].view3Commitment, 0, bArr2[i5][i8], 0, picnicEngine.digestSizeBytes);
            if (picnicEngine.transform == 1) {
                picnicEngine.G(challenge, proofArr2[i5].seed1, 0, viewArr4[i5], bArr3[i5][challenge]);
                i = challenge;
                picnicEngine = this;
                i2 = i7;
                picnicEngine.G(i2, proofArr2[i5].seed2, 0, viewArr3[i5], bArr3[i5][i7]);
                System.arraycopy(proofArr2[i5].view3UnruhG, 0, bArr3[i5][i8], 0, i == 0 ? picnicEngine.UnruhGWithInputBytes : picnicEngine.UnruhGWithoutInputBytes);
            } else {
                i = challenge;
                i2 = i7;
            }
            iArr4[i5][i] = viewArr4[i5].outputShare;
            iArr4[i5][i2] = viewArr3[i5].outputShare;
            int[] iArr5 = new int[picnicEngine.stateSizeWords];
            picnicEngine.xor_three(iArr5, viewArr4[i5].outputShare, viewArr3[i5].outputShare, iArr);
            iArr4[i5][i8] = iArr5;
            i5++;
            bArr4 = bArr7;
            proofArr = proofArr2;
            c = c3;
            viewArr = viewArr4;
            viewArr2 = viewArr3;
            c2 = c4;
        }
        LOG.fine("Invalid signature. Did not verify");
        return -1;
    }

    private int verify_picnic3(Signature2 signature2, int[] iArr, int[] iArr2, byte[] bArr) {
        int iVerifyMerkleTree;
        Logger logger;
        String string;
        StringBuilder sb;
        Tape[] tapeArr;
        int i;
        PicnicEngine picnicEngine = this;
        int[] iArr3 = {picnicEngine.numMPCRounds, picnicEngine.numMPCParties, picnicEngine.digestSizeBytes};
        Class cls = Byte.TYPE;
        byte[][][] bArr2 = (byte[][][]) Array.newInstance((Class<?>) cls, iArr3);
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) cls, picnicEngine.numMPCRounds, picnicEngine.digestSizeBytes);
        byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) cls, picnicEngine.numMPCRounds, picnicEngine.digestSizeBytes);
        Msg[] msgArr = new Msg[picnicEngine.numMPCRounds];
        Tree tree = new Tree(picnicEngine, picnicEngine.numMPCRounds, picnicEngine.digestSizeBytes);
        byte[] bArr5 = new byte[64];
        int i2 = picnicEngine.numMPCRounds;
        Tree[] treeArr = new Tree[i2];
        Tape[] tapeArr2 = new Tape[i2];
        Tree tree2 = new Tree(picnicEngine, picnicEngine.numMPCRounds, picnicEngine.seedSizeBytes);
        if (tree2.reconstructSeeds(signature2.challengeC, picnicEngine.numOpenedRounds, signature2.iSeedInfo, signature2.iSeedInfoLen, signature2.salt, 0) != 0) {
            return -1;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= picnicEngine.numMPCRounds) {
                int i4 = picnicEngine.numMPCParties - 1;
                byte[] bArr6 = new byte[MAX_AUX_BYTES];
                int i5 = 0;
                while (i5 < picnicEngine.numMPCRounds) {
                    byte[] bArr7 = bArr6;
                    Tape tape = new Tape(picnicEngine);
                    tapeArr2[i5] = tape;
                    byte[][] bArr8 = bArr3;
                    byte[][][] bArr9 = bArr2;
                    picnicEngine.createRandomTapes(tape, treeArr[i5].getLeaves(), treeArr[i5].getLeavesOffset(), signature2.salt, i5);
                    if (picnicEngine.contains(signature2.challengeC, picnicEngine.numOpenedRounds, i5)) {
                        tapeArr = tapeArr2;
                        int i6 = signature2.challengeP[indexOf(signature2.challengeC, picnicEngine.numOpenedRounds, i5)];
                        int i7 = 0;
                        while (i7 < i4) {
                            if (i7 != i6) {
                                picnicEngine.commit(bArr9[i5][i7], treeArr[i5].getLeaf(i7), null, signature2.salt, i5, i7);
                            }
                            i7++;
                            picnicEngine = this;
                        }
                        if (i4 != i6) {
                            picnicEngine = this;
                            picnicEngine.commit(bArr9[i5][i4], treeArr[i5].getLeaf(i4), signature2.proofs[i5].aux, signature2.salt, i5, i4);
                        } else {
                            picnicEngine = this;
                        }
                        System.arraycopy(signature2.proofs[i5].C, 0, bArr9[i5][i6], 0, picnicEngine.digestSizeBytes);
                    } else {
                        tapeArr2[i5].computeAuxTape(null);
                        Tape[] tapeArr3 = tapeArr2;
                        int i8 = 0;
                        while (i8 < i4) {
                            picnicEngine.commit(bArr9[i5][i8], treeArr[i5].getLeaf(i8), null, signature2.salt, i5, i8);
                            i8++;
                            tapeArr3 = tapeArr3;
                        }
                        tapeArr = tapeArr3;
                        picnicEngine.getAuxBits(bArr7, tapeArr[i5]);
                        picnicEngine.commit(bArr9[i5][i4], treeArr[i5].getLeaf(i4), bArr7, signature2.salt, i5, i4);
                    }
                    i5++;
                    bArr6 = bArr7;
                    bArr3 = bArr8;
                    tapeArr2 = tapeArr;
                    bArr2 = bArr9;
                }
                Tape[] tapeArr4 = tapeArr2;
                byte[][][] bArr10 = bArr2;
                byte[][] bArr11 = bArr3;
                for (int i9 = 0; i9 < picnicEngine.numMPCRounds; i9++) {
                    picnicEngine.commit_h(bArr11[i9], bArr10[i9]);
                }
                int[] iArr4 = new int[picnicEngine.stateSizeBits];
                int i10 = 0;
                while (true) {
                    int i11 = picnicEngine.numMPCRounds;
                    if (i10 >= i11) {
                        if (tree.addMerkleNodes(picnicEngine.getMissingLeavesList(signature2.challengeC), i11 - picnicEngine.numOpenedRounds, signature2.cvInfo, signature2.cvInfoLen) != 0 || (iVerifyMerkleTree = tree.verifyMerkleTree(bArr4, signature2.salt)) != 0) {
                            return -1;
                        }
                        picnicEngine.HCP(bArr5, null, null, bArr11, tree.nodes[0], signature2.salt, iArr, iArr2, bArr);
                        if (!subarrayEquals(signature2.challengeHash, bArr5, picnicEngine.digestSizeBytes)) {
                            logger = LOG;
                            string = "Challenge does not match, signature invalid";
                            break;
                        }
                        return iVerifyMerkleTree;
                    }
                    msgArr[i10] = new Msg(picnicEngine);
                    if (picnicEngine.contains(signature2.challengeC, picnicEngine.numOpenedRounds, i10)) {
                        int i12 = signature2.challengeP[indexOf(signature2.challengeC, picnicEngine.numOpenedRounds, i10)];
                        if (i12 != i4) {
                            tapeArr4[i10].setAuxBits(signature2.proofs[i10].aux);
                        }
                        System.arraycopy(signature2.proofs[i10].msgs, 0, msgArr[i10].msgs[i12], 0, picnicEngine.andSizeBytes);
                        Arrays.fill(tapeArr4[i10].tapes[i12], (byte) 0);
                        msgArr[i10].unopened = i12;
                        byte[] bArr12 = new byte[picnicEngine.stateSizeWords * 4];
                        byte[] bArr13 = signature2.proofs[i10].input;
                        System.arraycopy(bArr13, 0, bArr12, 0, bArr13.length);
                        int i13 = picnicEngine.stateSizeWords;
                        int[] iArr5 = new int[i13];
                        Pack.littleEndianToInt(bArr12, 0, iArr5, 0, i13);
                        if (picnicEngine.simulateOnline(iArr5, tapeArr4[i10], iArr4, msgArr[i10], iArr2, iArr) != 0) {
                            logger = LOG;
                            sb = new StringBuilder("MPC simulation failed for round ");
                            sb.append(i10);
                            sb.append(", signature invalid");
                            break;
                        }
                        picnicEngine.commit_v(bArr4[i10], signature2.proofs[i10].input, msgArr[i10]);
                    } else {
                        bArr4[i10] = null;
                    }
                    i10++;
                }
                logger.fine(string);
                return -1;
            }
            if (picnicEngine.contains(signature2.challengeC, picnicEngine.numOpenedRounds, i3)) {
                treeArr[i3] = new Tree(picnicEngine, picnicEngine.numMPCParties, picnicEngine.seedSizeBytes);
                int[] iArr6 = {signature2.challengeP[indexOf(signature2.challengeC, picnicEngine.numOpenedRounds, i3)]};
                Tree tree3 = treeArr[i3];
                Signature2.Proof2 proof2 = signature2.proofs[i3];
                int i14 = i3;
                int iReconstructSeeds = tree3.reconstructSeeds(iArr6, 1, proof2.seedInfo, proof2.seedInfoLen, signature2.salt, i14);
                i = i14;
                if (iReconstructSeeds != 0) {
                    logger = LOG;
                    sb = new StringBuilder("Failed to reconstruct seeds for round ");
                    sb.append(i);
                    break;
                }
            } else {
                Tree tree4 = new Tree(picnicEngine, picnicEngine.numMPCParties, picnicEngine.seedSizeBytes);
                treeArr[i3] = tree4;
                tree4.generateSeeds(tree2.getLeaf(i3), signature2.salt, i3);
                i = i3;
            }
            i3 = i + 1;
        }
        string = sb.toString();
        logger.fine(string);
        return -1;
    }

    private void wordToMsgs(int i, Msg msg) {
        for (int i2 = 0; i2 < this.numMPCParties; i2++) {
            Utils.setBit(msg.msgs[i2], msg.pos, (byte) Utils.getBit(i, i2));
        }
        msg.pos++;
    }

    private void xor_three(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        for (int i = 0; i < this.stateSizeWords; i++) {
            iArr[i] = (iArr2[i] ^ iArr3[i]) ^ iArr4[i];
        }
    }

    public void aux_mpc_sbox(int[] iArr, int[] iArr2, Tape tape) {
        for (int i = 0; i < this.numSboxes * 3; i += 3) {
            int i2 = i + 2;
            int bitFromWordArray = Utils.getBitFromWordArray(iArr, i2);
            int i3 = i + 1;
            int bitFromWordArray2 = Utils.getBitFromWordArray(iArr, i3);
            int bitFromWordArray3 = Utils.getBitFromWordArray(iArr, i);
            int bitFromWordArray4 = Utils.getBitFromWordArray(iArr2, i2);
            int bitFromWordArray5 = Utils.getBitFromWordArray(iArr2, i3);
            aux_mpc_AND(bitFromWordArray, bitFromWordArray2, ((Utils.getBitFromWordArray(iArr2, i) ^ bitFromWordArray) ^ bitFromWordArray2) ^ bitFromWordArray3, tape);
            aux_mpc_AND(bitFromWordArray2, bitFromWordArray3, bitFromWordArray4 ^ bitFromWordArray, tape);
            aux_mpc_AND(bitFromWordArray3, bitFromWordArray, (bitFromWordArray5 ^ bitFromWordArray) ^ bitFromWordArray2, tape);
        }
    }

    public void crypto_sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (picnic_sign(bArr3, bArr2, bArr)) {
            System.arraycopy(bArr2, 0, bArr, 4, bArr2.length);
        }
    }

    public void crypto_sign_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        int i = this.stateSizeWords;
        byte[] bArr3 = new byte[i * 4];
        byte[] bArr4 = new byte[i * 4];
        byte[] bArr5 = new byte[i * 4];
        picnic_keygen(bArr3, bArr4, bArr5, secureRandom);
        picnic_write_public_key(bArr4, bArr3, bArr);
        picnic_write_private_key(bArr5, bArr4, bArr3, bArr2);
    }

    public boolean crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (picnic_verify(bArr3, Arrays.copyOfRange(bArr2, 4, bArr.length + 4), bArr2, Pack.littleEndianToInt(bArr2, 0)) == -1) {
            return false;
        }
        System.arraycopy(bArr2, 4, bArr, 0, bArr.length);
        return true;
    }

    public int getChallenge(byte[] bArr, int i) {
        return Utils.getCrumbAligned(bArr, i);
    }

    public int getPublicKeySize() {
        return this.CRYPTO_PUBLICKEYBYTES;
    }

    public int getSecretKeySize() {
        return this.CRYPTO_SECRETKEYBYTES;
    }

    public int getSignatureSize(int i) {
        return this.CRYPTO_BYTES + i;
    }

    public int getTrueSignatureSize() {
        return this.signatureLength;
    }

    public void matrix_mul(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        matrix_mul_offset(iArr, 0, iArr2, 0, iArr3, i);
    }

    public void matrix_mul_offset(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int[] iArr4 = new int[16];
        int i4 = this.stateSizeWords;
        iArr4[i4 - 1] = 0;
        int i5 = this.stateSizeBits;
        int i6 = i5 / 32;
        int i7 = (i4 * 32) - i5;
        int iBitPermuteStepSimple = Bits.bitPermuteStepSimple(Bits.bitPermuteStepSimple(Bits.bitPermuteStepSimple((-1) >>> i7, 1431655765, 1), 858993459, 2), 252645135, 4);
        for (int i8 = 0; i8 < this.stateSizeBits; i8++) {
            int i9 = 0;
            for (int i10 = 0; i10 < i6; i10++) {
                i9 ^= iArr3[i3 + ((this.stateSizeWords * i8) + i10)] & iArr2[i2 + i10];
            }
            if (i7 > 0) {
                i9 ^= (iArr3[i3 + ((this.stateSizeWords * i8) + i6)] & iArr2[i2 + i6]) & iBitPermuteStepSimple;
            }
            Utils.setBit(iArr4, i8, Utils.parity32(i9));
        }
        System.arraycopy(iArr4, 0, iArr, i, this.stateSizeWords);
    }

    public void mpc_AND_verify(int[] iArr, int[] iArr2, int[] iArr3, Tape tape, View view, View view2) {
        byte bit = Utils.getBit(tape.tapes[0], tape.pos);
        byte bit2 = Utils.getBit(tape.tapes[1], tape.pos);
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr2[0];
        int i4 = (bit ^ (((i2 & i3) ^ (iArr2[1] & i)) ^ (i & i3))) ^ bit2;
        iArr3[0] = i4;
        Utils.setBit(view.communicatedBits, tape.pos, (byte) i4);
        iArr3[1] = Utils.getBit(view2.communicatedBits, tape.pos);
        tape.pos++;
    }

    public void mpc_LowMC_verify(View view, View view2, Tape tape, int[] iArr, int[] iArr2, int i) {
        Arrays.fill(iArr, 0, iArr.length, 0);
        mpc_xor_constant_verify(iArr, iArr2, 0, this.stateSizeWords, i);
        KMatricesWithPointer kMatricesWithPointerKMatrix = this.lowmcConstants.KMatrix(this, 0);
        int[] iArr3 = iArr;
        matrix_mul_offset(iArr3, 0, view.inputShare, 0, kMatricesWithPointerKMatrix.getData(), kMatricesWithPointerKMatrix.getMatrixPointer());
        matrix_mul_offset(iArr3, this.stateSizeWords, view2.inputShare, 0, kMatricesWithPointerKMatrix.getData(), kMatricesWithPointerKMatrix.getMatrixPointer());
        mpc_xor(iArr3, iArr3, 2);
        for (int i2 = 1; i2 <= this.numRounds; i2++) {
            KMatricesWithPointer kMatricesWithPointerKMatrix2 = this.lowmcConstants.KMatrix(this, i2);
            matrix_mul_offset(iArr3, 0, view.inputShare, 0, kMatricesWithPointerKMatrix2.getData(), kMatricesWithPointerKMatrix2.getMatrixPointer());
            matrix_mul_offset(iArr, this.stateSizeWords, view2.inputShare, 0, kMatricesWithPointerKMatrix2.getData(), kMatricesWithPointerKMatrix2.getMatrixPointer());
            mpc_substitution_verify(iArr, tape, view, view2);
            int i3 = i2 - 1;
            KMatricesWithPointer kMatricesWithPointerLMatrix = this.lowmcConstants.LMatrix(this, i3);
            int i4 = this.stateSizeWords;
            mpc_matrix_mul(iArr, i4 * 2, iArr, i4 * 2, kMatricesWithPointerLMatrix.getData(), kMatricesWithPointerLMatrix.getMatrixPointer(), 2);
            KMatricesWithPointer kMatricesWithPointerRConstant = this.lowmcConstants.RConstant(this, i3);
            iArr3 = iArr;
            mpc_xor_constant_verify(iArr3, kMatricesWithPointerRConstant.getData(), kMatricesWithPointerRConstant.getMatrixPointer(), this.stateSizeWords, i);
            mpc_xor(iArr3, iArr3, 2);
        }
        int i5 = this.stateSizeWords;
        System.arraycopy(iArr3, i5 * 2, view.outputShare, 0, i5);
        int i6 = this.stateSizeWords;
        System.arraycopy(iArr3, i6 * 3, view2.outputShare, 0, i6);
    }

    public void mpc_substitution_verify(int[] iArr, Tape tape, View view, View view2) {
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        int[] iArr4 = new int[2];
        int[] iArr5 = new int[2];
        int[] iArr6 = new int[2];
        int[] iArr7 = new int[2];
        int i = 0;
        while (i < this.numSboxes * 3) {
            for (int i2 = 0; i2 < 2; i2++) {
                int i3 = ((i2 + 2) * this.stateSizeWords * 32) + i;
                iArr2[i2] = Utils.getBitFromWordArray(iArr, i3 + 2);
                iArr3[i2] = Utils.getBitFromWordArray(iArr, i3 + 1);
                iArr4[i2] = Utils.getBitFromWordArray(iArr, i3);
            }
            mpc_AND_verify(iArr2, iArr3, iArr5, tape, view, view2);
            int[] iArr8 = iArr4;
            int[] iArr9 = iArr2;
            int[] iArr10 = iArr3;
            int[] iArr11 = iArr6;
            int[] iArr12 = iArr5;
            mpc_AND_verify(iArr10, iArr8, iArr11, tape, view, view2);
            int[] iArr13 = iArr7;
            mpc_AND_verify(iArr8, iArr9, iArr13, tape, view, view2);
            iArr2 = iArr9;
            for (int i4 = 0; i4 < 2; i4++) {
                int i5 = ((i4 + 2) * this.stateSizeWords * 32) + i;
                Utils.setBitInWordArray(iArr, i5 + 2, iArr2[i4] ^ iArr11[i4]);
                Utils.setBitInWordArray(iArr, i5 + 1, (iArr2[i4] ^ iArr10[i4]) ^ iArr13[i4]);
                Utils.setBitInWordArray(iArr, i5, ((iArr2[i4] ^ iArr10[i4]) ^ iArr8[i4]) ^ iArr12[i4]);
            }
            i += 3;
            iArr4 = iArr8;
            iArr3 = iArr10;
            iArr7 = iArr13;
            iArr5 = iArr12;
            iArr6 = iArr11;
        }
    }

    public void prove(Signature.Proof proof, int i, byte[] bArr, int i2, View[] viewArr, byte[][] bArr2, byte[][] bArr3) {
        if (i == 0) {
            System.arraycopy(bArr, i2, proof.seed1, 0, this.seedSizeBytes);
            int i3 = this.seedSizeBytes;
            System.arraycopy(bArr, i2 + i3, proof.seed2, 0, i3);
        } else if (i == 1) {
            int i4 = this.seedSizeBytes;
            System.arraycopy(bArr, i2 + i4, proof.seed1, 0, i4);
            int i5 = this.seedSizeBytes;
            System.arraycopy(bArr, i2 + (i5 * 2), proof.seed2, 0, i5);
        } else if (i != 2) {
            LOG.fine("Invalid challenge");
            w01.a("challenge");
            return;
        } else {
            int i6 = this.seedSizeBytes;
            System.arraycopy(bArr, (i6 * 2) + i2, proof.seed1, 0, i6);
            System.arraycopy(bArr, i2, proof.seed2, 0, this.seedSizeBytes);
        }
        if (i == 1 || i == 2) {
            System.arraycopy(viewArr[2].inputShare, 0, proof.inputShare, 0, this.stateSizeWords);
        }
        System.arraycopy(viewArr[(i + 1) % 3].communicatedBits, 0, proof.communicatedBits, 0, this.andSizeBytes);
        int i7 = (i + 2) % 3;
        System.arraycopy(bArr2[i7], 0, proof.view3Commitment, 0, this.digestSizeBytes);
        if (this.transform == 1) {
            System.arraycopy(bArr3[i7], 0, proof.view3UnruhG, 0, i == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes);
        }
    }

    public int serializeSignature(Signature signature, byte[] bArr, int i) {
        Signature.Proof[] proofArr = signature.proofs;
        byte[] bArr2 = signature.challengeBits;
        int iNumBytes = Utils.numBytes(this.numMPCRounds * 2) + 32;
        int i2 = this.numMPCRounds;
        int i3 = iNumBytes + (((this.seedSizeBytes * 2) + this.stateSizeBytes + this.andSizeBytes + this.digestSizeBytes) * i2);
        if (this.transform == 1) {
            i3 += this.UnruhGWithoutInputBytes * i2;
        }
        if (this.CRYPTO_BYTES < i3) {
            return -1;
        }
        System.arraycopy(bArr2, 0, bArr, i, Utils.numBytes(i2 * 2));
        int iNumBytes2 = Utils.numBytes(this.numMPCRounds * 2) + i;
        System.arraycopy(signature.salt, 0, bArr, iNumBytes2, 32);
        int i4 = iNumBytes2 + 32;
        for (int i5 = 0; i5 < this.numMPCRounds; i5++) {
            int challenge = getChallenge(bArr2, i5);
            System.arraycopy(proofArr[i5].view3Commitment, 0, bArr, i4, this.digestSizeBytes);
            int i6 = i4 + this.digestSizeBytes;
            if (this.transform == 1) {
                int i7 = challenge == 0 ? this.UnruhGWithInputBytes : this.UnruhGWithoutInputBytes;
                System.arraycopy(proofArr[i5].view3UnruhG, 0, bArr, i6, i7);
                i6 += i7;
            }
            System.arraycopy(proofArr[i5].communicatedBits, 0, bArr, i6, this.andSizeBytes);
            int i8 = i6 + this.andSizeBytes;
            System.arraycopy(proofArr[i5].seed1, 0, bArr, i8, this.seedSizeBytes);
            int i9 = this.seedSizeBytes;
            int i10 = i8 + i9;
            System.arraycopy(proofArr[i5].seed2, 0, bArr, i10, i9);
            i4 = i10 + this.seedSizeBytes;
            if (challenge == 1 || challenge == 2) {
                Pack.intToLittleEndian(proofArr[i5].inputShare, 0, this.stateSizeWords, bArr, i4);
                i4 += this.stateSizeBytes;
            }
        }
        return i4 - i;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0068  */
    public boolean verifyProof(Signature.Proof proof, View view, View view2, int i, byte[] bArr, int i2, byte[] bArr2, int[] iArr, Tape tape) {
        byte[] bArr3;
        boolean z;
        System.arraycopy(proof.communicatedBits, 0, view2.communicatedBits, 0, this.andSizeBytes);
        tape.pos = 0;
        if (i == 0) {
            bArr3 = bArr2;
            boolean zCreateRandomTape = createRandomTape(proof.seed1, 0, bArr, i2, 0, bArr3, this.stateSizeBytes + this.andSizeBytes);
            Pack.littleEndianToInt(bArr3, 0, view.inputShare);
            System.arraycopy(bArr3, this.stateSizeBytes, tape.tapes[0], 0, this.andSizeBytes);
            z = zCreateRandomTape && createRandomTape(proof.seed2, 0, bArr, i2, 1, bArr3, this.stateSizeBytes + this.andSizeBytes);
            if (z) {
                Pack.littleEndianToInt(bArr3, 0, view2.inputShare);
                System.arraycopy(bArr3, this.stateSizeBytes, tape.tapes[1], 0, this.andSizeBytes);
            }
        } else if (i == 1) {
            boolean zCreateRandomTape2 = createRandomTape(proof.seed1, 0, bArr, i2, 1, bArr2, this.stateSizeBytes + this.andSizeBytes);
            Pack.littleEndianToInt(bArr2, 0, view.inputShare);
            System.arraycopy(bArr2, this.stateSizeBytes, tape.tapes[0], 0, this.andSizeBytes);
            z = zCreateRandomTape2 && createRandomTape(proof.seed2, 0, bArr, i2, 2, tape.tapes[1], this.andSizeBytes);
            if (z) {
                System.arraycopy(proof.inputShare, 0, view2.inputShare, 0, this.stateSizeWords);
            }
            bArr3 = bArr2;
        } else if (i != 2) {
            LOG.fine("Invalid Challenge!");
            bArr3 = bArr2;
            z = false;
        } else {
            boolean zCreateRandomTape3 = createRandomTape(proof.seed1, 0, bArr, i2, 2, tape.tapes[0], this.andSizeBytes);
            System.arraycopy(proof.inputShare, 0, view.inputShare, 0, this.stateSizeWords);
            if (zCreateRandomTape3) {
                bArr3 = bArr2;
                if (createRandomTape(proof.seed2, 0, bArr, i2, 0, bArr3, this.stateSizeBytes + this.andSizeBytes)) {
                    z = true;
                }
                if (z) {
                    Pack.littleEndianToInt(bArr3, 0, view2.inputShare);
                    System.arraycopy(bArr3, this.stateSizeBytes, tape.tapes[1], 0, this.andSizeBytes);
                }
            } else {
                bArr3 = bArr2;
            }
            z = false;
            if (z) {
                Pack.littleEndianToInt(bArr3, 0, view2.inputShare);
                System.arraycopy(bArr3, this.stateSizeBytes, tape.tapes[1], 0, this.andSizeBytes);
            }
        }
        if (!z) {
            LOG.fine("Failed to generate random tapes, signature verification will fail (but signature may actually be valid)");
            return false;
        }
        Utils.zeroTrailingBits(view.inputShare, this.stateSizeBits);
        Utils.zeroTrailingBits(view2.inputShare, this.stateSizeBits);
        mpc_LowMC_verify(view, view2, tape, Pack.littleEndianToInt(bArr3, 0, bArr3.length / 4), iArr, i);
        return true;
    }

    public void xor_array(int[] iArr, int[] iArr2, int[] iArr3, int i) {
        for (int i2 = 0; i2 < this.stateSizeWords; i2++) {
            iArr[i2] = iArr2[i2] ^ iArr3[i2 + i];
        }
    }

    private boolean arePaddingBitsZero(int[] iArr, int i) {
        if ((i & 31) == 0) {
            return true;
        }
        return ((~Utils.getTrailingBitsMask(i)) & iArr[i >>> 5]) == 0;
    }

    private static boolean subarrayEquals(int[] iArr, int[] iArr2, int i) {
        if (iArr.length < i || iArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void H3(int[] iArr, int[] iArr2, int[][][] iArr3, byte[][][] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[][][] bArr5) {
        this.digest.update((byte) 1);
        byte[] bArr6 = new byte[this.stateSizeWords * 4];
        for (int i = 0; i < this.numMPCRounds; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                Pack.intToLittleEndian(iArr3[i][i2], bArr6, 0);
                this.digest.update(bArr6, 0, this.stateSizeBytes);
            }
        }
        implH3(iArr, iArr2, bArr, bArr2, bArr3, bArr4, bArr5);
    }

    private int mpc_AND(int i, int i2, int i3, int i4, Tape tape, Msg msg) {
        int iExtend = ((i3 & extend(i2)) ^ (i4 & extend(i))) ^ tape.tapesToWord();
        int i5 = msg.unopened;
        if (i5 >= 0) {
            iExtend = Utils.setBit(iExtend, msg.unopened, Utils.getBit(msg.msgs[i5], msg.pos));
        }
        wordToMsgs(iExtend, msg);
        return Utils.parity16(iExtend) ^ (i & i2);
    }
}
