package com.sun.org.apache.xalan.internal.xsltc.runtime.output;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class StringOutputBuffer implements OutputBuffer {
    private StringBuffer _buffer = new StringBuffer();

    @Override // com.sun.org.apache.xalan.internal.xsltc.runtime.output.OutputBuffer
    public OutputBuffer append(String str) {
        this._buffer.append(str);
        return this;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.runtime.output.OutputBuffer
    public String close() {
        return this._buffer.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.runtime.output.OutputBuffer
    public OutputBuffer append(char[] cArr, int i, int i2) {
        this._buffer.append(cArr, i, i2);
        return this;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.runtime.output.OutputBuffer
    public OutputBuffer append(char c) {
        this._buffer.append(c);
        return this;
    }
}
