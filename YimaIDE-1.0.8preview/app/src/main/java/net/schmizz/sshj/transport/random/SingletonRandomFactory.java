package net.schmizz.sshj.transport.random;

import net.schmizz.sshj.common.Factory;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class SingletonRandomFactory implements Random, Factory<Random> {
    private final Random random;

    public SingletonRandomFactory(Factory<Random> factory) {
        this.random = factory.create();
    }

    @Override // net.schmizz.sshj.transport.random.Random
    public void fill(byte[] bArr, int i, int i2) {
        this.random.fill(bArr, i, i2);
    }

    @Override // net.schmizz.sshj.common.Factory
    public Random create() {
        return this;
    }

    @Override // net.schmizz.sshj.transport.random.Random
    public void fill(byte[] bArr) {
        this.random.fill(bArr);
    }
}
