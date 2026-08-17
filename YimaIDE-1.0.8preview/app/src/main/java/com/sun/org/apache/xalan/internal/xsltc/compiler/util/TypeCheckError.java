package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeCheckError extends Exception {
    static final long serialVersionUID = 3246224233917854640L;
    ErrorMsg _error;
    SyntaxTreeNode _node;

    public TypeCheckError(String str, Object obj) {
        this._error = null;
        this._node = null;
        this._error = new ErrorMsg(str, obj);
    }

    public ErrorMsg getErrorMsg() {
        return this._error;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return toString();
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this._error == null) {
            if (this._node != null) {
                this._error = new ErrorMsg(ErrorMsg.TYPE_CHECK_ERR, this._node.toString());
            } else {
                this._error = new ErrorMsg(ErrorMsg.TYPE_CHECK_UNK_LOC_ERR);
            }
        }
        return this._error.toString();
    }

    public TypeCheckError(ErrorMsg errorMsg) {
        this._node = null;
        this._error = errorMsg;
    }

    public TypeCheckError(SyntaxTreeNode syntaxTreeNode) {
        this._error = null;
        this._node = syntaxTreeNode;
    }

    public TypeCheckError(String str, Object obj, Object obj2) {
        this._error = null;
        this._node = null;
        this._error = new ErrorMsg(str, obj, obj2);
    }
}
