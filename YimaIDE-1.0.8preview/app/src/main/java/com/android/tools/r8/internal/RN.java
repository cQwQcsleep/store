package com.android.tools.r8.internal;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RN extends D implements Serializable {
    public final MessageDigest b;
    public final int c;
    public final boolean d;
    public final String e;

    public RN() {
        boolean z;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            this.b = messageDigest;
            this.c = messageDigest.getDigestLength();
            this.e = "Hashing.sha256()";
            try {
                messageDigest.clone();
                z = true;
            } catch (CloneNotSupportedException unused) {
                z = false;
            }
            this.d = z;
        } catch (NoSuchAlgorithmException e) {
            x01.a(e);
            throw null;
        }
    }

    public final InterfaceC0990Ys a() {
        if (this.d) {
            try {
                return new QN((MessageDigest) this.b.clone(), this.c);
            } catch (CloneNotSupportedException unused) {
            }
        }
        try {
            return new QN(MessageDigest.getInstance(this.b.getAlgorithm()), this.c);
        } catch (NoSuchAlgorithmException e) {
            x01.a(e);
            return null;
        }
    }

    public final String toString() {
        return this.e;
    }
}
