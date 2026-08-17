package com.sun.org.apache.xalan.internal.xsltc.compiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CompilerException extends Exception {
    static final long serialVersionUID = 1732939618562742663L;
    private String _msg;

    public CompilerException(Exception exc) {
        super(exc.toString());
        this._msg = exc.toString();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        int iIndexOf = this._msg.indexOf(58);
        String str = this._msg;
        return iIndexOf > -1 ? str.substring(iIndexOf) : str;
    }

    public CompilerException() {
    }

    public CompilerException(String str) {
        super(str);
        this._msg = str;
    }
}
