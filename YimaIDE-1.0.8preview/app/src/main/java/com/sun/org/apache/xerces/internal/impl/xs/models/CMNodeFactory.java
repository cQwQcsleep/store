package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CMNodeFactory {
    private static final boolean DEBUG = false;
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final int MULTIPLICITY = 1;
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private XMLErrorReporter fErrorReporter;
    private int maxNodeLimit;
    private int nodeCount = 0;
    private XMLSecurityManager fSecurityManager = null;

    public CMNode getCMBinOpNode(int i, CMNode cMNode, CMNode cMNode2) {
        return new XSCMBinOp(i, cMNode, cMNode2);
    }

    public CMNode getCMLeafNode(int i, Object obj, int i2, int i3) {
        return new XSCMLeaf(i, obj, i2, i3);
    }

    public CMNode getCMRepeatingLeafNode(int i, Object obj, int i2, int i3, int i4, int i5) {
        nodeCountCheck();
        return new XSCMRepeatingLeaf(i, obj, i2, i3, i4, i5);
    }

    public CMNode getCMUniOpNode(int i, CMNode cMNode) {
        nodeCountCheck();
        return new XSCMUniOp(i, cMNode);
    }

    public void nodeCountCheck() {
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if (xMLSecurityManager == null || xMLSecurityManager.isNoLimit(this.maxNodeLimit)) {
            return;
        }
        int i = this.nodeCount;
        this.nodeCount = i + 1;
        int i2 = this.maxNodeLimit;
        if (i > i2) {
            this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "MaxOccurLimit", new Object[]{Integer.valueOf(i2)}, (short) 2);
            this.nodeCount = 0;
        }
    }

    public void reset(XMLComponentManager xMLComponentManager) {
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        try {
            this.fSecurityManager = (XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
            reset();
        } catch (XMLConfigurationException unused) {
            this.fSecurityManager = null;
        }
    }

    public void resetNodeCount() {
        this.nodeCount = 0;
    }

    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int length = str.length() - 33;
            if (length == 16 && str.endsWith(Constants.SECURITY_MANAGER_PROPERTY)) {
                XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) obj;
                this.fSecurityManager = xMLSecurityManager;
                this.maxNodeLimit = xMLSecurityManager != null ? xMLSecurityManager.getLimit(XMLSecurityManager.Limit.MAX_OCCUR_NODE_LIMIT) : 0;
            } else if (length == 23 && str.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) obj;
            }
        }
    }

    public void reset() {
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if (xMLSecurityManager != null) {
            this.maxNodeLimit = xMLSecurityManager.getLimit(XMLSecurityManager.Limit.MAX_OCCUR_NODE_LIMIT);
        }
    }
}
