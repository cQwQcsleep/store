package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import com.sun.org.apache.xerces.internal.util.IntStack;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathMatcher {
    protected static final boolean DEBUG_ALL = false;
    protected static final boolean DEBUG_ANY = false;
    protected static final boolean DEBUG_MATCH = false;
    protected static final boolean DEBUG_METHODS = false;
    protected static final boolean DEBUG_METHODS2 = false;
    protected static final boolean DEBUG_METHODS3 = false;
    protected static final boolean DEBUG_STACK = false;
    protected static final int MATCHED = 1;
    protected static final int MATCHED_ATTRIBUTE = 3;
    protected static final int MATCHED_DESCENDANT = 5;
    protected static final int MATCHED_DESCENDANT_PREVIOUS = 13;
    private final int[] fCurrentStep;
    private final XPath.LocationPath[] fLocationPaths;
    private final int[] fMatched;
    protected Object fMatchedString;
    private final int[] fNoMatchDepth;
    final QName fQName = new QName();
    private final IntStack[] fStepIndexes;

    public XPathMatcher(XPath xPath) {
        XPath.LocationPath[] locationPaths = xPath.getLocationPaths();
        this.fLocationPaths = locationPaths;
        this.fStepIndexes = new IntStack[locationPaths.length];
        int i = 0;
        while (true) {
            IntStack[] intStackArr = this.fStepIndexes;
            if (i >= intStackArr.length) {
                XPath.LocationPath[] locationPathArr = this.fLocationPaths;
                this.fCurrentStep = new int[locationPathArr.length];
                this.fNoMatchDepth = new int[locationPathArr.length];
                this.fMatched = new int[locationPathArr.length];
                return;
            }
            intStackArr[i] = new IntStack();
            i++;
        }
    }

    private static boolean matches(XPath.NodeTest nodeTest, QName qName) {
        short s = nodeTest.type;
        if (s == 1) {
            return nodeTest.name.equals(qName);
        }
        return s != 4 || nodeTest.name.uri == qName.uri;
    }

    private String normalize(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\n') {
                stringBuffer.append(cCharAt);
            } else {
                stringBuffer.append("\\n");
            }
        }
        return stringBuffer.toString();
    }

    public void endElement(QName qName, XSTypeDefinition xSTypeDefinition, boolean z, Object obj, short s, ShortList shortList) {
        int[] iArr;
        int i;
        for (int i2 = 0; i2 < this.fLocationPaths.length; i2++) {
            this.fCurrentStep[i2] = this.fStepIndexes[i2].pop();
            int[] iArr2 = this.fNoMatchDepth;
            int i3 = iArr2[i2];
            if (i3 > 0) {
                iArr2[i2] = i3 - 1;
            } else {
                int i4 = 0;
                while (i4 < i2 && (this.fMatched[i4] & 1) != 1) {
                    i4++;
                }
                if (i4 >= i2 && (i = (iArr = this.fMatched)[i4]) != 0) {
                    if ((i & 3) == 3) {
                        iArr[i2] = 0;
                    } else {
                        handleContent(xSTypeDefinition, z, obj, s, shortList);
                        this.fMatched[i2] = 0;
                    }
                }
            }
        }
    }

    public void handleContent(XSTypeDefinition xSTypeDefinition, boolean z, Object obj, short s, ShortList shortList) {
    }

    public boolean isMatched() {
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            int i2 = this.fMatched[i];
            if ((i2 & 1) == 1 && (i2 & 13) != 13 && (this.fNoMatchDepth[i] == 0 || (i2 & 5) == 5)) {
                return true;
            }
        }
        return false;
    }

    public void matched(Object obj, short s, ShortList shortList, boolean z) {
    }

    public void startDocumentFragment() {
        this.fMatchedString = null;
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            this.fStepIndexes[i].clear();
            this.fCurrentStep[i] = 0;
            this.fNoMatchDepth[i] = 0;
            this.fMatched[i] = 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:46:0x00a9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:49:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:51:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:86:0x014c A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    public void startElement(QName qName, XMLAttributes xMLAttributes) {
        int i;
        int i2;
        int[] iArr;
        int i3;
        for (int i4 = 0; i4 < this.fLocationPaths.length; i4++) {
            int i5 = this.fCurrentStep[i4];
            this.fStepIndexes[i4].push(i5);
            int[] iArr2 = this.fMatched;
            int i6 = iArr2[i4];
            if ((i6 & 5) == 1 || this.fNoMatchDepth[i4] > 0) {
                int[] iArr3 = this.fNoMatchDepth;
                iArr3[i4] = iArr3[i4] + 1;
            } else {
                if ((i6 & 5) == 5) {
                    iArr2[i4] = 13;
                }
                XPath.Step[] stepArr = this.fLocationPaths[i4].steps;
                while (true) {
                    int[] iArr4 = this.fCurrentStep;
                    i = iArr4[i4];
                    if (i >= stepArr.length || stepArr[i].axis.type != 3) {
                        break;
                    } else {
                        iArr4[i4] = i + 1;
                    }
                }
                if (i == stepArr.length) {
                    this.fMatched[i4] = 1;
                } else {
                    while (true) {
                        int[] iArr5 = this.fCurrentStep;
                        i2 = iArr5[i4];
                        if (i2 >= stepArr.length || stepArr[i2].axis.type != 4) {
                            break;
                        } else {
                            iArr5[i4] = i2 + 1;
                        }
                    }
                    boolean z = i2 > i;
                    if (i2 == stepArr.length) {
                        int[] iArr6 = this.fNoMatchDepth;
                        iArr6[i4] = iArr6[i4] + 1;
                    } else if (i2 == i5 || i2 > i) {
                        XPath.Step step = stepArr[i2];
                        if (step.axis.type == 1) {
                            boolean zMatches = matches(step.nodeTest, qName);
                            int[] iArr7 = this.fCurrentStep;
                            if (zMatches) {
                                iArr7[i4] = iArr7[i4] + 1;
                                iArr = this.fCurrentStep;
                                i3 = iArr[i4];
                                if (i3 == stepArr.length) {
                                    if (z) {
                                        iArr[i4] = i;
                                        this.fMatched[i4] = 5;
                                    } else {
                                        this.fMatched[i4] = 1;
                                    }
                                } else if (i3 >= stepArr.length && stepArr[i3].axis.type == 2) {
                                    int length = xMLAttributes.getLength();
                                    if (length > 0) {
                                        XPath.NodeTest nodeTest = stepArr[this.fCurrentStep[i4]].nodeTest;
                                        for (int i7 = 0; i7 < length; i7++) {
                                            xMLAttributes.getName(i7, this.fQName);
                                            if (matches(nodeTest, this.fQName)) {
                                                int[] iArr8 = this.fCurrentStep;
                                                int i8 = iArr8[i4] + 1;
                                                iArr8[i4] = i8;
                                                if (i8 != stepArr.length) {
                                                    break;
                                                }
                                                this.fMatched[i4] = 3;
                                                int i9 = 0;
                                                while (i9 < i4 && (this.fMatched[i9] & 1) != 1) {
                                                    i9++;
                                                }
                                                if (i9 != i4) {
                                                    break;
                                                }
                                                AttributePSVI attributePSVI = (AttributePSVI) xMLAttributes.getAugmentations(i7).getItem(Constants.ATTRIBUTE_PSVI);
                                                Object actualValue = attributePSVI.getSchemaValue().getActualValue();
                                                this.fMatchedString = actualValue;
                                                matched(actualValue, attributePSVI.getSchemaValue().getActualValueType(), attributePSVI.getSchemaValue().getListValueTypes(), false);
                                                break;
                                            }
                                        }
                                    }
                                    if ((this.fMatched[i4] & 1) != 1) {
                                        int[] iArr9 = this.fCurrentStep;
                                        if (iArr9[i4] > i) {
                                            iArr9[i4] = i;
                                        } else {
                                            int[] iArr10 = this.fNoMatchDepth;
                                            iArr10[i4] = iArr10[i4] + 1;
                                        }
                                    }
                                }
                            } else if (iArr7[i4] > i) {
                                iArr7[i4] = i;
                            } else {
                                int[] iArr11 = this.fNoMatchDepth;
                                iArr11[i4] = iArr11[i4] + 1;
                            }
                        } else {
                            iArr = this.fCurrentStep;
                            i3 = iArr[i4];
                            if (i3 == stepArr.length) {
                                if (z) {
                                    iArr[i4] = i;
                                    this.fMatched[i4] = 5;
                                } else {
                                    this.fMatched[i4] = 1;
                                }
                            } else if (i3 >= stepArr.length) {
                            }
                        }
                    } else {
                        iArr = this.fCurrentStep;
                        i3 = iArr[i4];
                        if (i3 == stepArr.length) {
                            if (z) {
                                iArr[i4] = i;
                                this.fMatched[i4] = 5;
                            } else {
                                this.fMatched[i4] = 1;
                            }
                        } else if (i3 >= stepArr.length) {
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        int[] iArr;
        StringBuffer stringBuffer = new StringBuffer();
        String string = super.toString();
        int iLastIndexOf = string.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            string = string.substring(iLastIndexOf + 1);
        }
        stringBuffer.append(string);
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            stringBuffer.append('[');
            XPath.Step[] stepArr = this.fLocationPaths[i].steps;
            int i2 = 0;
            while (true) {
                int length = stepArr.length;
                iArr = this.fCurrentStep;
                if (i2 >= length) {
                    break;
                }
                if (i2 == iArr[i]) {
                    stringBuffer.append('^');
                }
                stringBuffer.append(stepArr[i2].toString());
                if (i2 < stepArr.length - 1) {
                    stringBuffer.append('/');
                }
                i2++;
            }
            if (iArr[i] == stepArr.length) {
                stringBuffer.append('^');
            }
            stringBuffer.append("],");
        }
        return stringBuffer.toString();
    }
}
