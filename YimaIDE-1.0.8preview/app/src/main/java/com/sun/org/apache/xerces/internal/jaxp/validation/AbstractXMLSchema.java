package com.sun.org.apache.xerces.internal.jaxp.validation;

import java.util.HashMap;
import java.util.Map;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class AbstractXMLSchema extends Schema implements XSGrammarPoolContainer {
    private final Map<String, Boolean> fFeatures = new HashMap();
    private final Map<String, Object> fProperties = new HashMap();

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public final Boolean getFeature(String str) {
        return this.fFeatures.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public final Object getProperty(String str) {
        return this.fProperties.get(str);
    }

    @Override // javax.xml.validation.Schema
    public final Validator newValidator() {
        return new ValidatorImpl(this);
    }

    @Override // javax.xml.validation.Schema
    public final ValidatorHandler newValidatorHandler() {
        return new ValidatorHandlerImpl(this);
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public final void setFeature(String str, boolean z) {
        this.fFeatures.put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer
    public final void setProperty(String str, Object obj) {
        this.fProperties.put(str, obj);
    }
}
