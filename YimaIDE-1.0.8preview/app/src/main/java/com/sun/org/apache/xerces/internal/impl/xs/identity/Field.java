package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.impl.xpath.XPathException;
import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSComplexTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Field {
    protected final IdentityConstraint fIdentityConstraint;
    protected final XPath fXPath;

    public static class XPath extends com.sun.org.apache.xerces.internal.impl.xpath.XPath {
        public XPath(String str, SymbolTable symbolTable, NamespaceContext namespaceContext) throws XPathException {
            super(fixupXPath(str), symbolTable, namespaceContext);
            for (int i = 0; i < this.fLocationPaths.length; i++) {
                int i2 = 0;
                while (true) {
                    com.sun.org.apache.xerces.internal.impl.xpath.XPath.Step[] stepArr = this.fLocationPaths[i].steps;
                    if (i2 < stepArr.length) {
                        if (stepArr[i2].axis.type == 2 && i2 < stepArr.length - 1) {
                            throw new XPathException("c-fields-xpaths");
                        }
                        i2++;
                    }
                }
            }
        }

        private static String fixupXPath(String str) {
            int length = str.length();
            boolean z = true;
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (z) {
                    if (XMLChar.isSpace(cCharAt)) {
                        continue;
                    } else if (cCharAt == '.' || cCharAt == '/') {
                        z = false;
                    } else if (cCharAt != '|') {
                        return fixupXPath2(str, i, length);
                    }
                } else if (cCharAt == '|') {
                    z = true;
                }
            }
            return str;
        }

        private static String fixupXPath2(String str, int i, int i2) {
            StringBuffer stringBuffer = new StringBuffer(i2 + 2);
            for (int i3 = 0; i3 < i; i3++) {
                stringBuffer.append(str.charAt(i3));
            }
            stringBuffer.append("./");
            boolean z = false;
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (z) {
                    if (!XMLChar.isSpace(cCharAt)) {
                        if (cCharAt == '.' || cCharAt == '/') {
                            z = false;
                        } else if (cCharAt != '|') {
                            stringBuffer.append("./");
                            z = false;
                        }
                    }
                } else if (cCharAt == '|') {
                    z = true;
                }
                stringBuffer.append(cCharAt);
                i++;
            }
            return stringBuffer.toString();
        }
    }

    public Field(XPath xPath, IdentityConstraint identityConstraint) {
        this.fXPath = xPath;
        this.fIdentityConstraint = identityConstraint;
    }

    public XPathMatcher createMatcher(ValueStore valueStore) {
        return new Matcher(this.fXPath, valueStore);
    }

    public IdentityConstraint getIdentityConstraint() {
        return this.fIdentityConstraint;
    }

    public com.sun.org.apache.xerces.internal.impl.xpath.XPath getXPath() {
        return this.fXPath;
    }

    public String toString() {
        return this.fXPath.toString();
    }

    public class Matcher extends XPathMatcher {
        protected boolean fMayMatch;
        protected final ValueStore fStore;

        public Matcher(XPath xPath, ValueStore valueStore) {
            super(xPath);
            this.fMayMatch = true;
            this.fStore = valueStore;
        }

        private ShortList convertToPrimitiveKind(ShortList shortList) {
            if (shortList != null) {
                int length = shortList.getLength();
                int i = 0;
                while (i < length) {
                    short sItem = shortList.item(i);
                    if (sItem != convertToPrimitiveKind(sItem)) {
                        break;
                    }
                    i++;
                }
                if (i != length) {
                    short[] sArr = new short[length];
                    for (int i2 = 0; i2 < i; i2++) {
                        sArr[i2] = shortList.item(i2);
                    }
                    while (i < length) {
                        sArr[i] = convertToPrimitiveKind(shortList.item(i));
                        i++;
                    }
                    return new ShortListImpl(sArr, length);
                }
            }
            return shortList;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.XPathMatcher
        public void handleContent(XSTypeDefinition xSTypeDefinition, boolean z, Object obj, short s, ShortList shortList) {
            if (xSTypeDefinition == null || (xSTypeDefinition.getTypeCategory() == 15 && ((XSComplexTypeDefinition) xSTypeDefinition).getContentType() != 1)) {
                this.fStore.reportError("cvc-id.3", new Object[]{Field.this.fIdentityConstraint.getName(), Field.this.fIdentityConstraint.getElementName()});
            }
            this.fMatchedString = obj;
            matched(obj, s, shortList, z);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.XPathMatcher
        public void matched(Object obj, short s, ShortList shortList, boolean z) {
            super.matched(obj, s, shortList, z);
            if (z && Field.this.fIdentityConstraint.getCategory() == 1) {
                this.fStore.reportError("KeyMatchesNillable", new Object[]{Field.this.fIdentityConstraint.getElementName(), Field.this.fIdentityConstraint.getIdentityConstraintName()});
            }
            this.fStore.addValue(Field.this, this.fMayMatch, obj, convertToPrimitiveKind(s), convertToPrimitiveKind(shortList));
            this.fMayMatch = false;
        }

        private short convertToPrimitiveKind(short s) {
            if (s > 20) {
                if (s <= 29) {
                    return (short) 2;
                }
                if (s <= 42) {
                    return (short) 4;
                }
            }
            return s;
        }
    }
}
