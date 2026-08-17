package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xpath.internal.VariableStack;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.compiler.OpMap;
import com.sun.org.apache.xpath.internal.patterns.NodeTest;
import com.sun.org.apache.xpath.internal.patterns.StepPattern;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MatchPatternIterator extends LocPathIterator {
    private static final boolean DEBUG = false;
    static final long serialVersionUID = -5201153767396296474L;
    protected StepPattern m_pattern;
    protected int m_superAxis;
    protected DTMAxisTraverser m_traverser;

    public MatchPatternIterator(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2, false);
        this.m_superAxis = -1;
        this.m_pattern = WalkerFactory.loadSteps(this, compiler, OpMap.getFirstChildPos(i), 0);
        boolean z = (671088640 & i2) != 0;
        boolean z2 = (98066432 & i2) != 0;
        boolean z3 = (458752 & i2) != 0;
        boolean z4 = (i2 & 2129920) != 0;
        if (z || z2) {
            if (z4) {
                this.m_superAxis = 16;
                return;
            } else {
                this.m_superAxis = 17;
                return;
            }
        }
        if (!z3) {
            this.m_superAxis = 16;
        } else if (z4) {
            this.m_superAxis = 14;
        } else {
            this.m_superAxis = 5;
        }
    }

    public short acceptNode(int i, XPathContext xPathContext) {
        try {
            try {
                xPathContext.pushCurrentNode(i);
                xPathContext.pushIteratorRoot(this.m_context);
                short s = this.m_pattern.execute(xPathContext) == NodeTest.SCORE_NONE ? (short) 3 : (short) 1;
                xPathContext.popCurrentNode();
                xPathContext.popIteratorRoot();
                return s;
            } catch (TransformerException e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (Throwable th) {
            xPathContext.popCurrentNode();
            xPathContext.popIteratorRoot();
            throw th;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        if (this.m_allowDetach) {
            this.m_traverser = null;
            super.detach();
        }
    }

    public int getNextNode() {
        int i = this.m_lastFetched;
        DTMAxisTraverser dTMAxisTraverser = this.m_traverser;
        int iFirst = -1 == i ? dTMAxisTraverser.first(this.m_context) : dTMAxisTraverser.next(this.m_context, i);
        this.m_lastFetched = iFirst;
        return iFirst;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        VariableStack varStack;
        int stackFrame;
        int nextNode;
        if (this.m_foundLast) {
            return -1;
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
                nextNode = getNextNode();
                if (-1 == nextNode || 1 == acceptNode(nextNode, this.m_execContext)) {
                    break;
                    break;
                }
            } catch (Throwable th) {
                if (-1 != this.m_stackFrame) {
                    varStack.setStackFrame(stackFrame);
                }
                throw th;
            }
        } while (nextNode != -1);
        if (-1 != nextNode) {
            incrementCurrentPos();
            if (-1 != this.m_stackFrame) {
                varStack.setStackFrame(stackFrame);
            }
            return nextNode;
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
        this.m_traverser = this.m_cdtm.getAxisTraverser(this.m_superAxis);
    }
}
