package com.reandroid.arsc.item;

import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.utils.CompareUtil;
import com.reandroid.xml.StyleDocument;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlString extends StringItem {
    private ResXmlID mResXmlID;
    private ResXmlString namespacePrefix;

    public ResXmlString(boolean z) {
        super(z);
    }

    private String getNamespacePrefixString() {
        ResXmlString namespacePrefix = getNamespacePrefix();
        if (namespacePrefix != null) {
            return namespacePrefix.getXml();
        }
        return null;
    }

    @Override // com.reandroid.arsc.item.StringItem, java.lang.Comparable
    public int compareTo(StringItem stringItem) {
        if (!(stringItem instanceof ResXmlString)) {
            return -1;
        }
        if (stringItem == this) {
            return 0;
        }
        ResXmlString resXmlString = (ResXmlString) stringItem;
        int resourceId = getResourceId();
        int resourceId2 = resXmlString.getResourceId();
        int iCompare = CompareUtil.compare(resourceId == 0, resourceId2 == 0);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompareUnsigned = CompareUtil.compareUnsigned(resourceId, resourceId2);
        if (iCompareUnsigned != 0) {
            return iCompareUnsigned;
        }
        int iCompare2 = CompareUtil.compare(resXmlString.hasStyle(), hasStyle());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompare3 = CompareUtil.compare(getXml(), stringItem.getXml());
        if (iCompare3 != 0) {
            return iCompare3;
        }
        int iCompare4 = CompareUtil.compare(resXmlString.hasNamespacePrefix(), hasNamespacePrefix());
        if (iCompare4 != 0) {
            return iCompare4;
        }
        int iCompareReferences = compareReferences(resXmlString);
        if (iCompareReferences != 0) {
            return iCompareReferences;
        }
        int iCompare5 = CompareUtil.compare(getNamespacePrefixString(), resXmlString.getNamespacePrefixString());
        if (iCompare5 != 0) {
            return iCompare5;
        }
        return 0;
    }

    @Override // com.reandroid.arsc.item.StringItem
    public void ensureStringLinkUnlocked() {
    }

    public boolean equalsNamespace(String str, String str2) {
        if (str == null || str2 == null || !str.equals(getXml())) {
            return false;
        }
        return str2.equals(getNamespacePrefixString());
    }

    @Override // com.reandroid.arsc.item.StringItem
    public boolean equalsValue(String str) {
        return !hasResourceId() && super.equalsValue(str);
    }

    public ResXmlString getNamespacePrefix() {
        ResXmlString resXmlString = this.namespacePrefix;
        if (resXmlString == null || resXmlString.getParent() != null) {
            return resXmlString;
        }
        this.namespacePrefix = null;
        return null;
    }

    public ResXmlID getOrCreateResXmlID() {
        ResXmlID resXmlID = getResXmlID();
        if (resXmlID != null) {
            return resXmlID;
        }
        linkResourceIdInternal((ResXmlID) ((ResXmlDocument) getParentInstance(ResXmlDocument.class)).getResXmlIDMap().getResXmlIDArray().createNext());
        return getResXmlID();
    }

    @Override // com.reandroid.arsc.item.StringItem
    public int getReferencesSize() {
        int referencesSize = super.getReferencesSize();
        ResXmlID resXmlID = getResXmlID();
        return (resXmlID == null || !resXmlID.hasReference()) ? referencesSize : referencesSize + 1;
    }

    public ResXmlID getResXmlID() {
        return this.mResXmlID;
    }

    public int getResourceId() {
        ResXmlID resXmlID = getResXmlID();
        if (resXmlID == null) {
            return 0;
        }
        return resXmlID.get();
    }

    public boolean hasNamespacePrefix() {
        return getNamespacePrefix() != null;
    }

    @Override // com.reandroid.arsc.item.StringItem
    public boolean hasReference() {
        if (super.hasReference()) {
            return true;
        }
        ResXmlID resXmlID = getResXmlID();
        if (resXmlID != null) {
            return resXmlID.hasReference();
        }
        return false;
    }

    public boolean hasResourceId() {
        return getResourceId() != 0;
    }

    public void linkNamespacePrefixInternal(ResXmlString resXmlString) {
        if (resXmlString == this) {
            w01.a("Cyclic link of namespace prefix");
            return;
        }
        if (resXmlString == null) {
            x0e.a("Can not link null namespace prefix");
            return;
        }
        ResXmlString namespacePrefix = getNamespacePrefix();
        if (namespacePrefix == resXmlString) {
            return;
        }
        if (namespacePrefix == null) {
            this.namespacePrefix = resXmlString;
        } else {
            k2d.a("Uri string item is already linked");
        }
    }

    public void linkResourceIdInternal(ResXmlID resXmlID) {
        if (resXmlID == null) {
            x0e.a("Can not link null id item");
            return;
        }
        ResXmlID resXmlID2 = this.mResXmlID;
        if (resXmlID2 == resXmlID) {
            return;
        }
        if (resXmlID2 != null) {
            k2d.a("Resource id string item is already linked");
        } else {
            this.mResXmlID = resXmlID;
            resXmlID.setResXmlStringInternal(this);
        }
    }

    @Override // com.reandroid.arsc.item.StringItem
    public boolean merge(StringItem stringItem) {
        if (!super.merge(stringItem)) {
            return false;
        }
        int resourceId = ((ResXmlString) stringItem).getResourceId();
        if (resourceId == 0) {
            return true;
        }
        getOrCreateResXmlID().set(resourceId);
        return true;
    }

    public void setResourceId(int i) {
        if (i == 0) {
            unLinkResourceIdInternal();
        } else {
            getOrCreateResXmlID().set(i);
        }
    }

    public void unLinkResourceIdInternal() {
        ResXmlID resXmlID = this.mResXmlID;
        if (resXmlID != null) {
            this.mResXmlID = null;
            resXmlID.setResXmlStringInternal(null);
        }
    }

    @Override // com.reandroid.arsc.item.StringItem
    public boolean equalsValue(StyleDocument styleDocument) {
        return !hasResourceId() && super.equalsValue(styleDocument);
    }

    public boolean equalsValue(int i, StyleDocument styleDocument) {
        return i == getResourceId() && super.equalsValue(styleDocument);
    }

    public boolean equalsValue(int i, String str) {
        return i == getResourceId() && super.equalsValue(str);
    }
}
