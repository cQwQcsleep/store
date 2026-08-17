package com.sun.org.apache.xerces.internal.impl.xs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSGrammarBucket {
    Map<String, SchemaGrammar> fGrammarRegistry = new HashMap();
    SchemaGrammar fNoNSGrammar = null;

    public SchemaGrammar getGrammar(String str) {
        return str == null ? this.fNoNSGrammar : this.fGrammarRegistry.get(str);
    }

    public SchemaGrammar[] getGrammars() {
        int i = 0;
        int size = this.fGrammarRegistry.size() + (this.fNoNSGrammar == null ? 0 : 1);
        SchemaGrammar[] schemaGrammarArr = new SchemaGrammar[size];
        Iterator<Map.Entry<String, SchemaGrammar>> it = this.fGrammarRegistry.entrySet().iterator();
        while (it.hasNext()) {
            schemaGrammarArr[i] = it.next().getValue();
            i++;
        }
        SchemaGrammar schemaGrammar = this.fNoNSGrammar;
        if (schemaGrammar != null) {
            schemaGrammarArr[size - 1] = schemaGrammar;
        }
        return schemaGrammarArr;
    }

    public boolean putGrammar(SchemaGrammar schemaGrammar, boolean z) {
        SchemaGrammar grammar = getGrammar(schemaGrammar.fTargetNamespace);
        if (grammar != null) {
            return grammar == schemaGrammar;
        }
        if (!z) {
            putGrammar(schemaGrammar);
            return true;
        }
        ArrayList arrayList = (ArrayList) schemaGrammar.getImportedGrammars();
        if (arrayList == null) {
            putGrammar(schemaGrammar);
            return true;
        }
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        for (int i = 0; i < arrayList2.size(); i++) {
            SchemaGrammar schemaGrammar2 = (SchemaGrammar) arrayList2.get(i);
            SchemaGrammar grammar2 = getGrammar(schemaGrammar2.fTargetNamespace);
            if (grammar2 == null) {
                List<SchemaGrammar> importedGrammars = schemaGrammar2.getImportedGrammars();
                if (importedGrammars != null) {
                    for (int size = importedGrammars.size() - 1; size >= 0; size--) {
                        SchemaGrammar schemaGrammar3 = importedGrammars.get(size);
                        if (!arrayList2.contains(schemaGrammar3)) {
                            arrayList2.add(schemaGrammar3);
                        }
                    }
                }
            } else if (grammar2 != schemaGrammar2) {
                return false;
            }
        }
        putGrammar(schemaGrammar);
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            putGrammar((SchemaGrammar) arrayList2.get(size2));
        }
        return true;
    }

    public void reset() {
        this.fNoNSGrammar = null;
        this.fGrammarRegistry.clear();
    }

    public void putGrammar(SchemaGrammar schemaGrammar) {
        if (schemaGrammar.getTargetNamespace() == null) {
            this.fNoNSGrammar = schemaGrammar;
        } else {
            this.fGrammarRegistry.put(schemaGrammar.getTargetNamespace(), schemaGrammar);
        }
    }

    public boolean putGrammar(SchemaGrammar schemaGrammar, boolean z, boolean z2) {
        ArrayList arrayList;
        if (!z2) {
            return putGrammar(schemaGrammar, z);
        }
        if (getGrammar(schemaGrammar.fTargetNamespace) == null) {
            putGrammar(schemaGrammar);
        }
        if (!z || (arrayList = (ArrayList) schemaGrammar.getImportedGrammars()) == null) {
            return true;
        }
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        for (int i = 0; i < arrayList2.size(); i++) {
            SchemaGrammar schemaGrammar2 = (SchemaGrammar) arrayList2.get(i);
            if (getGrammar(schemaGrammar2.fTargetNamespace) == null) {
                List<SchemaGrammar> importedGrammars = schemaGrammar2.getImportedGrammars();
                if (importedGrammars != null) {
                    for (int size = importedGrammars.size() - 1; size >= 0; size--) {
                        SchemaGrammar schemaGrammar3 = importedGrammars.get(size);
                        if (!arrayList2.contains(schemaGrammar3)) {
                            arrayList2.add(schemaGrammar3);
                        }
                    }
                }
            } else {
                arrayList2.remove(schemaGrammar2);
            }
        }
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            putGrammar((SchemaGrammar) arrayList2.get(size2));
        }
        return true;
    }
}
