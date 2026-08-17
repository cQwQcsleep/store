package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.chunk.UnknownChunk;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.xml.XMLNode;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnknownResXmlNode extends ResXmlNode {
    public UnknownResXmlNode() {
        this(new UnknownChunk());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int autoSetLineNumber(int i) {
        return 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void fromJson(JSONObject jSONObject) throws JSONException, NumberFormatException {
        String strNodeTypeName = nodeTypeName();
        String str = ResXmlNode.JSON_node_type;
        if (strNodeTypeName.equals(jSONObject.optString(str, (String) null))) {
            String str2 = ResXmlNode.JSON_value;
            if (jSONObject.has(str2)) {
                setHexBytes(jSONObject.getString(str2));
                return;
            }
            return;
        }
        throw new JSONException("Expecting: " + nodeTypeName() + ", but found: " + jSONObject.optString(str, (String) null));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public Chunk<?> getChunk() {
        return (Chunk) getBaseBlock();
    }

    public int getChunkType() {
        return getChunk().getHeaderBlock().getType() & 65535;
    }

    public String getHexBytes() {
        return HexUtil.toHexString(getBytes());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getLineNumber() {
        return 0;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public Iterator<ResXmlEvent> getParserEvents() {
        return EmptyIterator.of();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public boolean isUnknown() {
        return true;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void linkStringReferences() {
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void merge(ResXmlNode resXmlNode) {
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ResXmlNode resXmlNode) {
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public String nodeTypeName() {
        return ResXmlNode.JSON_node_type_unknown;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void onPreRemove() {
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException {
    }

    public void setHexBytes(String str) throws NumberFormatException {
        try {
            readBytes(new BlockReader(HexUtil.fromHexSting(str)));
        } catch (IOException e) {
            s01.a("Invalid chunk hex bytes:", e.getMessage());
        }
    }

    public void setLineNumber(int i) {
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_node_type, nodeTypeName());
        jSONObject.put(ResXmlNode.JSON_type, getChunkType());
        jSONObject.put(ResXmlNode.JSON_value, getHexBytes());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    /* JADX INFO: renamed from: toXml */
    public XMLNode mo52toXml(boolean z) {
        return null;
    }

    public UnknownResXmlNode(Chunk<?> chunk) {
        super(chunk);
    }
}
