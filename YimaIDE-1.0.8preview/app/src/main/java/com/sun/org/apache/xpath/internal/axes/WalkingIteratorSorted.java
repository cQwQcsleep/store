package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WalkingIteratorSorted extends WalkingIterator {
    static final long serialVersionUID = -4512512007542368213L;
    protected boolean m_inNaturalOrderStatic;

    public WalkingIteratorSorted(PrefixResolver prefixResolver) {
        super(prefixResolver);
        this.m_inNaturalOrderStatic = false;
    }

    public boolean canBeWalkedInNaturalDocOrderStatic() {
        AxesWalker nextWalker = this.m_firstWalker;
        if (nextWalker == null) {
            return false;
        }
        while (nextWalker != null) {
            int axis = nextWalker.getAxis();
            if (!nextWalker.isDocOrdered()) {
                return false;
            }
            if (axis != 3 && axis != 13 && axis != 19 && axis != -1) {
                return nextWalker.getNextWalker() == null && ((nextWalker.isDocOrdered() && (axis == 4 || axis == 5 || axis == 17 || axis == 18)) || axis == 2);
            }
            nextWalker = nextWalker.getNextWalker();
        }
        return true;
    }

    @Override // com.sun.org.apache.xpath.internal.axes.WalkingIterator, com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
        if (WalkerFactory.isNaturalDocOrder(getAnalysisBits())) {
            this.m_inNaturalOrderStatic = true;
        } else {
            this.m_inNaturalOrderStatic = false;
        }
    }

    @Override // com.sun.org.apache.xpath.internal.axes.LocPathIterator, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isDocOrdered() {
        return this.m_inNaturalOrderStatic;
    }

    public WalkingIteratorSorted(Compiler compiler, int i, int i2, boolean z) throws TransformerException {
        super(compiler, i, i2, z);
        this.m_inNaturalOrderStatic = false;
    }
}
