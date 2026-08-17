package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XNumber extends XObject {
    static final long serialVersionUID = -2720400709619020193L;
    double m_val;

    public XNumber(Number number) {
        this.m_val = number.doubleValue();
        setObject(number);
    }

    private static String zeros(int i) {
        if (i < 1) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = '0';
        }
        return new String(cArr);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean bool() {
        return (Double.isNaN(this.m_val) || this.m_val == XPath.MATCH_SCORE_QNAME) ? false : true;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        xPathVisitor.visitNumberLiteral(expressionOwner, this);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        int type = xObject.getType();
        try {
            if (type == 4) {
                return xObject.equals((XObject) this);
            }
            if (type == 1) {
                return xObject.bool() == bool();
            }
            return this.m_val == xObject.num();
        } catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return 2;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String getTypeString() {
        return "#NUMBER";
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean isStableNumber() {
        return true;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public double num() {
        return this.m_val;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        if (this.m_obj == null) {
            setObject(Double.valueOf(this.m_val));
        }
        return this.m_obj;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        String str;
        if (Double.isNaN(this.m_val)) {
            return "NaN";
        }
        boolean zIsInfinite = Double.isInfinite(this.m_val);
        double d = this.m_val;
        if (zIsInfinite) {
            return d > XPath.MATCH_SCORE_QNAME ? Constants.ATTRVAL_INFINITY : "-Infinity";
        }
        String string = Double.toString(d);
        int length = string.length();
        int i = length - 2;
        if (string.charAt(i) == '.' && string.charAt(length - 1) == '0') {
            String strSubstring = string.substring(0, i);
            return strSubstring.equals("-0") ? "0" : strSubstring;
        }
        int iIndexOf = string.indexOf(69);
        if (iIndexOf < 0) {
            int i2 = length - 1;
            return string.charAt(i2) == '0' ? string.substring(0, i2) : string;
        }
        int i3 = Integer.parseInt(string.substring(iIndexOf + 1));
        if (string.charAt(0) == '-') {
            string = string.substring(1);
            iIndexOf--;
            str = "-";
        } else {
            str = "";
        }
        int i4 = iIndexOf - 2;
        if (i3 >= i4) {
            return str + string.substring(0, 1) + string.substring(2, iIndexOf) + zeros(i3 - i4);
        }
        while (string.charAt(iIndexOf - 1) == '0') {
            iIndexOf--;
        }
        if (i3 <= 0) {
            return str + "0." + zeros((-1) - i3) + string.substring(0, 1) + string.substring(2, iIndexOf);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(string.substring(0, 1));
        int i5 = i3 + 2;
        sb.append(string.substring(2, i5));
        sb.append(Constants.ATTRVAL_THIS);
        sb.append(string.substring(i5, iIndexOf));
        return sb.toString();
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public double num(XPathContext xPathContext) throws TransformerException {
        return this.m_val;
    }

    public XNumber(double d) {
        this.m_val = d;
    }
}
