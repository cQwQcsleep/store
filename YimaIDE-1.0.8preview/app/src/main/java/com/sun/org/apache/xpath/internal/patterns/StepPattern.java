package com.sun.org.apache.xpath.internal.patterns;

import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.axes.SubContextList;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StepPattern extends NodeTest implements SubContextList, ExpressionOwner {
    private static final boolean DEBUG_MATCHES = false;
    static final long serialVersionUID = 9071668960168152644L;
    protected int m_axis;
    Expression[] m_predicates;
    StepPattern m_relativePathPattern;
    String m_targetString;

    public class PredOwner implements ExpressionOwner {
        int m_index;

        public PredOwner(int i) {
            this.m_index = i;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return StepPattern.this.m_predicates[this.m_index];
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(StepPattern.this);
            StepPattern.this.m_predicates[this.m_index] = expression;
        }
    }

    public StepPattern(int i, String str, String str2, int i2, int i3) {
        super(i, str, str2);
        this.m_axis = i2;
    }

    private final boolean checkProximityPosition(XPathContext xPathContext, int i, DTM dtm, int i2, int i3) {
        boolean z;
        try {
            DTMAxisTraverser axisTraverser = dtm.getAxisTraverser(12);
            int iFirst = axisTraverser.first(i2);
            while (-1 != iFirst) {
                try {
                    xPathContext.pushCurrentNode(iFirst);
                    if (NodeTest.SCORE_NONE != super.execute(xPathContext, iFirst)) {
                        try {
                            xPathContext.pushSubContextList(this);
                            int i4 = 0;
                            while (true) {
                                if (i4 >= i) {
                                    z = true;
                                    break;
                                }
                                xPathContext.pushPredicatePos(i4);
                                try {
                                    XObject xObjectExecute = this.m_predicates[i4].execute(xPathContext);
                                    try {
                                        if (2 == xObjectExecute.getType()) {
                                            throw new Error("Why: Should never have been called");
                                        }
                                        if (!xObjectExecute.boolWithSideEffects()) {
                                            xObjectExecute.detach();
                                            xPathContext.popPredicatePos();
                                            z = false;
                                            break;
                                        }
                                        xObjectExecute.detach();
                                        xPathContext.popPredicatePos();
                                        i4++;
                                    } catch (Throwable th) {
                                        xObjectExecute.detach();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    xPathContext.popPredicatePos();
                                    throw th2;
                                }
                            }
                            xPathContext.popSubContextList();
                            if (z) {
                                i3--;
                            }
                            if (i3 < 1) {
                                xPathContext.popCurrentNode();
                                return false;
                            }
                        } catch (Throwable th3) {
                            xPathContext.popSubContextList();
                            throw th3;
                        }
                    }
                    xPathContext.popCurrentNode();
                    iFirst = axisTraverser.next(i2, iFirst);
                } catch (Throwable th4) {
                    xPathContext.popCurrentNode();
                    throw th4;
                }
            }
            return i3 == 1;
        } catch (TransformerException e) {
            f63.a(e.getMessage());
            return false;
        }
    }

    private final int getProximityPosition(XPathContext xPathContext, int i, boolean z) {
        boolean z2;
        int currentNode = xPathContext.getCurrentNode();
        DTM dtm = xPathContext.getDTM(currentNode);
        int parent = dtm.getParent(currentNode);
        try {
            DTMAxisTraverser axisTraverser = dtm.getAxisTraverser(3);
            int i2 = 0;
            for (int iFirst = axisTraverser.first(parent); -1 != iFirst; iFirst = axisTraverser.next(parent, iFirst)) {
                try {
                    xPathContext.pushCurrentNode(iFirst);
                    if (NodeTest.SCORE_NONE != super.execute(xPathContext, iFirst)) {
                        try {
                            xPathContext.pushSubContextList(this);
                            int i3 = 0;
                            while (true) {
                                if (i3 < i) {
                                    xPathContext.pushPredicatePos(i3);
                                    try {
                                        XObject xObjectExecute = this.m_predicates[i3].execute(xPathContext);
                                        try {
                                            if (2 == xObjectExecute.getType()) {
                                                if (i2 + 1 != ((int) xObjectExecute.numWithSideEffects())) {
                                                    xObjectExecute.detach();
                                                    xPathContext.popPredicatePos();
                                                    z2 = false;
                                                }
                                                xObjectExecute.detach();
                                                xPathContext.popPredicatePos();
                                                i3++;
                                            } else {
                                                if (!xObjectExecute.boolWithSideEffects()) {
                                                    xObjectExecute.detach();
                                                    xPathContext.popPredicatePos();
                                                    z2 = false;
                                                }
                                                xObjectExecute.detach();
                                                xPathContext.popPredicatePos();
                                                i3++;
                                            }
                                        } catch (Throwable th) {
                                            xObjectExecute.detach();
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        xPathContext.popPredicatePos();
                                        throw th2;
                                    }
                                } else {
                                    z2 = true;
                                }
                                xPathContext.popSubContextList();
                                if (z2) {
                                    i2++;
                                }
                                if (!z && iFirst == currentNode) {
                                    xPathContext.popCurrentNode();
                                    return i2;
                                }
                            }
                        } catch (Throwable th3) {
                            xPathContext.popSubContextList();
                            throw th3;
                        }
                    }
                    xPathContext.popCurrentNode();
                } catch (Throwable th4) {
                    xPathContext.popCurrentNode();
                    throw th4;
                }
            }
            return i2;
        } catch (TransformerException e) {
            f63.a(e.getMessage());
            return 0;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest
    public void calcScore() {
        if (getPredicateCount() > 0 || this.m_relativePathPattern != null) {
            this.m_score = NodeTest.SCORE_OTHER;
        } else {
            super.calcScore();
        }
        if (this.m_targetString == null) {
            calcTargetString();
        }
    }

    public void calcTargetString() {
        int whatToShow = getWhatToShow();
        if (whatToShow == -1) {
            this.m_targetString = "*";
            return;
        }
        if (whatToShow == 1) {
            if ("*".equals(this.m_name)) {
                this.m_targetString = "*";
                return;
            } else {
                this.m_targetString = this.m_name;
                return;
            }
        }
        if (whatToShow == 4 || whatToShow == 8 || whatToShow == 12) {
            this.m_targetString = PsuedoNames.PSEUDONAME_TEXT;
            return;
        }
        if (whatToShow == 128) {
            this.m_targetString = PsuedoNames.PSEUDONAME_COMMENT;
        } else if (whatToShow == 256 || whatToShow == 1280) {
            this.m_targetString = PsuedoNames.PSEUDONAME_ROOT;
        } else {
            this.m_targetString = "*";
        }
    }

    public void callSubtreeVisitors(XPathVisitor xPathVisitor) {
        Expression[] expressionArr = this.m_predicates;
        if (expressionArr != null) {
            int length = expressionArr.length;
            for (int i = 0; i < length; i++) {
                PredOwner predOwner = new PredOwner(i);
                if (xPathVisitor.visitPredicate(predOwner, this.m_predicates[i])) {
                    this.m_predicates[i].callVisitors(predOwner, xPathVisitor);
                }
            }
        }
        StepPattern stepPattern = this.m_relativePathPattern;
        if (stepPattern != null) {
            stepPattern.callVisitors(this, xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.XPathVisitable
    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        if (xPathVisitor.visitMatchPattern(expressionOwner, this)) {
            callSubtreeVisitors(xPathVisitor);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean canTraverseOutsideSubtree() {
        int predicateCount = getPredicateCount();
        for (int i = 0; i < predicateCount; i++) {
            if (getPredicate(i).canTraverseOutsideSubtree()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!super.deepEquals(expression)) {
            return false;
        }
        StepPattern stepPattern = (StepPattern) expression;
        Expression[] expressionArr = this.m_predicates;
        if (expressionArr != null) {
            int length = expressionArr.length;
            Expression[] expressionArr2 = stepPattern.m_predicates;
            if (expressionArr2 == null || expressionArr2.length != length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!this.m_predicates[i].deepEquals(stepPattern.m_predicates[i])) {
                    return false;
                }
            }
        } else if (stepPattern.m_predicates != null) {
            return false;
        }
        StepPattern stepPattern2 = this.m_relativePathPattern;
        if (stepPattern2 != null) {
            return stepPattern2.deepEquals(stepPattern.m_relativePathPattern);
        }
        return stepPattern.m_relativePathPattern == null;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext, int i, DTM dtm, int i2) throws TransformerException {
        if (this.m_whatToShow == 65536) {
            StepPattern stepPattern = this.m_relativePathPattern;
            return stepPattern != null ? stepPattern.execute(xPathContext) : NodeTest.SCORE_NONE;
        }
        XObject xObjectExecute = super.execute(xPathContext, i, dtm, i2);
        XNumber xNumber = NodeTest.SCORE_NONE;
        if (xObjectExecute == xNumber || (getPredicateCount() != 0 && !executePredicates(xPathContext, dtm, i))) {
            return xNumber;
        }
        StepPattern stepPattern2 = this.m_relativePathPattern;
        return stepPattern2 != null ? stepPattern2.executeRelativePathPattern(xPathContext, dtm, i) : xObjectExecute;
    }

    public final boolean executePredicates(XPathContext xPathContext, DTM dtm, int i) throws Throwable {
        XPathContext xPathContext2;
        Throwable th;
        Throwable th2;
        XObject xObjectExecute;
        DTM dtm2;
        int i2;
        StepPattern stepPattern;
        int predicateCount = getPredicateCount();
        try {
            xPathContext.pushSubContextList(this);
            boolean z = false;
            boolean z2 = false;
            int i3 = 0;
            while (true) {
                if (i3 < predicateCount) {
                    xPathContext.pushPredicatePos(i3);
                    try {
                        xObjectExecute = this.m_predicates[i3].execute(xPathContext);
                        try {
                            if (2 == xObjectExecute.getType()) {
                                int iNum = (int) xObjectExecute.num();
                                if (z2) {
                                    z = iNum == 1;
                                    try {
                                        xObjectExecute.detach();
                                        try {
                                            xPathContext.popPredicatePos();
                                            xPathContext2 = xPathContext;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            xPathContext2 = xPathContext;
                                            xPathContext2.popSubContextList();
                                            throw th;
                                        }
                                    } catch (Throwable th4) {
                                        th2 = th4;
                                        xPathContext2 = xPathContext;
                                        xPathContext2.popPredicatePos();
                                        throw th2;
                                    }
                                } else {
                                    StepPattern stepPattern2 = this;
                                    xPathContext2 = xPathContext;
                                    dtm2 = dtm;
                                    i2 = i;
                                    try {
                                        stepPattern = stepPattern2;
                                        if (!stepPattern2.checkProximityPosition(xPathContext2, i3, dtm2, i2, iNum)) {
                                            break;
                                        }
                                        z2 = true;
                                        try {
                                            xObjectExecute.detach();
                                            try {
                                                xPathContext2.popPredicatePos();
                                                i3++;
                                                this = stepPattern;
                                                xPathContext = xPathContext2;
                                                dtm = dtm2;
                                                i = i2;
                                            } catch (Throwable th5) {
                                                th = th5;
                                                th = th;
                                                xPathContext2.popSubContextList();
                                                throw th;
                                            }
                                        } catch (Throwable th6) {
                                            th = th6;
                                            th2 = th;
                                            xPathContext2.popPredicatePos();
                                            throw th2;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                    }
                                }
                            } else {
                                xPathContext2 = xPathContext;
                                dtm2 = dtm;
                                i2 = i;
                                stepPattern = this;
                                if (!xObjectExecute.boolWithSideEffects()) {
                                    break;
                                }
                                xObjectExecute.detach();
                                xPathContext2.popPredicatePos();
                                i3++;
                                this = stepPattern;
                                xPathContext = xPathContext2;
                                dtm = dtm2;
                                i = i2;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                        }
                        Throwable th9 = th;
                        xObjectExecute.detach();
                        throw th9;
                    } catch (Throwable th10) {
                        th = th10;
                        xPathContext2 = xPathContext;
                    }
                } else {
                    xPathContext2 = xPathContext;
                    z = true;
                }
                xPathContext2.popSubContextList();
                return z;
            }
            xObjectExecute.detach();
            xPathContext2.popPredicatePos();
            xPathContext2.popSubContextList();
            return z;
        } catch (Throwable th11) {
            th = th11;
            xPathContext2 = xPathContext;
        }
    }

    public final XObject executeRelativePathPattern(XPathContext xPathContext, DTM dtm, int i) throws TransformerException {
        XObject xObjectExecute = NodeTest.SCORE_NONE;
        DTMAxisTraverser axisTraverser = dtm.getAxisTraverser(this.m_axis);
        int iFirst = axisTraverser.first(i);
        while (-1 != iFirst) {
            try {
                xPathContext.pushCurrentNode(iFirst);
                xObjectExecute = execute(xPathContext);
                if (xObjectExecute != NodeTest.SCORE_NONE) {
                    return xObjectExecute;
                }
                xPathContext.popCurrentNode();
                iFirst = axisTraverser.next(i, iFirst);
            } finally {
                xPathContext.popCurrentNode();
            }
        }
        return xObjectExecute;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        if (this.m_predicates != null) {
            int i2 = 0;
            while (true) {
                Expression[] expressionArr = this.m_predicates;
                if (i2 >= expressionArr.length) {
                    break;
                }
                expressionArr[i2].fixupVariables(list, i);
                i2++;
            }
        }
        StepPattern stepPattern = this.m_relativePathPattern;
        if (stepPattern != null) {
            stepPattern.fixupVariables(list, i);
        }
    }

    public int getAxis() {
        return this.m_axis;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public Expression getExpression() {
        return this.m_relativePathPattern;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.SubContextList
    public int getLastPos(XPathContext xPathContext) {
        return getProximityPosition(xPathContext, xPathContext.getPredicatePos(), true);
    }

    public double getMatchScore(XPathContext xPathContext, int i) throws TransformerException {
        xPathContext.pushCurrentNode(i);
        xPathContext.pushCurrentExpressionNode(i);
        try {
            return execute(xPathContext).num();
        } finally {
            xPathContext.popCurrentNode();
            xPathContext.popCurrentExpressionNode();
        }
    }

    public Expression getPredicate(int i) {
        return this.m_predicates[i];
    }

    public final int getPredicateCount() {
        Expression[] expressionArr = this.m_predicates;
        if (expressionArr == null) {
            return 0;
        }
        return expressionArr.length;
    }

    public Expression[] getPredicates() {
        return this.m_predicates;
    }

    public StepPattern getRelativePathPattern() {
        return this.m_relativePathPattern;
    }

    public String getTargetString() {
        return this.m_targetString;
    }

    public void setAxis(int i) {
        this.m_axis = i;
    }

    @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
    public void setExpression(Expression expression) {
        expression.exprSetParent(this);
        this.m_relativePathPattern = (StepPattern) expression;
    }

    public void setPredicates(Expression[] expressionArr) {
        this.m_predicates = expressionArr;
        if (expressionArr != null) {
            for (Expression expression : expressionArr) {
                expression.exprSetParent(this);
            }
        }
        calcScore();
    }

    public void setRelativePathPattern(StepPattern stepPattern) {
        this.m_relativePathPattern = stepPattern;
        stepPattern.exprSetParent(this);
        calcScore();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (StepPattern stepPattern = this; stepPattern != null; stepPattern = stepPattern.m_relativePathPattern) {
            if (stepPattern != this) {
                stringBuffer.append(PsuedoNames.PSEUDONAME_ROOT);
            }
            stringBuffer.append(Axis.getNames(stepPattern.m_axis));
            stringBuffer.append("::");
            int i = stepPattern.m_whatToShow;
            if (20480 == i) {
                stringBuffer.append("doc()");
            } else if (65536 == i) {
                stringBuffer.append("function()");
            } else if (-1 == i) {
                stringBuffer.append("node()");
            } else if (4 == i) {
                stringBuffer.append("text()");
            } else if (64 == i) {
                stringBuffer.append("processing-instruction(");
                String str = stepPattern.m_name;
                if (str != null) {
                    stringBuffer.append(str);
                }
                stringBuffer.append(")");
            } else if (128 == i) {
                stringBuffer.append("comment()");
            } else if (stepPattern.m_name != null) {
                if (2 == i) {
                    stringBuffer.append("@");
                }
                if (stepPattern.m_namespace != null) {
                    stringBuffer.append("{");
                    stringBuffer.append(stepPattern.m_namespace);
                    stringBuffer.append("}");
                }
                stringBuffer.append(stepPattern.m_name);
            } else if (2 == i) {
                stringBuffer.append("@");
            } else if (1280 == i) {
                stringBuffer.append("doc-root()");
            } else {
                stringBuffer.append('?');
                stringBuffer.append(Integer.toHexString(stepPattern.m_whatToShow));
            }
            if (stepPattern.m_predicates != null) {
                for (int i2 = 0; i2 < stepPattern.m_predicates.length; i2++) {
                    stringBuffer.append("[");
                    stringBuffer.append(stepPattern.m_predicates[i2]);
                    stringBuffer.append("]");
                }
            }
        }
        return stringBuffer.toString();
    }

    public StepPattern(int i, int i2, int i3) {
        super(i);
        this.m_axis = i2;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return execute(xPathContext, xPathContext.getCurrentNode());
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext, int i) throws TransformerException {
        DTM dtm = xPathContext.getDTM(i);
        if (dtm != null) {
            return execute(xPathContext, i, dtm, dtm.getExpandedTypeID(i));
        }
        return NodeTest.SCORE_NONE;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.SubContextList
    public int getProximityPosition(XPathContext xPathContext) {
        return getProximityPosition(xPathContext, xPathContext.getPredicatePos(), false);
    }
}
