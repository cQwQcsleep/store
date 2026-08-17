package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlElementApi {
    private final ResXmlElement element;

    public ResXmlElementApi(ResXmlElement resXmlElement) {
        this.element = resXmlElement;
    }

    public ResXmlAttributeArray getAttributeArray() {
        return getStartElement().getResXmlAttributeArray();
    }

    public IntegerReference getAttributeCount() {
        return getStartElement().getAttributeCount();
    }

    public IntegerReference getAttributeStart() {
        return getStartElement().getAttributeStart();
    }

    public IntegerReference getAttributeUnitSize() {
        return getStartElement().getAttributeUnitSize();
    }

    public ResXmlAttributePosition getClassAttributePosition() {
        return getStartElement().getClassAttributePosition();
    }

    public ResXmlElement getElement() {
        return this.element;
    }

    public ResXmlEndElement getEndElement() {
        return this.element.getChunk().getEndElement();
    }

    public ResXmlAttributePosition getIdAttributePosition() {
        return getStartElement().getIdAttributePosition();
    }

    public ResXmlEndNamespace getResXmlEndNamespace() {
        ResXmlStartNamespace resXmlStartNamespace = getResXmlStartNamespace();
        if (resXmlStartNamespace != null) {
            return resXmlStartNamespace.getEnd();
        }
        return null;
    }

    public ResXmlStartNamespace getResXmlStartNamespace() {
        return getStartElement().getResXmlStartNamespace();
    }

    public ResXmlStartElement getStartElement() {
        return this.element.getChunk().getStartElement();
    }

    public ResXmlStartNamespaceList getStartNamespaceList() {
        return this.element.getChunk().getStartNamespaceList();
    }

    public ResXmlAttributePosition getStyleAttributePosition() {
        return getStartElement().getStyleAttributePosition();
    }

    public ResXmlStartElement.UnknownBytes getUnknownBytes() {
        return getStartElement().getUnknownBytes();
    }

    public String toString() {
        return this.element.toString();
    }
}
