-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-09-04 23:49:26.296

-- tables
-- Table: cuisine_type
CREATE TABLE cuisine_type (
                              id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                              type_name varchar(50)  NOT NULL,
                              CONSTRAINT cuisine_type_ak_1 UNIQUE (type_name),
                              CONSTRAINT cuisine_type_pk PRIMARY KEY (id)
);

-- Table: ingredient
CREATE TABLE ingredient (
                            id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                            ingredient_name varchar(50)  NOT NULL,
                            CONSTRAINT ingredient_ak_1 UNIQUE (ingredient_name),
                            CONSTRAINT ingredient_pk PRIMARY KEY (id)
);

-- Table: instruction
CREATE TABLE instruction (
                             id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                             instruction varchar(255)  NOT NULL,
                             CONSTRAINT instruction_pk PRIMARY KEY (id)
);

-- Table: recipe
CREATE TABLE recipe (
                        id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                        cuisine_type_id int  NOT NULL,
                        instruction_id int  NOT NULL,
                        recipe_name varchar(50)  NOT NULL,
                        date_added date  NOT NULL,
                        description varchar(255)  NOT NULL,
                        CONSTRAINT recipe_pk PRIMARY KEY (id)
);

-- Table: recipes_ingredients
CREATE TABLE recipe_ingredients (
                                     id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                                     ingredient_id int  NOT NULL,
                                     recipe_id int  NOT NULL,
                                     CONSTRAINT recipe_ingredients_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: recipe_cuisine_type (table: recipe)
ALTER TABLE recipe ADD CONSTRAINT recipe_cuisine_type
    FOREIGN KEY (cuisine_type_id)
        REFERENCES cuisine_type (id);

-- Reference: recipe_instruction (table: recipe)
ALTER TABLE recipe ADD CONSTRAINT recipe_instruction
    FOREIGN KEY (instruction_id)
        REFERENCES instruction (id);

-- Reference: recipes_ingredients_ingredient (table: recipes_ingredients)
ALTER TABLE recipe_ingredients ADD CONSTRAINT recipe_ingredients_ingredient
    FOREIGN KEY (ingredient_id)
        REFERENCES ingredient (id);

-- Reference: recipes_ingredients_recipe (table: recipes_ingredients)
ALTER TABLE recipe_ingredients ADD CONSTRAINT recipe_ingredients_recipe
    FOREIGN KEY (recipe_id)
        REFERENCES recipe (id);

-- End of file.

