DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS Category;

--
--  Category.java
--    private Integer categoryId;
--    private String categoryName;
--
-- Future Enhancement Fields.
--    private String locCategoryNameKey;
--    private Integer categoryOrder;
--    private LocalDateTime createDateTime;
--    private LocalDateTime modifiedDateTime;
--    private long userId;
--    private boolean categoryEnabled;
--
create TABLE IF NOT EXISTS Category (
   category_id smallint NOT NULL,
   category_name varchar(30) NOT NULL,
   PRIMARY KEY (category_id)
);

--
--  Message.java
--        @Id
--        Long messageId,
--        @NotNull
--        Category category,
--       @NotEmpty
--        String messageBody,
--        @Version
--        Integer messageVersion
--
-- Future Enhancement Fields.
--    private LocalDateTime createDateTime;
--    private LocalDateTime modifiedDateTime;
--    private long userId;
--
create TABLE IF NOT EXISTS Message (
   message_id bigint NOT NULL,
   category_id int NOT NULL,
   message_body TEXT NOT NULL,
   message_version int NOT NULL,
   PRIMARY KEY (message_id),
   CONSTRAINT fk_category
      FOREIGN KEY(category_id)
        REFERENCES category(category_id)
);
