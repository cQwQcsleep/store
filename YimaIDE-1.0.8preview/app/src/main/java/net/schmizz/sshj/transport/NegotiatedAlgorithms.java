package net.schmizz.sshj.transport;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class NegotiatedAlgorithms {
    private final String c2sCipher;
    private final String c2sComp;
    private final String c2sMAC;
    private final String kex;
    private final String s2cCipher;
    private final String s2cComp;
    private final String s2cMAC;
    private final String sig;

    public NegotiatedAlgorithms(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.kex = str;
        this.sig = str2;
        this.c2sCipher = str3;
        this.s2cCipher = str4;
        this.c2sMAC = str5;
        this.s2cMAC = str6;
        this.c2sComp = str7;
        this.s2cComp = str8;
    }

    public String getClient2ServerCipherAlgorithm() {
        return this.c2sCipher;
    }

    public String getClient2ServerCompressionAlgorithm() {
        return this.c2sComp;
    }

    public String getClient2ServerMACAlgorithm() {
        return this.c2sMAC;
    }

    public String getKeyExchangeAlgorithm() {
        return this.kex;
    }

    public String getServer2ClientCipherAlgorithm() {
        return this.s2cCipher;
    }

    public String getServer2ClientCompressionAlgorithm() {
        return this.s2cComp;
    }

    public String getServer2ClientMACAlgorithm() {
        return this.s2cMAC;
    }

    public String getSignatureAlgorithm() {
        return this.sig;
    }

    public String toString() {
        return "[ kex=" + this.kex + "; sig=" + this.sig + "; c2sCipher=" + this.c2sCipher + "; s2cCipher=" + this.s2cCipher + "; c2sMAC=" + this.c2sMAC + "; s2cMAC=" + this.s2cMAC + "; c2sComp=" + this.c2sComp + "; s2cComp=" + this.s2cComp + ";  ]";
    }
}
