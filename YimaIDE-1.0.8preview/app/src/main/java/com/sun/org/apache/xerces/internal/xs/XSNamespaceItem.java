package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSNamespaceItem {
    XSObjectList getAnnotations();

    XSAttributeDeclaration getAttributeDeclaration(String str);

    XSAttributeGroupDefinition getAttributeGroup(String str);

    XSNamedMap getComponents(short s);

    StringList getDocumentLocations();

    XSElementDeclaration getElementDeclaration(String str);

    XSIDCDefinition getIDCDefinition(String str);

    XSModelGroupDefinition getModelGroupDefinition(String str);

    XSNotationDeclaration getNotationDeclaration(String str);

    String getSchemaNamespace();

    XSTypeDefinition getTypeDefinition(String str);
}
