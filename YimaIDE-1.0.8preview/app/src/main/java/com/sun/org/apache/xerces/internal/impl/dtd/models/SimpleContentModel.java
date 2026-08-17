package com.sun.org.apache.xerces.internal.impl.dtd.models;

import com.sun.org.apache.xerces.internal.xni.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleContentModel implements ContentModelValidator {
    public static final short CHOICE = -1;
    public static final short SEQUENCE = -1;
    private int fOperator;
    private QName fFirstChild = new QName();
    private QName fSecondChild = new QName();

    public SimpleContentModel(short s, QName qName, QName qName2) {
        this.fFirstChild.setValues(qName);
        QName qName3 = this.fSecondChild;
        if (qName2 != null) {
            qName3.setValues(qName2);
        } else {
            qName3.clear();
        }
        this.fOperator = s;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.ContentModelValidator
    public int validate(QName[] qNameArr, int i, int i2) {
        int i3 = this.fOperator;
        int i4 = 0;
        if (i3 == 0) {
            if (i2 != 0 && qNameArr[i].rawname == this.fFirstChild.rawname) {
                return i2 > 1 ? 1 : -1;
            }
            return 0;
        }
        if (i3 == 1) {
            if (i2 != 1 || qNameArr[i].rawname == this.fFirstChild.rawname) {
                return i2 > 1 ? 1 : -1;
            }
            return 0;
        }
        if (i3 == 2) {
            if (i2 <= 0) {
                return -1;
            }
            while (i4 < i2) {
                if (qNameArr[i + i4].rawname != this.fFirstChild.rawname) {
                    return i4;
                }
                i4++;
            }
            return -1;
        }
        if (i3 == 3) {
            if (i2 == 0) {
                return 0;
            }
            while (i4 < i2) {
                if (qNameArr[i + i4].rawname != this.fFirstChild.rawname) {
                    return i4;
                }
                i4++;
            }
            return -1;
        }
        if (i3 == 4) {
            if (i2 == 0) {
                return 0;
            }
            String str = qNameArr[i].rawname;
            if (str == this.fFirstChild.rawname || str == this.fSecondChild.rawname) {
                return i2 > 1 ? 1 : -1;
            }
            return 0;
        }
        if (i3 != 5) {
            f63.a("ImplementationMessages.VAL_CST");
            return 0;
        }
        if (i2 == 2) {
            if (qNameArr[i].rawname != this.fFirstChild.rawname) {
                return 0;
            }
            return qNameArr[i + 1].rawname != this.fSecondChild.rawname ? 1 : -1;
        }
        if (i2 > 2) {
            return 2;
        }
        return i2;
    }
}
