package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelGroupImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.xs.XSTerm;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CMBuilder {
    private static XSEmptyCM fEmptyCM = new XSEmptyCM();
    private XSDeclarationPool fDeclPool = null;
    private int fLeafCount;
    private CMNodeFactory fNodeFactory;
    private int fParticleCount;

    public CMBuilder(CMNodeFactory cMNodeFactory) {
        this.fNodeFactory = cMNodeFactory;
    }

    private CMNode buildCompactSyntaxTree(XSParticleDecl xSParticleDecl) {
        int i;
        int i2 = xSParticleDecl.fMaxOccurs;
        int i3 = xSParticleDecl.fMinOccurs;
        short s = xSParticleDecl.fType;
        if (s == 2 || s == 1) {
            return buildCompactSyntaxTree2(xSParticleDecl, i3, i2);
        }
        CMNode cMNode = null;
        if (s == 3) {
            XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl) xSParticleDecl.fValue;
            int i4 = 0;
            if (xSModelGroupImpl.fParticleCount == 1 && (i3 != 1 || i2 != 1)) {
                return buildCompactSyntaxTree2(xSModelGroupImpl.fParticles[0], i3, i2);
            }
            int i5 = 0;
            while (true) {
                i = xSModelGroupImpl.fParticleCount;
                if (i4 >= i) {
                    break;
                }
                CMNode cMNodeBuildCompactSyntaxTree = buildCompactSyntaxTree(xSModelGroupImpl.fParticles[i4]);
                if (cMNodeBuildCompactSyntaxTree != null) {
                    i5++;
                    if (cMNode != null) {
                        cMNodeBuildCompactSyntaxTree = this.fNodeFactory.getCMBinOpNode(xSModelGroupImpl.fCompositor, cMNode, cMNodeBuildCompactSyntaxTree);
                    }
                    cMNode = cMNodeBuildCompactSyntaxTree;
                }
                i4++;
            }
            if (cMNode != null && xSModelGroupImpl.fCompositor == 101 && i5 < i) {
                return this.fNodeFactory.getCMUniOpNode(5, cMNode);
            }
        }
        return cMNode;
    }

    private CMNode buildCompactSyntaxTree2(XSParticleDecl xSParticleDecl, int i, int i2) {
        if (i == 1 && i2 == 1) {
            CMNodeFactory cMNodeFactory = this.fNodeFactory;
            short s = xSParticleDecl.fType;
            XSTerm xSTerm = xSParticleDecl.fValue;
            int i3 = this.fParticleCount;
            this.fParticleCount = i3 + 1;
            int i4 = this.fLeafCount;
            this.fLeafCount = i4 + 1;
            return cMNodeFactory.getCMLeafNode(s, xSTerm, i3, i4);
        }
        if (i == 0 && i2 == 1) {
            CMNodeFactory cMNodeFactory2 = this.fNodeFactory;
            short s2 = xSParticleDecl.fType;
            XSTerm xSTerm2 = xSParticleDecl.fValue;
            int i5 = this.fParticleCount;
            this.fParticleCount = i5 + 1;
            int i6 = this.fLeafCount;
            this.fLeafCount = i6 + 1;
            return this.fNodeFactory.getCMUniOpNode(5, cMNodeFactory2.getCMLeafNode(s2, xSTerm2, i5, i6));
        }
        if (i == 0 && i2 == -1) {
            CMNodeFactory cMNodeFactory3 = this.fNodeFactory;
            short s3 = xSParticleDecl.fType;
            XSTerm xSTerm3 = xSParticleDecl.fValue;
            int i7 = this.fParticleCount;
            this.fParticleCount = i7 + 1;
            int i8 = this.fLeafCount;
            this.fLeafCount = i8 + 1;
            return this.fNodeFactory.getCMUniOpNode(4, cMNodeFactory3.getCMLeafNode(s3, xSTerm3, i7, i8));
        }
        if (i == 1 && i2 == -1) {
            CMNodeFactory cMNodeFactory4 = this.fNodeFactory;
            short s4 = xSParticleDecl.fType;
            XSTerm xSTerm4 = xSParticleDecl.fValue;
            int i9 = this.fParticleCount;
            this.fParticleCount = i9 + 1;
            int i10 = this.fLeafCount;
            this.fLeafCount = i10 + 1;
            return this.fNodeFactory.getCMUniOpNode(6, cMNodeFactory4.getCMLeafNode(s4, xSTerm4, i9, i10));
        }
        CMNodeFactory cMNodeFactory5 = this.fNodeFactory;
        short s5 = xSParticleDecl.fType;
        XSTerm xSTerm5 = xSParticleDecl.fValue;
        int i11 = this.fParticleCount;
        this.fParticleCount = i11 + 1;
        int i12 = this.fLeafCount;
        this.fLeafCount = i12 + 1;
        CMNode cMRepeatingLeafNode = cMNodeFactory5.getCMRepeatingLeafNode(s5, xSTerm5, i, i2, i11, i12);
        CMNodeFactory cMNodeFactory6 = this.fNodeFactory;
        return i == 0 ? cMNodeFactory6.getCMUniOpNode(4, cMRepeatingLeafNode) : cMNodeFactory6.getCMUniOpNode(6, cMRepeatingLeafNode);
    }

    private CMNode buildSyntaxTree(XSParticleDecl xSParticleDecl, boolean z, boolean z2) {
        boolean zIsCompactedForUPA;
        int i;
        int i2 = xSParticleDecl.fMaxOccurs;
        int i3 = xSParticleDecl.fMinOccurs;
        if (z) {
            if (i3 <= 1) {
                zIsCompactedForUPA = false;
            } else if (i2 > i3 || xSParticleDecl.getMaxOccursUnbounded()) {
                i3 = 1;
                zIsCompactedForUPA = true;
            } else {
                i3 = 2;
                zIsCompactedForUPA = true;
            }
            if (i2 > 1) {
                i2 = 2;
                zIsCompactedForUPA = true;
            }
        } else {
            zIsCompactedForUPA = false;
        }
        short s = xSParticleDecl.fType;
        if (s == 2 || s == 1) {
            CMNodeFactory cMNodeFactory = this.fNodeFactory;
            XSTerm xSTerm = xSParticleDecl.fValue;
            int i4 = this.fParticleCount;
            this.fParticleCount = i4 + 1;
            int i5 = this.fLeafCount;
            this.fLeafCount = i5 + 1;
            CMNode cMNodeExpandContentModel = expandContentModel(cMNodeFactory.getCMLeafNode(s, xSTerm, i4, i5), i3, i2, z2);
            if (cMNodeExpandContentModel != null) {
                cMNodeExpandContentModel.setIsCompactUPAModel(zIsCompactedForUPA);
            }
            return cMNodeExpandContentModel;
        }
        CMNode cMUniOpNode = null;
        if (s == 3) {
            XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl) xSParticleDecl.fValue;
            int i6 = 0;
            boolean z3 = false;
            while (true) {
                i = xSModelGroupImpl.fParticleCount;
                if (i6 >= i) {
                    break;
                }
                CMNode cMNodeBuildSyntaxTree = buildSyntaxTree(xSModelGroupImpl.fParticles[i6], z, z2 && i3 == 1 && i2 == 1 && (xSModelGroupImpl.fCompositor == 102 || i == 1));
                if (cMNodeBuildSyntaxTree != null) {
                    zIsCompactedForUPA |= cMNodeBuildSyntaxTree.isCompactedForUPA();
                    if (cMUniOpNode == null) {
                        cMUniOpNode = cMNodeBuildSyntaxTree;
                    } else {
                        cMUniOpNode = this.fNodeFactory.getCMBinOpNode(xSModelGroupImpl.fCompositor, cMUniOpNode, cMNodeBuildSyntaxTree);
                        z3 = true;
                    }
                }
                i6++;
            }
            if (cMUniOpNode != null) {
                if (xSModelGroupImpl.fCompositor == 101 && !z3 && i > 1) {
                    cMUniOpNode = this.fNodeFactory.getCMUniOpNode(5, cMUniOpNode);
                }
                CMNode cMNodeExpandContentModel2 = expandContentModel(cMUniOpNode, i3, i2, false);
                cMNodeExpandContentModel2.setIsCompactUPAModel(zIsCompactedForUPA);
                return cMNodeExpandContentModel2;
            }
        }
        return cMUniOpNode;
    }

    private CMNode copyNode(CMNode cMNode) {
        int iType = cMNode.type();
        if (iType == 101 || iType == 102) {
            XSCMBinOp xSCMBinOp = (XSCMBinOp) cMNode;
            return this.fNodeFactory.getCMBinOpNode(iType, copyNode(xSCMBinOp.getLeft()), copyNode(xSCMBinOp.getRight()));
        }
        if (iType == 4 || iType == 6 || iType == 5) {
            return this.fNodeFactory.getCMUniOpNode(iType, copyNode(((XSCMUniOp) cMNode).getChild()));
        }
        if (iType != 1 && iType != 2) {
            return cMNode;
        }
        XSCMLeaf xSCMLeaf = (XSCMLeaf) cMNode;
        CMNodeFactory cMNodeFactory = this.fNodeFactory;
        int iType2 = xSCMLeaf.type();
        Object leaf = xSCMLeaf.getLeaf();
        int particleId = xSCMLeaf.getParticleId();
        int i = this.fLeafCount;
        this.fLeafCount = i + 1;
        return cMNodeFactory.getCMLeafNode(iType2, leaf, particleId, i);
    }

    private CMNode expandContentModel(CMNode cMNode, int i, int i2, boolean z) {
        if (i == 1 && i2 == 1) {
            return cMNode;
        }
        if (i == 0 && i2 == 1) {
            return this.fNodeFactory.getCMUniOpNode(5, cMNode);
        }
        if (i == 0 && i2 == -1) {
            return this.fNodeFactory.getCMUniOpNode(4, cMNode);
        }
        if (i == 1 && i2 == -1) {
            return this.fNodeFactory.getCMUniOpNode(6, cMNode);
        }
        if ((z && cMNode.type() == 1) || cMNode.type() == 2) {
            CMNode cMUniOpNode = this.fNodeFactory.getCMUniOpNode(i != 0 ? 6 : 4, cMNode);
            cMUniOpNode.setUserData(new int[]{i, i2});
            return cMUniOpNode;
        }
        if (i2 == -1) {
            return this.fNodeFactory.getCMBinOpNode(102, multiNodes(cMNode, i - 1, true), this.fNodeFactory.getCMUniOpNode(6, cMNode));
        }
        CMNode cMNodeMultiNodes = i > 0 ? multiNodes(cMNode, i, false) : null;
        if (i2 <= i) {
            return cMNodeMultiNodes;
        }
        CMNode cMUniOpNode2 = this.fNodeFactory.getCMUniOpNode(5, cMNode);
        return cMNodeMultiNodes == null ? multiNodes(cMUniOpNode2, i2 - i, false) : this.fNodeFactory.getCMBinOpNode(102, cMNodeMultiNodes, multiNodes(cMUniOpNode2, i2 - i, true));
    }

    private CMNode multiNodes(CMNode cMNode, int i, boolean z) {
        if (i == 0) {
            return null;
        }
        if (i == 1) {
            return z ? copyNode(cMNode) : cMNode;
        }
        int i2 = i / 2;
        return this.fNodeFactory.getCMBinOpNode(102, multiNodes(cMNode, i2, z), multiNodes(cMNode, i - i2, true));
    }

    private boolean useRepeatingLeafNodes(XSParticleDecl xSParticleDecl) {
        int i = xSParticleDecl.fMaxOccurs;
        int i2 = xSParticleDecl.fMinOccurs;
        if (xSParticleDecl.fType == 3) {
            XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl) xSParticleDecl.fValue;
            if (i2 != 1 || i != 1) {
                int i3 = xSModelGroupImpl.fParticleCount;
                if (i3 != 1) {
                    return i3 == 0;
                }
                XSParticleDecl xSParticleDecl2 = xSModelGroupImpl.fParticles[0];
                short s = xSParticleDecl2.fType;
                return (s == 1 || s == 2) && xSParticleDecl2.fMinOccurs == 1 && xSParticleDecl2.fMaxOccurs == 1;
            }
            for (int i4 = 0; i4 < xSModelGroupImpl.fParticleCount; i4++) {
                if (!useRepeatingLeafNodes(xSModelGroupImpl.fParticles[i4])) {
                    return false;
                }
            }
        }
        return true;
    }

    public XSCMValidator createAllCM(XSParticleDecl xSParticleDecl) {
        if (xSParticleDecl.fMaxOccurs == 0) {
            return null;
        }
        XSModelGroupImpl xSModelGroupImpl = (XSModelGroupImpl) xSParticleDecl.fValue;
        XSAllCM xSAllCM = new XSAllCM(xSParticleDecl.fMinOccurs == 0, xSModelGroupImpl.fParticleCount);
        for (int i = 0; i < xSModelGroupImpl.fParticleCount; i++) {
            XSParticleDecl xSParticleDecl2 = xSModelGroupImpl.fParticles[i];
            xSAllCM.addElement((XSElementDecl) xSParticleDecl2.fValue, xSParticleDecl2.fMinOccurs == 0);
        }
        return xSAllCM;
    }

    public XSCMValidator createDFACM(XSParticleDecl xSParticleDecl, boolean z) {
        this.fLeafCount = 0;
        this.fParticleCount = 0;
        CMNode cMNodeBuildCompactSyntaxTree = useRepeatingLeafNodes(xSParticleDecl) ? buildCompactSyntaxTree(xSParticleDecl) : buildSyntaxTree(xSParticleDecl, z, true);
        if (cMNodeBuildCompactSyntaxTree == null) {
            return null;
        }
        return new XSDFACM(cMNodeBuildCompactSyntaxTree, this.fLeafCount);
    }

    public XSCMValidator getContentModel(XSComplexTypeDecl xSComplexTypeDecl, boolean z) {
        short contentType = xSComplexTypeDecl.getContentType();
        if (contentType == 1 || contentType == 0) {
            return null;
        }
        XSParticleDecl xSParticleDecl = (XSParticleDecl) xSComplexTypeDecl.getParticle();
        if (xSParticleDecl == null) {
            return fEmptyCM;
        }
        XSCMValidator xSCMValidatorCreateAllCM = (xSParticleDecl.fType == 3 && ((XSModelGroupImpl) xSParticleDecl.fValue).fCompositor == 103) ? createAllCM(xSParticleDecl) : createDFACM(xSParticleDecl, z);
        this.fNodeFactory.resetNodeCount();
        return xSCMValidatorCreateAllCM == null ? fEmptyCM : xSCMValidatorCreateAllCM;
    }

    public void setDeclPool(XSDeclarationPool xSDeclarationPool) {
        this.fDeclPool = xSDeclarationPool;
    }
}
