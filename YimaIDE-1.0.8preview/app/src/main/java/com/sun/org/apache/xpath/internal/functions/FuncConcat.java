package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncConcat extends FunctionMultiArgs {
    static final long serialVersionUID = 1737228885202314413L;

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionMultiArgs, com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i < 2) {
            reportWrongNumberArgs();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.m_arg0.execute(xPathContext).str());
        stringBuffer.append(this.m_arg1.execute(xPathContext).str());
        Expression expression = this.m_arg2;
        if (expression != null) {
            stringBuffer.append(expression.execute(xPathContext).str());
        }
        if (this.m_args != null) {
            int i = 0;
            while (true) {
                Expression[] expressionArr = this.m_args;
                if (i >= expressionArr.length) {
                    break;
                }
                stringBuffer.append(expressionArr[i].execute(xPathContext).str());
                i++;
            }
        }
        return new XString(stringBuffer.toString());
    }

    @Override // com.sun.org.apache.xpath.internal.functions.FunctionMultiArgs, com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("gtone", null));
    }
}
