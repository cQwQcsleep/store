package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMAxisTraverser;
import com.sun.org.apache.xml.internal.dtm.DTMException;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.NodeVector;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import javax.xml.transform.Source;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DTMDefaultBaseIterators extends DTMDefaultBaseTraversers {

    public class AncestorIterator extends InternalAxisIteratorBase {
        NodeVector m_ancestors;
        int m_ancestorsPos;
        int m_markedPos;
        int m_realStartNode;

        public AncestorIterator() {
            super();
            this.m_ancestors = new NodeVector();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            this._isRestartable = false;
            try {
                AncestorIterator ancestorIterator = (AncestorIterator) super.clone();
                ancestorIterator._startNode = this._startNode;
                return ancestorIterator;
            } catch (CloneNotSupportedException unused) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getStartNode() {
            return this.m_realStartNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            int i = this.m_markedPos;
            this.m_ancestorsPos = i;
            this._currentNode = i >= 0 ? this.m_ancestors.elementAt(i) : -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public final boolean isReverse() {
            return true;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i2 = this.m_ancestorsPos - 1;
            this.m_ancestorsPos = i2;
            this._currentNode = i2 >= 0 ? this.m_ancestors.elementAt(i2) : -1;
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            int size = this.m_ancestors.size() - 1;
            this.m_ancestorsPos = size;
            this._currentNode = size >= 0 ? this.m_ancestors.elementAt(size) : -1;
            return resetPosition();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this.m_markedPos = this.m_ancestorsPos;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            NodeVector nodeVector;
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            this.m_realStartNode = i;
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(i);
            if (!this._includeSelf && i != -1) {
                iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
                i = DTMDefaultBaseIterators.this.makeNodeHandle(iMakeNodeIdentity);
            }
            this._startNode = i;
            while (true) {
                nodeVector = this.m_ancestors;
                if (iMakeNodeIdentity == -1) {
                    break;
                }
                nodeVector.addElement(i);
                iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
                i = DTMDefaultBaseIterators.this.makeNodeHandle(iMakeNodeIdentity);
            }
            int size = nodeVector.size() - 1;
            this.m_ancestorsPos = size;
            this._currentNode = size >= 0 ? this.m_ancestors.elementAt(size) : -1;
            return resetPosition();
        }
    }

    public final class AttributeIterator extends InternalAxisIteratorBase {
        public AttributeIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == -1) {
                return -1;
            }
            this._currentNode = DTMDefaultBaseIterators.this.getNextAttributeIdentity(i);
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
            this._currentNode = dTMDefaultBaseIterators.getFirstAttributeIdentity(dTMDefaultBaseIterators.makeNodeIdentity(i));
            return resetPosition();
        }
    }

    public final class ChildrenIterator extends InternalAxisIteratorBase {
        public ChildrenIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == -1) {
                return -1;
            }
            this._currentNode = DTMDefaultBaseIterators.this._nextsib(i);
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int i_firstch = -1;
            if (i != -1) {
                DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
                i_firstch = dTMDefaultBaseIterators._firstch(dTMDefaultBaseIterators.makeNodeIdentity(i));
            }
            this._currentNode = i_firstch;
            return resetPosition();
        }
    }

    public class DescendantIterator extends InternalAxisIteratorBase {
        public DescendantIterator() {
            super();
        }

        public boolean isDescendant(int i) {
            int i_parent = DTMDefaultBaseIterators.this._parent(i);
            int i2 = this._startNode;
            return i_parent >= i2 || i2 == i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._startNode;
            if (i == -1) {
                return -1;
            }
            if (this._includeSelf) {
                int i2 = this._currentNode;
                if (i2 + 1 == i) {
                    DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
                    int i3 = i2 + 1;
                    this._currentNode = i3;
                    return returnNode(dTMDefaultBaseIterators.makeNodeHandle(i3));
                }
            }
            int i4 = this._currentNode;
            while (true) {
                i4++;
                short s_type = DTMDefaultBaseIterators.this._type(i4);
                if (-1 == s_type || !isDescendant(i4)) {
                    break;
                }
                if (2 != s_type && 3 != s_type && 13 != s_type) {
                    this._currentNode = i4;
                    return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i4));
                }
            }
            this._currentNode = -1;
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            boolean z = this._isRestartable;
            this._isRestartable = true;
            setStartNode(DTMDefaultBaseIterators.this.makeNodeHandle(this._startNode));
            this._isRestartable = z;
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(i);
            this._startNode = iMakeNodeIdentity;
            if (this._includeSelf) {
                iMakeNodeIdentity--;
            }
            this._currentNode = iMakeNodeIdentity;
            return resetPosition();
        }
    }

    public class FollowingIterator extends InternalAxisIteratorBase {
        DTMAxisTraverser m_traverser;

        public FollowingIterator() {
            super();
            this.m_traverser = DTMDefaultBaseIterators.this.getAxisTraverser(6);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            this._currentNode = this.m_traverser.next(this._startNode, i);
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = this.m_traverser.first(i);
            return resetPosition();
        }
    }

    public class FollowingSiblingIterator extends InternalAxisIteratorBase {
        public FollowingSiblingIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i_nextsib = i != -1 ? DTMDefaultBaseIterators.this._nextsib(i) : -1;
            this._currentNode = i_nextsib;
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i_nextsib));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = DTMDefaultBaseIterators.this.makeNodeIdentity(i);
            return resetPosition();
        }
    }

    public abstract class InternalAxisIteratorBase extends DTMAxisIteratorBase {
        protected int _currentNode;

        public InternalAxisIteratorBase() {
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this._currentNode = this._markedNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this._markedNode = this._currentNode;
        }
    }

    public final class NamespaceAttributeIterator extends InternalAxisIteratorBase {
        private final int _nsType;

        public NamespaceAttributeIterator(int i) {
            super();
            this._nsType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (-1 != i) {
                this._currentNode = DTMDefaultBaseIterators.this.getNextNamespaceNode(this._startNode, i, false);
            }
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = DTMDefaultBaseIterators.this.getFirstNamespaceNode(i, false);
            return resetPosition();
        }
    }

    public final class NamespaceChildrenIterator extends InternalAxisIteratorBase {
        private final int _nsType;

        public NamespaceChildrenIterator(int i) {
            super();
            this._nsType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i != -1) {
                DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
                int i_firstch = -2 == i ? dTMDefaultBaseIterators._firstch(dTMDefaultBaseIterators.makeNodeIdentity(this._startNode)) : dTMDefaultBaseIterators._nextsib(i);
                while (i_firstch != -1) {
                    DTMDefaultBaseIterators dTMDefaultBaseIterators2 = DTMDefaultBaseIterators.this;
                    if (dTMDefaultBaseIterators2.m_expandedNameTable.getNamespaceID(dTMDefaultBaseIterators2._exptype(i_firstch)) == this._nsType) {
                        this._currentNode = i_firstch;
                        return returnNode(i_firstch);
                    }
                    i_firstch = DTMDefaultBaseIterators.this._nextsib(i_firstch);
                }
            }
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = i != -1 ? -2 : -1;
            return resetPosition();
        }
    }

    public class NamespaceIterator extends InternalAxisIteratorBase {
        public NamespaceIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (-1 != i) {
                this._currentNode = DTMDefaultBaseIterators.this.getNextNamespaceNode(this._startNode, i, true);
            }
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = DTMDefaultBaseIterators.this.getFirstNamespaceNode(i, true);
            return resetPosition();
        }
    }

    public class NthDescendantIterator extends DescendantIterator {
        int _pos;

        public NthDescendantIterator(int i) {
            super();
            this._pos = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.DescendantIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int iMakeNodeIdentity;
            int i_firstch;
            do {
                int next = super.next();
                if (next == -1) {
                    return -1;
                }
                iMakeNodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(next);
                i_firstch = DTMDefaultBaseIterators.this._firstch(DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity));
                int i = 0;
                do {
                    if (1 == DTMDefaultBaseIterators.this._type(i_firstch)) {
                        i++;
                    }
                    if (i >= this._pos) {
                        break;
                    }
                    i_firstch = DTMDefaultBaseIterators.this._nextsib(i_firstch);
                } while (i_firstch != -1);
            } while (iMakeNodeIdentity != i_firstch);
            return iMakeNodeIdentity;
        }
    }

    public final class ParentIterator extends InternalAxisIteratorBase {
        private int _nodeType;

        public ParentIterator() {
            super();
            this._nodeType = -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i2 = this._nodeType;
            if (i2 < 14 ? !(i2 == -1 || i2 == DTMDefaultBaseIterators.this.getNodeType(i)) : i2 != DTMDefaultBaseIterators.this.getExpandedTypeID(i)) {
                i = -1;
            }
            this._currentNode = -1;
            return returnNode(i);
        }

        public DTMAxisIterator setNodeType(int i) {
            this._nodeType = i;
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = DTMDefaultBaseIterators.this.getParent(i);
            return resetPosition();
        }
    }

    public class PrecedingIterator extends InternalAxisIteratorBase {
        protected int _markedDescendant;
        protected int _markedNode;
        protected int _markedsp;
        private final int _maxAncestors;
        protected int _oldsp;
        protected int _sp;
        protected int[] _stack;

        public PrecedingIterator() {
            super();
            this._maxAncestors = 8;
            this._stack = new int[8];
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator cloneIterator() {
            this._isRestartable = false;
            try {
                PrecedingIterator precedingIterator = (PrecedingIterator) super.clone();
                int[] iArr = this._stack;
                int[] iArr2 = new int[iArr.length];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                precedingIterator._stack = iArr2;
                return precedingIterator;
            } catch (CloneNotSupportedException unused) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void gotoMark() {
            this._sp = this._markedsp;
            this._currentNode = this._markedNode;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public boolean isReverse() {
            return true;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            this._currentNode++;
            while (true) {
                int i = this._sp;
                if (i < 0) {
                    return -1;
                }
                int i2 = this._currentNode;
                if (i2 >= this._stack[i]) {
                    this._sp = i - 1;
                } else if (DTMDefaultBaseIterators.this._type(i2) != 2 && DTMDefaultBaseIterators.this._type(this._currentNode) != 13) {
                    return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(this._currentNode));
                }
                this._currentNode++;
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            this._sp = this._oldsp;
            return resetPosition();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this._markedsp = this._sp;
            this._markedNode = this._currentNode;
            this._markedDescendant = this._stack[0];
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(i);
            if (DTMDefaultBaseIterators.this._type(iMakeNodeIdentity) == 2) {
                iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
            }
            this._startNode = iMakeNodeIdentity;
            this._stack[0] = iMakeNodeIdentity;
            int i2 = 0;
            while (true) {
                iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
                if (iMakeNodeIdentity == -1) {
                    break;
                }
                int i3 = i2 + 1;
                int[] iArr = this._stack;
                if (i3 == iArr.length) {
                    int[] iArr2 = new int[i2 + 5];
                    System.arraycopy(iArr, 0, iArr2, 0, i3);
                    this._stack = iArr2;
                }
                this._stack[i3] = iMakeNodeIdentity;
                i2 = i3;
            }
            if (i2 > 0) {
                i2--;
            }
            this._currentNode = this._stack[i2];
            this._sp = i2;
            this._oldsp = i2;
            return resetPosition();
        }
    }

    public class PrecedingSiblingIterator extends InternalAxisIteratorBase {
        protected int _startNodeID;

        public PrecedingSiblingIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public boolean isReverse() {
            return true;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == this._startNodeID || i == -1) {
                return -1;
            }
            this._currentNode = DTMDefaultBaseIterators.this._nextsib(i);
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int iMakeNodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(i);
            this._startNodeID = iMakeNodeIdentity;
            if (iMakeNodeIdentity == -1) {
                this._currentNode = iMakeNodeIdentity;
                return resetPosition();
            }
            DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
            short type = dTMDefaultBaseIterators.m_expandedNameTable.getType(dTMDefaultBaseIterators._exptype(iMakeNodeIdentity));
            if (2 == type || 13 == type) {
                this._currentNode = iMakeNodeIdentity;
            } else {
                int i_parent = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
                this._currentNode = i_parent;
                if (-1 != i_parent) {
                    this._currentNode = DTMDefaultBaseIterators.this._firstch(i_parent);
                } else {
                    this._currentNode = iMakeNodeIdentity;
                }
            }
            return resetPosition();
        }
    }

    public class RootIterator extends InternalAxisIteratorBase {
        public RootIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._startNode;
            if (i == this._currentNode) {
                return -1;
            }
            this._currentNode = i;
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = DTMDefaultBaseIterators.this.getDocumentRoot(i);
            this._currentNode = -1;
            return resetPosition();
        }
    }

    public final class TypedAncestorIterator extends AncestorIterator {
        private final int _nodeType;

        public TypedAncestorIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.AncestorIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            this.m_realStartNode = i;
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(i);
            int i2 = this._nodeType;
            if (!this._includeSelf && i != -1) {
                iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
            }
            this._startNode = i;
            if (i2 >= 14) {
                while (iMakeNodeIdentity != -1) {
                    if (DTMDefaultBaseIterators.this._exptype(iMakeNodeIdentity) == i2) {
                        this.m_ancestors.addElement(DTMDefaultBaseIterators.this.makeNodeHandle(iMakeNodeIdentity));
                    }
                    iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
                }
            } else {
                while (iMakeNodeIdentity != -1) {
                    int i_exptype = DTMDefaultBaseIterators.this._exptype(iMakeNodeIdentity);
                    if ((i_exptype >= 14 && DTMDefaultBaseIterators.this.m_expandedNameTable.getType(i_exptype) == i2) || (i_exptype < 14 && i_exptype == i2)) {
                        this.m_ancestors.addElement(DTMDefaultBaseIterators.this.makeNodeHandle(iMakeNodeIdentity));
                    }
                    iMakeNodeIdentity = DTMDefaultBaseIterators.this._parent(iMakeNodeIdentity);
                }
            }
            int size = this.m_ancestors.size() - 1;
            this.m_ancestorsPos = size;
            this._currentNode = size >= 0 ? this.m_ancestors.elementAt(size) : -1;
            return resetPosition();
        }
    }

    public final class TypedAttributeIterator extends InternalAxisIteratorBase {
        private final int _nodeType;

        public TypedAttributeIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            this._currentNode = -1;
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = DTMDefaultBaseIterators.this.getTypedAttribute(i, this._nodeType);
            return resetPosition();
        }
    }

    public final class TypedChildrenIterator extends InternalAxisIteratorBase {
        private final int _nodeType;

        public TypedChildrenIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_nextsib = this._currentNode;
            int i = this._nodeType;
            if (i >= 14) {
                while (i_nextsib != -1 && DTMDefaultBaseIterators.this._exptype(i_nextsib) != i) {
                    i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                }
            } else {
                while (i_nextsib != -1) {
                    int i_exptype = DTMDefaultBaseIterators.this._exptype(i_nextsib);
                    if (i_exptype < 14) {
                        if (i_exptype == i) {
                            break;
                        }
                        i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                    } else {
                        if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(i_exptype) == i) {
                            break;
                        }
                        i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                    }
                }
            }
            if (i_nextsib == -1) {
                this._currentNode = -1;
                return -1;
            }
            this._currentNode = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i_nextsib));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int i_firstch = -1;
            if (i != -1) {
                DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
                i_firstch = dTMDefaultBaseIterators._firstch(dTMDefaultBaseIterators.makeNodeIdentity(i));
            }
            this._currentNode = i_firstch;
            return resetPosition();
        }
    }

    public final class TypedDescendantIterator extends DescendantIterator {
        private final int _nodeType;

        public TypedDescendantIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.DescendantIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            if (this._startNode == -1) {
                return -1;
            }
            int i = this._currentNode;
            do {
                i++;
                short s_type = DTMDefaultBaseIterators.this._type(i);
                if (-1 != s_type && isDescendant(i)) {
                    if (s_type == this._nodeType) {
                        break;
                    }
                } else {
                    this._currentNode = -1;
                    return -1;
                }
            } while (DTMDefaultBaseIterators.this._exptype(i) != this._nodeType);
            this._currentNode = i;
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i));
        }
    }

    public final class TypedFollowingIterator extends FollowingIterator {
        private final int _nodeType;

        public TypedFollowingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.FollowingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i;
            do {
                i = this._currentNode;
                this._currentNode = this.m_traverser.next(this._startNode, i);
                if (i == -1 || DTMDefaultBaseIterators.this.getExpandedTypeID(i) == this._nodeType) {
                    break;
                }
            } while (DTMDefaultBaseIterators.this.getNodeType(i) != this._nodeType);
            if (i == -1) {
                return -1;
            }
            return returnNode(i);
        }
    }

    public final class TypedFollowingSiblingIterator extends FollowingSiblingIterator {
        private final int _nodeType;

        public TypedFollowingSiblingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.FollowingSiblingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_nextsib = this._currentNode;
            if (i_nextsib == -1) {
                return -1;
            }
            int i = this._nodeType;
            if (i >= 14) {
                do {
                    i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                    if (i_nextsib == -1) {
                        break;
                    }
                } while (DTMDefaultBaseIterators.this._exptype(i_nextsib) != i);
            } else {
                while (true) {
                    i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                    if (i_nextsib == -1) {
                        break;
                    }
                    int i_exptype = DTMDefaultBaseIterators.this._exptype(i_nextsib);
                    if (i_exptype < 14) {
                        if (i_exptype == i) {
                            break;
                        }
                    } else if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(i_exptype) == i) {
                        break;
                    }
                }
            }
            this._currentNode = i_nextsib;
            if (i_nextsib == -1) {
                return -1;
            }
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i_nextsib));
        }
    }

    public class TypedNamespaceIterator extends NamespaceIterator {
        private final int _nodeType;

        public TypedNamespaceIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.NamespaceIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int nextNamespaceNode = this._currentNode;
            while (nextNamespaceNode != -1) {
                if (DTMDefaultBaseIterators.this.getExpandedTypeID(nextNamespaceNode) == this._nodeType || DTMDefaultBaseIterators.this.getNodeType(nextNamespaceNode) == this._nodeType || DTMDefaultBaseIterators.this.getNamespaceType(nextNamespaceNode) == this._nodeType) {
                    this._currentNode = nextNamespaceNode;
                    return returnNode(nextNamespaceNode);
                }
                nextNamespaceNode = DTMDefaultBaseIterators.this.getNextNamespaceNode(this._startNode, nextNamespaceNode, true);
            }
            this._currentNode = -1;
            return -1;
        }
    }

    public final class TypedPrecedingIterator extends PrecedingIterator {
        private final int _nodeType;

        public TypedPrecedingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.PrecedingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i2 = this._nodeType;
            if (i2 < 14) {
                while (true) {
                    i++;
                    int i3 = this._sp;
                    if (i3 >= 0) {
                        if (i >= this._stack[i3]) {
                            int i4 = i3 - 1;
                            this._sp = i4;
                            if (i4 < 0) {
                            }
                        } else {
                            int i_exptype = DTMDefaultBaseIterators.this._exptype(i);
                            if (i_exptype < 14) {
                                if (i_exptype == i2) {
                                    break;
                                }
                            } else if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(i_exptype) == i2) {
                                break;
                            }
                        }
                    }
                    i = -1;
                    break;
                }
            }
            while (true) {
                i++;
                int i5 = this._sp;
                if (i5 >= 0) {
                    if (i >= this._stack[i5]) {
                        int i6 = i5 - 1;
                        this._sp = i6;
                        if (i6 < 0) {
                        }
                    } else if (DTMDefaultBaseIterators.this._exptype(i) == i2) {
                        break;
                    }
                }
                i = -1;
                break;
            }
            this._currentNode = i;
            if (i == -1) {
                return -1;
            }
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i));
        }
    }

    public final class TypedPrecedingSiblingIterator extends PrecedingSiblingIterator {
        private final int _nodeType;

        public TypedPrecedingSiblingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.PrecedingSiblingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_nextsib = this._currentNode;
            int i = this._nodeType;
            int i2 = this._startNodeID;
            if (i >= 14) {
                while (i_nextsib != -1 && i_nextsib != i2 && DTMDefaultBaseIterators.this._exptype(i_nextsib) != i) {
                    i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                }
            } else {
                while (i_nextsib != -1 && i_nextsib != i2) {
                    int i_exptype = DTMDefaultBaseIterators.this._exptype(i_nextsib);
                    if (i_exptype < 14) {
                        if (i_exptype == i) {
                            break;
                        }
                        i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                    } else {
                        if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(i_exptype) == i) {
                            break;
                        }
                        i_nextsib = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
                    }
                }
            }
            if (i_nextsib == -1 || i_nextsib == this._startNodeID) {
                this._currentNode = -1;
                return -1;
            }
            this._currentNode = DTMDefaultBaseIterators.this._nextsib(i_nextsib);
            return returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(i_nextsib));
        }
    }

    public class TypedRootIterator extends RootIterator {
        private final int _nodeType;

        public TypedRootIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.RootIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._startNode;
            if (i == this._currentNode) {
                return -1;
            }
            int i2 = this._nodeType;
            int expandedTypeID = DTMDefaultBaseIterators.this.getExpandedTypeID(i);
            this._currentNode = i;
            if (i2 >= 14) {
                if (i2 == expandedTypeID) {
                    return returnNode(i);
                }
            } else if (expandedTypeID < 14) {
                if (expandedTypeID == i2) {
                    return returnNode(i);
                }
            } else if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(expandedTypeID) == i2) {
                return returnNode(i);
            }
            return -1;
        }
    }

    public final class TypedSingletonIterator extends SingletonIterator {
        private final int _nodeType;

        public TypedSingletonIterator(int i) {
            super(DTMDefaultBaseIterators.this);
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.SingletonIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i2 = this._nodeType;
            this._currentNode = -1;
            DTMDefaultBaseIterators dTMDefaultBaseIterators = DTMDefaultBaseIterators.this;
            if (i2 >= 14) {
                if (dTMDefaultBaseIterators.getExpandedTypeID(i) == i2) {
                    return returnNode(i);
                }
            } else if (dTMDefaultBaseIterators.getNodeType(i) == i2) {
                return returnNode(i);
            }
            return -1;
        }
    }

    public DTMDefaultBaseIterators(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z) {
        super(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getAxisIterator(int i) {
        if (i == 19) {
            return new RootIterator();
        }
        switch (i) {
            case 0:
                return new AncestorIterator();
            case 1:
                return new AncestorIterator().includeSelf();
            case 2:
                return new AttributeIterator();
            case 3:
                return new ChildrenIterator();
            case 4:
                return new DescendantIterator();
            case 5:
                return new DescendantIterator().includeSelf();
            case 6:
                return new FollowingIterator();
            case 7:
                return new FollowingSiblingIterator();
            default:
                switch (i) {
                    case 9:
                        return new NamespaceIterator();
                    case 10:
                        return new ParentIterator();
                    case 11:
                        return new PrecedingIterator();
                    case 12:
                        return new PrecedingSiblingIterator();
                    case 13:
                        return new SingletonIterator(this);
                    default:
                        throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_AXIS_NOT_IMPLEMENTED", new Object[]{Axis.getNames(i)}));
                }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        if (i == 19) {
            return new TypedRootIterator(i2);
        }
        switch (i) {
            case 0:
                return new TypedAncestorIterator(i2);
            case 1:
                return new TypedAncestorIterator(i2).includeSelf();
            case 2:
                return new TypedAttributeIterator(i2);
            case 3:
                return new TypedChildrenIterator(i2);
            case 4:
                return new TypedDescendantIterator(i2);
            case 5:
                return new TypedDescendantIterator(i2).includeSelf();
            case 6:
                return new TypedFollowingIterator(i2);
            case 7:
                return new TypedFollowingSiblingIterator(i2);
            default:
                switch (i) {
                    case 9:
                        return new TypedNamespaceIterator(i2);
                    case 10:
                        return new ParentIterator().setNodeType(i2);
                    case 11:
                        return new TypedPrecedingIterator(i2);
                    case 12:
                        return new TypedPrecedingSiblingIterator(i2);
                    case 13:
                        return new TypedSingletonIterator(i2);
                    default:
                        throw new DTMException(XMLMessages.createXMLMessage("ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED", new Object[]{Axis.getNames(i)}));
                }
        }
    }

    public DTMDefaultBaseIterators(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z, int i2, boolean z2, boolean z3) {
        super(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z, i2, z2, z3);
    }

    public class SingletonIterator extends InternalAxisIteratorBase {
        private boolean _isConstant;

        public SingletonIterator(int i, boolean z) {
            super();
            this._startNode = i;
            this._currentNode = i;
            this._isConstant = z;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            this._currentNode = -1;
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            if (this._isConstant) {
                this._currentNode = this._startNode;
                return resetPosition();
            }
            boolean z = this._isRestartable;
            this._isRestartable = true;
            setStartNode(this._startNode);
            this._isRestartable = z;
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = DTMDefaultBaseIterators.this.getDocument();
            }
            if (this._isConstant) {
                this._currentNode = this._startNode;
                return resetPosition();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = i;
            return resetPosition();
        }

        public SingletonIterator(DTMDefaultBaseIterators dTMDefaultBaseIterators, int i) {
            this(i, false);
        }

        public SingletonIterator(DTMDefaultBaseIterators dTMDefaultBaseIterators) {
            this(Integer.MIN_VALUE, false);
        }
    }
}
