package com.sun.tools.javac.parser;

import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ScannerFactory {
    public static final Context.Key<ScannerFactory> scannerFactoryKey = new Context.Key<>();
    final boolean enableLineDocComments;
    final Log log;
    final Names names;
    final Preview preview;
    final Source source;
    final Tokens tokens;

    public ScannerFactory(Context context) {
        context.put(scannerFactoryKey, this);
        this.log = Log.instance(context);
        this.names = Names.instance(context);
        this.source = Source.instance(context);
        this.preview = Preview.instance(context);
        this.tokens = Tokens.instance(context);
        this.enableLineDocComments = !Options.instance(context).isSet(Option.DISABLE_LINE_DOC_COMMENTS);
    }

    public static ScannerFactory instance(Context context) {
        ScannerFactory scannerFactory = (ScannerFactory) context.get(scannerFactoryKey);
        return scannerFactory == null ? new ScannerFactory(context) : scannerFactory;
    }

    public Scanner newScanner(CharSequence charSequence, boolean z) {
        if (charSequence instanceof CharBuffer) {
            CharBuffer charBuffer = (CharBuffer) charSequence;
            return z ? new Scanner(this, new JavadocTokenizer(this, charBuffer)) : new Scanner(this, charBuffer);
        }
        char[] charArray = charSequence.toString().toCharArray();
        return newScanner(charArray, charArray.length, z);
    }

    public Scanner newScanner(char[] cArr, int i, boolean z) {
        if (z) {
            return new Scanner(this, new JavadocTokenizer(this, cArr, i));
        }
        return new Scanner(this, cArr, i);
    }
}
