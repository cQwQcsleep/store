package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.xml.base.NodeTree;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ResXmlNodeTree extends ResXmlNode implements NodeTree<ResXmlNode> {
    private List<ResXmlNode> CHILD_NODES;
    private ResXmlNode PARENT_NODE;

    public ResXmlNodeTree(Block block) {
        super(block);
    }

    public boolean add(ResXmlNode resXmlNode) {
        return getNodeList().add(resXmlNode);
    }

    public void clear() {
        getNodeList().clearChildes();
    }

    public int countIf(Predicate<? super ResXmlNode> predicate) {
        return getNodeList().countIf(predicate);
    }

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public ResXmlNode m61get(int i) {
        return (ResXmlNode) getNodeList().get(i);
    }

    public abstract ResXmlNodeList getNodeList();

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    /* JADX INFO: renamed from: getParentNode, reason: merged with bridge method [inline-methods] */
    public ResXmlNodeTree mo60getParentNode() {
        return (ResXmlNodeTree) super.mo60getParentNode();
    }

    public int indexOf(ResXmlNode resXmlNode) {
        return getNodeList().indexOf(resXmlNode);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<ResXmlNode> iterator() {
        return getNodeList().iterator();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void merge(ResXmlNode resXmlNode) {
        if (resXmlNode == this) {
            return;
        }
        for (ResXmlNode resXmlNode2 : (ResXmlNodeTree) resXmlNode) {
            newSimilarTo(resXmlNode2).merge(resXmlNode2);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ResXmlNode resXmlNode) {
        for (ResXmlNode resXmlNode2 : (ResXmlNodeTree) resXmlNode) {
            newSimilarTo(resXmlNode2).mergeWithName(resourceMergeOption, resXmlNode2);
        }
    }

    public void moveTo(ResXmlNode resXmlNode, int i) {
        getNodeList().moveTo(resXmlNode, i);
    }

    public abstract ResXmlNode newDocument();

    public abstract ResXmlNode newElement();

    public ResXmlNode newForNodeJson(JSONObject jSONObject) {
        String string = jSONObject.getString(ResXmlNode.JSON_node_type);
        return ResXmlNode.JSON_node_type_unknown.equals(string) ? newUnknown(ChunkType.get((short) jSONObject.optInt(ResXmlNode.JSON_type, 0))) : newForNodeTypeName(string);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    public ResXmlNode newForNodeTypeName(String str) throws JSONException {
        if (ResXmlNode.JSON_node_type_element.equals(str)) {
            return newElement();
        }
        if (ResXmlNode.JSON_node_type_document.equals(str)) {
            return newDocument();
        }
        if (ResXmlNode.JSON_node_type_text.equals(str) || ResXmlNode.JSON_node_type_comment.equals(str)) {
            return newText();
        }
        throw new JSONException("Unknown node type name: " + str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public ResXmlNode newSimilarTo(ResXmlNode resXmlNode) {
        if (resXmlNode.isElement()) {
            return newElement();
        }
        if (resXmlNode.isText() || resXmlNode.isComment()) {
            return newText();
        }
        if (resXmlNode.isDocument()) {
            return newDocument();
        }
        if (resXmlNode.isUnknown()) {
            return newUnknown();
        }
        ib0.a("Unknown node class: ", resXmlNode.getClass());
        return null;
    }

    public abstract ResXmlNode newText();

    public ResXmlNode newUnknown() {
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    public void nodesFromJson(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(ResXmlNode.JSON_nodes);
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
            String str = ResXmlNode.JSON_node_type;
            if (!jSONObject2.has(str)) {
                throw new JSONException("Missing: " + str);
            }
            newForNodeJson(jSONObject2).fromJson(jSONObject2);
        }
    }

    public JSONArray nodesToJson() {
        Iterator<ResXmlNode> it = iterator();
        JSONArray jSONArray = null;
        while (it.hasNext()) {
            JSONObject jSONObjectMo51toJson = it.next().toJson();
            if (jSONObjectMo51toJson != null) {
                if (jSONArray == null) {
                    jSONArray = new JSONArray();
                }
                jSONArray.put(jSONObjectMo51toJson);
            }
        }
        return jSONArray;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void onPreRemove() {
        clear();
    }

    public Iterator<ResXmlNode> recursive() {
        return new IterableIterator<ResXmlNode, ResXmlNode>(iterator()) { // from class: com.reandroid.arsc.chunk.xml.ResXmlNodeTree.1
            public Iterator<ResXmlNode> iterator(ResXmlNode resXmlNode) {
                return resXmlNode instanceof ResXmlNodeTree ? CombiningIterator.singleOne(resXmlNode, ((ResXmlNodeTree) resXmlNode).recursive()) : SingleIterator.of(resXmlNode);
            }
        };
    }

    /* JADX INFO: renamed from: remove, reason: merged with bridge method [inline-methods] */
    public ResXmlNode m62remove(int i) {
        return (ResXmlNode) getNodeList().remove(i);
    }

    public boolean removeIf(Predicate<? super ResXmlNode> predicate) {
        return getNodeList().removeIf(predicate);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public boolean removeSelf() {
        ResXmlNodeTree resXmlNodeTreeMo60getParentNode = mo60getParentNode();
        if (resXmlNodeTreeMo60getParentNode != null) {
            return resXmlNodeTreeMo60getParentNode.remove((ResXmlNode) this);
        }
        return false;
    }

    public Iterator<ResXmlNode> reversedIterator() {
        return getNodeList().reversedIterator();
    }

    public Iterator<ResXmlNode> reversedRecursive() {
        return new IterableIterator<ResXmlNode, ResXmlNode>(reversedIterator()) { // from class: com.reandroid.arsc.chunk.xml.ResXmlNodeTree.2
            public Iterator<ResXmlNode> iterator(ResXmlNode resXmlNode) {
                return resXmlNode instanceof ResXmlNodeTree ? CombiningIterator.two(((ResXmlNodeTree) resXmlNode).reversedRecursive(), SingleIterator.of(resXmlNode)) : SingleIterator.of(resXmlNode);
            }
        };
    }

    public void serializeNodes(XmlSerializer xmlSerializer, boolean z) throws IOException {
        Iterator<ResXmlNode> it = iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer, z);
        }
    }

    public int size() {
        return getNodeList().size();
    }

    public boolean sort(Comparator<? super ResXmlNode> comparator) {
        return getNodeList().sort(comparator);
    }

    @Override // com.reandroid.arsc.container.WrappedBlock
    public String toString() {
        touchChildNodesForDebug();
        return super.toString();
    }

    public void touchChildNodesForDebug() {
        this.PARENT_NODE = mo60getParentNode();
        if (this.CHILD_NODES == null) {
            this.CHILD_NODES = new AbstractList<ResXmlNode>() { // from class: com.reandroid.arsc.chunk.xml.ResXmlNodeTree.3
                @Override // java.util.AbstractList, java.util.List
                public ResXmlNode get(int i) {
                    return ResXmlNodeTree.this.m61get(i);
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return ResXmlNodeTree.this.size();
                }
            };
        }
    }

    public ResXmlNode newUnknown(ChunkType chunkType) {
        return null;
    }

    public <T extends ResXmlNode> Iterator<T> recursive(Class<T> cls, Predicate<? super T> predicate) {
        return InstanceIterator.of(recursive(), cls, predicate);
    }

    public void add(int i, ResXmlNode resXmlNode) {
        getNodeList().add(i, resXmlNode);
    }

    public <T extends ResXmlNode> Iterator<T> recursive(Class<T> cls) {
        return InstanceIterator.of(recursive(), cls);
    }

    public boolean remove(ResXmlNode resXmlNode) {
        return getNodeList().remove(resXmlNode);
    }
}
