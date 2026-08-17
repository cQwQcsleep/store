package javax.xml.transform;

import java.util.Properties;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Templates {
    Properties getOutputProperties();

    Transformer newTransformer() throws TransformerConfigurationException;
}
