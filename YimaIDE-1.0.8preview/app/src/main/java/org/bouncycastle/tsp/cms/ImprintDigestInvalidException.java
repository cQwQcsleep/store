package org.bouncycastle.tsp.cms;

import org.bouncycastle.tsp.TimeStampToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ImprintDigestInvalidException extends Exception {
    private TimeStampToken token;

    public ImprintDigestInvalidException(String str, TimeStampToken timeStampToken) {
        super(str);
        this.token = timeStampToken;
    }

    public TimeStampToken getTimeStampToken() {
        return this.token;
    }
}
