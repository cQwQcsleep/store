package com.reandroid.arsc.list;

import com.reandroid.arsc.chunk.Overlayable;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class OverlayableList extends BlockList<Overlayable> implements Iterable<Overlayable>, JSONConvert<JSONArray> {
    public Overlayable createNext() {
        Overlayable overlayable = new Overlayable();
        add(overlayable);
        return overlayable;
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            createNext().fromJson(jSONArray.getJSONObject(i));
        }
    }

    public Overlayable get(String str, String str2) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Overlayable overlayable = (Overlayable) it.next();
            if (ObjectsUtil.equals(str, overlayable.getName()) || ObjectsUtil.equals(str2, overlayable.getActor())) {
                return overlayable;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void merge(OverlayableList overlayableList) {
        if (overlayableList == null || overlayableList == this) {
            return;
        }
        Iterator it = overlayableList.iterator();
        while (it.hasNext()) {
            Overlayable overlayable = (Overlayable) it.next();
            Overlayable overlayableCreateNext = get(overlayable.getName(), overlayable.getActor());
            if (overlayableCreateNext == null) {
                overlayableCreateNext = get(overlayable.getName());
            }
            if (overlayableCreateNext == null) {
                overlayableCreateNext = createNext();
            }
            overlayableCreateNext.merge(overlayable);
        }
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 0) {
            xmlPullParser.next();
        }
        XMLUtil.ensureStartTag(xmlPullParser);
        if (PackageBlock.TAG_resources.equals(xmlPullParser.getName())) {
            xmlPullParser.next();
            XMLUtil.ensureStartTag(xmlPullParser);
        }
        while (xmlPullParser.getEventType() != 3 && xmlPullParser.getEventType() != 1) {
            createNext().parse(xmlPullParser);
            XMLUtil.ensureTag(xmlPullParser);
        }
        if (xmlPullParser.getEventType() == 3) {
            xmlPullParser.next();
            XMLUtil.ensureTag(xmlPullParser);
        }
        if (xmlPullParser.getEventType() == 3 && PackageBlock.TAG_resources.equals(xmlPullParser.getName())) {
            xmlPullParser.next();
            XMLUtil.ensureTag(xmlPullParser);
        }
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument("utf-8", null);
        xmlSerializer.startTag(null, PackageBlock.TAG_resources);
        Iterator it = iterator();
        while (it.hasNext()) {
            ((Overlayable) it.next()).serialize(xmlSerializer);
        }
        xmlSerializer.endTag(null, PackageBlock.TAG_resources);
        xmlSerializer.endDocument();
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m74toJson() {
        if (isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = iterator();
        while (it.hasNext()) {
            jSONArray.put(((Overlayable) it.next()).toJson());
        }
        return jSONArray;
    }

    public Overlayable get(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Overlayable overlayable = (Overlayable) it.next();
            if (ObjectsUtil.equals(str, overlayable.getName())) {
                return overlayable;
            }
        }
        return null;
    }
}
