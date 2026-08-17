package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ClassGen;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassGenerator extends ClassGen {
    protected static int INVALID_INDEX = -1;
    protected static final int TRANSLET_INDEX = 0;
    private final Instruction _aloadTranslet;
    private final String _applyTemplatesSig;
    private final String _applyTemplatesSigForImport;
    private final String _domClass;
    private final String _domClassSig;
    private final Parser _parser;
    private Stylesheet _stylesheet;

    public ClassGenerator(String str, String str2, String str3, int i, String[] strArr, Stylesheet stylesheet) {
        super(str, str2, str3, i, strArr);
        this._stylesheet = stylesheet;
        this._parser = stylesheet.getParser();
        this._aloadTranslet = new ALOAD(0);
        if (stylesheet.isMultiDocument()) {
            this._domClass = Constants.MULTI_DOM_CLASS;
            this._domClassSig = Constants.MULTI_DOM_SIG;
        } else {
            this._domClass = "com.sun.org.apache.xalan.internal.xsltc.dom.DOMAdapter";
            this._domClassSig = Constants.DOM_ADAPTER_SIG;
        }
        this._applyTemplatesSig = "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V";
        this._applyTemplatesSigForImport = Constants.ATTR_SET_SIG;
    }

    public void addMethod(MethodGenerator methodGenerator) {
        for (Method method : methodGenerator.getGeneratedMethods(this)) {
            addMethod(method);
        }
    }

    public final String getApplyTemplatesSig() {
        return this._applyTemplatesSig;
    }

    public final String getApplyTemplatesSigForImport() {
        return this._applyTemplatesSigForImport;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ClassGen
    public final String getClassName() {
        return this._stylesheet.getClassName();
    }

    public final String getDOMClass() {
        return this._domClass;
    }

    public final String getDOMClassSig() {
        return this._domClassSig;
    }

    public final Parser getParser() {
        return this._parser;
    }

    public final Stylesheet getStylesheet() {
        return this._stylesheet;
    }

    public boolean isExternal() {
        return false;
    }

    public Instruction loadTranslet() {
        return this._aloadTranslet;
    }
}
