package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParserConfigurationSettings implements XMLComponentManager {
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    protected Map<String, Boolean> fFeatures;
    protected XMLComponentManager fParentSettings;
    protected Map<String, Object> fProperties;
    protected List<String> fRecognizedFeatures;
    protected List<String> fRecognizedProperties;

    public ParserConfigurationSettings(XMLComponentManager xMLComponentManager) {
        this.fRecognizedFeatures = new ArrayList();
        this.fRecognizedProperties = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        this.fParentSettings = xMLComponentManager;
    }

    public void addRecognizedFeatures(String[] strArr) {
        int length = strArr != null ? strArr.length : 0;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (!this.fRecognizedFeatures.contains(str)) {
                this.fRecognizedFeatures.add(str);
            }
        }
    }

    public void addRecognizedProperties(String[] strArr) {
        this.fRecognizedProperties.addAll(Arrays.asList(strArr));
    }

    public FeatureState checkFeature(String str) throws XMLConfigurationException {
        if (this.fRecognizedFeatures.contains(str)) {
            return FeatureState.RECOGNIZED;
        }
        XMLComponentManager xMLComponentManager = this.fParentSettings;
        return xMLComponentManager != null ? xMLComponentManager.getFeatureState(str) : FeatureState.NOT_RECOGNIZED;
    }

    public PropertyState checkProperty(String str) throws XMLConfigurationException {
        if (!this.fRecognizedProperties.contains(str)) {
            XMLComponentManager xMLComponentManager = this.fParentSettings;
            if (xMLComponentManager == null) {
                return PropertyState.NOT_RECOGNIZED;
            }
            PropertyState propertyState = xMLComponentManager.getPropertyState(str);
            if (propertyState.isExceptional()) {
                return propertyState;
            }
        }
        return PropertyState.RECOGNIZED;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public boolean getFeature(String str) throws XMLConfigurationException {
        FeatureState featureState = getFeatureState(str);
        if (featureState.isExceptional()) {
            throw new XMLConfigurationException(featureState.status, str);
        }
        return featureState.state;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public FeatureState getFeatureState(String str) {
        Boolean bool = this.fFeatures.get(str);
        if (bool != null) {
            return FeatureState.is(bool.booleanValue());
        }
        FeatureState featureStateCheckFeature = checkFeature(str);
        return featureStateCheckFeature.isExceptional() ? featureStateCheckFeature : FeatureState.is(false);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public Object getProperty(String str) throws XMLConfigurationException {
        PropertyState propertyState = getPropertyState(str);
        if (propertyState.isExceptional()) {
            throw new XMLConfigurationException(propertyState.status, str);
        }
        return propertyState.state;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public PropertyState getPropertyState(String str) {
        Object obj = this.fProperties.get(str);
        if (obj == null) {
            PropertyState propertyStateCheckProperty = checkProperty(str);
            if (propertyStateCheckProperty.isExceptional()) {
                return propertyStateCheckProperty;
            }
        }
        return PropertyState.is(obj);
    }

    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        FeatureState featureStateCheckFeature = checkFeature(str);
        if (featureStateCheckFeature.isExceptional()) {
            throw new XMLConfigurationException(featureStateCheckFeature.status, str);
        }
        this.fFeatures.put(str, Boolean.valueOf(z));
    }

    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        PropertyState propertyStateCheckProperty = checkProperty(str);
        if (propertyStateCheckProperty.isExceptional()) {
            throw new XMLConfigurationException(propertyStateCheckProperty.status, str);
        }
        this.fProperties.put(str, obj);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public final boolean getFeature(String str, boolean z) {
        FeatureState featureState = getFeatureState(str);
        return featureState.isExceptional() ? z : featureState.state;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public final Object getProperty(String str, Object obj) {
        PropertyState propertyState = getPropertyState(str);
        return propertyState.isExceptional() ? obj : propertyState.state;
    }

    public ParserConfigurationSettings() {
        this(null);
    }
}
