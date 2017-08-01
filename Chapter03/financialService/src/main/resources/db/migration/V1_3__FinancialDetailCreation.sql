CREATE TABLE `bank_account_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
   `user_id` char(36) NOT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `account_number` varchar(20) NOT NULL,
  `account_holders_name` varchar(250) NOT NULL,
  `fsc` varchar(20) DEFAULT NULL,
  `account_type` int(11) DEFAULT NULL,
  `created_on` datetime(6) NOT NULL,
  `deleted_on` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE `obligations_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(36) NOT NULL,
  `total_monthly_spending` decimal(10,0) NOT NULL,
  `monthly_income_supports` decimal(10,0) NOT NULL,
  `monthly_emi_payout` decimal(10,0) NOT NULL,
  `created_on` datetime(6) NOT NULL,
  `deleted_on` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Obligations_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;