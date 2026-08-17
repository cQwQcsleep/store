package net.schmizz.sshj.transport.compression;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class NoneCompression implements Compression {

    public static class Factory implements net.schmizz.sshj.common.Factory.Named<Compression> {
        @Override // net.schmizz.sshj.common.Factory.Named
        public String getName() {
            return "none";
        }

        @Override // net.schmizz.sshj.common.Factory
        public Compression create() {
            return null;
        }
    }
}
