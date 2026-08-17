package javax.xml.xpath;

import java.util.Iterator;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XPathNodes extends Iterable<Node> {
    Node get(int i) throws XPathException;

    @Override // java.lang.Iterable
    Iterator<Node> iterator();

    int size();
}
