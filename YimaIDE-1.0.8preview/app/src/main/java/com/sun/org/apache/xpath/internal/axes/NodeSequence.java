package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.utils.NodeVector;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NodeSequence extends XObject implements DTMIterator, Cloneable, PathComponent {
    static final long serialVersionUID = 3866261934726581044L;
    private IteratorCache m_cache;
    protected DTMManager m_dtmMgr;
    protected DTMIterator m_iter;
    protected int m_last;
    protected int m_next;

    public static final class IteratorCache {
        private NodeVector m_vec2 = null;
        private boolean m_isComplete2 = false;
        private int m_useCount2 = 1;

        /* JADX INFO: Access modifiers changed from: private */
        public NodeVector getVector() {
            return this.m_vec2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseUseCount() {
            if (this.m_vec2 != null) {
                this.m_useCount2++;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isComplete() {
            return this.m_isComplete2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCacheComplete(boolean z) {
            this.m_isComplete2 = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVector(NodeVector nodeVector) {
            this.m_vec2 = nodeVector;
            this.m_useCount2 = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int useCount() {
            return this.m_useCount2;
        }
    }

    public NodeSequence(Object obj) {
        super(obj);
        this.m_last = -1;
        this.m_next = 0;
        boolean z = obj instanceof NodeVector;
        if (z) {
            SetVector((NodeVector) obj);
        }
        if (obj != null) {
            assertion(z, "Must have a NodeVector as the object for NodeSequence!");
            if (obj instanceof DTMIterator) {
                DTMIterator dTMIterator = (DTMIterator) obj;
                setIter(dTMIterator);
                this.m_last = dTMIterator.getLength();
            }
        }
    }

    private boolean cacheComplete() {
        IteratorCache iteratorCache = this.m_cache;
        if (iteratorCache != null) {
            return iteratorCache.isComplete();
        }
        return false;
    }

    private IteratorCache getCache() {
        return this.m_cache;
    }

    private void markCacheComplete() {
        if (getVector() != null) {
            this.m_cache.setCacheComplete(true);
        }
    }

    public void SetVector(NodeVector nodeVector) {
        setObject(nodeVector);
    }

    public int addNodeInDocOrder(int i) {
        int iElementAt;
        assertion(hasCache(), "addNodeInDocOrder must be done on a mutable sequence!");
        NodeVector vector = getVector();
        int size = vector.size();
        do {
            size--;
            if (size < 0) {
                break;
            }
            iElementAt = vector.elementAt(size);
            if (iElementAt == i) {
                size = -2;
                break;
            }
        } while (this.m_dtmMgr.getDTM(i).isNodeAfter(i, iElementAt));
        if (size == -2) {
            return -1;
        }
        int i2 = size + 1;
        vector.insertElementAt(i, i2);
        return i2;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void allowDetachToRelease(boolean z) {
        if (!z && !hasCache()) {
            setShouldCacheNodes(true);
        }
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            dTMIterator.allowDetachToRelease(z);
        }
        super.allowDetachToRelease(z);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public Object clone() throws CloneNotSupportedException {
        NodeSequence nodeSequence = (NodeSequence) super.clone();
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            nodeSequence.m_iter = (DTMIterator) dTMIterator.clone();
        }
        IteratorCache iteratorCache = this.m_cache;
        if (iteratorCache != null) {
            iteratorCache.increaseUseCount();
        }
        return nodeSequence;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        NodeSequence nodeSequence = (NodeSequence) super.clone();
        nodeSequence.m_next = 0;
        IteratorCache iteratorCache = this.m_cache;
        if (iteratorCache != null) {
            iteratorCache.increaseUseCount();
        }
        return nodeSequence;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            dTMIterator.detach();
        }
        super.detach();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        super.fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.axes.PathComponent
    public int getAnalysisBits() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator == null || !(dTMIterator instanceof PathComponent)) {
            return 0;
        }
        return ((PathComponent) dTMIterator).getAnalysisBits();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getAxis() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            return dTMIterator.getAxis();
        }
        assertion(false, "Can not getAxis from a non-iterated node sequence!");
        return 0;
    }

    public final DTMIterator getContainedIter() {
        return this.m_iter;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getCurrentNode() {
        if (!hasCache()) {
            DTMIterator dTMIterator = this.m_iter;
            if (dTMIterator != null) {
                return dTMIterator.getCurrentNode();
            }
            return -1;
        }
        int i = this.m_next - 1;
        NodeVector vector = getVector();
        if (i < 0 || i >= vector.size()) {
            return -1;
        }
        return vector.elementAt(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getCurrentPos() {
        return this.m_next;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTM getDTM(int i) {
        if (getDTMManager() != null) {
            return getDTMManager().getDTM(i);
        }
        assertion(false, "Can not get a DTM Unless a DTMManager has been set!");
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public DTMManager getDTMManager() {
        return this.m_dtmMgr;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean getExpandEntityReferences() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            return dTMIterator.getExpandEntityReferences();
        }
        return true;
    }

    public IteratorCache getIteratorCache() {
        return this.m_cache;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getLength() {
        IteratorCache cache = getCache();
        if (cache == null) {
            int i = this.m_last;
            if (-1 != i) {
                return i;
            }
            int length = this.m_iter.getLength();
            this.m_last = length;
            return length;
        }
        if (cache.isComplete()) {
            return cache.getVector().size();
        }
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator instanceof NodeSetDTM) {
            return dTMIterator.getLength();
        }
        if (-1 == this.m_last) {
            int i2 = this.m_next;
            runTo(-1);
            this.m_next = i2;
        }
        return this.m_last;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getRoot() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            return dTMIterator.getRoot();
        }
        return -1;
    }

    public NodeVector getVector() {
        IteratorCache iteratorCache = this.m_cache;
        if (iteratorCache != null) {
            return iteratorCache.getVector();
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int getWhatToShow() {
        if (hasCache()) {
            return -17;
        }
        return this.m_iter.getWhatToShow();
    }

    public boolean hasCache() {
        return getVector() != null;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isDocOrdered() {
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator != null) {
            return dTMIterator.isDocOrdered();
        }
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isFresh() {
        return this.m_next == 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public boolean isMutable() {
        return hasCache();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int item(int i) {
        setCurrentPos(i);
        int iNextNode = nextNode();
        this.m_next = i;
        return iNextNode;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int nextNode() {
        NodeVector vector = getVector();
        if (vector != null) {
            if (this.m_next < vector.size()) {
                int iElementAt = vector.elementAt(this.m_next);
                this.m_next++;
                return iElementAt;
            }
            if (cacheComplete() || -1 != this.m_last || this.m_iter == null) {
                this.m_next++;
                return -1;
            }
        }
        DTMIterator dTMIterator = this.m_iter;
        if (dTMIterator == null) {
            return -1;
        }
        int iNextNode = dTMIterator.nextNode();
        if (-1 == iNextNode) {
            markCacheComplete();
            int i = this.m_next;
            this.m_last = i;
            this.m_next = i + 1;
            return iNextNode;
        }
        if (!hasCache()) {
            this.m_next++;
            return iNextNode;
        }
        if (this.m_iter.isDocOrdered()) {
            getVector().addElement(iNextNode);
            this.m_next++;
            return iNextNode;
        }
        if (addNodeInDocOrder(iNextNode) >= 0) {
            this.m_next++;
        }
        return iNextNode;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public int previousNode() {
        if (!hasCache()) {
            this.m_iter.previousNode();
            int currentPos = this.m_iter.getCurrentPos();
            this.m_next = currentPos;
            return currentPos;
        }
        int i = this.m_next;
        if (i <= 0) {
            return -1;
        }
        int i2 = i - 1;
        this.m_next = i2;
        return item(i2);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void reset() {
        this.m_next = 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void runTo(int i) {
        int i2 = this.m_next;
        if (-1 == i) {
            while (-1 != nextNode()) {
            }
            this.m_next = i2;
            return;
        }
        if (i2 == i) {
            return;
        }
        if (hasCache() && i < getVector().size()) {
            this.m_next = i;
            return;
        }
        if (getVector() != null || i >= this.m_next) {
            while (this.m_next < i && -1 != nextNode()) {
            }
        } else {
            while (this.m_next >= i && -1 != previousNode()) {
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setCurrentPos(int i) {
        runTo(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setItem(int i, int i2) {
        NodeVector vector = getVector();
        if (vector == null) {
            this.m_iter.setItem(i, i2);
            return;
        }
        if (vector.elementAt(i2) != i && this.m_cache.useCount() > 1) {
            IteratorCache iteratorCache = new IteratorCache();
            try {
                vector = (NodeVector) vector.clone();
                iteratorCache.setVector(vector);
                iteratorCache.setCacheComplete(true);
                this.m_cache = iteratorCache;
                super.setObject(vector);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                f63.a(e.getMessage());
                return;
            }
        }
        vector.setElementAt(i, i2);
        this.m_last = vector.size();
    }

    public final void setIter(DTMIterator dTMIterator) {
        this.m_iter = dTMIterator;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void setObject(Object obj) {
        if (!(obj instanceof NodeVector)) {
            if (!(obj instanceof IteratorCache)) {
                super.setObject(obj);
                return;
            }
            IteratorCache iteratorCache = (IteratorCache) obj;
            this.m_cache = iteratorCache;
            iteratorCache.increaseUseCount();
            super.setObject(iteratorCache.getVector());
            return;
        }
        super.setObject(obj);
        NodeVector nodeVector = (NodeVector) obj;
        IteratorCache iteratorCache2 = this.m_cache;
        if (iteratorCache2 != null) {
            iteratorCache2.setVector(nodeVector);
            return;
        }
        IteratorCache iteratorCache3 = new IteratorCache();
        this.m_cache = iteratorCache3;
        iteratorCache3.setVector(nodeVector);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setRoot(int i, Object obj) {
        if (i == -1) {
            f63.a("Unable to evaluate expression using this context");
            return;
        }
        if (this.m_iter == null) {
            assertion(false, "Can not setRoot on a non-iterated NodeSequence!");
            return;
        }
        this.m_dtmMgr = ((XPathContext) obj).getDTMManager();
        this.m_iter.setRoot(i, obj);
        if (this.m_iter.isDocOrdered()) {
            return;
        }
        if (!hasCache()) {
            setShouldCacheNodes(true);
        }
        runTo(-1);
        this.m_next = 0;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void setShouldCacheNodes(boolean z) {
        if (!z) {
            SetVector(null);
        } else {
            if (hasCache()) {
                return;
            }
            SetVector(new NodeVector());
        }
    }

    private NodeSequence(DTMIterator dTMIterator, int i, XPathContext xPathContext, boolean z) {
        this.m_last = -1;
        this.m_next = 0;
        setIter(dTMIterator);
        setRoot(i, xPathContext);
        setShouldCacheNodes(z);
    }

    private NodeSequence(DTMManager dTMManager) {
        super(new NodeVector());
        this.m_next = 0;
        this.m_last = 0;
        this.m_dtmMgr = dTMManager;
    }

    public NodeSequence() {
        this.m_last = -1;
        this.m_next = 0;
    }
}
