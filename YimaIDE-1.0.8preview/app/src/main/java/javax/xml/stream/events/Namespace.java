package javax.xml.stream.events;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface Namespace extends Attribute {
    String getNamespaceURI();

    String getPrefix();

    boolean isDefaultNamespaceDeclaration();
}
