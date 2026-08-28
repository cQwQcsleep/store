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
public final class HashingSink extends ForwardingSink implements Sink {
    public static final Companion Companion = new Companion(null);
    private final Mac mac;
    private final MessageDigest messageDigest;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HashingSink hmacSha1(Sink sink, ByteString byteString) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            CloseableKt.checkNotNullParameter(byteString, "key");
            return new HashingSink(sink, byteString, "HmacSHA1");
        }

        public final HashingSink hmacSha256(Sink sink, ByteString byteString) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            CloseableKt.checkNotNullParameter(byteString, "key");
            return new HashingSink(sink, byteString, "HmacSHA256");
        }

        public final HashingSink hmacSha512(Sink sink, ByteString byteString) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            CloseableKt.checkNotNullParameter(byteString, "key");
            return new HashingSink(sink, byteString, "HmacSHA512");
        }

        public final HashingSink md5(Sink sink) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            return new HashingSink(sink, "MD5");
        }

        public final HashingSink sha1(Sink sink) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            return new HashingSink(sink, "SHA-1");
        }

        public final HashingSink sha256(Sink sink) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            return new HashingSink(sink, "SHA-256");
        }

        public final HashingSink sha512(Sink sink) {
            CloseableKt.checkNotNullParameter(sink, "sink");
            return new HashingSink(sink, "SHA-512");
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSink(Sink sink, MessageDigest messageDigest) {
        super(sink);
        CloseableKt.checkNotNullParameter(sink, "sink");
        CloseableKt.checkNotNullParameter(messageDigest, "digest");
        this.messageDigest = messageDigest;
        this.mac = null;
    }

    public static final HashingSink hmacSha1(Sink sink, ByteString byteString) {
        return Companion.hmacSha1(sink, byteString);
    }

    public static final HashingSink hmacSha256(Sink sink, ByteString byteString) {
        return Companion.hmacSha256(sink, byteString);
    }

    public static final HashingSink hmacSha512(Sink sink, ByteString byteString) {
        return Companion.hmacSha512(sink, byteString);
    }

    public static final HashingSink md5(Sink sink) {
        return Companion.md5(sink);
    }

    public static final HashingSink sha1(Sink sink) {
        return Companion.sha1(sink);
    }

    public static final HashingSink sha256(Sink sink) {
        return Companion.sha256(sink);
    }

    public static final HashingSink sha512(Sink sink) {
        return Companion.sha512(sink);
    }

    /* renamed from: -deprecated_hash, reason: not valid java name */
    public final ByteString m175deprecated_hash() {
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

    @Override // com.shadow.okio.ForwardingSink, com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws IllegalStateException, IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0L, j);
        Segment segment = buffer.head;
        CloseableKt.checkNotNull(segment);
        long j2 = 0;
        while (j2 < j) {
            int iMin = (int) Math.min(j - j2, segment.limit - segment.pos);
            MessageDigest messageDigest = this.messageDigest;
            if (messageDigest != null) {
                messageDigest.update(segment.data, segment.pos, iMin);
            } else {
                Mac mac = this.mac;
                CloseableKt.checkNotNull(mac);
                mac.update(segment.data, segment.pos, iMin);
            }
            j2 += iMin;
            segment = segment.next;
            CloseableKt.checkNotNull(segment);
        }
        super.write(buffer, j);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HashingSink(Sink sink, String str) throws NoSuchAlgorithmException {
        CloseableKt.checkNotNullParameter(sink, "sink");
        CloseableKt.checkNotNullParameter(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        CloseableKt.checkNotNullExpressionValue(messageDigest, "getInstance(...)");
        this(sink, messageDigest);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSink(Sink sink, Mac mac) {
        super(sink);
        CloseableKt.checkNotNullParameter(sink, "sink");
        CloseableKt.checkNotNullParameter(mac, "mac");
        this.mac = mac;
        this.messageDigest = null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HashingSink(Sink sink, ByteString byteString, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        CloseableKt.checkNotNullParameter(sink, "sink");
        CloseableKt.checkNotNullParameter(byteString, "key");
        CloseableKt.checkNotNullParameter(str, "algorithm");
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            this(sink, mac);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
