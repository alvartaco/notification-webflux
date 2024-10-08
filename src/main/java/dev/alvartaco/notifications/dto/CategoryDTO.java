package dev.alvartaco.notifications.dto;

public class CategoryDTO {

    private Short categoryId;
    private String categoryName;

    public CategoryDTO(Short categoryId, String categoryName) {
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
