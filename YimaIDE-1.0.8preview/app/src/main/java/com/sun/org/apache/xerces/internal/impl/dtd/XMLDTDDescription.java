package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDTDDescription extends XMLResourceIdentifierImpl implements com.sun.org.apache.xerces.internal.xni.grammars.XMLDTDDescription {
    protected List<String> fPossibleRoots;
    protected String fRootName;

    public XMLDTDDescription(XMLResourceIdentifier xMLResourceIdentifier, String str) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        setValues(xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId(), xMLResourceIdentifier.getExpandedSystemId());
        this.fRootName = str;
        this.fPossibleRoots = null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XMLGrammarDescription) || !getGrammarType().equals(((XMLGrammarDescription) obj).getGrammarType())) {
            return false;
        }
        XMLDTDDescription xMLDTDDescription = (XMLDTDDescription) obj;
        String str = this.fRootName;
        if (str != null) {
            String str2 = xMLDTDDescription.fRootName;
            if (str2 != null && !str2.equals(str)) {
                return false;
            }
            List<String> list = xMLDTDDescription.fPossibleRoots;
            if (list != null && !list.contains(this.fRootName)) {
                return false;
            }
        } else {
            List<String> list2 = this.fPossibleRoots;
            if (list2 != null) {
                String str3 = xMLDTDDescription.fRootName;
                if (str3 != null) {
                    if (!list2.contains(str3)) {
                        return false;
                    }
                } else {
                    if (xMLDTDDescription.fPossibleRoots == null) {
                        return false;
                    }
                    Iterator<String> it = list2.iterator();
                    boolean zContains = false;
                    while (it.hasNext()) {
                        zContains = xMLDTDDescription.fPossibleRoots.contains(it.next());
                        if (zContains) {
                            break;
                        }
                    }
                    if (!zContains) {
                        return false;
                    }
                }
            }
        }
        String str4 = this.fExpandedSystemId;
        String str5 = xMLDTDDescription.fExpandedSystemId;
        if (str4 != null) {
            if (!str4.equals(str5)) {
                return false;
            }
        } else if (str5 != null) {
            return false;
        }
        String str6 = this.fPublicId;
        String str7 = xMLDTDDescription.fPublicId;
        if (str6 != null) {
            return str6.equals(str7);
        }
        return str7 == null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription
    public String getGrammarType() {
        return "http://www.w3.org/TR/REC-xml";
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLDTDDescription
    public String getRootName() {
        return this.fRootName;
    }

    @Override // com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl
    public int hashCode() {
        String str = this.fExpandedSystemId;
        if (str != null) {
            return str.hashCode();
        }
        String str2 = this.fPublicId;
        if (str2 != null) {
            return str2.hashCode();
        }
        return 0;
    }

    public void setPossibleRoots(List<String> list) {
        this.fPossibleRoots = list;
    }

    public void setRootName(String str) {
        this.fRootName = str;
        this.fPossibleRoots = null;
    }

    public XMLDTDDescription(String str, String str2, String str3, String str4, String str5) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        setValues(str, str2, str3, str4);
        this.fRootName = str5;
        this.fPossibleRoots = null;
    }

    public XMLDTDDescription(XMLInputSource xMLInputSource) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        setValues(xMLInputSource.getPublicId(), null, xMLInputSource.getBaseSystemId(), xMLInputSource.getSystemId());
        this.fRootName = null;
        this.fPossibleRoots = null;
    }
}
