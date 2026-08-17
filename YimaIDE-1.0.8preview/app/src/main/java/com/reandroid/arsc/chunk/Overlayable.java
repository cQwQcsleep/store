package com.reandroid.arsc.chunk;

import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.header.OverlayableHeader;
import com.reandroid.arsc.io.BlockReader;
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
public class Overlayable extends Chunk<OverlayableHeader> implements Iterable<OverlayablePolicy>, JSONConvert<JSONObject> {
    private final BlockList<OverlayablePolicy> policyList;
    public static final String NAME_name = ObjectsUtil.of(TypeBlock.NAME_name);
    public static final String NAME_actor = ObjectsUtil.of("actor");
    public static final String NAME_policies = ObjectsUtil.of("policies");
    public static final String TAG_overlayable = ObjectsUtil.of("overlayable");
    public static final String FILE_NAME_XML = ObjectsUtil.of("overlayable.xml");

    public Overlayable() {
        super(new OverlayableHeader(), 2);
        BlockList<OverlayablePolicy> blockList = new BlockList<>();
        this.policyList = blockList;
        addChild(blockList);
    }

    private void readOverlayablePolicies(BlockReader blockReader) throws IOException {
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        BlockList<OverlayablePolicy> blockList = this.policyList;
        while (headerBlock != null && headerBlock.getChunkType() == ChunkType.OVERLAYABLE_POLICY) {
            OverlayablePolicy overlayablePolicy = new OverlayablePolicy();
            blockList.add(overlayablePolicy);
            overlayablePolicy.readBytes(blockReader);
            headerBlock = blockReader.readHeaderBlock();
        }
    }

    public void addPolicy(OverlayablePolicy overlayablePolicy) {
        this.policyList.add(overlayablePolicy);
    }

    public OverlayablePolicy createNext() {
        OverlayablePolicy overlayablePolicy = new OverlayablePolicy();
        this.policyList.add(overlayablePolicy);
        return overlayablePolicy;
    }

    public void fromJson(JSONObject jSONObject) {
        setName(jSONObject.optString(NAME_name, (String) null));
        setActor(jSONObject.optString(NAME_actor, (String) null));
        JSONArray jSONArray = jSONObject.getJSONArray(NAME_policies);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            createNext().fromJson(jSONArray.getJSONObject(i));
        }
    }

    public String getActor() {
        return getHeaderBlock().getActor().get();
    }

    public OverlayablePolicy getByFlag(int i) {
        for (OverlayablePolicy overlayablePolicy : this) {
            if (i == overlayablePolicy.getFlags()) {
                return overlayablePolicy;
            }
        }
        return null;
    }

    public String getName() {
        return getHeaderBlock().getName().get();
    }

    @Override // java.lang.Iterable
    public Iterator<OverlayablePolicy> iterator() {
        return this.policyList.iterator();
    }

    public void merge(Overlayable overlayable) {
        if (overlayable == null || overlayable == this) {
            return;
        }
        setName(overlayable.getName());
        setActor(overlayable.getActor());
        for (OverlayablePolicy overlayablePolicy : overlayable) {
            OverlayablePolicy byFlag = getByFlag(overlayablePolicy.getFlags());
            if (byFlag == null) {
                byFlag = createNext();
            }
            byFlag.merge(overlayablePolicy);
        }
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    @Override // com.reandroid.arsc.chunk.Chunk, com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        checkInvalidChunk(headerBlock);
        int chunkSize = headerBlock.getChunkSize();
        BlockReader blockReaderCreate = blockReader.create(chunkSize);
        getHeaderBlock().readBytes(blockReaderCreate);
        readOverlayablePolicies(blockReaderCreate);
        blockReader.offset(chunkSize);
        blockReaderCreate.close();
        onChunkLoaded();
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.ensureStartTag(xmlPullParser);
        String str = TAG_overlayable;
        if (!str.equals(xmlPullParser.getName())) {
            throw new XmlPullParserException("Expecting tag '" + str + "', but found '" + xmlPullParser.getName() + "'");
        }
        setName(xmlPullParser.getAttributeValue(null, NAME_name));
        setActor(xmlPullParser.getAttributeValue(null, NAME_actor));
        xmlPullParser.next();
        XMLUtil.ensureTag(xmlPullParser);
        while (xmlPullParser.getEventType() != 3 && xmlPullParser.getEventType() != 1) {
            createNext().parse(xmlPullParser);
            XMLUtil.ensureTag(xmlPullParser);
        }
        if (xmlPullParser.getEventType() == 3) {
            xmlPullParser.next();
            XMLUtil.ensureTag(xmlPullParser);
        }
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, TAG_overlayable);
        String name = getName();
        if (!StringsUtil.isEmpty(name)) {
            xmlSerializer.attribute(null, NAME_name, name);
        }
        String actor = getActor();
        if (!StringsUtil.isEmpty(actor)) {
            xmlSerializer.attribute(null, NAME_actor, actor);
        }
        Iterator<OverlayablePolicy> it = iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer);
        }
        xmlSerializer.endTag(null, TAG_overlayable);
    }

    public void setActor(String str) {
        getHeaderBlock().getActor().set(str);
    }

    public void setName(String str) {
        getHeaderBlock().getName().set(str);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_name, getName());
        jSONObject.put(NAME_actor, getActor());
        JSONArray jSONArray = new JSONArray();
        Iterator<OverlayablePolicy> it = iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        jSONObject.put(NAME_policies, jSONArray);
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return "name='" + getName() + "', actor='" + getActor() + "', policies=" + this.policyList.size();
    }
}
