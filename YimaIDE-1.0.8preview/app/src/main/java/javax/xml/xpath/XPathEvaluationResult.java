package javax.xml.xpath;

import javax.xml.namespace.QName;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XPathEvaluationResult<T> {

    public enum XPathResultType {
        ANY(new QName("http://www.w3.org/1999/XSL/Transform", "any"), XPathEvaluationResult.class),
        BOOLEAN(XPathConstants.BOOLEAN, Boolean.class),
        NUMBER(XPathConstants.NUMBER, Number.class),
        STRING(XPathConstants.STRING, String.class),
        NODESET(XPathConstants.NODESET, XPathNodes.class),
        NODE(XPathConstants.NODE, Node.class);

        final Class<?> clsType;
        final QName qnameType;

        XPathResultType(QName qName, Class cls) {
            this.qnameType = qName;
            this.clsType = cls;
        }

        private boolean equalsClassType(Class<?> cls) {
            if (cls == null || !this.clsType.isAssignableFrom(cls)) {
                return false;
            }
            if (this.clsType == Number.class) {
                return isAcceptedNumberSubType(cls);
            }
            return true;
        }

        public static QName getQNameType(Class<?> cls) {
            for (XPathResultType xPathResultType : values()) {
                if (xPathResultType.equalsClassType(cls)) {
                    return xPathResultType.qnameType;
                }
            }
            return null;
        }

        private boolean isAcceptedNumberSubType(Class<?> cls) {
            return cls.isAssignableFrom(Double.class) || cls.isAssignableFrom(Integer.class) || cls.isAssignableFrom(Long.class);
        }
    }

    XPathResultType type();

    T value();
}
