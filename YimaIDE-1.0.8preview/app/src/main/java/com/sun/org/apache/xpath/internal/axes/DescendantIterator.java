package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.VariableStack;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.compiler.OpMap;
import com.sun.org.apache.xpath.internal.patterns.NodeTest;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DescendantIterator extends LocPathIterator {
    static final long serialVersionUID = -1190338607743976938L;
    protected int m_axis;
    protected int m_extendedTypeID;
    protected transient DTMAxisTraverser m_traverser;

    /* JADX WARN: Code duplicated, block: B:23:0x003f  */
    /* JADX WARN: Code duplicated, block: B:25:0x0042 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x0044  */
    /* JADX WARN: Code duplicated, block: B:27:0x0049  */
    /* JADX WARN: Code duplicated, block: B:28:0x004e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    /* JADX WARN: Code duplicated, block: B:36:0x006e  */
    public DescendantIterator(Compiler compiler, int i, int i2) throws TransformerException {
        int nextStepPos;
        boolean z;
        int whatToShow;
        super(compiler, i, i2, false);
        int firstChildPos = OpMap.getFirstChildPos(i);
        int op = compiler.getOp(firstChildPos);
        boolean z2 = true;
        boolean z3 = 42 == op;
        if (48 != op) {
            if (50 == op) {
                if (compiler.getOp(compiler.getNextStepPos(firstChildPos)) == 42) {
                    z3 = true;
                }
            }
            while (true) {
                nextStepPos = compiler.getNextStepPos(firstChildPos);
                if (nextStepPos <= 0 || -1 == compiler.getOp(nextStepPos)) {
                    break;
                } else {
                    firstChildPos = nextStepPos;
                }
            }
            z = (i2 & 65536) == 0 ? z3 : false;
            if (z2) {
                if (z) {
                    this.m_axis = 18;
                } else {
                    this.m_axis = 17;
                }
            } else if (z) {
                this.m_axis = 5;
            } else {
                this.m_axis = 4;
            }
            whatToShow = compiler.getWhatToShow(firstChildPos);
            if ((whatToShow & 67) != 0 || whatToShow == -1) {
                initNodeTest(whatToShow);
            } else {
                initNodeTest(whatToShow, compiler.getStepNS(firstChildPos), compiler.getStepLocalName(firstChildPos));
            }
            initPredicateInfo(compiler, firstChildPos);
        }
        z3 = true;
        z2 = false;
        while (true) {
            nextStepPos = compiler.getNextStepPos(firstChildPos);
            if (nextStepPos <= 0) {
                break;
            }
            break;
            break;
            firstChildPos = nextStepPos;
        }
        if ((i2 & 65536) == 0) {
        }
        if (z2) {
            if (z) {
                this.m_axis = 18;
            } else {
                this.m_axis = 17;
            }
        } else if (z) {
            this.m_axis = 5;
        } else {
            this.m_axis = 4;
        }
        whatToShow = compiler.getWhatToShow(firstChildPos);
        if ((whatToShow & 67) != 0) {
            initNodeTest(whatToShow);
        } else {
            initNodeTest(whatToShow);
        }
        initPredicateInfo(compiler, firstChildPos);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xpath.internal.Expression
    public int asNode(XPathContext xPathContext) throws TransformerException {
        if (getPredicateCount() > 0) {
            return super.asNode(xPathContext);
        }
        int currentNode = xPathContext.getCurrentNode();
        DTM dtm = xPathContext.getDTM(currentNode);
        DTMAxisTraverser axisTraverser = dtm.getAxisTraverser(this.m_axis);
        String localName = getLocalName();
        String namespace = getNamespace();
        int i = this.m_whatToShow;
        return (-1 == i || localName == "*" || namespace == "*") ? axisTraverser.first(currentNode) : axisTraverser.first(currentNode, dtm.getExpandedTypeID(namespace, localName, NodeTest.getNodeTypeTest(i)));
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        DescendantIterator descendantIterator = (DescendantIterator) super.cloneWithReset();
        descendantIterator.m_traverser = this.m_traverser;
        descendantIterator.resetProximityPositions();
        return descendantIterator;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return super.deepEquals(expression) && this.m_axis == ((DescendantIterator) expression).m_axis;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        if (this.m_allowDetach) {
            this.m_traverser = null;
            this.m_extendedTypeID = 0;
            super.detach();
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        return this.m_axis;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        VariableStack varStack;
        int stackFrame;
        int iFirst;
        if (this.m_foundLast) {
            return -1;
        }
        if (-1 == this.m_lastFetched) {
            resetProximityPositions();
        }
        if (-1 != this.m_stackFrame) {
            varStack = this.m_execContext.getVarStack();
            stackFrame = varStack.getStackFrame();
            varStack.setStackFrame(this.m_stackFrame);
        } else {
            varStack = null;
            stackFrame = 0;
        }
        do {
            try {
                int i = this.m_extendedTypeID;
                int i2 = this.m_lastFetched;
                if (i == 0) {
                    DTMAxisTraverser dTMAxisTraverser = this.m_traverser;
                    iFirst = -1 == i2 ? dTMAxisTraverser.first(this.m_context) : dTMAxisTraverser.next(this.m_context, i2);
                    this.m_lastFetched = iFirst;
                } else {
                    DTMAxisTraverser dTMAxisTraverser2 = this.m_traverser;
                    iFirst = -1 == i2 ? dTMAxisTraverser2.first(this.m_context, i) : dTMAxisTraverser2.next(this.m_context, i2, i);
                    this.m_lastFetched = iFirst;
                }
                if (-1 == iFirst || 1 == acceptNode(iFirst)) {
                    break;
                    break;
                }
            } catch (Throwable th) {
                if (-1 != this.m_stackFrame) {
                    varStack.setStackFrame(stackFrame);
                }
                throw th;
            }
        } while (iFirst != -1);
        if (-1 != iFirst) {
            this.m_pos++;
            if (-1 != this.m_stackFrame) {
                varStack.setStackFrame(stackFrame);
            }
            return iFirst;
        }
        this.m_foundLast = true;
        if (-1 != this.m_stackFrame) {
            varStack.setStackFrame(stackFrame);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
        super.setRoot(i, obj);
        this.m_traverser = this.m_cdtm.getAxisTraverser(this.m_axis);
        String localName = getLocalName();
        String namespace = getNamespace();
        int i2 = this.m_whatToShow;
        if (-1 == i2 || "*".equals(localName) || "*".equals(namespace)) {
            this.m_extendedTypeID = 0;
        } else {
            this.m_extendedTypeID = this.m_cdtm.getExpandedTypeID(namespace, localName, NodeTest.getNodeTypeTest(i2));
        }
    }

    public DescendantIterator() {
        super(null);
        this.m_axis = 18;
        initNodeTest(-1);
    }
}
