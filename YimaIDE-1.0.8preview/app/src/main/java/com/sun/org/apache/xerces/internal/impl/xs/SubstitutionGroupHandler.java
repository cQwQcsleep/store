package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SubstitutionGroupHandler {
    private static final XSElementDecl[] EMPTY_GROUP = new XSElementDecl[0];
    private static final OneSubGroup[] EMPTY_VECTOR = new OneSubGroup[0];
    private final XSElementDeclHelper fXSElementDeclHelper;
    Map<XSElementDecl, Object> fSubGroupsB = new HashMap();
    Map<XSElementDecl, XSElementDecl[]> fSubGroups = new HashMap();

    public SubstitutionGroupHandler(XSElementDeclHelper xSElementDeclHelper) {
        this.fXSElementDeclHelper = xSElementDeclHelper;
    }

    private boolean getDBMethods(XSTypeDefinition xSTypeDefinition, XSTypeDefinition xSTypeDefinition2, OneSubGroup oneSubGroup) {
        short s = 0;
        short s2 = 0;
        while (xSTypeDefinition != xSTypeDefinition2) {
            XSComplexTypeDecl xSComplexTypeDecl = SchemaGrammar.fAnyType;
            if (xSTypeDefinition == xSComplexTypeDecl) {
                break;
            }
            s = (short) (xSTypeDefinition.getTypeCategory() == 15 ? s | ((XSComplexTypeDecl) xSTypeDefinition).fDerivedBy : s | 2);
            xSTypeDefinition = xSTypeDefinition.getBaseType();
            if (xSTypeDefinition == null) {
                xSTypeDefinition = xSComplexTypeDecl;
            }
            if (xSTypeDefinition.getTypeCategory() == 15) {
                s2 = (short) (s2 | ((XSComplexTypeDecl) xSTypeDefinition).fBlock);
            }
        }
        if (xSTypeDefinition != xSTypeDefinition2 || (s & s2) != 0) {
            return false;
        }
        oneSubGroup.dMethod = s;
        oneSubGroup.bMethod = s2;
        return true;
    }

    private OneSubGroup[] getSubGroupB(XSElementDecl xSElementDecl, OneSubGroup oneSubGroup) {
        Object obj = this.fSubGroupsB.get(xSElementDecl);
        if (obj == null) {
            Map<XSElementDecl, Object> map = this.fSubGroupsB;
            OneSubGroup[] oneSubGroupArr = EMPTY_VECTOR;
            map.put(xSElementDecl, oneSubGroupArr);
            return oneSubGroupArr;
        }
        if (obj instanceof OneSubGroup[]) {
            return (OneSubGroup[]) obj;
        }
        ArrayList arrayList = (ArrayList) obj;
        ArrayList arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            XSElementDecl xSElementDecl2 = (XSElementDecl) arrayList.get(size);
            if (getDBMethods(xSElementDecl2.fType, xSElementDecl.fType, oneSubGroup)) {
                short s = oneSubGroup.dMethod;
                short s2 = oneSubGroup.bMethod;
                arrayList2.add(new OneSubGroup(xSElementDecl2, s, s2));
                OneSubGroup[] subGroupB = getSubGroupB(xSElementDecl2, oneSubGroup);
                for (int length = subGroupB.length - 1; length >= 0; length--) {
                    OneSubGroup oneSubGroup2 = subGroupB[length];
                    short s3 = (short) (oneSubGroup2.dMethod | s);
                    short s4 = (short) (oneSubGroup2.bMethod | s2);
                    if ((s3 & s4) == 0) {
                        arrayList2.add(new OneSubGroup(oneSubGroup2.sub, s3, s4));
                    }
                }
            }
        }
        OneSubGroup[] oneSubGroupArr2 = new OneSubGroup[arrayList2.size()];
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            oneSubGroupArr2[size2] = (OneSubGroup) arrayList2.get(size2);
        }
        this.fSubGroupsB.put(xSElementDecl, oneSubGroupArr2);
        return oneSubGroupArr2;
    }

    private boolean typeDerivationOK(XSTypeDefinition xSTypeDefinition, XSTypeDefinition xSTypeDefinition2, short s) {
        XSTypeDefinition baseType = xSTypeDefinition;
        short s2 = s;
        short s3 = 0;
        while (baseType != xSTypeDefinition2) {
            XSComplexTypeDecl xSComplexTypeDecl = SchemaGrammar.fAnyType;
            if (baseType == xSComplexTypeDecl) {
                break;
            }
            s3 = (short) (baseType.getTypeCategory() == 15 ? s3 | ((XSComplexTypeDecl) baseType).fDerivedBy : s3 | 2);
            baseType = baseType.getBaseType();
            if (baseType == null) {
                baseType = xSComplexTypeDecl;
            }
            if (baseType.getTypeCategory() == 15) {
                s2 = (short) (s2 | ((XSComplexTypeDecl) baseType).fBlock);
            }
        }
        if (baseType == xSTypeDefinition2) {
            return (s3 & s2) == 0;
        }
        if (xSTypeDefinition2.getTypeCategory() == 16) {
            XSSimpleTypeDefinition xSSimpleTypeDefinition = (XSSimpleTypeDefinition) xSTypeDefinition2;
            if (xSSimpleTypeDefinition.getVariety() == 3) {
                XSObjectList memberTypes = xSSimpleTypeDefinition.getMemberTypes();
                int length = memberTypes.getLength();
                for (int i = 0; i < length; i++) {
                    if (typeDerivationOK(xSTypeDefinition, (XSTypeDefinition) memberTypes.item(i), s)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addSubstitutionGroup(XSElementDecl[] xSElementDeclArr) {
        for (int length = xSElementDeclArr.length - 1; length >= 0; length--) {
            XSElementDecl xSElementDecl = xSElementDeclArr[length];
            XSElementDecl xSElementDecl2 = xSElementDecl.fSubGroup;
            List arrayList = (List) this.fSubGroupsB.get(xSElementDecl2);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.fSubGroupsB.put(xSElementDecl2, arrayList);
            }
            arrayList.add(xSElementDecl);
        }
    }

    public XSElementDecl getMatchingElemDecl(QName qName, XSElementDecl xSElementDecl) {
        XSElementDecl globalElementDecl;
        if (Objects.equals(qName.localpart, xSElementDecl.fName) && Objects.equals(qName.uri, xSElementDecl.fTargetNamespace)) {
            return xSElementDecl;
        }
        if (xSElementDecl.fScope == 1 && (xSElementDecl.fBlock & 4) == 0 && (globalElementDecl = this.fXSElementDeclHelper.getGlobalElementDecl(qName)) != null && substitutionGroupOK(globalElementDecl, xSElementDecl, xSElementDecl.fBlock)) {
            return globalElementDecl;
        }
        return null;
    }

    public XSElementDecl[] getSubstitutionGroup(XSElementDecl xSElementDecl) {
        XSElementDecl[] xSElementDeclArr = this.fSubGroups.get(xSElementDecl);
        if (xSElementDeclArr != null) {
            return xSElementDeclArr;
        }
        if ((xSElementDecl.fBlock & 4) != 0) {
            Map<XSElementDecl, XSElementDecl[]> map = this.fSubGroups;
            XSElementDecl[] xSElementDeclArr2 = EMPTY_GROUP;
            map.put(xSElementDecl, xSElementDeclArr2);
            return xSElementDeclArr2;
        }
        OneSubGroup[] subGroupB = getSubGroupB(xSElementDecl, new OneSubGroup());
        int length = subGroupB.length;
        XSElementDecl[] xSElementDeclArr3 = new XSElementDecl[length];
        int i = 0;
        for (OneSubGroup oneSubGroup : subGroupB) {
            if ((xSElementDecl.fBlock & oneSubGroup.dMethod) == 0) {
                xSElementDeclArr3[i] = oneSubGroup.sub;
                i++;
            }
        }
        if (i < length) {
            XSElementDecl[] xSElementDeclArr4 = new XSElementDecl[i];
            System.arraycopy(xSElementDeclArr3, 0, xSElementDeclArr4, 0, i);
            xSElementDeclArr3 = xSElementDeclArr4;
        }
        this.fSubGroups.put(xSElementDecl, xSElementDeclArr3);
        return xSElementDeclArr3;
    }

    public boolean inSubstitutionGroup(XSElementDecl xSElementDecl, XSElementDecl xSElementDecl2) {
        return substitutionGroupOK(xSElementDecl, xSElementDecl2, xSElementDecl2.fBlock);
    }

    public void reset() {
        this.fSubGroupsB.clear();
        this.fSubGroups.clear();
    }

    public boolean substitutionGroupOK(XSElementDecl xSElementDecl, XSElementDecl xSElementDecl2, short s) {
        if (xSElementDecl == xSElementDecl2) {
            return true;
        }
        if ((s & 4) != 0) {
            return false;
        }
        XSElementDecl xSElementDecl3 = xSElementDecl.fSubGroup;
        while (xSElementDecl3 != null && xSElementDecl3 != xSElementDecl2) {
            xSElementDecl3 = xSElementDecl3.fSubGroup;
        }
        if (xSElementDecl3 == null) {
            return false;
        }
        return typeDerivationOK(xSElementDecl.fType, xSElementDecl2.fType, s);
    }

    public static final class OneSubGroup {
        short bMethod;
        short dMethod;
        XSElementDecl sub;

        public OneSubGroup(XSElementDecl xSElementDecl, short s, short s2) {
            this.sub = xSElementDecl;
            this.dMethod = s;
            this.bMethod = s2;
        }

        public OneSubGroup() {
        }
    }
}
