package com.reandroid.arsc.chunk;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.chunk.PolicyItem;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.header.OverlayablePolicyHeader;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class OverlayablePolicy extends Chunk<OverlayablePolicyHeader> implements Iterable<PolicyItem>, JSONConvert<JSONObject> {
    public static final String NAME_flags = "flags";
    public static final String NAME_references = "references";
    private final CountedBlockList<PolicyItem> itemList;
    private static final Creator<PolicyItem> CREATOR = new Creator() { // from class: cta
        public final Block newInstance() {
            return new PolicyItem();
        }
    };
    public static final String TAG_policy = ObjectsUtil.of("policy");

    public OverlayablePolicy() {
        super(new OverlayablePolicyHeader(), 1);
        CountedBlockList<PolicyItem> countedBlockList = new CountedBlockList<>(CREATOR, getHeaderBlock().getEntryCount());
        this.itemList = countedBlockList;
        addChild(countedBlockList);
    }

    public void add(PolicyItem policyItem) {
        getItemList().add(policyItem);
    }

    public void addFlag(PolicyFlag policyFlag) {
        setFlags((policyFlag == null ? 0 : policyFlag.flag()) | getFlags());
    }

    public PolicyItem createNext() {
        return (PolicyItem) getItemList().createNext();
    }

    public void fromJson(JSONObject jSONObject) {
        setFlags(jSONObject.getInt(NAME_flags));
        JSONArray jSONArray = jSONObject.getJSONArray(NAME_references);
        int length = jSONArray.length();
        CountedBlockList<PolicyItem> itemList = getItemList();
        itemList.setSize(length);
        for (int i = 0; i < length; i++) {
            ((PolicyItem) itemList.get(i)).set(jSONArray.getInt(i));
        }
    }

    public PolicyItem get(int i) {
        return (PolicyItem) getItemList().get(i);
    }

    public int getFlags() {
        return getHeaderBlock().getFlags().get();
    }

    public CountedBlockList<PolicyItem> getItemList() {
        return this.itemList;
    }

    public PolicyFlag[] getPolicyFlags() {
        return PolicyFlag.valuesOf(getFlags());
    }

    public int getReferenceCount() {
        return this.itemList.size();
    }

    @Override // com.reandroid.arsc.base.Block
    public boolean isNull() {
        return getReferenceCount() == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<PolicyItem> iterator() {
        return this.itemList.iterator();
    }

    public void merge(OverlayablePolicy overlayablePolicy) {
        if (overlayablePolicy == null || overlayablePolicy == this) {
            return;
        }
        setFlags(getFlags() | overlayablePolicy.getFlags());
        CountedBlockList<PolicyItem> itemList = getItemList();
        for (PolicyItem policyItem : overlayablePolicy.getItemList()) {
            if (!itemList.contains(policyItem)) {
                PolicyItem policyItem2 = new PolicyItem();
                policyItem2.set(policyItem.get());
                itemList.add(policyItem2);
            }
        }
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new XmlPullParserException("Parser not in START_TAG event: " + xmlPullParser.getEventType());
        }
        String str = TAG_policy;
        if (!str.equals(xmlPullParser.getName())) {
            throw new XmlPullParserException("Expecting tag '" + str + "', but found '" + xmlPullParser.getName() + "'");
        }
        setFlags(PolicyFlag.parse(xmlPullParser));
        xmlPullParser.next();
        XMLUtil.ensureTag(xmlPullParser);
        while (xmlPullParser.getEventType() != 3 && xmlPullParser.getEventType() != 1) {
            createNext().parse(xmlPullParser);
            XMLUtil.ensureTag(xmlPullParser);
        }
        if (xmlPullParser.getEventType() == 3) {
            xmlPullParser.next();
        }
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, TAG_policy);
        String string = PolicyFlag.toString(getPolicyFlags());
        if (!StringsUtil.isEmpty(string)) {
            xmlSerializer.attribute(null, "type", string);
        }
        Iterator<PolicyItem> it = iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer);
        }
        xmlSerializer.endTag(null, TAG_policy);
    }

    public void setFlags(int i) {
        getHeaderBlock().getFlags().set(i);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_flags, getFlags());
        JSONArray jSONArray = new JSONArray();
        Iterator<PolicyItem> it = iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().get());
        }
        jSONObject.put(NAME_references, jSONArray);
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return getClass().getSimpleName() + ": flags=" + PolicyFlag.toString(getPolicyFlags()) + "', count=" + getReferenceCount();
    }

    public void setFlags(PolicyFlag[] policyFlagArr) {
        setFlags(PolicyFlag.sum(policyFlagArr));
    }
}
