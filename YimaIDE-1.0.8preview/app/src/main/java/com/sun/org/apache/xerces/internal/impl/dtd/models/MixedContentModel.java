package com.sun.org.apache.xerces.internal.impl.dtd.models;

import com.sun.org.apache.xerces.internal.xni.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MixedContentModel implements ContentModelValidator {
    private QName[] fChildren;
    private int[] fChildrenType;
    private int fCount;
    private boolean fOrdered;

    public MixedContentModel(QName[] qNameArr, int[] iArr, int i, int i2, boolean z) {
        this.fCount = i2;
        this.fChildren = new QName[i2];
        this.fChildrenType = new int[i2];
        for (int i3 = 0; i3 < this.fCount; i3++) {
            int i4 = i + i3;
            this.fChildren[i3] = new QName(qNameArr[i4]);
            this.fChildrenType[i3] = iArr[i4];
        }
        this.fOrdered = z;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.ContentModelValidator
    public int validate(QName[] qNameArr, int i, int i2) {
        int i3;
        if (this.fOrdered) {
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                QName qName = qNameArr[i + i5];
                if (qName.localpart != null) {
                    int i6 = this.fChildrenType[i4];
                    if (i6 == 0) {
                        if (this.fChildren[i4].rawname != qName.rawname) {
                            return i5;
                        }
                        i4++;
                    } else if (i6 == 6) {
                        String str = this.fChildren[i4].uri;
                        if (str != null && str != qNameArr[i5].uri) {
                            return i5;
                        }
                        i4++;
                    } else if (i6 == 8) {
                        if (qNameArr[i5].uri != null) {
                            return i5;
                        }
                        i4++;
                    } else {
                        if (i6 == 7 && this.fChildren[i4].uri == qNameArr[i5].uri) {
                            return i5;
                        }
                        i4++;
                    }
                }
            }
            return -1;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            QName qName2 = qNameArr[i + i7];
            if (qName2.localpart != null) {
                int i8 = 0;
                while (true) {
                    i3 = this.fCount;
                    if (i8 >= i3) {
                        break;
                    }
                    int i9 = this.fChildrenType[i8];
                    if (i9 == 0) {
                        if (qName2.rawname == this.fChildren[i8].rawname) {
                            break;
                        }
                        i8++;
                    } else if (i9 == 6) {
                        String str2 = this.fChildren[i8].uri;
                        if (str2 == null || str2 == qNameArr[i7].uri) {
                            break;
                        }
                        i8++;
                    } else if (i9 != 8) {
                        if (i9 == 7 && this.fChildren[i8].uri != qNameArr[i7].uri) {
                            break;
                        }
                        i8++;
                    } else {
                        if (qNameArr[i7].uri == null) {
                            break;
                        }
                        i8++;
                    }
                }
                if (i8 == i3) {
                    return i7;
                }
            }
        }
        return -1;
    }
}
