package com.sun.org.apache.xpath.internal.axes;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.patterns.NodeTest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PredicatedNodeTest extends NodeTest implements SubContextList {
    static final boolean DEBUG_PREDICATECOUNTING = false;
    static final long serialVersionUID = -6193530757296377351L;
    protected LocPathIterator m_lpi;
    private Expression[] m_predicates;
    protected transient int[] m_proximityPositions;
    protected int m_predCount = -1;
    protected transient boolean m_foundLast = false;
    transient int m_predicateIndex = -1;

    public class PredOwner implements ExpressionOwner {
        int m_index;

        public PredOwner(int i) {
            this.m_index = i;
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public Expression getExpression() {
            return PredicatedNodeTest.this.m_predicates[this.m_index];
        }

        @Override // com.sun.org.apache.xpath.internal.ExpressionOwner
        public void setExpression(Expression expression) {
            expression.exprSetParent(PredicatedNodeTest.this);
            PredicatedNodeTest.this.m_predicates[this.m_index] = expression;
        }
    }

    public PredicatedNodeTest(LocPathIterator locPathIterator) {
        this.m_lpi = locPathIterator;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.m_predicateIndex = -1;
        this.m_predCount = -1;
        resetProximityPositions();
    }

    public short acceptNode(int i) {
        XPathContext xPathContext = this.m_lpi.getXPathContext();
        try {
            try {
                xPathContext.pushCurrentNode(i);
                if (execute(xPathContext, i) == NodeTest.SCORE_NONE) {
                    xPathContext.popCurrentNode();
                    return (short) 3;
                }
                if (getPredicateCount() > 0) {
                    countProximityPosition(0);
                    if (!executePredicates(i, xPathContext)) {
                        xPathContext.popCurrentNode();
                        return (short) 3;
                    }
                }
                xPathContext.popCurrentNode();
                return (short) 1;
            } catch (TransformerException e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Throwable th) {
            xPathContext.popCurrentNode();
            throw th;
        }
    }

    public void callPredicateVisitors(XPathVisitor xPathVisitor) {
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

    public Object clone() throws CloneNotSupportedException {
        PredicatedNodeTest predicatedNodeTest = (PredicatedNodeTest) super.clone();
        int[] iArr = this.m_proximityPositions;
        if (iArr != null && iArr == predicatedNodeTest.m_proximityPositions) {
            int[] iArr2 = new int[iArr.length];
            predicatedNodeTest.m_proximityPositions = iArr2;
            int[] iArr3 = this.m_proximityPositions;
            System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
        }
        if (predicatedNodeTest.m_lpi == this) {
            predicatedNodeTest.m_lpi = (LocPathIterator) predicatedNodeTest;
        }
        return predicatedNodeTest;
    }

    public void countProximityPosition(int i) {
        int[] iArr = this.m_proximityPositions;
        if (iArr == null || i >= iArr.length) {
            return;
        }
        iArr[i] = iArr[i] + 1;
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        if (!super.deepEquals(expression)) {
            return false;
        }
        PredicatedNodeTest predicatedNodeTest = (PredicatedNodeTest) expression;
        Expression[] expressionArr = this.m_predicates;
        if (expressionArr == null) {
            return predicatedNodeTest.m_predicates == null;
        }
        int length = expressionArr.length;
        Expression[] expressionArr2 = predicatedNodeTest.m_predicates;
        if (expressionArr2 == null || expressionArr2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!this.m_predicates[i].deepEquals(predicatedNodeTest.m_predicates[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean executePredicates(int i, XPathContext xPathContext) throws TransformerException {
        int predicateCount = getPredicateCount();
        if (predicateCount == 0) {
            return true;
        }
        xPathContext.getNamespaceContext();
        try {
            this.m_predicateIndex = 0;
            xPathContext.pushSubContextList(this);
            xPathContext.pushNamespaceContext(this.m_lpi.getPrefixResolver());
            xPathContext.pushCurrentNode(i);
            for (int i2 = 0; i2 < predicateCount; i2++) {
                XObject xObjectExecute = this.m_predicates[i2].execute(xPathContext);
                if (2 != xObjectExecute.getType()) {
                    if (!xObjectExecute.bool()) {
                        return false;
                    }
                    int i3 = this.m_predicateIndex + 1;
                    this.m_predicateIndex = i3;
                    countProximityPosition(i3);
                } else {
                    if (getProximityPosition(this.m_predicateIndex) != ((int) xObjectExecute.num())) {
                        return false;
                    }
                    if (this.m_predicates[i2].isStableNumber() && i2 == predicateCount - 1) {
                        this.m_foundLast = true;
                    }
                    int i4 = this.m_predicateIndex + 1;
                    this.m_predicateIndex = i4;
                    countProximityPosition(i4);
                }
            }
            return true;
        } finally {
            xPathContext.popCurrentNode();
            xPathContext.popNamespaceContext();
            xPathContext.popSubContextList();
            this.m_predicateIndex = -1;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        int predicateCount = getPredicateCount();
        for (int i2 = 0; i2 < predicateCount; i2++) {
            this.m_predicates[i2].fixupVariables(list, i);
        }
    }

    public abstract int getLastPos(XPathContext xPathContext);

    public LocPathIterator getLocPathIterator() {
        return this.m_lpi;
    }

    public Expression getPredicate(int i) {
        return this.m_predicates[i];
    }

    public int getPredicateCount() {
        int i = this.m_predCount;
        if (-1 != i) {
            return i;
        }
        Expression[] expressionArr = this.m_predicates;
        if (expressionArr == null) {
            return 0;
        }
        return expressionArr.length;
    }

    public int getPredicateIndex() {
        return this.m_predicateIndex;
    }

    public int getProximityPosition(int i) {
        if (i >= 0) {
            return this.m_proximityPositions[i];
        }
        return 0;
    }

    public void initPredicateInfo(Compiler compiler, int i) throws TransformerException {
        int firstPredicateOpPos = compiler.getFirstPredicateOpPos(i);
        if (firstPredicateOpPos <= 0) {
            return;
        }
        Expression[] compiledPredicates = compiler.getCompiledPredicates(firstPredicateOpPos);
        this.m_predicates = compiledPredicates;
        if (compiledPredicates == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            Expression[] expressionArr = this.m_predicates;
            if (i2 >= expressionArr.length) {
                return;
            }
            expressionArr[i2].exprSetParent(this);
            i2++;
        }
    }

    public void initProximityPosition(int i) throws TransformerException {
        this.m_proximityPositions[i] = 0;
    }

    public boolean isReverseAxes() {
        return false;
    }

    public String nodeToString(int i) {
        if (-1 == i) {
            return PsiKeyword.NULL;
        }
        return this.m_lpi.getXPathContext().getDTM(i).getNodeName(i) + "{" + (i + 1) + "}";
    }

    public void resetProximityPositions() {
        int predicateCount = getPredicateCount();
        if (predicateCount > 0) {
            if (this.m_proximityPositions == null) {
                this.m_proximityPositions = new int[predicateCount];
            }
            for (int i = 0; i < predicateCount; i++) {
                try {
                    initProximityPosition(i);
                } catch (Exception e) {
                    throw new WrappedRuntimeException(e);
                }
            }
        }
    }

    public void setLocPathIterator(LocPathIterator locPathIterator) {
        this.m_lpi = locPathIterator;
        if (this != locPathIterator) {
            locPathIterator.exprSetParent(this);
        }
    }

    public void setPredicateCount(int i) {
        if (i <= 0) {
            this.m_predicates = null;
            return;
        }
        Expression[] expressionArr = new Expression[i];
        for (int i2 = 0; i2 < i; i2++) {
            expressionArr[i2] = this.m_predicates[i2];
        }
        this.m_predicates = expressionArr;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.SubContextList
    public int getProximityPosition(XPathContext xPathContext) {
        return getProximityPosition();
    }

    public int getProximityPosition() {
        return getProximityPosition(this.m_predicateIndex);
    }

    public PredicatedNodeTest() {
    }
}
