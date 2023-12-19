package com.bitlabs.App.ServiceImpl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.bitlabs.App.Service.OtpService;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpServiceImpl implements OtpService {

    private static final long OTP_VALID_DURATION_MS = 120 * 1000; // 120 seconds
    private static final long OTP_RESEND_COOLDOWN_MS = 60 * 1000; // 60 seconds

    private Map<String, OtpData> otpMap = new HashMap<>();

    public String generateOtp(String userEmail) {
        String otp = generateRandomOtp();
        otpMap.put(userEmail, new OtpData(otp));
        return otp;
    }

    public boolean validateOtp(String userEmail, String enteredOtp) {
        OtpData otpData = otpMap.get(userEmail);
        if (otpData != null && otpData.isValid(enteredOtp)) {
            otpMap.remove(userEmail);
            return true;
        }
        return false;
    }

    public boolean isOtpExpired(String userEmail) {
        OtpData otpData = otpMap.get(userEmail);
        return otpData != null && otpData.isExpired();
    }

    public boolean canResendOtp(String userEmail) {
        OtpData otpData = otpMap.get(userEmail);
        return otpData == null || otpData.canResend();
    }

    private String generateRandomOtp() {
        return RandomStringUtils.randomNumeric(6);
    }

    public static class OtpData {
        private String otp;
        private long creationTime;
        private long lastResendTime;

        public OtpData(String otp) {
            this.otp = otp;
            this.creationTime = System.currentTimeMillis();
            this.lastResendTime = 0;
        }

        public boolean isValid(String enteredOtp) {
            long currentTime = System.currentTimeMillis();
            return currentTime - creationTime <= OTP_VALID_DURATION_MS && otp.equals(enteredOtp);
        }

        public long getCreationTime() {
            return creationTime;
        }

        public boolean isExpired() {
            long currentTime = System.currentTimeMillis();
            return currentTime - creationTime > OTP_VALID_DURATION_MS;
        }

        public boolean canResend() {
            long currentTime = System.currentTimeMillis();
            return currentTime - lastResendTime > OTP_RESEND_COOLDOWN_MS;
        }

        public void markAsResent() {
            this.lastResendTime = System.currentTimeMillis();
        }
    }
}
