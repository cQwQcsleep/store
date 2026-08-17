package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDDescription;
import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.stream.StaxXMLInputSource;
import com.sun.xml.internal.stream.dtd.DTDGrammarUtil;
import com.sun.xml.internal.stream.dtd.nonvalidating.DTDGrammar;
import defpackage.u01;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.xml.stream.XMLInputFactory;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDocumentScannerImpl extends XMLDocumentFragmentScannerImpl {
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    protected static final int SCANNER_STATE_DTD_EXTERNAL = 46;
    protected static final int SCANNER_STATE_DTD_EXTERNAL_DECLS = 47;
    protected static final int SCANNER_STATE_DTD_INTERNAL_DECLS = 45;
    protected static final int SCANNER_STATE_NO_SUCH_ELEMENT_EXCEPTION = 48;
    protected static final int SCANNER_STATE_PROLOG = 43;
    protected static final int SCANNER_STATE_TRAILING_MISC = 44;
    protected static final int SCANNER_STATE_XML_DECL = 42;
    protected String fDoctypeName;
    protected String fDoctypePublicId;
    protected String fDoctypeSystemId;
    protected boolean fScanEndElement;
    protected boolean fSeenDoctypeDecl;
    protected ValidationManager fValidationManager;
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    private static final String[] RECOGNIZED_FEATURES = {LOAD_EXTERNAL_DTD, DISALLOW_DOCTYPE_DECL_FEATURE};
    private static final Boolean[] FEATURE_DEFAULTS = {Boolean.TRUE, Boolean.FALSE};
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String[] RECOGNIZED_PROPERTIES = {DTD_SCANNER, VALIDATION_MANAGER};
    private static final Object[] PROPERTY_DEFAULTS = {null, null};
    private static final char[] DOCTYPE = {'D', 'O', 'C', 'T', 'Y', 'P', 'E'};
    private static final char[] COMMENTSTRING = {LocaleUtility.IETF_SEPARATOR, LocaleUtility.IETF_SEPARATOR};
    protected XMLDTDScanner fDTDScanner = null;
    protected XMLStringBuffer fDTDDecl = null;
    protected boolean fReadingDTD = false;
    protected boolean fAddedListener = false;
    protected NamespaceContext fNamespaceContext = new NamespaceSupport();
    protected boolean fLoadExternalDTD = true;
    protected XMLDocumentFragmentScannerImpl.Driver fXMLDeclDriver = new XMLDeclDriver();
    protected XMLDocumentFragmentScannerImpl.Driver fPrologDriver = new PrologDriver();
    protected XMLDocumentFragmentScannerImpl.Driver fDTDDriver = null;
    protected XMLDocumentFragmentScannerImpl.Driver fTrailingMiscDriver = new TrailingMiscDriver();
    protected int fStartPos = 0;
    protected int fEndPos = 0;
    protected boolean fSeenInternalSubset = false;
    private String[] fStrings = new String[3];
    private XMLInputSource fExternalSubsetSource = null;
    private final XMLDTDDescription fDTDDescription = new XMLDTDDescription(null, null, null, null, null);

    public class ContentDriver extends XMLDocumentFragmentScannerImpl.FragmentContentDriver {
        public ContentDriver() {
            super();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        public boolean elementDepthIsZeroHook() throws IOException, XNIException {
            XMLDocumentScannerImpl.this.setScannerState(44);
            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
            xMLDocumentScannerImpl.setDriver(xMLDocumentScannerImpl.fTrailingMiscDriver);
            return true;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        public void endOfFileHook(EOFException eOFException) throws IOException, XNIException {
            XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
        }

        public void resolveExternalSubsetAndRead() throws IOException, XNIException {
            XMLDocumentScannerImpl.this.fDTDDescription.setValues(null, null, XMLDocumentScannerImpl.this.fEntityManager.getCurrentResourceIdentifier().getExpandedSystemId(), null);
            XMLDocumentScannerImpl.this.fDTDDescription.setRootName(XMLDocumentScannerImpl.this.fElementQName.rawname);
            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
            XMLInputSource externalSubset = xMLDocumentScannerImpl.fExternalSubsetResolver.getExternalSubset(xMLDocumentScannerImpl.fDTDDescription);
            if (externalSubset != null) {
                XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                xMLDocumentScannerImpl2.fDoctypeName = xMLDocumentScannerImpl2.fElementQName.rawname;
                xMLDocumentScannerImpl2.fDoctypePublicId = externalSubset.getPublicId();
                XMLDocumentScannerImpl.this.fDoctypeSystemId = externalSubset.getSystemId();
                XMLDocumentScannerImpl xMLDocumentScannerImpl3 = XMLDocumentScannerImpl.this;
                XMLDocumentHandler xMLDocumentHandler = xMLDocumentScannerImpl3.fDocumentHandler;
                if (xMLDocumentHandler != null) {
                    xMLDocumentHandler.doctypeDecl(xMLDocumentScannerImpl3.fDoctypeName, xMLDocumentScannerImpl3.fDoctypePublicId, xMLDocumentScannerImpl3.fDoctypeSystemId, null);
                }
                try {
                    XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(externalSubset);
                    do {
                    } while (XMLDocumentScannerImpl.this.fDTDScanner.scanDTDExternalSubset(true));
                } finally {
                    XMLDocumentScannerImpl xMLDocumentScannerImpl4 = XMLDocumentScannerImpl.this;
                    xMLDocumentScannerImpl4.fEntityManager.setEntityHandler(xMLDocumentScannerImpl4);
                }
            }
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        public boolean scanForDoctypeHook() throws IOException, XNIException {
            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString(XMLDocumentScannerImpl.DOCTYPE)) {
                return false;
            }
            XMLDocumentScannerImpl.this.setScannerState(24);
            return true;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        public boolean scanRootElementHook() throws IOException, XNIException {
            if (!XMLDocumentScannerImpl.this.scanStartElement()) {
                return false;
            }
            XMLDocumentScannerImpl.this.setScannerState(44);
            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
            xMLDocumentScannerImpl.setDriver(xMLDocumentScannerImpl.fTrailingMiscDriver);
            return true;
        }
    }

    public final class DTDDriver implements XMLDocumentFragmentScannerImpl.Driver {
        public DTDDriver() {
        }

        /* JADX WARN: Code duplicated, block: B:88:0x019a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:95:0x0055 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        public boolean dispatch(boolean z) throws IOException, XNIException {
            boolean zScanDTDInternalSubset;
            boolean z2;
            XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(null);
            try {
                try {
                    try {
                        XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl();
                        XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
                        if (xMLDocumentScannerImpl.fDTDScanner == null) {
                            boolean z3 = xMLDocumentScannerImpl.fEntityManager.getEntityScanner() instanceof XML11EntityScanner;
                            XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                            if (z3) {
                                xMLDocumentScannerImpl2.fDTDScanner = new XML11DTDScannerImpl();
                            } else {
                                xMLDocumentScannerImpl2.fDTDScanner = new XMLDTDScannerImpl();
                            }
                            XMLDocumentScannerImpl xMLDocumentScannerImpl3 = XMLDocumentScannerImpl.this;
                            ((XMLDTDScannerImpl) xMLDocumentScannerImpl3.fDTDScanner).reset(xMLDocumentScannerImpl3.fPropertyManager);
                        }
                        XMLDocumentScannerImpl xMLDocumentScannerImpl4 = XMLDocumentScannerImpl.this;
                        xMLDocumentScannerImpl4.fDTDScanner.setLimitAnalyzer(xMLDocumentScannerImpl4.fLimitAnalyzer);
                        while (true) {
                            XMLDocumentScannerImpl xMLDocumentScannerImpl5 = XMLDocumentScannerImpl.this;
                            switch (xMLDocumentScannerImpl5.fScannerState) {
                                case 43:
                                    xMLDocumentScannerImpl5.setEndDTDScanState();
                                    break;
                                case 44:
                                default:
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("DTDDriver#dispatch: scanner state=");
                                    sb.append(XMLDocumentScannerImpl.this.fScannerState);
                                    sb.append(" (");
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl6 = XMLDocumentScannerImpl.this;
                                    sb.append(xMLDocumentScannerImpl6.getScannerStateName(xMLDocumentScannerImpl6.fScannerState));
                                    sb.append(')');
                                    throw new XNIException(sb.toString());
                                case 45:
                                    if (xMLDocumentScannerImpl5.fDTDScanner.skipDTD(xMLDocumentScannerImpl5.fSupportDTD)) {
                                        zScanDTDInternalSubset = false;
                                    } else {
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl7 = XMLDocumentScannerImpl.this;
                                        zScanDTDInternalSubset = xMLDocumentScannerImpl7.fDTDScanner.scanDTDInternalSubset(true, xMLDocumentScannerImpl7.fStandalone, xMLDocumentScannerImpl7.fHasExternalDTD && xMLDocumentScannerImpl7.fLoadExternalDTD);
                                    }
                                    Entity.ScannedEntity currentEntity = XMLDocumentScannerImpl.this.fEntityScanner.getCurrentEntity();
                                    if (currentEntity != null) {
                                        XMLDocumentScannerImpl.this.fEndPos = currentEntity.position;
                                    }
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl8 = XMLDocumentScannerImpl.this;
                                    xMLDocumentScannerImpl8.fReadingDTD = false;
                                    if (!zScanDTDInternalSubset) {
                                        if (!xMLDocumentScannerImpl8.fEntityScanner.skipChar(93, null)) {
                                            XMLDocumentScannerImpl xMLDocumentScannerImpl9 = XMLDocumentScannerImpl.this;
                                            xMLDocumentScannerImpl9.reportFatalError("DoctypedeclNotClosed", new Object[]{xMLDocumentScannerImpl9.fDoctypeName});
                                        }
                                        XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                                        if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(62, null)) {
                                            XMLDocumentScannerImpl xMLDocumentScannerImpl10 = XMLDocumentScannerImpl.this;
                                            xMLDocumentScannerImpl10.reportFatalError("DoctypedeclUnterminated", new Object[]{xMLDocumentScannerImpl10.fDoctypeName});
                                        }
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl11 = XMLDocumentScannerImpl.this;
                                        xMLDocumentScannerImpl11.fMarkupDepth--;
                                        if (!xMLDocumentScannerImpl11.fSupportDTD) {
                                            ((XMLDocumentFragmentScannerImpl) xMLDocumentScannerImpl11).fEntityStore = xMLDocumentScannerImpl11.fEntityManager.getEntityStore();
                                            ((XMLDocumentFragmentScannerImpl) XMLDocumentScannerImpl.this).fEntityStore.reset();
                                        } else if (xMLDocumentScannerImpl11.fDoctypeSystemId != null && (xMLDocumentScannerImpl11.fValidation || xMLDocumentScannerImpl11.fLoadExternalDTD)) {
                                            xMLDocumentScannerImpl11.setScannerState(46);
                                        }
                                        XMLDocumentScannerImpl.this.setEndDTDScanState();
                                    }
                                    z2 = false;
                                    if (z && !z2) {
                                    }
                                    break;
                                case 46:
                                    xMLResourceIdentifierImpl.setValues(xMLDocumentScannerImpl5.fDoctypePublicId, xMLDocumentScannerImpl5.fDoctypeSystemId, null, null);
                                    StaxXMLInputSource staxXMLInputSourceResolveEntityAsPerStax = XMLDocumentScannerImpl.this.fEntityManager.resolveEntityAsPerStax(xMLResourceIdentifierImpl);
                                    if (!staxXMLInputSourceResolveEntityAsPerStax.isCreatedByResolver()) {
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl12 = XMLDocumentScannerImpl.this;
                                        String strCheckAccess = xMLDocumentScannerImpl12.checkAccess(xMLDocumentScannerImpl12.fDoctypeSystemId, xMLDocumentScannerImpl12.fAccessExternalDTD);
                                        if (strCheckAccess != null) {
                                            XMLDocumentScannerImpl xMLDocumentScannerImpl13 = XMLDocumentScannerImpl.this;
                                            xMLDocumentScannerImpl13.reportFatalError("AccessExternalDTD", new Object[]{SecuritySupport.sanitizePath(xMLDocumentScannerImpl13.fDoctypeSystemId), strCheckAccess});
                                        }
                                    }
                                    XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(staxXMLInputSourceResolveEntityAsPerStax.getXMLInputSource());
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl14 = XMLDocumentScannerImpl.this;
                                    if (xMLDocumentScannerImpl14.fEntityScanner.fCurrentEntity != null) {
                                        xMLDocumentScannerImpl14.setScannerState(47);
                                    } else {
                                        xMLDocumentScannerImpl14.setScannerState(43);
                                    }
                                    z2 = true;
                                    if (z) {
                                    }
                                    break;
                                case 47:
                                    if (!xMLDocumentScannerImpl5.fDTDScanner.scanDTDExternalSubset(true)) {
                                        XMLDocumentScannerImpl.this.setEndDTDScanState();
                                    } else {
                                        z2 = false;
                                        if (z) {
                                        }
                                    }
                                    break;
                            }
                        }
                        XMLDocumentScannerImpl xMLDocumentScannerImpl15 = XMLDocumentScannerImpl.this;
                        xMLDocumentScannerImpl15.fEntityManager.setEntityHandler(xMLDocumentScannerImpl15);
                        return true;
                    } catch (EOFException e) {
                        e.printStackTrace();
                        XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                        XMLDocumentScannerImpl xMLDocumentScannerImpl16 = XMLDocumentScannerImpl.this;
                        xMLDocumentScannerImpl16.fEntityManager.setEntityHandler(xMLDocumentScannerImpl16);
                        return false;
                    }
                } catch (MalformedByteSequenceException e2) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError(e2.getDomain(), e2.getKey(), e2.getArguments(), (short) 2, (Exception) e2);
                    XMLDocumentScannerImpl xMLDocumentScannerImpl17 = XMLDocumentScannerImpl.this;
                    xMLDocumentScannerImpl17.fEntityManager.setEntityHandler(xMLDocumentScannerImpl17);
                    return false;
                } catch (CharConversionException e3) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e3);
                    XMLDocumentScannerImpl xMLDocumentScannerImpl18 = XMLDocumentScannerImpl.this;
                    xMLDocumentScannerImpl18.fEntityManager.setEntityHandler(xMLDocumentScannerImpl18);
                    return false;
                }
            } catch (Throwable th) {
                XMLDocumentScannerImpl xMLDocumentScannerImpl19 = XMLDocumentScannerImpl.this;
                xMLDocumentScannerImpl19.fEntityManager.setEntityHandler(xMLDocumentScannerImpl19);
                throw th;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException, XNIException {
            dispatch(true);
            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
            if (xMLDocumentScannerImpl.fPropertyManager == null) {
                return 11;
            }
            DTDGrammar grammar = ((XMLDTDScannerImpl) xMLDocumentScannerImpl.fDTDScanner).getGrammar();
            XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
            xMLDocumentScannerImpl.dtdGrammarUtil = new DTDGrammarUtil(grammar, xMLDocumentScannerImpl2.fSymbolTable, xMLDocumentScannerImpl2.fNamespaceContext);
            return 11;
        }
    }

    public final class PrologDriver implements XMLDocumentFragmentScannerImpl.Driver {
        public PrologDriver() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException, XNIException {
            ValidationManager validationManager;
            ValidationManager validationManager2;
            while (true) {
                try {
                    XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
                    int i = xMLDocumentScannerImpl.fScannerState;
                    if (i == 21) {
                        xMLDocumentScannerImpl.fMarkupDepth++;
                        if (xMLDocumentScannerImpl.isValidNameStartChar(xMLDocumentScannerImpl.fEntityScanner.peekChar())) {
                            break;
                        }
                        XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                        if (xMLDocumentScannerImpl2.isValidNameStartHighSurrogate(xMLDocumentScannerImpl2.fEntityScanner.peekChar())) {
                            break;
                        }
                        boolean zSkipChar = XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33, null);
                        XMLDocumentScannerImpl xMLDocumentScannerImpl3 = XMLDocumentScannerImpl.this;
                        if (zSkipChar) {
                            boolean zSkipChar2 = xMLDocumentScannerImpl3.fEntityScanner.skipChar(45, null);
                            XMLDocumentScannerImpl xMLDocumentScannerImpl4 = XMLDocumentScannerImpl.this;
                            if (zSkipChar2) {
                                if (!xMLDocumentScannerImpl4.fEntityScanner.skipChar(45, null)) {
                                    XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                }
                                XMLDocumentScannerImpl.this.setScannerState(27);
                            } else {
                                boolean zSkipString = xMLDocumentScannerImpl4.fEntityScanner.skipString(XMLDocumentScannerImpl.DOCTYPE);
                                XMLDocumentScannerImpl xMLDocumentScannerImpl5 = XMLDocumentScannerImpl.this;
                                if (zSkipString) {
                                    xMLDocumentScannerImpl5.setScannerState(24);
                                    Entity.ScannedEntity currentEntity = XMLDocumentScannerImpl.this.fEntityScanner.getCurrentEntity();
                                    if (currentEntity != null) {
                                        XMLDocumentScannerImpl.this.fStartPos = currentEntity.position;
                                    }
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl6 = XMLDocumentScannerImpl.this;
                                    xMLDocumentScannerImpl6.fReadingDTD = true;
                                    if (xMLDocumentScannerImpl6.fDTDDecl == null) {
                                        xMLDocumentScannerImpl6.fDTDDecl = new XMLStringBuffer();
                                    }
                                    XMLDocumentScannerImpl.this.fDTDDecl.append("<!DOCTYPE");
                                } else {
                                    xMLDocumentScannerImpl5.reportFatalError("MarkupNotRecognizedInProlog", null);
                                }
                            }
                        } else {
                            boolean zSkipChar3 = xMLDocumentScannerImpl3.fEntityScanner.skipChar(63, null);
                            XMLDocumentScannerImpl xMLDocumentScannerImpl7 = XMLDocumentScannerImpl.this;
                            if (zSkipChar3) {
                                xMLDocumentScannerImpl7.setScannerState(23);
                            } else {
                                xMLDocumentScannerImpl7.reportFatalError("MarkupNotRecognizedInProlog", null);
                            }
                        }
                    } else if (i == 43) {
                        xMLDocumentScannerImpl.fEntityScanner.skipSpaces();
                        boolean zSkipChar4 = XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60, null);
                        XMLDocumentScannerImpl xMLDocumentScannerImpl8 = XMLDocumentScannerImpl.this;
                        if (zSkipChar4) {
                            xMLDocumentScannerImpl8.setScannerState(21);
                        } else {
                            boolean zSkipChar5 = xMLDocumentScannerImpl8.fEntityScanner.skipChar(38, XMLScanner.NameType.REFERENCE);
                            XMLDocumentScannerImpl xMLDocumentScannerImpl9 = XMLDocumentScannerImpl.this;
                            if (zSkipChar5) {
                                xMLDocumentScannerImpl9.setScannerState(28);
                            } else {
                                xMLDocumentScannerImpl9.setScannerState(22);
                            }
                        }
                    }
                    XMLDocumentScannerImpl xMLDocumentScannerImpl10 = XMLDocumentScannerImpl.this;
                    int i2 = xMLDocumentScannerImpl10.fScannerState;
                    if (i2 != 43 && i2 != 21) {
                        switch (i2) {
                            case 22:
                                xMLDocumentScannerImpl10.reportFatalError("ContentIllegalInProlog", null);
                                XMLDocumentScannerImpl.this.fEntityScanner.scanChar(null);
                                return -1;
                            case 23:
                                xMLDocumentScannerImpl10.fContentBuffer.clear();
                                XMLDocumentScannerImpl xMLDocumentScannerImpl11 = XMLDocumentScannerImpl.this;
                                xMLDocumentScannerImpl11.scanPI(xMLDocumentScannerImpl11.fContentBuffer);
                                XMLDocumentScannerImpl.this.setScannerState(43);
                                return 3;
                            case 24:
                                if (xMLDocumentScannerImpl10.fDisallowDoctype) {
                                    xMLDocumentScannerImpl10.reportFatalError("DoctypeNotAllowed", null);
                                }
                                XMLDocumentScannerImpl xMLDocumentScannerImpl12 = XMLDocumentScannerImpl.this;
                                if (xMLDocumentScannerImpl12.fSeenDoctypeDecl) {
                                    xMLDocumentScannerImpl12.reportFatalError("AlreadySeenDoctype", null);
                                }
                                XMLDocumentScannerImpl xMLDocumentScannerImpl13 = XMLDocumentScannerImpl.this;
                                xMLDocumentScannerImpl13.fSeenDoctypeDecl = true;
                                boolean zScanDoctypeDecl = xMLDocumentScannerImpl13.scanDoctypeDecl(xMLDocumentScannerImpl13.fSupportDTD);
                                XMLDocumentScannerImpl xMLDocumentScannerImpl14 = XMLDocumentScannerImpl.this;
                                if (zScanDoctypeDecl) {
                                    xMLDocumentScannerImpl14.setScannerState(45);
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl15 = XMLDocumentScannerImpl.this;
                                    xMLDocumentScannerImpl15.fSeenInternalSubset = true;
                                    if (xMLDocumentScannerImpl15.fDTDDriver == null) {
                                        xMLDocumentScannerImpl15.fDTDDriver = xMLDocumentScannerImpl15.new DTDDriver();
                                    }
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl16 = XMLDocumentScannerImpl.this;
                                    xMLDocumentScannerImpl16.setDriver(xMLDocumentScannerImpl16.fContentDriver);
                                    return XMLDocumentScannerImpl.this.fDTDDriver.next();
                                }
                                if (xMLDocumentScannerImpl14.fSeenDoctypeDecl) {
                                    Entity.ScannedEntity currentEntity2 = xMLDocumentScannerImpl14.fEntityScanner.getCurrentEntity();
                                    if (currentEntity2 != null) {
                                        XMLDocumentScannerImpl.this.fEndPos = currentEntity2.position;
                                    }
                                    XMLDocumentScannerImpl.this.fReadingDTD = false;
                                }
                                XMLDocumentScannerImpl xMLDocumentScannerImpl17 = XMLDocumentScannerImpl.this;
                                if (xMLDocumentScannerImpl17.fDoctypeSystemId != null) {
                                    if ((xMLDocumentScannerImpl17.fValidation || xMLDocumentScannerImpl17.fLoadExternalDTD) && ((validationManager2 = xMLDocumentScannerImpl17.fValidationManager) == null || !validationManager2.isCachedDTD())) {
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl18 = XMLDocumentScannerImpl.this;
                                        if (xMLDocumentScannerImpl18.fSupportDTD) {
                                            xMLDocumentScannerImpl18.setScannerState(46);
                                        } else {
                                            xMLDocumentScannerImpl18.setScannerState(43);
                                        }
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl19 = XMLDocumentScannerImpl.this;
                                        xMLDocumentScannerImpl19.setDriver(xMLDocumentScannerImpl19.fContentDriver);
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl20 = XMLDocumentScannerImpl.this;
                                        if (xMLDocumentScannerImpl20.fDTDDriver == null) {
                                            xMLDocumentScannerImpl20.fDTDDriver = xMLDocumentScannerImpl20.new DTDDriver();
                                        }
                                        return XMLDocumentScannerImpl.this.fDTDDriver.next();
                                    }
                                } else if (xMLDocumentScannerImpl17.fExternalSubsetSource != null) {
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl21 = XMLDocumentScannerImpl.this;
                                    if ((xMLDocumentScannerImpl21.fValidation || xMLDocumentScannerImpl21.fLoadExternalDTD) && ((validationManager = xMLDocumentScannerImpl21.fValidationManager) == null || !validationManager.isCachedDTD())) {
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl22 = XMLDocumentScannerImpl.this;
                                        xMLDocumentScannerImpl22.fDTDScanner.setInputSource(xMLDocumentScannerImpl22.fExternalSubsetSource);
                                        XMLDocumentScannerImpl.this.fExternalSubsetSource = null;
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl23 = XMLDocumentScannerImpl.this;
                                        if (xMLDocumentScannerImpl23.fSupportDTD) {
                                            xMLDocumentScannerImpl23.setScannerState(47);
                                        } else {
                                            xMLDocumentScannerImpl23.setScannerState(43);
                                        }
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl24 = XMLDocumentScannerImpl.this;
                                        xMLDocumentScannerImpl24.setDriver(xMLDocumentScannerImpl24.fContentDriver);
                                        XMLDocumentScannerImpl xMLDocumentScannerImpl25 = XMLDocumentScannerImpl.this;
                                        if (xMLDocumentScannerImpl25.fDTDDriver == null) {
                                            xMLDocumentScannerImpl25.fDTDDriver = xMLDocumentScannerImpl25.new DTDDriver();
                                        }
                                        return XMLDocumentScannerImpl.this.fDTDDriver.next();
                                    }
                                }
                                XMLDTDScanner xMLDTDScanner = XMLDocumentScannerImpl.this.fDTDScanner;
                                if (xMLDTDScanner != null) {
                                    xMLDTDScanner.setInputSource(null);
                                }
                                XMLDocumentScannerImpl.this.setScannerState(43);
                                return 11;
                            case 25:
                            case 26:
                            default:
                                return -1;
                            case 27:
                                xMLDocumentScannerImpl10.scanComment();
                                XMLDocumentScannerImpl.this.setScannerState(43);
                                return 5;
                            case 28:
                                xMLDocumentScannerImpl10.reportFatalError("ReferenceIllegalInProlog", null);
                                return -1;
                        }
                    }
                } catch (MalformedByteSequenceException e) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, (Exception) e);
                    return -1;
                } catch (CharConversionException e2) {
                    XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e2);
                    return -1;
                } catch (EOFException unused) {
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    return -1;
                }
            }
            XMLDocumentScannerImpl.this.setScannerState(26);
            XMLDocumentScannerImpl xMLDocumentScannerImpl26 = XMLDocumentScannerImpl.this;
            xMLDocumentScannerImpl26.setDriver(xMLDocumentScannerImpl26.fContentDriver);
            return XMLDocumentScannerImpl.this.fContentDriver.next();
        }
    }

    public final class TrailingMiscDriver implements XMLDocumentFragmentScannerImpl.Driver {
        public TrailingMiscDriver() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException, XNIException {
            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
            if (xMLDocumentScannerImpl.fEmptyElement) {
                xMLDocumentScannerImpl.fEmptyElement = false;
                return 2;
            }
            try {
                if (xMLDocumentScannerImpl.fScannerState == 34) {
                    return 8;
                }
                while (true) {
                    XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                    int i = xMLDocumentScannerImpl2.fScannerState;
                    if (i == 21) {
                        xMLDocumentScannerImpl2.fMarkupDepth++;
                        boolean zSkipChar = xMLDocumentScannerImpl2.fEntityScanner.skipChar(63, null);
                        XMLDocumentScannerImpl xMLDocumentScannerImpl3 = XMLDocumentScannerImpl.this;
                        if (zSkipChar) {
                            xMLDocumentScannerImpl3.setScannerState(23);
                        } else {
                            boolean zSkipChar2 = xMLDocumentScannerImpl3.fEntityScanner.skipChar(33, null);
                            XMLDocumentScannerImpl xMLDocumentScannerImpl4 = XMLDocumentScannerImpl.this;
                            if (zSkipChar2) {
                                xMLDocumentScannerImpl4.setScannerState(27);
                            } else {
                                boolean zSkipChar3 = xMLDocumentScannerImpl4.fEntityScanner.skipChar(47, null);
                                XMLDocumentScannerImpl xMLDocumentScannerImpl5 = XMLDocumentScannerImpl.this;
                                if (zSkipChar3) {
                                    xMLDocumentScannerImpl5.reportFatalError("MarkupNotRecognizedInMisc", null);
                                } else if (xMLDocumentScannerImpl5.isValidNameStartChar(xMLDocumentScannerImpl5.fEntityScanner.peekChar())) {
                                    XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                    XMLDocumentScannerImpl.this.scanStartElement();
                                    XMLDocumentScannerImpl.this.setScannerState(22);
                                } else {
                                    XMLDocumentScannerImpl xMLDocumentScannerImpl6 = XMLDocumentScannerImpl.this;
                                    if (xMLDocumentScannerImpl6.isValidNameStartHighSurrogate(xMLDocumentScannerImpl6.fEntityScanner.peekChar())) {
                                        XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                        XMLDocumentScannerImpl.this.scanStartElement();
                                        XMLDocumentScannerImpl.this.setScannerState(22);
                                    } else {
                                        XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                    }
                                }
                            }
                        }
                    } else if (i == 44) {
                        xMLDocumentScannerImpl2.fEntityScanner.skipSpaces();
                        XMLDocumentScannerImpl xMLDocumentScannerImpl7 = XMLDocumentScannerImpl.this;
                        if (xMLDocumentScannerImpl7.fScannerState == 34) {
                            return 8;
                        }
                        boolean zSkipChar4 = xMLDocumentScannerImpl7.fEntityScanner.skipChar(60, null);
                        XMLDocumentScannerImpl xMLDocumentScannerImpl8 = XMLDocumentScannerImpl.this;
                        if (zSkipChar4) {
                            xMLDocumentScannerImpl8.setScannerState(21);
                        } else {
                            xMLDocumentScannerImpl8.setScannerState(22);
                        }
                    }
                    XMLDocumentScannerImpl xMLDocumentScannerImpl9 = XMLDocumentScannerImpl.this;
                    int i2 = xMLDocumentScannerImpl9.fScannerState;
                    if (i2 != 21 && i2 != 44) {
                        if (i2 == 22) {
                            int iPeekChar = xMLDocumentScannerImpl9.fEntityScanner.peekChar();
                            XMLDocumentScannerImpl xMLDocumentScannerImpl10 = XMLDocumentScannerImpl.this;
                            if (iPeekChar == -1) {
                                xMLDocumentScannerImpl10.setScannerState(34);
                                return 8;
                            }
                            xMLDocumentScannerImpl10.reportFatalError("ContentIllegalInTrailingMisc", null);
                            XMLDocumentScannerImpl.this.fEntityScanner.scanChar(null);
                            XMLDocumentScannerImpl.this.setScannerState(44);
                            return 4;
                        }
                        if (i2 == 23) {
                            xMLDocumentScannerImpl9.fContentBuffer.clear();
                            XMLDocumentScannerImpl xMLDocumentScannerImpl11 = XMLDocumentScannerImpl.this;
                            xMLDocumentScannerImpl11.scanPI(xMLDocumentScannerImpl11.fContentBuffer);
                            XMLDocumentScannerImpl.this.setScannerState(44);
                            return 3;
                        }
                        if (i2 == 27) {
                            if (!xMLDocumentScannerImpl9.fEntityScanner.skipString(XMLDocumentScannerImpl.COMMENTSTRING)) {
                                XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                            }
                            XMLDocumentScannerImpl.this.scanComment();
                            XMLDocumentScannerImpl.this.setScannerState(44);
                            return 5;
                        }
                        if (i2 == 28) {
                            xMLDocumentScannerImpl9.reportFatalError("ReferenceIllegalInTrailingMisc", null);
                            XMLDocumentScannerImpl.this.setScannerState(44);
                            return 9;
                        }
                        if (i2 == 34) {
                            xMLDocumentScannerImpl9.setScannerState(48);
                            return 8;
                        }
                        if (i2 == 48) {
                            throw new NoSuchElementException("No more events to be parsed");
                        }
                        throw new XNIException("Scanner State " + XMLDocumentScannerImpl.this.fScannerState + " not Recognized ");
                    }
                }
            } catch (MalformedByteSequenceException e) {
                XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, (Exception) e);
                return -1;
            } catch (CharConversionException e2) {
                XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e2);
                return -1;
            } catch (EOFException unused) {
                XMLDocumentScannerImpl xMLDocumentScannerImpl12 = XMLDocumentScannerImpl.this;
                if (xMLDocumentScannerImpl12.fMarkupDepth != 0) {
                    xMLDocumentScannerImpl12.reportFatalError("PrematureEOF", null);
                    return -1;
                }
                xMLDocumentScannerImpl12.setScannerState(34);
                return 8;
            }
        }
    }

    public final class XMLDeclDriver implements XMLDocumentFragmentScannerImpl.Driver {
        public XMLDeclDriver() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException, XNIException {
            XMLDocumentScannerImpl.this.setScannerState(43);
            XMLDocumentScannerImpl xMLDocumentScannerImpl = XMLDocumentScannerImpl.this;
            xMLDocumentScannerImpl.setDriver(xMLDocumentScannerImpl.fPrologDriver);
            try {
                if (XMLDocumentScannerImpl.this.fEntityScanner.skipString(XMLDocumentFragmentScannerImpl.XMLDECL)) {
                    boolean zIsSpace = XMLChar.isSpace(XMLDocumentScannerImpl.this.fEntityScanner.peekChar());
                    XMLDocumentScannerImpl xMLDocumentScannerImpl2 = XMLDocumentScannerImpl.this;
                    if (zIsSpace) {
                        xMLDocumentScannerImpl2.fMarkupDepth++;
                        xMLDocumentScannerImpl2.scanXMLDeclOrTextDecl(false);
                    } else {
                        xMLDocumentScannerImpl2.fEntityManager.fCurrentEntity.position = 0;
                    }
                }
                XMLDocumentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                return 7;
            } catch (MalformedByteSequenceException e) {
                XMLDocumentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, (Exception) e);
                return -1;
            } catch (CharConversionException e2) {
                XMLDocumentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e2);
                return -1;
            } catch (EOFException unused) {
                XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                return -1;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public XMLDocumentFragmentScannerImpl.Driver createContentDriver() {
        return new ContentDriver();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.impl.XMLEntityHandler
    public void endEntity(String str, Augmentations augmentations) throws IOException, XNIException {
        super.endEntity(str, augmentations);
        if (str.equals("[xml]")) {
            if (this.fMarkupDepth == 0 && this.fDriver == this.fTrailingMiscDriver) {
                setScannerState(34);
            } else {
                u01.a();
            }
        }
    }

    public String getCharacterEncodingScheme() {
        return this.fDeclaredEncoding;
    }

    public XMLStringBuffer getDTDDecl() {
        Entity.ScannedEntity currentEntity = this.fEntityScanner.getCurrentEntity();
        XMLStringBuffer xMLStringBuffer = this.fDTDDecl;
        char[] cArr = currentEntity.ch;
        int i = this.fStartPos;
        xMLStringBuffer.append(cArr, i, this.fEndPos - i);
        if (this.fSeenInternalSubset) {
            this.fDTDDecl.append("]>");
        }
        return this.fDTDDecl;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_FEATURES;
            if (i >= strArr.length) {
                return super.getFeatureDefault(str);
            }
            if (strArr[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
            i++;
        }
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_PROPERTIES;
            if (i >= strArr.length) {
                return super.getPropertyDefault(str);
            }
            if (strArr[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        String[] recognizedFeatures = super.getRecognizedFeatures();
        int length = recognizedFeatures != null ? recognizedFeatures.length : 0;
        String[] strArr = RECOGNIZED_FEATURES;
        String[] strArr2 = new String[strArr.length + length];
        if (recognizedFeatures != null) {
            System.arraycopy(recognizedFeatures, 0, strArr2, 0, recognizedFeatures.length);
        }
        System.arraycopy(strArr, 0, strArr2, length, strArr.length);
        return strArr2;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        String[] recognizedProperties = super.getRecognizedProperties();
        int length = recognizedProperties != null ? recognizedProperties.length : 0;
        String[] strArr = RECOGNIZED_PROPERTIES;
        String[] strArr2 = new String[strArr.length + length];
        if (recognizedProperties != null) {
            System.arraycopy(recognizedProperties, 0, strArr2, 0, recognizedProperties.length);
        }
        System.arraycopy(strArr, 0, strArr2, length, strArr.length);
        return strArr2;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl
    public String getScannerStateName(int i) {
        switch (i) {
            case 42:
                return "SCANNER_STATE_XML_DECL";
            case 43:
                return "SCANNER_STATE_PROLOG";
            case 44:
                return "SCANNER_STATE_TRAILING_MISC";
            case 45:
                return "SCANNER_STATE_DTD_INTERNAL_DECLS";
            case 46:
                return "SCANNER_STATE_DTD_EXTERNAL";
            case 47:
                return "SCANNER_STATE_DTD_EXTERNAL_DECLS";
            default:
                return super.getScannerStateName(i);
        }
    }

    public int getScannetState() {
        return this.fScannerState;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner
    public int next() throws IOException, XNIException {
        return this.fDriver.next();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.xml.internal.stream.XMLBufferListener
    public void refresh(int i) {
        super.refresh(i);
        if (this.fReadingDTD) {
            Entity.ScannedEntity currentEntity = this.fEntityScanner.getCurrentEntity();
            if (currentEntity != null) {
                this.fEndPos = currentEntity.position;
            }
            XMLStringBuffer xMLStringBuffer = this.fDTDDecl;
            char[] cArr = currentEntity.ch;
            int i2 = this.fStartPos;
            xMLStringBuffer.append(cArr, i2, this.fEndPos - i2);
            this.fStartPos = i;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        super.reset(xMLComponentManager);
        this.fDoctypeName = null;
        this.fDoctypePublicId = null;
        this.fDoctypeSystemId = null;
        this.fSeenDoctypeDecl = false;
        this.fExternalSubsetSource = null;
        this.fLoadExternalDTD = xMLComponentManager.getFeature(LOAD_EXTERNAL_DTD, true);
        this.fDisallowDoctype = xMLComponentManager.getFeature(DISALLOW_DOCTYPE_DECL_FEATURE, false);
        this.fNamespaces = xMLComponentManager.getFeature("http://xml.org/sax/features/namespaces", true);
        this.fSeenInternalSubset = false;
        this.fDTDScanner = (XMLDTDScanner) xMLComponentManager.getProperty(DTD_SCANNER);
        this.fValidationManager = (ValidationManager) xMLComponentManager.getProperty(VALIDATION_MANAGER, null);
        try {
            this.fNamespaceContext = (NamespaceContext) xMLComponentManager.getProperty(NAMESPACE_CONTEXT);
        } catch (XMLConfigurationException unused) {
        }
        if (this.fNamespaceContext == null) {
            this.fNamespaceContext = new NamespaceSupport();
        }
        this.fNamespaceContext.reset();
        this.fEndPos = 0;
        this.fStartPos = 0;
        XMLStringBuffer xMLStringBuffer = this.fDTDDecl;
        if (xMLStringBuffer != null) {
            xMLStringBuffer.clear();
        }
        setScannerState(42);
        setDriver(this.fXMLDeclDriver);
    }

    public boolean scanDoctypeDecl(boolean z) throws IOException, XNIException {
        XMLDocumentHandler xMLDocumentHandler;
        if (!this.fEntityScanner.skipSpaces()) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName(XMLScanner.NameType.DOCTYPE);
        this.fDoctypeName = strScanName;
        if (strScanName == null) {
            reportFatalError("MSG_ROOT_ELEMENT_TYPE_REQUIRED", null);
        }
        if (this.fEntityScanner.skipSpaces()) {
            scanExternalID(this.fStrings, false);
            String[] strArr = this.fStrings;
            this.fDoctypeSystemId = strArr[0];
            this.fDoctypePublicId = strArr[1];
            this.fEntityScanner.skipSpaces();
        }
        boolean z2 = this.fDoctypeSystemId != null;
        this.fHasExternalDTD = z2;
        if (z && !z2 && this.fExternalSubsetResolver != null) {
            this.fDTDDescription.setValues(null, null, this.fEntityManager.getCurrentResourceIdentifier().getExpandedSystemId(), null);
            this.fDTDDescription.setRootName(this.fDoctypeName);
            XMLInputSource externalSubset = this.fExternalSubsetResolver.getExternalSubset(this.fDTDDescription);
            this.fExternalSubsetSource = externalSubset;
            this.fHasExternalDTD = externalSubset != null;
        }
        if (z && (xMLDocumentHandler = this.fDocumentHandler) != null) {
            XMLInputSource xMLInputSource = this.fExternalSubsetSource;
            String str = this.fDoctypeName;
            if (xMLInputSource == null) {
                xMLDocumentHandler.doctypeDecl(str, this.fDoctypePublicId, this.fDoctypeSystemId, null);
            } else {
                xMLDocumentHandler.doctypeDecl(str, xMLInputSource.getPublicId(), this.fExternalSubsetSource.getSystemId(), null);
            }
        }
        if (this.fEntityScanner.skipChar(91, null)) {
            return true;
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62, null)) {
            reportFatalError("DoctypedeclUnterminated", new Object[]{this.fDoctypeName});
        }
        this.fMarkupDepth--;
        return false;
    }

    public void setEndDTDScanState() {
        setScannerState(43);
        setDriver(this.fPrologDriver);
        this.fEntityManager.setEntityHandler(this);
        this.fReadingDTD = false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        super.setFeature(str, z);
        if (str.startsWith(Constants.XERCES_FEATURE_PREFIX)) {
            int length = str.length() - 31;
            if (length == 31 && str.endsWith(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
                this.fLoadExternalDTD = z;
            } else if (length == 21 && str.endsWith(Constants.DISALLOW_DOCTYPE_DECL_FEATURE)) {
                this.fDisallowDoctype = z;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException {
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startDocumentEntity(xMLInputSource);
        setScannerState(7);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        super.setProperty(str, obj);
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int length = str.length() - 33;
            if (length == 20 && str.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
                this.fDTDScanner = (XMLDTDScanner) obj;
            }
            if (length == 26 && str.endsWith(Constants.NAMESPACE_CONTEXT_PROPERTY) && obj != null) {
                this.fNamespaceContext = (NamespaceContext) obj;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.impl.XMLEntityHandler
    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        super.startEntity(str, xMLResourceIdentifier, str2, augmentations);
        this.fEntityScanner.registerListener(this);
        if (!str.equals("[xml]") && this.fEntityScanner.isExternal() && (augmentations == null || !((Boolean) augmentations.getItem(Constants.ENTITY_SKIPPED)).booleanValue())) {
            setScannerState(36);
        }
        if (this.fDocumentHandler == null || !str.equals("[xml]")) {
            return;
        }
        this.fDocumentHandler.startDocument(this.fEntityScanner, str2, this.fNamespaceContext, null);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl, com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void reset(PropertyManager propertyManager) {
        super.reset(propertyManager);
        this.fDoctypeName = null;
        this.fDoctypePublicId = null;
        this.fDoctypeSystemId = null;
        this.fSeenDoctypeDecl = false;
        this.fNamespaceContext.reset();
        this.fSupportDTD = ((Boolean) propertyManager.getProperty(XMLInputFactory.SUPPORT_DTD)).booleanValue();
        this.fLoadExternalDTD = !((Boolean) propertyManager.getProperty("http://java.sun.com/xml/stream/properties/ignore-external-dtd")).booleanValue();
        setScannerState(7);
        setDriver(this.fXMLDeclDriver);
        this.fSeenInternalSubset = false;
        XMLDTDScanner xMLDTDScanner = this.fDTDScanner;
        if (xMLDTDScanner != null) {
            ((XMLDTDScannerImpl) xMLDTDScanner).reset(propertyManager);
        }
        this.fEndPos = 0;
        this.fStartPos = 0;
        XMLStringBuffer xMLStringBuffer = this.fDTDDecl;
        if (xMLStringBuffer != null) {
            xMLStringBuffer.clear();
        }
    }
}
