package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.serializer.Encodings;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.StringTokenizer;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Output extends TopLevelElement {
    private static final String HTML_VERSION = "4.0";
    private static final String STRING_SIG = "Ljava/lang/String;";
    private static final String XML_VERSION = "1.0";
    private String _cdata;
    private String _doctypePublic;
    private String _doctypeSystem;
    private String _encoding;
    private String _indentamount;
    private String _mediaType;
    private String _method;
    private String _standalone;
    private String _version;
    private boolean _omitHeader = false;
    private boolean _indent = false;
    private boolean _disabled = false;

    private void transferAttribute(Output output, String str) {
        if (hasAttribute(str) || !output.hasAttribute(str)) {
            return;
        }
        addAttribute(str, output.getAttribute(str));
    }

    public void disable() {
        this._disabled = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Output " + this._method);
    }

    public boolean enabled() {
        return !this._disabled;
    }

    public String getCdata() {
        return this._cdata;
    }

    public String getOutputMethod() {
        return this._method;
    }

    public void mergeOutput(Output output) {
        transferAttribute(output, "version");
        transferAttribute(output, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_METHOD);
        transferAttribute(output, "encoding");
        transferAttribute(output, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM);
        transferAttribute(output, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC);
        transferAttribute(output, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_MEDIATYPE);
        transferAttribute(output, "indent");
        transferAttribute(output, "omit-xml-declaration");
        transferAttribute(output, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_STANDALONE);
        if (output.hasAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS)) {
            addAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS, output.getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS) + ' ' + getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS));
        }
        String strLookupPrefix = lookupPrefix("http://xml.apache.org/xalan");
        if (strLookupPrefix != null) {
            transferAttribute(output, strLookupPrefix.concat(":indent-amount"));
        }
        String strLookupPrefix2 = lookupPrefix(com.sun.org.apache.xml.internal.utils.Constants.S_BUILTIN_OLD_EXTENSIONS_URL);
        if (strLookupPrefix2 != null) {
            transferAttribute(output, strLookupPrefix2.concat(":indent-amount"));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        Properties properties = new Properties();
        parser.setOutput(this);
        if (this._disabled) {
            return;
        }
        String attribute = getAttribute("version");
        this._version = attribute;
        if (attribute.equals("")) {
            this._version = null;
        } else {
            properties.setProperty("version", this._version);
        }
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_METHOD);
        this._method = attribute2;
        if (attribute2.equals("")) {
            this._method = null;
        }
        String str = this._method;
        if (str != null) {
            String lowerCase = str.toLowerCase();
            this._method = lowerCase;
            if (lowerCase.equals("xml") || this._method.equals("html") || this._method.equals("text") || (XML11Char.isXML11ValidQName(this._method) && this._method.indexOf(":") > 0)) {
                properties.setProperty(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_METHOD, this._method);
            } else {
                reportError(this, parser, ErrorMsg.INVALID_METHOD_IN_OUTPUT, this._method);
            }
        }
        String attribute3 = getAttribute("encoding");
        this._encoding = attribute3;
        if (attribute3.equals("")) {
            this._encoding = null;
        } else {
            try {
                new OutputStreamWriter(System.out, Encodings.convertMime2JavaEncoding(this._encoding));
            } catch (UnsupportedEncodingException unused) {
                parser.reportError(4, new ErrorMsg(ErrorMsg.UNSUPPORTED_ENCODING, (Object) this._encoding, (SyntaxTreeNode) this));
            }
            properties.setProperty("encoding", this._encoding);
        }
        String attribute4 = getAttribute("omit-xml-declaration");
        if (!attribute4.equals("")) {
            if (attribute4.equals(JdkConstants.JDK_YES)) {
                this._omitHeader = true;
            }
            properties.setProperty("omit-xml-declaration", attribute4);
        }
        String attribute5 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_STANDALONE);
        this._standalone = attribute5;
        if (attribute5.equals("")) {
            this._standalone = null;
        } else {
            properties.setProperty(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_STANDALONE, this._standalone);
        }
        String attribute6 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM);
        this._doctypeSystem = attribute6;
        if (attribute6.equals("")) {
            this._doctypeSystem = null;
        } else {
            properties.setProperty(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM, this._doctypeSystem);
        }
        String attribute7 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC);
        this._doctypePublic = attribute7;
        if (attribute7.equals("")) {
            this._doctypePublic = null;
        } else {
            properties.setProperty(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC, this._doctypePublic);
        }
        String attribute8 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS);
        this._cdata = attribute8;
        if (attribute8.equals("")) {
            this._cdata = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            StringTokenizer stringTokenizer = new StringTokenizer(this._cdata);
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                if (!XML11Char.isXML11ValidQName(strNextToken)) {
                    parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) strNextToken, (SyntaxTreeNode) this));
                }
                stringBuffer.append(parser.getQName(strNextToken).toString());
                stringBuffer.append(' ');
            }
            String string = stringBuffer.toString();
            this._cdata = string;
            properties.setProperty(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS, string);
        }
        String attribute9 = getAttribute("indent");
        if (attribute9.equals("")) {
            String str2 = this._method;
            if (str2 != null && str2.equals("html")) {
                this._indent = true;
            }
        } else {
            if (attribute9.equals(JdkConstants.JDK_YES)) {
                this._indent = true;
            }
            properties.setProperty("indent", attribute9);
        }
        String attribute10 = getAttribute(lookupPrefix("http://xml.apache.org/xalan"), "indent-amount");
        this._indentamount = attribute10;
        if (attribute10.equals("")) {
            this._indentamount = getAttribute(lookupPrefix(com.sun.org.apache.xml.internal.utils.Constants.S_BUILTIN_OLD_EXTENSIONS_URL), "indent-amount");
        }
        if (!this._indentamount.equals("")) {
            properties.setProperty("indent_amount", this._indentamount);
        }
        String attribute11 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_MEDIATYPE);
        this._mediaType = attribute11;
        if (attribute11.equals("")) {
            this._mediaType = null;
        } else {
            properties.setProperty(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_MEDIATYPE, this._mediaType);
        }
        String str3 = this._method;
        if (str3 != null) {
            if (str3.equals("html")) {
                if (this._version == null) {
                    this._version = HTML_VERSION;
                }
                if (this._mediaType == null) {
                    this._mediaType = "text/html";
                }
            } else if (this._method.equals("text") && this._mediaType == null) {
                this._mediaType = "text/plain";
            }
        }
        parser.getCurrentStylesheet().setOutputProperties(properties);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        if (this._disabled) {
            return;
        }
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(classGenerator.loadTranslet());
        String str = this._version;
        if (str != null && !str.equals("1.0")) {
            int iAddFieldref = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_version", "Ljava/lang/String;");
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, this._version));
            instructionList.append(new PUTFIELD(iAddFieldref));
        }
        if (this._method != null) {
            int iAddFieldref2 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_method", "Ljava/lang/String;");
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, this._method));
            instructionList.append(new PUTFIELD(iAddFieldref2));
        }
        if (this._encoding != null) {
            int iAddFieldref3 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_encoding", "Ljava/lang/String;");
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, this._encoding));
            instructionList.append(new PUTFIELD(iAddFieldref3));
        }
        if (this._omitHeader) {
            int iAddFieldref4 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_omitHeader", Constants.HASIDCALL_INDEX_SIG);
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, this._omitHeader));
            instructionList.append(new PUTFIELD(iAddFieldref4));
        }
        if (this._standalone != null) {
            int iAddFieldref5 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_standalone", "Ljava/lang/String;");
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, this._standalone));
            instructionList.append(new PUTFIELD(iAddFieldref5));
        }
        int iAddFieldref6 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_doctypeSystem", "Ljava/lang/String;");
        StackInstruction stackInstruction = Constants.DUP;
        instructionList.append(stackInstruction);
        instructionList.append(new PUSH(constantPool, this._doctypeSystem));
        instructionList.append(new PUTFIELD(iAddFieldref6));
        int iAddFieldref7 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_doctypePublic", "Ljava/lang/String;");
        instructionList.append(stackInstruction);
        instructionList.append(new PUSH(constantPool, this._doctypePublic));
        instructionList.append(new PUTFIELD(iAddFieldref7));
        if (this._mediaType != null) {
            int iAddFieldref8 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_mediaType", "Ljava/lang/String;");
            instructionList.append(stackInstruction);
            instructionList.append(new PUSH(constantPool, this._mediaType));
            instructionList.append(new PUTFIELD(iAddFieldref8));
        }
        if (this._indent) {
            int iAddFieldref9 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_indent", Constants.HASIDCALL_INDEX_SIG);
            instructionList.append(stackInstruction);
            instructionList.append(new PUSH(constantPool, this._indent));
            instructionList.append(new PUTFIELD(iAddFieldref9));
        }
        String str2 = this._indentamount;
        if (str2 != null && !str2.equals("")) {
            int iAddFieldref10 = constantPool.addFieldref(Constants.TRANSLET_CLASS, "_indentamount", "I");
            instructionList.append(stackInstruction);
            instructionList.append(new PUSH(constantPool, Integer.parseInt(this._indentamount)));
            instructionList.append(new PUTFIELD(iAddFieldref10));
        }
        if (this._cdata != null) {
            int iAddMethodref = constantPool.addMethodref(Constants.TRANSLET_CLASS, "addCdataElement", "(Ljava/lang/String;)V");
            StringTokenizer stringTokenizer = new StringTokenizer(this._cdata);
            while (stringTokenizer.hasMoreTokens()) {
                instructionList.append(Constants.DUP);
                instructionList.append(new PUSH(constantPool, stringTokenizer.nextToken()));
                instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
            }
        }
        instructionList.append(Constants.POP);
    }
}
