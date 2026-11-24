package protect.card_locker;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoyaltyCard {
    public int id;
    public String store;
    public String note;
    @Nullable
    public Date validFrom;
    @Nullable
    public Date expiry;
    public BigDecimal balance;
    @Nullable
    public Currency balanceType;
    public String cardId;
    @Nullable
    public String barcodeId;
    @Nullable
    public CatimaBarcode barcodeType;
    public Charset barcodeEncoding;
    @Nullable
    public Integer headerColor;
    public int starStatus;
    public long lastUsed;
    public int zoomLevel;
    public int zoomLevelWidth;
    public int archiveStatus;

    // New cardholder, business, and personal fields
    public String holderName;
    public String holderAddress;
    public String holderPhone;
    public String holderEmail;
    public String businessName;
    public String businessAddress;
    public String businessPhone;
    public String businessWebsite;
    public String businessEmail;
    public String personalDetails;

    @Nullable
    private Bitmap imageThumbnail;
    @Nullable
    private String imageThumbnailPath;
    @Nullable
    private Bitmap imageFront;
    @Nullable
    private String imageFrontPath;
    @Nullable
    private Bitmap imageBack;
    @Nullable
    private String imageBackPath;

    public static final String BUNDLE_LOYALTY_CARD_ID = "loyaltyCardId";
    public static final String BUNDLE_LOYALTY_CARD_STORE = "loyaltyCardStore";
    public static final String BUNDLE_LOYALTY_CARD_NOTE = "loyaltyCardNote";
    public static final String BUNDLE_LOYALTY_CARD_VALID_FROM = "loyaltyCardValidFrom";
    public static final String BUNDLE_LOYALTY_CARD_EXPIRY = "loyaltyCardExpiry";
    public static final String BUNDLE_LOYALTY_CARD_BALANCE = "loyaltyCardBalance";
    public static final String BUNDLE_LOYALTY_CARD_BALANCE_TYPE = "loyaltyCardBalanceType";
    public static final String BUNDLE_LOYALTY_CARD_CARD_ID = "loyaltyCardCardId";
    public static final String BUNDLE_LOYALTY_CARD_BARCODE_ID = "loyaltyCardBarcodeId";
    public static final String BUNDLE_LOYALTY_CARD_BARCODE_TYPE = "loyaltyCardBarcodeType";
    public static final String BUNDLE_LOYALTY_CARD_BARCODE_ENCODING = "loyaltyCardBarcodeEncoding";
    public static final String BUNDLE_LOYALTY_CARD_HEADER_COLOR = "loyaltyCardHeaderColor";
    public static final String BUNDLE_LOYALTY_CARD_STAR_STATUS = "loyaltyCardStarStatus";
    public static final String BUNDLE_LOYALTY_CARD_LAST_USED = "loyaltyCardLastUsed";
    public static final String BUNDLE_LOYALTY_CARD_ZOOM_LEVEL = "loyaltyCardZoomLevel";
    public static final String BUNDLE_LOYALTY_CARD_ZOOM_LEVEL_WIDTH = "loyaltyCardZoomLevelWidth";
    public static final String BUNDLE_LOYALTY_CARD_ARCHIVE_STATUS = "loyaltyCardArchiveStatus";
    public static final String BUNDLE_LOYALTY_CARD_HOLDER_NAME = "loyaltyCardHolderName";
    public static final String BUNDLE_LOYALTY_CARD_HOLDER_ADDRESS = "loyaltyCardHolderAddress";
    public static final String BUNDLE_LOYALTY_CARD_HOLDER_PHONE = "loyaltyCardHolderPhone";
    public static final String BUNDLE_LOYALTY_CARD_HOLDER_EMAIL = "loyaltyCardHolderEmail";
    public static final String BUNDLE_LOYALTY_CARD_BUSINESS_NAME = "loyaltyCardBusinessName";
    public static final String BUNDLE_LOYALTY_CARD_BUSINESS_ADDRESS = "loyaltyCardBusinessAddress";
    public static final String BUNDLE_LOYALTY_CARD_BUSINESS_PHONE = "loyaltyCardBusinessPhone";
    public static final String BUNDLE_LOYALTY_CARD_BUSINESS_WEBSITE = "loyaltyCardBusinessWebsite";
    public static final String BUNDLE_LOYALTY_CARD_BUSINESS_EMAIL = "loyaltyCardBusinessEmail";
    public static final String BUNDLE_LOYALTY_CARD_PERSONAL_DETAILS = "loyaltyCardPersonalDetails";
    public static final String BUNDLE_LOYALTY_CARD_IMAGE_THUMBNAIL = "loyaltyCardImageThumbnail";
    public static final String BUNDLE_LOYALTY_CARD_IMAGE_FRONT = "loyaltyCardImageFront";
    public static final String BUNDLE_LOYALTY_CARD_IMAGE_BACK = "loyaltyCardImageBack";

    private static final String TEMP_IMAGE_THUMBNAIL_FILE_NAME = "loyaltyCardTempImageThumbnailFileName";
    private static final String TEMP_IMAGE_FRONT_FILE_NAME = "loyaltyCardTempImageFrontFileName";
    private static final String TEMP_IMAGE_BACK_FILE_NAME = "loyaltyCardTempImageBackFileName";

    /**
     * Create a loyalty card object with default values
     */
    public LoyaltyCard() {
        setId(-1);
        setStore("");
        setNote("");
        setValidFrom(null);
        setExpiry(null);
        setBalance(new BigDecimal("0"));
        setBalanceType(null);
        setCardId("");
        setBarcodeId(null);
        setBarcodeType(null);
        setBarcodeEncoding(StandardCharsets.ISO_8859_1);
        setHeaderColor(null);
        setStarStatus(0);
        setLastUsed(Utils.getUnixTime());
        setZoomLevel(100);
        setZoomLevelWidth(100);
        setArchiveStatus(0);
        setHolderName("");
        setHolderAddress("");
        setHolderPhone("");
        setHolderEmail("");
        setBusinessName("");
        setBusinessAddress("");
        setBusinessPhone("");
        setBusinessWebsite("");
        setBusinessEmail("");
        setPersonalDetails("");
        setImageThumbnail(null, null);
        setImageFront(null, null);
        setImageBack(null, null);
    }

    /**
     * Backward-compatible constructor for existing importers and parsers
     */
    public LoyaltyCard(final int id, final String store, final String note, @Nullable final Date validFrom,
                       @Nullable final Date expiry, final BigDecimal balance, @Nullable final Currency balanceType,
                       final String cardId, @Nullable final String barcodeId, @Nullable final CatimaBarcode barcodeType,
                       @NonNull final Charset barcodeEncoding, @Nullable final Integer headerColor, final int starStatus,
                       final long lastUsed, final int zoomLevel, final int zoomLevelWidth, final int archiveStatus,
                       @Nullable Bitmap imageThumbnail, @Nullable String imageThumbnailPath,
                       @Nullable Bitmap imageFront, @Nullable String imageFrontPath,
                       @Nullable Bitmap imageBack, @Nullable String imageBackPath) {
        this(id, store, note, validFrom, expiry, balance, balanceType, cardId, barcodeId, barcodeType,
                barcodeEncoding, headerColor, starStatus, lastUsed, zoomLevel, zoomLevelWidth, archiveStatus,
                "", "", "", "", "", "", "", "", "", "",
                imageThumbnail, imageThumbnailPath, imageFront, imageFrontPath, imageBack, imageBackPath);
    }

    /**
     * Full constructor including cardholder, business, and personal fields
     */
    public LoyaltyCard(final int id, final String store, final String note, @Nullable final Date validFrom,
                       @Nullable final Date expiry, final BigDecimal balance, @Nullable final Currency balanceType,
                       final String cardId, @Nullable final String barcodeId, @Nullable final CatimaBarcode barcodeType,
                       @NonNull final Charset barcodeEncoding, @Nullable final Integer headerColor, final int starStatus,
                       final long lastUsed, final int zoomLevel, final int zoomLevelWidth, final int archiveStatus,
                       @NonNull final String holderName, @NonNull final String holderAddress,
                       @NonNull final String holderPhone, @NonNull final String holderEmail,
                       @NonNull final String businessName, @NonNull final String businessAddress,
                       @NonNull final String businessPhone, @NonNull final String businessWebsite,
                       @NonNull final String businessEmail, @NonNull final String personalDetails,
                       @Nullable Bitmap imageThumbnail, @Nullable String imageThumbnailPath,
                       @Nullable Bitmap imageFront, @Nullable String imageFrontPath,
                       @Nullable Bitmap imageBack, @Nullable String imageBackPath) {
        setId(id);
        setStore(store);
        setNote(note);
        setValidFrom(validFrom);
        setExpiry(expiry);
        setBalance(balance);
        setBalanceType(balanceType);
        setCardId(cardId);
        setBarcodeId(barcodeId);
        setBarcodeType(barcodeType);
        setBarcodeEncoding(barcodeEncoding);
        setHeaderColor(headerColor);
        setStarStatus(starStatus);
        setLastUsed(lastUsed);
        setZoomLevel(zoomLevel);
        setZoomLevelWidth(zoomLevelWidth);
        setArchiveStatus(archiveStatus);
        setHolderName(holderName);
        setHolderAddress(holderAddress);
        setHolderPhone(holderPhone);
        setHolderEmail(holderEmail);
        setBusinessName(businessName);
        setBusinessAddress(businessAddress);
        setBusinessPhone(businessPhone);
        setBusinessWebsite(businessWebsite);
        setBusinessEmail(businessEmail);
        setPersonalDetails(personalDetails);
        setImageThumbnail(imageThumbnail, imageThumbnailPath);
        setImageFront(imageFront, imageFrontPath);
        setImageBack(imageBack, imageBackPath);
    }

    @Nullable
    public Bitmap getImageThumbnail(Context context) {
        if (imageThumbnailPath != null) {
            if (imageThumbnailPath.equals(TEMP_IMAGE_THUMBNAIL_FILE_NAME)) {
                imageThumbnail = Utils.loadTempImage(context, imageThumbnailPath);
            } else {
                imageThumbnail = Utils.retrieveCardImage(context, imageThumbnailPath);
            }
            imageThumbnailPath = null;
        }

        if (imageThumbnail == null) {
            return null;
        }

        return imageThumbnail.copy(imageThumbnail.getConfig(), imageThumbnail.isMutable());
    }

    @Nullable
    public Bitmap getImageFront(Context context) {
        if (imageFrontPath != null) {
            if (imageFrontPath.equals(TEMP_IMAGE_FRONT_FILE_NAME)) {
                imageFront = Utils.loadTempImage(context, imageFrontPath);
            } else {
                imageFront = Utils.retrieveCardImage(context, imageFrontPath);
            }
            imageFrontPath = null;
        }

        if (imageFront == null) {
            return null;
        }

        return imageFront.copy(imageFront.getConfig(), imageFront.isMutable());
    }

    @Nullable
    public Bitmap getImageBack(Context context) {
        if (imageBackPath != null) {
            if (imageBackPath.equals(TEMP_IMAGE_BACK_FILE_NAME)) {
                imageBack = Utils.loadTempImage(context, imageBackPath);
            } else {
                imageBack = Utils.retrieveCardImage(context, imageBackPath);
            }
            imageBackPath = null;
        }

        if (imageBack == null) {
            return null;
        }

        return imageBack.copy(imageBack.getConfig(), imageBack.isMutable());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStore(@NonNull String store) {
        this.store = store;
    }

    public void setNote(@NonNull String note) {
        this.note = note;
    }

    public void setValidFrom(@Nullable Date validFrom) {
        this.validFrom = validFrom;
    }

    public void setExpiry(@Nullable Date expiry) {
        this.expiry = expiry;
    }

    public void setBalance(@NonNull BigDecimal balance) {
        this.balance = balance;
    }

    public void setBalanceType(@Nullable Currency balanceType) {
        this.balanceType = balanceType;
    }

    public void setCardId(@NonNull String cardId) {
        this.cardId = cardId;
    }

    public void setBarcodeId(@Nullable String barcodeId) {
        this.barcodeId = barcodeId;
    }

    public void setBarcodeType(@Nullable CatimaBarcode barcodeType) {
        this.barcodeType = barcodeType;
    }

    public void setBarcodeEncoding(@NonNull Charset barcodeEncoding) {
        this.barcodeEncoding = barcodeEncoding;
    }

    public void setHeaderColor(@Nullable Integer headerColor) {
        this.headerColor = headerColor;
    }

    public void setStarStatus(int starStatus) {
        if (starStatus != 0 && starStatus != 1) {
            throw new IllegalArgumentException("starStatus must be 0 or 1");
        }

        this.starStatus = starStatus;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }

    public void setZoomLevel(int zoomLevel) {
        if (zoomLevel < 0 || zoomLevel > 100) {
            throw new IllegalArgumentException("zoomLevel must be in range 0-100");
        }

        this.zoomLevel = zoomLevel;
    }

    public void setZoomLevelWidth(int zoomLevelWidth) {
        if (zoomLevelWidth < 0 || zoomLevelWidth > 100) {
            throw new IllegalArgumentException("zoomLevelWidth must be in range 0-100");
        }

        this.zoomLevelWidth = zoomLevelWidth;
    }

    public void setArchiveStatus(int archiveStatus) {
        if (archiveStatus != 0 && archiveStatus != 1) {
            throw new IllegalArgumentException("archiveStatus must be 0 or 1");
        }

        this.archiveStatus = archiveStatus;
    }

    public void setHolderName(@NonNull String holderName) {
        this.holderName = holderName;
    }

    public void setHolderAddress(@NonNull String holderAddress) {
        this.holderAddress = holderAddress;
    }

    public void setHolderPhone(@NonNull String holderPhone) {
        this.holderPhone = holderPhone;
    }

    public void setHolderEmail(@NonNull String holderEmail) {
        this.holderEmail = holderEmail;
    }

    public void setBusinessName(@NonNull String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessAddress(@NonNull String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setBusinessPhone(@NonNull String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public void setBusinessWebsite(@NonNull String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }

    public void setBusinessEmail(@NonNull String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public void setPersonalDetails(@NonNull String personalDetails) {
        this.personalDetails = personalDetails;
    }

    public boolean hasExtraDetails() {
        return !holderName.isEmpty() || !holderAddress.isEmpty() || !holderPhone.isEmpty() || !holderEmail.isEmpty()
                || !businessName.isEmpty() || !businessAddress.isEmpty() || !businessPhone.isEmpty()
                || !businessWebsite.isEmpty() || !businessEmail.isEmpty() || !personalDetails.isEmpty();
    }

    public void setImageThumbnail(@Nullable Bitmap imageThumbnail, @Nullable String imageThumbnailPath) {
        if (imageThumbnail != null && imageThumbnailPath != null) {
            throw new IllegalArgumentException("Cannot set both thumbnail and path");
        }

        this.imageThumbnailPath = imageThumbnailPath;
        this.imageThumbnail = imageThumbnail != null ? imageThumbnail.copy(imageThumbnail.getConfig(), imageThumbnail.isMutable()) : null;
    }

    public void setImageFront(@Nullable Bitmap imageFront, @Nullable String imageFrontPath) {
        if (imageFront != null && imageFrontPath != null) {
            throw new IllegalArgumentException("Cannot set both thumbnail and path");
        }

        this.imageFrontPath = imageFrontPath;
        this.imageFront = imageFront != null ? imageFront.copy(imageFront.getConfig(), imageFront.isMutable()) : null;
    }

    public void setImageBack(@Nullable Bitmap imageBack, @Nullable String imageBackPath) {
        if (imageBack != null && imageBackPath != null) {
            throw new IllegalArgumentException("Cannot set both thumbnail and path");
        }

        this.imageBackPath = imageBackPath;
        this.imageBack = imageBack != null ? imageBack.copy(imageBack.getConfig(), imageBack.isMutable()) : null;
    }

    @Nullable
    public Bitmap getImageForImageLocationType(Context context, ImageLocationType imageLocationType) {
        if (imageLocationType == ImageLocationType.icon) {
            return getImageThumbnail(context);
        } else if (imageLocationType == ImageLocationType.front) {
            return getImageFront(context);
        } else if (imageLocationType == ImageLocationType.back) {
            return getImageBack(context);
        }

        throw new IllegalArgumentException("Unknown image location type");
    }

    public void updateFromBundle(@NonNull Bundle bundle, boolean requireFull) {
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_ID)) {
            setId(bundle.getInt(BUNDLE_LOYALTY_CARD_ID));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_ID);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_STORE)) {
            setStore(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_STORE)));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_STORE);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_NOTE)) {
            setNote(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_NOTE)));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_NOTE);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_VALID_FROM)) {
            long tmpValidFrom = bundle.getLong(BUNDLE_LOYALTY_CARD_VALID_FROM);
            setValidFrom(tmpValidFrom > 0 ? new Date(tmpValidFrom) : null);
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_VALID_FROM);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_EXPIRY)) {
            long tmpExpiry = bundle.getLong(BUNDLE_LOYALTY_CARD_EXPIRY);
            setExpiry(tmpExpiry > 0 ? new Date(tmpExpiry) : null);
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_EXPIRY);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BALANCE)) {
            setBalance(new BigDecimal(bundle.getString(BUNDLE_LOYALTY_CARD_BALANCE)));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_BALANCE);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BALANCE_TYPE)) {
            String tmpBalanceType = bundle.getString(BUNDLE_LOYALTY_CARD_BALANCE_TYPE);
            setBalanceType(tmpBalanceType != null ? Currency.getInstance(tmpBalanceType) : null);
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_BALANCE_TYPE);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_CARD_ID)) {
            setCardId(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_CARD_ID)));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_CARD_ID);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BARCODE_ID)) {
            setBarcodeId(bundle.getString(BUNDLE_LOYALTY_CARD_BARCODE_ID));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_BARCODE_ID);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BARCODE_TYPE)) {
            String tmpBarcodeType = bundle.getString(BUNDLE_LOYALTY_CARD_BARCODE_TYPE);
            setBarcodeType(tmpBarcodeType != null ? CatimaBarcode.fromName(tmpBarcodeType) : null);
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_BARCODE_TYPE);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BARCODE_ENCODING)) {
            setBarcodeEncoding(Charset.forName(bundle.getString(BUNDLE_LOYALTY_CARD_BARCODE_ENCODING)));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_BARCODE_ENCODING);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_HEADER_COLOR)) {
            int tmpHeaderColor = bundle.getInt(BUNDLE_LOYALTY_CARD_HEADER_COLOR);
            setHeaderColor(tmpHeaderColor != -1 ? tmpHeaderColor : null);
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_HEADER_COLOR);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_STAR_STATUS)) {
            setStarStatus(bundle.getInt(BUNDLE_LOYALTY_CARD_STAR_STATUS));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_STAR_STATUS);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_LAST_USED)) {
            setLastUsed(bundle.getLong(BUNDLE_LOYALTY_CARD_LAST_USED));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_LAST_USED);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL)) {
            setZoomLevel(bundle.getInt(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_ZOOM_LEVEL);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL_WIDTH)) {
            setZoomLevelWidth(bundle.getInt(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL_WIDTH));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_ZOOM_LEVEL_WIDTH);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_ARCHIVE_STATUS)) {
            setArchiveStatus(bundle.getInt(BUNDLE_LOYALTY_CARD_ARCHIVE_STATUS));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_ARCHIVE_STATUS);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_HOLDER_NAME)) {
            setHolderName(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_HOLDER_NAME)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_HOLDER_ADDRESS)) {
            setHolderAddress(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_HOLDER_ADDRESS)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_HOLDER_PHONE)) {
            setHolderPhone(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_HOLDER_PHONE)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_HOLDER_EMAIL)) {
            setHolderEmail(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_HOLDER_EMAIL)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BUSINESS_NAME)) {
            setBusinessName(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_BUSINESS_NAME)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BUSINESS_ADDRESS)) {
            setBusinessAddress(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_BUSINESS_ADDRESS)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BUSINESS_PHONE)) {
            setBusinessPhone(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_BUSINESS_PHONE)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BUSINESS_WEBSITE)) {
            setBusinessWebsite(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_BUSINESS_WEBSITE)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_BUSINESS_EMAIL)) {
            setBusinessEmail(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_BUSINESS_EMAIL)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_PERSONAL_DETAILS)) {
            setPersonalDetails(Objects.requireNonNull(bundle.getString(BUNDLE_LOYALTY_CARD_PERSONAL_DETAILS)));
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_IMAGE_THUMBNAIL)) {
            setImageThumbnail(null, bundle.getString(BUNDLE_LOYALTY_CARD_IMAGE_THUMBNAIL));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_IMAGE_THUMBNAIL);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_IMAGE_FRONT)) {
            setImageFront(null, bundle.getString(BUNDLE_LOYALTY_CARD_IMAGE_FRONT));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_IMAGE_FRONT);
        }
        if (bundle.containsKey(BUNDLE_LOYALTY_CARD_IMAGE_BACK)) {
            setImageBack(null, bundle.getString(BUNDLE_LOYALTY_CARD_IMAGE_BACK));
        } else if (requireFull) {
            throw new IllegalArgumentException("Missing key " + BUNDLE_LOYALTY_CARD_IMAGE_BACK);
        }
    }

    public Bundle toBundle(Context context, List<String> exportLimit) {
        boolean exportIsLimited = !exportLimit.isEmpty();

        Bundle bundle = new Bundle();

        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_ID)) {
            bundle.putInt(BUNDLE_LOYALTY_CARD_ID, id);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_STORE)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_STORE, store);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_NOTE)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_NOTE, note);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_VALID_FROM)) {
            bundle.putLong(BUNDLE_LOYALTY_CARD_VALID_FROM, validFrom != null ? validFrom.getTime() : -1);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_EXPIRY)) {
            bundle.putLong(BUNDLE_LOYALTY_CARD_EXPIRY, expiry != null ? expiry.getTime() : -1);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_BALANCE)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_BALANCE, balance.toString());
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_BALANCE_TYPE)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_BALANCE_TYPE, balanceType != null ? balanceType.toString() : null);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_CARD_ID)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_CARD_ID, cardId);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_BARCODE_ID)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_BARCODE_ID, barcodeId);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_BARCODE_TYPE)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_BARCODE_TYPE, barcodeType != null ? barcodeType.name() : null);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_BARCODE_ENCODING)) {
            bundle.putString(BUNDLE_LOYALTY_CARD_BARCODE_ENCODING, barcodeEncoding.name());
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_HEADER_COLOR)) {
            bundle.putInt(BUNDLE_LOYALTY_CARD_HEADER_COLOR, headerColor != null ? headerColor : -1);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_STAR_STATUS)) {
            bundle.putInt(BUNDLE_LOYALTY_CARD_STAR_STATUS, starStatus);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_LAST_USED)) {
            bundle.putLong(BUNDLE_LOYALTY_CARD_LAST_USED, lastUsed);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL)) {
            bundle.putInt(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL, zoomLevel);
        }
        if (!exportIsLimited || exportLimit.contains(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL_WIDTH)) {
            bundle.putInt(BUNDLE_LOYALTY_CARD_ZOOM_LEVEL_WIDTH, zoomLevelWidth);
        }
        if (!exportIsLimited || export
