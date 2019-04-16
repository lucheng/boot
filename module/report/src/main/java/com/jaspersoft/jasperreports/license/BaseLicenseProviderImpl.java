package com.jaspersoft.jasperreports.license;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jp.protection.pub.License;
import com.jp.protection.pub.LicenseAdapter;
import com.jp.protection.pub.LicenseHost;
import com.jp.protection.pub.LicenseHostListener;
import com.jp.protection.pub.LicenseReader;
import com.jp.protection.pub.ProtectionFactory;

public abstract class BaseLicenseProviderImpl implements LicenseProvider {
	private static final Log log = LogFactory.getLog(BaseLicenseProviderImpl.class);
	private final byte[] decryptKeyBytes;
	private final String securityAlgorithm;
	private LicenseReader licenseReader;
	private LicenseHost licenseHost;
	private long lastCheckTime;
	private String errorCode;
	private String errorMessage;
	private Object[] errorArgs;
	private Set<String> enabledFeatures;

	protected BaseLicenseProviderImpl(byte[] decryptKeyBytes, String securityAlgorithm) {
		this.decryptKeyBytes = decryptKeyBytes;
		this.securityAlgorithm = securityAlgorithm;
		this.lastCheckTime = 0L;
		this.errorCode = null;
		this.errorMessage = null;
		this.errorArgs = null;
		this.enabledFeatures = new HashSet<>();
	}

	protected final void init() {
		initLicenseReader();
		initLicenseHost();
		setSecretStorages(this.licenseHost);
	}

	protected void setLicenseError(String code, String message, Object[] args) {
		this.enabledFeatures.clear();
		this.errorCode = code;
		this.errorMessage = message;
		this.errorArgs = args;
	}

	private void initLicenseReader() {
		this.licenseReader = ProtectionFactory.createLicenseReader();

		setLicenseLocation(this.licenseReader);

		this.licenseReader.setDecryptKeyBytes(this.decryptKeyBytes);
		this.licenseReader.setSecurityAlgorithm(this.securityAlgorithm);
	}

	protected abstract void setLicenseLocation(LicenseReader paramLicenseReader);

	private void initLicenseHost() {
		this.licenseHost = ProtectionFactory.createLicenseHost(this.licenseReader, createLicenseListener());

		this.licenseHost.setLicenseAcceptanceDelegate(ProtectionFactory.createLicenseAcceptanceDelegate());

		this.licenseHost.setAllowFlexibleExpirationDate(true);
	}

	private LicenseHostListener createLicenseListener() {
		return new LicenseAdapter() {
			public void licenseAvailable(LicenseReader licenseReader, String licenseLocation) {
				super.licenseAvailable(licenseReader, licenseLocation);

				BaseLicenseProviderImpl.this.licenseAvailable(licenseReader.getLicense());
			}

			public void featureChecked(LicenseHost aSource, License aLicense, String aFeature, boolean isEnabled) {
				if (isEnabled) {
					BaseLicenseProviderImpl.this.enabledFeatures.add(aFeature);
				}
			}

			public void licenseCorrupted(LicenseReader source, String licenseLocation) {
				BaseLicenseProviderImpl.this.setLicenseError("license.corrupted",
						"License at " + licenseLocation + " is corrupted", new Object[] { licenseLocation });

				super.licenseCorrupted(source, licenseLocation);
			}

			public void licenseExpired(LicenseHost aSource, License aLicense) {
				BaseLicenseProviderImpl.this.setLicenseError("license.expired",
						"License at " + aLicense.getLicenseLocation() + " has expired",
						new Object[] { aLicense.getLicenseLocation() });
				super.licenseExpired(aSource, aLicense);
			}

			public void licenseInvalid(LicenseHost aSource, License aLicense) {
				BaseLicenseProviderImpl.this.setLicenseError("license.invalid",
						"License at " + aLicense.getLicenseLocation() + " is invalid",
						new Object[] { aLicense.getLicenseLocation() });
				super.licenseInvalid(aSource, aLicense);
			}

			public void licenseMissing(LicenseReader aSource, String aLicenseLocation) {
				BaseLicenseProviderImpl.this.setLicenseError("license.missing",
						"License not found at " + aLicenseLocation, new Object[] { aLicenseLocation });

				super.licenseMissing(aSource, aLicenseLocation);
			}
		};
	}

	protected abstract void setSecretStorages(LicenseHost paramLicenseHost);

	protected void licenseAvailable(License license) {
	}

	public synchronized boolean checkLicense(boolean forceLicenseRead) {
		doCheckLicense(this.licenseHost, forceLicenseRead);
		this.lastCheckTime = System.currentTimeMillis();
		return licenseOK(this.licenseHost.getLicense());
	}

	protected abstract void doCheckLicense(LicenseHost paramLicenseHost, boolean paramBoolean);

	protected boolean licenseOK(License license) {
		return (license != null) && (license.getLicenseState() == 2);
	}

	public boolean checkLicense() {
		return checkLicense(false);
	}

	private boolean requireLicenseCheck() {
		return System.currentTimeMillis() > this.lastCheckTime + 3600000L;
	}

	public synchronized void requireLicense() {
		if (requireLicenseCheck()) {
			checkLicense();
		}
		return;
	}

	public synchronized void requireFeature(String featureName) {
		return;
	}

	protected boolean checkFeature(String featureName) {
		return this.enabledFeatures.contains(featureName);
	}

	public LicenseInfo getLicenseInfo() {
		License license = this.licenseHost.getLicense();
		if (license == null) {
			return null;
		}
		LicenseInfo licenseInfo = new LicenseInfo();
		licenseInfo.setProductName(license.getProduct());
		licenseInfo.setExpirationDate(this.licenseHost.getLicenseExpirationDate(true));
		licenseInfo.setLicenseType(translateLicenseType(license.getLicenseType()));
		return licenseInfo;
	}

	protected byte translateLicenseType(int licenseType) {
		byte infoType;
		switch (licenseType) {
		case 3:
			infoType = 1;
			break;
		case 1:
			infoType = 2;
			break;
		case 2:
			infoType = 3;
			break;
		default:
			if (log.isWarnEnabled()) {
				log.warn("Uknown Protection! license type " + licenseType);
			}
			infoType = 0;
		}
		return infoType;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}
