package com.sun.org.apache.xpath.internal.compiler;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.utils.ConfigurationError;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xpath.internal.functions.Function;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncLoader {
    private int m_funcID;
    private String m_funcName;

    public FuncLoader(String str, int i) {
        this.m_funcID = i;
        this.m_funcName = str;
    }

    public Function getFunction() throws TransformerException {
        try {
            String strConcat = this.m_funcName;
            if (strConcat.indexOf(Constants.ATTRVAL_THIS) < 0) {
                strConcat = "com.sun.org.apache.xpath.internal.functions.".concat(strConcat);
            }
            String strSubstring = strConcat.substring(0, strConcat.lastIndexOf(46));
            if (!strSubstring.equals("com.sun.org.apache.xalan.internal.templates") && !strSubstring.equals("com.sun.org.apache.xpath.internal.functions")) {
                throw new TransformerException("Application can't install his own xpath function.");
            }
            return (Function) ObjectFactory.newInstance(strConcat, true);
        } catch (ConfigurationError e) {
            throw new TransformerException(e.getException());
        }
    }

    public String getName() {
        return this.m_funcName;
    }
}
