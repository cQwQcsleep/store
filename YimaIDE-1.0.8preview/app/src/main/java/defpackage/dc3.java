package defpackage;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdk.xml.internal.JdkConstants;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class dc3 {
    public static final dc3 a = new dc3();
    public static final Set b = SetsKt.setOf(new String[]{"application", "activity", "activity-alias", "service", "receiver", "provider"});
    public static final int c = 8;

    public final String a(String str) {
        str.getClass();
        String str2 = "com.yimaide.debug." + j(str);
        if (!Intrinsics.areEqual(str2, "com.yimaide.debug")) {
            return str2;
        }
        k2d.a("debug host must be per-project, not com.yimaide.debug");
        return null;
    }

    public final Element b(Element element, String str) {
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        Element element2 = null;
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem instanceof Element) {
                Element element3 = (Element) nodeItem;
                if (element2 == null && (Intrinsics.areEqual(element3.getLocalName(), str) || Intrinsics.areEqual(element3.getTagName(), str))) {
                    element2 = element3;
                }
            }
        }
        return element2;
    }

    public final String c(String str) {
        str.getClass();
        return d(str) + ";" + i(str);
    }

    public final String d(String str) {
        str.getClass();
        return str + ".codeslot";
    }

    public final String e(String str) {
        str.getClass();
        String string = StringsKt.trim(str).toString();
        if (StringsKt.isBlank(string)) {
            string = "App";
        }
        return "调试 · " + string;
    }

    public final void f(Element element, String str) {
        String localName = element.getLocalName();
        if (localName == null) {
            localName = element.getTagName();
        }
        if (b.contains(localName)) {
            g(element, str);
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem instanceof Element) {
                a.f((Element) nodeItem, str);
            }
        }
    }

    public final void g(Element element, String str) {
        String str2;
        for (String str3 : CollectionsKt.listOf(new String[]{"name", "targetActivity"})) {
            String attributeNS = element.getAttributeNS("http://schemas.android.com/apk/res/android", str3);
            if (attributeNS != null && !StringsKt.isBlank(attributeNS)) {
                if (StringsKt.startsWith$default(attributeNS, Constants.ATTRVAL_THIS, false, 2, (Object) null)) {
                    str2 = str + attributeNS;
                } else if (StringsKt.contains$default(attributeNS, '.', false, 2, (Object) null)) {
                    str2 = attributeNS;
                } else {
                    str2 = str + Constants.ATTRVAL_THIS + attributeNS;
                }
                if (!Intrinsics.areEqual(str2, attributeNS)) {
                    element.setAttributeNS("http://schemas.android.com/apk/res/android", "android:" + str3, str2);
                }
            }
        }
    }

    public final boolean h(Element element) {
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem instanceof Element) {
                Element element2 = (Element) nodeItem;
                String localName = element2.getLocalName();
                if (localName == null) {
                    localName = element2.getTagName();
                }
                if (Intrinsics.areEqual(localName, "intent-filter")) {
                    NodeList childNodes2 = element2.getChildNodes();
                    int length2 = childNodes2.getLength();
                    boolean z2 = false;
                    boolean z3 = false;
                    for (int i2 = 0; i2 < length2; i2++) {
                        Node nodeItem2 = childNodes2.item(i2);
                        if (nodeItem2 instanceof Element) {
                            Element element3 = (Element) nodeItem2;
                            String localName2 = element3.getLocalName();
                            if (localName2 == null) {
                                localName2 = element3.getTagName();
                            }
                            String attributeNS = element3.getAttributeNS("http://schemas.android.com/apk/res/android", "name");
                            if (Intrinsics.areEqual(localName2, "action") && Intrinsics.areEqual(attributeNS, "android.intent.action.MAIN")) {
                                z2 = true;
                            } else if (Intrinsics.areEqual(localName2, "category") && Intrinsics.areEqual(attributeNS, "android.intent.category.LAUNCHER")) {
                                z3 = true;
                            }
                        }
                    }
                    if (z2 && z3) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public final String i(String str) {
        str.getClass();
        return str + ".hotswap";
    }

    public final String j(String str) throws IOException {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        StringBuilder sb = new StringBuilder();
        int length = lowerCase.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = lowerCase.charAt(i);
            if (Character.isLetterOrDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        String string = sb.toString();
        if (StringsKt.isBlank(string)) {
            string = "x";
        }
        String strTake = StringsKt.take(string, 16);
        if (!Character.isDigit(StringsKt.first(strTake))) {
            return strTake;
        }
        return "p" + strTake;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0112  */
    /* JADX WARN: Code duplicated, block: B:59:0x0162  */
    /* JADX WARN: Code duplicated, block: B:60:0x0164  */
    public final String k(String str, String str2, String str3, String str4) {
        String str5;
        Object obj;
        Object obj2;
        String strL;
        NodeList nodeList;
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(str, "${applicationId}", str4, false, 4, (Object) null), "${packageName}", str4, false, 4, (Object) null);
        try {
            Result.Companion companion = Result.Companion;
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryNewInstance.newDocumentBuilder();
            byte[] bytes = strReplace$default.getBytes(Charsets.UTF_8);
            bytes.getClass();
            Document document = documentBuilderNewDocumentBuilder.parse(new ByteArrayInputStream(bytes));
            Element documentElement = document.getDocumentElement();
            if (documentElement == null) {
                strL = strReplace$default;
                str5 = strL;
            } else {
                documentElement.setAttribute(PsiKeyword.PACKAGE, str4);
                f(documentElement, str2);
                String strE = e(str3);
                Element elementB = b(documentElement, "application");
                if (elementB != null) {
                    elementB.setAttributeNS("http://schemas.android.com/apk/res/android", "android:label", strE);
                    NodeList childNodes = elementB.getChildNodes();
                    int length = childNodes.getLength();
                    for (int i = 0; i < length; i++) {
                        Node nodeItem = childNodes.item(i);
                        if (nodeItem instanceof Element) {
                            Element element = (Element) nodeItem;
                            String localName = element.getLocalName();
                            if (localName == null) {
                                localName = element.getTagName();
                            }
                            if ((Intrinsics.areEqual(localName, "activity") || Intrinsics.areEqual(localName, "activity-alias")) && h(element)) {
                                element.setAttributeNS("http://schemas.android.com/apk/res/android", "android:label", strE);
                            }
                        }
                    }
                    String str6 = str2 + ".CodeSlotProvider";
                    String strD = d(str4);
                    String strI = i(str4);
                    String strC = c(str4);
                    NodeList childNodes2 = elementB.getChildNodes();
                    int length2 = childNodes2.getLength();
                    int i2 = 0;
                    Element element2 = null;
                    while (i2 < length2) {
                        Node nodeItem2 = childNodes2.item(i2);
                        if (nodeItem2 instanceof Element) {
                            Element element3 = (Element) nodeItem2;
                            String localName2 = element3.getLocalName();
                            if (localName2 == null) {
                                localName2 = element3.getTagName();
                            }
                            if (Intrinsics.areEqual(localName2, "provider")) {
                                String attributeNS = element3.getAttributeNS("http://schemas.android.com/apk/res/android", "authorities");
                                attributeNS.getClass();
                                str5 = strReplace$default;
                                nodeList = childNodes2;
                                try {
                                    if (StringsKt.contains$default(attributeNS, strD, false, 2, (Object) null) || StringsKt.contains$default(attributeNS, strI, false, 2, (Object) null)) {
                                        element2 = element3;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    Result.Companion companion2 = Result.Companion;
                                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                                    if (Result.exceptionOrNull-impl(obj) == null) {
                                        obj2 = obj;
                                    } else {
                                        obj2 = str5;
                                    }
                                    return (String) obj2;
                                }
                            } else {
                                str5 = strReplace$default;
                                nodeList = childNodes2;
                            }
                        } else {
                            str5 = strReplace$default;
                            nodeList = childNodes2;
                        }
                        i2++;
                        childNodes2 = nodeList;
                        strReplace$default = str5;
                    }
                    str5 = strReplace$default;
                    if (element2 != null) {
                        element2.setAttributeNS("http://schemas.android.com/apk/res/android", "android:authorities", strC);
                        element2.setAttributeNS("http://schemas.android.com/apk/res/android", "android:name", str6);
                        element2.setAttributeNS("http://schemas.android.com/apk/res/android", "android:exported", "true");
                    } else {
                        Element elementCreateElement = document.createElement("provider");
                        elementCreateElement.setAttributeNS("http://schemas.android.com/apk/res/android", "android:name", str6);
                        elementCreateElement.setAttributeNS("http://schemas.android.com/apk/res/android", "android:authorities", strC);
                        elementCreateElement.setAttributeNS("http://schemas.android.com/apk/res/android", "android:exported", "true");
                        elementB.appendChild(elementCreateElement);
                    }
                } else {
                    str5 = strReplace$default;
                }
                strL = l(document);
            }
            obj = Result.constructor-impl(strL);
        } catch (Throwable th2) {
            th = th2;
            str5 = strReplace$default;
        }
        if (Result.exceptionOrNull-impl(obj) == null) {
            obj2 = obj;
        } else {
            obj2 = str5;
        }
        return (String) obj2;
    }

    public final String l(Document document) throws TransformerException {
        Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
        transformerNewTransformer.setOutputProperty("encoding", "UTF-8");
        transformerNewTransformer.setOutputProperty("indent", JdkConstants.JDK_YES);
        transformerNewTransformer.setOutputProperty("omit-xml-declaration", "no");
        StringWriter stringWriter = new StringWriter();
        transformerNewTransformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        String string = stringWriter.toString();
        string.getClass();
        return string;
    }
}
