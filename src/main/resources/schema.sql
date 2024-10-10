--
-- Dropping tables at the beginning if the exist.
--
drop TABLE IF EXISTS Message;
drop TABLE IF EXISTS Category;

--
--  Category.java
--    private Short categoryId;
--    private String categoryName;
--
-- Future Enhancement Fields.
--    private String locCategoryNameKey;
--    private Integer categoryOrder;
--    private LocalDateTime createDateTime;
--    private LocalDateTime modifiedDateTime;
--    private Integer userId;
--    private boolean categoryEnabled;
--
create table if not exists Category (
   category_id smallint AUTO_INCREMENT not null,
   category_name varchar(30) not null,
   primary key (category_id)
);

--
--  Message.java
--          Integer messageId,
--          @NotNull
--          Category category,
--          @NotEmpty
--          String messageBody,
--          @NotEmpty
--          LocalDateTime createdOn
--
-- Future Enhancement Fields.
--    private LocalDateTime modifiedDateTime;
--    private Integer userId;
--
create table if not exists Message (
   message_id int AUTO_INCREMENT not null,
   category_id smallint not null,
   message_body text not null,
   message_created_on timestamp not null,
   primary key (message_id),
   constraint fk_category
      foreign key(category_id)
        references category(category_id)
);

--
-- Tables indexes creation.
-- They depends on DB Engine used
--
-- I relly on PK indexes for H2
-- Needed for Postgres
-- CREATE INDEX idx_category_category_id ON category(category_id);
-- CREATE INDEX idx_message_message_id ON message(message_id);
--
-- I trust also in FK Index for H2
-- Needed for Postgres
-- CREATE INDEX idx_message_category_id ON message(category_id);
--
-- Because queries look at this columns for
-- retrieval and sorting
--
create index idx_category_category_name on category(category_name);
create index idx_message_message_created_on on message(message_created_on);