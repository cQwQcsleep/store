package com.sun.org.apache.xerces.internal.impl.dtd.models;

import com.sun.org.apache.xerces.internal.xni.QName;
import defpackage.uv;
import java.io.PrintStream;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DFAContentModel implements ContentModelValidator {
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private int fLeafCount;
    private boolean fMixed;
    private static String fEpsilonString = "<<CMNODE_EPSILON>>".intern();
    private static String fEOCString = fEOCString.intern();
    private static String fEOCString = fEOCString.intern();
    private QName[] fElemMap = null;
    private int[] fElemMapType = null;
    private int fElemMapSize = 0;
    private int fEOCPos = 0;
    private boolean[] fFinalStateFlags = null;
    private CMStateSet[] fFollowList = null;
    private CMNode fHeadNode = null;
    private CMLeaf[] fLeafList = null;
    private int[] fLeafListType = null;
    private int[][] fTransTable = null;
    private int fTransTableSize = 0;
    private boolean fEmptyContentIsValid = false;
    private final QName fQName = new QName();

    public DFAContentModel(CMNode cMNode, int i, boolean z) {
        this.fLeafCount = 0;
        this.fLeafCount = i;
        this.fMixed = z;
        buildDFA(cMNode);
    }

    /* JADX WARN: Code duplicated, block: B:55:0x018b A[PHI: r5 r13
      0x018b: PHI (r5v4 int) = (r5v3 int), (r5v6 int) binds: [B:45:0x013e, B:53:0x0166] A[DONT_GENERATE, DONT_INLINE]
      0x018b: PHI (r13v5 com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet) = 
      (r13v4 com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet)
      (r13v7 com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet)
     binds: [B:45:0x013e, B:53:0x0166] A[DONT_GENERATE, DONT_INLINE]] */
    private void buildDFA(CMNode cMNode) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        QName qName = this.fQName;
        String str = fEOCString;
        CMStateSet cMStateSet = null;
        qName.setValues(null, str, str, null);
        CMLeaf cMLeaf = new CMLeaf(this.fQName);
        this.fHeadNode = new CMBinOp(5, cMNode, cMLeaf);
        int i6 = this.fLeafCount;
        this.fEOCPos = i6;
        this.fLeafCount = i6 + 1;
        cMLeaf.setPosition(i6);
        int i7 = this.fLeafCount;
        this.fLeafList = new CMLeaf[i7];
        this.fLeafListType = new int[i7];
        int i8 = 0;
        postTreeBuildInit(this.fHeadNode, 0);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        int i9 = 0;
        while (true) {
            int i10 = this.fLeafCount;
            if (i9 >= i10) {
                break;
            }
            this.fFollowList[i9] = new CMStateSet(i10);
            i9++;
        }
        calcFollowList(this.fHeadNode);
        int i11 = this.fLeafCount;
        this.fElemMap = new QName[i11];
        this.fElemMapType = new int[i11];
        this.fElemMapSize = 0;
        int i12 = 0;
        while (true) {
            i = this.fLeafCount;
            i2 = 1;
            if (i12 >= i) {
                break;
            }
            this.fElemMap[i12] = new QName();
            QName element = this.fLeafList[i12].getElement();
            int i13 = 0;
            while (true) {
                i5 = this.fElemMapSize;
                if (i13 >= i5 || this.fElemMap[i13].rawname == element.rawname) {
                    break;
                } else {
                    i13++;
                }
            }
            if (i13 == i5) {
                this.fElemMap[i5].setValues(element);
                int[] iArr = this.fElemMapType;
                int i14 = this.fElemMapSize;
                iArr[i14] = this.fLeafListType[i12];
                this.fElemMapSize = i14 + 1;
            }
            i12++;
        }
        int[] iArr2 = new int[i + this.fElemMapSize];
        int i15 = 0;
        int i16 = 0;
        while (true) {
            i3 = -1;
            if (i15 >= this.fElemMapSize) {
                break;
            }
            for (int i17 = 0; i17 < this.fLeafCount; i17++) {
                if (this.fLeafList[i17].getElement().rawname == this.fElemMap[i15].rawname) {
                    iArr2[i16] = i17;
                    i16++;
                }
            }
            iArr2[i16] = -1;
            i15++;
            i16++;
        }
        int i18 = this.fLeafCount * 4;
        CMStateSet[] cMStateSetArr = new CMStateSet[i18];
        this.fFinalStateFlags = new boolean[i18];
        this.fTransTable = new int[i18][];
        CMStateSet cMStateSetFirstPos = this.fHeadNode.firstPos();
        this.fTransTable[0] = makeDefStateList();
        cMStateSetArr[0] = cMStateSetFirstPos;
        HashMap map = new HashMap();
        int i19 = 0;
        while (i19 < i2) {
            CMStateSet cMStateSet2 = cMStateSetArr[i19];
            int[] iArr3 = this.fTransTable[i19];
            this.fFinalStateFlags[i19] = cMStateSet2.getBit(this.fEOCPos);
            i19++;
            int i20 = i8;
            int i21 = i20;
            CMStateSet cMStateSet3 = cMStateSet;
            while (i20 < this.fElemMapSize) {
                if (cMStateSet3 == null) {
                    cMStateSet3 = new CMStateSet(this.fLeafCount);
                } else {
                    cMStateSet3.zeroBits();
                }
                int i22 = i21 + 1;
                int i23 = iArr2[i21];
                while (i23 != i3) {
                    if (cMStateSet2.getBit(i23)) {
                        cMStateSet3.union(this.fFollowList[i23]);
                    }
                    i23 = iArr2[i22];
                    i22++;
                    i3 = -1;
                }
                if (cMStateSet3.isEmpty()) {
                    i4 = i8;
                } else {
                    Integer num = (Integer) map.get(cMStateSet3);
                    int iIntValue = num == null ? i2 : num.intValue();
                    if (iIntValue == i2) {
                        cMStateSetArr[i2] = cMStateSet3;
                        this.fTransTable[i2] = makeDefStateList();
                        map.put(cMStateSet3, Integer.valueOf(i2));
                        i2++;
                        cMStateSet3 = cMStateSet;
                    }
                    iArr3[i20] = iIntValue;
                    if (i2 == i18) {
                        int i24 = (int) (((double) i18) * 1.5d);
                        CMStateSet[] cMStateSetArr2 = new CMStateSet[i24];
                        boolean[] zArr = new boolean[i24];
                        int[][] iArr4 = new int[i24][];
                        i4 = 0;
                        System.arraycopy(cMStateSetArr, 0, cMStateSetArr2, 0, i18);
                        System.arraycopy(this.fFinalStateFlags, 0, zArr, 0, i18);
                        System.arraycopy(this.fTransTable, 0, iArr4, 0, i18);
                        this.fFinalStateFlags = zArr;
                        this.fTransTable = iArr4;
                        i18 = i24;
                        cMStateSetArr = cMStateSetArr2;
                    } else {
                        i4 = i8;
                    }
                }
                i20++;
                i8 = i4;
                i21 = i22;
                iArr2 = iArr2;
                cMStateSet = null;
                i3 = -1;
            }
        }
        this.fEmptyContentIsValid = ((CMBinOp) this.fHeadNode).getLeft().isNullable();
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
    }

    private void calcFollowList(CMNode cMNode) {
        if (cMNode.type() == 4) {
            CMBinOp cMBinOp = (CMBinOp) cMNode;
            calcFollowList(cMBinOp.getLeft());
            calcFollowList(cMBinOp.getRight());
            return;
        }
        int i = 0;
        if (cMNode.type() == 5) {
            CMBinOp cMBinOp2 = (CMBinOp) cMNode;
            calcFollowList(cMBinOp2.getLeft());
            calcFollowList(cMBinOp2.getRight());
            CMStateSet cMStateSetLastPos = cMBinOp2.getLeft().lastPos();
            CMStateSet cMStateSetFirstPos = cMBinOp2.getRight().firstPos();
            while (i < this.fLeafCount) {
                if (cMStateSetLastPos.getBit(i)) {
                    this.fFollowList[i].union(cMStateSetFirstPos);
                }
                i++;
            }
            return;
        }
        if (cMNode.type() != 2 && cMNode.type() != 3) {
            if (cMNode.type() == 1) {
                calcFollowList(((CMUniOp) cMNode).getChild());
                return;
            }
            return;
        }
        calcFollowList(((CMUniOp) cMNode).getChild());
        CMStateSet cMStateSetFirstPos2 = cMNode.firstPos();
        CMStateSet cMStateSetLastPos2 = cMNode.lastPos();
        while (i < this.fLeafCount) {
            if (cMStateSetLastPos2.getBit(i)) {
                this.fFollowList[i].union(cMStateSetFirstPos2);
            }
            i++;
        }
    }

    private void dumpTree(CMNode cMNode, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print("   ");
        }
        int iType = cMNode.type();
        if (iType == 4 || iType == 5) {
            if (iType == 4) {
                System.out.print("Choice Node ");
            } else {
                System.out.print("Seq Node ");
            }
            if (cMNode.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(cMNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cMNode.lastPos().toString());
            CMBinOp cMBinOp = (CMBinOp) cMNode;
            int i3 = i + 1;
            dumpTree(cMBinOp.getLeft(), i3);
            dumpTree(cMBinOp.getRight(), i3);
            return;
        }
        if (cMNode.type() == 2) {
            System.out.print("Rep Node ");
            if (cMNode.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(cMNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cMNode.lastPos().toString());
            dumpTree(((CMUniOp) cMNode).getChild(), i + 1);
            return;
        }
        if (cMNode.type() != 0) {
            f63.a("ImplementationMessages.VAL_NIICM");
            return;
        }
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder("Leaf: (pos=");
        CMLeaf cMLeaf = (CMLeaf) cMNode;
        sb.append(cMLeaf.getPosition());
        sb.append("), ");
        sb.append(cMLeaf.getElement());
        sb.append("(elemIndex=");
        sb.append(cMLeaf.getElement());
        sb.append(") ");
        printStream.print(sb.toString());
        if (cMNode.isNullable()) {
            System.out.print(" Nullable ");
        }
        System.out.print("firstPos=");
        System.out.print(cMNode.firstPos().toString());
        System.out.print(" lastPos=");
        System.out.println(cMNode.lastPos().toString());
    }

    private int[] makeDefStateList() {
        int[] iArr = new int[this.fElemMapSize];
        for (int i = 0; i < this.fElemMapSize; i++) {
            iArr[i] = -1;
        }
        return iArr;
    }

    private int postTreeBuildInit(CMNode cMNode, int i) {
        cMNode.setMaxStates(this.fLeafCount);
        if ((cMNode.type() & 15) == 6 || (cMNode.type() & 15) == 8 || (cMNode.type() & 15) == 7) {
            CMAny cMAny = (CMAny) cMNode;
            this.fLeafList[i] = new CMLeaf(new QName(null, null, null, cMAny.getURI()), cMAny.getPosition());
            this.fLeafListType[i] = cMNode.type();
        } else {
            if (cMNode.type() == 4 || cMNode.type() == 5) {
                CMBinOp cMBinOp = (CMBinOp) cMNode;
                return postTreeBuildInit(cMBinOp.getRight(), postTreeBuildInit(cMBinOp.getLeft(), i));
            }
            if (cMNode.type() == 2 || cMNode.type() == 3 || cMNode.type() == 1) {
                return postTreeBuildInit(((CMUniOp) cMNode).getChild(), i);
            }
            if (cMNode.type() != 0) {
                uv.a("ImplementationMessages.VAL_NIICM: type=", cMNode.type());
                return 0;
            }
            CMLeaf cMLeaf = (CMLeaf) cMNode;
            if (cMLeaf.getElement().localpart == fEpsilonString) {
                return i;
            }
            this.fLeafList[i] = cMLeaf;
            this.fLeafListType[i] = 0;
        }
        return i + 1;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.ContentModelValidator
    public int validate(QName[] qNameArr, int i, int i2) {
        int i3;
        if (i2 == 0) {
            return this.fEmptyContentIsValid ? -1 : 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            QName qName = qNameArr[i + i5];
            if (!this.fMixed || qName.localpart != null) {
                int i6 = 0;
                while (true) {
                    i3 = this.fElemMapSize;
                    if (i6 >= i3) {
                        break;
                    }
                    int i7 = this.fElemMapType[i6] & 15;
                    if (i7 == 0) {
                        if (this.fElemMap[i6].rawname == qName.rawname) {
                            break;
                        }
                        i6++;
                    } else if (i7 == 6) {
                        String str = this.fElemMap[i6].uri;
                        if (str == null || str == qName.uri) {
                            break;
                        }
                        i6++;
                    } else if (i7 != 8) {
                        if (i7 == 7 && this.fElemMap[i6].uri != qName.uri) {
                            break;
                        }
                        i6++;
                    } else {
                        if (qName.uri == null) {
                            break;
                        }
                        i6++;
                    }
                }
                if (i6 == i3 || (i4 = this.fTransTable[i4][i6]) == -1) {
                    return i5;
                }
            }
        }
        if (this.fFinalStateFlags[i4]) {
            return -1;
        }
        return i2;
    }
}
