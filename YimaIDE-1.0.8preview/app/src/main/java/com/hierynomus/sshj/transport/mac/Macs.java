package com.hierynomus.sshj.transport.mac;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class Macs {
    public static Factory HMACMD5() {
        return new Factory("hmac-md5", "HmacMD5", 16, 16, false);
    }

    public static Factory HMACMD596() {
        return new Factory("hmac-md5-96", "HmacMD5", 12, 16, false);
    }

    public static Factory HMACMD596Etm() {
        return new Factory("hmac-md5-96-etm@openssh.com", "HmacMD5", 12, 16, true);
    }

    public static Factory HMACMD5Etm() {
        return new Factory("hmac-md5-etm@openssh.com", "HmacMD5", 16, 16, true);
    }

    public static Factory HMACRIPEMD160() {
        return new Factory("hmac-ripemd160", "HMACRIPEMD160", 20, 20, false);
    }

    public static Factory HMACRIPEMD16096() {
        return new Factory("hmac-ripemd160-96", "HMACRIPEMD160", 12, 20, false);
    }

    public static Factory HMACRIPEMD160Etm() {
        return new Factory("hmac-ripemd160-etm@openssh.com", "HMACRIPEMD160", 20, 20, true);
    }

    public static Factory HMACRIPEMD160OpenSsh() {
        return new Factory("hmac-ripemd160@openssh.com", "HMACRIPEMD160", 20, 20, false);
    }

    public static Factory HMACSHA1() {
        return new Factory("hmac-sha1", "HmacSHA1", 20, 20, false);
    }

    public static Factory HMACSHA196() {
        return new Factory("hmac-sha1-96", "HmacSHA1", 12, 20, false);
    }

    public static Factory HMACSHA196Etm() {
        return new Factory("hmac-sha1-96@openssh.com", "HmacSHA1", 12, 20, true);
    }

    public static Factory HMACSHA1Etm() {
        return new Factory("hmac-sha1-etm@openssh.com", "HmacSHA1", 20, 20, true);
    }

    public static Factory HMACSHA2256() {
        return new Factory("hmac-sha2-256", "HmacSHA256", 32, 32, false);
    }

    public static Factory HMACSHA2256Etm() {
        return new Factory("hmac-sha2-256-etm@openssh.com", "HmacSHA256", 32, 32, true);
    }

    public static Factory HMACSHA2512() {
        return new Factory("hmac-sha2-512", "HmacSHA512", 64, 64, false);
    }

    public static Factory HMACSHA2512Etm() {
        return new Factory("hmac-sha2-512-etm@openssh.com", "HmacSHA512", 64, 64, true);
    }
}
