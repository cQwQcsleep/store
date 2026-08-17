package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.ExtensionsProvider;
import com.sun.org.apache.xpath.internal.functions.FuncExtFunction;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionException;
import javax.xml.xpath.XPathFunctionResolver;
import jdk.xml.internal.JdkXmlFeatures;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JAXPExtensionsProvider implements ExtensionsProvider {
    private boolean extensionInvocationDisabled;
    private final XPathFunctionResolver resolver;

    public JAXPExtensionsProvider(XPathFunctionResolver xPathFunctionResolver, boolean z, JdkXmlFeatures jdkXmlFeatures) {
        this.extensionInvocationDisabled = false;
        this.resolver = xPathFunctionResolver;
        if (!z || jdkXmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION)) {
            return;
        }
        this.extensionInvocationDisabled = true;
    }

    @Override // com.sun.org.apache.xpath.internal.ExtensionsProvider
    public boolean elementAvailable(String str, String str2) throws TransformerException {
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.ExtensionsProvider
    public Object extFunction(String str, String str2, List<XObject> list, Object obj) throws TransformerException {
        try {
            if (str2 == null) {
                throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_ARG_CANNOT_BE_NULL", new Object[]{"Function Name"}));
            }
            QName qName = new QName(str, str2);
            if (this.extensionInvocationDisabled) {
                throw new XPathFunctionException(XPATHMessages.createXPATHMessage("ER_EXTENSION_FUNCTION_CANNOT_BE_INVOKED", new Object[]{qName.toString()}));
            }
            int size = list.size();
            XPathFunction xPathFunctionResolveFunction = this.resolver.resolveFunction(qName, size);
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                XObject xObject = list.get(i);
                if (xObject instanceof XNodeSet) {
                    arrayList.add(i, ((XNodeSet) xObject).nodelist());
                } else if (xObject != null) {
                    arrayList.add(i, xObject.object());
                } else {
                    arrayList.add(i, xObject);
                }
            }
            return xPathFunctionResolveFunction.evaluate(arrayList);
        } catch (XPathFunctionException e) {
            throw new WrappedRuntimeException(e);
        } catch (Exception e2) {
            throw new TransformerException(e2);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.ExtensionsProvider
    public boolean functionAvailable(String str, String str2) throws TransformerException {
        try {
            if (str2 != null) {
                return this.resolver.resolveFunction(new QName(str, str2), 0) != null;
            }
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_ARG_CANNOT_BE_NULL", new Object[]{"Function Name"}));
        } catch (Exception unused) {
            return false;
        }
    }

    public JAXPExtensionsProvider(XPathFunctionResolver xPathFunctionResolver) {
        this.resolver = xPathFunctionResolver;
        this.extensionInvocationDisabled = false;
    }

    @Override // com.sun.org.apache.xpath.internal.ExtensionsProvider
    public Object extFunction(FuncExtFunction funcExtFunction, List<XObject> list) throws TransformerException {
        try {
            String namespace = funcExtFunction.getNamespace();
            String functionName = funcExtFunction.getFunctionName();
            int argCount = funcExtFunction.getArgCount();
            QName qName = new QName(namespace, functionName);
            if (!this.extensionInvocationDisabled) {
                XPathFunction xPathFunctionResolveFunction = this.resolver.resolveFunction(qName, argCount);
                ArrayList arrayList = new ArrayList(argCount);
                for (int i = 0; i < argCount; i++) {
                    XObject xObject = list.get(i);
                    if (xObject instanceof XNodeSet) {
                        arrayList.add(i, ((XNodeSet) xObject).nodelist());
                    } else if (xObject != null) {
                        arrayList.add(i, xObject.object());
                    } else {
                        arrayList.add(i, xObject);
                    }
                }
                return xPathFunctionResolveFunction.evaluate(arrayList);
            }
            throw new XPathFunctionException(XPATHMessages.createXPATHMessage("ER_EXTENSION_FUNCTION_CANNOT_BE_INVOKED", new Object[]{qName.toString()}));
        } catch (XPathFunctionException e) {
            throw new WrappedRuntimeException(e);
        } catch (Exception e2) {
            throw new TransformerException(e2);
        }
    }
}
