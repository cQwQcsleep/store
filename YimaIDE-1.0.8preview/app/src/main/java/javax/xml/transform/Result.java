package javax.xml.transform;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface Result {
    public static final String PI_DISABLE_OUTPUT_ESCAPING = "javax.xml.transform.disable-output-escaping";
    public static final String PI_ENABLE_OUTPUT_ESCAPING = "javax.xml.transform.enable-output-escaping";

    String getSystemId();

    void setSystemId(String str);
}
