package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.container.BlockList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlNodeList extends BlockList<ResXmlNode> {
    public void onPreRemove(ResXmlNode resXmlNode) {
        super.onPreRemove(resXmlNode);
        resXmlNode.onPreRemove();
    }
}
