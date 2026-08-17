package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncSubstring extends Function3Args {
    static final long serialVersionUID = -5996676095024715502L;

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void checkNumberArgs(int i) throws WrongNumberArgsException {
        if (i < 2) {
            reportWrongNumberArgs();
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003d  */
    /* JADX WARN: Code duplicated, block: B:18:0x004d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x004f  */
    /* JADX WARN: Code duplicated, block: B:20:0x0051  */
    /* JADX WARN: Code duplicated, block: B:23:0x0055  */
    /* JADX WARN: Code duplicated, block: B:25:0x005b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x005e  */
    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        double dRound;
        int i;
        Expression expression;
        XMLString xMLStringSubstring;
        int iRound;
        XMLString xMLStringXstr = this.m_arg0.execute(xPathContext).xstr();
        double dNum = this.m_arg1.execute(xPathContext).num();
        int length = xMLStringXstr.length();
        if (length <= 0) {
            return XString.EMPTYSTRING;
        }
        int i2 = 0;
        if (!Double.isNaN(dNum)) {
            dRound = Math.round(dNum);
            if (dRound > XPath.MATCH_SCORE_QNAME) {
                i = ((int) dRound) - 1;
            }
            expression = this.m_arg2;
            if (expression != null) {
                iRound = ((int) (Math.round(expression.num(xPathContext)) + dRound)) - 1;
                if (iRound >= 0) {
                    if (iRound > length) {
                        i2 = length;
                    } else {
                        i2 = iRound;
                    }
                }
                if (i <= length) {
                    length = i;
                }
                xMLStringSubstring = xMLStringXstr.substring(length, i2);
            } else {
                if (i <= length) {
                    length = i;
                }
                xMLStringSubstring = xMLStringXstr.substring(length);
            }
            return (XString) xMLStringSubstring;
        }
        dRound = -1000000.0d;
        i = 0;
        expression = this.m_arg2;
        if (expression != null) {
            iRound = ((int) (Math.round(expression.num(xPathContext)) + dRound)) - 1;
            if (iRound >= 0) {
                if (iRound > length) {
                    i2 = length;
                } else {
                    i2 = iRound;
                }
            }
            if (i <= length) {
                length = i;
            }
            xMLStringSubstring = xMLStringXstr.substring(length, i2);
        } else {
            if (i <= length) {
                length = i;
            }
            xMLStringSubstring = xMLStringXstr.substring(length);
        }
        return (XString) xMLStringSubstring;
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function3Args, com.sun.org.apache.xpath.internal.functions.Function2Args, com.sun.org.apache.xpath.internal.functions.FunctionOneArg, com.sun.org.apache.xpath.internal.functions.Function
    public void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("ER_TWO_OR_THREE", null));
    }
}
