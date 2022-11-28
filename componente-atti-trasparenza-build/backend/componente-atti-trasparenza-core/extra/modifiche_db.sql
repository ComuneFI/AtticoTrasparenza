-- DANIELE -- AGGIUNTA FINE PUBBLICAZIONE 2019-01-22

ALTER TABLE `componente_atti_trasparenza`.`atto` ADD COLUMN `data_fine_pubblicazione` DATETIME NULL AFTER `data_pubblicazione`;

-- DANIELE -- AGGIUNTA FINE PUBBLICAZIONE 2019-01-22


-- GIORGIO: stato annullamento per Revoca, ecc.
ALTER TABLE `componente_atti_trasparenza`.`atto` 
ADD COLUMN `stato_annullamento` VARCHAR(3) NULL AFTER `tipo_atto_id`;

-- EMANUELE: campi aggiuntivi per parere o votazione
CREATE TABLE `componente_atti_trasparenza`.`parere` (
  `id` BIGINT(20) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `data_invio` DATE NULL,
  `data_scadenza` DATE NULL,
  `data_parere` DATE NULL,
  `testo` VARCHAR(500) NULL,
  `id_atto` BIGINT(20) NULL,
  PRIMARY KEY (`id`));

  
ALTER TABLE `componente_atti_trasparenza`.`parere` 
ADD INDEX `fk_atto_idx` (`id_atto` ASC);
ALTER TABLE `componente_atti_trasparenza`.`parere` 
ADD CONSTRAINT `fk_atto`
  FOREIGN KEY (`id_atto`)
  REFERENCES `componente_atti_trasparenza`.`atto` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

 ALTER TABLE `componente_atti_trasparenza`.`parere` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;
  
  
ALTER TABLE `componente_atti_trasparenza`.`atto` 
DROP COLUMN `commissione`;

ALTER TABLE `componente_atti_trasparenza`.`atto` 
ADD COLUMN `num_astenuti` INT NULL AFTER `stato_annullamento`,
ADD COLUMN `nomi_astenuti` VARCHAR(1000) NULL AFTER `num_astenuti`,
ADD COLUMN `num_npv` INT NULL AFTER `nomi_astenuti`,
ADD COLUMN `nomi_npv` VARCHAR(1000) NULL AFTER `num_npv`,
ADD COLUMN `num_contrari` INT NULL AFTER `nomi_npv`,
ADD COLUMN `nomi_contrari` VARCHAR(1000) NULL AFTER `num_contrari`,
ADD COLUMN `num_favorevoli` INT NULL AFTER `nomi_contrari`,
ADD COLUMN `nomi_favorevoli` VARCHAR(1000) NULL AFTER `num_favorevoli`;

ALTER TABLE `componente_atti_trasparenza`.`atto` 
ADD COLUMN `num_presenti` INT NULL;

-- GIORGIO: estremi atto revoca
ALTER TABLE `componente_atti_trasparenza`.`atto` 
ADD COLUMN `numero_atto_annullamento` VARCHAR(50) NULL AFTER `num_presenti`,
ADD COLUMN `data_atto_annullamento` DATETIME NULL AFTER `numero_atto_annullamento`,
ADD COLUMN `tipo_atto_annullamento` VARCHAR(255) NULL AFTER `data_atto_annullamento`;

-- 20220308
ALTER TABLE componente_atti_trasparenza.atto ADD visibilita_documenti tinyint(1) DEFAULT 1 NULL;
UPDATE componente_atti_trasparenza.atto SET visibilita_documenti = 0 WHERE stato_annullamento = 'R';
ALTER TABLE componente_atti_trasparenza.atto ADD COLUMN descrizione_stato_annullamento VARCHAR(255) NULL AFTER stato_annullamento;
ALTER TABLE componente_atti_trasparenza.atto MODIFY COLUMN stato_annullamento varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL;


