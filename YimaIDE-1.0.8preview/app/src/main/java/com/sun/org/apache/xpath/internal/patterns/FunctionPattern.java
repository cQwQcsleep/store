package com.sun.org.apache.xpath.internal.patterns;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FunctionPattern extends StepPattern {
    static final long serialVersionUID = -5426793413091209944L;
    Expression m_functionExpr;

    public class FunctionOwner implements ExpressionOwner {
        public FunctionOwner() {
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return FunctionPattern.this.m_functionExpr;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(FunctionPattern.this);
            FunctionPattern.this.m_functionExpr = expression;
        }
    }

    public FunctionPattern(Expression expression, int i, int i2) {
        super(0, null, null, i, i2);
        this.m_functionExpr = expression;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern, com.sun.org.apache.xpath.internal.patterns.NodeTest
    public final void calcScore() {
        this.m_score = NodeTest.SCORE_OTHER;
        if (this.m_targetString == null) {
            calcTargetString();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern
    public void callSubtreeVisitors(XPathVisitor xPathVisitor) {
        this.m_functionExpr.callVisitors(new FunctionOwner(), xPathVisitor);
        super.callSubtreeVisitors(xPathVisitor);
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        int currentNode = xPathContext.getCurrentNode();
        DTMIterator dTMIteratorAsIterator = this.m_functionExpr.asIterator(xPathContext, currentNode);
        XNumber xNumber = NodeTest.SCORE_NONE;
        if (dTMIteratorAsIterator != null) {
            do {
                int iNextNode = dTMIteratorAsIterator.nextNode();
                if (-1 == iNextNode) {
                    break;
                }
                xNumber = iNextNode == currentNode ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE;
            } while (xNumber != NodeTest.SCORE_OTHER);
            dTMIteratorAsIterator.detach();
        }
        return xNumber;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        this.m_functionExpr.fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext, int i, DTM dtm, int i2) throws TransformerException {
        DTMIterator dTMIteratorAsIterator = this.m_functionExpr.asIterator(xPathContext, i);
        XNumber xNumber = NodeTest.SCORE_NONE;
        if (dTMIteratorAsIterator != null) {
            do {
                int iNextNode = dTMIteratorAsIterator.nextNode();
                if (-1 == iNextNode) {
                    break;
                }
                xNumber = iNextNode == i ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE;
            } while (xNumber != NodeTest.SCORE_OTHER);
            dTMIteratorAsIterator.detach();
        }
        return xNumber;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext, int i) throws TransformerException {
        DTMIterator dTMIteratorAsIterator = this.m_functionExpr.asIterator(xPathContext, i);
        XNumber xNumber = NodeTest.SCORE_NONE;
        if (dTMIteratorAsIterator != null) {
            do {
                int iNextNode = dTMIteratorAsIterator.nextNode();
                if (-1 == iNextNode) {
                    break;
                }
                xNumber = iNextNode == i ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE;
            } while (xNumber != NodeTest.SCORE_OTHER);
        }
        dTMIteratorAsIterator.detach();
        return xNumber;
    }
}
