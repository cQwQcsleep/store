package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.xml.ResXmlStartNamespace;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.Swappable;
import com.reandroid.xml.XMLUtil;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlStartNamespaceList extends ResXmlChunkList<ResXmlStartNamespace> implements JSONConvert<JSONArray> {
    private final BlockList<ResXmlEndNamespace> endNamespaceList;

    public ResXmlStartNamespaceList(BlockList<ResXmlEndNamespace> blockList) {
        this.endNamespaceList = blockList;
    }

    private boolean containsPrefix(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (str.equals(((ResXmlStartNamespace) get(i)).getPrefix())) {
                return true;
            }
        }
        return false;
    }

    private boolean containsPrefixEveryWhere(String str) {
        Iterator itRecursiveElements = document().recursiveElements();
        while (itRecursiveElements.hasNext()) {
            if (((ResXmlElement) itRecursiveElements.next()).getNamespaceList().containsPrefix(str)) {
                return true;
            }
        }
        return false;
    }

    private ResXmlStartNamespace createLocal(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = generateUniquePrefix();
        }
        return createNext(str, str2);
    }

    private ResXmlDocument document() {
        return element().getParentDocument();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ResXmlElement element() {
        return (ResXmlElement) getParentInstance(ResXmlElement.class);
    }

    private void ensureNamespace(String str, String str2, int i) {
        if (get(str, str2) == null) {
            createNext(str, str2).setLineNumber(i);
        }
    }

    private void ensureUniqueUri() {
        ResXmlDocument resXmlDocumentDocument = document();
        if (resXmlDocumentDocument != null) {
            Iterator itRecursiveElements = resXmlDocumentDocument.recursiveElements();
            while (itRecursiveElements.hasNext()) {
                ((ResXmlElement) itRecursiveElements.next()).getNamespaceList().ensureUniqueUriLocal();
            }
        }
    }

    private void ensureUniqueUriLocal() {
        int size = size();
        for (int i = 0; i < size; i++) {
            ((ResXmlStartNamespace) get(i)).ensureUniqueUri();
        }
    }

    private String generateUniquePrefix() {
        int i = 1;
        while (i < 10000) {
            String str = "ns" + i;
            if (!containsPrefixEveryWhere(str)) {
                return str;
            }
            i++;
        }
        qf1.a("Failed to generate unique prefix, trials = ", i);
        return null;
    }

    private ResXmlStartNamespace getLocal(String str, String str2) {
        ResXmlStartNamespace resXmlStartNamespace = null;
        if (str == null) {
            return null;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            ResXmlStartNamespace resXmlStartNamespace2 = (ResXmlStartNamespace) get(i);
            if (str.equals(resXmlStartNamespace2.getUri()) && ObjectsUtil.equals(str2, resXmlStartNamespace2.getPrefix()) && (resXmlStartNamespace == null || resXmlStartNamespace2.getReferencedCount() > resXmlStartNamespace.getReferencedCount())) {
                resXmlStartNamespace = resXmlStartNamespace2;
            }
        }
        return resXmlStartNamespace;
    }

    private ResXmlStartNamespace getLocalForUriReference(int i) {
        if (i == -1) {
            return null;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ResXmlStartNamespace resXmlStartNamespace = (ResXmlStartNamespace) get(i2);
            if (i == resXmlStartNamespace.getUriReference()) {
                return resXmlStartNamespace;
            }
        }
        return null;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlChunkList
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public void clearUndefined() {
        removeIf(new Predicate() { // from class: lfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlStartNamespace) obj).isUndefined();
            }
        });
    }

    public void clearUnused() {
        removeIf(new Predicate() { // from class: mfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlStartNamespace) obj).isUnused();
            }
        });
    }

    public ResXmlStartNamespace createNext() {
        ResXmlEndNamespace resXmlEndNamespace = new ResXmlEndNamespace();
        ResXmlStartNamespace resXmlStartNamespace = new ResXmlStartNamespace(resXmlEndNamespace);
        resXmlEndNamespace.setStart(resXmlStartNamespace);
        add(resXmlStartNamespace);
        this.endNamespaceList.add(resXmlEndNamespace);
        return resXmlStartNamespace;
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONArray.get(i);
                if (obj != null) {
                    if (!(obj instanceof JSONObject)) {
                        throw new JSONException("Expecting JSONObject but found: " + obj.getClass());
                    }
                    createNext().fromJson((JSONObject) obj);
                }
            }
        }
    }

    public ResXmlStartNamespace get(String str, String str2) {
        Iterator<ResXmlElement> descendingParentsWithSelf = element().getDescendingParentsWithSelf();
        ResXmlStartNamespace resXmlStartNamespace = null;
        while (descendingParentsWithSelf.hasNext()) {
            ResXmlStartNamespace local = descendingParentsWithSelf.next().getNamespaceList().getLocal(str, str2);
            if (local != null && local.isBetterThan(resXmlStartNamespace)) {
                resXmlStartNamespace = local;
            }
        }
        return resXmlStartNamespace;
    }

    public ResXmlNamespace getForPrefix(String str) {
        Iterator<ResXmlStartNamespace> visibleNamespaces = getVisibleNamespaces();
        ResXmlStartNamespace resXmlStartNamespace = null;
        while (visibleNamespaces.hasNext()) {
            ResXmlStartNamespace next = visibleNamespaces.next();
            if (ObjectsUtil.equals(str, next.getPrefix()) && (resXmlStartNamespace == null || Namespace.isValidUri(next.getUri()))) {
                resXmlStartNamespace = next;
            }
        }
        return resXmlStartNamespace;
    }

    public ResXmlNamespace getForUri(String str) {
        Iterator<ResXmlStartNamespace> visibleNamespaces = getVisibleNamespaces();
        ResXmlStartNamespace resXmlStartNamespace = null;
        while (visibleNamespaces.hasNext()) {
            ResXmlStartNamespace next = visibleNamespaces.next();
            if (ObjectsUtil.equals(str, next.getUri()) && (resXmlStartNamespace == null || Namespace.isValidPrefix(next.getPrefix()))) {
                resXmlStartNamespace = next;
            }
        }
        return resXmlStartNamespace;
    }

    public ResXmlStartNamespace getForUriReference(int i) {
        if (i == -1) {
            return null;
        }
        Iterator<ResXmlElement> parentElementsWithSelf = element().getParentElementsWithSelf();
        while (parentElementsWithSelf.hasNext()) {
            ResXmlStartNamespace localForUriReference = parentElementsWithSelf.next().getNamespaceList().getLocalForUriReference(i);
            if (localForUriReference != null) {
                return localForUriReference;
            }
        }
        return null;
    }

    public Iterator<ResXmlNamespace> getNamespaces() {
        return (Iterator) ObjectsUtil.cast(iterator());
    }

    public ResXmlStartNamespace getOrCreate(String str, String str2) {
        ResXmlStartNamespace resXmlStartNamespace = get(str, str2);
        return resXmlStartNamespace == null ? element().getRootElement().getNamespaceList().createLocal(str, str2) : resXmlStartNamespace;
    }

    public ResXmlNamespace getOrCreateForPrefix(String str) {
        if (StringsUtil.isBlank(str)) {
            return null;
        }
        ResXmlNamespace forPrefix = getForPrefix(str);
        if (forPrefix != null) {
            return forPrefix;
        }
        return getOrCreate(Namespace.PREFIX_ANDROID.equals(str) ? Namespace.URI_ANDROID : Namespace.URI_RES_AUTO, str);
    }

    public Iterator<ResXmlStartNamespace> getVisibleNamespaces() {
        return new IterableIterator<ResXmlElement, ResXmlStartNamespace>(element().getDescendingParentsWithSelf()) { // from class: com.reandroid.arsc.chunk.xml.ResXmlStartNamespaceList.1
            public Iterator<ResXmlStartNamespace> iterator(ResXmlElement resXmlElement) {
                return resXmlElement.getNamespaceList().iterator();
            }
        };
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void merge(ResXmlStartNamespaceList resXmlStartNamespaceList) {
        if (resXmlStartNamespaceList != this) {
            int size = resXmlStartNamespaceList.size();
            for (int i = 0; i < size; i++) {
                createNext().merge((ResXmlStartNamespace) resXmlStartNamespaceList.get(i));
            }
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlChunkList
    public void onPreRemove(ResXmlStartNamespace resXmlStartNamespace) {
        super.onPreRemove(resXmlStartNamespace);
        this.endNamespaceList.remove(resXmlStartNamespace.getEnd());
    }

    public void onRefreshed() {
        super.onRefreshed();
        clearUndefined();
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException {
        int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth());
        for (int i = 0; i < namespaceCount; i++) {
            ensureNamespace(xmlPullParser.getNamespaceUri(i), xmlPullParser.getNamespacePrefix(i), xmlPullParser.getLineNumber());
        }
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String strSplitPrefix = XMLUtil.splitPrefix(attributeName);
            if (strSplitPrefix != null) {
                String strSplitName = XMLUtil.splitName(attributeName);
                String attributeValue = xmlPullParser.getAttributeValue(i2);
                if (Namespace.isValidNamespace(attributeValue, strSplitPrefix)) {
                    ensureNamespace(attributeValue, strSplitName, 0);
                }
            }
        }
    }

    public boolean sort(Comparator<? super ResXmlStartNamespace> comparator) {
        if (!super.sort(comparator)) {
            return false;
        }
        this.endNamespaceList.sort(CompareUtil.getComparableComparator());
        return true;
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m65toJson() {
        return BlockList.toJsonArray(this);
    }

    public boolean sort(Comparator<? super ResXmlStartNamespace> comparator, Swappable swappable) {
        if (!super.sort(comparator, swappable)) {
            return false;
        }
        this.endNamespaceList.sort(CompareUtil.getComparableComparator());
        return true;
    }

    public ResXmlStartNamespace createNext(String str, String str2) {
        if (str != null) {
            ensureUniqueUri();
        }
        ResXmlStartNamespace resXmlStartNamespaceCreateNext = createNext();
        if (str == null && str2 == null) {
            return resXmlStartNamespaceCreateNext;
        }
        resXmlStartNamespaceCreateNext.set(str, str2);
        return resXmlStartNamespaceCreateNext;
    }
}
