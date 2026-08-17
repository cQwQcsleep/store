package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.ExtensionsProvider;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.compiler.FunctionTable;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncExtFunctionAvailable extends FunctionOneArg {
    static final long serialVersionUID = 5118814314918592241L;
    private transient FunctionTable m_functionTable = null;

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String namespaceForPrefix;
        String str = this.m_arg0.execute(xPathContext).str();
        int iIndexOf = str.indexOf(58);
        if (iIndexOf < 0) {
            namespaceForPrefix = "http://www.w3.org/1999/XSL/Transform";
        } else {
            namespaceForPrefix = xPathContext.getNamespaceContext().getNamespaceForPrefix(str.substring(0, iIndexOf));
            if (namespaceForPrefix == null) {
                return XBoolean.S_FALSE;
            }
            str = str.substring(iIndexOf + 1);
        }
        if (!namespaceForPrefix.equals("http://www.w3.org/1999/XSL/Transform")) {
            return ((ExtensionsProvider) xPathContext.getOwnerObject()).functionAvailable(namespaceForPrefix, str) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
        }
        try {
            if (this.m_functionTable == null) {
                this.m_functionTable = new FunctionTable();
            }
            return this.m_functionTable.functionAvailable(str) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
        } catch (Exception unused) {
            return XBoolean.S_FALSE;
        }
    }

    public void setFunctionTable(FunctionTable functionTable) {
        this.m_functionTable = functionTable;
    }
}
