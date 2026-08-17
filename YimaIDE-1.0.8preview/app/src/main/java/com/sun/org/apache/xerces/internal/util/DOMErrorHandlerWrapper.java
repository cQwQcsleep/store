package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.dom.DOMErrorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMLocatorImpl;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMLocator;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMErrorHandlerWrapper implements XMLErrorHandler, DOMErrorHandler {
    boolean eStatus;
    public Node fCurrentNode;
    protected final DOMErrorImpl fDOMError;
    protected DOMErrorHandler fDomErrorHandler;
    protected final XMLErrorCode fErrorCode;
    protected PrintWriter fOut;

    public static class DOMErrorTypeMap {
        private static final Map<XMLErrorCode, String> fgDOMErrorTypeTable;

        static {
            HashMap map = new HashMap();
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInCDSect"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "TwoColonsInQName"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ColonNotLegalWithNS"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInProlog"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "CDEndInContent"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "CDSectUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "DoctypeNotAllowed"), "doctype-not-allowed");
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ETagRequired"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EqRequiredInAttribute"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "OpenQuoteExpected"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "CloseQuoteExpected"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ETagUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MarkupNotRecognizedInContent"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "DoctypeIllegalInContent"), "doctype-not-allowed");
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInAttValue"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInPI"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInInternalSubset"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "QuoteRequiredInAttValue"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "LessthanInAttValue"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "AttributeValueUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PITargetRequired"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "SpaceRequiredInPI"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PIUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ReservedPITarget"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PI_NOT_IN_ONE_ENTITY"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PINotInOneEntity"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid"), MsgKey.ER_UNSUPPORTED_ENCODING);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported"), MsgKey.ER_UNSUPPORTED_ENCODING);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInEntityValue"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInExternalSubset"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInIgnoreSect"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInPublicID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInSystemID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "SpaceRequiredAfterSYSTEM"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "QuoteRequiredInSystemID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "SystemIDUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "SpaceRequiredAfterPUBLIC"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "QuoteRequiredInPublicID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PublicIDUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PubidCharIllegal"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "SpaceRequiredBetweenPublicAndSystem"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ROOT_ELEMENT_TYPE_REQUIRED"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "DoctypedeclUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PEReferenceWithinMarkup"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_CONTENTSPEC_REQUIRED_IN_ELEMENTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementDeclUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_CLOSE_PAREN_REQUIRED_IN_MIXED"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MixedContentUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "AttNameRequiredInAttDef"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "AttTypeRequiredInAttDef"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ATTRIBUTE_DEFINITION"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NAME_REQUIRED_IN_NOTATIONTYPE"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "NotationTypeUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NMTOKEN_REQUIRED_IN_ENUMERATION"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EnumerationUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DISTINCT_TOKENS_IN_ENUMERATION"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DISTINCT_NOTATION_IN_ENUMERATION"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "IncludeSectUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "IgnoreSectUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "NameRequiredInPEReference"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "SemicolonRequiredInPEReference"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_PEDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityDeclUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ExternalIDRequired"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_PUBIDLITERAL_IN_EXTERNALID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_AFTER_PUBIDLITERAL_IN_EXTERNALID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_SYSTEMLITERAL_IN_EXTERNALID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_URI_FRAGMENT_IN_SYSTEMID"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ExternalIDorPublicIDRequired"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "NotationDeclUnterminated"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ReferenceToExternalEntity"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ReferenceToUnparsedEntity"), MsgKey.ER_WF_INVALID_CHARACTER);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingNotSupported"), MsgKey.ER_UNSUPPORTED_ENCODING);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingRequired"), MsgKey.ER_UNSUPPORTED_ENCODING);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementXMLNSPrefix"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementPrefixUnbound"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "AttributePrefixUnbound"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "EmptyPrefixedAttName"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            map.put(new XMLErrorCode("http://www.w3.org/TR/1998/REC-xml-19980210", "PrefixDeclared"), MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
            fgDOMErrorTypeTable = Collections.unmodifiableMap(map);
        }

        private DOMErrorTypeMap() {
        }

        public static String getDOMErrorType(XMLErrorCode xMLErrorCode) {
            return fgDOMErrorTypeTable.get(xMLErrorCode);
        }
    }

    public DOMErrorHandlerWrapper() {
        this.eStatus = true;
        this.fErrorCode = new XMLErrorCode(null, null);
        this.fDOMError = new DOMErrorImpl();
        this.fOut = new PrintWriter(System.err);
    }

    private void printError(DOMError dOMError) {
        short severity = dOMError.getSeverity();
        this.fOut.print("[");
        if (severity == 1) {
            this.fOut.print("Warning");
        } else {
            PrintWriter printWriter = this.fOut;
            if (severity == 2) {
                printWriter.print("Error");
            } else {
                printWriter.print("FatalError");
                this.eStatus = false;
            }
        }
        this.fOut.print("] ");
        DOMLocator location = dOMError.getLocation();
        if (location != null) {
            this.fOut.print(location.getLineNumber());
            this.fOut.print(":");
            this.fOut.print(location.getColumnNumber());
            this.fOut.print(":");
            this.fOut.print(location.getByteOffset());
            this.fOut.print(",");
            this.fOut.print(location.getUtf16Offset());
            Node relatedNode = location.getRelatedNode();
            if (relatedNode != null) {
                this.fOut.print("[");
                this.fOut.print(relatedNode.getNodeName());
                this.fOut.print("]");
            }
            String uri = location.getUri();
            if (uri != null) {
                int iLastIndexOf = uri.lastIndexOf(47);
                if (iLastIndexOf != -1) {
                    uri = uri.substring(iLastIndexOf + 1);
                }
                this.fOut.print(": ");
                this.fOut.print(uri);
            }
        }
        this.fOut.print(":");
        this.fOut.print(dOMError.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void error(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        dOMErrorImpl.fSeverity = (short) 2;
        dOMErrorImpl.fException = xMLParseException;
        dOMErrorImpl.fType = str2;
        String message = xMLParseException.getMessage();
        dOMErrorImpl.fMessage = message;
        dOMErrorImpl.fRelatedData = message;
        DOMLocatorImpl dOMLocatorImpl = this.fDOMError.fLocator;
        if (dOMLocatorImpl != null) {
            dOMLocatorImpl.fColumnNumber = xMLParseException.getColumnNumber();
            dOMLocatorImpl.fLineNumber = xMLParseException.getLineNumber();
            dOMLocatorImpl.fUtf16Offset = xMLParseException.getCharacterOffset();
            dOMLocatorImpl.fUri = xMLParseException.getExpandedSystemId();
            dOMLocatorImpl.fRelatedNode = this.fCurrentNode;
        }
        DOMErrorHandler dOMErrorHandler = this.fDomErrorHandler;
        if (dOMErrorHandler != null) {
            dOMErrorHandler.handleError(this.fDOMError);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void fatalError(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        dOMErrorImpl.fSeverity = (short) 3;
        dOMErrorImpl.fException = xMLParseException;
        this.fErrorCode.setValues(str, str2);
        String dOMErrorType = DOMErrorTypeMap.getDOMErrorType(this.fErrorCode);
        DOMErrorImpl dOMErrorImpl2 = this.fDOMError;
        if (dOMErrorType != null) {
            str2 = dOMErrorType;
        }
        dOMErrorImpl2.fType = str2;
        String message = xMLParseException.getMessage();
        dOMErrorImpl2.fMessage = message;
        dOMErrorImpl2.fRelatedData = message;
        DOMLocatorImpl dOMLocatorImpl = this.fDOMError.fLocator;
        if (dOMLocatorImpl != null) {
            dOMLocatorImpl.fColumnNumber = xMLParseException.getColumnNumber();
            dOMLocatorImpl.fLineNumber = xMLParseException.getLineNumber();
            dOMLocatorImpl.fUtf16Offset = xMLParseException.getCharacterOffset();
            dOMLocatorImpl.fUri = xMLParseException.getExpandedSystemId();
            dOMLocatorImpl.fRelatedNode = this.fCurrentNode;
        }
        DOMErrorHandler dOMErrorHandler = this.fDomErrorHandler;
        if (dOMErrorHandler != null) {
            dOMErrorHandler.handleError(this.fDOMError);
        }
    }

    public DOMErrorHandler getErrorHandler() {
        return this.fDomErrorHandler;
    }

    @Override // org.w3c.dom.DOMErrorHandler
    public boolean handleError(DOMError dOMError) {
        printError(dOMError);
        return this.eStatus;
    }

    public void setErrorHandler(DOMErrorHandler dOMErrorHandler) {
        this.fDomErrorHandler = dOMErrorHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void warning(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        dOMErrorImpl.fSeverity = (short) 1;
        dOMErrorImpl.fException = xMLParseException;
        dOMErrorImpl.fType = str2;
        String message = xMLParseException.getMessage();
        dOMErrorImpl.fMessage = message;
        dOMErrorImpl.fRelatedData = message;
        DOMLocatorImpl dOMLocatorImpl = this.fDOMError.fLocator;
        if (dOMLocatorImpl != null) {
            dOMLocatorImpl.fColumnNumber = xMLParseException.getColumnNumber();
            dOMLocatorImpl.fLineNumber = xMLParseException.getLineNumber();
            dOMLocatorImpl.fUtf16Offset = xMLParseException.getCharacterOffset();
            dOMLocatorImpl.fUri = xMLParseException.getExpandedSystemId();
            dOMLocatorImpl.fRelatedNode = this.fCurrentNode;
        }
        DOMErrorHandler dOMErrorHandler = this.fDomErrorHandler;
        if (dOMErrorHandler != null) {
            dOMErrorHandler.handleError(this.fDOMError);
        }
    }

    public DOMErrorHandlerWrapper(DOMErrorHandler dOMErrorHandler) {
        this.eStatus = true;
        this.fErrorCode = new XMLErrorCode(null, null);
        this.fDOMError = new DOMErrorImpl();
        this.fDomErrorHandler = dOMErrorHandler;
    }
}
