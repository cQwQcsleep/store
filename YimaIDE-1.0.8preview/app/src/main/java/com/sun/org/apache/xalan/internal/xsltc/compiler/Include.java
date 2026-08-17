package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import java.util.Iterator;
import javax.xml.XMLConstants;
import jdk.xml.internal.SecuritySupport;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Include extends TopLevelElement {
    private Stylesheet _included = null;

    public Stylesheet getIncludedStylesheet() {
        return this._included;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        XMLReader xMLReader;
        XSLTC xsltc = parser.getXSLTC();
        Stylesheet currentStylesheet = parser.getCurrentStylesheet();
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_HREF);
        try {
            try {
                if (currentStylesheet.checkForLoop(attribute)) {
                    parser.reportError(2, new ErrorMsg(ErrorMsg.CIRCULAR_INCLUDE_ERR, (Object) attribute, (SyntaxTreeNode) this));
                    parser.setCurrentStylesheet(currentStylesheet);
                    return;
                }
                String systemId = currentStylesheet.getSystemId();
                SourceLoader sourceLoader = currentStylesheet.getSourceLoader();
                InputSource inputSource = null;
                XMLReader xMLReader2 = null;
                if (sourceLoader != null) {
                    InputSource inputSourceLoadSource = sourceLoader.loadSource(attribute, systemId, xsltc);
                    if (inputSourceLoadSource != null) {
                        attribute = inputSourceLoadSource.getSystemId();
                        xMLReader2 = xsltc.getXMLReader();
                    } else if (parser.errorsFound()) {
                        parser.setCurrentStylesheet(currentStylesheet);
                        return;
                    }
                    xMLReader = xMLReader2;
                    inputSource = inputSourceLoadSource;
                } else {
                    xMLReader = null;
                }
                if (inputSource == null) {
                    attribute = SystemIDResolver.getAbsoluteURI(attribute, systemId);
                    String strCheckAccess = SecuritySupport.checkAccess(attribute, (String) xsltc.getProperty(XMLConstants.ACCESS_EXTERNAL_STYLESHEET), "all");
                    if (strCheckAccess != null) {
                        parser.reportError(2, new ErrorMsg(ErrorMsg.ACCESSING_XSLT_TARGET_ERR, SecuritySupport.sanitizePath(attribute), strCheckAccess, this));
                        parser.setCurrentStylesheet(currentStylesheet);
                        return;
                    }
                    inputSource = new InputSource(attribute);
                }
                SyntaxTreeNode syntaxTreeNode = xMLReader != null ? parser.parse(xMLReader, inputSource) : parser.parse(inputSource);
                if (syntaxTreeNode == null) {
                    parser.setCurrentStylesheet(currentStylesheet);
                    return;
                }
                Stylesheet stylesheetMakeStylesheet = parser.makeStylesheet(syntaxTreeNode);
                this._included = stylesheetMakeStylesheet;
                if (stylesheetMakeStylesheet == null) {
                    parser.setCurrentStylesheet(currentStylesheet);
                    return;
                }
                stylesheetMakeStylesheet.setSourceLoader(sourceLoader);
                this._included.setSystemId(attribute);
                this._included.setParentStylesheet(currentStylesheet);
                this._included.setIncludingStylesheet(currentStylesheet);
                this._included.setTemplateInlining(currentStylesheet.getTemplateInlining());
                this._included.setImportPrecedence(currentStylesheet.getImportPrecedence());
                parser.setCurrentStylesheet(this._included);
                this._included.parseContents(parser);
                Iterator<SyntaxTreeNode> itElements = this._included.elements();
                Stylesheet topLevelStylesheet = parser.getTopLevelStylesheet();
                while (itElements.hasNext()) {
                    SyntaxTreeNode next = itElements.next();
                    if (next instanceof TopLevelElement) {
                        if (next instanceof Variable) {
                            topLevelStylesheet.addVariable((Variable) next);
                        } else if (next instanceof Param) {
                            topLevelStylesheet.addParam((Param) next);
                        } else {
                            topLevelStylesheet.addElement((TopLevelElement) next);
                        }
                    }
                }
                parser.setCurrentStylesheet(currentStylesheet);
            } catch (Exception e) {
                e.printStackTrace();
                parser.setCurrentStylesheet(currentStylesheet);
            }
        } catch (Throwable th) {
            parser.setCurrentStylesheet(currentStylesheet);
            throw th;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return Type.Void;
    }
}
