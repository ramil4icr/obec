# Obec Tršice
Repozitář pro webové stránky / aplikaci obce (primárně Tršice).

## Changelog
### Verze 1.3.3

* Upraveno třídění položek na úřední desce.
* Upraveno zobrazování položek z úřední desky na stránce rozpočtu,...
* Informační nápis u grafu čerpání rozpočtu, přídán odkaz na muzeum do footeru.

### Verze 1.3

* Ve WYSIWYG editoru se při vložení obrázku vloží i atribut "alt".
* Změněn vzhled menu.
* Graf plnění rozpočtu.
* Stránka s informacemi pro zastupitele (po přihlášení).
* Opravena chyba při přesměrování po přihlášení (mohla nastat jen za určitých okolností).
* Na úvodní stránce nyní načítá 10 položek z archivu hlášení rozhlasu.
* Fotky s odkazem na fotogalerii se načítají až ve chvíli, kdy na ně uživatel naroluje.
* Změněn design (jiné barvy, modernější logo, jiná fotka v záhlaví).

### Verze 1.2

* Zlepšena přístupnost pro znevýhodněné uživatele.
* Nová položka do menu UtilityReport.
* U desktop menu odebrána zbytečná položka úvod.

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

