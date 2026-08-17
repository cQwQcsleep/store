package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.VariableStack;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathVariableResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JAXPVariableStack extends VariableStack {
    private final XPathVariableResolver resolver;

    public JAXPVariableStack(XPathVariableResolver xPathVariableResolver) {
        this.resolver = xPathVariableResolver;
    }

    @Override // com.sun.org.apache.xpath.internal.VariableStack
    public XObject getVariableOrParam(XPathContext xPathContext, QName qName) throws TransformerException, IllegalArgumentException {
        if (qName == null) {
            w01.a(XPATHMessages.createXPATHMessage("ER_ARG_CANNOT_BE_NULL", new Object[]{"Variable qname"}));
            return null;
        }
        javax.xml.namespace.QName qName2 = new javax.xml.namespace.QName(qName.getNamespace(), qName.getLocalPart());
        Object objResolveVariable = this.resolver.resolveVariable(qName2);
        if (objResolveVariable != null) {
            return XObject.create(objResolveVariable, xPathContext);
        }
        throw new TransformerException(XPATHMessages.createXPATHMessage("ER_RESOLVE_VARIABLE_RETURNS_NULL", new Object[]{qName2.toString()}));
    }
}
