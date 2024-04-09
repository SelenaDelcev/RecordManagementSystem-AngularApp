--Liga podaci--
insert into liga(id, naziv, oznaka)
values (nextval('liga_seq'), 'Prva ženska liga', 'I ŽLS');

insert into liga(id, naziv, oznaka)
values (nextval('liga_seq'), 'Druga ženska liga', 'II ŽLS');

insert into liga(id, naziv, oznaka)
values (nextval('liga_seq'), 'Kadetska liga', 'KAD');

insert into liga(id, naziv, oznaka)
values (nextval('liga_seq'), 'Regionalna liga Sever', 'ŽRL Sever');

insert into liga(id, naziv, oznaka)
values (nextval('liga_seq'), 'Regionalna liga Jug', 'ŽRL Jug');

--Nacionalnost podaci--
insert into nacionalnost(id, naziv, skracenica)
values (nextval('nacionalnost_seq'), 'srpska', 'SRP');

insert into nacionalnost(id, naziv, skracenica)
values (nextval('nacionalnost_seq'), 'ruska', 'RUS');

insert into nacionalnost(id, naziv, skracenica)
values (nextval('nacionalnost_seq'), 'američka', 'USA');

insert into nacionalnost(id, naziv, skracenica)
values (nextval('nacionalnost_seq'), 'bugarska', 'BUG');

insert into nacionalnost(id, naziv, skracenica)
values (nextval('nacionalnost_seq'), 'makedonska', 'MAK');

insert into nacionalnost(id, naziv, skracenica)
values (nextval('nacionalnost_seq'), 'hrvatska', 'HRV');

--Tim podaci--
insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Crvena Zvezda', to_date('04.03.1945.', 'dd.mm.yyyy.'), 'Beograd', 1);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Partizan', to_date('24.06.1953.', 'dd.mm.yyyy.'), 'Beograd', 1);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Vojvodina 021', to_date('02.08.1999.', 'dd.mm.yyyy.'), 'Novi Sad', 1);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Duga', to_date('01.04.2015.', 'dd.mm.yyyy.'), 'Šabac', 1);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Proleter 023', to_date('16.10.2015.', 'dd.mm.yyyy.'), 'Zrenjanin', 2);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Srbobran', to_date('29.01.2005.', 'dd.mm.yyyy.'), 'Srbobran', 2);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Gimnazijalac Tigar', to_date('08.04.2010.', 'dd.mm.yyyy.'), 'Pirot', 2);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Vizura', to_date('21.10.2018.', 'dd.mm.yyyy.'), 'Zemun', 4);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Zvečan', to_date('11.05.2008.', 'dd.mm.yyyy.'), 'Zvečan', 5);

insert into tim(id, naziv, osnovan, sediste, liga)
values (nextval('tim_seq'), 'Kraljevo', to_date('18.08.2013.', 'dd.mm.yyyy.'), 'Kraljevo', 5);

--Igrac podaci--
insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Ivon', 'Anderson', '12', to_date('08.03.1990.', 'dd.mm.yyyy.'), 3, 1);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Sara', 'Vasiljević', '2', to_date('24.01.2004.', 'dd.mm.yyyy.'), 1, 2);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Tara', 'Biočanin', '22', to_date('13.11.2004.', 'dd.mm.yyyy.'), 1, 1);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Saša', 'Čađo', '6', to_date('13.07.1989.', 'dd.mm.yyyy.'), 2, 3);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Sofija', 'Olić', '55', to_date('25.09.2000.', 'dd.mm.yyyy.'), 1, 2);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Tamara', 'Ivkov', '10', to_date('15.12.2001.', 'dd.mm.yyyy.'), 3, 4);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Minela', 'Mehović', '2', to_date('29.03.2001.', 'dd.mm.yyyy.'), 6, 3);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Ana', 'Ilić', '15', to_date('02.03.1997.', 'dd.mm.yyyy.'), 5, 5);

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (nextval('igrac_seq'), 'Jovana', 'Spasovski', '11', to_date('02.04.2004.', 'dd.mm.yyyy.'), 4, 4);

--Test--
insert into liga(id, naziv, oznaka)
values (-100, 'test', 'test');

insert into nacionalnost(id, naziv, skracenica)
values (-100, 'test', 'test');

insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim)
values (-100, 'test', 'test', '555', to_date('28.03.2022.', 'dd.mm.yyyy.'), 3, 4);

insert into tim(id, naziv, osnovan, sediste, liga)
values (-100, 'test', to_date('28.03.2022.', 'dd.mm.yyyy.'), 'test', 2);



