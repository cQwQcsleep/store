package javax.xml.stream.events;

import java.util.Iterator;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface EndElement extends XMLEvent {
    QName getName();

    Iterator<Namespace> getNamespaces();
}
