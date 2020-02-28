ALTER TABLE `notebook`.`cmf_group_member`
ADD COLUMN `telephone` VARCHAR(45) NULL DEFAULT '' AFTER `street_name`;
