package org.xmlpull.v1;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlPullParserException extends Exception {
    protected int column;
    protected Throwable detail;
    protected int row;

    public XmlPullParserException(String str, XmlPullParser xmlPullParser, Throwable th) {
        String str2;
        StringBuilder sb = new StringBuilder();
        String str3 = XmlPullParser.NO_NAMESPACE;
        sb.append(str == null ? XmlPullParser.NO_NAMESPACE : str.concat(" "));
        if (xmlPullParser == null) {
            str2 = XmlPullParser.NO_NAMESPACE;
        } else {
            str2 = "(position:" + xmlPullParser.getPositionDescription() + ") ";
        }
        sb.append(str2);
        if (th != null) {
            str3 = "caused by: " + th;
        }
        sb.append(str3);
        super(sb.toString());
        this.row = -1;
        this.column = -1;
        if (xmlPullParser != null) {
            this.row = xmlPullParser.getLineNumber();
            this.column = xmlPullParser.getColumnNumber();
        }
        this.detail = th;
    }

    public int getColumnNumber() {
        return this.column;
    }

    public Throwable getDetail() {
        return this.detail;
    }

    public int getLineNumber() {
        return this.row;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        if (this.detail == null) {
            super.printStackTrace();
            return;
        }
        synchronized (System.err) {
            System.err.println(super.getMessage() + "; nested exception is:");
            this.detail.printStackTrace();
        }
    }

    public XmlPullParserException(String str) {
        super(str);
        this.row = -1;
        this.column = -1;
    }
}
