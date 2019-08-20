# Obec Tršice
Repozitář pro webové stránky / aplikaci obce (primárně Tršice).

## Changelog
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

