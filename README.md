# Obec Tršice
Repozitář pro webové stránky / aplikaci obce (primárně Tršice).

## Changelog
### Verze 1.1

* Opraveno generování ID pro článek v aktualitě.
* Kliknutím na logo obce přejít na úvodní stránku.
* RSS pro aktuality.
* RSS pro hlášení rozhlasu.
* Generování iCal exportu pro akce.
* U článků nepovinná pole pro obrázek a popis (kvůli OpenGraph).
* V administraci obecné nastavení.
* Několik menších oprav / vylepšení.

### Verze 1.0

* První dostupná verze.

## Nastavení

Je třeba nastavit správný resource pro přístup k databázi. Aplikace očekává, že bude nastaveno _zpravodajdb_. Toto je možné nastavit buď v souboru _/WEB-INF/resources.xml_ nebo přímo v _tomee.xml_ souboru. Zde je příklad nastavení pro HSQL databázi - v produkci pravděpodobně použijete jinou.

```
<Resource id="obecdb" type="DataSource">
JdbcDriver = org.hsqldb.jdbcDriver
JdbcUrl = jdbc:hsqldb:file:hsqldb
</Resource>
```

