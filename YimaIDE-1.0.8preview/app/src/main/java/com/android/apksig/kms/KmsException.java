package com.android.apksig.kms;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class KmsException extends RuntimeException {
    private final String mKmsType;

    public KmsException(String str, String str2) {
        super(str2);
        this.mKmsType = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "KMS " + this.mKmsType + " threw exception: " + super.getMessage();
    }

    public KmsException(String str, String str2, Throwable th) {
        super(str2, th);
        this.mKmsType = str;
    }

    public KmsException(String str, Throwable th) {
        super(th);
        this.mKmsType = str;
    }
}
