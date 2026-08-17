package com.reandroid.arsc.pool;

import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.chunk.xml.ResXmlIDMap;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.arsc.item.StringCreator;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.list.ResXmlIDList;
import com.reandroid.arsc.list.StringItemList;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.utils.NumbersUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.xml.StyleDocument;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlStringPool extends StringPool<ResXmlString> {
    public ResXmlStringPool(boolean z) {
        super(z, false, new StringCreator() { // from class: rfc
            @Override // com.reandroid.arsc.item.StringCreator
            public final StringItem newInstance(boolean z2) {
                return new ResXmlString(z2);
            }
        });
    }

    private ResXmlIDMap getResXmlIDMap() {
        ResXmlDocument resXmlDocument = (ResXmlDocument) getParentInstance(ResXmlDocument.class);
        return resXmlDocument != null ? resXmlDocument.getResXmlIDMap() : (ResXmlIDMap) ObjectsUtil.getNull();
    }

    public static /* synthetic */ boolean s(ResXmlString resXmlString) {
        return (resXmlString.hasNamespacePrefix() || resXmlString.hasResourceId() || resXmlString.hasStyle()) ? false : true;
    }

    public static /* synthetic */ boolean x(String str, ResXmlString resXmlString) {
        if (!str.equals(resXmlString.getXml()) || resXmlString.hasResourceId()) {
            return false;
        }
        return resXmlString.hasStyle();
    }

    public ResXmlString getNamespaceString(final String str, final String str2) {
        return get(str, new Predicate() { // from class: nfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlString) obj).equalsNamespace(str, str2);
            }
        });
    }

    @Override // com.reandroid.arsc.pool.StringPool
    public ResXmlString getOrCreate(StyleDocument styleDocument) {
        final String xml = styleDocument.getXml();
        if (!styleDocument.hasElements()) {
            return getOrCreate(0, xml);
        }
        ResXmlString resXmlString = get(xml, new Predicate() { // from class: ofc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ResXmlStringPool.x(xml, (ResXmlString) obj);
            }
        });
        if (resXmlString != null) {
            return resXmlString;
        }
        ResXmlString resXmlStringCreateNewString = createNewString();
        resXmlStringCreateNewString.set(styleDocument);
        return resXmlStringCreateNewString;
    }

    public ResXmlString getOrCreateNamespaceString(String str, String str2) {
        if (str == null || str2 == null || str.equals(str2)) {
            return null;
        }
        ResXmlString namespaceString = getNamespaceString(str, str2);
        if (namespaceString == null && (namespaceString = get(str, new Predicate() { // from class: pfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ResXmlStringPool.s((ResXmlString) obj);
            }
        })) != null) {
            namespaceString.linkNamespacePrefixInternal(getOrCreate(str2));
        }
        if (namespaceString != null) {
            return namespaceString;
        }
        ResXmlString resXmlStringCreateNewString = createNewString(str);
        resXmlStringCreateNewString.linkNamespacePrefixInternal(getOrCreate(str2));
        return resXmlStringCreateNewString;
    }

    public void linkResXmlIDMapInternal() {
        ResXmlIDMap resXmlIDMap = getResXmlIDMap();
        if (resXmlIDMap == null) {
            return;
        }
        StringItemList<ResXmlString> stringsArray = getStringsArray();
        int iMin = NumbersUtil.min(resXmlIDMap.size(), stringsArray.size());
        for (int i = 0; i < iMin; i++) {
            ((ResXmlString) stringsArray.get(i)).linkResourceIdInternal(resXmlIDMap.get(i));
        }
    }

    @Override // com.reandroid.arsc.pool.StringPool, com.reandroid.arsc.chunk.Chunk
    public void onChunkLoaded() {
        super.onChunkLoaded();
        linkResXmlIDMapInternal();
    }

    @Override // com.reandroid.arsc.pool.StringPool
    public void onPreAddInternal(int i, ResXmlString resXmlString) {
        ResXmlIDList resXmlIDArray = getResXmlIDMap().getResXmlIDArray();
        if (i < resXmlIDArray.size() - 1) {
            resXmlIDArray.createAt(i);
        }
        super.onPreAddInternal(i, resXmlString);
    }

    @Override // com.reandroid.arsc.pool.StringPool, com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        sort();
    }

    @Override // com.reandroid.arsc.pool.StringPool
    public void onSortedInternal() {
        super.onSortedInternal();
        getResXmlIDMap().getResXmlIDArray().sort();
    }

    @Override // com.reandroid.arsc.pool.StringPool
    public ResXmlString getOrCreate(String str) {
        return getOrCreate(0, str);
    }

    public ResXmlString getOrCreate(final int i, final String str) {
        ResXmlString resXmlString = get(str, new Predicate() { // from class: qfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlString) obj).equalsValue(i, str);
            }
        });
        if (resXmlString != null) {
            return resXmlString;
        }
        ResXmlString resXmlStringCreateNewString = createNewString(str);
        resXmlStringCreateNewString.setResourceId(i);
        return resXmlStringCreateNewString;
    }
}
