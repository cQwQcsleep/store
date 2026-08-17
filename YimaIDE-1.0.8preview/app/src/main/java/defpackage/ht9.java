package defpackage;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ht9 {
    public static final ht9 a = new ht9();
    public static final Set b = SetsKt.setOf(new String[]{"uses-permission", "uses-permission-sdk-23", "uses-feature", "permission", "permission-group"});
    public static final Set c = SetsKt.setOf(new String[]{"activity", "activity-alias", "service", "receiver", "provider", "meta-data", "uses-library", "property"});
    public static final Regex d = new Regex("<uses-permission(?:-sdk-23)?[^>]*android:name\\s*=\\s*\"([^\"]+)\"");
    public static final int e = 8;

    public static final class a {
        public final String a;
        public final List b;

        public a(String str, List list) {
            str.getClass();
            list.getClass();
            this.a = str;
            this.b = list;
        }

        public final List a() {
            return this.b;
        }

        public final String b() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b);
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Result(xml=" + this.a + ", warnings=" + this.b + ")";
        }
    }

    public static CharSequence a(String str) {
        str.getClass();
        return "    <uses-permission android:name=\"" + str + "\" />\n";
    }

    public static String b(MatchResult matchResult) {
        matchResult.getClass();
        return (String) matchResult.getGroupValues().get(1);
    }

    public final Element c(Element element, String str) {
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

    public final void d(Element element, String str) {
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        for (String str2 : CollectionsKt.listOf(new String[]{"name", "targetActivity"})) {
            String attributeNS = element.getAttributeNS("http://schemas.android.com/apk/res/android", str2);
            attributeNS.getClass();
            if (StringsKt.startsWith$default(attributeNS, Constants.ATTRVAL_THIS, false, 2, (Object) null)) {
                element.setAttributeNS("http://schemas.android.com/apk/res/android", "android:" + str2, str + attributeNS);
            }
        }
    }

    public final Node e(Document document, Element element) {
        Node nodeImportNode = document.importNode(element, true);
        if (nodeImportNode instanceof Element) {
            j((Element) nodeImportNode);
        }
        nodeImportNode.getClass();
        return nodeImportNode;
    }

    public final a f(String str, List list, String str2) {
        Object aVar;
        a aVar2;
        Object obj;
        Object obj2;
        Iterator it;
        NodeList nodeList;
        Iterator it2;
        String strH;
        String strH2;
        str.getClass();
        list.getClass();
        str2.getClass();
        ArrayList arrayList = new ArrayList();
        if (list.isEmpty()) {
            return new a(str, arrayList);
        }
        try {
            Result.Companion companion = Result.Companion;
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            int i = 1;
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryNewInstance.newDocumentBuilder();
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            bytes.getClass();
            Document document = documentBuilderNewDocumentBuilder.parse(new ByteArrayInputStream(bytes));
            Element documentElement = document.getDocumentElement();
            if (documentElement == null) {
                aVar2 = new a(str, arrayList);
            } else {
                Element elementC = c(documentElement, "application");
                if (elementC == null) {
                    elementC = document.createElement("application");
                    documentElement.appendChild(elementC);
                }
                Element element = elementC;
                HashSet hashSet = new HashSet();
                NodeList childNodes = documentElement.getChildNodes();
                int length = childNodes.getLength();
                for (int i2 = 0; i2 < length; i2++) {
                    Node nodeItem = childNodes.item(i2);
                    if ((nodeItem instanceof Element) && (strH2 = h((Element) nodeItem)) != null) {
                        hashSet.add(strH2);
                    }
                }
                HashSet hashSet2 = new HashSet();
                element.getClass();
                NodeList childNodes2 = element.getChildNodes();
                int length2 = childNodes2.getLength();
                for (int i3 = 0; i3 < length2; i3++) {
                    Node nodeItem2 = childNodes2.item(i3);
                    if ((nodeItem2 instanceof Element) && (strH = h((Element) nodeItem2)) != null) {
                        hashSet2.add(strH);
                    }
                }
                Iterator it3 = list.iterator();
                while (it3.hasNext()) {
                    File file = (File) it3.next();
                    try {
                        Result.Companion companion2 = Result.Companion;
                        obj = Result.constructor-impl(FilesKt.readText$default(file, (Charset) null, i, (Object) null));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.Companion;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (Result.isFailure-impl(obj)) {
                        obj = null;
                    }
                    String str3 = (String) obj;
                    if (str3 != null) {
                        String strK = k(str3, str2);
                        try {
                            DocumentBuilder documentBuilderNewDocumentBuilder2 = documentBuilderFactoryNewInstance.newDocumentBuilder();
                            documentBuilderFactoryNewInstance = documentBuilderFactoryNewInstance;
                            try {
                                byte[] bytes2 = strK.getBytes(Charsets.UTF_8);
                                bytes2.getClass();
                                obj2 = Result.constructor-impl(documentBuilderNewDocumentBuilder2.parse(new ByteArrayInputStream(bytes2)));
                            } catch (Throwable th2) {
                                th = th2;
                                Result.Companion companion4 = Result.Companion;
                                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            documentBuilderFactoryNewInstance = documentBuilderFactoryNewInstance;
                        }
                        if (Result.exceptionOrNull-impl(obj2) == null) {
                            Element documentElement2 = ((Document) obj2).getDocumentElement();
                            if (documentElement2 != null) {
                                String attribute = documentElement2.getAttribute(PsiKeyword.PACKAGE);
                                attribute.getClass();
                                String str4 = !StringsKt.isBlank(attribute) ? attribute : null;
                                NodeList childNodes3 = documentElement2.getChildNodes();
                                int length3 = childNodes3.getLength();
                                int i4 = 0;
                                while (i4 < length3) {
                                    int i5 = length3;
                                    Node nodeItem3 = childNodes3.item(i4);
                                    NodeList nodeList2 = childNodes3;
                                    if (nodeItem3 instanceof Element) {
                                        Element element2 = (Element) nodeItem3;
                                        String localName = element2.getLocalName();
                                        if (localName == null) {
                                            localName = element2.getTagName();
                                        }
                                        it2 = it3;
                                        if (b.contains(localName)) {
                                            d(element2, str4);
                                            String strH3 = h(element2);
                                            if (strH3 != null && hashSet.add(strH3)) {
                                                documentElement.appendChild(e(document, element2));
                                            }
                                        } else if (Intrinsics.areEqual(localName, "queries")) {
                                            documentElement.appendChild(e(document, element2));
                                        }
                                    } else {
                                        it2 = it3;
                                    }
                                    i4++;
                                    length3 = i5;
                                    childNodes3 = nodeList2;
                                    it3 = it2;
                                }
                                it = it3;
                                Element elementC2 = c(documentElement2, "application");
                                if (elementC2 != null) {
                                    NodeList childNodes4 = elementC2.getChildNodes();
                                    int length4 = childNodes4.getLength();
                                    int i6 = 0;
                                    while (i6 < length4) {
                                        Node nodeItem4 = childNodes4.item(i6);
                                        if (nodeItem4 instanceof Element) {
                                            Element element3 = (Element) nodeItem4;
                                            String localName2 = element3.getLocalName();
                                            if (localName2 == null) {
                                                localName2 = element3.getTagName();
                                            }
                                            nodeList = childNodes4;
                                            if (c.contains(localName2)) {
                                                d(element3, str4);
                                                String strH4 = h(element3);
                                                if (strH4 != null && hashSet2.add(strH4)) {
                                                    element.appendChild(e(document, element3));
                                                }
                                            }
                                        } else {
                                            nodeList = childNodes4;
                                        }
                                        i6++;
                                        childNodes4 = nodeList;
                                    }
                                }
                            }
                            i = 1;
                        } else {
                            it = it3;
                            File parentFile = file.getParentFile();
                            String name = parentFile != null ? parentFile.getName() : null;
                            arrayList.add("跳过无法解析的库清单: " + name + PsuedoNames.PSEUDONAME_ROOT + file.getName());
                        }
                        it3 = it;
                        i = 1;
                    }
                }
                aVar2 = new a(i(document), arrayList);
            }
            aVar = Result.constructor-impl(aVar2);
        } catch (Throwable th4) {
            Result.Companion companion5 = Result.Companion;
            aVar = Result.constructor-impl(ResultKt.createFailure(th4));
        }
        Throwable th5 = Result.exceptionOrNull-impl(aVar);
        if (th5 != null) {
            arrayList.add("清单 DOM 合并失败(" + th5.getMessage() + "),回退到只合并权限");
            aVar = new a(a.g(str, list), arrayList);
        }
        return (a) aVar;
    }

    public final String g(String str, List list) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            try {
                Result.Companion companion = Result.Companion;
                Iterator it2 = Regex.findAll$default(d, FilesKt.readText$default(file, (Charset) null, 1, (Object) null), 0, 2, (Object) null).iterator();
                while (it2.hasNext()) {
                    linkedHashSet.add(((MatchResult) it2.next()).getGroupValues().get(1));
                }
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
        if (!linkedHashSet.isEmpty()) {
            Set setMinus = SetsKt.minus(linkedHashSet, SequencesKt.toSet(SequencesKt.map(Regex.findAll$default(d, str, 0, 2, (Object) null), new Function1() { // from class: ft9
                public final Object invoke(Object obj) {
                    return ht9.b((MatchResult) obj);
                }
            })));
            if (!setMinus.isEmpty()) {
                int iLastIndexOf$default = StringsKt.lastIndexOf$default(str, "</manifest>", 0, false, 6, (Object) null);
                if (iLastIndexOf$default < 0) {
                    return str;
                }
                String strJoinToString$default = CollectionsKt.joinToString$default(setMinus, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: gt9
                    public final Object invoke(Object obj) {
                        return ht9.a((String) obj);
                    }
                }, 30, (Object) null);
                return str.substring(0, iLastIndexOf$default) + strJoinToString$default + str.substring(iLastIndexOf$default);
            }
        }
        return str;
    }

    public final String h(Element element) {
        String localName = element.getLocalName();
        if (localName == null) {
            localName = element.getTagName();
        }
        String attributeNS = element.getAttributeNS("http://schemas.android.com/apk/res/android", "name");
        attributeNS.getClass();
        if (!StringsKt.isBlank(attributeNS)) {
            return localName + "#" + attributeNS;
        }
        if (Intrinsics.areEqual(localName, "uses-feature")) {
            String attributeNS2 = element.getAttributeNS("http://schemas.android.com/apk/res/android", "glEsVersion");
            attributeNS2.getClass();
            if (!StringsKt.isBlank(attributeNS2)) {
                return localName + "@gl=" + attributeNS2;
            }
        }
        Intrinsics.areEqual(localName, "queries");
        return null;
    }

    public final String i(Document document) throws TransformerException {
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

    /* JADX WARN: Code duplicated, block: B:10:0x0040  */
    public final void j(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        ArrayList arrayList = new ArrayList();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = attributes.item(i);
            if (Intrinsics.areEqual(nodeItem.getNamespaceURI(), "http://schemas.android.com/tools")) {
                arrayList.add(nodeItem.getNodeName());
            } else {
                String nodeName = nodeItem.getNodeName();
                nodeName.getClass();
                if (StringsKt.startsWith$default(nodeName, "tools:", false, 2, (Object) null) || Intrinsics.areEqual(nodeItem.getNodeName(), "xmlns:tools")) {
                    arrayList.add(nodeItem.getNodeName());
                }
            }
        }
        Iterator it = arrayList.iterator();
        it.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            next.getClass();
            element.removeAttribute((String) next);
        }
        NodeList childNodes = element.getChildNodes();
        int length2 = childNodes.getLength();
        for (int i2 = 0; i2 < length2; i2++) {
            Node nodeItem2 = childNodes.item(i2);
            if (nodeItem2 instanceof Element) {
                a.j((Element) nodeItem2);
            }
        }
    }

    public final String k(String str, String str2) {
        return StringsKt.replace$default(StringsKt.replace$default(str, "${applicationId}", str2, false, 4, (Object) null), "${packageName}", str2, false, 4, (Object) null);
    }
}
