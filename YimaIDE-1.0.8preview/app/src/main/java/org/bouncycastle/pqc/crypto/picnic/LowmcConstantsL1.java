package org.bouncycastle.pqc.crypto.picnic;

import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import org.bouncycastle.util.Exceptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class LowmcConstantsL1 extends LowmcConstants {
    public LowmcConstantsL1() {
        try {
            DataInputStream dataInputStream = new DataInputStream(new GZIPInputStream(LowmcConstants.class.getResourceAsStream("lowmcL1.bin.properties")));
            this.linearMatrices = LowmcConstants.readArray(dataInputStream);
            this.roundConstants = LowmcConstants.readArray(dataInputStream);
            this.keyMatrices = LowmcConstants.readArray(dataInputStream);
            this.linearMatrices_full = LowmcConstants.readArray(dataInputStream);
            this.keyMatrices_full = LowmcConstants.readArray(dataInputStream);
            this.keyMatrices_inv = LowmcConstants.readArray(dataInputStream);
            this.linearMatrices_inv = LowmcConstants.readArray(dataInputStream);
            this.roundConstants_full = LowmcConstants.readArray(dataInputStream);
            this.LMatrix = new KMatrices(20, CodeEditor.FLAG_DRAW_SOFT_WRAP, 4, this.linearMatrices);
            this.KMatrix = new KMatrices(21, CodeEditor.FLAG_DRAW_SOFT_WRAP, 4, this.keyMatrices);
            this.RConstants = new KMatrices(0, 1, 4, this.roundConstants);
            this.LMatrix_full = new KMatrices(4, 129, 5, this.linearMatrices_full);
            this.LMatrix_inv = new KMatrices(4, 129, 5, this.linearMatrices_inv);
            this.KMatrix_full = new KMatrices(5, 129, 5, this.keyMatrices_full);
            this.KMatrix_inv = new KMatrices(1, 129, 5, this.keyMatrices_inv);
            this.RConstants_full = new KMatrices(4, 1, 5, this.roundConstants_full);
        } catch (IOException e) {
            throw Exceptions.illegalStateException("unable to load Picnic properties: " + e.getMessage(), e);
        }
    }
}
