package com.reandroid.arsc.chunk.xml;

import android.content.res.XmlResourceParser;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.container.WrappedBlock;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.xml.XMLNode;
import com.reandroid.xml.base.Node;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ResXmlNode extends WrappedBlock implements Node, JSONConvert<JSONObject> {
    private static final String FEATURE_INDENT_OUTPUT = "http://xmlpull.org/v1/doc/features.html#indent-output";
    public static final String JSON_encoding = ObjectsUtil.of("encoding");
    public static final String JSON_node_type = ObjectsUtil.of("node_type");
    public static final String JSON_node_type_document = ObjectsUtil.of("document");
    public static final String JSON_node_type_element = ObjectsUtil.of("element");
    public static final String JSON_node_type_text = ObjectsUtil.of("text");
    public static final String JSON_node_type_comment = ObjectsUtil.of("comment");
    public static final String JSON_node_type_unknown = ObjectsUtil.of("unknown");
    public static final String JSON_name = ObjectsUtil.of(TypeBlock.NAME_name);
    public static final String JSON_id = ObjectsUtil.of(TypeBlock.NAME_id);
    public static final String JSON_comment = ObjectsUtil.of("comment");
    public static final String JSON_namespaces = ObjectsUtil.of("namespaces");
    public static final String JSON_uri = ObjectsUtil.of("uri");
    public static final String JSON_prefix = ObjectsUtil.of("prefix");
    public static final String JSON_line = ObjectsUtil.of("line");
    public static final String JSON_line_end = ObjectsUtil.of("line_end");
    public static final String JSON_attributes = ObjectsUtil.of("attributes");
    public static final String JSON_nodes = ObjectsUtil.of("nodes");
    public static final String JSON_value = ObjectsUtil.of("value");
    public static final String JSON_type = ObjectsUtil.of("type");

    public ResXmlNode(Block block) {
        super(block);
    }

    public static boolean isTextEvent(int i) {
        return i == 4 || i == 6 || i == 7 || i == 5;
    }

    public static void setIndent(XmlSerializer xmlSerializer, boolean z) {
        try {
            xmlSerializer.setFeature(FEATURE_INDENT_OUTPUT, z);
        } catch (Throwable unused) {
        }
    }

    public abstract int autoSetLineNumber(int i);

    public void autoSetLineNumber() {
        autoSetLineNumber(1);
    }

    @Override // 
    public abstract void fromJson(JSONObject jSONObject);

    public Block getChunk() {
        return getBaseBlock();
    }

    public int getDepth() {
        return CollectionUtil.count(getParentNodes());
    }

    public int getEndLineNumber() {
        return 0;
    }

    public int getLineNumber() {
        return getStartLineNumber();
    }

    public ResXmlNode getNext() {
        ResXmlNode resXmlNodeMo60getParentNode = mo60getParentNode();
        if (resXmlNodeMo60getParentNode instanceof ResXmlNodeTree) {
            return ((ResXmlNodeTree) resXmlNodeMo60getParentNode).m61get(getIndex() + 1);
        }
        return null;
    }

    @Override // 
    /* JADX INFO: renamed from: getParentNode */
    public ResXmlNode mo60getParentNode() {
        return (ResXmlNode) getParentInstance(ResXmlNode.class);
    }

    public Iterator<ResXmlNode> getParentNodes() {
        return new ParentNodeIterator(mo60getParentNode());
    }

    public XmlPullParser getParser() {
        return new ResXmlEventParser(getParserEvents());
    }

    public abstract Iterator<ResXmlEvent> getParserEvents();

    public ResXmlNode getPrevious() {
        ResXmlNode resXmlNodeMo60getParentNode = mo60getParentNode();
        if (resXmlNodeMo60getParentNode instanceof ResXmlNodeTree) {
            return ((ResXmlNodeTree) resXmlNodeMo60getParentNode).m61get(getIndex() - 1);
        }
        return null;
    }

    public XmlResourceParser getResourceParser() {
        return new ResXmlPullParser(getParserEvents());
    }

    public int getStartLineNumber() {
        return 0;
    }

    public boolean isComment() {
        return false;
    }

    public boolean isDocument() {
        return false;
    }

    public boolean isElement() {
        return false;
    }

    public boolean isText() {
        return false;
    }

    public boolean isUnknown() {
        return false;
    }

    public abstract void linkStringReferences();

    public abstract void merge(ResXmlNode resXmlNode);

    public abstract void mergeWithName(ResourceMergeOption resourceMergeOption, ResXmlNode resXmlNode);

    public ResXmlNode newSimilarTo(ResXmlNode resXmlNode) {
        throw new RuntimeException("Method not implemented");
    }

    public abstract String nodeTypeName();

    public abstract void onPreRemove();

    public abstract void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException;

    public boolean removeSelf() {
        throw new RuntimeException("Method not implemented");
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        serialize(xmlSerializer, true);
    }

    public abstract void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException;

    public void serializeComment(XmlSerializer xmlSerializer, String str) throws IOException {
        if (str != null) {
            xmlSerializer.comment(str);
        }
    }

    @Override // 
    public abstract JSONObject toJson();

    /* JADX INFO: renamed from: toXml */
    public abstract XMLNode mo52toXml(boolean z);

    public <T extends ResXmlNode> Iterator<T> visitParentNodes(Class<T> cls, Class<? extends ResXmlNode> cls2) {
        return InstanceIterator.of(new ParentNodeIterator(this, cls2), cls);
    }

    public static class ParentNodeIterator implements Iterator<ResXmlNode> {
        private ResXmlNode parentNode;
        private final Class<? extends ResXmlNode> upperBoundClass;

        public ParentNodeIterator(ResXmlNode resXmlNode, Class<? extends ResXmlNode> cls) {
            if (resXmlNode != null && cls != null && cls.isInstance(resXmlNode)) {
                resXmlNode = null;
            }
            this.parentNode = resXmlNode;
            this.upperBoundClass = cls;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.parentNode != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ResXmlNode next() {
            ResXmlNode resXmlNode = this.parentNode;
            ResXmlNode resXmlNodeMo60getParentNode = resXmlNode.mo60getParentNode();
            Class<? extends ResXmlNode> cls = this.upperBoundClass;
            if (resXmlNodeMo60getParentNode != null && cls != null && cls.isInstance(resXmlNodeMo60getParentNode)) {
                resXmlNodeMo60getParentNode = null;
            }
            this.parentNode = resXmlNodeMo60getParentNode;
            return resXmlNode;
        }

        public ParentNodeIterator(ResXmlNode resXmlNode) {
            this(resXmlNode, null);
        }
    }
}
