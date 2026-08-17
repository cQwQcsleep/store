package com.reandroid.xml;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleText extends XMLText {
    public StyleText() {
        super("");
    }

    @Override // com.reandroid.xml.XMLNode
    public int getLength() {
        return getTextLength();
    }

    @Override // com.reandroid.xml.XMLNode
    public int getTextLength() {
        String text = getText();
        if (text != null) {
            return text.length();
        }
        return 0;
    }

    @Override // com.reandroid.xml.XMLText
    public boolean isIndent() {
        return false;
    }

    public void writeStyledText(Appendable appendable) throws IOException {
        appendable.append(getText(false));
    }
}
