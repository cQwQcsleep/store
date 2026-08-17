package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class UnexpectedResXmlNode extends UnknownResXmlNode {
    public UnexpectedResXmlNode(ChunkType chunkType) {
        super(of(chunkType));
    }

    private BaseXmlChunk getBaseXmlChunk() {
        return (BaseXmlChunk) getChunk();
    }

    public static boolean isSet(ChunkType chunkType) {
        return chunkType == ChunkType.XML_END_ELEMENT || chunkType == ChunkType.XML_END_NAMESPACE || chunkType == ChunkType.XML_CDATA;
    }

    private static Chunk<?> of(ChunkType chunkType) {
        if (chunkType == ChunkType.XML_END_ELEMENT) {
            return new ResXmlEndElement();
        }
        if (chunkType == ChunkType.XML_END_NAMESPACE) {
            return new ResXmlEndNamespace();
        }
        if (chunkType == ChunkType.XML_CDATA) {
            return new ResXmlTextChunk();
        }
        y04.a("Invalid chunk type: ", chunkType);
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    @Override // com.reandroid.arsc.chunk.xml.UnknownResXmlNode, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void fromJson(JSONObject jSONObject) throws JSONException, NumberFormatException {
        String strNodeTypeName = nodeTypeName();
        String str = ResXmlNode.JSON_node_type;
        if (strNodeTypeName.equals(jSONObject.optString(str, (String) null))) {
            setComment(jSONObject.optString(ResXmlNode.JSON_comment, (String) null));
            setUri(jSONObject.optString(ResXmlNode.JSON_uri, (String) null));
            setString(jSONObject.optString(ResXmlNode.JSON_value, (String) null));
        } else {
            throw new JSONException("Expecting: " + nodeTypeName() + ", but found: " + jSONObject.optString(str, (String) null));
        }
    }

    public String getComment() {
        return getBaseXmlChunk().getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.UnknownResXmlNode, com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getLineNumber() {
        return getBaseXmlChunk().getLineNumber();
    }

    public String getString() {
        BaseXmlChunk baseXmlChunk = getBaseXmlChunk();
        return baseXmlChunk.getString(baseXmlChunk.getStringReference());
    }

    public String getUri() {
        return getBaseXmlChunk().getUri();
    }

    @Override // com.reandroid.arsc.chunk.xml.UnknownResXmlNode, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void linkStringReferences() {
        getBaseXmlChunk().linkStringReferences();
    }

    @Override // com.reandroid.arsc.chunk.xml.UnknownResXmlNode, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void onPreRemove() {
        getBaseXmlChunk().onPreRemove();
    }

    public void setComment(String str) {
        getBaseXmlChunk().setComment(str);
    }

    public void setString(String str) {
        getBaseXmlChunk().setString(str);
    }

    public void setUri(String str) {
        BaseXmlChunk baseXmlChunk = getBaseXmlChunk();
        ResXmlString orCreateString = baseXmlChunk.getOrCreateString(str);
        baseXmlChunk.setNamespaceReference(orCreateString != null ? orCreateString.getIndex() : -1);
    }

    @Override // com.reandroid.arsc.chunk.xml.UnknownResXmlNode, com.reandroid.arsc.chunk.xml.ResXmlNode
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_node_type, nodeTypeName());
        jSONObject.put(ResXmlNode.JSON_type, getChunkType());
        jSONObject.put(ResXmlNode.JSON_comment, getComment());
        jSONObject.put(ResXmlNode.JSON_uri, getUri());
        jSONObject.put(ResXmlNode.JSON_value, getString());
        return jSONObject;
    }

    public static boolean isSet(int i) {
        return isSet(ChunkType.get((short) i));
    }
}
