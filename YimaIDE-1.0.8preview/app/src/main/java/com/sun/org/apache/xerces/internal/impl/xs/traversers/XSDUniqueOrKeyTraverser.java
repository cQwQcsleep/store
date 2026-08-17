package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import com.sun.org.apache.xerces.internal.impl.xs.identity.UniqueOrKey;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDUniqueOrKeyTraverser extends XSDAbstractIDConstraintTraverser {
    public XSDUniqueOrKeyTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    public void traverse(Element element, XSElementDecl xSElementDecl, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        String str = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (str == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{DOMUtil.getLocalName(element), SchemaSymbols.ATT_NAME}, element);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            return;
        }
        UniqueOrKey uniqueOrKey = DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_UNIQUE) ? new UniqueOrKey(xSDocumentInfo.fTargetNamespace, str, xSElementDecl.fName, (short) 3) : new UniqueOrKey(xSDocumentInfo.fTargetNamespace, str, xSElementDecl.fName, (short) 1);
        if (traverseIdentityConstraint(uniqueOrKey, element, xSDocumentInfo, objArrCheckAttributes)) {
            if (schemaGrammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName()) == null) {
                schemaGrammar.addIDConstraintDecl(xSElementDecl, uniqueOrKey);
            }
            String strSchemaDocument2SystemId = this.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
            IdentityConstraint iDConstraintDecl = schemaGrammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName(), strSchemaDocument2SystemId);
            if (iDConstraintDecl == null) {
                schemaGrammar.addIDConstraintDecl(xSElementDecl, uniqueOrKey, strSchemaDocument2SystemId);
            }
            XSDHandler xSDHandler = this.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (iDConstraintDecl != null && (iDConstraintDecl instanceof UniqueOrKey)) {
                    uniqueOrKey = (UniqueOrKey) iDConstraintDecl;
                }
                xSDHandler.addIDConstraintDecl(uniqueOrKey);
            }
        }
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
    }
}
