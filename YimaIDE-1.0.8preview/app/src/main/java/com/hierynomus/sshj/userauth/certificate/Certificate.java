package com.hierynomus.sshj.userauth.certificate;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class Certificate<T extends PublicKey> implements PublicKey {
    private static final long serialVersionUID = 1;
    private final Map<String, String> critOptions;
    private final Map<String, String> extensions;
    private final String id;
    private final byte[] nonce;
    private final T publicKey;
    private final BigInteger serial;
    private final byte[] signature;
    private final byte[] signatureKey;
    private final long type;
    private final Date validAfter;
    private final Date validBefore;
    private final List<String> validPrincipals;

    public static class Builder<T extends PublicKey> {
        private Map<String, String> critOptions;
        private Map<String, String> extensions;
        private String id;
        private byte[] nonce;
        private T publicKey;
        private BigInteger serial;
        private byte[] signature;
        private byte[] signatureKey;
        private long type;
        private Date validAfter;
        private Date validBefore;
        private List<String> validPrincipals;

        public Certificate<T> build() {
            return new Certificate<>(this);
        }

        public Builder<T> critOptions(Map<String, String> map) {
            this.critOptions = map;
            return this;
        }

        public Builder<T> extensions(Map<String, String> map) {
            this.extensions = map;
            return this;
        }

        public Map<String, String> getCritOptions() {
            return this.critOptions;
        }

        public Map<String, String> getExtensions() {
            return this.extensions;
        }

        public String getId() {
            return this.id;
        }

        public byte[] getNonce() {
            return this.nonce;
        }

        public T getPublicKey() {
            return this.publicKey;
        }

        public BigInteger getSerial() {
            return this.serial;
        }

        public byte[] getSignature() {
            return this.signature;
        }

        public byte[] getSignatureKey() {
            return this.signatureKey;
        }

        public long getType() {
            return this.type;
        }

        public Date getValidAfter() {
            return this.validAfter;
        }

        public Date getValidBefore() {
            return this.validBefore;
        }

        public List<String> getValidPrincipals() {
            return this.validPrincipals;
        }

        public Builder<T> id(String str) {
            this.id = str;
            return this;
        }

        public Builder<T> nonce(byte[] bArr) {
            this.nonce = bArr;
            return this;
        }

        public Builder<T> publicKey(T t) {
            this.publicKey = t;
            return this;
        }

        public Builder<T> serial(BigInteger bigInteger) {
            this.serial = bigInteger;
            return this;
        }

        public Builder<T> signature(byte[] bArr) {
            this.signature = bArr;
            return this;
        }

        public Builder<T> signatureKey(byte[] bArr) {
            this.signatureKey = bArr;
            return this;
        }

        public Builder<T> type(long j) {
            this.type = j;
            return this;
        }

        public Builder<T> validAfter(Date date) {
            this.validAfter = date;
            return this;
        }

        public Builder<T> validBefore(Date date) {
            this.validBefore = date;
            return this;
        }

        public Builder<T> validPrincipals(List<String> list) {
            this.validPrincipals = list;
            return this;
        }
    }

    public Certificate(Builder<T> builder) {
        this.publicKey = (T) builder.getPublicKey();
        this.nonce = builder.getNonce();
        this.serial = builder.getSerial();
        this.type = builder.getType();
        this.id = builder.getId();
        this.validPrincipals = builder.getValidPrincipals();
        this.validAfter = builder.getValidAfter();
        this.validBefore = builder.getValidBefore();
        this.critOptions = builder.getCritOptions();
        this.extensions = builder.getExtensions();
        this.signatureKey = builder.getSignatureKey();
        this.signature = builder.getSignature();
    }

    public static <P extends PublicKey> Builder<P> getBuilder() {
        return new Builder<>();
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.publicKey.getAlgorithm();
    }

    public Map<String, String> getCritOptions() {
        return this.critOptions;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.publicKey.getEncoded();
    }

    public Map<String, String> getExtensions() {
        return this.extensions;
    }

    @Override // java.security.Key
    public String getFormat() {
        return this.publicKey.getFormat();
    }

    public String getId() {
        return this.id;
    }

    public T getKey() {
        return this.publicKey;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public BigInteger getSerial() {
        return this.serial;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public byte[] getSignatureKey() {
        return this.signatureKey;
    }

    public long getType() {
        return this.type;
    }

    public Date getValidAfter() {
        return this.validAfter;
    }

    public Date getValidBefore() {
        return this.validBefore;
    }

    public List<String> getValidPrincipals() {
        return this.validPrincipals;
    }
}
