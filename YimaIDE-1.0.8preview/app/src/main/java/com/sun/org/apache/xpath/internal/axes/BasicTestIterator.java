package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xpath.internal.VariableStack;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.compiler.OpMap;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BasicTestIterator extends LocPathIterator {
    static final long serialVersionUID = 3505378079378096623L;

    public BasicTestIterator(Compiler compiler, int i, int i2) throws TransformerException {
        super(compiler, i, i2, false);
        int firstChildPos = OpMap.getFirstChildPos(i);
        int whatToShow = compiler.getWhatToShow(firstChildPos);
        if ((whatToShow & 4163) == 0 || whatToShow == -1) {
            initNodeTest(whatToShow);
        } else {
            initNodeTest(whatToShow, compiler.getStepNS(firstChildPos), compiler.getStepLocalName(firstChildPos));
        }
        initPredicateInfo(compiler, firstChildPos);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        ChildTestIterator childTestIterator = (ChildTestIterator) super.cloneWithReset();
        childTestIterator.resetProximityPositions();
        return childTestIterator;
    }

    public abstract int getNextNode();

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        VariableStack varStack;
        int stackFrame;
        int nextNode;
        if (this.m_foundLast) {
            this.m_lastFetched = -1;
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
                nextNode = getNextNode();
                if (-1 == nextNode || 1 == acceptNode(nextNode)) {
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
            this.m_pos++;
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

    public BasicTestIterator(PrefixResolver prefixResolver) {
        super(prefixResolver);
    }

    public BasicTestIterator() {
    }

    public BasicTestIterator(Compiler compiler, int i, int i2, boolean z) throws TransformerException {
        super(compiler, i, i2, z);
    }
}
