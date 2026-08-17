package com.sun.org.apache.xpath.internal.compiler;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xml.internal.utils.ObjectVector;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class OpMap {
    static final int BLOCKTOKENQUEUESIZE = 500;
    public static final int MAPINDEX_LENGTH = 1;
    static final int MAXTOKENQUEUESIZE = 500;
    protected String m_currentPattern;
    ObjectVector m_tokenQueue = new ObjectVector(WinError.ERROR_USER_PROFILE_LOAD, WinError.ERROR_USER_PROFILE_LOAD);
    OpMapVector m_opMap = null;

    public static int getFirstChildPos(int i) {
        return i + 2;
    }

    public static int getFirstChildPosOfStep(int i) {
        return i + 3;
    }

    public void error(String str, Object[] objArr) throws TransformerException {
        throw new TransformerException(XPATHMessages.createXPATHMessage(str, objArr));
    }

    public int getArgLength(int i) {
        return this.m_opMap.elementAt(i + 1);
    }

    public int getArgLengthOfStep(int i) {
        return this.m_opMap.elementAt(i + 2) - 3;
    }

    public int getFirstPredicateOpPos(int i) throws TransformerException {
        int iElementAt;
        int iElementAt2 = this.m_opMap.elementAt(i);
        if (iElementAt2 >= 37 && iElementAt2 <= 53) {
            iElementAt = this.m_opMap.elementAt(i + 2);
        } else {
            if (iElementAt2 < 22 || iElementAt2 > 25) {
                if (-2 == iElementAt2) {
                    return -2;
                }
                error("ER_UNKNOWN_OPCODE", new Object[]{String.valueOf(iElementAt2)});
                return -1;
            }
            iElementAt = this.m_opMap.elementAt(i + 1);
        }
        return i + iElementAt;
    }

    public int getNextOpPos(int i) {
        return i + this.m_opMap.elementAt(i + 1);
    }

    public int getNextStepPos(int i) {
        int op = getOp(i);
        if (op >= 37 && op <= 53) {
            return getNextOpPos(i);
        }
        if (op < 22 || op > 25) {
            f63.a(XPATHMessages.createXPATHMessage("ER_UNKNOWN_STEP", new Object[]{String.valueOf(op)}));
            return 0;
        }
        int nextOpPos = getNextOpPos(i);
        while (29 == getOp(nextOpPos)) {
            nextOpPos = getNextOpPos(nextOpPos);
        }
        int op2 = getOp(nextOpPos);
        if (op2 < 37 || op2 > 53) {
            return -1;
        }
        return nextOpPos;
    }

    public int getOp(int i) {
        return this.m_opMap.elementAt(i);
    }

    public OpMapVector getOpMap() {
        return this.m_opMap;
    }

    public String getPatternString() {
        return this.m_currentPattern;
    }

    public String getStepLocalName(int i) {
        int argLengthOfStep = getArgLengthOfStep(i);
        int iElementAt = -2;
        if (argLengthOfStep != 0) {
            if (argLengthOfStep == 1) {
                iElementAt = -3;
            } else if (argLengthOfStep == 2) {
                iElementAt = this.m_opMap.elementAt(i + 4);
            } else if (argLengthOfStep == 3) {
                iElementAt = this.m_opMap.elementAt(i + 5);
            }
        }
        if (iElementAt >= 0) {
            return this.m_tokenQueue.elementAt(iElementAt).toString();
        }
        if (-3 == iElementAt) {
            return "*";
        }
        return null;
    }

    public String getStepNS(int i) {
        if (getArgLengthOfStep(i) == 3) {
            int iElementAt = this.m_opMap.elementAt(i + 4);
            if (iElementAt >= 0) {
                return (String) this.m_tokenQueue.elementAt(iElementAt);
            }
            if (-3 == iElementAt) {
                return "*";
            }
        }
        return null;
    }

    public int getStepTestType(int i) {
        return this.m_opMap.elementAt(i + 3);
    }

    public Object getToken(int i) {
        return this.m_tokenQueue.elementAt(i);
    }

    public ObjectVector getTokenQueue() {
        return this.m_tokenQueue;
    }

    public int getTokenQueueSize() {
        return this.m_tokenQueue.size();
    }

    public void setOp(int i, int i2) {
        this.m_opMap.setElementAt(i2, i);
    }

    public void shrink() {
        int iElementAt = this.m_opMap.elementAt(1);
        this.m_opMap.setToSize(iElementAt + 4);
        this.m_opMap.setElementAt(0, iElementAt);
        this.m_opMap.setElementAt(0, iElementAt + 1);
        this.m_opMap.setElementAt(0, iElementAt + 2);
        int size = this.m_tokenQueue.size();
        this.m_tokenQueue.setToSize(size + 4);
        this.m_tokenQueue.setElementAt(null, size);
        this.m_tokenQueue.setElementAt(null, size + 1);
        this.m_tokenQueue.setElementAt(null, size + 2);
    }

    public String toString() {
        return this.m_currentPattern;
    }

    public static int getNextOpPos(int[] iArr, int i) {
        return i + iArr[i + 1];
    }
}
