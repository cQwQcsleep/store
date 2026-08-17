package net.schmizz.sshj.common;

import org.slf4j.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface LoggerFactory {
    public static final LoggerFactory DEFAULT = new LoggerFactory() { // from class: net.schmizz.sshj.common.LoggerFactory.1
        @Override // net.schmizz.sshj.common.LoggerFactory
        public Logger getLogger(String str) {
            return org.slf4j.LoggerFactory.getLogger(str);
        }

        @Override // net.schmizz.sshj.common.LoggerFactory
        public Logger getLogger(Class<?> cls) {
            return org.slf4j.LoggerFactory.getLogger(cls);
        }
    };

    Logger getLogger(Class<?> cls);

    Logger getLogger(String str);
}
