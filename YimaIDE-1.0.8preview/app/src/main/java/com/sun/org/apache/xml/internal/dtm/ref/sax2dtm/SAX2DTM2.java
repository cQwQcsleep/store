package com.sun.org.apache.xml.internal.dtm.ref.sax2dtm;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMException;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import com.sun.org.apache.xml.internal.dtm.ref.ExtendedType;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.SuballocatedIntVector;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xml.internal.utils.XMLStringDefault;
import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.xml.transform.Source;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAX2DTM2 extends SAX2DTM {
    private static final String EMPTY_STR = "";
    private static final XMLString EMPTY_XML_STR = new XMLStringDefault("");
    protected static final int TEXT_LENGTH_BITS = 10;
    protected static final int TEXT_LENGTH_MAX = 1023;
    protected static final int TEXT_OFFSET_BITS = 21;
    protected static final int TEXT_OFFSET_MAX = 2097151;
    protected int m_MASK;
    protected int m_SHIFT;
    protected int m_blocksize;
    protected boolean m_buildIdIndex;
    private int[][] m_exptype_map;
    private int[] m_exptype_map0;
    protected ExtendedType[] m_extendedTypes;
    private int[][] m_firstch_map;
    private int[] m_firstch_map0;
    private int m_maxNodeIndex;
    private int[][] m_nextsib_map;
    private int[] m_nextsib_map0;
    private int[][] m_parent_map;
    private int[] m_parent_map0;
    private int m_valueIndex;
    protected List<String> m_values;

    public class AncestorIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        private static final int m_blocksize = 32;
        int[] m_ancestors;
        int m_ancestorsPos;
        int m_markedPos;
        int m_realStartNode;
        int m_size;

        public AncestorIterator() {
            super();
            this.m_ancestors = new int[32];
            this.m_size = 0;
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
            this._currentNode = i >= 0 ? this.m_ancestors[i] : -1;
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
            this._currentNode = i2 >= 0 ? this.m_ancestors[i2] : -1;
            return returnNode(i);
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            int i = this.m_size - 1;
            this.m_ancestorsPos = i;
            this._currentNode = i >= 0 ? this.m_ancestors[i] : -1;
            return resetPosition();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.InternalAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public void setMark() {
            this.m_markedPos = this.m_ancestorsPos;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            int i2;
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            this.m_realStartNode = i;
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            this.m_size = 0;
            if (iMakeNodeIdentity == -1) {
                this._currentNode = -1;
                this.m_ancestorsPos = 0;
                return this;
            }
            if (!this._includeSelf) {
                iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                i = SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity);
            }
            this._startNode = i;
            while (true) {
                i2 = this.m_size;
                if (iMakeNodeIdentity == -1) {
                    break;
                }
                int[] iArr = this.m_ancestors;
                if (i2 >= iArr.length) {
                    int[] iArr2 = new int[i2 * 2];
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    this.m_ancestors = iArr2;
                }
                int[] iArr3 = this.m_ancestors;
                int i3 = this.m_size;
                this.m_size = i3 + 1;
                iArr3[i3] = i;
                iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                i = SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity);
            }
            int i4 = i2 - 1;
            this.m_ancestorsPos = i4;
            this._currentNode = i4 >= 0 ? this.m_ancestors[i4] : -1;
            return resetPosition();
        }
    }

    public final class AttributeIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        public AttributeIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == -1) {
                return -1;
            }
            this._currentNode = SAX2DTM2.this.getNextAttributeIdentity(i);
            return returnNode(SAX2DTM2.this.makeNodeHandle(i));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
            this._currentNode = sax2dtm2.getFirstAttributeIdentity(sax2dtm2.makeNodeIdentity(i));
            return resetPosition();
        }
    }

    public final class ChildrenIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        public ChildrenIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == -1) {
                return -1;
            }
            this._currentNode = SAX2DTM2.this._nextsib2(i);
            return returnNode(SAX2DTM2.this.makeNodeHandle(i));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int i_firstch2 = -1;
            if (i != -1) {
                SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
                i_firstch2 = sax2dtm2._firstch2(sax2dtm2.makeNodeIdentity(i));
            }
            this._currentNode = i_firstch2;
            return resetPosition();
        }
    }

    public class DescendantIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        public DescendantIterator() {
            super();
        }

        public final boolean isDescendant(int i) {
            int i_parent2 = SAX2DTM2.this._parent2(i);
            int i2 = this._startNode;
            return i_parent2 >= i2 || i2 == i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int nodeType;
            int i = this._startNode;
            if (i == -1) {
                return -1;
            }
            if (this._includeSelf) {
                int i2 = this._currentNode;
                if (i2 + 1 == i) {
                    SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
                    int i3 = i2 + 1;
                    this._currentNode = i3;
                    return returnNode(sax2dtm2.makeNodeHandle(i3));
                }
            }
            int i4 = this._currentNode;
            if (i != 0) {
                while (true) {
                    i4++;
                    int i_type2 = SAX2DTM2.this._type2(i4);
                    if (-1 == i_type2 || !isDescendant(i4)) {
                        break;
                    }
                    if (2 == i_type2 || 3 == i_type2 || 13 == i_type2) {
                    }
                }
                this._currentNode = -1;
                return -1;
            }
            while (true) {
                i4++;
                int i_exptype2 = SAX2DTM2.this._exptype2(i4);
                if (-1 != i_exptype2) {
                    if (i_exptype2 != 3 && (nodeType = SAX2DTM2.this.m_extendedTypes[i_exptype2].getNodeType()) != 2 && nodeType != 13) {
                        break;
                    }
                } else {
                    this._currentNode = -1;
                    return -1;
                }
            }
            this._currentNode = i4;
            return returnNode(SAX2DTM2.this.makeNodeHandle(i4));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator reset() {
            boolean z = this._isRestartable;
            this._isRestartable = true;
            setStartNode(SAX2DTM2.this.makeNodeHandle(this._startNode));
            this._isRestartable = z;
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            this._startNode = iMakeNodeIdentity;
            if (this._includeSelf) {
                iMakeNodeIdentity--;
            }
            this._currentNode = iMakeNodeIdentity;
            return resetPosition();
        }
    }

    public class FollowingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        public FollowingIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            while (true) {
                iMakeNodeIdentity++;
                int i_type2 = SAX2DTM2.this._type2(iMakeNodeIdentity);
                if (-1 == i_type2) {
                    this._currentNode = -1;
                    return returnNode(i);
                }
                if (2 != i_type2 && 13 != i_type2) {
                    this._currentNode = SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity);
                    return returnNode(i);
                }
            }
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            int i_firstch2;
            int i_nextsib2;
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            int i_type2 = SAX2DTM2.this._type2(iMakeNodeIdentity);
            if ((2 == i_type2 || 13 == i_type2) && -1 != (i_firstch2 = SAX2DTM2.this._firstch2((iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity))))) {
                this._currentNode = SAX2DTM2.this.makeNodeHandle(i_firstch2);
                return resetPosition();
            }
            do {
                i_nextsib2 = SAX2DTM2.this._nextsib2(iMakeNodeIdentity);
                if (-1 == i_nextsib2) {
                    iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                }
                if (-1 != i_nextsib2) {
                    break;
                }
            } while (-1 != iMakeNodeIdentity);
            this._currentNode = SAX2DTM2.this.makeNodeHandle(i_nextsib2);
            return resetPosition();
        }
    }

    public class FollowingSiblingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        public FollowingSiblingIterator() {
            super();
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            int i_nextsib2 = i != -1 ? SAX2DTM2.this._nextsib2(i) : -1;
            this._currentNode = i_nextsib2;
            return returnNode(SAX2DTM2.this.makeNodeHandle(i_nextsib2));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            this._currentNode = SAX2DTM2.this.makeNodeIdentity(i);
            return resetPosition();
        }
    }

    public final class ParentIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        private int _nodeType;

        public ParentIterator() {
            super();
            this._nodeType = -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == -1) {
                return -1;
            }
            int i2 = this._nodeType;
            if (i2 == -1) {
                this._currentNode = -1;
                return returnNode(SAX2DTM2.this.makeNodeHandle(i));
            }
            SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
            if (i2 >= 14) {
                if (i2 == sax2dtm2._exptype2(i)) {
                    this._currentNode = -1;
                    return returnNode(SAX2DTM2.this.makeNodeHandle(i));
                }
            } else if (i2 == sax2dtm2._type2(i)) {
                this._currentNode = -1;
                return returnNode(SAX2DTM2.this.makeNodeHandle(i));
            }
            return -1;
        }

        public DTMAxisIterator setNodeType(int i) {
            this._nodeType = i;
            return this;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            if (i != -1) {
                SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
                this._currentNode = sax2dtm2._parent2(sax2dtm2.makeNodeIdentity(i));
            } else {
                this._currentNode = -1;
            }
            return resetPosition();
        }
    }

    public class PrecedingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
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
            int i = this._currentNode;
            while (true) {
                this._currentNode = i + 1;
                int i2 = this._sp;
                if (i2 < 0) {
                    return -1;
                }
                int i3 = this._currentNode;
                if (i3 < this._stack[i2]) {
                    int i_type2 = SAX2DTM2.this._type2(i3);
                    if (i_type2 != 2 && i_type2 != 13) {
                        return returnNode(SAX2DTM2.this.makeNodeHandle(this._currentNode));
                    }
                } else {
                    this._sp = i2 - 1;
                }
                i = this._currentNode;
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
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            if (SAX2DTM2.this._type2(iMakeNodeIdentity) == 2) {
                iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
            }
            this._startNode = iMakeNodeIdentity;
            this._stack[0] = iMakeNodeIdentity;
            int i2 = 0;
            while (true) {
                iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                if (iMakeNodeIdentity == -1) {
                    break;
                }
                i2++;
                int[] iArr = this._stack;
                if (i2 == iArr.length) {
                    int[] iArr2 = new int[i2 * 2];
                    System.arraycopy(iArr, 0, iArr2, 0, i2);
                    this._stack = iArr2;
                }
                this._stack[i2] = iMakeNodeIdentity;
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

    public class PrecedingSiblingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
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
            this._currentNode = SAX2DTM2.this._nextsib2(i);
            return returnNode(SAX2DTM2.this.makeNodeHandle(i));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            this._startNodeID = iMakeNodeIdentity;
            if (iMakeNodeIdentity == -1) {
                this._currentNode = iMakeNodeIdentity;
                return resetPosition();
            }
            int i_type2 = SAX2DTM2.this._type2(iMakeNodeIdentity);
            if (2 == i_type2 || 13 == i_type2) {
                this._currentNode = iMakeNodeIdentity;
            } else {
                int i_parent2 = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                this._currentNode = i_parent2;
                if (-1 != i_parent2) {
                    this._currentNode = SAX2DTM2.this._firstch2(i_parent2);
                } else {
                    this._currentNode = iMakeNodeIdentity;
                }
            }
            return resetPosition();
        }
    }

    public final class TypedAncestorIterator extends AncestorIterator {
        private final int _nodeType;

        public TypedAncestorIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getLast() {
            return this.m_size;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getNodeByPosition(int i) {
            if (i <= 0 || i > this.m_size) {
                return -1;
            }
            return this.m_ancestors[i - 1];
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.AncestorIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            this.m_realStartNode = i;
            if (!this._isRestartable) {
                return this;
            }
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(i);
            this.m_size = 0;
            if (iMakeNodeIdentity == -1) {
                this._currentNode = -1;
                this.m_ancestorsPos = 0;
                return this;
            }
            int i2 = this._nodeType;
            if (!this._includeSelf) {
                iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                i = SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity);
            }
            this._startNode = i;
            if (i2 >= 14) {
                while (iMakeNodeIdentity != -1) {
                    if (SAX2DTM2.this._exptype2(iMakeNodeIdentity) == i2) {
                        int i3 = this.m_size;
                        int[] iArr = this.m_ancestors;
                        if (i3 >= iArr.length) {
                            int[] iArr2 = new int[i3 * 2];
                            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                            this.m_ancestors = iArr2;
                        }
                        int[] iArr3 = this.m_ancestors;
                        int i4 = this.m_size;
                        this.m_size = i4 + 1;
                        iArr3[i4] = SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity);
                    }
                    iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                }
            } else {
                while (iMakeNodeIdentity != -1) {
                    int i_exptype2 = SAX2DTM2.this._exptype2(iMakeNodeIdentity);
                    if ((i_exptype2 < 14 && i_exptype2 == i2) || (i_exptype2 >= 14 && SAX2DTM2.this.m_extendedTypes[i_exptype2].getNodeType() == i2)) {
                        int i5 = this.m_size;
                        int[] iArr4 = this.m_ancestors;
                        if (i5 >= iArr4.length) {
                            int[] iArr5 = new int[i5 * 2];
                            System.arraycopy(iArr4, 0, iArr5, 0, iArr4.length);
                            this.m_ancestors = iArr5;
                        }
                        int[] iArr6 = this.m_ancestors;
                        int i6 = this.m_size;
                        this.m_size = i6 + 1;
                        iArr6[i6] = SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity);
                    }
                    iMakeNodeIdentity = SAX2DTM2.this._parent2(iMakeNodeIdentity);
                }
            }
            int i7 = this.m_size - 1;
            this.m_ancestorsPos = i7;
            this._currentNode = i7 >= 0 ? this.m_ancestors[i7] : -1;
            return resetPosition();
        }
    }

    public final class TypedAttributeIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
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
            this._currentNode = SAX2DTM2.this.getTypedAttribute(i, this._nodeType);
            return resetPosition();
        }
    }

    public final class TypedChildrenIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
        private final int _nodeType;

        public TypedChildrenIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getNodeByPosition(int i) {
            if (i <= 0) {
                return -1;
            }
            int i_nextsib2 = this._currentNode;
            int i2 = this._nodeType;
            int i3 = 0;
            if (i2 != 1) {
                while (i_nextsib2 != -1) {
                    if (SAX2DTM2.this._exptype2(i_nextsib2) == i2 && (i3 = i3 + 1) == i) {
                        return SAX2DTM2.this.makeNodeHandle(i_nextsib2);
                    }
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
                return -1;
            }
            while (i_nextsib2 != -1) {
                if (SAX2DTM2.this._exptype2(i_nextsib2) >= 14 && (i3 = i3 + 1) == i) {
                    return SAX2DTM2.this.makeNodeHandle(i_nextsib2);
                }
                i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
            }
            return -1;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_nextsib2 = this._currentNode;
            if (i_nextsib2 == -1) {
                return -1;
            }
            int i = this._nodeType;
            if (i != 1) {
                while (i_nextsib2 != -1 && SAX2DTM2.this._exptype2(i_nextsib2) != i) {
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
            } else {
                while (i_nextsib2 != -1 && SAX2DTM2.this._exptype2(i_nextsib2) < 14) {
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
            }
            if (i_nextsib2 == -1) {
                this._currentNode = -1;
                return -1;
            }
            this._currentNode = SAX2DTM2.this._nextsib2(i_nextsib2);
            return returnNode(SAX2DTM2.this.makeNodeHandle(i_nextsib2));
        }

        @Override // com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public DTMAxisIterator setStartNode(int i) {
            if (i == 0) {
                i = SAX2DTM2.this.getDocument();
            }
            if (!this._isRestartable) {
                return this;
            }
            this._startNode = i;
            int i_firstch2 = -1;
            if (i != -1) {
                SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
                i_firstch2 = sax2dtm2._firstch2(sax2dtm2.makeNodeIdentity(i));
            }
            this._currentNode = i_firstch2;
            return resetPosition();
        }
    }

    public final class TypedDescendantIterator extends DescendantIterator {
        private final int _nodeType;

        public TypedDescendantIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.DescendantIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_exptype2;
            int i = this._startNode;
            if (i == -1) {
                return -1;
            }
            int i2 = this._currentNode;
            int i3 = this._nodeType;
            if (i3 == 1) {
                if (i != 0) {
                    while (true) {
                        i2++;
                        int i_exptype3 = SAX2DTM2.this._exptype2(i2);
                        if (-1 != i_exptype3 && (SAX2DTM2.this._parent2(i2) >= i || i == i2)) {
                            if (i_exptype3 >= 14 && SAX2DTM2.this.m_extendedTypes[i_exptype3].getNodeType() == 1) {
                                break;
                            }
                        } else {
                            this._currentNode = -1;
                            return -1;
                        }
                    }
                } else {
                    while (true) {
                        i2++;
                        int i_exptype4 = SAX2DTM2.this._exptype2(i2);
                        if (-1 == i_exptype4) {
                            this._currentNode = -1;
                            return -1;
                        }
                        if (i_exptype4 < 14 || SAX2DTM2.this.m_extendedTypes[i_exptype4].getNodeType() != 1) {
                        }
                    }
                }
            } else {
                do {
                    i2++;
                    i_exptype2 = SAX2DTM2.this._exptype2(i2);
                    if (-1 == i_exptype2 || (SAX2DTM2.this._parent2(i2) < i && i != i2)) {
                        this._currentNode = -1;
                        return -1;
                    }
                } while (i_exptype2 != i3);
            }
            this._currentNode = i2;
            return returnNode(SAX2DTM2.this.makeNodeHandle(i2));
        }
    }

    public final class TypedFollowingIterator extends FollowingIterator {
        private final int _nodeType;

        public TypedFollowingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.FollowingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i;
            int i_type2;
            int i_type3;
            int i2 = this._nodeType;
            int iMakeNodeIdentity = SAX2DTM2.this.makeNodeIdentity(this._currentNode);
            if (i2 >= 14) {
                while (true) {
                    i = iMakeNodeIdentity;
                    while (true) {
                        i++;
                        i_type3 = SAX2DTM2.this._type2(i);
                        if (i_type3 == -1 || (2 != i_type3 && 13 != i_type3)) {
                            break;
                        }
                    }
                    if (i_type3 == -1) {
                        i = -1;
                    }
                    if (iMakeNodeIdentity == -1 || SAX2DTM2.this._exptype2(iMakeNodeIdentity) == i2) {
                        break;
                    }
                    iMakeNodeIdentity = i;
                }
            } else {
                while (true) {
                    i = iMakeNodeIdentity;
                    while (true) {
                        i++;
                        i_type2 = SAX2DTM2.this._type2(i);
                        if (i_type2 == -1 || (2 != i_type2 && 13 != i_type2)) {
                            break;
                        }
                    }
                    if (i_type2 == -1) {
                        i = -1;
                    }
                    if (iMakeNodeIdentity == -1 || SAX2DTM2.this._exptype2(iMakeNodeIdentity) == i2 || SAX2DTM2.this._type2(iMakeNodeIdentity) == i2) {
                        break;
                    }
                    iMakeNodeIdentity = i;
                }
            }
            this._currentNode = SAX2DTM2.this.makeNodeHandle(i);
            if (iMakeNodeIdentity == -1) {
                return -1;
            }
            return returnNode(SAX2DTM2.this.makeNodeHandle(iMakeNodeIdentity));
        }
    }

    public final class TypedFollowingSiblingIterator extends FollowingSiblingIterator {
        private final int _nodeType;

        public TypedFollowingSiblingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.FollowingSiblingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_nextsib2 = this._currentNode;
            if (i_nextsib2 == -1) {
                return -1;
            }
            int i = this._nodeType;
            if (i == 1) {
                do {
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                    if (i_nextsib2 == -1) {
                        break;
                    }
                } while (SAX2DTM2.this._exptype2(i_nextsib2) < 14);
            } else {
                do {
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                    if (i_nextsib2 == -1) {
                        break;
                    }
                } while (SAX2DTM2.this._exptype2(i_nextsib2) != i);
            }
            this._currentNode = i_nextsib2;
            if (i_nextsib2 == -1) {
                return -1;
            }
            return returnNode(SAX2DTM2.this.makeNodeHandle(i_nextsib2));
        }
    }

    public final class TypedPrecedingIterator extends PrecedingIterator {
        private final int _nodeType;

        public TypedPrecedingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.PrecedingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
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
                            int i_exptype2 = SAX2DTM2.this._exptype2(i);
                            if (i_exptype2 < 14) {
                                if (i_exptype2 == i2) {
                                    break;
                                }
                            } else if (SAX2DTM2.this.m_extendedTypes[i_exptype2].getNodeType() == i2) {
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
                    } else if (SAX2DTM2.this._exptype2(i) == i2) {
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
            return returnNode(SAX2DTM2.this.makeNodeHandle(i));
        }
    }

    public final class TypedPrecedingSiblingIterator extends PrecedingSiblingIterator {
        private final int _nodeType;

        public TypedPrecedingSiblingIterator(int i) {
            super();
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int getLast() {
            int i = this._last;
            if (i != -1) {
                return i;
            }
            setMark();
            int i_nextsib2 = this._currentNode;
            int i2 = this._nodeType;
            int i3 = this._startNodeID;
            int i4 = 0;
            if (i2 != 1) {
                while (i_nextsib2 != -1 && i_nextsib2 != i3) {
                    if (SAX2DTM2.this._exptype2(i_nextsib2) == i2) {
                        i4++;
                    }
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
            } else {
                while (i_nextsib2 != -1 && i_nextsib2 != i3) {
                    if (SAX2DTM2.this._exptype2(i_nextsib2) >= 14) {
                        i4++;
                    }
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
            }
            gotoMark();
            this._last = i4;
            return i4;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2.PrecedingSiblingIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i_nextsib2 = this._currentNode;
            int i = this._nodeType;
            int i2 = this._startNodeID;
            if (i != 1) {
                while (i_nextsib2 != -1 && i_nextsib2 != i2 && SAX2DTM2.this._exptype2(i_nextsib2) != i) {
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
            } else {
                while (i_nextsib2 != -1 && i_nextsib2 != i2 && SAX2DTM2.this._exptype2(i_nextsib2) < 14) {
                    i_nextsib2 = SAX2DTM2.this._nextsib2(i_nextsib2);
                }
            }
            if (i_nextsib2 == -1 || i_nextsib2 == i2) {
                this._currentNode = -1;
                return -1;
            }
            this._currentNode = SAX2DTM2.this._nextsib2(i_nextsib2);
            return returnNode(SAX2DTM2.this.makeNodeHandle(i_nextsib2));
        }
    }

    public class TypedRootIterator extends DTMDefaultBaseIterators.RootIterator {
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
            SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
            int i_exptype2 = sax2dtm2._exptype2(sax2dtm2.makeNodeIdentity(i));
            this._currentNode = i;
            int i2 = this._nodeType;
            if (i2 >= 14) {
                if (i2 == i_exptype2) {
                    return returnNode(i);
                }
            } else if (i_exptype2 < 14) {
                if (i_exptype2 == i2) {
                    return returnNode(i);
                }
            } else if (SAX2DTM2.this.m_extendedTypes[i_exptype2].getNodeType() == this._nodeType) {
                return returnNode(i);
            }
            return -1;
        }
    }

    public final class TypedSingletonIterator extends DTMDefaultBaseIterators.SingletonIterator {
        private final int _nodeType;

        public TypedSingletonIterator(int i) {
            super(SAX2DTM2.this);
            this._nodeType = i;
        }

        @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.SingletonIterator, com.sun.org.apache.xml.internal.dtm.DTMAxisIterator
        public int next() {
            int i = this._currentNode;
            if (i == -1) {
                return -1;
            }
            this._currentNode = -1;
            int i2 = this._nodeType;
            SAX2DTM2 sax2dtm2 = SAX2DTM2.this;
            if (i2 >= 14) {
                if (sax2dtm2._exptype2(sax2dtm2.makeNodeIdentity(i)) == this._nodeType) {
                    return returnNode(i);
                }
            } else if (sax2dtm2._type2(sax2dtm2.makeNodeIdentity(i)) == this._nodeType) {
                return returnNode(i);
            }
            return -1;
        }
    }

    public SAX2DTM2(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
        super(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z, i2, z2, z4);
        int i3 = 0;
        this.m_valueIndex = 0;
        this.m_buildIdIndex = true;
        int i4 = i2;
        while (true) {
            i4 >>>= 1;
            if (i4 == 0) {
                int i5 = 1 << i3;
                this.m_blocksize = i5;
                this.m_SHIFT = i3;
                this.m_MASK = i5 - 1;
                this.m_buildIdIndex = z3;
                this.m_values = new ArrayList(32);
                this.m_maxNodeIndex = 65536;
                this.m_exptype_map0 = this.m_exptype.getMap0();
                this.m_nextsib_map0 = this.m_nextsib.getMap0();
                this.m_firstch_map0 = this.m_firstch.getMap0();
                this.m_parent_map0 = this.m_parent.getMap0();
                return;
            }
            i3++;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public final int _exptype(int i) {
        return this.m_exptype.elementAt(i);
    }

    public final int _exptype2(int i) {
        return i < this.m_blocksize ? this.m_exptype_map0[i] : this.m_exptype_map[i >>> this.m_SHIFT][this.m_MASK & i];
    }

    public final int _exptype2Type(int i) {
        if (-1 != i) {
            return this.m_extendedTypes[i].getNodeType();
        }
        return -1;
    }

    public final int _firstch2(int i) {
        return i < this.m_blocksize ? this.m_firstch_map0[i] : this.m_firstch_map[i >>> this.m_SHIFT][this.m_MASK & i];
    }

    public final int _nextsib2(int i) {
        return i < this.m_blocksize ? this.m_nextsib_map0[i] : this.m_nextsib_map[i >>> this.m_SHIFT][this.m_MASK & i];
    }

    public final int _parent2(int i) {
        return i < this.m_blocksize ? this.m_parent_map0[i] : this.m_parent_map[i >>> this.m_SHIFT][this.m_MASK & i];
    }

    public final int _type2(int i) {
        int i2 = i < this.m_blocksize ? this.m_exptype_map0[i] : this.m_exptype_map[i >>> this.m_SHIFT][i & this.m_MASK];
        if (-1 != i2) {
            return this.m_extendedTypes[i2].getNodeType();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM
    public final int addNode(int i, int i2, int i3, int i4, int i5, boolean z) {
        int i6 = this.m_size;
        this.m_size = i6 + 1;
        if (i6 == this.m_maxNodeIndex) {
            addNewDTMID(i6);
            this.m_maxNodeIndex += 65536;
        }
        this.m_firstch.addElement(-1);
        this.m_nextsib.addElement(-1);
        this.m_parent.addElement(i3);
        this.m_exptype.addElement(i2);
        this.m_dataOrQName.addElement(i5);
        SuballocatedIntVector suballocatedIntVector = this.m_prevsib;
        if (suballocatedIntVector != null) {
            suballocatedIntVector.addElement(i4);
        }
        if (this.m_locator != null && this.m_useSourceLocationProperty) {
            setSourceLocation();
        }
        if (i != 2) {
            if (i == 13) {
                declareNamespaceInContext(i3, i6);
            } else {
                if (-1 != i4) {
                    this.m_nextsib.setElementAt(i6, i4);
                    return i6;
                }
                if (-1 != i3) {
                    this.m_firstch.setElementAt(i6, i3);
                    return i6;
                }
            }
        }
        return i6;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM
    public final void charactersFlush() {
        SAX2DTM2 sax2dtm2;
        if (this.m_textPendingStart >= 0) {
            int size = this.m_chars.size() - this.m_textPendingStart;
            if (!(getShouldStripWhitespace() ? this.m_chars.isWhitespace(this.m_textPendingStart, size) : false)) {
                if (size > 0) {
                    if (size > 1023 || this.m_textPendingStart > TEXT_OFFSET_MAX) {
                        sax2dtm2 = this;
                        sax2dtm2.m_previous = sax2dtm2.addNode(sax2dtm2.m_coalescedTextType, 3, sax2dtm2.m_parents.peek(), sax2dtm2.m_previous, -sax2dtm2.m_data.size(), false);
                        sax2dtm2.m_data.addElement(sax2dtm2.m_textPendingStart);
                        sax2dtm2.m_data.addElement(size);
                    } else {
                        sax2dtm2 = this;
                        sax2dtm2.m_previous = addNode(this.m_coalescedTextType, 3, this.m_parents.peek(), this.m_previous, size + (this.m_textPendingStart << 10), false);
                    }
                }
                sax2dtm2.m_textPendingStart = -1;
                sax2dtm2.m_coalescedTextType = 3;
                sax2dtm2.m_textType = 3;
            }
            this.m_chars.setLength(this.m_textPendingStart);
            sax2dtm2 = this;
            sax2dtm2.m_textPendingStart = -1;
            sax2dtm2.m_coalescedTextType = 3;
            sax2dtm2.m_textType = 3;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_insideDTD) {
            return;
        }
        charactersFlush();
        this.m_values.add(new String(cArr, i, i2));
        int i3 = this.m_valueIndex;
        this.m_valueIndex = i3 + 1;
        this.m_previous = addNode(8, 8, this.m_parents.peek(), this.m_previous, i3, false);
    }

    public final void copyAttribute(int i, int i2, SerializationHandler serializationHandler) throws SAXException {
        String strIndexToString;
        ExtendedType extendedType = this.m_extendedTypes[i2];
        String namespace = extendedType.getNamespace();
        String localName = extendedType.getLocalName();
        int i_dataOrQName = _dataOrQName(i);
        String strSubstring = null;
        if (i_dataOrQName <= 0) {
            int i3 = -i_dataOrQName;
            int iElementAt = this.m_data.elementAt(i3);
            i_dataOrQName = this.m_data.elementAt(i3 + 1);
            strIndexToString = this.m_valuesOrPrefixes.indexToString(iElementAt);
            int iIndexOf = strIndexToString.indexOf(58);
            if (iIndexOf > 0) {
                strSubstring = strIndexToString.substring(0, iIndexOf);
            }
        } else {
            strIndexToString = null;
        }
        if (namespace.length() != 0) {
            serializationHandler.namespaceAfterStartElement(strSubstring, namespace);
        }
        serializationHandler.addAttribute(namespace, localName, strSubstring != null ? strIndexToString : localName, "CDATA", this.m_values.get(i_dataOrQName));
    }

    public final void copyAttributes(int i, SerializationHandler serializationHandler) throws SAXException {
        int firstAttributeIdentity = getFirstAttributeIdentity(i);
        while (firstAttributeIdentity != -1) {
            copyAttribute(firstAttributeIdentity, _exptype2(firstAttributeIdentity), serializationHandler);
            firstAttributeIdentity = getNextAttributeIdentity(firstAttributeIdentity);
        }
    }

    public final String copyElement(int i, int i2, SerializationHandler serializationHandler) throws SAXException {
        ExtendedType extendedType = this.m_extendedTypes[i2];
        String namespace = extendedType.getNamespace();
        String localName = extendedType.getLocalName();
        if (namespace.length() == 0) {
            serializationHandler.startElement(localName);
            return localName;
        }
        int iElementAt = this.m_dataOrQName.elementAt(i);
        if (iElementAt == 0) {
            serializationHandler.startElement(localName);
            serializationHandler.namespaceAfterStartElement("", namespace);
            return localName;
        }
        if (iElementAt < 0) {
            iElementAt = this.m_data.elementAt(-iElementAt);
        }
        String strIndexToString = this.m_valuesOrPrefixes.indexToString(iElementAt);
        serializationHandler.startElement(strIndexToString);
        int iIndexOf = strIndexToString.indexOf(58);
        serializationHandler.namespaceAfterStartElement(iIndexOf > 0 ? strIndexToString.substring(0, iIndexOf) : null, namespace);
        return strIndexToString;
    }

    public final void copyNS(int i, SerializationHandler serializationHandler, boolean z) throws SAXException {
        int nextNamespaceNode2;
        SuballocatedIntVector suballocatedIntVectorFindNamespaceContext;
        Vector<SuballocatedIntVector> vector;
        SuballocatedIntVector suballocatedIntVector = this.m_namespaceDeclSetElements;
        if (suballocatedIntVector == null || suballocatedIntVector.size() != 1 || (vector = this.m_namespaceDeclSets) == null || vector.get(0).size() != 1) {
            if (z) {
                suballocatedIntVectorFindNamespaceContext = findNamespaceContext(i);
                if (suballocatedIntVectorFindNamespaceContext == null || suballocatedIntVectorFindNamespaceContext.size() < 1) {
                    return;
                } else {
                    nextNamespaceNode2 = makeNodeIdentity(suballocatedIntVectorFindNamespaceContext.elementAt(0));
                }
            } else {
                nextNamespaceNode2 = getNextNamespaceNode2(i);
                suballocatedIntVectorFindNamespaceContext = null;
            }
            int i2 = 1;
            while (nextNamespaceNode2 != -1) {
                String localName = this.m_extendedTypes[_exptype2(nextNamespaceNode2)].getLocalName();
                int iElementAt = this.m_dataOrQName.elementAt(nextNamespaceNode2);
                if (iElementAt < 0) {
                    iElementAt = this.m_data.elementAt((-iElementAt) + 1);
                }
                serializationHandler.namespaceAfterStartElement(localName, this.m_values.get(iElementAt));
                if (!z) {
                    nextNamespaceNode2 = getNextNamespaceNode2(nextNamespaceNode2);
                } else {
                    if (i2 >= suballocatedIntVectorFindNamespaceContext.size()) {
                        return;
                    }
                    nextNamespaceNode2 = makeNodeIdentity(suballocatedIntVectorFindNamespaceContext.elementAt(i2));
                    i2++;
                }
            }
        }
    }

    public final void copyTextNode(int i, SerializationHandler serializationHandler) throws SAXException {
        if (i != -1) {
            int iElementAt = this.m_dataOrQName.elementAt(i);
            FastStringBuffer fastStringBuffer = this.m_chars;
            if (iElementAt >= 0) {
                fastStringBuffer.sendSAXcharacters(serializationHandler, iElementAt >>> 10, iElementAt & 1023);
            } else {
                int i2 = -iElementAt;
                fastStringBuffer.sendSAXcharacters(serializationHandler, this.m_data.elementAt(i2), this.m_data.elementAt(i2 + 1));
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public final void dispatchCharactersEvents(int i, ContentHandler contentHandler, boolean z) throws SAXException {
        int iElementAt;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity == -1) {
            return;
        }
        int i_type2 = _type2(iMakeNodeIdentity);
        int i2 = 0;
        if (i_type2 == 1 || i_type2 == 9) {
            int i_firstch2 = _firstch2(iMakeNodeIdentity);
            if (-1 != i_firstch2) {
                int iElementAt2 = -1;
                do {
                    int i_exptype2 = _exptype2(i_firstch2);
                    if (i_exptype2 == 3 || i_exptype2 == 4) {
                        int iElementAt3 = this.m_dataOrQName.elementAt(i_firstch2);
                        if (iElementAt3 >= 0) {
                            if (-1 == iElementAt2) {
                                iElementAt2 = iElementAt3 >>> 10;
                            }
                            iElementAt = iElementAt3 & 1023;
                        } else {
                            if (-1 == iElementAt2) {
                                iElementAt2 = this.m_data.elementAt(-iElementAt3);
                            }
                            iElementAt = this.m_data.elementAt((-iElementAt3) + 1);
                        }
                        i2 += iElementAt;
                    }
                    i_firstch2++;
                } while (_parent2(i_firstch2) >= iMakeNodeIdentity);
                if (i2 > 0) {
                    FastStringBuffer fastStringBuffer = this.m_chars;
                    if (z) {
                        fastStringBuffer.sendNormalizedSAXcharacters(contentHandler, iElementAt2, i2);
                        return;
                    } else {
                        fastStringBuffer.sendSAXcharacters(contentHandler, iElementAt2, i2);
                        return;
                    }
                }
                return;
            }
            return;
        }
        if (3 != i_type2 && 4 != i_type2) {
            int iElementAt4 = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
            if (iElementAt4 < 0) {
                iElementAt4 = this.m_data.elementAt((-iElementAt4) + 1);
            }
            String str = this.m_values.get(iElementAt4);
            if (z) {
                FastStringBuffer.sendNormalizedSAXcharacters(str.toCharArray(), 0, str.length(), contentHandler);
                return;
            } else {
                contentHandler.characters(str.toCharArray(), 0, str.length());
                return;
            }
        }
        int iElementAt5 = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
        if (iElementAt5 >= 0) {
            FastStringBuffer fastStringBuffer2 = this.m_chars;
            if (z) {
                fastStringBuffer2.sendNormalizedSAXcharacters(contentHandler, iElementAt5 >>> 10, iElementAt5 & 1023);
                return;
            } else {
                fastStringBuffer2.sendSAXcharacters(contentHandler, iElementAt5 >>> 10, iElementAt5 & 1023);
                return;
            }
        }
        FastStringBuffer fastStringBuffer3 = this.m_chars;
        if (z) {
            int i3 = -iElementAt5;
            fastStringBuffer3.sendNormalizedSAXcharacters(contentHandler, this.m_data.elementAt(i3), this.m_data.elementAt(i3 + 1));
        } else {
            int i4 = -iElementAt5;
            fastStringBuffer3.sendSAXcharacters(contentHandler, this.m_data.elementAt(i4), this.m_data.elementAt(i4 + 1));
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        super.endDocument();
        this.m_exptype.addElement(-1);
        this.m_parent.addElement(-1);
        this.m_nextsib.addElement(-1);
        this.m_firstch.addElement(-1);
        this.m_extendedTypes = this.m_expandedNameTable.getExtendedTypes();
        this.m_exptype_map = this.m_exptype.getMap();
        this.m_nextsib_map = this.m_nextsib.getMap();
        this.m_firstch_map = this.m_firstch.getMap();
        this.m_parent_map = this.m_parent.getMap();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        charactersFlush();
        this.m_contextIndexes.quickPop(1);
        int iPeek = this.m_contextIndexes.peek();
        if (iPeek != this.m_prefixMappings.size()) {
            this.m_prefixMappings.setSize(iPeek);
        }
        this.m_previous = this.m_parents.pop();
        popShouldStripWhitespace();
    }

    public final int getExpandedTypeID2(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity != -1) {
            return iMakeNodeIdentity < this.m_blocksize ? this.m_exptype_map0[iMakeNodeIdentity] : this.m_exptype_map[iMakeNodeIdentity >>> this.m_SHIFT][this.m_MASK & iMakeNodeIdentity];
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public final int getFirstAttribute(int i) {
        int i_type2;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity != -1 && 1 == _type2(iMakeNodeIdentity)) {
            do {
                iMakeNodeIdentity++;
                i_type2 = _type2(iMakeNodeIdentity);
                if (i_type2 == 2) {
                    return makeNodeHandle(iMakeNodeIdentity);
                }
            } while (13 == i_type2);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public int getFirstAttributeIdentity(int i) {
        int i_type2;
        if (i != -1 && 1 == _type2(i)) {
            do {
                i++;
                i_type2 = _type2(i);
                if (i_type2 == 2) {
                    return i;
                }
            } while (13 == i_type2);
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM
    public int getIdForNamespace(String str) {
        int iIndexOf = this.m_values.indexOf(str);
        if (iIndexOf >= 0) {
            return iIndexOf;
        }
        this.m_values.add(str);
        int i = this.m_valueIndex;
        this.m_valueIndex = i + 1;
        return i;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getLocalName(int i) {
        int i_exptype = _exptype(makeNodeIdentity(i));
        if (i_exptype != 7) {
            return this.m_expandedNameTable.getLocalName(i_exptype);
        }
        return this.m_valuesOrPrefixes.indexToString(this.m_data.elementAt(-_dataOrQName(makeNodeIdentity(i))));
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public int getNextAttributeIdentity(int i) {
        int i_type2;
        do {
            i++;
            i_type2 = _type2(i);
            if (i_type2 == 2) {
                return i;
            }
        } while (i_type2 == 13);
        return -1;
    }

    public final int getNextNamespaceNode2(int i) {
        int i_type2;
        do {
            i++;
            i_type2 = _type2(i);
        } while (i_type2 == 2);
        if (i_type2 == 13) {
            return i;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        ExtendedType extendedType = this.m_extendedTypes[_exptype2(iMakeNodeIdentity)];
        if (extendedType.getNamespace().length() != 0) {
            int iElementAt = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
            if (iElementAt == 0) {
                return extendedType.getLocalName();
            }
            if (iElementAt < 0) {
                iElementAt = this.m_data.elementAt(-iElementAt);
            }
            return this.m_valuesOrPrefixes.indexToString(iElementAt);
        }
        int nodeType = extendedType.getNodeType();
        String localName = extendedType.getLocalName();
        if (nodeType == 13) {
            return localName.length() == 0 ? "xmlns" : "xmlns:".concat(localName);
        }
        if (nodeType == 7) {
            return this.m_valuesOrPrefixes.indexToString(this.m_data.elementAt(-_dataOrQName(iMakeNodeIdentity)));
        }
        return localName.length() == 0 ? getFixedNames(nodeType) : localName;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public final String getNodeNameX(int i) {
        int iElementAt;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        int i_exptype2 = _exptype2(iMakeNodeIdentity);
        if (i_exptype2 == 7) {
            return this.m_valuesOrPrefixes.indexToString(this.m_data.elementAt(-_dataOrQName(iMakeNodeIdentity)));
        }
        ExtendedType extendedType = this.m_extendedTypes[i_exptype2];
        if (extendedType.getNamespace().length() != 0 && (iElementAt = this.m_dataOrQName.elementAt(iMakeNodeIdentity)) != 0) {
            if (iElementAt < 0) {
                iElementAt = this.m_data.elementAt(-iElementAt);
            }
            return this.m_valuesOrPrefixes.indexToString(iElementAt);
        }
        return extendedType.getLocalName();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeValue(int i) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        int i_type2 = _type2(iMakeNodeIdentity);
        if (i_type2 == 3 || i_type2 == 4) {
            int i_dataOrQName = _dataOrQName(iMakeNodeIdentity);
            FastStringBuffer fastStringBuffer = this.m_chars;
            if (i_dataOrQName > 0) {
                return fastStringBuffer.getString(i_dataOrQName >>> 10, i_dataOrQName & 1023);
            }
            int i2 = -i_dataOrQName;
            return fastStringBuffer.getString(this.m_data.elementAt(i2), this.m_data.elementAt(i2 + 1));
        }
        if (1 == i_type2 || 11 == i_type2 || 9 == i_type2) {
            return null;
        }
        int iElementAt = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
        if (iElementAt < 0) {
            iElementAt = this.m_data.elementAt((-iElementAt) + 1);
        }
        return this.m_values.get(iElementAt);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase, com.sun.org.apache.xml.internal.dtm.DTM
    public XMLString getStringValue(int i) {
        int iElementAt;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity == -1) {
            return EMPTY_XML_STR;
        }
        int i_type2 = _type2(iMakeNodeIdentity);
        if (i_type2 != 1 && i_type2 != 9) {
            if (3 != i_type2 && 4 != i_type2) {
                int iElementAt2 = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
                if (iElementAt2 < 0) {
                    iElementAt2 = this.m_data.elementAt((-iElementAt2) + 1);
                }
                XMLStringFactory xMLStringFactory = this.m_xstrf;
                List<String> list = this.m_values;
                return xMLStringFactory != null ? xMLStringFactory.newstr(list.get(iElementAt2)) : new XMLStringDefault(list.get(iElementAt2));
            }
            int iElementAt3 = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
            XMLStringFactory xMLStringFactory2 = this.m_xstrf;
            if (iElementAt3 >= 0) {
                FastStringBuffer fastStringBuffer = this.m_chars;
                return xMLStringFactory2 != null ? xMLStringFactory2.newstr(fastStringBuffer, iElementAt3 >>> 10, iElementAt3 & 1023) : new XMLStringDefault(fastStringBuffer.getString(iElementAt3 >>> 10, iElementAt3 & 1023));
            }
            FastStringBuffer fastStringBuffer2 = this.m_chars;
            if (xMLStringFactory2 != null) {
                int i2 = -iElementAt3;
                return xMLStringFactory2.newstr(fastStringBuffer2, this.m_data.elementAt(i2), this.m_data.elementAt(i2 + 1));
            }
            int i3 = -iElementAt3;
            return new XMLStringDefault(fastStringBuffer2.getString(this.m_data.elementAt(i3), this.m_data.elementAt(i3 + 1)));
        }
        int i_firstch2 = _firstch2(iMakeNodeIdentity);
        if (-1 == i_firstch2) {
            return EMPTY_XML_STR;
        }
        int i4 = 0;
        int iElementAt4 = -1;
        do {
            int i_exptype2 = _exptype2(i_firstch2);
            if (i_exptype2 == 3 || i_exptype2 == 4) {
                int iElementAt5 = this.m_dataOrQName.elementAt(i_firstch2);
                if (iElementAt5 >= 0) {
                    if (-1 == iElementAt4) {
                        iElementAt4 = iElementAt5 >>> 10;
                    }
                    iElementAt = iElementAt5 & 1023;
                } else {
                    if (-1 == iElementAt4) {
                        iElementAt4 = this.m_data.elementAt(-iElementAt5);
                    }
                    iElementAt = this.m_data.elementAt((-iElementAt5) + 1);
                }
                i4 += iElementAt;
            }
            i_firstch2++;
        } while (_parent2(i_firstch2) >= iMakeNodeIdentity);
        if (i4 <= 0) {
            return EMPTY_XML_STR;
        }
        XMLStringFactory xMLStringFactory3 = this.m_xstrf;
        FastStringBuffer fastStringBuffer3 = this.m_chars;
        return xMLStringFactory3 != null ? xMLStringFactory3.newstr(fastStringBuffer3, iElementAt4, i4) : new XMLStringDefault(fastStringBuffer3.getString(iElementAt4, i4));
    }

    public final String getStringValueX(int i) {
        int iElementAt;
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity == -1) {
            return "";
        }
        int i_type2 = _type2(iMakeNodeIdentity);
        if (i_type2 != 1 && i_type2 != 9) {
            if (3 != i_type2 && 4 != i_type2) {
                int iElementAt2 = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
                if (iElementAt2 < 0) {
                    iElementAt2 = this.m_data.elementAt((-iElementAt2) + 1);
                }
                return this.m_values.get(iElementAt2);
            }
            int iElementAt3 = this.m_dataOrQName.elementAt(iMakeNodeIdentity);
            FastStringBuffer fastStringBuffer = this.m_chars;
            if (iElementAt3 >= 0) {
                return fastStringBuffer.getString(iElementAt3 >>> 10, iElementAt3 & 1023);
            }
            int i2 = -iElementAt3;
            return fastStringBuffer.getString(this.m_data.elementAt(i2), this.m_data.elementAt(i2 + 1));
        }
        int i_firstch2 = _firstch2(iMakeNodeIdentity);
        if (-1 != i_firstch2) {
            int i3 = 0;
            int iElementAt4 = -1;
            do {
                int i_exptype2 = _exptype2(i_firstch2);
                if (i_exptype2 == 3 || i_exptype2 == 4) {
                    int iElementAt5 = this.m_dataOrQName.elementAt(i_firstch2);
                    if (iElementAt5 >= 0) {
                        if (-1 == iElementAt4) {
                            iElementAt4 = iElementAt5 >>> 10;
                        }
                        iElementAt = iElementAt5 & 1023;
                    } else {
                        if (-1 == iElementAt4) {
                            iElementAt4 = this.m_data.elementAt(-iElementAt5);
                        }
                        iElementAt = this.m_data.elementAt((-iElementAt5) + 1);
                    }
                    i3 += iElementAt;
                }
                i_firstch2++;
            } while (_parent2(i_firstch2) >= iMakeNodeIdentity);
            if (i3 > 0) {
                return this.m_chars.getString(iElementAt4, i3);
            }
        }
        return "";
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBase
    public final int getTypedAttribute(int i, int i2) {
        int iMakeNodeIdentity = makeNodeIdentity(i);
        if (iMakeNodeIdentity != -1 && 1 == _type2(iMakeNodeIdentity)) {
            while (true) {
                iMakeNodeIdentity++;
                int i_exptype2 = _exptype2(iMakeNodeIdentity);
                if (i_exptype2 == -1) {
                    break;
                }
                int nodeType = this.m_extendedTypes[i_exptype2].getNodeType();
                if (nodeType == 2) {
                    if (i_exptype2 == i2) {
                        return makeNodeHandle(iMakeNodeIdentity);
                    }
                } else if (13 != nodeType) {
                    break;
                }
            }
        }
        return -1;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        charactersFlush();
        this.m_previous = addNode(7, 7, this.m_parents.peek(), this.m_previous, -this.m_data.size(), false);
        this.m_data.addElement(this.m_valuesOrPrefixes.stringToIndex(str));
        this.m_values.add(str2);
        SuballocatedIntVector suballocatedIntVector = this.m_data;
        int i = this.m_valueIndex;
        this.m_valueIndex = i + 1;
        suballocatedIntVector.addElement(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.m_parents.push(addNode(9, 9, -1, -1, 0, true));
        this.m_previous = -1;
        this.m_contextIndexes.push(this.m_prefixMappings.size());
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        String strSubstring;
        charactersFlush();
        int i = 58;
        boolean shouldStripWhitespace = true;
        if ((str == null || str.isEmpty()) && (str2 == null || str2.isEmpty())) {
            int iLastIndexOf = str3.lastIndexOf(58);
            strSubstring = iLastIndexOf > -1 ? str3.substring(iLastIndexOf + 1) : str3;
        } else {
            strSubstring = str2;
        }
        int expandedTypeID = this.m_expandedNameTable.getExpandedTypeID(str, strSubstring, 1);
        int iAddNode = addNode(1, expandedTypeID, this.m_parents.peek(), this.m_previous, str3.length() != strSubstring.length() ? this.m_valuesOrPrefixes.stringToIndex(str3) : 0, true);
        if (this.m_indexing) {
            indexNode(expandedTypeID, iAddNode);
        }
        this.m_parents.push(iAddNode);
        int size = this.m_prefixMappings.size();
        if (!this.m_pastFirstElement) {
            int expandedTypeID2 = this.m_expandedNameTable.getExpandedTypeID(null, "xml", 13);
            this.m_values.add("http://www.w3.org/XML/1998/namespace");
            int i2 = this.m_valueIndex;
            this.m_valueIndex = i2 + 1;
            addNode(13, expandedTypeID2, iAddNode, -1, i2, false);
            this.m_pastFirstElement = true;
        }
        for (int iPeek = this.m_contextIndexes.peek(); iPeek < size; iPeek += 2) {
            String str4 = this.m_prefixMappings.get(iPeek);
            if (str4 != null) {
                String str5 = this.m_prefixMappings.get(iPeek + 1);
                int expandedTypeID3 = this.m_expandedNameTable.getExpandedTypeID(null, str4, 13);
                this.m_values.add(str5);
                int i3 = this.m_valueIndex;
                this.m_valueIndex = i3 + 1;
                addNode(13, expandedTypeID3, iAddNode, -1, i3, false);
            }
        }
        int length = attributes.getLength();
        int i4 = 0;
        while (true) {
            int i5 = 2;
            if (i4 >= length) {
                break;
            }
            String uri = attributes.getURI(i4);
            String localName = attributes.getLocalName(i4);
            String qName = attributes.getQName(i4);
            String value = attributes.getValue(i4);
            if (uri == null || uri.isEmpty()) {
                if (localName == null || localName.isEmpty()) {
                    int iLastIndexOf2 = qName.lastIndexOf(i);
                    localName = iLastIndexOf2 > -1 ? qName.substring(iLastIndexOf2 + 1) : qName;
                } else {
                    int iLastIndexOf3 = localName.lastIndexOf(i);
                    if (iLastIndexOf3 > -1) {
                        localName = localName.substring(iLastIndexOf3 + 1);
                    }
                }
            }
            if (qName != null && (qName.equals("xmlns") || qName.startsWith("xmlns:"))) {
                if (!declAlreadyDeclared(getPrefix(qName, uri))) {
                    i5 = 13;
                }
                i4++;
                i = 58;
            } else if (this.m_buildIdIndex && attributes.getType(i4).equalsIgnoreCase(SchemaSymbols.ATTVAL_ID)) {
                setIDAttribute(value, iAddNode);
            }
            if (value == null) {
                value = "";
            }
            this.m_values.add(value);
            int i6 = this.m_valueIndex;
            this.m_valueIndex = i6 + 1;
            if (localName.length() != qName.length()) {
                int iStringToIndex = this.m_valuesOrPrefixes.stringToIndex(qName);
                int size2 = this.m_data.size();
                this.m_data.addElement(iStringToIndex);
                this.m_data.addElement(i6);
                i6 = -size2;
            }
            addNode(i5, this.m_expandedNameTable.getExpandedTypeID(uri, localName, i5), iAddNode, -1, i6, false);
            i4++;
            i = 58;
        }
        DTMWSFilter dTMWSFilter = this.m_wsfilter;
        if (dTMWSFilter != null) {
            short shouldStripSpace = dTMWSFilter.getShouldStripSpace(makeNodeHandle(iAddNode), this);
            if (3 == shouldStripSpace) {
                shouldStripWhitespace = getShouldStripWhitespace();
            } else if (2 != shouldStripSpace) {
                shouldStripWhitespace = false;
            }
            pushShouldStripWhitespace(shouldStripWhitespace);
        }
        this.m_previous = -1;
        this.m_contextIndexes.push(this.m_prefixMappings.size());
    }

    public SAX2DTM2(DTMManager dTMManager, Source source, int i, DTMWSFilter dTMWSFilter, XMLStringFactory xMLStringFactory, boolean z) {
        this(dTMManager, source, i, dTMWSFilter, xMLStringFactory, z, 512, true, true, false);
    }

    public String getStringValue() {
        int i_firstch2 = _firstch2(0);
        if (i_firstch2 == -1) {
            return "";
        }
        if (_exptype2(i_firstch2) == 3 && _nextsib2(i_firstch2) == -1) {
            int iElementAt = this.m_dataOrQName.elementAt(i_firstch2);
            FastStringBuffer fastStringBuffer = this.m_chars;
            if (iElementAt >= 0) {
                return fastStringBuffer.getString(iElementAt >>> 10, iElementAt & 1023);
            }
            int i = -iElementAt;
            return fastStringBuffer.getString(this.m_data.elementAt(i), this.m_data.elementAt(i + 1));
        }
        return getStringValueX(getDocument());
    }
}
