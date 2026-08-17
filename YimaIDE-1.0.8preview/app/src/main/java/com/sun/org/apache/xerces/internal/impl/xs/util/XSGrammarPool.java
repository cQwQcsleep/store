package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelImpl;
import com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSGrammarPool extends XMLGrammarPoolImpl {
    public XSModel toXSModel(short s) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            XMLGrammarPoolImpl.Entry[] entryArr = this.fGrammars;
            if (i >= entryArr.length) {
                break;
            }
            for (XMLGrammarPoolImpl.Entry entry = entryArr[i]; entry != null; entry = entry.next) {
                if (entry.desc.getGrammarType().equals("http://www.w3.org/2001/XMLSchema")) {
                    arrayList.add(entry.grammar);
                }
            }
            i++;
        }
        int size = arrayList.size();
        return size == 0 ? toXSModel(new SchemaGrammar[0], s) : toXSModel((SchemaGrammar[]) arrayList.toArray(new SchemaGrammar[size]), s);
    }

    public XSModel toXSModel() {
        return toXSModel((short) 1);
    }

    public XSModel toXSModel(SchemaGrammar[] schemaGrammarArr, short s) {
        return new XSModelImpl(schemaGrammarArr, s);
    }
}
