package com.intellij.util.xml.dom;

import com.fasterxml.aalto.in.ByteSourceBootstrapper;
import com.fasterxml.aalto.in.CharSourceBootstrapper;
import com.fasterxml.aalto.in.ReaderConfig;
import com.fasterxml.aalto.stax.StreamReaderImpl;
import com.reandroid.arsc.chunk.TypeBlock;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;
import kotlin.Metadata;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\u001a\u0010\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u001c\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r\u001a \u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007\u001a\u0018\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u001a\u0018\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {TypeBlock.NAME_config, "Lcom/fasterxml/aalto/in/ReaderConfig;", "configWithCoalescing", "createConfig", "coalesce", XmlPullParser.NO_NAMESPACE, "createXmlStreamReader", "Lorg/codehaus/stax2/XMLStreamReader2;", "input", "Ljava/io/InputStream;", "locationSource", XmlPullParser.NO_NAMESPACE, "bytes", XmlPullParser.NO_NAMESPACE, "start", XmlPullParser.NO_NAMESPACE, "size", "createNonCoalescingXmlStreamReader", "reader", "Ljava/io/Reader;", "chars", XmlPullParser.NO_NAMESPACE, "intellij.platform.util.xmlDom"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class StaxFactory {
    private static final ReaderConfig config = createConfig(false);
    private static final ReaderConfig configWithCoalescing = createConfig(true);

    private static final ReaderConfig createConfig(boolean z) {
        ReaderConfig readerConfig = new ReaderConfig();
        readerConfig.doAutoCloseInput(true);
        Boolean bool = Boolean.FALSE;
        readerConfig.setProperty("javax.xml.stream.supportDTD", bool);
        readerConfig.setProperty("javax.xml.stream.isReplacingEntityReferences", bool);
        readerConfig.setProperty(XMLInputFactory2.P_INTERN_NAMES, bool);
        readerConfig.setProperty(XMLInputFactory2.P_INTERN_NS_URIS, bool);
        readerConfig.doPreserveLocation(false);
        readerConfig.setProperty(XMLInputFactory2.P_AUTO_CLOSE_INPUT, Boolean.TRUE);
        readerConfig.setXmlEncoding("UTF-8");
        readerConfig.doCoalesceText(z);
        readerConfig.doParseLazily(true);
        return readerConfig;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public static final XMLStreamReader2 createNonCoalescingXmlStreamReader(InputStream inputStream, String str) throws XMLStreamException {
        inputStream.getClass();
        StreamReaderImpl streamReaderImplConstruct = StreamReaderImpl.construct(ByteSourceBootstrapper.construct(config.createNonShared(null, str, "UTF-8"), inputStream));
        streamReaderImplConstruct.getClass();
        return streamReaderImplConstruct;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public static final XMLStreamReader2 createXmlStreamReader(byte[] bArr) throws XMLStreamException {
        bArr.getClass();
        StreamReaderImpl streamReaderImplConstruct = StreamReaderImpl.construct(ByteSourceBootstrapper.construct(configWithCoalescing.createNonShared(null, null, "UTF-8"), bArr, 0, bArr.length));
        streamReaderImplConstruct.getClass();
        return streamReaderImplConstruct;
    }

    public static /* synthetic */ XMLStreamReader2 createXmlStreamReader$default(InputStream inputStream, String str, int i, Object obj) throws XMLStreamException {
        if ((i & 2) != 0) {
            str = null;
        }
        return createXmlStreamReader(inputStream, str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public static final XMLStreamReader2 createXmlStreamReader(InputStream inputStream, String str) throws XMLStreamException {
        inputStream.getClass();
        StreamReaderImpl streamReaderImplConstruct = StreamReaderImpl.construct(ByteSourceBootstrapper.construct(configWithCoalescing.createNonShared(null, str, "UTF-8"), inputStream));
        streamReaderImplConstruct.getClass();
        return streamReaderImplConstruct;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public static final XMLStreamReader2 createXmlStreamReader(char[] cArr) throws XMLStreamException {
        cArr.getClass();
        StreamReaderImpl streamReaderImplConstruct = StreamReaderImpl.construct(CharSourceBootstrapper.construct(configWithCoalescing.createNonShared(null, null, "UTF-8"), cArr, 0, cArr.length));
        streamReaderImplConstruct.getClass();
        return streamReaderImplConstruct;
    }
}
