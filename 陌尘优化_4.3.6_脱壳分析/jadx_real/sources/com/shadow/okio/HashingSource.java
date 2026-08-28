package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class HashingSource extends ForwardingSource implements Source {
    public static final Companion Companion = new Companion(null);
    private final Mac mac;
    private final MessageDigest messageDigest;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HashingSource hmacSha1(Source source, ByteString byteString) {
            CloseableKt.checkNotNullParameter(source, "source");
            CloseableKt.checkNotNullParameter(byteString, "key");
            return new HashingSource(source, byteString, "HmacSHA1");
        }

        public final HashingSource hmacSha256(Source source, ByteString byteString) {
            CloseableKt.checkNotNullParameter(source, "source");
            CloseableKt.checkNotNullParameter(byteString, "key");
            return new HashingSource(source, byteString, "HmacSHA256");
        }

        public final HashingSource hmacSha512(Source source, ByteString byteString) {
            CloseableKt.checkNotNullParameter(source, "source");
            CloseableKt.checkNotNullParameter(byteString, "key");
            return new HashingSource(source, byteString, "HmacSHA512");
        }

        public final HashingSource md5(Source source) {
            CloseableKt.checkNotNullParameter(source, "source");
            return new HashingSource(source, "MD5");
        }

        public final HashingSource sha1(Source source) {
            CloseableKt.checkNotNullParameter(source, "source");
            return new HashingSource(source, "SHA-1");
        }

        public final HashingSource sha256(Source source) {
            CloseableKt.checkNotNullParameter(source, "source");
            return new HashingSource(source, "SHA-256");
        }

        public final HashingSource sha512(Source source) {
            CloseableKt.checkNotNullParameter(source, "source");
            return new HashingSource(source, "SHA-512");
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSource(Source source, MessageDigest messageDigest) {
        super(source);
        CloseableKt.checkNotNullParameter(source, "source");
        CloseableKt.checkNotNullParameter(messageDigest, "digest");
        this.messageDigest = messageDigest;
        this.mac = null;
    }

    public static final HashingSource hmacSha1(Source source, ByteString byteString) {
        return Companion.hmacSha1(source, byteString);
    }

    public static final HashingSource hmacSha256(Source source, ByteString byteString) {
        return Companion.hmacSha256(source, byteString);
    }

    public static final HashingSource hmacSha512(Source source, ByteString byteString) {
        return Companion.hmacSha512(source, byteString);
    }

    public static final HashingSource md5(Source source) {
        return Companion.md5(source);
    }

    public static final HashingSource sha1(Source source) {
        return Companion.sha1(source);
    }

    public static final HashingSource sha256(Source source) {
        return Companion.sha256(source);
    }

    public static final HashingSource sha512(Source source) {
        return Companion.sha512(source);
    }

    /* renamed from: -deprecated_hash, reason: not valid java name */
    public final ByteString m176deprecated_hash() {
        return hash();
    }

    public final ByteString hash() throws IllegalStateException {
        byte[] bArrDoFinal;
        MessageDigest messageDigest = this.messageDigest;
        if (messageDigest != null) {
            bArrDoFinal = messageDigest.digest();
        } else {
            Mac mac = this.mac;
            CloseableKt.checkNotNull(mac);
            bArrDoFinal = mac.doFinal();
        }
        CloseableKt.checkNotNull(bArrDoFinal);
        return new ByteString(bArrDoFinal);
    }

    @Override // com.shadow.okio.ForwardingSource, com.shadow.okio.Source
    public long read(Buffer buffer, long j) throws IllegalStateException, IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        long j2 = super.read(buffer, j);
        if (j2 != -1) {
            long size = buffer.size() - j2;
            long size2 = buffer.size();
            Segment segment = buffer.head;
            CloseableKt.checkNotNull(segment);
            while (size2 > size) {
                segment = segment.prev;
                CloseableKt.checkNotNull(segment);
                size2 -= segment.limit - segment.pos;
            }
            while (size2 < buffer.size()) {
                int i = (int) ((segment.pos + size) - size2);
                MessageDigest messageDigest = this.messageDigest;
                if (messageDigest != null) {
                    messageDigest.update(segment.data, i, segment.limit - i);
                } else {
                    Mac mac = this.mac;
                    CloseableKt.checkNotNull(mac);
                    mac.update(segment.data, i, segment.limit - i);
                }
                size2 += segment.limit - segment.pos;
                segment = segment.next;
                CloseableKt.checkNotNull(segment);
                size = size2;
            }
        }
        return j2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HashingSource(Source source, String str) throws NoSuchAlgorithmException {
        CloseableKt.checkNotNullParameter(source, "source");
        CloseableKt.checkNotNullParameter(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        CloseableKt.checkNotNullExpressionValue(messageDigest, "getInstance(...)");
        this(source, messageDigest);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSource(Source source, Mac mac) {
        super(source);
        CloseableKt.checkNotNullParameter(source, "source");
        CloseableKt.checkNotNullParameter(mac, "mac");
        this.mac = mac;
        this.messageDigest = null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HashingSource(Source source, ByteString byteString, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        CloseableKt.checkNotNullParameter(source, "source");
        CloseableKt.checkNotNullParameter(byteString, "key");
        CloseableKt.checkNotNullParameter(str, "algorithm");
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            this(source, mac);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
