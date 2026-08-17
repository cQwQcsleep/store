package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xs.XSObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSInputSource extends XMLInputSource {
    private XSObject[] fComponents;
    private SchemaGrammar[] fGrammars;

    public XSInputSource(SchemaGrammar[] schemaGrammarArr) {
        super(null, null, null, false);
        this.fGrammars = schemaGrammarArr;
        this.fComponents = null;
    }

    public XSObject[] getComponents() {
        return this.fComponents;
    }

    public SchemaGrammar[] getGrammars() {
        return this.fGrammars;
    }

    public void setComponents(XSObject[] xSObjectArr) {
        this.fComponents = xSObjectArr;
    }

    public void setGrammars(SchemaGrammar[] schemaGrammarArr) {
        this.fGrammars = schemaGrammarArr;
    }

    public XSInputSource(XSObject[] xSObjectArr) {
        super(null, null, null, false);
        this.fGrammars = null;
        this.fComponents = xSObjectArr;
    }
}
