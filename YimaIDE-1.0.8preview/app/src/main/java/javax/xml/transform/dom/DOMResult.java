package javax.xml.transform.dom;

import javax.xml.transform.Result;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMResult implements Result {
    public static final String FEATURE = "http://javax.xml.transform.dom.DOMResult/feature";
    private Node node = null;
    private Node nextSibling = null;
    private String systemId = null;

    public DOMResult(Node node, Node node2) {
        if (node2 != null) {
            if (node == null) {
                w01.a("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node.");
                throw null;
            }
            if ((node.compareDocumentPosition(node2) & 16) == 0) {
                w01.a("Cannot create a DOMResult when the nextSibling is not contained by the node.");
                throw null;
            }
        }
        setNode(node);
        setNextSibling(node2);
        setSystemId(null);
    }

    public Node getNextSibling() {
        return this.nextSibling;
    }

    public Node getNode() {
        return this.node;
    }

    @Override // javax.xml.transform.Result
    public String getSystemId() {
        return this.systemId;
    }

    public void setNextSibling(Node node) {
        if (node != null) {
            Node node2 = this.node;
            if (node2 == null) {
                k2d.a("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node.");
                return;
            } else if ((node2.compareDocumentPosition(node) & 16) == 0) {
                w01.a("Cannot create a DOMResult when the nextSibling is not contained by the node.");
                return;
            }
        }
        this.nextSibling = node;
    }

    public void setNode(Node node) {
        Node node2 = this.nextSibling;
        if (node2 != null) {
            if (node == null) {
                k2d.a("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node.");
                return;
            } else if ((node.compareDocumentPosition(node2) & 16) == 0) {
                w01.a("Cannot create a DOMResult when the nextSibling is not contained by the node.");
                return;
            }
        }
        this.node = node;
    }

    @Override // javax.xml.transform.Result
    public void setSystemId(String str) {
        this.systemId = str;
    }

    public DOMResult(Node node) {
        setNode(node);
        setNextSibling(null);
        setSystemId(null);
    }

    public DOMResult(Node node, String str) {
        setNode(node);
        setNextSibling(null);
        setSystemId(str);
    }

    public DOMResult() {
        setNode(null);
        setNextSibling(null);
        setSystemId(null);
    }

    public DOMResult(Node node, Node node2, String str) {
        if (node2 != null) {
            if (node != null) {
                if ((node.compareDocumentPosition(node2) & 16) == 0) {
                    w01.a("Cannot create a DOMResult when the nextSibling is not contained by the node.");
                    throw null;
                }
            } else {
                w01.a("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node.");
                throw null;
            }
        }
        setNode(node);
        setNextSibling(node2);
        setSystemId(str);
    }
}
