package com.sun.org.apache.xpath.internal.patterns;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.WalkerFactory;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ContextMatchStepPattern extends StepPattern {
    static final long serialVersionUID = -1888092779313211942L;

    public ContextMatchStepPattern(int i, int i2) {
        super(-1, i, i2);
    }

    @Override // com.sun.org.apache.xpath.internal.patterns.StepPattern, com.sun.org.apache.xpath.internal.patterns.NodeTest, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return xPathContext.getIteratorRoot() == xPathContext.getCurrentNode() ? getStaticScore() : NodeTest.SCORE_NONE;
    }

    public XObject executeRelativePathPattern(XPathContext xPathContext, StepPattern stepPattern) throws TransformerException {
        XObject xObjectExecute = NodeTest.SCORE_NONE;
        int currentNode = xPathContext.getCurrentNode();
        DTM dtm = xPathContext.getDTM(currentNode);
        if (dtm != null) {
            xPathContext.getCurrentNode();
            int i = this.m_axis;
            boolean zIsDownwardAxisOfMany = WalkerFactory.isDownwardAxisOfMany(i);
            boolean z = dtm.getNodeType(xPathContext.getIteratorRoot()) == 2;
            if (11 == i && z) {
                i = 15;
            }
            DTMAxisTraverser axisTraverser = dtm.getAxisTraverser(i);
            for (int iFirst = axisTraverser.first(currentNode); -1 != iFirst; iFirst = axisTraverser.next(currentNode, iFirst)) {
                try {
                    xPathContext.pushCurrentNode(iFirst);
                    xObjectExecute = execute(xPathContext);
                    XNumber xNumber = NodeTest.SCORE_NONE;
                    if (xObjectExecute != xNumber) {
                        if (executePredicates(xPathContext, dtm, currentNode)) {
                            xPathContext.popCurrentNode();
                            return xObjectExecute;
                        }
                        xObjectExecute = xNumber;
                    }
                    if (zIsDownwardAxisOfMany && z && 1 == dtm.getNodeType(iFirst)) {
                        int i2 = 2;
                        int i3 = 0;
                        while (i3 < 2) {
                            DTMAxisTraverser axisTraverser2 = dtm.getAxisTraverser(i2);
                            for (int iFirst2 = axisTraverser2.first(iFirst); -1 != iFirst2; iFirst2 = axisTraverser2.next(iFirst, iFirst2)) {
                                try {
                                    xPathContext.pushCurrentNode(iFirst2);
                                    xObjectExecute = execute(xPathContext);
                                    XNumber xNumber2 = NodeTest.SCORE_NONE;
                                    if (xObjectExecute != xNumber2 && xObjectExecute != xNumber2) {
                                        xPathContext.popCurrentNode();
                                        return xObjectExecute;
                                    }
                                    xPathContext.popCurrentNode();
                                } finally {
                                    xPathContext.popCurrentNode();
                                }
                            }
                            i3++;
                            i2 = 9;
                        }
                    }
                    xPathContext.popCurrentNode();
                } catch (Throwable th) {
                    xPathContext.popCurrentNode();
                    throw th;
                }
            }
        }
        return xObjectExecute;
    }
}
