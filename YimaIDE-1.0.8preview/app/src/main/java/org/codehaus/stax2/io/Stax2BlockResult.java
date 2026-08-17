package org.codehaus.stax2.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class Stax2BlockResult extends Stax2Result {
    @Override // org.codehaus.stax2.io.Stax2Result
    public abstract OutputStream constructOutputStream() throws IOException;

    @Override // org.codehaus.stax2.io.Stax2Result
    public abstract Writer constructWriter() throws IOException;
}
