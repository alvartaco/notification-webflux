package dev.alvartaco.notifications.model;

import java.util.List;

/**
 * It specifies the Category / channel through which the Users
 * would like to be notified, such as SMS, Email or Push Notification.
 * There will be three message categories:
 * • Sports
 * • Finance
 * • Movies
 *
 *     Future Enhancement Fields.
 *
 *     private String locCategoryNameKey;
 *     private Integer categoryOrder;
 *     private LocalDateTime createDateTime;
 *     private LocalDateTime modifiedDateTime;
 *     private Integer userId;
 *     private boolean categoryEnabled;
 */
public class Category {

    private Short categoryId;
    private String categoryName;

    public Category(Short categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }
}
