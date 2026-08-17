package com.sun.org.apache.xerces.internal.impl.validation;

import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ValidationState implements ValidationContext {
    private List<String> fIdRefList;
    private HashSet<String> fIds;
    private boolean fExtraChecking = true;
    private boolean fFacetChecking = true;
    private boolean fNormalize = true;
    private boolean fNamespaces = true;
    private EntityState fEntityState = null;
    private NamespaceContext fNamespaceContext = null;
    private SymbolTable fSymbolTable = null;
    private Locale fLocale = null;

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public void addId(String str) {
        if (this.fIds == null) {
            this.fIds = new HashSet<>();
        }
        this.fIds.add(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public void addIdRef(String str) {
        if (this.fIdRefList == null) {
            this.fIdRefList = new ArrayList();
        }
        this.fIdRefList.add(str);
    }

    public Iterator<String> checkIDRefID() {
        HashSet hashSet;
        if (this.fIdRefList != null) {
            hashSet = null;
            for (int i = 0; i < this.fIdRefList.size(); i++) {
                String str = this.fIdRefList.get(i);
                HashSet<String> hashSet2 = this.fIds;
                if (hashSet2 == null || !hashSet2.contains(str)) {
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(str);
                }
            }
        } else {
            hashSet = null;
        }
        if (hashSet != null) {
            return hashSet.iterator();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public Locale getLocale() {
        return this.fLocale;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public String getSymbol(String str) {
        SymbolTable symbolTable = this.fSymbolTable;
        return symbolTable != null ? symbolTable.addSymbol(str) : str.intern();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public String getURI(String str) {
        NamespaceContext namespaceContext = this.fNamespaceContext;
        if (namespaceContext != null) {
            return namespaceContext.getURI(str);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean isEntityDeclared(String str) {
        EntityState entityState = this.fEntityState;
        if (entityState != null) {
            return entityState.isEntityDeclared(getSymbol(str));
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean isEntityUnparsed(String str) {
        EntityState entityState = this.fEntityState;
        if (entityState != null) {
            return entityState.isEntityUnparsed(getSymbol(str));
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean isIdDeclared(String str) {
        HashSet<String> hashSet = this.fIds;
        return hashSet != null && hashSet.contains(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean needExtraChecking() {
        return this.fExtraChecking;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean needFacetChecking() {
        return this.fFacetChecking;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean needToNormalize() {
        return this.fNormalize;
    }

    public void reset() {
        this.fExtraChecking = true;
        this.fFacetChecking = true;
        this.fNamespaces = true;
        this.fIds = null;
        this.fIdRefList = null;
        this.fEntityState = null;
        this.fNamespaceContext = null;
        this.fSymbolTable = null;
    }

    public void resetIDTables() {
        this.fIds = null;
        this.fIdRefList = null;
    }

    public void setEntityState(EntityState entityState) {
        this.fEntityState = entityState;
    }

    public void setExtraChecking(boolean z) {
        this.fExtraChecking = z;
    }

    public void setFacetChecking(boolean z) {
        this.fFacetChecking = z;
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
    }

    public void setNamespaceSupport(NamespaceContext namespaceContext) {
        this.fNamespaceContext = namespaceContext;
    }

    public void setNormalizationRequired(boolean z) {
        this.fNormalize = z;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public void setUsingNamespaces(boolean z) {
        this.fNamespaces = z;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.ValidationContext
    public boolean useNamespaces() {
        return this.fNamespaces;
    }
}
