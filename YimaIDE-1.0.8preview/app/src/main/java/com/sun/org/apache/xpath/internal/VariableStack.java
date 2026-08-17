package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VariableStack implements Cloneable {
    public static final int CLEARLIMITATION = 1024;
    private static XObject[] m_nulls = new XObject[1024];
    private int _currentFrameBottom;
    int _frameTop;
    int _linksTop;
    XObject[] _stackFrames = new XObject[8192];
    int[] _links = new int[4096];

    public VariableStack() {
        reset();
    }

    public void clearLocalSlots(int i, int i2) {
        System.arraycopy(m_nulls, 0, this._stackFrames, i + this._currentFrameBottom, i2);
    }

    public synchronized Object clone() throws CloneNotSupportedException {
        VariableStack variableStack;
        variableStack = (VariableStack) super.clone();
        variableStack._stackFrames = (XObject[]) this._stackFrames.clone();
        variableStack._links = (int[]) this._links.clone();
        return variableStack;
    }

    public XObject elementAt(int i) {
        return this._stackFrames[i];
    }

    public XObject getGlobalVariable(XPathContext xPathContext, int i, boolean z) throws TransformerException {
        XObject xObject = this._stackFrames[i];
        if (xObject.getType() != 600) {
            return z ? xObject : xObject.getFresh();
        }
        XObject[] xObjectArr = this._stackFrames;
        XObject xObjectExecute = xObject.execute(xPathContext);
        xObjectArr[i] = xObjectExecute;
        return xObjectExecute;
    }

    public XObject getLocalVariable(XPathContext xPathContext, int i, boolean z) throws TransformerException {
        int i2 = i + this._currentFrameBottom;
        XObject xObject = this._stackFrames[i2];
        if (xObject == null) {
            throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VARIABLE_ACCESSED_BEFORE_BIND", null), xPathContext.getSAXLocator());
        }
        if (xObject.getType() != 600) {
            return z ? xObject : xObject.getFresh();
        }
        XObject[] xObjectArr = this._stackFrames;
        XObject xObjectExecute = xObject.execute(xPathContext);
        xObjectArr[i2] = xObjectExecute;
        return xObjectExecute;
    }

    public int getStackFrame() {
        return this._currentFrameBottom;
    }

    public XObject getVariableOrParam(XPathContext xPathContext, QName qName) throws TransformerException {
        throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VAR_NOT_RESOLVABLE", new Object[]{qName.toString()}));
    }

    public boolean isLocalSet(int i) throws TransformerException {
        return this._stackFrames[i + this._currentFrameBottom] != null;
    }

    public int link(int i) {
        int i2 = this._frameTop;
        this._currentFrameBottom = i2;
        int i3 = i2 + i;
        this._frameTop = i3;
        XObject[] xObjectArr = this._stackFrames;
        if (i3 >= xObjectArr.length) {
            XObject[] xObjectArr2 = new XObject[xObjectArr.length + 4096 + i];
            System.arraycopy(xObjectArr, 0, xObjectArr2, 0, xObjectArr.length);
            this._stackFrames = xObjectArr2;
        }
        int i4 = this._linksTop + 1;
        int[] iArr = this._links;
        if (i4 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 2048];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this._links = iArr2;
        }
        int[] iArr3 = this._links;
        int i5 = this._linksTop;
        this._linksTop = i5 + 1;
        int i6 = this._currentFrameBottom;
        iArr3[i5] = i6;
        return i6;
    }

    public void reset() {
        this._frameTop = 0;
        int[] iArr = this._links;
        this._linksTop = 1;
        iArr[0] = 0;
        this._stackFrames = new XObject[this._stackFrames.length];
    }

    public void setGlobalVariable(int i, XObject xObject) {
        this._stackFrames[i] = xObject;
    }

    public void setLocalVariable(int i, XObject xObject) {
        this._stackFrames[i + this._currentFrameBottom] = xObject;
    }

    public void setStackFrame(int i) {
        this._currentFrameBottom = i;
    }

    public int size() {
        return this._frameTop;
    }

    public void unlink() {
        int[] iArr = this._links;
        int i = this._linksTop;
        int i2 = i - 1;
        this._linksTop = i2;
        this._frameTop = iArr[i2];
        this._currentFrameBottom = iArr[i - 2];
    }

    public void setLocalVariable(int i, XObject xObject, int i2) {
        this._stackFrames[i + i2] = xObject;
    }

    public void unlink(int i) {
        int[] iArr = this._links;
        int i2 = this._linksTop - 1;
        this._linksTop = i2;
        this._frameTop = iArr[i2];
        this._currentFrameBottom = i;
    }

    public XObject getGlobalVariable(XPathContext xPathContext, int i) throws TransformerException {
        XObject xObject = this._stackFrames[i];
        if (xObject.getType() != 600) {
            return xObject;
        }
        XObject[] xObjectArr = this._stackFrames;
        XObject xObjectExecute = xObject.execute(xPathContext);
        xObjectArr[i] = xObjectExecute;
        return xObjectExecute;
    }

    public XObject getLocalVariable(int i, int i2) throws TransformerException {
        return this._stackFrames[i + i2];
    }

    public XObject getLocalVariable(XPathContext xPathContext, int i) throws TransformerException {
        int i2 = i + this._currentFrameBottom;
        XObject xObject = this._stackFrames[i2];
        if (xObject != null) {
            if (xObject.getType() != 600) {
                return xObject;
            }
            XObject[] xObjectArr = this._stackFrames;
            XObject xObjectExecute = xObject.execute(xPathContext);
            xObjectArr[i2] = xObjectExecute;
            return xObjectExecute;
        }
        throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VARIABLE_ACCESSED_BEFORE_BIND", null), xPathContext.getSAXLocator());
    }
}
