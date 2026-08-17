package com.sun.org.apache.xalan.internal.xsltc.dom;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import okhttp3.internal.http2.Http2Connection;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class BitArray implements Externalizable {
    private static final boolean DEBUG_ASSERTIONS = false;
    private static final int[] _masks = {PKIFailureInfo.systemUnavail, 1073741824, PKIFailureInfo.duplicateCertReq, 268435456, 134217728, 67108864, 33554432, Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE, 8388608, 4194304, PKIFailureInfo.badSenderNonce, PKIFailureInfo.badCertTemplate, PKIFailureInfo.signerNotTrusted, PKIFailureInfo.transactionIdInUse, PKIFailureInfo.unsupportedVersion, PKIFailureInfo.notAuthorized, 32768, 16384, 8192, 4096, PKIFailureInfo.wrongIntegrity, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    static final long serialVersionUID = -4876019880708377663L;
    private int _bit;
    private int _bitSize;
    private int[] _bits;
    int _first;
    private int _int;
    private int _intSize;
    int _last;
    private int _mask;
    private int _node;
    private int _pos;

    public BitArray(int i) {
        this._first = Integer.MAX_VALUE;
        this._last = PKIFailureInfo.systemUnavail;
        this._pos = Integer.MAX_VALUE;
        this._node = 0;
        this._int = 0;
        this._bit = 0;
        i = i < 32 ? 32 : i;
        this._bitSize = i;
        int i2 = i >>> 5;
        this._intSize = i2 + 1;
        this._bits = new int[i2 + 2];
    }

    public BitArray cloneArray() {
        return new BitArray(this._intSize, this._bits);
    }

    public final int[] data() {
        return this._bits;
    }

    public final boolean getBit(int i) {
        return (this._bits[i >>> 5] & _masks[i % 32]) != 0;
    }

    public final int getBitNumber(int i) {
        int i2 = this._pos;
        if (i == i2) {
            return this._node;
        }
        if (i < i2) {
            this._pos = 0;
            this._bit = 0;
            this._int = 0;
        }
        while (true) {
            int i3 = this._int;
            if (i3 > this._intSize) {
                return 0;
            }
            int i4 = this._bits[i3];
            if (i4 != 0) {
                while (true) {
                    int i5 = this._bit;
                    if (i5 >= 32) {
                        this._bit = 0;
                        break;
                    }
                    if ((_masks[i5] & i4) != 0) {
                        int i6 = this._pos + 1;
                        this._pos = i6;
                        if (i6 == i) {
                            int i7 = ((this._int << 5) + i5) - 1;
                            this._node = i7;
                            return i7;
                        }
                    }
                    this._bit = i5 + 1;
                }
            }
            this._int++;
        }
    }

    public int getMask() {
        return this._mask;
    }

    public final int getNextBit(int i) {
        int i2 = i >>> 5;
        while (i2 <= this._intSize) {
            int i3 = this._bits[i2];
            if (i3 != 0) {
                for (int i4 = i % 32; i4 < 32; i4++) {
                    if ((_masks[i4] & i3) != 0) {
                        return (i2 << 5) + i4;
                    }
                }
            }
            i2++;
            i = 0;
        }
        return -1;
    }

    public final BitArray merge(BitArray bitArray) {
        int i = this._last;
        if (i == -1) {
            this._bits = bitArray._bits;
            return this;
        }
        int i2 = bitArray._last;
        if (i2 != -1) {
            int i3 = this._first;
            int i4 = bitArray._first;
            if (i3 >= i4) {
                i3 = i4;
            }
            if (i <= i2) {
                i = i2;
            }
            int i5 = bitArray._intSize;
            int i6 = this._intSize;
            if (i5 > i6) {
                if (i > i6) {
                    i = i6;
                }
                while (true) {
                    int[] iArr = bitArray._bits;
                    if (i3 > i) {
                        this._bits = iArr;
                        return this;
                    }
                    iArr[i3] = iArr[i3] | this._bits[i3];
                    i3++;
                }
            } else {
                if (i > i5) {
                    i = i5;
                }
                while (i3 <= i) {
                    int[] iArr2 = this._bits;
                    iArr2[i3] = iArr2[i3] | bitArray._bits[i3];
                    i3++;
                }
            }
        }
        return this;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int i = objectInput.readInt();
        this._bitSize = i;
        this._intSize = (i >>> 5) + 1;
        this._mask = objectInput.readInt();
        this._bits = (int[]) objectInput.readObject();
    }

    public final void resize(int i) {
        int i2 = this._bitSize;
        if (i > i2) {
            int i3 = i >>> 5;
            this._intSize = i3 + 1;
            int[] iArr = new int[i3 + 2];
            System.arraycopy(this._bits, 0, iArr, 0, (i2 >>> 5) + 1);
            this._bits = iArr;
            this._bitSize = i;
        }
    }

    public final void setBit(int i) {
        if (i >= this._bitSize) {
            return;
        }
        int i2 = i >>> 5;
        if (i2 < this._first) {
            this._first = i2;
        }
        if (i2 > this._last) {
            this._last = i2;
        }
        int[] iArr = this._bits;
        iArr[i2] = _masks[i % 32] | iArr[i2];
    }

    public void setMask(int i) {
        this._mask = i;
    }

    public final int size() {
        return this._bitSize;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this._bitSize);
        objectOutput.writeInt(this._mask);
        objectOutput.writeObject(this._bits);
        objectOutput.flush();
    }

    public BitArray() {
        this(32);
    }

    public BitArray(int i, int[] iArr) {
        this._first = Integer.MAX_VALUE;
        this._last = PKIFailureInfo.systemUnavail;
        this._pos = Integer.MAX_VALUE;
        this._node = 0;
        this._int = 0;
        this._bit = 0;
        i = i < 32 ? 32 : i;
        this._bitSize = i;
        this._intSize = (i >>> 5) + 1;
        this._bits = iArr;
    }
}
