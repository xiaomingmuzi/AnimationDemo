package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class CertificateFactoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_factory);
//        try {
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
////            InputStream im = getResources().getAssets().open("animationdemo.jks");
//            InputStream im = getResources().getAssets().open("cnfol.keystore");
//            X509Certificate cert = (X509Certificate) cf.generateCertificate(im);
//            String signature = new String(cert.getSignature());
//            LogUtil.w("signature："+signature);
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            KeyStore ks = KeyStore.getInstance("AndroidCAStore");
            ks.load(null, null);
            Enumeration<String> aliases = ks.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                LogUtil.e("Certificate alias：" + alias);
                X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
                LogUtil.w("Subject DN："+cert.getSubjectDN().getName());
//                LogUtil.i("Issuer DN："+cert.getIssuerDN().getName());
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
