package com.sun.org.apache.xpath.internal.patterns;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnionPattern extends Expression {
    static final long serialVersionUID = -6670449967116905820L;
    private StepPattern[] m_patterns;

    public class UnionPathPartOwner implements ExpressionOwner {
        int m_index;

        public UnionPathPartOwner(int i) {
            this.m_index = i;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return UnionPattern.this.m_patterns[this.m_index];
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(UnionPattern.this);
            UnionPattern.this.m_patterns[this.m_index] = (StepPattern) expression;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        xPathVisitor.visitUnionPattern(expressionOwner, this);
        StepPattern[] stepPatternArr = this.m_patterns;
        if (stepPatternArr != null) {
            int length = stepPatternArr.length;
            for (int i = 0; i < length; i++) {
                this.m_patterns[i].callVisitors(new UnionPathPartOwner(i), xPathVisitor);
            }
        }
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        StepPattern[] stepPatternArr = this.m_patterns;
        if (stepPatternArr != null) {
            int length = stepPatternArr.length;
            for (int i = 0; i < length; i++) {
                if (this.m_patterns[i].canTraverseOutsideSubtree()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!isSameClass(expression)) {
            return false;
        }
        UnionPattern unionPattern = (UnionPattern) expression;
        StepPattern[] stepPatternArr = this.m_patterns;
        if (stepPatternArr == null) {
            return unionPattern.m_patterns == null;
        }
        int length = stepPatternArr.length;
        StepPattern[] stepPatternArr2 = unionPattern.m_patterns;
        if (stepPatternArr2 == null || stepPatternArr2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!this.m_patterns[i].deepEquals(unionPattern.m_patterns[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        int length = this.m_patterns.length;
        XObject xObject = null;
        for (int i = 0; i < length; i++) {
            XObject xObjectExecute = this.m_patterns[i].execute(xPathContext);
            if (xObjectExecute != NodeTest.SCORE_NONE && (xObject == null || xObjectExecute.num() > xObject.num())) {
                xObject = xObjectExecute;
            }
        }
        return xObject == null ? NodeTest.SCORE_NONE : xObject;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        int i2 = 0;
        while (true) {
            StepPattern[] stepPatternArr = this.m_patterns;
            if (i2 >= stepPatternArr.length) {
                return;
            }
            stepPatternArr[i2].fixupVariables(list, i);
            i2++;
        }
    }

    public StepPattern[] getPatterns() {
        return this.m_patterns;
    }

    public void setPatterns(StepPattern[] stepPatternArr) {
        this.m_patterns = stepPatternArr;
        if (stepPatternArr != null) {
            for (StepPattern stepPattern : stepPatternArr) {
                stepPattern.exprSetParent(this);
            }
        }
    }
}
