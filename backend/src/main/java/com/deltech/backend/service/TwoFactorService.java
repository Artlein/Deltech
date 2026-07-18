package com.deltech.backend.service;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorService {

    @Autowired
    private SecretGenerator secretGenerator;

    @Autowired
    private QrGenerator qrGenerator;

    @Autowired
    private CodeVerifier codeVerifier;

    public String generateNewSecret() {
        return secretGenerator.generate();
    }

    public String getQrCodeUri(String secret, String label) {
        QrData data = new QrData.Builder()
                .label(label)
                .secret(secret)
                .issuer("DeltechParkingSystems")
                .algorithm(HashingAlgorithm.SHA1)
                .digits(6)
                .period(30)
                .build();
        try {
            return Utils.getDataUriForImage(
                    qrGenerator.generate(data),
                    qrGenerator.getImageMimeType()
            );
        } catch (QrGenerationException e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }

    public boolean verifyCode(String secret, String code) {
        return codeVerifier.isValidCode(secret, code);
    }
}
