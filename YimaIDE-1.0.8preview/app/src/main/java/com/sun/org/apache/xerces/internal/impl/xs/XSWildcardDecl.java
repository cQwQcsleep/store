package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSWildcard;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSWildcardDecl implements XSWildcard {
    public static final String ABSENT = null;
    public String[] fNamespaceList;
    public short fType = 1;
    public short fProcessContents = 1;
    public XSObjectList fAnnotations = null;
    private String fDescription = null;

    private boolean areSame(XSWildcardDecl xSWildcardDecl) {
        short s = this.fType;
        if (s == xSWildcardDecl.fType) {
            if (s == 1) {
                return true;
            }
            String[] strArr = this.fNamespaceList;
            if (s == 2) {
                return strArr[0] == xSWildcardDecl.fNamespaceList[0];
            }
            if (strArr.length == xSWildcardDecl.fNamespaceList.length) {
                int i = 0;
                while (true) {
                    String[] strArr2 = this.fNamespaceList;
                    if (i >= strArr2.length) {
                        return true;
                    }
                    if (!elementInSet(strArr2[i], xSWildcardDecl.fNamespaceList)) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    public boolean allowNamespace(String str) {
        short s = this.fType;
        if (s == 1) {
            return true;
        }
        if (s == 2) {
            int length = this.fNamespaceList.length;
            boolean z = false;
            for (int i = 0; i < length && !z; i++) {
                if (str == this.fNamespaceList[i]) {
                    z = true;
                }
            }
            if (!z) {
                return true;
            }
        }
        if (this.fType == 3) {
            int length2 = this.fNamespaceList.length;
            for (int i2 = 0; i2 < length2; i2++) {
                if (str == this.fNamespaceList[i2]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean elementInSet(String str, String[] strArr) {
        boolean z = false;
        for (int i = 0; i < strArr.length && !z; i++) {
            if (str == strArr[i]) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSWildcard
    public XSAnnotation getAnnotation() {
        XSObjectList xSObjectList = this.fAnnotations;
        if (xSObjectList != null) {
            return (XSAnnotation) xSObjectList.item(0);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSWildcard
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSWildcard
    public short getConstraintType() {
        return this.fType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getName() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getNamespace() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSWildcard
    public StringList getNsConstraintList() {
        String[] strArr = this.fNamespaceList;
        return new StringListImpl(strArr, strArr == null ? 0 : strArr.length);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSWildcard
    public short getProcessContents() {
        return this.fProcessContents;
    }

    public String getProcessContentsAsString() {
        short s = this.fProcessContents;
        if (s == 1) {
            return SchemaSymbols.ATTVAL_STRICT;
        }
        if (s != 2) {
            return s != 3 ? "invalid value" : SchemaSymbols.ATTVAL_LAX;
        }
        return SchemaSymbols.ATTVAL_SKIP;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 9;
    }

    public String[] intersect2sets(String[] strArr, String[] strArr2) {
        String[] strArr3 = new String[Math.min(strArr.length, strArr2.length)];
        int i = 0;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (elementInSet(strArr[i2], strArr2)) {
                strArr3[i] = strArr[i2];
                i++;
            }
        }
        String[] strArr4 = new String[i];
        System.arraycopy(strArr3, 0, strArr4, 0, i);
        return strArr4;
    }

    public boolean isSubsetOf(XSWildcardDecl xSWildcardDecl) {
        if (xSWildcardDecl == null) {
            return false;
        }
        short s = xSWildcardDecl.fType;
        if (s == 1) {
            return true;
        }
        short s2 = this.fType;
        if (s2 == 2 && s == 2 && this.fNamespaceList[0] == xSWildcardDecl.fNamespaceList[0]) {
            return true;
        }
        if (s2 == 3) {
            if (s == 3 && subset2sets(this.fNamespaceList, xSWildcardDecl.fNamespaceList)) {
                return true;
            }
            if (xSWildcardDecl.fType == 2 && !elementInSet(xSWildcardDecl.fNamespaceList[0], this.fNamespaceList) && !elementInSet(ABSENT, this.fNamespaceList)) {
                return true;
            }
        }
        return false;
    }

    public XSWildcardDecl performIntersectionWith(XSWildcardDecl xSWildcardDecl, short s) {
        short s2;
        String[] strArr;
        if (xSWildcardDecl == null) {
            return null;
        }
        XSWildcardDecl xSWildcardDecl2 = new XSWildcardDecl();
        xSWildcardDecl2.fProcessContents = s;
        boolean zAreSame = areSame(xSWildcardDecl);
        short s3 = this.fType;
        if (zAreSame) {
            xSWildcardDecl2.fType = s3;
            xSWildcardDecl2.fNamespaceList = this.fNamespaceList;
            return xSWildcardDecl2;
        }
        if (s3 == 1 || (s2 = xSWildcardDecl.fType) == 1) {
            if (s3 == 1) {
                this = xSWildcardDecl;
            }
            xSWildcardDecl2.fType = this.fType;
            xSWildcardDecl2.fNamespaceList = this.fNamespaceList;
            return xSWildcardDecl2;
        }
        if ((s3 != 2 || s2 != 3) && (s3 != 3 || s2 != 2)) {
            if (s3 == 3 && s2 == 3) {
                xSWildcardDecl2.fType = (short) 3;
                xSWildcardDecl2.fNamespaceList = intersect2sets(this.fNamespaceList, xSWildcardDecl.fNamespaceList);
                return xSWildcardDecl2;
            }
            if (s3 == 2 && s2 == 2) {
                String str = this.fNamespaceList[0];
                String str2 = ABSENT;
                if (str != str2 && xSWildcardDecl.fNamespaceList[0] != str2) {
                    return null;
                }
                if (str == str2) {
                    this = xSWildcardDecl;
                }
                xSWildcardDecl2.fType = this.fType;
                xSWildcardDecl2.fNamespaceList = this.fNamespaceList;
            }
            return xSWildcardDecl2;
        }
        if (s3 == 2) {
            strArr = this.fNamespaceList;
        } else {
            String[] strArr2 = xSWildcardDecl.fNamespaceList;
            xSWildcardDecl = this;
            strArr = strArr2;
        }
        String[] strArr3 = xSWildcardDecl.fNamespaceList;
        String[] strArr4 = new String[strArr3.length];
        int i = 0;
        for (String str3 : strArr3) {
            if (str3 != strArr[0] && str3 != ABSENT) {
                strArr4[i] = str3;
                i++;
            }
        }
        xSWildcardDecl2.fType = (short) 3;
        String[] strArr5 = new String[i];
        xSWildcardDecl2.fNamespaceList = strArr5;
        System.arraycopy(strArr4, 0, strArr5, 0, i);
        return xSWildcardDecl2;
    }

    public XSWildcardDecl performUnionWith(XSWildcardDecl xSWildcardDecl, short s) {
        short s2;
        String[] strArr;
        String[] strArr2;
        if (xSWildcardDecl == null) {
            return null;
        }
        XSWildcardDecl xSWildcardDecl2 = new XSWildcardDecl();
        xSWildcardDecl2.fProcessContents = s;
        boolean zAreSame = areSame(xSWildcardDecl);
        short s3 = this.fType;
        if (zAreSame) {
            xSWildcardDecl2.fType = s3;
            xSWildcardDecl2.fNamespaceList = this.fNamespaceList;
            return xSWildcardDecl2;
        }
        if (s3 == 1 || (s2 = xSWildcardDecl.fType) == 1) {
            xSWildcardDecl2.fType = (short) 1;
            return xSWildcardDecl2;
        }
        if (s3 == 3 && s2 == 3) {
            xSWildcardDecl2.fType = (short) 3;
            xSWildcardDecl2.fNamespaceList = union2sets(this.fNamespaceList, xSWildcardDecl.fNamespaceList);
            return xSWildcardDecl2;
        }
        if (s3 == 2 && s2 == 2) {
            xSWildcardDecl2.fType = (short) 2;
            xSWildcardDecl2.fNamespaceList = new String[]{str, str};
            String str = ABSENT;
            return xSWildcardDecl2;
        }
        if ((s3 == 2 && s2 == 3) || (s3 == 3 && s2 == 2)) {
            if (s3 == 2) {
                strArr = this.fNamespaceList;
                strArr2 = xSWildcardDecl.fNamespaceList;
            } else {
                strArr = xSWildcardDecl.fNamespaceList;
                strArr2 = this.fNamespaceList;
            }
            String str2 = ABSENT;
            boolean zElementInSet = elementInSet(str2, strArr2);
            String str3 = strArr[0];
            if (str3 != str2) {
                boolean zElementInSet2 = elementInSet(str3, strArr2);
                if (zElementInSet2 && zElementInSet) {
                    xSWildcardDecl2.fType = (short) 1;
                    return xSWildcardDecl2;
                }
                if (zElementInSet2 && !zElementInSet) {
                    xSWildcardDecl2.fType = (short) 2;
                    xSWildcardDecl2.fNamespaceList = new String[]{str2, str2};
                    return xSWildcardDecl2;
                }
                if (!zElementInSet2 && zElementInSet) {
                    return null;
                }
                xSWildcardDecl2.fType = (short) 2;
                xSWildcardDecl2.fNamespaceList = strArr;
                return xSWildcardDecl2;
            }
            if (zElementInSet) {
                xSWildcardDecl2.fType = (short) 1;
                return xSWildcardDecl2;
            }
            xSWildcardDecl2.fType = (short) 2;
            xSWildcardDecl2.fNamespaceList = strArr;
        }
        return xSWildcardDecl2;
    }

    public boolean subset2sets(String[] strArr, String[] strArr2) {
        for (String str : strArr) {
            if (!elementInSet(str, strArr2)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.fDescription == null) {
            StringBuffer stringBuffer = new StringBuffer("WC[");
            short s = this.fType;
            if (s == 1) {
                stringBuffer.append(SchemaSymbols.ATTVAL_TWOPOUNDANY);
            } else if (s == 2) {
                stringBuffer.append("##other:\"");
                String str = this.fNamespaceList[0];
                if (str != null) {
                    stringBuffer.append(str);
                }
                stringBuffer.append("\"");
            } else if (s == 3 && this.fNamespaceList.length != 0) {
                stringBuffer.append("\"");
                String str2 = this.fNamespaceList[0];
                if (str2 != null) {
                    stringBuffer.append(str2);
                }
                stringBuffer.append("\"");
                for (int i = 1; i < this.fNamespaceList.length; i++) {
                    stringBuffer.append(",\"");
                    String str3 = this.fNamespaceList[i];
                    if (str3 != null) {
                        stringBuffer.append(str3);
                    }
                    stringBuffer.append("\"");
                }
            }
            stringBuffer.append(']');
            this.fDescription = stringBuffer.toString();
        }
        return this.fDescription;
    }

    public String[] union2sets(String[] strArr, String[] strArr2) {
        String[] strArr3 = new String[strArr.length];
        int i = 0;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (!elementInSet(strArr[i2], strArr2)) {
                strArr3[i] = strArr[i2];
                i++;
            }
        }
        String[] strArr4 = new String[strArr2.length + i];
        System.arraycopy(strArr3, 0, strArr4, 0, i);
        System.arraycopy(strArr2, 0, strArr4, i, strArr2.length);
        return strArr4;
    }

    public boolean weakerProcessContents(XSWildcardDecl xSWildcardDecl) {
        short s = this.fProcessContents;
        return (s == 3 && xSWildcardDecl.fProcessContents == 1) || (s == 2 && xSWildcardDecl.fProcessContents != 2);
    }
}
