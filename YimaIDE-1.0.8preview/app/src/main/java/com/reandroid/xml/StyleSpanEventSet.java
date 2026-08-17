package com.reandroid.xml;

import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleSpanEventSet {
    private final List<StyleSpanEvent> eventList = new ArrayCollection();

    private static StyleSpanEventSet[] create(String str) {
        int length = str.length();
        StyleSpanEventSet[] styleSpanEventSetArr = new StyleSpanEventSet[length + 1];
        for (int i = 0; i < length; i++) {
            StyleSpanEventSet styleSpanEventSet = new StyleSpanEventSet();
            styleSpanEventSet.addChar(str.charAt(i));
            styleSpanEventSetArr[i] = styleSpanEventSet;
        }
        styleSpanEventSetArr[length] = new StyleSpanEventSet();
        return styleSpanEventSetArr;
    }

    private static void fill(StyleSpanEventSet[] styleSpanEventSetArr, SpanSet<?> spanSet) {
        int lastChar;
        Iterator<T> spans = spanSet.getSpans();
        while (spans.hasNext()) {
            Span span = (Span) spans.next();
            int firstChar = span.getFirstChar();
            if (firstChar < styleSpanEventSetArr.length && (lastChar = span.getLastChar()) < styleSpanEventSetArr.length) {
                StyleSpanEventSet styleSpanEventSet = styleSpanEventSetArr[firstChar];
                if (firstChar >= lastChar) {
                    styleSpanEventSet.addStartEnd(span);
                } else {
                    styleSpanEventSet.addStart(span);
                    styleSpanEventSetArr[lastChar].addEnd(span);
                }
            }
        }
    }

    private List<StyleSpanEvent> getEventList() {
        List<StyleSpanEvent> list = this.eventList;
        list.sort(CompareUtil.getComparableComparator());
        return list;
    }

    public static StyleDocument serialize(String str, SpanSet<?> spanSet) {
        try {
            StyleSpanEventSet[] styleSpanEventSetArrCreate = create(str, spanSet);
            StyleDocument styleDocument = new StyleDocument();
            DocumentSerializer documentSerializer = new DocumentSerializer(styleDocument);
            for (StyleSpanEventSet styleSpanEventSet : styleSpanEventSetArrCreate) {
                styleSpanEventSet.serialize(documentSerializer);
            }
            return styleDocument;
        } catch (IOException unused) {
            return null;
        }
    }

    public void add(StyleSpanEvent styleSpanEvent) {
        this.eventList.add(styleSpanEvent);
    }

    public void addChar(char c) {
        add(new StyleSpanEvent(c));
    }

    public void addEnd(Span span) {
        add(new StyleSpanEvent(StyleSpanEvent.TYPE_END_TAG, span));
    }

    public void addStart(Span span) {
        add(new StyleSpanEvent(StyleSpanEvent.TYPE_START_TAG, span));
    }

    public void addStartEnd(Span span) {
        add(new StyleSpanEvent(StyleSpanEvent.TYPE_START_END, span));
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        List<StyleSpanEvent> eventList = getEventList();
        int size = eventList.size();
        for (int i = 0; i < size; i++) {
            eventList.get(i).serialize(xmlSerializer);
        }
    }

    private static StyleSpanEventSet[] create(String str, SpanSet<?> spanSet) {
        StyleSpanEventSet[] styleSpanEventSetArrCreate = create(str);
        fill(styleSpanEventSetArrCreate, spanSet);
        return styleSpanEventSetArrCreate;
    }
}
