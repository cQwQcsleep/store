package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSDeclarationPool {
    private static final int CHUNK_MASK = 255;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private SchemaDVFactoryImpl dvFactory;
    private XSElementDecl[][] fElementDecl = new XSElementDecl[4][];
    private int fElementDeclIndex = 0;
    private XSParticleDecl[][] fParticleDecl = new XSParticleDecl[4][];
    private int fParticleDeclIndex = 0;
    private XSModelGroupImpl[][] fModelGroup = new XSModelGroupImpl[4][];
    private int fModelGroupIndex = 0;
    private XSAttributeDecl[][] fAttrDecl = new XSAttributeDecl[4][];
    private int fAttrDeclIndex = 0;
    private XSComplexTypeDecl[][] fCTDecl = new XSComplexTypeDecl[4][];
    private int fCTDeclIndex = 0;
    private XSSimpleTypeDecl[][] fSTDecl = new XSSimpleTypeDecl[4][];
    private int fSTDeclIndex = 0;
    private XSAttributeUseImpl[][] fAttributeUse = new XSAttributeUseImpl[4][];
    private int fAttributeUseIndex = 0;

    private boolean ensureAttrDeclCapacity(int i) {
        XSAttributeDecl[][] xSAttributeDeclArr = this.fAttrDecl;
        if (i >= xSAttributeDeclArr.length) {
            this.fAttrDecl = resize(xSAttributeDeclArr, xSAttributeDeclArr.length * 2);
        } else if (xSAttributeDeclArr[i] != null) {
            return false;
        }
        this.fAttrDecl[i] = new XSAttributeDecl[256];
        return true;
    }

    private boolean ensureAttributeUseCapacity(int i) {
        XSAttributeUseImpl[][] xSAttributeUseImplArr = this.fAttributeUse;
        if (i >= xSAttributeUseImplArr.length) {
            this.fAttributeUse = resize(xSAttributeUseImplArr, xSAttributeUseImplArr.length * 2);
        } else if (xSAttributeUseImplArr[i] != null) {
            return false;
        }
        this.fAttributeUse[i] = new XSAttributeUseImpl[256];
        return true;
    }

    private boolean ensureCTDeclCapacity(int i) {
        XSComplexTypeDecl[][] xSComplexTypeDeclArr = this.fCTDecl;
        if (i >= xSComplexTypeDeclArr.length) {
            this.fCTDecl = resize(xSComplexTypeDeclArr, xSComplexTypeDeclArr.length * 2);
        } else if (xSComplexTypeDeclArr[i] != null) {
            return false;
        }
        this.fCTDecl[i] = new XSComplexTypeDecl[256];
        return true;
    }

    private boolean ensureElementDeclCapacity(int i) {
        XSElementDecl[][] xSElementDeclArr = this.fElementDecl;
        if (i >= xSElementDeclArr.length) {
            this.fElementDecl = resize(xSElementDeclArr, xSElementDeclArr.length * 2);
        } else if (xSElementDeclArr[i] != null) {
            return false;
        }
        this.fElementDecl[i] = new XSElementDecl[256];
        return true;
    }

    private boolean ensureModelGroupCapacity(int i) {
        XSModelGroupImpl[][] xSModelGroupImplArr = this.fModelGroup;
        if (i >= xSModelGroupImplArr.length) {
            this.fModelGroup = resize(xSModelGroupImplArr, xSModelGroupImplArr.length * 2);
        } else if (xSModelGroupImplArr[i] != null) {
            return false;
        }
        this.fModelGroup[i] = new XSModelGroupImpl[256];
        return true;
    }

    private boolean ensureParticleDeclCapacity(int i) {
        XSParticleDecl[][] xSParticleDeclArr = this.fParticleDecl;
        if (i >= xSParticleDeclArr.length) {
            this.fParticleDecl = resize(xSParticleDeclArr, xSParticleDeclArr.length * 2);
        } else if (xSParticleDeclArr[i] != null) {
            return false;
        }
        this.fParticleDecl[i] = new XSParticleDecl[256];
        return true;
    }

    private boolean ensureSTDeclCapacity(int i) {
        XSSimpleTypeDecl[][] xSSimpleTypeDeclArr = this.fSTDecl;
        if (i >= xSSimpleTypeDeclArr.length) {
            this.fSTDecl = resize(xSSimpleTypeDeclArr, xSSimpleTypeDeclArr.length * 2);
        } else if (xSSimpleTypeDeclArr[i] != null) {
            return false;
        }
        this.fSTDecl[i] = new XSSimpleTypeDecl[256];
        return true;
    }

    private static XSElementDecl[][] resize(XSElementDecl[][] xSElementDeclArr, int i) {
        XSElementDecl[][] xSElementDeclArr2 = new XSElementDecl[i][];
        System.arraycopy(xSElementDeclArr, 0, xSElementDeclArr2, 0, xSElementDeclArr.length);
        return xSElementDeclArr2;
    }

    public final XSAttributeDecl getAttributeDecl() {
        int i = this.fAttrDeclIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureAttrDeclCapacity(i2);
        XSAttributeDecl[] xSAttributeDeclArr = this.fAttrDecl[i2];
        XSAttributeDecl xSAttributeDecl = xSAttributeDeclArr[i3];
        if (xSAttributeDecl == null) {
            xSAttributeDeclArr[i3] = new XSAttributeDecl();
        } else {
            xSAttributeDecl.reset();
        }
        this.fAttrDeclIndex++;
        return this.fAttrDecl[i2][i3];
    }

    public final XSAttributeUseImpl getAttributeUse() {
        int i = this.fAttributeUseIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureAttributeUseCapacity(i2);
        XSAttributeUseImpl[] xSAttributeUseImplArr = this.fAttributeUse[i2];
        XSAttributeUseImpl xSAttributeUseImpl = xSAttributeUseImplArr[i3];
        if (xSAttributeUseImpl == null) {
            xSAttributeUseImplArr[i3] = new XSAttributeUseImpl();
        } else {
            xSAttributeUseImpl.reset();
        }
        this.fAttributeUseIndex++;
        return this.fAttributeUse[i2][i3];
    }

    public final XSComplexTypeDecl getComplexTypeDecl() {
        int i = this.fCTDeclIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureCTDeclCapacity(i2);
        XSComplexTypeDecl[] xSComplexTypeDeclArr = this.fCTDecl[i2];
        XSComplexTypeDecl xSComplexTypeDecl = xSComplexTypeDeclArr[i3];
        if (xSComplexTypeDecl == null) {
            xSComplexTypeDeclArr[i3] = new XSComplexTypeDecl();
        } else {
            xSComplexTypeDecl.reset();
        }
        this.fCTDeclIndex++;
        return this.fCTDecl[i2][i3];
    }

    public final XSElementDecl getElementDecl() {
        int i = this.fElementDeclIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureElementDeclCapacity(i2);
        XSElementDecl[] xSElementDeclArr = this.fElementDecl[i2];
        XSElementDecl xSElementDecl = xSElementDeclArr[i3];
        if (xSElementDecl == null) {
            xSElementDeclArr[i3] = new XSElementDecl();
        } else {
            xSElementDecl.reset();
        }
        this.fElementDeclIndex++;
        return this.fElementDecl[i2][i3];
    }

    public final XSModelGroupImpl getModelGroup() {
        int i = this.fModelGroupIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureModelGroupCapacity(i2);
        XSModelGroupImpl[] xSModelGroupImplArr = this.fModelGroup[i2];
        XSModelGroupImpl xSModelGroupImpl = xSModelGroupImplArr[i3];
        if (xSModelGroupImpl == null) {
            xSModelGroupImplArr[i3] = new XSModelGroupImpl();
        } else {
            xSModelGroupImpl.reset();
        }
        this.fModelGroupIndex++;
        return this.fModelGroup[i2][i3];
    }

    public final XSParticleDecl getParticleDecl() {
        int i = this.fParticleDeclIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureParticleDeclCapacity(i2);
        XSParticleDecl[] xSParticleDeclArr = this.fParticleDecl[i2];
        XSParticleDecl xSParticleDecl = xSParticleDeclArr[i3];
        if (xSParticleDecl == null) {
            xSParticleDeclArr[i3] = new XSParticleDecl();
        } else {
            xSParticleDecl.reset();
        }
        this.fParticleDeclIndex++;
        return this.fParticleDecl[i2][i3];
    }

    public final XSSimpleTypeDecl getSimpleTypeDecl() {
        int i = this.fSTDeclIndex;
        int i2 = i >> 8;
        int i3 = i & 255;
        ensureSTDeclCapacity(i2);
        XSSimpleTypeDecl[] xSSimpleTypeDeclArr = this.fSTDecl[i2];
        XSSimpleTypeDecl xSSimpleTypeDecl = xSSimpleTypeDeclArr[i3];
        if (xSSimpleTypeDecl == null) {
            xSSimpleTypeDeclArr[i3] = this.dvFactory.newXSSimpleTypeDecl();
        } else {
            xSSimpleTypeDecl.reset();
        }
        this.fSTDeclIndex++;
        return this.fSTDecl[i2][i3];
    }

    public void reset() {
        this.fElementDeclIndex = 0;
        this.fParticleDeclIndex = 0;
        this.fModelGroupIndex = 0;
        this.fSTDeclIndex = 0;
        this.fCTDeclIndex = 0;
        this.fAttrDeclIndex = 0;
        this.fAttributeUseIndex = 0;
    }

    public void setDVFactory(SchemaDVFactoryImpl schemaDVFactoryImpl) {
        this.dvFactory = schemaDVFactoryImpl;
    }

    private static XSParticleDecl[][] resize(XSParticleDecl[][] xSParticleDeclArr, int i) {
        XSParticleDecl[][] xSParticleDeclArr2 = new XSParticleDecl[i][];
        System.arraycopy(xSParticleDeclArr, 0, xSParticleDeclArr2, 0, xSParticleDeclArr.length);
        return xSParticleDeclArr2;
    }

    private static XSModelGroupImpl[][] resize(XSModelGroupImpl[][] xSModelGroupImplArr, int i) {
        XSModelGroupImpl[][] xSModelGroupImplArr2 = new XSModelGroupImpl[i][];
        System.arraycopy(xSModelGroupImplArr, 0, xSModelGroupImplArr2, 0, xSModelGroupImplArr.length);
        return xSModelGroupImplArr2;
    }

    private static XSAttributeDecl[][] resize(XSAttributeDecl[][] xSAttributeDeclArr, int i) {
        XSAttributeDecl[][] xSAttributeDeclArr2 = new XSAttributeDecl[i][];
        System.arraycopy(xSAttributeDeclArr, 0, xSAttributeDeclArr2, 0, xSAttributeDeclArr.length);
        return xSAttributeDeclArr2;
    }

    private static XSAttributeUseImpl[][] resize(XSAttributeUseImpl[][] xSAttributeUseImplArr, int i) {
        XSAttributeUseImpl[][] xSAttributeUseImplArr2 = new XSAttributeUseImpl[i][];
        System.arraycopy(xSAttributeUseImplArr, 0, xSAttributeUseImplArr2, 0, xSAttributeUseImplArr.length);
        return xSAttributeUseImplArr2;
    }

    private static XSSimpleTypeDecl[][] resize(XSSimpleTypeDecl[][] xSSimpleTypeDeclArr, int i) {
        XSSimpleTypeDecl[][] xSSimpleTypeDeclArr2 = new XSSimpleTypeDecl[i][];
        System.arraycopy(xSSimpleTypeDeclArr, 0, xSSimpleTypeDeclArr2, 0, xSSimpleTypeDeclArr.length);
        return xSSimpleTypeDeclArr2;
    }

    private static XSComplexTypeDecl[][] resize(XSComplexTypeDecl[][] xSComplexTypeDeclArr, int i) {
        XSComplexTypeDecl[][] xSComplexTypeDeclArr2 = new XSComplexTypeDecl[i][];
        System.arraycopy(xSComplexTypeDeclArr, 0, xSComplexTypeDeclArr2, 0, xSComplexTypeDeclArr.length);
        return xSComplexTypeDeclArr2;
    }
}
