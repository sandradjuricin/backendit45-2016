INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES(nextval('smer_seq'), 'Injz informacionih sistema', 'IT');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES(nextval('smer_seq'), 'Graficko injzenjerstvo', 'GR');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES(nextval('smer_seq'), 'Mehatronika', 'MX');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES(nextval('smer_seq'), 'Arhitektura', 'AR');
INSERT INTO "smer"("id", "naziv", "oznaka")
VALUES(-100, 'Injz informacionih sistema', 'ITTTT');

INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES(nextval('projekat_seq'), 'Upravljanje projektima', 'UP','Upravljanje i kreiranje projekata');
INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES(nextval('projekat_seq'), 'Resursi', 'R','Upravljanje resursima');
INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES(nextval('projekat_seq'), 'Obrada podataka', 'OP','Obrada svih podataka');
INSERT INTO "projekat"("id", "naziv", "oznaka", "opis")
VALUES(-100, 'test','test','test');


INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES(nextval('grupa_seq'), 'prva', 3);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES(nextval('grupa_seq'), 'druga', 3);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES(nextval('grupa_seq'), 'prva', 2);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES(nextval('grupa_seq'), 'druga', 2);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES(nextval('grupa_seq'), 'treca', 3);
INSERT INTO "grupa"("id", "oznaka", "smer")
VALUES(-100, 'test', 3);

	   
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Sasa', 'Petrovic','45-2016',1,2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Sandra', 'Petrovic','46-2016',1,2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Marija', 'Kesic','56-2016',2,2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Vesna', 'Misic','42-2016',3,1);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Dejan', 'Dejanovic','23-2016',3,1);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Marko', 'Peric','14-2016',1,3);

INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES(nextval('student_seq'), 'Milica', 'Tobic','3-2016',1,3);
	   
	   
	   
	   