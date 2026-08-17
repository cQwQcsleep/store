package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.SubstitutionGroupHandler;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSWildcardDecl;
import com.sun.org.apache.xerces.internal.xni.QName;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSDFACM implements XSCMValidator {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private static long time;
    private int[] fElemMapCounter;
    private int[] fElemMapCounterLowerBound;
    private int[] fElemMapCounterUpperBound;
    private boolean fIsCompactedForUPA;
    private int fLeafCount;
    private Object[] fElemMap = null;
    private int[] fElemMapType = null;
    private int[] fElemMapId = null;
    private int fElemMapSize = 0;
    private boolean[] fFinalStateFlags = null;
    private CMStateSet[] fFollowList = null;
    private CMNode fHeadNode = null;
    private XSCMLeaf[] fLeafList = null;
    private int[] fLeafListType = null;
    private int[][] fTransTable = null;
    private Occurence[] fCountingStates = null;
    private int fTransTableSize = 0;

    public static final class Occurence {
        final int elemIndex;
        final int maxOccurs;
        final int minOccurs;

        public Occurence(XSCMRepeatingLeaf xSCMRepeatingLeaf, int i) {
            this.minOccurs = xSCMRepeatingLeaf.getMinOccurs();
            this.maxOccurs = xSCMRepeatingLeaf.getMaxOccurs();
            this.elemIndex = i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("minOccurs=");
            sb.append(this.minOccurs);
            sb.append(";maxOccurs=");
            int i = this.maxOccurs;
            sb.append(i != -1 ? Integer.toString(i) : SchemaSymbols.ATTVAL_UNBOUNDED);
            return sb.toString();
        }
    }

    public XSDFACM(CMNode cMNode, int i) {
        this.fLeafCount = i;
        this.fIsCompactedForUPA = cMNode.isCompactedForUPA();
        buildDFA(cMNode);
    }

    private void buildDFA(CMNode cMNode) {
        int i;
        int i2;
        int i3 = this.fLeafCount;
        this.fLeafCount = i3 + 1;
        int i4 = 1;
        CMStateSet cMStateSet = null;
        int i5 = -1;
        XSCMBinOp xSCMBinOp = new XSCMBinOp(102, cMNode, new XSCMLeaf(1, null, -1, i3));
        this.fHeadNode = xSCMBinOp;
        int i6 = this.fLeafCount;
        this.fLeafList = new XSCMLeaf[i6];
        this.fLeafListType = new int[i6];
        postTreeBuildInit(xSCMBinOp);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        int i7 = 0;
        while (true) {
            int i8 = this.fLeafCount;
            if (i7 >= i8) {
                break;
            }
            this.fFollowList[i7] = new CMStateSet(i8);
            i7++;
        }
        calcFollowList(this.fHeadNode);
        int i9 = this.fLeafCount;
        this.fElemMap = new Object[i9];
        this.fElemMapType = new int[i9];
        this.fElemMapId = new int[i9];
        this.fElemMapCounter = new int[i9];
        this.fElemMapCounterLowerBound = new int[i9];
        this.fElemMapCounterUpperBound = new int[i9];
        this.fElemMapSize = 0;
        int i10 = 0;
        Occurence[] occurenceArr = null;
        while (true) {
            i = this.fLeafCount;
            if (i10 >= i) {
                break;
            }
            this.fElemMap[i10] = null;
            int particleId = this.fLeafList[i10].getParticleId();
            int i11 = 0;
            while (true) {
                i2 = this.fElemMapSize;
                if (i11 >= i2 || particleId == this.fElemMapId[i11]) {
                    break;
                } else {
                    i11++;
                }
            }
            if (i11 == i2) {
                XSCMLeaf xSCMLeaf = this.fLeafList[i10];
                this.fElemMap[i2] = xSCMLeaf.getLeaf();
                if (xSCMLeaf instanceof XSCMRepeatingLeaf) {
                    if (occurenceArr == null) {
                        occurenceArr = new Occurence[this.fLeafCount];
                    }
                    int i12 = this.fElemMapSize;
                    occurenceArr[i12] = new Occurence((XSCMRepeatingLeaf) xSCMLeaf, i12);
                }
                int[] iArr = this.fElemMapType;
                int i13 = this.fElemMapSize;
                iArr[i13] = this.fLeafListType[i10];
                this.fElemMapId[i13] = particleId;
                int[] iArr2 = (int[]) xSCMLeaf.getUserData();
                int[] iArr3 = this.fElemMapCounter;
                if (iArr2 != null) {
                    int i14 = this.fElemMapSize;
                    iArr3[i14] = 0;
                    this.fElemMapCounterLowerBound[i14] = iArr2[0];
                    this.fElemMapCounterUpperBound[i14] = iArr2[1];
                } else {
                    int i15 = this.fElemMapSize;
                    iArr3[i15] = -1;
                    this.fElemMapCounterLowerBound[i15] = -1;
                    this.fElemMapCounterUpperBound[i15] = -1;
                }
                this.fElemMapSize++;
            }
            i10++;
        }
        int i16 = this.fElemMapSize - 1;
        this.fElemMapSize = i16;
        int[] iArr4 = new int[i + i16];
        int i17 = 0;
        int i18 = 0;
        while (i17 < this.fElemMapSize) {
            int i19 = this.fElemMapId[i17];
            for (int i20 = 0; i20 < this.fLeafCount; i20++) {
                if (i19 == this.fLeafList[i20].getParticleId()) {
                    iArr4[i18] = i20;
                    i18++;
                }
            }
            iArr4[i18] = -1;
            i17++;
            i18++;
        }
        int i21 = this.fLeafCount * 4;
        CMStateSet[] cMStateSetArr = new CMStateSet[i21];
        this.fFinalStateFlags = new boolean[i21];
        this.fTransTable = new int[i21][];
        CMStateSet cMStateSetFirstPos = this.fHeadNode.firstPos();
        this.fTransTable[0] = makeDefStateList();
        cMStateSetArr[0] = cMStateSetFirstPos;
        HashMap map = new HashMap();
        int i22 = 0;
        while (i22 < i4) {
            CMStateSet cMStateSet2 = cMStateSetArr[i22];
            int[] iArr5 = this.fTransTable[i22];
            this.fFinalStateFlags[i22] = cMStateSet2.getBit(i3);
            i22++;
            int i23 = 0;
            int i24 = 0;
            CMStateSet cMStateSet3 = cMStateSet;
            while (i23 < this.fElemMapSize) {
                if (cMStateSet3 == null) {
                    cMStateSet3 = new CMStateSet(this.fLeafCount);
                } else {
                    cMStateSet3.zeroBits();
                }
                int i25 = i24 + 1;
                int i26 = iArr4[i24];
                i24 = i25;
                int i27 = i26;
                while (i27 != i5) {
                    if (cMStateSet2.getBit(i27)) {
                        cMStateSet3.union(this.fFollowList[i27]);
                    }
                    int i28 = iArr4[i24];
                    i24++;
                    i27 = i28;
                    i5 = -1;
                }
                if (!cMStateSet3.isEmpty()) {
                    Integer num = (Integer) map.get(cMStateSet3);
                    int iIntValue = num == null ? i4 : num.intValue();
                    if (iIntValue == i4) {
                        cMStateSetArr[i4] = cMStateSet3;
                        this.fTransTable[i4] = makeDefStateList();
                        map.put(cMStateSet3, Integer.valueOf(i4));
                        i4++;
                        cMStateSet3 = null;
                    }
                    iArr5[i23] = iIntValue;
                    if (i4 == i21) {
                        int i29 = (int) (((double) i21) * 1.5d);
                        CMStateSet[] cMStateSetArr2 = new CMStateSet[i29];
                        boolean[] zArr = new boolean[i29];
                        int i30 = i4;
                        int[][] iArr6 = new int[i29][];
                        System.arraycopy(cMStateSetArr, 0, cMStateSetArr2, 0, i21);
                        System.arraycopy(this.fFinalStateFlags, 0, zArr, 0, i21);
                        System.arraycopy(this.fTransTable, 0, iArr6, 0, i21);
                        this.fFinalStateFlags = zArr;
                        this.fTransTable = iArr6;
                        i21 = i29;
                        cMStateSetArr = cMStateSetArr2;
                        i4 = i30;
                    }
                }
                i23++;
                i3 = i3;
                i5 = -1;
            }
            cMStateSet = null;
        }
        if (occurenceArr != null) {
            this.fCountingStates = new Occurence[i4];
            for (int i31 = 0; i31 < i4; i31++) {
                int[] iArr7 = this.fTransTable[i31];
                for (int i32 = 0; i32 < iArr7.length; i32++) {
                    if (i31 == iArr7[i32]) {
                        this.fCountingStates[i31] = occurenceArr[i32];
                        break;
                    }
                }
            }
        }
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
        this.fLeafListType = null;
        this.fElemMapId = null;
    }

    private void calcFollowList(CMNode cMNode) {
        if (cMNode.type() == 101) {
            XSCMBinOp xSCMBinOp = (XSCMBinOp) cMNode;
            calcFollowList(xSCMBinOp.getLeft());
            calcFollowList(xSCMBinOp.getRight());
            return;
        }
        int i = 0;
        if (cMNode.type() == 102) {
            XSCMBinOp xSCMBinOp2 = (XSCMBinOp) cMNode;
            calcFollowList(xSCMBinOp2.getLeft());
            calcFollowList(xSCMBinOp2.getRight());
            CMStateSet cMStateSetLastPos = xSCMBinOp2.getLeft().lastPos();
            CMStateSet cMStateSetFirstPos = xSCMBinOp2.getRight().firstPos();
            while (i < this.fLeafCount) {
                if (cMStateSetLastPos.getBit(i)) {
                    this.fFollowList[i].union(cMStateSetFirstPos);
                }
                i++;
            }
            return;
        }
        if (cMNode.type() != 4 && cMNode.type() != 6) {
            if (cMNode.type() == 5) {
                calcFollowList(((XSCMUniOp) cMNode).getChild());
                return;
            }
            return;
        }
        calcFollowList(((XSCMUniOp) cMNode).getChild());
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
        if (iType == 1) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder("Leaf: (pos=");
            XSCMLeaf xSCMLeaf = (XSCMLeaf) cMNode;
            sb.append(xSCMLeaf.getPosition());
            sb.append("), (elemIndex=");
            sb.append(xSCMLeaf.getLeaf());
            sb.append(") ");
            printStream.print(sb.toString());
            if (cMNode.isNullable()) {
                System.out.print(" Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(cMNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cMNode.lastPos().toString());
            return;
        }
        if (iType == 2) {
            System.out.print("Any Node: ");
            System.out.print("firstPos=");
            System.out.print(cMNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cMNode.lastPos().toString());
            return;
        }
        if (iType == 4 || iType == 5 || iType == 6) {
            System.out.print("Rep Node ");
            if (cMNode.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(cMNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cMNode.lastPos().toString());
            dumpTree(((XSCMUniOp) cMNode).getChild(), i + 1);
            return;
        }
        if (iType != 101 && iType != 102) {
            f63.a("ImplementationMessages.VAL_NIICM");
            return;
        }
        if (iType == 101) {
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
        XSCMBinOp xSCMBinOp = (XSCMBinOp) cMNode;
        int i3 = i + 1;
        dumpTree(xSCMBinOp.getLeft(), i3);
        dumpTree(xSCMBinOp.getRight(), i3);
    }

    private int[] makeDefStateList() {
        int[] iArr = new int[this.fElemMapSize];
        for (int i = 0; i < this.fElemMapSize; i++) {
            iArr[i] = -1;
        }
        return iArr;
    }

    private void postTreeBuildInit(CMNode cMNode) throws RuntimeException {
        cMNode.setMaxStates(this.fLeafCount);
        if (cMNode.type() == 2) {
            XSCMLeaf xSCMLeaf = (XSCMLeaf) cMNode;
            int position = xSCMLeaf.getPosition();
            this.fLeafList[position] = xSCMLeaf;
            this.fLeafListType[position] = 2;
            return;
        }
        if (cMNode.type() == 101 || cMNode.type() == 102) {
            XSCMBinOp xSCMBinOp = (XSCMBinOp) cMNode;
            postTreeBuildInit(xSCMBinOp.getLeft());
            postTreeBuildInit(xSCMBinOp.getRight());
        } else {
            if (cMNode.type() == 4 || cMNode.type() == 6 || cMNode.type() == 5) {
                postTreeBuildInit(((XSCMUniOp) cMNode).getChild());
                return;
            }
            if (cMNode.type() != 1) {
                f63.a("ImplementationMessages.VAL_NIICM");
                return;
            }
            XSCMLeaf xSCMLeaf2 = (XSCMLeaf) cMNode;
            int position2 = xSCMLeaf2.getPosition();
            this.fLeafList[position2] = xSCMLeaf2;
            this.fLeafListType[position2] = 1;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public List<String> checkMinMaxBounds() {
        ArrayList arrayList = null;
        for (int i = 0; i < this.fElemMapSize; i++) {
            int i2 = this.fElemMapCounter[i];
            if (i2 != -1) {
                int i3 = this.fElemMapCounterLowerBound[i];
                int i4 = this.fElemMapCounterUpperBound[i];
                if (i2 < i3) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add("cvc-complex-type.2.4.b");
                    arrayList.add("{" + this.fElemMap[i] + "}");
                }
                if (i4 != -1 && i2 > i4) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add("cvc-complex-type.2.4.d.1");
                    arrayList.add("{" + this.fElemMap[i] + "}");
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0070  */
    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException {
        short s;
        Occurence occurence;
        int i = this.fElemMapSize;
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i, i);
        int i2 = 0;
        while (true) {
            int[][] iArr = this.fTransTable;
            if (i2 >= iArr.length || iArr[i2] == null) {
                break;
            }
            int i3 = 0;
            while (i3 < this.fElemMapSize) {
                int i4 = i3 + 1;
                for (int i5 = i4; i5 < this.fElemMapSize; i5++) {
                    int[] iArr2 = this.fTransTable[i2];
                    if (iArr2[i3] != -1 && iArr2[i5] != -1 && bArr[i3][i5] == 0) {
                        Object[] objArr = this.fElemMap;
                        if (XSConstraints.overlapUPA(objArr[i3], objArr[i5], substitutionGroupHandler)) {
                            Occurence[] occurenceArr = this.fCountingStates;
                            if (occurenceArr == null || (occurence = occurenceArr[i2]) == null) {
                                bArr[i3][i5] = 1;
                            } else {
                                int[] iArr3 = this.fTransTable[i2];
                                if (((iArr3[i5] == i2) ^ (iArr3[i3] == i2)) && occurence.minOccurs == occurence.maxOccurs) {
                                    bArr[i3][i5] = -1;
                                } else {
                                    bArr[i3][i5] = 1;
                                }
                            }
                        } else {
                            bArr[i3][i5] = -1;
                        }
                    }
                }
                i3 = i4;
            }
            i2++;
        }
        for (int i6 = 0; i6 < this.fElemMapSize; i6++) {
            for (int i7 = 0; i7 < this.fElemMapSize; i7++) {
                if (bArr[i6][i7] == 1) {
                    throw new XMLSchemaException("cos-nonambig", new Object[]{this.fElemMap[i6].toString(), this.fElemMap[i7].toString()});
                }
            }
        }
        for (int i8 = 0; i8 < this.fElemMapSize; i8++) {
            if (this.fElemMapType[i8] == 2 && ((s = ((XSWildcardDecl) this.fElemMap[i8]).fType) == 3 || s == 2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean endContentModel(int[] iArr) {
        Occurence occurence;
        int i = iArr[0];
        if (!this.fFinalStateFlags[i]) {
            return false;
        }
        Occurence[] occurenceArr = this.fCountingStates;
        return occurenceArr == null || (occurence = occurenceArr[i]) == null || iArr[2] >= occurence.minOccurs;
    }

    public Object findMatchingDecl(QName qName, int[] iArr, SubstitutionGroupHandler substitutionGroupHandler, int i) {
        int i2 = iArr[0];
        Object matchingElemDecl = null;
        int i3 = 0;
        while (true) {
            i++;
            if (i >= this.fElemMapSize) {
                break;
            }
            i3 = this.fTransTable[i2][i];
            if (i3 != -1) {
                int i4 = this.fElemMapType[i];
                if (i4 != 1) {
                    if (i4 == 2 && ((XSWildcardDecl) this.fElemMap[i]).allowNamespace(qName.uri)) {
                        matchingElemDecl = this.fElemMap[i];
                        break;
                    }
                } else {
                    matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, (XSElementDecl) this.fElemMap[i]);
                    if (matchingElemDecl != null) {
                        break;
                    }
                }
            }
        }
        if (i == this.fElemMapSize) {
            iArr[1] = iArr[0];
            iArr[0] = -1;
            return findMatchingDecl(qName, substitutionGroupHandler);
        }
        iArr[0] = i3;
        Occurence occurence = this.fCountingStates[i3];
        if (occurence != null) {
            iArr[2] = i == occurence.elemIndex ? 1 : 0;
        }
        return matchingElemDecl;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public String getTermName(int i) {
        Object obj = this.fElemMap[i];
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean isCompactedForUPA() {
        return this.fIsCompactedForUPA;
    }

    public boolean isFinalState(int i) {
        if (i < 0) {
            return false;
        }
        return this.fFinalStateFlags[i];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public int[] occurenceInfo(int[] iArr) {
        Occurence[] occurenceArr = this.fCountingStates;
        if (occurenceArr == null) {
            return null;
        }
        int i = iArr[0];
        if (i < 0) {
            i = iArr[1];
        }
        Occurence occurence = occurenceArr[i];
        if (occurence != null) {
            return new int[]{occurence.minOccurs, occurence.maxOccurs, iArr[2], occurence.elemIndex};
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public Object oneTransition(QName qName, int[] iArr, SubstitutionGroupHandler substitutionGroupHandler) {
        int i = iArr[0];
        if (i == -1 || i == -2) {
            if (i == -1) {
                iArr[0] = -2;
            }
            return findMatchingDecl(qName, substitutionGroupHandler);
        }
        Object matchingElemDecl = null;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= this.fElemMapSize) {
                break;
            }
            i3 = this.fTransTable[i][i2];
            if (i3 != -1) {
                int i4 = this.fElemMapType[i2];
                if (i4 != 1) {
                    if (i4 == 2 && ((XSWildcardDecl) this.fElemMap[i2]).allowNamespace(qName.uri)) {
                        matchingElemDecl = this.fElemMap[i2];
                        int[] iArr2 = this.fElemMapCounter;
                        int i5 = iArr2[i2];
                        if (i5 < 0) {
                            break;
                        }
                        iArr2[i2] = i5 + 1;
                        break;
                    }
                } else {
                    matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, (XSElementDecl) this.fElemMap[i2]);
                    if (matchingElemDecl != null) {
                        int[] iArr3 = this.fElemMapCounter;
                        int i6 = iArr3[i2];
                        if (i6 < 0) {
                            break;
                        }
                        iArr3[i2] = i6 + 1;
                        break;
                    }
                }
            }
            i2++;
        }
        if (i2 == this.fElemMapSize) {
            iArr[1] = iArr[0];
            iArr[0] = -1;
            return findMatchingDecl(qName, substitutionGroupHandler);
        }
        Occurence[] occurenceArr = this.fCountingStates;
        if (occurenceArr != null) {
            Occurence occurence = occurenceArr[i];
            if (occurence == null) {
                Occurence occurence2 = occurenceArr[i3];
                if (occurence2 != null) {
                    iArr[2] = i2 != occurence2.elemIndex ? 0 : 1;
                }
            } else if (i == i3) {
                int i7 = iArr[2] + 1;
                iArr[2] = i7;
                int i8 = occurence.maxOccurs;
                if (i7 > i8 && i8 != -1) {
                    return findMatchingDecl(qName, iArr, substitutionGroupHandler, i2);
                }
            } else {
                if (iArr[2] < occurence.minOccurs) {
                    iArr[1] = iArr[0];
                    iArr[0] = -1;
                    return findMatchingDecl(qName, substitutionGroupHandler);
                }
                Occurence occurence3 = occurenceArr[i3];
                if (occurence3 != null) {
                    iArr[2] = i2 != occurence3.elemIndex ? 0 : 1;
                }
            }
        }
        iArr[0] = i3;
        return matchingElemDecl;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public int[] startContentModel() {
        for (int i = 0; i < this.fElemMapSize; i++) {
            int[] iArr = this.fElemMapCounter;
            if (iArr[i] != -1) {
                iArr[i] = 0;
            }
        }
        return new int[3];
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0035  */
    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public List<Object> whatCanGoHere(int[] iArr) {
        int i = iArr[0];
        if (i < 0) {
            i = iArr[1];
        }
        Occurence[] occurenceArr = this.fCountingStates;
        Occurence occurence = occurenceArr != null ? occurenceArr[i] : null;
        int i2 = iArr[2];
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.fElemMapSize; i3++) {
            int i4 = this.fTransTable[i][i3];
            if (i4 != -1) {
                if (occurence == null) {
                    arrayList.add(this.fElemMap[i3]);
                } else if (i == i4) {
                    int i5 = occurence.maxOccurs;
                    if (i2 < i5 || i5 == -1) {
                        arrayList.add(this.fElemMap[i3]);
                    }
                } else if (i2 >= occurence.minOccurs) {
                    arrayList.add(this.fElemMap[i3]);
                }
            }
        }
        return arrayList;
    }

    public Object findMatchingDecl(QName qName, SubstitutionGroupHandler substitutionGroupHandler) {
        for (int i = 0; i < this.fElemMapSize; i++) {
            int i2 = this.fElemMapType[i];
            if (i2 == 1) {
                XSElementDecl matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, (XSElementDecl) this.fElemMap[i]);
                if (matchingElemDecl != null) {
                    return matchingElemDecl;
                }
            } else if (i2 == 2 && ((XSWildcardDecl) this.fElemMap[i]).allowNamespace(qName.uri)) {
                return this.fElemMap[i];
            }
        }
        return null;
    }
}
