package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.xs.util.XSGrammarPool;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XSGrammar;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xs.LSInputList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSLoader;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import com.sun.org.apache.xerces.internal.xs.XSNamedMap;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.ls.LSInput;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSLoaderImpl implements XSLoader, DOMConfiguration {
    private final XSGrammarPool fGrammarPool;
    private final XMLSchemaLoader fSchemaLoader;

    public static final class XSGrammarMerger extends XSGrammarPool {
        private void mergeSchemaGrammars(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
            XSNamedMap components = schemaGrammar2.getComponents((short) 2);
            int length = components.getLength();
            for (int i = 0; i < length; i++) {
                XSElementDecl xSElementDecl = (XSElementDecl) components.item(i);
                if (schemaGrammar.getGlobalElementDecl(xSElementDecl.getName()) == null) {
                    schemaGrammar.addGlobalElementDecl(xSElementDecl);
                }
            }
            XSNamedMap components2 = schemaGrammar2.getComponents((short) 1);
            int length2 = components2.getLength();
            for (int i2 = 0; i2 < length2; i2++) {
                XSAttributeDecl xSAttributeDecl = (XSAttributeDecl) components2.item(i2);
                if (schemaGrammar.getGlobalAttributeDecl(xSAttributeDecl.getName()) == null) {
                    schemaGrammar.addGlobalAttributeDecl(xSAttributeDecl);
                }
            }
            XSNamedMap components3 = schemaGrammar2.getComponents((short) 3);
            int length3 = components3.getLength();
            for (int i3 = 0; i3 < length3; i3++) {
                XSTypeDefinition xSTypeDefinition = (XSTypeDefinition) components3.item(i3);
                if (schemaGrammar.getGlobalTypeDecl(xSTypeDefinition.getName()) == null) {
                    schemaGrammar.addGlobalTypeDecl(xSTypeDefinition);
                }
            }
            XSNamedMap components4 = schemaGrammar2.getComponents((short) 5);
            int length4 = components4.getLength();
            for (int i4 = 0; i4 < length4; i4++) {
                XSAttributeGroupDecl xSAttributeGroupDecl = (XSAttributeGroupDecl) components4.item(i4);
                if (schemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl.getName()) == null) {
                    schemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl);
                }
            }
            XSNamedMap components5 = schemaGrammar2.getComponents((short) 7);
            int length5 = components5.getLength();
            for (int i5 = 0; i5 < length5; i5++) {
                XSGroupDecl xSGroupDecl = (XSGroupDecl) components5.item(i5);
                if (schemaGrammar.getGlobalGroupDecl(xSGroupDecl.getName()) == null) {
                    schemaGrammar.addGlobalGroupDecl(xSGroupDecl);
                }
            }
            XSNamedMap components6 = schemaGrammar2.getComponents((short) 11);
            int length6 = components6.getLength();
            for (int i6 = 0; i6 < length6; i6++) {
                XSNotationDecl xSNotationDecl = (XSNotationDecl) components6.item(i6);
                if (schemaGrammar.getGlobalNotationDecl(xSNotationDecl.getName()) == null) {
                    schemaGrammar.addGlobalNotationDecl(xSNotationDecl);
                }
            }
            XSObjectList annotations = schemaGrammar2.getAnnotations();
            int length7 = annotations.getLength();
            for (int i7 = 0; i7 < length7; i7++) {
                schemaGrammar.addAnnotation((XSAnnotationImpl) annotations.item(i7));
            }
        }

        private SchemaGrammar toSchemaGrammar(Grammar grammar) {
            if (grammar instanceof SchemaGrammar) {
                return (SchemaGrammar) grammar;
            }
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl
        public boolean containsGrammar(XMLGrammarDescription xMLGrammarDescription) {
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl
        public Grammar getGrammar(XMLGrammarDescription xMLGrammarDescription) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl
        public void putGrammar(Grammar grammar) {
            SchemaGrammar schemaGrammar = toSchemaGrammar(super.getGrammar(grammar.getGrammarDescription()));
            if (schemaGrammar == null) {
                super.putGrammar(grammar);
                return;
            }
            SchemaGrammar schemaGrammar2 = toSchemaGrammar(grammar);
            if (schemaGrammar2 != null) {
                mergeSchemaGrammars(schemaGrammar, schemaGrammar2);
            }
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl, com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl, com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar[] retrieveInitialGrammarSet(String str) {
            return new Grammar[0];
        }
    }

    public XSLoaderImpl() {
        XSGrammarMerger xSGrammarMerger = new XSGrammarMerger();
        this.fGrammarPool = xSGrammarMerger;
        XMLSchemaLoader xMLSchemaLoader = new XMLSchemaLoader();
        this.fSchemaLoader = xMLSchemaLoader;
        xMLSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xSGrammarMerger);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String str, Object obj) {
        return this.fSchemaLoader.canSetParameter(str, obj);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public DOMConfiguration getConfig() {
        return this;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String str) throws DOMException {
        return this.fSchemaLoader.getParameter(str);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        return this.fSchemaLoader.getParameterNames();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel load(LSInput lSInput) {
        try {
            this.fGrammarPool.clear();
            XMLSchemaLoader xMLSchemaLoader = this.fSchemaLoader;
            return ((XSGrammar) xMLSchemaLoader.loadGrammar(xMLSchemaLoader.dom2xmlInputSource(lSInput))).toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel loadInputList(LSInputList lSInputList) {
        int length = lSInputList.getLength();
        try {
            this.fGrammarPool.clear();
            for (int i = 0; i < length; i++) {
                XMLSchemaLoader xMLSchemaLoader = this.fSchemaLoader;
                xMLSchemaLoader.loadGrammar(xMLSchemaLoader.dom2xmlInputSource(lSInputList.item(i)));
            }
            return this.fGrammarPool.toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel loadURI(String str) {
        try {
            this.fGrammarPool.clear();
            return ((XSGrammar) this.fSchemaLoader.loadGrammar(new XMLInputSource(null, str, null, false))).toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSLoader
    public XSModel loadURIList(StringList stringList) {
        int length = stringList.getLength();
        try {
            this.fGrammarPool.clear();
            for (int i = 0; i < length; i++) {
                this.fSchemaLoader.loadGrammar(new XMLInputSource(null, stringList.item(i), null, false));
            }
            return this.fGrammarPool.toXSModel();
        } catch (Exception e) {
            this.fSchemaLoader.reportDOMFatalError(e);
            return null;
        }
    }

    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String str, Object obj) throws DOMException {
        this.fSchemaLoader.setParameter(str, obj);
    }
}
