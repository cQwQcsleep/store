package org.bouncycastle.pqc.crypto.picnic;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class Signature {
    final byte[] challengeBits;
    final Proof[] proofs;
    final byte[] salt = new byte[32];

    public static class Proof {
        final byte[] communicatedBits;
        final int[] inputShare;
        final byte[] seed1;
        final byte[] seed2;
        final byte[] view3Commitment;
        final byte[] view3UnruhG;

        public Proof(PicnicEngine picnicEngine) {
            int i = picnicEngine.seedSizeBytes;
            this.seed1 = new byte[i];
            this.seed2 = new byte[i];
            this.inputShare = new int[picnicEngine.stateSizeWords];
            this.communicatedBits = new byte[picnicEngine.andSizeBytes];
            this.view3Commitment = new byte[picnicEngine.digestSizeBytes];
            int i2 = picnicEngine.UnruhGWithInputBytes;
            if (i2 > 0) {
                this.view3UnruhG = new byte[i2];
            } else {
                this.view3UnruhG = null;
            }
        }
    }

    public Signature(PicnicEngine picnicEngine) {
        this.challengeBits = new byte[Utils.numBytes(picnicEngine.numMPCRounds * 2)];
        this.proofs = new Proof[picnicEngine.numMPCRounds];
        int i = 0;
        while (true) {
            Proof[] proofArr = this.proofs;
            if (i >= proofArr.length) {
                return;
            }
            proofArr[i] = new Proof(picnicEngine);
            i++;
        }
    }
}
