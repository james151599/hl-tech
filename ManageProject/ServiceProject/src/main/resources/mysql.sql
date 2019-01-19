alter database testdb character set utf8;
--alter table exam_info convert to character set utf8;

CREATE TABLE `exam_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `score` int(3) DEFAULT NULL,
  `img` longblob,
  PRIMARY KEY (`id`)
)