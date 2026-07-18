package com.deltech.backend.dto;

public class TwoFactorSetupResponse {
    private String secret;
    private String qrCodeUrl;

    public TwoFactorSetupResponse() {}
    public TwoFactorSetupResponse(String secret, String qrCodeUrl) {
        this.secret = secret;
        this.qrCodeUrl = qrCodeUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String secret;
        private String qrCodeUrl;
        public Builder secret(String secret) { this.secret = secret; return this; }
        public Builder qrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; return this; }
        public TwoFactorSetupResponse build() {
            return new TwoFactorSetupResponse(secret, qrCodeUrl);
        }
    }

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }
}
