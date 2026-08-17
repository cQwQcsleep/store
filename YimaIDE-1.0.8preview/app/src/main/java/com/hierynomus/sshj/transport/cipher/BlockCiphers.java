package com.hierynomus.sshj.transport.cipher;

import net.schmizz.sshj.transport.cipher.BlockCipher;
import net.schmizz.sshj.transport.cipher.Cipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class BlockCiphers {
    public static final String CIPHER_BLOCK_CHAINING_MODE = "CBC";
    public static final String COUNTER_MODE = "CTR";

    public static class Factory implements net.schmizz.sshj.common.Factory.Named<Cipher> {
        private String cipher;
        private int ivsize;
        private int keysize;
        private String mode;
        private String name;

        public Factory(int i, int i2, String str, String str2, String str3) {
            this.name = str;
            this.keysize = i2;
            this.cipher = str2;
            this.mode = str3;
            this.ivsize = i;
        }

        public Cipher create() {
            return new BlockCipher(this.ivsize, this.keysize / 8, this.cipher, this.cipher + "/" + this.mode + "/NoPadding");
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return getName();
        }
    }

    public static Factory AES128CBC() {
        return new Factory(16, 128, "aes128-cbc", "AES", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory AES128CTR() {
        return new Factory(16, 128, "aes128-ctr", "AES", COUNTER_MODE);
    }

    public static Factory AES192CBC() {
        return new Factory(16, 192, "aes192-cbc", "AES", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory AES192CTR() {
        return new Factory(16, 192, "aes192-ctr", "AES", COUNTER_MODE);
    }

    public static Factory AES256CBC() {
        return new Factory(16, 256, "aes256-cbc", "AES", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory AES256CTR() {
        return new Factory(16, 256, "aes256-ctr", "AES", COUNTER_MODE);
    }

    public static Factory BlowfishCBC() {
        return new Factory(8, 128, "blowfish-cbc", "Blowfish", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory BlowfishCTR() {
        return new Factory(8, 256, "blowfish-ctr", "Blowfish", COUNTER_MODE);
    }

    public static Factory Cast128CBC() {
        return new Factory(8, 128, "cast128-cbc", "CAST5", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Cast128CTR() {
        return new Factory(8, 128, "cast128-ctr", "CAST5", COUNTER_MODE);
    }

    public static Factory IDEACBC() {
        return new Factory(8, 128, "idea-cbc", "IDEA", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory IDEACTR() {
        return new Factory(8, 128, "idea-ctr", "IDEA", COUNTER_MODE);
    }

    public static Factory Serpent128CBC() {
        return new Factory(16, 128, "serpent128-cbc", "Serpent", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Serpent128CTR() {
        return new Factory(16, 128, "serpent128-ctr", "Serpent", COUNTER_MODE);
    }

    public static Factory Serpent192CBC() {
        return new Factory(16, 192, "serpent192-cbc", "Serpent", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Serpent192CTR() {
        return new Factory(16, 192, "serpent192-ctr", "Serpent", COUNTER_MODE);
    }

    public static Factory Serpent256CBC() {
        return new Factory(16, 256, "serpent256-cbc", "Serpent", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Serpent256CTR() {
        return new Factory(16, 256, "serpent256-ctr", "Serpent", COUNTER_MODE);
    }

    public static Factory TripleDESCBC() {
        return new Factory(8, 192, "3des-cbc", "DESede", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory TripleDESCTR() {
        return new Factory(8, 192, "3des-ctr", "DESede", COUNTER_MODE);
    }

    public static Factory Twofish128CBC() {
        return new Factory(16, 128, "twofish128-cbc", "Twofish", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Twofish128CTR() {
        return new Factory(16, 128, "twofish128-ctr", "Twofish", COUNTER_MODE);
    }

    public static Factory Twofish192CBC() {
        return new Factory(16, 192, "twofish192-cbc", "Twofish", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Twofish192CTR() {
        return new Factory(16, 192, "twofish192-ctr", "Twofish", COUNTER_MODE);
    }

    public static Factory Twofish256CBC() {
        return new Factory(16, 256, "twofish256-cbc", "Twofish", CIPHER_BLOCK_CHAINING_MODE);
    }

    public static Factory Twofish256CTR() {
        return new Factory(16, 256, "twofish256-ctr", "Twofish", COUNTER_MODE);
    }

    public static Factory TwofishCBC() {
        return new Factory(16, 256, "twofish-cbc", "Twofish", CIPHER_BLOCK_CHAINING_MODE);
    }
}
