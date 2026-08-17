package com.sun.org.apache.xalan.internal.xsltc.runtime;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMCache;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMAdapter;
import com.sun.org.apache.xalan.internal.xsltc.dom.KeyIndex;
import com.sun.org.apache.xalan.internal.xsltc.runtime.output.TransletOutputHandlerFactory;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Templates;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractTranslet implements Translet {
    public static final int CURRENT_TRANSLET_VERSION = 101;
    private static final String EMPTYSTRING = "";
    public static final int FIRST_TRANSLET_VERSION = 100;
    private static final String ID_INDEX_NAME = "##id";
    public static final int VER_SPLIT_NAMES_ARRAY = 101;
    private boolean _overrideDefaultParser;
    protected String[] namesArray;
    protected String[] namespaceArray;
    protected int[] typesArray;
    protected String[] urisArray;
    public String _version = "1.0";
    public String _method = null;
    public String _encoding = "UTF-8";
    public boolean _omitHeader = false;
    public String _standalone = null;
    public boolean _isStandalone = false;
    public String _doctypePublic = null;
    public String _doctypeSystem = null;
    public boolean _indent = false;
    public String _mediaType = null;
    public List<String> _cdata = null;
    public int _indentamount = -1;
    public Map<String, DecimalFormat> _formatSymbols = null;
    protected int transletVersion = 100;
    protected Templates _templates = null;
    protected boolean _hasIdCall = false;
    protected StringValueHandler stringValueHandler = new StringValueHandler();
    protected int pbase = 0;
    protected int pframe = 0;
    protected List<Object> paramsStack = new ArrayList();
    protected DOMImplementation _domImplementation = null;
    private FileOutputStream output = null;
    private String _accessExternalStylesheet = "all";
    private String _accessErr = null;
    private MessageHandler _msgHandler = null;
    private Map<String, KeyIndex> _keyIndexes = null;
    private KeyIndex _emptyKeyIndex = null;
    private int _indexSize = 0;
    private int _currentRootForKeys = 0;
    private DOMCache _domCache = null;
    private Map<String, Class<?>> _auxClasses = null;

    private final void buildIDIndex(DOM dom) {
        setRootForKeys(dom.getDocument());
        if (dom instanceof DOMEnhancedForDTM) {
            DOMEnhancedForDTM dOMEnhancedForDTM = (DOMEnhancedForDTM) dom;
            if (dOMEnhancedForDTM.hasDOMSource()) {
                buildKeyIndex(ID_INDEX_NAME, dom);
                return;
            }
            Map<String, Integer> elementsWithIDs = dOMEnhancedForDTM.getElementsWithIDs();
            if (elementsWithIDs == null) {
                return;
            }
            boolean z = false;
            for (Map.Entry<String, Integer> entry : elementsWithIDs.entrySet()) {
                buildKeyIndex(ID_INDEX_NAME, dom.getNodeHandle(entry.getValue().intValue()), entry.getKey());
                z = true;
            }
            if (z) {
                setKeyIndexDom(ID_INDEX_NAME, dom);
            }
        }
    }

    private KeyIndex buildKeyIndexHelper(String str) {
        if (this._keyIndexes == null) {
            this._keyIndexes = new HashMap();
        }
        KeyIndex keyIndex = this._keyIndexes.get(str);
        if (keyIndex != null) {
            return keyIndex;
        }
        Map<String, KeyIndex> map = this._keyIndexes;
        KeyIndex keyIndex2 = new KeyIndex(this._indexSize);
        map.put(str, keyIndex2);
        return keyIndex2;
    }

    private void setRootForKeys(int i) {
        this._currentRootForKeys = i;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public void addAuxiliaryClass(Class<?> cls) {
        if (this._auxClasses == null) {
            this._auxClasses = new HashMap();
        }
        this._auxClasses.put(cls.getName(), cls);
    }

    public void addCdataElement(String str) {
        if (this._cdata == null) {
            this._cdata = new ArrayList();
        }
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf <= 0) {
            this._cdata.add(null);
            this._cdata.add(str);
        } else {
            String strSubstring = str.substring(0, iLastIndexOf);
            String strSubstring2 = str.substring(iLastIndexOf + 1);
            this._cdata.add(strSubstring);
            this._cdata.add(strSubstring2);
        }
    }

    public void addDecimalFormat(String str, DecimalFormatSymbols decimalFormatSymbols) {
        if (this._formatSymbols == null) {
            this._formatSymbols = new HashMap();
        }
        if (str == null) {
            str = "";
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        if (decimalFormatSymbols != null) {
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        }
        this._formatSymbols.put(str, decimalFormat);
    }

    public final Object addParameter(String str, Object obj, boolean z) {
        Parameter parameter;
        int i = this.pframe;
        do {
            i--;
            int i2 = this.pbase;
            List<Object> list = this.paramsStack;
            if (i < i2) {
                int i3 = this.pframe;
                this.pframe = i3 + 1;
                list.add(i3, new Parameter(str, obj, z));
                return obj;
            }
            parameter = (Parameter) list.get(i);
        } while (!parameter._name.equals(str));
        if (!parameter._isDefault && z) {
            return parameter._value;
        }
        parameter._value = obj;
        parameter._isDefault = z;
        return obj;
    }

    public void buildKeyIndex(String str, DOM dom) {
        buildKeyIndexHelper(str).setDom(dom, dom.getDocument());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public void buildKeys(DOM dom, DTMAxisIterator dTMAxisIterator, SerializationHandler serializationHandler, int i) throws TransletException {
    }

    public final void characters(String str, SerializationHandler serializationHandler) throws TransletException {
        if (str != null) {
            try {
                serializationHandler.characters(str);
            } catch (Exception e) {
                throw new TransletException(e);
            }
        }
    }

    public void clearParameters() {
        this.pframe = 0;
        this.pbase = 0;
        this.paramsStack.clear();
    }

    public void closeOutputHandler(SerializationHandler serializationHandler) {
        try {
            serializationHandler.endDocument();
            serializationHandler.close();
            FileOutputStream fileOutputStream = this.output;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Exception unused) {
        }
    }

    public KeyIndex createKeyIndex() {
        return new KeyIndex(this._indexSize);
    }

    public final void displayMessage(String str) {
        MessageHandler messageHandler = this._msgHandler;
        if (messageHandler == null) {
            System.err.println(str);
        } else {
            messageHandler.displayMessage(str);
        }
    }

    public String getAccessError() {
        return this._accessErr;
    }

    public String getAllowedProtocols() {
        return this._accessExternalStylesheet;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public Class<?> getAuxiliaryClass(String str) {
        Map<String, Class<?>> map = this._auxClasses;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public DOMCache getDOMCache() {
        return this._domCache;
    }

    public final DecimalFormat getDecimalFormat(String str) {
        Map<String, DecimalFormat> map = this._formatSymbols;
        if (map == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        DecimalFormat decimalFormat = map.get(str);
        return decimalFormat == null ? this._formatSymbols.get("") : decimalFormat;
    }

    public KeyIndex getKeyIndex(String str) {
        Map<String, KeyIndex> map = this._keyIndexes;
        if (map == null) {
            KeyIndex keyIndex = this._emptyKeyIndex;
            if (keyIndex != null) {
                return keyIndex;
            }
            KeyIndex keyIndex2 = new KeyIndex(1);
            this._emptyKeyIndex = keyIndex2;
            return keyIndex2;
        }
        KeyIndex keyIndex3 = map.get(str);
        if (keyIndex3 != null) {
            return keyIndex3;
        }
        KeyIndex keyIndex4 = this._emptyKeyIndex;
        if (keyIndex4 != null) {
            return keyIndex4;
        }
        KeyIndex keyIndex5 = new KeyIndex(1);
        this._emptyKeyIndex = keyIndex5;
        return keyIndex5;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public String[] getNamesArray() {
        return this.namesArray;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public String[] getNamespaceArray() {
        return this.namespaceArray;
    }

    public final Object getParameter(String str) {
        Parameter parameter;
        String strMapQNameToJavaName = BasisLibrary.mapQNameToJavaName(str);
        int i = this.pframe;
        do {
            i--;
            if (i < this.pbase) {
                return null;
            }
            parameter = (Parameter) this.paramsStack.get(i);
        } while (!parameter._name.equals(strMapQNameToJavaName));
        return parameter._value;
    }

    public Templates getTemplates() {
        return this._templates;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public int[] getTypesArray() {
        return this.typesArray;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public String[] getUrisArray() {
        return this.urisArray;
    }

    public boolean hasIdCall() {
        return this._hasIdCall;
    }

    public final DOMAdapter makeDOMAdapter(DOM dom) throws TransletException {
        setRootForKeys(dom.getDocument());
        return new DOMAdapter(dom, this.namesArray, this.urisArray, this.typesArray, this.namespaceArray);
    }

    public Document newDocument(String str, String str2) throws ParserConfigurationException {
        if (this._domImplementation == null) {
            this._domImplementation = JdkXmlUtils.getDOMFactory(this._overrideDefaultParser).newDocumentBuilder().getDOMImplementation();
        }
        return this._domImplementation.createDocument(str, str2, null);
    }

    public SerializationHandler openOutputHandler(String str, boolean z) throws TransletException {
        try {
            TransletOutputHandlerFactory transletOutputHandlerFactoryNewInstance = TransletOutputHandlerFactory.newInstance(this._overrideDefaultParser, this._msgHandler.getErrorListener());
            String parent = new File(str).getParent();
            if (parent != null && parent.length() > 0) {
                new File(parent).mkdirs();
            }
            this.output = new FileOutputStream(str, z);
            transletOutputHandlerFactoryNewInstance.setEncoding(this._encoding);
            transletOutputHandlerFactoryNewInstance.setOutputMethod(this._method);
            transletOutputHandlerFactoryNewInstance.setOutputStream(new BufferedOutputStream(this.output));
            transletOutputHandlerFactoryNewInstance.setOutputType(0);
            SerializationHandler serializationHandler = transletOutputHandlerFactoryNewInstance.getSerializationHandler();
            transferOutputSettings(serializationHandler);
            serializationHandler.startDocument();
            return serializationHandler;
        } catch (Exception e) {
            throw new TransletException(e);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public boolean overrideDefaultParser() {
        return this._overrideDefaultParser;
    }

    public final void popParamFrame() {
        int i = this.pbase;
        if (i <= 0) {
            return;
        }
        List<Object> list = this.paramsStack;
        int i2 = i - 1;
        this.pbase = i2;
        int iIntValue = ((Integer) list.get(i2)).intValue();
        int i3 = this.pframe;
        while (true) {
            i3--;
            int i4 = this.pbase;
            if (i3 < i4) {
                this.pframe = i4;
                this.pbase = iIntValue;
                return;
            }
            this.paramsStack.remove(i3);
        }
    }

    public final void postInitialization() {
        if (this.transletVersion < 101) {
            int length = this.namesArray.length;
            String[] strArr = new String[length];
            String[] strArr2 = new String[length];
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                String strSubstring = this.namesArray[i];
                int iLastIndexOf = strSubstring.lastIndexOf(58);
                int i2 = iLastIndexOf + 1;
                if (iLastIndexOf > -1) {
                    strArr[i] = strSubstring.substring(0, iLastIndexOf);
                }
                if (strSubstring.charAt(i2) == '@') {
                    i2 = iLastIndexOf + 2;
                    iArr[i] = 2;
                } else if (strSubstring.charAt(i2) == '?') {
                    i2 = iLastIndexOf + 2;
                    iArr[i] = 13;
                } else {
                    iArr[i] = 1;
                }
                if (i2 != 0) {
                    strSubstring = strSubstring.substring(i2);
                }
                strArr2[i] = strSubstring;
            }
            this.namesArray = strArr2;
            this.urisArray = strArr;
            this.typesArray = iArr;
        }
        if (this.transletVersion > 101) {
            BasisLibrary.runTimeError(BasisLibrary.UNKNOWN_TRANSLET_VERSION_ERR, getClass().getName());
        }
    }

    public final void prepassDocument(DOM dom) {
        setIndexSize(dom.getSize());
        buildIDIndex(dom);
    }

    public void printInternalState() {
        System.out.println("-------------------------------------");
        System.out.println("AbstractTranslet this = " + this);
        System.out.println("pbase = " + this.pbase);
        System.out.println("vframe = " + this.pframe);
        System.out.println("paramsStack.size() = " + this.paramsStack.size());
        System.out.println("namesArray.size = " + this.namesArray.length);
        System.out.println("namespaceArray.size = " + this.namespaceArray.length);
        System.out.println("");
        System.out.println("Total memory = " + Runtime.getRuntime().totalMemory());
    }

    public final void pushParamFrame() {
        this.paramsStack.add(this.pframe, Integer.valueOf(this.pbase));
        int i = this.pframe + 1;
        this.pframe = i;
        this.pbase = i;
    }

    public void setAccessError(String str) {
        this._accessErr = str;
    }

    public void setAllowedProtocols(String str) {
        this._accessExternalStylesheet = str;
    }

    public void setAuxiliaryClasses(Map<String, Class<?>> map) {
        this._auxClasses = map;
    }

    public void setDOMCache(DOMCache dOMCache) {
        this._domCache = dOMCache;
    }

    public void setIndexSize(int i) {
        if (i > this._indexSize) {
            this._indexSize = i;
        }
    }

    public void setKeyIndexDom(String str, DOM dom) {
        getKeyIndex(str).setDom(dom, dom.getDocument());
    }

    public final void setMessageHandler(MessageHandler messageHandler) {
        this._msgHandler = messageHandler;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public void setOverrideDefaultParser(boolean z) {
        this._overrideDefaultParser = z;
    }

    public void setTemplates(Templates templates) {
        this._templates = templates;
    }

    public void transferOutputSettings(SerializationHandler serializationHandler) {
        String str = this._method;
        if (str == null) {
            serializationHandler.setCdataSectionElements(this._cdata);
            String str2 = this._version;
            if (str2 != null) {
                serializationHandler.setVersion(str2);
            }
            String str3 = this._standalone;
            if (str3 != null) {
                serializationHandler.setStandalone(str3);
            }
            if (this._omitHeader) {
                serializationHandler.setOmitXMLDeclaration(true);
            }
            serializationHandler.setIndent(this._indent);
            serializationHandler.setDoctype(this._doctypeSystem, this._doctypePublic);
            serializationHandler.setIsStandalone(this._isStandalone);
            return;
        }
        if (!str.equals("xml")) {
            if (this._method.equals("html")) {
                serializationHandler.setIndent(this._indent);
                serializationHandler.setDoctype(this._doctypeSystem, this._doctypePublic);
                String str4 = this._mediaType;
                if (str4 != null) {
                    serializationHandler.setMediaType(str4);
                    return;
                }
                return;
            }
            return;
        }
        String str5 = this._standalone;
        if (str5 != null) {
            serializationHandler.setStandalone(str5);
        }
        if (this._omitHeader) {
            serializationHandler.setOmitXMLDeclaration(true);
        }
        serializationHandler.setCdataSectionElements(this._cdata);
        String str6 = this._version;
        if (str6 != null) {
            serializationHandler.setVersion(str6);
        }
        serializationHandler.setIndent(this._indent);
        int i = this._indentamount;
        if (i >= 0) {
            serializationHandler.setIndentAmount(i);
        }
        String str7 = this._doctypeSystem;
        if (str7 != null) {
            serializationHandler.setDoctype(str7, this._doctypePublic);
        }
        serializationHandler.setIsStandalone(this._isStandalone);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public abstract void transform(DOM dom, DTMAxisIterator dTMAxisIterator, SerializationHandler serializationHandler) throws TransletException;

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public final void transform(DOM dom, SerializationHandler serializationHandler) throws TransletException {
        try {
            transform(dom, dom.getIterator(), serializationHandler);
        } finally {
            this._keyIndexes = null;
        }
    }

    public void buildKeyIndex(String str, int i, String str2) {
        buildKeyIndexHelper(str).add(str2, i, this._currentRootForKeys);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.Translet
    public final Object addParameter(String str, Object obj) {
        return addParameter(BasisLibrary.mapQNameToJavaName(str), obj, false);
    }

    public SerializationHandler openOutputHandler(String str) throws TransletException {
        return openOutputHandler(str, false);
    }
}
