package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMap4Types;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSElementDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSIDCDefinition;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import com.sun.org.apache.xerces.internal.xs.XSModelGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSNamedMap;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItemList;
import com.sun.org.apache.xerces.internal.xs.XSNotationDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSModelImpl extends AbstractList<XSNamespaceItem> implements XSModel, XSNamespaceItemList {
    private static final boolean[] GLOBAL_COMP = {false, true, true, true, false, true, true, false, false, false, true, true, false, false, false, true, true};
    private static final short MAX_COMP_IDX = 16;
    private XSObjectList fAnnotations;
    private final XSNamedMap[] fGlobalComponents;
    private final int fGrammarCount;
    private final SchemaGrammar[] fGrammarList;
    private final SymbolHash fGrammarMap;
    private final boolean fHasIDC;
    private final XSNamedMap[][] fNSComponents;
    private final String[] fNamespaces;
    private final StringList fNamespacesList;
    private final SymbolHash fSubGroupMap;

    public XSModelImpl(SchemaGrammar[] schemaGrammarArr, short s) {
        this.fAnnotations = null;
        int length = schemaGrammarArr.length;
        int i = length + 1;
        int iMax = Math.max(i, 5);
        String[] strArr = new String[iMax];
        SchemaGrammar[] schemaGrammarArr2 = new SchemaGrammar[iMax];
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            SchemaGrammar schemaGrammar = schemaGrammarArr[i2];
            String targetNamespace = schemaGrammar.getTargetNamespace();
            strArr[i2] = targetNamespace;
            schemaGrammarArr2[i2] = schemaGrammar;
            if (targetNamespace == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
                z = true;
            }
        }
        if (!z) {
            strArr[length] = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            schemaGrammarArr2[length] = SchemaGrammar.getS4SGrammar(s);
            length = i;
        }
        for (int i3 = 0; i3 < length; i3++) {
            List<SchemaGrammar> importedGrammars = schemaGrammarArr2[i3].getImportedGrammars();
            for (int size = importedGrammars == null ? -1 : importedGrammars.size() - 1; size >= 0; size--) {
                SchemaGrammar schemaGrammar2 = importedGrammars.get(size);
                int i4 = 0;
                while (i4 < length && schemaGrammar2 != schemaGrammarArr2[i4]) {
                    i4++;
                }
                if (i4 == length) {
                    if (length == schemaGrammarArr2.length) {
                        int i5 = length * 2;
                        String[] strArr2 = new String[i5];
                        System.arraycopy(strArr, 0, strArr2, 0, length);
                        SchemaGrammar[] schemaGrammarArr3 = new SchemaGrammar[i5];
                        System.arraycopy(schemaGrammarArr2, 0, schemaGrammarArr3, 0, length);
                        schemaGrammarArr2 = schemaGrammarArr3;
                        strArr = strArr2;
                    }
                    strArr[length] = schemaGrammar2.getTargetNamespace();
                    schemaGrammarArr2[length] = schemaGrammar2;
                    length++;
                }
            }
        }
        this.fNamespaces = strArr;
        this.fGrammarList = schemaGrammarArr2;
        this.fGrammarMap = new SymbolHash(length * 2);
        boolean z2 = false;
        for (int i6 = 0; i6 < length; i6++) {
            this.fGrammarMap.put(null2EmptyString(this.fNamespaces[i6]), this.fGrammarList[i6]);
            if (this.fGrammarList[i6].hasIDConstraints()) {
                z2 = true;
            }
        }
        this.fHasIDC = z2;
        this.fGrammarCount = length;
        this.fGlobalComponents = new XSNamedMap[17];
        this.fNSComponents = (XSNamedMap[][]) Array.newInstance((Class<?>) XSNamedMap.class, length, 17);
        this.fNamespacesList = new StringListImpl(this.fNamespaces, length);
        this.fSubGroupMap = buildSubGroups();
    }

    private SymbolHash buildSubGroups() {
        SubstitutionGroupHandler substitutionGroupHandler = new SubstitutionGroupHandler(null);
        for (int i = 0; i < this.fGrammarCount; i++) {
            substitutionGroupHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        XSObjectListImpl globalElements = getGlobalElements();
        int length = globalElements.getLength();
        SymbolHash symbolHash = new SymbolHash(length * 2);
        for (int i2 = 0; i2 < length; i2++) {
            XSElementDecl xSElementDecl = (XSElementDecl) globalElements.item(i2);
            XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xSElementDecl);
            symbolHash.put(xSElementDecl, substitutionGroup.length > 0 ? new XSObjectListImpl(substitutionGroup, substitutionGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
        return symbolHash;
    }

    private SymbolHash buildSubGroups_Org() {
        SubstitutionGroupHandler substitutionGroupHandler = new SubstitutionGroupHandler(null);
        for (int i = 0; i < this.fGrammarCount; i++) {
            substitutionGroupHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        XSNamedMap components = getComponents((short) 2);
        int length = components.getLength();
        SymbolHash symbolHash = new SymbolHash(length * 2);
        for (int i2 = 0; i2 < length; i2++) {
            XSElementDecl xSElementDecl = (XSElementDecl) components.item(i2);
            XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xSElementDecl);
            symbolHash.put(xSElementDecl, substitutionGroup.length > 0 ? new XSObjectListImpl(substitutionGroup, substitutionGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
        return symbolHash;
    }

    private XSObjectListImpl getGlobalElements() {
        SymbolHash[] symbolHashArr = new SymbolHash[this.fGrammarCount];
        int length = 0;
        for (int i = 0; i < this.fGrammarCount; i++) {
            SymbolHash symbolHash = this.fGrammarList[i].fAllGlobalElemDecls;
            symbolHashArr[i] = symbolHash;
            length += symbolHash.getLength();
        }
        if (length == 0) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        XSObject[] xSObjectArr = new XSObject[length];
        int length2 = 0;
        for (int i2 = 0; i2 < this.fGrammarCount; i2++) {
            symbolHashArr[i2].getValues(xSObjectArr, length2);
            length2 += symbolHashArr[i2].getLength();
        }
        return new XSObjectListImpl(xSObjectArr, length);
    }

    private ListIterator<XSNamespaceItem> listIterator0(int i) {
        return new XSNamespaceItemListIterator(i);
    }

    private static final String null2EmptyString(String str) {
        return str == null ? XMLSymbols.EMPTY_STRING : str;
    }

    private void toArray0(Object[] objArr) {
        int i = this.fGrammarCount;
        if (i > 0) {
            System.arraycopy(this.fGrammarList, 0, objArr, 0, i);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public XSNamespaceItem get(int i) {
        if (i >= 0 && i < this.fGrammarCount) {
            return this.fGrammarList[i];
        }
        b1e.a("Index: ", i);
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public synchronized XSObjectList getAnnotations() {
        try {
            XSObjectList xSObjectList = this.fAnnotations;
            if (xSObjectList != null) {
                return xSObjectList;
            }
            int i = 0;
            for (int i2 = 0; i2 < this.fGrammarCount; i2++) {
                i += this.fGrammarList[i2].fNumAnnotations;
            }
            if (i == 0) {
                XSObjectListImpl xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
                this.fAnnotations = xSObjectListImpl;
                return xSObjectListImpl;
            }
            XSAnnotationImpl[] xSAnnotationImplArr = new XSAnnotationImpl[i];
            int i3 = 0;
            for (int i4 = 0; i4 < this.fGrammarCount; i4++) {
                SchemaGrammar schemaGrammar = this.fGrammarList[i4];
                int i5 = schemaGrammar.fNumAnnotations;
                if (i5 > 0) {
                    System.arraycopy(schemaGrammar.fAnnotations, 0, xSAnnotationImplArr, i3, i5);
                    i3 += schemaGrammar.fNumAnnotations;
                }
            }
            XSObjectListImpl xSObjectListImpl2 = new XSObjectListImpl(xSAnnotationImplArr, i);
            this.fAnnotations = xSObjectListImpl2;
            return xSObjectListImpl2;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSAttributeDeclaration getAttributeDeclaration(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSAttributeDeclaration) schemaGrammar.fGlobalAttrDecls.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSAttributeGroupDefinition getAttributeGroup(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSAttributeGroupDefinition) schemaGrammar.fGlobalAttrGrpDecls.get(str);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0062 A[Catch: all -> 0x0045, TryCatch #0 {all -> 0x0045, blocks: (B:6:0x0007, B:9:0x000f, B:12:0x001a, B:40:0x007c, B:31:0x003c, B:34:0x0047, B:35:0x0050, B:36:0x0059, B:37:0x0062, B:38:0x006b, B:39:0x0074, B:44:0x0084, B:45:0x0092, B:46:0x009f, B:49:0x00a5), top: B:54:0x0007 }] */
    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public synchronized XSNamedMap getComponents(short s) {
        if (s > 0 && s <= 16) {
            try {
                if (GLOBAL_COMP[s]) {
                    SymbolHash[] symbolHashArr = new SymbolHash[this.fGrammarCount];
                    if (this.fGlobalComponents[s] == null) {
                        for (int i = 0; i < this.fGrammarCount; i++) {
                            if (s == 1) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalAttrDecls;
                            } else if (s == 2) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalElemDecls;
                            } else if (s == 3) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalTypeDecls;
                            } else if (s == 5) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalAttrGrpDecls;
                            } else if (s == 6) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalGroupDecls;
                            } else if (s == 10) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalIDConstraintDecls;
                            } else if (s == 11) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalNotationDecls;
                            } else if (s == 15 || s == 16) {
                                symbolHashArr[i] = this.fGrammarList[i].fGlobalTypeDecls;
                            }
                        }
                        if (s == 15 || s == 16) {
                            this.fGlobalComponents[s] = new XSNamedMap4Types(this.fNamespaces, symbolHashArr, this.fGrammarCount, s);
                        } else {
                            this.fGlobalComponents[s] = new XSNamedMapImpl(this.fNamespaces, symbolHashArr, this.fGrammarCount);
                        }
                    }
                    return this.fGlobalComponents[s];
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return XSNamedMapImpl.EMPTY_MAP;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0080 A[Catch: all -> 0x0024, TryCatch #0 {all -> 0x0024, blocks: (B:6:0x0007, B:11:0x0012, B:13:0x0016, B:16:0x0021, B:25:0x0035, B:27:0x0039, B:30:0x003d, B:59:0x0099, B:60:0x00a1, B:49:0x0064, B:50:0x006b, B:51:0x0072, B:52:0x0079, B:53:0x0080, B:54:0x0087, B:55:0x008e, B:61:0x00a8, B:19:0x0027, B:21:0x002b, B:24:0x0032, B:64:0x00b0), top: B:69:0x0007 }] */
    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public synchronized XSNamedMap getComponentsByNamespace(short s, String str) {
        SymbolHash symbolHash;
        if (s > 0 && s <= 16) {
            try {
                if (GLOBAL_COMP[s]) {
                    int i = 0;
                    if (str != null) {
                        while (i < this.fGrammarCount && !str.equals(this.fNamespaces[i])) {
                            i++;
                        }
                    } else {
                        while (i < this.fGrammarCount && this.fNamespaces[i] != null) {
                            i++;
                        }
                    }
                    if (i == this.fGrammarCount) {
                        return XSNamedMapImpl.EMPTY_MAP;
                    }
                    XSNamedMap[] xSNamedMapArr = this.fNSComponents[i];
                    if (xSNamedMapArr[s] == null) {
                        if (s == 1) {
                            symbolHash = this.fGrammarList[i].fGlobalAttrDecls;
                        } else if (s == 2) {
                            symbolHash = this.fGrammarList[i].fGlobalElemDecls;
                        } else if (s == 3) {
                            symbolHash = this.fGrammarList[i].fGlobalTypeDecls;
                        } else if (s == 5) {
                            symbolHash = this.fGrammarList[i].fGlobalAttrGrpDecls;
                        } else if (s == 6) {
                            symbolHash = this.fGrammarList[i].fGlobalGroupDecls;
                        } else if (s == 10) {
                            symbolHash = this.fGrammarList[i].fGlobalIDConstraintDecls;
                        } else if (s == 11) {
                            symbolHash = this.fGrammarList[i].fGlobalNotationDecls;
                        } else if (s == 15 || s == 16) {
                            symbolHash = this.fGrammarList[i].fGlobalTypeDecls;
                        } else {
                            symbolHash = null;
                        }
                        if (s == 15 || s == 16) {
                            xSNamedMapArr[s] = new XSNamedMap4Types(str, symbolHash, s);
                        } else {
                            xSNamedMapArr[s] = new XSNamedMapImpl(str, symbolHash);
                        }
                    }
                    return this.fNSComponents[i][s];
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return XSNamedMapImpl.EMPTY_MAP;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSElementDeclaration getElementDeclaration(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSElementDeclaration) schemaGrammar.fGlobalElemDecls.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSIDCDefinition getIDCDefinition(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSIDCDefinition) schemaGrammar.fGlobalIDConstraintDecls.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItemList
    public int getLength() {
        return this.fGrammarCount;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSModelGroupDefinition getModelGroupDefinition(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSModelGroupDefinition) schemaGrammar.fGlobalGroupDecls.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSNamespaceItemList getNamespaceItems() {
        return this;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public StringList getNamespaces() {
        return this.fNamespacesList;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSNotationDeclaration getNotationDeclaration(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSNotationDeclaration) schemaGrammar.fGlobalNotationDecls.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSObjectList getSubstitutionGroup(XSElementDeclaration xSElementDeclaration) {
        return (XSObjectList) this.fSubGroupMap.get(xSElementDeclaration);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModel
    public XSTypeDefinition getTypeDefinition(String str, String str2) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSTypeDefinition) schemaGrammar.fGlobalTypeDecls.get(str);
    }

    public boolean hasIDConstraints() {
        return this.fHasIDC;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItemList
    public XSNamespaceItem item(int i) {
        if (i < 0 || i >= this.fGrammarCount) {
            return null;
        }
        return this.fGrammarList[i];
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<XSNamespaceItem> iterator() {
        return listIterator0(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<XSNamespaceItem> listIterator(int i) {
        if (i >= 0 && i < this.fGrammarCount) {
            return listIterator0(i);
        }
        b1e.a("Index: ", i);
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return getLength();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.fGrammarCount) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.fGrammarCount);
        }
        toArray0(objArr);
        int length = objArr.length;
        int i = this.fGrammarCount;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }

    public final class XSNamespaceItemListIterator implements ListIterator<XSNamespaceItem> {
        private int index;

        public XSNamespaceItemListIterator(int i) {
            this.index = i;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.index < XSModelImpl.this.fGrammarCount;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public XSNamespaceItem next() {
            if (this.index >= XSModelImpl.this.fGrammarCount) {
                z0e.a();
                return null;
            }
            SchemaGrammar[] schemaGrammarArr = XSModelImpl.this.fGrammarList;
            int i = this.index;
            this.index = i + 1;
            return schemaGrammarArr[i];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.ListIterator
        public XSNamespaceItem previous() {
            if (this.index <= 0) {
                z0e.a();
                return null;
            }
            SchemaGrammar[] schemaGrammarArr = XSModelImpl.this.fGrammarList;
            int i = this.index - 1;
            this.index = i;
            return schemaGrammarArr[i];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void add(XSNamespaceItem xSNamespaceItem) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(XSNamespaceItem xSNamespaceItem) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<XSNamespaceItem> listIterator() {
        return listIterator0(0);
    }

    public XSAttributeDeclaration getAttributeDeclaration(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getGlobalAttributeDecl(str, str3);
    }

    public XSAttributeGroupDefinition getAttributeGroup(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getGlobalAttributeGroupDecl(str, str3);
    }

    public XSElementDeclaration getElementDeclaration(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getGlobalElementDecl(str, str3);
    }

    public XSIDCDefinition getIDCDefinition(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getIDConstraintDecl(str, str3);
    }

    public XSModelGroupDefinition getModelGroupDefinition(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getGlobalGroupDecl(str, str3);
    }

    public XSNotationDeclaration getNotationDeclaration(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getGlobalNotationDecl(str, str3);
    }

    public XSTypeDefinition getTypeDefinition(String str, String str2, String str3) {
        SchemaGrammar schemaGrammar = (SchemaGrammar) this.fGrammarMap.get(null2EmptyString(str2));
        if (schemaGrammar == null) {
            return null;
        }
        return schemaGrammar.getGlobalTypeDecl(str, str3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        Object[] objArr = new Object[this.fGrammarCount];
        toArray0(objArr);
        return objArr;
    }

    public XSModelImpl(SchemaGrammar[] schemaGrammarArr) {
        this(schemaGrammarArr, (short) 1);
    }
}
