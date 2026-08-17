package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xpath.internal.functions.FuncExtFunction;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExtensionsProvider {
    boolean elementAvailable(String str, String str2) throws TransformerException;

    Object extFunction(FuncExtFunction funcExtFunction, List<XObject> list) throws TransformerException;

    Object extFunction(String str, String str2, List<XObject> list, Object obj) throws TransformerException;

    boolean functionAvailable(String str, String str2) throws TransformerException;
}
