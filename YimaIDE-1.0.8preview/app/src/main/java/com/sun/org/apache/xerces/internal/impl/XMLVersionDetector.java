package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.xml.internal.stream.Entity;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLVersionDetector {
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final char[] XML11_VERSION = {'1', '.', '1'};
    protected static final String fVersionSymbol = "version".intern();
    protected static final String fXMLSymbol = "[xml]".intern();
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected SymbolTable fSymbolTable;
    protected String fEncoding = null;
    private XMLString fVersionNum = new XMLString();
    private final char[] fExpectedVersionString = {'<', '?', 'x', 'm', 'l', ' ', 'v', 'e', 'r', 's', 'i', 'o', 'n', '=', ' ', ' ', ' ', ' ', ' '};

    private void fixupCurrentEntity(XMLEntityManager xMLEntityManager, char[] cArr, int i) {
        Entity.ScannedEntity currentEntity = xMLEntityManager.getCurrentEntity();
        int i2 = currentEntity.count;
        int i3 = currentEntity.position;
        int i4 = (i2 - i3) + i;
        char[] cArr2 = currentEntity.ch;
        if (i4 > cArr2.length) {
            char[] cArr3 = new char[((i2 + i) - i3) + 1];
            currentEntity.ch = cArr3;
            System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
        }
        int i5 = currentEntity.position;
        if (i5 < i) {
            char[] cArr4 = currentEntity.ch;
            System.arraycopy(cArr4, i5, cArr4, i, currentEntity.count - i5);
            currentEntity.count += i - currentEntity.position;
        } else {
            for (int i6 = i; i6 < currentEntity.position; i6++) {
                currentEntity.ch[i6] = ' ';
            }
        }
        System.arraycopy(cArr, 0, currentEntity.ch, 0, i);
        currentEntity.position = 0;
        currentEntity.baseCharOffset = 0;
        currentEntity.startPosition = 0;
        currentEntity.lineNumber = 1;
        currentEntity.columnNumber = 1;
    }

    public short determineDocVersion(XMLInputSource xMLInputSource) throws IOException {
        char[] cArr;
        char[] cArr2;
        this.fEncoding = this.fEntityManager.setupCurrentEntity(false, fXMLSymbol, xMLInputSource, false, true);
        this.fEntityManager.setScannerVersion((short) 1);
        XMLEntityScanner entityScanner = this.fEntityManager.getEntityScanner();
        entityScanner.detectingVersion = true;
        try {
            if (!entityScanner.skipString("<?xml")) {
                entityScanner.detectingVersion = false;
                return (short) 1;
            }
            if (!entityScanner.skipDeclSpaces()) {
                fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 5);
                entityScanner.detectingVersion = false;
                return (short) 1;
            }
            if (!entityScanner.skipString("version")) {
                fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 6);
                entityScanner.detectingVersion = false;
                return (short) 1;
            }
            entityScanner.skipDeclSpaces();
            if (entityScanner.peekChar() != 61) {
                fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 13);
                entityScanner.detectingVersion = false;
                return (short) 1;
            }
            entityScanner.scanChar(null);
            entityScanner.skipDeclSpaces();
            this.fExpectedVersionString[14] = (char) entityScanner.scanChar(null);
            int i = 0;
            while (true) {
                int length = XML11_VERSION.length;
                cArr = this.fExpectedVersionString;
                if (i >= length) {
                    break;
                }
                cArr[i + 15] = (char) entityScanner.scanChar(null);
                i++;
            }
            cArr[18] = (char) entityScanner.scanChar(null);
            fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 19);
            int i2 = 0;
            while (true) {
                cArr2 = XML11_VERSION;
                if (i2 >= cArr2.length || this.fExpectedVersionString[i2 + 15] != cArr2[i2]) {
                    break;
                    break;
                }
                i2++;
            }
            entityScanner.detectingVersion = false;
            return i2 == cArr2.length ? (short) 2 : (short) 1;
        } catch (MalformedByteSequenceException e) {
            this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, (Exception) e);
            entityScanner.detectingVersion = false;
            return (short) 1;
        } catch (CharConversionException e2) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e2);
            entityScanner.detectingVersion = false;
            return (short) 1;
        } catch (EOFException unused) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "PrematureEOF", null, (short) 2);
            entityScanner.detectingVersion = false;
            return (short) 1;
        }
    }

    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntityManager = (XMLEntityManager) xMLComponentManager.getProperty(ENTITY_MANAGER);
        int i = 14;
        while (true) {
            char[] cArr = this.fExpectedVersionString;
            if (i >= cArr.length) {
                return;
            }
            cArr[i] = ' ';
            i++;
        }
    }

    public void startDocumentParsing(XMLEntityHandler xMLEntityHandler, short s) {
        XMLEntityManager xMLEntityManager = this.fEntityManager;
        if (s == 1) {
            xMLEntityManager.setScannerVersion((short) 1);
        } else {
            xMLEntityManager.setScannerVersion((short) 2);
        }
        this.fErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
        this.fEntityManager.setEntityHandler(xMLEntityHandler);
        xMLEntityHandler.startEntity(fXMLSymbol, this.fEntityManager.getCurrentResourceIdentifier(), this.fEncoding, null);
    }
}
