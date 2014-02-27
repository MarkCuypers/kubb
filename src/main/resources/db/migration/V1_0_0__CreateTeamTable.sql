-- -----------------------------------------------------
-- Table `kubb`.`TEAM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kubb`.`TEAM` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
AUTO_INCREMENT = 1;
