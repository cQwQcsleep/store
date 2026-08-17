package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class WinCryptUtil {

    public static class MANAGED_CRYPT_SIGN_MESSAGE_PARA extends WinCrypt.CRYPT_SIGN_MESSAGE_PARA {
        private WinCrypt.CRYPT_ATTRIBUTE[] rgAuthAttrs;
        private WinCrypt.CRYPT_ATTRIBUTE[] rgUnauthAttrs;
        private WinCrypt.CERT_CONTEXT[] rgpMsgCerts;
        private WinCrypt.CRL_CONTEXT[] rgpMsgCrls;

        @Override // com.sun.jna.platform.win32.WinCrypt.CRYPT_SIGN_MESSAGE_PARA
        public WinCrypt.CRYPT_ATTRIBUTE[] getRgAuthAttr() {
            return this.rgAuthAttrs;
        }

        @Override // com.sun.jna.platform.win32.WinCrypt.CRYPT_SIGN_MESSAGE_PARA
        public WinCrypt.CRYPT_ATTRIBUTE[] getRgUnauthAttr() {
            return this.rgUnauthAttrs;
        }

        @Override // com.sun.jna.platform.win32.WinCrypt.CRYPT_SIGN_MESSAGE_PARA
        public WinCrypt.CERT_CONTEXT[] getRgpMsgCert() {
            return this.rgpMsgCerts;
        }

        @Override // com.sun.jna.platform.win32.WinCrypt.CRYPT_SIGN_MESSAGE_PARA
        public WinCrypt.CRL_CONTEXT[] getRgpMsgCrl() {
            return this.rgpMsgCrls;
        }

        @Override // com.sun.jna.Structure
        public void read() {
            WinCrypt.CERT_CONTEXT[] cert_contextArr = this.rgpMsgCerts;
            if (cert_contextArr != null) {
                for (WinCrypt.CERT_CONTEXT cert_context : cert_contextArr) {
                    cert_context.read();
                }
            }
            WinCrypt.CRL_CONTEXT[] crl_contextArr = this.rgpMsgCrls;
            if (crl_contextArr != null) {
                for (WinCrypt.CRL_CONTEXT crl_context : crl_contextArr) {
                    crl_context.read();
                }
            }
            WinCrypt.CRYPT_ATTRIBUTE[] crypt_attributeArr = this.rgAuthAttrs;
            if (crypt_attributeArr != null) {
                for (WinCrypt.CRYPT_ATTRIBUTE crypt_attribute : crypt_attributeArr) {
                    crypt_attribute.read();
                }
            }
            WinCrypt.CRYPT_ATTRIBUTE[] crypt_attributeArr2 = this.rgUnauthAttrs;
            if (crypt_attributeArr2 != null) {
                for (WinCrypt.CRYPT_ATTRIBUTE crypt_attribute2 : crypt_attributeArr2) {
                    crypt_attribute2.read();
                }
            }
            super.read();
        }

        public void setRgAuthAttr(WinCrypt.CRYPT_ATTRIBUTE[] crypt_attributeArr) {
            this.rgAuthAttrs = crypt_attributeArr;
            if (crypt_attributeArr == null || crypt_attributeArr.length == 0) {
                this.rgAuthAttr = null;
                this.cMsgCert = 0;
            } else {
                this.cMsgCert = this.rgpMsgCerts.length;
                this.rgAuthAttr = crypt_attributeArr[0].getPointer();
            }
        }

        public void setRgUnauthAttr(WinCrypt.CRYPT_ATTRIBUTE[] crypt_attributeArr) {
            this.rgUnauthAttrs = crypt_attributeArr;
            if (crypt_attributeArr == null || crypt_attributeArr.length == 0) {
                this.rgUnauthAttr = null;
                this.cMsgCert = 0;
            } else {
                this.cMsgCert = this.rgpMsgCerts.length;
                this.rgUnauthAttr = crypt_attributeArr[0].getPointer();
            }
        }

        public void setRgpMsgCert(WinCrypt.CERT_CONTEXT[] cert_contextArr) {
            this.rgpMsgCerts = cert_contextArr;
            if (cert_contextArr == null || cert_contextArr.length == 0) {
                this.rgpMsgCert = null;
                this.cMsgCert = 0;
                return;
            }
            this.cMsgCert = cert_contextArr.length;
            Memory memory = new Memory(Native.POINTER_SIZE * cert_contextArr.length);
            for (int i = 0; i < cert_contextArr.length; i++) {
                memory.setPointer(Native.POINTER_SIZE * i, cert_contextArr[i].getPointer());
            }
            this.rgpMsgCert = memory;
        }

        public void setRgpMsgCrl(WinCrypt.CRL_CONTEXT[] crl_contextArr) {
            this.rgpMsgCrls = crl_contextArr;
            if (crl_contextArr == null || crl_contextArr.length == 0) {
                this.rgpMsgCert = null;
                this.cMsgCert = 0;
                return;
            }
            this.cMsgCert = crl_contextArr.length;
            Memory memory = new Memory(Native.POINTER_SIZE * crl_contextArr.length);
            for (int i = 0; i < crl_contextArr.length; i++) {
                memory.setPointer(Native.POINTER_SIZE * i, crl_contextArr[i].getPointer());
            }
            this.rgpMsgCert = memory;
        }

        @Override // com.sun.jna.Structure
        public void write() {
            WinCrypt.CERT_CONTEXT[] cert_contextArr = this.rgpMsgCerts;
            if (cert_contextArr != null) {
                for (WinCrypt.CERT_CONTEXT cert_context : cert_contextArr) {
                    cert_context.write();
                }
            }
            WinCrypt.CRL_CONTEXT[] crl_contextArr = this.rgpMsgCrls;
            if (crl_contextArr != null) {
                for (WinCrypt.CRL_CONTEXT crl_context : crl_contextArr) {
                    crl_context.write();
                }
            }
            WinCrypt.CRYPT_ATTRIBUTE[] crypt_attributeArr = this.rgAuthAttrs;
            if (crypt_attributeArr != null) {
                for (WinCrypt.CRYPT_ATTRIBUTE crypt_attribute : crypt_attributeArr) {
                    crypt_attribute.write();
                }
            }
            WinCrypt.CRYPT_ATTRIBUTE[] crypt_attributeArr2 = this.rgUnauthAttrs;
            if (crypt_attributeArr2 != null) {
                for (WinCrypt.CRYPT_ATTRIBUTE crypt_attribute2 : crypt_attributeArr2) {
                    crypt_attribute2.write();
                }
            }
            this.cbSize = size();
            super.write();
        }
    }
}
