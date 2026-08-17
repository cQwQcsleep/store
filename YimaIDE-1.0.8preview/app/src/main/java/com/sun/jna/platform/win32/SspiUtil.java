package com.sun.jna.platform.win32;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SspiUtil {

    public static class ManagedSecBufferDesc extends Sspi.SecBufferDesc {
        private final Sspi.SecBuffer[] secBuffers;

        public ManagedSecBufferDesc(int i) {
            this.cBuffers = i;
            Sspi.SecBuffer[] secBufferArr = (Sspi.SecBuffer[]) new Sspi.SecBuffer().toArray(i);
            this.secBuffers = secBufferArr;
            this.pBuffers = secBufferArr[0].getPointer();
            this.cBuffers = secBufferArr.length;
        }

        public Sspi.SecBuffer getBuffer(int i) {
            return this.secBuffers[i];
        }

        @Override // com.sun.jna.Structure
        public void read() {
            for (Sspi.SecBuffer secBuffer : this.secBuffers) {
                secBuffer.read();
            }
        }

        @Override // com.sun.jna.Structure
        public void write() {
            for (Sspi.SecBuffer secBuffer : this.secBuffers) {
                secBuffer.write();
            }
            writeField("ulVersion");
            writeField("pBuffers");
            writeField("cBuffers");
        }

        public ManagedSecBufferDesc(int i, int i2) {
            Sspi.SecBuffer[] secBufferArr = {new Sspi.SecBuffer(i, i2)};
            this.secBuffers = secBufferArr;
            this.pBuffers = secBufferArr[0].getPointer();
            this.cBuffers = secBufferArr.length;
        }

        public ManagedSecBufferDesc(int i, byte[] bArr) {
            Sspi.SecBuffer[] secBufferArr = {new Sspi.SecBuffer(i, bArr)};
            this.secBuffers = secBufferArr;
            this.pBuffers = secBufferArr[0].getPointer();
            this.cBuffers = secBufferArr.length;
        }
    }
}
