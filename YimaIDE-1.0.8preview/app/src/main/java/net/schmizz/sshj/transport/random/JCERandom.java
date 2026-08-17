package net.schmizz.sshj.transport.random;

import java.security.SecureRandom;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class JCERandom implements Random {
    private static final Logger logger = LoggerFactory.getLogger(JCERandom.class);
    private final SecureRandom random;
    private byte[] tmp = new byte[16];

    public static class Factory implements net.schmizz.sshj.common.Factory.Named<Random> {
        @Override // net.schmizz.sshj.common.Factory
        public Random create() {
            return new JCERandom();
        }

        @Override // net.schmizz.sshj.common.Factory.Named
        public String getName() {
            return TokenStreamRewriter.DEFAULT_PROGRAM_NAME;
        }
    }

    public JCERandom() {
        Logger logger2 = logger;
        logger2.info("Creating new SecureRandom.");
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.random = new SecureRandom();
        logger2.debug("Random creation took {} ms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0014 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:10:0x000f, B:12:0x0014, B:15:0x001b, B:16:0x0028), top: B:23:0x000f, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:23:0x000f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x000e A[Catch: all -> 0x000c, TRY_LEAVE, TryCatch #1 {all -> 0x000c, blocks: (B:4:0x0003, B:6:0x0006, B:9:0x000e, B:20:0x002c, B:10:0x000f, B:12:0x0014, B:15:0x001b, B:16:0x0028), top: B:25:0x0003, inners: #0 }] */
    @Override // net.schmizz.sshj.transport.random.Random
    public synchronized void fill(byte[] bArr, int i, int i2) {
        if (i == 0) {
            try {
                if (i2 == bArr.length) {
                    this.random.nextBytes(bArr);
                } else {
                    synchronized (this) {
                        try {
                            if (i2 > this.tmp.length) {
                                this.tmp = new byte[i2];
                            }
                            this.random.nextBytes(this.tmp);
                            System.arraycopy(this.tmp, 0, bArr, i, i2);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } else {
            synchronized (this) {
                if (i2 > this.tmp.length) {
                    this.tmp = new byte[i2];
                }
                this.random.nextBytes(this.tmp);
                System.arraycopy(this.tmp, 0, bArr, i, i2);
            }
        }
    }

    @Override // net.schmizz.sshj.transport.random.Random
    public void fill(byte[] bArr) {
        this.random.nextBytes(bArr);
    }
}
