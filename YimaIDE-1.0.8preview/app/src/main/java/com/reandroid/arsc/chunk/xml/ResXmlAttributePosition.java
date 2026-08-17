package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.item.ShortItem;
import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlAttributePosition extends ShortItem {
    private ResXmlAttribute mAttribute;
    private final int type;
    public static final int TYPE_ID = ObjectsUtil.of(0);
    public static final int TYPE_CLASS = ObjectsUtil.of(1);
    public static final int TYPE_STYLE = ObjectsUtil.of(2);

    public ResXmlAttributePosition(int i) {
        this.type = i;
    }

    private ResXmlAttribute computePosition(ResXmlAttributeArray resXmlAttributeArray) {
        int iType = type();
        if (iType == TYPE_ID) {
            return resXmlAttributeArray.searchAttributeByResourceId(ResXmlAttribute.ATTRIBUTE_RESOURCE_ID_id);
        }
        if (iType == TYPE_CLASS) {
            return resXmlAttributeArray.searchAttributeByName(ResXmlAttribute.ATTRIBUTE_NAME_CLASS);
        }
        if (iType == TYPE_STYLE) {
            return resXmlAttributeArray.searchAttributeByName(ResXmlAttribute.ATTRIBUTE_NAME_STYLE);
        }
        gke.a("Unknown position type: ", iType);
        return null;
    }

    private ResXmlAttributeArray getAttributeArray() {
        ResXmlStartElement resXmlStartElement = (ResXmlStartElement) getParentInstance(ResXmlStartElement.class);
        return resXmlStartElement != null ? resXmlStartElement.getResXmlAttributeArray() : (ResXmlAttributeArray) ObjectsUtil.getNull();
    }

    public static int getPositionType(ResXmlAttribute resXmlAttribute) {
        int nameId = resXmlAttribute.getNameId();
        if (nameId != 0) {
            if (nameId == ResXmlAttribute.ATTRIBUTE_RESOURCE_ID_id) {
                return TYPE_ID;
            }
            return -1;
        }
        if (ResXmlAttribute.ATTRIBUTE_NAME_CLASS.equals(resXmlAttribute.getName())) {
            return TYPE_CLASS;
        }
        if (ResXmlAttribute.ATTRIBUTE_NAME_STYLE.equals(resXmlAttribute.getName())) {
            return TYPE_STYLE;
        }
        return -1;
    }

    private static boolean isInvalidAttribute(ResXmlAttribute resXmlAttribute) {
        return resXmlAttribute == null || resXmlAttribute.isNull() || resXmlAttribute.isUndefined() || resXmlAttribute.getParent() == null;
    }

    private void pullAttribute(boolean z) {
        int position = getPosition();
        ResXmlAttribute resXmlAttribute = null;
        if (position >= 0) {
            ResXmlAttributeArray attributeArray = getAttributeArray();
            resXmlAttribute = attributeArray != null ? (ResXmlAttribute) attributeArray.get(position) : null;
            if (z && resXmlAttribute == null) {
                if (attributeArray == null) {
                    w01.a("Unable to find attributes array, could be removed or uninitialized element");
                    return;
                } else {
                    kac.a("Position ", position, " out of range, for ", attributeArray.size());
                    return;
                }
            }
        }
        this.mAttribute = resXmlAttribute;
    }

    private void writePosition(int i) {
        if (i < -1 || i > 65534) {
            ib9.a("Attribute position ", i, " out of range, must be between -1 to 65534");
        } else {
            set(i + 1);
        }
    }

    public void clear() {
        this.mAttribute = null;
        writePosition(-1);
    }

    public void fixName() {
        int iType = type();
        if (iType == TYPE_CLASS) {
            fixName(ResXmlAttribute.ATTRIBUTE_NAME_CLASS);
        } else if (iType == TYPE_STYLE) {
            fixName(ResXmlAttribute.ATTRIBUTE_NAME_STYLE);
        }
    }

    public ResXmlAttribute getAttribute() {
        return this.mAttribute;
    }

    public int getPosition() {
        return get() - 1;
    }

    public void linkAttribute() {
        pullAttribute(false);
    }

    public void refresh() {
        ResXmlAttribute resXmlAttribute = this.mAttribute;
        if (isInvalidAttribute(resXmlAttribute)) {
            clear();
        } else {
            writePosition(resXmlAttribute.getIndex());
        }
    }

    public void setAttribute(ResXmlAttribute resXmlAttribute) {
        this.mAttribute = resXmlAttribute;
        writePosition(resXmlAttribute != null ? resXmlAttribute.getIndex() : -1);
    }

    public void setPosition(int i) {
        writePosition(i);
        pullAttribute(true);
    }

    @Override // com.reandroid.arsc.item.ShortItem
    public String toString() {
        return getPosition() + " (" + this.mAttribute + ")";
    }

    public int type() {
        return this.type;
    }

    private void fixName(String str) {
        ResXmlAttribute resXmlAttribute = this.mAttribute;
        if (resXmlAttribute == null || str.equals(resXmlAttribute.getName())) {
            return;
        }
        resXmlAttribute.setName(str);
    }

    public void computePosition() {
        ResXmlAttributeArray attributeArray = getAttributeArray();
        setAttribute(attributeArray != null ? computePosition(attributeArray) : null);
    }
}
